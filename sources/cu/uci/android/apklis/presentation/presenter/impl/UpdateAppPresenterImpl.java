package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.device.Service.ServiceDownload;
import cu.uci.android.apklis.domain.ApiRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CheckAppDownloadInteractor;
import cu.uci.android.apklis.domain.interactor.GetAppsToUpdateInteractor;
import cu.uci.android.apklis.domain.interactor.InstallAppInteractor;
import cu.uci.android.apklis.domain.interactor.impl.CheckAppDownloadInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.GetAppsToUpdateInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.InstallAppInteractorImpl;
import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.presentation.converter.AppDetailsModelConverter;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class UpdateAppPresenterImpl extends AbstractPresenter implements UpdateAppPresenter, GetAppsToUpdateInteractor.Callback, InstallAppInteractor.Callback, CheckAppDownloadInteractor.Callback {
    UpdateAppPresenter.View mView;
    ApiRepository repository;

    public UpdateAppPresenterImpl(Executor executor, MainThread mainThread, UpdateAppPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter
    public void cancelDownloadUpdate(String str) {
        ServiceDownload.cancelDownload(str);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter
    public void checkAppDownload(String str) {
        new CheckAppDownloadInteractorImpl(this.mExecutor, this.mMainThread, this, str).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter
    public void getAppsToUpdate() {
        this.mView.showProgress();
        new GetAppsToUpdateInteractorImpl(this.mExecutor, this.mMainThread, this).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter
    public void installApp(String str, String str2, String str3, String str4, String str5, String str6) {
        new InstallAppInteractorImpl(this.mExecutor, this.mMainThread, this, str, str2, str3, str4, str5, str6).execute();
    }

    @Override // cu.uci.android.apklis.domain.interactor.CheckAppDownloadInteractor.Callback
    public void onAppDownloadChecked(String str, Long l) {
        if (l != null) {
            this.mView.onDownloadStart(str, l);
        }
    }

    @Override // cu.uci.android.apklis.domain.interactor.GetAppsToUpdateInteractor.Callback
    public void onAppsToUpdateLoaded(App[] appArr) {
        ArrayList<AppDetails> convertToViewModelList = AppDetailsModelConverter.convertToViewModelList(appArr);
        this.mView.hideProgress();
        this.mView.onAppsToUpdateLoaded(convertToViewModelList);
    }

    @Override // cu.uci.android.apklis.domain.interactor.InstallAppInteractor.Callback
    public void onDownloadError(String str) {
        if (str != null) {
            try {
                onDownloadError(str);
            } catch (Exception e) {
                MainApp.log(getClass().getName(), e);
                e.printStackTrace();
            }
        }
    }

    @Override // cu.uci.android.apklis.domain.interactor.InstallAppInteractor.Callback
    public void onDownloadStart(String str, long j) {
        this.mView.onDownloadStart(str, Long.valueOf(j));
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
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
}
