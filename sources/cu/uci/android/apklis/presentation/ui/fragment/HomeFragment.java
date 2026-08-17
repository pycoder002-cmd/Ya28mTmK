package cu.uci.android.apklis.presentation.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.domain.interactor.impl.AddWishListImpl;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.model.GroupType;
import cu.uci.android.apklis.presentation.model.SnapApp;
import cu.uci.android.apklis.presentation.presenter.AddWishListPresenter;
import cu.uci.android.apklis.presentation.presenter.HomeFragmentPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.HomeFragmentPresenterImpl;
import cu.uci.android.apklis.presentation.ui.adapter.SnapAppAdapter;
import cu.uci.android.apklis.threading.MainThreadImpl;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HomeFragment extends Fragment implements HomeFragmentPresenter.View {
    private SnapAppAdapter adapter;
    private AddWishListPresenter addWishListPresenter;
    private int[] arrayApp;
    LinearLayout btnTryAgain;
    LinearLayout emptyContent;
    private FloatingActionButton fab;
    private Consumer<? super List> observer;
    private HomeFragmentPresenter presenter;
    private AddWishListPresenter presenterWhish;
    RecyclerView recycler;
    SwipeRefreshLayout swipeRefreshLayout;
    private String username;
    private int groupsLoaded = 0;
    private int totalGroup = 5;
    private Boolean updating = false;
    BehaviorSubject<ArrayList<AppDetails>> behaviorSubject = BehaviorSubject.createDefault(new ArrayList());
    private ArrayList listId = new ArrayList();
    private String text = "Agregado a lista de deseos";

    public static HomeFragment createInstance() {
        return new HomeFragment();
    }

    private void init(View view) {
        this.recycler = (RecyclerView) view.findViewById(R.id.recycler);
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        this.emptyContent = (LinearLayout) view.findViewById(R.id.empty_content);
        this.btnTryAgain = (LinearLayout) view.findViewById(R.id.btn_try_again);
        this.btnTryAgain.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.HomeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                HomeFragment.this.onClickTryAgain();
            }
        });
        this.recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recycler.setHasFixedSize(true);
        this.adapter = new SnapAppAdapter(getContext(), MainApp.SPAN_COUNT_RECYCLER_GRID_VIEW, this.behaviorSubject);
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.HomeFragment.3
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                HomeFragment.this.loadGroups();
                HomeFragment.this.fab.setVisibility(8);
            }
        });
        loadGroups();
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
        this.swipeRefreshLayout.setRefreshing(false);
        this.swipeRefreshLayout.setVisibility(8);
        this.emptyContent.setVisibility(8);
        this.recycler.setVisibility(8);
        this.updating = false;
    }

    public void loadGroups() {
        this.groupsLoaded = 0;
        this.adapter.clearSnaps();
        showProgress();
        for (int i = 0; i < this.totalGroup; i++) {
            this.presenter.getAppsInGroup(0, MainApp.APPS_LOAD_IN_GROUP, i);
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.HomeFragmentPresenter.View
    public void onAppsRetrived(ArrayList<AppDetails> arrayList, int i, boolean z) {
        if (i == -1) {
            this.swipeRefreshLayout.setRefreshing(false);
            this.swipeRefreshLayout.setVisibility(8);
            this.recycler.setVisibility(8);
            this.emptyContent.setVisibility(0);
            return;
        }
        this.groupsLoaded++;
        GroupType typeByValue = GroupType.getTypeByValue(i);
        if (arrayList.size() > 0 && isAdded()) {
            this.adapter.addSnap(new SnapApp(i, getString(typeByValue.resTitle()), arrayList, z));
        }
        if (this.groupsLoaded >= this.totalGroup) {
            this.recycler.setAdapter(this.adapter);
            this.recycler.setScrollY(0);
            this.swipeRefreshLayout.setRefreshing(false);
            this.emptyContent.setVisibility(4);
            this.swipeRefreshLayout.setVisibility(0);
            this.recycler.setVisibility(0);
        }
    }

    void onClickTryAgain() {
        loadGroups();
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_home, viewGroup, false);
        this.presenter = new HomeFragmentPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.addWishListPresenter = new AddWishListImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), (AddWishListPresenter.View) this.addWishListPresenter);
        init(inflate);
        this.fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        return inflate;
    }

    @Override // android.support.v4.app.Fragment
    @SuppressLint({"CheckResult"})
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.behaviorSubject.subscribe(new Consumer<ArrayList<AppDetails>>() { // from class: cu.uci.android.apklis.presentation.ui.fragment.HomeFragment.1
            @Override // io.reactivex.functions.Consumer
            public void accept(ArrayList<AppDetails> arrayList) throws Exception {
                if (arrayList.isEmpty()) {
                    HomeFragment.this.fab.setVisibility(8);
                } else {
                    HomeFragment.this.fab.setVisibility(0);
                    HomeFragment.this.fab.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.HomeFragment.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            ArrayList<AppDetails> value = HomeFragment.this.behaviorSubject.getValue();
                            HomeFragment.this.arrayApp = new int[value.size()];
                            for (int i = 0; i < value.size(); i++) {
                                HomeFragment.this.arrayApp[i] = value.get(i).getId().intValue();
                            }
                            if (MainApp.userAccount.getUsername() == null) {
                                Toast.makeText(HomeFragment.this.getActivity(), "Debe estar autenticado", 0).show();
                                return;
                            }
                            HomeFragment.this.username = MainApp.userAccount.getUsername();
                            HomeFragment.this.listId = MainApp.userAccount.getFavoriteApps();
                            if (HomeFragment.this.listId == null || HomeFragment.this.listId.isEmpty()) {
                                HomeFragment.this.addWishListPresenter.saveWhisList(HomeFragment.this.username, HomeFragment.this.arrayApp, HomeFragment.this.text);
                            } else {
                                HomeFragment.this.arrayApp = new int[HomeFragment.this.listId.size() + value.size()];
                                for (int i2 = 0; i2 < value.size(); i2++) {
                                    HomeFragment.this.listId.add(value.get(i2).getId());
                                }
                                for (int i3 = 0; i3 < HomeFragment.this.listId.size(); i3++) {
                                    HomeFragment.this.arrayApp[i3] = Integer.parseInt(String.valueOf(HomeFragment.this.listId.get(i3)));
                                }
                                HomeFragment.this.addWishListPresenter.saveWhisList(HomeFragment.this.username, HomeFragment.this.arrayApp, HomeFragment.this.text);
                                HomeFragment.this.behaviorSubject.onNext(new ArrayList<>());
                                HomeFragment.this.adapter.notifyDataSetChanged();
                            }
                            HomeFragment.this.behaviorSubject.onNext(new ArrayList<>());
                            HomeFragment.this.adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        this.updating = false;
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
        this.recycler.setVisibility(8);
        this.emptyContent.setVisibility(8);
        this.swipeRefreshLayout.setVisibility(0);
        this.swipeRefreshLayout.setRefreshing(true);
    }
}
