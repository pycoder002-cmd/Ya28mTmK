package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.UserRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.ActivateAccountInteractor;
import cu.uci.android.apklis.domain.interactor.impl.ActivateAccountInteractorImpl;
import cu.uci.android.apklis.presentation.presenter.ActivateAccountPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class ActivateAccountPresenterImpl extends AbstractPresenter implements ActivateAccountPresenter, ActivateAccountInteractor.Callback {
    ActivateAccountPresenter.View mView;
    UserRepository repository;

    public ActivateAccountPresenterImpl(Executor executor, MainThread mainThread, ActivateAccountPresenter.View view, UserRepository userRepository) {
        super(executor, mainThread);
        this.mView = view;
        this.repository = userRepository;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ActivateAccountPresenter
    public void activateAccount(String str) {
        this.mView.showProgress();
        new ActivateAccountInteractorImpl(this.mExecutor, this.mMainThread, this, this.repository, str).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
    }

    @Override // cu.uci.android.apklis.domain.interactor.ActivateAccountInteractor.Callback
    public void onServerError() {
        this.mView.hideProgress();
        this.mView.onServerError();
    }

    @Override // cu.uci.android.apklis.domain.interactor.ActivateAccountInteractor.Callback
    public void onUserActivated(boolean z) {
        this.mView.hideProgress();
        this.mView.onUserActivated(z);
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
