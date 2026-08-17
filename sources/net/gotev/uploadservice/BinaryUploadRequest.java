package net.gotev.uploadservice;

import android.content.Context;
import java.io.FileNotFoundException;
import java.util.List;

/* loaded from: classes2.dex */
public class BinaryUploadRequest extends HttpUploadRequest {
    public BinaryUploadRequest(Context context, String str) {
        this(context, null, str);
    }

    public BinaryUploadRequest(Context context, String str, String str2) {
        super(context, str, str2);
    }

    private void logDoesNotSupportParameters() {
        Logger.error(getClass().getSimpleName(), "This upload method does not support adding parameters");
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public HttpUploadRequest addArrayParameter(String str, List<String> list) {
        logDoesNotSupportParameters();
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public HttpUploadRequest addArrayParameter(String str, String... strArr) {
        logDoesNotSupportParameters();
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public BinaryUploadRequest addHeader(String str, String str2) {
        super.addHeader(str, str2);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public HttpUploadRequest addParameter(String str, String str2) {
        logDoesNotSupportParameters();
        return this;
    }

    @Override // net.gotev.uploadservice.UploadRequest
    protected Class<? extends UploadTask> getTaskClass() {
        return BinaryUploadTask.class;
    }

    @Override // net.gotev.uploadservice.UploadRequest
    public BinaryUploadRequest setAutoDeleteFilesAfterSuccessfulUpload(boolean z) {
        super.setAutoDeleteFilesAfterSuccessfulUpload(z);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public BinaryUploadRequest setBasicAuth(String str, String str2) {
        super.setBasicAuth(str, str2);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public BinaryUploadRequest setCustomUserAgent(String str) {
        super.setCustomUserAgent(str);
        return this;
    }

    public BinaryUploadRequest setFileToUpload(String str) throws FileNotFoundException {
        this.params.getFiles().clear();
        this.params.addFile(new UploadFile(str));
        return this;
    }

    @Override // net.gotev.uploadservice.UploadRequest
    public BinaryUploadRequest setMaxRetries(int i) {
        super.setMaxRetries(i);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public BinaryUploadRequest setMethod(String str) {
        super.setMethod(str);
        return this;
    }

    @Override // net.gotev.uploadservice.UploadRequest
    public BinaryUploadRequest setNotificationConfig(UploadNotificationConfig uploadNotificationConfig) {
        super.setNotificationConfig(uploadNotificationConfig);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public BinaryUploadRequest setUsesFixedLengthStreamingMode(boolean z) {
        super.setUsesFixedLengthStreamingMode(z);
        return this;
    }
}
