package com.liulishuo.filedownloader.services;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.liulishuo.filedownloader.download.CustomComponentHolder;
import com.liulishuo.filedownloader.util.FileDownloadHelper;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadProperties;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.lang.ref.WeakReference;

@SuppressLint({"Registered"})
/* loaded from: classes.dex */
public class FileDownloadService extends Service {
    private IFileDownloadServiceHandler handler;

    /* loaded from: classes.dex */
    public static class SeparateProcessService extends FileDownloadService {
    }

    /* loaded from: classes.dex */
    public static class SharedMainProcessService extends FileDownloadService {
    }

    @TargetApi(26)
    private void makeServiceForeground() {
        ForegroundServiceConfig foregroundConfigInstance = CustomComponentHolder.getImpl().getForegroundConfigInstance();
        if (FileDownloadLog.NEED_LOG) {
            FileDownloadLog.d(this, "make service foreground: %s", foregroundConfigInstance);
        }
        if (foregroundConfigInstance.isNeedRecreateChannelId()) {
            NotificationChannel notificationChannel = new NotificationChannel(foregroundConfigInstance.getNotificationChannelId(), foregroundConfigInstance.getNotificationChannelName(), 2);
            NotificationManager notificationManager = (NotificationManager) getSystemService("notification");
            if (notificationManager == null) {
                return;
            } else {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
        startForeground(foregroundConfigInstance.getNotificationId(), foregroundConfigInstance.getNotification(this));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.handler.onBind(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        FileDownloadHelper.holdContext(this);
        try {
            FileDownloadUtils.setMinProgressStep(FileDownloadProperties.getImpl().downloadMinProgressStep);
            FileDownloadUtils.setMinProgressTime(FileDownloadProperties.getImpl().downloadMinProgressTime);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        FileDownloadManager fileDownloadManager = new FileDownloadManager();
        if (FileDownloadProperties.getImpl().processNonSeparate) {
            this.handler = new FDServiceSharedHandler(new WeakReference(this), fileDownloadManager);
        } else {
            this.handler = new FDServiceSeparateHandler(new WeakReference(this), fileDownloadManager);
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.handler.onDestroy();
        stopForeground(true);
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        this.handler.onStartCommand(intent, i, i2);
        if (!FileDownloadUtils.needMakeServiceForeground(this)) {
            return 1;
        }
        makeServiceForeground();
        return 1;
    }
}
