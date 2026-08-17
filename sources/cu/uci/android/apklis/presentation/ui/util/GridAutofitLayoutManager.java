package cu.uci.android.apklis.presentation.ui.util;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

/* loaded from: classes.dex */
public class GridAutofitLayoutManager extends GridLayoutManager {
    private int mColumnWidth;
    private boolean mColumnWidthChanged;

    public GridAutofitLayoutManager(Context context, int i) {
        super(context, 1);
        this.mColumnWidthChanged = true;
        setColumnWidth(checkedColumnWidth(context, i));
    }

    public GridAutofitLayoutManager(Context context, int i, int i2, boolean z) {
        super(context, 1, i2, z);
        this.mColumnWidthChanged = true;
        setColumnWidth(checkedColumnWidth(context, i));
    }

    private int checkedColumnWidth(Context context, int i) {
        return i <= 0 ? (int) TypedValue.applyDimension(1, 100.0f, context.getResources().getDisplayMetrics()) : i;
    }

    @Override // android.support.v7.widget.GridLayoutManager, android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mColumnWidthChanged && this.mColumnWidth > 0) {
            setSpanCount(Math.max(1, (getOrientation() == 1 ? (getWidth() - getPaddingRight()) - getPaddingLeft() : (getHeight() - getPaddingTop()) - getPaddingBottom()) / this.mColumnWidth));
            this.mColumnWidthChanged = false;
        }
        super.onLayoutChildren(recycler, state);
    }

    public void setColumnWidth(int i) {
        if (i <= 0 || i == this.mColumnWidth) {
            return;
        }
        this.mColumnWidth = i;
        this.mColumnWidthChanged = true;
    }
}
