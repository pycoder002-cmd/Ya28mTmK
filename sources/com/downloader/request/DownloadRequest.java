package com.downloader.request;

import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.Priority;
import com.downloader.Response;
import com.downloader.Status;
import com.downloader.core.Core;
import com.downloader.internal.ComponentHolder;
import com.downloader.internal.DownloadRequestQueue;
import com.downloader.internal.SynchronousCall;
import com.downloader.utils.Utils;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

/* loaded from: classes.dex */
public class DownloadRequest {
    private int connectTimeout;
    private String dirPath;
    private int downloadId;
    private long downloadedBytes;
    private String fileName;
    private Future future;
    private HashMap<String, List<String>> headerMap;
    private OnCancelListener onCancelListener;
    private OnDownloadListener onDownloadListener;
    private OnPauseListener onPauseListener;
    private OnProgressListener onProgressListener;
    private OnStartOrResumeListener onStartOrResumeListener;
    private Priority priority;
    private int readTimeout;
    private int sequenceNumber;
    private Status status;
    private Object tag;
    private long totalBytes;
    private String url;
    private String userAgent;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadRequest(DownloadRequestBuilder downloadRequestBuilder) {
        this.url = downloadRequestBuilder.url;
        this.dirPath = downloadRequestBuilder.dirPath;
        this.fileName = downloadRequestBuilder.fileName;
        this.headerMap = downloadRequestBuilder.headerMap;
        this.priority = downloadRequestBuilder.priority;
        this.tag = downloadRequestBuilder.tag;
        this.readTimeout = downloadRequestBuilder.readTimeout != 0 ? downloadRequestBuilder.readTimeout : getReadTimeoutFromConfig();
        this.connectTimeout = downloadRequestBuilder.connectTimeout != 0 ? downloadRequestBuilder.connectTimeout : getConnectTimeoutFromConfig();
        this.userAgent = downloadRequestBuilder.userAgent;
    }

    private void deliverCancelEvent() {
        Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() { // from class: com.downloader.request.DownloadRequest.5
            @Override // java.lang.Runnable
            public void run() {
                if (DownloadRequest.this.onCancelListener != null) {
                    DownloadRequest.this.onCancelListener.onCancel();
                }
            }
        });
    }

    private void destroy() {
        this.onProgressListener = null;
        this.onDownloadListener = null;
        this.onStartOrResumeListener = null;
        this.onPauseListener = null;
        this.onCancelListener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish() {
        destroy();
        DownloadRequestQueue.getInstance().finish(this);
    }

    private int getConnectTimeoutFromConfig() {
        return ComponentHolder.getInstance().getConnectTimeout();
    }

    private int getReadTimeoutFromConfig() {
        return ComponentHolder.getInstance().getReadTimeout();
    }

    public void cancel() {
        this.status = Status.CANCELLED;
        if (this.future != null) {
            this.future.cancel(true);
        }
        deliverCancelEvent();
        Utils.deleteTempFileAndDatabaseEntryInBackground(Utils.getTempPath(this.dirPath, this.fileName), this.downloadId);
    }

    public void deliverError(final Error error) {
        if (this.status != Status.CANCELLED) {
            Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() { // from class: com.downloader.request.DownloadRequest.1
                @Override // java.lang.Runnable
                public void run() {
                    if (DownloadRequest.this.onDownloadListener != null) {
                        DownloadRequest.this.onDownloadListener.onError(error);
                    }
                    DownloadRequest.this.finish();
                }
            });
        }
    }

    public void deliverPauseEvent() {
        if (this.status != Status.CANCELLED) {
            Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() { // from class: com.downloader.request.DownloadRequest.4
                @Override // java.lang.Runnable
                public void run() {
                    if (DownloadRequest.this.onPauseListener != null) {
                        DownloadRequest.this.onPauseListener.onPause();
                    }
                }
            });
        }
    }

    public void deliverStartEvent() {
        if (this.status != Status.CANCELLED) {
            Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() { // from class: com.downloader.request.DownloadRequest.3
                @Override // java.lang.Runnable
                public void run() {
                    if (DownloadRequest.this.onStartOrResumeListener != null) {
                        DownloadRequest.this.onStartOrResumeListener.onStartOrResume();
                    }
                }
            });
        }
    }

    public void deliverSuccess() {
        if (this.status != Status.CANCELLED) {
            setStatus(Status.COMPLETED);
            Core.getInstance().getExecutorSupplier().forMainThreadTasks().execute(new Runnable() { // from class: com.downloader.request.DownloadRequest.2
                @Override // java.lang.Runnable
                public void run() {
                    if (DownloadRequest.this.onDownloadListener != null) {
                        DownloadRequest.this.onDownloadListener.onDownloadComplete();
                    }
                    DownloadRequest.this.finish();
                }
            });
        }
    }

    public Response executeSync() {
        this.downloadId = Utils.getUniqueId(this.url, this.dirPath, this.fileName);
        return new SynchronousCall(this).execute();
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public String getDirPath() {
        return this.dirPath;
    }

    public int getDownloadId() {
        return this.downloadId;
    }

    public long getDownloadedBytes() {
        return this.downloadedBytes;
    }

    public String getFileName() {
        return this.fileName;
    }

    public Future getFuture() {
        return this.future;
    }

    public HashMap<String, List<String>> getHeaders() {
        return this.headerMap;
    }

    public OnProgressListener getOnProgressListener() {
        return this.onProgressListener;
    }

    public Priority getPriority() {
        return this.priority;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public Status getStatus() {
        return this.status;
    }

    public Object getTag() {
        return this.tag;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUserAgent() {
        if (this.userAgent == null) {
            this.userAgent = ComponentHolder.getInstance().getUserAgent();
        }
        return this.userAgent;
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public void setDirPath(String str) {
        this.dirPath = str;
    }

    public void setDownloadId(int i) {
        this.downloadId = i;
    }

    public void setDownloadedBytes(long j) {
        this.downloadedBytes = j;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public void setFuture(Future future) {
        this.future = future;
    }

    public DownloadRequest setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

    public DownloadRequest setOnPauseListener(OnPauseListener onPauseListener) {
        this.onPauseListener = onPauseListener;
        return this;
    }

    public DownloadRequest setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
        return this;
    }

    public DownloadRequest setOnStartOrResumeListener(OnStartOrResumeListener onStartOrResumeListener) {
        this.onStartOrResumeListener = onStartOrResumeListener;
        return this;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTag(Object obj) {
        this.tag = obj;
    }

    public void setTotalBytes(long j) {
        this.totalBytes = j;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setUserAgent(String str) {
        this.userAgent = str;
    }

    public int start(OnDownloadListener onDownloadListener) {
        this.onDownloadListener = onDownloadListener;
        this.downloadId = Utils.getUniqueId(this.url, this.dirPath, this.fileName);
        DownloadRequestQueue.getInstance().addRequest(this);
        return this.downloadId;
    }
}
