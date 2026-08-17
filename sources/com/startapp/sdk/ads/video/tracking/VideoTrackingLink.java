package com.startapp.sdk.ads.video.tracking;

import com.startapp.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class VideoTrackingLink implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean appendReplayParameter;
    private String replayParameter;

    @f(type = TrackingSource.class)
    private TrackingSource trackingSource;
    private String trackingUrl;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum TrackingSource {
        STARTAPP,
        EXTERNAL
    }

    public String a() {
        return this.replayParameter;
    }

    public void a(String str) {
        this.replayParameter = str;
    }

    public void a(boolean z) {
        this.appendReplayParameter = z;
    }

    public TrackingSource b() {
        return this.trackingSource;
    }

    public void b(String str) {
        this.trackingUrl = str;
    }

    public String c() {
        return this.trackingUrl;
    }

    public boolean d() {
        return this.appendReplayParameter;
    }

    public String toString() {
        return "trackingSource=" + this.trackingSource + ", trackingUrl=" + this.trackingUrl + ", replayParameter=" + this.replayParameter + ", appendReplayParameter=" + this.appendReplayParameter;
    }
}
