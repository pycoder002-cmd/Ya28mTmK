package com.startapp.sdk.ads.video.player;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public interface VideoPlayerInterface {

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum VideoPlayerErrorType {
        UNKNOWN,
        SERVER_DIED,
        BUFFERING_TIMEOUT,
        PLAYER_CREATION
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface b {
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface c {
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface d {
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class e {
        public VideoPlayerErrorType a;
        public String b;
        public int c;

        public e(VideoPlayerErrorType videoPlayerErrorType, String str, int i) {
            this.a = videoPlayerErrorType;
            this.b = str;
            this.c = i;
        }
    }
}
