package com.startapp;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioDeviceInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.common.SDKException;
import com.startapp.sdk.components.ComponentLocator;
import io.sentry.marshaller.json.JsonMarshaller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class j5 {
    public static final Collection<Integer> a;
    public String A;
    public String B;
    public String C;
    public String D;
    public String E;
    public String F;
    public String G;
    public String H;
    public String I;
    public String J;
    public String K;
    public String L;
    public int M;
    public int N;
    public float O;
    public Boolean P;
    public int Q;
    public String R;
    public String S;
    public int T;
    public boolean U;
    public boolean V;
    public boolean W;
    public boolean X;
    public String Y;
    public String Z;
    public final int a0;
    public String b;
    public Long b0;
    public String c;
    public Integer c0;
    public String d;
    public Boolean d0;
    public Map<String, String> e;
    public Boolean e0;
    public Map<String, ?> f;
    public Boolean f0;
    public String g;
    public Boolean g0;
    public pa h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public Boolean r;
    public Boolean s;
    public String t;
    public boolean u;
    public int v;
    public String w;
    public String x;
    public String y;
    public String z;

    static {
        ArrayList arrayList = new ArrayList();
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            arrayList.add(23);
        }
        if (i >= 26) {
            arrayList.add(22);
        }
        if (i >= 23) {
            arrayList.add(3);
            arrayList.add(4);
            arrayList.add(7);
            arrayList.add(8);
        }
        a = Collections.unmodifiableCollection(arrayList);
    }

    public j5(int i) {
        new HashMap();
        this.d = "4.9.1";
        this.e = new TreeMap();
        this.L = "android";
        this.Q = 3;
        this.a0 = i;
    }

    public void a(Context context) {
        if (MetaData.h.k()) {
            return;
        }
        pa a2 = ComponentLocator.a(context).a().a();
        this.h = a2;
        if (TextUtils.isEmpty(a2.b) || "0".equals(a2.b)) {
            nc b = ComponentLocator.a(context).m.b();
            String str = b.b;
            if (str == null) {
                synchronized (b) {
                    str = b.b;
                    if (str == null) {
                        str = b.a.getString("e695c6d894060903", null);
                        if (str == null) {
                            str = UUID.randomUUID().toString();
                            if (!b.a.edit().putString("e695c6d894060903", str).commit()) {
                                str = "00000000-0000-0000-0000-000000000000";
                            }
                        }
                        b.b = str;
                    }
                }
            }
            this.k = str;
        }
        try {
            this.f = ComponentLocator.a(context).H.b().getAll();
        } catch (Throwable th) {
            p7.a(context, th);
        }
    }

    public void a(Context context, AdPreferences adPreferences) {
        c(context);
        try {
            b(context, adPreferences);
        } catch (Throwable unused) {
        }
        try {
            d(context);
        } catch (Throwable th) {
            p7.a(context, th);
        }
        try {
            e(context);
        } catch (Throwable th2) {
            p7.a(context, th2);
        }
        try {
            this.K = ComponentLocator.a(context).p().a(this);
        } catch (Throwable th3) {
            p7.a(context, th3);
        }
        try {
            a(context);
        } catch (Throwable th4) {
            p7.a(context, th4);
        }
        try {
            b(context);
        } catch (Throwable th5) {
            p7.a(context, th5);
        }
    }

    public void a(u9 u9Var) throws SDKException {
        Map<String, ?> map = this.f;
        if (map != null) {
            for (Map.Entry<String, ?> entry : map.entrySet()) {
                u9Var.a(entry.getKey(), entry.getValue(), false, true);
            }
        }
        u9Var.a("publisherId", (Object) this.b, false, true);
        u9Var.a("productId", (Object) this.c, a(), true);
        u9Var.a("os", (Object) this.L, true, true);
        u9Var.a("sdkVersion", (Object) this.d, false, true);
        u9Var.a("flavor", (Object) 1023, false, true);
        Map<String, String> map2 = this.e;
        if (map2 != null && !map2.isEmpty()) {
            String str = "";
            for (String str2 : this.e.keySet()) {
                str = str + str2 + ":" + this.e.get(str2) + ";";
            }
            u9Var.a("frameworksData", (Object) str.substring(0, str.length() - 1), false, false);
        }
        u9Var.a("packageId", (Object) this.i, false, true);
        u9Var.a("installerPkg", (Object) this.j, false, true);
        u9Var.a("age", (Object) this.g, false, true);
        pa paVar = this.h;
        if (paVar != null) {
            u9Var.a("userAdvertisingId", (Object) paVar.b, false, true);
            boolean z = this.h.d;
            if (z) {
                u9Var.a("limat", (Object) Boolean.valueOf(z), false, true);
            }
            u9Var.a("advertisingIdSource", (Object) this.h.c, false, true);
        }
        String str3 = this.k;
        if (str3 != null) {
            u9Var.a("duid", (Object) str3, false, true);
        }
        u9Var.a(FileDownloadBroadcastHandler.KEY_MODEL, (Object) this.l, false, true);
        u9Var.a("manufacturer", (Object) this.m, false, true);
        u9Var.a("deviceVersion", (Object) this.n, false, true);
        u9Var.a("locale", (Object) this.o, false, true);
        u9Var.a("localeList", (Object) this.p, false, true);
        u9Var.a("inputLangs", (Object) this.q, false, true);
        u9Var.a("isp", (Object) this.x, false, true);
        u9Var.a("ispName", (Object) this.y, false, true);
        u9Var.a("ispCarrId", (Object) this.z, false, true);
        u9Var.a("ispCarrIdName", (Object) this.A, false, true);
        u9Var.a("netOper", (Object) this.B, false, true);
        u9Var.a("networkOperName", (Object) this.C, false, true);
        u9Var.a("cid", (Object) this.D, false, true);
        u9Var.a("lac", (Object) this.E, false, true);
        u9Var.a("tac", (Object) this.F, false, true);
        u9Var.a("blat", (Object) this.G, false, true);
        u9Var.a("blon", (Object) this.H, false, true);
        u9Var.a("subPublisherId", (Object) null, false, true);
        u9Var.a("subProductId", (Object) null, false, true);
        u9Var.a("retryCount", (Object) null, false, true);
        u9Var.a("roaming", (Object) this.s, false, true);
        u9Var.a("grid", (Object) this.t, false, true);
        if (this.u) {
            u9Var.a("c5g", "1", false, false);
        }
        int i = this.v;
        if (i >= 0) {
            u9Var.a(NotificationCompat.CATEGORY_TRANSPORT, (Object) String.valueOf(i), false, false);
        }
        u9Var.a("silev", (Object) this.w, false, true);
        u9Var.a("cellSignalLevel", (Object) this.I, false, true);
        u9Var.a("cellTimingAdv", (Object) this.J, false, true);
        u9Var.a("outsource", (Object) this.r, false, true);
        u9Var.a("width", (Object) String.valueOf(this.M), false, true);
        u9Var.a("height", (Object) String.valueOf(this.N), false, true);
        u9Var.a("density", (Object) String.valueOf(this.O), false, true);
        u9Var.a("fgApp", (Object) this.P, false, true);
        u9Var.a("sdkId", (Object) String.valueOf(this.Q), true, true);
        u9Var.a("clientSessionId", (Object) this.R, false, true);
        u9Var.a("appVersion", (Object) this.S, false, true);
        u9Var.a("appCode", (Object) Integer.valueOf(this.T), false, true);
        u9Var.a("timeSinceBoot", (Object) Long.valueOf(SystemClock.elapsedRealtime()), false, true);
        u9Var.a("udbg", (Object) Boolean.valueOf(this.U), false, true);
        u9Var.a("root", (Object) Boolean.valueOf(this.V), false, true);
        u9Var.a("smltr", (Object) Boolean.valueOf(this.W), false, true);
        u9Var.a("isddbg", (Object) Boolean.valueOf(this.X), false, true);
        u9Var.a("pas", (Object) this.Y, false, true);
        u9Var.a("prm", (Object) this.Z, false, false);
        u9Var.a("free", (Object) this.b0, false, false);
        u9Var.a("chr", (Object) this.d0, false, false);
        u9Var.a("blp", (Object) this.c0, false, false);
        u9Var.a("hs", (Object) this.e0, false, false);
        u9Var.a("lpm", (Object) this.f0, false, false);
        u9Var.a("dm", (Object) this.g0, false, false);
        u9Var.a("rsc", (Object) this.K, false, true);
    }

    public void a(String str) {
        this.Z = str;
    }

    public boolean a() {
        return false;
    }

    public void b(Context context) {
        boolean z;
        try {
            this.b0 = null;
            if (Build.VERSION.SDK_INT >= 18) {
                this.b0 = Long.valueOf(new StatFs(Environment.getRootDirectory().getAbsolutePath()).getFreeBytes());
            }
        } catch (Throwable th) {
            p7.a(context, th);
        }
        boolean z2 = true;
        try {
            this.d0 = null;
            this.c0 = null;
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null) {
                if (registerReceiver.hasExtra("status")) {
                    int intExtra = registerReceiver.getIntExtra("status", -1);
                    if (intExtra != 2 && intExtra != 5) {
                        z = false;
                        this.d0 = Boolean.valueOf(z);
                    }
                    z = true;
                    this.d0 = Boolean.valueOf(z);
                }
                if (registerReceiver.hasExtra(JsonMarshaller.LEVEL) && registerReceiver.hasExtra("scale")) {
                    int intExtra2 = registerReceiver.getIntExtra(JsonMarshaller.LEVEL, -1);
                    int intExtra3 = registerReceiver.getIntExtra("scale", -1);
                    if (intExtra2 >= 0 && intExtra3 > 0) {
                        this.c0 = Integer.valueOf((intExtra2 * 100) / intExtra3);
                    }
                }
            }
        } catch (Throwable th2) {
            p7.a(context, th2);
        }
        try {
            this.e0 = null;
            Object systemService = context.getSystemService("audio");
            if (systemService instanceof AudioManager) {
                AudioManager audioManager = (AudioManager) systemService;
                if (Build.VERSION.SDK_INT >= 23) {
                    AudioDeviceInfo[] devices = audioManager.getDevices(2);
                    if (devices != null) {
                        for (AudioDeviceInfo audioDeviceInfo : devices) {
                            if (audioDeviceInfo != null && a.contains(Integer.valueOf(audioDeviceInfo.getType()))) {
                                break;
                            }
                        }
                    }
                    z2 = false;
                    this.e0 = Boolean.valueOf(z2);
                }
            }
        } catch (Throwable th3) {
            p7.a(context, th3);
        }
        try {
            this.f0 = null;
            Object systemService2 = context.getSystemService("power");
            if (systemService2 instanceof PowerManager) {
                PowerManager powerManager = (PowerManager) systemService2;
                if (Build.VERSION.SDK_INT >= 21) {
                    this.f0 = Boolean.valueOf(powerManager.isPowerSaveMode());
                }
            }
        } catch (Throwable th4) {
            p7.a(context, th4);
        }
        try {
            this.g0 = null;
            int i = context.getResources().getConfiguration().uiMode & 48;
            if (i == 32) {
                this.g0 = Boolean.TRUE;
            } else if (i == 16) {
                this.g0 = Boolean.FALSE;
            }
        } catch (Throwable th5) {
            p7.a(context, th5);
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(27:1|(1:3)|4|5|6|7|8|9|10|11|12|13|(1:15)(1:52)|(12:17|18|19|20|21|(1:48)(1:29)|30|(1:34)|35|(4:37|38|(2:41|39)|42)|43|44)|51|18|19|20|21|(1:23)|48|30|(2:32|34)|35|(0)|43|44) */
    /* JADX WARN: Removed duplicated region for block: B:37:0x011d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(android.content.Context r5, com.startapp.sdk.adsbase.model.AdPreferences r6) {
        /*
            Method dump skipped, instructions count: 327
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.j5.b(android.content.Context, com.startapp.sdk.adsbase.model.AdPreferences):void");
    }

    public void c(Context context) {
        if (!(this.c != null)) {
            i5 c = ComponentLocator.a(context).c();
            String str = c.c;
            if (str == null) {
                synchronized (c.a) {
                    str = c.c;
                    if (str == null) {
                        str = c.b.getString("c88d4eab540fab77", null);
                    }
                }
            }
            this.b = str;
            String str2 = c.d;
            if (str2 == null) {
                synchronized (c.a) {
                    str2 = c.d;
                    if (str2 == null && (str2 = c.b.getString("2696a7f502faed4b", null)) == null) {
                        str2 = c.b.getString("31721150b470a3b9", null);
                    }
                }
            }
            this.c = str2;
        }
        this.i = context.getPackageName();
    }

    /* JADX WARN: Code restructure failed: missing block: B:81:0x0093, code lost:
    
        r0 = "e105";
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.j5.d(android.content.Context):void");
    }

    public void e(Context context) {
        cc b = ComponentLocator.a(context).s().b();
        if (b.a(7) == 5) {
            this.x = b.b(8);
            this.y = b.b(9);
            this.z = b.b(15);
            this.A = b.b(16);
        }
        int a2 = b.a(10);
        if (a2 != 0 && a2 != 2) {
            this.B = b.b(11);
            this.C = b.b(12);
        }
        this.D = b.b(4);
        this.E = b.b(3);
        this.F = b.b(5);
        this.G = b.b(1);
        this.H = b.b(2);
        this.J = b.b(13);
        this.u = b.a(14) == 1;
    }
}
