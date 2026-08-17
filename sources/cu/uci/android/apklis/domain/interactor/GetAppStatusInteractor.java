package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;
import cu.uci.android.apklis.presentation.model.AppStatus;

/* loaded from: classes.dex */
public interface GetAppStatusInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void onAppStatusReturned(AppStatus appStatus);
    }
}
