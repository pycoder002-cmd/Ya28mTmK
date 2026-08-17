package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CheckConnectionInteractor;
import cu.uci.android.apklis.domain.interactor.CheckUserAccountInfoInteractor;
import cu.uci.android.apklis.domain.interactor.impl.CheckConnectionInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.CheckUserAccountInfoInteractorImpl;
import cu.uci.android.apklis.presentation.presenter.SplashPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class SplashPresenterImpl extends AbstractPresenter implements SplashPresenter, CheckConnectionInteractor.Callback, CheckUserAccountInfoInteractor.Callback {
    SplashPresenter.View mView;

    public SplashPresenterImpl(Executor executor, MainThread mainThread, SplashPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.SplashPresenter
    public void checkConnection() {
        this.mView.showProgress();
        new CheckConnectionInteractorImpl(this.mExecutor, this.mMainThread, this).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.domain.interactor.CheckConnectionInteractor.Callback
    public void offline() {
        this.mView.offline();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
    }

    @Override // cu.uci.android.apklis.domain.interactor.CheckUserAccountInfoInteractor.Callback
    public void onUserAccountInfoLoaded(Boolean bool) {
        this.mView.online();
    }

    @Override // cu.uci.android.apklis.domain.interactor.CheckConnectionInteractor.Callback
    public void online() {
        new CheckUserAccountInfoInteractorImpl(this.mExecutor, this.mMainThread, this, null, null).execute();
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
