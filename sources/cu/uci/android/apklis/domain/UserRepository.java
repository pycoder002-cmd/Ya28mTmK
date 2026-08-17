package cu.uci.android.apklis.domain;

import cu.uci.android.apklis.domain.model.UserAccount;
import cu.uci.android.apklis.storage.repository.model.ApiUser;

/* loaded from: classes.dex */
public interface UserRepository {
    UserAccount activateAccount(String str);

    String changePassword(String str, String str2, String str3);

    void getUserInfo(UserAccount userAccount);

    UserAccount login(String str, String str2);

    String newPassword(String str);

    ApiUser signUp(String str, String str2, String str3, String str4, String str5);

    String suggest(String str, String str2, String str3);
}
