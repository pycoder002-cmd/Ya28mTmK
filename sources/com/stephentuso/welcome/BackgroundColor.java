package com.stephentuso.welcome;

import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;

/* loaded from: classes.dex */
public class BackgroundColor {
    private int color;

    public BackgroundColor(@ColorInt int i) {
        this.color = 0;
        this.color = i;
    }

    public BackgroundColor(@ColorInt @Nullable Integer num, @ColorInt int i) {
        this.color = 0;
        this.color = num != null ? num.intValue() : i;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BackgroundColor) && ((BackgroundColor) obj).value() == value();
    }

    public int value() {
        return this.color;
    }
}
