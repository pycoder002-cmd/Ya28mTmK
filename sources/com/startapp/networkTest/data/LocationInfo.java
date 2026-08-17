package com.startapp.networkTest.data;

import com.startapp.networkTest.enums.LocationProviders;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class LocationInfo implements Cloneable, Serializable {
    private static final long serialVersionUID = -937846764579533362L;
    public double LocationAccuracyHorizontal;
    public double LocationAccuracyVertical;
    public double LocationAltitude;
    public double LocationBearing;
    public double LocationLatitude;
    public double LocationLongitude;
    public double LocationSpeed;
    public transient long locationTimestampMillis;
    public LocationProviders LocationProvider = LocationProviders.Unknown;
    public String LocationTimestamp = "";
    public long LocationAge = -1;
    public int IsMocked = -1;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
