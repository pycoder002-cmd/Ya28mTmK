package cu.uci.android.apklis.domain.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetCategoriesInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.domain.model.Category;
import cu.uci.android.apklis.storage.repository.converter.CategoryConverter;
import cu.uci.android.apklis.storage.repository.model.ApiCategory;
import io.swagger.client.api.CategoryApi;
import java.util.HashMap;

/* loaded from: classes.dex */
public class GetCategoriesInteractorImpl extends AbstractInteractor implements GetCategoriesInteractor {
    private GetCategoriesInteractor.Callback mCallback;

    public GetCategoriesInteractorImpl(Executor executor, MainThread mainThread, GetCategoriesInteractor.Callback callback) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        CategoryApi categoryApi = new CategoryApi();
        categoryApi.setBasePath(MainApp.SERVER_API_URL);
        HashMap hashMap = new HashMap();
        hashMap.put(CategoryApi.categoryListQueryParams.ordering.value(), String.valueOf(""));
        hashMap.put(CategoryApi.categoryListQueryParams.search.value(), String.valueOf(""));
        categoryApi.categoryList(hashMap, null, null, null, new Response.Listener<String>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetCategoriesInteractorImpl.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(final String str) {
                GetCategoriesInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetCategoriesInteractorImpl.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Category[] categoryArr = new Category[0];
                        try {
                            categoryArr = CategoryConverter.convertToViewModel((ApiCategory[]) new Gson().fromJson(new String(str.getBytes("ISO-8859-1"), "UTF-8"), ApiCategory[].class));
                        } catch (Exception e) {
                            MainApp.log(getClass().getName(), e);
                        }
                        GetCategoriesInteractorImpl.this.mCallback.onCategoriesReturned(categoryArr);
                    }
                });
            }
        }, new Response.ErrorListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetCategoriesInteractorImpl.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                GetCategoriesInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetCategoriesInteractorImpl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GetCategoriesInteractorImpl.this.mCallback.onCategoriesReturned(new Category[0]);
                    }
                });
            }
        });
        return null;
    }
}
