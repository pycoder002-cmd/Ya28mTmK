package com.startapp.networkTest.controller;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import com.startapp.h1;
import com.startapp.n1;
import com.startapp.networkTest.data.LocationInfo;
import com.startapp.networkTest.enums.LocationProviders;
import com.startapp.s;
import com.startapp.s1;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class LocationController {
    private static double a = 0.0d;
    private static double b = 0.0d;
    private static final String c = "LocationController";
    private static final boolean d = false;
    private LocationManager e;
    private long f;
    private LocationInfo g;
    private Location h;
    private long i;
    private boolean l;
    private c m;
    private long k = 4000;
    private b j = new b(this, null);

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum ProviderMode {
        Passive,
        Network,
        Gps,
        GpsAndNetwork,
        RailNet
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            ProviderMode.values();
            int[] iArr = new int[5];
            a = iArr;
            try {
                iArr[ProviderMode.Gps.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[ProviderMode.GpsAndNetwork.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[ProviderMode.Network.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[ProviderMode.Passive.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements LocationListener {
        private b() {
        }

        public /* synthetic */ b(LocationController locationController, a aVar) {
            this();
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location == null || location.getProvider() == null) {
                return;
            }
            if (LocationController.this.h == null || location.getProvider().equals("gps") || LocationController.this.h.getProvider() == null || !LocationController.this.h.getProvider().equals("gps") || SystemClock.elapsedRealtime() - LocationController.this.f >= 5000) {
                LocationController.this.h = location;
                LocationController.this.i = SystemClock.elapsedRealtime();
                LocationController.this.g = LocationController.b(location);
                LocationController.this.g.LocationAge = 0L;
                LocationController.this.f = SystemClock.elapsedRealtime();
                if (LocationController.this.m != null) {
                    LocationController.this.m.a(LocationController.this.g);
                }
                if (location.getProvider().equals("gps")) {
                    s.f().a(location);
                }
            }
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface c {
        void a(LocationInfo locationInfo);
    }

    public LocationController(Context context) {
        this.e = (LocationManager) context.getSystemService("location");
    }

    public static double a() {
        return a;
    }

    public static double b() {
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static LocationInfo b(Location location) {
        LocationInfo locationInfo = new LocationInfo();
        locationInfo.LocationAccuracyHorizontal = location.getAccuracy();
        int i = Build.VERSION.SDK_INT;
        if (i < 26 || !location.hasVerticalAccuracy()) {
            locationInfo.LocationAccuracyVertical = location.getAccuracy();
        } else {
            locationInfo.LocationAccuracyVertical = location.getVerticalAccuracyMeters();
        }
        long d2 = n1.d();
        locationInfo.locationTimestampMillis = d2;
        locationInfo.LocationTimestamp = s1.b(d2);
        locationInfo.LocationAltitude = location.getAltitude();
        locationInfo.LocationBearing = location.getBearing();
        locationInfo.LocationLatitude = location.getLatitude();
        locationInfo.LocationLongitude = location.getLongitude();
        if (i >= 18) {
            locationInfo.IsMocked = location.isFromMockProvider() ? 1 : 0;
        }
        if (location.getProvider() == null) {
            locationInfo.LocationProvider = LocationProviders.Unknown;
        } else if (location.getProvider().equals("gps")) {
            locationInfo.LocationProvider = LocationProviders.Gps;
        } else if (location.getProvider().equals("network")) {
            locationInfo.LocationProvider = LocationProviders.Network;
        } else if (location.getProvider().equals("fused")) {
            locationInfo.LocationProvider = LocationProviders.Fused;
        } else {
            locationInfo.LocationProvider = LocationProviders.Unknown;
        }
        locationInfo.LocationSpeed = location.getSpeed();
        return locationInfo;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0030 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040 A[ADDED_TO_REGION, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d() {
        /*
            r10 = this;
            android.location.LocationManager r0 = r10.e
            java.util.List r0 = r0.getAllProviders()
            r1 = 0
            if (r0 == 0) goto L44
            int r2 = r0.size()
            if (r2 <= 0) goto L44
            r2 = 0
            r3 = r1
        L11:
            int r4 = r0.size()
            if (r2 >= r4) goto L43
            android.location.LocationManager r4 = r10.e     // Catch: java.lang.Throwable -> L24 java.lang.SecurityException -> L29
            java.lang.Object r5 = r0.get(r2)     // Catch: java.lang.Throwable -> L24 java.lang.SecurityException -> L29
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.Throwable -> L24 java.lang.SecurityException -> L29
            android.location.Location r4 = r4.getLastKnownLocation(r5)     // Catch: java.lang.Throwable -> L24 java.lang.SecurityException -> L29
            goto L2e
        L24:
            r4 = move-exception
            com.startapp.h1.a(r4)
            goto L2d
        L29:
            r4 = move-exception
            com.startapp.h1.b(r4)
        L2d:
            r4 = r1
        L2e:
            if (r4 == 0) goto L40
            if (r3 != 0) goto L33
            goto L3f
        L33:
            long r5 = r4.getTime()
            long r7 = r3.getTime()
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L40
        L3f:
            r3 = r4
        L40:
            int r2 = r2 + 1
            goto L11
        L43:
            r1 = r3
        L44:
            if (r1 == 0) goto L6f
            r10.h = r1
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 17
            if (r0 < r2) goto L59
            long r2 = r1.getElapsedRealtimeNanos()
            r4 = 1000000(0xf4240, double:4.940656E-318)
            long r2 = r2 / r4
            r10.i = r2
            goto L69
        L59:
            long r2 = android.os.SystemClock.elapsedRealtime()
            long r4 = java.lang.System.currentTimeMillis()
            long r6 = r1.getTime()
            long r4 = r4 - r6
            long r2 = r2 + r4
            r10.i = r2
        L69:
            com.startapp.networkTest.data.LocationInfo r0 = b(r1)
            r10.g = r0
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.networkTest.controller.LocationController.d():void");
    }

    public void a(long j) {
        this.k = j;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0059 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x001a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.startapp.networkTest.controller.LocationController.ProviderMode r13) {
        /*
            Method dump skipped, instructions count: 224
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.networkTest.controller.LocationController.a(com.startapp.networkTest.controller.LocationController$ProviderMode):void");
    }

    public void a(c cVar) {
        this.m = cVar;
    }

    public LocationInfo c() {
        if (this.g == null) {
            d();
        }
        if (this.g == null) {
            LocationInfo locationInfo = new LocationInfo();
            this.g = locationInfo;
            locationInfo.LocationProvider = LocationProviders.Unknown;
        }
        LocationInfo locationInfo2 = this.g;
        if (locationInfo2.LocationProvider != LocationProviders.Unknown) {
            locationInfo2.LocationAge = SystemClock.elapsedRealtime() - this.i;
        }
        LocationInfo locationInfo3 = this.g;
        a = locationInfo3.LocationLatitude;
        b = locationInfo3.LocationLongitude;
        try {
            return (LocationInfo) locationInfo3.clone();
        } catch (Throwable th) {
            h1.a(th);
            return this.g;
        }
    }

    public long e() {
        return this.k;
    }

    public void f() {
        b bVar;
        LocationManager locationManager = this.e;
        if (locationManager != null && (bVar = this.j) != null) {
            try {
                locationManager.removeUpdates(bVar);
            } catch (Throwable th) {
                h1.a(th);
            }
        }
        this.l = false;
    }
}
