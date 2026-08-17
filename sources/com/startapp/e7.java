package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.cache.DiskAdCacheManager$DiskCachedAd;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class e7 implements Runnable {
    public final /* synthetic */ Context a;
    public final /* synthetic */ String b;
    public final /* synthetic */ AdEventListener c;
    public final /* synthetic */ g7 d;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ DiskAdCacheManager$DiskCachedAd a;

        public a(DiskAdCacheManager$DiskCachedAd diskAdCacheManager$DiskCachedAd) {
            this.a = diskAdCacheManager$DiskCachedAd;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                DiskAdCacheManager$DiskCachedAd diskAdCacheManager$DiskCachedAd = this.a;
                if (diskAdCacheManager$DiskCachedAd == null) {
                    e7 e7Var = e7.this;
                    d.a(e7Var.a, e7Var.c, (Ad) null);
                } else {
                    if (diskAdCacheManager$DiskCachedAd.a() != null && this.a.a().isReady()) {
                        if (this.a.a().d()) {
                            e7 e7Var2 = e7.this;
                            d.a(e7Var2.a, e7Var2.c, (Ad) null);
                        } else {
                            e7 e7Var3 = e7.this;
                            d.a(e7Var3.a, this.a, e7Var3.d, e7Var3.c);
                        }
                    }
                    e7 e7Var4 = e7.this;
                    d.a(e7Var4.a, e7Var4.c, (Ad) null);
                }
            } catch (Throwable th) {
                p7.a(e7.this.a, th);
                e7 e7Var5 = e7.this;
                d.a(e7Var5.a, e7Var5.c, (Ad) null);
            }
        }
    }

    public e7(Context context, String str, AdEventListener adEventListener, g7 g7Var) {
        this.a = context;
        this.b = str;
        this.c = adEventListener;
        this.d = g7Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            new Handler(Looper.getMainLooper()).post(new a((DiskAdCacheManager$DiskCachedAd) h9.a(this.a, d.c(), this.b)));
        } catch (Throwable th) {
            p7.a(this.a, th);
            d.a(this.a, this.c, (Ad) null);
        }
    }
}
