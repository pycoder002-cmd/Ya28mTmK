package com.startapp.sdk.ads.video.tracking;

import com.startapp.sdk.adsbase.commontracking.TrackingParams;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class VideoTrackingParams extends TrackingParams {
    private static final long serialVersionUID = 1;
    private int completed;
    public boolean internalParamsIndicator;
    private String replayParameter;
    private boolean shouldAppendOffset;
    private String videoPlayingMode;

    public VideoTrackingParams(String str, int i, int i2, String str2) {
        super(str);
        a(i2);
        this.completed = i;
        this.videoPlayingMode = str2;
    }

    public VideoTrackingParams a(boolean z) {
        this.shouldAppendOffset = z;
        return this;
    }

    public String b(String str) {
        if (!this.internalParamsIndicator) {
            return c();
        }
        return super.e() + str;
    }

    public VideoTrackingParams c(String str) {
        this.replayParameter = str;
        return this;
    }

    @Override // com.startapp.sdk.adsbase.commontracking.TrackingParams
    public String c() {
        if (!this.shouldAppendOffset) {
            return "";
        }
        String str = this.replayParameter;
        return str != null ? str.replace("%startapp_replay_count%", new Integer(b()).toString()) : super.c();
    }

    @Override // com.startapp.sdk.adsbase.commontracking.TrackingParams
    public String e() {
        return b(f() + g());
    }

    public String f() {
        return "&cp=" + this.completed;
    }

    public String g() {
        return "&vpm=" + this.videoPlayingMode;
    }
}
