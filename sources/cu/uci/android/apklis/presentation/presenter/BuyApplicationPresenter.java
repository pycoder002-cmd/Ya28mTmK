package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;

/* loaded from: classes.dex */
public interface BuyApplicationPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void buyApplication();

        void closeApplication();
    }

    String buyApplication(Integer num);
}
