package cu.uci.android.apklis.device;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import cu.uci.android.apklis.MainApp;
import java.io.File;
import java.text.DecimalFormat;

/* loaded from: classes.dex */
public class DeviceInfo {
    private static boolean canExecuteCommand(String str) {
        try {
            Runtime.getRuntime().exec(str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean externalMemoryAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String formatSize(long j) {
        String str;
        if (j >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            str = " KB";
            j /= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            if (j >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                str = " MB";
                j /= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
            }
        } else {
            str = null;
        }
        StringBuilder sb = new StringBuilder(Long.toString(j));
        for (int length = sb.length() - 3; length > 0; length -= 3) {
            sb.insert(length, ',');
        }
        if (str != null) {
            sb.append(str);
        }
        return sb.toString();
    }

    public static long freeRamMemorySize(MainApp mainApp) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) mainApp.getSystemService("activity")).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
    }

    public static long getAvailableExternalMemorySize() {
        if (!externalMemoryAvailable()) {
            return 0L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    public static long getAvailableInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }

    public static long getTotalExternalMemorySize() {
        if (!externalMemoryAvailable()) {
            return 0L;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return statFs.getBlockCount() * statFs.getBlockSize();
    }

    public static long getTotalInternalMemorySize() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getBlockCount() * statFs.getBlockSize();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isAccessibilitySettingsOn(android.content.Context r6) {
        /*
            java.lang.String r0 = "AS"
            r1 = 0
            android.content.Context r2 = r6.getApplicationContext()     // Catch: android.provider.Settings.SettingNotFoundException -> L28
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch: android.provider.Settings.SettingNotFoundException -> L28
            java.lang.String r3 = "accessibility_enabled"
            int r2 = android.provider.Settings.Secure.getInt(r2, r3)     // Catch: android.provider.Settings.SettingNotFoundException -> L28
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: android.provider.Settings.SettingNotFoundException -> L26
            r3.<init>()     // Catch: android.provider.Settings.SettingNotFoundException -> L26
            java.lang.String r4 = "accessibilityEnabled = "
            r3.append(r4)     // Catch: android.provider.Settings.SettingNotFoundException -> L26
            r3.append(r2)     // Catch: android.provider.Settings.SettingNotFoundException -> L26
            java.lang.String r3 = r3.toString()     // Catch: android.provider.Settings.SettingNotFoundException -> L26
            android.util.Log.v(r0, r3)     // Catch: android.provider.Settings.SettingNotFoundException -> L26
            goto L42
        L26:
            r3 = move-exception
            goto L2a
        L28:
            r3 = move-exception
            r2 = r1
        L2a:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Error finding setting, default accessibility to not found: "
            r4.append(r5)
            java.lang.String r3 = r3.getMessage()
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            cu.uci.android.apklis.MainApp.log(r3)
        L42:
            android.text.TextUtils$SimpleStringSplitter r3 = new android.text.TextUtils$SimpleStringSplitter
            r4 = 58
            r3.<init>(r4)
            r4 = 1
            if (r2 != r4) goto L90
            java.lang.String r2 = "***ACCESSIBILIY IS ENABLED*** -----------------"
            android.util.Log.v(r0, r2)
            android.content.Context r6 = r6.getApplicationContext()
            android.content.ContentResolver r6 = r6.getContentResolver()
            java.lang.String r2 = "enabled_accessibility_services"
            java.lang.String r6 = android.provider.Settings.Secure.getString(r6, r2)
            if (r6 == 0) goto L95
            r3.setString(r6)
        L64:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L95
            java.lang.String r6 = r3.next()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "-------------- > accessabilityService :: "
            r2.append(r5)
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            android.util.Log.v(r0, r2)
            java.lang.String r2 = "cu.uci.android.apklis/cu.uci.android.apklis.device.Service.SilentInstallAccessibilityService"
            boolean r6 = r6.equalsIgnoreCase(r2)
            if (r6 == 0) goto L64
            java.lang.String r6 = "We've found the correct setting - accessibility is switched on!"
            android.util.Log.v(r0, r6)
            return r4
        L90:
            java.lang.String r6 = "***ACCESSIBILIY IS DISABLED***"
            android.util.Log.v(r0, r6)
        L95:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.device.DeviceInfo.isAccessibilitySettingsOn(android.content.Context):boolean");
    }

    public static boolean isRooted() {
        String str = Build.TAGS;
        if (str != null && str.contains("test-keys")) {
            return true;
        }
        try {
            if (new File("/system/app/Superuser.apk").exists()) {
                return true;
            }
        } catch (Exception unused) {
        }
        return canExecuteCommand("/system/xbin/which su") || canExecuteCommand("/system/bin/which su") || canExecuteCommand("which su");
    }

    private String returnToDecimalPlaces(long j) {
        return new DecimalFormat("#.00").format(j);
    }
}
