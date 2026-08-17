package com.michaelflisar.dragselectrecyclerview;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

/* loaded from: classes.dex */
public class DragSelectTouchListener implements RecyclerView.OnItemTouchListener {
    private static final String TAG = "DSTL";
    private int mBottomBoundFrom;
    private int mBottomBoundTo;
    private int mEnd;
    private boolean mInBottomSpot;
    private boolean mInTopSpot;
    private boolean mIsActive;
    private int mLastEnd;
    private int mLastStart;
    private float mLastX;
    private float mLastY;
    private RecyclerView mRecyclerView;
    private int mScrollDistance;
    private float mScrollSpeedFactor;
    private ScrollerCompat mScroller;
    private OnDragSelectListener mSelectListener;
    private int mStart;
    private int mTopBoundFrom;
    private int mTopBoundTo;
    private Runnable mScrollRunnable = new Runnable() { // from class: com.michaelflisar.dragselectrecyclerview.DragSelectTouchListener.1
        @Override // java.lang.Runnable
        public void run() {
            if (DragSelectTouchListener.this.mScroller == null || !DragSelectTouchListener.this.mScroller.computeScrollOffset()) {
                return;
            }
            DragSelectTouchListener.this.scrollBy(DragSelectTouchListener.this.mScrollDistance);
            ViewCompat.postOnAnimation(DragSelectTouchListener.this.mRecyclerView, DragSelectTouchListener.this.mScrollRunnable);
        }
    };
    private int mMaxScrollDistance = 16;
    private int mAutoScrollDistance = (int) (Resources.getSystem().getDisplayMetrics().density * 56.0f);
    private int mTouchRegionTopOffset = 0;
    private int mTouchRegionBottomOffset = 0;
    private boolean mScrollAboveTopRegion = true;
    private boolean mScrollBelowTopRegion = true;
    private boolean mDebug = false;

    /* loaded from: classes.dex */
    public interface OnAdvancedDragSelectListener extends OnDragSelectListener {
        void onSelectionFinished(int i);

        void onSelectionStarted(int i);
    }

    /* loaded from: classes.dex */
    public interface OnDragSelectListener {
        void onSelectChange(int i, int i2, boolean z);
    }

    public DragSelectTouchListener() {
        reset();
    }

    private void initScroller(Context context) {
        if (this.mScroller == null) {
            this.mScroller = ScrollerCompat.create(context, new LinearInterpolator());
        }
    }

    private void notifySelectRangeChange() {
        if (this.mSelectListener == null || this.mStart == -1 || this.mEnd == -1) {
            return;
        }
        int min = Math.min(this.mStart, this.mEnd);
        int max = Math.max(this.mStart, this.mEnd);
        if (this.mLastStart != -1 && this.mLastEnd != -1) {
            if (min > this.mLastStart) {
                this.mSelectListener.onSelectChange(this.mLastStart, min - 1, false);
            } else if (min < this.mLastStart) {
                this.mSelectListener.onSelectChange(min, this.mLastStart - 1, true);
            }
            if (max > this.mLastEnd) {
                this.mSelectListener.onSelectChange(this.mLastEnd + 1, max, true);
            } else if (max < this.mLastEnd) {
                this.mSelectListener.onSelectChange(max + 1, this.mLastEnd, false);
            }
        } else if (max - min == 1) {
            this.mSelectListener.onSelectChange(min, min, true);
        } else {
            this.mSelectListener.onSelectChange(min, max, true);
        }
        this.mLastStart = min;
        this.mLastEnd = max;
    }

    private void processAutoScroll(MotionEvent motionEvent) {
        int y = (int) motionEvent.getY();
        if (this.mDebug) {
            Log.d(TAG, "y = " + y + " | rv.height = " + this.mRecyclerView.getHeight() + " | mTopBoundFrom => mTopBoundTo = " + this.mTopBoundFrom + " => " + this.mTopBoundTo + " | mBottomBoundFrom => mBottomBoundTo = " + this.mBottomBoundFrom + " => " + this.mBottomBoundTo + " | mTouchRegionTopOffset = " + this.mTouchRegionTopOffset + " | mTouchRegionBottomOffset = " + this.mTouchRegionBottomOffset);
        }
        if (y >= this.mTopBoundFrom && y <= this.mTopBoundTo) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            this.mScrollSpeedFactor = ((this.mTopBoundTo - this.mTopBoundFrom) - (y - this.mTopBoundFrom)) / (this.mTopBoundTo - this.mTopBoundFrom);
            this.mScrollDistance = (int) (this.mMaxScrollDistance * this.mScrollSpeedFactor * (-1.0f));
            if (this.mDebug) {
                Log.d(TAG, "SCROLL - mScrollSpeedFactor=" + this.mScrollSpeedFactor + " | mScrollDistance=" + this.mScrollDistance);
            }
            if (this.mInTopSpot) {
                return;
            }
            this.mInTopSpot = true;
            startAutoScroll();
            return;
        }
        if (this.mScrollAboveTopRegion && y < this.mTopBoundFrom) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            this.mScrollDistance = this.mMaxScrollDistance * (-1);
            if (this.mInTopSpot) {
                return;
            }
            this.mInTopSpot = true;
            startAutoScroll();
            return;
        }
        if (y < this.mBottomBoundFrom || y > this.mBottomBoundTo) {
            if (!this.mScrollBelowTopRegion || y <= this.mBottomBoundTo) {
                this.mInBottomSpot = false;
                this.mInTopSpot = false;
                this.mLastX = Float.MIN_VALUE;
                this.mLastY = Float.MIN_VALUE;
                stopAutoScroll();
                return;
            }
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            this.mScrollDistance = this.mMaxScrollDistance;
            if (this.mInTopSpot) {
                return;
            }
            this.mInTopSpot = true;
            startAutoScroll();
            return;
        }
        this.mLastX = motionEvent.getX();
        this.mLastY = motionEvent.getY();
        this.mScrollSpeedFactor = (y - this.mBottomBoundFrom) / (this.mBottomBoundTo - this.mBottomBoundFrom);
        this.mScrollDistance = (int) (this.mMaxScrollDistance * this.mScrollSpeedFactor);
        if (this.mDebug) {
            Log.d(TAG, "SCROLL - mScrollSpeedFactor=" + this.mScrollSpeedFactor + " | mScrollDistance=" + this.mScrollDistance);
        }
        if (this.mInBottomSpot) {
            return;
        }
        this.mInBottomSpot = true;
        startAutoScroll();
    }

    private void reset() {
        setIsActive(false);
        if (this.mSelectListener != null && (this.mSelectListener instanceof OnAdvancedDragSelectListener)) {
            ((OnAdvancedDragSelectListener) this.mSelectListener).onSelectionFinished(this.mEnd);
        }
        this.mStart = -1;
        this.mEnd = -1;
        this.mLastStart = -1;
        this.mLastEnd = -1;
        this.mInTopSpot = false;
        this.mInBottomSpot = false;
        this.mLastX = Float.MIN_VALUE;
        this.mLastY = Float.MIN_VALUE;
        stopAutoScroll();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scrollBy(int i) {
        this.mRecyclerView.scrollBy(0, i > 0 ? Math.min(i, this.mMaxScrollDistance) : Math.max(i, -this.mMaxScrollDistance));
        if (this.mLastX == Float.MIN_VALUE || this.mLastY == Float.MIN_VALUE) {
            return;
        }
        updateSelectedRange(this.mRecyclerView, this.mLastX, this.mLastY);
    }

    private void updateSelectedRange(RecyclerView recyclerView, float f, float f2) {
        int childAdapterPosition;
        View findChildViewUnder = recyclerView.findChildViewUnder(f, f2);
        if (findChildViewUnder == null || (childAdapterPosition = recyclerView.getChildAdapterPosition(findChildViewUnder)) == -1 || this.mEnd == childAdapterPosition) {
            return;
        }
        this.mEnd = childAdapterPosition;
        notifySelectRangeChange();
    }

    private void updateSelectedRange(RecyclerView recyclerView, MotionEvent motionEvent) {
        updateSelectedRange(recyclerView, motionEvent.getX(), motionEvent.getY());
    }

    @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (!this.mIsActive || recyclerView.getAdapter().getItemCount() == 0) {
            return false;
        }
        int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
        if (actionMasked == 0 || actionMasked == 5) {
            reset();
        }
        this.mRecyclerView = recyclerView;
        int height = recyclerView.getHeight();
        this.mTopBoundFrom = this.mTouchRegionTopOffset + 0;
        this.mTopBoundTo = 0 + this.mTouchRegionTopOffset + this.mAutoScrollDistance;
        this.mBottomBoundFrom = (this.mTouchRegionBottomOffset + height) - this.mAutoScrollDistance;
        this.mBottomBoundTo = height + this.mTouchRegionBottomOffset;
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean z) {
    }

    @Override // android.support.v7.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        if (this.mIsActive) {
            int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
            if (actionMasked != 6) {
                switch (actionMasked) {
                    case 1:
                    case 3:
                        break;
                    case 2:
                        if (!this.mInTopSpot && !this.mInBottomSpot) {
                            updateSelectedRange(recyclerView, motionEvent);
                        }
                        processAutoScroll(motionEvent);
                        return;
                    default:
                        return;
                }
            }
            reset();
        }
    }

    public void setIsActive(boolean z) {
        this.mIsActive = z;
    }

    public void startAutoScroll() {
        if (this.mRecyclerView == null) {
            return;
        }
        initScroller(this.mRecyclerView.getContext());
        if (this.mScroller.isFinished()) {
            this.mRecyclerView.removeCallbacks(this.mScrollRunnable);
            this.mScroller.startScroll(0, this.mScroller.getCurrY(), 0, 5000, 100000);
            ViewCompat.postOnAnimation(this.mRecyclerView, this.mScrollRunnable);
        }
    }

    public void startDragSelection(int i) {
        setIsActive(true);
        this.mStart = i;
        this.mEnd = i;
        this.mLastStart = i;
        this.mLastEnd = i;
        if (this.mSelectListener == null || !(this.mSelectListener instanceof OnAdvancedDragSelectListener)) {
            return;
        }
        ((OnAdvancedDragSelectListener) this.mSelectListener).onSelectionStarted(i);
    }

    public void stopAutoScroll() {
        if (this.mScroller == null || this.mScroller.isFinished()) {
            return;
        }
        this.mRecyclerView.removeCallbacks(this.mScrollRunnable);
        this.mScroller.abortAnimation();
    }

    public DragSelectTouchListener withBottomOffset(int i) {
        this.mTouchRegionBottomOffset = i;
        return this;
    }

    public DragSelectTouchListener withDebug(boolean z) {
        this.mDebug = z;
        return this;
    }

    public DragSelectTouchListener withMaxScrollDistance(int i) {
        this.mMaxScrollDistance = i;
        return this;
    }

    public DragSelectTouchListener withScrollAboveTopRegion(boolean z) {
        this.mScrollAboveTopRegion = z;
        return this;
    }

    public DragSelectTouchListener withScrollBelowTopRegion(boolean z) {
        this.mScrollBelowTopRegion = z;
        return this;
    }

    public DragSelectTouchListener withSelectListener(OnDragSelectListener onDragSelectListener) {
        this.mSelectListener = onDragSelectListener;
        return this;
    }

    public DragSelectTouchListener withTopOffset(int i) {
        this.mTouchRegionTopOffset = i;
        return this;
    }

    public DragSelectTouchListener withTouchRegion(int i) {
        this.mAutoScrollDistance = i;
        return this;
    }
}
