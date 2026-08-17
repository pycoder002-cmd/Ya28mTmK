package cu.uci.android.apklis.presentation.converter;

import cu.uci.android.apklis.presentation.model.Review;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class ReviewModelConverter {
    public static Review convertToViewModel(Review review) {
        if (review == null) {
            return null;
        }
        return review;
    }

    public static ArrayList<Review> convertToViewModelList(Review[] reviewArr) {
        ArrayList<Review> arrayList = new ArrayList<>();
        for (Review review : reviewArr) {
            arrayList.add(convertToViewModel(review));
        }
        return arrayList;
    }
}
