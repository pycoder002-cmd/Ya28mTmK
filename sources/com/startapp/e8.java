package com.startapp;

import io.reactivex.annotations.SchedulerSupport;
import java.util.Arrays;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class e8 {
    public static final List<String> a = Arrays.asList("portrait", "landscape", SchedulerSupport.NONE);
    public boolean b;
    public int c;

    public e8() {
        this(true, 2);
    }

    public e8(boolean z, int i) {
        this.b = z;
        this.c = i;
    }

    public static int a(String str) {
        int indexOf = a.indexOf(str);
        if (indexOf != -1) {
            return indexOf;
        }
        return 2;
    }
}
