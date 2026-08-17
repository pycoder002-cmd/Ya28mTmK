package com.startapp;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import com.startapp.sdk.adsbase.remoteconfig.BaseSensorConfig;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.SensorsConfig;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class w8 {
    public SensorManager c;
    public oa d;
    public HashMap<Integer, b> a = null;
    public SensorEventListener f = new a();
    public v8 b = new v8();
    public int e = 0;

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
            int size;
            JSONArray jSONArray;
            v8 v8Var = w8.this.b;
            synchronized (v8Var) {
                int type = sensorEvent.sensor.getType();
                SensorEvent sensorEvent2 = v8Var.a.get(Integer.valueOf(type));
                if (sensorEvent2 == null || sensorEvent2.accuracy <= sensorEvent.accuracy) {
                    v8Var.a.put(Integer.valueOf(type), sensorEvent);
                }
                size = v8Var.a.size();
            }
            w8 w8Var = w8.this;
            if (size == w8Var.e) {
                w8Var.c.unregisterListener(w8Var.f);
                w8 w8Var2 = w8.this;
                oa oaVar = w8Var2.d;
                if (oaVar != null) {
                    try {
                        jSONArray = w8Var2.b.a();
                    } catch (Exception unused) {
                        jSONArray = null;
                    }
                    oaVar.a(jSONArray);
                }
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b {
        public int a;
        public int b;

        public b(w8 w8Var, int i, int i2) {
            this.a = i;
            this.b = i2;
        }
    }

    public w8(Context context, oa oaVar) {
        this.c = (SensorManager) context.getSystemService("sensor");
        this.d = oaVar;
        a();
    }

    public final void a() {
        this.a = new HashMap<>();
        SensorsConfig B = MetaData.h.B();
        a(13, B.a());
        a(9, B.b());
        a(5, B.d());
        a(10, B.e());
        a(2, B.f());
        a(6, B.g());
        a(12, B.i());
        a(11, B.j());
        a(16, B.c());
    }

    public final void a(int i, BaseSensorConfig baseSensorConfig) {
        if (baseSensorConfig.c()) {
            this.a.put(Integer.valueOf(i), new b(this, baseSensorConfig.b(), baseSensorConfig.a()));
        }
    }

    public void b() {
        Sensor defaultSensor;
        Iterator<Integer> it = this.a.keySet().iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            b bVar = this.a.get(Integer.valueOf(intValue));
            if (Build.VERSION.SDK_INT >= bVar.a && (defaultSensor = this.c.getDefaultSensor(intValue)) != null) {
                this.c.registerListener(this.f, defaultSensor, bVar.b);
                this.e++;
            }
        }
    }
}
