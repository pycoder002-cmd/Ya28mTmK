package cu.uci.android.apklis.device.Service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.device.NotificationUtils;
import cu.uci.android.apklis.device.PackageManagerHelper;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes.dex */
public class ServiceDownload extends Service {
    private static Context context;
    private static HashMap<Long, String> downloadIdHash;
    private static ArrayList<DownloadListener> listeners;
    private static HashMap<String, Long> packageNameHash;
    private static String shaAppFromServer;

    /* loaded from: classes.dex */
    private class MyThread extends Thread {
        private Context context;

        public MyThread(Context context) {
            this.context = context;
        }

        /* JADX WARN: Failed to find 'out' block for switch in B:20:0x0089. Please report as an issue. */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            boolean z;
            Cursor query;
            super.run();
            while (ServiceDownload.listeners.size() > 0) {
                DownloadManager downloadManager = (DownloadManager) MainApp.context.getSystemService("download");
                char c = 0;
                int i = 0;
                while (i < ServiceDownload.listeners.size()) {
                    DownloadListener downloadListener = (DownloadListener) ServiceDownload.listeners.get(i);
                    String id = downloadListener.getId();
                    if (downloadListener != null) {
                        String package_name = downloadListener.getPackage_name();
                        if (ServiceDownload.packageNameHash.containsKey(package_name)) {
                            Long l = (Long) ServiceDownload.packageNameHash.get(package_name);
                            try {
                                DownloadManager.Query query2 = new DownloadManager.Query();
                                z = true;
                                long[] jArr = new long[1];
                                jArr[c] = l.longValue();
                                query2.setFilterById(jArr);
                                query = downloadManager.query(query2);
                            } catch (Exception e) {
                                MainApp.log(e.getMessage());
                            }
                            if (query.moveToFirst()) {
                                int columnIndex = query.getColumnIndex("status");
                                int columnIndex2 = query.getColumnIndex("total_size");
                                int columnIndex3 = query.getColumnIndex("bytes_so_far");
                                int i2 = query.getInt(columnIndex);
                                if (i2 != 4) {
                                    if (i2 == 8) {
                                        ServiceDownload.removeListener(id);
                                    } else if (i2 != 16) {
                                        switch (i2) {
                                            case 1:
                                                downloadListener.onDownloadPending();
                                                break;
                                        }
                                    } else {
                                        ServiceDownload.removeListener(id);
                                    }
                                    z = false;
                                }
                                if (z) {
                                    downloadListener.onDownloadProgressChange((int) ((query.getInt(columnIndex3) * 100) / query.getInt(columnIndex2)));
                                }
                                if (8 == query.getInt(columnIndex)) {
                                    String string = query.getString(query.getColumnIndex("local_uri"));
                                    if (string.endsWith(".apk")) {
                                        PackageManagerHelper.installAppByUri(package_name, string);
                                    } else {
                                        PackageManagerHelper.installAppByUri(package_name, string);
                                    }
                                }
                                query.close();
                                i++;
                                try {
                                    Thread.sleep(100L);
                                } catch (Exception e2) {
                                    MainApp.log("ServiceDownload", e2);
                                }
                            } else {
                                ServiceDownload.removeListener(id);
                            }
                        } else {
                            ServiceDownload.removeListener(id);
                        }
                    } else {
                        ServiceDownload.removeListener(id);
                    }
                    c = 0;
                }
            }
            ServiceDownload.this.stopSelf();
        }
    }

    public static void addDownload(String str, Long l, String str2) {
        shaAppFromServer = str2;
        inithashes();
        packageNameHash.put(str, l);
        downloadIdHash.put(l, str);
    }

    public static void addListener(DownloadListener downloadListener) {
        removeListener(downloadListener.getId());
        if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.add(downloadListener);
    }

    public static Long areDownloading(String str) {
        inithashes();
        return packageNameHash.get(str);
    }

    public static void cancelDownload(Long l) {
        ((DownloadManager) MainApp.context.getSystemService("download")).remove(l.longValue());
        removeDownload(l);
    }

    public static void cancelDownload(String str) {
        Long downloadIdByPackageName = getDownloadIdByPackageName(str);
        if (downloadIdByPackageName != null) {
            ((DownloadManager) MainApp.context.getSystemService("download")).remove(downloadIdByPackageName.longValue());
        }
        removeDownload(str);
    }

    public static String existPackageNameByDownloadId(Long l) {
        inithashes();
        return downloadIdHash.get(l);
    }

    public static Long getDownloadIdByPackageName(String str) {
        inithashes();
        setIdFromDownload(str);
        return packageNameHash.get(str);
    }

    private static void inithashes() {
        if (packageNameHash == null) {
            packageNameHash = new HashMap<>();
        }
        if (downloadIdHash == null) {
            downloadIdHash = new HashMap<>();
        }
    }

    public static void removeDownload(Long l) {
        inithashes();
        synchronized (downloadIdHash) {
            if (downloadIdHash.containsKey(l)) {
                String str = downloadIdHash.get(l);
                downloadIdHash.remove(l);
                packageNameHash.remove(str);
            }
        }
    }

    public static void removeDownload(String str) {
        inithashes();
        synchronized (packageNameHash) {
            if (packageNameHash.containsKey(str)) {
                Long l = packageNameHash.get(str);
                packageNameHash.remove(str);
                downloadIdHash.remove(l);
            }
        }
    }

    private static void removeListener(int i) {
        if (listeners != null) {
            synchronized (listeners) {
                if (listeners.get(i) != null) {
                    listeners.get(i).onDownloadClosed();
                }
                listeners.remove(i);
            }
        }
    }

    public static void removeListener(String str) {
        if (listeners != null) {
            synchronized (listeners) {
                int i = 0;
                while (true) {
                    if (i < listeners.size()) {
                        if (listeners.get(i) != null && listeners.get(i).getId().equals(str)) {
                            listeners.get(i).onDownloadClosed();
                            listeners.remove(i);
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static void setContext(Context context2) {
        context = context2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0040, code lost:
    
        if (r6.equals(r0) == false) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0044, code lost:
    
        if (r1 == 8) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0048, code lost:
    
        if (r1 == 16) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x004b, code lost:
    
        if (r1 == 1) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x004d, code lost:
    
        cu.uci.android.apklis.device.Service.ServiceDownload.packageNameHash.put(r6, java.lang.Long.valueOf(r4));
        cu.uci.android.apklis.device.Service.ServiceDownload.downloadIdHash.put(java.lang.Long.valueOf(r4), r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0063, code lost:
    
        if (r2.moveToNext() != false) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001c, code lost:
    
        if (r2.moveToFirst() != false) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x001e, code lost:
    
        r0 = r2.getColumnIndex(com.liulishuo.filedownloader.model.FileDownloadModel.ID);
        r1 = r2.getColumnIndex("description");
        r3 = r2.getColumnIndex("status");
        r4 = r2.getLong(r0);
        r0 = r2.getString(r1);
        r1 = r2.getInt(r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void setIdFromDownload(java.lang.String r6) {
        /*
            android.content.Context r0 = cu.uci.android.apklis.MainApp.context
            java.lang.String r1 = "download"
            java.lang.Object r0 = r0.getSystemService(r1)
            android.app.DownloadManager r0 = (android.app.DownloadManager) r0
            android.app.DownloadManager$Query r1 = new android.app.DownloadManager$Query     // Catch: java.lang.Exception -> L69
            r1.<init>()     // Catch: java.lang.Exception -> L69
            r2 = 0
            if (r0 == 0) goto L16
            android.database.Cursor r2 = r0.query(r1)     // Catch: java.lang.Exception -> L69
        L16:
            if (r2 == 0) goto L65
            boolean r0 = r2.moveToFirst()     // Catch: java.lang.Exception -> L69
            if (r0 == 0) goto L65
        L1e:
            java.lang.String r0 = "_id"
            int r0 = r2.getColumnIndex(r0)     // Catch: java.lang.Exception -> L69
            java.lang.String r1 = "description"
            int r1 = r2.getColumnIndex(r1)     // Catch: java.lang.Exception -> L69
            java.lang.String r3 = "status"
            int r3 = r2.getColumnIndex(r3)     // Catch: java.lang.Exception -> L69
            long r4 = r2.getLong(r0)     // Catch: java.lang.Exception -> L69
            java.lang.String r0 = r2.getString(r1)     // Catch: java.lang.Exception -> L69
            int r1 = r2.getInt(r3)     // Catch: java.lang.Exception -> L69
            boolean r0 = r6.equals(r0)     // Catch: java.lang.Exception -> L69
            if (r0 == 0) goto L5f
            r0 = 8
            if (r1 == r0) goto L5f
            r0 = 16
            if (r1 == r0) goto L5f
            r0 = 1
            if (r1 == r0) goto L5f
            java.util.HashMap<java.lang.String, java.lang.Long> r0 = cu.uci.android.apklis.device.Service.ServiceDownload.packageNameHash     // Catch: java.lang.Exception -> L69
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Exception -> L69
            r0.put(r6, r1)     // Catch: java.lang.Exception -> L69
            java.util.HashMap<java.lang.Long, java.lang.String> r0 = cu.uci.android.apklis.device.Service.ServiceDownload.downloadIdHash     // Catch: java.lang.Exception -> L69
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Exception -> L69
            r0.put(r1, r6)     // Catch: java.lang.Exception -> L69
        L5f:
            boolean r0 = r2.moveToNext()     // Catch: java.lang.Exception -> L69
            if (r0 != 0) goto L1e
        L65:
            r2.close()     // Catch: java.lang.Exception -> L69
            goto L6d
        L69:
            r6 = move-exception
            r6.printStackTrace()
        L6d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.device.Service.ServiceDownload.setIdFromDownload(java.lang.String):void");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.e("MY_DEBUG", "Start ServiceDownload");
        if (Build.VERSION.SDK_INT >= 26) {
            try {
                startForeground(1991, NotificationUtils.toServiceFor8("cu.uci.android.apklis.device.Service.SD", "Servicio de descarga Apklis", getApplicationContext()));
            } catch (Exception e) {
                MainApp.log(getClass().getName(), e);
                e.printStackTrace();
            }
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        new MyThread(getApplicationContext()).start();
        return 2;
    }
}
