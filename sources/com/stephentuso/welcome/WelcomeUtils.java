package com.stephentuso.welcome;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/* loaded from: classes.dex */
public class WelcomeUtils {
    static final int NO_COLOR_SET = -1;
    private static final String TAG = "com.stephentuso.welcome.WelcomeUtils";

    public static void applyParallaxEffect(View view, boolean z, int i, float f, float f2) {
        if (z) {
            applyParallaxEffectRecursively(view, i, f, f2, 0);
        } else {
            applyParallaxEffectToImmediateChildren(view, i, f, f2);
        }
    }

    private static int applyParallaxEffectRecursively(View view, int i, float f, float f2, int i2) {
        if (!(view instanceof ViewGroup)) {
            translateViewForParallaxEffect(view, i2, i, f, f2);
            return i2 + 1;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
            i2 = applyParallaxEffectRecursively(viewGroup.getChildAt(i3), i, f, f2, i2);
        }
        return i2;
    }

    private static void applyParallaxEffectToImmediateChildren(View view, int i, float f, float f2) {
        if (!(view instanceof ViewGroup)) {
            translateViewForParallaxEffect(view, 0, i, f, f2);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            translateViewForParallaxEffect(viewGroup.getChildAt(i2), i2, i, f, f2);
        }
    }

    private static int calculateParallaxLayers(View view) {
        if (view instanceof ViewGroup) {
            return ((ViewGroup) view).getChildCount();
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int calculateParallaxLayers(View view, boolean z) {
        return z ? calculateParallaxLayersRecursively(view, 0) : calculateParallaxLayers(view);
    }

    private static int calculateParallaxLayersRecursively(View view, int i) {
        if (!(view instanceof ViewGroup)) {
            return i + 1;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            i = calculateParallaxLayersRecursively(viewGroup.getChildAt(i2), i);
        }
        return i;
    }

    private static float calculateParallaxOffsetAmount(int i, int i2, float f, float f2) {
        return (f + (i * f2)) * (-i2);
    }

    public static String getKey(Class<? extends WelcomeActivity> cls) {
        String str;
        try {
            str = (String) cls.getMethod("welcomeKey", new Class[0]).invoke(null, new Object[0]);
        } catch (NoSuchMethodException unused) {
            str = null;
        } catch (Exception e) {
            e = e;
            str = null;
        }
        try {
            if (str.isEmpty()) {
                Log.w(TAG, "welcomeKey() from " + cls.getSimpleName() + " returned an empty string. Is that an accident?");
            }
        } catch (NoSuchMethodException unused2) {
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
        }
        return str == null ? "" : str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isIndexBeforeLastPage(int i, int i2, boolean z) {
        if (z) {
            if (i <= i2) {
                return false;
            }
        } else if (i >= i2) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean setTypeface(TextView textView, String str, Context context) {
        if (str == null || str.equals("")) {
            return false;
        }
        try {
            textView.setTypeface(Typeface.createFromAsset(context.getAssets(), str));
            return true;
        } catch (Exception unused) {
            Log.w(TAG, "Error setting typeface");
            return false;
        }
    }

    private static void translateViewForParallaxEffect(View view, int i, int i2, float f, float f2) {
        if (Build.VERSION.SDK_INT >= 11) {
            view.setTranslationX(calculateParallaxOffsetAmount(i, i2, f, f2));
        }
    }
}
