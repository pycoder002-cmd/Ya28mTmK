package io.sentry;

import io.sentry.connection.Connection;
import io.sentry.connection.EventSendCallback;
import io.sentry.connection.LockedDownException;
import io.sentry.connection.TooManyRequestsException;
import io.sentry.context.Context;
import io.sentry.context.ContextManager;
import io.sentry.event.Event;
import io.sentry.event.EventBuilder;
import io.sentry.event.helper.EventBuilderHelper;
import io.sentry.event.helper.ShouldSendEventCallback;
import io.sentry.event.interfaces.ExceptionInterface;
import io.sentry.util.Util;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes.dex */
public class SentryClient {
    private final Connection connection;
    private final ContextManager contextManager;
    protected String dist;
    protected String environment;
    protected String release;
    protected String serverName;
    private SentryUncaughtExceptionHandler uncaughtExceptionHandler;
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) SentryClient.class);
    private static final Logger lockdownLogger = LoggerFactory.getLogger(SentryClient.class.getName() + ".lockdown");
    protected Map<String, String> tags = new HashMap();
    protected Set<String> mdcTags = new HashSet();
    protected Map<String, Object> extra = new HashMap();
    private final Set<ShouldSendEventCallback> shouldSendEventCallbacks = new HashSet();
    private final List<EventBuilderHelper> builderHelpers = new CopyOnWriteArrayList();

    public SentryClient(Connection connection, ContextManager contextManager) {
        this.connection = connection;
        this.contextManager = contextManager;
    }

    public void addBuilderHelper(EventBuilderHelper eventBuilderHelper) {
        logger.debug("Adding '{}' to the list of builder helpers.", eventBuilderHelper);
        this.builderHelpers.add(eventBuilderHelper);
    }

    public void addEventSendCallback(EventSendCallback eventSendCallback) {
        this.connection.addEventSendCallback(eventSendCallback);
    }

    public void addExtra(String str, Object obj) {
        this.extra.put(str, obj);
    }

    @Deprecated
    public void addExtraTag(String str) {
        addMdcTag(str);
    }

    public void addMdcTag(String str) {
        this.mdcTags.add(str);
    }

    public void addShouldSendEventCallback(ShouldSendEventCallback shouldSendEventCallback) {
        this.shouldSendEventCallbacks.add(shouldSendEventCallback);
    }

    public void addTag(String str, String str2) {
        this.tags.put(str, str2);
    }

    public void clearContext() {
        this.contextManager.clear();
    }

    public void closeConnection() {
        if (this.uncaughtExceptionHandler != null) {
            this.uncaughtExceptionHandler.disable();
        }
        try {
            this.connection.close();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't close the Sentry connection", e);
        }
    }

    public List<EventBuilderHelper> getBuilderHelpers() {
        return Collections.unmodifiableList(this.builderHelpers);
    }

    public Context getContext() {
        return this.contextManager.getContext();
    }

    public String getDist() {
        return this.dist;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public Map<String, Object> getExtra() {
        return this.extra;
    }

    public Set<String> getMdcTags() {
        return Collections.unmodifiableSet(this.mdcTags);
    }

    public String getRelease() {
        return this.release;
    }

    public String getServerName() {
        return this.serverName;
    }

    public Map<String, String> getTags() {
        return Collections.unmodifiableMap(this.tags);
    }

    public void removeBuilderHelper(EventBuilderHelper eventBuilderHelper) {
        logger.debug("Removing '{}' from the list of builder helpers.", eventBuilderHelper);
        this.builderHelpers.remove(eventBuilderHelper);
    }

    public void runBuilderHelpers(EventBuilder eventBuilder) {
        Iterator<EventBuilderHelper> it = this.builderHelpers.iterator();
        while (it.hasNext()) {
            it.next().helpBuildingEvent(eventBuilder);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.util.UUID] */
    public void sendEvent(Event event) {
        ShouldSendEventCallback next;
        Iterator<ShouldSendEventCallback> it = this.shouldSendEventCallbacks.iterator();
        do {
            try {
                if (!it.hasNext()) {
                    try {
                        this.connection.send(event);
                    } catch (LockedDownException | TooManyRequestsException unused) {
                        logger.debug("Dropping an Event due to lockdown: " + event);
                    } catch (Exception e) {
                        logger.error("An exception occurred while sending the event to Sentry.", (Throwable) e);
                    }
                    return;
                }
                next = it.next();
            } finally {
                getContext().setLastEventId(event.getId());
            }
        } while (next.shouldSend(event));
        logger.trace("Not sending Event because of ShouldSendEventCallback: {}", next);
    }

    public void sendEvent(EventBuilder eventBuilder) {
        if (!Util.isNullOrEmpty(this.release)) {
            eventBuilder.withRelease(this.release.trim());
            if (!Util.isNullOrEmpty(this.dist)) {
                eventBuilder.withDist(this.dist.trim());
            }
        }
        if (!Util.isNullOrEmpty(this.environment)) {
            eventBuilder.withEnvironment(this.environment.trim());
        }
        if (!Util.isNullOrEmpty(this.serverName)) {
            eventBuilder.withServerName(this.serverName.trim());
        }
        for (Map.Entry<String, String> entry : this.tags.entrySet()) {
            eventBuilder.withTag(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Object> entry2 : this.extra.entrySet()) {
            eventBuilder.withExtra(entry2.getKey(), entry2.getValue());
        }
        runBuilderHelpers(eventBuilder);
        sendEvent(eventBuilder.build());
    }

    public void sendException(Throwable th) {
        sendEvent(new EventBuilder().withMessage(th.getMessage()).withLevel(Event.Level.ERROR).withSentryInterface(new ExceptionInterface(th)));
    }

    public void sendMessage(String str) {
        sendEvent(new EventBuilder().withMessage(str).withLevel(Event.Level.INFO));
    }

    public void setDist(String str) {
        this.dist = str;
    }

    public void setEnvironment(String str) {
        this.environment = str;
    }

    public void setExtra(Map<String, Object> map) {
        if (map == null) {
            this.extra = new HashMap();
        } else {
            this.extra = map;
        }
    }

    @Deprecated
    public void setExtraTags(Set<String> set) {
        setMdcTags(set);
    }

    public void setMdcTags(Set<String> set) {
        if (set == null) {
            this.mdcTags = new HashSet();
        } else {
            this.mdcTags = set;
        }
    }

    public void setRelease(String str) {
        this.release = str;
    }

    public void setServerName(String str) {
        this.serverName = str;
    }

    public void setTags(Map<String, String> map) {
        if (map == null) {
            this.tags = new HashMap();
        } else {
            this.tags = map;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setupUncaughtExceptionHandler() {
        this.uncaughtExceptionHandler = SentryUncaughtExceptionHandler.setup();
    }

    public String toString() {
        return "SentryClient{release='" + this.release + "', dist='" + this.dist + "', environment='" + this.environment + "', serverName='" + this.serverName + "', tags=" + this.tags + ", mdcTags=" + this.mdcTags + ", extra=" + this.extra + ", connection=" + this.connection + ", builderHelpers=" + this.builderHelpers + ", contextManager=" + this.contextManager + ", uncaughtExceptionHandler=" + this.uncaughtExceptionHandler + '}';
    }
}
