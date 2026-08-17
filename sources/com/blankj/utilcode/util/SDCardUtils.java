package com.blankj.utilcode.util;

import android.annotation.TargetApi;
import android.os.Environment;
import android.os.StatFs;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.InputStreamReader;

/* loaded from: classes.dex */
public final class SDCardUtils {

    /* loaded from: classes.dex */
    public static class SDCardInfo {
        long availableBlocks;
        long availableBytes;
        long blockByteSize;
        long freeBlocks;
        long freeBytes;
        boolean isExist;
        long totalBlocks;
        long totalBytes;

        public String toString() {
            return "isExist=" + this.isExist + "\ntotalBlocks=" + this.totalBlocks + "\nfreeBlocks=" + this.freeBlocks + "\navailableBlocks=" + this.availableBlocks + "\nblockByteSize=" + this.blockByteSize + "\ntotalBytes=" + this.totalBytes + "\nfreeBytes=" + this.freeBytes + "\navailableBytes=" + this.availableBytes;
        }
    }

    private SDCardUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String getDataPath() {
        if (!isSDCardEnable()) {
            return null;
        }
        return Environment.getExternalStorageDirectory().getPath() + File.separator + "data" + File.separator;
    }

    @TargetApi(18)
    public static String getFreeSpace() {
        if (!isSDCardEnable()) {
            return null;
        }
        StatFs statFs = new StatFs(getSDCardPath());
        return ConvertUtils.byte2FitMemorySize(statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong());
    }

    @TargetApi(18)
    public static String getSDCardInfo() {
        if (!isSDCardEnable()) {
            return null;
        }
        SDCardInfo sDCardInfo = new SDCardInfo();
        sDCardInfo.isExist = true;
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        sDCardInfo.totalBlocks = statFs.getBlockCountLong();
        sDCardInfo.blockByteSize = statFs.getBlockSizeLong();
        sDCardInfo.availableBlocks = statFs.getAvailableBlocksLong();
        sDCardInfo.availableBytes = statFs.getAvailableBytes();
        sDCardInfo.freeBlocks = statFs.getFreeBlocksLong();
        sDCardInfo.freeBytes = statFs.getFreeBytes();
        sDCardInfo.totalBytes = statFs.getTotalBytes();
        return sDCardInfo.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r1v15, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.BufferedReader] */
    public static String getSDCardPath() {
        ?? r2;
        Closeable[] closeableArr;
        String str = null;
        if (!isSDCardEnable()) {
            return null;
        }
        try {
            try {
                Process exec = Runtime.getRuntime().exec("cat /proc/mounts");
                r2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(exec.getInputStream())));
                while (true) {
                    try {
                        String readLine = r2.readLine();
                        if (readLine != null) {
                            if (readLine.contains("sdcard") && readLine.contains(".android_secure")) {
                                String[] split = readLine.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                                if (split.length >= 5) {
                                    String str2 = split[1].replace("/.android_secure", "") + File.separator;
                                    CloseUtils.closeIO(new Closeable[]{r2});
                                    return str2;
                                }
                            }
                            if (exec.waitFor() != 0 && exec.exitValue() == 1) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        e = e;
                        str = r2;
                        e.printStackTrace();
                        closeableArr = new Closeable[]{str};
                        CloseUtils.closeIO(closeableArr);
                        StringBuilder sb = new StringBuilder();
                        sb.append(Environment.getExternalStorageDirectory().getPath());
                        str = File.separator;
                        sb.append(str);
                        return sb.toString();
                    } catch (Throwable th) {
                        th = th;
                        CloseUtils.closeIO(new Closeable[]{r2});
                        throw th;
                    }
                }
                closeableArr = new Closeable[]{r2};
            } catch (Throwable th2) {
                th = th2;
                r2 = str;
            }
        } catch (Exception e2) {
            e = e2;
        }
        CloseUtils.closeIO(closeableArr);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Environment.getExternalStorageDirectory().getPath());
        str = File.separator;
        sb2.append(str);
        return sb2.toString();
    }

    public static boolean isSDCardEnable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
