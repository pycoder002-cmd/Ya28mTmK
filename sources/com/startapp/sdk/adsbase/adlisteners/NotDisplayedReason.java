package com.startapp.sdk.adsbase.adlisteners;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public enum NotDisplayedReason {
    AD_CLOSED_TOO_QUICKLY(10),
    NETWORK_PROBLEM(20),
    APP_IN_BACKGROUND(30),
    WINDOW_NOT_FOCUSED(31),
    VIEW_NOT_ATTACHED(32),
    AD_RULES(40),
    AD_NOT_READY(41),
    AD_EXPIRED(42),
    VIDEO_BACK(43),
    VIDEO_ERROR(44),
    AD_NOT_READY_VIDEO_FALLBACK(45),
    VIEW_NOT_VISIBLE(50),
    VIEW_TRANSPARENT(51),
    VIEW_INVALID_SIZE(52),
    AD_CLIPPED(60),
    AD_WAS_COVERED(61),
    SERVING_ADS_DISABLED(70),
    INTERNAL_ERROR(80);

    private final int priority;

    NotDisplayedReason(int i) {
        this.priority = i;
    }

    public int a() {
        return this.priority;
    }
}
