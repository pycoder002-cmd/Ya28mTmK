package cu.uci.android.apklis.presentation.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.utils.Utils;
import com.google.gson.Gson;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.squareup.picasso.Picasso;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity;
import cu.uci.android.apklis.presentation.ui.listener.OnLoadMoreListener;
import cu.uci.android.apklis.presentation.ui.listener.RecyclerViewClickListener;
import cu.uci.android.apklis.presentation.ui.util.LabelLayout;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements RecyclerViewClickListener {
    private final int VIEW_TYPE_ITEM;
    private final int VIEW_TYPE_LOADING;
    private ArrayList<AppDetails> apps;
    private BehaviorSubject<ArrayList<AppDetails>> behaviorSubject;
    private boolean isLoading;
    private int lastVisibleItem;
    private ArrayList list;
    private Context mContext;
    private Consumer<? super Object> observer;
    private AdapterView.OnItemLongClickListener onItemLongClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private int resLayout;
    private int totalItemCount;
    private View view;
    private ViewHolder view1;
    private int visibleThreshold;

    /* loaded from: classes.dex */
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }

    /* loaded from: classes.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppDetails app;
        CardView card;
        private FloatingActionButton fab;
        public ImageView icon;
        public ImageView iv_apklis;
        ArrayList list_favorite_apps;
        private RecyclerViewClickListener mListener;
        public TextView name;
        LabelLayout price;
        public SimpleRatingBar rating;
        public ImageView wish_list;

        public ViewHolder(View view, RecyclerViewClickListener recyclerViewClickListener) {
            super(view);
            this.card = (CardView) view.findViewById(R.id.cardApp);
            this.icon = (ImageView) view.findViewById(R.id.app_icon);
            this.name = (TextView) view.findViewById(R.id.app_name);
            this.rating = (SimpleRatingBar) view.findViewById(R.id.simpleRatingBar);
            this.price = (LabelLayout) view.findViewById(R.id.price);
            this.iv_apklis = (ImageView) view.findViewById(R.id.iv_apklisItem);
            this.card.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.adapter.AppAdapter.ViewHolder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    ViewHolder.this.mListener.onClickView(ViewHolder.this.getAdapterPosition());
                }
            });
            this.mListener = recyclerViewClickListener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void drawSelected(View view) {
            this.card.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorAccent));
            this.card.setSelected(true);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void drawUnselected(View view) {
            this.card.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.colorAppDetailsBackground));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:23:0x005b -> B:6:0x006b). Please report as a decompilation issue!!! */
        public void setup(final AppDetails appDetails, final BehaviorSubject<ArrayList<AppDetails>> behaviorSubject) {
            this.app = appDetails;
            this.card.setVisibility(0);
            int i = R.drawable.ic_apk_default;
            try {
                this.name.setText(appDetails.getName());
                this.rating.setRating(Float.parseFloat(appDetails.getRating()));
                Picasso.get().load(Uri.parse(appDetails.getIcon())).fit().placeholder(R.drawable.ic_apk_default).error(R.drawable.ic_apk_default).into(this.icon);
                if (behaviorSubject.getValue().contains(appDetails)) {
                    drawSelected(this.card);
                } else {
                    drawUnselected(this.card);
                }
            } catch (Exception e) {
                this.icon.setImageResource(i);
                MainApp.log(getClass().getName(), e);
            }
            try {
                i = (appDetails.getPrice().doubleValue() > Utils.DOUBLE_EPSILON ? 1 : (appDetails.getPrice().doubleValue() == Utils.DOUBLE_EPSILON ? 0 : -1));
                if (i > 0) {
                    this.price.setLabelText("$" + appDetails.getPriceString());
                    this.price.setLabelHeight(1);
                } else {
                    this.price.setLabelText("");
                    this.price.setLabelHeight(0);
                }
                if (appDetails.getExtension().equals(".apklis")) {
                    this.iv_apklis.setVisibility(0);
                } else {
                    this.iv_apklis.setVisibility(8);
                }
            } catch (Exception e2) {
                MainApp.log(getClass().getName(), e2);
                this.price.setLabelText("");
                this.price.setLabelHeight(0);
            }
            this.card.setOnLongClickListener(new View.OnLongClickListener() { // from class: cu.uci.android.apklis.presentation.ui.adapter.AppAdapter.ViewHolder.1
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    if (MainApp.userAccount == null || MainApp.userAccount.isEmpty()) {
                        Toast.makeText(MainApp.context, "Debe estar Autenticado", 0).show();
                        return true;
                    }
                    ViewHolder.this.mListener.onLongClickView(ViewHolder.this.getAdapterPosition());
                    if (!ViewHolder.this.card.isSelected()) {
                        ViewHolder.this.drawSelected(view);
                        ArrayList arrayList = (ArrayList) behaviorSubject.getValue();
                        arrayList.add(appDetails);
                        behaviorSubject.onNext(arrayList);
                        return true;
                    }
                    ViewHolder.this.drawUnselected(view);
                    ArrayList arrayList2 = (ArrayList) behaviorSubject.getValue();
                    arrayList2.remove(appDetails);
                    behaviorSubject.onNext(arrayList2);
                    ViewHolder.this.card.setSelected(false);
                    return true;
                }
            });
        }
    }

    public AppAdapter(Context context, RecyclerView recyclerView, int i) {
        this.VIEW_TYPE_ITEM = 0;
        this.VIEW_TYPE_LOADING = 1;
        this.visibleThreshold = 1;
        this.list = new ArrayList();
        this.mContext = context;
        this.resLayout = i;
        this.apps = new ArrayList<>();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: cu.uci.android.apklis.presentation.ui.adapter.AppAdapter.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i2, int i3) {
                super.onScrolled(recyclerView2, i2, i3);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView2.getLayoutManager();
                AppAdapter.this.totalItemCount = linearLayoutManager.getItemCount();
                AppAdapter.this.lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (AppAdapter.this.isLoading || AppAdapter.this.totalItemCount > AppAdapter.this.lastVisibleItem + AppAdapter.this.visibleThreshold || AppAdapter.this.totalItemCount < MainApp.APPS_LOAD_RANGE) {
                    return;
                }
                if (AppAdapter.this.onLoadMoreListener != null) {
                    AppAdapter.this.onLoadMoreListener.onLoadMore();
                }
                AppAdapter.this.isLoading = true;
            }
        });
    }

    public AppAdapter(Context context, RecyclerView recyclerView, int i, BehaviorSubject<ArrayList<AppDetails>> behaviorSubject) {
        this(context, recyclerView, i);
        this.behaviorSubject = behaviorSubject;
    }

    public void addApp(AppDetails appDetails) {
        this.apps.add(appDetails);
        notifyItemInserted(this.apps.size() - 1);
    }

    public void addApps(@NonNull ArrayList<AppDetails> arrayList) {
        this.apps.addAll((this.apps.size() <= 0 || this.apps.get(this.apps.size() + (-1)) != null) ? this.apps.size() : this.apps.size() - 1, arrayList);
        notifyDataSetChanged();
    }

    public void clearApps() {
        this.apps = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.apps == null) {
            return 0;
        }
        return this.apps.size();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return this.apps.get(i) == null ? 1 : 0;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ViewHolder) {
            ((ViewHolder) viewHolder).setup(this.apps.get(viewHolder.getAdapterPosition()), this.behaviorSubject);
        } else if (viewHolder instanceof LoadingViewHolder) {
            ((LoadingViewHolder) viewHolder).progressBar.setIndeterminate(true);
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.RecyclerViewClickListener
    public void onClickView(int i) {
        try {
            String json = new Gson().toJson(this.apps.get(i));
            Intent intent = new Intent(this.mContext, (Class<?>) AppDetailsActivity.class);
            intent.putExtra("APP_JSON_PARAM", json);
            this.mContext.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        if (i != 0) {
            return new LoadingViewHolder(LayoutInflater.from(context).inflate(R.layout.item_loading, viewGroup, false));
        }
        this.view = LayoutInflater.from(context).inflate(this.resLayout, viewGroup, false);
        this.view1 = new ViewHolder(this.view, this);
        return this.view1;
    }

    @Override // cu.uci.android.apklis.presentation.ui.listener.RecyclerViewClickListener
    public void onLongClickView(int i) {
        this.list.add(this.apps.get(i));
    }

    public void setLoaded() {
        if (this.apps != null && this.apps.size() > 0) {
            int size = this.apps.size() - 1;
            if (this.apps.get(size) == null) {
                this.apps.remove(size);
            }
        }
        this.isLoading = false;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void updateApps(@NonNull ArrayList<AppDetails> arrayList) {
        if (this.apps != null) {
            this.apps.clear();
        }
        this.apps = arrayList;
        notifyDataSetChanged();
        String[] strArr = new String[5];
    }
}
