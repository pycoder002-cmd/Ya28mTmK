package com.startapp;

import android.content.pm.PackageInfo;
import java.util.Comparator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class t5 implements Comparator<PackageInfo> {
    @Override // java.util.Comparator
    public int compare(PackageInfo packageInfo, PackageInfo packageInfo2) {
        long j = packageInfo.firstInstallTime;
        long j2 = packageInfo2.firstInstallTime;
        if (j > j2) {
            return -1;
        }
        return j == j2 ? 0 : 1;
    }
}
