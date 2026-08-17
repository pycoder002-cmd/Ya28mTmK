package me.zhanghai.android.materialprogressbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.util.Log;
import me.zhanghai.android.materialprogressbar.internal.ThemeUtils;

/* loaded from: classes2.dex */
public class HorizontalProgressDrawable extends LayerDrawable implements IntrinsicPaddingDrawable, ShowBackgroundDrawable, TintableDrawable {
    private static final String TAG = "HorizontalProgressDrawable";
    private float mBackgroundAlpha;
    private HorizontalProgressBackgroundDrawable mBackgroundDrawable;
    private boolean mHasSecondaryProgressTint;
    private boolean mHasSecondaryProgressTintColor;
    private SingleHorizontalProgressDrawable mProgressDrawable;
    private SingleHorizontalProgressDrawable mSecondaryProgressDrawable;
    private ColorStateList mSecondaryProgressTint;
    private int mSecondaryProgressTintColor;

    public HorizontalProgressDrawable(Context context) {
        super(new Drawable[]{new HorizontalProgressBackgroundDrawable(context), new SingleHorizontalProgressDrawable(context), new SingleHorizontalProgressDrawable(context)});
        this.mBackgroundAlpha = ThemeUtils.getFloatFromAttrRes(android.R.attr.disabledAlpha, context);
        setId(0, android.R.id.background);
        this.mBackgroundDrawable = (HorizontalProgressBackgroundDrawable) getDrawable(0);
        setId(1, android.R.id.secondaryProgress);
        this.mSecondaryProgressDrawable = (SingleHorizontalProgressDrawable) getDrawable(1);
        setId(2, android.R.id.progress);
        this.mProgressDrawable = (SingleHorizontalProgressDrawable) getDrawable(2);
        setTint(ThemeUtils.getColorFromAttrRes(R.attr.colorControlActivated, context));
    }

    private float compositeAlpha(float f, float f2) {
        return f + (f2 * (1.0f - f));
    }

    private void setSecondaryProgressTint(int i) {
        this.mHasSecondaryProgressTintColor = true;
        this.mSecondaryProgressTintColor = i;
        this.mHasSecondaryProgressTint = false;
        updateSecondaryProgressTint();
    }

    private void setSecondaryProgressTintList(ColorStateList colorStateList) {
        this.mHasSecondaryProgressTintColor = false;
        this.mHasSecondaryProgressTint = true;
        this.mSecondaryProgressTint = colorStateList;
        updateSecondaryProgressTint();
    }

    @SuppressLint({"NewApi"})
    private void updateSecondaryProgressTint() {
        if (this.mHasSecondaryProgressTintColor) {
            int i = this.mSecondaryProgressTintColor;
            if (!getShowBackground()) {
                float alpha = Color.alpha(i) / 255.0f;
                i = ColorUtils.setAlphaComponent(i, Math.round(255.0f * compositeAlpha(alpha, alpha)));
            }
            this.mSecondaryProgressDrawable.setTint(i);
            return;
        }
        if (this.mHasSecondaryProgressTint) {
            ColorStateList colorStateList = this.mSecondaryProgressTint;
            if (!getShowBackground()) {
                colorStateList = colorStateList.withAlpha(Math.round(255.0f * compositeAlpha(this.mBackgroundAlpha, this.mBackgroundAlpha)));
            }
            this.mSecondaryProgressDrawable.setTintList(colorStateList);
        }
    }

    @Override // me.zhanghai.android.materialprogressbar.ShowBackgroundDrawable
    public boolean getShowBackground() {
        return this.mBackgroundDrawable.getShowBackground();
    }

    @Override // me.zhanghai.android.materialprogressbar.IntrinsicPaddingDrawable
    public boolean getUseIntrinsicPadding() {
        return this.mBackgroundDrawable.getUseIntrinsicPadding();
    }

    @Override // me.zhanghai.android.materialprogressbar.ShowBackgroundDrawable
    public void setShowBackground(boolean z) {
        if (this.mBackgroundDrawable.getShowBackground() != z) {
            this.mBackgroundDrawable.setShowBackground(z);
            updateSecondaryProgressTint();
        }
    }

    @Override // android.graphics.drawable.Drawable, me.zhanghai.android.materialprogressbar.TintableDrawable
    @SuppressLint({"NewApi"})
    public void setTint(@ColorInt int i) {
        int alphaComponent = ColorUtils.setAlphaComponent(i, Math.round(Color.alpha(i) * this.mBackgroundAlpha));
        this.mBackgroundDrawable.setTint(alphaComponent);
        setSecondaryProgressTint(alphaComponent);
        this.mProgressDrawable.setTint(i);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable, me.zhanghai.android.materialprogressbar.TintableDrawable
    @SuppressLint({"NewApi"})
    public void setTintList(@Nullable ColorStateList colorStateList) {
        ColorStateList colorStateList2;
        if (colorStateList != null) {
            if (!colorStateList.isOpaque()) {
                Log.w(TAG, "setTintList() called with a non-opaque ColorStateList, its original alpha will be discarded");
            }
            colorStateList2 = colorStateList.withAlpha(Math.round(255.0f * this.mBackgroundAlpha));
        } else {
            colorStateList2 = null;
        }
        this.mBackgroundDrawable.setTintList(colorStateList2);
        setSecondaryProgressTintList(colorStateList2);
        this.mProgressDrawable.setTintList(colorStateList);
    }

    @Override // android.graphics.drawable.Drawable, me.zhanghai.android.materialprogressbar.TintableDrawable
    @SuppressLint({"NewApi"})
    public void setTintMode(@NonNull PorterDuff.Mode mode) {
        this.mBackgroundDrawable.setTintMode(mode);
        this.mSecondaryProgressDrawable.setTintMode(mode);
        this.mProgressDrawable.setTintMode(mode);
    }

    @Override // me.zhanghai.android.materialprogressbar.IntrinsicPaddingDrawable
    public void setUseIntrinsicPadding(boolean z) {
        this.mBackgroundDrawable.setUseIntrinsicPadding(z);
        this.mSecondaryProgressDrawable.setUseIntrinsicPadding(z);
        this.mProgressDrawable.setUseIntrinsicPadding(z);
    }
}
