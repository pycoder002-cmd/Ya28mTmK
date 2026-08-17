package cu.uci.android.apklis.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.permissionrequest.PermissionRequest;
import com.example.permissionrequest.PermissionRequestListener;
import com.google.android.gms.common.api.GoogleApiClient;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.presentation.presenter.SplashPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.SplashPresenterImpl;
import cu.uci.android.apklis.threading.MainThreadImpl;

/* loaded from: classes.dex */
public class SplashActivity extends AppCompatActivity implements SplashPresenter.View {
    static DisplayMetrics dm;
    LinearLayout check;
    private GoogleApiClient client;
    ImageView ivLogoSplash;
    SplashPresenter presenter;
    ProgressBar progressBar;
    TextView text;

    private void checkConnection() {
        if (MainApp.getUserStatus() == MainApp.USER_STATUS_AUTENTICATED) {
            PermissionRequest.requestPermission("android.permission.GET_ACCOUNTS", getString(R.string.get_account_permission_name), this).withListener(new PermissionRequestListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.SplashActivity.3
                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionAcept(String str) {
                    SplashActivity.this.executeCheckConection();
                }

                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionDeny(String str) {
                }

                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionDenyPermanent(String str) {
                }
            }).check();
        } else {
            executeCheckConection();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeCheckConection() {
        this.presenter.checkConnection();
    }

    private void init() {
        this.text = (TextView) findViewById(R.id.text);
        this.check = (LinearLayout) findViewById(R.id.btn_check);
        this.ivLogoSplash = (ImageView) findViewById(R.id.ivLogoSplash);
        this.check.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.SplashActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SplashActivity.this.checkConection();
            }
        });
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.presenter = new SplashPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        checkConnection();
    }

    public static void setDisplayMetrics(boolean z) {
        double d;
        int i;
        try {
            if (z) {
                i = dm.heightPixels;
                int i2 = dm.widthPixels;
            } else {
                i = dm.widthPixels;
                int i3 = dm.heightPixels;
            }
            d = i / dm.xdpi;
        } catch (NullPointerException e) {
            MainApp.log(AppDetailsActivity.class.getName(), e);
            e.printStackTrace();
            d = 3.0d;
        }
        MainApp.SPAN_COUNT_RECYCLER_GRID_VIEW = Math.max(3, (int) (d / 0.6399999856948853d));
        MainApp.SPAN_COUNT_RECYCLER_GRID_VIEW_CATEGORY = Math.max(2, (int) (d / 0.9599999785423279d));
        MainApp.APPS_LOAD_RANGE = MainApp.SPAN_COUNT_RECYCLER_GRID_VIEW * 4;
        MainApp.APPS_LOAD_IN_GROUP = MainApp.SPAN_COUNT_RECYCLER_GRID_VIEW * 3;
    }

    public void checkConection() {
        checkConnection();
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
        this.progressBar.setVisibility(8);
        this.ivLogoSplash.setVisibility(8);
        this.text.setVisibility(0);
        this.check.setVisibility(0);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.SplashPresenter.View
    public void offline() {
        hideProgress();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        setDisplayMetrics(false);
        init();
        this.text.setOnKeyListener(new View.OnKeyListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.SplashActivity.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.SplashPresenter.View
    public void online() {
        startActivity(new Intent(this, (Class<?>) HomeActivity.class));
        finish();
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
        this.text.setVisibility(8);
        this.check.setVisibility(8);
        this.ivLogoSplash.setVisibility(0);
        this.progressBar.setVisibility(0);
    }
}
