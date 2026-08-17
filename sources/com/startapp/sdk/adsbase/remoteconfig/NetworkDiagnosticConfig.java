package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.startapp.aa;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class NetworkDiagnosticConfig implements Serializable {
    private static final long serialVersionUID = 600844380854621516L;
    private boolean enabled;
    private int ief;
    private int sendingIntervalMinutes = 60;
    private int minCountToSend = 10;
    private int types = 1;

    public int a() {
        return this.ief;
    }

    public int b() {
        return this.minCountToSend;
    }

    public int c() {
        return this.sendingIntervalMinutes;
    }

    public int d() {
        return this.types;
    }

    public boolean e() {
        return this.enabled;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || NetworkDiagnosticConfig.class != obj.getClass()) {
            return false;
        }
        NetworkDiagnosticConfig networkDiagnosticConfig = (NetworkDiagnosticConfig) obj;
        return this.enabled == networkDiagnosticConfig.enabled && this.sendingIntervalMinutes == networkDiagnosticConfig.sendingIntervalMinutes && this.minCountToSend == networkDiagnosticConfig.minCountToSend && this.types == networkDiagnosticConfig.types && this.ief == networkDiagnosticConfig.ief;
    }

    public int hashCode() {
        Object[] objArr = {Boolean.valueOf(this.enabled), Integer.valueOf(this.sendingIntervalMinutes), Integer.valueOf(this.minCountToSend), Integer.valueOf(this.types), Integer.valueOf(this.ief)};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
