package com.startapp;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.HashSet;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class o6 extends BroadcastReceiver {
    public final /* synthetic */ p6 a;

    public o6(p6 p6Var) {
        this.a = p6Var;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.bluetooth.device.action.FOUND".equals(action)) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            n6 n6Var = this.a.c;
            if (n6Var.b == null) {
                n6Var.b = new HashSet();
            }
            n6Var.b.add(bluetoothDevice);
            return;
        }
        if ("android.bluetooth.adapter.action.DISCOVERY_FINISHED".equals(action)) {
            this.a.c();
            p6 p6Var = this.a;
            p6Var.b.a(p6Var.b());
        }
    }
}
