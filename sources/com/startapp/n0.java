package com.startapp;

import com.startapp.networkTest.enums.SimStates;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class n0 implements Cloneable {
    public String CarrierName = "";
    public String CountryIso = "";
    public boolean DataRoaming = false;
    public int Mcc = -1;
    public int Mnc = -1;
    public int SimSlotIndex = -1;
    public int SubscriptionId = -1;
    public String IccId = "";
    public String IMSI = "";
    public String GroupIdentifierLevel1 = "";
    public SimStates SimState = SimStates.Unknown;
    public String Apn = "";
    public String ApnTypes = "";

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
