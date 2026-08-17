package cu.uci.android.apklis.domain.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetAppsInRangeInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.storage.repository.converter.ApplicationConverter;
import cu.uci.android.apklis.storage.repository.model.ApiApplication;
import cu.uci.android.apklis.storage.repository.model.ApiResponse;
import io.swagger.client.api.ApplicationApi;
import java.util.HashMap;

/* loaded from: classes.dex */
public class GetAppsInRangeInteractorImpl extends AbstractInteractor implements GetAppsInRangeInteractor {
    private GetAppsInRangeInteractor.Callback mCallback;
    private String query;
    private int size;
    private int start;

    public GetAppsInRangeInteractorImpl(Executor executor, MainThread mainThread, GetAppsInRangeInteractor.Callback callback, int i, int i2, String str) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.start = i;
        this.size = i2;
        this.query = str;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        HashMap hashMap;
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.setBasePath(MainApp.SERVER_API_URL);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(ApplicationApi.applicationListQueryParams.limit.value(), String.valueOf(this.size));
        hashMap2.put(ApplicationApi.applicationListQueryParams.offset.value(), String.valueOf(this.start));
        if (this.query != null) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.contains.value(), String.valueOf(this.query));
        }
        HashMap hashMap3 = new HashMap();
        if (!((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.IGNORE_MY_SDK, Boolean.class, false)).booleanValue()) {
            hashMap3.put("releases__version_sdk__lte", MainApp.MY_SDK_VERSION + "");
        }
        if (MainApp.isUserAuthenticate()) {
            hashMap = new HashMap();
            hashMap.put("Content-Type", "application/json");
            hashMap.put("Accept", "application/json");
            hashMap.put("Authorization", MainApp.userAccount.getAccessToken());
        } else {
            hashMap = null;
        }
        applicationApi.applicationList(hashMap2, hashMap, null, hashMap3, new Response.Listener<String>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeInteractorImpl.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str) {
                final App[] appArr;
                App[] appArr2 = new App[0];
                try {
                    appArr = ApplicationConverter.convertToViewModel((ApiApplication[]) ((ApiResponse) new Gson().fromJson(new String(str.getBytes("ISO-8859-1"), "UTF-8"), new TypeToken<ApiResponse<ApiApplication>>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeInteractorImpl.1.1
                    }.getType())).getResults());
                } catch (Exception e) {
                    MainApp.log(getClass().getName(), e);
                    appArr = appArr2;
                }
                GetAppsInRangeInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeInteractorImpl.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        GetAppsInRangeInteractorImpl.this.mCallback.onAppsRetrieved(appArr);
                    }
                });
            }
        }, new Response.ErrorListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeInteractorImpl.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                GetAppsInRangeInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeInteractorImpl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GetAppsInRangeInteractorImpl.this.mCallback.onAppsRetrieved(new App[0]);
                    }
                });
            }
        });
        return null;
    }
}
