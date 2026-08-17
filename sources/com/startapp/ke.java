package com.startapp;

import com.startapp.simple.bloomfilter.version.BloomVersion;
import java.util.HashMap;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ke {
    public final Map<BloomVersion, je> a;

    public ke() {
        HashMap hashMap = new HashMap();
        this.a = hashMap;
        hashMap.put(BloomVersion.ZERO, new oe());
        hashMap.put(BloomVersion.THREE, new ne());
        hashMap.put(BloomVersion.FOUR, new me());
        hashMap.put(BloomVersion.FIVE, new le());
    }
}
