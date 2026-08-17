package com.startapp;

import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class x4 {
    public List<String> a;
    public String b;

    public x4(List<String> list, String str) {
        this.a = list;
        this.b = str;
    }

    public String toString() {
        return "[VideoEvent: tag=" + this.b + ", fullUrls=" + this.a.toString() + "]";
    }
}
