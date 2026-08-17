package com.downloader;

/* loaded from: classes.dex */
public class Error {
    private boolean isConnectionError;
    private boolean isServerError;

    public boolean isConnectionError() {
        return this.isConnectionError;
    }

    public boolean isServerError() {
        return this.isServerError;
    }

    public void setConnectionError(boolean z) {
        this.isConnectionError = z;
    }

    public void setServerError(boolean z) {
        this.isServerError = z;
    }
}
