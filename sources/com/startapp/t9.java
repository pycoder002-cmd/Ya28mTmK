package com.startapp;

import android.app.Activity;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class t9 {
    public String a;
    public String b;
    public Set<String> c;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || t9.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((t9) obj).a);
    }

    public int hashCode() {
        Object[] objArr = {this.a};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }

    public String toString() {
        return "NameValueObject [name=" + this.a + ", value=" + this.b + ", valueSet=" + this.c + "]";
    }
}
