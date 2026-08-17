package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.device.Service.ServiceDownload;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CancelAppDownloadInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.presentation.presenter.impl.UpdateAppPresenterImpl;

/* loaded from: classes.dex */
public class CancelAppDownloadInteractorImpl extends AbstractInteractor implements CancelAppDownloadInteractor {
    private CancelAppDownloadInteractor.Callback mCallback;
    private String packageName;
    private String package_name;
    private UpdateAppPresenterImpl updateAppPresenter;

    public CancelAppDownloadInteractorImpl(Executor executor, MainThread mainThread, CancelAppDownloadInteractor.Callback callback, String str) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.package_name = str;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        ServiceDownload.cancelDownload(this.package_name);
        return null;
    }
}
