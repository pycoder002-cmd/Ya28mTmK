package com.startapp;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.support.v4.os.EnvironmentCompat;
import android.text.TextUtils;
import io.sentry.marshaller.json.JsonMarshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class ra {
    public static final String[] a = {"/dev/socket/genyd", "/dev/socket/baseband_genyd"};
    public static final String[] b = {"goldfish"};
    public static final String[] c = {"/dev/socket/qemud", "/dev/qemu_pipe"};
    public static final String[] d = {"ueventd.android_x86.rc", "x86.prop", "ueventd.ttVM_x86.rc", "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86", "init.vbox86.rc", "ueventd.vbox86.rc"};
    public static final String[] e = {"fstab.andy", "ueventd.andy.rc"};
    public static final String[] f = {"fstab.nox", "init.nox.rc", "ueventd.nox.rc", "/BigNoxGameHD", "/YSLauncher"};
    public static final sa[] g = {new sa("init.svc.qemud", null), new sa("init.svc.qemu-props", null), new sa("qemu.hw.mainkeys", null), new sa("qemu.sf.fake_camera", null), new sa("qemu.sf.lcd_density", null), new sa("ro.bootloader", EnvironmentCompat.MEDIA_UNKNOWN), new sa("ro.bootmode", EnvironmentCompat.MEDIA_UNKNOWN), new sa("ro.hardware", "goldfish"), new sa("ro.kernel.android.qemud", null), new sa("ro.kernel.qemu.gles", null), new sa("ro.kernel.qemu", "1"), new sa("ro.product.device", "generic"), new sa("ro.product.model", JsonMarshaller.SDK), new sa("ro.product.name", JsonMarshaller.SDK), new sa("ro.serialno", null), new sa("ro.build.description", "72656C656173652D6B657973"), new sa("ro.build.fingerprint", "3A757365722F72656C656173652D6B657973"), new sa("net.eth0.dns1", null), new sa("rild.libpath", "2F73797374656D2F6C69622F6C69627265666572656E63652D72696C2E736F"), new sa("ro.radio.use-ppp", null), new sa("gsm.version.baseband", null), new sa("ro.build.tags", "72656C656173652D6B65"), new sa("ro.build.display.id", "746573742D"), new sa("init.svc.console", null)};
    public static ra h;
    public static Boolean i;
    public final Context j;
    public List<String> k;

    public ra(Context context) {
        ArrayList arrayList = new ArrayList();
        this.k = arrayList;
        this.j = context;
        arrayList.add("com.google.android.launcher.layouts.genymotion");
        this.k.add("com.bluestacks");
        this.k.add("com.bignox.app");
        this.k.add("com.vphone.launcher");
    }

    public static boolean a(Context context) {
        if (i == null) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            if (h == null) {
                h = new ra(y8.b(context));
            }
            i = Boolean.valueOf(h.c());
        }
        return i.booleanValue();
    }

    public final boolean a() {
        if (!a(this.j, "android.permission.INTERNET")) {
            return false;
        }
        String[] strArr = {"/system/bin/netcfg"};
        StringBuilder sb = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(strArr);
            processBuilder.directory(new File("/system/bin/"));
            processBuilder.redirectErrorStream(true);
            InputStream inputStream = processBuilder.start().getInputStream();
            byte[] bArr = new byte[1024];
            while (inputStream.read(bArr) != -1) {
                sb.append(new String(bArr));
            }
            inputStream.close();
        } catch (Exception unused) {
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            return false;
        }
        for (String str : sb2.split("\n")) {
            if ((str.contains("wlan0") || str.contains("tunl0") || str.contains("eth0")) && str.contains("10.0.2.15")) {
                return true;
            }
        }
        return false;
    }

    public final boolean a(Context context, String str) {
        try {
            return Build.VERSION.SDK_INT >= 23 ? context.checkSelfPermission(str) == 0 : context.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable unused) {
            return false;
        }
    }

    public final boolean a(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (((a(this.j, "android.permission.READ_EXTERNAL_STORAGE") && str2.contains("/") && str.equals("Nox")) ? new File(Environment.getExternalStorageDirectory() + str2) : new File(str2)).exists()) {
                return true;
            }
        }
        return false;
    }

    public final boolean b() {
        File[] fileArr = {new File("/proc/tty/drivers"), new File("/proc/cpuinfo")};
        for (int i2 = 0; i2 < 2; i2++) {
            File file = fileArr[i2];
            if (file.exists() && file.canRead()) {
                char[] cArr = new char[1024];
                StringBuilder sb = new StringBuilder();
                BufferedReader bufferedReader = null;
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    while (true) {
                        try {
                            int read = bufferedReader2.read(cArr);
                            if (read != -1) {
                                sb.append(cArr, 0, read);
                            } else {
                                try {
                                    break;
                                } catch (IOException unused) {
                                }
                            }
                        } catch (Exception unused2) {
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException unused3) {
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader2;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (IOException unused4) {
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader2.close();
                    String sb2 = sb.toString();
                    for (String str : b) {
                        if (sb2.contains(str)) {
                            return true;
                        }
                    }
                } catch (Exception unused5) {
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0100  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean c() {
        /*
            Method dump skipped, instructions count: 455
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.ra.c():boolean");
    }
}
