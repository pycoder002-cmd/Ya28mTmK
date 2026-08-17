package com.startapp.networkTest.results;

import com.startapp.b0;
import com.startapp.d0;
import com.startapp.e0;
import com.startapp.f;
import com.startapp.g0;
import com.startapp.h0;
import com.startapp.n0;
import com.startapp.networkTest.data.BatteryInfo;
import com.startapp.networkTest.data.IspInfo;
import com.startapp.networkTest.data.LocationInfo;
import com.startapp.networkTest.data.RadioInfo;
import com.startapp.networkTest.data.TimeInfo;
import com.startapp.networkTest.data.WifiInfo;
import com.startapp.networkTest.data.radio.ApnInfo;
import com.startapp.networkTest.data.radio.CellInfo;
import com.startapp.networkTest.data.radio.NetworkRegistrationInfo;
import com.startapp.networkTest.enums.CtTestTypes;
import com.startapp.networkTest.enums.FileTypes;
import com.startapp.networkTest.enums.IdleStates;
import com.startapp.networkTest.enums.NetworkTypes;
import com.startapp.networkTest.enums.ScreenStates;
import com.startapp.networkTest.enums.voice.CallStates;
import com.startapp.v1;
import java.util.ArrayList;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ConnectivityTestResult extends BaseResult {
    public String AirportCode;
    public String AmazonId;

    @f(type = ArrayList.class, value = ApnInfo.class)
    public ArrayList<ApnInfo> ApnInfo;

    @f(complex = true)
    public BatteryInfo BatteryInfo;
    public long BytesRead;
    public CallStates CallState;

    @f(type = ArrayList.class, value = CellInfo.class)
    public ArrayList<CellInfo> CellInfo;
    public String CtId;

    @f(complex = true)
    public b0 DeviceInfo;
    public long DurationDNS;
    public long DurationHttpGetCommand;
    public long DurationHttpReceive;
    public long DurationOverall;
    public long DurationOverallNoSleep;
    public long DurationSSL;
    public long DurationTcpConnect;
    public String ErrorReason;
    public int HTTPStatus;
    public long HeaderBytesRead;
    public IdleStates IdleStateOnEnd;
    public IdleStates IdleStateOnStart;
    public int IsAppInForeground;
    public boolean IsKeepAlive;

    @f(complex = true)
    public IspInfo IspInfo;
    public boolean LocalhostPingSuccess;

    @f(complex = true)
    public LocationInfo LocationInfo;

    @f(complex = true)
    public d0 MemoryInfo;

    @f(type = ArrayList.class, value = e0.class)
    public ArrayList<e0> MultiCdnInfo;

    @f(type = ArrayList.class, value = NetworkRegistrationInfo.class)
    public ArrayList<NetworkRegistrationInfo> NetworkRegistrationInfo;

    @f(complex = true)
    public RadioInfo RadioInfo;

    @f(complex = true)
    public RadioInfo RadioInfoOnEnd;
    public ScreenStates ScreenState;
    public String ServerFilename;
    public String ServerHostname;
    public String ServerIp;
    public long ServerMultiSuccess;

    @f(complex = true)
    public n0 SimInfo;
    public String SslException;

    @f(complex = true)
    public g0 StorageInfo;
    public boolean Success;
    public String TestTimestamp;
    public CtTestTypes TestType;

    @f(complex = true)
    public TimeInfo TimeInfo;

    @f(complex = true)
    public h0 TrafficInfo;
    public long TruststoreTimestamp;
    public NetworkTypes VoiceNetworkType;

    @f(complex = true)
    public WifiInfo WifiInfo;

    public ConnectivityTestResult(String str, String str2) {
        super(str, str2);
        this.CtId = "";
        this.TestTimestamp = "";
        this.DurationDNS = -1L;
        this.DurationTcpConnect = -1L;
        this.DurationHttpGetCommand = -1L;
        this.DurationHttpReceive = -1L;
        this.DurationSSL = -1L;
        this.DurationOverall = -1L;
        this.DurationOverallNoSleep = -1L;
        this.ServerIp = "";
        this.Success = false;
        this.LocalhostPingSuccess = false;
        this.IsKeepAlive = false;
        this.ServerHostname = "";
        this.ServerFilename = "";
        this.BytesRead = -1L;
        this.HeaderBytesRead = -1L;
        this.HTTPStatus = -1;
        this.AmazonId = "";
        this.TestType = CtTestTypes.Unknown;
        this.ScreenState = ScreenStates.Unknown;
        IdleStates idleStates = IdleStates.Unknown;
        this.IdleStateOnStart = idleStates;
        this.IdleStateOnEnd = idleStates;
        this.ErrorReason = "";
        this.SslException = "";
        this.CallState = CallStates.Unknown;
        this.VoiceNetworkType = NetworkTypes.Unknown;
        this.ServerMultiSuccess = -1L;
        this.AirportCode = "";
        this.IsAppInForeground = -1;
        this.BatteryInfo = new BatteryInfo();
        this.DeviceInfo = new b0();
        this.LocationInfo = new LocationInfo();
        this.MemoryInfo = new d0();
        this.RadioInfo = new RadioInfo();
        this.RadioInfoOnEnd = new RadioInfo();
        this.StorageInfo = new g0();
        this.TrafficInfo = new h0();
        this.WifiInfo = new WifiInfo();
        this.TimeInfo = new TimeInfo();
        this.IspInfo = new IspInfo();
        this.SimInfo = new n0();
        this.MultiCdnInfo = new ArrayList<>();
        this.CellInfo = new ArrayList<>();
        this.ApnInfo = new ArrayList<>();
        this.NetworkRegistrationInfo = new ArrayList<>();
    }

    public String toJson() {
        return v1.a(FileTypes.CT, this);
    }
}
