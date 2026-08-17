package com.iab.omid.library.startapp.walking.a;

import com.iab.omid.library.startapp.walking.a.b;
import java.util.HashSet;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class a extends b {
    public final HashSet<String> a;
    public final JSONObject b;
    public final long c;

    public a(b.InterfaceC0050b interfaceC0050b, HashSet<String> hashSet, JSONObject jSONObject, long j) {
        super(interfaceC0050b);
        this.a = new HashSet<>(hashSet);
        this.b = jSONObject;
        this.c = j;
    }
}
