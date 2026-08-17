package com.github.rubensousa.gravitysnaphelper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* loaded from: classes.dex */
public class GravitySnapHelper extends LinearSnapHelper {
    private GravityDelegate delegate;

    /* loaded from: classes.dex */
    public interface SnapListener {
        void onSnap(int i);
    }

    public GravitySnapHelper(int i) {
        this(i, false, null);
    }

    public GravitySnapHelper(int i, boolean z) {
        this(i, z, null);
    }

    public GravitySnapHelper(int i, boolean z, SnapListener snapListener) {
        this.delegate = new GravityDelegate(i, z, snapListener);
    }

    @Override // android.support.v7.widget.SnapHelper
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        this.delegate.attachToRecyclerView(recyclerView);
        super.attachToRecyclerView(recyclerView);
    }

    @Override // android.support.v7.widget.LinearSnapHelper, android.support.v7.widget.SnapHelper
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View view) {
        return this.delegate.calculateDistanceToFinalSnap(layoutManager, view);
    }

    public void enableLastItemSnap(boolean z) {
        this.delegate.enableLastItemSnap(z);
    }

    @Override // android.support.v7.widget.LinearSnapHelper, android.support.v7.widget.SnapHelper
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return this.delegate.findSnapView(layoutManager);
    }
}
