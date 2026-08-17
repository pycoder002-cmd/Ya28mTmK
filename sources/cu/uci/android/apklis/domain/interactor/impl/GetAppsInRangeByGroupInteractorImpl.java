package cu.uci.android.apklis.domain.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.GetAppsInRangeByGroupInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.presentation.model.GroupType;
import cu.uci.android.apklis.storage.repository.converter.ApplicationConverter;
import cu.uci.android.apklis.storage.repository.model.ApiApplication;
import cu.uci.android.apklis.storage.repository.model.ApiResponse;
import io.swagger.client.api.ApplicationApi;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

/* loaded from: classes.dex */
public class GetAppsInRangeByGroupInteractorImpl extends AbstractInteractor implements GetAppsInRangeByGroupInteractor {
    private int group;
    private GetAppsInRangeByGroupInteractor.Callback mCallback;
    private int size;
    private int start;

    public GetAppsInRangeByGroupInteractorImpl(Executor executor, MainThread mainThread, GetAppsInRangeByGroupInteractor.Callback callback, int i, int i2, int i3) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.start = i;
        this.size = i2;
        this.group = i3;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        HashMap hashMap;
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.setBasePath(MainApp.SERVER_API_URL);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(ApplicationApi.applicationListQueryParams.offset.value(), String.valueOf(this.start));
        hashMap2.put(ApplicationApi.applicationListQueryParams.limit.value(), String.valueOf(this.size));
        HashMap hashMap3 = new HashMap();
        if (!((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.IGNORE_MY_SDK, Boolean.class, false)).booleanValue()) {
            hashMap3.put("releases__version_sdk__lte", MainApp.MY_SDK_VERSION + "");
        }
        if (this.group == GroupType.RECOMMENDED_APPLICATIONS.value()) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), "-sponsored,-updated");
            hashMap2.put(ApplicationApi.applicationListQueryParams.sponsoredGt.value(), "0");
        } else if (this.group == GroupType.CUBAN_GAMES_APPLICATIONS.value()) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), "-updated");
            hashMap3.put("categories__name__in", "Cuba");
        } else if (this.group == GroupType.LATEST_UPDATES.value()) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), "-updated");
            hashMap2.put(ApplicationApi.applicationListQueryParams.releasesCountGt.value(), "1");
        } else if (this.group == GroupType.FASHION_GAME_APPLICATIONS.value()) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), "-download_count,-reviews_count,-rating");
        } else if (this.group == GroupType.RECENT_RELEASES.value()) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), "-updated");
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(new Date());
            gregorianCalendar.add(6, -7);
            hashMap2.put(ApplicationApi.applicationListQueryParams.updatedGt.value(), gregorianCalendar.get(1) + "-" + (gregorianCalendar.get(2) + 1) + "-" + gregorianCalendar.get(5));
            hashMap2.put(ApplicationApi.applicationListQueryParams.releasesCount.value(), "1");
        } else if (this.group == GroupType.BEST_APPLICATIONS.value()) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), "-rating,-updated");
            hashMap3.put("categories__name__in", "Adultos,Arte y diseño,Comer y beber,Comunicación,Cuba,Deportes,Educación,Entretenimiento,Estilo de vida,Eventos,Familia,Finanzas,Fotografía,Herramientas,Música y audio,Noticias y revistas,Salud y bienestar,Utilidades,Viajes");
        } else if (this.group == GroupType.BEST_GAMES.value()) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), "-rating,-updated");
            hashMap3.put("categories__name__in", "Acción,Carreras,Estrategia,Preguntas y respuestas");
        } else if (this.group == GroupType.TOOLS_UTILITIES.value()) {
            hashMap2.put(ApplicationApi.applicationListQueryParams.ordering.value(), "-rating,-updated");
            hashMap3.put("categories__name__in", "Herramientas,Comunicación,Mapas y navegación,Medicina,Personalización,Utilidades,Salud y bienestar");
        }
        if (MainApp.isUserAuthenticate()) {
            hashMap = new HashMap();
            hashMap.put("Content-Type", "application/json");
            hashMap.put("Accept", "application/json");
            hashMap.put("Authorization", MainApp.userAccount.getAccessToken());
        } else {
            hashMap = null;
        }
        applicationApi.applicationList(hashMap2, hashMap, null, hashMap3, new Response.Listener<String>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeByGroupInteractorImpl.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str) {
                final App[] appArr;
                App[] appArr2 = new App[0];
                try {
                    ApiResponse apiResponse = (ApiResponse) new Gson().fromJson(new String(str.getBytes("ISO-8859-1"), "UTF-8"), new TypeToken<ApiResponse<ApiApplication>>() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeByGroupInteractorImpl.1.1
                    }.getType());
                    r2 = apiResponse.getNext() != null;
                    appArr = ApplicationConverter.convertToViewModel((ApiApplication[]) apiResponse.getResults());
                } catch (Exception e) {
                    MainApp.log(getClass().getName(), e);
                    appArr = appArr2;
                }
                GetAppsInRangeByGroupInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeByGroupInteractorImpl.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        GetAppsInRangeByGroupInteractorImpl.this.mCallback.onAppsRetrieved(appArr, GetAppsInRangeByGroupInteractorImpl.this.group, r3);
                    }
                });
            }
        }, new Response.ErrorListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeByGroupInteractorImpl.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                GetAppsInRangeByGroupInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.GetAppsInRangeByGroupInteractorImpl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GetAppsInRangeByGroupInteractorImpl.this.mCallback.onAppsRetrieved(new App[0], -1, false);
                    }
                });
            }
        });
        return null;
    }
}
