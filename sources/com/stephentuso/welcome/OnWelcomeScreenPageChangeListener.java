package com.stephentuso.welcome;

import android.support.v4.view.ViewPager;

/* loaded from: classes.dex */
interface OnWelcomeScreenPageChangeListener extends ViewPager.OnPageChangeListener {
    void setup(WelcomeConfiguration welcomeConfiguration);
}
