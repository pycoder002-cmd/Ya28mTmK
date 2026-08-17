package com.stephentuso.welcome;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import com.stephentuso.welcome.WelcomePage;

/* loaded from: classes.dex */
public abstract class WelcomePage<T extends WelcomePage> implements OnWelcomeScreenPageChangeListener {
    private Fragment fragment;
    private Integer backgroundColorResId = null;
    private BackgroundColor backgroundColor = null;
    protected int index = -2;
    protected boolean isRtl = false;
    protected int totalPages = 0;

    /* loaded from: classes.dex */
    public interface OnChangeListener {
        void onWelcomeScreenPageScrollStateChanged(int i, int i2);

        void onWelcomeScreenPageScrolled(int i, float f, int i2);

        void onWelcomeScreenPageSelected(int i, int i2);
    }

    public T background(@ColorRes int i) {
        this.backgroundColorResId = Integer.valueOf(i);
        this.backgroundColor = null;
        return this;
    }

    public T background(BackgroundColor backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.backgroundColorResId = null;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean backgroundIsSet() {
        return (this.backgroundColorResId == null && this.backgroundColor == null) ? false : true;
    }

    public Fragment createFragment() {
        this.fragment = fragment();
        return this.fragment;
    }

    protected abstract Fragment fragment();

    /* JADX INFO: Access modifiers changed from: package-private */
    public BackgroundColor getBackground(Context context) {
        if (this.backgroundColor == null) {
            this.backgroundColor = new BackgroundColor(ColorHelper.getColor(context, this.backgroundColorResId.intValue()));
        }
        return this.backgroundColor;
    }

    public Fragment getFragment() {
        return this.fragment;
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (getFragment() == null || !(getFragment() instanceof OnChangeListener)) {
            return;
        }
        ((OnChangeListener) getFragment()).onWelcomeScreenPageScrollStateChanged(this.index, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        boolean z = true;
        if (this.isRtl) {
            i = (this.totalPages - 1) - i;
        }
        if (getFragment() == null || !(getFragment() instanceof OnChangeListener) || i - this.index > 1) {
            return;
        }
        Fragment fragment = getFragment();
        int width = fragment.getView() != null ? fragment.getView().getWidth() : 0;
        if (!this.isRtl ? i >= this.index : i <= this.index) {
            z = false;
        }
        if (z) {
            f = -(1.0f - f);
        }
        if (z) {
            i2 = -(width - i2);
        }
        ((OnChangeListener) fragment).onWelcomeScreenPageScrolled(this.index, f, i2);
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (getFragment() == null || !(getFragment() instanceof OnChangeListener)) {
            return;
        }
        ((OnChangeListener) getFragment()).onWelcomeScreenPageSelected(this.index, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIndex(int i) {
        this.index = i;
    }

    @Override // com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        this.isRtl = welcomeConfiguration.isRtl();
        this.totalPages = welcomeConfiguration.pageCount();
    }
}
