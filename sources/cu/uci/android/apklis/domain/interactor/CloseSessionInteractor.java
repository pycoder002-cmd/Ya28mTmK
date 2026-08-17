package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;

/* loaded from: classes.dex */
public interface CloseSessionInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void goClose();

        void goKeep();
    }
}
