package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;

/* loaded from: classes.dex */
public interface ActivateAccountPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void onServerError();

        void onUserActivated(boolean z);
    }

    void activateAccount(String str);
}
