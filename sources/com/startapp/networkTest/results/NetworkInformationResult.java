package com.startapp.networkTest.results;

import com.startapp.f;
import com.startapp.networkTest.data.LocationInfo;
import com.startapp.networkTest.data.RadioInfo;
import com.startapp.networkTest.data.TimeInfo;
import com.startapp.networkTest.data.WifiInfo;
import com.startapp.networkTest.data.radio.CellInfo;
import com.startapp.networkTest.enums.NetworkTypes;
import com.startapp.networkTest.enums.ScreenStates;
import com.startapp.networkTest.enums.TriggerEvents;
import com.startapp.networkTest.enums.voice.CallStates;
import java.util.ArrayList;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class NetworkInformationResult extends BaseResult {
    public CallStates CallState;
    public double CellIdDeltaDistance;

    @f(type = ArrayList.class, value = CellInfo.class)
    public ArrayList<CellInfo> CellInfo;

    @f(complex = true)
    public LocationInfo LocationInfo;
    public String NirId;
    public String PrevCellId;
    public String PrevLAC;
    public String PrevMCC;
    public String PrevMNC;
    public NetworkTypes PrevNetworkType;
    public String PrevNirId;
    public int PrevRXLevel;

    @f(complex = true)
    public RadioInfo RadioInfo;
    public ScreenStates ScreenState;

    @f(complex = true)
    public TimeInfo TimeInfo;
    public String Timestamp;
    public TriggerEvents TriggerEvent;

    @f(complex = true)
    public WifiInfo WifiInfo;
    public transient long timestampMillis;

    public NetworkInformationResult(String str, String str2) {
        super(str, str2);
        this.NirId = "";
        this.Timestamp = "";
        this.TriggerEvent = TriggerEvents.Unknown;
        this.ScreenState = ScreenStates.Unknown;
        this.CallState = CallStates.Unknown;
        this.CellIdDeltaDistance = -1.0d;
        this.PrevNirId = "";
        this.PrevCellId = "";
        this.PrevLAC = "";
        this.PrevMCC = "";
        this.PrevMNC = "";
        this.PrevNetworkType = NetworkTypes.Unknown;
        this.RadioInfo = new RadioInfo();
        this.LocationInfo = new LocationInfo();
        this.TimeInfo = new TimeInfo();
        this.WifiInfo = new WifiInfo();
        this.CellInfo = new ArrayList<>();
    }
}
