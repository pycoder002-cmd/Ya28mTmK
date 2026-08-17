package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;

/* loaded from: classes.dex */
public interface NewPasswordPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void onServerError();

        void wrong_pass();
    }

    void change_password_whit_tk(String str, String str2, String str3);
}
