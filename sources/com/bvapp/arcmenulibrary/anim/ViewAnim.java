package com.bvapp.arcmenulibrary.anim;

import android.view.View;
import android.view.animation.Animation;

/* loaded from: classes.dex */
public class ViewAnim {
    public static void rotateAnimation(View view, boolean z) {
        if (view != null) {
            view.startAnimation(AnimationObject.rotationAnimationRelativeToSelf(z ? 0.0f : 45.0f, z ? 45.0f : 0.0f, 0.5f, 0.5f, 200, true));
        }
    }

    public static void shrinkExpandAnimation(final View view) {
        if (view != null) {
            Animation scaleAnimationRelativeToSelf = AnimationObject.scaleAnimationRelativeToSelf(1.0f, 0.85f, 1.0f, 0.85f, 0.5f, 0.5f, 100, false);
            final Animation scaleAnimationRelativeToSelf2 = AnimationObject.scaleAnimationRelativeToSelf(0.85f, 1.0f, 0.85f, 1.0f, 0.5f, 0.5f, 100, false);
            view.startAnimation(scaleAnimationRelativeToSelf);
            scaleAnimationRelativeToSelf.setAnimationListener(new Animation.AnimationListener() { // from class: com.bvapp.arcmenulibrary.anim.ViewAnim.1
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
        }
    }
}
