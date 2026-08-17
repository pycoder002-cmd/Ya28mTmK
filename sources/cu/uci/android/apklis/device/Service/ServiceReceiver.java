package cu.uci.android.apklis.device.Service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import cu.uci.android.apklis.device.NotificationUtils;
import cu.uci.android.apklis.presentation.AppDownloadedBroadcastReceiver;

/* loaded from: classes.dex */
public class ServiceReceiver extends Service {
    AppDownloadedBroadcastReceiver receiverDownload;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.e("MY_DEBUG", "Start ServiceReceiver");
        if (Build.VERSION.SDK_INT == 26) {
            try {
                startForeground(2018, NotificationUtils.toServiceFor8("cu.uci.android.apklis.device.Service.SR", "Servicio de descarga Apklis.", getApplicationContext()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.receiverDownload = new AppDownloadedBroadcastReceiver();
        registerReceiver(this.receiverDownload, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
    }

    @Override // android.app.Service
    public void onDestroy() {
        unregisterReceiver(this.receiverDownload);
        super.onDestroy();
        stopSelf();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }
}
