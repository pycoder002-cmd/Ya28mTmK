package com.github.mikephil.charting.buffer;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

/* loaded from: classes.dex */
public class BarBuffer extends AbstractBuffer<IBarDataSet> {
    protected float mBarWidth;
    protected boolean mContainsStacks;
    protected int mDataSetCount;
    protected int mDataSetIndex;
    protected boolean mInverted;

    public BarBuffer(int i, int i2, boolean z) {
        super(i);
        this.mDataSetIndex = 0;
        this.mDataSetCount = 1;
        this.mContainsStacks = false;
        this.mInverted = false;
        this.mBarWidth = 1.0f;
        this.mDataSetCount = i2;
        this.mContainsStacks = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addBar(float f, float f2, float f3, float f4) {
        float[] fArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        fArr[i] = f;
        float[] fArr2 = this.buffer;
        int i2 = this.index;
        this.index = i2 + 1;
        fArr2[i2] = f2;
        float[] fArr3 = this.buffer;
        int i3 = this.index;
        this.index = i3 + 1;
        fArr3[i3] = f3;
        float[] fArr4 = this.buffer;
        int i4 = this.index;
        this.index = i4 + 1;
        fArr4[i4] = f4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.github.mikephil.charting.buffer.AbstractBuffer
    public void feed(IBarDataSet iBarDataSet) {
        float f;
        float abs;
        float f2;
        float entryCount = iBarDataSet.getEntryCount() * this.phaseX;
        float f3 = this.mBarWidth / 2.0f;
        for (int i = 0; i < entryCount; i++) {
            BarEntry barEntry = (BarEntry) iBarDataSet.getEntryForIndex(i);
            if (barEntry != null) {
                float x = barEntry.getX();
                float y = barEntry.getY();
                float[] yVals = barEntry.getYVals();
                if (!this.mContainsStacks || yVals == null) {
                    float f4 = x - f3;
                    float f5 = x + f3;
                    if (this.mInverted) {
                        float f6 = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                        float f7 = y;
                        y = f6;
                        f = f7;
                    } else {
                        f = y >= 0.0f ? y : 0.0f;
                        if (y > 0.0f) {
                            y = 0.0f;
                        }
                    }
                    if (f > 0.0f) {
                        f *= this.phaseY;
                    } else {
                        y *= this.phaseY;
                    }
                    addBar(f4, f, f5, y);
                } else {
                    float f8 = -barEntry.getNegativeSum();
                    float f9 = 0.0f;
                    for (float f10 : yVals) {
                        if (f10 == 0.0f && (f9 == 0.0f || f8 == 0.0f)) {
                            abs = f10;
                        } else if (f10 >= 0.0f) {
                            abs = f10 + f9;
                            f10 = f9;
                            f9 = abs;
                        } else {
                            abs = Math.abs(f10) + f8;
                            float abs2 = Math.abs(f10) + f8;
                            f10 = f8;
                            f8 = abs2;
                        }
                        float f11 = x - f3;
                        float f12 = x + f3;
                        if (this.mInverted) {
                            f2 = f10 >= abs ? f10 : abs;
                            if (f10 > abs) {
                                f10 = abs;
                            }
                        } else {
                            float f13 = f10 >= abs ? f10 : abs;
                            if (f10 > abs) {
                                f10 = abs;
                            }
                            float f14 = f10;
                            f10 = f13;
                            f2 = f14;
                        }
                        addBar(f11, f10 * this.phaseY, f12, f2 * this.phaseY);
                    }
                }
            }
        }
        reset();
    }

    public void setBarWidth(float f) {
        this.mBarWidth = f;
    }

    public void setDataSet(int i) {
        this.mDataSetIndex = i;
    }

    public void setInverted(boolean z) {
        this.mInverted = z;
    }
}
