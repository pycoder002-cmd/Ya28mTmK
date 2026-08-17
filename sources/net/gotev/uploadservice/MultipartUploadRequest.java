package net.gotev.uploadservice;

import android.content.Context;
import android.content.Intent;
import java.io.FileNotFoundException;
import java.util.List;

/* loaded from: classes2.dex */
public class MultipartUploadRequest extends HttpUploadRequest {
    private static final String LOG_TAG = "net.gotev.uploadservice.MultipartUploadRequest";
    private boolean isUtf8Charset;

    public MultipartUploadRequest(Context context, String str) {
        this(context, null, str);
    }

    public MultipartUploadRequest(Context context, String str, String str2) {
        super(context, str, str2);
        this.isUtf8Charset = false;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public /* bridge */ /* synthetic */ HttpUploadRequest addArrayParameter(String str, List list) {
        return addArrayParameter(str, (List<String>) list);
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public MultipartUploadRequest addArrayParameter(String str, List<String> list) {
        super.addArrayParameter(str, list);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public MultipartUploadRequest addArrayParameter(String str, String... strArr) {
        super.addArrayParameter(str, strArr);
        return this;
    }

    public MultipartUploadRequest addFileToUpload(String str, String str2) throws FileNotFoundException, IllegalArgumentException {
        return addFileToUpload(str, str2, null, null);
    }

    public MultipartUploadRequest addFileToUpload(String str, String str2, String str3) throws FileNotFoundException, IllegalArgumentException {
        return addFileToUpload(str, str2, str3, null);
    }

    public MultipartUploadRequest addFileToUpload(String str, String str2, String str3, String str4) throws FileNotFoundException, IllegalArgumentException {
        UploadFile uploadFile = new UploadFile(str);
        String absolutePath = uploadFile.getAbsolutePath();
        if (str2 == null || "".equals(str2)) {
            throw new IllegalArgumentException("Please specify parameterName value for file: " + absolutePath);
        }
        uploadFile.setProperty("httpParamName", str2);
        if (str4 == null || str4.isEmpty()) {
            str4 = ContentType.autoDetect(absolutePath);
            Logger.debug(LOG_TAG, "Auto-detected MIME type for " + absolutePath + " is: " + str4);
        } else {
            Logger.debug(LOG_TAG, "Content Type set for " + absolutePath + " is: " + str4);
        }
        uploadFile.setProperty("httpContentType", str4);
        if (str3 == null || "".equals(str3)) {
            str3 = uploadFile.getName();
            Logger.debug(LOG_TAG, "Using original file name: " + str3);
        } else {
            Logger.debug(LOG_TAG, "Using custom file name: " + str3);
        }
        uploadFile.setProperty("httpRemoteFileName", str3);
        this.params.addFile(uploadFile);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public MultipartUploadRequest addHeader(String str, String str2) {
        super.addHeader(str, str2);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public MultipartUploadRequest addParameter(String str, String str2) {
        super.addParameter(str, str2);
        return this;
    }

    @Override // net.gotev.uploadservice.UploadRequest
    protected Class<? extends UploadTask> getTaskClass() {
        return MultipartUploadTask.class;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.gotev.uploadservice.HttpUploadRequest, net.gotev.uploadservice.UploadRequest
    public void initializeIntent(Intent intent) {
        super.initializeIntent(intent);
        intent.putExtra("multipartUtf8Charset", this.isUtf8Charset);
    }

    @Override // net.gotev.uploadservice.UploadRequest
    public MultipartUploadRequest setAutoDeleteFilesAfterSuccessfulUpload(boolean z) {
        super.setAutoDeleteFilesAfterSuccessfulUpload(z);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public MultipartUploadRequest setBasicAuth(String str, String str2) {
        super.setBasicAuth(str, str2);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public MultipartUploadRequest setCustomUserAgent(String str) {
        super.setCustomUserAgent(str);
        return this;
    }

    @Override // net.gotev.uploadservice.UploadRequest
    public MultipartUploadRequest setMaxRetries(int i) {
        super.setMaxRetries(i);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public MultipartUploadRequest setMethod(String str) {
        super.setMethod(str);
        return this;
    }

    @Override // net.gotev.uploadservice.UploadRequest
    public MultipartUploadRequest setNotificationConfig(UploadNotificationConfig uploadNotificationConfig) {
        super.setNotificationConfig(uploadNotificationConfig);
        return this;
    }

    @Override // net.gotev.uploadservice.HttpUploadRequest
    public MultipartUploadRequest setUsesFixedLengthStreamingMode(boolean z) {
        super.setUsesFixedLengthStreamingMode(z);
        return this;
    }

    public MultipartUploadRequest setUtf8Charset() {
        this.isUtf8Charset = true;
        return this;
    }
}
