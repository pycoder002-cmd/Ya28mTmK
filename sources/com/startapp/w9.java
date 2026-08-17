package com.startapp;

import android.os.Build;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class w9 implements Executor {
    public final Queue<Runnable> a;
    public final Executor b;
    public Runnable c;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ Runnable a;

        public a(Runnable runnable) {
            this.a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.a.run();
            } finally {
                w9.this.a();
            }
        }
    }

    public w9(Executor executor) {
        if (Build.VERSION.SDK_INT >= 9) {
            this.a = new ArrayDeque();
        } else {
            this.a = new LinkedList();
        }
        this.b = executor;
    }

    public synchronized void a() {
        Runnable poll = this.a.poll();
        this.c = poll;
        if (poll != null) {
            this.b.execute(poll);
        }
    }

    @Override // java.util.concurrent.Executor
    public synchronized void execute(Runnable runnable) {
        this.a.offer(new a(runnable));
        if (this.c == null) {
            a();
        }
    }
}
