package com.startapp;

import com.startapp.sdk.adsbase.model.AdPreferences;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class i6 implements Comparable<i6> {
    public long a = System.currentTimeMillis();
    public AdPreferences.Placement b;
    public String c;

    public i6(AdPreferences.Placement placement, String str) {
        this.b = placement;
        this.c = str == null ? "" : str;
    }

    @Override // java.lang.Comparable
    public int compareTo(i6 i6Var) {
        long j = this.a - i6Var.a;
        if (j > 0) {
            return 1;
        }
        return j == 0 ? 0 : -1;
    }

    public String toString() {
        return "AdDisplayEvent [displayTime=" + this.a + ", placement=" + this.b + ", adTag=" + this.c + "]";
    }
}
