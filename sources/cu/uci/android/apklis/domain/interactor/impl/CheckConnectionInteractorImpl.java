package cu.uci.android.apklis.domain.interactor.impl;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.CheckConnectionInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import io.swagger.client.api.ApplicationApi;
import java.util.HashMap;

/* loaded from: classes.dex */
public class CheckConnectionInteractorImpl extends AbstractInteractor implements CheckConnectionInteractor {
    private CheckConnectionInteractor.Callback mCallback;

    public CheckConnectionInteractorImpl(Executor executor, MainThread mainThread, CheckConnectionInteractor.Callback callback) {
        super(executor, mainThread);
        if (callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        ApplicationApi applicationApi = new ApplicationApi();
        applicationApi.setBasePath(MainApp.SERVER_API_URL);
        HashMap hashMap = new HashMap();
        hashMap.put(ApplicationApi.applicationListQueryParams.limit.value(), String.valueOf(1));
        hashMap.put(ApplicationApi.applicationListQueryParams.offset.value(), String.valueOf(0));
        hashMap.put(ApplicationApi.applicationListQueryParams.ordering.value(), String.valueOf(""));
        hashMap.put(ApplicationApi.applicationListQueryParams.search.value(), String.valueOf(""));
        applicationApi.applicationList(hashMap, null, null, null, new Response.Listener<String>() { // from class: cu.uci.android.apklis.domain.interactor.impl.CheckConnectionInteractorImpl.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str) {
                CheckConnectionInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.CheckConnectionInteractorImpl.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CheckConnectionInteractorImpl.this.mCallback.online();
                    }
                });
            }
        }, new Response.ErrorListener() { // from class: cu.uci.android.apklis.domain.interactor.impl.CheckConnectionInteractorImpl.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                CheckConnectionInteractorImpl.this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.CheckConnectionInteractorImpl.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CheckConnectionInteractorImpl.this.mCallback.offline();
                    }
                });
            }
        });
        return null;
    }
}
