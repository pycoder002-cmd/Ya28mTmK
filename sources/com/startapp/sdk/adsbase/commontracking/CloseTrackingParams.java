package com.startapp.sdk.adsbase.commontracking;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class CloseTrackingParams extends TrackingParams {
    private static final long serialVersionUID = 1;
    private final long duration;

    public CloseTrackingParams(long j, String str) {
        super(str);
        this.duration = j;
    }

    @Override // com.startapp.sdk.adsbase.commontracking.TrackingParams
    public String e() {
        return super.e() + "&displayDuration=" + Math.max(0L, this.duration);
    }
}
