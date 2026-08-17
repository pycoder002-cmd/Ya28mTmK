package com.downloader.internal;

import com.downloader.Error;
import com.downloader.Priority;
import com.downloader.Response;
import com.downloader.Status;
import com.downloader.request.DownloadRequest;

/* loaded from: classes.dex */
public class DownloadRunnable implements Runnable {
    public final Priority priority;
    public final DownloadRequest request;
    public final int sequence;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadRunnable(DownloadRequest downloadRequest) {
        this.request = downloadRequest;
        this.priority = downloadRequest.getPriority();
        this.sequence = downloadRequest.getSequenceNumber();
    }

    @Override // java.lang.Runnable
    public void run() {
        this.request.setStatus(Status.RUNNING);
        Response run = DownloadTask.create(this.request).run();
        if (run.isSuccessful()) {
            this.request.deliverSuccess();
            return;
        }
        if (run.isPaused()) {
            this.request.deliverPauseEvent();
        } else if (run.getError() != null) {
            this.request.deliverError(run.getError());
        } else {
            if (run.isCancelled()) {
                return;
            }
            this.request.deliverError(new Error());
        }
    }
}
