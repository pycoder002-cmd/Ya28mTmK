package com.startapp;

import com.startapp.sdk.adsbase.model.AdDetails;
import java.util.HashMap;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class g3 {
    public List<e3> b;
    public String c = "";
    public z2 a = new z2();

    public void a() {
        for (r5 r5Var : this.a.a.values()) {
            if (r5Var != null) {
                r5Var.a("AD_CLOSED_TOO_QUICKLY", null);
            }
        }
    }

    public void a(i3 i3Var, boolean z) {
        z2 z2Var = this.a;
        z2Var.d = i3Var;
        if (z) {
            z2Var.c.clear();
            z2Var.e = 0;
            z2Var.f.clear();
            HashMap<String, r5> hashMap = z2Var.a;
            if (hashMap != null) {
                for (r5 r5Var : hashMap.values()) {
                    if (r5Var != null) {
                        r5Var.a("AD_CLOSED_TOO_QUICKLY", null);
                    }
                }
                z2Var.a.clear();
            }
        }
    }

    public void a(AdDetails adDetails) {
        e3 e3Var = new e3(adDetails);
        this.b.add(e3Var);
        this.a.a(this.b.size() - 1, e3Var.a, e3Var.i);
    }
}
