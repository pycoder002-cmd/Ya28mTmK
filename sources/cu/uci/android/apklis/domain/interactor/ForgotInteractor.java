package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;

/* loaded from: classes.dex */
public interface ForgotInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void hideProgress();

        void onReceiveToken(boolean z);

        void onServerError();

        void onUserActivated(boolean z);
    }
}
