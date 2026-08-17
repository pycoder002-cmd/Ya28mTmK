package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;
import cu.uci.android.apklis.domain.model.UserAccount;

/* loaded from: classes.dex */
public interface SignUpUserInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void onServerError();

        void onUserDataError(UserAccount userAccount);

        void onUserRegistered(UserAccount userAccount, boolean z);
    }
}
