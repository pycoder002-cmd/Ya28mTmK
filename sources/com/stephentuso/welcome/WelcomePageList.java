package com.stephentuso.welcome;

import android.content.Context;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes.dex */
public class WelcomePageList extends ArrayList<WelcomePage> implements OnWelcomeScreenPageChangeListener {
    public WelcomePageList(WelcomePage... welcomePageArr) {
        super(Arrays.asList(welcomePageArr));
    }

    public BackgroundColor getBackgroundColor(Context context, int i) {
        return get(i).getBackground(context);
    }

    public BackgroundColor[] getBackgroundColors(Context context) {
        ArrayList arrayList = new ArrayList();
        Iterator<WelcomePage> it = iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getBackground(context));
        }
        return (BackgroundColor[]) arrayList.toArray(new BackgroundColor[1]);
    }

    public Fragment getFragment(int i) {
        return get(i).getFragment();
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        Iterator<WelcomePage> it = iterator();
        while (it.hasNext()) {
            it.next().onPageScrollStateChanged(i);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        Iterator<WelcomePage> it = iterator();
        while (it.hasNext()) {
            it.next().onPageScrolled(i, f, i2);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        Iterator<WelcomePage> it = iterator();
        while (it.hasNext()) {
            it.next().onPageSelected(i);
        }
    }

    public void reversePageOrder() {
        Collections.reverse(this);
    }

    @Override // com.stephentuso.welcome.OnWelcomeScreenPageChangeListener
    public void setup(WelcomeConfiguration welcomeConfiguration) {
        Iterator<WelcomePage> it = iterator();
        while (it.hasNext()) {
            it.next().setup(welcomeConfiguration);
        }
    }
}
