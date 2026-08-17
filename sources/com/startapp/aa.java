package com.startapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.InstallSourceInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.RemoteException;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;
import android.webkit.WebView;
import com.startapp.sdk.ads.banner.banner3d.Banner3DAd;
import com.startapp.sdk.ads.banner.bannerstandard.BannerStandardAd;
import com.startapp.sdk.ads.interstitials.OverlayActivity;
import com.startapp.sdk.ads.interstitials.ReturnAd;
import com.startapp.sdk.ads.nativead.NativeAd;
import com.startapp.sdk.ads.offerWall.offerWallHtml.OfferWallAd;
import com.startapp.sdk.ads.offerWall.offerWallJson.OfferWall3DAd;
import com.startapp.sdk.ads.splash.SplashAd;
import com.startapp.sdk.ads.video.VideoEnabledAd;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.common.SDKException;
import com.startapp.sdk.components.ComponentLocator;
import io.sentry.DefaultSentryClientFactory;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class aa {
    public static Map<Activity, Integer> a = new WeakHashMap();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
        void a();

        void a(String str);
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class b {
        public static StackTraceElement[] a() {
            return Thread.currentThread().getStackTrace();
        }
    }

    public static int a(Activity activity, int i, boolean z) {
        if (z) {
            if (!a.containsKey(activity)) {
                a.put(activity, Integer.valueOf(activity.getRequestedOrientation()));
            }
            return i == activity.getResources().getConfiguration().orientation ? ya.a(activity, i, false) : ya.a(activity, i, true);
        }
        int i2 = -1;
        if (a.containsKey(activity)) {
            i2 = a.get(activity).intValue();
            int i3 = ya.a;
            try {
                activity.setRequestedOrientation(i2);
            } catch (Throwable unused) {
            }
            a.remove(activity);
        }
        return i2;
    }

    public static Intent a(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            if (str == null) {
                str = context.getPackageName();
            }
            return packageManager.getLaunchIntentForPackage(str);
        } catch (Throwable th) {
            p7.a(context, th);
            return null;
        }
    }

    public static <T> T a(String str, Class<T> cls) throws SDKException {
        T t = (T) c.a(str, cls);
        if (t != null) {
            return t;
        }
        throw new SDKException();
    }

    public static StackTraceElement a(int i) {
        if (i < 0) {
            i = 0;
        }
        StackTraceElement[] a2 = b.a();
        if (a2 == null) {
            return null;
        }
        String name = b.class.getName();
        int length = a2.length;
        for (int i2 = 0; i2 < length; i2++) {
            StackTraceElement stackTraceElement = a2[i2];
            if (stackTraceElement != null && name.equals(stackTraceElement.getClassName())) {
                int i3 = i2 + 3 + i;
                if (i3 < length) {
                    return a2[i3];
                }
                return null;
            }
        }
        return null;
    }

    public static StackTraceElement a(Throwable th) {
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            StackTraceElement a2 = a(th.getStackTrace());
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    public static StackTraceElement a(StackTraceElement[] stackTraceElementArr) {
        String className;
        if (stackTraceElementArr == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTraceElementArr) {
            if (stackTraceElement != null && (className = stackTraceElement.getClassName()) != null && className.startsWith("com.startapp.")) {
                return stackTraceElement;
            }
        }
        return null;
    }

    public static String a(Context context) {
        ActivityInfo activityInfo;
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null || (activityInfo = resolveActivity.activityInfo) == null) {
                return "";
            }
            String str = activityInfo.packageName;
            return str != null ? str.toLowerCase() : str;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Drawable drawable, int i, int i2, Bitmap.Config config) {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, config);
        Drawable mutate = drawable.mutate();
        mutate.setBounds(0, 0, i, i2);
        mutate.draw(new Canvas(createBitmap));
        g9 g9Var = new g9(i * i2);
        createBitmap.compress(Bitmap.CompressFormat.PNG, 100, g9Var);
        return new String(Base64.encode(g9Var.a(), 0, g9Var.b(), 2));
    }

    public static String a(Ad ad) {
        if (ad instanceof VideoEnabledAd) {
            VideoEnabledAd videoEnabledAd = (VideoEnabledAd) ad;
            return videoEnabledAd.getType() == Ad.AdType.VIDEO ? "VIDEO" : videoEnabledAd.getType() == Ad.AdType.REWARDED_VIDEO ? "REWARDED_VIDEO" : "INTERSTITIAL";
        }
        if (ad instanceof ReturnAd) {
            return "RETURN";
        }
        if (ad instanceof OfferWallAd) {
            return "OFFER_WALL";
        }
        if (ad instanceof OfferWall3DAd) {
            return "OFFER_WALL_3D";
        }
        if (!(ad instanceof BannerStandardAd)) {
            return ad instanceof Banner3DAd ? "BANNER_3D" : ad instanceof NativeAd ? "NATIVE" : ad instanceof SplashAd ? "SPLASH" : "UNDEFINED";
        }
        BannerStandardAd bannerStandardAd = (BannerStandardAd) ad;
        return bannerStandardAd.u() == 0 ? AdPreferences.TYPE_BANNER : bannerStandardAd.u() == 1 ? "MREC" : bannerStandardAd.u() == 2 ? "COVER" : "BANNER_UNDEFINED";
    }

    public static <T> String a(Iterable<T> iterable, String str) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (T t : iterable) {
            if (z) {
                sb.append(str);
            }
            sb.append(t);
            z = true;
        }
        return sb.toString();
    }

    public static String a(StackTraceElement stackTraceElement) {
        if (stackTraceElement == null) {
            return "null";
        }
        return stackTraceElement.getClassName() + '.' + stackTraceElement.getMethodName() + "()";
    }

    public static String a(String str, String str2, String str3) {
        int indexOf;
        int indexOf2;
        if (str == null || str2 == null || str3 == null || (indexOf = str.indexOf(str2)) == -1 || (indexOf2 = str.indexOf(str3, str2.length() + indexOf)) == -1) {
            return null;
        }
        return str.substring(indexOf + str2.length(), indexOf2);
    }

    public static String a(int... iArr) {
        int length = iArr.length;
        char[] cArr = new char[length];
        char c = (char) length;
        for (int i = 0; i < length; i++) {
            c = (char) (c + iArr[i]);
            cArr[i] = c;
        }
        return new String(cArr);
    }

    public static List<String> a(List<String> list) {
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (d(str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static List<Field> a(List<Field> list, Class<?> cls) {
        list.addAll(Arrays.asList(cls.getDeclaredFields()));
        if (cls.getSuperclass() != null) {
            a(list, (Class<?>) cls.getSuperclass());
        }
        return list;
    }

    public static void a(Activity activity, boolean z) {
        a(activity, activity.getResources().getConfiguration().orientation, z);
    }

    public static void a(Context context, WebView webView, String str) {
        try {
            webView.loadDataWithBaseURL(MetaData.h.l(), str, "text/html", "utf-8", null);
        } catch (Throwable th) {
            p7.a(context, th);
        }
    }

    public static void a(Context context, Object obj, Throwable th) {
        if (obj.getClass().getName().startsWith("com.startapp.")) {
            p7.a(context, th);
        }
    }

    public static void a(Context context, boolean z, String str, boolean z2) {
        if (z) {
            Log.e("StartAppSDK", str);
        } else {
            Log.i("StartAppSDK", str);
        }
        boolean z3 = f(context) || ya.c(context);
        if (z2 && z3) {
            p7 p7Var = new p7(q7.b);
            p7Var.d = "Log for a publisher";
            p7Var.e = str;
            p7Var.a(context);
        }
    }

    public static void a(WebView webView, boolean z, String str, Object... objArr) {
        if (webView != null) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append("(");
                if (objArr != null) {
                    for (int i = 0; i < objArr.length; i++) {
                        if (z && (objArr[i] instanceof String)) {
                            sb.append("\"");
                            sb.append(objArr[i]);
                            sb.append("\"");
                        } else {
                            sb.append(objArr[i]);
                        }
                        if (i < objArr.length - 1) {
                            sb.append(",");
                        }
                    }
                }
                sb.append(")");
                webView.loadUrl("javascript:" + sb.toString());
            } catch (Exception unused) {
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void a(PrintWriter printWriter, StackTraceElement[] stackTraceElementArr) {
        String className;
        if (stackTraceElementArr == null) {
            return;
        }
        int length = stackTraceElementArr.length;
        StackTraceElement stackTraceElement = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        while (i < length) {
            StackTraceElement stackTraceElement2 = stackTraceElementArr[i];
            if (stackTraceElement2 != null && (className = stackTraceElement2.getClassName()) != null) {
                boolean z2 = i < 3;
                boolean startsWith = className.startsWith("com.startapp.");
                if (z2 || startsWith || z) {
                    if (i2 > 0) {
                        printWriter.print(' ');
                        printWriter.println(i2);
                        i2 = 0;
                    }
                    if (stackTraceElement != null) {
                        printWriter.print(' ');
                        printWriter.print(stackTraceElement.getClassName());
                        printWriter.print('.');
                        printWriter.print(stackTraceElement.getMethodName());
                        printWriter.println("()");
                        stackTraceElement = null;
                    }
                    printWriter.print(' ');
                    printWriter.print(stackTraceElement2.getClassName());
                    printWriter.print('.');
                    printWriter.print(stackTraceElement2.getMethodName());
                    printWriter.println("()");
                } else {
                    if (stackTraceElement != null) {
                        i2++;
                    }
                    stackTraceElement = stackTraceElement2;
                }
                z = startsWith;
            }
            i++;
        }
        if (stackTraceElement != null) {
            i2++;
        }
        if (i2 > 0) {
            printWriter.print(' ');
            printWriter.println(i2);
        }
    }

    public static <T> boolean a(T t, T t2) {
        return t == null ? t2 == null : t.equals(t2);
    }

    public static boolean a(Throwable th, Class<? extends Throwable> cls) {
        while (th != null) {
            if (cls.isInstance(th)) {
                return true;
            }
            th = th.getCause();
        }
        return false;
    }

    public static byte[] a(String str) throws IOException {
        byte[] bytes = str.getBytes();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, new Deflater(9, true));
        deflaterOutputStream.write(bytes);
        deflaterOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    public static String b(Context context) {
        PackageManager packageManager;
        String str = null;
        try {
            packageManager = context.getPackageManager();
        } catch (Throwable unused) {
            packageManager = null;
        }
        if (packageManager == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT > 29) {
            try {
                InstallSourceInfo installSourceInfo = packageManager.getInstallSourceInfo(context.getPackageName());
                if (installSourceInfo != null) {
                    str = installSourceInfo.getInstallingPackageName();
                }
            } catch (Throwable unused2) {
            }
        }
        if (str != null) {
            return str;
        }
        try {
            return packageManager.getInstallerPackageName(context.getPackageName());
        } catch (Throwable unused3) {
            return str;
        }
    }

    public static String b(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String b(Throwable th) {
        try {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                PrintWriter printWriter = new PrintWriter(new DeflaterOutputStream(new Base64OutputStream(byteArrayOutputStream, 10), new Deflater(9, true)));
                y9 y9Var = new y9(th);
                while (y9Var.hasNext()) {
                    Throwable next = y9Var.next();
                    if (y9Var.d) {
                        printWriter.println('-');
                    }
                    printWriter.println(next.toString().trim());
                    a(printWriter, next.getStackTrace());
                }
                printWriter.close();
                return byteArrayOutputStream.toString();
            } catch (Throwable unused) {
                return th.toString();
            }
        } catch (Throwable unused2) {
            return th.getMessage();
        }
    }

    public static <T> List<T> b(List<T> list) {
        return list != null ? Collections.unmodifiableList(list) : Collections.emptyList();
    }

    public static boolean b(Context context, String str) {
        if (Build.VERSION.SDK_INT < 15) {
            return false;
        }
        if (str.startsWith("sms:") || str.startsWith("smsto:")) {
            Intent intent = new Intent("android.intent.action.SENDTO");
            intent.setData(Uri.parse(str));
            intent.addFlags(268435456);
            try {
                context.startActivity(intent);
                return true;
            } catch (Throwable th) {
                p7.a(context, th);
            }
        }
        return false;
    }

    public static <T> boolean b(T t, T t2) {
        Object obj;
        boolean z = false;
        try {
            Class<?> cls = t2.getClass();
            LinkedList<Field> linkedList = new LinkedList();
            linkedList.addAll(Arrays.asList(cls.getDeclaredFields()));
            if (cls.getSuperclass() != null) {
                a((List<Field>) linkedList, (Class<?>) cls.getSuperclass());
            }
            for (Field field : linkedList) {
                int modifiers = field.getModifiers();
                if (!Modifier.isTransient(modifiers) && !Modifier.isStatic(modifiers)) {
                    field.setAccessible(true);
                    if (field.get(t) == null && (obj = field.get(t2)) != null) {
                        field.set(t, obj);
                        z = true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return z;
    }

    public static String c(Context context) {
        ComponentName component;
        Intent a2 = a(context, (String) null);
        if (a2 == null || (component = a2.getComponent()) == null) {
            return null;
        }
        return component.getClassName();
    }

    public static String c(String str) throws IOException {
        return Base64.encodeToString(wa.a(a(str)), 10);
    }

    public static boolean d(Context context) {
        boolean z = false;
        try {
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
            boolean z2 = false;
            int i = 0;
            while (!z2) {
                try {
                    if (i >= activityInfoArr.length) {
                        return z2;
                    }
                    int i2 = i + 1;
                    ActivityInfo activityInfo = activityInfoArr[i];
                    if (activityInfo.name.equals(OverlayActivity.class.getName())) {
                        int i3 = activityInfo.flags & 512;
                        i = i2;
                        z2 = i3 == 0;
                    } else {
                        i = i2;
                    }
                } catch (PackageManager.NameNotFoundException | Exception unused) {
                    z = z2;
                    return z;
                }
            }
            return z2;
        } catch (PackageManager.NameNotFoundException | Exception unused2) {
        }
    }

    public static boolean d(String str) {
        if (str == null) {
            return false;
        }
        try {
            String[] split = new URL(MetaData.h.c()).getHost().split("\\.");
            if (split.length > 1) {
                Locale locale = Locale.ENGLISH;
                return str.toLowerCase(locale).contains(split[1].toLowerCase(locale));
            }
        } catch (MalformedURLException unused) {
        }
        return false;
    }

    public static long e(String str) {
        long j;
        if (str == null || str.length() < 1) {
            return 0L;
        }
        int length = str.length() - 1;
        long j2 = 0;
        long j3 = 0;
        char c = 0;
        boolean z = true;
        while (length >= 0) {
            char charAt = str.charAt(length);
            if (charAt < '0' || charAt > '9') {
                if (charAt != 'm' || c != 's') {
                    if (z) {
                        if (charAt == 's') {
                            j = 1000;
                        } else if (charAt == 'm') {
                            j = DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT;
                        } else if (charAt == 'h') {
                            j = 3600000;
                        } else if (charAt == 'd') {
                            j = 86400000;
                        }
                        j3 = j;
                        z = false;
                    }
                    return ~length;
                }
                j3 = 1;
                length--;
                c = charAt;
            } else {
                if (j3 == 0) {
                    return ~length;
                }
                j2 += (charAt - '0') * j3;
                j3 *= 10;
                z = true;
                length--;
                c = charAt;
            }
        }
        return j2;
    }

    public static boolean e(Context context) {
        ActivityManager activityManager;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            activityManager = (ActivityManager) context.getSystemService("activity");
        } catch (Throwable th) {
            if (!a(th, (Class<? extends Throwable>) SecurityException.class) && !a(th, (Class<? extends Throwable>) RemoteException.class)) {
                p7.a(context, th);
            }
        }
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo != null && runningAppProcessInfo.importance == 100 && packageName.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean f(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo != null) {
                return (applicationInfo.flags & 2) != 0;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean g(Context context) {
        return ComponentLocator.a(context).e().a();
    }
}
