package cu.uci.android.apklis.presentation.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.startapp.sdk.adsbase.StartAppAd;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.ui.adapter.ViewPagerAdapter;
import cu.uci.android.apklis.presentation.ui.fragment.AllAppFragment;
import cu.uci.android.apklis.presentation.ui.fragment.CategoryFragment;
import cu.uci.android.apklis.presentation.ui.fragment.HomeFragment;
import cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment;

/* loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    private AllAppFragment allAppFragment;
    private HomeFragment homeFragment;
    MaterialSearchView searchView;
    TabLayout tabLayout;
    private UpdateAppFragment updateAppFragment;
    ViewPager viewPager;

    private void init() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        this.allAppFragment = AllAppFragment.createInstance();
        this.homeFragment = HomeFragment.createInstance();
        this.updateAppFragment = UpdateAppFragment.createInstance();
        try {
            viewPagerAdapter.addFragment(this.homeFragment, getString(R.string.home_tab_title));
            viewPagerAdapter.addFragment(this.updateAppFragment, getString(R.string.updates_apps_tab_title));
            viewPagerAdapter.addFragment(CategoryFragment.createInstance(), getString(R.string.categories));
            viewPagerAdapter.notifyDataSetChanged();
            this.viewPager.setOffscreenPageLimit(3);
            this.viewPager.setAdapter(viewPagerAdapter);
            this.tabLayout.setupWithViewPager(this.viewPager);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.searchView.isSearchOpen()) {
            this.searchView.closeSearch();
        } else {
            StartAppAd.onBackPressed(this);
            super.onBackPressed();
        }
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.viewPager = (ViewPager) findViewById(R.id.viewPager_home);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout_home_activity);
        this.searchView = (MaterialSearchView) findViewById(R.id.search_view_activity);
        setSupportActionBar(toolbar);
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
