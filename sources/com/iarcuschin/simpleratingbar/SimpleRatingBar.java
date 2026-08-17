package com.iarcuschin.simpleratingbar;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.ColorInt;
import android.support.annotation.Dimension;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

/* loaded from: classes.dex */
public class SimpleRatingBar extends View {

    @ColorInt
    private int backgroundColor;

    @ColorInt
    private int borderColor;
    private View.OnClickListener clickListener;
    private CornerPathEffect cornerPathEffect;
    private float currentStarSize;
    private float defaultStarSize;
    private float desiredStarSize;
    private boolean drawBorderEnabled;

    @ColorInt
    private int fillColor;
    private Gravity gravity;
    private Bitmap internalBitmap;
    private Canvas internalCanvas;
    private boolean isIndicator;
    private float maxStarSize;
    private int numberOfStars;
    private Paint paintStarBackground;
    private Paint paintStarBorder;
    private Paint paintStarFill;
    private Paint paintStarOutline;

    @ColorInt
    private int pressedBackgroundColor;

    @ColorInt
    private int pressedBorderColor;

    @ColorInt
    private int pressedFillColor;

    @ColorInt
    private int pressedStarBackgroundColor;
    private float rating;
    private ValueAnimator ratingAnimator;
    private OnRatingBarChangeListener ratingListener;

    @ColorInt
    private int starBackgroundColor;
    private float starBorderWidth;
    private float starCornerRadius;
    private Path starPath;
    private float[] starVertex;
    private RectF starsDrawingSpace;
    private float starsSeparation;
    private RectF starsTouchSpace;
    private float stepSize;
    private boolean touchInProgress;

    /* loaded from: classes.dex */
    public class AnimationBuilder {
        private Animator.AnimatorListener animatorListener;
        private long duration;
        private Interpolator interpolator;
        private SimpleRatingBar ratingBar;
        private float ratingTarget;
        private int repeatCount;
        private int repeatMode;

        private AnimationBuilder(SimpleRatingBar simpleRatingBar) {
            this.ratingBar = simpleRatingBar;
            this.duration = 2000L;
            this.interpolator = new BounceInterpolator();
            this.ratingTarget = simpleRatingBar.getNumberOfStars();
            this.repeatCount = 1;
            this.repeatMode = 2;
        }

        public AnimationBuilder setAnimatorListener(Animator.AnimatorListener animatorListener) {
            this.animatorListener = animatorListener;
            return this;
        }

        public AnimationBuilder setDuration(long j) {
            this.duration = j;
            return this;
        }

        public AnimationBuilder setInterpolator(Interpolator interpolator) {
            this.interpolator = interpolator;
            return this;
        }

        public AnimationBuilder setRatingTarget(float f) {
            this.ratingTarget = f;
            return this;
        }

        public AnimationBuilder setRepeatCount(int i) {
            this.repeatCount = i;
            return this;
        }

        public AnimationBuilder setRepeatMode(int i) {
            this.repeatMode = i;
            return this;
        }

        public void start() {
            this.ratingBar.animateRating(this);
        }
    }

    /* loaded from: classes.dex */
    public enum Gravity {
        Left(0),
        Right(1);

        int id;

        Gravity(int i) {
            this.id = i;
        }

        static Gravity fromId(int i) {
            for (Gravity gravity : values()) {
                if (gravity.id == i) {
                    return gravity;
                }
            }
            Log.w("SimpleRatingBar", String.format("Gravity chosen is neither 'left' nor 'right', I will set it to Left", new Object[0]));
            return Left;
        }
    }

    /* loaded from: classes.dex */
    public interface OnRatingBarChangeListener {
        void onRatingChanged(SimpleRatingBar simpleRatingBar, float f, boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.iarcuschin.simpleratingbar.SimpleRatingBar.SavedState.1
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
        private float rating;

        protected SavedState(Parcel parcel) {
            super(parcel);
            this.rating = 0.0f;
            this.rating = parcel.readFloat();
        }

        @TargetApi(24)
        protected SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.rating = 0.0f;
        }

        protected SavedState(Parcelable parcelable) {
            super(parcelable);
            this.rating = 0.0f;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.rating);
        }
    }

    public SimpleRatingBar(Context context) {
        super(context);
        initView();
    }

    public SimpleRatingBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        parseAttrs(attributeSet);
        initView();
    }

    public SimpleRatingBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        parseAttrs(attributeSet);
        initView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animateRating(AnimationBuilder animationBuilder) {
        animationBuilder.ratingTarget = normalizeRating(animationBuilder.ratingTarget);
        this.ratingAnimator = ValueAnimator.ofFloat(0.0f, animationBuilder.ratingTarget);
        this.ratingAnimator.setDuration(animationBuilder.duration);
        this.ratingAnimator.setRepeatCount(animationBuilder.repeatCount);
        this.ratingAnimator.setRepeatMode(animationBuilder.repeatMode);
        this.ratingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.iarcuschin.simpleratingbar.SimpleRatingBar.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SimpleRatingBar.this.setRating(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        if (animationBuilder.interpolator != null) {
            this.ratingAnimator.setInterpolator(animationBuilder.interpolator);
        }
        if (animationBuilder.animatorListener != null) {
            this.ratingAnimator.addListener(animationBuilder.animatorListener);
        }
        this.ratingAnimator.addListener(new Animator.AnimatorListener() { // from class: com.iarcuschin.simpleratingbar.SimpleRatingBar.2
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                if (SimpleRatingBar.this.ratingListener != null) {
                    SimpleRatingBar.this.ratingListener.onRatingChanged(SimpleRatingBar.this, SimpleRatingBar.this.rating, false);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (SimpleRatingBar.this.ratingListener != null) {
                    SimpleRatingBar.this.ratingListener.onRatingChanged(SimpleRatingBar.this, SimpleRatingBar.this.rating, false);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
                if (SimpleRatingBar.this.ratingListener != null) {
                    SimpleRatingBar.this.ratingListener.onRatingChanged(SimpleRatingBar.this, SimpleRatingBar.this.rating, false);
                }
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
        this.ratingAnimator.start();
    }

    private float calculateBestStarSize(int i, int i2) {
        if (this.maxStarSize != 2.14748365E9f) {
            return (((float) calculateTotalWidth(this.maxStarSize, this.numberOfStars, this.starsSeparation, true)) >= ((float) i) || ((float) calculateTotalHeight(this.maxStarSize, this.numberOfStars, this.starsSeparation, true)) >= ((float) i2)) ? Math.min((((i - getPaddingLeft()) - getPaddingRight()) - (this.starsSeparation * (this.numberOfStars - 1))) / this.numberOfStars, (i2 - getPaddingTop()) - getPaddingBottom()) : this.maxStarSize;
        }
        return Math.min((((i - getPaddingLeft()) - getPaddingRight()) - (this.starsSeparation * (this.numberOfStars - 1))) / this.numberOfStars, (i2 - getPaddingTop()) - getPaddingBottom());
    }

    private int calculateTotalHeight(float f, int i, float f2, boolean z) {
        return Math.round(f) + (z ? getPaddingTop() + getPaddingBottom() : 0);
    }

    private int calculateTotalWidth(float f, int i, float f2, boolean z) {
        return Math.round((f * i) + (f2 * (i - 1))) + (z ? getPaddingLeft() + getPaddingRight() : 0);
    }

    private void drawFromLeftToRight(Canvas canvas) {
        float f = this.rating;
        float f2 = this.starsDrawingSpace.left;
        float f3 = this.starsDrawingSpace.top;
        float f4 = f2;
        float f5 = f;
        for (int i = 0; i < this.numberOfStars; i++) {
            if (f5 >= 1.0f) {
                drawStar(canvas, f4, f3, 1.0f, Gravity.Left);
                f5 -= 1.0f;
            } else {
                drawStar(canvas, f4, f3, f5, Gravity.Left);
                f5 = 0.0f;
            }
            f4 += this.starsSeparation + this.currentStarSize;
        }
    }

    private void drawFromRightToLeft(Canvas canvas) {
        float f = this.rating;
        float f2 = this.starsDrawingSpace.right - this.currentStarSize;
        float f3 = this.starsDrawingSpace.top;
        float f4 = f2;
        float f5 = f;
        for (int i = 0; i < this.numberOfStars; i++) {
            if (f5 >= 1.0f) {
                drawStar(canvas, f4, f3, 1.0f, Gravity.Right);
                f5 -= 1.0f;
            } else {
                drawStar(canvas, f4, f3, f5, Gravity.Right);
                f5 = 0.0f;
            }
            f4 -= this.starsSeparation + this.currentStarSize;
        }
    }

    private void drawStar(Canvas canvas, float f, float f2, float f3, Gravity gravity) {
        float f4 = this.currentStarSize * f3;
        this.starPath.reset();
        this.starPath.moveTo(this.starVertex[0] + f, this.starVertex[1] + f2);
        for (int i = 2; i < this.starVertex.length; i += 2) {
            this.starPath.lineTo(this.starVertex[i] + f, this.starVertex[i + 1] + f2);
        }
        this.starPath.close();
        canvas.drawPath(this.starPath, this.paintStarOutline);
        if (gravity == Gravity.Left) {
            float f5 = f + f4;
            canvas.drawRect(f, f2, f5 + (this.currentStarSize * 0.02f), f2 + this.currentStarSize, this.paintStarFill);
            canvas.drawRect(f5, f2, f + this.currentStarSize, f2 + this.currentStarSize, this.paintStarBackground);
        } else {
            canvas.drawRect((this.currentStarSize + f) - ((this.currentStarSize * 0.02f) + f4), f2, f + this.currentStarSize, f2 + this.currentStarSize, this.paintStarFill);
            canvas.drawRect(f, f2, (this.currentStarSize + f) - f4, f2 + this.currentStarSize, this.paintStarBackground);
        }
        if (this.drawBorderEnabled) {
            canvas.drawPath(this.starPath, this.paintStarBorder);
        }
    }

    private void generateInternalCanvas(int i, int i2) {
        if (this.internalBitmap != null) {
            this.internalBitmap.recycle();
        }
        if (i <= 0 || i2 <= 0) {
            return;
        }
        this.internalBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        this.internalBitmap.eraseColor(0);
        this.internalCanvas = new Canvas(this.internalBitmap);
    }

    private void initView() {
        this.starPath = new Path();
        this.cornerPathEffect = new CornerPathEffect(this.starCornerRadius);
        this.paintStarOutline = new Paint(5);
        this.paintStarOutline.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paintStarOutline.setAntiAlias(true);
        this.paintStarOutline.setDither(true);
        this.paintStarOutline.setStrokeJoin(Paint.Join.ROUND);
        this.paintStarOutline.setStrokeCap(Paint.Cap.ROUND);
        this.paintStarOutline.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.paintStarOutline.setPathEffect(this.cornerPathEffect);
        this.paintStarBorder = new Paint(5);
        this.paintStarBorder.setStyle(Paint.Style.STROKE);
        this.paintStarBorder.setStrokeJoin(Paint.Join.ROUND);
        this.paintStarBorder.setStrokeCap(Paint.Cap.ROUND);
        this.paintStarBorder.setStrokeWidth(this.starBorderWidth);
        this.paintStarBorder.setPathEffect(this.cornerPathEffect);
        this.paintStarBackground = new Paint(5);
        this.paintStarBackground.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paintStarBackground.setAntiAlias(true);
        this.paintStarBackground.setDither(true);
        this.paintStarBackground.setStrokeJoin(Paint.Join.ROUND);
        this.paintStarBackground.setStrokeCap(Paint.Cap.ROUND);
        this.paintStarFill = new Paint(5);
        this.paintStarFill.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paintStarFill.setAntiAlias(true);
        this.paintStarFill.setDither(true);
        this.paintStarFill.setStrokeJoin(Paint.Join.ROUND);
        this.paintStarFill.setStrokeCap(Paint.Cap.ROUND);
        this.defaultStarSize = TypedValue.applyDimension(1, 30.0f, getResources().getDisplayMetrics());
    }

    private float normalizeRating(float f) {
        if (f < 0.0f) {
            Log.w("SimpleRatingBar", String.format("Assigned rating is less than 0 (%f < 0), I will set it to exactly 0", Float.valueOf(f)));
            return 0.0f;
        }
        if (f <= this.numberOfStars) {
            return f;
        }
        Log.w("SimpleRatingBar", String.format("Assigned rating is greater than numberOfStars (%f > %d), I will set it to exactly numberOfStars", Float.valueOf(f), Integer.valueOf(this.numberOfStars)));
        return this.numberOfStars;
    }

    private void parseAttrs(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.SimpleRatingBar);
        this.borderColor = obtainStyledAttributes.getColor(R.styleable.SimpleRatingBar_srb_borderColor, getResources().getColor(R.color.golden_stars));
        this.fillColor = obtainStyledAttributes.getColor(R.styleable.SimpleRatingBar_srb_fillColor, this.borderColor);
        this.starBackgroundColor = obtainStyledAttributes.getColor(R.styleable.SimpleRatingBar_srb_starBackgroundColor, 0);
        this.backgroundColor = obtainStyledAttributes.getColor(R.styleable.SimpleRatingBar_srb_backgroundColor, 0);
        this.pressedBorderColor = obtainStyledAttributes.getColor(R.styleable.SimpleRatingBar_srb_pressedBorderColor, this.borderColor);
        this.pressedFillColor = obtainStyledAttributes.getColor(R.styleable.SimpleRatingBar_srb_pressedFillColor, this.fillColor);
        this.pressedStarBackgroundColor = obtainStyledAttributes.getColor(R.styleable.SimpleRatingBar_srb_pressedStarBackgroundColor, this.starBackgroundColor);
        this.pressedBackgroundColor = obtainStyledAttributes.getColor(R.styleable.SimpleRatingBar_srb_pressedBackgroundColor, this.backgroundColor);
        this.numberOfStars = obtainStyledAttributes.getInteger(R.styleable.SimpleRatingBar_srb_numberOfStars, 5);
        this.starsSeparation = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SimpleRatingBar_srb_starsSeparation, (int) valueToPixels(4.0f, 0));
        this.maxStarSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SimpleRatingBar_srb_maxStarSize, Integer.MAX_VALUE);
        this.desiredStarSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.SimpleRatingBar_srb_starSize, Integer.MAX_VALUE);
        this.stepSize = obtainStyledAttributes.getFloat(R.styleable.SimpleRatingBar_srb_stepSize, 0.1f);
        this.starBorderWidth = obtainStyledAttributes.getFloat(R.styleable.SimpleRatingBar_srb_starBorderWidth, 5.0f);
        this.starCornerRadius = obtainStyledAttributes.getFloat(R.styleable.SimpleRatingBar_srb_starCornerRadius, 6.0f);
        this.rating = normalizeRating(obtainStyledAttributes.getFloat(R.styleable.SimpleRatingBar_srb_rating, 0.0f));
        this.isIndicator = obtainStyledAttributes.getBoolean(R.styleable.SimpleRatingBar_srb_isIndicator, false);
        this.drawBorderEnabled = obtainStyledAttributes.getBoolean(R.styleable.SimpleRatingBar_srb_drawBorderEnabled, true);
        this.gravity = Gravity.fromId(obtainStyledAttributes.getInt(R.styleable.SimpleRatingBar_srb_gravity, Gravity.Left.id));
        obtainStyledAttributes.recycle();
        validateAttrs();
    }

    private void performStarSizeAssociatedCalculations(int i, int i2) {
        float calculateTotalWidth = calculateTotalWidth(this.currentStarSize, this.numberOfStars, this.starsSeparation, false);
        float calculateTotalHeight = calculateTotalHeight(this.currentStarSize, this.numberOfStars, this.starsSeparation, false);
        float paddingLeft = ((((i - getPaddingLeft()) - getPaddingRight()) / 2) - (calculateTotalWidth / 2.0f)) + getPaddingLeft();
        float paddingTop = ((((i2 - getPaddingTop()) - getPaddingBottom()) / 2) - (calculateTotalHeight / 2.0f)) + getPaddingTop();
        this.starsDrawingSpace = new RectF(paddingLeft, paddingTop, calculateTotalWidth + paddingLeft, calculateTotalHeight + paddingTop);
        float width = this.starsDrawingSpace.width() * 0.05f;
        this.starsTouchSpace = new RectF(this.starsDrawingSpace.left - width, this.starsDrawingSpace.top, this.starsDrawingSpace.right + width, this.starsDrawingSpace.bottom);
        float f = this.currentStarSize * 0.2f;
        float f2 = this.currentStarSize * 0.35f;
        float f3 = this.currentStarSize * 0.5f;
        float f4 = this.currentStarSize * 0.05f;
        float f5 = this.currentStarSize * 0.03f;
        float f6 = this.currentStarSize * 0.38f;
        float f7 = this.currentStarSize * 0.32f;
        float f8 = this.currentStarSize * 0.6f;
        this.starVertex = new float[]{f5, f6, f5 + f2, f6, f3, f4, (this.currentStarSize - f5) - f2, f6, this.currentStarSize - f5, f6, this.currentStarSize - f7, f8, this.currentStarSize - f, this.currentStarSize - f4, f3, this.currentStarSize - (this.currentStarSize * 0.27f), f, this.currentStarSize - f4, f7, f8};
    }

    private void setNewRatingFromTouch(float f, float f2) {
        if (this.gravity != Gravity.Left) {
            f = getWidth() - f;
        }
        if (f < this.starsDrawingSpace.left) {
            this.rating = 0.0f;
            return;
        }
        if (f > this.starsDrawingSpace.right) {
            this.rating = this.numberOfStars;
            return;
        }
        this.rating = (this.numberOfStars / this.starsDrawingSpace.width()) * (f - this.starsDrawingSpace.left);
        float f3 = this.rating % this.stepSize;
        if (f3 < this.stepSize / 4.0f) {
            this.rating -= f3;
            this.rating = Math.max(0.0f, this.rating);
        } else {
            this.rating = (this.rating - f3) + this.stepSize;
            this.rating = Math.min(this.numberOfStars, this.rating);
        }
    }

    private void setupColorsInPaint() {
        if (this.touchInProgress) {
            this.paintStarBorder.setColor(this.pressedBorderColor);
            this.paintStarFill.setColor(this.pressedFillColor);
            if (this.pressedFillColor != 0) {
                this.paintStarFill.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
            } else {
                this.paintStarFill.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            }
            this.paintStarBackground.setColor(this.pressedStarBackgroundColor);
            if (this.pressedStarBackgroundColor != 0) {
                this.paintStarBackground.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
                return;
            } else {
                this.paintStarBackground.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                return;
            }
        }
        this.paintStarBorder.setColor(this.borderColor);
        this.paintStarFill.setColor(this.fillColor);
        if (this.fillColor != 0) {
            this.paintStarFill.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        } else {
            this.paintStarFill.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
        this.paintStarBackground.setColor(this.starBackgroundColor);
        if (this.starBackgroundColor != 0) {
            this.paintStarBackground.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        } else {
            this.paintStarBackground.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }
    }

    private void validateAttrs() {
        if (this.numberOfStars <= 0) {
            throw new IllegalArgumentException(String.format("SimpleRatingBar initialized with invalid value for numberOfStars. Found %d, but should be greater than 0", Integer.valueOf(this.numberOfStars)));
        }
        if (this.desiredStarSize != 2.14748365E9f && this.maxStarSize != 2.14748365E9f && this.desiredStarSize > this.maxStarSize) {
            Log.w("SimpleRatingBar", String.format("Initialized with conflicting values: starSize is greater than maxStarSize (%f > %f). I will ignore maxStarSize", Float.valueOf(this.desiredStarSize), Float.valueOf(this.maxStarSize)));
        }
        if (this.stepSize <= 0.0f) {
            throw new IllegalArgumentException(String.format("SimpleRatingBar initialized with invalid value for stepSize. Found %f, but should be greater than 0", Float.valueOf(this.stepSize)));
        }
        if (this.starBorderWidth <= 0.0f) {
            throw new IllegalArgumentException(String.format("SimpleRatingBar initialized with invalid value for starBorderWidth. Found %f, but should be greater than 0", Float.valueOf(this.starBorderWidth)));
        }
        if (this.starCornerRadius < 0.0f) {
            throw new IllegalArgumentException(String.format("SimpleRatingBar initialized with invalid value for starCornerRadius. Found %f, but should be greater or equal than 0", Float.valueOf(this.starBorderWidth)));
        }
    }

    private float valueFromPixels(float f, @Dimension int i) {
        return i != 0 ? i != 2 ? f : f / getResources().getDisplayMetrics().scaledDensity : f / getResources().getDisplayMetrics().density;
    }

    private float valueToPixels(float f, @Dimension int i) {
        return i != 0 ? i != 2 ? f : TypedValue.applyDimension(2, f, getResources().getDisplayMetrics()) : TypedValue.applyDimension(1, f, getResources().getDisplayMetrics());
    }

    public AnimationBuilder getAnimationBuilder() {
        return new AnimationBuilder(this);
    }

    @ColorInt
    public int getBorderColor() {
        return this.borderColor;
    }

    @ColorInt
    public int getFillColor() {
        return this.fillColor;
    }

    public Gravity getGravity() {
        return this.gravity;
    }

    public float getMaxStarSize() {
        return this.maxStarSize;
    }

    public float getMaxStarSize(@Dimension int i) {
        return valueFromPixels(this.maxStarSize, i);
    }

    public int getNumberOfStars() {
        return this.numberOfStars;
    }

    @ColorInt
    public int getPressedBorderColor() {
        return this.pressedBorderColor;
    }

    @ColorInt
    public int getPressedFillColor() {
        return this.pressedFillColor;
    }

    @ColorInt
    public int getPressedStarBackgroundColor() {
        return this.pressedStarBackgroundColor;
    }

    public float getRating() {
        return this.rating;
    }

    @ColorInt
    public int getStarBackgroundColor() {
        return this.starBackgroundColor;
    }

    public float getStarBorderWidth() {
        return this.starBorderWidth;
    }

    public float getStarBorderWidth(@Dimension int i) {
        return valueFromPixels(this.starBorderWidth, i);
    }

    public float getStarCornerRadius() {
        return this.starCornerRadius;
    }

    public float getStarCornerRadius(@Dimension int i) {
        return valueFromPixels(this.starCornerRadius, i);
    }

    public float getStarSize() {
        return this.currentStarSize;
    }

    public float getStarSize(@Dimension int i) {
        return valueFromPixels(this.currentStarSize, i);
    }

    public float getStarsSeparation() {
        return this.starsSeparation;
    }

    public float getStarsSeparation(@Dimension int i) {
        return valueFromPixels(this.starsSeparation, i);
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public boolean isDrawBorderEnabled() {
        return this.drawBorderEnabled;
    }

    public boolean isIndicator() {
        return this.isIndicator;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        if (getWidth() == 0 || height == 0) {
            return;
        }
        this.internalCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        setupColorsInPaint();
        if (this.gravity == Gravity.Left) {
            drawFromLeftToRight(this.internalCanvas);
        } else {
            drawFromRightToLeft(this.internalCanvas);
        }
        if (this.touchInProgress) {
            canvas.drawColor(this.pressedBackgroundColor);
        } else {
            canvas.drawColor(this.backgroundColor);
        }
        canvas.drawBitmap(this.internalBitmap, 0.0f, 0.0f, (Paint) null);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int width = getWidth();
        int height = getHeight();
        if (this.desiredStarSize == 2.14748365E9f) {
            this.currentStarSize = calculateBestStarSize(width, height);
        } else {
            this.currentStarSize = this.desiredStarSize;
        }
        performStarSizeAssociatedCalculations(width, height);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = mode == Integer.MIN_VALUE ? this.desiredStarSize != 2.14748365E9f ? Math.min(calculateTotalWidth(this.desiredStarSize, this.numberOfStars, this.starsSeparation, true), size) : this.maxStarSize != 2.14748365E9f ? Math.min(calculateTotalWidth(this.maxStarSize, this.numberOfStars, this.starsSeparation, true), size) : Math.min(calculateTotalWidth(this.defaultStarSize, this.numberOfStars, this.starsSeparation, true), size) : this.desiredStarSize != 2.14748365E9f ? calculateTotalWidth(this.desiredStarSize, this.numberOfStars, this.starsSeparation, true) : this.maxStarSize != 2.14748365E9f ? calculateTotalWidth(this.maxStarSize, this.numberOfStars, this.starsSeparation, true) : calculateTotalWidth(this.defaultStarSize, this.numberOfStars, this.starsSeparation, true);
        }
        float paddingLeft = (((size - getPaddingLeft()) - getPaddingRight()) - (this.starsSeparation * (this.numberOfStars - 1))) / this.numberOfStars;
        if (mode2 != 1073741824) {
            size2 = mode2 == Integer.MIN_VALUE ? this.desiredStarSize != 2.14748365E9f ? Math.min(calculateTotalHeight(this.desiredStarSize, this.numberOfStars, this.starsSeparation, true), size2) : this.maxStarSize != 2.14748365E9f ? Math.min(calculateTotalHeight(this.maxStarSize, this.numberOfStars, this.starsSeparation, true), size2) : Math.min(calculateTotalHeight(paddingLeft, this.numberOfStars, this.starsSeparation, true), size2) : this.desiredStarSize != 2.14748365E9f ? calculateTotalHeight(this.desiredStarSize, this.numberOfStars, this.starsSeparation, true) : this.maxStarSize != 2.14748365E9f ? calculateTotalHeight(this.maxStarSize, this.numberOfStars, this.starsSeparation, true) : calculateTotalHeight(paddingLeft, this.numberOfStars, this.starsSeparation, true);
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setRating(savedState.rating);
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.rating = getRating();
        return savedState;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        generateInternalCanvas(i, i2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0019. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.isIndicator
            r1 = 0
            if (r0 != 0) goto L73
            android.animation.ValueAnimator r0 = r5.ratingAnimator
            if (r0 == 0) goto L12
            android.animation.ValueAnimator r0 = r5.ratingAnimator
            boolean r0 = r0.isRunning()
            if (r0 == 0) goto L12
            goto L73
        L12:
            int r0 = r6.getAction()
            r0 = r0 & 255(0xff, float:3.57E-43)
            r2 = 1
            switch(r0) {
                case 0: goto L3f;
                case 1: goto L1d;
                case 2: goto L3f;
                case 3: goto L31;
                default: goto L1c;
            }
        L1c:
            goto L6f
        L1d:
            float r0 = r6.getX()
            float r6 = r6.getY()
            r5.setNewRatingFromTouch(r0, r6)
            android.view.View$OnClickListener r6 = r5.clickListener
            if (r6 == 0) goto L31
            android.view.View$OnClickListener r6 = r5.clickListener
            r6.onClick(r5)
        L31:
            com.iarcuschin.simpleratingbar.SimpleRatingBar$OnRatingBarChangeListener r6 = r5.ratingListener
            if (r6 == 0) goto L3c
            com.iarcuschin.simpleratingbar.SimpleRatingBar$OnRatingBarChangeListener r6 = r5.ratingListener
            float r0 = r5.rating
            r6.onRatingChanged(r5, r0, r2)
        L3c:
            r5.touchInProgress = r1
            goto L6f
        L3f:
            android.graphics.RectF r0 = r5.starsTouchSpace
            float r3 = r6.getX()
            float r4 = r6.getY()
            boolean r0 = r0.contains(r3, r4)
            if (r0 == 0) goto L5d
            r5.touchInProgress = r2
            float r0 = r6.getX()
            float r6 = r6.getY()
            r5.setNewRatingFromTouch(r0, r6)
            goto L6f
        L5d:
            boolean r6 = r5.touchInProgress
            if (r6 == 0) goto L6c
            com.iarcuschin.simpleratingbar.SimpleRatingBar$OnRatingBarChangeListener r6 = r5.ratingListener
            if (r6 == 0) goto L6c
            com.iarcuschin.simpleratingbar.SimpleRatingBar$OnRatingBarChangeListener r6 = r5.ratingListener
            float r0 = r5.rating
            r6.onRatingChanged(r5, r0, r2)
        L6c:
            r5.touchInProgress = r1
            return r1
        L6f:
            r5.invalidate()
            return r2
        L73:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.iarcuschin.simpleratingbar.SimpleRatingBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setBorderColor(@ColorInt int i) {
        this.borderColor = i;
        invalidate();
    }

    public void setDrawBorderEnabled(boolean z) {
        this.drawBorderEnabled = z;
        invalidate();
    }

    public void setFillColor(@ColorInt int i) {
        this.fillColor = i;
        invalidate();
    }

    public void setGravity(Gravity gravity) {
        this.gravity = gravity;
        invalidate();
    }

    public void setIndicator(boolean z) {
        this.isIndicator = z;
        this.touchInProgress = false;
    }

    public void setMaxStarSize(float f) {
        this.maxStarSize = f;
        if (this.currentStarSize > f) {
            requestLayout();
            generateInternalCanvas(getWidth(), getHeight());
            invalidate();
        }
    }

    public void setMaxStarSize(float f, @Dimension int i) {
        setMaxStarSize(valueToPixels(f, i));
    }

    public void setNumberOfStars(int i) {
        this.numberOfStars = i;
        if (i <= 0) {
            throw new IllegalArgumentException(String.format("SimpleRatingBar initialized with invalid value for numberOfStars. Found %d, but should be greater than 0", Integer.valueOf(i)));
        }
        this.rating = 0.0f;
        requestLayout();
        generateInternalCanvas(getWidth(), getHeight());
        invalidate();
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    public void setOnRatingBarChangeListener(OnRatingBarChangeListener onRatingBarChangeListener) {
        this.ratingListener = onRatingBarChangeListener;
    }

    public void setPressedBorderColor(@ColorInt int i) {
        this.pressedBorderColor = i;
        invalidate();
    }

    public void setPressedFillColor(@ColorInt int i) {
        this.pressedFillColor = i;
        invalidate();
    }

    public void setPressedStarBackgroundColor(@ColorInt int i) {
        this.pressedStarBackgroundColor = i;
        invalidate();
    }

    public void setRating(float f) {
        this.rating = normalizeRating(f);
        invalidate();
        if (this.ratingListener != null) {
            if (this.ratingAnimator == null || !this.ratingAnimator.isRunning()) {
                this.ratingListener.onRatingChanged(this, f, false);
            }
        }
    }

    public void setStarBackgroundColor(@ColorInt int i) {
        this.starBackgroundColor = i;
        invalidate();
    }

    public void setStarBorderWidth(float f) {
        this.starBorderWidth = f;
        if (f <= 0.0f) {
            throw new IllegalArgumentException(String.format("SimpleRatingBar initialized with invalid value for starBorderWidth. Found %f, but should be greater than 0", Float.valueOf(f)));
        }
        this.paintStarBorder.setStrokeWidth(f);
        invalidate();
    }

    public void setStarBorderWidth(float f, @Dimension int i) {
        setStarBorderWidth(valueToPixels(f, i));
    }

    public void setStarCornerRadius(float f) {
        this.starCornerRadius = f;
        if (f < 0.0f) {
            throw new IllegalArgumentException(String.format("SimpleRatingBar initialized with invalid value for starCornerRadius. Found %f, but should be greater or equal than 0", Float.valueOf(f)));
        }
        this.cornerPathEffect = new CornerPathEffect(f);
        this.paintStarBorder.setPathEffect(this.cornerPathEffect);
        this.paintStarOutline.setPathEffect(this.cornerPathEffect);
        invalidate();
    }

    public void setStarCornerRadius(float f, @Dimension int i) {
        setStarCornerRadius(valueToPixels(f, i));
    }

    public void setStarSize(float f) {
        this.desiredStarSize = f;
        if (f != 2.14748365E9f && this.maxStarSize != 2.14748365E9f && f > this.maxStarSize) {
            Log.w("SimpleRatingBar", String.format("Initialized with conflicting values: starSize is greater than maxStarSize (%f > %f). I will ignore maxStarSize", Float.valueOf(f), Float.valueOf(this.maxStarSize)));
        }
        requestLayout();
        generateInternalCanvas(getWidth(), getHeight());
        invalidate();
    }

    public void setStarSize(float f, @Dimension int i) {
        setStarSize(valueToPixels(f, i));
    }

    public void setStarsSeparation(float f) {
        this.starsSeparation = f;
        requestLayout();
        generateInternalCanvas(getWidth(), getHeight());
        invalidate();
    }

    public void setStarsSeparation(float f, @Dimension int i) {
        setStarsSeparation(valueToPixels(f, i));
    }

    public void setStepSize(float f) {
        this.stepSize = f;
        if (f <= 0.0f) {
            throw new IllegalArgumentException(String.format("SimpleRatingBar initialized with invalid value for stepSize. Found %f, but should be greater than 0", Float.valueOf(f)));
        }
        invalidate();
    }
}
