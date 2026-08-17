package cu.uci.android.apklis.domain.interactor;

import cu.uci.android.apklis.domain.interactor.base.Interactor;

/* loaded from: classes.dex */
public interface InstallAppInteractor extends Interactor {

    /* loaded from: classes.dex */
    public interface Callback {
        void onDownloadError(String str);

        void onDownloadStart(String str, long j);
    }
}
