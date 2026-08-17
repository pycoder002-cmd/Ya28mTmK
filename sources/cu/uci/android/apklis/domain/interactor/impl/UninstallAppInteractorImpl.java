package cu.uci.android.apklis.domain.interactor.impl;

import com.blankj.utilcode.util.AppUtils;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.UninstallAppInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;

/* loaded from: classes.dex */
public class UninstallAppInteractorImpl extends AbstractInteractor implements UninstallAppInteractor {
    private UninstallAppInteractor.Callback mCallback;
    private String package_name;

    public UninstallAppInteractorImpl(Executor executor, MainThread mainThread, UninstallAppInteractor.Callback callback, String str) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.package_name = str;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        try {
            AppUtils.uninstallApp(this.package_name);
            return null;
        } catch (Exception e) {
            MainApp.log(e.getMessage() == null ? "Null message" : e.getMessage());
            return null;
        }
    }
}
