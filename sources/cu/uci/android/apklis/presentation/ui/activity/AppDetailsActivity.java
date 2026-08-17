package cu.uci.android.apklis.presentation.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.downloader.PRDownloader;
import com.example.permissionrequest.PermissionRequest;
import com.example.permissionrequest.PermissionRequestListener;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.gson.Gson;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import com.squareup.picasso.Picasso;
import com.startapp.sdk.adsbase.StartAppAd;
import com.stepstone.apprating.listener.RatingDialogListener;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.device.PackageManagerHelper;
import cu.uci.android.apklis.device.Service.DownloadListener;
import cu.uci.android.apklis.device.Service.ServiceDownload;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.domain.model.JsonTransfermovil;
import cu.uci.android.apklis.domain.model.Release;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.model.AppStatus;
import cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter;
import cu.uci.android.apklis.presentation.presenter.BuyApplicationPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.AppDetailsPresenterImpl;
import cu.uci.android.apklis.presentation.presenter.impl.BuyApplicationPresenterImpl;
import cu.uci.android.apklis.presentation.ui.adapter.ViewPagerAdapter;
import cu.uci.android.apklis.presentation.ui.fragment.GeneralAppDetailsFragment;
import cu.uci.android.apklis.presentation.ui.fragment.ReviewAppDetailsFragment;
import cu.uci.android.apklis.presentation.ui.util.LabelLayout;
import cu.uci.android.apklis.threading.MainThreadImpl;
import java.io.File;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes.dex */
public class AppDetailsActivity extends AppCompatActivity implements AppDetailsPresenter.View, RatingDialogListener, BuyApplicationPresenter.View {
    LinearLayout actionsLayout;
    private AppDetails app;
    AppBarLayout appBarLayout;
    private String appPackageName;
    TextView author;
    LinearLayout btnBuy;
    LinearLayout btnCancelDownload;
    LinearLayout btnInstall;
    LinearLayout btnOpen;
    LinearLayout btnUninstall;
    LinearLayout btnUpdate;
    private BuyApplicationPresenter buyPresenter;
    TextView date;
    ProgressBar downloadProgressBar;
    TextView downloadText;
    private GeneralAppDetailsFragment generalAppDetailsFragment;
    ImageView icon;
    ImageView iv_apklis;
    private ImageView iv_wish_list;
    private AppDetailsPresenter presenter;
    LabelLayout price;
    ProgressDialog progressDialog;
    RelativeLayout progressLayout;
    TextView rating;
    private ReviewAppDetailsFragment reviewAppDetailsFragment;
    SimpleRatingBar simpleRatingBar;
    TabLayout tabLayout;
    TextView textBuy;
    TextView title;
    ViewPager viewPager;
    private String listenerId = null;
    private Boolean checkAppStatus = false;

    /* JADX INFO: Access modifiers changed from: private */
    public void checkAppStatus() {
        if (this.app == null || !(this.app.getLastRelease() instanceof Release)) {
            return;
        }
        this.presenter.getAppStatus(this.appPackageName, this.app.getLastRelease().getPackage_versionCode(), this.app.getPrice());
    }

    private void downloadInstallApp() {
        if (this.app != null) {
            PermissionRequest.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.get_account_permission_name), this).withListener(new PermissionRequestListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.8
                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionAcept(String str) {
                    AppDetailsActivity.this.installApp();
                }

                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionDeny(String str) {
                }

                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionDenyPermanent(String str) {
                }
            }).check();
        }
    }

    private void fillAppInfo() {
        try {
            this.author.setText(this.app.getAuthor());
            this.date.setText(this.app.getLastRelease().getPublishedAtFormated());
            if (this.app.getLastRelease() instanceof Release) {
                this.presenter.getAppStatus(this.appPackageName, this.app.getLastRelease().getPackage_versionCode(), this.app.getPrice());
            }
        } catch (Exception e) {
            MainApp.log(getClass().getName(), e);
            MainApp.log(e.getMessage());
        }
    }

    private void fillViewPager() {
        this.generalAppDetailsFragment = GeneralAppDetailsFragment.createInstance(this.app);
        this.reviewAppDetailsFragment = ReviewAppDetailsFragment.createInstance(this.app);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(this.generalAppDetailsFragment, getString(R.string.details_app_tab_title));
        viewPagerAdapter.addFragment(this.reviewAppDetailsFragment, getString(R.string.review_app_tab_title));
        this.viewPager.setAdapter(viewPagerAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ifButtonIsPayment(Integer num) {
    }

    @RequiresApi(api = 21)
    private void init() {
        this.price = (LabelLayout) findViewById(R.id.ivIconApp);
        this.icon = (ImageView) findViewById(R.id.app_icon);
        this.iv_apklis = (ImageView) findViewById(R.id.iv_apklis);
        this.title = (TextView) findViewById(R.id.title);
        this.author = (TextView) findViewById(R.id.author);
        this.date = (TextView) findViewById(R.id.date);
        this.date.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.simpleRatingBar = (SimpleRatingBar) findViewById(R.id.simpleRatingBar);
        this.rating = (TextView) findViewById(R.id.rating);
        this.actionsLayout = (LinearLayout) findViewById(R.id.actionsLayout);
        this.btnInstall = (LinearLayout) findViewById(R.id.btn_install);
        PRDownloader.initialize(getApplicationContext());
        this.btnInstall.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppDetailsActivity.this.onClickInstall();
            }
        });
        this.btnBuy = (LinearLayout) findViewById(R.id.btn_buy);
        this.btnBuy.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppDetailsActivity.this.ifButtonIsPayment(AppDetailsActivity.this.app.getId());
            }
        });
        this.textBuy = (TextView) findViewById(R.id.text_buy);
        this.btnUninstall = (LinearLayout) findViewById(R.id.btn_uninstall);
        this.btnUninstall.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppDetailsActivity.this.onClickUninstall(view);
            }
        });
        this.btnOpen = (LinearLayout) findViewById(R.id.btn_open);
        this.btnOpen.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppDetailsActivity.this.onClickOpen(view);
            }
        });
        this.btnUpdate = (LinearLayout) findViewById(R.id.btn_update);
        this.btnUpdate.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppDetailsActivity.this.onClickUpdate();
            }
        });
        this.progressLayout = (RelativeLayout) findViewById(R.id.progressLayout);
        this.downloadText = (TextView) findViewById(R.id.download_text);
        this.downloadProgressBar = (ProgressBar) findViewById(R.id.downloadProgressBar);
        this.btnCancelDownload = (LinearLayout) findViewById(R.id.btn_cancel_download);
        this.btnCancelDownload.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AppDetailsActivity.this.onClickCancelDownload();
            }
        });
        this.viewPager = (ViewPager) findViewById(R.id.viewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        this.appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        this.progressDialog = new ProgressDialog(this);
        Gson gson = new Gson();
        if (getIntent().getExtras() != null) {
            this.app = (AppDetails) gson.fromJson(getIntent().getExtras().getString("APP_JSON_PARAM"), AppDetails.class);
            this.appPackageName = this.app.getPackageName();
            setTitle(this.app.getName());
            this.title.setText(this.app.getName());
            this.author.setText(this.app.getAuthor());
            this.date.setText(this.app.getLastRelease().getPublishedAtFormated());
            Float valueOf = Float.valueOf(Float.parseFloat(this.app.getRating()));
            String format = String.format(Locale.US, "(%.1f)", valueOf);
            this.simpleRatingBar.setRating(valueOf.floatValue());
            this.rating.setText(format);
            try {
                String extension = this.app.getExtension();
                Picasso.get().load(Uri.parse(this.app.getIcon())).placeholder(R.drawable.ic_apk_default).error(R.drawable.ic_apk_default).into(this.icon);
                if (extension.split("\\?")[0].equals(".apklis")) {
                    this.iv_apklis.setVisibility(0);
                }
            } catch (Exception unused) {
                this.icon.setImageResource(R.drawable.ic_apk_default);
            }
        }
        this.presenter = new AppDetailsPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.presenter.checkAppDownload(this.appPackageName);
        fillViewPager();
    }

    public static String toString(JsonTransfermovil jsonTransfermovil) {
        return new Gson().toJson(jsonTransfermovil);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.BuyApplicationPresenter.View
    public void buyApplication() {
        this.progressDialog.show();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.BuyApplicationPresenter.View
    public void closeApplication() {
        this.progressDialog.hide();
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
    }

    public void installApp() {
        String downloadUrl = this.app.getLastRelease().getDownloadUrl();
        SharedPreferencesManager.getInstance().putValue(this.app.getPackageName(), this.app.getLastRelease().getDownloadSha256());
        String str = ((String) SharedPreferencesManager.getInstance().getValue(MainApp.DOWNLOAD_DIRECTORY, String.class, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "")) + File.separator + this.appPackageName + this.app.getLastRelease().getPackage_versionName() + this.app.getExtension();
        if (!new File(str).exists()) {
            this.presenter.installApp(downloadUrl, this.app.getName(), this.app.getLastRelease().getPackage_versionName(), this.app.getPackageName(), this.app.getLastRelease().getDownloadSha256(), this.app.getExtension());
            return;
        }
        this.checkAppStatus = true;
        PackageManagerHelper.installAppByUri(this.app.getPackageName(), "file://" + str);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter.View
    public void onAppDetailsReturned(AppDetails appDetails) {
        this.app = appDetails;
        fillAppInfo();
        fillViewPager();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter.View
    public void onAppDownloadChecked(Long l) {
        if (l != null) {
            onDownloadStart(l);
        } else {
            checkAppStatus();
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter.View
    public void onAppStatusReturned(AppStatus appStatus) {
        this.progressLayout.setVisibility(8);
        this.actionsLayout.setVisibility(0);
        switch (appStatus) {
            case INSTALLED:
                this.btnUpdate.setVisibility(8);
                this.btnInstall.setVisibility(8);
                this.btnBuy.setVisibility(8);
                this.btnCancelDownload.setVisibility(8);
                this.btnUninstall.setVisibility(0);
                this.btnOpen.setVisibility(0);
                if (((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.ENABLE_AUTODELETE_APK, Boolean.class, false)).booleanValue()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append((String) SharedPreferencesManager.getInstance().getValue(MainApp.DOWNLOAD_DIRECTORY, String.class, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + ""));
                    sb.append(File.separator);
                    sb.append(this.appPackageName);
                    sb.append(this.app.getLastRelease().getPackage_versionName());
                    sb.append(".apk");
                    PackageManagerHelper.deleteApp(sb.toString());
                    return;
                }
                return;
            case NOT_INSTALLED:
                this.btnUpdate.setVisibility(8);
                this.btnOpen.setVisibility(8);
                this.btnUninstall.setVisibility(8);
                this.btnBuy.setVisibility(8);
                this.btnCancelDownload.setVisibility(8);
                this.btnInstall.setVisibility(0);
                return;
            case UPDATABLE:
                this.btnInstall.setVisibility(8);
                this.btnBuy.setVisibility(8);
                this.btnCancelDownload.setVisibility(8);
                this.btnUninstall.setVisibility(8);
                this.btnOpen.setVisibility(0);
                this.btnUpdate.setVisibility(0);
                return;
            case NOT_FOUND:
                this.btnUpdate.setVisibility(8);
                this.btnOpen.setVisibility(8);
                this.btnUninstall.setVisibility(8);
                this.btnInstall.setVisibility(8);
                this.btnBuy.setVisibility(8);
                this.btnCancelDownload.setVisibility(8);
                return;
            case BUY:
                this.btnUpdate.setVisibility(8);
                this.btnOpen.setVisibility(8);
                this.btnUninstall.setVisibility(8);
                this.btnInstall.setVisibility(8);
                this.btnCancelDownload.setVisibility(8);
                this.btnBuy.setVisibility(0);
                this.textBuy.setText("$" + this.app.getPriceString() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getString(R.string.buy));
                return;
            default:
                return;
        }
    }

    public void onClickBou(View view) {
        AppDetails appDetails = this.app;
    }

    public void onClickCancelDownload() {
        this.presenter.cancelDownload(this.appPackageName);
    }

    public void onClickInstall() {
        downloadInstallApp();
    }

    public void onClickOpen(View view) {
        if (this.app != null) {
            this.presenter.openApp(this.app.getPackageName());
        }
    }

    public void onClickUninstall(View view) {
        if (this.app != null) {
            this.presenter.uninstallApp(this.app.getPackageName());
            this.checkAppStatus = true;
        }
    }

    public void onClickUpdate() {
        downloadInstallApp();
    }

    @RequiresApi(api = 21)
    public void onConfigurationChanged() {
        int i = getResources().getConfiguration().orientation;
        Log.d("CHANGESCREEN", "Orientation: " + i);
        switch (i) {
            case 1:
                init();
                return;
            case 2:
                init();
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    @RequiresApi(api = 21)
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_app_details);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        this.buyPresenter = new BuyApplicationPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.listenerId != null) {
            ServiceDownload.removeListener(this.listenerId);
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter.View
    public void onDownloadError() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter.View
    public void onDownloadStart(Long l) {
        this.btnUpdate.setVisibility(8);
        this.btnOpen.setVisibility(8);
        this.btnUninstall.setVisibility(8);
        this.btnInstall.setVisibility(8);
        this.btnBuy.setVisibility(8);
        this.btnCancelDownload.setVisibility(0);
        this.downloadText.setText(R.string.zero_percent);
        DownloadListener downloadListener = new DownloadListener(this.app.getPackageName(), new DownloadListener.DownloadStatusListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.9
            @Override // cu.uci.android.apklis.device.Service.DownloadListener.DownloadStatusListener
            public void onDownloadClosed() {
                AppDetailsActivity.this.runOnUiThread(new Runnable() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.9.3
                    @Override // java.lang.Runnable
                    public void run() {
                        AppDetailsActivity.this.listenerId = null;
                        AppDetailsActivity.this.btnCancelDownload.setVisibility(8);
                        AppDetailsActivity.this.downloadText.setText(R.string.zero_percent);
                        AppDetailsActivity.this.checkAppStatus = true;
                        AppDetailsActivity.this.checkAppStatus();
                    }
                });
            }

            @Override // cu.uci.android.apklis.device.Service.DownloadListener.DownloadStatusListener
            public void onDownloadPending() {
                AppDetailsActivity.this.runOnUiThread(new Runnable() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.9.1
                    @Override // java.lang.Runnable
                    public void run() {
                        AppDetailsActivity.this.downloadText.setText(R.string.zero_percent);
                    }
                });
            }

            @Override // cu.uci.android.apklis.device.Service.DownloadListener.DownloadStatusListener
            public void onDownloadProgressChange(final int i) {
                AppDetailsActivity.this.runOnUiThread(new Runnable() { // from class: cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity.9.2
                    @Override // java.lang.Runnable
                    public void run() {
                        int i2 = i;
                        if (i2 < 0) {
                            i2 = 0;
                        }
                        if (i2 > 100) {
                            i2 = 100;
                        }
                        AppDetailsActivity.this.downloadText.setText(i2 + "%");
                    }
                });
            }
        });
        ServiceDownload.addListener(downloadListener);
        this.listenerId = downloadListener.getId();
        if (Build.VERSION.SDK_INT == 26) {
            MainApp.context.startForegroundService(new Intent(this, (Class<?>) ServiceDownload.class));
        } else {
            startService(new Intent(this, (Class<?>) ServiceDownload.class));
        }
    }

    @Override // com.stepstone.apprating.listener.RatingDialogListener
    public void onNegativeButtonClicked() {
    }

    @Override // com.stepstone.apprating.listener.RatingDialogListener
    public void onNeutralButtonClicked() {
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

    @Override // com.stepstone.apprating.listener.RatingDialogListener
    public void onPositiveButtonClicked(int i, @NotNull String str) {
        this.reviewAppDetailsFragment.onPositiveButtonClicked(i, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.checkAppStatus.booleanValue()) {
            checkAppStatus();
            this.checkAppStatus = false;
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        MainApp.log(str);
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
    }
}
