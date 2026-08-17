package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.BuyApplicationdInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import okhttp3.Request;

/* loaded from: classes.dex */
public class BuyApplicactionInteractorImpl extends AbstractInteractor implements BuyApplicationdInteractor {
    private String body;
    Integer id;
    private BuyApplicationdInteractor.Callback mCallback;
    private Request.Builder request;

    public BuyApplicactionInteractorImpl(Executor executor, MainThread mainThread, BuyApplicationdInteractor.Callback callback, Integer num) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.id = num;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0094  */
    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String run() {
        /*
            r7 = this;
            cu.uci.android.apklis.domain.interactor.BuyApplicationdInteractor$Callback r0 = r7.mCallback
            r0.buyAplication()
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L86
            r1.<init>()     // Catch: java.lang.Exception -> L86
            java.lang.String r2 = "https://api.apklis.cu/v1/application/"
            r1.append(r2)     // Catch: java.lang.Exception -> L86
            java.lang.Integer r2 = r7.id     // Catch: java.lang.Exception -> L86
            r1.append(r2)     // Catch: java.lang.Exception -> L86
            java.lang.String r2 = "/buy/"
            r1.append(r2)     // Catch: java.lang.Exception -> L86
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L86
            okhttp3.OkHttpClient r2 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> L86
            r2.<init>()     // Catch: java.lang.Exception -> L86
            okhttp3.OkHttpClient$Builder r2 = r2.newBuilder()     // Catch: java.lang.Exception -> L86
            r3 = 0
            okhttp3.OkHttpClient$Builder r2 = r2.followRedirects(r3)     // Catch: java.lang.Exception -> L86
            okhttp3.OkHttpClient r2 = r2.build()     // Catch: java.lang.Exception -> L86
            okhttp3.Request$Builder r4 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> L86
            r4.<init>()     // Catch: java.lang.Exception -> L86
            okhttp3.Request$Builder r1 = r4.url(r1)     // Catch: java.lang.Exception -> L86
            java.lang.String r4 = "Authorization"
            cu.uci.android.apklis.domain.model.UserAccount r5 = cu.uci.android.apklis.MainApp.userAccount     // Catch: java.lang.Exception -> L86
            java.lang.String r5 = r5.getAccessToken()     // Catch: java.lang.Exception -> L86
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> L86
            okhttp3.Request$Builder r1 = r1.get()     // Catch: java.lang.Exception -> L86
            r7.request = r1     // Catch: java.lang.Exception -> L86
            okhttp3.Request$Builder r1 = r7.request     // Catch: java.lang.Exception -> L86
            okhttp3.Request r1 = r1.build()     // Catch: java.lang.Exception -> L86
            okhttp3.Call r1 = r2.newCall(r1)     // Catch: java.lang.Exception -> L86
            okhttp3.Response r1 = r1.execute()     // Catch: java.lang.Exception -> L86
            okhttp3.ResponseBody r0 = r1.body()     // Catch: java.lang.Exception -> L81
            java.lang.String r0 = r0.string()     // Catch: java.lang.Exception -> L81
            r7.body = r0     // Catch: java.lang.Exception -> L81
            android.content.Context r0 = cu.uci.android.apklis.MainApp.context     // Catch: java.lang.Exception -> L81
            r2 = 2131558607(0x7f0d00cf, float:1.8742535E38)
            android.widget.Toast r0 = android.widget.Toast.makeText(r0, r2, r3)     // Catch: java.lang.Exception -> L81
            r0.show()     // Catch: java.lang.Exception -> L81
            boolean r0 = r1.isSuccessful()     // Catch: java.lang.Exception -> L81
            if (r0 == 0) goto L7f
            cu.uci.android.apklis.domain.executor.MainThread r0 = r7.mMainThread     // Catch: java.lang.Exception -> L81
            cu.uci.android.apklis.domain.interactor.impl.BuyApplicactionInteractorImpl$1 r2 = new cu.uci.android.apklis.domain.interactor.impl.BuyApplicactionInteractorImpl$1     // Catch: java.lang.Exception -> L81
            r2.<init>()     // Catch: java.lang.Exception -> L81
            r0.post(r2)     // Catch: java.lang.Exception -> L81
        L7f:
            r0 = r1
            goto L92
        L81:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L87
        L86:
            r1 = move-exception
        L87:
            java.lang.Class r2 = r1.getClass()
            java.lang.String r2 = r2.getName()
            cu.uci.android.apklis.MainApp.log(r2, r1)
        L92:
            if (r0 == 0) goto L97
            r0.close()
        L97:
            java.lang.String r0 = r7.body
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.domain.interactor.impl.BuyApplicactionInteractorImpl.run():java.lang.String");
    }
}
