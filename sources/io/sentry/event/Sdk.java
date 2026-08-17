package io.sentry.event;

import java.io.Serializable;
import java.util.Set;

/* loaded from: classes2.dex */
public class Sdk implements Serializable {
    private Set<String> integrations;
    private String name;
    private String version;

    public Sdk(String str, String str2, Set<String> set) {
        this.name = str;
        this.version = str2;
        this.integrations = set;
    }

    public Set<String> getIntegrations() {
        return this.integrations;
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }
}
