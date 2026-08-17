package com.startapp;

import android.bluetooth.BluetoothDevice;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class n6 {
    public Set<BluetoothDevice> a;
    public Set<BluetoothDevice> b;

    public final JSONArray a(Set<BluetoothDevice> set) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (BluetoothDevice bluetoothDevice : set) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("bluetoothClass", bluetoothDevice.getBluetoothClass().getDeviceClass());
                jSONObject.put("name", bluetoothDevice.getName());
                jSONObject.put("mac", bluetoothDevice.getAddress());
                jSONObject.put("bondState", bluetoothDevice.getBondState());
                jSONArray.put(jSONObject);
            }
            return jSONArray;
        } catch (Exception unused) {
            return null;
        }
    }
}
