package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.SaveProfileInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;

/* loaded from: classes.dex */
public class SaveProfileInteractorImpl extends AbstractInteractor implements SaveProfileInteractor {
    private String firstName;
    private String lastName;
    private SaveProfileInteractor.Callback mCallback;
    private String userName;

    public SaveProfileInteractorImpl(Executor executor, MainThread mainThread, SaveProfileInteractor.Callback callback, String str, String str2, String str3) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.userName = str;
        this.firstName = str2;
        this.lastName = str3;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00a5  */
    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String run() {
        /*
            r6 = this;
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L96
            r1.<init>()     // Catch: java.lang.Exception -> L96
            java.lang.String r2 = "https://api.apklis.cu/v1/user/"
            r1.append(r2)     // Catch: java.lang.Exception -> L96
            java.lang.String r2 = r6.userName     // Catch: java.lang.Exception -> L96
            r1.append(r2)     // Catch: java.lang.Exception -> L96
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch: java.lang.Exception -> L96
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L96
            okhttp3.OkHttpClient r2 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> L96
            r2.<init>()     // Catch: java.lang.Exception -> L96
            okhttp3.OkHttpClient$Builder r2 = r2.newBuilder()     // Catch: java.lang.Exception -> L96
            r3 = 0
            okhttp3.OkHttpClient$Builder r2 = r2.followRedirects(r3)     // Catch: java.lang.Exception -> L96
            okhttp3.OkHttpClient r2 = r2.build()     // Catch: java.lang.Exception -> L96
            java.lang.String r3 = "multipart/form-data"
            okhttp3.MediaType r3 = okhttp3.MediaType.parse(r3)     // Catch: java.lang.Exception -> L96
            okhttp3.MultipartBody$Builder r4 = new okhttp3.MultipartBody$Builder     // Catch: java.lang.Exception -> L96
            r4.<init>()     // Catch: java.lang.Exception -> L96
            okhttp3.MultipartBody$Builder r3 = r4.setType(r3)     // Catch: java.lang.Exception -> L96
            java.lang.String r4 = "first_name"
            java.lang.String r5 = r6.firstName     // Catch: java.lang.Exception -> L96
            okhttp3.MultipartBody$Builder r3 = r3.addFormDataPart(r4, r5)     // Catch: java.lang.Exception -> L96
            java.lang.String r4 = "last_name"
            java.lang.String r5 = r6.lastName     // Catch: java.lang.Exception -> L96
            okhttp3.MultipartBody$Builder r3 = r3.addFormDataPart(r4, r5)     // Catch: java.lang.Exception -> L96
            okhttp3.MultipartBody r3 = r3.build()     // Catch: java.lang.Exception -> L96
            okhttp3.Request$Builder r4 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> L96
            r4.<init>()     // Catch: java.lang.Exception -> L96
            okhttp3.Request$Builder r1 = r4.url(r1)     // Catch: java.lang.Exception -> L96
            java.lang.String r4 = "Content-Type"
            java.lang.String r5 = "application/json"
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> L96
            java.lang.String r4 = "Accept"
            java.lang.String r5 = "application/json"
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> L96
            java.lang.String r4 = "Authorization"
            cu.uci.android.apklis.domain.model.UserAccount r5 = cu.uci.android.apklis.MainApp.userAccount     // Catch: java.lang.Exception -> L96
            java.lang.String r5 = r5.getAccessToken()     // Catch: java.lang.Exception -> L96
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> L96
            okhttp3.Request$Builder r1 = r1.patch(r3)     // Catch: java.lang.Exception -> L96
            okhttp3.Request r1 = r1.build()     // Catch: java.lang.Exception -> L96
            okhttp3.Call r1 = r2.newCall(r1)     // Catch: java.lang.Exception -> L96
            okhttp3.Response r1 = r1.execute()     // Catch: java.lang.Exception -> L96
            boolean r2 = r1.isSuccessful()     // Catch: java.lang.Exception -> L94
            if (r2 == 0) goto La3
            cu.uci.android.apklis.domain.executor.MainThread r2 = r6.mMainThread     // Catch: java.lang.Exception -> L94
            cu.uci.android.apklis.domain.interactor.impl.SaveProfileInteractorImpl$1 r3 = new cu.uci.android.apklis.domain.interactor.impl.SaveProfileInteractorImpl$1     // Catch: java.lang.Exception -> L94
            r3.<init>()     // Catch: java.lang.Exception -> L94
            r2.post(r3)     // Catch: java.lang.Exception -> L94
            goto La3
        L94:
            r2 = move-exception
            goto L98
        L96:
            r2 = move-exception
            r1 = r0
        L98:
            java.lang.Class r3 = r2.getClass()
            java.lang.String r3 = r3.getName()
            cu.uci.android.apklis.MainApp.log(r3, r2)
        La3:
            if (r1 == 0) goto La8
            r1.close()
        La8:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.domain.interactor.impl.SaveProfileInteractorImpl.run():java.lang.String");
    }
}
