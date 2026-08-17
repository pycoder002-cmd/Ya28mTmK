package com.startapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class p6 {
    public Context a;
    public oa b;
    public n6 c = new n6();
    public BluetoothAdapter d = a();
    public BroadcastReceiver e;

    public p6(Context context, oa oaVar) {
        this.a = context;
        this.b = oaVar;
    }

    public final BluetoothAdapter a() {
        if (ya.a(this.a, "android.permission.BLUETOOTH")) {
            return BluetoothAdapter.getDefaultAdapter();
        }
        return null;
    }

    public void a(boolean z) {
        BluetoothAdapter bluetoothAdapter = this.d;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            this.b.a(null);
            return;
        }
        n6 n6Var = this.c;
        Set<BluetoothDevice> hashSet = new HashSet<>();
        try {
            if (ya.a(this.a, "android.permission.BLUETOOTH") && this.d.isEnabled()) {
                hashSet = this.d.getBondedDevices();
            }
        } catch (Throwable th) {
            p7.a(this.a, th);
        }
        n6Var.a = hashSet;
        if (!z || !ya.a(this.a, "android.permission.BLUETOOTH_ADMIN")) {
            this.b.a(b());
            return;
        }
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.device.action.FOUND");
        o6 o6Var = new o6(this);
        this.e = o6Var;
        try {
            this.a.registerReceiver(o6Var, intentFilter);
            this.d.startDiscovery();
        } catch (Exception e) {
            this.d.cancelDiscovery();
            this.b.a(b());
            p7.a(this.a, e);
        }
    }

    public JSONObject b() {
        try {
            n6 n6Var = this.c;
            n6Var.getClass();
            JSONObject jSONObject = new JSONObject();
            try {
                Set<BluetoothDevice> set = n6Var.a;
                if (set != null && set.size() > 0) {
                    jSONObject.put("paired", n6Var.a(n6Var.a));
                }
                Set<BluetoothDevice> set2 = n6Var.b;
                if (set2 != null && set2.size() > 0) {
                    jSONObject.put("available", n6Var.a(n6Var.b));
                }
            } catch (Exception unused) {
            }
            if (jSONObject.length() > 0) {
                return jSONObject;
            }
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public void c() {
        BluetoothAdapter bluetoothAdapter;
        if (!ya.a(this.a, "android.permission.BLUETOOTH_ADMIN") || this.e == null || (bluetoothAdapter = this.d) == null) {
            return;
        }
        bluetoothAdapter.cancelDiscovery();
        try {
            this.a.unregisterReceiver(this.e);
        } catch (Throwable th) {
            p7.a(this.a, th);
        }
        this.e = null;
    }
}
