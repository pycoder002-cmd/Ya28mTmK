package com.afollestad.materialdialogs.color;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.FloatRange;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.afollestad.materialdialogs.util.DialogUtils;

/* loaded from: classes.dex */
public class CircleView extends FrameLayout {
    private final int borderWidthLarge;
    private final int borderWidthSmall;
    private final Paint innerPaint;
    private final Paint outerPaint;
    private boolean selected;
    private final Paint whitePaint;

    public CircleView(Context context) {
        this(context, null, 0);
    }

    public CircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = getResources();
        this.borderWidthSmall = (int) TypedValue.applyDimension(1, 3.0f, resources.getDisplayMetrics());
        this.borderWidthLarge = (int) TypedValue.applyDimension(1, 5.0f, resources.getDisplayMetrics());
        this.whitePaint = new Paint();
        this.whitePaint.setAntiAlias(true);
        this.whitePaint.setColor(-1);
        this.innerPaint = new Paint();
        this.innerPaint.setAntiAlias(true);
        this.outerPaint = new Paint();
        this.outerPaint.setAntiAlias(true);
        update(-12303292);
        setWillNotDraw(false);
    }

    private Drawable createSelector(int i) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(translucentColor(shiftColorUp(i)));
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, shapeDrawable);
        return stateListDrawable;
    }

    @ColorInt
    public static int shiftColor(@ColorInt int i, @FloatRange(from = 0.0d, to = 2.0d) float f) {
        if (f == 1.0f) {
            return i;
        }
        Color.colorToHSV(i, r0);
        float[] fArr = {0.0f, 0.0f, fArr[2] * f};
        return Color.HSVToColor(fArr);
    }

    @ColorInt
    public static int shiftColorDown(@ColorInt int i) {
        return shiftColor(i, 0.9f);
    }

    @ColorInt
    public static int shiftColorUp(@ColorInt int i) {
        return shiftColor(i, 1.1f);
    }

    @ColorInt
    private static int translucentColor(int i) {
        return Color.argb(Math.round(Color.alpha(i) * 0.7f), Color.red(i), Color.green(i), Color.blue(i));
    }

    private void update(@ColorInt int i) {
        this.innerPaint.setColor(i);
        this.outerPaint.setColor(shiftColorDown(i));
        Drawable createSelector = createSelector(i);
        if (Build.VERSION.SDK_INT >= 21) {
            setForeground(new RippleDrawable(new ColorStateList(new int[][]{new int[]{R.attr.state_pressed}}, new int[]{shiftColorUp(i)}), createSelector, null));
        } else {
            setForeground(createSelector);
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth() / 2;
        if (!this.selected) {
            canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, measuredWidth, this.innerPaint);
            return;
        }
        int i = measuredWidth - this.borderWidthLarge;
        int i2 = i - this.borderWidthSmall;
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, measuredWidth, this.outerPaint);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, i, this.whitePaint);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, i2, this.innerPaint);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    @Override // android.view.View
    @Deprecated
    public void setActivated(boolean z) {
        throw new IllegalStateException("Cannot use setActivated() on CircleView.");
    }

    @Override // android.view.View
    @Deprecated
    public void setBackground(Drawable drawable) {
        throw new IllegalStateException("Cannot use setBackground() on CircleView.");
    }

    @Override // android.view.View
    public void setBackgroundColor(@ColorInt int i) {
        update(i);
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        throw new IllegalStateException("Cannot use setBackgroundDrawable() on CircleView.");
    }

    @Override // android.view.View
    public void setBackgroundResource(@ColorRes int i) {
        setBackgroundColor(DialogUtils.getColor(getContext(), i));
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        this.selected = z;
        requestLayout();
        invalidate();
    }

    public void showHint(int i) {
        int[] iArr = new int[2];
        Rect rect = new Rect();
        getLocationOnScreen(iArr);
        getWindowVisibleDisplayFrame(rect);
        Context context = getContext();
        int width = getWidth();
        int height = getHeight();
        int i2 = iArr[1] + (height / 2);
        int i3 = iArr[0] + (width / 2);
        if (ViewCompat.getLayoutDirection(this) == 0) {
            i3 = context.getResources().getDisplayMetrics().widthPixels - i3;
        }
        Toast makeText = Toast.makeText(context, String.format("#%06X", Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)), 0);
        if (i2 < rect.height()) {
            makeText.setGravity(8388661, i3, (iArr[1] + height) - rect.top);
        } else {
            makeText.setGravity(81, 0, height);
        }
        makeText.show();
    }
}
