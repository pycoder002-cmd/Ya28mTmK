package cu.uci.android.apklis.presentation.ui.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import cu.uci.manolo.android.kiosko.R;

/* loaded from: classes.dex */
public class LabelLayout extends FrameLayout {
    private ImageView ic_apklis;
    private Drawable mLabelBackground;
    private Drawable mLabelBackgroundRight;
    private int mLabelDistance;
    private Gravity mLabelGravity;
    private int mLabelHeight;
    private String mLabelText;
    private int mLabelTextColor;
    private TextDirection mLabelTextDirection;
    private int mLabelTextSize;
    private final Paint mTextPaint;

    /* loaded from: classes.dex */
    public enum Gravity {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_RIGHT,
        BOTTOM_LEFT
    }

    /* loaded from: classes.dex */
    public enum TextDirection {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT
    }

    public LabelLayout(Context context) {
        this(context, null);
    }

    public LabelLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LabelLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setWillNotDraw(false);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LabelLayout);
        this.mLabelDistance = obtainStyledAttributes.getDimensionPixelSize(1, 0);
        this.mLabelHeight = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        this.mLabelBackground = obtainStyledAttributes.getDrawable(0);
        this.mLabelGravity = Gravity.values()[obtainStyledAttributes.getInt(2, Gravity.BOTTOM_LEFT.ordinal())];
        this.mLabelText = obtainStyledAttributes.getString(4);
        this.mLabelTextSize = obtainStyledAttributes.getDimensionPixelSize(7, (int) new Paint().getTextSize());
        this.mLabelTextColor = obtainStyledAttributes.getColor(5, ViewCompat.MEASURED_STATE_MASK);
        this.mLabelTextDirection = TextDirection.values()[obtainStyledAttributes.getInt(6, TextDirection.LEFT_TO_RIGHT.ordinal())];
        obtainStyledAttributes.recycle();
        this.mTextPaint = new Paint();
        this.mTextPaint.setDither(true);
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mTextPaint.setStrokeCap(Paint.Cap.SQUARE);
    }

    private Rect calculateBackgroundBounds(Drawable drawable, Rect rect) {
        int height;
        if (drawable instanceof ColorDrawable) {
            return new Rect(rect);
        }
        Rect rect2 = new Rect();
        if (drawable.getIntrinsicWidth() > rect.width() || drawable.getIntrinsicHeight() > rect.height()) {
            int intrinsicWidth = drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight();
            int i = 0;
            if (rect.height() <= 0) {
                i = rect.height() * intrinsicWidth;
                height = rect.height();
            } else if (drawable.getIntrinsicWidth() / drawable.getIntrinsicHeight() >= rect.width() / rect.height()) {
                i = rect.width();
                height = rect.width() / intrinsicWidth;
            } else {
                height = 0;
            }
            int i2 = i / 2;
            rect2.left = rect.centerX() - i2;
            int i3 = height / 2;
            rect2.top = rect.centerY() - i3;
            rect2.right = rect.centerX() + i2;
            rect2.bottom = rect.centerY() + i3;
        } else {
            rect2.left = rect.centerX() - (drawable.getIntrinsicWidth() / 2);
            rect2.top = rect.centerY() - (drawable.getIntrinsicHeight() / 2);
            rect2.right = rect.centerX() + (drawable.getIntrinsicWidth() / 2);
            rect2.bottom = rect.centerY() + (drawable.getIntrinsicHeight() / 2);
        }
        return rect2;
    }

    private int[] calculateBisectorCoordinates(int i, int i2, Gravity gravity) {
        int measuredWidth;
        int measuredWidth2;
        int i3;
        int calculateBisectorIntersectAbsolutePosition = calculateBisectorIntersectAbsolutePosition(i, i2);
        int[] iArr = new int[4];
        switch (gravity) {
            case TOP_RIGHT:
                measuredWidth = getMeasuredWidth() - calculateBisectorIntersectAbsolutePosition;
                measuredWidth2 = getMeasuredWidth();
                i3 = calculateBisectorIntersectAbsolutePosition;
                calculateBisectorIntersectAbsolutePosition = 0;
                break;
            case BOTTOM_RIGHT:
                measuredWidth = getMeasuredWidth() - calculateBisectorIntersectAbsolutePosition;
                int measuredHeight = getMeasuredHeight();
                int measuredWidth3 = getMeasuredWidth();
                i3 = getMeasuredHeight() - calculateBisectorIntersectAbsolutePosition;
                calculateBisectorIntersectAbsolutePosition = measuredHeight;
                measuredWidth2 = measuredWidth3;
                break;
            case BOTTOM_LEFT:
                int measuredHeight2 = getMeasuredHeight() - calculateBisectorIntersectAbsolutePosition;
                i3 = getMeasuredHeight();
                measuredWidth2 = calculateBisectorIntersectAbsolutePosition;
                calculateBisectorIntersectAbsolutePosition = measuredHeight2;
                measuredWidth = 0;
                break;
            default:
                measuredWidth2 = calculateBisectorIntersectAbsolutePosition;
                measuredWidth = 0;
                i3 = 0;
                break;
        }
        iArr[0] = measuredWidth;
        iArr[1] = calculateBisectorIntersectAbsolutePosition;
        iArr[2] = measuredWidth2;
        iArr[3] = i3;
        return iArr;
    }

    private int calculateBisectorIntersectAbsolutePosition(int i, int i2) {
        return (int) (Math.sqrt(2.0d) * (i + (i2 / 2)));
    }

    private int calculateCenterAbsolutePosition(int i, int i2) {
        return (int) ((i + (i2 / 2)) / Math.sqrt(2.0d));
    }

    private int[] calculateCenterCoordinate(int i, int i2, Gravity gravity) {
        int measuredWidth;
        int i3;
        int[] iArr = new int[2];
        int calculateCenterAbsolutePosition = calculateCenterAbsolutePosition(i, i2);
        switch (gravity) {
            case TOP_RIGHT:
                measuredWidth = getMeasuredWidth() - calculateCenterAbsolutePosition;
                int i4 = measuredWidth;
                i3 = calculateCenterAbsolutePosition;
                calculateCenterAbsolutePosition = i4;
                break;
            case BOTTOM_RIGHT:
                measuredWidth = getMeasuredWidth() - calculateCenterAbsolutePosition;
                calculateCenterAbsolutePosition = getMeasuredHeight() - calculateCenterAbsolutePosition;
                int i42 = measuredWidth;
                i3 = calculateCenterAbsolutePosition;
                calculateCenterAbsolutePosition = i42;
                break;
            case BOTTOM_LEFT:
                i3 = getMeasuredHeight() - calculateCenterAbsolutePosition;
                break;
            default:
                i3 = calculateCenterAbsolutePosition;
                break;
        }
        iArr[0] = calculateCenterAbsolutePosition;
        iArr[1] = i3;
        return iArr;
    }

    private float calculateRotateDegree(Gravity gravity) {
        int i = AnonymousClass1.$SwitchMap$cu$uci$android$apklis$presentation$ui$util$LabelLayout$Gravity[gravity.ordinal()];
        return (i == 1 || i == 3) ? 0.0f : -45.0f;
    }

    private float[] calculateTextOffsets(String str, Paint paint, int i, int i2, Gravity gravity) {
        float[] fArr = new float[2];
        paint.getTextBounds(str, 0, str.length(), new Rect());
        float calculateBisectorIntersectAbsolutePosition = (float) ((calculateBisectorIntersectAbsolutePosition(i, i2) / Math.sqrt(2.0d)) - (r2.width() / 2.0d));
        float height = i >= i2 ? r2.height() * 0.5f : (gravity.equals(Gravity.TOP_LEFT) || gravity.equals(Gravity.TOP_RIGHT)) ? r2.height() * (0.5f + (((i2 - i) / i2) * 0.5f)) : r2.height() * (0.5f - (((i2 - i) / i2) * 0.5f));
        Log.d(LabelLayout.class.getSimpleName(), String.format("%d, %d, %f", Integer.valueOf(i), Integer.valueOf(i2), Float.valueOf(height)));
        fArr[0] = calculateBisectorIntersectAbsolutePosition;
        fArr[1] = height;
        return fArr;
    }

    private int calculateWidth(int i, int i2) {
        return 2 * (i + i2);
    }

    private void drawSelf(Canvas canvas) {
        this.mTextPaint.setTextSize(this.mLabelTextSize);
        this.mTextPaint.setColor(this.mLabelTextColor);
        String stringBuffer = this.mLabelTextDirection == TextDirection.LEFT_TO_RIGHT ? this.mLabelText : new StringBuffer(this.mLabelText).reverse().toString();
        Rect rect = new Rect();
        this.mTextPaint.getTextBounds(stringBuffer, 0, stringBuffer.length(), rect);
        int height = rect.height();
        if (this.mLabelHeight != 0) {
            this.mLabelHeight = ((int) (height * 0.31d)) + height;
        }
        int[] calculateCenterCoordinate = calculateCenterCoordinate(this.mLabelDistance, this.mLabelHeight, this.mLabelGravity);
        int calculateWidth = calculateWidth(this.mLabelDistance, this.mLabelHeight) / 2;
        int i = this.mLabelHeight / 2;
        this.mLabelBackground.setBounds(calculateBackgroundBounds(this.mLabelBackground, new Rect(calculateCenterCoordinate[0] - calculateWidth, calculateCenterCoordinate[1] - i, calculateCenterCoordinate[0] + calculateWidth, calculateCenterCoordinate[1] + i)));
        canvas.save();
        canvas.rotate(calculateRotateDegree(this.mLabelGravity), calculateCenterCoordinate[0], calculateCenterCoordinate[1]);
        this.mLabelBackground.draw(canvas);
        canvas.restore();
        Path path = new Path();
        int[] calculateBisectorCoordinates = calculateBisectorCoordinates(this.mLabelDistance, this.mLabelHeight, this.mLabelGravity);
        path.moveTo(calculateBisectorCoordinates[0], calculateBisectorCoordinates[1]);
        path.lineTo(calculateBisectorCoordinates[2], calculateBisectorCoordinates[3]);
        float[] calculateTextOffsets = calculateTextOffsets(this.mLabelText, this.mTextPaint, this.mLabelDistance, this.mLabelHeight, this.mLabelGravity);
        float f = (int) (height * 0.18d);
        canvas.drawTextOnPath(stringBuffer, path, calculateTextOffsets[0] - f, calculateTextOffsets[1] - f, this.mTextPaint);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawSelf(canvas);
    }

    public Drawable getLabelBackground() {
        return this.mLabelBackground;
    }

    public int getLabelDistance() {
        return this.mLabelDistance;
    }

    public Gravity getLabelGravity() {
        return this.mLabelGravity;
    }

    public int getLabelHeight() {
        return this.mLabelHeight;
    }

    public String getLabelText() {
        return this.mLabelText;
    }

    public int getLabelTextColor() {
        return this.mLabelTextColor;
    }

    public TextDirection getLabelTextDirection() {
        return this.mLabelTextDirection;
    }

    public int getLabelTextSize() {
        return this.mLabelTextSize;
    }

    public Drawable getmLabelBackgroundRight() {
        return this.mLabelBackgroundRight;
    }

    @Override // android.view.View
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        drawSelf(canvas);
    }

    public void setLabelBackground(Drawable drawable) {
        this.mLabelBackground = drawable;
        invalidate();
    }

    public void setLabelDistance(int i) {
        this.mLabelDistance = i;
        invalidate();
    }

    public void setLabelGravity(Gravity gravity) {
        this.mLabelGravity = gravity;
        invalidate();
    }

    public void setLabelHeight(int i) {
        this.mLabelHeight = i;
        invalidate();
    }

    public void setLabelText(String str) {
        this.mLabelText = str;
        invalidate();
    }

    public void setLabelTextColor(int i) {
        this.mLabelTextColor = i;
        invalidate();
    }

    public void setLabelTextDirection(TextDirection textDirection) {
        this.mLabelTextDirection = textDirection;
        invalidate();
    }

    public void setLabelTextSize(int i) {
        this.mLabelTextSize = i;
        invalidate();
    }

    public void setmLabelBackgroundRight(Drawable drawable) {
        this.mLabelBackgroundRight = drawable;
        invalidate();
    }

    public void showIconApklis(int i) {
        this.ic_apklis.setVisibility(i);
    }
}
