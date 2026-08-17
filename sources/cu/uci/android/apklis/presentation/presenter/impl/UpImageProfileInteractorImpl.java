package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.UpImageProfileInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;

/* loaded from: classes.dex */
public class UpImageProfileInteractorImpl extends AbstractInteractor implements UpImageProfileInteractor {
    private String body;
    private String img;
    private UpImageProfileInteractor.Callback mCallback;
    private String userName;

    public UpImageProfileInteractorImpl(Executor executor, MainThread mainThread, UpImageProfileInteractor.Callback callback, String str, String str2) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.userName = str;
        this.img = str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00e8  */
    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String run() {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lce
            r2.<init>()     // Catch: java.lang.Exception -> Lce
            java.lang.String r3 = "https://api.apklis.cu/v1/user/"
            r2.append(r3)     // Catch: java.lang.Exception -> Lce
            java.lang.String r3 = r9.userName     // Catch: java.lang.Exception -> Lce
            r2.append(r3)     // Catch: java.lang.Exception -> Lce
            java.lang.String r3 = "/"
            r2.append(r3)     // Catch: java.lang.Exception -> Lce
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Lce
            okhttp3.OkHttpClient r3 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> Lce
            r3.<init>()     // Catch: java.lang.Exception -> Lce
            okhttp3.OkHttpClient$Builder r3 = r3.newBuilder()     // Catch: java.lang.Exception -> Lce
            okhttp3.OkHttpClient$Builder r3 = r3.followRedirects(r0)     // Catch: java.lang.Exception -> Lce
            okhttp3.OkHttpClient r3 = r3.build()     // Catch: java.lang.Exception -> Lce
            java.io.File r4 = new java.io.File     // Catch: java.lang.Exception -> Lce
            java.lang.String r5 = r9.img     // Catch: java.lang.Exception -> Lce
            r4.<init>(r5)     // Catch: java.lang.Exception -> Lce
            java.lang.String r5 = "multipart/form-data"
            okhttp3.MediaType r5 = okhttp3.MediaType.parse(r5)     // Catch: java.lang.Exception -> Lce
            java.lang.String r6 = "image/*"
            okhttp3.MediaType r6 = okhttp3.MediaType.parse(r6)     // Catch: java.lang.Exception -> Lce
            okhttp3.RequestBody r6 = okhttp3.RequestBody.create(r6, r4)     // Catch: java.lang.Exception -> Lce
            java.lang.String r7 = "avatar"
            java.lang.String r8 = r4.getAbsolutePath()     // Catch: java.lang.Exception -> Lce
            okhttp3.MultipartBody$Part r6 = okhttp3.MultipartBody.Part.createFormData(r7, r8, r6)     // Catch: java.lang.Exception -> Lce
            okhttp3.MultipartBody$Builder r7 = new okhttp3.MultipartBody$Builder     // Catch: java.lang.Exception -> Lce
            r7.<init>()     // Catch: java.lang.Exception -> Lce
            okhttp3.MultipartBody$Builder r5 = r7.setType(r5)     // Catch: java.lang.Exception -> Lce
            java.lang.String r7 = "username"
            java.lang.String r8 = r9.userName     // Catch: java.lang.Exception -> Lce
            okhttp3.MultipartBody$Builder r5 = r5.addFormDataPart(r7, r8)     // Catch: java.lang.Exception -> Lce
            java.lang.String r7 = "avatar"
            java.lang.String r4 = r4.getAbsolutePath()     // Catch: java.lang.Exception -> Lce
            okhttp3.MultipartBody$Builder r4 = r5.addFormDataPart(r7, r4)     // Catch: java.lang.Exception -> Lce
            okhttp3.MultipartBody$Builder r4 = r4.addPart(r6)     // Catch: java.lang.Exception -> Lce
            okhttp3.MultipartBody r4 = r4.build()     // Catch: java.lang.Exception -> Lce
            okhttp3.Request$Builder r5 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> Lce
            r5.<init>()     // Catch: java.lang.Exception -> Lce
            okhttp3.Request$Builder r2 = r5.url(r2)     // Catch: java.lang.Exception -> Lce
            java.lang.String r5 = "Content-Type"
            java.lang.String r6 = "application/json"
            okhttp3.Request$Builder r2 = r2.addHeader(r5, r6)     // Catch: java.lang.Exception -> Lce
            java.lang.String r5 = "Accept"
            java.lang.String r6 = "application/json"
            okhttp3.Request$Builder r2 = r2.addHeader(r5, r6)     // Catch: java.lang.Exception -> Lce
            java.lang.String r5 = "Authorization"
            cu.uci.android.apklis.domain.model.UserAccount r6 = cu.uci.android.apklis.MainApp.userAccount     // Catch: java.lang.Exception -> Lce
            java.lang.String r6 = r6.getAccessToken()     // Catch: java.lang.Exception -> Lce
            okhttp3.Request$Builder r2 = r2.addHeader(r5, r6)     // Catch: java.lang.Exception -> Lce
            okhttp3.Request$Builder r2 = r2.patch(r4)     // Catch: java.lang.Exception -> Lce
            okhttp3.Request r2 = r2.build()     // Catch: java.lang.Exception -> Lce
            okhttp3.Call r2 = r3.newCall(r2)     // Catch: java.lang.Exception -> Lce
            okhttp3.Response r2 = r2.execute()     // Catch: java.lang.Exception -> Lce
            okhttp3.ResponseBody r3 = r2.body()     // Catch: java.lang.Exception -> Lcc
            java.lang.String r3 = r3.string()     // Catch: java.lang.Exception -> Lcc
            r9.body = r3     // Catch: java.lang.Exception -> Lcc
            java.lang.String r3 = r9.body     // Catch: java.lang.Exception -> Lcc
            java.lang.String r3 = "run: "
            java.lang.String r4 = r9.body     // Catch: java.lang.Exception -> Lcc
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> Lcc
            android.util.Log.e(r3, r4)     // Catch: java.lang.Exception -> Lcc
            boolean r3 = r2.isSuccessful()     // Catch: java.lang.Exception -> Lcc
            if (r3 == 0) goto Le6
            cu.uci.android.apklis.domain.executor.MainThread r3 = r9.mMainThread     // Catch: java.lang.Exception -> Lcc
            cu.uci.android.apklis.presentation.presenter.impl.UpImageProfileInteractorImpl$1 r4 = new cu.uci.android.apklis.presentation.presenter.impl.UpImageProfileInteractorImpl$1     // Catch: java.lang.Exception -> Lcc
            r4.<init>()     // Catch: java.lang.Exception -> Lcc
            r3.post(r4)     // Catch: java.lang.Exception -> Lcc
            goto Le6
        Lcc:
            r3 = move-exception
            goto Ld0
        Lce:
            r3 = move-exception
            r2 = r1
        Ld0:
            java.lang.Class r4 = r3.getClass()
            java.lang.String r4 = r4.getName()
            cu.uci.android.apklis.MainApp.log(r4, r3)
            android.content.Context r3 = cu.uci.android.apklis.MainApp.context
            java.lang.String r4 = "No se pudo modificar el avatar"
            android.widget.Toast r0 = android.widget.Toast.makeText(r3, r4, r0)
            r0.show()
        Le6:
            if (r2 == 0) goto Leb
            r2.close()
        Leb:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.presentation.presenter.impl.UpImageProfileInteractorImpl.run():java.lang.String");
    }
}
