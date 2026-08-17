package com.startapp;

import java.util.Collection;
import java.util.Locale;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class wb {
    public static final wb a = new wb();
    public final String b;
    public final String c;
    public final String d;

    public wb() {
        this.b = null;
        this.c = null;
        this.d = null;
    }

    public wb(Locale locale, Collection<Locale> collection) {
        this.b = locale.toString();
        this.c = a(null, collection, ';');
        this.d = a(locale, collection, ',');
    }

    public static String a(Locale locale, Iterable<Locale> iterable, char c) {
        boolean z;
        StringBuilder sb;
        if (locale != null) {
            sb = new StringBuilder();
            sb.append(locale);
            z = true;
        } else {
            z = false;
            sb = null;
        }
        if (iterable != null) {
            for (Locale locale2 : iterable) {
                if (locale2 != null) {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    if (z) {
                        sb.append(c);
                    }
                    sb.append(locale2);
                    z = true;
                }
            }
        }
        if (sb != null) {
            return sb.toString();
        }
        return null;
    }
}
