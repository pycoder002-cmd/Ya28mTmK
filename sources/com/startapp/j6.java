package com.startapp;

import com.startapp.sdk.adsbase.model.AdPreferences;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class j6 {
    public static j6 a = new j6();
    public List<i6> b = new ArrayList();
    public Map<AdPreferences.Placement, List<i6>> c = new HashMap();
    public Map<String, List<i6>> d = new HashMap();

    public static j6 a() {
        return a;
    }

    public synchronized void a(i6 i6Var) {
        this.b.add(0, i6Var);
        List<i6> list = this.c.get(i6Var.b);
        if (list == null) {
            list = new ArrayList<>();
            this.c.put(i6Var.b, list);
        }
        list.add(0, i6Var);
        List<i6> list2 = this.d.get(i6Var.c);
        if (list2 == null) {
            list2 = new ArrayList<>();
            this.d.put(i6Var.c, list2);
        }
        list2.add(0, i6Var);
    }

    public int b() {
        return this.b.size();
    }
}
