package com.stephentuso.welcome;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

/* loaded from: classes.dex */
public class ParallaxPage extends WelcomePage<ParallaxPage> {
    private String description;
    private int layoutResId;
    private String title;
    private float firstParallaxFactor = 0.2f;
    private float lastParallaxFactor = 1.0f;
    private boolean parallaxRecursive = false;
    private String headerTypefacePath = null;
    private String descriptionTyefacePath = null;
    private int headerColor = -1;
    private int descriptionColor = -1;

    public ParallaxPage(@LayoutRes int i, String str, String str2) {
        this.layoutResId = i;
        this.title = str;
        this.description = str2;
    }

    public ParallaxPage descriptionColor(@ColorInt int i) {
        this.descriptionColor = i;
        return this;
    }

    public ParallaxPage descriptionColorResource(Context context, @ColorRes int i) {
        this.descriptionColor = ContextCompat.getColor(context, i);
        return this;
    }

    public ParallaxPage descriptionTypefacePath(String str) {
        this.descriptionTyefacePath = str;
        return this;
    }

    public ParallaxPage firstParallaxFactor(float f) {
        this.firstParallaxFactor = f;
        return this;
    }

    @Override // com.stephentuso.welcome.WelcomePage
    public Fragment fragment() {
        return WelcomeParallaxFragment.newInstance(this.layoutResId, this.title, this.description, this.firstParallaxFactor, this.lastParallaxFactor, this.parallaxRecursive, this.headerTypefacePath, this.descriptionTyefacePath, this.headerColor, this.descriptionColor);
    }

    String getDescription() {
        return this.description;
    }

    int getDescriptionColor() {
        return this.descriptionColor;
    }

    String getDescriptionTyefacePath() {
        return this.descriptionTyefacePath;
    }

    float getFirstParallaxFactor() {
        return this.firstParallaxFactor;
    }

    int getHeaderColor() {
        return this.headerColor;
    }

    String getHeaderTypefacePath() {
        return this.headerTypefacePath;
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

    String getTitle() {
        return this.title;
    }

    public ParallaxPage headerColor(@ColorInt int i) {
        this.headerColor = i;
        return this;
    }

    public ParallaxPage headerColorResource(Context context, @ColorRes int i) {
        this.headerColor = ContextCompat.getColor(context, i);
        return this;
    }

    public ParallaxPage headerTypeface(String str) {
        this.headerTypefacePath = str;
        return this;
    }

    public ParallaxPage lastParallaxFactor(float f) {
        this.lastParallaxFactor = f;
        return this;
    }

    public ParallaxPage recursive(boolean z) {
        this.parallaxRecursive = z;
        return this;
    }

    @Override // com.stephentuso.welcome.WelcomePage, com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        super.setup(welcomeConfiguration);
        if (this.headerTypefacePath == null) {
            headerTypeface(welcomeConfiguration.getDefaultHeaderTypefacePath());
        }
        if (this.descriptionTyefacePath == null) {
            descriptionTypefacePath(welcomeConfiguration.getDefaultDescriptionTypefacePath());
        }
    }
}
