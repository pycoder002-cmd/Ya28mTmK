package com.startapp;

import android.app.Activity;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Paint;
import android.os.Build;
import android.webkit.WebView;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ya {
    public static final /* synthetic */ int a = 0;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements Comparator<Signature> {
        @Override // java.util.Comparator
        public int compare(Signature signature, Signature signature2) {
            return signature.toCharsString().compareTo(signature2.toCharsString());
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface b {
    }

    static {
        a((Class<?>) ya.class);
    }

    public static int a(int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 17) {
            return i;
        }
        if (i == 16) {
            return 0;
        }
        if (i == 17) {
            return 1;
        }
        if (i == 20) {
            return 9;
        }
        if (i == 21) {
            return 11;
        }
        if (i == 8388611) {
            if (i2 < 14) {
                return 3;
            }
            return i;
        }
        if (i == 8388613 && i2 < 14) {
            return 5;
        }
        return i;
    }

    public static int a(Activity activity, int i, boolean z) {
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int i2 = 8;
        if (i != 1) {
            if (i == 2) {
                if (Build.VERSION.SDK_INT <= 8 || z || rotation == 0 || rotation == 1) {
                    i2 = 0;
                }
            }
            i2 = 1;
        } else {
            if (Build.VERSION.SDK_INT > 8 && !z && (rotation == 1 || rotation == 2)) {
                i2 = 9;
            }
            i2 = 1;
        }
        try {
            activity.setRequestedOrientation(i2);
        } catch (Throwable unused) {
        }
        return i2;
    }

    public static String a(Context context) {
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            if (signatureArr == null || signatureArr.length <= 0) {
                return null;
            }
            if (signatureArr.length == 1) {
                return signatureArr[0].toCharsString();
            }
            Arrays.sort(signatureArr, new a());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < signatureArr.length; i++) {
                sb.append(signatureArr[i].toCharsString());
                if (i < signatureArr.length - 1) {
                    sb.append(';');
                }
            }
            return sb.toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String a(Class<?> cls) {
        return "startapp." + cls.getSimpleName();
    }

    public static String a(String str, Context context) throws FileNotFoundException {
        String str2;
        int i;
        try {
            str2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).sourceDir;
        } catch (Throwable unused) {
            str2 = null;
        }
        if (str2 == null) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(str2);
        StringBuilder sb = new StringBuilder();
        try {
            byte[] bArr = new byte[8192];
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                if (read > 0) {
                    messageDigest.update(bArr, 0, read);
                }
            }
            for (byte b2 : messageDigest.digest()) {
                sb.append(Integer.toString((b2 & FileDownloadStatus.error) + 256, 16).substring(1));
            }
        } catch (Throwable unused2) {
        }
        a(fileInputStream);
        return sb.toString().toUpperCase(Locale.ENGLISH);
    }

    public static List<PackageInfo> a(PackageManager packageManager) throws Exception {
        return (List) packageManager.getClass().getMethod("getInstalledPackages", Integer.TYPE).invoke(packageManager, 8192);
    }

    public static void a(Context context, ServiceConnection serviceConnection) {
        if (serviceConnection != null) {
            try {
                context.unbindService(serviceConnection);
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.onPause();
        } else {
            try {
                Class.forName("android.webkit.WebView").getMethod("onPause", new Class[0]).invoke(webView, new Object[0]);
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(WebView webView, Paint paint) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.setLayerType(1, null);
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

    public static boolean a() {
        return Build.VERSION.SDK_INT >= 14;
    }

    public static boolean a(Context context, String str) {
        try {
            return Build.VERSION.SDK_INT >= 23 ? context.checkSelfPermission(str) == 0 : context.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a(Context context, String str, int i) {
        try {
            return context.getPackageManager().getPackageInfo(str, 128).versionCode >= i;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean a(PackageInfo packageInfo) {
        int i = packageInfo.applicationInfo.flags;
        return ((i & 1) == 0 && (i & 128) == 0) ? false : true;
    }

    public static void b(WebView webView) {
        if (Build.VERSION.SDK_INT >= 11) {
            webView.onResume();
        } else {
            try {
                Class.forName("android.webkit.WebView").getMethod("onResume", new Class[0]).invoke(webView, new Object[0]);
            } catch (Throwable unused) {
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0022, code lost:
    
        if (android.provider.Settings.Secure.getInt(r5.getContentResolver(), "install_non_market_apps") == 1) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(android.content.Context r5) {
        /*
            r0 = 0
            r1 = 1
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L25
            r3 = 17
            java.lang.String r4 = "install_non_market_apps"
            if (r2 < r3) goto L1a
            r3 = 21
            if (r2 < r3) goto Lf
            goto L1a
        Lf:
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L25
            int r5 = android.provider.Settings.Global.getInt(r5, r4)     // Catch: java.lang.Throwable -> L25
            if (r5 != r1) goto L25
            goto L24
        L1a:
            android.content.ContentResolver r5 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L25
            int r5 = android.provider.Settings.Secure.getInt(r5, r4)     // Catch: java.lang.Throwable -> L25
            if (r5 != r1) goto L25
        L24:
            r0 = 1
        L25:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.ya.b(android.content.Context):boolean");
    }

    public static boolean c(Context context) {
        try {
            return ra.a(context);
        } catch (Throwable unused) {
            return false;
        }
    }
}
