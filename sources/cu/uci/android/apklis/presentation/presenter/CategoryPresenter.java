package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.model.CategoryView;
import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;
import java.util.ArrayList;

/* loaded from: classes.dex */
public interface CategoryPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void onCategoriesReturned(ArrayList<CategoryView> arrayList);
    }

    void getCategories();
}
