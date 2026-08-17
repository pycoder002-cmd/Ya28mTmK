package com.startapp.sdk.adsbase.remoteconfig;

import android.app.Activity;
import com.github.mikephil.charting.utils.Utils;
import com.startapp.aa;
import com.startapp.f;
import com.startapp.sdk.adsbase.remoteconfig.RcdTargets;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class RcdMetadata implements Serializable {
    private static final long serialVersionUID = -5907202998030810278L;

    @f(complex = true, parser = RcdTargets.Parser.class)
    private RcdTargets targets;
    private boolean enabled = false;
    private double prb = Utils.DOUBLE_EPSILON;
    private int ief = 0;
    private double iep = Utils.DOUBLE_EPSILON;

    public double a() {
        return this.prb;
    }

    public RcdTargets b() {
        return this.targets;
    }

    public boolean c() {
        return this.enabled;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || RcdMetadata.class != obj.getClass()) {
            return false;
        }
        RcdMetadata rcdMetadata = (RcdMetadata) obj;
        return this.enabled == rcdMetadata.enabled && Double.compare(this.prb, rcdMetadata.prb) == 0 && this.ief == rcdMetadata.ief && Double.compare(this.iep, rcdMetadata.iep) == 0 && aa.a(this.targets, rcdMetadata.targets);
    }

    public int hashCode() {
        Object[] objArr = {Boolean.valueOf(this.enabled), Double.valueOf(this.prb), Integer.valueOf(this.ief), Double.valueOf(this.iep), this.targets};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }
}
