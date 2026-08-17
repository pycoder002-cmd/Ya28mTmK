package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.BuyApplicationdInteractor;
import cu.uci.android.apklis.domain.interactor.impl.BuyApplicactionInteractorImpl;
import cu.uci.android.apklis.presentation.presenter.BuyApplicationPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class BuyApplicationPresenterImpl extends AbstractPresenter implements BuyApplicationPresenter, BuyApplicationdInteractor.Callback {
    BuyApplicationPresenter.View mView;

    public BuyApplicationPresenterImpl(Executor executor, MainThread mainThread, BuyApplicationPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.domain.interactor.BuyApplicationdInteractor.Callback
    public void buyAplication() {
        this.mView.buyApplication();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.BuyApplicationPresenter
    public String buyApplication(Integer num) {
        this.mView.showProgress();
        new BuyApplicactionInteractorImpl(this.mExecutor, this.mMainThread, this, num).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
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
