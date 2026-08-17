package com.bvapp.arcmenulibrary;

import android.support.annotation.NonNull;
import android.widget.AbsListView;

/* loaded from: classes.dex */
abstract class AbsListViewScrollDetector implements AbsListView.OnScrollListener {
    private int mLastScrollY;
    private AbsListView mListView;
    private int mPreviousFirstVisibleItem;
    private int mScrollThreshold;

    private int getTopItemScrollY() {
        if (this.mListView == null || this.mListView.getChildAt(0) == null) {
            return 0;
        }
        return this.mListView.getChildAt(0).getTop();
    }

    private boolean isSameRow(int i) {
        return i == this.mPreviousFirstVisibleItem;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (i3 == 0) {
            return;
        }
        if (!isSameRow(i)) {
            if (i > this.mPreviousFirstVisibleItem) {
                onScrollUp();
            } else {
                onScrollDown();
            }
            this.mLastScrollY = getTopItemScrollY();
            this.mPreviousFirstVisibleItem = i;
            return;
        }
        int topItemScrollY = getTopItemScrollY();
        if (Math.abs(this.mLastScrollY - topItemScrollY) > this.mScrollThreshold) {
            if (this.mLastScrollY > topItemScrollY) {
                onScrollUp();
            } else {
                onScrollDown();
            }
        }
        this.mLastScrollY = topItemScrollY;
    }

    abstract void onScrollDown();

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    abstract void onScrollUp();

    public void setListView(@NonNull AbsListView absListView) {
        this.mListView = absListView;
    }

    public void setScrollThreshold(int i) {
        this.mScrollThreshold = i;
    }
}
