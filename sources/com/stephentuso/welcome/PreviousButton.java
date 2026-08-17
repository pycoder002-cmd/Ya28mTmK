package com.stephentuso.welcome;

import android.view.View;

/* loaded from: classes.dex */
class PreviousButton extends WelcomeViewWrapper {
    private boolean shouldShow;

    public PreviousButton(View view) {
        super(view);
        this.shouldShow = false;
    }

    @Override // com.stephentuso.welcome.WelcomeViewWrapper
    public void onPageSelected(int i, int i2, int i3) {
        setVisibility(this.shouldShow && i != i2);
    }

    @Override // com.stephentuso.welcome.WelcomeViewWrapper, com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        super.setup(welcomeConfiguration);
        this.shouldShow = welcomeConfiguration.getShowPrevButton();
    }
}
