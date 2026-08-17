package com.downloader.internal;

import com.downloader.Status;
import com.downloader.core.Core;
import com.downloader.request.DownloadRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class DownloadRequestQueue {
    private static DownloadRequestQueue instance;
    private final Map<Integer, DownloadRequest> currentRequestMap = new ConcurrentHashMap();
    private final AtomicInteger sequenceGenerator = new AtomicInteger();

    private DownloadRequestQueue() {
    }

    private void cancelAndRemoveFromMap(DownloadRequest downloadRequest) {
        if (downloadRequest != null) {
            downloadRequest.cancel();
            this.currentRequestMap.remove(Integer.valueOf(downloadRequest.getDownloadId()));
        }
    }

    public static DownloadRequestQueue getInstance() {
        if (instance == null) {
            synchronized (DownloadRequestQueue.class) {
                if (instance == null) {
                    instance = new DownloadRequestQueue();
                }
            }
        }
        return instance;
    }

    private int getSequenceNumber() {
        return this.sequenceGenerator.incrementAndGet();
    }

    public static void initialize() {
        getInstance();
    }

    public void addRequest(DownloadRequest downloadRequest) {
        this.currentRequestMap.put(Integer.valueOf(downloadRequest.getDownloadId()), downloadRequest);
        downloadRequest.setStatus(Status.QUEUED);
        downloadRequest.setSequenceNumber(getSequenceNumber());
        downloadRequest.setFuture(Core.getInstance().getExecutorSupplier().forDownloadTasks().submit(new DownloadRunnable(downloadRequest)));
    }

    public void cancel(int i) {
        cancelAndRemoveFromMap(this.currentRequestMap.get(Integer.valueOf(i)));
    }

    public void cancel(Object obj) {
        Iterator<Map.Entry<Integer, DownloadRequest>> it = this.currentRequestMap.entrySet().iterator();
        while (it.hasNext()) {
            DownloadRequest value = it.next().getValue();
            if ((value.getTag() instanceof String) && (obj instanceof String)) {
                if (((String) value.getTag()).equals((String) obj)) {
                    cancelAndRemoveFromMap(value);
                }
            } else if (value.getTag().equals(obj)) {
                cancelAndRemoveFromMap(value);
            }
        }
    }

    public void cancelAll() {
        Iterator<Map.Entry<Integer, DownloadRequest>> it = this.currentRequestMap.entrySet().iterator();
        while (it.hasNext()) {
            cancelAndRemoveFromMap(it.next().getValue());
        }
    }

    public void finish(DownloadRequest downloadRequest) {
        this.currentRequestMap.remove(Integer.valueOf(downloadRequest.getDownloadId()));
    }

    public Status getStatus(int i) {
        DownloadRequest downloadRequest = this.currentRequestMap.get(Integer.valueOf(i));
        return downloadRequest != null ? downloadRequest.getStatus() : Status.UNKNOWN;
    }

    public void pause(int i) {
        DownloadRequest downloadRequest = this.currentRequestMap.get(Integer.valueOf(i));
        if (downloadRequest != null) {
            downloadRequest.setStatus(Status.PAUSED);
        }
    }

    public void resume(int i) {
        DownloadRequest downloadRequest = this.currentRequestMap.get(Integer.valueOf(i));
        if (downloadRequest != null) {
            downloadRequest.setStatus(Status.QUEUED);
            downloadRequest.setFuture(Core.getInstance().getExecutorSupplier().forDownloadTasks().submit(new DownloadRunnable(downloadRequest)));
        }
    }
}
