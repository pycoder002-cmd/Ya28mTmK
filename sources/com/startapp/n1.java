package com.startapp;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import com.startapp.networkTest.data.TimeInfo;
import com.startapp.networkTest.enums.TimeSources;
import com.startapp.networkTest.threads.ThreadManager;
import java.util.Date;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class n1 {
    private static final String a = "n1";
    private static final String b = "0.de.pool.ntp.org";
    private static final long c = 28800000;
    private static final int d = 10000;
    private static final int e = 30000;
    private long i;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private long j = -1;
    private long k = -1;
    private long l = -1;
    private long m = -1;
    private m1 n = new m1();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends AsyncTask<Void, Void, Void> {
        public a() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            try {
                String unused = n1.a;
                if (n1.this.n.a(n1.b, 10000)) {
                    long a = n1.this.n.a();
                    if (a > 1458564533202L && a < 3468524400000L) {
                        n1.this.j = SystemClock.elapsedRealtime();
                        n1.this.k = a;
                        String unused2 = n1.a;
                        new Date(n1.this.k).toString();
                        n1.this.g = true;
                    }
                } else {
                    String unused3 = n1.a;
                    n1.this.i = SystemClock.elapsedRealtime();
                }
                return null;
            } catch (Throwable th) {
                h1.a(th);
                return null;
            }
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r2) {
            n1.this.f = false;
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            n1.this.f = true;
        }
    }

    public n1() {
        if (s.b().NTP_SYNC_ENABLED()) {
            g();
        }
    }

    private void b() {
        if (!s.b().NTP_SYNC_ENABLED() || this.f || SystemClock.elapsedRealtime() - this.i <= 30000) {
            return;
        }
        g();
    }

    private TimeInfo c() {
        long currentTimeMillis;
        TimeInfo timeInfo = new TimeInfo();
        boolean z = this.g;
        timeInfo.IsSynced = z || this.h;
        if (this.h && this.l > this.j) {
            currentTimeMillis = this.m + (SystemClock.elapsedRealtime() - this.l);
            timeInfo.DeviceDriftMillis = System.currentTimeMillis() - currentTimeMillis;
            timeInfo.MillisSinceLastSync = currentTimeMillis - this.m;
            timeInfo.TimeSource = TimeSources.GPS;
            if (SystemClock.elapsedRealtime() - this.j > c) {
                b();
            }
        } else if (z) {
            if (SystemClock.elapsedRealtime() - this.j > c) {
                b();
            }
            currentTimeMillis = this.k + (SystemClock.elapsedRealtime() - this.j);
            timeInfo.DeviceDriftMillis = System.currentTimeMillis() - currentTimeMillis;
            timeInfo.MillisSinceLastSync = currentTimeMillis - this.k;
            timeInfo.TimeSource = TimeSources.NTP;
        } else {
            b();
            currentTimeMillis = System.currentTimeMillis();
            timeInfo.TimeSource = TimeSources.Device;
        }
        timeInfo.setMillis(currentTimeMillis);
        return timeInfo;
    }

    public static long d() {
        return s.f().f();
    }

    public static TimeInfo e() {
        return s.f().c();
    }

    private long f() {
        if (this.h && this.l > this.j) {
            if (SystemClock.elapsedRealtime() - this.j > c) {
                b();
            }
            return this.m + (SystemClock.elapsedRealtime() - this.l);
        }
        if (!this.g) {
            b();
            return System.currentTimeMillis();
        }
        if (SystemClock.elapsedRealtime() - this.j > c) {
            b();
        }
        return this.k + (SystemClock.elapsedRealtime() - this.j);
    }

    private void g() {
        if (Build.VERSION.SDK_INT < 11) {
            new a().execute(new Void[0]);
        } else {
            new a().executeOnExecutor(ThreadManager.b().a(), new Void[0]);
        }
    }

    public void a(Location location) {
        this.m = location.getTime();
        this.l = SystemClock.elapsedRealtime();
        this.h = true;
    }
}
