package com.stephentuso.welcome;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
class SimpleViewPagerIndicator extends View implements ViewPager.OnPageChangeListener {
    private Animation animation;
    private float currentMaxAlpha;
    private int currentPage;
    private int currentPageColor;
    private float currentPageOffset;
    private int displayedPage;
    private boolean isRtl;
    private float otherMaxAlpha;
    private int otherPageColor;
    private int pageIndexOffset;
    private Paint paint;
    private int size;
    private int spacing;
    private int totalPages;

    /* loaded from: classes.dex */
    public enum Animation {
        NONE,
        SLIDE,
        FADE
    }

    public SimpleViewPagerIndicator(Context context) {
        this(context, null);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.welcomeIndicatorStyle);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.currentPageColor = -1711276033;
        this.otherPageColor = 570425344;
        this.totalPages = 0;
        this.currentPage = 0;
        this.displayedPage = 0;
        this.currentPageOffset = 0.0f;
        this.pageIndexOffset = 0;
        this.spacing = 16;
        this.size = 4;
        this.animation = Animation.FADE;
        this.isRtl = false;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SimpleViewPagerIndicator, i, 0);
            this.currentPageColor = obtainStyledAttributes.getColor(R.styleable.SimpleViewPagerIndicator_currentPageColor, this.currentPageColor);
            this.otherPageColor = obtainStyledAttributes.getColor(R.styleable.SimpleViewPagerIndicator_indicatorColor, this.otherPageColor);
            this.animation = animationFromInt(obtainStyledAttributes.getInt(R.styleable.SimpleViewPagerIndicator_animation, this.animation.ordinal()), this.animation);
            obtainStyledAttributes.recycle();
        }
        init(context);
    }

    private Animation animationFromInt(int i, Animation animation) {
        for (Animation animation2 : Animation.values()) {
            if (animation2.ordinal() == i) {
                return animation2;
            }
        }
        return animation;
    }

    private boolean canShowAnimation() {
        if (this.isRtl) {
            if (this.currentPage >= 0) {
                return false;
            }
        } else if (this.currentPage != this.totalPages - 1) {
            return false;
        }
        return true;
    }

    private Point getCenter() {
        return new Point((getRight() - getLeft()) / 2, (getBottom() - getTop()) / 2);
    }

    private float getFirstDotPosition(float f) {
        float floor = (float) Math.floor((this.totalPages % 2 == 0 ? this.totalPages - 1 : this.totalPages) / 2);
        if (this.totalPages % 2 == 0) {
            floor = (float) (floor + 0.5d);
        }
        return f - (this.spacing * floor);
    }

    private void init(Context context) {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        float f = context.getResources().getDisplayMetrics().density;
        this.spacing = (int) (this.spacing * f);
        this.size = (int) (this.size * f);
        this.currentMaxAlpha = Color.alpha(this.currentPageColor);
        this.otherMaxAlpha = Color.alpha(this.otherPageColor);
    }

    public int getDisplayedPosition() {
        return this.displayedPage;
    }

    public Animation getIndicatorAnimation() {
        return this.animation;
    }

    public int getPageIndexOffset() {
        return this.pageIndexOffset;
    }

    public int getPosition() {
        return this.currentPage;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public boolean isRtl() {
        return this.isRtl;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Point center = getCenter();
        float firstDotPosition = getFirstDotPosition(center.x);
        this.paint.setColor(this.otherPageColor);
        for (int i = 0; i < this.totalPages; i++) {
            canvas.drawCircle((this.spacing * i) + firstDotPosition, center.y, this.size, this.paint);
        }
        this.paint.setColor(this.currentPageColor);
        if (this.animation == Animation.NONE || this.animation == Animation.SLIDE) {
            canvas.drawCircle(firstDotPosition + (this.spacing * (this.displayedPage + this.currentPageOffset)), center.y, this.size, this.paint);
        } else if (this.animation == Animation.FADE) {
            this.paint.setAlpha((int) (this.currentMaxAlpha * (1.0f - this.currentPageOffset)));
            canvas.drawCircle((this.spacing * this.displayedPage) + firstDotPosition, center.y, this.size, this.paint);
            this.paint.setAlpha((int) (this.currentMaxAlpha * this.currentPageOffset));
            canvas.drawCircle(firstDotPosition + (this.spacing * (this.displayedPage + 1)), center.y, this.size, this.paint);
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        if (this.animation != Animation.NONE) {
            setPosition(i);
            if (canShowAnimation()) {
                f = 0.0f;
            }
            this.currentPageOffset = f;
            invalidate();
        }
    }

    @Override // android.support.v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        if (this.animation == Animation.NONE) {
            setPosition(i);
            this.currentPageOffset = 0.0f;
            invalidate();
        }
    }

    public void setIndicatorAnimation(Animation animation) {
        this.animation = animation;
    }

    public void setPageIndexOffset(int i) {
        this.pageIndexOffset = i;
    }

    public void setPosition(int i) {
        this.currentPage = i + this.pageIndexOffset;
        this.displayedPage = this.isRtl ? Math.max(this.currentPage, 0) : Math.min(this.currentPage, this.totalPages - 1);
        invalidate();
    }

    public void setRtl(boolean z) {
        this.isRtl = z;
    }

    public void setTotalPages(int i) {
        this.totalPages = i;
        invalidate();
    }
}
