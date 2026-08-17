package com.afollestad.materialdialogs.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ScrollView;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.R;
import com.afollestad.materialdialogs.StackingBehavior;
import com.afollestad.materialdialogs.util.DialogUtils;

/* loaded from: classes.dex */
public class MDRootLayout extends ViewGroup {
    private static final int INDEX_NEGATIVE = 1;
    private static final int INDEX_NEUTRAL = 0;
    private static final int INDEX_POSITIVE = 2;
    private ViewTreeObserver.OnScrollChangedListener bottomOnScrollChangedListener;
    private int buttonBarHeight;
    private GravityEnum buttonGravity;
    private int buttonHorizontalEdgeMargin;
    private int buttonPaddingFull;
    private final MDButton[] buttons;
    private View content;
    private Paint dividerPaint;
    private int dividerWidth;
    private boolean drawBottomDivider;
    private boolean drawTopDivider;
    private boolean isStacked;
    private int maxHeight;
    private boolean noTitleNoPadding;
    private int noTitlePaddingFull;
    private boolean reducePaddingNoTitleNoButtons;
    private StackingBehavior stackBehavior;
    private View titleBar;
    private ViewTreeObserver.OnScrollChangedListener topOnScrollChangedListener;
    private boolean useFullPadding;

    public MDRootLayout(Context context) {
        super(context);
        this.buttons = new MDButton[3];
        this.drawTopDivider = false;
        this.drawBottomDivider = false;
        this.stackBehavior = StackingBehavior.ADAPTIVE;
        this.isStacked = false;
        this.useFullPadding = true;
        this.buttonGravity = GravityEnum.START;
        init(context, null, 0);
    }

    public MDRootLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.buttons = new MDButton[3];
        this.drawTopDivider = false;
        this.drawBottomDivider = false;
        this.stackBehavior = StackingBehavior.ADAPTIVE;
        this.isStacked = false;
        this.useFullPadding = true;
        this.buttonGravity = GravityEnum.START;
        init(context, attributeSet, 0);
    }

    @TargetApi(11)
    public MDRootLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.buttons = new MDButton[3];
        this.drawTopDivider = false;
        this.drawBottomDivider = false;
        this.stackBehavior = StackingBehavior.ADAPTIVE;
        this.isStacked = false;
        this.useFullPadding = true;
        this.buttonGravity = GravityEnum.START;
        init(context, attributeSet, i);
    }

    @TargetApi(21)
    public MDRootLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.buttons = new MDButton[3];
        this.drawTopDivider = false;
        this.drawBottomDivider = false;
        this.stackBehavior = StackingBehavior.ADAPTIVE;
        this.isStacked = false;
        this.useFullPadding = true;
        this.buttonGravity = GravityEnum.START;
        init(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addScrollListener(final ViewGroup viewGroup, final boolean z, final boolean z2) {
        if ((z2 || this.topOnScrollChangedListener != null) && !(z2 && this.bottomOnScrollChangedListener == null)) {
            return;
        }
        if (viewGroup instanceof RecyclerView) {
            RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.afollestad.materialdialogs.internal.MDRootLayout.2
                @Override // android.support.v7.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    MDButton[] mDButtonArr = MDRootLayout.this.buttons;
                    boolean z3 = false;
                    int length = mDButtonArr.length;
                    int i3 = 0;
                    while (true) {
                        if (i3 < length) {
                            MDButton mDButton = mDButtonArr[i3];
                            if (mDButton != null && mDButton.getVisibility() != 8) {
                                z3 = true;
                                break;
                            }
                            i3++;
                        } else {
                            break;
                        }
                    }
                    MDRootLayout.this.invalidateDividersForScrollingView(viewGroup, z, z2, z3);
                    MDRootLayout.this.invalidate();
                }
            };
            RecyclerView recyclerView = (RecyclerView) viewGroup;
            recyclerView.addOnScrollListener(onScrollListener);
            onScrollListener.onScrolled(recyclerView, 0, 0);
            return;
        }
        ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: com.afollestad.materialdialogs.internal.MDRootLayout.3
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public void onScrollChanged() {
                MDButton[] mDButtonArr = MDRootLayout.this.buttons;
                boolean z3 = false;
                int length = mDButtonArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        MDButton mDButton = mDButtonArr[i];
                        if (mDButton != null && mDButton.getVisibility() != 8) {
                            z3 = true;
                            break;
                        }
                        i++;
                    } else {
                        break;
                    }
                }
                if (viewGroup instanceof WebView) {
                    MDRootLayout.this.invalidateDividersForWebView((WebView) viewGroup, z, z2, z3);
                } else {
                    MDRootLayout.this.invalidateDividersForScrollingView(viewGroup, z, z2, z3);
                }
                MDRootLayout.this.invalidate();
            }
        };
        if (z2) {
            this.bottomOnScrollChangedListener = onScrollChangedListener;
            viewGroup.getViewTreeObserver().addOnScrollChangedListener(this.bottomOnScrollChangedListener);
        } else {
            this.topOnScrollChangedListener = onScrollChangedListener;
            viewGroup.getViewTreeObserver().addOnScrollChangedListener(this.topOnScrollChangedListener);
        }
        onScrollChangedListener.onScrollChanged();
    }

    private static boolean canAdapterViewScroll(AdapterView adapterView) {
        if (adapterView.getLastVisiblePosition() == -1) {
            return false;
        }
        return !(adapterView.getFirstVisiblePosition() == 0) || !(adapterView.getLastVisiblePosition() == adapterView.getCount() - 1) || adapterView.getChildCount() <= 0 || adapterView.getChildAt(0).getTop() < adapterView.getPaddingTop() || adapterView.getChildAt(adapterView.getChildCount() - 1).getBottom() > adapterView.getHeight() - adapterView.getPaddingBottom();
    }

    public static boolean canRecyclerViewScroll(RecyclerView recyclerView) {
        return (recyclerView == null || recyclerView.getLayoutManager() == null || !recyclerView.getLayoutManager().canScrollVertically()) ? false : true;
    }

    private static boolean canScrollViewScroll(ScrollView scrollView) {
        if (scrollView.getChildCount() == 0) {
            return false;
        }
        return (scrollView.getMeasuredHeight() - scrollView.getPaddingTop()) - scrollView.getPaddingBottom() < scrollView.getChildAt(0).getMeasuredHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean canWebViewScroll(WebView webView) {
        return ((float) webView.getMeasuredHeight()) < ((float) webView.getContentHeight()) * webView.getScale();
    }

    @Nullable
    private static View getBottomView(ViewGroup viewGroup) {
        if (viewGroup == null || viewGroup.getChildCount() == 0) {
            return null;
        }
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt.getVisibility() == 0 && childAt.getBottom() == viewGroup.getMeasuredHeight()) {
                return childAt;
            }
        }
        return null;
    }

    @Nullable
    private static View getTopView(ViewGroup viewGroup) {
        if (viewGroup == null || viewGroup.getChildCount() == 0) {
            return null;
        }
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            if (childAt.getVisibility() == 0 && childAt.getTop() == 0) {
                return childAt;
            }
        }
        return null;
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MDRootLayout, i, 0);
        this.reducePaddingNoTitleNoButtons = obtainStyledAttributes.getBoolean(R.styleable.MDRootLayout_md_reduce_padding_no_title_no_buttons, true);
        obtainStyledAttributes.recycle();
        this.noTitlePaddingFull = resources.getDimensionPixelSize(R.dimen.md_notitle_vertical_padding);
        this.buttonPaddingFull = resources.getDimensionPixelSize(R.dimen.md_button_frame_vertical_padding);
        this.buttonHorizontalEdgeMargin = resources.getDimensionPixelSize(R.dimen.md_button_padding_frame_side);
        this.buttonBarHeight = resources.getDimensionPixelSize(R.dimen.md_button_height);
        this.dividerPaint = new Paint();
        this.dividerWidth = resources.getDimensionPixelSize(R.dimen.md_divider_height);
        this.dividerPaint.setColor(DialogUtils.resolveColor(context, R.attr.md_divider_color));
        setWillNotDraw(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateDividersForScrollingView(ViewGroup viewGroup, boolean z, boolean z2, boolean z3) {
        if (z && viewGroup.getChildCount() > 0) {
            this.drawTopDivider = (this.titleBar == null || this.titleBar.getVisibility() == 8 || viewGroup.getScrollY() + viewGroup.getPaddingTop() <= viewGroup.getChildAt(0).getTop()) ? false : true;
        }
        if (!z2 || viewGroup.getChildCount() <= 0) {
            return;
        }
        this.drawBottomDivider = z3 && (viewGroup.getScrollY() + viewGroup.getHeight()) - viewGroup.getPaddingBottom() < viewGroup.getChildAt(viewGroup.getChildCount() - 1).getBottom();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateDividersForWebView(WebView webView, boolean z, boolean z2, boolean z3) {
        boolean z4 = false;
        if (z) {
            this.drawTopDivider = (this.titleBar == null || this.titleBar.getVisibility() == 8 || webView.getScrollY() + webView.getPaddingTop() <= 0) ? false : true;
        }
        if (z2) {
            if (z3 && (webView.getScrollY() + webView.getMeasuredHeight()) - webView.getPaddingBottom() < webView.getContentHeight() * webView.getScale()) {
                z4 = true;
            }
            this.drawBottomDivider = z4;
        }
    }

    private void invertGravityIfNecessary() {
        if (Build.VERSION.SDK_INT >= 17 && getResources().getConfiguration().getLayoutDirection() == 1) {
            switch (this.buttonGravity) {
                case START:
                    this.buttonGravity = GravityEnum.END;
                    return;
                case END:
                    this.buttonGravity = GravityEnum.START;
                    return;
                default:
                    return;
            }
        }
    }

    private static boolean isVisible(View view) {
        boolean z = (view == null || view.getVisibility() == 8) ? false : true;
        return (z && (view instanceof MDButton)) ? ((MDButton) view).getText().toString().trim().length() > 0 : z;
    }

    private void setUpDividersVisibility(final View view, final boolean z, final boolean z2) {
        if (view == null) {
            return;
        }
        if (view instanceof ScrollView) {
            ScrollView scrollView = (ScrollView) view;
            if (canScrollViewScroll(scrollView)) {
                addScrollListener(scrollView, z, z2);
                return;
            }
            if (z) {
                this.drawTopDivider = false;
            }
            if (z2) {
                this.drawBottomDivider = false;
                return;
            }
            return;
        }
        if (view instanceof AdapterView) {
            AdapterView adapterView = (AdapterView) view;
            if (canAdapterViewScroll(adapterView)) {
                addScrollListener(adapterView, z, z2);
                return;
            }
            if (z) {
                this.drawTopDivider = false;
            }
            if (z2) {
                this.drawBottomDivider = false;
                return;
            }
            return;
        }
        if (view instanceof WebView) {
            view.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.afollestad.materialdialogs.internal.MDRootLayout.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    if (view.getMeasuredHeight() == 0) {
                        return true;
                    }
                    if (MDRootLayout.canWebViewScroll((WebView) view)) {
                        MDRootLayout.this.addScrollListener((ViewGroup) view, z, z2);
                    } else {
                        if (z) {
                            MDRootLayout.this.drawTopDivider = false;
                        }
                        if (z2) {
                            MDRootLayout.this.drawBottomDivider = false;
                        }
                    }
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
            return;
        }
        if (view instanceof RecyclerView) {
            boolean canRecyclerViewScroll = canRecyclerViewScroll((RecyclerView) view);
            if (z) {
                this.drawTopDivider = canRecyclerViewScroll;
            }
            if (z2) {
                this.drawBottomDivider = canRecyclerViewScroll;
            }
            if (canRecyclerViewScroll) {
                addScrollListener((ViewGroup) view, z, z2);
                return;
            }
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            View topView = getTopView(viewGroup);
            setUpDividersVisibility(topView, z, z2);
            View bottomView = getBottomView(viewGroup);
            if (bottomView != topView) {
                setUpDividersVisibility(bottomView, false, true);
            }
        }
    }

    public void noTitleNoPadding() {
        this.noTitleNoPadding = true;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.content != null) {
            if (this.drawTopDivider) {
                canvas.drawRect(0.0f, r0 - this.dividerWidth, getMeasuredWidth(), this.content.getTop(), this.dividerPaint);
            }
            if (this.drawBottomDivider) {
                canvas.drawRect(0.0f, this.content.getBottom(), getMeasuredWidth(), r0 + this.dividerWidth, this.dividerPaint);
            }
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt.getId() == R.id.md_titleFrame) {
                this.titleBar = childAt;
            } else if (childAt.getId() == R.id.md_buttonDefaultNeutral) {
                this.buttons[0] = (MDButton) childAt;
            } else if (childAt.getId() == R.id.md_buttonDefaultNegative) {
                this.buttons[1] = (MDButton) childAt;
            } else if (childAt.getId() == R.id.md_buttonDefaultPositive) {
                this.buttons[2] = (MDButton) childAt;
            } else {
                this.content = childAt;
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int measuredWidth;
        int i10;
        int measuredWidth2;
        if (isVisible(this.titleBar)) {
            int measuredHeight = this.titleBar.getMeasuredHeight() + i2;
            this.titleBar.layout(i, i2, i3, measuredHeight);
            i2 = measuredHeight;
        } else if (!this.noTitleNoPadding && this.useFullPadding) {
            i2 += this.noTitlePaddingFull;
        }
        if (isVisible(this.content)) {
            this.content.layout(i, i2, i3, this.content.getMeasuredHeight() + i2);
        }
        if (this.isStacked) {
            int i11 = i4 - this.buttonPaddingFull;
            for (MDButton mDButton : this.buttons) {
                if (isVisible(mDButton)) {
                    mDButton.layout(i, i11 - mDButton.getMeasuredHeight(), i3, i11);
                    i11 -= mDButton.getMeasuredHeight();
                }
            }
        } else {
            if (this.useFullPadding) {
                i4 -= this.buttonPaddingFull;
            }
            int i12 = i4 - this.buttonBarHeight;
            int i13 = this.buttonHorizontalEdgeMargin;
            if (isVisible(this.buttons[2])) {
                if (this.buttonGravity == GravityEnum.END) {
                    measuredWidth2 = i + i13;
                    i10 = this.buttons[2].getMeasuredWidth() + measuredWidth2;
                    i5 = -1;
                } else {
                    i10 = i3 - i13;
                    measuredWidth2 = i10 - this.buttons[2].getMeasuredWidth();
                    i5 = measuredWidth2;
                }
                this.buttons[2].layout(measuredWidth2, i12, i10, i4);
                i13 += this.buttons[2].getMeasuredWidth();
            } else {
                i5 = -1;
            }
            if (isVisible(this.buttons[1])) {
                if (this.buttonGravity == GravityEnum.END) {
                    i9 = i13 + i;
                    measuredWidth = this.buttons[1].getMeasuredWidth() + i9;
                } else if (this.buttonGravity == GravityEnum.START) {
                    measuredWidth = i3 - i13;
                    i9 = measuredWidth - this.buttons[1].getMeasuredWidth();
                } else {
                    i9 = this.buttonHorizontalEdgeMargin + i;
                    measuredWidth = this.buttons[1].getMeasuredWidth() + i9;
                    i6 = measuredWidth;
                    this.buttons[1].layout(i9, i12, measuredWidth, i4);
                }
                i6 = -1;
                this.buttons[1].layout(i9, i12, measuredWidth, i4);
            } else {
                i6 = -1;
            }
            if (isVisible(this.buttons[0])) {
                if (this.buttonGravity == GravityEnum.END) {
                    i8 = i3 - this.buttonHorizontalEdgeMargin;
                    i7 = i8 - this.buttons[0].getMeasuredWidth();
                } else if (this.buttonGravity == GravityEnum.START) {
                    i7 = i + this.buttonHorizontalEdgeMargin;
                    i8 = this.buttons[0].getMeasuredWidth() + i7;
                } else {
                    if (i6 == -1 && i5 != -1) {
                        i6 = i5 - this.buttons[0].getMeasuredWidth();
                    } else if (i5 == -1 && i6 != -1) {
                        i5 = i6 + this.buttons[0].getMeasuredWidth();
                    } else if (i5 == -1) {
                        i6 = ((i3 - i) / 2) - (this.buttons[0].getMeasuredWidth() / 2);
                        i5 = i6 + this.buttons[0].getMeasuredWidth();
                    }
                    i7 = i6;
                    i8 = i5;
                }
                this.buttons[0].layout(i7, i12, i8, i4);
            }
        }
        setUpDividersVisibility(this.content, true, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00bf  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r13, int r14) {
        /*
            Method dump skipped, instructions count: 281
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.internal.MDRootLayout.onMeasure(int, int):void");
    }

    public void setButtonGravity(GravityEnum gravityEnum) {
        this.buttonGravity = gravityEnum;
        invertGravityIfNecessary();
    }

    public void setButtonStackedGravity(GravityEnum gravityEnum) {
        for (MDButton mDButton : this.buttons) {
            if (mDButton != null) {
                mDButton.setStackedGravity(gravityEnum);
            }
        }
    }

    public void setDividerColor(int i) {
        this.dividerPaint.setColor(i);
        invalidate();
    }

    public void setMaxHeight(int i) {
        this.maxHeight = i;
    }

    public void setStackingBehavior(StackingBehavior stackingBehavior) {
        this.stackBehavior = stackingBehavior;
        invalidate();
    }
}
