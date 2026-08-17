package com.bvapp.arcmenulibrary.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;
import com.blankj.utilcode.constant.MemoryConstants;
import com.bvapp.arcmenulibrary.TextStructure;
import com.bvapp.arcmenulibrary.anim.RotateAndTranslateAnimation;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ArcLayout extends RelativeLayout {
    public static final int BOTTOM_LEFT = 3844;
    public static final int BOTTOM_MIDDLE = 3846;
    public static final int BOTTOM_RIGHT = 3845;
    public static final int CENTER = 3849;
    public static final float DEFAULT_FROM_DEGREES = 270.0f;
    public static final int DEFAULT_HINT_GRAVITY = 3849;
    public static final float DEFAULT_TO_DEGREES = 360.0f;
    public static final int LEFT_MIDDLE = 3848;
    private static final int MIN_RADIUS = 100;
    public static final int RIGHT_MIDDLE = 3847;
    public static final int TOOLTIP_DOWN = 3873;
    public static final int TOOLTIP_LEFT = 3875;
    public static final int TOOLTIP_RIGHT = 3874;
    public static final int TOOLTIP_UP = 3872;
    public static final int TOP_LEFT = 3841;
    public static final int TOP_MIDDLE = 3843;
    public static final int TOP_RIGHT = 3842;
    private boolean checkCenterGravity;
    private int childCount;
    private boolean mAnimDone;
    private int mChildPadding;
    private int mChildSize;
    private Context mContext;
    private int mDefaultShift;
    private int mDuration;
    private boolean mExpandDone;
    private boolean mExpanded;
    private float mFromDegrees;
    private int mLayoutCenterX;
    private int mLayoutCenterY;
    private int mLayoutHeight;
    private int mLayoutPadding;
    private int mLayoutWidth;
    private int mMarginBottom;
    private int mMarginLeft;
    private int mMarginRight;
    private int mMarginTop;
    private int mMenuSize;
    private int mMinRadius;
    private int mPreChildOffset;
    private long mPreOffset;
    private int mRadius;
    private boolean mRaiusCtrl;
    private float mToDegrees;
    private int mToolTipSide;
    private int mViewHeight;
    private int mViewWidth;
    private int menuGravity;
    private boolean menuItemRotatationInClosing;
    private OnMenuItemOpenClose menuListener;
    private float tDeg;
    private float tPerDeg;
    private ArrayList<TextStructure> textStructure;
    private boolean toolTipCtrl;

    /* loaded from: classes.dex */
    public interface OnMenuItemOpenClose {
        void menuStatus(boolean z);
    }

    public ArcLayout(Context context) {
        super(context);
        this.childCount = 2;
        this.mMenuSize = 32;
        this.mChildSize = 32;
        this.mChildPadding = 5;
        this.mLayoutPadding = 10;
        this.mDuration = 300;
        this.menuGravity = 3849;
        this.mFromDegrees = 270.0f;
        this.mToDegrees = 360.0f;
        this.tDeg = 270.0f;
        this.tPerDeg = 360.0f;
        this.mRadius = 0;
        this.mMinRadius = 100;
        this.mExpanded = false;
        this.mAnimDone = true;
        this.textStructure = new ArrayList<>();
    }

    public ArcLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.childCount = 2;
        this.mMenuSize = 32;
        this.mChildSize = 32;
        this.mChildPadding = 5;
        this.mLayoutPadding = 10;
        this.mDuration = 300;
        this.menuGravity = 3849;
        this.mFromDegrees = 270.0f;
        this.mToDegrees = 360.0f;
        this.tDeg = 270.0f;
        this.tPerDeg = 360.0f;
        this.mRadius = 0;
        this.mMinRadius = 100;
        this.mExpanded = false;
        this.mAnimDone = true;
        this.textStructure = new ArrayList<>();
        this.mContext = context;
    }

    private void bindChildAnimation(View view, int i, int i2, long j) {
        Rect computeChildFrame;
        int i3;
        Animation createExpandAnimation;
        boolean z = this.mExpanded;
        getLayoutCenter();
        int i4 = this.mLayoutCenterX;
        int i5 = this.mLayoutCenterY;
        int i6 = z ? 0 : this.mRadius;
        int childCount = getChildCount() / 2;
        int i7 = childCount - 1;
        float f = (this.mToDegrees - this.mFromDegrees) / i7;
        int i8 = i2 % 2;
        if (i8 != 0) {
            float f2 = i;
            computeChildFrame = computeChildFrame(i4, i5, this.mExpanded ? 0 : this.mRadius, 0, 0, this.mFromDegrees + (f * f2), view.getMeasuredHeight(), view.getMeasuredWidth());
            int frameOffsetX = getFrameOffsetX(view.getMeasuredWidth());
            int frameOffsetY = getFrameOffsetY(view.getMeasuredHeight());
            if (this.mToolTipSide == 3872) {
                computeChildFrame.top -= frameOffsetY;
                computeChildFrame.bottom -= frameOffsetY;
            } else if (this.mToolTipSide == 3873) {
                computeChildFrame.top += frameOffsetY;
                computeChildFrame.bottom += frameOffsetY;
            } else if (this.mToolTipSide == 3874) {
                computeChildFrame.left += frameOffsetX;
                computeChildFrame.right += frameOffsetX;
            } else if (this.mToolTipSide == 3875) {
                computeChildFrame.left -= frameOffsetX;
                computeChildFrame.right -= frameOffsetX;
            } else {
                computeChildFrame = computeChildFrame(i4, i5, this.mExpanded ? 0 : this.mRadius, this.mChildSize, getShift(this.mFromDegrees, this.mToDegrees, this.tDeg + (f2 * this.tPerDeg), view.getMeasuredWidth()), this.tDeg + (f2 * this.tPerDeg), view.getMeasuredHeight(), view.getMeasuredWidth());
            }
        } else {
            computeChildFrame = computeChildFrame(i4, i5, i6, this.mFromDegrees + (i * f), this.mChildSize);
        }
        int left = computeChildFrame.left - view.getLeft();
        int top = computeChildFrame.top - view.getTop();
        Interpolator accelerateInterpolator = this.mExpanded ? new AccelerateInterpolator() : new OvershootInterpolator(1.5f);
        long computeStartOffset = computeStartOffset(childCount, this.mExpanded, i, 0.1f, j, accelerateInterpolator);
        if (i8 == 0) {
            this.mPreOffset = computeStartOffset;
        }
        if (this.mExpanded) {
            i3 = i7;
            createExpandAnimation = createShrinkAnimation(0.0f, left, 0.0f, top, this.mPreOffset, j, accelerateInterpolator, i2);
        } else {
            i3 = i7;
            createExpandAnimation = createExpandAnimation(0.0f, left, 0.0f, top, this.mPreOffset, j, accelerateInterpolator, i2);
        }
        final boolean z2 = getTransformedIndex(z, childCount, i) == i3;
        createExpandAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.bvapp.arcmenulibrary.widget.ArcLayout.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (z2) {
                    ArcLayout.this.postDelayed(new Runnable() { // from class: com.bvapp.arcmenulibrary.widget.ArcLayout.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ArcLayout.this.onAllAnimationsEnd();
                        }
                    }, 0L);
                }
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                ArcLayout.this.mAnimDone = false;
            }
        });
        view.setAnimation(createExpandAnimation);
    }

    private static Rect computeChildFrame(int i, int i2, int i3, float f, int i4) {
        double d = i3;
        double d2 = f;
        double cos = i + (Math.cos(Math.toRadians(d2)) * d);
        double sin = i2 + (d * Math.sin(Math.toRadians(d2)));
        double d3 = i4 / 2;
        return new Rect((int) (cos - d3), (int) (sin - d3), (int) (cos + d3), (int) (sin + d3));
    }

    private static Rect computeChildFrame(int i, int i2, int i3, int i4, int i5, float f, int i6, int i7) {
        double d = i5 + i3;
        double d2 = f;
        double cos = i + (d * Math.cos(Math.toRadians(d2)));
        double sin = i2 + ((i4 + i3) * Math.sin(Math.toRadians(d2)));
        double d3 = i7 / 2;
        double d4 = i6 / 2;
        return new Rect((int) (cos - d3), (int) (sin - d4), (int) (cos + d3), (int) (sin + d4));
    }

    private static double computeOffsetX(int i, float f) {
        return i * Math.cos(Math.toRadians(f));
    }

    private static double computeOffsetY(int i, float f) {
        return i * Math.sin(Math.toRadians(f));
    }

    private static int computeRadius(float f, int i, int i2, int i3, int i4) {
        if (i < 2) {
            return i4;
        }
        return Math.max((int) (((i2 + i3) / 2) / Math.sin(Math.toRadians((f / (i - 1)) / 2.0f))), i4);
    }

    private long computeStartOffset(int i, boolean z, int i2, float f, long j, Interpolator interpolator) {
        float f2 = f * ((float) j);
        long transformedIndex = getTransformedIndex(z, i, i2) * f2;
        float f3 = f2 * i;
        return interpolator.getInterpolation(((float) transformedIndex) / f3) * f3;
    }

    private Animation createExpandAnimation(float f, float f2, float f3, float f4, long j, long j2, Interpolator interpolator, int i) {
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(true);
        RotateAndTranslateAnimation rotateAndTranslateAnimation = new RotateAndTranslateAnimation(0.0f, f2, 0.0f, f4, 0.0f, 720.0f);
        rotateAndTranslateAnimation.setStartOffset(j);
        rotateAndTranslateAnimation.setDuration(j2);
        rotateAndTranslateAnimation.setInterpolator(interpolator);
        rotateAndTranslateAnimation.setFillAfter(true);
        animationSet.addAnimation(rotateAndTranslateAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setStartOffset(j);
        alphaAnimation.setDuration(j2);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setFillAfter(true);
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setDuration((long) (j2 * 0.2d));
        alphaAnimation2.setInterpolator(interpolator);
        alphaAnimation2.setFillAfter(true);
        if (i % 2 != 0) {
            animationSet.addAnimation(alphaAnimation);
        } else {
            animationSet.addAnimation(alphaAnimation2);
        }
        return animationSet;
    }

    private Animation createShrinkAnimation(float f, float f2, float f3, float f4, long j, long j2, Interpolator interpolator, int i) {
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(true);
        long j3 = j2 / 2;
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setStartOffset(j);
        rotateAnimation.setDuration(j3);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setFillAfter(true);
        if (this.menuItemRotatationInClosing) {
            animationSet.addAnimation(rotateAnimation);
        }
        RotateAndTranslateAnimation rotateAndTranslateAnimation = new RotateAndTranslateAnimation(0.0f, f2, 0.0f, f4, 360.0f, 720.0f);
        long j4 = j + j3;
        rotateAndTranslateAnimation.setStartOffset(j4);
        long j5 = j2 - j3;
        rotateAndTranslateAnimation.setDuration(j5);
        rotateAndTranslateAnimation.setInterpolator(interpolator);
        rotateAndTranslateAnimation.setFillAfter(true);
        animationSet.addAnimation(rotateAndTranslateAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setStartOffset(j4);
        alphaAnimation.setDuration(j5);
        alphaAnimation.setInterpolator(interpolator);
        alphaAnimation.setFillAfter(true);
        if (i % 2 != 0) {
            animationSet.addAnimation(alphaAnimation);
        }
        return animationSet;
    }

    public static float dpToPx(float f) {
        return f * Resources.getSystem().getDisplayMetrics().density;
    }

    private int getFrameOffsetX(int i) {
        return ((i + this.mChildSize) + ((int) dpToPx(4.0f))) / 2;
    }

    private int getFrameOffsetY(int i) {
        return i > this.mChildSize ? i : this.mChildSize;
    }

    private void getLayoutCenter() {
        int width = getWidth() / 2;
        int height = getHeight() / 2;
        this.mLayoutHeight = getHeight();
        this.mLayoutWidth = getWidth();
        switch (this.menuGravity) {
            case 3841:
                width = (int) ((this.mDefaultShift * 1.5d) + (this.mMenuSize / 2));
                height = (int) ((1.5d * this.mDefaultShift) + (this.mMenuSize / 2));
                break;
            case 3842:
                width = (int) ((getWidth() - (this.mDefaultShift * 1.5d)) - (this.mMenuSize / 2));
                height = (int) ((1.5d * this.mDefaultShift) + (this.mMenuSize / 2));
                break;
            case 3843:
                width = getWidth() / 2;
                height = (int) ((1.5d * this.mDefaultShift) + (this.mMenuSize / 2));
                break;
            case 3844:
                width = (int) ((this.mDefaultShift * 1.5d) + (this.mMenuSize / 2));
                height = (int) ((getHeight() - (1.5d * this.mDefaultShift)) - (this.mMenuSize / 2));
                break;
            case 3845:
                width = (int) ((getWidth() - (this.mDefaultShift * 1.5d)) - (this.mMenuSize / 2));
                height = (int) ((getHeight() - (1.5d * this.mDefaultShift)) - (this.mMenuSize / 2));
                break;
            case 3846:
                width = getWidth() / 2;
                height = (int) ((getHeight() - (1.5d * this.mDefaultShift)) - (this.mMenuSize / 2));
                break;
            case 3847:
                width = (int) ((getWidth() - (1.5d * this.mDefaultShift)) - (this.mMenuSize / 2));
                height = getHeight() / 2;
                break;
            case 3848:
                width = (int) ((1.5d * this.mDefaultShift) + (this.mMenuSize / 2));
                height = getHeight() / 2;
                break;
            case 3849:
                width = getWidth() / 2;
                height = getHeight() / 2;
                break;
        }
        this.mLayoutCenterX = width;
        this.mLayoutCenterY = height;
    }

    private int getShift(float f, float f2, float f3, int i) {
        int i2 = this.mChildSize;
        if (f == 265.0f && f2 == 365.0f) {
            if (Math.abs(f3 - 365.0f) < 45.0f && i > this.mChildSize) {
                return i;
            }
        } else if (f == 175.0f && f2 == 365.0f) {
            if ((Math.abs(f3 - 365.0f) < 45.0f || Math.abs(f3 - 175.0f) < 45.0f) && i > this.mChildSize) {
                return i;
            }
        } else if (f == 275.0f && f2 == 175.0f) {
            if (Math.abs(f3 - 275.0f) > 45.0f && i > this.mChildSize) {
                return i;
            }
        } else if (f == -95.0f && f2 == 95.0f) {
            if ((Math.abs(f3 - (-95.0f)) > 45.0f || Math.abs(f3 - 95.0f) > 45.0f) && i > this.mChildSize) {
                return i;
            }
        } else if (f == 275.0f && f2 == 85.0f) {
            if ((Math.abs(f3 - 275.0f) > 45.0f || Math.abs(f3 - 85.0f) > 45.0f) && i > this.mChildSize) {
                return i;
            }
        } else if (f == -5.0f && f2 == 95.0f) {
            if (Math.abs(f3 - 95.0f) > 45.0f && i > this.mChildSize) {
                return i;
            }
        } else if (f == -5.0f && f2 == 185.0f) {
            if ((Math.abs(f3) < 45.0f || Math.abs(f3 - 185.0f) < 45.0f) && i > this.mChildSize) {
                return i;
            }
        } else if (f == 85.0f && f2 == 185.0f) {
            if (Math.abs(f3 - 85.0f) > 45.0f && i > this.mChildSize) {
                return i;
            }
        } else if (f == 0.0f && f2 == 360.0f && (((Math.abs(f3 - 270.0f) > 45.0f && f3 > 135.0f) || Math.abs(f3 - 360.0f) < 45.0f || f3 < 45.0f) && i > this.mChildSize)) {
            return i;
        }
        return i2;
    }

    private int getTransformedIndex(boolean z, int i, int i2) {
        return z ? (i - 1) - i2 : i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAllAnimationsEnd() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).clearAnimation();
        }
        this.mAnimDone = true;
        this.mExpandDone = this.mExpanded;
        if (this.menuListener != null) {
            this.menuListener.menuStatus(this.mExpanded);
        }
        requestLayout();
    }

    public static float pxToDp(float f) {
        return f / Resources.getSystem().getDisplayMetrics().density;
    }

    public void changeTextSize(int i, int i2, int i3) {
        TextStructure textStructure = new TextStructure();
        textStructure.h = i3;
        textStructure.w = i2;
        if (this.textStructure.size() > i) {
            this.textStructure.remove(i);
            this.textStructure.add(i, textStructure);
        }
    }

    public int getChildSize() {
        return this.mChildSize;
    }

    public int getLayoutCenterX() {
        return this.mLayoutCenterX;
    }

    public int getLayoutCenterY() {
        return this.mLayoutCenterY;
    }

    public int getRadius() {
        return this.mRadius;
    }

    public boolean isAnimDone() {
        return this.mAnimDone;
    }

    public boolean isExpandDone() {
        return this.mExpandDone;
    }

    public boolean isExpanded() {
        return this.mExpanded;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x01ae  */
    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void onLayout(boolean r24, int r25, int r26, int r27, int r28) {
        /*
            Method dump skipped, instructions count: 467
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bvapp.arcmenulibrary.widget.ArcLayout.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int computeRadius = computeRadius(Math.abs(this.mToDegrees - this.mFromDegrees), getChildCount() / 2, this.mChildSize, this.mChildPadding, this.mMinRadius);
        this.mRadius = computeRadius;
        int i3 = (computeRadius * 3) + this.mChildSize + this.mChildPadding + (this.mLayoutPadding * 2) + this.mMenuSize + (4 * this.mDefaultShift) + this.mViewWidth;
        switch (this.menuGravity) {
            case 3841:
                int i4 = i3 / 2;
                setMeasuredDimension(i4, i4);
                break;
            case 3842:
                int i5 = i3 / 2;
                setMeasuredDimension(i5, i5);
                break;
            case 3843:
                setMeasuredDimension(i3, i3 / 2);
                break;
            case 3844:
                int i6 = i3 / 2;
                setMeasuredDimension(i6, i6);
                break;
            case 3845:
                int i7 = i3 / 2;
                setMeasuredDimension(i7, i7);
                break;
            case 3846:
                setMeasuredDimension(i3, i3 / 2);
                break;
            case 3847:
                setMeasuredDimension(i3 / 2, i3);
                break;
            case 3848:
                setMeasuredDimension(i3 / 2, i3);
                break;
            case 3849:
                setMeasuredDimension(i3, i3);
                break;
            default:
                setMeasuredDimension(i3, i3);
                break;
        }
        int childCount = getChildCount();
        TextStructure textStructure = new TextStructure();
        int i8 = 0;
        for (int i9 = 0; i9 < childCount; i9++) {
            int i10 = i9 % 2;
            if (i10 != 0) {
                textStructure = this.textStructure.get(i8);
                i8++;
            }
            getChildAt(i9).measure(View.MeasureSpec.makeMeasureSpec(i10 == 0 ? this.mChildSize : textStructure.w, MemoryConstants.GB), View.MeasureSpec.makeMeasureSpec(i10 == 0 ? this.mChildSize : textStructure.h, MemoryConstants.GB));
        }
    }

    public void setAnimDone(boolean z) {
        this.mAnimDone = z;
    }

    public void setArc(float f, float f2) {
        if (this.mFromDegrees == f && this.mToDegrees == f2) {
            return;
        }
        this.mFromDegrees = f;
        this.mToDegrees = f2;
        requestLayout();
    }

    public void setChildSize(int i) {
        if (this.mChildSize == i || i < 0) {
            return;
        }
        this.mChildSize = i;
        requestLayout();
    }

    public void setDefaultShift(int i) {
        this.mDefaultShift = i;
        requestLayout();
    }

    public void setDuration(int i) {
        if (i > 100) {
            this.mDuration = i;
        }
    }

    public void setExpandDone(boolean z) {
        this.mExpandDone = z;
    }

    public void setExpandMenu(boolean z) {
        this.mExpanded = z;
    }

    public void setItemRotation(boolean z) {
        this.menuItemRotatationInClosing = z;
    }

    public void setMargin(int i, int i2, int i3, int i4) {
        this.mMarginBottom = i4;
        this.mMarginLeft = i;
        this.mMarginRight = i3;
        this.mMarginTop = i2;
        requestLayout();
    }

    public void setMenuGravity(int i) {
        this.menuGravity = i;
        requestLayout();
    }

    public void setMenuSize(int i) {
        this.mMenuSize = i;
        requestLayout();
    }

    public void setMinRadius(int i) {
        this.mMinRadius = i;
        requestLayout();
    }

    public void setOnMenuItemOpenClose(OnMenuItemOpenClose onMenuItemOpenClose) {
        this.menuListener = onMenuItemOpenClose;
    }

    public void setRadius(int i) {
        this.mRadius = i;
        this.mRaiusCtrl = true;
        requestLayout();
    }

    public void setTextSize(int i, int i2) {
        TextStructure textStructure = new TextStructure();
        textStructure.h = i2;
        textStructure.w = i;
        this.textStructure.add(textStructure);
    }

    public void setTextViewSize(int i, int i2) {
        this.mViewHeight = i2;
        this.mViewWidth = i;
        requestLayout();
    }

    public void setToolTipSide(int i) {
        this.mToolTipSide = i;
    }

    public void showTooltip(boolean z) {
        this.toolTipCtrl = z;
    }

    public void switchState(boolean z) {
        if (z) {
            int childCount = getChildCount();
            int i = 0;
            int i2 = 0;
            while (i < childCount) {
                int i3 = i % 2;
                if (i3 == 0 && i != 0) {
                    i2++;
                }
                int i4 = i2;
                if (i3 == 0 || this.toolTipCtrl) {
                    bindChildAnimation(getChildAt(i), i4, i, this.mDuration);
                }
                i++;
                i2 = i4;
            }
        }
        this.mExpanded = !this.mExpanded;
        if (!z) {
            requestLayout();
        }
        invalidate();
    }
}
