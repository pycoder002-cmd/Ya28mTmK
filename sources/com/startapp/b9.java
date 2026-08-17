package com.startapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class b9 {
    public final Context a;
    public String b;
    public b c;
    public int d;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {

        /* compiled from: StartAppSDK */
        /* renamed from: com.startapp.b9$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class RunnableC0056a implements Runnable {
            public final /* synthetic */ Bitmap a;

            public RunnableC0056a(Bitmap bitmap) {
                this.a = bitmap;
            }

            @Override // java.lang.Runnable
            public void run() {
                b9 b9Var = b9.this;
                b bVar = b9Var.c;
                if (bVar != null) {
                    bVar.a(this.a, b9Var.d);
                }
            }
        }

        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            new Handler(Looper.getMainLooper()).post(new RunnableC0056a(c9.b(b9.this.b)));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface b {
        void a(Bitmap bitmap, int i);
    }

    public b9(Context context, String str, b bVar, int i) {
        this.a = context;
        this.b = str;
        this.c = bVar;
        this.d = i;
    }

    public void a() {
        ComponentLocator.a(this.a).C.b().execute(new a());
    }
}
