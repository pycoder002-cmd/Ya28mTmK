package cu.uci.android.apklis.domain.interactor.impl;

import android.accounts.Account;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.device.AccountHelper;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CheckUserAccountInfoInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.domain.model.UserAccount;
import cu.uci.android.apklis.storage.repository.UserRepositoryImpl;

/* loaded from: classes.dex */
public class CheckUserAccountInfoInteractorImpl extends AbstractInteractor implements CheckUserAccountInfoInteractor {
    private CheckUserAccountInfoInteractor.Callback mCallback;
    private String password;
    private String username;

    public CheckUserAccountInfoInteractorImpl(Executor executor, MainThread mainThread, CheckUserAccountInfoInteractor.Callback callback, String str, String str2) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.username = str;
        this.password = str2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkUserAccountInfo() {
        final Boolean bool = false;
        if (AccountHelper.hasAccount(MainApp.context)) {
            Account currentAccount = AccountHelper.getCurrentAccount(MainApp.context);
            String str = currentAccount.name;
            String accountPassword = AccountHelper.getAccountPassword(currentAccount);
            MainApp.userAccount = new UserAccount();
            MainApp.userAccount.setUsername(str);
            MainApp.userAccount.setPassword(accountPassword);
            UserAccount login = new UserRepositoryImpl().login(str, accountPassword);
            if (login != null) {
                bool = true;
                login.setAccessToken(login.getAccessToken());
                login.setId(login.getId());
                login.setUsername(str);
                login.setPassword(accountPassword);
                MainApp.userAccount = login;
            }
        }
        if (bool.booleanValue()) {
            MainApp.setUserStatus(MainApp.USER_STATUS_AUTENTICATED, null);
        } else if (MainApp.getUserStatus() == MainApp.USER_STATUS_AUTENTICATED) {
            MainApp.setUserStatus(MainApp.USER_STATUS_NORMAL, null);
        }
        this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.CheckUserAccountInfoInteractorImpl.2
            @Override // java.lang.Runnable
            public void run() {
                CheckUserAccountInfoInteractorImpl.this.mCallback.onUserAccountInfoLoaded(bool);
            }
        });
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        if (this.username == null || this.username.length() <= 0 || this.password == null || this.password.length() <= 0) {
            checkUserAccountInfo();
            return null;
        }
        AccountHelper.clearAccounts(MainApp.context, new AccountHelper.OnAccountListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.CheckUserAccountInfoInteractorImpl.1
            @Override // cu.uci.android.apklis.device.AccountHelper.OnAccountListener
            public void onFinished() {
                AccountHelper.addAccount(MainApp.context, CheckUserAccountInfoInteractorImpl.this.username, CheckUserAccountInfoInteractorImpl.this.password, "");
                CheckUserAccountInfoInteractorImpl.this.checkUserAccountInfo();
            }
        });
        return null;
    }
}
