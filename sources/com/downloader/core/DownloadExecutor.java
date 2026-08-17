package com.downloader.core;

import com.downloader.internal.DownloadRunnable;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class DownloadExecutor extends ThreadPoolExecutor {
    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadExecutor(int i, ThreadFactory threadFactory) {
        super(i, i, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), threadFactory);
    }

    @Override // java.util.concurrent.AbstractExecutorService, java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        DownloadFutureTask downloadFutureTask = new DownloadFutureTask((DownloadRunnable) runnable);
        execute(downloadFutureTask);
        return downloadFutureTask;
    }
}
