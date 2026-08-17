package cu.uci.android.apklis.presentation.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.device.Service.DownloadListener;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.presentation.model.CategoryView;
import cu.uci.android.apklis.presentation.model.SnapCategory;
import cu.uci.android.apklis.presentation.presenter.CategoryFragmentPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.CategoryFragmentPresenterImpl;
import cu.uci.android.apklis.presentation.ui.adapter.SnapCategoryAdapter;
import cu.uci.android.apklis.threading.MainThreadImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class CategoryFragment extends Fragment implements CategoryFragmentPresenter.View {
    private SnapCategoryAdapter adapter;
    ArrayList<CategoryView> appCategories;
    ArrayList<CategoryView> gameCategories;
    private CategoryFragmentPresenter presenter;
    RecyclerView recycler;
    SwipeRefreshLayout swipeRefreshLayout;
    private Boolean updateList = false;
    List<DownloadListener> downloadListeners = new ArrayList();

    public static CategoryFragment createInstance() {
        return new CategoryFragment();
    }

    private void init(View view) {
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshCategory);
        this.recycler = (RecyclerView) view.findViewById(R.id.recycler);
    }

    private void initData(ArrayList<CategoryView> arrayList) {
        this.gameCategories = new ArrayList<>();
        this.appCategories = new ArrayList<>();
        Iterator<CategoryView> it = arrayList.iterator();
        while (it.hasNext()) {
            CategoryView next = it.next();
            if (next.getGroup().equals("Games")) {
                this.gameCategories.add(next);
            } else {
                this.appCategories.add(next);
            }
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
        this.swipeRefreshLayout.setRefreshing(false);
        this.swipeRefreshLayout.setVisibility(8);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.CategoryFragmentPresenter.View
    public void onCategoriesReturned(ArrayList<CategoryView> arrayList) {
        this.swipeRefreshLayout.setRefreshing(false);
        this.swipeRefreshLayout.setVisibility(8);
        initData(arrayList);
        this.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recycler.setHasFixedSize(true);
        if (isAdded()) {
            this.adapter = new SnapCategoryAdapter(getContext(), MainApp.SPAN_COUNT_RECYCLER_GRID_VIEW_CATEGORY);
            this.adapter.addSnap(new SnapCategory(R.drawable.ic_game_black, getString(R.string.game_title), this.gameCategories));
            this.adapter.addSnap(new SnapCategory(R.drawable.ic_app_black, getString(R.string.app_title), this.appCategories));
            this.recycler.setAdapter(this.adapter);
            this.recycler.setScrollY(0);
        }
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_category, viewGroup, false);
        init(inflate);
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z && this.gameCategories == null && this.appCategories == null) {
            showProgress();
            this.presenter = new CategoryFragmentPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
            this.presenter.getCategories();
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        this.swipeRefreshLayout.setRefreshing(false);
        this.swipeRefreshLayout.setVisibility(8);
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
        this.swipeRefreshLayout.setVisibility(0);
        this.swipeRefreshLayout.setRefreshing(true);
    }
}
