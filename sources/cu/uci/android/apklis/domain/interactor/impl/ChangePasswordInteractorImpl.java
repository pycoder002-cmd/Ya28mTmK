package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.ChangePasswordInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;

/* loaded from: classes.dex */
public class ChangePasswordInteractorImpl extends AbstractInteractor implements ChangePasswordInteractor {
    private ChangePasswordInteractor.Callback mCallback;
    private String newPass;
    private String oldPass;

    public ChangePasswordInteractorImpl(Executor executor, MainThread mainThread, ChangePasswordInteractor.Callback callback, String str, String str2) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.oldPass = str;
        this.newPass = str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x008f  */
    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String run() {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r1 = "https://api.apklis.cu/v1/user/set_password/"
            okhttp3.OkHttpClient r2 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> L80
            r2.<init>()     // Catch: java.lang.Exception -> L80
            okhttp3.OkHttpClient$Builder r2 = r2.newBuilder()     // Catch: java.lang.Exception -> L80
            r3 = 0
            okhttp3.OkHttpClient$Builder r2 = r2.followRedirects(r3)     // Catch: java.lang.Exception -> L80
            okhttp3.OkHttpClient r2 = r2.build()     // Catch: java.lang.Exception -> L80
            java.lang.String r3 = "multipart/form-data"
            okhttp3.MediaType r3 = okhttp3.MediaType.parse(r3)     // Catch: java.lang.Exception -> L80
            okhttp3.MultipartBody$Builder r4 = new okhttp3.MultipartBody$Builder     // Catch: java.lang.Exception -> L80
            r4.<init>()     // Catch: java.lang.Exception -> L80
            okhttp3.MultipartBody$Builder r3 = r4.setType(r3)     // Catch: java.lang.Exception -> L80
            java.lang.String r4 = "old_password"
            java.lang.String r5 = r6.oldPass     // Catch: java.lang.Exception -> L80
            okhttp3.MultipartBody$Builder r3 = r3.addFormDataPart(r4, r5)     // Catch: java.lang.Exception -> L80
            java.lang.String r4 = "new_password"
            java.lang.String r5 = r6.newPass     // Catch: java.lang.Exception -> L80
            okhttp3.MultipartBody$Builder r3 = r3.addFormDataPart(r4, r5)     // Catch: java.lang.Exception -> L80
            okhttp3.MultipartBody r3 = r3.build()     // Catch: java.lang.Exception -> L80
            okhttp3.Request$Builder r4 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> L80
            r4.<init>()     // Catch: java.lang.Exception -> L80
            okhttp3.Request$Builder r1 = r4.url(r1)     // Catch: java.lang.Exception -> L80
            java.lang.String r4 = "Content-Type"
            java.lang.String r5 = "application/json"
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> L80
            java.lang.String r4 = "Accept"
            java.lang.String r5 = "application/json"
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> L80
            java.lang.String r4 = "Authorization"
            cu.uci.android.apklis.domain.model.UserAccount r5 = cu.uci.android.apklis.MainApp.userAccount     // Catch: java.lang.Exception -> L80
            java.lang.String r5 = r5.getAccessToken()     // Catch: java.lang.Exception -> L80
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> L80
            okhttp3.Request$Builder r1 = r1.put(r3)     // Catch: java.lang.Exception -> L80
            okhttp3.Request r1 = r1.build()     // Catch: java.lang.Exception -> L80
            okhttp3.Call r1 = r2.newCall(r1)     // Catch: java.lang.Exception -> L80
            okhttp3.Response r1 = r1.execute()     // Catch: java.lang.Exception -> L80
            boolean r2 = r1.isSuccessful()     // Catch: java.lang.Exception -> L7e
            if (r2 == 0) goto L8d
            cu.uci.android.apklis.domain.executor.MainThread r2 = r6.mMainThread     // Catch: java.lang.Exception -> L7e
            cu.uci.android.apklis.domain.interactor.impl.ChangePasswordInteractorImpl$1 r3 = new cu.uci.android.apklis.domain.interactor.impl.ChangePasswordInteractorImpl$1     // Catch: java.lang.Exception -> L7e
            r3.<init>()     // Catch: java.lang.Exception -> L7e
            r2.post(r3)     // Catch: java.lang.Exception -> L7e
            goto L8d
        L7e:
            r2 = move-exception
            goto L82
        L80:
            r2 = move-exception
            r1 = r0
        L82:
            java.lang.Class r3 = r2.getClass()
            java.lang.String r3 = r3.getName()
            cu.uci.android.apklis.MainApp.log(r3, r2)
        L8d:
            if (r1 == 0) goto L92
            r1.close()
        L92:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.domain.interactor.impl.ChangePasswordInteractorImpl.run():java.lang.String");
    }
}
