package com.startapp;

import android.os.AsyncTask;
import android.os.Build;
import com.startapp.networkTest.data.IspInfo;
import com.startapp.networkTest.data.WifiInfo;
import com.startapp.networkTest.net.WebApiClient;
import com.startapp.networkTest.threads.ThreadManager;
import java.util.HashMap;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class o0 {
    private static final String a = "o0";
    private static final String b = "ispinfo";
    private static final String c = "anonymize";
    private static o0 d;
    private boolean e = false;
    private boolean f = false;
    private HashMap<String, IspInfo> g = new HashMap<>();
    private IspInfo h;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends AsyncTask<WifiInfo, Void, IspInfo> {
        public a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IspInfo doInBackground(WifiInfo... wifiInfoArr) {
            return o0.this.a(wifiInfoArr[0]);
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(IspInfo ispInfo) {
            super.onPostExecute(ispInfo);
            o0.this.e = false;
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            o0.this.e = true;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b extends AsyncTask<Void, Void, IspInfo> {
        public b() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IspInfo doInBackground(Void... voidArr) {
            return o0.this.a(null);
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(IspInfo ispInfo) {
            super.onPostExecute(ispInfo);
            o0.this.f = false;
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            super.onPreExecute();
            o0.this.f = true;
        }
    }

    private o0() {
    }

    public static o0 a() {
        if (d == null) {
            d = new o0();
        }
        return d;
    }

    public IspInfo a(WifiInfo wifiInfo) {
        return b(wifiInfo, true);
    }

    public IspInfo a(WifiInfo wifiInfo, boolean z) {
        IspInfo ispInfo;
        synchronized (this.g) {
            ispInfo = this.g.get(wifiInfo.WifiBSSID_Full);
        }
        if (ispInfo != null) {
            return ispInfo;
        }
        if (z && !this.e) {
            if (Build.VERSION.SDK_INT < 11) {
                new a().execute(wifiInfo);
            } else {
                new a().executeOnExecutor(ThreadManager.b().a(), wifiInfo);
            }
        }
        return new IspInfo();
    }

    public IspInfo a(boolean z, boolean z2) {
        IspInfo ispInfo = this.h;
        if ((z && !this.f && ispInfo == null) || (z && !this.f && z2)) {
            if (Build.VERSION.SDK_INT < 11) {
                new b().execute(new Void[0]);
            } else {
                new b().executeOnExecutor(ThreadManager.b().a(), new Void[0]);
            }
        }
        return ispInfo == null ? new IspInfo() : ispInfo;
    }

    public IspInfo b(WifiInfo wifiInfo, boolean z) {
        String str;
        p0 p0Var;
        IspInfo ispInfo = new IspInfo();
        try {
            if (z) {
                str = s.b().GEOIP_URL() + b;
            } else {
                str = s.b().GEOIP_URL() + b + "?" + c + "=false";
            }
            y0 a2 = WebApiClient.a(WebApiClient.RequestMethod.GET, str);
            if (a2.b.length() > 0 && (p0Var = (p0) v1.a(a2.b, p0.class)) != null) {
                ispInfo.AutonomousSystemNumber = b2.a(p0Var.AutonomousSystemNumber);
                ispInfo.AutonomousSystemOrganization = b2.a(p0Var.AutonomousSystemOrganization);
                ispInfo.IpAddress = b2.a(p0Var.IpAddress);
                ispInfo.IspName = b2.a(p0Var.IspName);
                ispInfo.IspOrganizationalName = b2.a(p0Var.IspOrganizationalName);
                ispInfo.SuccessfulIspLookup = true;
                if (wifiInfo != null) {
                    synchronized (this.g) {
                        this.g.put(wifiInfo.WifiBSSID_Full, ispInfo);
                    }
                } else {
                    this.h = ispInfo;
                }
            }
        } catch (Throwable th) {
            h1.a(th);
        }
        return ispInfo;
    }
}
