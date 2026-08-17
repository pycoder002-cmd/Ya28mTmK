package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.SaveProfileInteractor;
import cu.uci.android.apklis.domain.interactor.impl.SaveProfileInteractorImpl;
import cu.uci.android.apklis.presentation.presenter.SaveProfilePresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class SaveProfilePresenterImpl extends AbstractPresenter implements SaveProfilePresenter, SaveProfileInteractor.Callback {
    SaveProfilePresenter.View mView;

    public SaveProfilePresenterImpl(Executor executor, MainThread mainThread, SaveProfilePresenter.View view) {
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

    @Override // cu.uci.android.apklis.domain.interactor.SaveProfileInteractor.Callback
    public void profileChanged() {
        this.mView.hideProgress();
        this.mView.profileChanged();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void resume() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.SaveProfilePresenter
    public void saveProfile(String str, String str2, String str3) {
        this.mView.showProgress();
        new SaveProfileInteractorImpl(this.mExecutor, this.mMainThread, this, str, str2, str3).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void stop() {
    }
}
