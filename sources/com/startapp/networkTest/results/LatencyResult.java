package com.startapp.networkTest.results;

import com.startapp.f;
import com.startapp.g1;
import com.startapp.networkTest.enums.ScreenStates;
import com.startapp.networkTest.results.speedtest.MeasurementPointLatency;
import java.util.ArrayList;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class LatencyResult extends P3TestResult {
    public String AirportCode;
    public long DurationOverall;
    public long DurationOverallNoSleep;
    public double Jitter;
    public String LtrId;

    @f(type = ArrayList.class, value = MeasurementPointLatency.class)
    public ArrayList<MeasurementPointLatency> MeasurementPoints;
    public int Pause;
    public int Pings;
    public ScreenStates ScreenStateOnEnd;
    public ScreenStates ScreenStateOnStart;
    public int SuccessfulPings;

    public LatencyResult(String str, String str2) {
        super(str, str2);
        this.LtrId = "";
        this.DurationOverall = -1L;
        this.DurationOverallNoSleep = -1L;
        ScreenStates screenStates = ScreenStates.Unknown;
        this.ScreenStateOnStart = screenStates;
        this.ScreenStateOnEnd = screenStates;
        this.AirportCode = "";
        this.MeasurementPoints = new ArrayList<>();
    }

    public void calculateStats(ArrayList<MeasurementPointLatency> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).Rtt != -1) {
                arrayList2.add(Integer.valueOf(arrayList.get(i).Rtt));
            }
        }
        this.MinValue = g1.e(arrayList2);
        this.MaxValue = g1.c(arrayList2);
        this.AvgValue = g1.a(arrayList2);
        this.MedValue = g1.d(arrayList2);
        this.Jitter = g1.b(arrayList2);
        this.MeasurementPoints = arrayList;
    }
}
