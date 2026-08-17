package com.startapp.sdk.ads.video;

import com.startapp.a5;
import com.startapp.f;
import com.startapp.sdk.ads.video.tracking.VideoTrackingDetails;
import com.startapp.sdk.adsbase.VideoConfig;
import com.startapp.sdk.omsdk.AdVerification;
import com.startapp.sdk.omsdk.VerificationDetails;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class VideoAdDetails implements Serializable {
    private static final long serialVersionUID = 1;

    @f(name = "adVerifications", type = VerificationDetails.class)
    private VerificationDetails[] adVerifications;
    private String clickUrl;
    private boolean clickable;
    private boolean closeable;
    private boolean isVideoMuted;
    private String localVideoPath;

    @f(type = PostRollType.class)
    private PostRollType postRoll;
    private boolean skippable;
    private long skippableAfter;

    @f(complex = true)
    private VideoTrackingDetails videoTrackingDetails;
    private String videoUrl;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum PostRollType {
        IMAGE,
        LAST_FRAME,
        NONE
    }

    public VideoAdDetails() {
    }

    public VideoAdDetails(a5 a5Var, VideoConfig videoConfig, boolean z) {
        this.videoTrackingDetails = new VideoTrackingDetails(a5Var);
        this.videoUrl = a5Var.j();
        Integer n = a5Var.n();
        if (z) {
            this.skippableAfter = n != null ? n.intValue() : videoConfig.m();
            this.skippable = true;
        } else {
            this.skippable = false;
        }
        String c = a5Var.c();
        this.clickUrl = c;
        this.clickable = c != null;
        this.postRoll = PostRollType.NONE;
        a(new AdVerification((VerificationDetails[]) a5Var.b().toArray(new VerificationDetails[0])));
    }

    public AdVerification a() {
        return new AdVerification(this.adVerifications);
    }

    public void a(AdVerification adVerification) {
        if (adVerification.a() != null) {
            this.adVerifications = (VerificationDetails[]) adVerification.a().toArray(new VerificationDetails[adVerification.a().size()]);
        }
    }

    public void a(String str) {
        this.localVideoPath = str;
    }

    public void a(boolean z) {
        this.isVideoMuted = z;
    }

    public String b() {
        return this.clickUrl;
    }

    public String c() {
        return this.localVideoPath;
    }

    public PostRollType d() {
        return this.postRoll;
    }

    public long e() {
        return this.skippableAfter;
    }

    public VideoTrackingDetails f() {
        return this.videoTrackingDetails;
    }

    public String g() {
        return this.videoUrl;
    }

    public boolean h() {
        return this.clickable;
    }

    public boolean i() {
        return this.closeable;
    }

    public boolean j() {
        return this.skippable;
    }

    public boolean k() {
        return this.isVideoMuted;
    }

    public void l() {
        this.skippableAfter = TimeUnit.SECONDS.toMillis(this.skippableAfter);
    }

    public String toString() {
        return super.toString();
    }
}
