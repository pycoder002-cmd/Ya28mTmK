package cu.uci.android.apklis.device;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Build;
import cu.uci.android.apklis.MainApp;

/* loaded from: classes.dex */
public final class AccountHelper {
    private static final String MISSING_PERMISSION = "MissingPermission";
    private static int sDeletedAccounts;
    private static AccountManager sManager;

    /* loaded from: classes.dex */
    public interface OnAccountListener {
        void onFinished();
    }

    private AccountHelper() {
    }

    public static void addAccount(Context context, String str, String str2, String str3) {
        validateAccountManager();
        clearAccounts(context, new OnAddAccountListener(str, context, str2, str3));
    }

    public static void clearAccounts(Context context, OnAccountListener onAccountListener) {
        synchronized (AccountHelper.class) {
            validateAccountManager();
            sDeletedAccounts = 0;
            Account[] accountsByType = sManager.getAccountsByType(context.getPackageName());
            if (accountsByType.length == 0) {
                if (onAccountListener != null) {
                    onAccountListener.onFinished();
                }
                return;
            }
            if (Build.VERSION.SDK_INT >= 22) {
                for (Account account : accountsByType) {
                    sManager.removeAccountExplicitly(account);
                }
                if (onAccountListener != null) {
                    onAccountListener.onFinished();
                }
            } else {
                removeAccounts(onAccountListener, accountsByType);
            }
        }
    }

    public static String getAccountPassword(Account account) {
        validateAccountManager();
        return sManager.getPassword(account);
    }

    public static Account getCurrentAccount(Context context) {
        validateAccountManager();
        return sManager.getAccountsByType(context.getPackageName())[0];
    }

    public static String getCurrentToken(Context context) {
        validateAccountManager();
        return sManager.peekAuthToken(sManager.getAccountsByType(context.getPackageName())[0], context.getPackageName());
    }

    public static boolean hasAccount(Context context) {
        validateAccountManager();
        return sManager.getAccountsByType(context.getPackageName()).length > 0;
    }

    public static void initialize(Context context) {
        sManager = AccountManager.get(context);
    }

    public static void onFinished(String str, Context context, String str2, String str3) {
        Account account = new Account(str, context.getPackageName());
        try {
            sManager.addAccountExplicitly(account, str2, null);
            sManager.setAuthToken(account, context.getPackageName(), str3);
        } catch (Exception e) {
            e.printStackTrace();
            MainApp.log("AccountHelper", e);
        }
    }

    private static void removeAccounts(OnAccountListener onAccountListener, Account... accountArr) {
        for (Account account : accountArr) {
            if (Build.VERSION.SDK_INT >= 22) {
                int i = sDeletedAccounts + 1;
                sDeletedAccounts = i;
                sManager.removeAccount(account, null, new AccountCallback(i, onAccountListener, accountArr.length), null);
            } else {
                int i2 = sDeletedAccounts + 1;
                sDeletedAccounts = i2;
                sManager.removeAccount(account, new AccountCallback(i2, onAccountListener, accountArr.length), null);
            }
        }
    }

    private static void validateAccountManager() {
        if (sManager == null) {
            throw new ExceptionInInitializerError("It's necessary to call AccountHelper.initialize first");
        }
    }
}
