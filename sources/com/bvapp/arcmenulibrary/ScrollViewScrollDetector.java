package com.bvapp.arcmenulibrary;

import android.widget.ScrollView;
import com.bvapp.arcmenulibrary.widget.ObservableScrollView;

/* loaded from: classes.dex */
abstract class ScrollViewScrollDetector implements ObservableScrollView.OnScrollChangedListener {
    private int mLastScrollY;
    private int mScrollThreshold;

    @Override // com.bvapp.arcmenulibrary.widget.ObservableScrollView.OnScrollChangedListener
    public void onScrollChanged(ScrollView scrollView, int i, int i2, int i3, int i4) {
        if (Math.abs(i2 - this.mLastScrollY) > this.mScrollThreshold) {
            if (i2 > this.mLastScrollY) {
                onScrollUp();
            } else {
                onScrollDown();
            }
        }
        this.mLastScrollY = i2;
    }

    abstract void onScrollDown();

    abstract void onScrollUp();

    public void setScrollThreshold(int i) {
        this.mScrollThreshold = i;
    }
}
