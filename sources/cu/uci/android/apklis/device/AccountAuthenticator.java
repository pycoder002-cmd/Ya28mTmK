package cu.uci.android.apklis.device;

import android.accounts.AbstractAccountAuthenticator;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorResponse;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import cu.uci.android.apklis.R;

/* loaded from: classes.dex */
class AccountAuthenticator extends AbstractAccountAuthenticator {
    private static final int ERROR_CODE_ONE_ACCOUNT_ALLOWED = 1001;
    protected final Context mContext;
    private final Handler mHandler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccountAuthenticator(Context context) {
        super(context);
        this.mHandler = new Handler();
        this.mContext = context;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String str, String str2, String[] strArr, Bundle bundle) throws NetworkErrorException {
        if (!AccountHelper.hasAccount(this.mContext)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putInt("errorCode", 1001);
        bundle2.putString("errorMessage", this.mContext.getString(R.string.error_account_only_one_allowed));
        this.mHandler.post(new Runnable() { // from class: cu.uci.android.apklis.device.AccountAuthenticator.1
            @Override // java.lang.Runnable
            public void run() {
                Toast.makeText(AccountAuthenticator.this.mContext, R.string.error_account_only_one_allowed, 0).show();
            }
        });
        return bundle2;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String str) {
        return null;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) throws NetworkErrorException {
        return null;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public String getAuthTokenLabel(String str) {
        return null;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strArr) throws NetworkErrorException {
        return null;
    }

    @Override // android.accounts.AbstractAccountAuthenticator
    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String str, Bundle bundle) throws NetworkErrorException {
        return null;
    }
}
