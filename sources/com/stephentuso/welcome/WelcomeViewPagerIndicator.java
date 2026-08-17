package com.stephentuso.welcome;

import android.content.Context;
import android.util.AttributeSet;
import com.stephentuso.welcome.SimpleViewPagerIndicator;

/* loaded from: classes.dex */
public class WelcomeViewPagerIndicator extends SimpleViewPagerIndicator implements OnWelcomeScreenPageChangeListener {
    public WelcomeViewPagerIndicator(Context context) {
        super(context);
    }

    public WelcomeViewPagerIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public WelcomeViewPagerIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ int getDisplayedPosition() {
        return super.getDisplayedPosition();
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ SimpleViewPagerIndicator.Animation getIndicatorAnimation() {
        return super.getIndicatorAnimation();
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ int getPageIndexOffset() {
        return super.getPageIndexOffset();
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ int getPosition() {
        return super.getPosition();
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ int getTotalPages() {
        return super.getTotalPages();
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ boolean isRtl() {
        return super.isRtl();
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator, android.support.v4.view.ViewPager.OnPageChangeListener
    public /* bridge */ /* synthetic */ void onPageScrollStateChanged(int i) {
        super.onPageScrollStateChanged(i);
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator, android.support.v4.view.ViewPager.OnPageChangeListener
    public /* bridge */ /* synthetic */ void onPageScrolled(int i, float f, int i2) {
        super.onPageScrolled(i, f, i2);
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator, android.support.v4.view.ViewPager.OnPageChangeListener
    public /* bridge */ /* synthetic */ void onPageSelected(int i) {
        super.onPageSelected(i);
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ void setIndicatorAnimation(SimpleViewPagerIndicator.Animation animation) {
        super.setIndicatorAnimation(animation);
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ void setPageIndexOffset(int i) {
        super.setPageIndexOffset(i);
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ void setPosition(int i) {
        super.setPosition(i);
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ void setRtl(boolean z) {
        super.setRtl(z);
    }

    @Override // com.stephentuso.welcome.SimpleViewPagerIndicator
    public /* bridge */ /* synthetic */ void setTotalPages(int i) {
        super.setTotalPages(i);
    }

    @Override // com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        setTotalPages(welcomeConfiguration.viewablePageCount());
        if (welcomeConfiguration.isRtl()) {
            setRtl(true);
            if (welcomeConfiguration.getSwipeToDismiss()) {
                setPageIndexOffset(-1);
            }
        }
    }
}
