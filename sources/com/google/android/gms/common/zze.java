package com.google.android.gms.common;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.UserManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzy;
import com.google.android.gms.common.util.zzi;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.util.zzx;
import com.google.android.gms.common.zzd;
import com.google.android.gms.internal.zzsz;
import java.io.InputStream;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class zze {

    @Deprecated
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    public static boolean xb;
    public static boolean xc;
    static boolean xd;
    private static boolean xe;

    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = zzaqq();
    static final AtomicBoolean xf = new AtomicBoolean();
    private static final AtomicBoolean xg = new AtomicBoolean();

    @Deprecated
    public static PendingIntent getErrorPendingIntent(int i, Context context, int i2) {
        return zzc.zzaql().getErrorResolutionPendingIntent(context, i, i2);
    }

    @Deprecated
    public static String getErrorString(int i) {
        return ConnectionResult.getStatusString(i);
    }

    @Deprecated
    public static String getOpenSourceSoftwareLicenseInfo(Context context) {
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(new Uri.Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build());
            try {
                try {
                    return new Scanner(openInputStream).useDelimiter("\\A").next();
                } catch (NoSuchElementException unused) {
                    if (openInputStream != null) {
                        openInputStream.close();
                    }
                    return null;
                }
            } finally {
                if (openInputStream != null) {
                    openInputStream.close();
                }
            }
        } catch (Exception unused2) {
        }
    }

    public static Context getRemoteContext(Context context) {
        try {
            return context.createPackageContext("com.google.android.gms", 3);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static Resources getRemoteResource(Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0064, code lost:
    
        if (r7.zza(r5, r1) == null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0066, code lost:
    
        r7 = "GooglePlayServicesUtil";
        r0 = "Google Play services signature invalid.";
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0080, code lost:
    
        if (com.google.android.gms.common.util.zzl.zzhh(r5.versionCode) >= com.google.android.gms.common.util.zzl.zzhh(com.google.android.gms.common.zze.GOOGLE_PLAY_SERVICES_VERSION_CODE)) goto L32;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0082, code lost:
    
        r0 = com.google.android.gms.common.zze.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        r1 = r5.versionCode;
        r2 = new java.lang.StringBuilder(77);
        r2.append("Google Play services out of date.  Requires ");
        r2.append(r0);
        r2.append(" but found ");
        r2.append(r1);
        android.util.Log.w("GooglePlayServicesUtil", r2.toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00a7, code lost:
    
        return 2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a8, code lost:
    
        r7 = r5.applicationInfo;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00aa, code lost:
    
        if (r7 != null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00be, code lost:
    
        if (r7.enabled != false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c0, code lost:
    
        return 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c2, code lost:
    
        return 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00ac, code lost:
    
        r7 = r0.getApplicationInfo("com.google.android.gms", 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b3, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b4, code lost:
    
        android.util.Log.wtf("GooglePlayServicesUtil", "Google Play services missing when getting application info.", r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00bb, code lost:
    
        return 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0071, code lost:
    
        if (r7.zza(r5, com.google.android.gms.common.zzd.C0020zzd.xa) == null) goto L24;
     */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int isGooglePlayServicesAvailable(android.content.Context r7) {
        /*
            android.content.pm.PackageManager r0 = r7.getPackageManager()
            android.content.res.Resources r1 = r7.getResources()     // Catch: java.lang.Throwable -> Le
            int r2 = com.google.android.gms.R.string.common_google_play_services_unknown_issue     // Catch: java.lang.Throwable -> Le
            r1.getString(r2)     // Catch: java.lang.Throwable -> Le
            goto L15
        Le:
            java.lang.String r1 = "GooglePlayServicesUtil"
            java.lang.String r2 = "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included."
            android.util.Log.e(r1, r2)
        L15:
            java.lang.String r1 = "com.google.android.gms"
            java.lang.String r2 = r7.getPackageName()
            boolean r1 = r1.equals(r2)
            if (r1 != 0) goto L24
            zzbp(r7)
        L24:
            boolean r1 = com.google.android.gms.common.util.zzi.zzci(r7)
            r2 = 1
            r1 = r1 ^ r2
            r3 = 0
            r4 = 9
            if (r1 == 0) goto L40
            java.lang.String r3 = "com.android.vending"
            r5 = 8256(0x2040, float:1.1569E-41)
            android.content.pm.PackageInfo r3 = r0.getPackageInfo(r3, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L38
            goto L40
        L38:
            java.lang.String r7 = "GooglePlayServicesUtil"
            java.lang.String r0 = "Google Play Store is missing."
        L3c:
            android.util.Log.w(r7, r0)
            return r4
        L40:
            java.lang.String r5 = "com.google.android.gms"
            r6 = 64
            android.content.pm.PackageInfo r5 = r0.getPackageInfo(r5, r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lc3
            com.google.android.gms.common.zzf r7 = com.google.android.gms.common.zzf.zzbv(r7)
            r6 = 0
            if (r1 == 0) goto L6b
            com.google.android.gms.common.zzd$zza[] r1 = com.google.android.gms.common.zzd.C0020zzd.xa
            com.google.android.gms.common.zzd$zza r1 = r7.zza(r3, r1)
            if (r1 != 0) goto L5c
            java.lang.String r7 = "GooglePlayServicesUtil"
            java.lang.String r0 = "Google Play Store signature invalid."
            goto L3c
        L5c:
            com.google.android.gms.common.zzd$zza[] r3 = new com.google.android.gms.common.zzd.zza[r2]
            r3[r6] = r1
            com.google.android.gms.common.zzd$zza r7 = r7.zza(r5, r3)
            if (r7 != 0) goto L74
        L66:
            java.lang.String r7 = "GooglePlayServicesUtil"
            java.lang.String r0 = "Google Play services signature invalid."
            goto L3c
        L6b:
            com.google.android.gms.common.zzd$zza[] r1 = com.google.android.gms.common.zzd.C0020zzd.xa
            com.google.android.gms.common.zzd$zza r7 = r7.zza(r5, r1)
            if (r7 != 0) goto L74
            goto L66
        L74:
            int r7 = com.google.android.gms.common.zze.GOOGLE_PLAY_SERVICES_VERSION_CODE
            int r7 = com.google.android.gms.common.util.zzl.zzhh(r7)
            int r1 = r5.versionCode
            int r1 = com.google.android.gms.common.util.zzl.zzhh(r1)
            if (r1 >= r7) goto La8
            java.lang.String r7 = "GooglePlayServicesUtil"
            int r0 = com.google.android.gms.common.zze.GOOGLE_PLAY_SERVICES_VERSION_CODE
            int r1 = r5.versionCode
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r3 = 77
            r2.<init>(r3)
            java.lang.String r3 = "Google Play services out of date.  Requires "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = " but found "
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r2.toString()
            android.util.Log.w(r7, r0)
            r7 = 2
            return r7
        La8:
            android.content.pm.ApplicationInfo r7 = r5.applicationInfo
            if (r7 != 0) goto Lbc
            java.lang.String r7 = "com.google.android.gms"
            android.content.pm.ApplicationInfo r7 = r0.getApplicationInfo(r7, r6)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Lb3
            goto Lbc
        Lb3:
            r7 = move-exception
            java.lang.String r0 = "GooglePlayServicesUtil"
            java.lang.String r1 = "Google Play services missing when getting application info."
            android.util.Log.wtf(r0, r1, r7)
            return r2
        Lbc:
            boolean r7 = r7.enabled
            if (r7 != 0) goto Lc2
            r7 = 3
            return r7
        Lc2:
            return r6
        Lc3:
            java.lang.String r7 = "GooglePlayServicesUtil"
            java.lang.String r0 = "Google Play services is missing."
            android.util.Log.w(r7, r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.zze.isGooglePlayServicesAvailable(android.content.Context):int");
    }

    @Deprecated
    public static boolean isUserRecoverableError(int i) {
        if (i == 9) {
            return true;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
                return true;
            default:
                return false;
        }
    }

    private static int zzaqq() {
        return 9877000;
    }

    @Deprecated
    public static boolean zzaqr() {
        return zzi.zzaym();
    }

    @Deprecated
    public static void zzaz(Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = zzc.zzaql().isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable != 0) {
            Intent zzb = zzc.zzaql().zzb(context, isGooglePlayServicesAvailable, "e");
            StringBuilder sb = new StringBuilder(57);
            sb.append("GooglePlayServices not available due to error ");
            sb.append(isGooglePlayServicesAvailable);
            Log.e("GooglePlayServicesUtil", sb.toString());
            if (zzb != null) {
                throw new GooglePlayServicesRepairableException(isGooglePlayServicesAvailable, "Google Play Services not available", zzb);
            }
            throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
    }

    @Deprecated
    public static int zzbk(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            Log.w("GooglePlayServicesUtil", "Google Play services is missing.");
            return 0;
        }
    }

    @Deprecated
    public static void zzbn(Context context) {
        if (xf.getAndSet(true)) {
            return;
        }
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.cancel(10436);
            }
        } catch (SecurityException unused) {
        }
    }

    private static void zzbp(Context context) {
        if (xg.get()) {
            return;
        }
        int zzce = zzy.zzce(context);
        if (zzce == 0) {
            throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
        }
        if (zzce != GOOGLE_PLAY_SERVICES_VERSION_CODE) {
            int i = GOOGLE_PLAY_SERVICES_VERSION_CODE;
            String valueOf = String.valueOf("com.google.android.gms.version");
            StringBuilder sb = new StringBuilder(290 + String.valueOf(valueOf).length());
            sb.append("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected ");
            sb.append(i);
            sb.append(" but found ");
            sb.append(zzce);
            sb.append(".  You must have the following declaration within the <application> element:     <meta-data android:name=\"");
            sb.append(valueOf);
            sb.append("\" android:value=\"@integer/google_play_services_version\" />");
            throw new IllegalStateException(sb.toString());
        }
    }

    public static boolean zzbq(Context context) {
        zzbt(context);
        return xd;
    }

    public static boolean zzbr(Context context) {
        return zzbq(context) || !zzaqr();
    }

    @TargetApi(18)
    public static boolean zzbs(Context context) {
        Bundle applicationRestrictions;
        return zzs.zzayt() && (applicationRestrictions = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName())) != null && "true".equals(applicationRestrictions.getString("restricted_profile"));
    }

    private static void zzbt(Context context) {
        if (xe) {
            return;
        }
        zzbu(context);
    }

    private static void zzbu(Context context) {
        try {
            try {
                PackageInfo packageInfo = zzsz.zzco(context).getPackageInfo("com.google.android.gms", 64);
                if (packageInfo == null || zzf.zzbv(context).zza(packageInfo, zzd.C0020zzd.xa[1]) == null) {
                    xd = false;
                } else {
                    xd = true;
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.w("GooglePlayServicesUtil", "Cannot find Google Play services package name.", e);
            }
        } finally {
            xe = true;
        }
    }

    @TargetApi(19)
    @Deprecated
    public static boolean zzc(Context context, int i, String str) {
        return zzx.zzc(context, i, str);
    }

    @Deprecated
    public static boolean zzd(Context context, int i) {
        if (i == 18) {
            return true;
        }
        if (i == 1) {
            return zzs(context, "com.google.android.gms");
        }
        return false;
    }

    @Deprecated
    public static boolean zze(Context context, int i) {
        if (i == 9) {
            return zzs(context, "com.android.vending");
        }
        return false;
    }

    @Deprecated
    public static boolean zzf(Context context, int i) {
        return zzx.zzf(context, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(21)
    public static boolean zzs(Context context, String str) {
        boolean equals = str.equals("com.google.android.gms");
        if (equals && com.google.android.gms.common.util.zzd.zzayi()) {
            return false;
        }
        if (zzs.zzayx()) {
            Iterator<PackageInstaller.SessionInfo> it = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().getAppPackageName())) {
                    return true;
                }
            }
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            return equals ? applicationInfo.enabled : applicationInfo.enabled && !zzbs(context);
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
