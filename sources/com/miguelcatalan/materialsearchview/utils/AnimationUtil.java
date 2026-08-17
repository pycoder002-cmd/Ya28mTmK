package com.miguelcatalan.materialsearchview.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;

/* loaded from: classes.dex */
public class AnimationUtil {
    public static int ANIMATION_DURATION_LONG = 800;
    public static int ANIMATION_DURATION_MEDIUM = 400;
    public static int ANIMATION_DURATION_SHORT = 150;

    /* loaded from: classes.dex */
    public interface AnimationListener {
        boolean onAnimationCancel(View view);

        boolean onAnimationEnd(View view);

        boolean onAnimationStart(View view);
    }

    public static void crossFadeViews(View view, View view2) {
        crossFadeViews(view, view2, ANIMATION_DURATION_SHORT);
    }

    public static void crossFadeViews(View view, View view2, int i) {
        fadeInView(view, i);
        fadeOutView(view2, i);
    }

    public static void fadeInView(View view) {
        fadeInView(view, ANIMATION_DURATION_SHORT);
    }

    public static void fadeInView(View view, int i) {
        fadeInView(view, i, null);
    }

    public static void fadeInView(View view, int i, final AnimationListener animationListener) {
        view.setVisibility(0);
        view.setAlpha(0.0f);
        ViewCompat.animate(view).alpha(1.0f).setDuration(i).setListener(animationListener != null ? new ViewPropertyAnimatorListener() { // from class: com.miguelcatalan.materialsearchview.utils.AnimationUtil.1
            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationCancel(View view2) {
            }

            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view2) {
                if (AnimationListener.this.onAnimationEnd(view2)) {
                    return;
                }
                view2.setDrawingCacheEnabled(false);
            }

            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationStart(View view2) {
                if (AnimationListener.this.onAnimationStart(view2)) {
                    return;
                }
                view2.setDrawingCacheEnabled(true);
            }
        } : null);
    }

    public static void fadeOutView(View view) {
        fadeOutView(view, ANIMATION_DURATION_SHORT);
    }

    public static void fadeOutView(View view, int i) {
        fadeOutView(view, i, null);
    }

    public static void fadeOutView(View view, int i, final AnimationListener animationListener) {
        ViewCompat.animate(view).alpha(0.0f).setDuration(i).setListener(new ViewPropertyAnimatorListener() { // from class: com.miguelcatalan.materialsearchview.utils.AnimationUtil.3
            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationCancel(View view2) {
            }

            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view2) {
                if (AnimationListener.this == null || !AnimationListener.this.onAnimationEnd(view2)) {
                    view2.setVisibility(8);
                    view2.setDrawingCacheEnabled(false);
                }
            }

            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationStart(View view2) {
                if (AnimationListener.this == null || !AnimationListener.this.onAnimationStart(view2)) {
                    view2.setDrawingCacheEnabled(true);
                }
            }
        });
    }

    @TargetApi(21)
    public static void reveal(final View view, final AnimationListener animationListener) {
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, view.getWidth() - ((int) TypedValue.applyDimension(1, 24.0f, view.getResources().getDisplayMetrics())), view.getHeight() / 2, 0.0f, Math.max(view.getWidth(), view.getHeight()));
        view.setVisibility(0);
        createCircularReveal.addListener(new AnimatorListenerAdapter() { // from class: com.miguelcatalan.materialsearchview.utils.AnimationUtil.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                AnimationListener.this.onAnimationCancel(view);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                AnimationListener.this.onAnimationEnd(view);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                AnimationListener.this.onAnimationStart(view);
            }
        });
        createCircularReveal.start();
    }
}
