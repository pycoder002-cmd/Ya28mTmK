package cu.uci.android.apklis.presentation.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.model.GroupType;
import cu.uci.android.apklis.presentation.model.SnapApp;
import cu.uci.android.apklis.presentation.ui.activity.AppListActivity;
import cu.uci.android.apklis.presentation.ui.util.SpacesItemDecoration;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes.dex */
public class SnapAppAdapter extends RecyclerView.Adapter<ViewHolder> implements GravitySnapHelper.SnapListener {
    public static final int TYPE_CAR = 2;
    public static final int TYPE_GUIDE = 1;
    public static final int TYPE_HOSTAL = 0;
    BehaviorSubject<ArrayList<AppDetails>> behaviorSubject;
    private int colunmCount;
    private Context mContext;
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() { // from class: cu.uci.android.apklis.presentation.ui.adapter.SnapAppAdapter.1
        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            return false;
        }
    };
    private ArrayList<SnapApp> mSnaps = new ArrayList<>();

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout btnMore;
        RecyclerView recycler;
        TextView snapTextView;

        public ViewHolder(View view) {
            super(view);
            this.snapTextView = (TextView) view.findViewById(R.id.snapTextView);
            this.recycler = (RecyclerView) view.findViewById(R.id.recyclerView);
            this.btnMore = (RelativeLayout) view.findViewById(R.id.btn_more);
        }

        public void setup(final SnapApp snapApp) {
            this.snapTextView.setText(snapApp.getTitle());
            this.recycler.setLayoutManager(new GridLayoutManager(this.recycler.getContext(), SnapAppAdapter.this.colunmCount, 1, false));
            this.recycler.setNestedScrollingEnabled(false);
            AppAdapter appAdapter = new AppAdapter(SnapAppAdapter.this.mContext, this.recycler, R.layout.item_app_snap, SnapAppAdapter.this.behaviorSubject);
            appAdapter.updateApps(snapApp.getApps());
            this.recycler.setAdapter(appAdapter);
            appAdapter.notifyDataSetChanged();
            this.recycler.addItemDecoration(new SpacesItemDecoration(4));
            if (snapApp.isHasMore()) {
                this.btnMore.setVisibility(0);
            } else {
                this.btnMore.setVisibility(8);
            }
            this.btnMore.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.adapter.SnapAppAdapter.ViewHolder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    GroupType typeByValue = GroupType.getTypeByValue(snapApp.getType().intValue());
                    Intent intent = new Intent(SnapAppAdapter.this.mContext, (Class<?>) AppListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("ACTION_PARAM", AppListActivity.ACTION_COLLECTION_VALUE.intValue());
                    bundle.putString(AppListActivity.TITLE_PARAM, MainApp.context.getString(typeByValue.resTitle()));
                    bundle.putString(AppListActivity.QUERY_PARAM, typeByValue.value() + "");
                    intent.putExtras(bundle);
                    SnapAppAdapter.this.mContext.startActivity(intent);
                }
            });
        }
    }

    public SnapAppAdapter(Context context, int i, BehaviorSubject<ArrayList<AppDetails>> behaviorSubject) {
        this.behaviorSubject = BehaviorSubject.createDefault(new ArrayList());
        this.mContext = context;
        this.colunmCount = i;
        this.behaviorSubject = behaviorSubject;
    }

    public void addSnap(SnapApp snapApp) {
        this.mSnaps.add(snapApp);
        Collections.sort(this.mSnaps, new Comparator<SnapApp>() { // from class: cu.uci.android.apklis.presentation.ui.adapter.SnapAppAdapter.2
            @Override // java.util.Comparator
            public int compare(SnapApp snapApp2, SnapApp snapApp3) {
                return snapApp2.getType().compareTo(snapApp3.getType());
            }
        });
        notifyDataSetChanged();
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
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_snap, viewGroup, false));
    }

    @Override // com.github.rubensousa.gravitysnaphelper.GravitySnapHelper.SnapListener
    public void onSnap(int i) {
        Log.d("Snapped: ", i + "");
    }
}
