package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.model.Review;
import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public interface ReviewsPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void onReviewDeleted(boolean z);

        void onReviewSent(Review review);

        void onReviewsLoaded(ArrayList<Review> arrayList);

        void onUserReviewLoaded(Review review);
    }

    void deleteReview(Integer num);

    void editReview(Integer num, Integer num2, String str);

    List<Review> getReviewsByAppInRange(int i, int i2, Integer num, Integer num2);

    Review getReviewsByUsername(Integer num, Integer num2);

    void sendReview(Integer num, Integer num2, String str);
}
