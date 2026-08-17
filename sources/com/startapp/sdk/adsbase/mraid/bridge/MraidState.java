package com.startapp.sdk.adsbase.mraid.bridge;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public enum MraidState {
    LOADING,
    DEFAULT,
    EXPANDED,
    RESIZED,
    HIDDEN;

    @Override // java.lang.Enum
    public String toString() {
        return name().toLowerCase();
    }
}
