package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.SendUserReviewInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;

/* loaded from: classes.dex */
public class SendUserReviewInteractorImpl extends AbstractInteractor implements SendUserReviewInteractor {
    private Integer appId;
    private String comment;
    private boolean edit;
    private SendUserReviewInteractor.Callback mCallback;
    private Integer stars;

    public SendUserReviewInteractorImpl(Executor executor, MainThread mainThread, SendUserReviewInteractor.Callback callback, Integer num, Integer num2, String str, boolean z) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.appId = num;
        this.stars = num2;
        this.comment = str;
        this.edit = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00e6  */
    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String run() {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r1 = "https://api.apklis.cu/v1/review/"
            okhttp3.OkHttpClient r2 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> Lcd
            r2.<init>()     // Catch: java.lang.Exception -> Lcd
            okhttp3.OkHttpClient$Builder r2 = r2.newBuilder()     // Catch: java.lang.Exception -> Lcd
            r3 = 0
            okhttp3.OkHttpClient$Builder r2 = r2.followRedirects(r3)     // Catch: java.lang.Exception -> Lcd
            okhttp3.OkHttpClient r2 = r2.build()     // Catch: java.lang.Exception -> Lcd
            java.lang.String r3 = "multipart/form-data"
            okhttp3.MediaType r3 = okhttp3.MediaType.parse(r3)     // Catch: java.lang.Exception -> Lcd
            okhttp3.MultipartBody$Builder r4 = new okhttp3.MultipartBody$Builder     // Catch: java.lang.Exception -> Lcd
            r4.<init>()     // Catch: java.lang.Exception -> Lcd
            okhttp3.MultipartBody$Builder r3 = r4.setType(r3)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r4 = "comment"
            java.lang.String r5 = r6.comment     // Catch: java.lang.Exception -> Lcd
            okhttp3.MultipartBody$Builder r3 = r3.addFormDataPart(r4, r5)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r4 = "rating"
            java.lang.Integer r5 = r6.stars     // Catch: java.lang.Exception -> Lcd
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Exception -> Lcd
            okhttp3.MultipartBody$Builder r3 = r3.addFormDataPart(r4, r5)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r4 = "application"
            java.lang.Integer r5 = r6.appId     // Catch: java.lang.Exception -> Lcd
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch: java.lang.Exception -> Lcd
            okhttp3.MultipartBody$Builder r3 = r3.addFormDataPart(r4, r5)     // Catch: java.lang.Exception -> Lcd
            okhttp3.MultipartBody r3 = r3.build()     // Catch: java.lang.Exception -> Lcd
            okhttp3.Request$Builder r4 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> Lcd
            r4.<init>()     // Catch: java.lang.Exception -> Lcd
            okhttp3.Request$Builder r1 = r4.url(r1)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r4 = "Content-Type"
            java.lang.String r5 = "application/json"
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r4 = "Accept"
            java.lang.String r5 = "application/json"
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> Lcd
            java.lang.String r4 = "Authorization"
            cu.uci.android.apklis.domain.model.UserAccount r5 = cu.uci.android.apklis.MainApp.userAccount     // Catch: java.lang.Exception -> Lcd
            java.lang.String r5 = r5.getAccessToken()     // Catch: java.lang.Exception -> Lcd
            okhttp3.Request$Builder r1 = r1.addHeader(r4, r5)     // Catch: java.lang.Exception -> Lcd
            okhttp3.Request$Builder r1 = r1.post(r3)     // Catch: java.lang.Exception -> Lcd
            okhttp3.Request r1 = r1.build()     // Catch: java.lang.Exception -> Lcd
            okhttp3.Call r1 = r2.newCall(r1)     // Catch: java.lang.Exception -> Lcd
            okhttp3.Response r1 = r1.execute()     // Catch: java.lang.Exception -> Lcd
            boolean r2 = r1.isSuccessful()     // Catch: java.lang.Exception -> Lcb
            if (r2 == 0) goto Lb5
            com.google.gson.Gson r2 = new com.google.gson.Gson     // Catch: java.lang.Exception -> L9d
            r2.<init>()     // Catch: java.lang.Exception -> L9d
            okhttp3.ResponseBody r3 = r1.body()     // Catch: java.lang.Exception -> L9d
            java.lang.String r3 = r3.string()     // Catch: java.lang.Exception -> L9d
            java.lang.Class<cu.uci.android.apklis.storage.repository.model.ApiReview> r4 = cu.uci.android.apklis.storage.repository.model.ApiReview.class
            java.lang.Object r2 = r2.fromJson(r3, r4)     // Catch: java.lang.Exception -> L9d
            cu.uci.android.apklis.storage.repository.model.ApiReview r2 = (cu.uci.android.apklis.storage.repository.model.ApiReview) r2     // Catch: java.lang.Exception -> L9d
            cu.uci.android.apklis.presentation.model.Review r2 = cu.uci.android.apklis.storage.repository.converter.ReviewConverter.convertToViewModel(r2)     // Catch: java.lang.Exception -> L9d
            goto Laa
        L9d:
            r2 = move-exception
            java.lang.Class r3 = r6.getClass()     // Catch: java.lang.Exception -> Lcb
            java.lang.String r3 = r3.getName()     // Catch: java.lang.Exception -> Lcb
            cu.uci.android.apklis.MainApp.log(r3, r2)     // Catch: java.lang.Exception -> Lcb
            r2 = r0
        Laa:
            cu.uci.android.apklis.domain.executor.MainThread r3 = r6.mMainThread     // Catch: java.lang.Exception -> Lcb
            cu.uci.android.apklis.domain.interactor.impl.SendUserReviewInteractorImpl$1 r4 = new cu.uci.android.apklis.domain.interactor.impl.SendUserReviewInteractorImpl$1     // Catch: java.lang.Exception -> Lcb
            r4.<init>()     // Catch: java.lang.Exception -> Lcb
            r3.post(r4)     // Catch: java.lang.Exception -> Lcb
            goto Le4
        Lb5:
            okhttp3.ResponseBody r2 = r1.body()     // Catch: java.lang.Exception -> Lcb
            java.lang.String r2 = r2.string()     // Catch: java.lang.Exception -> Lcb
            cu.uci.android.apklis.MainApp.log(r2)     // Catch: java.lang.Exception -> Lcb
            cu.uci.android.apklis.domain.executor.MainThread r2 = r6.mMainThread     // Catch: java.lang.Exception -> Lcb
            cu.uci.android.apklis.domain.interactor.impl.SendUserReviewInteractorImpl$2 r3 = new cu.uci.android.apklis.domain.interactor.impl.SendUserReviewInteractorImpl$2     // Catch: java.lang.Exception -> Lcb
            r3.<init>()     // Catch: java.lang.Exception -> Lcb
            r2.post(r3)     // Catch: java.lang.Exception -> Lcb
            goto Le4
        Lcb:
            r2 = move-exception
            goto Lcf
        Lcd:
            r2 = move-exception
            r1 = r0
        Lcf:
            java.lang.Class r3 = r2.getClass()
            java.lang.String r3 = r3.getName()
            cu.uci.android.apklis.MainApp.log(r3, r2)
            cu.uci.android.apklis.domain.executor.MainThread r2 = r6.mMainThread
            cu.uci.android.apklis.domain.interactor.impl.SendUserReviewInteractorImpl$3 r3 = new cu.uci.android.apklis.domain.interactor.impl.SendUserReviewInteractorImpl$3
            r3.<init>()
            r2.post(r3)
        Le4:
            if (r1 == 0) goto Le9
            r1.close()
        Le9:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.domain.interactor.impl.SendUserReviewInteractorImpl.run():java.lang.String");
    }
}
