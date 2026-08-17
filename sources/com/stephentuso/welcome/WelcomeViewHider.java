package com.stephentuso.welcome;

import android.os.Build;
import android.view.View;

/* loaded from: classes.dex */
class WelcomeViewHider implements OnWelcomeScreenPageChangeListener {
    private View view;
    private Integer lastPage = null;
    private boolean isRtl = false;
    private OnViewHiddenListener listener = null;
    private boolean enabled = false;

    /* loaded from: classes.dex */
    public interface OnViewHiddenListener {
        void onViewHidden();
    }

    public WelcomeViewHider(View view) {
        this.view = view;
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (this.enabled) {
            if (i == this.lastPage.intValue() && f == 0.0f && this.listener != null) {
                this.listener.onViewHidden();
            }
            if (Build.VERSION.SDK_INT < 11) {
                return;
            }
            boolean z = true;
            boolean z2 = i == (this.isRtl ? this.lastPage.intValue() : this.lastPage.intValue() - 1);
            if (!this.isRtl) {
                f = 1.0f - f;
            }
            if (!this.isRtl ? i >= this.lastPage.intValue() - 1 : i <= this.lastPage.intValue()) {
                z = false;
            }
            if (z2) {
                this.view.setAlpha(f);
            } else {
                if (!z || this.view.getAlpha() == 1.0f) {
                    return;
                }
                this.view.setAlpha(1.0f);
            }
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
    }

    public void setOnViewHiddenListener(OnViewHiddenListener onViewHiddenListener) {
        this.listener = onViewHiddenListener;
    }

    @Override // com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        this.enabled = welcomeConfiguration.getSwipeToDismiss();
        this.lastPage = Integer.valueOf(welcomeConfiguration.lastPageIndex());
        this.isRtl = welcomeConfiguration.isRtl();
    }
}
