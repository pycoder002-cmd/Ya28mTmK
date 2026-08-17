package com.startapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.SystemClock;
import android.util.Pair;
import com.startapp.sdk.adsbase.remoteconfig.AnalyticsCategoryConfig;
import com.startapp.sdk.adsbase.remoteconfig.AnalyticsConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class v7 {
    public final s7 a;
    public final l9 b;
    public final Executor c;
    public final k9<Integer> d;
    public final j7 e;
    public final j9<p7, r7, u7, Runnable> f;
    public final k9<AnalyticsConfig> g;
    public final Map<String, Pair<r7, Long>> h = new HashMap();
    public final AtomicLong i = new AtomicLong();
    public final k9<Void> j = new a();
    public final Runnable k = new b();
    public final k9<Void> l = new c();
    public final u7 m = new d();
    public final i9<p7, Void> n = new g();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements k9<Void> {
        public a() {
        }

        @Override // com.startapp.k9
        public Void call() {
            try {
                v7 v7Var = v7.this;
                v7Var.b.execute(new x7(v7Var));
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                v7.this.b();
            } catch (Throwable unused) {
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements k9<Void> {
        public c() {
        }

        @Override // com.startapp.k9
        public Void call() {
            try {
                v7.this.a(0L);
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements u7 {
        public d() {
        }

        @Override // com.startapp.u7
        public void a(p7 p7Var, int i) {
            try {
                v7 v7Var = v7.this;
                v7Var.getClass();
                v7Var.b.execute(new w7(v7Var, p7Var, i, System.currentTimeMillis()));
            } catch (Throwable unused) {
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e implements Runnable {
        public final /* synthetic */ p7 a;
        public final /* synthetic */ r7 b;
        public final /* synthetic */ u7 c;

        public e(p7 p7Var, r7 r7Var, u7 u7Var) {
            this.a = p7Var;
            this.b = r7Var;
            this.c = u7Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i;
            v7 v7Var = v7.this;
            p7 p7Var = this.a;
            r7 r7Var = this.b;
            u7 u7Var = this.c;
            v7Var.getClass();
            try {
                i = v7Var.a.a(p7Var, r7Var) ? 2 : 3;
                if (u7Var == null) {
                    return;
                }
            } catch (Throwable unused) {
                if (u7Var == null) {
                    return;
                } else {
                    i = 0;
                }
            }
            u7Var.a(p7Var, i);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class f implements Runnable {
        public final /* synthetic */ p7 a;
        public final /* synthetic */ r7 b;
        public final /* synthetic */ u7 c;

        public f(long j, p7 p7Var, r7 r7Var, u7 u7Var) {
            this.a = p7Var;
            this.b = r7Var;
            this.c = u7Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            v7.this.a(this.a, this.b, this.c);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class g implements i9<p7, Void> {
        public g() {
        }

        @Override // com.startapp.i9
        public Void a(p7 p7Var) {
            p7 p7Var2 = p7Var;
            if (p7Var2 == null) {
                return null;
            }
            try {
                v7.this.a(p7Var2);
                return null;
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public v7(s7 s7Var, l9 l9Var, Executor executor, k9<Integer> k9Var, j7 j7Var, j9<p7, r7, u7, Runnable> j9Var, k9<AnalyticsConfig> k9Var2) {
        this.a = s7Var;
        this.b = l9Var;
        this.c = executor;
        this.d = k9Var;
        this.e = j7Var;
        this.f = j9Var;
        this.g = k9Var2;
    }

    public final r7 a(q7 q7Var) {
        r7 r7Var;
        Map<String, AnalyticsCategoryConfig> a2;
        AnalyticsCategoryConfig analyticsCategoryConfig;
        synchronized (this.h) {
            Pair<r7, Long> pair = this.h.get(q7Var.o);
            r7Var = (pair == null || SystemClock.uptimeMillis() >= ((Long) pair.second).longValue()) ? null : (r7) pair.first;
        }
        if (r7Var != null) {
            return r7Var;
        }
        AnalyticsConfig call = this.g.call();
        if (call != null && (a2 = call.a()) != null && (analyticsCategoryConfig = a2.get(q7Var.o)) != null) {
            r7Var = new r7(q7Var.p, analyticsCategoryConfig);
        }
        if (r7Var == null) {
            r7Var = q7Var.p;
        }
        synchronized (this.h) {
            this.h.put(q7Var.o, new Pair<>(r7Var, Long.valueOf(SystemClock.uptimeMillis() + 30000)));
        }
        return r7Var;
    }

    public void a() {
        ConnectivityManager.OnNetworkActiveListener onNetworkActiveListener;
        if (this.i.compareAndSet(0L, SystemClock.uptimeMillis())) {
            s7 s7Var = this.a;
            k9<Void> k9Var = this.l;
            synchronized (s7Var) {
                s7Var.d.add(k9Var);
            }
            j7 j7Var = this.e;
            k9<Void> k9Var2 = this.j;
            synchronized (j7Var) {
                if (!j7Var.d.contains(k9Var2)) {
                    j7Var.d.add(k9Var2);
                }
            }
            j7 j7Var2 = this.e;
            if (!j7Var2.e.getAndSet(true)) {
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) j7Var2.a.getSystemService("connectivity");
                    if (connectivityManager != null) {
                        int i = Build.VERSION.SDK_INT;
                        if (i >= 24 && ya.a(j7Var2.a, "android.permission.ACCESS_NETWORK_STATE")) {
                            ConnectivityManager.NetworkCallback networkCallback = j7Var2.b;
                            if (networkCallback != null) {
                                connectivityManager.registerDefaultNetworkCallback(networkCallback);
                            }
                        } else if (i >= 21 && (onNetworkActiveListener = j7Var2.c) != null) {
                            connectivityManager.addDefaultNetworkActiveListener(onNetworkActiveListener);
                        }
                    }
                } catch (Throwable th) {
                    p7.a(j7Var2.a, th);
                }
            }
            this.b.execute(new x7(this));
        }
    }

    public void a(long j) {
        if (j < 0) {
            j = 0;
        }
        this.b.a(this.k, j);
    }

    public void a(p7 p7Var) {
        r7 a2 = a(p7Var.a);
        long uptimeMillis = (this.i.get() + a2.f) - SystemClock.uptimeMillis();
        if (uptimeMillis > 0) {
            a(uptimeMillis);
            return;
        }
        s7 s7Var = this.a;
        long currentTimeMillis = System.currentTimeMillis();
        s7Var.getClass();
        long j = p7Var.b;
        s7.a(j, currentTimeMillis);
        SQLiteDatabase a3 = s7Var.a();
        a3.beginTransaction();
        try {
            int a4 = s7.a(a3, j);
            ContentValues contentValues = new ContentValues();
            contentValues.put("send", Long.valueOf(currentTimeMillis));
            contentValues.put("attempt", Integer.valueOf(a4 + 1));
            a3.update("events", contentValues, "rowid = ?", new String[]{String.valueOf(j)});
            a3.setTransactionSuccessful();
            a3.endTransaction();
            a(p7Var, a2, this.m);
        } catch (Throwable th) {
            a3.endTransaction();
            throw th;
        }
    }

    public void a(p7 p7Var, int i, long j) {
        if (i == 1) {
            s7 s7Var = this.a;
            s7Var.getClass();
            long j2 = p7Var.b;
            s7.a(j2, j);
            ContentValues contentValues = new ContentValues();
            contentValues.put("sendSuccess", Long.valueOf(j));
            s7Var.a().update("events", contentValues, "rowid = ?", new String[]{String.valueOf(j2)});
            a(0L);
            return;
        }
        AnalyticsConfig call = this.g.call();
        int max = call != null ? Math.max(1, call.f()) : 1;
        s7 s7Var2 = this.a;
        s7Var2.getClass();
        long j3 = p7Var.b;
        s7.a(j3, j);
        SQLiteDatabase a2 = s7Var2.a();
        a2.beginTransaction();
        try {
            if (s7.a(a2, j3) >= max) {
                a2.delete("events", "rowid = ?", new String[]{String.valueOf(j3)});
            } else {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("sendFailure", Long.valueOf(j));
                a2.update("events", contentValues2, "rowid = ?", new String[]{String.valueOf(j3)});
            }
            a2.setTransactionSuccessful();
            a2.endTransaction();
            AnalyticsConfig call2 = this.g.call();
            a(call2 != null ? Math.max(1000L, call2.g()) : 1000L);
        } catch (Throwable th) {
            a2.endTransaction();
            throw th;
        }
    }

    public void a(p7 p7Var, r7 r7Var, u7 u7Var) {
        kb kbVar = (kb) this.f;
        kbVar.getClass();
        z7 z7Var = (p7Var == null || r7Var == null) ? null : new z7(kbVar.a.b, p7Var, r7Var, u7Var);
        if (z7Var != null) {
            this.c.execute(z7Var);
        } else if (u7Var != null) {
            u7Var.a(p7Var, 0);
        }
    }

    public void a(p7 p7Var, u7 u7Var) {
        AnalyticsConfig call = this.g.call();
        if (call == null || call.dns) {
            if (u7Var != null) {
                u7Var.a(p7Var, 3);
                return;
            }
            return;
        }
        r7 a2 = a(p7Var.a);
        if (Math.random() >= a2.a) {
            if (u7Var != null) {
                u7Var.a(p7Var, 3);
            }
        } else {
            if (a2.d) {
                this.b.execute(new e(p7Var, a2, u7Var));
                return;
            }
            if (!this.e.a()) {
                if (u7Var != null) {
                    u7Var.a(p7Var, 3);
                }
            } else {
                long uptimeMillis = (this.i.get() + a2.f) - SystemClock.uptimeMillis();
                if (uptimeMillis > 0) {
                    this.b.a(new f(uptimeMillis, p7Var, a2, u7Var), uptimeMillis);
                } else {
                    a(p7Var, a2, u7Var);
                }
            }
        }
    }

    public void b() {
        this.b.a(this.k);
        if (!this.e.a()) {
            AnalyticsConfig call = this.g.call();
            a(call != null ? Math.max(300000L, aa.e(call.b())) : 300000L);
            return;
        }
        Integer call2 = this.d.call();
        int max = call2 != null ? Math.max(1, call2.intValue()) : 1;
        AnalyticsConfig call3 = this.g.call();
        try {
            this.a.a(this.n, call3 != null ? Math.max(1, call3.f()) : 1, max);
        } catch (Throwable unused) {
        }
    }
}
