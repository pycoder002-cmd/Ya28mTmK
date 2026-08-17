package com.bvapp.arcmenulibrary.drawable;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.bvapp.arcmenulibrary.util.ColorUtil;

/* loaded from: classes.dex */
public class OvalShadowDrawable extends Drawable implements Animatable {
    private static final int COLOR_SHADOW_END = 0;
    private static final int COLOR_SHADOW_START = 1275068416;
    public static final long FRAME_DURATION = 16;
    private int mAnimDuration;
    private float mAnimProgress;
    private ColorStateList mColorStateList;
    private int mCurColor;
    private Paint mGlowPaint;
    private Path mGlowPath;
    private int mPrevColor;
    private int mRadius;
    private float mShadowOffset;
    private Paint mShadowPaint;
    private Path mShadowPath;
    private float mShadowSize;
    private long mStartTime;
    private boolean mRunning = false;
    private boolean mEnable = true;
    private boolean mInEditMode = false;
    private boolean mAnimEnable = true;
    private RectF mTempRect = new RectF();
    private boolean mNeedBuildShadow = true;
    private final Runnable mUpdater = new Runnable() { // from class: com.bvapp.arcmenulibrary.drawable.OvalShadowDrawable.1
        @Override // java.lang.Runnable
        public void run() {
            OvalShadowDrawable.this.update();
        }
    };
    private Paint mPaint = new Paint(5);

    public OvalShadowDrawable(int i, ColorStateList colorStateList, float f, float f2, int i2) {
        this.mAnimDuration = i2;
        this.mPaint.setStyle(Paint.Style.FILL);
        setColor(colorStateList);
        setRadius(i);
        setShadow(f, f2);
    }

    private void buildShadow() {
        if (this.mShadowSize <= 0.0f) {
            return;
        }
        if (this.mShadowPaint == null) {
            this.mShadowPaint = new Paint(5);
            this.mShadowPaint.setStyle(Paint.Style.FILL);
            this.mShadowPaint.setDither(true);
        }
        this.mShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, this.mShadowSize + this.mRadius, new int[]{COLOR_SHADOW_START, COLOR_SHADOW_START, 0}, new float[]{0.0f, this.mRadius / ((this.mRadius + this.mShadowSize) + this.mShadowOffset), 1.0f}, Shader.TileMode.CLAMP));
        if (this.mShadowPath == null) {
            this.mShadowPath = new Path();
            this.mShadowPath.setFillType(Path.FillType.EVEN_ODD);
        } else {
            this.mShadowPath.reset();
        }
        float f = this.mRadius + this.mShadowSize;
        float f2 = -f;
        this.mTempRect.set(f2, f2, f, f);
        this.mShadowPath.addOval(this.mTempRect, Path.Direction.CW);
        float f3 = this.mRadius - 1;
        float f4 = -f3;
        this.mTempRect.set(f4, f4 - this.mShadowOffset, f3, f3 - this.mShadowOffset);
        this.mShadowPath.addOval(this.mTempRect, Path.Direction.CW);
        if (this.mGlowPaint == null) {
            this.mGlowPaint = new Paint(5);
            this.mGlowPaint.setStyle(Paint.Style.FILL);
            this.mGlowPaint.setDither(true);
        }
        this.mGlowPaint.setShader(new RadialGradient(0.0f, 0.0f, (this.mShadowSize / 2.0f) + this.mRadius, new int[]{COLOR_SHADOW_START, COLOR_SHADOW_START, 0}, new float[]{0.0f, (this.mRadius - (this.mShadowSize / 2.0f)) / (this.mRadius + (this.mShadowSize / 2.0f)), 1.0f}, Shader.TileMode.CLAMP));
        if (this.mGlowPath == null) {
            this.mGlowPath = new Path();
            this.mGlowPath.setFillType(Path.FillType.EVEN_ODD);
        } else {
            this.mGlowPath.reset();
        }
        float f5 = this.mRadius + (this.mShadowSize / 2.0f);
        float f6 = -f5;
        this.mTempRect.set(f6, f6, f5, f5);
        this.mGlowPath.addOval(this.mTempRect, Path.Direction.CW);
        float f7 = this.mRadius - 1;
        float f8 = -f7;
        this.mTempRect.set(f8, f8, f7, f7);
        this.mGlowPath.addOval(this.mTempRect, Path.Direction.CW);
    }

    private static boolean hasState(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private void resetAnimation() {
        this.mStartTime = SystemClock.uptimeMillis();
        this.mAnimProgress = 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        this.mAnimProgress = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mAnimDuration);
        if (this.mAnimProgress == 1.0f) {
            this.mRunning = false;
        }
        if (isRunning()) {
            scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mNeedBuildShadow) {
            buildShadow();
            this.mNeedBuildShadow = false;
        }
        if (this.mShadowSize > 0.0f) {
            int save = canvas.save();
            canvas.translate(this.mShadowSize + this.mRadius, this.mShadowSize + this.mRadius + this.mShadowOffset);
            canvas.drawPath(this.mShadowPath, this.mShadowPaint);
            canvas.restoreToCount(save);
        }
        int save2 = canvas.save();
        canvas.translate(this.mShadowSize + this.mRadius, this.mShadowSize + this.mRadius);
        if (this.mShadowSize > 0.0f) {
            canvas.drawPath(this.mGlowPath, this.mGlowPaint);
        }
        this.mTempRect.set(-this.mRadius, -this.mRadius, this.mRadius, this.mRadius);
        if (isRunning()) {
            this.mPaint.setColor(ColorUtil.getMiddleColor(this.mPrevColor, this.mCurColor, this.mAnimProgress));
        } else {
            this.mPaint.setColor(this.mCurColor);
        }
        canvas.drawOval(this.mTempRect, this.mPaint);
        canvas.restoreToCount(save2);
    }

    public float getCenterX() {
        return this.mRadius + this.mShadowSize;
    }

    public float getCenterY() {
        return this.mRadius + this.mShadowSize;
    }

    public ColorStateList getColor() {
        return this.mColorStateList;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) (((this.mRadius + this.mShadowSize) * 2.0f) + this.mShadowOffset + 0.5f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) (((this.mRadius + this.mShadowSize) * 2.0f) + 0.5f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public float getPaddingBottom() {
        return this.mShadowSize + this.mShadowOffset;
    }

    public float getPaddingLeft() {
        return this.mShadowSize;
    }

    public float getPaddingRight() {
        return this.mShadowSize;
    }

    public float getPaddingTop() {
        return this.mShadowSize;
    }

    public int getRadius() {
        return this.mRadius;
    }

    public float getShadowOffset() {
        return this.mShadowOffset;
    }

    public float getShadowSize() {
        return this.mShadowSize;
    }

    public boolean isPointerOver(float f, float f2) {
        return ((float) Math.sqrt(Math.pow((double) (f - getCenterX()), 2.0d) + Math.pow((double) (f2 - getCenterY()), 2.0d))) < ((float) this.mRadius);
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mRunning;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        stop();
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        this.mEnable = hasState(iArr, R.attr.state_enabled);
        int colorForState = this.mColorStateList.getColorForState(iArr, this.mCurColor);
        if (this.mCurColor == colorForState) {
            if (isRunning()) {
                return false;
            }
            this.mPrevColor = colorForState;
            return false;
        }
        if (this.mInEditMode || !this.mAnimEnable || !this.mEnable || this.mAnimDuration <= 0) {
            this.mPrevColor = colorForState;
            this.mCurColor = colorForState;
            invalidateSelf();
            return true;
        }
        this.mPrevColor = isRunning() ? this.mPrevColor : this.mCurColor;
        this.mCurColor = colorForState;
        start();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        this.mRunning = true;
        super.scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mShadowPaint.setAlpha(i);
        this.mPaint.setAlpha(i);
    }

    public void setAnimEnable(boolean z) {
        this.mAnimEnable = z;
    }

    public boolean setAnimationDuration(int i) {
        if (this.mAnimDuration == i) {
            return false;
        }
        this.mAnimDuration = i;
        return true;
    }

    public void setColor(int i) {
        this.mColorStateList = ColorStateList.valueOf(i);
        onStateChange(getState());
    }

    public void setColor(ColorStateList colorStateList) {
        this.mColorStateList = colorStateList;
        onStateChange(getState());
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mShadowPaint.setColorFilter(colorFilter);
        this.mPaint.setColorFilter(colorFilter);
    }

    public void setInEditMode(boolean z) {
        this.mInEditMode = z;
    }

    public boolean setRadius(int i) {
        if (this.mRadius == i) {
            return false;
        }
        this.mRadius = i;
        this.mNeedBuildShadow = true;
        invalidateSelf();
        return true;
    }

    public boolean setShadow(float f, float f2) {
        if (this.mShadowSize == f && this.mShadowOffset == f2) {
            return false;
        }
        this.mShadowSize = f;
        this.mShadowOffset = f2;
        this.mNeedBuildShadow = true;
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        resetAnimation();
        scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.mRunning = false;
        unscheduleSelf(this.mUpdater);
        invalidateSelf();
    }
}
