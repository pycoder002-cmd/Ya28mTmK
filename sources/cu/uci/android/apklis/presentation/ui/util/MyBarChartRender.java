package cu.uci.android.apklis.presentation.ui.util;

import android.graphics.Canvas;
import android.graphics.RectF;
import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.buffer.BarBuffer;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.renderer.HorizontalBarChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;

/* loaded from: classes.dex */
public class MyBarChartRender extends HorizontalBarChartRenderer {
    private RectF mBarShadowRectBuffer;

    public MyBarChartRender(BarDataProvider barDataProvider, ChartAnimator chartAnimator, ViewPortHandler viewPortHandler) {
        super(barDataProvider, chartAnimator, viewPortHandler);
        this.mBarShadowRectBuffer = new RectF();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.renderer.HorizontalBarChartRenderer, com.github.mikephil.charting.renderer.BarChartRenderer
    protected void drawDataSet(Canvas canvas, IBarDataSet iBarDataSet, int i) {
        Transformer transformer = this.mChart.getTransformer(iBarDataSet.getAxisDependency());
        this.mBarBorderPaint.setColor(iBarDataSet.getBarBorderColor());
        this.mBarBorderPaint.setStrokeWidth(Utils.convertDpToPixel(iBarDataSet.getBarBorderWidth()));
        int i2 = (iBarDataSet.getBarBorderWidth() > 0.0f ? 1 : (iBarDataSet.getBarBorderWidth() == 0.0f ? 0 : -1));
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
        BarBuffer barBuffer = this.mBarBuffers[i];
        barBuffer.setPhases(phaseX, phaseY);
        barBuffer.setDataSet(i);
        barBuffer.setInverted(this.mChart.isInverted(iBarDataSet.getAxisDependency()));
        barBuffer.setBarWidth(this.mChart.getBarData().getBarWidth());
        barBuffer.feed(iBarDataSet);
        transformer.pointValuesToPixel(barBuffer.buffer);
        boolean z = iBarDataSet.getColors().size() == 1;
        if (z) {
            this.mRenderPaint.setColor(iBarDataSet.getColor());
        }
        for (int i4 = 0; i4 < barBuffer.size(); i4 += 4) {
            int i5 = i4 + 3;
            if (!this.mViewPortHandler.isInBoundsTop(barBuffer.buffer[i5])) {
                return;
            }
            int i6 = i4 + 1;
            if (this.mViewPortHandler.isInBoundsBottom(barBuffer.buffer[i6])) {
                if (!z) {
                    this.mRenderPaint.setColor(iBarDataSet.getColor(i4 / 4));
                }
                int i7 = i4 + 2;
                if (barBuffer.buffer[i7] - barBuffer.buffer[i4] != 0.0f) {
                    canvas.drawRoundRect(new RectF(barBuffer.buffer[i4], barBuffer.buffer[i6], barBuffer.buffer[i7] / 2.0f, barBuffer.buffer[i5]), 0.0f, 0.0f, this.mRenderPaint);
                    canvas.drawRoundRect(new RectF(barBuffer.buffer[i4], barBuffer.buffer[i6], barBuffer.buffer[i7], barBuffer.buffer[i5]), 20.0f, 20.0f, this.mRenderPaint);
                } else {
                    canvas.drawRoundRect(new RectF(barBuffer.buffer[i4], barBuffer.buffer[i6], barBuffer.buffer[i7] + 2.2f, barBuffer.buffer[i5]), 0.0f, 0.0f, this.mRenderPaint);
                }
            }
        }
    }

    @Override // com.github.mikephil.charting.renderer.BarChartRenderer, com.github.mikephil.charting.renderer.DataRenderer
    public void drawHighlighted(Canvas canvas, Highlight[] highlightArr) {
        super.drawHighlighted(canvas, highlightArr);
    }
}
