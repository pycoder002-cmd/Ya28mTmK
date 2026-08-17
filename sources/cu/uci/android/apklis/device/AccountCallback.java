package cu.uci.android.apklis.device;

import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import cu.uci.android.apklis.device.AccountHelper;

/* loaded from: classes.dex */
public class AccountCallback<T> implements AccountManagerCallback<T> {
    private final int mAccountsLength;
    private final int mDeletedAccounts;
    private final AccountHelper.OnAccountListener mOnAccountListener;

    public AccountCallback(int i, AccountHelper.OnAccountListener onAccountListener, int i2) {
        this.mDeletedAccounts = i;
        this.mOnAccountListener = onAccountListener;
        this.mAccountsLength = i2;
    }

    @Override // android.accounts.AccountManagerCallback
    public void run(AccountManagerFuture<T> accountManagerFuture) {
        if (this.mOnAccountListener == null || this.mDeletedAccounts != this.mAccountsLength) {
            return;
        }
        this.mOnAccountListener.onFinished();
    }
}
