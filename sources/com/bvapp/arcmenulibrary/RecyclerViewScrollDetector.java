package com.bvapp.arcmenulibrary;

import android.support.v7.widget.RecyclerView;

/* loaded from: classes.dex */
abstract class RecyclerViewScrollDetector extends RecyclerView.OnScrollListener {
    private int mScrollThreshold;

    abstract void onScrollDown();

    abstract void onScrollUp();

    @Override // android.support.v7.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        if (Math.abs(i2) > this.mScrollThreshold) {
            if (i2 > 0) {
                onScrollUp();
            } else {
                onScrollDown();
            }
        }
    }

    public void setScrollThreshold(int i) {
        this.mScrollThreshold = i;
    }
}
