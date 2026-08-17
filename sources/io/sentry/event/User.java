package io.sentry.event;

import java.io.Serializable;
import java.util.Map;

/* loaded from: classes.dex */
public class User implements Serializable {
    private final Map<String, Object> data;
    private final String email;
    private final String id;
    private final String ipAddress;
    private final String username;

    public User(String str, String str2, String str3, String str4) {
        this(str, str2, str3, str4, null);
    }

    public User(String str, String str2, String str3, String str4, Map<String, Object> map) {
        this.id = str;
        this.username = str2;
        this.ipAddress = str3;
        this.email = str4;
        this.data = map;
    }

    public Map<String, Object> getData() {
        return this.data;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() {
        return this.id;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public String getUsername() {
        return this.username;
    }
}
