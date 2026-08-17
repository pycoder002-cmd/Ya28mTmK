package com.stephentuso.welcome;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/* loaded from: classes.dex */
class ColorChangingBackgroundView extends View {
    private BackgroundColor[] mColors;
    private int mCurrentPosition;
    private float mOffset;
    private Paint mPaint;
    private Rect mRect;

    public ColorChangingBackgroundView(Context context) {
        super(context);
        this.mColors = new BackgroundColor[0];
        this.mCurrentPosition = 0;
        this.mOffset = 0.0f;
        this.mPaint = null;
        this.mRect = new Rect();
    }

    public ColorChangingBackgroundView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mColors = new BackgroundColor[0];
        this.mCurrentPosition = 0;
        this.mOffset = 0.0f;
        this.mPaint = null;
        this.mRect = new Rect();
    }

    public ColorChangingBackgroundView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mColors = new BackgroundColor[0];
        this.mCurrentPosition = 0;
        this.mOffset = 0.0f;
        this.mPaint = null;
        this.mRect = new Rect();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mPaint == null) {
            this.mPaint = new Paint();
        }
        if (this.mColors == null || this.mColors.length == 0) {
            return;
        }
        getDrawingRect(this.mRect);
        this.mPaint.setColor(this.mColors[this.mCurrentPosition].value());
        this.mPaint.setAlpha(255);
        canvas.drawRect(this.mRect, this.mPaint);
        if (this.mCurrentPosition != this.mColors.length - 1) {
            this.mPaint.setColor(this.mColors[this.mCurrentPosition + 1].value());
            this.mPaint.setAlpha((int) (this.mOffset * 255.0f));
            canvas.drawRect(this.mRect, this.mPaint);
        }
    }

    public void setColors(BackgroundColor[] backgroundColorArr) {
        this.mColors = backgroundColorArr;
    }

    public void setPosition(int i, float f) {
        this.mCurrentPosition = i;
        this.mOffset = f;
        invalidate();
    }
}
