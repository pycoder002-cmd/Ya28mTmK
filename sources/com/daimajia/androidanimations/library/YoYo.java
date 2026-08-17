package com.daimajia.androidanimations.library;

import android.animation.Animator;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class YoYo {
    public static final float CENTER_PIVOT = Float.MAX_VALUE;
    private static final long DURATION = 1000;
    public static final int INFINITE = -1;
    private static final long NO_DELAY = 0;
    private BaseViewAnimator animator;
    private List<Animator.AnimatorListener> callbacks;
    private long delay;
    private long duration;
    private Interpolator interpolator;
    private float pivotX;
    private float pivotY;
    private boolean repeat;
    private int repeatMode;
    private int repeatTimes;
    private View target;

    /* loaded from: classes.dex */
    public static final class AnimationComposer {
        private BaseViewAnimator animator;
        private List<Animator.AnimatorListener> callbacks;
        private long delay;
        private long duration;
        private Interpolator interpolator;
        private float pivotX;
        private float pivotY;
        private boolean repeat;
        private int repeatMode;
        private int repeatTimes;
        private View target;

        private AnimationComposer(BaseViewAnimator baseViewAnimator) {
            this.callbacks = new ArrayList();
            this.duration = 1000L;
            this.delay = 0L;
            this.repeat = false;
            this.repeatTimes = 0;
            this.repeatMode = 1;
            this.pivotX = Float.MAX_VALUE;
            this.pivotY = Float.MAX_VALUE;
            this.animator = baseViewAnimator;
        }

        private AnimationComposer(Techniques techniques) {
            this.callbacks = new ArrayList();
            this.duration = 1000L;
            this.delay = 0L;
            this.repeat = false;
            this.repeatTimes = 0;
            this.repeatMode = 1;
            this.pivotX = Float.MAX_VALUE;
            this.pivotY = Float.MAX_VALUE;
            this.animator = techniques.getAnimator();
        }

        public AnimationComposer delay(long j) {
            this.delay = j;
            return this;
        }

        public AnimationComposer duration(long j) {
            this.duration = j;
            return this;
        }

        public AnimationComposer interpolate(Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public AnimationComposer onCancel(final AnimatorCallback animatorCallback) {
            this.callbacks.add(new EmptyAnimatorListener() { // from class: com.daimajia.androidanimations.library.YoYo.AnimationComposer.3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.daimajia.androidanimations.library.YoYo.EmptyAnimatorListener, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer onEnd(final AnimatorCallback animatorCallback) {
            this.callbacks.add(new EmptyAnimatorListener() { // from class: com.daimajia.androidanimations.library.YoYo.AnimationComposer.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.daimajia.androidanimations.library.YoYo.EmptyAnimatorListener, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer onRepeat(final AnimatorCallback animatorCallback) {
            this.callbacks.add(new EmptyAnimatorListener() { // from class: com.daimajia.androidanimations.library.YoYo.AnimationComposer.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.daimajia.androidanimations.library.YoYo.EmptyAnimatorListener, android.animation.Animator.AnimatorListener
                public void onAnimationRepeat(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer onStart(final AnimatorCallback animatorCallback) {
            this.callbacks.add(new EmptyAnimatorListener() { // from class: com.daimajia.androidanimations.library.YoYo.AnimationComposer.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // com.daimajia.androidanimations.library.YoYo.EmptyAnimatorListener, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    animatorCallback.call(animator);
                }
            });
            return this;
        }

        public AnimationComposer pivot(float f, float f2) {
            this.pivotX = f;
            this.pivotY = f2;
            return this;
        }

        public AnimationComposer pivotX(float f) {
            this.pivotX = f;
            return this;
        }

        public AnimationComposer pivotY(float f) {
            this.pivotY = f;
            return this;
        }

        public YoYoString playOn(View view) {
            this.target = view;
            return new YoYoString(new YoYo(this).play(), this.target);
        }

        public AnimationComposer repeat(int i) {
            if (i < -1) {
                throw new RuntimeException("Can not be less than -1, -1 is infinite loop");
            }
            this.repeat = i != 0;
            this.repeatTimes = i;
            return this;
        }

        public AnimationComposer repeatMode(int i) {
            this.repeatMode = i;
            return this;
        }

        public AnimationComposer withListener(Animator.AnimatorListener animatorListener) {
            this.callbacks.add(animatorListener);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public interface AnimatorCallback {
        void call(Animator animator);
    }

    /* loaded from: classes.dex */
    private static class EmptyAnimatorListener implements Animator.AnimatorListener {
        private EmptyAnimatorListener() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    /* loaded from: classes.dex */
    public static final class YoYoString {
        private BaseViewAnimator animator;
        private View target;

        private YoYoString(BaseViewAnimator baseViewAnimator, View view) {
            this.target = view;
            this.animator = baseViewAnimator;
        }

        public boolean isRunning() {
            return this.animator.isRunning();
        }

        public boolean isStarted() {
            return this.animator.isStarted();
        }

        public void stop() {
            stop(true);
        }

        public void stop(boolean z) {
            this.animator.cancel();
            if (z) {
                this.animator.reset(this.target);
            }
        }
    }

    private YoYo(AnimationComposer animationComposer) {
        this.animator = animationComposer.animator;
        this.duration = animationComposer.duration;
        this.delay = animationComposer.delay;
        this.repeat = animationComposer.repeat;
        this.repeatTimes = animationComposer.repeatTimes;
        this.repeatMode = animationComposer.repeatMode;
        this.interpolator = animationComposer.interpolator;
        this.pivotX = animationComposer.pivotX;
        this.pivotY = animationComposer.pivotY;
        this.callbacks = animationComposer.callbacks;
        this.target = animationComposer.target;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BaseViewAnimator play() {
        this.animator.setTarget(this.target);
        if (this.pivotX == Float.MAX_VALUE) {
            ViewCompat.setPivotX(this.target, this.target.getMeasuredWidth() / 2.0f);
        } else {
            this.target.setPivotX(this.pivotX);
        }
        if (this.pivotY == Float.MAX_VALUE) {
            ViewCompat.setPivotY(this.target, this.target.getMeasuredHeight() / 2.0f);
        } else {
            this.target.setPivotY(this.pivotY);
        }
        this.animator.setDuration(this.duration).setRepeatTimes(this.repeatTimes).setRepeatMode(this.repeatMode).setInterpolator(this.interpolator).setStartDelay(this.delay);
        if (this.callbacks.size() > 0) {
            Iterator<Animator.AnimatorListener> it = this.callbacks.iterator();
            while (it.hasNext()) {
                this.animator.addAnimatorListener(it.next());
            }
        }
        this.animator.animate();
        return this.animator;
    }

    public static AnimationComposer with(BaseViewAnimator baseViewAnimator) {
        return new AnimationComposer(baseViewAnimator);
    }

    public static AnimationComposer with(Techniques techniques) {
        return new AnimationComposer(techniques);
    }
}
