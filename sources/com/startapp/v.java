package com.startapp;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.net.TrafficStats;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.startapp.networkTest.enums.IdleStates;
import com.startapp.networkTest.enums.MemoryStates;
import com.startapp.networkTest.enums.Os;
import com.startapp.networkTest.enums.PhoneTypes;
import com.startapp.networkTest.enums.ScreenStates;
import com.startapp.networkTest.enums.SimStates;
import com.startapp.networkTest.enums.ThreeState;
import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class v {
    private static final String a = "v";
    private static final boolean b = false;

    private static long a(Context context, Uri uri) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_size"}, null, null, null);
            long j = 0;
            if (cursor != null) {
                if (cursor.getCount() == 0) {
                    cursor.close();
                    return 0L;
                }
                while (cursor.moveToNext()) {
                    j += cursor.getLong(cursor.getColumnIndexOrThrow("_size"));
                }
            }
            return j;
        } catch (Throwable th) {
            try {
                h1.a(th);
                if (cursor != null) {
                    cursor.close();
                }
                return -1L;
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }

    public static h0 a(x xVar) {
        h0 h0Var = new h0();
        h0Var.MobileRxBytes = c2.e();
        h0Var.MobileTxBytes = c2.f();
        h0Var.TotalRxBytes = TrafficStats.getTotalRxBytes();
        h0Var.TotalTxBytes = TrafficStats.getTotalTxBytes();
        if (xVar != null) {
            h0Var.WifiRxBytes = xVar.d();
            h0Var.WifiTxBytes = xVar.e();
        } else {
            h0Var.WifiRxBytes = -1L;
            h0Var.WifiTxBytes = -1L;
        }
        return h0Var;
    }

    private static k0 a(Context context) {
        k0 k0Var = new k0();
        k0Var.MissingPermission = true;
        return k0Var;
    }

    public static n0 a(int i, Context context) {
        return f(context).getSimInfoSubId(i);
    }

    private static String a(String str) {
        int ordinal;
        if (str.length() == 0 || (ordinal = s.b().SIMINFO_ICCID_RECORDTYPE().ordinal()) == 0) {
            return str;
        }
        if (ordinal != 1) {
            return "";
        }
        if (str.length() < 11) {
            return str.replaceAll("[\\d\\w]", "*");
        }
        return str.substring(0, 7) + str.substring(7, str.length()).replaceAll("[\\d\\w]", "*");
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0055 A[Catch: all -> 0x0063, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0063, blocks: (B:10:0x0055, B:26:0x005f), top: B:5:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(android.content.Context r9, int r10, com.startapp.n0 r11) {
        /*
            java.lang.String r0 = "type"
            java.lang.String r1 = "apn"
            r2 = -1
            if (r10 == r2) goto L19
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "content://telephony/carriers/preferapn/subId/"
            r2.append(r3)
            r2.append(r10)
            java.lang.String r10 = r2.toString()
            goto L1b
        L19:
            java.lang.String r10 = "content://telephony/carriers/preferapn"
        L1b:
            r2 = 0
            android.net.Uri r4 = android.net.Uri.parse(r10)     // Catch: java.lang.Throwable -> L59
            android.content.ContentResolver r3 = r9.getContentResolver()     // Catch: java.lang.Throwable -> L59
            java.lang.String[] r5 = new java.lang.String[]{r1, r0}     // Catch: java.lang.Throwable -> L59
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r9 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L59
            if (r9 == 0) goto L52
            boolean r10 = r9.moveToFirst()     // Catch: java.lang.Throwable -> L4f
            if (r10 == 0) goto L52
            int r10 = r9.getColumnIndex(r1)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Throwable -> L4f
            int r0 = r9.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L4f
            java.lang.String r0 = r9.getString(r0)     // Catch: java.lang.Throwable -> L4f
            r11.Apn = r10     // Catch: java.lang.Throwable -> L4f
            r11.ApnTypes = r0     // Catch: java.lang.Throwable -> L4f
            r9.close()     // Catch: java.lang.Throwable -> L4f
            goto L53
        L4f:
            r10 = move-exception
            r2 = r9
            goto L5a
        L52:
            r2 = r9
        L53:
            if (r2 == 0) goto L67
            r2.close()     // Catch: java.lang.Throwable -> L63
            goto L67
        L59:
            r10 = move-exception
        L5a:
            com.startapp.h1.a(r10)     // Catch: java.lang.Throwable -> L68
            if (r2 == 0) goto L67
            r2.close()     // Catch: java.lang.Throwable -> L63
            goto L67
        L63:
            r9 = move-exception
            com.startapp.h1.b(r9)
        L67:
            return
        L68:
            r9 = move-exception
            if (r2 == 0) goto L73
            r2.close()     // Catch: java.lang.Throwable -> L6f
            goto L73
        L6f:
            r10 = move-exception
            com.startapp.h1.b(r10)
        L73:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.v.a(android.content.Context, int, com.startapp.n0):void");
    }

    private static boolean a() {
        String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (int i = 0; i < 10; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    public static b0 b(Context context) {
        b0 b0Var = new b0();
        b0Var.DeviceManufacturer = Build.MANUFACTURER;
        b0Var.DeviceName = Build.MODEL;
        b0Var.OS = Os.Android;
        b0Var.OSVersion = Build.VERSION.RELEASE;
        b0Var.BuildFingerprint = Build.FINGERPRINT;
        b0Var.DeviceUpTime = SystemClock.elapsedRealtime();
        b0Var.UserLocal = Locale.getDefault().toString();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            b0Var.SimOperator = b2.a(telephonyManager.getSimOperator());
            b0Var.SimOperatorName = b2.a(telephonyManager.getSimOperatorName());
            int i = Build.VERSION.SDK_INT;
            if (i >= 29) {
                String typeAllocationCode = telephonyManager.getTypeAllocationCode();
                if (typeAllocationCode == null || typeAllocationCode.isEmpty()) {
                    String manufacturerCode = telephonyManager.getManufacturerCode();
                    if (manufacturerCode != null && !manufacturerCode.isEmpty()) {
                        b0Var.TAC = manufacturerCode;
                    }
                } else {
                    b0Var.TAC = typeAllocationCode;
                }
            }
            SimStates simStates = SimStates.Unknown;
            int simState = telephonyManager.getSimState();
            if (simState == 1) {
                simStates = SimStates.Absent;
            } else if (simState == 2) {
                simStates = SimStates.PinRequired;
            } else if (simState == 3) {
                simStates = SimStates.PukRequired;
            } else if (simState == 4) {
                simStates = SimStates.NetworkLocked;
            } else if (simState == 5) {
                simStates = SimStates.Ready;
            }
            b0Var.SimState = simStates;
            if (i >= 23) {
                try {
                    b0Var.PhoneCount = ((Integer) telephonyManager.getClass().getDeclaredMethod("getPhoneCount", new Class[0]).invoke(telephonyManager, new Object[0])).intValue();
                } catch (Throwable th) {
                    h1.b(th);
                }
            }
            PhoneTypes phoneTypes = PhoneTypes.Unknown;
            int phoneType = telephonyManager.getPhoneType();
            if (phoneType == 0) {
                phoneTypes = PhoneTypes.None;
            } else if (phoneType == 1) {
                phoneTypes = PhoneTypes.GSM;
            } else if (phoneType == 2) {
                phoneTypes = PhoneTypes.CDMA;
            } else if (phoneType == 3) {
                phoneTypes = PhoneTypes.SIP;
            }
            b0Var.PhoneType = phoneTypes;
        }
        b0Var.IsRooted = a();
        String[] a2 = Build.VERSION.SDK_INT <= 24 ? z1.a("/proc/version") : z1.b("uname -a");
        if (a2.length > 0) {
            b0Var.OsSystemVersion = b2.a(a2[0]);
        }
        b0Var.BluetoothInfo = a(context);
        b0Var.PowerSaveMode = g(context);
        b0Var.MultiSimInfo = f(context);
        b0Var.HostAppInfo = c(context);
        return b0Var;
    }

    private static String b(String str) {
        int ordinal;
        if (str.length() == 0 || (ordinal = s.b().SIMINFO_IMSI_RECORDTYPE().ordinal()) == 0) {
            return str;
        }
        if (ordinal != 1) {
            return "";
        }
        if (str.length() < 14) {
            return str.replaceAll("[\\d\\w]", "*");
        }
        return str.substring(0, 10) + str.substring(10, str.length()).replaceAll("[\\d\\w]", "*");
    }

    private static boolean b() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            h1.a(th);
            return false;
        }
    }

    private static l0 c(Context context) {
        l0 l0Var = new l0();
        l0Var.AppPackageName = context.getPackageName();
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (applicationInfo == null) {
            try {
                applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 0);
            } catch (Throwable th) {
                h1.b(th);
            }
        }
        if (applicationInfo != null) {
            l0Var.AppTargetVersion = applicationInfo.targetSdkVersion;
            l0Var.AppName = (String) applicationInfo.loadLabel(context.getPackageManager());
            if (Build.VERSION.SDK_INT >= 26) {
                l0Var.AppCategory = p1.a(applicationInfo.category);
            }
        }
        ArrayList<i0> arrayList = new ArrayList<>();
        try {
            for (String str : context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions) {
                i0 i0Var = new i0();
                i0Var.Permission = str.toLowerCase();
                if (str.equalsIgnoreCase("android.permission.PACKAGE_USAGE_STATS")) {
                    i0Var.IsGranted = p1.b(context) ? 1 : 0;
                } else {
                    i0Var.IsGranted = context.checkPermission(str, Process.myPid(), Process.myUid()) == 0 ? 1 : 0;
                }
                arrayList.add(i0Var);
            }
        } finally {
            try {
                return l0Var;
            } finally {
            }
        }
        return l0Var;
    }

    public static IdleStates d(Context context) {
        PowerManager powerManager;
        IdleStates idleStates = IdleStates.Unknown;
        int i = Build.VERSION.SDK_INT;
        if (i >= 23 && (powerManager = (PowerManager) context.getSystemService("power")) != null) {
            if (i >= 24) {
                try {
                    if (((Boolean) powerManager.getClass().getDeclaredMethod("isLightDeviceIdleMode", new Class[0]).invoke(powerManager, new Object[0])).booleanValue()) {
                        idleStates = IdleStates.LightIdle;
                    }
                } catch (Throwable th) {
                    h1.b(th);
                }
            }
            if (idleStates != IdleStates.LightIdle) {
                return powerManager.isDeviceIdleMode() ? IdleStates.DeepIdle : IdleStates.NonIdle;
            }
        }
        return idleStates;
    }

    public static d0 e(Context context) {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService("activity")).getMemoryInfo(memoryInfo);
        d0 d0Var = new d0();
        long j = memoryInfo.availMem;
        d0Var.MemoryFree = j;
        if (Build.VERSION.SDK_INT >= 16) {
            long j2 = memoryInfo.totalMem;
            d0Var.MemoryTotal = j2;
            d0Var.MemoryUsed = j2 - j;
        }
        if (memoryInfo.lowMemory) {
            d0Var.MemoryState = MemoryStates.Low;
        } else {
            d0Var.MemoryState = MemoryStates.Normal;
        }
        return d0Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:215:0x022f, code lost:
    
        if (r2 == 1) goto L152;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x0231, code lost:
    
        if (r2 == 2) goto L151;
     */
    /* JADX WARN: Code restructure failed: missing block: B:217:0x0233, code lost:
    
        r0.MultiSimVariant = com.startapp.networkTest.enums.MultiSimVariants.Unknown;
     */
    /* JADX WARN: Code restructure failed: missing block: B:218:0x0238, code lost:
    
        r0.MultiSimVariant = com.startapp.networkTest.enums.MultiSimVariants.TSTS;
     */
    /* JADX WARN: Code restructure failed: missing block: B:219:0x023d, code lost:
    
        r0.MultiSimVariant = com.startapp.networkTest.enums.MultiSimVariants.DSDS;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.startapp.m0 f(android.content.Context r13) {
        /*
            Method dump skipped, instructions count: 820
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.v.f(android.content.Context):com.startapp.m0");
    }

    private static ThreeState g(Context context) {
        String string;
        try {
            string = Settings.System.getString(context.getContentResolver(), "user_powersaver_enable");
        } catch (Throwable th) {
            h1.a(th);
        }
        if (string != null) {
            return string.equals("1") ? ThreeState.Enabled : ThreeState.Disabled;
        }
        int i = Build.VERSION.SDK_INT;
        if (i >= 21) {
            if (Build.MANUFACTURER.toLowerCase().startsWith("sony") && i < 23) {
                return ThreeState.Unknown;
            }
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            if (powerManager != null) {
                return powerManager.isPowerSaveMode() ? ThreeState.Enabled : ThreeState.Disabled;
            }
        }
        return ThreeState.Unknown;
    }

    public static ScreenStates h(Context context) {
        ScreenStates screenStates = ScreenStates.Unknown;
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager != null ? powerManager.isScreenOn() ? ScreenStates.On : ScreenStates.Off : screenStates;
    }

    public static n0 i(Context context) {
        return f(context).getDefaultDataSimInfo();
    }

    public static n0 j(Context context) {
        return f(context).getDefaultVoiceSimInfo();
    }

    public static g0 k(Context context) {
        g0 g0Var = new g0();
        long blockSize = new StatFs(Environment.getDataDirectory().getPath()).getBlockSize();
        g0Var.StorageInternalSize = r2.getBlockCount() * blockSize;
        g0Var.StorageInternalAvailable = blockSize * r2.getAvailableBlocks();
        g0Var.StorageInternalAudio = a(context, MediaStore.Audio.Media.INTERNAL_CONTENT_URI);
        g0Var.StorageInternalImages = a(context, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        g0Var.StorageInternalVideo = a(context, MediaStore.Video.Media.INTERNAL_CONTENT_URI);
        if (b()) {
            try {
                long blockSize2 = new StatFs(Environment.getExternalStorageDirectory().getPath()).getBlockSize();
                g0Var.StorageExternalSize = r2.getBlockCount() * blockSize2;
                g0Var.StorageExternalAvailable = blockSize2 * r2.getAvailableBlocks();
            } catch (IllegalArgumentException unused) {
                g0Var.StorageExternalSize = -1L;
                g0Var.StorageExternalAvailable = -1L;
            }
            if (context.checkCallingOrSelfPermission("android.permission.READ_EXTERNAL_STORAGE") == 0) {
                g0Var.StorageExternalAudio = a(context, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                g0Var.StorageExternalImages = a(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                g0Var.StorageExternalVideo = a(context, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            }
        }
        return g0Var;
    }
}
