package me.zhanghai.android.materialprogressbar.internal;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.os.Build;
import android.util.Property;

/* loaded from: classes2.dex */
public class ObjectAnimatorCompat {
    private ObjectAnimatorCompat() {
    }

    public static <T> ObjectAnimator ofArgb(T t, Property<T, Integer> property, int... iArr) {
        return Build.VERSION.SDK_INT >= 21 ? ObjectAnimatorCompatLollipop.ofArgb(t, property, iArr) : ObjectAnimatorCompatBase.ofArgb(t, property, iArr);
    }

    public static ObjectAnimator ofArgb(Object obj, String str, int... iArr) {
        return Build.VERSION.SDK_INT >= 21 ? ObjectAnimatorCompatLollipop.ofArgb(obj, str, iArr) : ObjectAnimatorCompatBase.ofArgb(obj, str, iArr);
    }

    public static <T> ObjectAnimator ofFloat(T t, Property<T, Float> property, Property<T, Float> property2, Path path) {
        return Build.VERSION.SDK_INT >= 21 ? ObjectAnimatorCompatLollipop.ofFloat(t, property, property2, path) : ObjectAnimatorCompatBase.ofFloat(t, property, property2, path);
    }

    public static ObjectAnimator ofFloat(Object obj, String str, String str2, Path path) {
        return Build.VERSION.SDK_INT >= 21 ? ObjectAnimatorCompatLollipop.ofFloat(obj, str, str2, path) : ObjectAnimatorCompatBase.ofFloat(obj, str, str2, path);
    }

    public static <T> ObjectAnimator ofInt(T t, Property<T, Integer> property, Property<T, Integer> property2, Path path) {
        return Build.VERSION.SDK_INT >= 21 ? ObjectAnimatorCompatLollipop.ofInt(t, property, property2, path) : ObjectAnimatorCompatBase.ofInt(t, property, property2, path);
    }

    public static ObjectAnimator ofInt(Object obj, String str, String str2, Path path) {
        return Build.VERSION.SDK_INT >= 21 ? ObjectAnimatorCompatLollipop.ofInt(obj, str, str2, path) : ObjectAnimatorCompatBase.ofInt(obj, str, str2, path);
    }
}
