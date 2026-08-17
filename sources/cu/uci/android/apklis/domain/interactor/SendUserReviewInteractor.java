package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;
import cu.uci.android.apklis.presentation.model.Review;

/* loaded from: classes.dex */
public interface SendUserReviewInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void onReviewEdited(Review review);

        void onReviewSent(Review review);
    }
}
