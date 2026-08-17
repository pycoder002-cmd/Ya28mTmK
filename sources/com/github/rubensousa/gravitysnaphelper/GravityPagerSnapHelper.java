package com.github.rubensousa.gravitysnaphelper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

/* loaded from: classes.dex */
public class GravityPagerSnapHelper extends PagerSnapHelper {
    private GravityDelegate delegate;

    public GravityPagerSnapHelper(int i) {
        this(i, false, null);
    }

    public GravityPagerSnapHelper(int i, boolean z) {
        this(i, z, null);
    }

    public GravityPagerSnapHelper(int i, boolean z, GravitySnapHelper.SnapListener snapListener) {
        this.delegate = new GravityDelegate(i, z, snapListener);
    }

    @Override // android.support.v7.widget.SnapHelper
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        if (recyclerView != null && (!(recyclerView.getLayoutManager() instanceof LinearLayoutManager) || (recyclerView.getLayoutManager() instanceof GridLayoutManager))) {
            throw new IllegalStateException("GravityPagerSnapHelper needs a RecyclerView with a LinearLayoutManager");
        }
        this.delegate.attachToRecyclerView(recyclerView);
        super.attachToRecyclerView(recyclerView);
    }

    @Override // android.support.v7.widget.PagerSnapHelper, android.support.v7.widget.SnapHelper
    @Nullable
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        return this.delegate.calculateDistanceToFinalSnap(layoutManager, view);
    }

    public void enableLastItemSnap(boolean z) {
        this.delegate.enableLastItemSnap(z);
    }

    @Override // android.support.v7.widget.PagerSnapHelper, android.support.v7.widget.SnapHelper
    @Nullable
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return this.delegate.findSnapView(layoutManager);
    }
}
