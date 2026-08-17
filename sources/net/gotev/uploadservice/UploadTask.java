package net.gotev.uploadservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import net.gotev.uploadservice.BroadcastData;

/* loaded from: classes2.dex */
public abstract class UploadTask implements Runnable {
    protected static final byte[] EMPTY_RESPONSE = "".getBytes(Charset.forName("UTF-8"));
    private static final String LOG_TAG = "UploadTask";
    protected static final int TASK_COMPLETED_SUCCESSFULLY = 200;
    private int attempts;
    private long lastProgressNotificationTime;
    private Handler mainThreadHandler;
    private NotificationCompat.Builder notification;
    private int notificationId;
    private NotificationManager notificationManager;
    protected UploadService service;
    protected long totalBytes;
    protected long uploadedBytes;
    protected UploadTaskParameters params = null;
    private final List<String> successfullyUploadedFiles = new ArrayList();
    protected boolean shouldContinue = true;
    private final long startTime = new Date().getTime();

    private void broadcastError(final Exception exc) {
        Logger.info(LOG_TAG, "Broadcasting error for upload with ID: " + this.params.getId() + ". " + exc.getMessage());
        final UploadInfo uploadInfo = new UploadInfo(this.params.getId(), this.startTime, this.uploadedBytes, this.totalBytes, this.attempts + (-1), this.successfullyUploadedFiles);
        BroadcastData exception = new BroadcastData().setStatus(BroadcastData.Status.ERROR).setUploadInfo(uploadInfo).setException(exc);
        final UploadStatusDelegate uploadStatusDelegate = UploadService.getUploadStatusDelegate(this.params.getId());
        if (uploadStatusDelegate != null) {
            this.mainThreadHandler.post(new Runnable() { // from class: net.gotev.uploadservice.UploadTask.4
                @Override // java.lang.Runnable
                public void run() {
                    uploadStatusDelegate.onError(uploadInfo, exc);
                }
            });
        } else {
            this.service.sendBroadcast(exception.getIntent());
        }
        updateNotificationError(uploadInfo);
        this.service.taskCompleted(this.params.getId());
    }

    private void createNotification(UploadInfo uploadInfo) {
        if (this.params.getNotificationConfig() == null) {
            return;
        }
        this.notification.setContentTitle(replacePlaceholders(this.params.getNotificationConfig().getTitle(), uploadInfo)).setContentText(replacePlaceholders(this.params.getNotificationConfig().getInProgressMessage(), uploadInfo)).setContentIntent(this.params.getNotificationConfig().getPendingIntent(this.service)).setSmallIcon(this.params.getNotificationConfig().getIconResourceID()).setGroup(UploadService.NAMESPACE).setProgress(100, 0, true).setOngoing(true);
        Notification build = this.notification.build();
        if (this.service.holdForegroundNotification(this.params.getId(), build)) {
            this.notificationManager.cancel(this.notificationId);
        } else {
            this.notificationManager.notify(this.notificationId, build);
        }
    }

    private boolean deleteFile(File file) {
        boolean z;
        try {
            z = file.delete();
        } catch (Exception e) {
            e = e;
            z = false;
        }
        try {
            if (z) {
                Logger.info(LOG_TAG, "Successfully deleted: " + file.getAbsolutePath());
            } else {
                Logger.error(LOG_TAG, "Unable to delete: " + file.getAbsolutePath());
            }
        } catch (Exception e2) {
            e = e2;
            Logger.error(LOG_TAG, "Error while deleting: " + file.getAbsolutePath() + " Check if you granted: android.permission.WRITE_EXTERNAL_STORAGE", e);
            return z;
        }
        return z;
    }

    private String replacePlaceholders(String str, UploadInfo uploadInfo) {
        return str.replace(Placeholders.ELAPSED_TIME, uploadInfo.getElapsedTimeString()).replace(Placeholders.PROGRESS, uploadInfo.getProgressPercent() + "%").replace(Placeholders.UPLOAD_RATE, uploadInfo.getUploadRateString());
    }

    private void setRingtone() {
        if (this.params.getNotificationConfig().isRingToneEnabled()) {
            this.notification.setSound(RingtoneManager.getActualDefaultRingtoneUri(this.service, 2));
            this.notification.setOnlyAlertOnce(false);
        }
    }

    private void updateNotificationCompleted(UploadInfo uploadInfo) {
        if (this.params.getNotificationConfig() == null) {
            return;
        }
        this.notificationManager.cancel(this.notificationId);
        if (this.params.getNotificationConfig().isAutoClearOnSuccess()) {
            return;
        }
        this.notification.setContentTitle(replacePlaceholders(this.params.getNotificationConfig().getTitle(), uploadInfo)).setContentText(replacePlaceholders(this.params.getNotificationConfig().getCompletedMessage(), uploadInfo)).setContentIntent(this.params.getNotificationConfig().getPendingIntent(this.service)).setAutoCancel(this.params.getNotificationConfig().isClearOnAction()).setSmallIcon(this.params.getNotificationConfig().getIconResourceID()).setGroup(UploadService.NAMESPACE).setProgress(0, 0, false).setOngoing(false);
        setRingtone();
        this.notificationManager.notify(this.notificationId + 1, this.notification.build());
    }

    private void updateNotificationError(UploadInfo uploadInfo) {
        if (this.params.getNotificationConfig() == null) {
            return;
        }
        this.notificationManager.cancel(this.notificationId);
        this.notification.setContentTitle(replacePlaceholders(this.params.getNotificationConfig().getTitle(), uploadInfo)).setContentText(replacePlaceholders(this.params.getNotificationConfig().getErrorMessage(), uploadInfo)).setContentIntent(this.params.getNotificationConfig().getPendingIntent(this.service)).setAutoCancel(this.params.getNotificationConfig().isClearOnAction()).setSmallIcon(this.params.getNotificationConfig().getIconResourceID()).setGroup(UploadService.NAMESPACE).setProgress(0, 0, false).setOngoing(false);
        setRingtone();
        this.notificationManager.notify(this.notificationId + 1, this.notification.build());
    }

    private void updateNotificationProgress(UploadInfo uploadInfo) {
        if (this.params.getNotificationConfig() == null) {
            return;
        }
        this.notification.setContentTitle(replacePlaceholders(this.params.getNotificationConfig().getTitle(), uploadInfo)).setContentText(replacePlaceholders(this.params.getNotificationConfig().getInProgressMessage(), uploadInfo)).setContentIntent(this.params.getNotificationConfig().getPendingIntent(this.service)).setSmallIcon(this.params.getNotificationConfig().getIconResourceID()).setGroup(UploadService.NAMESPACE).setProgress((int) uploadInfo.getTotalBytes(), (int) uploadInfo.getUploadedBytes(), false).setOngoing(true);
        Notification build = this.notification.build();
        if (this.service.holdForegroundNotification(this.params.getId(), build)) {
            this.notificationManager.cancel(this.notificationId);
        } else {
            this.notificationManager.notify(this.notificationId, build);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addSuccessfullyUploadedFile(String str) {
        if (this.successfullyUploadedFiles.contains(str)) {
            return;
        }
        this.successfullyUploadedFiles.add(str);
    }

    protected final void broadcastCancelled() {
        Logger.debug(LOG_TAG, "Broadcasting cancellation for upload with ID: " + this.params.getId());
        final UploadInfo uploadInfo = new UploadInfo(this.params.getId(), this.startTime, this.uploadedBytes, this.totalBytes, this.attempts + (-1), this.successfullyUploadedFiles);
        BroadcastData uploadInfo2 = new BroadcastData().setStatus(BroadcastData.Status.CANCELLED).setUploadInfo(uploadInfo);
        final UploadStatusDelegate uploadStatusDelegate = UploadService.getUploadStatusDelegate(this.params.getId());
        if (uploadStatusDelegate != null) {
            this.mainThreadHandler.post(new Runnable() { // from class: net.gotev.uploadservice.UploadTask.3
                @Override // java.lang.Runnable
                public void run() {
                    uploadStatusDelegate.onCancelled(uploadInfo);
                }
            });
        } else {
            this.service.sendBroadcast(uploadInfo2.getIntent());
        }
        updateNotificationError(uploadInfo);
        this.service.taskCompleted(this.params.getId());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void broadcastCompleted(int i, byte[] bArr, LinkedHashMap<String, String> linkedHashMap) {
        boolean z = i / 100 == 2;
        if (z) {
            onSuccessfulUpload();
            if (this.params.isAutoDeleteSuccessfullyUploadedFiles() && !this.successfullyUploadedFiles.isEmpty()) {
                Iterator<String> it = this.successfullyUploadedFiles.iterator();
                while (it.hasNext()) {
                    deleteFile(new File(it.next()));
                }
            }
        }
        Logger.debug(LOG_TAG, "Broadcasting upload completed for " + this.params.getId());
        final UploadInfo uploadInfo = new UploadInfo(this.params.getId(), this.startTime, this.uploadedBytes, this.totalBytes, this.attempts + (-1), this.successfullyUploadedFiles);
        final ServerResponse serverResponse = new ServerResponse(i, bArr, linkedHashMap);
        BroadcastData serverResponse2 = new BroadcastData().setStatus(BroadcastData.Status.COMPLETED).setUploadInfo(uploadInfo).setServerResponse(serverResponse);
        final UploadStatusDelegate uploadStatusDelegate = UploadService.getUploadStatusDelegate(this.params.getId());
        if (uploadStatusDelegate != null) {
            this.mainThreadHandler.post(new Runnable() { // from class: net.gotev.uploadservice.UploadTask.2
                @Override // java.lang.Runnable
                public void run() {
                    uploadStatusDelegate.onCompleted(uploadInfo, serverResponse);
                }
            });
        } else {
            this.service.sendBroadcast(serverResponse2.getIntent());
        }
        if (z) {
            updateNotificationCompleted(uploadInfo);
        } else {
            updateNotificationError(uploadInfo);
        }
        this.service.taskCompleted(this.params.getId());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void broadcastProgress(long j, long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < this.lastProgressNotificationTime + 166) {
            return;
        }
        setLastProgressNotificationTime(currentTimeMillis);
        Logger.debug(LOG_TAG, "Broadcasting upload progress for " + this.params.getId() + ": " + j + " bytes of " + j2);
        final UploadInfo uploadInfo = new UploadInfo(this.params.getId(), this.startTime, j, j2, this.attempts + (-1), this.successfullyUploadedFiles);
        BroadcastData uploadInfo2 = new BroadcastData().setStatus(BroadcastData.Status.IN_PROGRESS).setUploadInfo(uploadInfo);
        final UploadStatusDelegate uploadStatusDelegate = UploadService.getUploadStatusDelegate(this.params.getId());
        if (uploadStatusDelegate != null) {
            this.mainThreadHandler.post(new Runnable() { // from class: net.gotev.uploadservice.UploadTask.1
                @Override // java.lang.Runnable
                public void run() {
                    uploadStatusDelegate.onProgress(uploadInfo);
                }
            });
        } else {
            this.service.sendBroadcast(uploadInfo2.getIntent());
        }
        updateNotificationProgress(uploadInfo);
    }

    public final void cancel() {
        this.shouldContinue = false;
    }

    protected final List<String> getSuccessfullyUploadedFiles() {
        return this.successfullyUploadedFiles;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init(UploadService uploadService, Intent intent) throws IOException {
        this.notificationManager = (NotificationManager) uploadService.getSystemService("notification");
        this.notification = new NotificationCompat.Builder(uploadService);
        this.service = uploadService;
        this.mainThreadHandler = new Handler(uploadService.getMainLooper());
        this.params = (UploadTaskParameters) intent.getParcelableExtra("taskParameters");
    }

    protected void onSuccessfulUpload() {
    }

    @Override // java.lang.Runnable
    public final void run() {
        createNotification(new UploadInfo(this.params.getId()));
        this.attempts = 0;
        int i = UploadService.INITIAL_RETRY_WAIT_TIME;
        while (this.attempts <= this.params.getMaxRetries() && this.shouldContinue) {
            this.attempts++;
            try {
                upload();
                break;
            } catch (Exception e) {
                if (!this.shouldContinue) {
                    break;
                }
                if (this.attempts > this.params.getMaxRetries()) {
                    broadcastError(e);
                } else {
                    Logger.info(LOG_TAG, "Error in uploadId " + this.params.getId() + " on attempt " + this.attempts + ". Waiting " + (i / 1000) + "s before next attempt. " + e.getMessage());
                    SystemClock.sleep((long) i);
                    i *= UploadService.BACKOFF_MULTIPLIER;
                    if (i > UploadService.MAX_RETRY_WAIT_TIME) {
                        i = UploadService.MAX_RETRY_WAIT_TIME;
                    }
                }
            }
        }
        if (this.shouldContinue) {
            return;
        }
        broadcastCancelled();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final UploadTask setLastProgressNotificationTime(long j) {
        this.lastProgressNotificationTime = j;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final UploadTask setNotificationId(int i) {
        this.notificationId = i;
        return this;
    }

    protected abstract void upload() throws Exception;
}
