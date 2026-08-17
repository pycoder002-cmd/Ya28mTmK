package com.startapp;

import android.app.Activity;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class id extends nd {
    public final int a;

    public id(int i) {
        this.a = i;
    }

    @Override // com.startapp.nd
    public boolean a(Object obj) {
        if (!(obj instanceof j5)) {
            return false;
        }
        int i = ((j5) obj).a0;
        return (this.a & i) == i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && id.class == obj.getClass() && this.a == ((id) obj).a;
    }

    public int hashCode() {
        Object[] objArr = {Integer.valueOf(this.a)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
