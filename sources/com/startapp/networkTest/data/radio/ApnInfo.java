package com.startapp.networkTest.data.radio;

import com.startapp.networkTest.enums.NetworkTypes;
import com.startapp.networkTest.enums.wifi.WifiDetailedStates;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ApnInfo implements Cloneable, Serializable {
    public String Apn = "";
    public String ApnTypes = "";
    public String Reason = "";
    public String Capabilities = "";
    public String PcscfAddresses = "";
    public int SamsungSipError = -1;
    public String SamsungImsServices = "";
    public WifiDetailedStates MobileDataConnectionState = WifiDetailedStates.Unknown;
    public int SubscriptionId = -1;
    public long TxBytes = -1;
    public long RxBytes = -1;
    public NetworkTypes NetworkType = NetworkTypes.Unknown;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
