package com.downloader;

/* loaded from: classes.dex */
public interface OnDownloadListener {
    void onDownloadComplete();

    void onError(Error error);
}
