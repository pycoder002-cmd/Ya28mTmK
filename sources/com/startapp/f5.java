package com.startapp;

import java.lang.Comparable;
import java.util.regex.Pattern;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class f5<T extends Comparable<T>> implements Comparable<f5<T>> {
    public static Pattern a = Pattern.compile("\\d{2}:\\d{2}:\\d{2}(.\\d{3})?");
    public static Pattern b = Pattern.compile("((\\d{1,2})|(100))%");
    public final String c;
    public final T d;

    public f5(String str, T t) {
        this.c = str;
        this.d = t;
    }

    public static boolean a(String str) {
        return a.matcher(str).matches();
    }

    public static Integer b(String str) {
        String[] split = str.split(":");
        if (split.length != 3) {
            return null;
        }
        try {
            return Integer.valueOf((Integer.parseInt(split[0]) * 60 * 60 * 1000) + (Integer.parseInt(split[1]) * 60 * 1000) + ((int) (Float.parseFloat(split[2]) * 1000.0f)));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Object obj) {
        return this.d.compareTo(((f5) obj).d);
    }
}
