package com.downloader.core;

import com.downloader.Priority;
import com.downloader.internal.DownloadRunnable;
import java.util.concurrent.FutureTask;

/* loaded from: classes.dex */
public class DownloadFutureTask extends FutureTask<DownloadRunnable> implements Comparable<DownloadFutureTask> {
    private final DownloadRunnable runnable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadFutureTask(DownloadRunnable downloadRunnable) {
        super(downloadRunnable, null);
        this.runnable = downloadRunnable;
    }

    @Override // java.lang.Comparable
    public int compareTo(DownloadFutureTask downloadFutureTask) {
        Priority priority = this.runnable.priority;
        Priority priority2 = downloadFutureTask.runnable.priority;
        return priority == priority2 ? this.runnable.sequence - downloadFutureTask.runnable.sequence : priority2.ordinal() - priority.ordinal();
    }
}
