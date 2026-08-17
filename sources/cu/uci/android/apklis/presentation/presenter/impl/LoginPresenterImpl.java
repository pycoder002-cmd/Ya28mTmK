package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CheckUserAccountInfoInteractor;
import cu.uci.android.apklis.domain.interactor.impl.CheckUserAccountInfoInteractorImpl;
import cu.uci.android.apklis.presentation.presenter.LoginPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class LoginPresenterImpl extends AbstractPresenter implements LoginPresenter, CheckUserAccountInfoInteractor.Callback {
    LoginPresenter.View mView;

    public LoginPresenterImpl(Executor executor, MainThread mainThread, LoginPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.LoginPresenter
    public void checkUserAccount(String str, String str2) {
        this.mView.showProgress();
        new CheckUserAccountInfoInteractorImpl(this.mExecutor, this.mMainThread, this, str, str2).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
    }

    @Override // cu.uci.android.apklis.domain.interactor.CheckUserAccountInfoInteractor.Callback
    public void onUserAccountInfoLoaded(Boolean bool) {
        if (!bool.booleanValue()) {
            this.mView.hideProgress();
        }
        this.mView.onUserAccountChecked(bool);
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
