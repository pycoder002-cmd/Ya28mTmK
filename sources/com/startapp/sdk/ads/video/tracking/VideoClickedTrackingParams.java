package com.startapp.sdk.ads.video.tracking;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class VideoClickedTrackingParams extends VideoTrackingParams {
    private static final long serialVersionUID = 1;
    private ClickOrigin clickOrigin;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum ClickOrigin {
        POSTROLL,
        VIDEO
    }

    public VideoClickedTrackingParams(String str, int i, int i2, ClickOrigin clickOrigin, String str2) {
        super(str, i, i2, str2);
        this.clickOrigin = clickOrigin;
    }

    @Override // com.startapp.sdk.ads.video.tracking.VideoTrackingParams, com.startapp.sdk.adsbase.commontracking.TrackingParams
    public String e() {
        StringBuilder sb = new StringBuilder();
        sb.append(f());
        sb.append("&co=" + this.clickOrigin.toString());
        sb.append(g());
        return b(sb.toString());
    }
}
