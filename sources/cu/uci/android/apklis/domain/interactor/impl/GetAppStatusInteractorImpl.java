package cu.uci.android.apklis.domain.interactor.impl;

import com.github.mikephil.charting.utils.Utils;
import cu.uci.android.apklis.device.PackageManagerHelper;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetAppStatusInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.presentation.model.AppStatus;

/* loaded from: classes.dex */
public class GetAppStatusInteractorImpl extends AbstractInteractor implements GetAppStatusInteractor {
    private GetAppStatusInteractor.Callback mCallback;
    private String package_name;
    private String package_versionCode;
    private Double price;

    public GetAppStatusInteractorImpl(Executor executor, MainThread mainThread, GetAppStatusInteractor.Callback callback, String str, String str2, Double d) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.package_name = str;
        this.package_versionCode = str2;
        this.price = d;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        final AppStatus appStatus = PackageManagerHelper.getAppStatus(this.package_name, this.package_versionCode);
        if (appStatus.equals(AppStatus.NOT_INSTALLED) && this.price.doubleValue() > Utils.DOUBLE_EPSILON) {
            appStatus = AppStatus.BUY;
        }
        this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppStatusInteractorImpl.1
            @Override // java.lang.Runnable
            public void run() {
                GetAppStatusInteractorImpl.this.mCallback.onAppStatusReturned(appStatus);
            }
        });
        return null;
    }
}
