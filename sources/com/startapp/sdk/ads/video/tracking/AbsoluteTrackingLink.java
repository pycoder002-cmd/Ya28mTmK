package com.startapp.sdk.ads.video.tracking;

import com.startapp.e;
import java.io.Serializable;

/* compiled from: StartAppSDK */
@e(extendsClass = true)
/* loaded from: classes3.dex */
public class AbsoluteTrackingLink extends VideoTrackingLink implements Serializable {
    private static final long serialVersionUID = 1;
    private int videoOffsetMillis;

    public void a(int i) {
        this.videoOffsetMillis = i;
    }

    public int e() {
        return this.videoOffsetMillis;
    }

    @Override // com.startapp.sdk.ads.video.tracking.VideoTrackingLink
    public String toString() {
        return super.toString() + ", videoOffsetMillis=" + this.videoOffsetMillis;
    }
}
