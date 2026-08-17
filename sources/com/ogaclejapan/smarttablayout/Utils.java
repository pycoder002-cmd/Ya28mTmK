package com.ogaclejapan.smarttablayout;

import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
final class Utils {
    private Utils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getEnd(View view) {
        return getEnd(view, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getEnd(View view, boolean z) {
        if (view == null) {
            return 0;
        }
        return isLayoutRtl(view) ? z ? view.getLeft() + getPaddingEnd(view) : view.getLeft() : z ? view.getRight() - getPaddingEnd(view) : view.getRight();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMarginEnd(View view) {
        if (view == null) {
            return 0;
        }
        return MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) view.getLayoutParams());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMarginHorizontally(View view) {
        if (view == null) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return MarginLayoutParamsCompat.getMarginStart(marginLayoutParams) + MarginLayoutParamsCompat.getMarginEnd(marginLayoutParams);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMarginStart(View view) {
        if (view == null) {
            return 0;
        }
        return MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) view.getLayoutParams());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getMeasuredWidth(View view) {
        if (view == null) {
            return 0;
        }
        return view.getMeasuredWidth();
    }

    static int getPaddingEnd(View view) {
        if (view == null) {
            return 0;
        }
        return ViewCompat.getPaddingEnd(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getPaddingHorizontally(View view) {
        if (view == null) {
            return 0;
        }
        return view.getPaddingLeft() + view.getPaddingRight();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getPaddingStart(View view) {
        if (view == null) {
            return 0;
        }
        return ViewCompat.getPaddingStart(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getStart(View view) {
        return getStart(view, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getStart(View view, boolean z) {
        if (view == null) {
            return 0;
        }
        return isLayoutRtl(view) ? z ? view.getRight() - getPaddingStart(view) : view.getRight() : z ? view.getLeft() + getPaddingStart(view) : view.getLeft();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getWidth(View view) {
        if (view == null) {
            return 0;
        }
        return view.getWidth();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getWidthWithMargin(View view) {
        return getWidth(view) + getMarginHorizontally(view);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isLayoutRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }
}
