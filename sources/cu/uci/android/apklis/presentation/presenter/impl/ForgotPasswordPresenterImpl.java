package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.UserRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.ForgotInteractor;
import cu.uci.android.apklis.domain.interactor.impl.ForgotInteractorImpl;
import cu.uci.android.apklis.presentation.presenter.ForgotPasswordPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;
import cu.uci.android.apklis.presentation.ui.dialog.ForgotPasswordDialog;
import cu.uci.android.apklis.presentation.ui.dialog.NewPasswordDialog;
import cu.uci.android.apklis.storage.repository.UserRepositoryImpl;

/* loaded from: classes.dex */
public class ForgotPasswordPresenterImpl extends AbstractPresenter implements ForgotPasswordPresenter, ForgotInteractor.Callback {
    ForgotPasswordDialog mView;
    UserRepository repository;

    public ForgotPasswordPresenterImpl(Executor executor, MainThread mainThread, ForgotPasswordDialog forgotPasswordDialog, UserRepository userRepository) {
        super(executor, mainThread);
        this.mView = forgotPasswordDialog;
        this.repository = userRepository;
    }

    public ForgotPasswordPresenterImpl(Executor executor, MainThread mainThread, NewPasswordDialog newPasswordDialog, UserRepositoryImpl userRepositoryImpl) {
        super(executor, mainThread);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.domain.interactor.ForgotInteractor.Callback
    public void hideProgress() {
        this.mView.hideProgress();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
    }

    @Override // cu.uci.android.apklis.domain.interactor.ForgotInteractor.Callback
    public void onReceiveToken(boolean z) {
        this.mView.hideProgress();
        this.mView.onReceiveToken(z);
    }

    @Override // cu.uci.android.apklis.domain.interactor.ForgotInteractor.Callback
    public void onServerError() {
        this.mView.hideProgress();
        this.mView.onServerError();
    }

    @Override // cu.uci.android.apklis.domain.interactor.ForgotInteractor.Callback
    public void onUserActivated(boolean z) {
        this.mView.hideProgress();
        this.mView.onReceiveToken(z);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void pause() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ForgotPasswordPresenter
    public void receiveToken(String str) {
        this.mView.showProgress();
        new ForgotInteractorImpl(this.mExecutor, this.mMainThread, this, this.repository, str).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void resume() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void stop() {
    }
}
