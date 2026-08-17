package net.gotev.uploadservice;

import android.annotation.SuppressLint;
import android.content.Intent;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import net.gotev.uploadservice.http.HttpConnection;

/* loaded from: classes2.dex */
public abstract class HttpUploadTask extends UploadTask {
    private static final String LOG_TAG = "HttpUploadTask";
    private HttpConnection connection;
    protected HttpUploadTaskParameters httpParams = null;

    protected abstract long getBodyLength() throws UnsupportedEncodingException;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.gotev.uploadservice.UploadTask
    public void init(UploadService uploadService, Intent intent) throws IOException {
        super.init(uploadService, intent);
        this.httpParams = (HttpUploadTaskParameters) intent.getParcelableExtra("httpTaskParameters");
    }

    @Override // net.gotev.uploadservice.UploadTask
    @SuppressLint({"NewApi"})
    protected void upload() throws Exception {
        Logger.debug(LOG_TAG, "Starting upload task with ID " + this.params.getId());
        try {
            this.totalBytes = getBodyLength();
            if (this.httpParams.isCustomUserAgentDefined()) {
                this.httpParams.addRequestHeader("User-Agent", this.httpParams.getCustomUserAgent());
            }
            this.connection = UploadService.HTTP_STACK.createNewConnection(this.httpParams.getMethod(), this.params.getServerUrl());
            this.connection.setHeaders(this.httpParams.getRequestHeaders(), this.httpParams.isUsesFixedLengthStreamingMode(), getBodyLength());
            writeBody(this.connection);
            int serverResponseCode = this.connection.getServerResponseCode();
            Logger.debug(LOG_TAG, "Server responded with HTTP " + serverResponseCode + " to upload with ID: " + this.params.getId());
            if (this.shouldContinue) {
                broadcastCompleted(serverResponseCode, this.connection.getServerResponseBody(), this.connection.getServerResponseHeaders());
            }
        } finally {
            this.connection.close();
        }
    }

    protected abstract void writeBody(HttpConnection httpConnection) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public final void writeStream(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[UploadService.BUFFER_SIZE];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read <= 0 || !this.shouldContinue) {
                return;
            }
            this.connection.writeBody(bArr, read);
            this.uploadedBytes += read;
            broadcastProgress(this.uploadedBytes, this.totalBytes);
        }
    }
}
