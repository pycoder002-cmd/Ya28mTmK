package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;
import cu.uci.android.apklis.domain.model.App;

/* loaded from: classes.dex */
public interface GetAppsToUpdateInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void onAppsToUpdateLoaded(App[] appArr);
    }
}
