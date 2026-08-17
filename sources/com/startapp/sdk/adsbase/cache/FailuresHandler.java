package com.startapp.sdk.adsbase.cache;

import android.app.Activity;
import com.startapp.aa;
import com.startapp.f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class FailuresHandler implements Serializable {
    private static final long serialVersionUID = 1;

    @f(type = ArrayList.class, value = Integer.class)
    private List<Integer> intervals = Arrays.asList(10, 30, 60, 300);
    private boolean infiniteLastRetry = true;

    public List<Integer> a() {
        return this.intervals;
    }

    public boolean b() {
        return this.infiniteLastRetry;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FailuresHandler.class != obj.getClass()) {
            return false;
        }
        FailuresHandler failuresHandler = (FailuresHandler) obj;
        return this.infiniteLastRetry == failuresHandler.infiniteLastRetry && aa.a(this.intervals, failuresHandler.intervals);
    }

    public int hashCode() {
        Object[] objArr = {this.intervals, Boolean.valueOf(this.infiniteLastRetry)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
