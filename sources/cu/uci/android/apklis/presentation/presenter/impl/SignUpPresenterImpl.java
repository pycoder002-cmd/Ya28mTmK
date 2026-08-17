package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.UserRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.SignUpUserInteractor;
import cu.uci.android.apklis.domain.interactor.impl.SignUpUserInteractorImpl;
import cu.uci.android.apklis.domain.model.UserAccount;
import cu.uci.android.apklis.presentation.presenter.SignUpPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class SignUpPresenterImpl extends AbstractPresenter implements SignUpPresenter, SignUpUserInteractor.Callback {
    SignUpPresenter.View mView;
    UserRepository repository;

    public SignUpPresenterImpl(Executor executor, MainThread mainThread, SignUpPresenter.View view, UserRepository userRepository) {
        super(executor, mainThread);
        this.mView = view;
        this.repository = userRepository;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
    }

    @Override // cu.uci.android.apklis.domain.interactor.SignUpUserInteractor.Callback
    public void onServerError() {
        this.mView.hideProgress();
        this.mView.onServerError();
    }

    @Override // cu.uci.android.apklis.domain.interactor.SignUpUserInteractor.Callback
    public void onUserDataError(UserAccount userAccount) {
        this.mView.hideProgress();
        this.mView.onUserDataError(userAccount);
    }

    @Override // cu.uci.android.apklis.domain.interactor.SignUpUserInteractor.Callback
    public void onUserRegistered(UserAccount userAccount, boolean z) {
        if (z) {
            MainApp.setUserStatus(MainApp.USER_STATUS_NORMAL, null);
        } else {
            MainApp.setUserStatus(MainApp.USER_STATUS_REQUEST_ACTIVATION, userAccount);
        }
        this.mView.hideProgress();
        this.mView.onUserRegistered(userAccount, z);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void pause() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void resume() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.SignUpPresenter
    public void signUp(String str, String str2, String str3, String str4, String str5) {
        this.mView.showProgress();
        new SignUpUserInteractorImpl(this.mExecutor, this.mMainThread, this, this.repository, str, str2, str3, str4, str5).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void stop() {
    }
}
