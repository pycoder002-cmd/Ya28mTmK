package cu.uci.android.apklis.domain.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.DeleteUserReviewInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import io.swagger.client.api.ReviewApi;
import java.util.HashMap;

/* loaded from: classes.dex */
public class DeleteUserReviewInteractorImpl extends AbstractInteractor implements DeleteUserReviewInteractor {
    private DeleteUserReviewInteractor.Callback mCallback;
    private Integer reviewId;

    public DeleteUserReviewInteractorImpl(Executor executor, MainThread mainThread, DeleteUserReviewInteractor.Callback callback, Integer num) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.reviewId = num;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        ReviewApi reviewApi = new ReviewApi();
        reviewApi.setBasePath(MainApp.SERVER_API_URL);
        HashMap hashMap = new HashMap();
        hashMap.put("Authorization", MainApp.userAccount.getAccessToken());
        reviewApi.reviewDelete(this.reviewId, null, hashMap, null, null, new Response.Listener<String>() { // from class: cu.uci.android.apklis.domain.interactor.impl.DeleteUserReviewInteractorImpl.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str) {
                DeleteUserReviewInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.DeleteUserReviewInteractorImpl.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DeleteUserReviewInteractorImpl.this.mCallback.onReviewDeleted(true);
                    }
                });
            }
        }, new Response.ErrorListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.DeleteUserReviewInteractorImpl.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                DeleteUserReviewInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.DeleteUserReviewInteractorImpl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        DeleteUserReviewInteractorImpl.this.mCallback.onReviewDeleted(false);
                    }
                });
            }
        });
        return null;
    }
}
