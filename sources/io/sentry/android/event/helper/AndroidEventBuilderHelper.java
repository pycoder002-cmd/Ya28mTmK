package io.sentry.android.event.helper;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.support.v4.os.EnvironmentCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import io.sentry.event.EventBuilder;
import io.sentry.event.helper.EventBuilderHelper;
import io.sentry.event.interfaces.DebugMetaInterface;
import io.sentry.event.interfaces.UserInterface;
import io.sentry.marshaller.json.JsonMarshaller;
import io.sentry.util.Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/* loaded from: classes2.dex */
public class AndroidEventBuilderHelper implements EventBuilderHelper {
    private static final Boolean IS_EMULATOR = isEmulator();
    private static final String KERNEL_VERSION = getKernelVersion();
    public static final String TAG = "io.sentry.android.event.helper.AndroidEventBuilderHelper";
    private static String[] cachedProGuardUuids;
    private Context ctx;

    public AndroidEventBuilderHelper(Context context) {
        this.ctx = context;
    }

    private static String getApplicationName(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            int i = applicationInfo.labelRes;
            if (i != 0) {
                return context.getString(i);
            }
            if (applicationInfo.nonLocalizedLabel != null) {
                return applicationInfo.nonLocalizedLabel.toString();
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error getting application name.", e);
            return null;
        }
    }

    private static Float getBatteryLevel(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver == null) {
                return null;
            }
            int intExtra = registerReceiver.getIntExtra(JsonMarshaller.LEVEL, -1);
            int intExtra2 = registerReceiver.getIntExtra("scale", -1);
            if (intExtra != -1 && intExtra2 != -1) {
                return Float.valueOf((intExtra / intExtra2) * 100.0f);
            }
            return null;
        } catch (Exception e) {
            Log.e(TAG, "Error getting device battery level.", e);
            return null;
        }
    }

    private Map<String, Map<String, Object>> getContexts() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        hashMap.put("os", hashMap3);
        hashMap.put("device", hashMap2);
        hashMap.put("app", hashMap4);
        hashMap2.put("manufacturer", Build.MANUFACTURER);
        hashMap2.put("brand", Build.BRAND);
        hashMap2.put(FileDownloadBroadcastHandler.KEY_MODEL, Build.MODEL);
        hashMap2.put("family", getFamily());
        hashMap2.put("model_id", Build.ID);
        hashMap2.put("battery_level", getBatteryLevel(this.ctx));
        hashMap2.put("orientation", getOrientation(this.ctx));
        hashMap2.put("simulator", IS_EMULATOR);
        hashMap2.put("arch", Build.CPU_ABI);
        hashMap2.put("storage_size", getTotalInternalStorage());
        hashMap2.put("free_storage", getUnusedInternalStorage());
        hashMap2.put("external_storage_size", getTotalExternalStorage());
        hashMap2.put("external_free_storage", getUnusedExternalStorage());
        hashMap2.put("charging", isCharging(this.ctx));
        hashMap2.put("online", Boolean.valueOf(isConnected(this.ctx)));
        DisplayMetrics displayMetrics = getDisplayMetrics(this.ctx);
        if (displayMetrics != null) {
            hashMap2.put("screen_resolution", Integer.toString(Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels)) + "x" + Integer.toString(Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels)));
            hashMap2.put("screen_density", Float.valueOf(displayMetrics.density));
            hashMap2.put("screen_dpi", Integer.valueOf(displayMetrics.densityDpi));
        }
        ActivityManager.MemoryInfo memInfo = getMemInfo(this.ctx);
        if (memInfo != null) {
            hashMap2.put("free_memory", Long.valueOf(memInfo.availMem));
            if (Build.VERSION.SDK_INT >= 16) {
                hashMap2.put("memory_size", Long.valueOf(memInfo.totalMem));
            }
            hashMap2.put("low_memory", Boolean.valueOf(memInfo.lowMemory));
        }
        hashMap3.put("name", "Android");
        hashMap3.put(ClientCookie.VERSION_ATTR, Build.VERSION.RELEASE);
        hashMap3.put("build", Build.DISPLAY);
        hashMap3.put("kernel_version", KERNEL_VERSION);
        hashMap3.put("rooted", isRooted());
        PackageInfo packageInfo = getPackageInfo(this.ctx);
        if (packageInfo != null) {
            hashMap4.put("app_version", packageInfo.versionName);
            hashMap4.put("app_build", Integer.valueOf(packageInfo.versionCode));
            hashMap4.put("app_identifier", packageInfo.packageName);
        }
        hashMap4.put("app_name", getApplicationName(this.ctx));
        hashMap4.put("app_start_time", stringifyDate(new Date()));
        return hashMap;
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        try {
            return context.getResources().getDisplayMetrics();
        } catch (Exception e) {
            Log.e(TAG, "Error getting DisplayMetrics.", e);
            return null;
        }
    }

    private static String getFamily() {
        try {
            return Build.MODEL.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[0];
        } catch (Exception e) {
            Log.e(TAG, "Error getting device family.", e);
            return null;
        }
    }

    private static String getKernelVersion() {
        String property = System.getProperty("os.version");
        BufferedReader bufferedReader = null;
        try {
            try {
                File file = new File("/proc/version");
                if (!file.canRead()) {
                    return property;
                }
                BufferedReader bufferedReader2 = new BufferedReader(new FileReader(file));
                try {
                    String readLine = bufferedReader2.readLine();
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (IOException e) {
                            Log.e(TAG, "Exception while attempting to read kernel information", e);
                        }
                    }
                    return readLine;
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = bufferedReader2;
                    Log.e(TAG, "Exception while attempting to read kernel information", e);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e3) {
                            Log.e(TAG, "Exception while attempting to read kernel information", e3);
                        }
                    }
                    return property;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e4) {
                            Log.e(TAG, "Exception while attempting to read kernel information", e4);
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static ActivityManager.MemoryInfo getMemInfo(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            return memoryInfo;
        } catch (Exception e) {
            Log.e(TAG, "Error getting MemoryInfo.", e);
            return null;
        }
    }

    private static String getOrientation(Context context) {
        try {
            switch (context.getResources().getConfiguration().orientation) {
                case 1:
                    return "portrait";
                case 2:
                    return "landscape";
                default:
                    return null;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting device orientation.", e);
            return null;
        }
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Error getting package info.", e);
            return null;
        }
    }

    private static String[] getProGuardUuids(Context context) {
        if (cachedProGuardUuids != null) {
            return cachedProGuardUuids;
        }
        String[] strArr = new String[0];
        try {
            InputStream open = context.getAssets().open("sentry-debug-meta.properties");
            Properties properties = new Properties();
            properties.load(open);
            open.close();
            String property = properties.getProperty("io.sentry.ProguardUuids");
            if (!Util.isNullOrEmpty(property)) {
                strArr = property.split("\\|");
            }
        } catch (FileNotFoundException unused) {
            Log.d(TAG, "Proguard UUIDs file not found.");
        } catch (Exception e) {
            Log.e(TAG, "Error getting Proguard UUIDs.", e);
        }
        cachedProGuardUuids = strArr;
        return strArr;
    }

    private static Long getTotalExternalStorage() {
        try {
            if (!isExternalStorageMounted()) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.valueOf(statFs.getBlockCount() * statFs.getBlockSize());
        } catch (Exception e) {
            Log.e(TAG, "Error getting total external storage amount.", e);
            return null;
        }
    }

    private static Long getTotalInternalStorage() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return Long.valueOf(statFs.getBlockCount() * statFs.getBlockSize());
        } catch (Exception e) {
            Log.e(TAG, "Error getting total internal storage amount.", e);
            return null;
        }
    }

    private static Long getUnusedExternalStorage() {
        try {
            if (!isExternalStorageMounted()) {
                return null;
            }
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return Long.valueOf(statFs.getAvailableBlocks() * statFs.getBlockSize());
        } catch (Exception e) {
            Log.e(TAG, "Error getting unused external storage amount.", e);
            return null;
        }
    }

    private static Long getUnusedInternalStorage() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return Long.valueOf(statFs.getAvailableBlocks() * statFs.getBlockSize());
        } catch (Exception e) {
            Log.e(TAG, "Error getting unused internal storage amount.", e);
            return null;
        }
    }

    private static Boolean isCharging(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver == null) {
                return null;
            }
            int intExtra = registerReceiver.getIntExtra("plugged", -1);
            boolean z = true;
            if (intExtra != 1 && intExtra != 2) {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Exception e) {
            Log.e(TAG, "Error getting device charging state.", e);
            return null;
        }
    }

    private static boolean isConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static Boolean isEmulator() {
        boolean z;
        try {
            if (!Build.FINGERPRINT.startsWith("generic") && !Build.FINGERPRINT.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) && !Build.MODEL.contains("google_sdk") && !Build.MODEL.contains("Emulator") && !Build.MODEL.contains("Android SDK built for x86") && !Build.MANUFACTURER.contains("Genymotion") && ((!Build.BRAND.startsWith("generic") || !Build.DEVICE.startsWith("generic")) && !"google_sdk".equals(Build.PRODUCT))) {
                z = false;
                return Boolean.valueOf(z);
            }
            z = true;
            return Boolean.valueOf(z);
        } catch (Exception e) {
            Log.e(TAG, "Error checking whether application is running in an emulator.", e);
            return null;
        }
    }

    private static boolean isExternalStorageMounted() {
        return Environment.getExternalStorageState().equals("mounted") && !Environment.isExternalStorageEmulated();
    }

    private static Boolean isRooted() {
        if (Build.TAGS != null && Build.TAGS.contains("test-keys")) {
            return true;
        }
        for (String str : new String[]{"/data/local/bin/su", "/data/local/su", "/data/local/xbin/su", "/sbin/su", "/su/bin", "/su/bin/su", "/system/app/SuperSU", "/system/app/SuperSU.apk", "/system/app/Superuser", "/system/app/Superuser.apk", "/system/bin/failsafe/su", "/system/bin/su", "/system/sd/xbin/su", "/system/xbin/daemonsu", "/system/xbin/su"}) {
            try {
            } catch (Exception e) {
                Log.e(TAG, "Exception while attempting to detect whether the device is rooted", e);
            }
            if (new File(str).exists()) {
                return true;
            }
            continue;
        }
        return false;
    }

    private static String stringifyDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH).format(date);
    }

    @Override // io.sentry.event.helper.EventBuilderHelper
    public void helpBuildingEvent(EventBuilder eventBuilder) {
        eventBuilder.withSdkIntegration("android");
        PackageInfo packageInfo = getPackageInfo(this.ctx);
        if (packageInfo != null) {
            eventBuilder.withRelease(packageInfo.packageName + "-" + packageInfo.versionName);
            eventBuilder.withDist(Integer.toString(packageInfo.versionCode));
        }
        String string = Settings.Secure.getString(this.ctx.getContentResolver(), "android_id");
        if (string != null && !string.trim().equals("")) {
            eventBuilder.withSentryInterface(new UserInterface("android:" + string, null, null, null), false);
        }
        String[] proGuardUuids = getProGuardUuids(this.ctx);
        if (proGuardUuids != null && proGuardUuids.length > 0) {
            DebugMetaInterface debugMetaInterface = new DebugMetaInterface();
            for (String str : proGuardUuids) {
                debugMetaInterface.addDebugImage(new DebugMetaInterface.DebugImage(str));
            }
            eventBuilder.withSentryInterface(debugMetaInterface);
        }
        eventBuilder.withContexts(getContexts());
    }
}
