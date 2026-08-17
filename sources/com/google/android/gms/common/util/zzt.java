package com.google.android.gms.common.util;

import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* loaded from: classes.dex */
public class zzt {
    private static String GE;
    private static final int GF = Process.myPid();

    public static String zzayz() {
        if (GE == null) {
            GE = zzhi(GF);
        }
        return GE;
    }

    static String zzhi(int i) {
        BufferedReader bufferedReader;
        StrictMode.ThreadPolicy allowThreadDiskReads;
        BufferedReader bufferedReader2 = null;
        if (i <= 0) {
            return null;
        }
        try {
            allowThreadDiskReads = StrictMode.allowThreadDiskReads();
            try {
                StringBuilder sb = new StringBuilder(25);
                sb.append("/proc/");
                sb.append(i);
                sb.append("/cmdline");
                bufferedReader = new BufferedReader(new FileReader(sb.toString()));
            } finally {
            }
        } catch (IOException unused) {
            bufferedReader = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
            String trim = bufferedReader.readLine().trim();
            zzo.zzb(bufferedReader);
            return trim;
        } catch (IOException unused2) {
            zzo.zzb(bufferedReader);
            return null;
        } catch (Throwable th2) {
            th = th2;
            bufferedReader2 = bufferedReader;
            zzo.zzb(bufferedReader2);
            throw th;
        }
    }
}
