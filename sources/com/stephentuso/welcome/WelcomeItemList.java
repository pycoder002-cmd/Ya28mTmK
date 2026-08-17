package com.stephentuso.welcome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* loaded from: classes.dex */
class WelcomeItemList extends ArrayList<OnWelcomeScreenPageChangeListener> implements OnWelcomeScreenPageChangeListener {
    /* JADX INFO: Access modifiers changed from: package-private */
    public WelcomeItemList(OnWelcomeScreenPageChangeListener... onWelcomeScreenPageChangeListenerArr) {
        super(Arrays.asList(onWelcomeScreenPageChangeListenerArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAll(OnWelcomeScreenPageChangeListener... onWelcomeScreenPageChangeListenerArr) {
        super.addAll(Arrays.asList(onWelcomeScreenPageChangeListenerArr));
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        Iterator<OnWelcomeScreenPageChangeListener> it = iterator();
        while (it.hasNext()) {
            it.next().onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        Iterator<OnWelcomeScreenPageChangeListener> it = iterator();
        while (it.hasNext()) {
            it.next().onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        Iterator<OnWelcomeScreenPageChangeListener> it = iterator();
        while (it.hasNext()) {
            it.next().onPageSelected(i);
        }
    }

    @Override // com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        Iterator<OnWelcomeScreenPageChangeListener> it = iterator();
        while (it.hasNext()) {
            it.next().setup(welcomeConfiguration);
        }
    }
}
