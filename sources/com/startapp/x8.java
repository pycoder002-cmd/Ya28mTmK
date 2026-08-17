package com.startapp;

import android.app.Activity;
import com.startapp.sdk.adsbase.model.AdPreferences;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class x8 {
    public final Map<a, String> a = new ConcurrentHashMap();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a {
        public final AdPreferences.Placement a;
        public final int b;

        public a(AdPreferences.Placement placement, int i) {
            this.a = placement;
            this.b = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || a.class != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return this.b == aVar.b && this.a == aVar.a;
        }

        public int hashCode() {
            Object[] objArr = {this.a, Integer.valueOf(this.b)};
            Map<Activity, Integer> map = aa.a;
            return Arrays.deepHashCode(objArr);
        }
    }
}
