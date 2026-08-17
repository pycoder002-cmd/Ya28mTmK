package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ICandleDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FSize;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public class LegendRenderer extends Renderer {
    protected List<LegendEntry> computedEntries;
    protected Paint.FontMetrics legendFontMetrics;
    protected Legend mLegend;
    protected Paint mLegendFormPaint;
    protected Paint mLegendLabelPaint;
    private Path mLineFormPath;

    public LegendRenderer(ViewPortHandler viewPortHandler, Legend legend) {
        super(viewPortHandler);
        this.computedEntries = new ArrayList(16);
        this.legendFontMetrics = new Paint.FontMetrics();
        this.mLineFormPath = new Path();
        this.mLegend = legend;
        this.mLegendLabelPaint = new Paint(1);
        this.mLegendLabelPaint.setTextSize(Utils.convertDpToPixel(9.0f));
        this.mLegendLabelPaint.setTextAlign(Paint.Align.LEFT);
        this.mLegendFormPaint = new Paint(1);
        this.mLegendFormPaint.setStyle(Paint.Style.FILL);
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    /* JADX WARN: Type inference failed for: r7v1, types: [com.github.mikephil.charting.interfaces.datasets.IDataSet] */
    public void computeLegend(ChartData<?> chartData) {
        ChartData<?> chartData2;
        ChartData<?> chartData3 = chartData;
        if (!this.mLegend.isLegendCustom()) {
            this.computedEntries.clear();
            int i = 0;
            while (i < chartData.getDataSetCount()) {
                ?? dataSetByIndex = chartData3.getDataSetByIndex(i);
                List<Integer> colors = dataSetByIndex.getColors();
                int entryCount = dataSetByIndex.getEntryCount();
                if (dataSetByIndex instanceof IBarDataSet) {
                    IBarDataSet iBarDataSet = (IBarDataSet) dataSetByIndex;
                    if (iBarDataSet.isStacked()) {
                        String[] stackLabels = iBarDataSet.getStackLabels();
                        for (int i2 = 0; i2 < colors.size() && i2 < iBarDataSet.getStackSize(); i2++) {
                            this.computedEntries.add(new LegendEntry(stackLabels[i2 % stackLabels.length], dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), colors.get(i2).intValue()));
                        }
                        if (iBarDataSet.getLabel() != null) {
                            this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), Legend.LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                        }
                        chartData2 = chartData3;
                        i++;
                        chartData3 = chartData2;
                    }
                }
                if (dataSetByIndex instanceof IPieDataSet) {
                    IPieDataSet iPieDataSet = (IPieDataSet) dataSetByIndex;
                    for (int i3 = 0; i3 < colors.size() && i3 < entryCount; i3++) {
                        this.computedEntries.add(new LegendEntry(iPieDataSet.getEntryForIndex(i3).getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), colors.get(i3).intValue()));
                    }
                    if (iPieDataSet.getLabel() != null) {
                        this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), Legend.LegendForm.NONE, Float.NaN, Float.NaN, null, ColorTemplate.COLOR_NONE));
                    }
                } else {
                    if (dataSetByIndex instanceof ICandleDataSet) {
                        ICandleDataSet iCandleDataSet = (ICandleDataSet) dataSetByIndex;
                        if (iCandleDataSet.getDecreasingColor() != 1122867) {
                            int decreasingColor = iCandleDataSet.getDecreasingColor();
                            int increasingColor = iCandleDataSet.getIncreasingColor();
                            this.computedEntries.add(new LegendEntry(null, dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), decreasingColor));
                            this.computedEntries.add(new LegendEntry(dataSetByIndex.getLabel(), dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), increasingColor));
                        }
                    }
                    int i4 = 0;
                    while (i4 < colors.size() && i4 < entryCount) {
                        this.computedEntries.add(new LegendEntry((i4 >= colors.size() + (-1) || i4 >= entryCount + (-1)) ? chartData.getDataSetByIndex(i).getLabel() : null, dataSetByIndex.getForm(), dataSetByIndex.getFormSize(), dataSetByIndex.getFormLineWidth(), dataSetByIndex.getFormLineDashEffect(), colors.get(i4).intValue()));
                        i4++;
                    }
                }
                chartData2 = chartData;
                i++;
                chartData3 = chartData2;
            }
            if (this.mLegend.getExtraEntries() != null) {
                Collections.addAll(this.computedEntries, this.mLegend.getExtraEntries());
            }
            this.mLegend.setEntries(this.computedEntries);
        }
        Typeface typeface = this.mLegend.getTypeface();
        if (typeface != null) {
            this.mLegendLabelPaint.setTypeface(typeface);
        }
        this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
        this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
        this.mLegend.calculateDimensions(this.mLegendLabelPaint, this.mViewPortHandler);
    }

    protected void drawForm(Canvas canvas, float f, float f2, LegendEntry legendEntry, Legend legend) {
        if (legendEntry.formColor == 1122868 || legendEntry.formColor == 1122867 || legendEntry.formColor == 0) {
            return;
        }
        int save = canvas.save();
        Legend.LegendForm legendForm = legendEntry.form;
        if (legendForm == Legend.LegendForm.DEFAULT) {
            legendForm = legend.getForm();
        }
        this.mLegendFormPaint.setColor(legendEntry.formColor);
        float convertDpToPixel = Utils.convertDpToPixel(Float.isNaN(legendEntry.formSize) ? legend.getFormSize() : legendEntry.formSize);
        float f3 = convertDpToPixel / 2.0f;
        switch (legendForm) {
            case DEFAULT:
            case CIRCLE:
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(f + f3, f2, f3, this.mLegendFormPaint);
                break;
            case SQUARE:
                this.mLegendFormPaint.setStyle(Paint.Style.FILL);
                canvas.drawRect(f, f2 - f3, f + convertDpToPixel, f2 + f3, this.mLegendFormPaint);
                break;
            case LINE:
                float convertDpToPixel2 = Utils.convertDpToPixel(Float.isNaN(legendEntry.formLineWidth) ? legend.getFormLineWidth() : legendEntry.formLineWidth);
                DashPathEffect formLineDashEffect = legendEntry.formLineDashEffect == null ? legend.getFormLineDashEffect() : legendEntry.formLineDashEffect;
                this.mLegendFormPaint.setStyle(Paint.Style.STROKE);
                this.mLegendFormPaint.setStrokeWidth(convertDpToPixel2);
                this.mLegendFormPaint.setPathEffect(formLineDashEffect);
                this.mLineFormPath.reset();
                this.mLineFormPath.moveTo(f, f2);
                this.mLineFormPath.lineTo(f + convertDpToPixel, f2);
                canvas.drawPath(this.mLineFormPath, this.mLegendFormPaint);
                break;
        }
        canvas.restoreToCount(save);
    }

    protected void drawLabel(Canvas canvas, float f, float f2, String str) {
        canvas.drawText(str, f, f2, this.mLegendLabelPaint);
    }

    public Paint getFormPaint() {
        return this.mLegendFormPaint;
    }

    public Paint getLabelPaint() {
        return this.mLegendLabelPaint;
    }

    public void renderLegend(Canvas canvas) {
        float f;
        float f2;
        float f3;
        float f4;
        float chartWidth;
        float f5;
        float contentLeft;
        double d;
        float f6;
        List<Boolean> list;
        float f7;
        float f8;
        LegendEntry[] legendEntryArr;
        List<FSize> list2;
        int i;
        float f9;
        int i2;
        float f10;
        float f11;
        float f12;
        float f13;
        float contentTop;
        Legend.LegendDirection legendDirection;
        float f14;
        LegendEntry legendEntry;
        float f15;
        float f16;
        float f17;
        float f18;
        if (this.mLegend.isEnabled()) {
            Typeface typeface = this.mLegend.getTypeface();
            if (typeface != null) {
                this.mLegendLabelPaint.setTypeface(typeface);
            }
            this.mLegendLabelPaint.setTextSize(this.mLegend.getTextSize());
            this.mLegendLabelPaint.setColor(this.mLegend.getTextColor());
            float lineHeight = Utils.getLineHeight(this.mLegendLabelPaint, this.legendFontMetrics);
            float lineSpacing = Utils.getLineSpacing(this.mLegendLabelPaint, this.legendFontMetrics) + Utils.convertDpToPixel(this.mLegend.getYEntrySpace());
            float calcTextHeight = lineHeight - (Utils.calcTextHeight(this.mLegendLabelPaint, "ABC") / 2.0f);
            LegendEntry[] entries = this.mLegend.getEntries();
            float convertDpToPixel = Utils.convertDpToPixel(this.mLegend.getFormToTextSpace());
            float convertDpToPixel2 = Utils.convertDpToPixel(this.mLegend.getXEntrySpace());
            Legend.LegendOrientation orientation = this.mLegend.getOrientation();
            Legend.LegendHorizontalAlignment horizontalAlignment = this.mLegend.getHorizontalAlignment();
            Legend.LegendVerticalAlignment verticalAlignment = this.mLegend.getVerticalAlignment();
            Legend.LegendDirection direction = this.mLegend.getDirection();
            float convertDpToPixel3 = Utils.convertDpToPixel(this.mLegend.getFormSize());
            float convertDpToPixel4 = Utils.convertDpToPixel(this.mLegend.getStackSpace());
            float yOffset = this.mLegend.getYOffset();
            float xOffset = this.mLegend.getXOffset();
            switch (horizontalAlignment) {
                case LEFT:
                    f = convertDpToPixel4;
                    f2 = lineSpacing;
                    f3 = convertDpToPixel;
                    f4 = convertDpToPixel2;
                    if (orientation != Legend.LegendOrientation.VERTICAL) {
                        xOffset += this.mViewPortHandler.contentLeft();
                    }
                    if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                        xOffset += this.mLegend.mNeededWidth;
                    }
                    f5 = xOffset;
                    break;
                case RIGHT:
                    f = convertDpToPixel4;
                    f2 = lineSpacing;
                    f3 = convertDpToPixel;
                    f4 = convertDpToPixel2;
                    chartWidth = orientation == Legend.LegendOrientation.VERTICAL ? this.mViewPortHandler.getChartWidth() - xOffset : this.mViewPortHandler.contentRight() - xOffset;
                    if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                        xOffset = chartWidth - this.mLegend.mNeededWidth;
                        f5 = xOffset;
                        break;
                    }
                    f5 = chartWidth;
                    break;
                case CENTER:
                    if (orientation == Legend.LegendOrientation.VERTICAL) {
                        contentLeft = this.mViewPortHandler.getChartWidth() / 2.0f;
                        f = convertDpToPixel4;
                    } else {
                        f = convertDpToPixel4;
                        contentLeft = this.mViewPortHandler.contentLeft() + (this.mViewPortHandler.contentWidth() / 2.0f);
                    }
                    chartWidth = (direction == Legend.LegendDirection.LEFT_TO_RIGHT ? xOffset : -xOffset) + contentLeft;
                    if (orientation != Legend.LegendOrientation.VERTICAL) {
                        f2 = lineSpacing;
                        f3 = convertDpToPixel;
                        f4 = convertDpToPixel2;
                        f5 = chartWidth;
                        break;
                    } else {
                        f2 = lineSpacing;
                        double d2 = chartWidth;
                        if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                            f3 = convertDpToPixel;
                            f4 = convertDpToPixel2;
                            d = ((-this.mLegend.mNeededWidth) / 2.0d) + xOffset;
                        } else {
                            f3 = convertDpToPixel;
                            f4 = convertDpToPixel2;
                            d = (this.mLegend.mNeededWidth / 2.0d) - xOffset;
                        }
                        xOffset = (float) (d2 + d);
                        f5 = xOffset;
                        break;
                    }
                default:
                    f = convertDpToPixel4;
                    f2 = lineSpacing;
                    f3 = convertDpToPixel;
                    f4 = convertDpToPixel2;
                    f5 = 0.0f;
                    break;
            }
            switch (orientation) {
                case HORIZONTAL:
                    float f19 = f;
                    float f20 = f3;
                    List<FSize> calculatedLineSizes = this.mLegend.getCalculatedLineSizes();
                    List<FSize> calculatedLabelSizes = this.mLegend.getCalculatedLabelSizes();
                    List<Boolean> calculatedLabelBreakPoints = this.mLegend.getCalculatedLabelBreakPoints();
                    switch (verticalAlignment) {
                        case TOP:
                            break;
                        case BOTTOM:
                            yOffset = (this.mViewPortHandler.getChartHeight() - yOffset) - this.mLegend.mNeededHeight;
                            break;
                        case CENTER:
                            yOffset += (this.mViewPortHandler.getChartHeight() - this.mLegend.mNeededHeight) / 2.0f;
                            break;
                        default:
                            yOffset = 0.0f;
                            break;
                    }
                    int length = entries.length;
                    float f21 = yOffset;
                    List<FSize> list3 = calculatedLabelSizes;
                    float f22 = f5;
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < length) {
                        float f23 = f19;
                        LegendEntry legendEntry2 = entries[i3];
                        int i5 = length;
                        float f24 = f20;
                        boolean z = legendEntry2.form != Legend.LegendForm.NONE;
                        float convertDpToPixel5 = Float.isNaN(legendEntry2.formSize) ? convertDpToPixel3 : Utils.convertDpToPixel(legendEntry2.formSize);
                        if (i3 >= calculatedLabelBreakPoints.size() || !calculatedLabelBreakPoints.get(i3).booleanValue()) {
                            f6 = f21;
                        } else {
                            f6 = f21 + lineHeight + f2;
                            f22 = f5;
                        }
                        if (f22 == f5 && horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER && i4 < calculatedLineSizes.size()) {
                            f22 += (direction == Legend.LegendDirection.RIGHT_TO_LEFT ? calculatedLineSizes.get(i4).width : -calculatedLineSizes.get(i4).width) / 2.0f;
                            i4++;
                        }
                        int i6 = i4;
                        boolean z2 = legendEntry2.label == null;
                        if (z) {
                            if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                f22 -= convertDpToPixel5;
                            }
                            i = i5;
                            f7 = f5;
                            i2 = i3;
                            list = calculatedLabelBreakPoints;
                            f8 = calcTextHeight;
                            list2 = list3;
                            legendEntryArr = entries;
                            f9 = f24;
                            drawForm(canvas, f22, f6 + calcTextHeight, legendEntry2, this.mLegend);
                            if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                f22 += convertDpToPixel5;
                            }
                        } else {
                            list = calculatedLabelBreakPoints;
                            f7 = f5;
                            f8 = calcTextHeight;
                            legendEntryArr = entries;
                            list2 = list3;
                            i = i5;
                            f9 = f24;
                            i2 = i3;
                        }
                        if (z2) {
                            f10 = f4;
                            if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                f11 = f23;
                                f12 = -f11;
                            } else {
                                f11 = f23;
                                f12 = f11;
                            }
                            f22 += f12;
                        } else {
                            if (z) {
                                f22 += direction == Legend.LegendDirection.RIGHT_TO_LEFT ? -f9 : f9;
                            }
                            if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                f22 -= list2.get(i2).width;
                            }
                            float f25 = f22;
                            drawLabel(canvas, f25, f6 + lineHeight, legendEntry2.label);
                            if (direction == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                f25 += list2.get(i2).width;
                            }
                            if (direction == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                f10 = f4;
                                f13 = -f10;
                            } else {
                                f10 = f4;
                                f13 = f10;
                            }
                            f22 = f25 + f13;
                            f11 = f23;
                        }
                        i3 = i2 + 1;
                        f19 = f11;
                        f4 = f10;
                        list3 = list2;
                        f20 = f9;
                        f21 = f6;
                        i4 = i6;
                        length = i;
                        calculatedLabelBreakPoints = list;
                        f5 = f7;
                        calcTextHeight = f8;
                        entries = legendEntryArr;
                    }
                    return;
                case VERTICAL:
                    switch (verticalAlignment) {
                        case TOP:
                            contentTop = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER ? 0.0f : this.mViewPortHandler.contentTop()) + yOffset;
                            break;
                        case BOTTOM:
                            contentTop = (horizontalAlignment == Legend.LegendHorizontalAlignment.CENTER ? this.mViewPortHandler.getChartHeight() : this.mViewPortHandler.contentBottom()) - (this.mLegend.mNeededHeight + yOffset);
                            break;
                        case CENTER:
                            contentTop = ((this.mViewPortHandler.getChartHeight() / 2.0f) - (this.mLegend.mNeededHeight / 2.0f)) + this.mLegend.getYOffset();
                            break;
                        default:
                            contentTop = 0.0f;
                            break;
                    }
                    float f26 = contentTop;
                    float f27 = 0.0f;
                    int i7 = 0;
                    boolean z3 = false;
                    while (i7 < entries.length) {
                        LegendEntry legendEntry3 = entries[i7];
                        boolean z4 = legendEntry3.form != Legend.LegendForm.NONE;
                        float convertDpToPixel6 = Float.isNaN(legendEntry3.formSize) ? convertDpToPixel3 : Utils.convertDpToPixel(legendEntry3.formSize);
                        if (z4) {
                            f15 = direction == Legend.LegendDirection.LEFT_TO_RIGHT ? f5 + f27 : f5 - (convertDpToPixel6 - f27);
                            f14 = f;
                            legendDirection = direction;
                            drawForm(canvas, f15, f26 + calcTextHeight, legendEntry3, this.mLegend);
                            if (legendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                f15 += convertDpToPixel6;
                            }
                            legendEntry = legendEntry3;
                        } else {
                            legendDirection = direction;
                            f14 = f;
                            legendEntry = legendEntry3;
                            f15 = f5;
                        }
                        if (legendEntry.label != null) {
                            if (!z4 || z3) {
                                f16 = f3;
                                f17 = z3 ? f5 : f15;
                            } else {
                                if (legendDirection == Legend.LegendDirection.LEFT_TO_RIGHT) {
                                    f18 = f3;
                                    f16 = f18;
                                } else {
                                    f16 = f3;
                                    f18 = -f16;
                                }
                                f17 = f15 + f18;
                            }
                            if (legendDirection == Legend.LegendDirection.RIGHT_TO_LEFT) {
                                f17 -= Utils.calcTextWidth(this.mLegendLabelPaint, legendEntry.label);
                            }
                            if (z3) {
                                f26 += lineHeight + f2;
                                drawLabel(canvas, f17, f26 + lineHeight, legendEntry.label);
                            } else {
                                drawLabel(canvas, f17, f26 + lineHeight, legendEntry.label);
                            }
                            f26 += lineHeight + f2;
                            f27 = 0.0f;
                        } else {
                            f16 = f3;
                            f27 += convertDpToPixel6 + f14;
                            z3 = true;
                        }
                        i7++;
                        f3 = f16;
                        f = f14;
                        direction = legendDirection;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
