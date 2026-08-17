package com.stephentuso.welcome;

import android.view.View;
import android.widget.TextView;

/* loaded from: classes.dex */
class DoneButton extends WelcomeViewWrapper {
    private boolean shouldShow;

    public DoneButton(View view) {
        super(view);
        this.shouldShow = true;
        if (view != null) {
            hideImmediately();
        }
    }

    @Override // com.stephentuso.welcome.WelcomeViewWrapper
    public void onPageSelected(int i, int i2, int i3) {
        setVisibility(this.shouldShow && !WelcomeUtils.isIndexBeforeLastPage(i, i3, this.isRtl));
    }

    @Override // com.stephentuso.welcome.WelcomeViewWrapper, com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        super.setup(welcomeConfiguration);
        this.shouldShow = !welcomeConfiguration.getUseCustomDoneButton();
        if (getView() instanceof TextView) {
            WelcomeUtils.setTypeface((TextView) getView(), welcomeConfiguration.getDoneButtonTypefacePath(), welcomeConfiguration.getContext());
        }
    }
}
