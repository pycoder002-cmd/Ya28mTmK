package com.startapp.sdk.ads.video.player;

import android.media.MediaPlayer;
import android.widget.VideoView;
import com.startapp.c4;
import com.startapp.g5;
import com.startapp.p7;
import com.startapp.r4;
import com.startapp.s4;
import com.startapp.sdk.ads.video.VideoMode;
import com.startapp.sdk.ads.video.player.VideoPlayerInterface;
import com.startapp.t4;
import com.startapp.u4;
import com.startapp.z4;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class NativeVideoPlayer extends z4 implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
    public MediaPlayer f;
    public final VideoView g;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum MediaErrorExtra {
        MEDIA_ERROR_IO,
        MEDIA_ERROR_MALFORMED,
        MEDIA_ERROR_UNSUPPORTED,
        MEDIA_ERROR_TIMED_OUT
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum MediaErrorType {
        MEDIA_ERROR_UNKNOWN,
        MEDIA_ERROR_SERVER_DIED
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements MediaPlayer.OnBufferingUpdateListener {
        public a() {
        }

        @Override // android.media.MediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
            VideoPlayerInterface.a aVar = NativeVideoPlayer.this.e;
            if (aVar != null) {
                ((t4) aVar).a(i);
            }
        }
    }

    public NativeVideoPlayer(VideoView videoView) {
        this.g = videoView;
        videoView.setOnPreparedListener(this);
        videoView.setOnCompletionListener(this);
        videoView.setOnErrorListener(this);
    }

    public void a(String str) {
        this.a = str;
        if (str != null) {
            try {
                this.g.setVideoPath(str);
            } catch (Throwable th) {
                p7.a(this.g.getContext(), th);
                onError(this.f, 1, 0);
            }
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        VideoPlayerInterface.b bVar = this.d;
        if (bVar != null) {
            s4 s4Var = (s4) bVar;
            if (!s4Var.a.F()) {
                s4Var.a.a(VideoMode.VideoFinishedReason.COMPLETE);
            }
            VideoPlayerInterface videoPlayerInterface = s4Var.a.L;
            if (videoPlayerInterface != null) {
                ((NativeVideoPlayer) videoPlayerInterface).g.stopPlayback();
            }
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.c == null) {
            return false;
        }
        int currentPosition = mediaPlayer != null ? mediaPlayer.getCurrentPosition() : -1;
        VideoPlayerInterface.c cVar = this.c;
        VideoPlayerInterface.VideoPlayerErrorType videoPlayerErrorType = (i == 100 ? MediaErrorType.MEDIA_ERROR_SERVER_DIED : MediaErrorType.MEDIA_ERROR_UNKNOWN) == MediaErrorType.MEDIA_ERROR_SERVER_DIED ? VideoPlayerInterface.VideoPlayerErrorType.SERVER_DIED : VideoPlayerInterface.VideoPlayerErrorType.UNKNOWN;
        String str = (i2 != -1010 ? i2 != -1007 ? i2 != -110 ? MediaErrorExtra.MEDIA_ERROR_IO : MediaErrorExtra.MEDIA_ERROR_TIMED_OUT : MediaErrorExtra.MEDIA_ERROR_MALFORMED : MediaErrorExtra.MEDIA_ERROR_UNSUPPORTED).toString();
        VideoPlayerInterface.e eVar = new VideoPlayerInterface.e(videoPlayerErrorType, str, currentPosition);
        u4 u4Var = (u4) cVar;
        VideoMode videoMode = u4Var.a;
        if (videoMode.L != null) {
            videoMode.g0 = false;
            if (!videoMode.f0 || videoMode.j0 > videoMode.k0 || currentPosition <= 0 || !str.equals("MEDIA_ERROR_IO")) {
                u4Var.a.a(eVar);
            } else {
                VideoMode videoMode2 = u4Var.a;
                videoMode2.j0++;
                videoMode2.L();
                VideoMode videoMode3 = u4Var.a;
                ((NativeVideoPlayer) videoMode3.L).a(videoMode3.v().c());
                ((NativeVideoPlayer) u4Var.a.L).g.seekTo(eVar.c);
            }
        }
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        MediaPlayer mediaPlayer2;
        this.f = mediaPlayer;
        VideoPlayerInterface.d dVar = this.b;
        if (dVar != null) {
            r4 r4Var = (r4) dVar;
            VideoMode videoMode = r4Var.a;
            videoMode.g0 = true;
            if (videoMode.V && videoMode.W) {
                videoMode.y();
            }
            if (r4Var.a.A()) {
                r4Var.a.M();
            }
        }
        if (g5.c(this.a) && (mediaPlayer2 = this.f) != null) {
            mediaPlayer2.setOnBufferingUpdateListener(new a());
        } else {
            if (g5.c(this.a)) {
                return;
            }
            c4.b.a.b = this.e;
        }
    }
}
