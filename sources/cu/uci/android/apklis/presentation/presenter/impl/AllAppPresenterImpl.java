package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetAppsInRangeByCategoryInteractor;
import cu.uci.android.apklis.domain.interactor.GetAppsInRangeByGroupInteractor;
import cu.uci.android.apklis.domain.interactor.GetAppsInRangeByUserInteractor;
import cu.uci.android.apklis.domain.interactor.GetAppsInRangeInteractor;
import cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeByCategoryInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeByGroupInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeByUserInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.GetAppsPackageNameInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.GetFavoriteAppsInteractorImpl;
import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.presentation.converter.AppDetailsModelConverter;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.model.AppView;
import cu.uci.android.apklis.presentation.presenter.AllAppPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class AllAppPresenterImpl extends AbstractPresenter implements AllAppPresenter, GetAppsInRangeInteractor.Callback, GetAppsInRangeByCategoryInteractor.Callback, GetAppsInRangeByGroupInteractor.Callback, GetAppsInRangeByUserInteractor.Callback {
    AllAppPresenter.View mView;
    private String query;

    public AllAppPresenterImpl(Executor executor, MainThread mainThread, AllAppPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter
    public List<AppView> getAppsByUser(String str) {
        this.mView.showProgress();
        new GetAppsInRangeByUserInteractorImpl(this.mExecutor, this.mMainThread, this, str).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter
    public List<AppView> getAppsInGroup(int i, int i2, int i3) {
        if (i == 0) {
            this.mView.showProgress();
        }
        new GetAppsInRangeByGroupInteractorImpl(this.mExecutor, this.mMainThread, this, i, i2, i3).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter
    public List<AppView> getAppsInRange(int i, int i2, String str) {
        if (i == 0) {
            this.mView.showProgress();
        }
        new GetAppsInRangeInteractorImpl(this.mExecutor, this.mMainThread, this, i, i2, str).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter
    public List<AppView> getAppsInRangeByCategory(int i, int i2, Integer num) {
        if (i == 0) {
            this.mView.showProgress();
        }
        new GetAppsInRangeByCategoryInteractorImpl(this.mExecutor, this.mMainThread, this, i, i2, num).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter
    public List<AppView> getAppsPackageName(int i, int i2, String str) {
        if (i == 0) {
            this.mView.showProgress();
        }
        new GetAppsPackageNameInteractorImpl(this.mExecutor, this.mMainThread, this, i, i2, str).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.AllAppPresenter
    public List<AppView> getFavoriteApps(String str, String str2) {
        this.mView.showProgress();
        new GetFavoriteAppsInteractorImpl(this.mExecutor, this.mMainThread, this, str, str2).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.domain.interactor.GetAppsInRangeInteractor.Callback, cu.uci.android.apklis.domain.interactor.GetAppsInRangeByCategoryInteractor.Callback, cu.uci.android.apklis.domain.interactor.GetAppsInRangeByUserInteractor.Callback
    public void onAppsRetrieved(App[] appArr) {
        ArrayList<AppDetails> convertToViewModelList = AppDetailsModelConverter.convertToViewModelList(appArr);
        this.mView.hideProgress();
        this.mView.onAppsRetrived(convertToViewModelList);
    }

    @Override // cu.uci.android.apklis.domain.interactor.GetAppsInRangeByGroupInteractor.Callback
    public void onAppsRetrieved(App[] appArr, int i, boolean z) {
        ArrayList<AppDetails> convertToViewModelList = AppDetailsModelConverter.convertToViewModelList(appArr);
        this.mView.hideProgress();
        this.mView.onAppsRetrived(convertToViewModelList);
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
