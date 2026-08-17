package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.AddWhisListInteractor;
import cu.uci.android.apklis.presentation.presenter.AddWishListPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.AddWishListInteractorImpl;
import cu.uci.android.apklis.presentation.ui.fragment.HomeFragment;

/* loaded from: classes.dex */
public class AddWishListImpl extends AbstractPresenter implements AddWishListPresenter, AddWhisListInteractor.Callback {
    HomeFragment homeFragment;
    AddWishListPresenter.View mView;

    public AddWishListImpl(Executor executor, MainThread mainThread, AddWishListPresenter.View view) {
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

    @Override // cu.uci.android.apklis.presentation.presenter.AddWishListPresenter
    public void saveWhisList(String str, int[] iArr, String str2) {
        new AddWishListInteractorImpl(this.mExecutor, this.mMainThread, this, str, iArr, str2).execute();
    }

    @Override // cu.uci.android.apklis.domain.interactor.AddWhisListInteractor.Callback
    public void sendWishList() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void stop() {
    }
}
