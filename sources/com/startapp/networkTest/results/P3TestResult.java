package com.startapp.networkTest.results;

import com.startapp.b0;
import com.startapp.d0;
import com.startapp.f;
import com.startapp.f0;
import com.startapp.h0;
import com.startapp.networkTest.data.BatteryInfo;
import com.startapp.networkTest.data.LocationInfo;
import com.startapp.networkTest.data.RadioInfo;
import com.startapp.networkTest.data.TimeInfo;
import com.startapp.networkTest.data.WifiInfo;
import com.startapp.networkTest.enums.ConnectionTypes;
import com.startapp.networkTest.enums.IpVersions;
import com.startapp.networkTest.enums.MeasurementTypes;
import com.startapp.networkTest.enums.NetworkGenerations;
import com.startapp.networkTest.enums.SpeedtestEndStates;
import com.startapp.networkTest.results.speedtest.MeasurementPointBase;
import com.startapp.networkTest.speedtest.SpeedtestEngineError;
import com.startapp.w;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class P3TestResult extends BaseResult {
    public int AvgValue;

    @f(complex = true)
    public BatteryInfo BatteryInfoOnEnd;

    @f(complex = true)
    public BatteryInfo BatteryInfoOnStart;
    public String CampaignId;
    public long ConnectingTimeControlServer;
    public long ConnectingTimeTestServerControl;
    public long ConnectingTimeTestServerSockets;
    public String CustomerID;

    @f(complex = true)
    public b0 DeviceInfo;
    public String IMEI;
    public String IMSI;
    public IpVersions IpVersion;
    public int IsAppInForeground;

    @f(complex = true)
    public LocationInfo LocationInfoOnEnd;

    @f(complex = true)
    public LocationInfo LocationInfoOnStart;
    public int MaxValue;
    public MeasurementTypes MeasurementType;
    public int MedValue;

    @f(complex = true)
    public d0 MemoryInfoOnEnd;

    @f(complex = true)
    public d0 MemoryInfoOnStart;
    public String Meta;
    public int MinValue;

    @f(type = ArrayList.class, value = f0.class)
    public ArrayList<f0> QuestionAnswerList;
    public String QuestionnaireName;

    @f(complex = true)
    public RadioInfo RadioInfoOnEnd;

    @f(complex = true)
    public RadioInfo RadioInfoOnStart;
    public double RatShare2G;
    public double RatShare3G;
    public double RatShare4G;
    public double RatShare5G;
    public double RatShareUnknown;
    public double RatShareWiFi;
    public String SequenceID;
    public String Server;
    public boolean Success;
    public SpeedtestEndStates TestEndState;
    public SpeedtestEngineError TestErrorReason;

    @f(complex = true)
    public TimeInfo TimeInfoOnEnd;

    @f(complex = true)
    public TimeInfo TimeInfoOnStart;

    @f(complex = true)
    public h0 TrafficInfoOnEnd;

    @f(complex = true)
    public h0 TrafficInfoOnStart;

    @f(complex = true)
    public WifiInfo WifiInfoOnEnd;

    @f(complex = true)
    public WifiInfo WifiInfoOnStart;

    public P3TestResult(String str, String str2) {
        super(str, str2);
        this.Server = "";
        this.IpVersion = IpVersions.Unknown;
        this.MeasurementType = MeasurementTypes.Unknown;
        this.QuestionnaireName = "";
        this.TestEndState = SpeedtestEndStates.Unknown;
        this.TestErrorReason = SpeedtestEngineError.OK;
        this.ConnectingTimeControlServer = -1L;
        this.ConnectingTimeTestServerControl = -1L;
        this.ConnectingTimeTestServerSockets = -1L;
        this.IMSI = "";
        this.IMEI = "";
        this.Meta = "";
        this.CampaignId = "";
        this.CustomerID = "";
        this.SequenceID = "";
        this.IsAppInForeground = -1;
        this.TimeInfoOnStart = new TimeInfo();
        this.TimeInfoOnEnd = new TimeInfo();
        this.QuestionAnswerList = new ArrayList<>();
        this.BatteryInfoOnEnd = new BatteryInfo();
        this.BatteryInfoOnStart = new BatteryInfo();
        this.LocationInfoOnEnd = new LocationInfo();
        this.LocationInfoOnStart = new LocationInfo();
        this.MemoryInfoOnEnd = new d0();
        this.MemoryInfoOnStart = new d0();
        this.RadioInfoOnEnd = new RadioInfo();
        this.RadioInfoOnStart = new RadioInfo();
        this.TrafficInfoOnEnd = new h0();
        this.TrafficInfoOnStart = new h0();
        this.WifiInfoOnEnd = new WifiInfo();
        this.WifiInfoOnStart = new WifiInfo();
        this.DeviceInfo = new b0();
    }

    public void calcRatShare(ArrayList<? extends MeasurementPointBase> arrayList) {
        Iterator<? extends MeasurementPointBase> it = arrayList.iterator();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (it.hasNext()) {
            MeasurementPointBase next = it.next();
            ConnectionTypes connectionTypes = next.ConnectionType;
            if (connectionTypes != ConnectionTypes.Unknown) {
                if (connectionTypes == ConnectionTypes.Mobile) {
                    NetworkGenerations b = w.b(next.NetworkType);
                    if (next.NrState.equals("CONNECTED")) {
                        b = NetworkGenerations.Gen5;
                    }
                    int ordinal = b.ordinal();
                    if (ordinal == 0) {
                        i7++;
                    } else if (ordinal == 1) {
                        i6++;
                    } else if (ordinal == 2) {
                        i5++;
                    } else if (ordinal == 3) {
                        i4++;
                    }
                } else {
                    i3++;
                }
                i++;
            }
            i2++;
            i++;
        }
        if (i > 0) {
            double d = i;
            this.RatShare2G = i7 / d;
            this.RatShare3G = i6 / d;
            this.RatShare4G = i5 / d;
            this.RatShare5G = i4 / d;
            this.RatShareWiFi = i3 / d;
            this.RatShareUnknown = i2 / d;
        }
    }
}
