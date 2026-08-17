package com.startapp;

import java.util.regex.Pattern;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ae {
    public final Pattern a = Pattern.compile("\\+");
    public final Pattern b = Pattern.compile("/");
    public final Pattern c = Pattern.compile("=");

    public ae() {
        Pattern.compile("_");
        Pattern.compile("\\*");
        Pattern.compile("#");
    }

    public String a(String str) {
        return this.c.matcher(this.b.matcher(this.a.matcher(str).replaceAll("_")).replaceAll("*")).replaceAll("#");
    }
}
