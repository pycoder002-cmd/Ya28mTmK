package cu.uci.android.apklis.presentation.presenter;

import cu.uci.android.apklis.presentation.presenter.base.BasePresenter;
import cu.uci.android.apklis.presentation.ui.BaseView;

/* loaded from: classes.dex */
public interface SaveProfilePresenter extends BasePresenter {

    /* loaded from: classes.dex */
    public interface View extends BaseView {
        void profileChanged();
    }

    void saveProfile(String str, String str2, String str3);
}
