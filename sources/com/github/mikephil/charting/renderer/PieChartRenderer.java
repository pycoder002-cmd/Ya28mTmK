package com.github.mikephil.charting.renderer;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public class PieChartRenderer extends DataRenderer {
    protected Canvas mBitmapCanvas;
    private RectF mCenterTextLastBounds;
    private CharSequence mCenterTextLastValue;
    private StaticLayout mCenterTextLayout;
    private TextPaint mCenterTextPaint;
    protected PieChart mChart;
    protected WeakReference<Bitmap> mDrawBitmap;
    protected Path mDrawCenterTextPathBuffer;
    protected RectF mDrawHighlightedRectF;
    private Paint mEntryLabelsPaint;
    private Path mHoleCirclePath;
    protected Paint mHolePaint;
    private RectF mInnerRectBuffer;
    private Path mPathBuffer;
    private RectF[] mRectBuffer;
    protected Paint mTransparentCirclePaint;
    protected Paint mValueLinePaint;

    public PieChartRenderer(PieChart pieChart, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mCenterTextLastBounds = new RectF();
        this.mRectBuffer = new RectF[]{new RectF(), new RectF(), new RectF()};
        this.mPathBuffer = new Path();
        this.mInnerRectBuffer = new RectF();
        this.mHoleCirclePath = new Path();
        this.mDrawCenterTextPathBuffer = new Path();
        this.mDrawHighlightedRectF = new RectF();
        this.mChart = pieChart;
        this.mHolePaint = new Paint(1);
        this.mHolePaint.setColor(-1);
        this.mHolePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint = new Paint(1);
        this.mTransparentCirclePaint.setColor(-1);
        this.mTransparentCirclePaint.setStyle(Paint.Style.FILL);
        this.mTransparentCirclePaint.setAlpha(105);
        this.mCenterTextPaint = new TextPaint(1);
        this.mCenterTextPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mCenterTextPaint.setTextSize(Utils.convertDpToPixel(12.0f));
        this.mValuePaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValuePaint.setColor(-1);
        this.mValuePaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint = new Paint(1);
        this.mEntryLabelsPaint.setColor(-1);
        this.mEntryLabelsPaint.setTextAlign(Paint.Align.CENTER);
        this.mEntryLabelsPaint.setTextSize(Utils.convertDpToPixel(13.0f));
        this.mValueLinePaint = new Paint(1);
        this.mValueLinePaint.setStyle(Paint.Style.STROKE);
    }

    protected float calculateMinimumRadiusForSpacedSlice(MPPointF mPPointF, float f, float f2, float f3, float f4, float f5, float f6) {
        double d = (f5 + f6) * 0.017453292f;
        float cos = mPPointF.x + (((float) Math.cos(d)) * f);
        float sin = mPPointF.y + (((float) Math.sin(d)) * f);
        double d2 = (f5 + (f6 / 2.0f)) * 0.017453292f;
        return (float) ((f - ((float) ((Math.sqrt(Math.pow(cos - f3, 2.0d) + Math.pow(sin - f4, 2.0d)) / 2.0d) * Math.tan(0.017453292519943295d * ((180.0d - f2) / 2.0d))))) - Math.sqrt(Math.pow((mPPointF.x + (((float) Math.cos(d2)) * f)) - ((cos + f3) / 2.0f), 2.0d) + Math.pow((mPPointF.y + (((float) Math.sin(d2)) * f)) - ((sin + f4) / 2.0f), 2.0d)));
    }

    protected void drawCenterText(Canvas canvas) {
        MPPointF mPPointF;
        CharSequence centerText = this.mChart.getCenterText();
        if (!this.mChart.isDrawCenterTextEnabled() || centerText == null) {
            return;
        }
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        MPPointF centerTextOffset = this.mChart.getCenterTextOffset();
        float f = centerCircleBox.x + centerTextOffset.x;
        float f2 = centerCircleBox.y + centerTextOffset.y;
        float radius = (!this.mChart.isDrawHoleEnabled() || this.mChart.isDrawSlicesUnderHoleEnabled()) ? this.mChart.getRadius() : this.mChart.getRadius() * (this.mChart.getHoleRadius() / 100.0f);
        RectF rectF = this.mRectBuffer[0];
        rectF.left = f - radius;
        rectF.top = f2 - radius;
        rectF.right = f + radius;
        rectF.bottom = f2 + radius;
        RectF rectF2 = this.mRectBuffer[1];
        rectF2.set(rectF);
        float centerTextRadiusPercent = this.mChart.getCenterTextRadiusPercent() / 100.0f;
        if (centerTextRadiusPercent > Utils.DOUBLE_EPSILON) {
            rectF2.inset((rectF2.width() - (rectF2.width() * centerTextRadiusPercent)) / 2.0f, (rectF2.height() - (rectF2.height() * centerTextRadiusPercent)) / 2.0f);
        }
        if (centerText.equals(this.mCenterTextLastValue) && rectF2.equals(this.mCenterTextLastBounds)) {
            mPPointF = centerTextOffset;
        } else {
            this.mCenterTextLastBounds.set(rectF2);
            this.mCenterTextLastValue = centerText;
            mPPointF = centerTextOffset;
            this.mCenterTextLayout = new StaticLayout(centerText, 0, centerText.length(), this.mCenterTextPaint, (int) Math.max(Math.ceil(this.mCenterTextLastBounds.width()), 1.0d), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        }
        float height = this.mCenterTextLayout.getHeight();
        canvas.save();
        if (Build.VERSION.SDK_INT >= 18) {
            Path path = this.mDrawCenterTextPathBuffer;
            path.reset();
            path.addOval(rectF, Path.Direction.CW);
            canvas.clipPath(path);
        }
        canvas.translate(rectF2.left, rectF2.top + ((rectF2.height() - height) / 2.0f));
        this.mCenterTextLayout.draw(canvas);
        canvas.restore();
        MPPointF.recycleInstance(centerCircleBox);
        MPPointF.recycleInstance(mPPointF);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        int chartWidth = (int) this.mViewPortHandler.getChartWidth();
        int chartHeight = (int) this.mViewPortHandler.getChartHeight();
        if (this.mDrawBitmap == null || this.mDrawBitmap.get().getWidth() != chartWidth || this.mDrawBitmap.get().getHeight() != chartHeight) {
            if (chartWidth <= 0 || chartHeight <= 0) {
                return;
            }
            this.mDrawBitmap = new WeakReference<>(Bitmap.createBitmap(chartWidth, chartHeight, Bitmap.Config.ARGB_4444));
            this.mBitmapCanvas = new Canvas(this.mDrawBitmap.get());
        }
        this.mDrawBitmap.get().eraseColor(0);
        for (IPieDataSet iPieDataSet : ((PieData) this.mChart.getData()).getDataSets()) {
            if (iPieDataSet.isVisible() && iPieDataSet.getEntryCount() > 0) {
                drawDataSet(canvas, iPieDataSet);
            }
        }
    }

    protected void drawDataSet(Canvas canvas, IPieDataSet iPieDataSet) {
        int i;
        int i2;
        float f;
        float f2;
        float f3;
        RectF rectF;
        int i3;
        float[] fArr;
        int i4;
        float f4;
        MPPointF mPPointF;
        float f5;
        float f6;
        MPPointF mPPointF2;
        float f7;
        int i5;
        PieChartRenderer pieChartRenderer = this;
        IPieDataSet iPieDataSet2 = iPieDataSet;
        float rotationAngle = pieChartRenderer.mChart.getRotationAngle();
        float phaseX = pieChartRenderer.mAnimator.getPhaseX();
        float phaseY = pieChartRenderer.mAnimator.getPhaseY();
        RectF circleBox = pieChartRenderer.mChart.getCircleBox();
        int entryCount = iPieDataSet.getEntryCount();
        float[] drawAngles = pieChartRenderer.mChart.getDrawAngles();
        MPPointF centerCircleBox = pieChartRenderer.mChart.getCenterCircleBox();
        float radius = pieChartRenderer.mChart.getRadius();
        int i6 = 1;
        boolean z = pieChartRenderer.mChart.isDrawHoleEnabled() && !pieChartRenderer.mChart.isDrawSlicesUnderHoleEnabled();
        float holeRadius = z ? (pieChartRenderer.mChart.getHoleRadius() / 100.0f) * radius : 0.0f;
        int i7 = 0;
        for (int i8 = 0; i8 < entryCount; i8++) {
            if (Math.abs(iPieDataSet2.getEntryForIndex(i8).getY()) > Utils.FLOAT_EPSILON) {
                i7++;
            }
        }
        float sliceSpace = i7 <= 1 ? 0.0f : pieChartRenderer.getSliceSpace(iPieDataSet2);
        int i9 = 0;
        float f8 = 0.0f;
        while (i9 < entryCount) {
            float f9 = drawAngles[i9];
            if (Math.abs(iPieDataSet2.getEntryForIndex(i9).getY()) <= Utils.FLOAT_EPSILON || pieChartRenderer.mChart.needsHighlight(i9)) {
                i = i9;
                i2 = i6;
                f = radius;
                f2 = rotationAngle;
                f3 = phaseX;
                rectF = circleBox;
                i3 = entryCount;
                fArr = drawAngles;
                i4 = i7;
                f4 = holeRadius;
                mPPointF = centerCircleBox;
            } else {
                int i10 = (sliceSpace <= 0.0f || f9 > 180.0f) ? 0 : i6;
                pieChartRenderer.mRenderPaint.setColor(iPieDataSet2.getColor(i9));
                float f10 = i7 == 1 ? 0.0f : sliceSpace / (0.017453292f * radius);
                float f11 = rotationAngle + ((f8 + (f10 / 2.0f)) * phaseY);
                float f12 = (f9 - f10) * phaseY;
                if (f12 < 0.0f) {
                    f12 = 0.0f;
                }
                pieChartRenderer.mPathBuffer.reset();
                int i11 = i9;
                int i12 = i7;
                double d = f11 * 0.017453292f;
                i3 = entryCount;
                fArr = drawAngles;
                float cos = centerCircleBox.x + (((float) Math.cos(d)) * radius);
                float sin = centerCircleBox.y + (((float) Math.sin(d)) * radius);
                if (f12 < 360.0f || f12 % 360.0f > Utils.FLOAT_EPSILON) {
                    f3 = phaseX;
                    pieChartRenderer.mPathBuffer.moveTo(cos, sin);
                    pieChartRenderer.mPathBuffer.arcTo(circleBox, f11, f12);
                } else {
                    f3 = phaseX;
                    pieChartRenderer.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, radius, Path.Direction.CW);
                }
                float f13 = f12;
                pieChartRenderer.mInnerRectBuffer.set(centerCircleBox.x - holeRadius, centerCircleBox.y - holeRadius, centerCircleBox.x + holeRadius, centerCircleBox.y + holeRadius);
                if (!z) {
                    f4 = holeRadius;
                    f = radius;
                    f2 = rotationAngle;
                    rectF = circleBox;
                    i = i11;
                    i4 = i12;
                    f5 = f13;
                    i2 = 1;
                    mPPointF = centerCircleBox;
                    f6 = 360.0f;
                } else if (holeRadius > 0.0f || i10 != 0) {
                    if (i10 != 0) {
                        f7 = f13;
                        rectF = circleBox;
                        i4 = i12;
                        i = i11;
                        f4 = holeRadius;
                        i5 = 1;
                        f = radius;
                        mPPointF2 = centerCircleBox;
                        float calculateMinimumRadiusForSpacedSlice = pieChartRenderer.calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f9 * phaseY, cos, sin, f11, f7);
                        if (calculateMinimumRadiusForSpacedSlice < 0.0f) {
                            calculateMinimumRadiusForSpacedSlice = -calculateMinimumRadiusForSpacedSlice;
                        }
                        holeRadius = Math.max(f4, calculateMinimumRadiusForSpacedSlice);
                    } else {
                        f4 = holeRadius;
                        f = radius;
                        mPPointF2 = centerCircleBox;
                        rectF = circleBox;
                        i = i11;
                        i4 = i12;
                        f7 = f13;
                        i5 = 1;
                    }
                    float f14 = (i4 == i5 || holeRadius == 0.0f) ? 0.0f : sliceSpace / (0.017453292f * holeRadius);
                    float f15 = ((f8 + (f14 / 2.0f)) * phaseY) + rotationAngle;
                    float f16 = (f9 - f14) * phaseY;
                    if (f16 < 0.0f) {
                        f16 = 0.0f;
                    }
                    float f17 = f15 + f16;
                    if (f7 < 360.0f || f7 % 360.0f > Utils.FLOAT_EPSILON) {
                        i2 = i5;
                        pieChartRenderer = this;
                        double d2 = f17 * 0.017453292f;
                        f2 = rotationAngle;
                        pieChartRenderer.mPathBuffer.lineTo(mPPointF2.x + (((float) Math.cos(d2)) * holeRadius), mPPointF2.y + (holeRadius * ((float) Math.sin(d2))));
                        pieChartRenderer.mPathBuffer.arcTo(pieChartRenderer.mInnerRectBuffer, f17, -f16);
                    } else {
                        i2 = i5;
                        pieChartRenderer = this;
                        pieChartRenderer.mPathBuffer.addCircle(mPPointF2.x, mPPointF2.y, holeRadius, Path.Direction.CCW);
                        f2 = rotationAngle;
                    }
                    mPPointF = mPPointF2;
                    pieChartRenderer.mPathBuffer.close();
                    pieChartRenderer.mBitmapCanvas.drawPath(pieChartRenderer.mPathBuffer, pieChartRenderer.mRenderPaint);
                } else {
                    f4 = holeRadius;
                    f = radius;
                    f2 = rotationAngle;
                    rectF = circleBox;
                    i = i11;
                    i4 = i12;
                    f5 = f13;
                    f6 = 360.0f;
                    i2 = 1;
                    mPPointF = centerCircleBox;
                }
                if (f5 % f6 > Utils.FLOAT_EPSILON) {
                    if (i10 != 0) {
                        float calculateMinimumRadiusForSpacedSlice2 = pieChartRenderer.calculateMinimumRadiusForSpacedSlice(mPPointF, f, f9 * phaseY, cos, sin, f11, f5);
                        double d3 = (f11 + (f5 / 2.0f)) * 0.017453292f;
                        pieChartRenderer.mPathBuffer.lineTo(mPPointF.x + (((float) Math.cos(d3)) * calculateMinimumRadiusForSpacedSlice2), mPPointF.y + (calculateMinimumRadiusForSpacedSlice2 * ((float) Math.sin(d3))));
                    } else {
                        pieChartRenderer.mPathBuffer.lineTo(mPPointF.x, mPPointF.y);
                    }
                }
                pieChartRenderer.mPathBuffer.close();
                pieChartRenderer.mBitmapCanvas.drawPath(pieChartRenderer.mPathBuffer, pieChartRenderer.mRenderPaint);
            }
            f8 += f9 * f3;
            i9 = i + 1;
            centerCircleBox = mPPointF;
            i7 = i4;
            holeRadius = f4;
            radius = f;
            i6 = i2;
            entryCount = i3;
            drawAngles = fArr;
            phaseX = f3;
            circleBox = rectF;
            rotationAngle = f2;
            iPieDataSet2 = iPieDataSet;
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    protected void drawEntryLabel(Canvas canvas, String str, float f, float f2) {
        canvas.drawText(str, f, f2, this.mEntryLabelsPaint);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
        drawHole(canvas);
        canvas.drawBitmap(this.mDrawBitmap.get(), 0.0f, 0.0f, (Paint) null);
        drawCenterText(canvas);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        IPieDataSet dataSetByIndex;
        float f;
        int i;
        boolean z;
        float f2;
        float f3;
        float[] fArr;
        float[] fArr2;
        RectF rectF;
        int i2;
        int i3;
        float f4;
        int i4;
        float f5;
        float f6;
        float f7;
        Highlight[] highlightArr2 = highlightArr;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        float rotationAngle = this.mChart.getRotationAngle();
        float[] drawAngles = this.mChart.getDrawAngles();
        float[] absoluteAngles = this.mChart.getAbsoluteAngles();
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        float radius = this.mChart.getRadius();
        boolean z2 = this.mChart.isDrawHoleEnabled() && !this.mChart.isDrawSlicesUnderHoleEnabled();
        boolean z3 = false;
        float holeRadius = z2 ? (this.mChart.getHoleRadius() / 100.0f) * radius : 0.0f;
        RectF rectF2 = this.mDrawHighlightedRectF;
        rectF2.set(0.0f, 0.0f, 0.0f, 0.0f);
        int i5 = 0;
        while (i5 < highlightArr2.length) {
            int x = (int) highlightArr2[i5].getX();
            if (x < drawAngles.length && (dataSetByIndex = ((PieData) this.mChart.getData()).getDataSetByIndex(highlightArr2[i5].getDataSetIndex())) != null && dataSetByIndex.isHighlightEnabled()) {
                int entryCount = dataSetByIndex.getEntryCount();
                int i6 = 0;
                int i7 = 0;
                while (i6 < entryCount) {
                    int i8 = entryCount;
                    if (Math.abs(dataSetByIndex.getEntryForIndex(i6).getY()) > Utils.FLOAT_EPSILON) {
                        i7++;
                    }
                    i6++;
                    entryCount = i8;
                }
                if (x == 0) {
                    i = 1;
                    f = 0.0f;
                } else {
                    f = absoluteAngles[x - 1] * phaseX;
                    i = 1;
                }
                float sliceSpace = i7 <= i ? 0.0f : dataSetByIndex.getSliceSpace();
                float f8 = drawAngles[x];
                float selectionShift = dataSetByIndex.getSelectionShift();
                float f9 = radius + selectionShift;
                int i9 = i5;
                rectF2.set(this.mChart.getCircleBox());
                float f10 = -selectionShift;
                rectF2.inset(f10, f10);
                boolean z4 = sliceSpace > 0.0f && f8 <= 180.0f;
                this.mRenderPaint.setColor(dataSetByIndex.getColor(x));
                float f11 = i7 == 1 ? 0.0f : sliceSpace / (0.017453292f * radius);
                float f12 = i7 == 1 ? 0.0f : sliceSpace / (0.017453292f * f9);
                float f13 = rotationAngle + ((f + (f11 / 2.0f)) * phaseY);
                float f14 = (f8 - f11) * phaseY;
                z = false;
                float f15 = f14 < 0.0f ? 0.0f : f14;
                float f16 = ((f + (f12 / 2.0f)) * phaseY) + rotationAngle;
                float f17 = (f8 - f12) * phaseY;
                if (f17 < 0.0f) {
                    f17 = 0.0f;
                }
                this.mPathBuffer.reset();
                if (f15 < 360.0f || f15 % 360.0f > Utils.FLOAT_EPSILON) {
                    f2 = holeRadius;
                    f3 = phaseX;
                    double d = f16 * 0.017453292f;
                    fArr = drawAngles;
                    fArr2 = absoluteAngles;
                    this.mPathBuffer.moveTo(centerCircleBox.x + (((float) Math.cos(d)) * f9), centerCircleBox.y + (f9 * ((float) Math.sin(d))));
                    this.mPathBuffer.arcTo(rectF2, f16, f17);
                } else {
                    this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, f9, Path.Direction.CW);
                    f2 = holeRadius;
                    f3 = phaseX;
                    fArr = drawAngles;
                    fArr2 = absoluteAngles;
                }
                if (z4) {
                    double d2 = f13 * 0.017453292f;
                    i3 = i9;
                    f4 = f2;
                    rectF = rectF2;
                    i4 = 1;
                    i2 = i7;
                    f5 = calculateMinimumRadiusForSpacedSlice(centerCircleBox, radius, f8 * phaseY, (((float) Math.cos(d2)) * radius) + centerCircleBox.x, centerCircleBox.y + (((float) Math.sin(d2)) * radius), f13, f15);
                } else {
                    rectF = rectF2;
                    i2 = i7;
                    i3 = i9;
                    f4 = f2;
                    i4 = 1;
                    f5 = 0.0f;
                }
                this.mInnerRectBuffer.set(centerCircleBox.x - f4, centerCircleBox.y - f4, centerCircleBox.x + f4, centerCircleBox.y + f4);
                if (!z2 || (f4 <= 0.0f && !z4)) {
                    f6 = f4;
                    if (f15 % 360.0f > Utils.FLOAT_EPSILON) {
                        if (z4) {
                            double d3 = (f13 + (f15 / 2.0f)) * 0.017453292f;
                            this.mPathBuffer.lineTo(centerCircleBox.x + (((float) Math.cos(d3)) * f5), centerCircleBox.y + (f5 * ((float) Math.sin(d3))));
                        } else {
                            this.mPathBuffer.lineTo(centerCircleBox.x, centerCircleBox.y);
                        }
                    }
                } else {
                    if (z4) {
                        if (f5 < 0.0f) {
                            f5 = -f5;
                        }
                        f7 = Math.max(f4, f5);
                    } else {
                        f7 = f4;
                    }
                    float f18 = (i2 == i4 || f7 == 0.0f) ? 0.0f : sliceSpace / (0.017453292f * f7);
                    float f19 = rotationAngle + ((f + (f18 / 2.0f)) * phaseY);
                    float f20 = (f8 - f18) * phaseY;
                    if (f20 < 0.0f) {
                        f20 = 0.0f;
                    }
                    float f21 = f19 + f20;
                    if (f15 < 360.0f || f15 % 360.0f > Utils.FLOAT_EPSILON) {
                        double d4 = f21 * 0.017453292f;
                        f6 = f4;
                        this.mPathBuffer.lineTo(centerCircleBox.x + (((float) Math.cos(d4)) * f7), centerCircleBox.y + (f7 * ((float) Math.sin(d4))));
                        this.mPathBuffer.arcTo(this.mInnerRectBuffer, f21, -f20);
                    } else {
                        this.mPathBuffer.addCircle(centerCircleBox.x, centerCircleBox.y, f7, Path.Direction.CCW);
                        f6 = f4;
                    }
                }
                this.mPathBuffer.close();
                this.mBitmapCanvas.drawPath(this.mPathBuffer, this.mRenderPaint);
            } else {
                i3 = i5;
                rectF = rectF2;
                f6 = holeRadius;
                z = z3;
                f3 = phaseX;
                fArr = drawAngles;
                fArr2 = absoluteAngles;
            }
            i5 = i3 + 1;
            z3 = z;
            phaseX = f3;
            drawAngles = fArr;
            absoluteAngles = fArr2;
            rectF2 = rectF;
            holeRadius = f6;
            highlightArr2 = highlightArr;
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    protected void drawHole(Canvas canvas) {
        if (!this.mChart.isDrawHoleEnabled() || this.mBitmapCanvas == null) {
            return;
        }
        float radius = this.mChart.getRadius();
        float holeRadius = (this.mChart.getHoleRadius() / 100.0f) * radius;
        MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
        if (Color.alpha(this.mHolePaint.getColor()) > 0) {
            this.mBitmapCanvas.drawCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, this.mHolePaint);
        }
        if (Color.alpha(this.mTransparentCirclePaint.getColor()) > 0 && this.mChart.getTransparentCircleRadius() > this.mChart.getHoleRadius()) {
            int alpha = this.mTransparentCirclePaint.getAlpha();
            float transparentCircleRadius = radius * (this.mChart.getTransparentCircleRadius() / 100.0f);
            this.mTransparentCirclePaint.setAlpha((int) (alpha * this.mAnimator.getPhaseX() * this.mAnimator.getPhaseY()));
            this.mHoleCirclePath.reset();
            this.mHoleCirclePath.addCircle(centerCircleBox.x, centerCircleBox.y, transparentCircleRadius, Path.Direction.CW);
            this.mHoleCirclePath.addCircle(centerCircleBox.x, centerCircleBox.y, holeRadius, Path.Direction.CCW);
            this.mBitmapCanvas.drawPath(this.mHoleCirclePath, this.mTransparentCirclePaint);
            this.mTransparentCirclePaint.setAlpha(alpha);
        }
        MPPointF.recycleInstance(centerCircleBox);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void drawRoundedSlices(Canvas canvas) {
        float f;
        float f2;
        float[] fArr;
        float f3;
        if (this.mChart.isDrawRoundedSlicesEnabled()) {
            IPieDataSet dataSet = ((PieData) this.mChart.getData()).getDataSet();
            if (dataSet.isVisible()) {
                float phaseX = this.mAnimator.getPhaseX();
                float phaseY = this.mAnimator.getPhaseY();
                MPPointF centerCircleBox = this.mChart.getCenterCircleBox();
                float radius = this.mChart.getRadius();
                float holeRadius = (radius - ((this.mChart.getHoleRadius() * radius) / 100.0f)) / 2.0f;
                float[] drawAngles = this.mChart.getDrawAngles();
                float rotationAngle = this.mChart.getRotationAngle();
                int i = 0;
                while (i < dataSet.getEntryCount()) {
                    float f4 = drawAngles[i];
                    if (Math.abs(dataSet.getEntryForIndex(i).getY()) > Utils.FLOAT_EPSILON) {
                        double d = radius - holeRadius;
                        double d2 = (rotationAngle + f4) * phaseY;
                        fArr = drawAngles;
                        f3 = rotationAngle;
                        f2 = phaseY;
                        f = phaseX;
                        float cos = (float) ((Math.cos(Math.toRadians(d2)) * d) + centerCircleBox.x);
                        float sin = (float) ((d * Math.sin(Math.toRadians(d2))) + centerCircleBox.y);
                        this.mRenderPaint.setColor(dataSet.getColor(i));
                        this.mBitmapCanvas.drawCircle(cos, sin, holeRadius, this.mRenderPaint);
                    } else {
                        f = phaseX;
                        f2 = phaseY;
                        fArr = drawAngles;
                        f3 = rotationAngle;
                    }
                    rotationAngle = f3 + (f4 * f);
                    i++;
                    drawAngles = fArr;
                    phaseY = f2;
                    phaseX = f;
                }
                MPPointF.recycleInstance(centerCircleBox);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x023b  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0188 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0308 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x038e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0326 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01c1  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x028a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02d6  */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void drawValues(android.graphics.Canvas r58) {
        /*
            Method dump skipped, instructions count: 1040
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.renderer.PieChartRenderer.drawValues(android.graphics.Canvas):void");
    }

    public TextPaint getPaintCenterText() {
        return this.mCenterTextPaint;
    }

    public Paint getPaintEntryLabels() {
        return this.mEntryLabelsPaint;
    }

    public Paint getPaintHole() {
        return this.mHolePaint;
    }

    public Paint getPaintTransparentCircle() {
        return this.mTransparentCirclePaint;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected float getSliceSpace(IPieDataSet iPieDataSet) {
        if (iPieDataSet.isAutomaticallyDisableSliceSpacingEnabled() && iPieDataSet.getSliceSpace() / this.mViewPortHandler.getSmallestContentExtension() > (iPieDataSet.getYMin() / ((PieData) this.mChart.getData()).getYValueSum()) * 2.0f) {
            return 0.0f;
        }
        return iPieDataSet.getSliceSpace();
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }

    public void releaseBitmap() {
        if (this.mBitmapCanvas != null) {
            this.mBitmapCanvas.setBitmap(null);
            this.mBitmapCanvas = null;
        }
        if (this.mDrawBitmap != null) {
            this.mDrawBitmap.get().recycle();
            this.mDrawBitmap.clear();
            this.mDrawBitmap = null;
        }
    }
}
