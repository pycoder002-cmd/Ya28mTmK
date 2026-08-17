package cu.uci.android.apklis.domain.interactor.impl;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.support.design.internal.NavigationMenuPresenter;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.downloader.Error;
import com.downloader.OnCancelListener;
import com.downloader.OnDownloadListener;
import com.downloader.OnPauseListener;
import com.downloader.OnProgressListener;
import com.downloader.OnStartOrResumeListener;
import com.downloader.PRDownloader;
import com.downloader.PRDownloaderConfig;
import com.downloader.Progress;
import com.google.firebase.appindexing.Indexable;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.device.PackageManagerHelper;
import cu.uci.android.apklis.device.Service.ServiceDownload;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.domain.interactor.InstallAppInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.AppDetailsPresenterImpl;
import cu.uci.android.apklis.presentation.ui.activity.AppDetailsActivity;
import cu.uci.android.apklis.threading.MainThreadImpl;
import java.io.File;

/* loaded from: classes.dex */
public class InstallAppInteractorImpl extends AbstractInteractor implements InstallAppInteractor {
    private Long actualDownloadId1;
    private AppDetails app;
    private AppDetailsActivity appDetailsActivity;
    private String appName;
    private String appPackageName;
    ProgressBar downloadProgressBar;
    TextView downloadText;
    private String extencion;
    private Handler handle;
    private InstallAppInteractor.Callback mCallback;
    AppDetailsPresenter.View mView;
    private NavigationMenuPresenter navigationView;
    private String packageName;
    private AppDetailsPresenter presenter;
    private String shaAppFromServer;
    private String uri;
    private String versionName;
    View view;

    public InstallAppInteractorImpl(Executor executor, MainThread mainThread, InstallAppInteractor.Callback callback, String str, String str2, String str3, String str4, String str5, String str6) {
        super(executor, mainThread);
        this.appDetailsActivity = new AppDetailsActivity();
        this.handle = new Handler();
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.uri = str;
        this.appName = str2;
        this.versionName = str3;
        this.packageName = str4;
        this.shaAppFromServer = str5;
        this.extencion = str6;
    }

    public InstallAppInteractorImpl(Executor executor, MainThread mainThread, AppDetailsPresenter.View view) {
        super(executor, mainThread);
        this.appDetailsActivity = new AppDetailsActivity();
        this.handle = new Handler();
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        this.actualDownloadId1 = ServiceDownload.areDownloading(this.packageName);
        DownloadManager downloadManager = (DownloadManager) MainApp.context.getSystemService("download");
        if (Build.VERSION.SDK_INT == 26) {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(this.uri));
            request.addRequestHeader("X-APPLICATION-ID", this.packageName);
            request.setTitle(this.appName);
            final String str = this.packageName + this.versionName + this.extencion;
            request.setDescription(this.packageName);
            String str2 = (String) SharedPreferencesManager.getInstance().getValue(MainApp.DOWNLOAD_DIRECTORY, String.class, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "");
            Uri.fromFile(new File(str2 + File.separator + str));
            PRDownloaderConfig build = PRDownloaderConfig.newBuilder().setDatabaseEnabled(true).build();
            PRDownloader.initialize(MainApp.context, build);
            downloadManager.query(new DownloadManager.Query());
            PRDownloaderConfig.newBuilder().setReadTimeout(Indexable.MAX_BYTE_SIZE).setConnectTimeout(Indexable.MAX_BYTE_SIZE).build();
            PRDownloader.initialize(MainApp.context, build);
            Long.valueOf(((DownloadManager) MainApp.context.getSystemService("download")).enqueue(new DownloadManager.Request(Uri.parse(this.uri))));
            PRDownloader.download(this.uri, str2, str).build().setOnStartOrResumeListener(new OnStartOrResumeListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl.5
                @Override // com.downloader.OnStartOrResumeListener
                public void onStartOrResume() {
                }
            }).setOnPauseListener(new OnPauseListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl.4
                @Override // com.downloader.OnPauseListener
                public void onPause() {
                }
            }).setOnCancelListener(new OnCancelListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl.3
                @Override // com.downloader.OnCancelListener
                public void onCancel() {
                }
            }).setOnProgressListener(new OnProgressListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl.2
                @Override // com.downloader.OnProgressListener
                public void onProgress(Progress progress) {
                }
            }).start(new OnDownloadListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl.1
                @Override // com.downloader.OnDownloadListener
                public void onDownloadComplete() {
                    InstallAppInteractorImpl.this.presenter = new AppDetailsPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), InstallAppInteractorImpl.this.view);
                    SharedPreferencesManager.getInstance().putValue(InstallAppInteractorImpl.this.packageName, InstallAppInteractorImpl.this.shaAppFromServer);
                    Uri fromFile = Uri.fromFile(new File(((String) SharedPreferencesManager.getInstance().getValue(MainApp.DOWNLOAD_DIRECTORY, String.class, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "")) + File.separator + str));
                    PackageManagerHelper.installAppByUri(InstallAppInteractorImpl.this.packageName, "" + fromFile);
                }

                @Override // com.downloader.OnDownloadListener
                public void onError(Error error) {
                    Toast.makeText(MainApp.context, "Error en la descarga", 0).show();
                }
            });
            return null;
        }
        final Long areDownloading = ServiceDownload.areDownloading(this.packageName);
        if (areDownloading != null) {
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl.8
                @Override // java.lang.Runnable
                public void run() {
                    InstallAppInteractorImpl.this.mCallback.onDownloadStart(InstallAppInteractorImpl.this.packageName, areDownloading.longValue());
                }
            });
            return null;
        }
        try {
            DownloadManager downloadManager2 = (DownloadManager) MainApp.context.getSystemService("download");
            DownloadManager.Request request2 = new DownloadManager.Request(Uri.parse(this.uri));
            request2.addRequestHeader("X-APPLICATION-ID", this.packageName);
            request2.setTitle(this.appName);
            String str3 = this.packageName + this.versionName + this.extencion;
            request2.setDescription(this.packageName);
            String str4 = (String) SharedPreferencesManager.getInstance().getValue(MainApp.DOWNLOAD_DIRECTORY, String.class, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "");
            Uri.fromFile(new File(str4 + File.separator + str3));
            request2.setDestinationUri(Uri.fromFile(new File(str4 + File.separator + str3)));
            final Long valueOf = Long.valueOf(downloadManager2.enqueue(request2));
            ServiceDownload.addDownload(this.packageName, valueOf, this.shaAppFromServer);
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl.6
                @Override // java.lang.Runnable
                public void run() {
                    int i = Build.VERSION.SDK_INT;
                    InstallAppInteractorImpl.this.mCallback.onDownloadStart(InstallAppInteractorImpl.this.packageName, valueOf.longValue());
                }
            });
            return null;
        } catch (Exception e) {
            MainApp.log(getClass().getName(), e);
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl.7
                @Override // java.lang.Runnable
                public void run() {
                    InstallAppInteractorImpl.this.mCallback.onDownloadError(InstallAppInteractorImpl.this.packageName);
                }
            });
            MainApp.log(e.getMessage() == null ? "Null message" : e.getMessage());
            return null;
        }
    }
}
