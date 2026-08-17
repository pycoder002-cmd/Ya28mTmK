package com.stephentuso.welcome;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: classes.dex */
public class WelcomeBackgroundView extends ColorChangingBackgroundView implements OnWelcomeScreenPageChangeListener {
    public WelcomeBackgroundView(Context context) {
        super(context);
    }

    public WelcomeBackgroundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WelcomeBackgroundView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        setPosition(i, f);
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
    }

    @Override // com.stephentuso.welcome.ColorChangingBackgroundView
    public /* bridge */ /* synthetic */ void setColors(BackgroundColor[] backgroundColorArr) {
        super.setColors(backgroundColorArr);
    }

    @Override // com.stephentuso.welcome.ColorChangingBackgroundView
    public /* bridge */ /* synthetic */ void setPosition(int i, float f) {
        super.setPosition(i, f);
    }

    @Override // com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        setColors(welcomeConfiguration.getBackgroundColors());
    }
}
