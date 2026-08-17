package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.ChangePasswordInteractor;
import cu.uci.android.apklis.domain.interactor.impl.ChangePasswordInteractorImpl;
import cu.uci.android.apklis.presentation.presenter.ChangePasswordPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class ChangePasswordPresenterImpl extends AbstractPresenter implements ChangePasswordPresenter, ChangePasswordInteractor.Callback {
    ChangePasswordPresenter.View mView;

    public ChangePasswordPresenterImpl(Executor executor, MainThread mainThread, ChangePasswordPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ChangePasswordPresenter
    public void changePassword(String str, String str2) {
        this.mView.showProgress();
        new ChangePasswordInteractorImpl(this.mExecutor, this.mMainThread, this, str, str2).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
    }

    @Override // cu.uci.android.apklis.domain.interactor.ChangePasswordInteractor.Callback
    public void passwordChanged() {
        this.mView.hideProgress();
        this.mView.passwordChanged();
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
