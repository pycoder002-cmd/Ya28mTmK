package com.startapp;

import com.github.mikephil.charting.utils.Utils;
import com.startapp.networkTest.data.RadioInfo;
import com.startapp.networkTest.results.speedtest.MeasurementPointLatency;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class g1 {
    public static int a(ArrayList<Integer> arrayList, int i) {
        int size = arrayList.size();
        if (size == 0) {
            return 0;
        }
        if (size == 1) {
            return arrayList.get(0).intValue();
        }
        int i2 = size - 1;
        double d = (i / 100.0d) * i2;
        int i3 = (int) d;
        double intValue = arrayList.get(i3).intValue();
        double d2 = d - i3;
        return (int) ((i3 == i2 || d2 == Utils.DOUBLE_EPSILON) ? Math.round(intValue) : Math.round(intValue + (d2 * (arrayList.get(i3 + 1).intValue() - intValue))));
    }

    public static int a(List<Integer> list) {
        if (list.size() == 0) {
            return 0;
        }
        if (list.size() == 1) {
            return list.get(0).intValue();
        }
        long j = 0;
        for (int i = 0; i < list.size(); i++) {
            j += list.get(i).intValue();
        }
        return Math.round((float) (j / list.size()));
    }

    public static MeasurementPointLatency a(long j, RadioInfo radioInfo, long j2) {
        MeasurementPointLatency measurementPointLatency = new MeasurementPointLatency();
        measurementPointLatency.Rtt = (int) j;
        measurementPointLatency.ConnectionType = radioInfo.ConnectionType;
        measurementPointLatency.NetworkType = radioInfo.NetworkType;
        measurementPointLatency.RxLev = radioInfo.RXLevel;
        measurementPointLatency.Delta = j2;
        return measurementPointLatency;
    }

    public static double b(List<Integer> list) {
        long j = 0;
        for (int i = 0; i < list.size(); i++) {
            j += list.get(i).intValue();
        }
        double size = j / list.size();
        double d = 0.0d;
        for (int i2 = 0; i2 < list.size(); i2++) {
            d += Math.pow(list.get(i2).intValue() - size, 2.0d);
        }
        double sqrt = Math.sqrt(d / list.size());
        return Double.isNaN(sqrt) ? Utils.DOUBLE_EPSILON : sqrt;
    }

    public static void b(ArrayList<?> arrayList, int i) {
        arrayList.ensureCapacity(i);
        while (arrayList.size() < i) {
            arrayList.add(null);
        }
    }

    public static int c(List<Integer> list) {
        if (list.size() == 0) {
            return 0;
        }
        if (list.size() == 1) {
            return list.get(0).intValue();
        }
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).intValue() > i) {
                i = list.get(i2).intValue();
            }
        }
        return i;
    }

    public static int d(List<Integer> list) {
        if (list.size() == 0) {
            return 0;
        }
        if (list.size() == 1) {
            return list.get(0).intValue();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(list.get(i));
        }
        Collections.sort(arrayList);
        return arrayList.size() % 2 == 0 ? (int) Math.round((((Integer) arrayList.get(arrayList.size() / 2)).intValue() + ((Integer) arrayList.get((arrayList.size() / 2) - 1)).intValue()) / 2.0d) : ((Integer) arrayList.get(arrayList.size() / 2)).intValue();
    }

    public static int e(List<Integer> list) {
        if (list.size() == 0) {
            return 0;
        }
        if (list.size() == 1) {
            return list.get(0).intValue();
        }
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).intValue() < i) {
                i = list.get(i2).intValue();
            }
        }
        return i;
    }
}
