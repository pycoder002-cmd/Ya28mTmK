package com.startapp.networkTest.results.speedtest;

import com.startapp.networkTest.enums.ConnectionTypes;
import com.startapp.networkTest.enums.NetworkTypes;
import com.startapp.networkTest.enums.ThreeStateShort;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class MeasurementPointBase implements Cloneable {
    public long Delta;
    public int RxLev;
    public ConnectionTypes ConnectionType = ConnectionTypes.Unknown;
    public NetworkTypes NetworkType = NetworkTypes.Unknown;
    public String NrState = "Unknown";
    public ThreeStateShort NrAvailable = ThreeStateShort.Unknown;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
