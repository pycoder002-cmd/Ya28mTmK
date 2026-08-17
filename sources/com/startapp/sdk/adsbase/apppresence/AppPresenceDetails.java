package com.startapp.sdk.adsbase.apppresence;

import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AppPresenceDetails implements Serializable {
    private static final long serialVersionUID = 1;
    private int adAttempt;
    private int minAppVersion;
    private String packageName;
    private String trackingUrl;
    private boolean isShown = true;
    private boolean appPresence = false;

    public AppPresenceDetails(String str, String str2, int i, int i2) {
        this.trackingUrl = str;
        this.packageName = str2;
        this.adAttempt = i;
        this.minAppVersion = i2;
    }

    public int a() {
        return this.minAppVersion;
    }

    public void a(String str) {
        this.trackingUrl = str;
    }

    public void a(boolean z) {
        this.appPresence = z;
    }

    public String b() {
        return this.packageName;
    }

    public void b(boolean z) {
        this.isShown = z;
    }

    public String c() {
        return this.trackingUrl;
    }

    public boolean d() {
        return this.appPresence;
    }

    public boolean e() {
        return this.isShown;
    }
}
