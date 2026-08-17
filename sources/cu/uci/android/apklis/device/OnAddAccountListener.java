package cu.uci.android.apklis.device;

import android.content.Context;
import cu.uci.android.apklis.device.AccountHelper;

/* loaded from: classes.dex */
public class OnAddAccountListener implements AccountHelper.OnAccountListener {
    private final Context mContext;
    private final String mName;
    private final String mPassword;
    private final String mToken;

    public OnAddAccountListener(String str, Context context, String str2, String str3) {
        this.mName = str;
        this.mContext = context;
        this.mPassword = str2;
        this.mToken = str3;
    }

    @Override // cu.uci.android.apklis.device.AccountHelper.OnAccountListener
    public void onFinished() {
        AccountHelper.onFinished(this.mName, this.mContext, this.mPassword, this.mToken);
    }
}
