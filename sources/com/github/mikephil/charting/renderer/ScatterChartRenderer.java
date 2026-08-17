package com.github.mikephil.charting.renderer;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.data.BaseEntry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.ScatterDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IScatterDataSet;
import com.github.mikephil.charting.renderer.scatter.IShapeRenderer;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import java.util.List;

/* loaded from: classes.dex */
public class ScatterChartRenderer extends LineScatterCandleRadarRenderer {
    protected ScatterDataProvider mChart;
    float[] mPixelBuffer;

    public ScatterChartRenderer(ScatterDataProvider scatterDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(chartAnimator, viewPortHandler);
        this.mPixelBuffer = new float[2];
        this.mChart = scatterDataProvider;
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawData(Canvas canvas) {
        for (T t : this.mChart.getScatterData().getDataSets()) {
            if (t.isVisible()) {
                drawDataSet(canvas, t);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [com.github.mikephil.charting.data.Entry] */
    protected void drawDataSet(Canvas canvas, IScatterDataSet iScatterDataSet) {
        ViewPortHandler viewPortHandler = this.mViewPortHandler;
        Transformer transformer = this.mChart.getTransformer(iScatterDataSet.getAxisDependency());
        float phaseY = this.mAnimator.getPhaseY();
        IShapeRenderer shapeRenderer = iScatterDataSet.getShapeRenderer();
        if (shapeRenderer == null) {
            Log.i("MISSING", "There's no IShapeRenderer specified for ScatterDataSet");
            return;
        }
        int min = (int) Math.min(Math.ceil(iScatterDataSet.getEntryCount() * this.mAnimator.getPhaseX()), iScatterDataSet.getEntryCount());
        for (int i = 0; i < min; i++) {
            ?? entryForIndex = iScatterDataSet.getEntryForIndex(i);
            this.mPixelBuffer[0] = entryForIndex.getX();
            this.mPixelBuffer[1] = entryForIndex.getY() * phaseY;
            transformer.pointValuesToPixel(this.mPixelBuffer);
            if (!viewPortHandler.isInBoundsRight(this.mPixelBuffer[0])) {
                return;
            }
            if (viewPortHandler.isInBoundsLeft(this.mPixelBuffer[0]) && viewPortHandler.isInBoundsY(this.mPixelBuffer[1])) {
                this.mRenderPaint.setColor(iScatterDataSet.getColor(i / 2));
                shapeRenderer.renderShape(canvas, iScatterDataSet, this.mViewPortHandler, this.mPixelBuffer[0], this.mPixelBuffer[1], this.mRenderPaint);
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawExtras(Canvas canvas) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        ScatterData scatterData = this.mChart.getScatterData();
        for (Highlight highlight : highlightArr) {
            IScatterDataSet iScatterDataSet = (IScatterDataSet) scatterData.getDataSetByIndex(highlight.getDataSetIndex());
            if (iScatterDataSet != null && iScatterDataSet.isHighlightEnabled()) {
                ?? entryForXValue = iScatterDataSet.getEntryForXValue(highlight.getX(), highlight.getY());
                if (isInBoundsX(entryForXValue, iScatterDataSet)) {
                    MPPointD pixelForValues = this.mChart.getTransformer(iScatterDataSet.getAxisDependency()).getPixelForValues(entryForXValue.getX(), entryForXValue.getY() * this.mAnimator.getPhaseY());
                    highlight.setDraw((float) pixelForValues.x, (float) pixelForValues.y);
                    drawHighlightLines(canvas, (float) pixelForValues.x, (float) pixelForValues.y, iScatterDataSet);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.github.mikephil.charting.data.Entry] */
    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void drawValues(Canvas canvas) {
        int i;
        MPPointF mPPointF;
        BaseEntry baseEntry;
        ScatterChartRenderer scatterChartRenderer = this;
        if (scatterChartRenderer.isDrawingValuesAllowed(scatterChartRenderer.mChart)) {
            List<T> dataSets = scatterChartRenderer.mChart.getScatterData().getDataSets();
            int i2 = 0;
            while (i2 < scatterChartRenderer.mChart.getScatterData().getDataSetCount()) {
                IScatterDataSet iScatterDataSet = (IScatterDataSet) dataSets.get(i2);
                if (scatterChartRenderer.shouldDrawValues(iScatterDataSet)) {
                    scatterChartRenderer.applyValueTextStyle(iScatterDataSet);
                    scatterChartRenderer.mXBounds.set(scatterChartRenderer.mChart, iScatterDataSet);
                    float[] generateTransformedValuesScatter = scatterChartRenderer.mChart.getTransformer(iScatterDataSet.getAxisDependency()).generateTransformedValuesScatter(iScatterDataSet, scatterChartRenderer.mAnimator.getPhaseX(), scatterChartRenderer.mAnimator.getPhaseY(), scatterChartRenderer.mXBounds.min, scatterChartRenderer.mXBounds.max);
                    float convertDpToPixel = Utils.convertDpToPixel(iScatterDataSet.getScatterShapeSize());
                    MPPointF mPPointF2 = MPPointF.getInstance(iScatterDataSet.getIconsOffset());
                    mPPointF2.x = Utils.convertDpToPixel(mPPointF2.x);
                    mPPointF2.y = Utils.convertDpToPixel(mPPointF2.y);
                    int i3 = 0;
                    while (i3 < generateTransformedValuesScatter.length && scatterChartRenderer.mViewPortHandler.isInBoundsRight(generateTransformedValuesScatter[i3])) {
                        if (scatterChartRenderer.mViewPortHandler.isInBoundsLeft(generateTransformedValuesScatter[i3])) {
                            int i4 = i3 + 1;
                            if (scatterChartRenderer.mViewPortHandler.isInBoundsY(generateTransformedValuesScatter[i4])) {
                                int i5 = i3 / 2;
                                ?? entryForIndex = iScatterDataSet.getEntryForIndex(scatterChartRenderer.mXBounds.min + i5);
                                if (iScatterDataSet.isDrawValuesEnabled()) {
                                    IValueFormatter valueFormatter = iScatterDataSet.getValueFormatter();
                                    float y = entryForIndex.getY();
                                    float f = generateTransformedValuesScatter[i3];
                                    float f2 = generateTransformedValuesScatter[i4] - convertDpToPixel;
                                    int valueTextColor = iScatterDataSet.getValueTextColor(i5 + scatterChartRenderer.mXBounds.min);
                                    baseEntry = entryForIndex;
                                    i = i3;
                                    mPPointF = mPPointF2;
                                    scatterChartRenderer.drawValue(canvas, valueFormatter, y, entryForIndex, i2, f, f2, valueTextColor);
                                } else {
                                    baseEntry = entryForIndex;
                                    i = i3;
                                    mPPointF = mPPointF2;
                                }
                                if (baseEntry.getIcon() != null && iScatterDataSet.isDrawIconsEnabled()) {
                                    Drawable icon = baseEntry.getIcon();
                                    Utils.drawImage(canvas, icon, (int) (generateTransformedValuesScatter[i] + mPPointF.x), (int) (generateTransformedValuesScatter[i4] + mPPointF.y), icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
                                }
                                i3 = i + 2;
                                mPPointF2 = mPPointF;
                                scatterChartRenderer = this;
                            }
                        }
                        i = i3;
                        mPPointF = mPPointF2;
                        i3 = i + 2;
                        mPPointF2 = mPPointF;
                        scatterChartRenderer = this;
                    }
                    MPPointF.recycleInstance(mPPointF2);
                }
                i2++;
                scatterChartRenderer = this;
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.DataRenderer
    public void initBuffers() {
    }
}
