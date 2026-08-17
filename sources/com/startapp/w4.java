package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.startapp.c4;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import java.net.URL;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class w4 {
    public Context a;
    public URL b;
    public String c;
    public b d;
    public c4.a e;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ String a;

        public a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = w4.this.d;
            if (bVar != null) {
                bVar.a(this.a);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface b {
        void a(String str);
    }

    public w4(Context context, URL url, String str, b bVar, c4.a aVar) {
        this.a = context;
        this.b = url;
        this.c = str;
        this.d = bVar;
        this.e = aVar;
    }

    public void a() {
        String str;
        try {
            str = AdsCommonMetaData.h.G().p() ? c4.b.a.a(this.a, this.b, this.c, this.e) : d.a(this.a, this.b, this.c);
        } catch (Exception unused) {
            str = null;
        }
        new Handler(Looper.getMainLooper()).post(new a(str));
    }
}
