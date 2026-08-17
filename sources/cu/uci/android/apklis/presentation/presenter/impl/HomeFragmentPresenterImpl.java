package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.ApiRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetAppsInRangeByGroupInteractor;
import cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeByGroupInteractorImpl;
import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.presentation.converter.AppDetailsModelConverter;
import cu.uci.android.apklis.presentation.model.AppView;
import cu.uci.android.apklis.presentation.presenter.HomeFragmentPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;
import java.util.List;

/* loaded from: classes.dex */
public class HomeFragmentPresenterImpl extends AbstractPresenter implements HomeFragmentPresenter, GetAppsInRangeByGroupInteractor.Callback {
    HomeFragmentPresenter.View mView;
    ApiRepository repository;

    public HomeFragmentPresenterImpl(Executor executor, MainThread mainThread, HomeFragmentPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.HomeFragmentPresenter
    public List<AppView> getAppsInGroup(int i, int i2, int i3) {
        new GetAppsInRangeByGroupInteractorImpl(this.mExecutor, this.mMainThread, this, i, i2, i3).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.domain.interactor.GetAppsInRangeByGroupInteractor.Callback
    public void onAppsRetrieved(App[] appArr, int i, boolean z) {
        this.mView.onAppsRetrived(AppDetailsModelConverter.convertToViewModelList(appArr), i, z);
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
