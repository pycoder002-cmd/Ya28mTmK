package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.device.AccountHelper;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CloseSessionInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;

/* loaded from: classes.dex */
public class CloseSessionInteractorImpl extends AbstractInteractor implements CloseSessionInteractor {
    private CloseSessionInteractor.Callback mCallback;

    public CloseSessionInteractorImpl(Executor executor, MainThread mainThread, CloseSessionInteractor.Callback callback) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        AccountHelper.clearAccounts(MainApp.context, new AccountHelper.OnAccountListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.CloseSessionInteractorImpl.1
            @Override // cu.uci.android.apklis.device.AccountHelper.OnAccountListener
            public void onFinished() {
                CloseSessionInteractorImpl.this.mCallback.goClose();
            }
        });
        return null;
    }
}
