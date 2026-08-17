package cu.uci.android.apklis.domain.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.device.PackageManagerHelper;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetAppsToUpdateInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.storage.repository.converter.ApplicationConverter;
import cu.uci.android.apklis.storage.repository.model.ApiApplication;
import cu.uci.android.apklis.storage.repository.model.ApiResponse;
import io.swagger.client.api.ApplicationApi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class GetAppsToUpdateInteractorImpl extends AbstractInteractor implements GetAppsToUpdateInteractor {
    private GetAppsToUpdateInteractor.Callback mCallback;

    public GetAppsToUpdateInteractorImpl(Executor executor, MainThread mainThread, GetAppsToUpdateInteractor.Callback callback) {
        super(executor, mainThread);
        this.mCallback = callback;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        HashMap hashMap;
        final Map<String, Integer> userInstalledApps = PackageManagerHelper.getUserInstalledApps();
        StringBuilder sb = null;
        for (String str : userInstalledApps.keySet()) {
            if (sb == null) {
                sb = new StringBuilder(str);
            } else {
                sb.append(", ");
                sb.append(str);
            }
        }
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.setBasePath(MainApp.SERVER_API_URL);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(ApplicationApi.applicationListQueryParams.limit.value(), String.valueOf(userInstalledApps.size()));
        hashMap2.put(ApplicationApi.applicationListQueryParams.offset.value(), String.valueOf(0));
        hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), String.valueOf(""));
        hashMap2.put(ApplicationApi.applicationListQueryParams.packageNameIn.value(), String.valueOf(sb.toString()));
        hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), "-sponsored,-updated");
        HashMap hashMap3 = new HashMap();
        if (!((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.IGNORE_MY_SDK, Boolean.class, false)).booleanValue()) {
            hashMap3.put("releases__version_sdk__lte", MainApp.MY_SDK_VERSION + "");
        }
        if (MainApp.isUserAuthenticate()) {
            HashMap hashMap4 = new HashMap();
            hashMap4.put("Content-Type", "application/json");
            hashMap4.put("Accept", "application/json");
            hashMap4.put("Authorization", MainApp.userAccount.getAccessToken());
            hashMap = hashMap4;
        } else {
            hashMap = null;
        }
        applicationApi.applicationList(hashMap2, hashMap, null, hashMap3, new Response.Listener<String>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsToUpdateInteractorImpl.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str2) {
                final App[] appArr;
                App[] appArr2 = new App[0];
                try {
                    ApiResponse apiResponse = (ApiResponse) new Gson().fromJson(new String(str2.getBytes("ISO-8859-1"), "UTF-8"), new TypeToken<ApiResponse<ApiApplication>>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsToUpdateInteractorImpl.1.1
                    }.getType());
                    ArrayList arrayList = new ArrayList();
                    for (ApiApplication apiApplication : (ApiApplication[]) apiResponse.getResults()) {
                        Integer num = (Integer) userInstalledApps.get(apiApplication.getPackage_name());
                        if (num != null) {
                            if (num.intValue() < apiApplication.getLast_release().getVersion_code().intValue()) {
                                arrayList.add(apiApplication);
                            }
                        }
                    }
                    appArr = ApplicationConverter.convertToViewModel((ApiApplication[]) arrayList.toArray(new ApiApplication[arrayList.size()]));
                } catch (Exception e) {
                    MainApp.log(getClass().getName(), e);
                    appArr = appArr2;
                }
                GetAppsToUpdateInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsToUpdateInteractorImpl.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        GetAppsToUpdateInteractorImpl.this.mCallback.onAppsToUpdateLoaded(appArr);
                    }
                });
            }
        }, new Response.ErrorListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsToUpdateInteractorImpl.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                GetAppsToUpdateInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsToUpdateInteractorImpl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GetAppsToUpdateInteractorImpl.this.mCallback.onAppsToUpdateLoaded(new App[0]);
                    }
                });
            }
        });
        return null;
    }
}
