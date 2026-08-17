package com.startapp;

import android.app.Activity;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class kd extends nd {
    public final q7 a;
    public final String b;

    public kd(q7 q7Var, String str) {
        this.a = q7Var;
        this.b = str;
    }

    @Override // com.startapp.nd
    public boolean a(Object obj) {
        if (!(obj instanceof y7)) {
            return false;
        }
        q7 q7Var = this.a;
        p7 p7Var = ((y7) obj).h0;
        if (q7Var != p7Var.a) {
            return false;
        }
        String str = this.b;
        return str == null || str.equals(p7Var.d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || kd.class != obj.getClass()) {
            return false;
        }
        kd kdVar = (kd) obj;
        return aa.a(this.a, kdVar.a) && aa.a(this.b, kdVar.b);
    }

    public int hashCode() {
        Object[] objArr = {this.a, this.b};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
