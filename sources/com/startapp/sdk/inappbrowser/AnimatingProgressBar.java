package com.startapp.sdk.inappbrowser;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AnimatingProgressBar extends ProgressBar {
    public static final Interpolator a = new AccelerateDecelerateInterpolator();
    public ValueAnimator b;
    public boolean c;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements ValueAnimator.AnimatorUpdateListener {
        public Integer a;

        public a() {
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            Integer num = (Integer) valueAnimator.getAnimatedValue();
            this.a = num;
            AnimatingProgressBar.super.setProgress(num.intValue());
        }
    }

    public AnimatingProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = false;
        this.c = Build.VERSION.SDK_INT >= 11;
    }

    public void a() {
        super.setProgress(0);
        ValueAnimator valueAnimator = this.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    @Override // android.widget.ProgressBar, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int i) {
        if (!this.c) {
            super.setProgress(i);
            return;
        }
        ValueAnimator valueAnimator = this.b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            if (getProgress() >= i) {
                return;
            }
        } else {
            ValueAnimator ofInt = ValueAnimator.ofInt(getProgress(), i);
            this.b = ofInt;
            ofInt.setInterpolator(a);
            this.b.addUpdateListener(new a());
        }
        this.b.setIntValues(getProgress(), i);
        this.b.start();
    }
}
