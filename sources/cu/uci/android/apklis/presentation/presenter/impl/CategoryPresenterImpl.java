package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetCategoriesInteractor;
import cu.uci.android.apklis.domain.interactor.impl.GetCategoriesInteractorImpl;
import cu.uci.android.apklis.domain.model.Category;
import cu.uci.android.apklis.presentation.converter.CategoryViewModelConverter;
import cu.uci.android.apklis.presentation.presenter.CategoryPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;

/* loaded from: classes.dex */
public class CategoryPresenterImpl extends AbstractPresenter implements CategoryPresenter, GetCategoriesInteractor.Callback {
    CategoryPresenter.View mView;

    public CategoryPresenterImpl(Executor executor, MainThread mainThread, CategoryPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.CategoryPresenter
    public void getCategories() {
        new GetCategoriesInteractorImpl(this.mExecutor, this.mMainThread, this).execute();
    }

    @Override // cu.uci.android.apklis.domain.interactor.GetCategoriesInteractor.Callback
    public void onCategoriesReturned(Category[] categoryArr) {
        this.mView.onCategoriesReturned(CategoryViewModelConverter.convertToViewModelList(categoryArr));
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
