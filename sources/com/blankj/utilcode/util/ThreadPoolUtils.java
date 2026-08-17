package com.blankj.utilcode.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class ThreadPoolUtils {
    public static final int CachedThread = 1;
    public static final int FixedThread = 0;
    public static final int SingleThread = 2;
    private ExecutorService exec;
    private ScheduledExecutorService scheduleExec;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface Type {
    }

    private ThreadPoolUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public ThreadPoolUtils(int i, int i2) {
        this.scheduleExec = Executors.newScheduledThreadPool(i2);
        switch (i) {
            case 0:
                this.exec = Executors.newFixedThreadPool(i2);
                return;
            case 1:
                this.exec = Executors.newCachedThreadPool();
                return;
            case 2:
                this.exec = Executors.newSingleThreadExecutor();
                return;
            default:
                return;
        }
    }

    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.exec.awaitTermination(j, timeUnit);
    }

    public void execute(Runnable runnable) {
        this.exec.execute(runnable);
    }

    public void execute(List<Runnable> list) {
        Iterator<Runnable> it = list.iterator();
        while (it.hasNext()) {
            this.exec.execute(it.next());
        }
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.exec.invokeAll(collection);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.exec.invokeAll(collection, j, timeUnit);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws InterruptedException, ExecutionException {
        return (T) this.exec.invokeAny(collection);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return (T) this.exec.invokeAny(collection, j, timeUnit);
    }

    public boolean isShutDown() {
        return this.exec.isShutdown();
    }

    public boolean isTerminated() {
        return this.exec.isTerminated();
    }

    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.scheduleExec.schedule(runnable, j, timeUnit);
    }

    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        return this.scheduleExec.schedule(callable, j, timeUnit);
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.scheduleExec.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
    }

    public ScheduledFuture<?> scheduleWithFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.scheduleExec.scheduleAtFixedRate(runnable, j, j2, timeUnit);
    }

    public void shutDown() {
        this.exec.shutdown();
    }

    public List<Runnable> shutDownNow() {
        return this.exec.shutdownNow();
    }

    public Future<?> submit(Runnable runnable) {
        return this.exec.submit(runnable);
    }

    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.exec.submit(runnable, t);
    }

    public <T> Future<T> submit(Callable<T> callable) {
        return this.exec.submit(callable);
    }
}
