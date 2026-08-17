package com.startapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Handler;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.search.SearchAuth;
import com.startapp.p5;
import com.startapp.sdk.adsbase.remoteconfig.MotionMetadata;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class xc {
    public static final AtomicInteger a = new AtomicInteger();
    public final Context b;
    public final p5 c;
    public final k9<MotionMetadata> d;
    public final Handler e;
    public zc f;
    public boolean g;
    public int h;
    public Sensor i;
    public final SensorEventListener j = new a();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements SensorEventListener {
        public a() {
        }

        @Override // android.hardware.SensorEventListener
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @Override // android.hardware.SensorEventListener
        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                xc xcVar = xc.this;
                zc zcVar = xcVar.f;
                if (zcVar != null) {
                    if (zcVar != null && zcVar.b.offer(sensorEvent)) {
                        return;
                    }
                    xcVar.a(8, null);
                }
            } catch (OutOfMemoryError unused) {
                xc xcVar2 = xc.this;
                xcVar2.getClass();
                try {
                    xcVar2.e();
                } catch (Throwable th) {
                    p7.a(xcVar2.b, th);
                }
            } catch (Throwable th2) {
                xc.this.a(16, th2);
            }
        }
    }

    public xc(Context context, p5 p5Var, k9<MotionMetadata> k9Var, Handler handler) {
        this.b = context;
        this.c = p5Var;
        this.d = k9Var;
        this.e = handler;
    }

    public final MotionMetadata a() {
        MotionMetadata call = this.d.call();
        if (call == null || !call.u()) {
            return null;
        }
        return call;
    }

    public final void a(int i, Throwable th) {
        if (a(i)) {
            int i2 = this.h;
            if ((i2 & i) != 0) {
                return;
            }
            this.h = i2 | i;
            if (th != null) {
                p7.a(this.b, th);
                return;
            }
            p7 p7Var = new p7(q7.c);
            p7Var.d = "MP";
            p7Var.e = String.valueOf(i);
            p7Var.a(this.b);
        }
    }

    public final boolean a(int i) {
        MotionMetadata a2;
        return (!this.g || (a2 = a()) == null || (i & a2.j()) == 0) ? false : true;
    }

    public double b() {
        MotionMetadata a2 = a();
        if (a2 == null) {
            return -1.0d;
        }
        zc zcVar = this.f;
        return zcVar != null ? Double.longBitsToDouble(zcVar.d.get()) : this.c.getFloat("e9142de3c7cc5952", 0.0f) * g.a(System.currentTimeMillis(), this.c.getLong("7783513af1730383", 0L), a2.b(), a2.a(), a2.c(), d.a(Utils.DOUBLE_EPSILON, a2.a(), a2.c()));
    }

    public final void c() {
        if (this.f != null) {
            p5.a edit = this.c.edit();
            float longBitsToDouble = (float) Double.longBitsToDouble(this.f.e.get());
            edit.a("e9142de3c7cc5952", (String) Float.valueOf(longBitsToDouble));
            edit.a.putFloat("e9142de3c7cc5952", longBitsToDouble);
            long j = this.f.f.get();
            edit.a("7783513af1730383", (String) Long.valueOf(j));
            edit.a.putLong("7783513af1730383", j);
            edit.apply();
            if (a(4)) {
                p7 p7Var = new p7(q7.b);
                p7Var.d = "MP.save";
                p7Var.e = String.format(Locale.ENGLISH, "%.6f", Double.valueOf(b()));
                p7Var.a(this.b);
            }
        }
    }

    public final void d() {
        SensorManager sensorManager;
        MotionMetadata a2 = a();
        if (a2 != null && (sensorManager = (SensorManager) this.b.getSystemService("sensor")) != null && Build.VERSION.SDK_INT >= 9 && this.i == null) {
            Sensor defaultSensor = sensorManager.getDefaultSensor(1);
            int min = Math.min(Math.max(SearchAuth.StatusCodes.AUTH_DISABLED, (int) (aa.e(a2.r()) * 1000)), 100000);
            if (defaultSensor == null || !sensorManager.registerListener(this.j, defaultSensor, min)) {
                return;
            }
            this.i = defaultSensor;
            double d = this.c.getFloat("e9142de3c7cc5952", 0.0f);
            long j = this.c.getLong("7783513af1730383", 0L);
            zc zcVar = this.f;
            if (zcVar != null) {
                zcVar.interrupt();
                this.f = null;
            }
            if (this.f == null) {
                zc zcVar2 = new zc("startapp-mp-" + a.incrementAndGet(), this.b, a2, a2.q(), d, j);
                this.f = zcVar2;
                zcVar2.start();
            }
            if (a(1)) {
                p7 p7Var = new p7(q7.b);
                p7Var.d = "MP.start";
                p7Var.e = defaultSensor.getName() + "," + defaultSensor.getMinDelay() + "," + defaultSensor.getPower();
                p7Var.a(this.b);
            }
        }
    }

    public final void e() {
        Sensor sensor;
        SensorManager sensorManager = (SensorManager) this.b.getSystemService("sensor");
        if (sensorManager == null || (sensor = this.i) == null) {
            return;
        }
        sensorManager.unregisterListener(this.j, sensor);
        this.i = null;
        c();
        zc zcVar = this.f;
        if (zcVar != null) {
            zcVar.interrupt();
            this.f = null;
        }
        if (a(2)) {
            p7 p7Var = new p7(q7.b);
            p7Var.d = "MP.stop";
            p7Var.a(this.b);
        }
    }
}
