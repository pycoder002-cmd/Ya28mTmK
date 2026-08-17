package com.startapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class md extends nd {
    public final String a;
    public final Map<String, String> b;

    public md(String str, Map<String, String> map) {
        this.a = str;
        this.b = map;
    }

    @Override // com.startapp.nd
    public boolean a(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        if (pair.first != this) {
            return false;
        }
        Object obj2 = pair.second;
        if (!(obj2 instanceof Intent)) {
            return false;
        }
        Intent intent = (Intent) obj2;
        if (!this.a.equals(intent.getAction())) {
            return false;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            extras = Bundle.EMPTY;
        }
        for (Map.Entry<String, String> entry : this.b.entrySet()) {
            if (!entry.getValue().equals(String.valueOf(extras.get(entry.getKey())))) {
                return false;
            }
        }
        return true;
    }
}
