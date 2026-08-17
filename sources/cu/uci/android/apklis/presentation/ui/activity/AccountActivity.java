package cu.uci.android.apklis.presentation.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.startapp.sdk.adsbase.StartAppAd;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.ui.adapter.ViewPagerAdapter;
import cu.uci.android.apklis.presentation.ui.fragment.SignInFragment;
import cu.uci.android.apklis.presentation.ui.fragment.SignUpFragment;

/* loaded from: classes.dex */
public class AccountActivity extends AppCompatActivity {
    ImageView btnBack;
    SmartTabLayout smartTabLayout;
    ViewPager viewPager;

    private void init() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(SignInFragment.createInstance(), getString(R.string.signin_title));
        viewPagerAdapter.addFragment(SignUpFragment.createInstance(), getString(R.string.signup_title));
        this.viewPager.setAdapter(viewPagerAdapter);
        this.smartTabLayout.setViewPager(this.viewPager);
    }

    public void back(View view) {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

    public void goToSignIn() {
        if (!(((ViewPagerAdapter) this.viewPager.getAdapter()).getItem(0) instanceof SignInFragment) || this.viewPager.getCurrentItem() == 0) {
            return;
        }
        this.viewPager.setCurrentItem(0, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        this.btnBack = (ImageView) findViewById(R.id.btn_back);
        this.smartTabLayout = (SmartTabLayout) findViewById(R.id.smart_tab_layout);
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        this.btnBack.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AccountActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AccountActivity.this.back(view);
            }
        });
        init();
    }
}
