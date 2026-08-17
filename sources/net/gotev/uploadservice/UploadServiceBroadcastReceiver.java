package net.gotev.uploadservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/* loaded from: classes2.dex */
public class UploadServiceBroadcastReceiver extends BroadcastReceiver implements UploadStatusDelegate {
    @Override // net.gotev.uploadservice.UploadStatusDelegate
    public void onCancelled(UploadInfo uploadInfo) {
    }

    @Override // net.gotev.uploadservice.UploadStatusDelegate
    public void onCompleted(UploadInfo uploadInfo, ServerResponse serverResponse) {
    }

    @Override // net.gotev.uploadservice.UploadStatusDelegate
    public void onError(UploadInfo uploadInfo, Exception exc) {
    }

    @Override // net.gotev.uploadservice.UploadStatusDelegate
    public void onProgress(UploadInfo uploadInfo) {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || !UploadService.getActionBroadcast().equals(intent.getAction())) {
            return;
        }
        BroadcastData broadcastData = (BroadcastData) intent.getParcelableExtra("broadcastData");
        switch (broadcastData.getStatus()) {
            case ERROR:
                onError(broadcastData.getUploadInfo(), broadcastData.getException());
                return;
            case COMPLETED:
                onCompleted(broadcastData.getUploadInfo(), broadcastData.getServerResponse());
                return;
            case IN_PROGRESS:
                onProgress(broadcastData.getUploadInfo());
                return;
            case CANCELLED:
                onCancelled(broadcastData.getUploadInfo());
                return;
            default:
                return;
        }
    }

    public void register(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(UploadService.getActionBroadcast());
        context.registerReceiver(this, intentFilter);
    }

    public void unregister(Context context) {
        context.unregisterReceiver(this);
    }
}
