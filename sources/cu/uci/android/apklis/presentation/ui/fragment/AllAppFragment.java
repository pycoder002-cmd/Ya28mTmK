package cu.uci.android.apklis.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.presenter.AllAppPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.AllAppPresenterImpl;
import cu.uci.android.apklis.presentation.ui.adapter.AppAdapter;
import cu.uci.android.apklis.presentation.ui.listener.OnLoadMoreListener;
import cu.uci.android.apklis.presentation.ui.util.SpacesItemDecoration;
import cu.uci.android.apklis.threading.MainThreadImpl;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AllAppFragment extends Fragment implements AllAppPresenter.View {
    private AppAdapter adapter;
    LinearLayout emptyContent;
    private AllAppPresenter presenter;
    ProgressBar progress;
    RecyclerView recycler;
    private Boolean loadMore = true;
    private String query = null;
    private String categoryName = null;
    private Integer categoryId = null;
    private Boolean updating = false;
    BehaviorSubject<ArrayList<AppDetails>> behaviorSubject = BehaviorSubject.createDefault(new ArrayList());

    public static AllAppFragment createInstance() {
        return new AllAppFragment();
    }

    private void init(View view) {
        this.recycler = (RecyclerView) view.findViewById(R.id.recycler);
        this.emptyContent = (LinearLayout) view.findViewById(R.id.empty_content);
        this.adapter = new AppAdapter(getContext(), this.recycler, R.layout.item_app, this.behaviorSubject);
        this.recycler.setLayoutManager(new GridLayoutManager(getContext(), MainApp.SPAN_COUNT_RECYCLER_GRID_VIEW));
        this.recycler.setAdapter(this.adapter);
        this.recycler.addItemDecoration(new SpacesItemDecoration(4));
        this.adapter.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.AllAppFragment.1
            @Override // cu.uci.android.apklis.presentation.ui.listener.OnLoadMoreListener
            public void onLoadMore() {
                if (!AllAppFragment.this.loadMore.booleanValue()) {
                    Toast.makeText(AllAppFragment.this.getActivity(), "Todos los datos fueron cargados", 0).show();
                    return;
                }
                int itemCount = AllAppFragment.this.adapter.getItemCount();
                AllAppFragment.this.adapter.addApp(null);
                if (AllAppFragment.this.categoryId == null) {
                    AllAppFragment.this.presenter.getAppsInRange(itemCount, MainApp.APPS_LOAD_RANGE, AllAppFragment.this.query);
                } else {
                    AllAppFragment.this.presenter.getAppsInRangeByCategory(itemCount, MainApp.APPS_LOAD_RANGE, AllAppFragment.this.categoryId);
                }
            }
        });
        this.presenter.getAppsInRange(this.adapter.getItemCount(), MainApp.APPS_LOAD_RANGE, this.query);
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
        this.progress.setVisibility(4);
        this.emptyContent.setVisibility(4);
        this.recycler.setVisibility(0);
        this.updating = false;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter.View
    public void loadAppListByCategory(Integer num, String str) {
        this.categoryName = str;
        this.categoryId = num;
        if (this.updating.booleanValue()) {
            return;
        }
        this.updating = true;
        this.adapter.clearApps();
        this.presenter.getAppsInRangeByCategory(this.adapter.getItemCount(), MainApp.APPS_LOAD_RANGE, num);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter.View
    public void onAppsRetrived(ArrayList<AppDetails> arrayList) {
        if (this.adapter.getItemCount() + arrayList.size() <= 0) {
            this.recycler.setVisibility(4);
            this.progress.setVisibility(4);
            this.emptyContent.setVisibility(0);
        } else {
            this.progress.setVisibility(4);
            this.emptyContent.setVisibility(4);
            this.recycler.setVisibility(0);
            this.loadMore = Boolean.valueOf(arrayList.size() >= MainApp.APPS_LOAD_RANGE);
            this.adapter.addApps(arrayList);
            this.adapter.setLoaded();
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter.View
    public void onClickApp(String str) {
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.content_app_list, viewGroup, false);
        this.progress = (ProgressBar) inflate.findViewById(R.id.progress_content_app_list);
        this.presenter = new AllAppPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        init(inflate);
        return inflate;
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        this.updating = false;
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
        this.recycler.setVisibility(4);
        this.emptyContent.setVisibility(4);
        this.progress.setVisibility(0);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter.View
    public void updateAppList(String str) {
        this.categoryName = null;
        this.categoryId = null;
        if (this.updating.booleanValue()) {
            return;
        }
        this.updating = true;
        this.query = str;
        this.adapter.clearApps();
        this.presenter.getAppsInRange(0, MainApp.APPS_LOAD_RANGE, str);
    }
}
