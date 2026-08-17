package com.stephentuso.welcome;

import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;

/* loaded from: classes.dex */
public class FullscreenParallaxPage extends WelcomePage<FullscreenParallaxPage> {
    private int layoutResId;
    private float firstParallaxFactor = 0.2f;
    private float lastParallaxFactor = 1.0f;
    private boolean parallaxRecursive = false;

    public FullscreenParallaxPage(@LayoutRes int i) {
        this.layoutResId = i;
    }

    public FullscreenParallaxPage firstParallaxFactor(float f) {
        this.firstParallaxFactor = f;
        return this;
    }

    @Override // com.stephentuso.welcome.WelcomePage
    public Fragment fragment() {
        return WelcomeFullScreenParallaxFragment.newInstance(this.layoutResId, this.firstParallaxFactor, this.lastParallaxFactor, this.parallaxRecursive);
    }

    float getFirstParallaxFactor() {
        return this.firstParallaxFactor;
    }

    float getLastParallaxFactor() {
        return this.lastParallaxFactor;
    }

    int getLayoutResId() {
        return this.layoutResId;
    }

    boolean getParallaxRecursive() {
        return this.parallaxRecursive;
    }

    public FullscreenParallaxPage lastParallaxFactor(float f) {
        this.lastParallaxFactor = f;
        return this;
    }

    public FullscreenParallaxPage recursive(boolean z) {
        this.parallaxRecursive = z;
        return this;
    }
}
