package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.UpImageProfileInteractor;
import cu.uci.android.apklis.presentation.presenter.UpImageProfilePresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.UpImageProfileInteractorImpl;

/* loaded from: classes.dex */
public class UpImagePresenterImpl extends AbstractPresenter implements UpImageProfilePresenter, UpImageProfileInteractor.Callback {
    UpImageProfilePresenter.View mView;

    public UpImagePresenterImpl(Executor executor, MainThread mainThread, UpImageProfilePresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
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

    @Override // cu.uci.android.apklis.domain.interactor.UpImageProfileInteractor.Callback
    public void saveImage() {
        this.mView.hideProgress();
        this.mView.saveImage();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpImageProfilePresenter
    public void saveImage(String str, String str2) {
        this.mView.showProgress();
        new UpImageProfileInteractorImpl(this.mExecutor, this.mMainThread, this, str, str2).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void stop() {
    }
}
