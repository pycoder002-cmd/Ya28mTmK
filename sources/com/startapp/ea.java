package com.startapp;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ea {
    public final Handler a;
    public final WeakReference<View> b;
    public final int c;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ b a;

        public a(b bVar) {
            this.a = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a.onUpdate(ea.a(ea.this))) {
                ea.this.a.postDelayed(this, 100L);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface b {
        boolean onUpdate(boolean z);
    }

    public ea(View view, int i, b bVar) {
        Handler handler = new Handler(Looper.getMainLooper());
        this.a = handler;
        this.b = new WeakReference<>(view);
        this.c = i;
        handler.postDelayed(new a(bVar), 100L);
    }

    public static boolean a(ea eaVar) {
        return d.a(eaVar.b.get(), eaVar.c, (AtomicReference<JSONObject>) new AtomicReference()) == null;
    }

    public void a() {
        this.a.removeCallbacksAndMessages(null);
    }
}
