package com.startapp;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class t1 {
    private static final int a = 6371000;

    public static double a(double d, double d2, double d3, double d4) {
        double radians = Math.toRadians(d4 - d2) * Math.cos(Math.toRadians(d + d3) / 2.0d);
        double radians2 = Math.toRadians(d3 - d);
        return Math.sqrt((radians * radians) + (radians2 * radians2)) * 6371000.0d;
    }

    public static double b(double d, double d2, double d3, double d4) {
        double radians = Math.toRadians(d3 - d);
        double d5 = radians / 2.0d;
        double radians2 = Math.toRadians(d4 - d2) / 2.0d;
        double sin = (Math.sin(d5) * Math.sin(d5)) + (Math.cos(Math.toRadians(d)) * Math.cos(Math.toRadians(d3)) * Math.sin(radians2) * Math.sin(radians2));
        return Math.atan2(Math.sqrt(sin), Math.sqrt(1.0d - sin)) * 2.0d * 6371000.0d;
    }
}
