package com.startapp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class h3 {
    public static h3 a = new h3();
    public Map<String, g3> b = new ConcurrentHashMap();

    public g3 a(String str) {
        if (this.b.containsKey(str)) {
            return this.b.get(str);
        }
        g3 g3Var = new g3();
        this.b.put(str, g3Var);
        return g3Var;
    }
}
