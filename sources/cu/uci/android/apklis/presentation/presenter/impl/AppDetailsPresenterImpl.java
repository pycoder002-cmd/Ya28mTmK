package cu.uci.android.apklis.presentation.presenter.impl;

import android.view.View;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CancelAppDownloadInteractor;
import cu.uci.android.apklis.domain.interactor.CheckAppDownloadInteractor;
import cu.uci.android.apklis.domain.interactor.GetAppStatusInteractor;
import cu.uci.android.apklis.domain.interactor.InstallAppInteractor;
import cu.uci.android.apklis.domain.interactor.OpenAppInteractor;
import cu.uci.android.apklis.domain.interactor.UninstallAppInteractor;
import cu.uci.android.apklis.domain.interactor.impl.CancelAppDownloadInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.CheckAppDownloadInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.GetAppStatusInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.OpenAppInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.UninstallAppInteractorImpl;
import cu.uci.android.apklis.presentation.model.AppStatus;
import cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class AppDetailsPresenterImpl extends AbstractPresenter implements AppDetailsPresenter, CheckAppDownloadInteractor.Callback, GetAppStatusInteractor.Callback, OpenAppInteractor.Callback, UninstallAppInteractor.Callback, InstallAppInteractor.Callback, CancelAppDownloadInteractor.Callback {
    View View;
    AppDetailsPresenter.View mView;

    public AppDetailsPresenterImpl(Executor executor, MainThread mainThread, View view) {
        super(executor, mainThread);
        this.View = view;
    }

    public AppDetailsPresenterImpl(Executor executor, MainThread mainThread, AppDetailsPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter
    public void cancelDownload(String str) {
        new CancelAppDownloadInteractorImpl(this.mExecutor, this.mMainThread, this, str).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter
    public void checkAppDownload(String str) {
        this.mView.showProgress();
        new CheckAppDownloadInteractorImpl(this.mExecutor, this.mMainThread, this, str).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter
    public void getAppStatus(String str, String str2, Double d) {
        new GetAppStatusInteractorImpl(this.mExecutor, this.mMainThread, this, str, str2, d).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter
    public void installApp(String str, String str2, String str3, String str4, String str5, String str6) {
        new InstallAppInteractorImpl(this.mExecutor, this.mMainThread, this, str, str2, str3, str4, str5, str6).execute();
    }

    @Override // cu.uci.android.apklis.domain.interactor.CheckAppDownloadInteractor.Callback
    public void onAppDownloadChecked(String str, Long l) {
        this.mView.onAppDownloadChecked(l);
    }

    @Override // cu.uci.android.apklis.domain.interactor.GetAppStatusInteractor.Callback
    public void onAppStatusReturned(AppStatus appStatus) {
        this.mView.onAppStatusReturned(appStatus);
    }

    @Override // cu.uci.android.apklis.domain.interactor.InstallAppInteractor.Callback
    public void onDownloadError(String str) {
        this.mView.onDownloadError();
    }

    @Override // cu.uci.android.apklis.domain.interactor.InstallAppInteractor.Callback
    public void onDownloadStart(String str, long j) {
        this.mView.onDownloadStart(Long.valueOf(j));
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter
    public void openApp(String str) {
        new OpenAppInteractorImpl(this.mExecutor, this.mMainThread, this, str).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void pause() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void resume() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void stop() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AppDetailsPresenter
    public void uninstallApp(String str) {
        new UninstallAppInteractorImpl(this.mExecutor, this.mMainThread, this, str).execute();
    }
}
