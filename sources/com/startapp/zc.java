package com.startapp;

import android.content.Context;
import android.hardware.SensorEvent;
import com.github.mikephil.charting.utils.Utils;
import com.startapp.sdk.adsbase.remoteconfig.MotionMetadata;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class zc extends Thread {
    public final Context a;
    public final BlockingDeque<SensorEvent> b;
    public final k c;
    public final AtomicLong d;
    public final AtomicLong e;
    public final AtomicLong f;

    public zc(String str, Context context, MotionMetadata motionMetadata, int i, double d, long j) {
        super(str);
        this.d = new AtomicLong(Double.doubleToRawLongBits(Utils.DOUBLE_EPSILON));
        this.e = new AtomicLong(Double.doubleToRawLongBits(Utils.DOUBLE_EPSILON));
        this.f = new AtomicLong(0L);
        this.a = context;
        k kVar = new k(motionMetadata.f(), motionMetadata.g(), motionMetadata.h(), motionMetadata.i(), motionMetadata.s(), motionMetadata.t(), motionMetadata.d(), motionMetadata.e(), motionMetadata.b(), motionMetadata.a(), motionMetadata.c(), motionMetadata.o(), motionMetadata.p(), motionMetadata.m(), motionMetadata.l(), motionMetadata.n());
        this.c = kVar;
        kVar.a(d, j);
        this.b = new LinkedBlockingDeque(i);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        while (true) {
            try {
                SensorEvent take = this.b.take();
                if (take == null) {
                    return;
                }
                k kVar = this.c;
                long currentTimeMillis = System.currentTimeMillis();
                long j = take.timestamp;
                float[] fArr = take.values;
                kVar.a(currentTimeMillis, j, fArr[0], fArr[1], fArr[2]);
                this.d.set(Double.doubleToRawLongBits(this.c.k.i));
                this.e.set(Double.doubleToRawLongBits(this.c.k.g));
                this.f.set(this.c.k.h);
            } catch (InterruptedException unused) {
                return;
            } catch (Throwable th) {
                p7.a(this.a, th);
                return;
            }
        }
    }
}
