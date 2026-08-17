package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;

/* loaded from: classes.dex */
public interface NewPasswordInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void hideProgress();

        void onServerError();

        void showMessage(String str);
    }
}
