package cu.uci.android.apklis.domain.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.ApiRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetReviewsByUsernameInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.presentation.model.Review;
import cu.uci.android.apklis.storage.repository.converter.ReviewConverter;
import cu.uci.android.apklis.storage.repository.model.ApiResponse;
import cu.uci.android.apklis.storage.repository.model.ApiReview;
import io.swagger.client.api.ReviewApi;
import java.util.HashMap;

/* loaded from: classes.dex */
public class GetReviewsByUsernameInteractorImpl extends AbstractInteractor implements GetReviewsByUsernameInteractor {
    private Integer appId;
    private GetReviewsByUsernameInteractor.Callback mCallback;
    private ApiRepository repository;
    private Integer userId;

    public GetReviewsByUsernameInteractorImpl(Executor executor, MainThread mainThread, GetReviewsByUsernameInteractor.Callback callback, Integer num, Integer num2) {
        super(executor, mainThread);
        this.mCallback = callback;
        this.userId = num;
        this.appId = num2;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        ReviewApi reviewApi = new ReviewApi();
        reviewApi.setBasePath(MainApp.SERVER_API_URL);
        HashMap hashMap = new HashMap();
        hashMap.put(ReviewApi.reviewListQueryParams.limit.value(), String.valueOf(1));
        hashMap.put(ReviewApi.reviewListQueryParams.offset.value(), String.valueOf(0));
        hashMap.put(ReviewApi.reviewListQueryParams.ordering.value(), String.valueOf(""));
        hashMap.put(ReviewApi.reviewListQueryParams.search.value(), String.valueOf(""));
        hashMap.put(ReviewApi.reviewListQueryParams.user.value(), String.valueOf(this.userId));
        hashMap.put(ReviewApi.reviewListQueryParams.application.value(), String.valueOf(this.appId));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("Authorization", MainApp.userAccount.getAccessToken());
        reviewApi.reviewList(hashMap, hashMap2, null, null, new Response.Listener<String>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByUsernameInteractorImpl.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str) {
                Review[] reviewArr;
                Review[] reviewArr2 = new Review[0];
                try {
                    reviewArr = ReviewConverter.convertToViewModel((ApiReview[]) ((ApiResponse) new Gson().fromJson(new String(str.getBytes("ISO-8859-1"), "UTF-8"), new TypeToken<ApiResponse<ApiReview>>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByUsernameInteractorImpl.1.1
                    }.getType())).getResults(), -1);
                } catch (Exception e) {
                    MainApp.log(getClass().getName(), e);
                    reviewArr = reviewArr2;
                }
                final Review review = reviewArr.length > 0 ? reviewArr[0] : null;
                GetReviewsByUsernameInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByUsernameInteractorImpl.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        GetReviewsByUsernameInteractorImpl.this.mCallback.onReviewLoaded(review);
                    }
                });
            }
        }, new Response.ErrorListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByUsernameInteractorImpl.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                GetReviewsByUsernameInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByUsernameInteractorImpl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GetReviewsByUsernameInteractorImpl.this.mCallback.onReviewLoaded(null);
                    }
                });
            }
        });
        return null;
    }
}
