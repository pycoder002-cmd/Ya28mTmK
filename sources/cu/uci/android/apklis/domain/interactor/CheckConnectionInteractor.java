package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;

/* loaded from: classes.dex */
public interface CheckConnectionInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void offline();

        void online();
    }
}
