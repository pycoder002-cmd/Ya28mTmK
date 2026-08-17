package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;
import java.util.ArrayList;

/* loaded from: classes.dex */
public interface UpdateAppPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void onAppsToUpdateLoaded(ArrayList<AppDetails> arrayList);

        void onClickCancelDownload(AppDetails appDetails);

        void onClickUpdate(AppDetails appDetails);

        void onDownloadError(String str);

        void onDownloadStart(String str, Long l);
    }

    void cancelDownloadUpdate(String str);

    void checkAppDownload(String str);

    void getAppsToUpdate();

    void installApp(String str, String str2, String str3, String str4, String str5, String str6);
}
