package com.startapp;

import java.nio.charset.Charset;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class wd {
    public static final Charset a;

    static {
        Charset.forName("ISO-8859-1");
        Charset.forName("US-ASCII");
        Charset.forName("UTF-16");
        Charset.forName("UTF-16BE");
        Charset.forName("UTF-16LE");
        a = Charset.forName("UTF-8");
    }
}
