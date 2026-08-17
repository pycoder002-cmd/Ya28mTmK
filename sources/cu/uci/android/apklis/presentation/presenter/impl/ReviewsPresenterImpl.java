package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.DeleteUserReviewInteractor;
import cu.uci.android.apklis.domain.interactor.GetReviewsByAppInRangeInteractor;
import cu.uci.android.apklis.domain.interactor.GetReviewsByUsernameInteractor;
import cu.uci.android.apklis.domain.interactor.SendUserReviewInteractor;
import cu.uci.android.apklis.domain.interactor.impl.DeleteUserReviewInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.GetReviewsByAppInRangeInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.GetReviewsByUsernameInteractorImpl;
import cu.uci.android.apklis.domain.interactor.impl.SendUserReviewInteractorImpl;
import cu.uci.android.apklis.presentation.converter.ReviewModelConverter;
import cu.uci.android.apklis.presentation.model.Review;
import cu.uci.android.apklis.presentation.presenter.ReviewsPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;
import java.util.List;

/* loaded from: classes.dex */
public class ReviewsPresenterImpl extends AbstractPresenter implements ReviewsPresenter, GetReviewsByAppInRangeInteractor.Callback, GetReviewsByUsernameInteractor.Callback, SendUserReviewInteractor.Callback, DeleteUserReviewInteractor.Callback {
    ReviewsPresenter.View mView;

    public ReviewsPresenterImpl(Executor executor, MainThread mainThread, ReviewsPresenter.View view) {
        super(executor, mainThread);
        this.mView = view;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ReviewsPresenter
    public void deleteReview(Integer num) {
        new DeleteUserReviewInteractorImpl(this.mExecutor, this.mMainThread, this, num).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ReviewsPresenter
    public void editReview(Integer num, Integer num2, String str) {
        new SendUserReviewInteractorImpl(this.mExecutor, this.mMainThread, this, num, num2, str, true).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ReviewsPresenter
    public List<Review> getReviewsByAppInRange(int i, int i2, Integer num, Integer num2) {
        if (i == 0) {
            this.mView.showProgress();
        }
        new GetReviewsByAppInRangeInteractorImpl(this.mExecutor, this.mMainThread, this, i, i2, num, num2).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ReviewsPresenter
    public Review getReviewsByUsername(Integer num, Integer num2) {
        new GetReviewsByUsernameInteractorImpl(this.mExecutor, this.mMainThread, this, num, num2).execute();
        return null;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
    }

    @Override // cu.uci.android.apklis.domain.interactor.DeleteUserReviewInteractor.Callback
    public void onReviewDeleted(boolean z) {
        this.mView.onReviewDeleted(z);
    }

    @Override // cu.uci.android.apklis.domain.interactor.SendUserReviewInteractor.Callback
    public void onReviewEdited(Review review) {
        if (review == null) {
            this.mView.showError("");
        } else {
            this.mView.onUserReviewLoaded(review);
        }
    }

    @Override // cu.uci.android.apklis.domain.interactor.GetReviewsByUsernameInteractor.Callback
    public void onReviewLoaded(Review review) {
        this.mView.onUserReviewLoaded(review);
    }

    @Override // cu.uci.android.apklis.domain.interactor.SendUserReviewInteractor.Callback
    public void onReviewSent(Review review) {
        if (review == null) {
            this.mView.showError(null);
        }
        this.mView.onUserReviewLoaded(review);
    }

    @Override // cu.uci.android.apklis.domain.interactor.GetReviewsByAppInRangeInteractor.Callback
    public void onReviewsLoaded(Review[] reviewArr) {
        this.mView.onReviewsLoaded(ReviewModelConverter.convertToViewModelList(reviewArr));
        this.mView.hideProgress();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void pause() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void resume() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ReviewsPresenter
    public void sendReview(Integer num, Integer num2, String str) {
        new SendUserReviewInteractorImpl(this.mExecutor, this.mMainThread, this, num, num2, str, false).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void stop() {
    }
}
