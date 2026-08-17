package com.startapp.networkTest.threads;

import android.os.Build;
import com.startapp.j1;
import com.startapp.l1;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ThreadManager {
    private static final ThreadManager a = new ThreadManager();
    private final ScheduledExecutorService b = new l1(a(1, 60L, TimeUnit.SECONDS, true));
    private final ExecutorService c = new j1(a(0, 1, 30, ThreadManager.class.getSimpleName() + "-Single"));
    private final ExecutorService d = new j1(a(0, 4, 30, ThreadManager.class.getSimpleName() + "-Cached"));

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements ThreadFactory {
        public final AtomicInteger a = new AtomicInteger();
        public final /* synthetic */ String b;

        public a(String str) {
            this.b = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, this.b + "-" + this.a.incrementAndGet());
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            try {
                threadPoolExecutor.getQueue().put(runnable);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private ThreadManager() {
    }

    private static ExecutorService a(int i, int i2, long j, String str) {
        if (Build.VERSION.SDK_INT < 21) {
            return i2 < 2 ? Executors.newSingleThreadExecutor() : Executors.newCachedThreadPool();
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i2, j, TimeUnit.SECONDS, new LinkedTransferQueue<Runnable>() { // from class: com.startapp.networkTest.threads.ThreadManager.1
            @Override // java.util.concurrent.LinkedTransferQueue, java.util.Queue, java.util.concurrent.BlockingQueue
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public boolean offer(Runnable runnable) {
                return tryTransfer(runnable);
            }
        }, new a(str), new b());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    private static ScheduledThreadPoolExecutor a(int i, long j, TimeUnit timeUnit, boolean z) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(i);
        scheduledThreadPoolExecutor.setKeepAliveTime(j, timeUnit);
        if (Build.VERSION.SDK_INT >= 9) {
            scheduledThreadPoolExecutor.allowCoreThreadTimeOut(z);
        }
        return scheduledThreadPoolExecutor;
    }

    public static ThreadManager b() {
        return a;
    }

    public ExecutorService a() {
        return this.d;
    }

    public ExecutorService c() {
        return this.c;
    }

    public ScheduledExecutorService d() {
        return this.b;
    }
}
