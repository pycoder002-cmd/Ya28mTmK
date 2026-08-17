package com.startapp;

import android.content.Context;
import android.support.v4.view.InputDeviceCompat;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyCallback;
import android.telephony.TelephonyManager;
import com.startapp.p5;
import com.startapp.sdk.adsbase.remoteconfig.TelephonyDataConfig;
import com.startapp.sdk.adsbase.remoteconfig.TelephonyMetadata;
import io.sentry.marshaller.json.JsonMarshaller;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class fc {
    public final Context a;
    public final l9 b;
    public final p5 c;
    public final k9<TelephonyMetadata> d;
    public c e;
    public final double f = Math.random();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements c {
        public final TelephonyCallback a;

        public a() {
            this.a = new b();
        }

        @Override // com.startapp.fc.c
        public void a(TelephonyManager telephonyManager) {
            telephonyManager.registerTelephonyCallback(fc.this.b, this.a);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b extends TelephonyCallback implements TelephonyCallback.ServiceStateListener, TelephonyCallback.SignalStrengthsListener {
        public b() {
        }

        @Override // android.telephony.TelephonyCallback.ServiceStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            fc.this.a(ServiceState.class, serviceState);
        }

        @Override // android.telephony.TelephonyCallback.SignalStrengthsListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            fc.this.a(SignalStrength.class, signalStrength);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface c {
        void a(TelephonyManager telephonyManager);
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements c {
        public final PhoneStateListener a = new a();

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class a extends PhoneStateListener {
            public a() {
            }

            @Override // android.telephony.PhoneStateListener
            public void onServiceStateChanged(ServiceState serviceState) {
                fc.this.a(ServiceState.class, serviceState);
            }

            @Override // android.telephony.PhoneStateListener
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                fc.this.a(SignalStrength.class, signalStrength);
            }
        }

        public d() {
        }

        @Override // com.startapp.fc.c
        public void a(TelephonyManager telephonyManager) {
            telephonyManager.listen(this.a, InputDeviceCompat.SOURCE_KEYBOARD);
        }
    }

    public fc(Context context, l9 l9Var, p5 p5Var, k9<TelephonyMetadata> k9Var) {
        this.a = context;
        this.b = l9Var;
        this.c = p5Var;
        this.d = k9Var;
    }

    public final TelephonyMetadata a() {
        TelephonyMetadata call = this.d.call();
        if (call == null || !call.c()) {
            return null;
        }
        return call;
    }

    public Map<String, String> a(q7 q7Var) {
        List<String> a2;
        TelephonyMetadata a3 = a();
        if (a3 == null) {
            return Collections.emptyMap();
        }
        HashMap hashMap = null;
        for (Map.Entry<String, ?> entry : this.c.getAll().entrySet()) {
            Object value = entry.getValue();
            if (value instanceof String) {
                String key = entry.getKey();
                TelephonyDataConfig a4 = a3.a(key);
                if (a4.c() && (a2 = a4.a()) != null && a2.contains(q7Var.o)) {
                    String b2 = a4.b();
                    if (b2 != null) {
                        key = b2;
                    }
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    hashMap.put(key, (String) value);
                }
            }
        }
        return hashMap == null ? Collections.emptyMap() : hashMap;
    }

    public <T> void a(Class<T> cls, T t) {
        TelephonyMetadata a2 = a();
        if (a2 == null || t == null) {
            return;
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            String simpleName = cls.getSimpleName();
            if (a2.a(simpleName).c()) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(JsonMarshaller.TIMESTAMP, currentTimeMillis);
                jSONObject.put("type", simpleName);
                jSONObject.put("data", t.toString());
                String c2 = aa.c(jSONObject.toString());
                p5.a edit = this.c.edit();
                edit.a(simpleName, c2);
                edit.a.putString(simpleName, c2);
                edit.apply();
            }
        } catch (Throwable th) {
            if (a(2)) {
                p7.a(this.a, th);
            }
        }
    }

    public boolean a(int i) {
        TelephonyMetadata a2 = a();
        return a2 != null && this.f < a2.b() && (a2.a() & i) == i;
    }
}
