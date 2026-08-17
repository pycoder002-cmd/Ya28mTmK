package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.model.AppView;
import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public interface HomeFragmentPresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void onAppsRetrived(ArrayList<AppDetails> arrayList, int i, boolean z);
    }

    List<AppView> getAppsInGroup(int i, int i2, int i3);
}
