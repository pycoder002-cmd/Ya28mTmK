package com.bvapp.arcmenulibrary.widget;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.bvapp.arcmenulibrary.R;
import com.bvapp.arcmenulibrary.app.ThemeManager;
import com.bvapp.arcmenulibrary.drawable.LineMorphingDrawable;
import com.bvapp.arcmenulibrary.drawable.OvalShadowDrawable;
import com.bvapp.arcmenulibrary.drawable.RippleDrawable;
import com.bvapp.arcmenulibrary.util.Util;

@CoordinatorLayout.DefaultBehavior(MoveUpwardBehavior.class)
/* loaded from: classes.dex */
public class FloatingActionButton extends View implements ThemeManager.OnThemeChangedListener {
    private int mAnimDuration;
    private OvalShadowDrawable mBackground;
    protected int mCurrentStyle;
    private Drawable mIcon;
    private int mIconSize;
    private Interpolator mInterpolator;
    private Drawable mPrevIcon;
    private RippleManager mRippleManager;
    protected int mStyleId;
    private SwitchIconAnimator mSwitchIconAnimator;
    public static final int SIZE_LARGE = Util.dpToPx(64);
    public static final int SIZE_NORMAL = Util.dpToPx(56);
    public static final int SIZE_MINI = Util.dpToPx(42);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.bvapp.arcmenulibrary.widget.FloatingActionButton.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int state;

        private SavedState(Parcel parcel) {
            super(parcel);
            this.state = parcel.readInt();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public String toString() {
            return "FloatingActionButton.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " state=" + this.state + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class SwitchIconAnimator implements Runnable {
        boolean mRunning = false;
        long mStartTime;

        SwitchIconAnimator() {
        }

        public void resetAnimation() {
            this.mStartTime = SystemClock.uptimeMillis();
            FloatingActionButton.this.mIcon.setAlpha(0);
            FloatingActionButton.this.mPrevIcon.setAlpha(255);
        }

        @Override // java.lang.Runnable
        public void run() {
            float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / FloatingActionButton.this.mAnimDuration);
            float interpolation = FloatingActionButton.this.mInterpolator.getInterpolation(min);
            FloatingActionButton.this.mIcon.setAlpha(Math.round(255.0f * interpolation));
            FloatingActionButton.this.mPrevIcon.setAlpha(Math.round(255.0f * (1.0f - interpolation)));
            if (min == 1.0f) {
                stopAnimation();
            }
            if (this.mRunning) {
                if (FloatingActionButton.this.getHandler() != null) {
                    FloatingActionButton.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
                } else {
                    stopAnimation();
                }
            }
            FloatingActionButton.this.invalidate();
        }

        public boolean startAnimation(Drawable drawable) {
            if (FloatingActionButton.this.mIcon == drawable) {
                return false;
            }
            FloatingActionButton.this.mPrevIcon = FloatingActionButton.this.mIcon;
            FloatingActionButton.this.mIcon = drawable;
            float f = FloatingActionButton.this.mIconSize / 2.0f;
            FloatingActionButton.this.mIcon.setBounds((int) (FloatingActionButton.this.mBackground.getCenterX() - f), (int) (FloatingActionButton.this.mBackground.getCenterY() - f), (int) (FloatingActionButton.this.mBackground.getCenterX() + f), (int) (FloatingActionButton.this.mBackground.getCenterY() + f));
            FloatingActionButton.this.mIcon.setCallback(FloatingActionButton.this);
            if (FloatingActionButton.this.getHandler() != null) {
                resetAnimation();
                this.mRunning = true;
                FloatingActionButton.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
            } else {
                FloatingActionButton.this.mPrevIcon.setCallback(null);
                FloatingActionButton.this.unscheduleDrawable(FloatingActionButton.this.mPrevIcon);
                FloatingActionButton.this.mPrevIcon = null;
            }
            FloatingActionButton.this.invalidate();
            return true;
        }

        public void stopAnimation() {
            this.mRunning = false;
            FloatingActionButton.this.mPrevIcon.setCallback(null);
            FloatingActionButton.this.unscheduleDrawable(FloatingActionButton.this.mPrevIcon);
            FloatingActionButton.this.mPrevIcon = null;
            FloatingActionButton.this.mIcon.setAlpha(255);
            if (FloatingActionButton.this.getHandler() != null) {
                FloatingActionButton.this.getHandler().removeCallbacks(this);
            }
            FloatingActionButton.this.invalidate();
        }
    }

    public FloatingActionButton(Context context) {
        super(context);
        this.mAnimDuration = -1;
        this.mIconSize = -1;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, null, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAnimDuration = -1;
        this.mIconSize = -1;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimDuration = -1;
        this.mIconSize = -1;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, i, 0);
    }

    private void invalidateIcon() {
        float f = this.mIconSize / 2.0f;
        if (this.mIcon != null) {
            this.mIcon.setBounds((int) (this.mBackground.getCenterX() - f), (int) (this.mBackground.getCenterY() - f), (int) (this.mBackground.getCenterX() + f), (int) (this.mBackground.getCenterY() + f));
            this.mIcon.setCallback(this);
            invalidate();
        }
    }

    public static FloatingActionButton make(Context context, int i) {
        return new FloatingActionButton(context, null, i);
    }

    private void setLeftMargin(ViewGroup.LayoutParams layoutParams, int i) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = i;
            return;
        }
        Log.v(FloatingActionButton.class.getSimpleName(), "cannot recognize LayoutParams: " + layoutParams);
    }

    private void setTopMargin(ViewGroup.LayoutParams layoutParams, int i) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = i;
            return;
        }
        Log.v(FloatingActionButton.class.getSimpleName(), "cannot recognize LayoutParams: " + layoutParams);
    }

    private void updateParams(int i, int i2, int i3, ViewGroup.LayoutParams layoutParams) {
        int i4 = i3 & 7;
        if (i4 == 1) {
            setLeftMargin(layoutParams, (int) (i - this.mBackground.getCenterX()));
        } else if (i4 == 3) {
            setLeftMargin(layoutParams, (int) (i - this.mBackground.getPaddingLeft()));
        } else if (i4 != 5) {
            setLeftMargin(layoutParams, (int) (i - this.mBackground.getPaddingLeft()));
        } else {
            setLeftMargin(layoutParams, (int) ((i - this.mBackground.getPaddingLeft()) - (this.mBackground.getRadius() * 2)));
        }
        int i5 = i3 & 112;
        if (i5 == 16) {
            setTopMargin(layoutParams, (int) (i2 - this.mBackground.getCenterY()));
        } else if (i5 == 48) {
            setTopMargin(layoutParams, (int) (i2 - this.mBackground.getPaddingTop()));
        } else if (i5 != 80) {
            setTopMargin(layoutParams, (int) (i2 - this.mBackground.getPaddingTop()));
        } else {
            setTopMargin(layoutParams, (int) ((i2 - this.mBackground.getPaddingTop()) - (this.mBackground.getRadius() * 2)));
        }
        setLayoutParams(layoutParams);
    }

    public void applyStyle(int i) {
        applyStyle(getContext(), null, 0, i);
    }

    protected void applyStyle(Context context, AttributeSet attributeSet, int i, int i2) {
        int resourceId;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        ColorStateList colorStateList = null;
        while (i6 < indexCount) {
            int index = obtainStyledAttributes.getIndex(i6);
            int i9 = indexCount;
            if (index == R.styleable.FloatingActionButton_fab_radius) {
                i3 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_elevation) {
                i4 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_backgroundColor) {
                colorStateList = obtainStyledAttributes.getColorStateList(index);
            } else if (index == R.styleable.FloatingActionButton_fab_backgroundAnimDuration) {
                i5 = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_iconSrc) {
                i8 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_iconLineMorphing) {
                i7 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_iconSize) {
                this.mIconSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_animDuration) {
                this.mAnimDuration = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_interpolator && (resourceId = obtainStyledAttributes.getResourceId(R.styleable.FloatingActionButton_fab_interpolator, 0)) != 0) {
                this.mInterpolator = AnimationUtils.loadInterpolator(context, resourceId);
            }
            i6++;
            indexCount = i9;
        }
        obtainStyledAttributes.recycle();
        if (this.mIconSize < 0) {
            this.mIconSize = Util.dpToPx(24);
        }
        if (this.mAnimDuration < 0) {
            this.mAnimDuration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
        }
        if (this.mInterpolator == null) {
            this.mInterpolator = new DecelerateInterpolator();
        }
        if (this.mBackground == null) {
            if (i3 < 0) {
                i3 = Util.dpToPx(28);
            }
            int i10 = i3;
            if (i4 < 0) {
                i4 = Util.dpToPx(4);
            }
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(Util.colorAccent(context, 0));
            }
            float f = i4;
            this.mBackground = new OvalShadowDrawable(i10, colorStateList, f, f, i5 < 0 ? 0 : i5);
            this.mBackground.setInEditMode(isInEditMode());
            this.mBackground.setBounds(0, 0, getWidth(), getHeight());
            this.mBackground.setCallback(this);
        } else {
            if (i3 >= 0) {
                this.mBackground.setRadius(i3);
            }
            if (colorStateList != null) {
                this.mBackground.setColor(colorStateList);
            }
            if (i4 >= 0) {
                float f2 = i4;
                this.mBackground.setShadow(f2, f2);
            }
            if (i5 >= 0) {
                this.mBackground.setAnimationDuration(i5);
            }
        }
        if (i7 != 0) {
            setIcon(new LineMorphingDrawable.Builder(context, i7).build(), false);
        } else if (i8 != 0) {
            setIcon(context.getResources().getDrawable(i8), false);
        }
        getRippleManager().onCreate(this, context, attributeSet, i, i2);
        Drawable background = getBackground();
        if (background == null || !(background instanceof RippleDrawable)) {
            return;
        }
        RippleDrawable rippleDrawable = (RippleDrawable) background;
        rippleDrawable.setBackgroundDrawable(null);
        rippleDrawable.setMask(1, 0, 0, 0, 0, (int) this.mBackground.getPaddingLeft(), (int) this.mBackground.getPaddingTop(), (int) this.mBackground.getPaddingRight(), (int) this.mBackground.getPaddingBottom());
    }

    public void dismiss() {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
    }

    @Override // android.view.View
    public void draw(@NonNull Canvas canvas) {
        this.mBackground.draw(canvas);
        super.draw(canvas);
        if (this.mPrevIcon != null) {
            this.mPrevIcon.draw(canvas);
        }
        if (this.mIcon != null) {
            this.mIcon.draw(canvas);
        }
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackground != null) {
            this.mBackground.setState(getDrawableState());
        }
        if (this.mIcon != null) {
            this.mIcon.setState(getDrawableState());
        }
        if (this.mPrevIcon != null) {
            this.mPrevIcon.setState(getDrawableState());
        }
    }

    public ColorStateList getBackgroundColor() {
        return this.mBackground.getColor();
    }

    @Override // android.view.View
    @TargetApi(21)
    public float getElevation() {
        return Build.VERSION.SDK_INT >= 21 ? super.getElevation() : this.mBackground.getShadowSize();
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public int getIntrinsicHeight() {
        return this.mBackground.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.mBackground.getIntrinsicWidth();
    }

    public int getLineMorphingState() {
        if (this.mIcon == null || !(this.mIcon instanceof LineMorphingDrawable)) {
            return -1;
        }
        return ((LineMorphingDrawable) this.mIcon).getLineState();
    }

    public int getRadius() {
        return this.mBackground.getRadius();
    }

    protected RippleManager getRippleManager() {
        if (this.mRippleManager == null) {
            synchronized (RippleManager.class) {
                if (this.mRippleManager == null) {
                    this.mRippleManager = new RippleManager();
                }
            }
        }
        return this.mRippleManager;
    }

    public float getShadowSize() {
        return this.mBackground.getShadowSize();
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
        setClickable(true);
        this.mSwitchIconAnimator = new SwitchIconAnimator();
        applyStyle(context, attributeSet, i, i2);
        if (isInEditMode()) {
            return;
        }
        this.mStyleId = ThemeManager.getStyleId(context, attributeSet, i, i2);
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mStyleId != 0) {
            ThemeManager.getInstance().registerOnThemeChangedListener(this);
            onThemeChanged(null);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RippleManager.cancelRipple(this);
        if (this.mStyleId != 0) {
            ThemeManager.getInstance().unregisterOnThemeChangedListener(this);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mBackground.getIntrinsicWidth(), this.mBackground.getIntrinsicHeight());
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.state >= 0) {
            setLineMorphingState(savedState.state, false);
        }
        requestLayout();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.state = getLineMorphingState();
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mBackground.setBounds(0, 0, i, i2);
        if (this.mIcon != null) {
            float f = this.mIconSize / 2.0f;
            this.mIcon.setBounds((int) (this.mBackground.getCenterX() - f), (int) (this.mBackground.getCenterY() - f), (int) (this.mBackground.getCenterX() + f), (int) (this.mBackground.getCenterY() + f));
        }
        if (this.mPrevIcon != null) {
            float f2 = this.mIconSize / 2.0f;
            this.mPrevIcon.setBounds((int) (this.mBackground.getCenterX() - f2), (int) (this.mBackground.getCenterY() - f2), (int) (this.mBackground.getCenterX() + f2), (int) (this.mBackground.getCenterY() + f2));
        }
    }

    @Override // com.bvapp.arcmenulibrary.app.ThemeManager.OnThemeChangedListener
    public void onThemeChanged(ThemeManager.OnThemeChangedEvent onThemeChangedEvent) {
        int currentStyle = ThemeManager.getInstance().getCurrentStyle(this.mStyleId);
        if (this.mCurrentStyle != currentStyle) {
            this.mCurrentStyle = currentStyle;
            applyStyle(this.mCurrentStyle);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 0 || this.mBackground.isPointerOver(motionEvent.getX(), motionEvent.getY())) {
            return getRippleManager().onTouchEvent(this, motionEvent) || super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mBackground.setColor(i);
        invalidate();
    }

    public void setBackgroundColor(ColorStateList colorStateList) {
        this.mBackground.setColor(colorStateList);
        invalidate();
    }

    @Override // android.view.View
    @TargetApi(21)
    public void setElevation(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f);
        } else if (this.mBackground.setShadow(f, f)) {
            requestLayout();
        }
    }

    public void setIcon(@DrawableRes int i) {
        try {
            setIcon(ContextCompat.getDrawable(getContext(), i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (this.mIcon != null) {
            this.mIcon.setCallback(null);
            unscheduleDrawable(this.mIcon);
        }
        this.mIcon = drawable;
        invalidateIcon();
    }

    public void setIcon(Drawable drawable, boolean z) {
        if (drawable == null) {
            return;
        }
        if (!z) {
            setIcon(drawable);
        } else {
            this.mSwitchIconAnimator.startAnimation(drawable);
            invalidate();
        }
    }

    public void setIconSize(int i) {
        int i2 = i / 2;
        if (i2 >= (this.mBackground.getRadius() * 4) / 5 || i2 <= (this.mBackground.getRadius() * 1) / 5) {
            return;
        }
        this.mIconSize = i;
        invalidateIcon();
    }

    public void setLineMorphingState(int i, boolean z) {
        if (this.mIcon == null || !(this.mIcon instanceof LineMorphingDrawable)) {
            return;
        }
        ((LineMorphingDrawable) this.mIcon).switchLineState(i, z);
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        RippleManager rippleManager = getRippleManager();
        if (onClickListener == rippleManager) {
            super.setOnClickListener(onClickListener);
        } else {
            rippleManager.setOnClickListener(onClickListener);
            setOnClickListener(rippleManager);
        }
    }

    public void setOnShrinkExpandClickListener(View.OnClickListener onClickListener) {
        Util.shrinkExpandAnimation(this, onClickListener);
    }

    public void setRadius(int i) {
        if (this.mBackground.setRadius(i)) {
            this.mIconSize = i;
            requestLayout();
        }
    }

    public void setShadow(boolean z) {
        int dpToPx = Util.dpToPx(4);
        if (z) {
            float f = dpToPx;
            this.mBackground.setShadow(f, f);
        } else {
            this.mBackground.setShadow(0.0f, 0.0f);
        }
        invalidate();
    }

    public void setSize(int i) {
        int i2 = i / 2;
        if (this.mBackground.setRadius(i2)) {
            this.mIconSize = i2;
            requestLayout();
        }
    }

    public void show(Activity activity, int i, int i2, int i3) {
        if (getParent() != null) {
            updateLocation(i, i2, i3);
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mBackground.getIntrinsicWidth(), this.mBackground.getIntrinsicHeight());
        updateParams(i, i2, i3, layoutParams);
        activity.getWindow().addContentView(this, layoutParams);
    }

    public void show(ViewGroup viewGroup, int i, int i2, int i3) {
        if (getParent() != null) {
            updateLocation(i, i2, i3);
            return;
        }
        ViewGroup.LayoutParams generateLayoutParams = viewGroup.generateLayoutParams((AttributeSet) null);
        generateLayoutParams.width = this.mBackground.getIntrinsicWidth();
        generateLayoutParams.height = this.mBackground.getIntrinsicHeight();
        updateParams(i, i2, i3, generateLayoutParams);
        viewGroup.addView(this, generateLayoutParams);
    }

    public void updateLocation(int i, int i2, int i3) {
        if (getParent() != null) {
            updateParams(i, i2, i3, getLayoutParams());
        } else {
            Log.v(FloatingActionButton.class.getSimpleName(), "updateLocation() is called without parent");
        }
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || this.mBackground == drawable || this.mIcon == drawable || this.mPrevIcon == drawable;
    }
}
