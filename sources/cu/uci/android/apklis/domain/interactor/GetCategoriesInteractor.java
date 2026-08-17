package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;
import cu.uci.android.apklis.domain.model.Category;

/* loaded from: classes.dex */
public interface GetCategoriesInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void onCategoriesReturned(Category[] categoryArr);
    }
}
