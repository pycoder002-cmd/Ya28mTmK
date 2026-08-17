package com.bvapp.arcmenulibrary.util;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

/* loaded from: classes.dex */
public class Util {
    public static final long FRAME_DURATION = 16;
    private static TypedValue value;

    @TargetApi(21)
    public static int colorAccent(Context context, int i) {
        return Build.VERSION.SDK_INT >= 21 ? getColor(context, R.attr.colorAccent, i) : getColor(context, com.bvapp.arcmenulibrary.R.attr.colorAccent, i);
    }

    @TargetApi(21)
    public static int colorControlActivated(Context context, int i) {
        return Build.VERSION.SDK_INT >= 21 ? getColor(context, R.attr.colorControlActivated, i) : getColor(context, com.bvapp.arcmenulibrary.R.attr.colorControlActivated, i);
    }

    @TargetApi(21)
    public static int colorControlHighlight(Context context, int i) {
        return Build.VERSION.SDK_INT >= 21 ? getColor(context, R.attr.colorControlHighlight, i) : getColor(context, com.bvapp.arcmenulibrary.R.attr.colorControlHighlight, i);
    }

    @TargetApi(21)
    public static int colorControlNormal(Context context, int i) {
        return Build.VERSION.SDK_INT >= 21 ? getColor(context, R.attr.colorControlNormal, i) : getColor(context, com.bvapp.arcmenulibrary.R.attr.colorControlNormal, i);
    }

    @TargetApi(21)
    public static int colorPrimary(Context context, int i) {
        return Build.VERSION.SDK_INT >= 21 ? getColor(context, R.attr.colorPrimary, i) : getColor(context, com.bvapp.arcmenulibrary.R.attr.colorPrimary, i);
    }

    @TargetApi(21)
    public static int colorPrimaryDark(Context context, int i) {
        return Build.VERSION.SDK_INT >= 21 ? getColor(context, R.attr.colorPrimaryDark, i) : getColor(context, com.bvapp.arcmenulibrary.R.attr.colorPrimaryDark, i);
    }

    public static int dpToPx(int i) {
        return (int) (i * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int dpToSp(int i) {
        return (int) (dpToPx(i) / spToPx(i));
    }

    private static int getColor(Context context, int i, int i2) {
        if (value == null) {
            value = new TypedValue();
        }
        try {
            Resources.Theme theme = context.getTheme();
            if (theme != null && theme.resolveAttribute(i, value, true)) {
                if (value.type >= 16 && value.type <= 31) {
                    return value.data;
                }
                if (value.type == 3) {
                    return context.getResources().getColor(value.resourceId);
                }
            }
        } catch (Exception unused) {
        }
        return i2;
    }

    public static int getDeviceHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static int getDeviceWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getHeight(Context context, CharSequence charSequence, int i, int i2, Typeface typeface, int i3) {
        return getMeasured(context, charSequence, i, i2, typeface, i3)[1];
    }

    private static int[] getMeasured(Context context, CharSequence charSequence, int i, int i2, Typeface typeface, int i3) {
        TextView textView = new TextView(context);
        textView.setPadding(i3, 0, i3, i3);
        textView.setTypeface(typeface);
        textView.setText(charSequence, TextView.BufferType.SPANNABLE);
        textView.setTextSize(2, i);
        textView.measure(View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        return new int[]{textView.getMeasuredWidth(), textView.getMeasuredHeight()};
    }

    public static int getType(TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i);
        }
        TypedValue peekValue = typedArray.peekValue(i);
        if (peekValue == null) {
            return 0;
        }
        return peekValue.type;
    }

    public static int getWidth(Context context, CharSequence charSequence, int i, int i2, Typeface typeface, int i3) {
        return getMeasured(context, charSequence, i, i2, typeface, i3)[0];
    }

    public static Animation scaleAnimationRelativeToSelf(float f, float f2, float f3, float f4, float f5, float f6, int i, boolean z) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f3, f4, 1, f5, 1, f6);
        scaleAnimation.setDuration(i);
        scaleAnimation.setFillAfter(z);
        return scaleAnimation;
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void shrinkExpandAnimation(final View view, final View.OnClickListener onClickListener) {
        if (view != null) {
            final Animation scaleAnimationRelativeToSelf = scaleAnimationRelativeToSelf(1.0f, 0.85f, 1.0f, 0.85f, 0.5f, 0.5f, 80, false);
            final Animation scaleAnimationRelativeToSelf2 = scaleAnimationRelativeToSelf(0.85f, 1.0f, 0.85f, 1.0f, 0.5f, 0.5f, 80, false);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.bvapp.arcmenulibrary.util.Util.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    view.startAnimation(scaleAnimationRelativeToSelf);
                }
            });
            scaleAnimationRelativeToSelf.setAnimationListener(new Animation.AnimationListener() { // from class: com.bvapp.arcmenulibrary.util.Util.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    view.startAnimation(scaleAnimationRelativeToSelf2);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
            scaleAnimationRelativeToSelf2.setAnimationListener(new Animation.AnimationListener() { // from class: com.bvapp.arcmenulibrary.util.Util.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    if (onClickListener != null) {
                        onClickListener.onClick(view);
                    }
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }
            });
        }
    }

    public static int spToPx(float f) {
        return (int) TypedValue.applyDimension(2, f, Resources.getSystem().getDisplayMetrics());
    }
}
