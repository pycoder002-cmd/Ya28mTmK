package com.stephentuso.welcome;

import android.content.Context;
import android.os.Build;
import android.support.annotation.AnimRes;
import android.support.annotation.ColorRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;

/* loaded from: classes.dex */
public class WelcomeConfiguration {
    public static final int NO_ANIMATION_SET = -1;
    private Builder builder;
    private WelcomePageList pages = new WelcomePageList(new WelcomePage[0]);

    /* loaded from: classes.dex */
    public enum BottomLayout {
        STANDARD(R.layout.wel_bottom_standard),
        STANDARD_DONE_IMAGE(R.layout.wel_bottom_done_image),
        BUTTON_BAR(R.layout.wel_bottom_button_bar),
        BUTTON_BAR_SINGLE(R.layout.wel_bottom_single_button),
        INDICATOR_ONLY(R.layout.wel_bottom_indicator),
        NONE(R.layout.wel_bottom_none);


        @LayoutRes
        final int resId;

        BottomLayout(@LayoutRes int i) {
            this.resId = i;
        }
    }

    /* loaded from: classes.dex */
    public static class Builder {
        private Context context;
        private BackgroundColor defaultBackgroundColor;
        private WelcomePageList pages = new WelcomePageList(new WelcomePage[0]);
        private boolean canSkip = true;
        private boolean backButtonSkips = true;
        private boolean backButtonNavigatesPages = true;
        private boolean swipeToDismiss = false;
        private int exitAnimationResId = -1;
        private String skipButtonTypefacePath = null;
        private String doneButtonTypefacePath = null;
        private String defaultTitleTypefacePath = null;
        private String defaultHeaderTypefacePath = null;
        private String defaultDescriptionTypefacePath = null;
        private boolean animateButtons = true;
        private boolean useCustomDoneButton = false;
        private boolean showNextButton = true;
        private boolean showPrevButton = false;
        private boolean showActionBarBackButton = false;
        private int bottomLayoutRes = BottomLayout.STANDARD.resId;

        public Builder(Context context) {
            this.context = context;
            initDefaultBackgroundColor(this.context);
        }

        private void initDefaultBackgroundColor(Context context) {
            int color = ColorHelper.getColor(context, R.color.wel_default_background_color);
            int resolveColorAttribute = ColorHelper.resolveColorAttribute(context, R.attr.colorPrimary, color);
            if (resolveColorAttribute == color && Build.VERSION.SDK_INT >= 21) {
                resolveColorAttribute = ColorHelper.resolveColorAttribute(context, android.R.attr.colorPrimary, resolveColorAttribute);
            }
            this.defaultBackgroundColor = new BackgroundColor(Integer.valueOf(resolveColorAttribute), color);
        }

        public Builder animateButtons(boolean z) {
            this.animateButtons = z;
            return this;
        }

        public Builder backButtonNavigatesPages(boolean z) {
            this.backButtonNavigatesPages = z;
            return this;
        }

        public Builder backButtonSkips(boolean z) {
            this.backButtonSkips = z;
            return this;
        }

        public Builder bottomLayout(BottomLayout bottomLayout) {
            this.bottomLayoutRes = bottomLayout.resId;
            return this;
        }

        public WelcomeConfiguration build() {
            return new WelcomeConfiguration(this);
        }

        public Builder canSkip(boolean z) {
            this.canSkip = z;
            return this;
        }

        public Builder defaultBackgroundColor(@ColorRes int i) {
            this.defaultBackgroundColor = new BackgroundColor(ColorHelper.getColor(this.context, i));
            return this;
        }

        public Builder defaultBackgroundColor(BackgroundColor backgroundColor) {
            this.defaultBackgroundColor = backgroundColor;
            return this;
        }

        public Builder defaultDescriptionTypefacePath(String str) {
            this.defaultDescriptionTypefacePath = str;
            return this;
        }

        public Builder defaultHeaderTypefacePath(String str) {
            this.defaultHeaderTypefacePath = str;
            return this;
        }

        public Builder defaultTitleTypefacePath(String str) {
            this.defaultTitleTypefacePath = str;
            return this;
        }

        public Builder doneButtonTypefacePath(String str) {
            this.doneButtonTypefacePath = str;
            return this;
        }

        public Builder exitAnimation(@AnimRes int i) {
            this.exitAnimationResId = i;
            return this;
        }

        public Builder page(WelcomePage welcomePage) {
            welcomePage.setIndex(this.pages.size());
            if (!welcomePage.backgroundIsSet()) {
                welcomePage.background(this.defaultBackgroundColor);
            }
            this.pages.add(welcomePage);
            return this;
        }

        public Builder showActionBarBackButton(boolean z) {
            this.showActionBarBackButton = z;
            return this;
        }

        public Builder showNextButton(boolean z) {
            this.showNextButton = z;
            return this;
        }

        public Builder showPrevButton(boolean z) {
            this.showPrevButton = z;
            return this;
        }

        public Builder skipButtonTypefacePath(String str) {
            this.skipButtonTypefacePath = str;
            return this;
        }

        public Builder swipeToDismiss(boolean z) {
            this.swipeToDismiss = z;
            return this;
        }

        public Builder useCustomDoneButton(boolean z) {
            this.useCustomDoneButton = z;
            return this;
        }
    }

    public WelcomeConfiguration(Builder builder) {
        this.builder = builder;
        this.pages.addAll(builder.pages);
        if (pageCount() == 0) {
            throw new IllegalStateException("0 pages; at least one page must be added");
        }
        if (getSwipeToDismiss()) {
            this.pages.add(new FragmentWelcomePage() { // from class: com.stephentuso.welcome.WelcomeConfiguration.1
                @Override // com.stephentuso.welcome.WelcomePage
                protected Fragment fragment() {
                    return new Fragment();
                }
            }.background(this.pages.getBackgroundColor(getContext(), pageCount() - 1)));
        }
        if (isRtl()) {
            this.pages.reversePageOrder();
        }
    }

    public Fragment createFragment(int i) {
        return this.pages.get(i).createFragment();
    }

    public int firstPageIndex() {
        if (isRtl()) {
            return this.pages.size() - 1;
        }
        return 0;
    }

    public boolean getAnimateButtons() {
        return this.builder.animateButtons;
    }

    public boolean getBackButtonNavigatesPages() {
        return this.builder.backButtonNavigatesPages;
    }

    public boolean getBackButtonSkips() {
        return this.builder.backButtonSkips;
    }

    public BackgroundColor[] getBackgroundColors() {
        return this.pages.getBackgroundColors(getContext());
    }

    @LayoutRes
    public int getBottomLayoutResId() {
        return this.builder.bottomLayoutRes;
    }

    public boolean getCanSkip() {
        return this.builder.canSkip;
    }

    public Context getContext() {
        return this.builder.context;
    }

    public BackgroundColor getDefaultBackgroundColor() {
        return this.builder.defaultBackgroundColor;
    }

    public String getDefaultDescriptionTypefacePath() {
        return this.builder.defaultDescriptionTypefacePath;
    }

    public String getDefaultHeaderTypefacePath() {
        return this.builder.defaultHeaderTypefacePath;
    }

    public String getDefaultTitleTypefacePath() {
        return this.builder.defaultTitleTypefacePath;
    }

    public String getDoneButtonTypefacePath() {
        return this.builder.doneButtonTypefacePath;
    }

    @AnimRes
    public int getExitAnimation() {
        return this.builder.exitAnimationResId;
    }

    public Fragment getFragment(int i) {
        return this.pages.getFragment(i);
    }

    public WelcomePageList getPages() {
        return this.pages;
    }

    public boolean getShowActionBarBackButton() {
        return this.builder.showActionBarBackButton;
    }

    public boolean getShowNextButton() {
        return this.builder.showNextButton;
    }

    public boolean getShowPrevButton() {
        return this.builder.showPrevButton;
    }

    public String getSkipButtonTypefacePath() {
        return this.builder.skipButtonTypefacePath;
    }

    public boolean getSwipeToDismiss() {
        return this.builder.swipeToDismiss && Build.VERSION.SDK_INT >= 11;
    }

    public boolean getUseCustomDoneButton() {
        return this.builder.useCustomDoneButton;
    }

    public boolean isRtl() {
        return this.builder.context.getResources().getBoolean(R.bool.wel_is_rtl);
    }

    public int lastPageIndex() {
        if (isRtl()) {
            return 0;
        }
        return this.pages.size() - 1;
    }

    public int lastViewablePageIndex() {
        return getSwipeToDismiss() ? Math.abs(lastPageIndex() - 1) : lastPageIndex();
    }

    public int pageCount() {
        return this.pages.size();
    }

    public int viewablePageCount() {
        return getSwipeToDismiss() ? pageCount() - 1 : pageCount();
    }
}
