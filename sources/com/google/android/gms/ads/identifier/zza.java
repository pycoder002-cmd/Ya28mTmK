package com.google.android.gms.ads.identifier;

import android.support.annotation.WorkerThread;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
public class zza {
    @WorkerThread
    public void zzv(String str) {
        String str2;
        String valueOf;
        StringBuilder sb;
        String str3;
        Exception exc;
        try {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    int responseCode = httpURLConnection.getResponseCode();
                    if (responseCode < 200 || responseCode >= 300) {
                        StringBuilder sb2 = new StringBuilder(65 + String.valueOf(str).length());
                        sb2.append("Received non-success response code ");
                        sb2.append(responseCode);
                        sb2.append(" from pinging URL: ");
                        sb2.append(str);
                        Log.w("HttpUrlPinger", sb2.toString());
                    }
                } finally {
                    httpURLConnection.disconnect();
                }
            } catch (IndexOutOfBoundsException e) {
                str2 = "HttpUrlPinger";
                valueOf = String.valueOf(e.getMessage());
                sb = new StringBuilder(32 + String.valueOf(str).length() + String.valueOf(valueOf).length());
                str3 = "Error while parsing ping URL: ";
                exc = e;
                sb.append(str3);
                sb.append(str);
                sb.append(". ");
                sb.append(valueOf);
                Log.w(str2, sb.toString(), exc);
            }
        } catch (IOException | RuntimeException e2) {
            str2 = "HttpUrlPinger";
            valueOf = String.valueOf(e2.getMessage());
            sb = new StringBuilder(27 + String.valueOf(str).length() + String.valueOf(valueOf).length());
            str3 = "Error while pinging URL: ";
            exc = e2;
            sb.append(str3);
            sb.append(str);
            sb.append(". ");
            sb.append(valueOf);
            Log.w(str2, sb.toString(), exc);
        }
    }
}
