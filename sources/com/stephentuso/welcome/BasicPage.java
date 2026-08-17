package com.stephentuso.welcome;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/* loaded from: classes.dex */
public class BasicPage extends WelcomePage<BasicPage> {
    private String description;
    private int drawableResId;
    private String title;
    private boolean showParallax = true;
    private String headerTypefacePath = null;
    private String descriptionTypefacePath = null;
    private int headerColor = -1;
    private int descriptionColor = -1;

    public BasicPage(@DrawableRes int i, String str, String str2) {
        this.drawableResId = i;
        this.title = str;
        this.description = str2;
    }

    public BasicPage descriptionColor(@ColorInt int i) {
        this.descriptionColor = i;
        return this;
    }

    public BasicPage descriptionColorResource(Context context, @ColorRes int i) {
        this.descriptionColor = ContextCompat.getColor(context, i);
        return this;
    }

    public BasicPage descriptionTypeface(String str) {
        this.descriptionTypefacePath = str;
        return this;
    }

    @Override // com.stephentuso.welcome.WelcomePage
    public Fragment fragment() {
        return WelcomeBasicFragment.newInstance(this.drawableResId, this.title, this.description, this.showParallax, this.headerTypefacePath, this.descriptionTypefacePath, this.headerColor, this.descriptionColor);
    }

    String getDescription() {
        return this.description;
    }

    int getDescriptionColor() {
        return this.descriptionColor;
    }

    String getDescriptionTypefacePath() {
        return this.descriptionTypefacePath;
    }

    int getDrawableResId() {
        return this.drawableResId;
    }

    int getHeaderColor() {
        return this.headerColor;
    }

    String getHeaderTypefacePath() {
        return this.headerTypefacePath;
    }

    boolean getShowParallax() {
        return this.showParallax;
    }

    String getTitle() {
        return this.title;
    }

    public BasicPage headerColor(@ColorInt int i) {
        this.headerColor = i;
        return this;
    }

    public BasicPage headerColorResource(Context context, @ColorRes int i) {
        this.headerColor = ContextCompat.getColor(context, i);
        return this;
    }

    public BasicPage headerTypeface(String str) {
        this.headerTypefacePath = str;
        return this;
    }

    public BasicPage parallax(boolean z) {
        this.showParallax = z;
        return this;
    }

    @Override // com.stephentuso.welcome.WelcomePage, com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        super.setup(welcomeConfiguration);
        if (this.headerTypefacePath == null) {
            headerTypeface(welcomeConfiguration.getDefaultHeaderTypefacePath());
        }
        if (this.descriptionTypefacePath == null) {
            descriptionTypeface(welcomeConfiguration.getDefaultDescriptionTypefacePath());
        }
    }
}
