package com.stephentuso.welcome;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/* loaded from: classes.dex */
public class TitlePage extends WelcomePage<TitlePage> {
    private int drawableResId;
    private String title;
    private boolean showParallax = true;
    private String titleTypefacePath = null;
    private int titleColor = -1;

    public TitlePage(@DrawableRes int i, String str) {
        this.drawableResId = i;
        this.title = str;
    }

    @Override // com.stephentuso.welcome.WelcomePage
    public Fragment fragment() {
        return WelcomeTitleFragment.newInstance(this.drawableResId, this.title, this.showParallax, this.titleTypefacePath, this.titleColor);
    }

    int getDrawableResId() {
        return this.drawableResId;
    }

    boolean getShowParallax() {
        return this.showParallax;
    }

    String getTitle() {
        return this.title;
    }

    int getTitleColor() {
        return this.titleColor;
    }

    String getTitleTypefacePath() {
        return this.titleTypefacePath;
    }

    public TitlePage parallax(boolean z) {
        this.showParallax = z;
        return this;
    }

    @Override // com.stephentuso.welcome.WelcomePage, com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        super.setup(welcomeConfiguration);
        if (this.titleTypefacePath == null) {
            titleTypeface(welcomeConfiguration.getDefaultTitleTypefacePath());
        }
    }

    public TitlePage titleColor(@ColorInt int i) {
        this.titleColor = i;
        return this;
    }

    public TitlePage titleColorResource(Context context, @ColorRes int i) {
        this.titleColor = ContextCompat.getColor(context, i);
        return this;
    }

    public TitlePage titleTypeface(String str) {
        this.titleTypefacePath = str;
        return this;
    }
}
