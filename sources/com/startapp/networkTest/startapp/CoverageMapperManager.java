package com.startapp.networkTest.startapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import android.telephony.CellLocation;
import android.telephony.ServiceState;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.startapp.h1;
import com.startapp.networkTest.controller.LocationController;
import com.startapp.networkTest.data.LocationInfo;
import com.startapp.networkTest.enums.LocationProviders;
import com.startapp.networkTest.enums.TriggerEvents;
import com.startapp.networkTest.results.NetworkInformationResult;
import com.startapp.networkTest.threads.ThreadManager;
import com.startapp.s0;
import com.startapp.y;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class CoverageMapperManager implements LocationController.c, y {
    private static final String a = "CoverageMapperManager";
    private static final long b = 1;
    private static final long c = 10000;
    private static final long d = 10000;
    private static final int e = 500;
    private static final int f = 10000;
    private static final int g = 1000;
    private static final int h = 1000;
    private static final int i = 1000;
    private static final int j = 2000;
    private Context k;
    private s0 l;
    private long n;
    private ScheduledFuture<?> o;
    private ScheduledFuture<?> p;
    private ScheduledFuture<?> q;
    private long r;
    private long s;
    private long u;
    private long w;
    private OnNetworkInfoResultListener x;
    private boolean m = false;
    private int t = -1;
    private int v = -1;
    private Runnable y = new b();
    private Runnable z = new c();
    private Runnable A = new d();
    private BroadcastReceiver B = new e();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface OnNetworkInfoResultListener {
        void onNetworkInfoResult(NetworkInformationResult networkInformationResult);
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ LocationInfo a;

        public a(LocationInfo locationInfo) {
            this.a = locationInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            CoverageMapperManager.this.a(this.a, TriggerEvents.LocationUpdateGps, false);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CoverageMapperManager.this.a(null, TriggerEvents.OutOfService, true);
            if (CoverageMapperManager.this.r + 10000 < SystemClock.elapsedRealtime()) {
                CoverageMapperManager.this.o.cancel(false);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            CoverageMapperManager.this.a(null, TriggerEvents.CellIdChange, true);
            if (CoverageMapperManager.this.u + 1 < SystemClock.elapsedRealtime()) {
                CoverageMapperManager.this.p.cancel(false);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (SystemClock.elapsedRealtime() > CoverageMapperManager.this.n + 2000) {
                CoverageMapperManager.this.a(null, TriggerEvents.Foreground, false);
            }
            if (CoverageMapperManager.this.w + 10000 < SystemClock.elapsedRealtime()) {
                CoverageMapperManager.this.q.cancel(false);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e extends BroadcastReceiver {
        public e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            CoverageMapperManager.this.s = SystemClock.elapsedRealtime();
        }
    }

    public CoverageMapperManager(Context context) {
        this.l = new s0(context);
        this.k = context;
    }

    private void a() {
        this.u = SystemClock.elapsedRealtime();
        ScheduledFuture<?> scheduledFuture = this.p;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            this.p = ThreadManager.b().d().scheduleWithFixedDelay(this.z, 0L, 1000L, TimeUnit.MILLISECONDS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(LocationInfo locationInfo, TriggerEvents triggerEvents, boolean z) {
        NetworkInformationResult a2 = locationInfo == null ? this.l.a(triggerEvents, z) : this.l.a(locationInfo, triggerEvents, z);
        OnNetworkInfoResultListener onNetworkInfoResultListener = this.x;
        if (onNetworkInfoResultListener != null) {
            onNetworkInfoResultListener.onNetworkInfoResult(a2);
        }
    }

    private void d() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.s + 10000 > elapsedRealtime) {
            return;
        }
        this.r = elapsedRealtime;
        ScheduledFuture<?> scheduledFuture = this.o;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            this.o = ThreadManager.b().d().scheduleWithFixedDelay(this.y, 0L, 1000L, TimeUnit.MILLISECONDS);
        }
    }

    @Override // com.startapp.y
    public void a(CellLocation cellLocation, int i2) {
        if (this.l.b().b().DefaultDataSimId != i2 || cellLocation == null) {
            return;
        }
        int cid = cellLocation.getClass().equals(GsmCellLocation.class) ? ((GsmCellLocation) cellLocation).getCid() : cellLocation.getClass().equals(CdmaCellLocation.class) ? ((CdmaCellLocation) cellLocation).getBaseStationId() : -1;
        int i3 = this.v;
        if (cid != i3 && i3 != -1 && cid > 0 && cid != Integer.MAX_VALUE) {
            this.v = cid;
            a();
        } else {
            if (cid <= 0 || cid >= Integer.MAX_VALUE) {
                return;
            }
            this.v = cid;
        }
    }

    @Override // com.startapp.y
    public void a(ServiceState serviceState, int i2) {
        if (this.l.b().b().DefaultDataSimId == i2) {
            int state = serviceState.getState();
            if (state == 1 && this.t == 0) {
                d();
            }
            this.t = state;
        }
    }

    @Override // com.startapp.networkTest.controller.LocationController.c
    public void a(LocationInfo locationInfo) {
        if (locationInfo.LocationProvider == LocationProviders.Gps) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime < this.n + 500) {
                return;
            }
            this.n = elapsedRealtime;
            ThreadManager.b().a().execute(new a(locationInfo));
        }
    }

    public void a(OnNetworkInfoResultListener onNetworkInfoResultListener) {
        this.x = onNetworkInfoResultListener;
    }

    public void b() {
        if (this.m) {
            return;
        }
        this.m = true;
        this.l.a((y) this);
        this.l.a((LocationController.c) this);
        this.l.e();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        intentFilter.addAction("android.intent.action.ACTION_SHUTDOWN");
        this.k.registerReceiver(this.B, intentFilter);
    }

    public void c() {
        this.w = SystemClock.elapsedRealtime();
        ScheduledFuture<?> scheduledFuture = this.q;
        if (scheduledFuture == null || scheduledFuture.isDone()) {
            this.q = ThreadManager.b().d().scheduleWithFixedDelay(this.A, 0L, 1000L, TimeUnit.MILLISECONDS);
        }
    }

    public void e() {
        if (this.m) {
            this.l.b(this);
            this.l.c();
            this.l.f();
            try {
                this.k.unregisterReceiver(this.B);
            } catch (Throwable th) {
                h1.a(th);
            }
            this.m = false;
        }
    }

    public void f() {
        ScheduledFuture<?> scheduledFuture = this.q;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }
}
