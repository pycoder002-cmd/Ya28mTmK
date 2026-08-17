package cu.uci.android.apklis.presentation.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.model.SnapCategory;
import cu.uci.android.apklis.presentation.ui.util.SpacesItemDecorationVH;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SnapCategoryAdapter extends RecyclerView.Adapter<ViewHolder> implements GravitySnapHelper.SnapListener {
    private int colunmCount;
    private Context mContext;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() { // from class: cu.uci.android.apklis.presentation.ui.adapter.SnapCategoryAdapter.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        }
    };
    private ArrayList<SnapCategory> mSnaps = new ArrayList<>();

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView groupIcon;
        RecyclerView recycler;
        TextView snapTextView;

        public ViewHolder(View view) {
            super(view);
            this.groupIcon = (ImageView) view.findViewById(R.id.group_icon);
            this.snapTextView = (TextView) view.findViewById(R.id.snapTextView);
            this.recycler = (RecyclerView) view.findViewById(R.id.recyclerView);
        }

        public void setup(SnapCategory snapCategory) {
            this.groupIcon.setImageResource(snapCategory.getIcon());
            this.snapTextView.setText(snapCategory.getTitle());
            this.recycler.setLayoutManager(new GridLayoutManager(this.recycler.getContext(), SnapCategoryAdapter.this.colunmCount, 1, false));
            this.recycler.setNestedScrollingEnabled(false);
            CategoryAdapter categoryAdapter = new CategoryAdapter(SnapCategoryAdapter.this.mContext, this.recycler);
            categoryAdapter.updateCategories(snapCategory.getCategories());
            this.recycler.setAdapter(categoryAdapter);
            this.recycler.addItemDecoration(new SpacesItemDecorationVH(16));
        }
    }

    public SnapCategoryAdapter(Context context, int i) {
        this.mContext = context;
        this.colunmCount = i;
    }

    public void addSnap(SnapCategory snapCategory) {
        this.mSnaps.add(snapCategory);
    }

    public void clearSnaps() {
        this.mSnaps.clear();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mSnaps.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.setup(this.mSnaps.get(viewHolder.getAdapterPosition()));
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category_snap, viewGroup, false));
    }

    @Override // com.github.rubensousa.gravitysnaphelper.GravitySnapHelper.SnapListener
    public void onSnap(int i) {
        Log.d("Snapped: ", i + "");
    }
}
