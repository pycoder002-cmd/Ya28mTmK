package com.stephentuso.welcome;

import android.view.View;
import android.widget.TextView;

/* loaded from: classes.dex */
class SkipButton extends WelcomeViewWrapper {
    private boolean enabled;
    private boolean onlyShowOnFirstPage;

    public SkipButton(View view) {
        super(view);
        this.enabled = true;
        this.onlyShowOnFirstPage = false;
    }

    @Override // com.stephentuso.welcome.WelcomeViewWrapper
    public void onPageSelected(int i, int i2, int i3) {
        boolean z = false;
        if (this.onlyShowOnFirstPage) {
            if (this.enabled && i == i2) {
                z = true;
            }
            setVisibility(z);
            return;
        }
        if (this.enabled && WelcomeUtils.isIndexBeforeLastPage(i, i3, this.isRtl)) {
            z = true;
        }
        setVisibility(z);
    }

    @Override // com.stephentuso.welcome.WelcomeViewWrapper, com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        super.setup(welcomeConfiguration);
        this.onlyShowOnFirstPage = welcomeConfiguration.getShowPrevButton();
        this.enabled = welcomeConfiguration.getCanSkip();
        setVisibility(this.enabled, false);
        if (getView() instanceof TextView) {
            WelcomeUtils.setTypeface((TextView) getView(), welcomeConfiguration.getSkipButtonTypefacePath(), welcomeConfiguration.getContext());
        }
    }
}
