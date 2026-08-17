package com.stephentuso.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import com.stephentuso.welcome.WelcomeViewHider;

/* loaded from: classes.dex */
public abstract class WelcomeActivity extends AppCompatActivity {
    public static final String WELCOME_SCREEN_KEY = "welcome_screen_key";
    private WelcomeFragmentPagerAdapter adapter;
    private WelcomeConfiguration configuration;
    private WelcomeItemList responsiveItems = new WelcomeItemList(new OnWelcomeScreenPageChangeListener[0]);
    protected ViewPager viewPager;

    /* loaded from: classes.dex */
    private class WelcomeFragmentPagerAdapter extends FragmentPagerAdapter {
        public WelcomeFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return WelcomeActivity.this.configuration.pageCount();
        }

        @Override // android.support.v4.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            return WelcomeActivity.this.configuration.createFragment(i);
        }
    }

    private void addViewWrapper(WelcomeViewWrapper welcomeViewWrapper, View.OnClickListener onClickListener) {
        if (welcomeViewWrapper.getView() != null) {
            welcomeViewWrapper.setOnClickListener(onClickListener);
            this.responsiveItems.add(welcomeViewWrapper);
        }
    }

    private String getKey() {
        return WelcomeUtils.getKey(getClass());
    }

    private void setWelcomeScreenResult(int i) {
        Intent intent = getIntent();
        intent.putExtra(WELCOME_SCREEN_KEY, getKey());
        setResult(i, intent);
    }

    protected boolean canScrollToNextPage() {
        if (this.configuration.isRtl()) {
            if (getNextPageIndex() < this.configuration.lastViewablePageIndex()) {
                return false;
            }
        } else if (getNextPageIndex() > this.configuration.lastViewablePageIndex()) {
            return false;
        }
        return true;
    }

    protected boolean canScrollToPreviousPage() {
        if (this.configuration.isRtl()) {
            if (getPreviousPageIndex() > this.configuration.firstPageIndex()) {
                return false;
            }
        } else if (getPreviousPageIndex() < this.configuration.firstPageIndex()) {
            return false;
        }
        return true;
    }

    protected void cancelWelcomeScreen() {
        setWelcomeScreenResult(0);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void completeWelcomeScreen() {
        WelcomeSharedPreferencesHelper.storeWelcomeCompleted(this, getKey());
        setWelcomeScreenResult(-1);
        finish();
        if (this.configuration.getExitAnimation() != -1) {
            overridePendingTransition(R.anim.wel_none, this.configuration.getExitAnimation());
        }
    }

    protected abstract WelcomeConfiguration configuration();

    protected int getNextPageIndex() {
        return this.viewPager.getCurrentItem() + (this.configuration.isRtl() ? -1 : 1);
    }

    protected int getPreviousPageIndex() {
        return this.viewPager.getCurrentItem() + (this.configuration.isRtl() ? 1 : -1);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.configuration.getBackButtonNavigatesPages() && scrollToPreviousPage()) {
            return;
        }
        if (this.configuration.getCanSkip() && this.configuration.getBackButtonSkips()) {
            completeWelcomeScreen();
        } else {
            cancelWelcomeScreen();
        }
    }

    protected void onButtonBarFirstPressed() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onButtonBarSecondPressed() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        ActionBar supportActionBar;
        this.configuration = configuration();
        super.onCreate(null);
        setContentView(R.layout.wel_activity_welcome);
        this.adapter = new WelcomeFragmentPagerAdapter(getSupportFragmentManager());
        this.viewPager = (ViewPager) findViewById(R.id.wel_view_pager);
        this.viewPager.setAdapter(this.adapter);
        this.responsiveItems = new WelcomeItemList(new OnWelcomeScreenPageChangeListener[0]);
        View.inflate(this, this.configuration.getBottomLayoutResId(), (FrameLayout) findViewById(R.id.wel_bottom_frame));
        if (this.configuration.getShowActionBarBackButton() && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        addViewWrapper(new SkipButton(findViewById(R.id.wel_button_skip)), new View.OnClickListener() { // from class: com.stephentuso.welcome.WelcomeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WelcomeActivity.this.completeWelcomeScreen();
            }
        });
        addViewWrapper(new PreviousButton(findViewById(R.id.wel_button_prev)), new View.OnClickListener() { // from class: com.stephentuso.welcome.WelcomeActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WelcomeActivity.this.scrollToPreviousPage();
            }
        });
        addViewWrapper(new NextButton(findViewById(R.id.wel_button_next)), new View.OnClickListener() { // from class: com.stephentuso.welcome.WelcomeActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WelcomeActivity.this.scrollToNextPage();
            }
        });
        addViewWrapper(new DoneButton(findViewById(R.id.wel_button_done)), new View.OnClickListener() { // from class: com.stephentuso.welcome.WelcomeActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WelcomeActivity.this.completeWelcomeScreen();
            }
        });
        View findViewById = findViewById(R.id.wel_button_bar_first);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.stephentuso.welcome.WelcomeActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WelcomeActivity.this.onButtonBarFirstPressed();
                }
            });
        }
        View findViewById2 = findViewById(R.id.wel_button_bar_second);
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.stephentuso.welcome.WelcomeActivity.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    WelcomeActivity.this.onButtonBarSecondPressed();
                }
            });
        }
        WelcomeViewPagerIndicator welcomeViewPagerIndicator = (WelcomeViewPagerIndicator) findViewById(R.id.wel_pager_indicator);
        if (welcomeViewPagerIndicator != null) {
            this.responsiveItems.add(welcomeViewPagerIndicator);
        }
        WelcomeBackgroundView welcomeBackgroundView = (WelcomeBackgroundView) findViewById(R.id.wel_background_view);
        WelcomeViewHider welcomeViewHider = new WelcomeViewHider(findViewById(R.id.wel_root));
        welcomeViewHider.setOnViewHiddenListener(new WelcomeViewHider.OnViewHiddenListener() { // from class: com.stephentuso.welcome.WelcomeActivity.7
            @Override // com.stephentuso.welcome.WelcomeViewHider.OnViewHiddenListener
            public void onViewHidden() {
                WelcomeActivity.this.completeWelcomeScreen();
            }
        });
        this.responsiveItems.addAll(welcomeBackgroundView, welcomeViewHider, this.configuration.getPages());
        this.responsiveItems.setup(this.configuration);
        this.viewPager.addOnPageChangeListener(this.responsiveItems);
        this.viewPager.setCurrentItem(this.configuration.firstPageIndex());
        this.responsiveItems.onPageSelected(this.viewPager.getCurrentItem());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.viewPager != null) {
            this.viewPager.clearOnPageChangeListeners();
        }
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (!this.configuration.getShowActionBarBackButton() || menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    boolean scrollToNextPage() {
        if (!canScrollToNextPage()) {
            return false;
        }
        this.viewPager.setCurrentItem(getNextPageIndex());
        return true;
    }

    boolean scrollToPreviousPage() {
        if (!canScrollToPreviousPage()) {
            return false;
        }
        this.viewPager.setCurrentItem(getPreviousPageIndex());
        return true;
    }
}
