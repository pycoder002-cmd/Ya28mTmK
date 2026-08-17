package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class m8 {
    public final Context a;
    public final oa b;
    public final Runnable d = new a();
    public final Handler c = new Handler(Looper.getMainLooper());

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            m8.this.a();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements oa {
        public boolean a;
        public final /* synthetic */ oa b;

        public b(oa oaVar) {
            this.b = oaVar;
        }

        @Override // com.startapp.oa
        public synchronized void a(Object obj) {
            if (!this.a) {
                this.a = true;
                m8.this.c.removeCallbacksAndMessages(null);
                this.b.a(obj);
            }
        }
    }

    public m8(Context context, oa oaVar) {
        this.a = context;
        this.b = new b(oaVar);
    }

    public abstract void a();
}
