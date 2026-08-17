package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.highlight.Range;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* loaded from: classes.dex */
public class BarChartRenderer extends BarLineScatterCandleBubbleRenderer {
    protected Paint mBarBorderPaint;
    protected BarBuffer[] mBarBuffers;
    protected RectF mBarRect;
    private RectF mBarShadowRectBuffer;
    protected BarDataProvider mChart;
    protected Paint mShadowPaint;

    public BarChartRenderer(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mBarRect = new RectF();
        this.mBarShadowRectBuffer = new RectF();
        this.mChart = barDataProvider;
        this.mHighlightPaint = new Paint(1);
        this.mHighlightPaint.setStyle(Paint.Style.FILL);
        this.mHighlightPaint.setColor(Color.rgb(0, 0, 0));
        this.mHighlightPaint.setAlpha(Opcodes.ISHL);
        this.mShadowPaint = new Paint(1);
        this.mShadowPaint.setStyle(Paint.Style.FILL);
        this.mBarBorderPaint = new Paint(1);
        this.mBarBorderPaint.setStyle(Paint.Style.STROKE);
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        BarData barData = this.mChart.getBarData();
        for (int i = 0; i < barData.getDataSetCount(); i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            if (iBarDataSet.isVisible()) {
                drawDataSet(canvas, iBarDataSet, i);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
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
                this.mBarShadowRectBuffer.left = x - barWidth;
                this.mBarShadowRectBuffer.right = x + barWidth;
                transformer.rectValueToPixel(this.mBarShadowRectBuffer);
                if (this.mViewPortHandler.isInBoundsLeft(this.mBarShadowRectBuffer.right)) {
                    if (!this.mViewPortHandler.isInBoundsRight(this.mBarShadowRectBuffer.left)) {
                        break;
                    }
                    this.mBarShadowRectBuffer.top = this.mViewPortHandler.contentTop();
                    this.mBarShadowRectBuffer.bottom = this.mViewPortHandler.contentBottom();
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
            int i4 = i2 + 2;
            if (this.mViewPortHandler.isInBoundsLeft(barBuffer.buffer[i4])) {
                if (!this.mViewPortHandler.isInBoundsRight(barBuffer.buffer[i2])) {
                    return;
                }
                if (!z2) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i2 / 4));
                }
                int i5 = i2 + 1;
                int i6 = i2 + 3;
                canvas2.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i5], barBuffer.buffer[i4], barBuffer.buffer[i6], this.mRenderPaint);
                if (z) {
                    canvas.drawRect(barBuffer.buffer[i2], barBuffer.buffer[i5], barBuffer.buffer[i4], barBuffer.buffer[i6], this.mBarBorderPaint);
                }
            }
            i2 += 4;
            canvas2 = canvas;
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        float y;
        float f;
        float f2;
        float f3;
        BarData barData = this.mChart.getBarData();
        for (Highlight highlight : highlightArr) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iBarDataSet != null && iBarDataSet.isHighlightEnabled()) {
                BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(barEntry, iBarDataSet)) {
                    Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                    this.mHighlightPaint.setColor(iBarDataSet.getHighLightColor());
                    this.mHighlightPaint.setAlpha(iBarDataSet.getHighLightAlpha());
                    if (!(highlight.getStackIndex() >= 0 && barEntry.isStacked())) {
                        y = barEntry.getY();
                        f = 0.0f;
                    } else if (this.mChart.isHighlightFullBarEnabled()) {
                        y = barEntry.getPositiveSum();
                        f = -barEntry.getNegativeSum();
                    } else {
                        Range range = barEntry.getRanges()[highlight.getStackIndex()];
                        f3 = range.from;
                        f2 = range.to;
                        prepareBarHighlight(barEntry.getX(), f3, f2, barData.getBarWidth() / 2.0f, transformer);
                        setHighlightDrawPos(highlight, this.mBarRect);
                        canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                    }
                    f2 = f;
                    f3 = y;
                    prepareBarHighlight(barEntry.getX(), f3, f2, barData.getBarWidth() / 2.0f, transformer);
                    setHighlightDrawPos(highlight, this.mBarRect);
                    canvas.drawRect(this.mBarRect, this.mHighlightPaint);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        MPPointF mPPointF;
        List list;
        int i;
        float f;
        boolean z;
        int i2;
        float[] fArr;
        Transformer transformer;
        int i3;
        float[] fArr2;
        float f2;
        float f3;
        float f4;
        BarEntry barEntry;
        float f5;
        boolean z2;
        int i4;
        int i5;
        MPPointF mPPointF2;
        List list2;
        BarBuffer barBuffer;
        float f6;
        Entry entry;
        if (isDrawingValuesAllowed(this.mChart)) {
            List dataSets = this.mChart.getBarData().getDataSets();
            float convertDpToPixel = Utils.convertDpToPixel(4.5f);
            boolean isDrawValueAboveBarEnabled = this.mChart.isDrawValueAboveBarEnabled();
            int i6 = 0;
            while (i6 < this.mChart.getBarData().getDataSetCount()) {
                IBarDataSet iBarDataSet = (IBarDataSet) dataSets.get(i6);
                if (shouldDrawValues(iBarDataSet)) {
                    applyValueTextStyle(iBarDataSet);
                    boolean isInverted = this.mChart.isInverted(iBarDataSet.getAxisDependency());
                    float calcTextHeight = Utils.calcTextHeight(this.mValuePaint, "8");
                    float f7 = isDrawValueAboveBarEnabled ? -convertDpToPixel : calcTextHeight + convertDpToPixel;
                    float f8 = isDrawValueAboveBarEnabled ? calcTextHeight + convertDpToPixel : -convertDpToPixel;
                    if (isInverted) {
                        f7 = (-f7) - calcTextHeight;
                        f8 = (-f8) - calcTextHeight;
                    }
                    float f9 = f7;
                    float f10 = f8;
                    BarBuffer barBuffer2 = this.mBarBuffers[i6];
                    float phaseY = this.mAnimator.getPhaseY();
                    MPPointF mPPointF3 = MPPointF.getInstance(iBarDataSet.getIconsOffset());
                    mPPointF3.x = Utils.convertDpToPixel(mPPointF3.x);
                    mPPointF3.y = Utils.convertDpToPixel(mPPointF3.y);
                    if (iBarDataSet.isStacked()) {
                        mPPointF = mPPointF3;
                        list = dataSets;
                        Transformer transformer2 = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
                        int i7 = 0;
                        int i8 = 0;
                        while (i7 < iBarDataSet.getEntryCount() * this.mAnimator.getPhaseX()) {
                            BarEntry barEntry2 = (BarEntry) iBarDataSet.getEntryForIndex(i7);
                            float[] yVals = barEntry2.getYVals();
                            float f11 = (barBuffer2.buffer[i8] + barBuffer2.buffer[i8 + 2]) / 2.0f;
                            int valueTextColor = iBarDataSet.getValueTextColor(i7);
                            if (yVals != null) {
                                i = i7;
                                f = convertDpToPixel;
                                z = isDrawValueAboveBarEnabled;
                                i2 = i6;
                                fArr = yVals;
                                transformer = transformer2;
                                float f12 = f11;
                                float[] fArr3 = new float[fArr.length * 2];
                                float f13 = -barEntry2.getNegativeSum();
                                float f14 = 0.0f;
                                int i9 = 0;
                                int i10 = 0;
                                while (i9 < fArr3.length) {
                                    float f15 = fArr[i10];
                                    if (f15 != 0.0f || (f14 != 0.0f && f13 != 0.0f)) {
                                        if (f15 >= 0.0f) {
                                            f15 = f14 + f15;
                                            f14 = f15;
                                        } else {
                                            float f16 = f13;
                                            f13 -= f15;
                                            f15 = f16;
                                        }
                                    }
                                    fArr3[i9 + 1] = f15 * phaseY;
                                    i9 += 2;
                                    i10++;
                                }
                                transformer.pointValuesToPixel(fArr3);
                                int i11 = 0;
                                while (i11 < fArr3.length) {
                                    int i12 = i11 / 2;
                                    float f17 = fArr[i12];
                                    float f18 = fArr3[i11 + 1] + (((f17 > 0.0f ? 1 : (f17 == 0.0f ? 0 : -1)) == 0 && (f13 > 0.0f ? 1 : (f13 == 0.0f ? 0 : -1)) == 0 && (f14 > 0.0f ? 1 : (f14 == 0.0f ? 0 : -1)) > 0) || (f17 > 0.0f ? 1 : (f17 == 0.0f ? 0 : -1)) < 0 ? f10 : f9);
                                    if (!this.mViewPortHandler.isInBoundsRight(f12)) {
                                        break;
                                    }
                                    if (this.mViewPortHandler.isInBoundsY(f18) && this.mViewPortHandler.isInBoundsLeft(f12)) {
                                        if (iBarDataSet.isDrawValuesEnabled()) {
                                            f3 = f18;
                                            i3 = i11;
                                            fArr2 = fArr3;
                                            f2 = f12;
                                            drawValue(canvas, iBarDataSet.getValueFormatter(), fArr[i12], barEntry2, i2, f12, f3, valueTextColor);
                                        } else {
                                            f3 = f18;
                                            i3 = i11;
                                            fArr2 = fArr3;
                                            f2 = f12;
                                        }
                                        if (barEntry2.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                            Drawable icon = barEntry2.getIcon();
                                            Utils.drawImage(canvas, icon, (int) (f2 + mPPointF.x), (int) (f3 + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                        }
                                    } else {
                                        i3 = i11;
                                        fArr2 = fArr3;
                                        f2 = f12;
                                    }
                                    i11 = i3 + 2;
                                    fArr3 = fArr2;
                                    f12 = f2;
                                }
                            } else {
                                if (!this.mViewPortHandler.isInBoundsRight(f11)) {
                                    break;
                                }
                                int i13 = i8 + 1;
                                if (this.mViewPortHandler.isInBoundsY(barBuffer2.buffer[i13]) && this.mViewPortHandler.isInBoundsLeft(f11)) {
                                    if (iBarDataSet.isDrawValuesEnabled()) {
                                        f4 = f11;
                                        f = convertDpToPixel;
                                        fArr = yVals;
                                        int i14 = i6;
                                        z = isDrawValueAboveBarEnabled;
                                        barEntry = barEntry2;
                                        i = i7;
                                        i2 = i6;
                                        transformer = transformer2;
                                        drawValue(canvas, iBarDataSet.getValueFormatter(), barEntry2.getY(), barEntry2, i14, f4, barBuffer2.buffer[i13] + (barEntry2.getY() >= 0.0f ? f9 : f10), valueTextColor);
                                    } else {
                                        f4 = f11;
                                        i = i7;
                                        f = convertDpToPixel;
                                        z = isDrawValueAboveBarEnabled;
                                        i2 = i6;
                                        fArr = yVals;
                                        barEntry = barEntry2;
                                        transformer = transformer2;
                                    }
                                    if (barEntry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                        Drawable icon2 = barEntry.getIcon();
                                        Utils.drawImage(canvas, icon2, (int) (f4 + mPPointF.x), (int) (barBuffer2.buffer[i13] + (barEntry.getY() >= 0.0f ? f9 : f10) + mPPointF.y), icon2.getIntrinsicWidth(), icon2.getIntrinsicHeight());
                                    }
                                } else {
                                    f = convertDpToPixel;
                                    z = isDrawValueAboveBarEnabled;
                                    i2 = i6;
                                    transformer2 = transformer2;
                                    i7 = i7;
                                    convertDpToPixel = f;
                                    isDrawValueAboveBarEnabled = z;
                                    i6 = i2;
                                }
                            }
                            i8 = fArr == null ? i8 + 4 : i8 + (4 * fArr.length);
                            i7 = i + 1;
                            transformer2 = transformer;
                            convertDpToPixel = f;
                            isDrawValueAboveBarEnabled = z;
                            i6 = i2;
                        }
                    } else {
                        int i15 = 0;
                        while (i15 < barBuffer2.buffer.length * this.mAnimator.getPhaseX()) {
                            float f19 = (barBuffer2.buffer[i15] + barBuffer2.buffer[i15 + 2]) / 2.0f;
                            if (!this.mViewPortHandler.isInBoundsRight(f19)) {
                                break;
                            }
                            int i16 = i15 + 1;
                            if (this.mViewPortHandler.isInBoundsY(barBuffer2.buffer[i16]) && this.mViewPortHandler.isInBoundsLeft(f19)) {
                                int i17 = i15 / 4;
                                Entry entry2 = (BarEntry) iBarDataSet.getEntryForIndex(i17);
                                float y = entry2.getY();
                                if (iBarDataSet.isDrawValuesEnabled()) {
                                    f6 = f19;
                                    i5 = i15;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                    drawValue(canvas, iBarDataSet.getValueFormatter(), y, entry2, i6, f6, y >= 0.0f ? barBuffer2.buffer[i16] + f9 : barBuffer2.buffer[i15 + 3] + f10, iBarDataSet.getValueTextColor(i17));
                                    entry = entry2;
                                } else {
                                    f6 = f19;
                                    i5 = i15;
                                    mPPointF2 = mPPointF3;
                                    list2 = dataSets;
                                    barBuffer = barBuffer2;
                                    entry = entry2;
                                }
                                if (entry.getIcon() != null && iBarDataSet.isDrawIconsEnabled()) {
                                    Drawable icon3 = entry.getIcon();
                                    Utils.drawImage(canvas, icon3, (int) (f6 + mPPointF2.x), (int) ((y >= 0.0f ? barBuffer.buffer[i16] + f9 : barBuffer.buffer[i5 + 3] + f10) + mPPointF2.y), icon3.getIntrinsicWidth(), icon3.getIntrinsicHeight());
                                }
                            } else {
                                i5 = i15;
                                mPPointF2 = mPPointF3;
                                list2 = dataSets;
                                barBuffer = barBuffer2;
                            }
                            i15 = i5 + 4;
                            barBuffer2 = barBuffer;
                            mPPointF3 = mPPointF2;
                            dataSets = list2;
                        }
                        mPPointF = mPPointF3;
                        list = dataSets;
                    }
                    f5 = convertDpToPixel;
                    z2 = isDrawValueAboveBarEnabled;
                    i4 = i6;
                    MPPointF.recycleInstance(mPPointF);
                } else {
                    list = dataSets;
                    f5 = convertDpToPixel;
                    z2 = isDrawValueAboveBarEnabled;
                    i4 = i6;
                }
                i6 = i4 + 1;
                dataSets = list;
                convertDpToPixel = f5;
                isDrawValueAboveBarEnabled = z2;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
        BarData barData = this.mChart.getBarData();
        this.mBarBuffers = new BarBuffer[barData.getDataSetCount()];
        for (int i = 0; i < this.mBarBuffers.length; i++) {
            IBarDataSet iBarDataSet = (IBarDataSet) barData.getDataSetByIndex(i);
            this.mBarBuffers[i] = new BarBuffer(iBarDataSet.getEntryCount() * 4 * (iBarDataSet.isStacked() ? iBarDataSet.getStackSize() : 1), barData.getDataSetCount(), iBarDataSet.isStacked());
        }
    }

    protected void prepareBarHighlight(float f, float f2, float f3, float f4, Transformer transformer) {
        this.mBarRect.set(f - f4, f2, f + f4, f3);
        transformer.rectToPixelPhase(this.mBarRect, this.mAnimator.getPhaseY());
    }

    protected void setHighlightDrawPos(Highlight highlight, RectF rectF) {
        highlight.setDraw(rectF.centerX(), rectF.top);
    }
}
