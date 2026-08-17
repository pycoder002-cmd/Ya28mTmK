package com.startapp.networkTest.data;

import com.startapp.e2;
import com.startapp.networkTest.enums.TimeSources;
import com.startapp.s1;
import java.io.Serializable;
import java.util.TimeZone;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class TimeInfo implements Cloneable, Serializable {
    private static final long serialVersionUID = 3793653153982296400L;
    public long DeviceDriftMillis;
    public boolean IsSynced;
    public long MillisSinceLastSync;
    public long TimestampMillis;
    public double TimestampOffset;
    public transient int day;
    public transient int hour;
    public transient int millisecond;
    public transient int minute;
    public transient int month;
    public transient int second;
    public transient int year;
    public String TimestampTableau = "";
    public String TimestampDateTime = "";
    public TimeSources TimeSource = TimeSources.Unknown;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void setMillis(long j) {
        this.TimestampTableau = s1.b(j);
        this.TimestampDateTime = s1.a(j);
        this.TimestampOffset = ((TimeZone.getDefault().getOffset(j) / 1000.0f) / 60.0f) / 60.0f;
        this.TimestampMillis = j;
        e2 c = s1.c(j);
        this.year = c.a;
        this.month = c.b;
        this.day = c.c;
        this.hour = c.d;
        this.minute = c.e;
        this.second = c.f;
        this.millisecond = c.g;
    }
}
