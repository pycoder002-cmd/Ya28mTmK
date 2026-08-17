package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.device.Service.ServiceDownload;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CheckAppDownloadInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;

/* loaded from: classes.dex */
public class CheckAppDownloadInteractorImpl extends AbstractInteractor implements CheckAppDownloadInteractor {
    private CheckAppDownloadInteractor.Callback mCallback;
    private String package_name;

    public CheckAppDownloadInteractorImpl(Executor executor, MainThread mainThread, CheckAppDownloadInteractor.Callback callback, String str) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.package_name = str;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        final Long downloadIdByPackageName = ServiceDownload.getDownloadIdByPackageName(this.package_name);
        this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.CheckAppDownloadInteractorImpl.1
            @Override // java.lang.Runnable
            public void run() {
                CheckAppDownloadInteractorImpl.this.mCallback.onAppDownloadChecked(CheckAppDownloadInteractorImpl.this.package_name, downloadIdByPackageName);
            }
        });
        return null;
    }
}
