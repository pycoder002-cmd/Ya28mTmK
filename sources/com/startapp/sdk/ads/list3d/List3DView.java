package com.startapp.sdk.ads.list3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import com.blankj.utilcode.constant.MemoryConstants;
import com.startapp.b3;
import com.startapp.c3;
import com.startapp.y2;
import com.startapp.ya;
import java.util.LinkedList;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class List3DView extends AdapterView<Adapter> {
    public Adapter a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public VelocityTracker k;
    public y2 l;
    public Runnable m;
    public final LinkedList<View> n;
    public Runnable o;
    public Rect p;
    public Camera q;
    public Matrix r;
    public Paint s;
    public int t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;

    public List3DView(Context context, AttributeSet attributeSet, String str, String str2) {
        super(context, null);
        this.b = 0;
        this.n = new LinkedList<>();
        this.t = Integer.MIN_VALUE;
        this.u = false;
        this.v = false;
        this.w = false;
        this.x = false;
    }

    public final int a(View view) {
        return view.getBottom() + c(view);
    }

    public final void a(float f) {
        VelocityTracker velocityTracker = this.k;
        if (velocityTracker == null) {
            return;
        }
        if (velocityTracker != null) {
            velocityTracker.recycle();
        }
        this.k = null;
        removeCallbacks(this.o);
        if (this.m == null) {
            this.m = new c3(this);
        }
        y2 y2Var = this.l;
        if (y2Var != null) {
            float f2 = this.f;
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            y2Var.b = f;
            y2Var.a = f2;
            y2Var.e = currentAnimationTimeMillis;
            post(this.m);
        }
        this.b = 0;
    }

    public void a(int i) {
        int i2 = this.e + i;
        this.f = i2;
        int height = (-(i2 * 270)) / getHeight();
        this.h = height;
        int i3 = height % 90;
        int height2 = i3 < 45 ? ((-(height - i3)) * getHeight()) / 270 : ((-((height + 90) - i3)) * getHeight()) / 270;
        if (this.t == Integer.MIN_VALUE && this.j == this.a.getCount() - 1 && a(getChildAt(getChildCount() - 1)) < getHeight()) {
            this.t = height2;
        }
        if (height2 > 0) {
            height2 = 0;
        } else {
            int i4 = this.t;
            if (height2 < i4) {
                height2 = i4;
            }
        }
        y2 y2Var = this.l;
        float f = height2;
        y2Var.c = f;
        y2Var.d = f;
        requestLayout();
    }

    public final void a(int i, int i2) {
        while (i + i2 < getHeight() && this.j < this.a.getCount() - 1) {
            int i3 = this.j + 1;
            this.j = i3;
            View view = this.a.getView(i3, this.n.size() != 0 ? this.n.removeFirst() : null, this);
            a(view, 0);
            i += b(view);
        }
    }

    public final void a(Canvas canvas, Bitmap bitmap, int i, int i2, int i3, int i4, float f, float f2) {
        if (this.q == null) {
            this.q = new Camera();
        }
        this.q.save();
        this.q.translate(0.0f, 0.0f, i4);
        this.q.rotateX(f2);
        float f3 = -i4;
        this.q.translate(0.0f, 0.0f, f3);
        if (this.r == null) {
            this.r = new Matrix();
        }
        this.q.getMatrix(this.r);
        this.q.restore();
        this.r.preTranslate(-i3, f3);
        this.r.postScale(f, f);
        this.r.postTranslate(i2 + i3, i + i4);
        if (this.s == null) {
            Paint paint = new Paint();
            this.s = paint;
            paint.setAntiAlias(true);
            this.s.setFilterBitmap(true);
        }
        Paint paint2 = this.s;
        double cos = Math.cos((f2 * 3.141592653589793d) / 180.0d);
        int i5 = ((int) (cos * 200.0d)) + 55;
        int pow = (int) (Math.pow(cos, 200.0d) * 70.0d);
        if (i5 > 255) {
            i5 = 255;
        }
        if (pow > 255) {
            pow = 255;
        }
        paint2.setColorFilter(new LightingColorFilter(Color.rgb(i5, i5, i5), Color.rgb(pow, pow, pow)));
        canvas.drawBitmap(bitmap, this.r, this.s);
    }

    public final void a(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        }
        int i2 = i == 1 ? 0 : -1;
        view.setDrawingCacheEnabled(true);
        addViewInLayout(view, i2, layoutParams, true);
        view.measure(((int) (getWidth() * 0.85f)) | MemoryConstants.GB, 0);
    }

    public final boolean a() {
        int i = ya.a;
        return Build.VERSION.SDK_INT >= 12;
    }

    public int b(int i, int i2) {
        if (this.p == null) {
            this.p = new Rect();
        }
        for (int i3 = 0; i3 < getChildCount(); i3++) {
            getChildAt(i3).getHitRect(this.p);
            if (this.p.contains(i, i2)) {
                return i3;
            }
        }
        return -1;
    }

    public final int b(View view) {
        return view.getMeasuredHeight() + (c(view) * 2);
    }

    public final int c(View view) {
        return (int) ((view.getMeasuredHeight() * 0.35000002f) / 2.0f);
    }

    public int d(View view) {
        return view.getTop() - c(view);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j) {
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            return super.drawChild(canvas, view, j);
        }
        int top = view.getTop();
        int left = view.getLeft();
        int width = view.getWidth() / 2;
        int height = view.getHeight() / 2;
        float height2 = getHeight() / 2;
        float f = ((top + height) - height2) / height2;
        float cos = (float) (1.0d - ((1.0d - Math.cos(f)) * 0.15000000596046448d));
        float f2 = (this.h - (f * 20.0f)) % 90.0f;
        if (f2 < 0.0f) {
            f2 += 90.0f;
        }
        float f3 = f2;
        if (f3 < 45.0f) {
            a(canvas, drawingCache, top, left, width, height, cos, f3 - 90.0f);
            a(canvas, drawingCache, top, left, width, height, cos, f3);
            return false;
        }
        a(canvas, drawingCache, top, left, width, height, cos, f3);
        a(canvas, drawingCache, top, left, width, height, cos, f3 - 90.0f);
        return false;
    }

    @Override // android.widget.AdapterView
    public Adapter getAdapter() {
        return this.a;
    }

    @Override // android.widget.AdapterView
    public View getSelectedView() {
        return null;
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.m);
    }

    @Override // android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5;
        super.onLayout(z, i, i2, i3, i4);
        if (!this.u || this.a == null) {
            return;
        }
        if (getChildCount() == 0) {
            if (this.w) {
                this.f = getHeight() / 3;
            }
            this.j = -1;
            a(this.f, 0);
        } else {
            int d = (this.f + this.g) - d(getChildAt(0));
            int childCount = getChildCount();
            if (this.j != this.a.getCount() - 1 && childCount > 1) {
                View childAt = getChildAt(0);
                while (childAt != null && a(childAt) + d < 0) {
                    removeViewInLayout(childAt);
                    childCount--;
                    this.n.addLast(childAt);
                    this.i++;
                    this.g += b(childAt);
                    childAt = childCount > 1 ? getChildAt(0) : null;
                }
            }
            if (this.i != 0 && childCount > 1) {
                View childAt2 = getChildAt(childCount - 1);
                while (childAt2 != null && d(childAt2) + d > getHeight()) {
                    removeViewInLayout(childAt2);
                    childCount--;
                    this.n.addLast(childAt2);
                    this.j--;
                    childAt2 = childCount > 1 ? getChildAt(childCount - 1) : null;
                }
            }
            a(a(getChildAt(getChildCount() - 1)), d);
            int d2 = d(getChildAt(0));
            while (d2 + d > 0 && (i5 = this.i) > 0) {
                int i6 = i5 - 1;
                this.i = i6;
                View view = this.a.getView(i6, this.n.size() != 0 ? this.n.removeFirst() : null, this);
                a(view, 1);
                int b = b(view);
                d2 -= b;
                this.g -= b;
            }
        }
        int i7 = this.f + this.g;
        float width = getWidth() * 0.0f;
        float height = 1.0f / (getHeight() * 0.9f);
        for (int i8 = 0; i8 < getChildCount(); i8++) {
            View childAt3 = getChildAt(i8);
            int sin = (int) (width * Math.sin(height * 6.283185307179586d * i7));
            int measuredWidth = childAt3.getMeasuredWidth();
            int measuredHeight = childAt3.getMeasuredHeight();
            int width2 = sin + ((getWidth() - measuredWidth) / 2);
            int c = c(childAt3);
            int i9 = i7 + c;
            childAt3.layout(width2, i9, measuredWidth + width2, i9 + measuredHeight);
            i7 += measuredHeight + (c * 2);
        }
        if (this.w && !this.x) {
            this.x = true;
            dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 0, 0.0f, 0.0f, 0));
            postDelayed(new b3(this), 5L);
        }
        invalidate();
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x003a, code lost:
    
        if (r1 <= (r0 + 10)) goto L22;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.sdk.ads.list3d.List3DView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.AdapterView
    public void setAdapter(Adapter adapter) {
        if (a() && this.v) {
            int i = ya.a;
            if (Build.VERSION.SDK_INT >= 11) {
                setAlpha(0.0f);
            }
        }
        this.a = adapter;
        removeAllViewsInLayout();
        requestLayout();
    }

    public void setDynamics(y2 y2Var) {
        y2 y2Var2 = this.l;
        if (y2Var2 != null) {
            float f = y2Var2.a;
            float f2 = y2Var2.b;
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            y2Var.b = f2;
            y2Var.a = f;
            y2Var.e = currentAnimationTimeMillis;
        }
        this.l = y2Var;
    }

    public void setFade(boolean z) {
        this.v = z;
    }

    public void setHint(boolean z) {
        this.w = z;
    }

    @Override // android.widget.AdapterView
    public void setSelection(int i) {
        throw new UnsupportedOperationException("Not supported");
    }

    public void setStarted() {
        this.u = true;
    }

    public void setTag(String str) {
    }
}
