package net.gotev.uploadservice;

import android.util.Log;
import net.gotev.uploadservice.Logger;

/* loaded from: classes2.dex */
public class DefaultLoggerDelegate implements Logger.LoggerDelegate {
    private static final String TAG = "UploadService";

    @Override // net.gotev.uploadservice.Logger.LoggerDelegate
    public void debug(String str, String str2) {
        Log.d(TAG, str + " - " + str2);
    }

    @Override // net.gotev.uploadservice.Logger.LoggerDelegate
    public void error(String str, String str2) {
        Log.e(TAG, str + " - " + str2);
    }

    @Override // net.gotev.uploadservice.Logger.LoggerDelegate
    public void error(String str, String str2, Throwable th) {
        Log.e(TAG, str + " - " + str2, th);
    }

    @Override // net.gotev.uploadservice.Logger.LoggerDelegate
    public void info(String str, String str2) {
        Log.i(TAG, str + " - " + str2);
    }
}
