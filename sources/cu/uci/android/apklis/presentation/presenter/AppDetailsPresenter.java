package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.model.AppStatus;
import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;

/* loaded from: classes.dex */
public interface AppDetailsPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void onAppDetailsReturned(AppDetails appDetails);

        void onAppDownloadChecked(Long l);

        void onAppStatusReturned(AppStatus appStatus);

        void onDownloadError();

        void onDownloadStart(Long l);
    }

    void cancelDownload(String str);

    void checkAppDownload(String str);

    void getAppStatus(String str, String str2, Double d);

    void installApp(String str, String str2, String str3, String str4, String str5, String str6);

    void openApp(String str);

    void uninstallApp(String str);
}
