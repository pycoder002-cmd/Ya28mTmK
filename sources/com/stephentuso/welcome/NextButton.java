package com.stephentuso.welcome;

import android.view.View;

/* loaded from: classes.dex */
class NextButton extends WelcomeViewWrapper {
    private boolean shouldShow;

    public NextButton(View view) {
        super(view);
        this.shouldShow = true;
    }

    @Override // com.stephentuso.welcome.WelcomeViewWrapper
    public void onPageSelected(int i, int i2, int i3) {
        setVisibility(this.shouldShow && WelcomeUtils.isIndexBeforeLastPage(i, i3, this.isRtl));
    }

    @Override // com.stephentuso.welcome.WelcomeViewWrapper, com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        super.setup(welcomeConfiguration);
        this.shouldShow = welcomeConfiguration.getShowNextButton();
    }
}
