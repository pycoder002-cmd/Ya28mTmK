package com.downloader.core;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class DefaultExecutorSupplier implements ExecutorSupplier {
    private static final int DEFAULT_MAX_NUM_THREADS = (2 * Runtime.getRuntime().availableProcessors()) + 1;
    private final DownloadExecutor networkExecutor = new DownloadExecutor(DEFAULT_MAX_NUM_THREADS, new PriorityThreadFactory(10));
    private final Executor backgroundExecutor = Executors.newSingleThreadExecutor();
    private final Executor mainThreadExecutor = new MainThreadExecutor();

    @Override // com.downloader.core.ExecutorSupplier
    public Executor forBackgroundTasks() {
        return this.backgroundExecutor;
    }

    @Override // com.downloader.core.ExecutorSupplier
    public DownloadExecutor forDownloadTasks() {
        return this.networkExecutor;
    }

    @Override // com.downloader.core.ExecutorSupplier
    public Executor forMainThreadTasks() {
        return this.mainThreadExecutor;
    }
}
