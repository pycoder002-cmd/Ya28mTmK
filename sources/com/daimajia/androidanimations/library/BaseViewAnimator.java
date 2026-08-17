package com.daimajia.androidanimations.library;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.Iterator;

/* loaded from: classes.dex */
public abstract class BaseViewAnimator {
    public static final long DURATION = 1000;
    private long mDuration = 1000;
    private int mRepeatTimes = 0;
    private int mRepeatMode = 1;
    private AnimatorSet mAnimatorSet = new AnimatorSet();

    public BaseViewAnimator addAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.mAnimatorSet.addListener(animatorListener);
        return this;
    }

    public void animate() {
        start();
    }

    public void cancel() {
        this.mAnimatorSet.cancel();
    }

    public AnimatorSet getAnimatorAgent() {
        return this.mAnimatorSet;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public long getStartDelay() {
        return this.mAnimatorSet.getStartDelay();
    }

    public boolean isRunning() {
        return this.mAnimatorSet.isRunning();
    }

    public boolean isStarted() {
        return this.mAnimatorSet.isStarted();
    }

    protected abstract void prepare(View view);

    public void removeAllListener() {
        this.mAnimatorSet.removeAllListeners();
    }

    public void removeAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.mAnimatorSet.removeListener(animatorListener);
    }

    public void reset(View view) {
        ViewCompat.setAlpha(view, 1.0f);
        ViewCompat.setScaleX(view, 1.0f);
        ViewCompat.setScaleY(view, 1.0f);
        ViewCompat.setTranslationX(view, 0.0f);
        ViewCompat.setTranslationY(view, 0.0f);
        ViewCompat.setRotation(view, 0.0f);
        ViewCompat.setRotationY(view, 0.0f);
        ViewCompat.setRotationX(view, 0.0f);
    }

    public void restart() {
        this.mAnimatorSet = this.mAnimatorSet.clone();
        start();
    }

    public BaseViewAnimator setDuration(long j) {
        this.mDuration = j;
        return this;
    }

    public BaseViewAnimator setInterpolator(Interpolator interpolator) {
        this.mAnimatorSet.setInterpolator(interpolator);
        return this;
    }

    public BaseViewAnimator setRepeatMode(int i) {
        this.mRepeatMode = i;
        return this;
    }

    public BaseViewAnimator setRepeatTimes(int i) {
        this.mRepeatTimes = i;
        return this;
    }

    public BaseViewAnimator setStartDelay(long j) {
        getAnimatorAgent().setStartDelay(j);
        return this;
    }

    public BaseViewAnimator setTarget(View view) {
        reset(view);
        prepare(view);
        return this;
    }

    public void start() {
        Iterator<Animator> it = this.mAnimatorSet.getChildAnimations().iterator();
        while (it.hasNext()) {
            Animator next = it.next();
            if (next instanceof ValueAnimator) {
                ValueAnimator valueAnimator = (ValueAnimator) next;
                valueAnimator.setRepeatCount(this.mRepeatTimes);
                valueAnimator.setRepeatMode(this.mRepeatMode);
            }
        }
        this.mAnimatorSet.setDuration(this.mDuration);
        this.mAnimatorSet.start();
    }
}
