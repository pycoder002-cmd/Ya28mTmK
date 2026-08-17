package com.github.rubensousa.gravitysnaphelper;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class GravityDelegate {
    private int gravity;
    private OrientationHelper horizontalHelper;
    private boolean isRtlHorizontal;
    private GravitySnapHelper.SnapListener listener;
    private RecyclerView.OnScrollListener mScrollListener;
    private boolean snapLastItem;
    private boolean snapping;
    private OrientationHelper verticalHelper;

    public GravityDelegate(int i) {
        this(i, false, null);
    }

    public GravityDelegate(int i, GravitySnapHelper.SnapListener snapListener) {
        this(i, false, snapListener);
    }

    public GravityDelegate(int i, boolean z, GravitySnapHelper.SnapListener snapListener) {
        this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: com.github.rubensousa.gravitysnaphelper.GravityDelegate.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (i2 == 2) {
                    GravityDelegate.this.snapping = false;
                }
                if (i2 == 0 && GravityDelegate.this.snapping && GravityDelegate.this.listener != null) {
                    int snappedPosition = GravityDelegate.this.getSnappedPosition(recyclerView);
                    if (snappedPosition != -1) {
                        GravityDelegate.this.listener.onSnap(snappedPosition);
                    }
                    GravityDelegate.this.snapping = false;
                }
            }
        };
        if (i != 8388611 && i != 8388613 && i != 80 && i != 48) {
            throw new IllegalArgumentException("Invalid gravity value. Use START | END | BOTTOM | TOP constants");
        }
        this.snapLastItem = z;
        this.gravity = i;
        this.listener = snapListener;
    }

    private int distanceToEnd(View view, OrientationHelper orientationHelper, boolean z) {
        return (!this.isRtlHorizontal || z) ? orientationHelper.getDecoratedEnd(view) - orientationHelper.getEndAfterPadding() : distanceToStart(view, orientationHelper, true);
    }

    private int distanceToStart(View view, OrientationHelper orientationHelper, boolean z) {
        return (!this.isRtlHorizontal || z) ? orientationHelper.getDecoratedStart(view) - orientationHelper.getStartAfterPadding() : distanceToEnd(view, orientationHelper, true);
    }

    private View findEndView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return null;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        int spanCount = layoutManager instanceof GridLayoutManager ? (((GridLayoutManager) layoutManager).getSpanCount() - 1) + 1 : 1;
        if (findLastVisibleItemPosition == -1) {
            return null;
        }
        View findViewByPosition = layoutManager.findViewByPosition(findLastVisibleItemPosition);
        float decoratedEnd = this.isRtlHorizontal ? orientationHelper.getDecoratedEnd(findViewByPosition) / orientationHelper.getDecoratedMeasurement(findViewByPosition) : (orientationHelper.getTotalSpace() - orientationHelper.getDecoratedStart(findViewByPosition)) / orientationHelper.getDecoratedMeasurement(findViewByPosition);
        boolean z = linearLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
        if (decoratedEnd > 0.5f && !z) {
            return findViewByPosition;
        }
        if (this.snapLastItem && z) {
            return findViewByPosition;
        }
        if (z) {
            return null;
        }
        return layoutManager.findViewByPosition(findLastVisibleItemPosition - spanCount);
    }

    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return null;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int spanCount = layoutManager instanceof GridLayoutManager ? (((GridLayoutManager) layoutManager).getSpanCount() - 1) + 1 : 1;
        if (findFirstVisibleItemPosition == -1) {
            return null;
        }
        View findViewByPosition = layoutManager.findViewByPosition(findFirstVisibleItemPosition);
        float totalSpace = this.isRtlHorizontal ? (orientationHelper.getTotalSpace() - orientationHelper.getDecoratedStart(findViewByPosition)) / orientationHelper.getDecoratedMeasurement(findViewByPosition) : orientationHelper.getDecoratedEnd(findViewByPosition) / orientationHelper.getDecoratedMeasurement(findViewByPosition);
        boolean z = linearLayoutManager.findLastCompletelyVisibleItemPosition() == layoutManager.getItemCount() - 1;
        if (totalSpace > 0.5f && !z) {
            return findViewByPosition;
        }
        if (this.snapLastItem && z) {
            return findViewByPosition;
        }
        if (z) {
            return null;
        }
        return layoutManager.findViewByPosition(findFirstVisibleItemPosition + spanCount);
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (this.horizontalHelper == null) {
            this.horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.horizontalHelper;
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        if (this.verticalHelper == null) {
            this.verticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.verticalHelper;
    }

    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) {
        if (recyclerView != null) {
            recyclerView.setOnFlingListener(null);
            if ((this.gravity == 8388611 || this.gravity == 8388613) && Build.VERSION.SDK_INT >= 17) {
                this.isRtlHorizontal = recyclerView.getContext().getResources().getConfiguration().getLayoutDirection() == 1;
            }
            if (this.listener != null) {
                recyclerView.addOnScrollListener(this.mScrollListener);
            }
        }
    }

    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        int[] iArr = new int[2];
        if (!layoutManager.canScrollHorizontally()) {
            iArr[0] = 0;
        } else if (this.gravity == 8388611) {
            iArr[0] = distanceToStart(view, getHorizontalHelper(layoutManager), false);
        } else {
            iArr[0] = distanceToEnd(view, getHorizontalHelper(layoutManager), false);
        }
        if (!layoutManager.canScrollVertically()) {
            iArr[1] = 0;
        } else if (this.gravity == 48) {
            iArr[1] = distanceToStart(view, getVerticalHelper(layoutManager), false);
        } else {
            iArr[1] = distanceToEnd(view, getVerticalHelper(layoutManager), false);
        }
        return iArr;
    }

    public void enableLastItemSnap(boolean z) {
        this.snapLastItem = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View findSnapView(android.support.v7.widget.RecyclerView.LayoutManager r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof android.support.v7.widget.LinearLayoutManager
            if (r0 == 0) goto L3d
            int r0 = r2.gravity
            r1 = 48
            if (r0 == r1) goto L34
            r1 = 80
            if (r0 == r1) goto L2b
            r1 = 8388611(0x800003, float:1.1754948E-38)
            if (r0 == r1) goto L22
            r1 = 8388613(0x800005, float:1.175495E-38)
            if (r0 == r1) goto L19
            goto L3d
        L19:
            android.support.v7.widget.OrientationHelper r0 = r2.getHorizontalHelper(r3)
            android.view.View r3 = r2.findEndView(r3, r0)
            goto L3e
        L22:
            android.support.v7.widget.OrientationHelper r0 = r2.getHorizontalHelper(r3)
            android.view.View r3 = r2.findStartView(r3, r0)
            goto L3e
        L2b:
            android.support.v7.widget.OrientationHelper r0 = r2.getVerticalHelper(r3)
            android.view.View r3 = r2.findEndView(r3, r0)
            goto L3e
        L34:
            android.support.v7.widget.OrientationHelper r0 = r2.getVerticalHelper(r3)
            android.view.View r3 = r2.findStartView(r3, r0)
            goto L3e
        L3d:
            r3 = 0
        L3e:
            if (r3 == 0) goto L42
            r0 = 1
            goto L43
        L42:
            r0 = 0
        L43:
            r2.snapping = r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.rubensousa.gravitysnaphelper.GravityDelegate.findSnapView(android.support.v7.widget.RecyclerView$LayoutManager):android.view.View");
    }

    int getSnappedPosition(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return -1;
        }
        if (this.gravity == 8388611 || this.gravity == 48) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        if (this.gravity == 8388613 || this.gravity == 80) {
            return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        return -1;
    }
}
