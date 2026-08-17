package com.startapp;

import com.startapp.networkTest.enums.bluetooth.BluetoothConnectionState;
import java.util.ArrayList;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class k0 {
    public BluetoothConnectionState A2DPConnectionState;
    public boolean BluetoothEnabled;

    @f(type = ArrayList.class, value = j0.class)
    public ArrayList<j0> ConnectedA2DPBluetoothDevices;

    @f(type = ArrayList.class, value = j0.class)
    public ArrayList<j0> ConnectedHeadsetBluetoothDevices;

    @f(type = ArrayList.class, value = j0.class)
    public ArrayList<j0> ConnectedHealthBluetoothDevices;
    public BluetoothConnectionState HeadsetConnectionState;
    public BluetoothConnectionState HealthConnectionState;
    public boolean MissingPermission;

    @f(type = ArrayList.class, value = j0.class)
    public ArrayList<j0> PairedBluetoothDevices;

    public k0() {
        BluetoothConnectionState bluetoothConnectionState = BluetoothConnectionState.Unknown;
        this.HealthConnectionState = bluetoothConnectionState;
        this.HeadsetConnectionState = bluetoothConnectionState;
        this.A2DPConnectionState = bluetoothConnectionState;
        this.MissingPermission = false;
        this.PairedBluetoothDevices = new ArrayList<>();
        this.ConnectedA2DPBluetoothDevices = new ArrayList<>();
        this.ConnectedHealthBluetoothDevices = new ArrayList<>();
        this.ConnectedHeadsetBluetoothDevices = new ArrayList<>();
    }
}
