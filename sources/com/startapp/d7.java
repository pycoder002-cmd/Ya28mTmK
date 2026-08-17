package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.sdk.adsbase.cache.DiskAdCacheManager$DiskCacheKey;
import com.startapp.v6;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class d7 implements Runnable {
    public final /* synthetic */ Context a;
    public final /* synthetic */ h7 b;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ List a;

        public a(List list) {
            this.a = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            h7 h7Var = d7.this.b;
            List<DiskAdCacheManager$DiskCacheKey> list = this.a;
            q6 q6Var = (q6) h7Var;
            q6Var.getClass();
            if (list != null) {
                try {
                    for (DiskAdCacheManager$DiskCacheKey diskAdCacheManager$DiskCacheKey : list) {
                        if (q6Var.b.a(diskAdCacheManager$DiskCacheKey.placement)) {
                            q6Var.b.a(q6Var.a, null, diskAdCacheManager$DiskCacheKey.placement, diskAdCacheManager$DiskCacheKey.adPreferences, null, true, diskAdCacheManager$DiskCacheKey.a());
                        }
                    }
                } catch (Throwable th) {
                    p7.a(q6Var.a, th);
                }
            }
            v6 v6Var = q6Var.b;
            Context context = q6Var.a;
            v6Var.e = false;
            for (v6.a aVar : v6Var.f) {
                if (v6Var.a(aVar.b)) {
                    v6Var.a(context, aVar.a, aVar.b, aVar.c, aVar.d, false, 0);
                }
            }
            v6Var.f.clear();
        }
    }

    public d7(Context context, h7 h7Var) {
        this.a = context;
        this.b = h7Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            new Handler(Looper.getMainLooper()).post(new a(h9.d(this.a, d.d())));
        } catch (Throwable th) {
            p7.a(this.a, th);
        }
    }
}
