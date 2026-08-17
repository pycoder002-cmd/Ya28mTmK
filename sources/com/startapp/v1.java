package com.startapp;

import com.startapp.networkTest.enums.FileTypes;
import com.startapp.networkTest.results.BaseResult;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class v1 {
    public static <T> T a(String str, Class<T> cls) {
        return (T) a(str, cls, false);
    }

    public static <T> T a(String str, Class<T> cls, boolean z) {
        return (T) c.a(str, cls);
    }

    public static String a(FileTypes fileTypes, BaseResult baseResult) {
        return a(baseResult);
    }

    public static String a(Object obj) {
        return a(obj, obj.getClass());
    }

    public static String a(Object obj, Class<?> cls) {
        return String.valueOf(c.b(obj));
    }
}
