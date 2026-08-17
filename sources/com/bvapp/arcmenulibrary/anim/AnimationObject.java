package com.bvapp.arcmenulibrary.anim;

import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/* loaded from: classes.dex */
public class AnimationObject {
    public static Animation rotationAnimationRelativeToSelf(float f, float f2, float f3, float f4, int i, boolean z) {
        RotateAnimation rotateAnimation = new RotateAnimation(f, f2, 1, f3, 1, f4);
        rotateAnimation.setDuration(i);
        rotateAnimation.setFillAfter(z);
        return rotateAnimation;
    }

    public static Animation scaleAnimation(float f, float f2, float f3, float f4, int i, int i2, float f5, float f6, int i3, boolean z) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f3, f4, i, f5, i2, f6);
        scaleAnimation.setDuration(i3);
        scaleAnimation.setFillAfter(z);
        return scaleAnimation;
    }

    public static Animation scaleAnimationRelativeToSelf(float f, float f2, float f3, float f4, float f5, float f6, int i, boolean z) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f3, f4, 1, f5, 1, f6);
        scaleAnimation.setDuration(i);
        scaleAnimation.setFillAfter(z);
        return scaleAnimation;
    }

    public static Animation translateAnimation(int i, float f, float f2, float f3, float f4, Interpolator interpolator, int i2, boolean z) {
        TranslateAnimation translateAnimation = new TranslateAnimation(i, f, i, f2, i, f3, i, f4);
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setDuration(i2);
        translateAnimation.setFillAfter(z);
        return translateAnimation;
    }

    public static Animation translateAnimationRelativeToParent(float f, float f2, float f3, float f4, Interpolator interpolator, int i, boolean z) {
        TranslateAnimation translateAnimation = new TranslateAnimation(2, f, 2, f2, 2, f3, 2, f4);
        translateAnimation.setInterpolator(interpolator);
        translateAnimation.setDuration(i);
        translateAnimation.setFillAfter(z);
        return translateAnimation;
    }
}
