package com.blankj.utilcode.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public final class CrashUtils implements Thread.UncaughtExceptionHandler {
    private static volatile CrashUtils mInstance;
    private String crashDir;
    private Thread.UncaughtExceptionHandler mHandler;
    private boolean mInitialized;
    private int versionCode;
    private String versionName;

    private CrashUtils() {
    }

    private static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean createOrExistsFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getCrashHead() {
        return "\n************* Crash Log Head ****************\nDevice Manufacturer: " + Build.MANUFACTURER + "\nDevice Model       : " + Build.MODEL + "\nAndroid Version    : " + Build.VERSION.RELEASE + "\nAndroid SDK        : " + Build.VERSION.SDK_INT + "\nApp VersionName    : " + this.versionName + "\nApp VersionCode    : " + this.versionCode + "\n************* Crash Log Head ****************\n\n";
    }

    public static CrashUtils getInstance() {
        if (mInstance == null) {
            synchronized (CrashUtils.class) {
                if (mInstance == null) {
                    mInstance = new CrashUtils();
                }
            }
        }
        return mInstance;
    }

    public boolean init() {
        if (this.mInitialized) {
            return true;
        }
        if ("mounted".equals(Environment.getExternalStorageState())) {
            File externalCacheDir = Utils.getContext().getExternalCacheDir();
            if (externalCacheDir == null) {
                return false;
            }
            this.crashDir = externalCacheDir.getPath() + File.separator + "crash" + File.separator;
        } else {
            File cacheDir = Utils.getContext().getCacheDir();
            if (cacheDir == null) {
                return false;
            }
            this.crashDir = cacheDir.getPath() + File.separator + "crash" + File.separator;
        }
        try {
            PackageInfo packageInfo = Utils.getContext().getPackageManager().getPackageInfo(Utils.getContext().getPackageName(), 0);
            this.versionName = packageInfo.versionName;
            this.versionCode = packageInfo.versionCode;
            this.mHandler = Thread.getDefaultUncaughtExceptionHandler();
            Thread.setDefaultUncaughtExceptionHandler(this);
            this.mInitialized = true;
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, final Throwable th) {
        final String str = this.crashDir + new SimpleDateFormat("yyMMdd HH-mm-ss", Locale.getDefault()).format(new Date()) + ".txt";
        if (createOrExistsFile(str)) {
            new Thread(new Runnable() { // from class: com.blankj.utilcode.util.CrashUtils.1
                @Override // java.lang.Runnable
                public void run() {
                    Throwable th2;
                    PrintWriter printWriter;
                    IOException e;
                    Closeable[] closeableArr;
                    try {
                        try {
                            printWriter = new PrintWriter(new FileWriter(str, false));
                            try {
                                printWriter.write(CrashUtils.this.getCrashHead());
                                th.printStackTrace(printWriter);
                                for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                                    cause.printStackTrace(printWriter);
                                }
                                closeableArr = new Closeable[]{printWriter};
                            } catch (IOException e2) {
                                e = e2;
                                e.printStackTrace();
                                closeableArr = new Closeable[]{printWriter};
                                CloseUtils.closeIO(closeableArr);
                            }
                        } catch (Throwable th3) {
                            th2 = th3;
                            CloseUtils.closeIO(null);
                            throw th2;
                        }
                    } catch (IOException e3) {
                        printWriter = null;
                        e = e3;
                    } catch (Throwable th4) {
                        th2 = th4;
                        CloseUtils.closeIO(null);
                        throw th2;
                    }
                    CloseUtils.closeIO(closeableArr);
                }
            }).start();
            if (this.mHandler != null) {
                this.mHandler.uncaughtException(thread, th);
            }
        }
    }
}
