package cu.uci.android.apklis.presentation;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.device.PackageManagerHelper;
import cu.uci.android.apklis.device.Service.ServiceDownload;

/* loaded from: classes.dex */
public class AppDownloadedBroadcastReceiver extends BroadcastReceiver {
    private long downloadId;
    private DownloadManager downloadManager;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.downloadManager = (DownloadManager) MainApp.context.getSystemService("download");
        if ("android.intent.action.DOWNLOAD_COMPLETE".equals(intent.getAction())) {
            this.downloadId = intent.getLongExtra("extra_download_id", 0L);
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(this.downloadId);
            Cursor query2 = this.downloadManager.query(query);
            if (query2.moveToFirst()) {
                int columnIndex = query2.getColumnIndex("status");
                String string = query2.getString(query2.getColumnIndex("description"));
                if (8 == query2.getInt(columnIndex)) {
                    ServiceDownload.removeDownload(Long.valueOf(this.downloadId));
                    PackageManagerHelper.installAppByUri(string, query2.getString(query2.getColumnIndex("local_uri")));
                    Toast.makeText(MainApp.context, R.string.download_complete, 0).show();
                }
            }
            query2.close();
        }
    }
}
