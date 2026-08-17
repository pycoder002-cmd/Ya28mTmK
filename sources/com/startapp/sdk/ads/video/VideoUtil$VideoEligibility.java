package com.startapp.sdk.ads.video;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public enum VideoUtil$VideoEligibility {
    ELIGIBLE(""),
    INELIGIBLE_NO_STORAGE("Not enough storage for video"),
    INELIGIBLE_MISSING_ACTIVITY("OverlayActivity not declared in AndroidManifest.xml"),
    INELIGIBLE_ERRORS_THRESHOLD_REACHED("Video errors threshold reached.");

    private String desctiption;

    VideoUtil$VideoEligibility(String str) {
        this.desctiption = str;
    }

    public String a() {
        return this.desctiption;
    }
}
