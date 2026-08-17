package cu.uci.android.apklis.domain;

import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.domain.model.Category;
import cu.uci.android.apklis.domain.model.UserAccount;

/* loaded from: classes.dex */
public interface ApiRepository {
    Boolean checkConnectionToServer();

    App getAppDetailsById(String str);

    App[] getAppListMinus(int i, String str);

    App[] getAppListMinusByCategory(int i, String str);

    Category[] getCategories();

    UserAccount getUserAccountInfo(String str);

    UserAccount login(String str, String str2);

    UserAccount newLogin();
}
