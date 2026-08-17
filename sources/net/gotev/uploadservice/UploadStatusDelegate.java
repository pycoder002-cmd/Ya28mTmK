package net.gotev.uploadservice;

/* loaded from: classes2.dex */
public interface UploadStatusDelegate {
    void onCancelled(UploadInfo uploadInfo);

    void onCompleted(UploadInfo uploadInfo, ServerResponse serverResponse);

    void onError(UploadInfo uploadInfo, Exception exc);

    void onProgress(UploadInfo uploadInfo);
}
