package com.ogaclejapan.smarttablayout;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/* loaded from: classes.dex */
public class SmartTabLayout extends HorizontalScrollView {
    private static final boolean DEFAULT_DISTRIBUTE_EVENLY = false;
    private static final boolean TAB_CLICKABLE = true;
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    private static final boolean TAB_VIEW_TEXT_ALL_CAPS = true;
    private static final int TAB_VIEW_TEXT_COLOR = -67108864;
    private static final int TAB_VIEW_TEXT_MIN_WIDTH = 0;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 12;
    private static final int TITLE_OFFSET_AUTO_CENTER = -1;
    private static final int TITLE_OFFSET_DIPS = 24;
    private boolean distributeEvenly;
    private InternalTabClickListener internalTabClickListener;
    private OnScrollChangeListener onScrollChangeListener;
    private OnTabClickListener onTabClickListener;
    private TabProvider tabProvider;
    protected final SmartTabStrip tabStrip;
    private int tabViewBackgroundResId;
    private boolean tabViewTextAllCaps;
    private ColorStateList tabViewTextColors;
    private int tabViewTextHorizontalPadding;
    private int tabViewTextMinWidth;
    private float tabViewTextSize;
    private int titleOffset;
    private ViewPager viewPager;
    private ViewPager.OnPageChangeListener viewPagerPageChangeListener;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class InternalTabClickListener implements View.OnClickListener {
        private InternalTabClickListener() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            for (int i = 0; i < SmartTabLayout.this.tabStrip.getChildCount(); i++) {
                if (view == SmartTabLayout.this.tabStrip.getChildAt(i)) {
                    if (SmartTabLayout.this.onTabClickListener != null) {
                        SmartTabLayout.this.onTabClickListener.onTabClicked(i);
                    }
                    SmartTabLayout.this.viewPager.setCurrentItem(i);
                    return;
                }
            }
        }
    }

    /* loaded from: classes.dex */
    private class InternalViewPagerListener implements ViewPager.OnPageChangeListener {
        private int scrollState;

        private InternalViewPagerListener() {
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            this.scrollState = i;
            if (SmartTabLayout.this.viewPagerPageChangeListener != null) {
                SmartTabLayout.this.viewPagerPageChangeListener.onPageScrollStateChanged(i);
            }
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            int childCount = SmartTabLayout.this.tabStrip.getChildCount();
            if (childCount == 0 || i < 0 || i >= childCount) {
                return;
            }
            SmartTabLayout.this.tabStrip.onViewPagerPageChanged(i, f);
            SmartTabLayout.this.scrollToTab(i, f);
            if (SmartTabLayout.this.viewPagerPageChangeListener != null) {
                SmartTabLayout.this.viewPagerPageChangeListener.onPageScrolled(i, f, i2);
            }
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            if (this.scrollState == 0) {
                SmartTabLayout.this.tabStrip.onViewPagerPageChanged(i, 0.0f);
                SmartTabLayout.this.scrollToTab(i, 0.0f);
            }
            int childCount = SmartTabLayout.this.tabStrip.getChildCount();
            int i2 = 0;
            while (i2 < childCount) {
                SmartTabLayout.this.tabStrip.getChildAt(i2).setSelected(i == i2);
                i2++;
            }
            if (SmartTabLayout.this.viewPagerPageChangeListener != null) {
                SmartTabLayout.this.viewPagerPageChangeListener.onPageSelected(i);
            }
        }
    }

    /* loaded from: classes.dex */
    public interface OnScrollChangeListener {
        void onScrollChanged(int i, int i2);
    }

    /* loaded from: classes.dex */
    public interface OnTabClickListener {
        void onTabClicked(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SimpleTabProvider implements TabProvider {
        private final LayoutInflater inflater;
        private final int tabViewLayoutId;
        private final int tabViewTextViewId;

        private SimpleTabProvider(Context context, int i, int i2) {
            this.inflater = LayoutInflater.from(context);
            this.tabViewLayoutId = i;
            this.tabViewTextViewId = i2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v3, types: [android.view.View] */
        @Override // com.ogaclejapan.smarttablayout.SmartTabLayout.TabProvider
        public View createTabView(ViewGroup viewGroup, int i, PagerAdapter pagerAdapter) {
            TextView textView = null;
            TextView inflate = this.tabViewLayoutId != -1 ? this.inflater.inflate(this.tabViewLayoutId, viewGroup, false) : null;
            if (this.tabViewTextViewId != -1 && inflate != null) {
                textView = (TextView) inflate.findViewById(this.tabViewTextViewId);
            }
            if (textView == null && TextView.class.isInstance(inflate)) {
                textView = inflate;
            }
            if (textView != null) {
                textView.setText(pagerAdapter.getPageTitle(i));
            }
            return inflate;
        }
    }

    /* loaded from: classes.dex */
    public interface TabColorizer {
        int getDividerColor(int i);

        int getIndicatorColor(int i);
    }

    /* loaded from: classes.dex */
    public interface TabProvider {
        View createTabView(ViewGroup viewGroup, int i, PagerAdapter pagerAdapter);
    }

    public SmartTabLayout(Context context) {
        this(context, null);
    }

    public SmartTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SmartTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setHorizontalScrollBarEnabled(false);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float f = displayMetrics.density;
        float applyDimension = TypedValue.applyDimension(2, 12.0f, displayMetrics);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.stl_SmartTabLayout, i, 0);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_defaultTabBackground, -1);
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_defaultTabTextAllCaps, true);
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.stl_SmartTabLayout_stl_defaultTabTextColor);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.stl_SmartTabLayout_stl_defaultTabTextSize, applyDimension);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_defaultTabTextHorizontalPadding, (int) (16.0f * f));
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.stl_SmartTabLayout_stl_defaultTabTextMinWidth, (int) (0.0f * f));
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_customTabTextLayoutId, -1);
        int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.stl_SmartTabLayout_stl_customTabTextViewId, -1);
        boolean z2 = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_distributeEvenly, false);
        boolean z3 = obtainStyledAttributes.getBoolean(R.styleable.stl_SmartTabLayout_stl_clickable, true);
        int layoutDimension = obtainStyledAttributes.getLayoutDimension(R.styleable.stl_SmartTabLayout_stl_titleOffset, (int) (24.0f * f));
        obtainStyledAttributes.recycle();
        this.titleOffset = layoutDimension;
        this.tabViewBackgroundResId = resourceId;
        this.tabViewTextAllCaps = z;
        this.tabViewTextColors = colorStateList == null ? ColorStateList.valueOf(TAB_VIEW_TEXT_COLOR) : colorStateList;
        this.tabViewTextSize = dimension;
        this.tabViewTextHorizontalPadding = dimensionPixelSize;
        this.tabViewTextMinWidth = dimensionPixelSize2;
        this.internalTabClickListener = z3 ? new InternalTabClickListener() : null;
        this.distributeEvenly = z2;
        if (resourceId2 != -1) {
            setCustomTabView(resourceId2, resourceId3);
        }
        this.tabStrip = new SmartTabStrip(context, attributeSet);
        if (z2 && this.tabStrip.isIndicatorAlwaysInCenter()) {
            throw new UnsupportedOperationException("'distributeEvenly' and 'indicatorAlwaysInCenter' both use does not support");
        }
        setFillViewport(!this.tabStrip.isIndicatorAlwaysInCenter());
        addView(this.tabStrip, -1, -1);
    }

    private void populateTabStrip() {
        PagerAdapter adapter = this.viewPager.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            View createDefaultTabView = this.tabProvider == null ? createDefaultTabView(adapter.getPageTitle(i)) : this.tabProvider.createTabView(this.tabStrip, i, adapter);
            if (createDefaultTabView == null) {
                throw new IllegalStateException("tabView is null.");
            }
            if (this.distributeEvenly) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) createDefaultTabView.getLayoutParams();
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            }
            if (this.internalTabClickListener != null) {
                createDefaultTabView.setOnClickListener(this.internalTabClickListener);
            }
            this.tabStrip.addView(createDefaultTabView);
            if (i == this.viewPager.getCurrentItem()) {
                createDefaultTabView.setSelected(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollToTab(int i, float f) {
        int i2;
        int childCount = this.tabStrip.getChildCount();
        if (childCount == 0 || i < 0 || i >= childCount) {
            return;
        }
        boolean isLayoutRtl = Utils.isLayoutRtl(this);
        View childAt = this.tabStrip.getChildAt(i);
        int width = (int) ((Utils.getWidth(childAt) + Utils.getMarginHorizontally(childAt)) * f);
        if (this.tabStrip.isIndicatorAlwaysInCenter()) {
            if (0.0f < f && f < 1.0f) {
                View childAt2 = this.tabStrip.getChildAt(i + 1);
                width = Math.round(f * ((Utils.getWidth(childAt) / 2) + Utils.getMarginEnd(childAt) + (Utils.getWidth(childAt2) / 2) + Utils.getMarginStart(childAt2)));
            }
            View childAt3 = this.tabStrip.getChildAt(0);
            scrollTo(isLayoutRtl ? ((Utils.getEnd(childAt) - Utils.getMarginEnd(childAt)) - width) - (((Utils.getWidth(childAt3) + Utils.getMarginEnd(childAt3)) - (Utils.getWidth(childAt) + Utils.getMarginEnd(childAt))) / 2) : ((Utils.getStart(childAt) - Utils.getMarginStart(childAt)) + width) - (((Utils.getWidth(childAt3) + Utils.getMarginStart(childAt3)) - (Utils.getWidth(childAt) + Utils.getMarginStart(childAt))) / 2), 0);
            return;
        }
        if (this.titleOffset == -1) {
            if (0.0f < f && f < 1.0f) {
                View childAt4 = this.tabStrip.getChildAt(i + 1);
                width = Math.round(f * ((Utils.getWidth(childAt) / 2) + Utils.getMarginEnd(childAt) + (Utils.getWidth(childAt4) / 2) + Utils.getMarginStart(childAt4)));
            }
            i2 = isLayoutRtl ? (((-Utils.getWidthWithMargin(childAt)) / 2) + (getWidth() / 2)) - Utils.getPaddingStart(this) : ((Utils.getWidthWithMargin(childAt) / 2) - (getWidth() / 2)) + Utils.getPaddingStart(this);
        } else if (isLayoutRtl) {
            if (i > 0 || f > 0.0f) {
                i2 = this.titleOffset;
            }
            i2 = 0;
        } else {
            if (i > 0 || f > 0.0f) {
                i2 = -this.titleOffset;
            }
            i2 = 0;
        }
        int start = Utils.getStart(childAt);
        int marginStart = Utils.getMarginStart(childAt);
        scrollTo(isLayoutRtl ? i2 + (((start + marginStart) - width) - getWidth()) + Utils.getPaddingHorizontally(this) : i2 + (start - marginStart) + width, 0);
    }

    protected TextView createDefaultTabView(CharSequence charSequence) {
        TextView textView = new TextView(getContext());
        textView.setGravity(17);
        textView.setText(charSequence);
        textView.setTextColor(this.tabViewTextColors);
        textView.setTextSize(0, this.tabViewTextSize);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        if (this.tabViewBackgroundResId != -1) {
            textView.setBackgroundResource(this.tabViewBackgroundResId);
        } else if (Build.VERSION.SDK_INT >= 11) {
            TypedValue typedValue = new TypedValue();
            getContext().getTheme().resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true);
            textView.setBackgroundResource(typedValue.resourceId);
        }
        if (Build.VERSION.SDK_INT >= 14) {
            textView.setAllCaps(this.tabViewTextAllCaps);
        }
        textView.setPadding(this.tabViewTextHorizontalPadding, 0, this.tabViewTextHorizontalPadding, 0);
        if (this.tabViewTextMinWidth > 0) {
            textView.setMinWidth(this.tabViewTextMinWidth);
        }
        return textView;
    }

    public View getTabAt(int i) {
        return this.tabStrip.getChildAt(i);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!z || this.viewPager == null) {
            return;
        }
        scrollToTab(this.viewPager.getCurrentItem(), 0.0f);
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        if (this.onScrollChangeListener != null) {
            this.onScrollChangeListener.onScrollChanged(i, i3);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (!this.tabStrip.isIndicatorAlwaysInCenter() || this.tabStrip.getChildCount() <= 0) {
            return;
        }
        View childAt = this.tabStrip.getChildAt(0);
        View childAt2 = this.tabStrip.getChildAt(this.tabStrip.getChildCount() - 1);
        int measuredWidth = ((i - Utils.getMeasuredWidth(childAt)) / 2) - Utils.getMarginStart(childAt);
        int measuredWidth2 = ((i - Utils.getMeasuredWidth(childAt2)) / 2) - Utils.getMarginEnd(childAt2);
        this.tabStrip.setMinimumWidth(this.tabStrip.getMeasuredWidth());
        ViewCompat.setPaddingRelative(this, measuredWidth, getPaddingTop(), measuredWidth2, getPaddingBottom());
        setClipToPadding(false);
    }

    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        this.tabStrip.setCustomTabColorizer(tabColorizer);
    }

    public void setCustomTabView(int i, int i2) {
        this.tabProvider = new SimpleTabProvider(getContext(), i, i2);
    }

    public void setCustomTabView(TabProvider tabProvider) {
        this.tabProvider = tabProvider;
    }

    public void setDefaultTabTextColor(int i) {
        this.tabViewTextColors = ColorStateList.valueOf(i);
    }

    public void setDefaultTabTextColor(ColorStateList colorStateList) {
        this.tabViewTextColors = colorStateList;
    }

    public void setDistributeEvenly(boolean z) {
        this.distributeEvenly = z;
    }

    public void setDividerColors(int... iArr) {
        this.tabStrip.setDividerColors(iArr);
    }

    public void setIndicationInterpolator(SmartTabIndicationInterpolator smartTabIndicationInterpolator) {
        this.tabStrip.setIndicationInterpolator(smartTabIndicationInterpolator);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.viewPagerPageChangeListener = onPageChangeListener;
    }

    public void setOnScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public void setOnTabClickListener(OnTabClickListener onTabClickListener) {
        this.onTabClickListener = onTabClickListener;
    }

    public void setSelectedIndicatorColors(int... iArr) {
        this.tabStrip.setSelectedIndicatorColors(iArr);
    }

    public void setViewPager(ViewPager viewPager) {
        this.tabStrip.removeAllViews();
        this.viewPager = viewPager;
        if (viewPager == null || viewPager.getAdapter() == null) {
            return;
        }
        viewPager.addOnPageChangeListener(new InternalViewPagerListener());
        populateTabStrip();
    }
}
