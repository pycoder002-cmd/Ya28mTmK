package com.startapp;

import com.startapp.networkTest.enums.Os;
import com.startapp.networkTest.enums.PhoneTypes;
import com.startapp.networkTest.enums.SimStates;
import com.startapp.networkTest.enums.ThreeState;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class b0 implements Cloneable {
    public long DeviceUpTime;
    public boolean IsRooted;
    public String DeviceManufacturer = "";
    public String DeviceName = "";
    public String SimOperator = "";
    public String SimOperatorName = "";
    public SimStates SimState = SimStates.Unknown;
    public Os OS = Os.Android;
    public String OSVersion = "";
    public String TAC = "";
    public String BuildFingerprint = "";
    public String OsSystemVersion = "";
    public String UserLocal = "";
    public int PhoneCount = -1;
    public ThreeState PowerSaveMode = ThreeState.Unknown;
    public PhoneTypes PhoneType = PhoneTypes.Unknown;

    @f(complex = true)
    public k0 BluetoothInfo = new k0();

    @f(complex = true)
    public m0 MultiSimInfo = new m0();

    @f(complex = true)
    public l0 HostAppInfo = new l0();

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
