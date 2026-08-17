package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.domain.model.UserAccount;
import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;

/* loaded from: classes.dex */
public interface SignUpPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void onServerError();

        void onUserDataError(UserAccount userAccount);

        void onUserRegistered(UserAccount userAccount, boolean z);
    }

    void signUp(String str, String str2, String str3, String str4, String str5);
}
