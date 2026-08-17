package com.startapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.github.mikephil.charting.utils.Utils;
import com.startapp.networkTest.controller.LocationController;
import com.startapp.networkTest.data.LocationInfo;
import com.startapp.networkTest.data.RadioInfo;
import com.startapp.networkTest.data.TimeInfo;
import com.startapp.networkTest.enums.NetworkTypes;
import com.startapp.networkTest.enums.TriggerEvents;
import com.startapp.networkTest.enums.voice.CallStates;
import com.startapp.networkTest.results.NetworkInformationResult;
import java.util.ArrayList;
import java.util.Arrays;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class s0 {
    private static final String a = "s0";
    private static final boolean b = false;
    private static final int c = 30000;
    private static final String d = "p3insnir";
    private static final String e = "P3INS_PFK_NIR_FIRSTCELLID_LATITUDE";
    private static final String f = "P3INS_PFK_NIR_FIRSTCELLID_LONGITUDE";
    private static final String g = "P3INS_PFK_NIR_FIRSTCELLID_GSMCELLID";
    private SharedPreferences h;
    private Context i;
    private t j;
    private w l;
    private x m;
    private LocationController n;
    private TelephonyManager o;
    private b p;
    private c q;
    private int s;
    private boolean t;
    private int r = 0;
    private String k = s.b().PROJECT_ID();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b {
        public String a;
        public double b;
        public double c;

        public b(String str, double d, double d2) {
            this.a = str;
            this.b = d;
            this.c = d2;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c {
        public String a;
        public String b;
        public String c;
        public NetworkTypes d;
        public String e;
        public String f;
        public int g;

        private c() {
            this.a = "";
            this.b = "";
            this.c = "";
            this.d = NetworkTypes.Unknown;
            this.e = "";
            this.f = "";
        }

        public void a(String str, String str2, String str3, NetworkTypes networkTypes, String str4, String str5, int i) {
            this.b = str;
            this.a = str2;
            this.c = str3;
            this.d = networkTypes;
            this.e = str4;
            this.f = str5;
            this.g = i;
        }
    }

    public s0(Context context) {
        int i;
        this.i = context;
        this.j = new t(context);
        this.h = context.getSharedPreferences(d, 0);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        this.o = telephonyManager;
        if (Build.VERSION.SDK_INT >= 24 && telephonyManager != null && (i = v.i(context).SubscriptionId) != -1) {
            this.o = this.o.createForSubscriptionId(i);
        }
        this.l = new w(this.i);
        this.m = new x(this.i);
        this.n = new LocationController(this.i);
        this.q = new c();
        this.t = s.b().NIR_COLLECT_CELLINFO();
        int NIR_COLLECT_CELLINFO_THRESHOLD = s.b().NIR_COLLECT_CELLINFO_THRESHOLD();
        this.s = NIR_COLLECT_CELLINFO_THRESHOLD;
        if (NIR_COLLECT_CELLINFO_THRESHOLD <= 0) {
            this.s = 1;
        }
    }

    private CallStates a() {
        TelephonyManager telephonyManager = this.o;
        if (telephonyManager == null) {
            return CallStates.Unknown;
        }
        int callState = telephonyManager.getCallState();
        return callState != 0 ? callState != 1 ? callState != 2 ? CallStates.Unknown : CallStates.Offhook : CallStates.Ringing : CallStates.Idle;
    }

    private void a(b bVar) {
        this.h.edit().putString(g, bVar.a).commit();
        this.h.edit().putLong(e, Double.doubleToRawLongBits(bVar.b)).commit();
        this.h.edit().putLong(f, Double.doubleToRawLongBits(bVar.c)).commit();
    }

    private void d() {
        String string = this.h.getString(g, "");
        if (string.isEmpty()) {
            return;
        }
        this.p = new b(string, Double.longBitsToDouble(this.h.getLong(e, 0L)), Double.longBitsToDouble(this.h.getLong(f, 0L)));
    }

    public NetworkInformationResult a(LocationInfo locationInfo, TriggerEvents triggerEvents, boolean z) {
        b bVar;
        String str;
        b bVar2;
        b bVar3;
        b bVar4;
        NetworkInformationResult networkInformationResult = new NetworkInformationResult(this.k, this.j.p());
        if (locationInfo != null) {
            networkInformationResult.LocationInfo = locationInfo;
        } else {
            networkInformationResult.LocationInfo = this.n.c();
        }
        TimeInfo e2 = n1.e();
        networkInformationResult.TimeInfo = e2;
        networkInformationResult.Timestamp = e2.TimestampTableau;
        networkInformationResult.timestampMillis = e2.TimestampMillis;
        networkInformationResult.NirId = u1.a(e2, networkInformationResult.GUID);
        networkInformationResult.WifiInfo = this.m.c();
        networkInformationResult.TriggerEvent = triggerEvents;
        networkInformationResult.ScreenState = v.h(this.i);
        networkInformationResult.CallState = a();
        if (this.t) {
            int i = this.r;
            this.r = i + 1;
            if (i % this.s == 0 || z) {
                networkInformationResult.CellInfo = new ArrayList<>(Arrays.asList(this.l.c()));
            }
        }
        networkInformationResult.RadioInfo = this.l.h();
        String str2 = "";
        synchronized (this) {
            if (this.p == null) {
                d();
            }
            if (!networkInformationResult.RadioInfo.GsmCellId.isEmpty()) {
                if (networkInformationResult.LocationInfo.LocationAge < 30000 && ((bVar4 = this.p) == null || !bVar4.a.equals(networkInformationResult.RadioInfo.GsmCellId))) {
                    String str3 = networkInformationResult.RadioInfo.GsmCellId;
                    LocationInfo locationInfo2 = networkInformationResult.LocationInfo;
                    b bVar5 = new b(str3, locationInfo2.LocationLatitude, locationInfo2.LocationLongitude);
                    this.p = bVar5;
                    networkInformationResult.CellIdDeltaDistance = Utils.DOUBLE_EPSILON;
                    a(bVar5);
                }
                str2 = networkInformationResult.RadioInfo.GsmCellId;
            } else if (!networkInformationResult.RadioInfo.CdmaBaseStationId.isEmpty()) {
                if (networkInformationResult.LocationInfo.LocationAge < 30000 && ((bVar = this.p) == null || !bVar.a.equals(networkInformationResult.RadioInfo.CdmaBaseStationId))) {
                    String str4 = networkInformationResult.RadioInfo.CdmaBaseStationId;
                    LocationInfo locationInfo3 = networkInformationResult.LocationInfo;
                    b bVar6 = new b(str4, locationInfo3.LocationLatitude, locationInfo3.LocationLongitude);
                    this.p = bVar6;
                    networkInformationResult.CellIdDeltaDistance = Utils.DOUBLE_EPSILON;
                    a(bVar6);
                }
                str2 = networkInformationResult.RadioInfo.CdmaBaseStationId;
            }
            str = str2;
        }
        if ((!networkInformationResult.RadioInfo.GsmCellId.isEmpty() && networkInformationResult.CellIdDeltaDistance == -1.0d && (bVar3 = this.p) != null && bVar3.a.equals(networkInformationResult.RadioInfo.GsmCellId)) || (!networkInformationResult.RadioInfo.CdmaBaseStationId.isEmpty() && networkInformationResult.CellIdDeltaDistance == -1.0d && (bVar2 = this.p) != null && bVar2.a.equals(networkInformationResult.RadioInfo.CdmaBaseStationId))) {
            b bVar7 = this.p;
            double d2 = bVar7.b;
            double d3 = bVar7.c;
            LocationInfo locationInfo4 = networkInformationResult.LocationInfo;
            networkInformationResult.CellIdDeltaDistance = t1.a(d2, d3, locationInfo4.LocationLatitude, locationInfo4.LocationLongitude);
        }
        if (!str.isEmpty() && !str.equals(this.q.a)) {
            c cVar = this.q;
            networkInformationResult.PrevNirId = cVar.b;
            networkInformationResult.PrevCellId = cVar.a;
            networkInformationResult.PrevLAC = cVar.c;
            networkInformationResult.PrevNetworkType = cVar.d;
            networkInformationResult.PrevMCC = cVar.e;
            networkInformationResult.PrevMNC = cVar.f;
            networkInformationResult.PrevRXLevel = cVar.g;
        }
        c cVar2 = this.q;
        String str5 = networkInformationResult.NirId;
        RadioInfo radioInfo = networkInformationResult.RadioInfo;
        cVar2.a(str5, str, radioInfo.GsmLAC, radioInfo.NetworkType, radioInfo.MCC, radioInfo.MNC, radioInfo.RXLevel);
        return networkInformationResult;
    }

    public NetworkInformationResult a(TriggerEvents triggerEvents, boolean z) {
        return a(this.n.c(), triggerEvents, z);
    }

    public void a(LocationController.c cVar) {
        LocationController locationController = this.n;
        if (locationController != null) {
            locationController.a(cVar);
        }
    }

    public void a(y yVar) {
        w wVar = this.l;
        if (wVar != null) {
            wVar.a(yVar);
        }
    }

    public w b() {
        return this.l;
    }

    public void b(y yVar) {
        w wVar = this.l;
        if (wVar != null) {
            wVar.b(yVar);
        }
    }

    public void c() {
        LocationController locationController = this.n;
        if (locationController != null) {
            locationController.a((LocationController.c) null);
        }
    }

    public void e() {
        this.n.a(LocationController.ProviderMode.Passive);
        this.l.x();
        this.m.f();
    }

    public void f() {
        this.n.f();
        this.l.y();
        this.m.g();
    }
}
