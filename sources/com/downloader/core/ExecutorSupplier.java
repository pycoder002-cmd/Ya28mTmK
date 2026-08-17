package com.downloader.core;

import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public interface ExecutorSupplier {
    Executor forBackgroundTasks();

    DownloadExecutor forDownloadTasks();

    Executor forMainThreadTasks();
}
