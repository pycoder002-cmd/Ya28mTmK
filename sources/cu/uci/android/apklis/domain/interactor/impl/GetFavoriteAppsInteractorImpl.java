package cu.uci.android.apklis.domain.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetAppsInRangeByUserInteractor;
import cu.uci.android.apklis.domain.interactor.GetAppsInRangeInteractor;
import cu.uci.android.apklis.domain.interactor.GetFavoriteAppsInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.storage.repository.converter.ApplicationConverter;
import cu.uci.android.apklis.storage.repository.model.ApiApplication;
import cu.uci.android.apklis.storage.repository.model.ApiResponse;
import io.swagger.client.api.ApplicationApi;
import java.util.HashMap;

/* loaded from: classes.dex */
public class GetFavoriteAppsInteractorImpl extends AbstractInteractor implements GetAppsInRangeInteractor, GetFavoriteAppsInteractor {
    private GetAppsInRangeByUserInteractor.Callback mCallback;
    private String resStr;
    private String user;

    public GetFavoriteAppsInteractorImpl(Executor executor, MainThread mainThread, GetAppsInRangeByUserInteractor.Callback callback, String str, String str2) {
        super(executor, mainThread);
        this.resStr = "";
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.user = str;
        this.resStr = str2;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        HashMap hashMap;
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.setBasePath(MainApp.SERVER_API_URL);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(ApplicationApi.applicationListQueryParams.offset.value(), String.valueOf(0));
        if (this.resStr.isEmpty()) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.favoriteApps.value(), String.valueOf("-1"));
        } else {
            hashMap2.put(ApplicationApi.applicationListQueryParams.favoriteApps.value(), String.valueOf(this.resStr));
        }
        HashMap hashMap3 = new HashMap();
        if (MainApp.isUserAuthenticate()) {
            hashMap = new HashMap();
            hashMap.put("Content-Type", "application/json");
            hashMap.put("Accept", "application/json");
            hashMap.put("Authorization", MainApp.userAccount.getAccessToken());
        } else {
            hashMap = null;
        }
        applicationApi.applicationList(hashMap2, hashMap, null, hashMap3, new Response.Listener<String>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetFavoriteAppsInteractorImpl.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(final String str) {
                GetFavoriteAppsInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetFavoriteAppsInteractorImpl.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        App[] appArr = new App[0];
                        try {
                            appArr = ApplicationConverter.convertToViewModel((ApiApplication[]) ((ApiResponse) new Gson().fromJson(new String(str.getBytes("ISO-8859-1"), "UTF-8"), new TypeToken<ApiResponse<ApiApplication>>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetFavoriteAppsInteractorImpl.1.1.1
                            }.getType())).getResults());
                        } catch (Exception e) {
                            MainApp.log(getClass().getName(), e);
                        }
                        GetFavoriteAppsInteractorImpl.this.mCallback.onAppsRetrieved(appArr);
                    }
                });
            }
        }, new Response.ErrorListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetFavoriteAppsInteractorImpl.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                GetFavoriteAppsInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetFavoriteAppsInteractorImpl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GetFavoriteAppsInteractorImpl.this.mCallback.onAppsRetrieved(new App[0]);
                    }
                });
            }
        });
        return null;
    }
}
