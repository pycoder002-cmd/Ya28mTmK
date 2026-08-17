package com.daimajia.androidanimations.library.attention;

import android.animation.ObjectAnimator;
import android.view.View;
import com.daimajia.androidanimations.library.BaseViewAnimator;

/* loaded from: classes.dex */
public class WobbleAnimator extends BaseViewAnimator {
    @Override // com.daimajia.androidanimations.library.BaseViewAnimator
    public void prepare(View view) {
        float width = (float) (view.getWidth() / 100.0d);
        float f = 0.0f * width;
        getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "translationX", f, (-25.0f) * width, 20.0f * width, (-15.0f) * width, 10.0f * width, (-5.0f) * width, f, 0.0f), ObjectAnimator.ofFloat(view, "rotation", 0.0f, -5.0f, 3.0f, -3.0f, 2.0f, -1.0f, 0.0f));
    }
}
