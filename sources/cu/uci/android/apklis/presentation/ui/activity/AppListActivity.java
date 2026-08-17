package cu.uci.android.apklis.presentation.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.startapp.sdk.adsbase.StartAppAd;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.domain.interactor.impl.AddWishListImpl;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.presenter.AddWishListPresenter;
import cu.uci.android.apklis.presentation.presenter.AllAppPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.AllAppPresenterImpl;
import cu.uci.android.apklis.presentation.ui.adapter.AppAdapter;
import cu.uci.android.apklis.presentation.ui.listener.OnLoadMoreListener;
import cu.uci.android.apklis.presentation.ui.util.SpacesItemDecoration;
import cu.uci.android.apklis.storage.repository.ApiRepositoryImpl;
import cu.uci.android.apklis.threading.MainThreadImpl;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.List;
import kotlin.text.Regex;

/* loaded from: classes.dex */
public class AppListActivity extends AppCompatActivity implements AllAppPresenter.View {
    public static final String ACTION_PARAM = "ACTION_PARAM";
    public static final String QUERY_PARAM = "QUERY_PARAM";
    public static final String TITLE_PARAM = "TITLE_PARAM";
    private int action;
    private AppAdapter adapter;
    private AddWishListImpl addWishListPresenter;
    ApiRepositoryImpl apiRepository;
    private int[] arrayApp;
    LinearLayout btTryAgain;
    LinearLayout emptyContent;
    private FloatingActionButton fab;
    private ArrayList list;
    private ArrayList<Integer> listId;
    private AllAppPresenter presenter;
    private String query;
    RecyclerView recycler;
    SwipeRefreshLayout swipeRefreshLayout;
    private String username;
    public static final Integer ACTION_COLLECTION_VALUE = 0;
    public static final Integer ACTION_CATEGORY_VALUE = 1;
    public static final Integer ACTION_SEARCH_VALUE = 2;
    public static final Integer ACTION_SEARCH_VALUE_PACKAGE_NAME = 4;
    public static final Integer ACTION_USER_APPS_VALUE = 3;
    public static final Integer ACTION_FAVORITE_APPS_VALUE = 5;
    private Boolean loadMore = true;
    private Boolean loadMoreMyApps = true;
    private Boolean updating = false;
    private boolean goLink = false;
    BehaviorSubject<ArrayList<AppDetails>> behaviorSubject = BehaviorSubject.createDefault(new ArrayList());
    private String resStr = "";
    private String text = "Eliminado de la lista";

    private void init() {
        this.presenter = new AllAppPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.apiRepository = new ApiRepositoryImpl();
        this.adapter = new AppAdapter(this, this.recycler, R.layout.item_app, this.behaviorSubject);
        this.fab = (FloatingActionButton) findViewById(R.id.fab_app_list);
        this.recycler.setLayoutManager(new GridLayoutManager(this, MainApp.SPAN_COUNT_RECYCLER_GRID_VIEW));
        this.recycler.setAdapter(this.adapter);
        this.recycler.addItemDecoration(new SpacesItemDecoration(4));
        this.adapter.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppListActivity.2
            @Override // cu.uci.android.apklis.presentation.ui.listener.OnLoadMoreListener
            public void onLoadMore() {
                if (!AppListActivity.this.loadMore.booleanValue()) {
                    AppListActivity.this.showError(AppListActivity.this.getString(R.string.all_data_loaded_message));
                    return;
                }
                AppListActivity.this.adapter.getItemCount();
                AppListActivity.this.adapter.addApp(null);
                AppListActivity.this.loadApps();
            }
        });
        this.behaviorSubject.subscribe(new Consumer<ArrayList<AppDetails>>() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppListActivity.3
            @Override // io.reactivex.functions.Consumer
            public void accept(ArrayList<AppDetails> arrayList) throws Exception {
                if (arrayList.isEmpty()) {
                    AppListActivity.this.fab.setVisibility(8);
                } else {
                    AppListActivity.this.fab.setVisibility(0);
                    AppListActivity.this.fab.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppListActivity.3.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            ArrayList<AppDetails> value = AppListActivity.this.behaviorSubject.getValue();
                            AppListActivity.this.arrayApp = new int[value.size()];
                            for (int i = 0; i < value.size(); i++) {
                                AppListActivity.this.arrayApp[i] = value.get(i).getId().intValue();
                            }
                            if (MainApp.userAccount.getUsername() != null) {
                                AppListActivity.this.username = MainApp.userAccount.getUsername();
                                AppListActivity.this.listId = MainApp.userAccount.getFavoriteApps();
                                if (AppListActivity.this.arrayApp.length == AppListActivity.this.listId.size()) {
                                    for (int i2 = 0; i2 < AppListActivity.this.arrayApp.length; i2++) {
                                        for (int i3 = 0; i3 < AppListActivity.this.listId.size(); i3++) {
                                            if (AppListActivity.this.arrayApp[i2] == ((Integer) AppListActivity.this.listId.get(i3)).intValue() && ((Integer) AppListActivity.this.listId.get(i3)).intValue() == AppListActivity.this.arrayApp[i2]) {
                                                AppListActivity.this.listId.remove(i3);
                                            }
                                        }
                                    }
                                    AppListActivity.this.arrayApp = new int[AppListActivity.this.listId.size()];
                                    AppListActivity.this.addWishListPresenter.saveWhisList(AppListActivity.this.username, AppListActivity.this.arrayApp, AppListActivity.this.text);
                                    AppListActivity.this.behaviorSubject.onNext(new ArrayList<>());
                                    AppListActivity.this.adapter.notifyDataSetChanged();
                                    AppListActivity.this.recreate();
                                    return;
                                }
                                if (AppListActivity.this.arrayApp.length >= AppListActivity.this.listId.size()) {
                                    Toast.makeText(AppListActivity.this, "other case", 0).show();
                                    return;
                                }
                                for (int i4 = 0; i4 < AppListActivity.this.arrayApp.length; i4++) {
                                    for (int i5 = 0; i5 < AppListActivity.this.listId.size(); i5++) {
                                        if (AppListActivity.this.arrayApp[i4] == ((Integer) AppListActivity.this.listId.get(i5)).intValue()) {
                                            AppListActivity.this.listId.remove(i5);
                                        }
                                    }
                                }
                                AppListActivity.this.arrayApp = new int[AppListActivity.this.listId.size() + value.size()];
                                for (int i6 = 0; i6 < AppListActivity.this.listId.size(); i6++) {
                                    AppListActivity.this.arrayApp[i6] = Integer.parseInt(String.valueOf(AppListActivity.this.listId.get(i6)));
                                }
                                AppListActivity.this.addWishListPresenter.saveWhisList(AppListActivity.this.username, AppListActivity.this.arrayApp, AppListActivity.this.text);
                                ArrayList<AppDetails> arrayList2 = new ArrayList<>();
                                AppListActivity.this.recreate();
                                AppListActivity.this.behaviorSubject.onNext(arrayList2);
                                AppListActivity.this.adapter.notifyDataSetChanged();
                            }
                        }
                    });
                }
            }
        });
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppListActivity.4
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                AppListActivity.this.adapter.clearApps();
                AppListActivity.this.loadApps();
            }
        });
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        if ("android.intent.action.VIEW".equals(action)) {
            try {
                if (data.toString().contains("play.google.com")) {
                    String str = data.toString().split("=")[1];
                    if (str.contains("&hl")) {
                        str = str.replace("&hl", "");
                    }
                    this.action = 2;
                    this.query = str;
                    this.goLink = true;
                }
                if (data.toString().contains(MainApp.APP_URL_NAME)) {
                    String str2 = new Regex("application/([^/]+)/").find(data.toString().split("=")[0], 0).getGroupValues().get(1);
                    this.action = 4;
                    this.query = str2;
                    this.goLink = true;
                } else {
                    List<String> pathSegments = data.getPathSegments();
                    if (pathSegments.size() > 1) {
                        this.action = 2;
                        this.query = pathSegments.get(2);
                        this.goLink = true;
                    }
                }
            } catch (NullPointerException e) {
                MainApp.log(getClass().getName(), e);
                e.printStackTrace();
            }
        }
        loadApps();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadApps() {
        int itemCount = this.adapter.getItemCount();
        if (this.action == ACTION_CATEGORY_VALUE.intValue()) {
            this.presenter.getAppsInRangeByCategory(itemCount, MainApp.APPS_LOAD_RANGE, Integer.valueOf(Integer.parseInt(this.query)));
            return;
        }
        if (this.action == ACTION_SEARCH_VALUE.intValue()) {
            this.presenter.getAppsInRange(itemCount, MainApp.APPS_LOAD_RANGE, this.query);
            return;
        }
        if (this.action == ACTION_SEARCH_VALUE_PACKAGE_NAME.intValue()) {
            this.presenter.getAppsPackageName(itemCount, MainApp.APPS_LOAD_RANGE, this.query);
            return;
        }
        if (this.action == ACTION_COLLECTION_VALUE.intValue()) {
            this.presenter.getAppsInGroup(itemCount, MainApp.APPS_LOAD_RANGE, Integer.parseInt(this.query));
            return;
        }
        if (this.action != ACTION_FAVORITE_APPS_VALUE.intValue()) {
            if (this.action == ACTION_USER_APPS_VALUE.intValue()) {
                if (this.loadMoreMyApps.booleanValue()) {
                    this.presenter.getAppsByUser(this.query);
                    return;
                } else {
                    this.adapter.setLoaded();
                    this.loadMoreMyApps = true;
                    return;
                }
            }
            return;
        }
        if (MainApp.userAccount != null) {
            this.list = MainApp.userAccount.getFavoriteApps();
            for (int i = 0; i < this.list.size(); i++) {
                if (i == 0) {
                    this.resStr += String.valueOf(this.list.get(i));
                } else {
                    this.resStr += ',' + String.valueOf(this.list.get(i));
                }
            }
            this.presenter.getFavoriteApps(this.query, this.resStr);
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
        this.swipeRefreshLayout.setRefreshing(false);
        this.swipeRefreshLayout.setVisibility(8);
        this.emptyContent.setVisibility(8);
        this.recycler.setVisibility(8);
        this.updating = false;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter.View
    public void loadAppListByCategory(Integer num, String str) {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter.View
    public void onAppsRetrived(ArrayList<AppDetails> arrayList) {
        if (this.adapter.getItemCount() + arrayList.size() <= 0) {
            this.swipeRefreshLayout.setRefreshing(false);
            this.swipeRefreshLayout.setVisibility(8);
            this.recycler.setVisibility(8);
            this.emptyContent.setVisibility(0);
            return;
        }
        this.swipeRefreshLayout.setRefreshing(false);
        this.emptyContent.setVisibility(4);
        this.swipeRefreshLayout.setVisibility(0);
        this.recycler.setVisibility(0);
        this.loadMore = Boolean.valueOf(arrayList.size() >= MainApp.APPS_LOAD_RANGE);
        this.loadMoreMyApps = false;
        this.adapter.addApps(arrayList);
        this.adapter.setLoaded();
        if (this.goLink && arrayList.size() == 1) {
            this.adapter.onClickView(0);
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter.View
    public void onClickApp(String str) {
    }

    public void onClickTryAgain() {
        this.adapter.clearApps();
        loadApps();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_app_list);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.addWishListPresenter = new AddWishListImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), (AddWishListPresenter.View) this.addWishListPresenter);
        Bundle extras = getIntent().getExtras();
        try {
            setTitle(extras.getString(TITLE_PARAM, ""));
            this.action = extras.getInt("ACTION_PARAM", ACTION_SEARCH_VALUE.intValue());
            this.query = extras.getString(QUERY_PARAM, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.recycler = (RecyclerView) findViewById(R.id.recycler);
        this.emptyContent = (LinearLayout) findViewById(R.id.empty_content);
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        this.btTryAgain = (LinearLayout) findViewById(R.id.btn_try_again);
        this.btTryAgain.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppListActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppListActivity.this.onClickTryAgain();
            }
        });
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
        return true;
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        this.updating = false;
        MainApp.showToast(str, true);
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
        this.recycler.setVisibility(8);
        this.emptyContent.setVisibility(8);
        this.swipeRefreshLayout.setVisibility(0);
        this.swipeRefreshLayout.setRefreshing(true);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter.View
    public void updateAppList(String str) {
    }
}
