package com.startapp.sdk.common.advertisingid;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import com.startapp.k9;
import com.startapp.p7;
import com.startapp.pa;
import com.startapp.q7;
import com.startapp.qa;
import com.startapp.sdk.adsbase.remoteconfig.AdvertisingIdResolverMetadata;
import com.startapp.ya;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdvertisingIdResolver {
    public final Context a;
    public final ThreadFactory b;
    public final k9<AdvertisingIdResolverMetadata> c;
    public final Lock d;
    public final Condition e;
    public final AtomicReference<pa> f;
    public final AtomicInteger g;
    public final double h;
    public int i;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class InternalException extends Exception {
        public final int infoEventFlags;

        public InternalException(int i) {
            super(String.valueOf(i));
            this.infoEventFlags = i;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            pa paVar;
            boolean z = false;
            try {
                AdvertisingIdResolver.this.d.lock();
                z = true;
                AdvertisingIdResolver advertisingIdResolver = AdvertisingIdResolver.this;
                AtomicReference<pa> atomicReference = advertisingIdResolver.f;
                Context context = advertisingIdResolver.a;
                advertisingIdResolver.getClass();
                try {
                    paVar = AdvertisingIdResolver.a(context);
                } catch (InternalException e) {
                    advertisingIdResolver.b(e.infoEventFlags);
                    try {
                        paVar = AdvertisingIdResolver.b(context);
                    } catch (InternalException e2) {
                        advertisingIdResolver.b(e2.infoEventFlags);
                        paVar = pa.a;
                        atomicReference.set(paVar);
                        AdvertisingIdResolver.this.g.set(2);
                        AdvertisingIdResolver.this.e.signalAll();
                        AdvertisingIdResolver.this.d.unlock();
                    } catch (Throwable th) {
                        if (advertisingIdResolver.a(256)) {
                            p7.a(context, th);
                        }
                        paVar = pa.a;
                        atomicReference.set(paVar);
                        AdvertisingIdResolver.this.g.set(2);
                        AdvertisingIdResolver.this.e.signalAll();
                        AdvertisingIdResolver.this.d.unlock();
                    }
                    atomicReference.set(paVar);
                    AdvertisingIdResolver.this.g.set(2);
                    AdvertisingIdResolver.this.e.signalAll();
                    AdvertisingIdResolver.this.d.unlock();
                } catch (Throwable th2) {
                    if (advertisingIdResolver.a(128)) {
                        p7.a(context, th2);
                    }
                    paVar = AdvertisingIdResolver.b(context);
                    atomicReference.set(paVar);
                    AdvertisingIdResolver.this.g.set(2);
                    AdvertisingIdResolver.this.e.signalAll();
                    AdvertisingIdResolver.this.d.unlock();
                }
                atomicReference.set(paVar);
                AdvertisingIdResolver.this.g.set(2);
                AdvertisingIdResolver.this.e.signalAll();
                AdvertisingIdResolver.this.d.unlock();
            } catch (Throwable th3) {
                try {
                    if (AdvertisingIdResolver.this.a(64)) {
                        p7.a(AdvertisingIdResolver.this.a, th3);
                    }
                } finally {
                    AdvertisingIdResolver.this.g.set(2);
                    if (z) {
                        AdvertisingIdResolver.this.e.signalAll();
                        AdvertisingIdResolver.this.d.unlock();
                    }
                }
            }
        }
    }

    public AdvertisingIdResolver(Context context, ThreadFactory threadFactory, k9<AdvertisingIdResolverMetadata> k9Var) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.d = reentrantLock;
        this.e = reentrantLock.newCondition();
        this.f = new AtomicReference<>();
        this.g = new AtomicInteger(0);
        this.h = Math.random();
        this.a = context;
        this.b = threadFactory;
        this.c = k9Var;
    }

    public static pa a(Context context) throws Exception {
        Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", Context.class).invoke(null, context);
        if (invoke == null) {
            throw new InternalException(512);
        }
        String str = (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
        if (str == null || str.length() < 1) {
            throw new InternalException(1024);
        }
        return new pa(str, "APP", Boolean.TRUE.equals((Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0])));
    }

    public static pa b(Context context) throws Exception {
        qa qaVar;
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            qaVar = new qa();
            try {
                if (!context.bindService(intent, qaVar, 1)) {
                    throw new InternalException(2048);
                }
                if (qaVar.b) {
                    throw new IllegalStateException("Binder already retrieved");
                }
                IBinder take = qaVar.a.take();
                if (take == null) {
                    throw new IllegalStateException("Binder is null");
                }
                qaVar.b = true;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                    take.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    if (readString == null) {
                        if (Build.VERSION.SDK_INT < 15) {
                            throw new RemoteException();
                        }
                        throw new RemoteException("Receive null from remote service");
                    }
                    obtain = Parcel.obtain();
                    obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                        obtain.writeInt(1);
                        take.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        boolean z = obtain2.readInt() != 0;
                        obtain2.recycle();
                        obtain.recycle();
                        pa paVar = new pa(readString, "DEVICE", z);
                        ya.a(context, qaVar);
                        return paVar;
                    } finally {
                    }
                } finally {
                }
            } catch (Throwable th) {
                th = th;
                ya.a(context, qaVar);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            qaVar = null;
        }
    }

    public pa a() {
        pa paVar = this.f.get();
        if (paVar != null) {
            return paVar;
        }
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            b(1);
            return pa.a;
        }
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (!this.d.tryLock(1000L, TimeUnit.MILLISECONDS)) {
                b(8);
                return pa.a;
            }
            try {
                b();
                while (this.g.get() != 2) {
                    long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
                    if (elapsedRealtime2 > 1000) {
                        b(2);
                        return pa.a;
                    }
                    this.e.await(1000 - elapsedRealtime2, TimeUnit.MILLISECONDS);
                }
                pa paVar2 = this.f.get();
                if (paVar2 == null) {
                    b(4);
                    paVar2 = pa.a;
                }
                this.d.unlock();
                return paVar2;
            } finally {
                this.d.unlock();
            }
        } catch (Throwable th) {
            if (a(32)) {
                p7.a(this.a, th);
            }
            return pa.a;
        }
    }

    public boolean a(int i) {
        AdvertisingIdResolverMetadata call = this.c.call();
        if (call == null || !call.c()) {
            call = null;
        }
        return call != null && this.h < call.b() && (call.a() & i) == i;
    }

    public final void b() {
        if (this.g.get() == 0) {
            this.b.newThread(new a()).start();
            this.g.set(1);
        }
    }

    public final void b(int i) {
        if (a(i)) {
            int i2 = this.i;
            if ((i2 & i) == i) {
                return;
            }
            this.i = i2 | i;
            p7 p7Var = new p7(q7.c);
            p7Var.d = "AIR";
            p7Var.e = String.valueOf(i);
            p7Var.a(this.a);
        }
    }
}
