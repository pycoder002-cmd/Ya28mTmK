package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.model.AppView;
import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public interface AllAppPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void loadAppListByCategory(Integer num, String str);

        void onAppsRetrived(ArrayList<AppDetails> arrayList);

        void onClickApp(String str);

        void updateAppList(String str);
    }

    List<AppView> getAppsByUser(String str);

    List<AppView> getAppsInGroup(int i, int i2, int i3);

    List<AppView> getAppsInRange(int i, int i2, String str);

    List<AppView> getAppsInRangeByCategory(int i, int i2, Integer num);

    List<AppView> getAppsPackageName(int i, int i2, String str);

    List<AppView> getFavoriteApps(String str, String str2);
}
