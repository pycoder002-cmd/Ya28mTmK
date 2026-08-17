package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CloseSessionInteractor;
import cu.uci.android.apklis.domain.interactor.impl.CloseSessionInteractorImpl;
import cu.uci.android.apklis.presentation.presenter.CloseSessionPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class CloseSessionPresenterImpl extends AbstractPresenter implements CloseSessionPresenter, CloseSessionInteractor.Callback {
    CloseSessionPresenter.View mView;

    public CloseSessionPresenterImpl(Executor executor, MainThread mainThread, CloseSessionPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.CloseSessionPresenter
    public void closeSession() {
        this.mView.showProgress();
        new CloseSessionInteractorImpl(this.mExecutor, this.mMainThread, this).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.domain.interactor.CloseSessionInteractor.Callback
    public void goClose() {
        this.mView.goClose();
    }

    @Override // cu.uci.android.apklis.domain.interactor.CloseSessionInteractor.Callback
    public void goKeep() {
        this.mView.goKeep();
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
