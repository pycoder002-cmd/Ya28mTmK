package com.iab.omid.library.startapp.adsession;

import io.reactivex.annotations.SchedulerSupport;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public enum Owner {
    NATIVE("native"),
    JAVASCRIPT("javascript"),
    NONE(SchedulerSupport.NONE);

    private final String owner;

    Owner(String str) {
        this.owner = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.owner;
    }
}
