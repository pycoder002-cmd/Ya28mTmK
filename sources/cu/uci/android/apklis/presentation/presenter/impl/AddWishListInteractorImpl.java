package cu.uci.android.apklis.presentation.presenter.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.AddWhisListInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class AddWishListInteractorImpl extends AbstractInteractor implements AddWhisListInteractor {
    private int[] arryApps;
    private String body;
    private ArrayList lisfavorite;
    private AddWhisListInteractor.Callback mCallback;
    private String resStr;
    private String text;
    private String userName;

    public AddWishListInteractorImpl(Executor executor, MainThread mainThread, AddWhisListInteractor.Callback callback, String str, int[] iArr, String str2) {
        super(executor, mainThread);
        this.resStr = "";
        this.lisfavorite = new ArrayList();
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.userName = str;
        this.arryApps = iArr;
        this.text = str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00e7  */
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
            java.lang.String r2 = "https://api.apklis.cu/v1/user/update_wish_list/"
            okhttp3.OkHttpClient r3 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> Lcc
            r3.<init>()     // Catch: java.lang.Exception -> Lcc
            okhttp3.OkHttpClient$Builder r3 = r3.newBuilder()     // Catch: java.lang.Exception -> Lcc
            okhttp3.OkHttpClient$Builder r3 = r3.followRedirects(r1)     // Catch: java.lang.Exception -> Lcc
            okhttp3.OkHttpClient r3 = r3.build()     // Catch: java.lang.Exception -> Lcc
            java.lang.String r4 = "multipart/form-data"
            okhttp3.MediaType r4 = okhttp3.MediaType.parse(r4)     // Catch: java.lang.Exception -> Lcc
            r5 = r1
        L1c:
            int[] r6 = r9.arryApps     // Catch: java.lang.Exception -> Lcc
            int r6 = r6.length     // Catch: java.lang.Exception -> Lcc
            if (r5 >= r6) goto L62
            if (r5 != 0) goto L3f
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lcc
            r6.<init>()     // Catch: java.lang.Exception -> Lcc
            java.lang.String r7 = r9.resStr     // Catch: java.lang.Exception -> Lcc
            r6.append(r7)     // Catch: java.lang.Exception -> Lcc
            int[] r7 = r9.arryApps     // Catch: java.lang.Exception -> Lcc
            r7 = r7[r5]     // Catch: java.lang.Exception -> Lcc
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Exception -> Lcc
            r6.append(r7)     // Catch: java.lang.Exception -> Lcc
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> Lcc
            r9.resStr = r6     // Catch: java.lang.Exception -> Lcc
            goto L5f
        L3f:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lcc
            r6.<init>()     // Catch: java.lang.Exception -> Lcc
            java.lang.String r7 = r9.resStr     // Catch: java.lang.Exception -> Lcc
            r6.append(r7)     // Catch: java.lang.Exception -> Lcc
            r7 = 44
            r6.append(r7)     // Catch: java.lang.Exception -> Lcc
            int[] r7 = r9.arryApps     // Catch: java.lang.Exception -> Lcc
            r7 = r7[r5]     // Catch: java.lang.Exception -> Lcc
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch: java.lang.Exception -> Lcc
            r6.append(r7)     // Catch: java.lang.Exception -> Lcc
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Exception -> Lcc
            r9.resStr = r6     // Catch: java.lang.Exception -> Lcc
        L5f:
            int r5 = r5 + 1
            goto L1c
        L62:
            okhttp3.MultipartBody$Builder r5 = new okhttp3.MultipartBody$Builder     // Catch: java.lang.Exception -> Lcc
            r5.<init>()     // Catch: java.lang.Exception -> Lcc
            okhttp3.MultipartBody$Builder r4 = r5.setType(r4)     // Catch: java.lang.Exception -> Lcc
            java.lang.String r5 = "favorite_apps"
            java.lang.String r6 = r9.resStr     // Catch: java.lang.Exception -> Lcc
            okhttp3.MultipartBody$Builder r4 = r4.addFormDataPart(r5, r6)     // Catch: java.lang.Exception -> Lcc
            okhttp3.MultipartBody r4 = r4.build()     // Catch: java.lang.Exception -> Lcc
            okhttp3.Request$Builder r5 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> Lcc
            r5.<init>()     // Catch: java.lang.Exception -> Lcc
            okhttp3.Request$Builder r2 = r5.url(r2)     // Catch: java.lang.Exception -> Lcc
            java.lang.String r5 = "Content-Type"
            java.lang.String r6 = "application/json"
            okhttp3.Request$Builder r2 = r2.addHeader(r5, r6)     // Catch: java.lang.Exception -> Lcc
            java.lang.String r5 = "Accept"
            java.lang.String r6 = "application/json"
            okhttp3.Request$Builder r2 = r2.addHeader(r5, r6)     // Catch: java.lang.Exception -> Lcc
            java.lang.String r5 = "Authorization"
            cu.uci.android.apklis.domain.model.UserAccount r6 = cu.uci.android.apklis.MainApp.userAccount     // Catch: java.lang.Exception -> Lcc
            java.lang.String r6 = r6.getAccessToken()     // Catch: java.lang.Exception -> Lcc
            okhttp3.Request$Builder r2 = r2.addHeader(r5, r6)     // Catch: java.lang.Exception -> Lcc
            okhttp3.Request$Builder r2 = r2.post(r4)     // Catch: java.lang.Exception -> Lcc
            okhttp3.Request r2 = r2.build()     // Catch: java.lang.Exception -> Lcc
            okhttp3.Call r2 = r3.newCall(r2)     // Catch: java.lang.Exception -> Lcc
            okhttp3.Response r2 = r2.execute()     // Catch: java.lang.Exception -> Lcc
            okhttp3.ResponseBody r3 = r2.body()     // Catch: java.lang.Exception -> Lc7
            java.lang.String r3 = r3.string()     // Catch: java.lang.Exception -> Lc7
            r9.body = r3     // Catch: java.lang.Exception -> Lc7
            boolean r3 = r2.isSuccessful()     // Catch: java.lang.Exception -> Lc7
            if (r3 == 0) goto Le5
            cu.uci.android.apklis.domain.executor.MainThread r3 = r9.mMainThread     // Catch: java.lang.Exception -> Lc7
            cu.uci.android.apklis.presentation.presenter.impl.AddWishListInteractorImpl$1 r4 = new cu.uci.android.apklis.presentation.presenter.impl.AddWishListInteractorImpl$1     // Catch: java.lang.Exception -> Lc7
            r4.<init>()     // Catch: java.lang.Exception -> Lc7
            r3.post(r4)     // Catch: java.lang.Exception -> Lc7
            goto Le5
        Lc7:
            r3 = move-exception
            r8 = r3
            r3 = r2
            r2 = r8
            goto Lce
        Lcc:
            r2 = move-exception
            r3 = r0
        Lce:
            java.lang.Class r4 = r2.getClass()
            java.lang.String r4 = r4.getName()
            cu.uci.android.apklis.MainApp.log(r4, r2)
            android.content.Context r2 = cu.uci.android.apklis.MainApp.context
            java.lang.String r4 = "Error al agregar a la lista"
            android.widget.Toast r1 = android.widget.Toast.makeText(r2, r4, r1)
            r1.show()
            r2 = r3
        Le5:
            if (r2 == 0) goto Lea
            r2.close()
        Lea:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.presentation.presenter.impl.AddWishListInteractorImpl.run():java.lang.String");
    }
}
