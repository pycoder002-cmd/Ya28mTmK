package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.buffer.HorizontalBarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.dataprovider.ChartInterface;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes.dex */
public class HorizontalBarChartRenderer extends BarChartRenderer {
    private RectF mBarShadowRectBuffer;

    public HorizontalBarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.mBarShadowRectBuffer = new RectF();
        this.mValuePaint.setTextAlign(Paint.Align.LEFT);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        int i2 = 0;
        boolean z = iBarDataSet.getBarBorderWidth() > 0.0f;
        float phaseX = this.mAnimator.getPhaseX();
        float phaseY = this.mAnimator.getPhaseY();
        if (this.mChart.isDrawBarShadowEnabled()) {
            this.mShadowPaint.setColor(iBarDataSet.getBarShadowColor());
            float barWidth = this.mChart.getBarData().getBarWidth() / 2.0f;
            int min = Math.min((int) Math.ceil(iBarDataSet.getEntryCount() * phaseX), iBarDataSet.getEntryCount());
            for (int i3 = 0; i3 < min; i3++) {
                float x = ((BarEntry) iBarDataSet.getEntryForIndex(i3)).getX();
                this.mBarShadowRectBuffer.top = x - barWidth;
                this.mBarShadowRectBuffer.bottom = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsTop(this.mBarShadowRectBuffer.bottom)) {
                    if (!this.mViewPortHandler.isInBoundsBottom(this.mBarShadowRectBuffer.top)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.left = this.mViewPortHandler.contentLeft();
                    this.mBarShadowRectBuffer.right = this.mViewPortHandler.contentRight();
                    canvas.drawRect(this.mBarShadowRectBuffer, this.mShadowPaint);
                }
            }
        }
        Canvas canvas2 = canvas;
        BarBuffer barBuffer = this.mBarBuffers[i];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(i);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet);
        transformer.pointValuesToPixel(barBuffer.buffer);
        boolean z2 = iBarDataSet.getColors().size() == 1;
        if (z2) {
            this.mRenderPaint.setColor(iBarDataSet.getColor());
        }
        while (i2 < barBuffer.size()) {
            int i4 = i2 + 3;
            if (!this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i4])) {
                return;
            }
            int i5 = i2 + 1;
            if (this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i5])) {
                if (!z2) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i2 / 4));
                }
                int i6 = i2 + 2;
                canvas2.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i5], barBuffer.buffer[i6], barBuffer.buffer[i4], this.mRenderPaint);
                if (z) {
                    canvas.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i5], barBuffer.buffer[i6], barBuffer.buffer[i4], this.mBarBorderPaint);
                }
            }
            i2 += 4;
            canvas2 = canvas;
        }
    }

    protected void drawValue(Canvas canvas, String str, float f, float f2, int i) {
        this.mValuePaint.setColor(i);
        canvas.drawText(str, f, f2, this.mValuePaint);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        List list;
        MPPointF mPPointF;
        float f;
        int i;
        float[] fArr;
        boolean z;
        float f2;
        float f3;
        int i2;
        float[] fArr2;
        BarEntry barEntry;
        float f4;
        boolean z2;
        String str;
        float f5;
        IValueFormatter iValueFormatter;
        float f6;
        float f7;
        int i3;
        List list2;
        boolean z3;
        float f8;
        MPPointF mPPointF2;
        IValueFormatter iValueFormatter2;
        BarBuffer barBuffer;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(5.0f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i4 = 0;
            while (i4 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i4);
                if (shouldDrawValues(iBarDataSet)) {
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    applyValueTextStyle(iBarDataSet);
                    float f9 = 2.0f;
                    float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, "10") / 2.0f;
                    IValueFormatter valueFormatter = iBarDataSet.getValueFormatter();
                    BarBuffer barBuffer2 = this.mBarBuffers[i4];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (iBarDataSet.isStacked()) {
                        list = dataSets;
                        mPPointF = mPPointF3;
                        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i5 = 0;
                        int i6 = 0;
                        while (i5 < iBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry2 = (BarEntry) iBarDataSet.getEntryForIndex(i5);
                            int valueTextColor = iBarDataSet.getValueTextColor(i5);
                            float[] yVals = barEntry2.getYVals();
                            if (yVals == null) {
                                int i7 = i6 + 1;
                                if (!this.mViewPortHandler.isInBoundsTop(barBuffer2.buffer[i7])) {
                                    break;
                                }
                                if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[i6]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i7])) {
                                    String formattedValue = valueFormatter.getFormattedValue(barEntry2.getY(), barEntry2, i4, this.mViewPortHandler);
                                    float calcTextWidth = Utils.calcTextWidth(this.mValuePaint, formattedValue);
                                    float f10 = isDrawValueAboveBarEnabled ? convertDpToPixel : -(calcTextWidth + convertDpToPixel);
                                    float f11 = isDrawValueAboveBarEnabled ? -(calcTextWidth + convertDpToPixel) : convertDpToPixel;
                                    if (isInverted) {
                                        f10 = (-f10) - calcTextWidth;
                                        f11 = (-f11) - calcTextWidth;
                                    }
                                    float f12 = f10;
                                    float f13 = f11;
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        i = i5;
                                        fArr = yVals;
                                        f = convertDpToPixel;
                                        barEntry = barEntry2;
                                        drawValue(canvas, formattedValue, barBuffer2.buffer[i6 + 2] + (barEntry2.getY() >= 0.0f ? f12 : f13), barBuffer2.buffer[i7] + calcTextHeight, valueTextColor);
                                    } else {
                                        f = convertDpToPixel;
                                        i = i5;
                                        fArr = yVals;
                                        barEntry = barEntry2;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon = barEntry.getIcon();
                                        float f14 = barBuffer2.buffer[i6 + 2];
                                        if (barEntry.getY() < 0.0f) {
                                            f12 = f13;
                                        }
                                        Utils.drawImage(canvas, icon, (int) (f14 + f12 + mPPointF.x), (int) (barBuffer2.buffer[i7] + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                    }
                                }
                            } else {
                                f = convertDpToPixel;
                                i = i5;
                                fArr = yVals;
                                float[] fArr3 = new float[fArr.length * 2];
                                float f15 = -barEntry2.getNegativeSum();
                                float f16 = 0.0f;
                                int i8 = 0;
                                int i9 = 0;
                                while (i8 < fArr3.length) {
                                    float f17 = fArr[i9];
                                    if (f17 != 0.0f || (f16 != 0.0f && f15 != 0.0f)) {
                                        if (f17 >= 0.0f) {
                                            f17 = f16 + f17;
                                            f16 = f17;
                                        } else {
                                            float f18 = f15;
                                            f15 -= f17;
                                            f17 = f18;
                                        }
                                    }
                                    fArr3[i8] = f17 * phaseY;
                                    i8 += 2;
                                    i9++;
                                }
                                transformer.pointValuesToPixel(fArr3);
                                int i10 = 0;
                                while (i10 < fArr3.length) {
                                    float f19 = fArr[i10 / 2];
                                    String formattedValue2 = valueFormatter.getFormattedValue(f19, barEntry2, i4, this.mViewPortHandler);
                                    float calcTextWidth2 = Utils.calcTextWidth(this.mValuePaint, formattedValue2);
                                    float f20 = isDrawValueAboveBarEnabled ? f : -(calcTextWidth2 + f);
                                    if (isDrawValueAboveBarEnabled) {
                                        z = isDrawValueAboveBarEnabled;
                                        f2 = -(calcTextWidth2 + f);
                                    } else {
                                        z = isDrawValueAboveBarEnabled;
                                        f2 = f;
                                    }
                                    if (isInverted) {
                                        f20 = (-f20) - calcTextWidth2;
                                        f2 = (-f2) - calcTextWidth2;
                                    }
                                    boolean z4 = (f19 == 0.0f && f15 == 0.0f && f16 > 0.0f) || f19 < 0.0f;
                                    float f21 = fArr3[i10];
                                    if (z4) {
                                        f20 = f2;
                                    }
                                    float f22 = f21 + f20;
                                    float f23 = (barBuffer2.buffer[i6 + 1] + barBuffer2.buffer[i6 + 3]) / 2.0f;
                                    if (!this.mViewPortHandler.isInBoundsTop(f23)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsX(f22) && this.mViewPortHandler.isInBoundsBottom(f23)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f3 = f23;
                                            i2 = i10;
                                            fArr2 = fArr3;
                                            drawValue(canvas, formattedValue2, f22, f23 + calcTextHeight, valueTextColor);
                                        } else {
                                            f3 = f23;
                                            i2 = i10;
                                            fArr2 = fArr3;
                                        }
                                        if (barEntry2.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon2 = barEntry2.getIcon();
                                            Utils.drawImage(canvas, icon2, (int) (f22 + mPPointF.x), (int) (f3 + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                        }
                                    } else {
                                        i2 = i10;
                                        fArr2 = fArr3;
                                    }
                                    i10 = i2 + 2;
                                    fArr3 = fArr2;
                                    isDrawValueAboveBarEnabled = z;
                                }
                            }
                            z = isDrawValueAboveBarEnabled;
                            i6 = fArr == null ? i6 + 4 : i6 + (4 * fArr.length);
                            i5 = i + 1;
                            convertDpToPixel = f;
                            isDrawValueAboveBarEnabled = z;
                        }
                    } else {
                        int i11 = 0;
                        while (i11 < barBuffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            int i12 = i11 + 1;
                            float f24 = (barBuffer2.buffer[i12] + barBuffer2.buffer[i11 + 3]) / f9;
                            if (!this.mViewPortHandler.isInBoundsTop(barBuffer2.buffer[i12])) {
                                break;
                            }
                            if (this.mViewPortHandler.isInBoundsX(barBuffer2.buffer[i11]) && this.mViewPortHandler.isInBoundsBottom(barBuffer2.buffer[i12])) {
                                BarEntry barEntry3 = (BarEntry) iBarDataSet.getEntryForIndex(i11 / 4);
                                float y = barEntry3.getY();
                                String formattedValue3 = valueFormatter.getFormattedValue(y, barEntry3, i4, this.mViewPortHandler);
                                MPPointF mPPointF4 = mPPointF3;
                                float calcTextWidth3 = Utils.calcTextWidth(this.mValuePaint, formattedValue3);
                                if (isDrawValueAboveBarEnabled) {
                                    str = formattedValue3;
                                    f5 = convertDpToPixel;
                                } else {
                                    str = formattedValue3;
                                    f5 = -(calcTextWidth3 + convertDpToPixel);
                                }
                                if (isDrawValueAboveBarEnabled) {
                                    iValueFormatter = valueFormatter;
                                    f6 = -(calcTextWidth3 + convertDpToPixel);
                                } else {
                                    iValueFormatter = valueFormatter;
                                    f6 = convertDpToPixel;
                                }
                                if (isInverted) {
                                    f5 = (-f5) - calcTextWidth3;
                                    f6 = (-f6) - calcTextWidth3;
                                }
                                float f25 = f5;
                                float f26 = f6;
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    f7 = y;
                                    i3 = i11;
                                    list2 = dataSets;
                                    mPPointF2 = mPPointF4;
                                    f8 = calcTextHeight;
                                    barBuffer = barBuffer2;
                                    z3 = isInverted;
                                    iValueFormatter2 = iValueFormatter;
                                    drawValue(canvas, str, (y >= 0.0f ? f25 : f26) + barBuffer2.buffer[i11 + 2], f24 + calcTextHeight, iBarDataSet.getValueTextColor(i11 / 2));
                                } else {
                                    f7 = y;
                                    i3 = i11;
                                    list2 = dataSets;
                                    z3 = isInverted;
                                    f8 = calcTextHeight;
                                    mPPointF2 = mPPointF4;
                                    iValueFormatter2 = iValueFormatter;
                                    barBuffer = barBuffer2;
                                }
                                if (barEntry3.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = barEntry3.getIcon();
                                    float f27 = barBuffer.buffer[i3 + 2];
                                    if (f7 < 0.0f) {
                                        f25 = f26;
                                    }
                                    Utils.drawImage(canvas, icon3, (int) (f27 + f25 + mPPointF2.x), (int) (f24 + mPPointF2.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            } else {
                                i3 = i11;
                                list2 = dataSets;
                                z3 = isInverted;
                                f8 = calcTextHeight;
                                mPPointF2 = mPPointF3;
                                barBuffer = barBuffer2;
                                iValueFormatter2 = valueFormatter;
                            }
                            i11 = i3 + 4;
                            mPPointF3 = mPPointF2;
                            valueFormatter = iValueFormatter2;
                            barBuffer2 = barBuffer;
                            dataSets = list2;
                            calcTextHeight = f8;
                            isInverted = z3;
                            f9 = 2.0f;
                        }
                        list = dataSets;
                        mPPointF = mPPointF3;
                    }
                    f4 = convertDpToPixel;
                    z2 = isDrawValueAboveBarEnabled;
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                    f4 = convertDpToPixel;
                    z2 = isDrawValueAboveBarEnabled;
                }
                i4++;
                dataSets = list;
                convertDpToPixel = f4;
                isDrawValueAboveBarEnabled = z2;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new HorizontalBarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new HorizontalBarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public boolean isDrawingValuesAllowed(ChartInterface chartInterface) {
        return ((float) chartInterface.getData().getEntryCount()) < ((float) chartInterface.getMaxVisibleCount()) * this.mViewPortHandler.getScaleY();
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f2, f - f4, f3, f + f4);
        transformer.rectToPixelPhaseHorizontal(this.mBarRect, this.mAnimator.getPhaseY());
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer
    protected void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerY(), rectF.right);
    }
}
