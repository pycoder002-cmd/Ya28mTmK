package io.sentry.connection;

import io.sentry.environment.SentryEnvironment;
import io.sentry.event.Event;
import io.sentry.util.Util;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes2.dex */
public abstract class AbstractConnection implements Connection {
    public static final String SENTRY_PROTOCOL_VERSION = "6";
    private final String authHeader;
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) AbstractConnection.class);
    private static final Logger lockdownLogger = LoggerFactory.getLogger(AbstractConnection.class.getName() + ".lockdown");
    private LockdownManager lockdownManager = new LockdownManager();
    private Set<EventSendCallback> eventSendCallbacks = new HashSet();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractConnection(String str, String str2) {
        String str3;
        StringBuilder sb = new StringBuilder();
        sb.append("Sentry sentry_version=6,sentry_client=");
        sb.append(SentryEnvironment.getSentryName());
        sb.append(",");
        sb.append("sentry_key=");
        sb.append(str);
        if (Util.isNullOrEmpty(str2)) {
            str3 = "";
        } else {
            str3 = ",sentry_secret=" + str2;
        }
        sb.append(str3);
        this.authHeader = sb.toString();
    }

    @Override // io.sentry.connection.Connection
    public void addEventSendCallback(EventSendCallback eventSendCallback) {
        this.eventSendCallbacks.add(eventSendCallback);
    }

    protected abstract void doSend(Event event) throws ConnectionException;

    /* JADX INFO: Access modifiers changed from: protected */
    public String getAuthHeader() {
        return this.authHeader;
    }

    @Override // io.sentry.connection.Connection
    public final void send(Event event) throws ConnectionException {
        try {
            if (this.lockdownManager.isLockedDown()) {
                throw new LockedDownException();
            }
            doSend(event);
            this.lockdownManager.unlock();
            for (EventSendCallback eventSendCallback : this.eventSendCallbacks) {
                try {
                    eventSendCallback.onSuccess(event);
                } catch (Exception e) {
                    logger.warn("An exception occurred while running an EventSendCallback.onSuccess: " + eventSendCallback.getClass().getName(), (Throwable) e);
                }
            }
        } catch (ConnectionException e2) {
            for (EventSendCallback eventSendCallback2 : this.eventSendCallbacks) {
                try {
                    eventSendCallback2.onFailure(event, e2);
                } catch (Exception e3) {
                    logger.warn("An exception occurred while running an EventSendCallback.onFailure: " + eventSendCallback2.getClass().getName(), (Throwable) e3);
                }
            }
            if (this.lockdownManager.lockdown(e2)) {
                lockdownLogger.warn("Initiated a temporary lockdown because of exception: " + e2.getMessage());
            }
            throw e2;
        }
    }
}
