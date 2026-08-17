package com.startapp;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class cc {
    public static final cc a = new cc(new JSONObject());
    public final JSONObject b;

    public cc() {
        this(new JSONObject());
    }

    public cc(JSONObject jSONObject) {
        this.b = jSONObject;
    }

    public final int a(int i) {
        Object opt = this.b.opt(String.valueOf(i));
        if (opt instanceof Number) {
            return ((Number) opt).intValue();
        }
        return 0;
    }

    public final void a(int i, Object obj) {
        try {
            this.b.put(String.valueOf(i), obj);
        } catch (JSONException unused) {
        }
    }

    public final String b(int i) {
        Object opt = this.b.opt(String.valueOf(i));
        if (opt != null) {
            return opt.toString();
        }
        return null;
    }
}
