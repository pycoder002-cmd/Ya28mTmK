package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;

/* loaded from: classes.dex */
public interface ChangePasswordPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void passwordChanged();
    }

    void changePassword(String str, String str2);
}
