package cu.uci.android.apklis.presentation.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.model.CategoryView;
import cu.uci.android.apklis.presentation.ui.activity.AppListActivity;
import cu.uci.android.apklis.presentation.ui.listener.UpdateViewClickListener;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements UpdateViewClickListener {
    private ArrayList<CategoryView> categories = new ArrayList<>();
    private Context mContext;

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CategoryView category;
        public ImageView icon;
        private UpdateViewClickListener mListener;
        public TextView name;

        public ViewHolder(View view, UpdateViewClickListener updateViewClickListener) {
            super(view);
            this.icon = (ImageView) view.findViewById(R.id.category_icon);
            this.name = (TextView) view.findViewById(R.id.category_name);
            view.setOnClickListener(this);
            this.mListener = updateViewClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.mListener.onClickView(getAdapterPosition());
        }

        public void setup(CategoryView categoryView, Context context) {
            this.category = categoryView;
            this.name.setText(categoryView.getName());
            try {
                int identifier = context.getResources().getIdentifier(categoryView.getIcon(), "drawable", this.itemView.getContext().getPackageName());
                if (identifier == 0) {
                    this.icon.setImageResource(R.drawable.agilidad_mental);
                } else {
                    Picasso.get().load(identifier).placeholder(R.drawable.agilidad_mental).error(R.drawable.agilidad_mental).into(this.icon);
                }
            } catch (Exception e) {
                this.icon.setImageResource(R.drawable.agilidad_mental);
                MainApp.log(getClass().getName(), e);
            }
        }
    }

    public CategoryAdapter(Context context, RecyclerView recyclerView) {
        this.mContext = context;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.categories == null) {
            return 0;
        }
        return this.categories.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((ViewHolder) viewHolder).setup(this.categories.get(viewHolder.getAdapterPosition()), this.mContext);
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.UpdateViewClickListener
    public void onClickCancelDownload(int i) {
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.UpdateViewClickListener
    public void onClickUpdate(int i) {
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.UpdateViewClickListener
    public void onClickView(int i) {
        CategoryView categoryView = this.categories.get(i);
        Intent intent = new Intent(this.mContext, (Class<?>) AppListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("ACTION_PARAM", AppListActivity.ACTION_CATEGORY_VALUE.intValue());
        String name = categoryView.getName();
        Integer id = categoryView.getId();
        bundle.putString(AppListActivity.TITLE_PARAM, name);
        bundle.putString(AppListActivity.QUERY_PARAM, id + "");
        intent.putExtras(bundle);
        this.mContext.startActivity(intent);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false), this);
    }

    public void updateCategories(@NonNull ArrayList<CategoryView> arrayList) {
        if (this.categories != null) {
            this.categories.clear();
        }
        this.categories = arrayList;
        notifyDataSetChanged();
    }
}
