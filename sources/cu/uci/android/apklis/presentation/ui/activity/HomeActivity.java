package cu.uci.android.apklis.presentation.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.blankj.utilcode.util.StringUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.startapp.sdk.adsbase.StartAppAd;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.model.GroupType;
import cu.uci.android.apklis.presentation.presenter.CategoryFragmentPresenter;
import cu.uci.android.apklis.presentation.ui.adapter.ViewPagerAdapter;
import cu.uci.android.apklis.presentation.ui.fragment.AllAppFragment;
import cu.uci.android.apklis.presentation.ui.fragment.CategoryFragment;
import cu.uci.android.apklis.presentation.ui.fragment.HomeFragment;
import cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment;
import cu.uci.android.apklis.storage.repository.UserRepositoryImpl;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int IO_BUFFER_SIZE = 8989;
    private String TAG;
    private AllAppFragment allAppFragment;
    private Button bt_cancel_sugges;
    private Button bt_sugges;
    private AlertDialog.Builder dialog;
    private EditText et_cause;
    private EditText et_name_sugges;
    private EditText et_url;
    private FloatingActionButton fab;
    private HomeFragment homeFragment;
    private Bitmap imagen;
    MenuItem itemAccount;
    MenuItem itemSuggesApps;
    MenuItem itemUserApps;
    MenuItem itemWhishList;
    private Bitmap mIcon_val;
    private RadioGroup myRadioGroup;
    private NavigationView navigationView;
    ViewPagerAdapter pagerAdapter;
    private String path3;
    private CategoryFragmentPresenter presenter;
    private RadioButton rb_aplicacion;
    private RadioButton rb_game;
    MaterialSearchView searchView;
    TabLayout tabLayout;
    private UpdateAppFragment updateAppFragment;
    ViewPager viewPager;
    BehaviorSubject<ArrayList<AppDetails>> behaviorSubject = BehaviorSubject.createDefault(new ArrayList());
    private MenuItem lastItem = null;
    private final int groupCategoryId = 1111111111;

    private void init() {
        TabLayout.Tab tabAt;
        this.searchView = (MaterialSearchView) findViewById(R.id.search_view);
        this.viewPager = (ViewPager) findViewById(R.id.viewPager_home);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout_home_activity);
        this.fab = (FloatingActionButton) findViewById(R.id.fab);
        this.allAppFragment = AllAppFragment.createInstance();
        this.homeFragment = HomeFragment.createInstance();
        this.updateAppFragment = UpdateAppFragment.createInstance();
        this.pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this.behaviorSubject);
        this.pagerAdapter.addFragment(this.homeFragment, getString(R.string.home_tab_title));
        this.pagerAdapter.addFragment(this.updateAppFragment, getString(R.string.updates_apps_tab_title));
        this.pagerAdapter.addFragment(CategoryFragment.createInstance(), getString(R.string.categories));
        this.pagerAdapter.notifyDataSetChanged();
        this.viewPager.setOffscreenPageLimit(3);
        this.viewPager.setAdapter(this.pagerAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.HomeActivity.1
            @Override // com.miguelcatalan.materialsearchview.MaterialSearchView.OnQueryTextListener
            public boolean onQueryTextChange(String str) {
                MainApp.log("Text changed");
                return false;
            }

            @Override // com.miguelcatalan.materialsearchview.MaterialSearchView.OnQueryTextListener
            public boolean onQueryTextSubmit(String str) {
                try {
                    Intent intent = new Intent(HomeActivity.this, (Class<?>) AppListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("ACTION_PARAM", AppListActivity.ACTION_SEARCH_VALUE.intValue());
                    bundle.putString(AppListActivity.TITLE_PARAM, str);
                    bundle.putString(AppListActivity.QUERY_PARAM, str);
                    intent.putExtras(bundle);
                    HomeActivity.this.startActivity(intent);
                } catch (Exception e) {
                    MainApp.log(getClass().getName(), e);
                }
                MainApp.log("Text submited");
                return false;
            }
        });
        if (!getIntent().hasExtra("id_intent_notf") || (tabAt = this.tabLayout.getTabAt(1)) == null) {
            return;
        }
        tabAt.select();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean validateData() {
        boolean z;
        if (this.et_name_sugges.getText().length() <= 0) {
            this.et_name_sugges.setError(getString(R.string.error_field));
            z = false;
        } else {
            z = true;
        }
        if (this.et_url.getText().length() > 0) {
            if (!Pattern.compile("^((https?|http)://|(www)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$").matcher(this.et_url.getText()).find()) {
                this.et_url.setError(getString(R.string.error_url));
            }
            z = false;
        }
        if (this.et_cause.getText().length() <= 0) {
            this.et_cause.setError(getString(R.string.error_field));
            z = false;
        }
        if (this.rb_aplicacion.isChecked() || this.rb_game.isChecked()) {
            return z;
        }
        this.rb_aplicacion.setError(getString(R.string.error_field_radio));
        this.rb_game.setError(getString(R.string.error_field_radio));
        return false;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            return;
        }
        if (this.searchView.isSearchOpen()) {
            this.searchView.closeSearch();
            return;
        }
        boolean z = true;
        if (this.viewPager.getCurrentItem() != 0) {
            this.viewPager.setCurrentItem(0, true);
            z = false;
        }
        if (this.tabLayout.getTabAt(0).getText().toString() != getString(R.string.home_tab_title)) {
            ((HomeFragment) ((ViewPagerAdapter) this.viewPager.getAdapter()).getItem(0)).loadGroups();
            this.tabLayout.getTabAt(0).setText(R.string.home_tab_title);
            z = false;
        }
        if (z) {
            StartAppAd.onBackPressed(this);
            super.onBackPressed();
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        this.navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.navigationView.setNavigationItemSelectedListener(this);
        this.navigationView.setItemIconTintList(null);
        this.itemAccount = this.navigationView.getMenu().findItem(R.id.nav_account);
        this.itemUserApps = this.navigationView.getMenu().findItem(R.id.nav_user_apps);
        this.itemSuggesApps = this.navigationView.getMenu().findItem(R.id.nav_sugges_apps);
        this.itemWhishList = this.navigationView.getMenu().findItem(R.id.nav_wish_lis);
        init();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        this.searchView.setMenuItem(menu.findItem(R.id.action_search));
        return true;
    }

    @Override // android.support.design.widget.NavigationView.OnNavigationItemSelectedListener
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        if (this.lastItem != null) {
            this.lastItem.setChecked(false);
        }
        this.lastItem = menuItem;
        int itemId = menuItem.getItemId();
        menuItem.setChecked(true);
        if (menuItem.getGroupId() == R.id.nav_group_account) {
            if (itemId == R.id.nav_account) {
                if (MainApp.userAccount == null || StringUtils.isEmpty(MainApp.userAccount.getAccessToken())) {
                    startActivity(new Intent(this, (Class<?>) AccountActivity.class));
                } else {
                    Intent intent = new Intent(this, (Class<?>) ProfileActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(ProfileActivity.name, MainApp.userAccount.getFirstName());
                    bundle.putString(ProfileActivity.lastName, MainApp.userAccount.getLastName());
                    bundle.putString(ProfileActivity.password, MainApp.userAccount.getPassword());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            } else if (itemId == R.id.nav_user_apps) {
                if (MainApp.userAccount != null && MainApp.userAccount.getAccessToken() != null && MainApp.userAccount.getAccessToken().length() > 0) {
                    Intent intent2 = new Intent(this, (Class<?>) AppListActivity.class);
                    Bundle bundle2 = new Bundle();
                    bundle2.putInt("ACTION_PARAM", AppListActivity.ACTION_USER_APPS_VALUE.intValue());
                    bundle2.putString(AppListActivity.TITLE_PARAM, getString(R.string.text_user_apps));
                    bundle2.putString(AppListActivity.QUERY_PARAM, MainApp.userAccount.getUsername());
                    intent2.putExtras(bundle2);
                    startActivity(intent2);
                }
            } else if (itemId == R.id.nav_sugges_apps) {
                if (MainApp.userAccount != null && MainApp.userAccount.getAccessToken() != null && MainApp.userAccount.getAccessToken().length() > 0) {
                    LayoutInflater layoutInflater = getLayoutInflater();
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    View inflate = layoutInflater.inflate(R.layout.content_sugges, (ViewGroup) null);
                    this.dialog = new AlertDialog.Builder(this);
                    builder.setView(inflate);
                    final AlertDialog create = builder.create();
                    this.et_name_sugges = (EditText) inflate.findViewById(R.id.et_name_sugges);
                    this.et_url = (EditText) inflate.findViewById(R.id.et_url);
                    this.et_cause = (EditText) inflate.findViewById(R.id.et_cause);
                    this.myRadioGroup = (RadioGroup) inflate.findViewById(R.id.myRadioGroup);
                    this.rb_aplicacion = (RadioButton) inflate.findViewById(R.id.rb_aplicacion);
                    this.rb_game = (RadioButton) inflate.findViewById(R.id.rb_game);
                    this.bt_sugges = (Button) inflate.findViewById(R.id.bt_sugges);
                    this.bt_cancel_sugges = (Button) inflate.findViewById(R.id.bt_cancel_sugges);
                    this.bt_sugges.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.HomeActivity.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            if (!HomeActivity.this.validateData()) {
                                Log.e("onClick: ", "Datos INCORRECTOS");
                                return;
                            }
                            UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();
                            HomeActivity.this.myRadioGroup.getCheckedRadioButtonId();
                            userRepositoryImpl.suggest(HomeActivity.this.et_cause.getText().toString(), HomeActivity.this.rb_game.isChecked() ? "Juego" : "Aplicación", HomeActivity.this.et_name_sugges.getText().toString());
                            create.dismiss();
                            new AwesomeSuccessDialog(HomeActivity.this).setTitle(R.string.app_name).setMessage("Sugerencia Enviada").setColoredCircle(R.color.dialogSuccessBackgroundColor).setDialogIconAndColor(R.drawable.ic_success, R.color.white).setCancelable(true).setPositiveButtonText(HomeActivity.this.getString(R.string.dialog_ok_button)).setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor).setPositiveButtonTextColor(R.color.white).setNegativeButtonbackgroundColor(R.color.dialogSuccessBackgroundColor).setNegativeButtonTextColor(R.color.white).setPositiveButtonClick(new Closure() { // from class: cu.uci.android.apklis.presentation.ui.activity.HomeActivity.2.1
                                @Override // com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure
                                public void exec() {
                                }
                            }).show();
                        }
                    });
                    this.bt_cancel_sugges.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.HomeActivity.3
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            create.dismiss();
                        }
                    });
                    create.show();
                }
            } else if (itemId == R.id.nav_wish_lis && MainApp.userAccount != null && MainApp.userAccount.getAccessToken() != null && MainApp.userAccount.getAccessToken().length() > 0) {
                Intent intent3 = new Intent(this, (Class<?>) AppListActivity.class);
                Bundle bundle3 = new Bundle();
                bundle3.putInt("ACTION_PARAM", AppListActivity.ACTION_FAVORITE_APPS_VALUE.intValue());
                bundle3.putString(AppListActivity.TITLE_PARAM, getString(R.string.text_favorite_apps));
                bundle3.putString(AppListActivity.QUERY_PARAM, MainApp.userAccount.getUsername());
                intent3.putExtras(bundle3);
                startActivity(intent3);
            }
        } else if (menuItem.getGroupId() == R.id.nav_group_collections) {
            Intent intent4 = new Intent(this, (Class<?>) AppListActivity.class);
            Bundle bundle4 = new Bundle();
            bundle4.putInt("ACTION_PARAM", AppListActivity.ACTION_COLLECTION_VALUE.intValue());
            if (menuItem.getItemId() == R.id.nav_recommended_applications) {
                GroupType groupType = GroupType.RECOMMENDED_APPLICATIONS;
                bundle4.putString(AppListActivity.TITLE_PARAM, getString(groupType.resTitle()));
                bundle4.putString(AppListActivity.QUERY_PARAM, groupType.value() + "");
            } else if (menuItem.getItemId() == R.id.nav_cuban_games_applications) {
                GroupType groupType2 = GroupType.CUBAN_GAMES_APPLICATIONS;
                bundle4.putString(AppListActivity.TITLE_PARAM, getString(groupType2.resTitle()));
                bundle4.putString(AppListActivity.QUERY_PARAM, groupType2.value() + "");
            } else if (menuItem.getItemId() == R.id.nav_latest_updates) {
                GroupType groupType3 = GroupType.LATEST_UPDATES;
                bundle4.putString(AppListActivity.TITLE_PARAM, getString(groupType3.resTitle()));
                bundle4.putString(AppListActivity.QUERY_PARAM, groupType3.value() + "");
            } else if (menuItem.getItemId() == R.id.nav_fashion_game_applications) {
                GroupType groupType4 = GroupType.FASHION_GAME_APPLICATIONS;
                bundle4.putString(AppListActivity.TITLE_PARAM, getString(groupType4.resTitle()));
                bundle4.putString(AppListActivity.QUERY_PARAM, groupType4.value() + "");
            } else if (menuItem.getItemId() == R.id.nav_recent_releases) {
                GroupType groupType5 = GroupType.RECENT_RELEASES;
                bundle4.putString(AppListActivity.TITLE_PARAM, getString(groupType5.resTitle()));
                bundle4.putString(AppListActivity.QUERY_PARAM, groupType5.value() + "");
            } else if (menuItem.getItemId() == R.id.nav_best_applications) {
                GroupType groupType6 = GroupType.BEST_APPLICATIONS;
                bundle4.putString(AppListActivity.TITLE_PARAM, getString(groupType6.resTitle()));
                bundle4.putString(AppListActivity.QUERY_PARAM, groupType6.value() + "");
            } else if (menuItem.getItemId() == R.id.nav_best_games) {
                GroupType groupType7 = GroupType.BEST_GAMES;
                bundle4.putString(AppListActivity.TITLE_PARAM, getString(groupType7.resTitle()));
                bundle4.putString(AppListActivity.QUERY_PARAM, groupType7.value() + "");
            } else if (menuItem.getItemId() == R.id.nav_tools_utilities) {
                GroupType groupType8 = GroupType.TOOLS_UTILITIES;
                bundle4.putString(AppListActivity.TITLE_PARAM, getString(groupType8.resTitle()));
                bundle4.putString(AppListActivity.QUERY_PARAM, groupType8.value() + "");
            }
            intent4.putExtras(bundle4);
            startActivity(intent4);
        } else if (menuItem.getGroupId() == 1111111111) {
            Intent intent5 = new Intent(this, (Class<?>) AppListActivity.class);
            Bundle bundle5 = new Bundle();
            bundle5.putInt("ACTION_PARAM", AppListActivity.ACTION_CATEGORY_VALUE.intValue());
            String charSequence = menuItem.getTitle().toString();
            Integer valueOf = Integer.valueOf(menuItem.getItemId());
            bundle5.putString(AppListActivity.TITLE_PARAM, charSequence);
            bundle5.putString(AppListActivity.QUERY_PARAM, valueOf + "");
            intent5.putExtras(bundle5);
            startActivity(intent5);
        } else if (menuItem.getGroupId() == R.id.nav_group_info) {
            if (itemId == R.id.nav_term_condition) {
                Intent intent6 = new Intent(this, (Class<?>) InfoActivity.class);
                Bundle bundle6 = new Bundle();
                bundle6.putInt("ACTION_PARAM", InfoActivity.ACTION_TERMS_CONDITIONS_VALUE.intValue());
                intent6.putExtras(bundle6);
                startActivity(intent6);
            } else if (itemId == R.id.nav_about) {
                Intent intent7 = new Intent(this, (Class<?>) InfoActivity.class);
                Bundle bundle7 = new Bundle();
                bundle7.putInt("ACTION_PARAM", InfoActivity.ACTION_ABOUT_VALUE.intValue());
                intent7.putExtras(bundle7);
                startActivity(intent7);
            } else if (itemId == R.id.nav_config) {
                startActivity(new Intent(this, (Class<?>) SettingActivity.class));
            }
        } else if (menuItem.getGroupId() == R.id.nav_group_xapklis) {
            if (itemId == R.id.nav_xapklis) {
                startActivity(new Intent(this, (Class<?>) XapklisActivity.class));
            }
        } else if (menuItem.getGroupId() == R.id.nav_group_siguenos) {
            if (itemId == R.id.nav_facebook) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/apklis/")));
                finish();
            }
            if (itemId == R.id.nav_twitter) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/Apklis_Cuba")));
                finish();
            }
            if (itemId == R.id.nav_telegram) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://t.me/apklis")));
                finish();
            }
        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        TabLayout.Tab tabAt;
        super.onNewIntent(intent);
        if (!intent.hasExtra("id_intent_notf") || (tabAt = this.tabLayout.getTabAt(1)) == null) {
            return;
        }
        tabAt.select();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        menuItem.getItemId();
        return super.onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (MainApp.userAccount == null || MainApp.userAccount.getAccessToken() == null || MainApp.userAccount.getAccessToken().length() <= 0) {
            this.itemAccount.setTitle(getString(R.string.text_login));
            this.itemUserApps.setVisible(false);
            this.itemSuggesApps.setVisible(false);
            this.itemWhishList.setVisible(false);
            return;
        }
        this.itemAccount.setTitle(MainApp.userAccount.getFirstName());
        this.itemUserApps.setVisible(true);
        this.itemSuggesApps.setVisible(true);
        this.itemWhishList.setVisible(true);
    }
}
