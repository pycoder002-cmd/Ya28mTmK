package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.startapp.aa;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class BaseSensorConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private int delay;
    private boolean enabled;
    private int minApiLevel;

    public BaseSensorConfig() {
        this.delay = 3;
        this.minApiLevel = 18;
        this.enabled = true;
    }

    public BaseSensorConfig(int i) {
        this.delay = 3;
        this.minApiLevel = 18;
        this.enabled = true;
        this.minApiLevel = i;
    }

    public int a() {
        return this.delay;
    }

    public int b() {
        return this.minApiLevel;
    }

    public boolean c() {
        return this.enabled;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || BaseSensorConfig.class != obj.getClass()) {
            return false;
        }
        BaseSensorConfig baseSensorConfig = (BaseSensorConfig) obj;
        return this.delay == baseSensorConfig.delay && this.minApiLevel == baseSensorConfig.minApiLevel && this.enabled == baseSensorConfig.enabled;
    }

    public int hashCode() {
        Object[] objArr = {Integer.valueOf(this.delay), Integer.valueOf(this.minApiLevel), Boolean.valueOf(this.enabled)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
