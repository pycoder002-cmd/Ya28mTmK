package com.startapp.sdk.ads.video;

import android.R;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.media.ExifInterface;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.iab.omid.library.startapp.Omid;
import com.iab.omid.library.startapp.adsession.AdEvents;
import com.iab.omid.library.startapp.adsession.AdSession;
import com.iab.omid.library.startapp.adsession.AdSessionContext;
import com.iab.omid.library.startapp.adsession.FriendlyObstructionPurpose;
import com.iab.omid.library.startapp.adsession.Partner;
import com.iab.omid.library.startapp.adsession.VerificationScriptResource;
import com.iab.omid.library.startapp.adsession.media.MediaEvents;
import com.iab.omid.library.startapp.adsession.media.Position;
import com.iab.omid.library.startapp.adsession.media.VastProperties;
import com.startapp.aa;
import com.startapp.bd;
import com.startapp.c4;
import com.startapp.g5;
import com.startapp.hc;
import com.startapp.i4;
import com.startapp.j4;
import com.startapp.k4;
import com.startapp.l4;
import com.startapp.la;
import com.startapp.m4;
import com.startapp.n4;
import com.startapp.o4;
import com.startapp.p4;
import com.startapp.p7;
import com.startapp.q2;
import com.startapp.q4;
import com.startapp.q7;
import com.startapp.r4;
import com.startapp.s4;
import com.startapp.sdk.ads.video.VideoAdDetails;
import com.startapp.sdk.ads.video.player.NativeVideoPlayer;
import com.startapp.sdk.ads.video.player.VideoPlayerInterface;
import com.startapp.sdk.ads.video.tracking.AbsoluteTrackingLink;
import com.startapp.sdk.ads.video.tracking.FractionTrackingLink;
import com.startapp.sdk.ads.video.tracking.VideoClickedTrackingParams;
import com.startapp.sdk.ads.video.tracking.VideoPausedTrackingParams;
import com.startapp.sdk.ads.video.tracking.VideoProgressTrackingParams;
import com.startapp.sdk.ads.video.tracking.VideoTrackingLink;
import com.startapp.sdk.ads.video.tracking.VideoTrackingParams;
import com.startapp.sdk.ads.video.vast.VASTErrorCodes;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.VideoConfig;
import com.startapp.sdk.adsbase.adinformation.AdInformationView;
import com.startapp.sdk.adsbase.adlisteners.NotDisplayedReason;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.omsdk.AdVerification;
import com.startapp.sdk.omsdk.VerificationDetails;
import com.startapp.t4;
import com.startapp.u4;
import com.startapp.v4;
import com.startapp.xa;
import com.startapp.y4;
import com.startapp.y8;
import com.startapp.ya;
import com.startapp.z4;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class VideoMode extends q2 {
    public static final String K = "VideoMode";
    public VideoPlayerInterface L;
    public VideoView M;
    public RelativeLayout N;
    public RelativeLayout O;
    public ProgressBar P;
    public boolean U;
    public int k0;
    public long s0;
    public long t0;
    public MediaEvents u0;
    public boolean Q = false;
    public int R = 0;
    public int S = 0;
    public int T = 0;
    public boolean V = false;
    public boolean W = false;
    public boolean X = false;
    public boolean Y = false;
    public HashMap<Integer, Boolean> Z = new HashMap<>();
    public HashMap<Integer, Boolean> a0 = new HashMap<>();
    public int b0 = 1;
    public boolean c0 = false;
    public boolean d0 = false;
    public int e0 = 0;
    public boolean f0 = false;
    public boolean g0 = false;
    public boolean h0 = false;
    public boolean i0 = false;
    public int j0 = 0;
    public String l0 = null;
    public Handler m0 = new Handler();
    public Handler n0 = new Handler();
    public Handler o0 = new Handler();
    public Handler p0 = new Handler();
    public final Map<Integer, List<FractionTrackingLink>> q0 = new HashMap();
    public final Map<Integer, List<AbsoluteTrackingLink>> r0 = new HashMap();
    public boolean v0 = false;
    public final BroadcastReceiver w0 = new d();
    public final Runnable x0 = new e();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum HtmlMode {
        PLAYER,
        POST_ROLL
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum VideoFinishedReason {
        COMPLETE,
        CLICKED,
        SKIPPED
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ int a;
        public final /* synthetic */ Handler b;

        public a(int i, Handler handler) {
            this.a = i;
            this.b = handler;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            VideoMode videoMode = VideoMode.this;
            VideoPlayerInterface videoPlayerInterface = videoMode.L;
            if (videoPlayerInterface != null) {
                ((NativeVideoPlayer) videoPlayerInterface).g.start();
                videoMode.w.setBackgroundColor(33554431);
                VideoMode videoMode2 = VideoMode.this;
                MediaEvents mediaEvents = videoMode2.u0;
                if (mediaEvents != null && (i = this.a) > 0) {
                    mediaEvents.start(i, videoMode2.Q ? 0.0f : 1.0f);
                }
                VideoMode videoMode3 = VideoMode.this;
                videoMode3.X = true;
                videoMode3.N();
                this.b.post(VideoMode.this.x0);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                VideoPlayerInterface videoPlayerInterface = VideoMode.this.L;
                if (videoPlayerInterface != null) {
                    if (((NativeVideoPlayer) videoPlayerInterface).g.getCurrentPosition() > 0) {
                        VideoMode.this.f(0);
                        VideoMode.this.e(0);
                        VideoMode videoMode = VideoMode.this;
                        if (videoMode.F == 0) {
                            videoMode.G();
                            la.a(VideoMode.this.b).a(new Intent("com.startapp.android.ShowDisplayBroadcastListener"));
                        }
                    } else {
                        VideoMode videoMode2 = VideoMode.this;
                        if (!videoMode2.Y) {
                            videoMode2.m0.postDelayed(this, 100L);
                        }
                    }
                }
            } catch (Throwable th) {
                p7.a(VideoMode.this.b, th);
                VideoMode.this.b();
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements Runnable {

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    VideoMode.this.N();
                    VideoMode.this.getClass();
                    VideoMode videoMode = VideoMode.this;
                    videoMode.a(new VideoPlayerInterface.e(VideoPlayerInterface.VideoPlayerErrorType.BUFFERING_TIMEOUT, "Buffering timeout reached", videoMode.R));
                } catch (Throwable th) {
                    p7.a(VideoMode.this.b, th);
                }
            }
        }

        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                VideoMode.this.P.setVisibility(0);
                MediaEvents mediaEvents = VideoMode.this.u0;
                if (mediaEvents != null) {
                    mediaEvents.bufferStart();
                }
                VideoMode.this.p0.postDelayed(new a(), AdsCommonMetaData.h.G().c());
            } catch (Throwable th) {
                VideoMode.this.N();
                p7.a(VideoMode.this.b, th);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d extends BroadcastReceiver {
        public d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (VideoMode.this.w0.isInitialStickyBroadcast()) {
                return;
            }
            VideoMode videoMode = VideoMode.this;
            if (videoMode.Q == videoMode.E()) {
                return;
            }
            VideoMode videoMode2 = VideoMode.this;
            videoMode2.Q = !videoMode2.Q;
            videoMode2.I();
            VideoMode videoMode3 = VideoMode.this;
            videoMode3.a(videoMode3.Q);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            VideoMode.this.y();
        }
    }

    public boolean A() {
        return !this.f0 ? B() && this.V : this.e0 >= AdsCommonMetaData.h.G().i() && B() && this.V;
    }

    public boolean B() {
        VideoPlayerInterface videoPlayerInterface = this.L;
        if (videoPlayerInterface != null) {
            if (((NativeVideoPlayer) videoPlayerInterface).f != null) {
                return true;
            }
        }
        return false;
    }

    public boolean C() {
        ProgressBar progressBar = this.P;
        return progressBar != null && progressBar.isShown();
    }

    public final boolean D() {
        return this.F > 0 || v().j() || this.c0;
    }

    public boolean E() {
        AudioManager audioManager = (AudioManager) this.b.getSystemService("audio");
        return audioManager != null && (audioManager.getRingerMode() == 0 || audioManager.getRingerMode() == 1);
    }

    public boolean F() {
        return this.R == -1;
    }

    public void G() {
        this.C.b();
        a(v().f().d(), new VideoTrackingParams(this.p, 0, this.F, this.l0), 0, "impression");
        a(v().f().b(), new VideoTrackingParams(this.p, 0, this.F, this.l0), 0, "creativeView");
    }

    public final void H() {
        aa.a(this.w, true, "videoApi.setSkipTimer", Long.valueOf(c(this.R + 50)));
    }

    public void I() {
        VideoPlayerInterface videoPlayerInterface = this.L;
        if (videoPlayerInterface != null) {
            try {
                boolean z = this.Q;
                MediaPlayer mediaPlayer = ((NativeVideoPlayer) videoPlayerInterface).f;
                if (mediaPlayer != null) {
                    if (z) {
                        mediaPlayer.setVolume(0.0f, 0.0f);
                    } else {
                        mediaPlayer.setVolume(1.0f, 1.0f);
                    }
                }
            } catch (Throwable th) {
                p7.a(this.b, th);
            }
        }
        Object[] objArr = new Object[1];
        objArr[0] = this.Q ? "OFF" : "ON";
        aa.a(this.w, true, "videoApi.setSound", objArr);
    }

    public void J() {
        if (this.L == null) {
            return;
        }
        boolean p = AdsCommonMetaData.h.G().p();
        String c2 = v().c();
        if (c2 != null) {
            VideoPlayerInterface videoPlayerInterface = this.L;
            if (videoPlayerInterface != null) {
                ((NativeVideoPlayer) videoPlayerInterface).a(c2);
            }
            if (p && c2.endsWith(".temp")) {
                this.f0 = true;
                this.i0 = true;
                this.e0 = AdsCommonMetaData.h.G().i();
            }
        } else if (p) {
            String g = v().g();
            c4 c4Var = c4.b.a;
            if (g != null && g.equals(c4Var.c)) {
                c4Var.a = false;
            }
            VideoPlayerInterface videoPlayerInterface2 = this.L;
            if (videoPlayerInterface2 != null) {
                ((NativeVideoPlayer) videoPlayerInterface2).a(g);
            }
            this.f0 = true;
            L();
        } else {
            a(VideoFinishedReason.SKIPPED);
        }
        if (this.l0 == null) {
            this.l0 = this.f0 ? ExifInterface.GPS_MEASUREMENT_2D : "1";
        }
    }

    public int K() {
        VideoPlayerInterface videoPlayerInterface = this.L;
        int duration = videoPlayerInterface == null ? 0 : (((NativeVideoPlayer) videoPlayerInterface).g.getCurrentPosition() != ((NativeVideoPlayer) this.L).g.getDuration() || F()) ? ((NativeVideoPlayer) this.L).g.getDuration() - ((NativeVideoPlayer) this.L).g.getCurrentPosition() : ((NativeVideoPlayer) this.L).g.getDuration();
        int i = duration / 1000;
        if (i > 0 && duration % 1000 < 100) {
            i--;
        }
        aa.a(this.w, true, "videoApi.setVideoRemainingTimer", Integer.valueOf(i));
        return duration;
    }

    public void L() {
        if (C()) {
            return;
        }
        this.p0.postDelayed(new c(), AdsCommonMetaData.h.G().g());
    }

    public void M() {
        this.h0 = true;
        VideoPlayerInterface videoPlayerInterface = this.L;
        aa.a(this.w, true, "videoApi.setVideoDuration", Integer.valueOf(videoPlayerInterface != null ? ((NativeVideoPlayer) videoPlayerInterface).g.getDuration() / 1000 : 0));
        K();
        H();
        aa.a(this.w, true, "videoApi.setVideoCurrentPosition", Integer.valueOf(this.R / 1000));
        if (F()) {
            VideoPlayerInterface videoPlayerInterface2 = this.L;
            if (videoPlayerInterface2 != null) {
                ((NativeVideoPlayer) videoPlayerInterface2).g.pause();
                return;
            }
            return;
        }
        VideoPlayerInterface videoPlayerInterface3 = this.L;
        int duration = videoPlayerInterface3 != null ? ((NativeVideoPlayer) videoPlayerInterface3).g.getDuration() : 0;
        Handler handler = new Handler();
        a aVar = new a(duration, handler);
        long currentTimeMillis = System.currentTimeMillis() - this.s0;
        long j = 0;
        if (this.R == 0 && this.F == 0 && currentTimeMillis < 500) {
            j = Math.max(200L, 500 - currentTimeMillis);
        }
        handler.postDelayed(aVar, j);
        if (this.R == 0) {
            this.m0.postDelayed(new b(), 100L);
        }
        VideoPlayerInterface videoPlayerInterface4 = this.L;
        this.T = videoPlayerInterface4 != null ? ((NativeVideoPlayer) videoPlayerInterface4).g.getDuration() : 0;
        Iterator<Integer> it = this.q0.keySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            a(d(intValue), this.m0, new l4(this, intValue));
        }
        Iterator<Integer> it2 = this.r0.keySet().iterator();
        while (it2.hasNext()) {
            int intValue2 = it2.next().intValue();
            a(intValue2, this.m0, new m4(this, intValue2));
        }
        if (!this.f0) {
            a(d(AdsCommonMetaData.h.G().k()), this.o0, new n4(this));
        }
        this.n0.post(new j4(this));
        H();
        this.n0.post(new k4(this));
        this.c.b.setVisibility(4);
        I();
    }

    public void N() {
        this.p0.removeCallbacksAndMessages(null);
        if (C()) {
            this.P.setVisibility(8);
            MediaEvents mediaEvents = this.u0;
            if (mediaEvents != null) {
                mediaEvents.bufferFinish();
            }
        }
    }

    public void O() {
        if (C()) {
            N();
        }
        a(VideoFinishedReason.SKIPPED);
        a(v().f().o(), new VideoTrackingParams(this.p, b(this.S), this.F, this.l0), this.S, "skipped");
    }

    public final void a(int i, Handler handler, Runnable runnable) {
        if (this.R < i) {
            handler.postDelayed(runnable, i - r0);
        }
    }

    @Override // com.startapp.q2, com.startapp.p2
    public void a(Bundle bundle) {
        boolean z;
        super.a(bundle);
        try {
            this.s0 = System.currentTimeMillis();
            this.k0 = 100 / AdsCommonMetaData.h.G().h();
            w();
            z();
            if (!E() && !v().k() && !AdsCommonMetaData.h.G().l().equals("muted")) {
                z = false;
                this.Q = z;
                if (bundle == null && bundle.containsKey("currentPosition")) {
                    this.R = bundle.getInt("currentPosition");
                    this.S = bundle.getInt("latestPosition");
                    this.Z = (HashMap) bundle.getSerializable("fractionProgressImpressionsSent");
                    this.a0 = (HashMap) bundle.getSerializable("absoluteProgressImpressionsSent");
                    this.Q = bundle.getBoolean("isMuted");
                    this.U = bundle.getBoolean("shouldSetBg");
                    this.b0 = bundle.getInt("pauseNum");
                    return;
                }
            }
            z = true;
            this.Q = z;
            if (bundle == null) {
            }
        } catch (Throwable th) {
            p7.a(this.b, th);
            u();
            b();
        }
    }

    @Override // com.startapp.q2
    public void a(View view) {
        boolean z;
        AdSession a2;
        URL url;
        this.V = true;
        if (this.W && B()) {
            y();
        } else if (F()) {
            b((View) this.w);
        }
        if (A()) {
            M();
        }
        if (F()) {
            x();
        }
        VideoAdDetails v = v();
        if (!MetaData.h.O() || this.x != null || v == null || v.a().a() == null) {
            return;
        }
        AdVerification a3 = v().a();
        Context context = this.w.getContext();
        try {
            if (!Omid.isActive()) {
                Omid.activate(context);
            }
            z = true;
        } catch (Throwable th) {
            p7.a(context, th);
            z = false;
        }
        if (z) {
            String a4 = bd.a();
            List<VerificationDetails> a5 = a3.a();
            ArrayList arrayList = new ArrayList(a5.size());
            for (VerificationDetails verificationDetails : a5) {
                try {
                    url = new URL(verificationDetails.c());
                } catch (Throwable th2) {
                    p7.a(context, th2);
                    url = null;
                }
                if (url != null) {
                    arrayList.add(VerificationScriptResource.createVerificationScriptResourceWithParameters(verificationDetails.a(), url, verificationDetails.b()));
                }
            }
            a2 = com.startapp.d.a(AdSessionContext.createNativeAdSessionContext(Partner.createPartner("StartApp", "4.9.1"), a4, arrayList, null, ""), true);
        } else {
            a2 = null;
        }
        this.x = a2;
        if (a2 != null) {
            this.u0 = MediaEvents.createMediaEvents(a2);
            AdInformationView adInformationView = this.c.b;
            if (adInformationView != null) {
                try {
                    this.x.addFriendlyObstruction(adInformationView, FriendlyObstructionPurpose.OTHER, null);
                } catch (RuntimeException e2) {
                    Log.e(K, "OMSDK error", e2);
                }
            }
            this.x.addFriendlyObstruction(this.w, FriendlyObstructionPurpose.VIDEO_CONTROLS, null);
            this.x.addFriendlyObstruction(this.O, FriendlyObstructionPurpose.OTHER, null);
            this.x.registerAdView(this.M);
            this.x.start();
            VastProperties createVastPropertiesForSkippableMedia = D() ? VastProperties.createVastPropertiesForSkippableMedia(v().j() ? (float) v().e() : 0.0f, true, Position.STANDALONE) : VastProperties.createVastPropertiesForNonSkippableMedia(true, Position.STANDALONE);
            AdEvents createAdEvents = AdEvents.createAdEvents(this.x);
            createAdEvents.loaded(createVastPropertiesForSkippableMedia);
            createAdEvents.impressionOccurred();
        }
    }

    public void a(VideoFinishedReason videoFinishedReason) {
        MediaEvents mediaEvents;
        MediaEvents mediaEvents2;
        if (videoFinishedReason == VideoFinishedReason.COMPLETE && (mediaEvents2 = this.u0) != null) {
            mediaEvents2.complete();
        }
        VideoFinishedReason videoFinishedReason2 = VideoFinishedReason.SKIPPED;
        if (videoFinishedReason == videoFinishedReason2 && (mediaEvents = this.u0) != null) {
            mediaEvents.skipped();
        }
        if (videoFinishedReason == videoFinishedReason2 || videoFinishedReason == VideoFinishedReason.CLICKED) {
            this.m0.removeCallbacksAndMessages(null);
            this.o0.removeCallbacksAndMessages(null);
            VideoPlayerInterface videoPlayerInterface = this.L;
            if (videoPlayerInterface != null) {
                this.S = ((NativeVideoPlayer) videoPlayerInterface).g.getCurrentPosition();
                ((NativeVideoPlayer) this.L).g.pause();
            }
        } else {
            this.S = this.T;
            s();
        }
        this.n0.removeCallbacksAndMessages(null);
        this.Z.clear();
        this.a0.clear();
        if (videoFinishedReason == VideoFinishedReason.CLICKED) {
            this.R = -1;
            return;
        }
        VideoAdDetails.PostRollType d2 = v().d();
        VideoAdDetails.PostRollType postRollType = VideoAdDetails.PostRollType.NONE;
        if (d2 != postRollType) {
            x();
            this.c.b.setVisibility(0);
        } else if (v().d() == postRollType) {
            b();
        }
        this.R = -1;
        if (v().d() != postRollType) {
            a(v().f().l(), new VideoTrackingParams(this.p, b(this.S), this.F, this.l0), this.S, "postrollImression");
        }
    }

    public void a(VideoPlayerInterface.e eVar) {
        VideoPlayerInterface videoPlayerInterface;
        p7 p7Var = new p7(q7.c);
        p7Var.d = "Video player error: " + eVar.a;
        p7Var.e = eVar.b;
        p7Var.g = a();
        p7Var.a(this.b);
        int ordinal = eVar.a.ordinal();
        VASTErrorCodes vASTErrorCodes = ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? VASTErrorCodes.UndefinedError : VASTErrorCodes.MediaFileDisplayError : VASTErrorCodes.TimeoutMediaFileURI : VASTErrorCodes.GeneralLinearError;
        y4 y4Var = new y4(v().f().e(), new VideoTrackingParams(this.p, b(this.S), this.F, this.l0), v().g(), this.S);
        y4Var.f = vASTErrorCodes;
        y4Var.e = "error";
        com.startapp.d.a(this.b, y4Var.a());
        if (((!this.f0 || (videoPlayerInterface = this.L) == null) ? this.R : ((NativeVideoPlayer) videoPlayerInterface).g.getCurrentPosition()) == 0) {
            g5.a(this.b, this.i, this.p, this.F, "VIDEO_ERROR", (JSONObject) null);
            if (!this.f0) {
                com.startapp.d.c(this.b);
            } else if (!eVar.a.equals(VideoPlayerInterface.VideoPlayerErrorType.BUFFERING_TIMEOUT)) {
                com.startapp.d.c(this.b);
            }
        }
        if ((!(this.l.getType() == Ad.AdType.REWARDED_VIDEO) || this.E) && v().d() != VideoAdDetails.PostRollType.NONE) {
            a(VideoFinishedReason.SKIPPED);
        } else {
            u();
            b();
        }
    }

    public void a(boolean z) {
        if (this.L == null) {
            return;
        }
        a(z ? v().f().f() : v().f().g(), new VideoTrackingParams(this.p, b(((NativeVideoPlayer) this.L).g.getCurrentPosition()), this.F, this.l0), ((NativeVideoPlayer) this.L).g.getCurrentPosition(), "sound");
        MediaEvents mediaEvents = this.u0;
        if (mediaEvents != null) {
            mediaEvents.volumeChange(z ? 0.0f : 1.0f);
        }
    }

    public final void a(VideoTrackingLink[] videoTrackingLinkArr, VideoTrackingParams videoTrackingParams, int i, String str) {
        y4 y4Var = new y4(videoTrackingLinkArr, videoTrackingParams, v().g(), i);
        y4Var.e = str;
        com.startapp.d.a(this.b, y4Var.a());
    }

    @Override // com.startapp.q2
    public boolean a(String str, boolean z) {
        if (!TextUtils.isEmpty(v().b())) {
            str = v().b();
            z = true;
        }
        VideoClickedTrackingParams.ClickOrigin clickOrigin = F() ? VideoClickedTrackingParams.ClickOrigin.POSTROLL : VideoClickedTrackingParams.ClickOrigin.VIDEO;
        if (clickOrigin == VideoClickedTrackingParams.ClickOrigin.VIDEO) {
            a(VideoFinishedReason.CLICKED);
        }
        a(v().f().h(), new VideoClickedTrackingParams(this.p, b(this.S), this.F, clickOrigin, this.l0), this.S, "clicked");
        return super.a(str, z);
    }

    public final int b(int i) {
        int i2 = this.T;
        if (i2 > 0) {
            return (i * 100) / i2;
        }
        return 0;
    }

    @Override // com.startapp.q2, com.startapp.p2
    public void b() {
        super.b();
        if (this.i0) {
            String c2 = v().c();
            if (c2 != null && c2.endsWith(".temp")) {
                new File(c2).delete();
            }
        }
    }

    @Override // com.startapp.q2, com.startapp.p2
    public void b(Bundle bundle) {
        super.b(bundle);
        bundle.putInt("currentPosition", this.R);
        bundle.putInt("latestPosition", this.S);
        bundle.putSerializable("fractionProgressImpressionsSent", this.Z);
        bundle.putSerializable("absoluteProgressImpressionsSent", this.a0);
        bundle.putBoolean("isMuted", this.Q);
        bundle.putBoolean("shouldSetBg", this.U);
        bundle.putInt("pauseNum", this.b0);
    }

    public final void b(View view) {
        aa.a(this.w, true, "videoApi.setVideoFrame", Integer.valueOf(com.startapp.d.b(this.b, view.getLeft())), Integer.valueOf(com.startapp.d.b(this.b, view.getTop())), Integer.valueOf(com.startapp.d.b(this.b, view.getWidth())), Integer.valueOf(com.startapp.d.b(this.b, view.getHeight())));
    }

    @Override // com.startapp.q2
    public void b(WebView webView) {
        this.D = false;
        webView.setOnTouchListener(new q2.d());
        ya.a(webView, (Paint) null);
    }

    public long c(int i) {
        if (this.c0 || this.F > 0) {
            return 0L;
        }
        long e2 = v().e() - i;
        if (e2 <= 0) {
            return 0L;
        }
        return (e2 / 1000) + 1;
    }

    @Override // com.startapp.q2, com.startapp.p2
    public boolean c() {
        if (F()) {
            i();
            return false;
        }
        VideoPlayerInterface videoPlayerInterface = this.L;
        if (videoPlayerInterface == null) {
            return false;
        }
        long c2 = c(((NativeVideoPlayer) videoPlayerInterface).g.getCurrentPosition() + 50);
        if (D() && c2 == 0) {
            O();
            return true;
        }
        if (!v().i() && !this.d0) {
            return true;
        }
        i();
        return false;
    }

    public int d(int i) {
        return (this.T * i) / 100;
    }

    @Override // com.startapp.q2, com.startapp.p2
    public void e() {
        if (!F() && !this.b.isFinishing() && !this.d0 && !this.c0) {
            VideoPausedTrackingParams.PauseOrigin pauseOrigin = VideoPausedTrackingParams.PauseOrigin.EXTERNAL;
            VideoPlayerInterface videoPlayerInterface = this.L;
            if (videoPlayerInterface != null) {
                int currentPosition = ((NativeVideoPlayer) videoPlayerInterface).g.getCurrentPosition();
                this.R = currentPosition;
                this.S = currentPosition;
                ((NativeVideoPlayer) this.L).g.pause();
                MediaEvents mediaEvents = this.u0;
                if (mediaEvents != null) {
                    mediaEvents.pause();
                }
            }
            a(v().f().j(), new VideoPausedTrackingParams(this.p, b(this.S), this.F, this.b0, pauseOrigin, this.l0), this.S, "paused");
        }
        VideoPlayerInterface videoPlayerInterface2 = this.L;
        if (videoPlayerInterface2 != null) {
            NativeVideoPlayer nativeVideoPlayer = (NativeVideoPlayer) videoPlayerInterface2;
            if (nativeVideoPlayer.f != null) {
                nativeVideoPlayer.f = null;
            }
            c4.b.a.b = null;
            this.L = null;
        }
        this.m0.removeCallbacksAndMessages(null);
        this.n0.removeCallbacksAndMessages(null);
        this.o0.removeCallbacksAndMessages(null);
        N();
        this.U = true;
        if (this.v0) {
            this.b.unregisterReceiver(this.w0);
            this.v0 = false;
        }
        super.e();
    }

    public void e(int i) {
        List<AbsoluteTrackingLink> list;
        if (this.a0.get(Integer.valueOf(i)) == null) {
            if (this.r0.containsKey(Integer.valueOf(i)) && (list = this.r0.get(Integer.valueOf(i))) != null) {
                a((VideoTrackingLink[]) list.toArray(new AbsoluteTrackingLink[0]), new VideoProgressTrackingParams(this.p, i, this.F, this.l0), i, "absolute");
            }
            this.a0.put(Integer.valueOf(i), Boolean.TRUE);
        }
    }

    @Override // com.startapp.q2, com.startapp.p2
    public void f() {
        super.f();
        this.b.registerReceiver(this.w0, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
        this.v0 = true;
        if (this.b.isFinishing()) {
            return;
        }
        if (this.M == null) {
            Context a2 = y8.a(this.b);
            if (a2 == null) {
                a2 = this.b;
            }
            this.t0 = SystemClock.uptimeMillis();
            this.O = (RelativeLayout) this.b.findViewById(1475346432);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            VideoView videoView = new VideoView(a2);
            this.M = videoView;
            videoView.setId(100);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams2.addRule(13);
            ProgressBar progressBar = new ProgressBar(a2, null, R.attr.progressBarStyleInverse);
            this.P = progressBar;
            progressBar.setVisibility(4);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams3.addRule(14);
            layoutParams3.addRule(15);
            RelativeLayout relativeLayout = new RelativeLayout(a2);
            this.N = relativeLayout;
            relativeLayout.setId(1475346436);
            this.b.setContentView(this.N);
            this.N.addView(this.M, layoutParams2);
            this.N.addView(this.O, layoutParams);
            this.N.addView(this.P, layoutParams3);
            if (AdsConstants.h.booleanValue()) {
                RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams4.addRule(12);
                layoutParams4.addRule(14);
                RelativeLayout relativeLayout2 = this.N;
                TextView textView = new TextView(a2);
                textView.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                int i = ya.a;
                if (Build.VERSION.SDK_INT >= 11) {
                    textView.setAlpha(0.5f);
                }
                textView.setTextColor(-7829368);
                textView.setSingleLine(false);
                textView.setText("url=" + v().g());
                relativeLayout2.addView(textView, layoutParams4);
            }
            this.c.b.setVisibility(4);
        }
        if (this.L == null) {
            this.L = new NativeVideoPlayer(this.M);
        }
        this.W = false;
        this.N.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        J();
        if (F()) {
            this.c.b.setVisibility(0);
            this.M.setVisibility(4);
        } else {
            int i2 = this.R;
            if (i2 != 0) {
                ((NativeVideoPlayer) this.L).g.seekTo(i2);
                a(v().f().m(), new VideoPausedTrackingParams(this.p, b(this.S), this.F, this.b0, VideoPausedTrackingParams.PauseOrigin.EXTERNAL, this.l0), this.S, "resumed");
                this.b0++;
            }
        }
        z4 z4Var = (z4) this.L;
        z4Var.b = new r4(this);
        z4Var.d = new s4(this);
        t4 t4Var = new t4(this);
        z4Var.c = new u4(this);
        z4Var.getClass();
        z4 z4Var2 = (z4) this.L;
        z4Var2.e = t4Var;
        z4Var2.getClass();
        VideoView videoView2 = this.M;
        v4 v4Var = new v4(this);
        int i3 = ya.a;
        if (Build.VERSION.SDK_INT >= 11) {
            videoView2.addOnLayoutChangeListener(new xa(v4Var));
        }
    }

    public void f(int i) {
        if (this.Z.get(Integer.valueOf(i)) == null) {
            if (this.q0.containsKey(Integer.valueOf(i))) {
                List<FractionTrackingLink> list = this.q0.get(Integer.valueOf(i));
                if (list != null) {
                    a((VideoTrackingLink[]) list.toArray(new FractionTrackingLink[0]), new VideoProgressTrackingParams(this.p, i, this.F, this.l0), (this.T * i) / 100, "fraction");
                }
                MediaEvents mediaEvents = this.u0;
                if (mediaEvents != null) {
                    if (i == 25) {
                        mediaEvents.firstQuartile();
                    } else if (i == 50) {
                        mediaEvents.midpoint();
                    } else if (i == 75) {
                        mediaEvents.thirdQuartile();
                    }
                }
            }
            this.Z.put(Integer.valueOf(i), Boolean.TRUE);
        }
    }

    @Override // com.startapp.p2
    public void h() {
        if (this.Y) {
            return;
        }
        la.a(this.b).a(new Intent("com.startapp.android.HideDisplayBroadcastListener"));
    }

    @Override // com.startapp.q2
    public void i() {
        if (this.Y) {
            return;
        }
        if (F() || this.M == null) {
            a(v().f().k(), new VideoTrackingParams(this.p, b(this.S), this.F, this.l0), this.S, "postrollClosed");
            super.i();
        } else {
            VideoPlayerInterface videoPlayerInterface = this.L;
            int currentPosition = videoPlayerInterface != null ? ((NativeVideoPlayer) videoPlayerInterface).g.getCurrentPosition() : 0;
            a(v().f().i(), new VideoTrackingParams(this.p, b(currentPosition), this.F, this.l0), currentPosition, "closed");
        }
    }

    @Override // com.startapp.q2
    public long j() {
        return (SystemClock.uptimeMillis() - this.t0) / 1000;
    }

    @Override // com.startapp.q2
    public hc k() {
        Activity activity = this.b;
        Runnable runnable = this.I;
        return new i4(activity, runnable, runnable, new q4(this), new p4(this), new o4(this), new TrackingParams(this.p), a(0));
    }

    @Override // com.startapp.q2
    public long l() {
        Long l = this.q;
        return l != null ? TimeUnit.SECONDS.toMillis(l.longValue()) : TimeUnit.SECONDS.toMillis(MetaData.h.n());
    }

    @Override // com.startapp.q2
    public TrackingParams m() {
        return new VideoTrackingParams(this.p, 0, this.F, this.l0);
    }

    @Override // com.startapp.q2
    public boolean o() {
        return this.l.getType() == Ad.AdType.REWARDED_VIDEO;
    }

    @Override // com.startapp.q2
    public void p() {
    }

    @Override // com.startapp.q2
    public boolean q() {
        return false;
    }

    @Override // com.startapp.q2
    public void r() {
        a(v().f().n(), new VideoTrackingParams(this.p, AdsCommonMetaData.h.G().k(), this.F, this.l0), d(AdsCommonMetaData.h.G().k()), "rewarded");
    }

    public final void u() {
        Intent intent = new Intent("com.startapp.android.ShowFailedDisplayBroadcastListener");
        intent.putExtra("showFailedReason", NotDisplayedReason.VIDEO_ERROR);
        la.a(this.b).a(intent);
        this.Y = true;
    }

    public VideoAdDetails v() {
        return ((VideoEnabledAd) this.l).w();
    }

    public final void w() {
        if (this.h.equals("back")) {
            if (AdsCommonMetaData.h.G().a().equals(VideoConfig.BackMode.BOTH)) {
                this.c0 = true;
                this.d0 = true;
                return;
            }
            if (AdsCommonMetaData.h.G().a().equals(VideoConfig.BackMode.SKIP)) {
                this.c0 = true;
                this.d0 = false;
            } else if (AdsCommonMetaData.h.G().a().equals(VideoConfig.BackMode.CLOSE)) {
                this.c0 = false;
                this.d0 = true;
            } else if (AdsCommonMetaData.h.G().a().equals(VideoConfig.BackMode.DISABLED)) {
                this.c0 = false;
                this.d0 = false;
            } else {
                this.c0 = false;
                this.d0 = false;
            }
        }
    }

    public final void x() {
        Object[] objArr = new Object[1];
        objArr[0] = Boolean.valueOf(this.L != null);
        aa.a(this.w, true, "videoApi.setReplayEnabled", objArr);
        aa.a(this.w, true, "videoApi.setMode", HtmlMode.POST_ROLL + "_" + v().d());
        aa.a(this.w, true, "videoApi.setCloseable", Boolean.TRUE);
    }

    public void y() {
        if (this.X) {
            b(this.M);
            if (F()) {
                return;
            }
            aa.a(this.w, true, "videoApi.setClickableVideo", Boolean.valueOf(v().h()));
            aa.a(this.w, true, "videoApi.setMode", "PLAYER");
            Object[] objArr = new Object[1];
            objArr[0] = Boolean.valueOf(v().i() || this.d0);
            aa.a(this.w, true, "videoApi.setCloseable", objArr);
            aa.a(this.w, true, "videoApi.setSkippable", Boolean.valueOf(D()));
        }
    }

    public final void z() {
        FractionTrackingLink[] c2 = v().f().c();
        if (c2 != null) {
            for (FractionTrackingLink fractionTrackingLink : c2) {
                List<FractionTrackingLink> list = this.q0.get(Integer.valueOf(fractionTrackingLink.e()));
                if (list == null) {
                    list = new ArrayList<>();
                    this.q0.put(Integer.valueOf(fractionTrackingLink.e()), list);
                }
                list.add(fractionTrackingLink);
            }
        }
        AbsoluteTrackingLink[] a2 = v().f().a();
        if (a2 != null) {
            for (AbsoluteTrackingLink absoluteTrackingLink : a2) {
                List<AbsoluteTrackingLink> list2 = this.r0.get(Integer.valueOf(absoluteTrackingLink.e()));
                if (list2 == null) {
                    list2 = new ArrayList<>();
                    this.r0.put(Integer.valueOf(absoluteTrackingLink.e()), list2);
                }
                list2.add(absoluteTrackingLink);
            }
        }
    }
}
