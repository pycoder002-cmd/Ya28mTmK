package com.stephentuso.welcome;

import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;

/* loaded from: classes.dex */
abstract class WelcomeViewWrapper implements OnWelcomeScreenPageChangeListener {
    private View view;
    private int firstPageIndex = 0;
    private int lastPageIndex = 0;
    private boolean animated = true;
    protected boolean isRtl = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WelcomeViewWrapper(View view) {
        this.view = view;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAlpha(float f) {
        if (Build.VERSION.SDK_INT >= 11) {
            this.view.setAlpha(f);
        }
    }

    private void show(boolean z) {
        if (z) {
            showWithAnimation();
        } else {
            showImmediately();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public View getView() {
        return this.view;
    }

    protected void hide(boolean z) {
        if (z) {
            hideWithAnimation();
        } else {
            hideImmediately();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void hideImmediately() {
        this.view.setVisibility(4);
    }

    protected void hideWithAnimation() {
        ViewCompat.animate(this.view).alpha(0.0f).setListener(new ViewPropertyAnimatorListener() { // from class: com.stephentuso.welcome.WelcomeViewWrapper.1
            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationCancel(View view) {
                WelcomeViewWrapper.this.setAlpha(0.0f);
                WelcomeViewWrapper.this.view.setVisibility(4);
            }

            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view) {
                WelcomeViewWrapper.this.setAlpha(0.0f);
                WelcomeViewWrapper.this.view.setVisibility(4);
            }

            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationStart(View view) {
            }
        }).start();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        onPageSelected(i, this.firstPageIndex, this.lastPageIndex);
    }

    public abstract void onPageSelected(int i, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.view.setOnClickListener(onClickListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setVisibility(boolean z) {
        setVisibility(z, this.animated);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setVisibility(boolean z, boolean z2) {
        this.view.setEnabled(z);
        if (z) {
            show(z2);
        } else {
            hide(z2);
        }
    }

    @Override // com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        this.firstPageIndex = welcomeConfiguration.firstPageIndex();
        this.lastPageIndex = welcomeConfiguration.lastViewablePageIndex();
        this.animated = welcomeConfiguration.getAnimateButtons();
        this.isRtl = welcomeConfiguration.isRtl();
    }

    protected void showImmediately() {
        setAlpha(1.0f);
        this.view.setVisibility(0);
    }

    protected void showWithAnimation() {
        ViewCompat.animate(this.view).alpha(1.0f).setListener(new ViewPropertyAnimatorListener() { // from class: com.stephentuso.welcome.WelcomeViewWrapper.2
            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationCancel(View view) {
                WelcomeViewWrapper.this.setAlpha(1.0f);
            }

            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view) {
                WelcomeViewWrapper.this.setAlpha(1.0f);
            }

            @Override // android.support.v4.view.ViewPropertyAnimatorListener
            public void onAnimationStart(View view) {
                WelcomeViewWrapper.this.view.setVisibility(0);
            }
        }).start();
    }
}
