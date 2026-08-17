package cu.uci.android.apklis.presentation.ui.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import cu.uci.android.apklis.MainApp;

/* loaded from: classes.dex */
public class SpacesItemDecorationVH extends RecyclerView.ItemDecoration {
    private int space;

    public SpacesItemDecorationVH(int i) {
        this.space = i;
    }

    @Override // android.support.v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int i = ((int) (this.space * MainApp.context.getResources().getDisplayMetrics().density)) / 2;
        rect.left = i;
        rect.right = i;
        rect.bottom = i;
        rect.top = i;
    }
}
