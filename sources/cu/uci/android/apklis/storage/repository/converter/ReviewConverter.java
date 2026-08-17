package cu.uci.android.apklis.storage.repository.converter;

import cu.uci.android.apklis.presentation.model.Review;
import cu.uci.android.apklis.storage.repository.model.ApiReview;

/* loaded from: classes.dex */
public class ReviewConverter {
    public static Review convertToViewModel(ApiReview apiReview) {
        if (apiReview == null) {
            return null;
        }
        Review review = new Review();
        review.setId(apiReview.getId());
        review.setAvatar(apiReview.getUser().getAvatar());
        review.setFullName(apiReview.getUser().getFullName());
        review.setRating(apiReview.getRating());
        review.setComment(apiReview.getComment());
        review.setPublished(apiReview.getPublished());
        return review;
    }

    public static Review[] convertToViewModel(ApiReview[] apiReviewArr, Integer num) {
        Review[] reviewArr = new Review[apiReviewArr.length];
        for (int i = 0; i < apiReviewArr.length; i++) {
            ApiReview apiReview = apiReviewArr[i];
            if (apiReview.getId() != num) {
                reviewArr[i] = convertToViewModel(apiReview);
            }
        }
        return reviewArr;
    }
}
