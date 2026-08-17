package io.sentry.event;

import io.sentry.event.interfaces.SentryInterface;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes2.dex */
public class Event implements Serializable {
    private static final Logger _logger = LoggerFactory.getLogger((Class<?>) Event.class);
    private String checksum;
    private String culprit;
    private String dist;
    private String environment;
    private List<String> fingerprint;
    private final UUID id;
    private Level level;
    private String logger;
    private String message;
    private String platform;
    private String release;
    private Sdk sdk;
    private String serverName;
    private Date timestamp;
    private String transaction;
    private Map<String, String> tags = new HashMap();
    private List<Breadcrumb> breadcrumbs = new ArrayList();
    private Map<String, Map<String, Object>> contexts = new HashMap();
    private transient Map<String, Object> extra = new HashMap();
    private Map<String, SentryInterface> sentryInterfaces = new HashMap();

    /* loaded from: classes2.dex */
    public enum Level {
        FATAL,
        ERROR,
        WARNING,
        INFO,
        DEBUG
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Event(UUID uuid) {
        if (uuid == null) {
            throw new IllegalArgumentException("The id can't be null");
        }
        this.id = uuid;
    }

    private static HashMap<String, ? super Serializable> convertToSerializable(Map<String, Object> map) {
        HashMap<String, ? super Serializable> hashMap = new HashMap<>(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() == null) {
                hashMap.put(entry.getKey(), (String) null);
            } else if (entry.getValue() instanceof Serializable) {
                hashMap.put(entry.getKey(), (Serializable) entry.getValue());
            } else {
                hashMap.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return hashMap;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.extra = (Map) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(convertToSerializable(this.extra));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.id.equals(((Event) obj).id);
    }

    public List<Breadcrumb> getBreadcrumbs() {
        return this.breadcrumbs;
    }

    public String getChecksum() {
        return this.checksum;
    }

    public Map<String, Map<String, Object>> getContexts() {
        return this.contexts;
    }

    public String getCulprit() {
        return this.culprit;
    }

    public String getDist() {
        return this.dist;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public Map<String, Object> getExtra() {
        if (this.extra == null) {
            this.extra = new HashMap();
            _logger.warn("`extra` field was null, deserialization must not have been called, please check your ProGuard (or other obfuscation) configuration.");
        }
        return this.extra;
    }

    public List<String> getFingerprint() {
        return this.fingerprint;
    }

    public UUID getId() {
        return this.id;
    }

    public Level getLevel() {
        return this.level;
    }

    public String getLogger() {
        return this.logger;
    }

    public String getMessage() {
        return this.message;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getRelease() {
        return this.release;
    }

    public Sdk getSdk() {
        return this.sdk;
    }

    public Map<String, SentryInterface> getSentryInterfaces() {
        return this.sentryInterfaces;
    }

    public String getServerName() {
        return this.serverName;
    }

    public Map<String, String> getTags() {
        return this.tags;
    }

    public Date getTimestamp() {
        if (this.timestamp != null) {
            return (Date) this.timestamp.clone();
        }
        return null;
    }

    public String getTransaction() {
        return this.transaction;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBreadcrumbs(List<Breadcrumb> list) {
        this.breadcrumbs = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setChecksum(String str) {
        this.checksum = str;
    }

    public void setContexts(Map<String, Map<String, Object>> map) {
        this.contexts = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public void setCulprit(String str) {
        this.culprit = str;
    }

    public void setDist(String str) {
        this.dist = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setEnvironment(String str) {
        this.environment = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setExtra(Map<String, Object> map) {
        this.extra = map;
    }

    public void setFingerprint(List<String> list) {
        this.fingerprint = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLevel(Level level) {
        this.level = level;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setLogger(String str) {
        this.logger = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMessage(String str) {
        this.message = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPlatform(String str) {
        this.platform = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRelease(String str) {
        this.release = str;
    }

    public void setSdk(Sdk sdk) {
        this.sdk = sdk;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSentryInterfaces(Map<String, SentryInterface> map) {
        this.sentryInterfaces = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setServerName(String str) {
        this.serverName = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTags(Map<String, String> map) {
        this.tags = map;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTimestamp(Date date) {
        this.timestamp = date;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setTransaction(String str) {
        this.transaction = str;
    }

    public String toString() {
        return "Event{level=" + this.level + ", message='" + this.message + "', logger='" + this.logger + "'}";
    }
}
