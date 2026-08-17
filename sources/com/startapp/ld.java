package com.startapp;

import android.app.Activity;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ld extends nd {
    public final List<q7> a;

    public ld(List<q7> list) {
        this.a = list;
    }

    @Override // com.startapp.nd
    public boolean a(Object obj) {
        if (obj instanceof y7) {
            return this.a.contains(((y7) obj).h0.a);
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ld.class != obj.getClass()) {
            return false;
        }
        return aa.a(this.a, ((ld) obj).a);
    }

    public int hashCode() {
        Object[] objArr = {this.a};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
