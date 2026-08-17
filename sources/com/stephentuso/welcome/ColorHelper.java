package com.stephentuso.welcome;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

/* loaded from: classes.dex */
class ColorHelper {
    ColorHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getColor(Context context, @ColorRes int i) {
        return ContextCompat.getColor(context, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int resolveColorAttribute(Context context, @AttrRes int i, int i2) {
        TypedValue typedValue = new TypedValue();
        return context.getTheme().resolveAttribute(i, typedValue, true) ? typedValue.data : i2;
    }
}
