package com.downloader;

/* loaded from: classes.dex */
public class Response {
    private Error error;
    private boolean isCancelled;
    private boolean isPaused;
    private boolean isSuccessful;

    public Error getError() {
        return this.error;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public boolean isPaused() {
        return this.isPaused;
    }

    public boolean isSuccessful() {
        return this.isSuccessful;
    }

    public void setCancelled(boolean z) {
        this.isCancelled = z;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public void setPaused(boolean z) {
        this.isPaused = z;
    }

    public void setSuccessful(boolean z) {
        this.isSuccessful = z;
    }
}
