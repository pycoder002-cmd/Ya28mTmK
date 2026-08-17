package cu.uci.android.apklis.domain.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.ApiRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetReviewsByAppInRangeInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.presentation.model.Review;
import cu.uci.android.apklis.storage.repository.converter.ReviewConverter;
import cu.uci.android.apklis.storage.repository.model.ApiResponse;
import cu.uci.android.apklis.storage.repository.model.ApiReview;
import io.swagger.client.api.ReviewApi;
import java.util.HashMap;

/* loaded from: classes.dex */
public class GetReviewsByAppInRangeInteractorImpl extends AbstractInteractor implements GetReviewsByAppInRangeInteractor {
    private Integer application;
    private Integer excludeUserId;
    private GetReviewsByAppInRangeInteractor.Callback mCallback;
    private ApiRepository repository;
    private int size;
    private int start;

    public GetReviewsByAppInRangeInteractorImpl(Executor executor, MainThread mainThread, GetReviewsByAppInRangeInteractor.Callback callback, int i, int i2, Integer num, Integer num2) {
        super(executor, mainThread);
        this.mCallback = callback;
        this.start = i;
        this.size = i2;
        this.application = num;
        this.excludeUserId = num2;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        ReviewApi reviewApi = new ReviewApi();
        reviewApi.setBasePath(MainApp.SERVER_API_URL);
        HashMap hashMap = new HashMap();
        hashMap.put(ReviewApi.reviewListQueryParams.limit.value(), String.valueOf(50));
        hashMap.put(ReviewApi.reviewListQueryParams.offset.value(), String.valueOf(0));
        hashMap.put(ReviewApi.reviewListQueryParams.ordering.value(), String.valueOf("-published"));
        hashMap.put(ReviewApi.reviewListQueryParams.search.value(), String.valueOf(""));
        hashMap.put(ReviewApi.reviewListQueryParams.application.value(), String.valueOf(this.application));
        HashMap hashMap2 = new HashMap();
        if (this.excludeUserId.intValue() != -1) {
            hashMap2.put("user!", String.valueOf(this.excludeUserId));
        }
        reviewApi.reviewList(hashMap, null, null, hashMap2, new Response.Listener<String>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByAppInRangeInteractorImpl.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str) {
                final Review[] reviewArr;
                Review[] reviewArr2 = new Review[0];
                try {
                    reviewArr = ReviewConverter.convertToViewModel((ApiReview[]) ((ApiResponse) new Gson().fromJson(new String(str.getBytes("ISO-8859-1"), "UTF-8"), new TypeToken<ApiResponse<ApiReview>>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByAppInRangeInteractorImpl.1.1
                    }.getType())).getResults(), GetReviewsByAppInRangeInteractorImpl.this.excludeUserId);
                } catch (Exception e) {
                    MainApp.log(getClass().getName(), e);
                    reviewArr = reviewArr2;
                }
                GetReviewsByAppInRangeInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByAppInRangeInteractorImpl.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        GetReviewsByAppInRangeInteractorImpl.this.mCallback.onReviewsLoaded(reviewArr);
                    }
                });
            }
        }, new Response.ErrorListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByAppInRangeInteractorImpl.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                GetReviewsByAppInRangeInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetReviewsByAppInRangeInteractorImpl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GetReviewsByAppInRangeInteractorImpl.this.mCallback.onReviewsLoaded(new Review[0]);
                    }
                });
            }
        });
        return null;
    }
}
