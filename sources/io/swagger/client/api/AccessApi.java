package io.swagger.client.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import io.swagger.client.ApiException;
import io.swagger.client.ApiInvoker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/* loaded from: classes2.dex */
public class AccessApi {
    String basePath = "https://localhost";
    ApiInvoker apiInvoker = ApiInvoker.getInstance();

    /* loaded from: classes2.dex */
    public enum accessListFormParams {
        ;

        private String value;

        accessListFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum accessListHeaderParams {
        ;

        private String value;

        accessListHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum accessListQueryParams {
        limit("limit"),
        offset("offset"),
        ordering("ordering"),
        search("search");

        private String value;

        accessListQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum accessReadFormParams {
        ;

        private String value;

        accessReadFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum accessReadHeaderParams {
        ;

        private String value;

        accessReadHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum accessReadQueryParams {
        ;

        private String value;

        accessReadQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    public void accessList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str, map.get(str)));
            }
        }
        if (map4 != null) {
            for (String str2 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map4.get(str2)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str3 : map2.keySet()) {
                hashMap.put(str3, map2.get(str3));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/access/", "GET", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
            }
        } catch (ApiException e) {
            throw e;
        } catch (InterruptedException e2) {
            throw e2;
        } catch (ExecutionException e3) {
            if (e3.getCause() instanceof VolleyError) {
                VolleyError volleyError = (VolleyError) e3.getCause();
                if (volleyError.networkResponse != null) {
                    throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
                }
            }
            throw e3;
        } catch (TimeoutException e4) {
            throw e4;
        }
    }

    public void accessList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/access/".replaceAll("\\{format\\}", "json");
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str, map.get(str)));
            }
        }
        if (map4 != null) {
            for (String str2 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map4.get(str2)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str3 : map2.keySet()) {
                hashMap.put(str3, map2.get(str3));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.AccessApi.1
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.AccessApi.2
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void accessRead(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling accessRead", new ApiException(400, "Missing the required parameter 'id' when calling accessRead"));
        }
        String replaceAll = "/v1/access/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str, map.get(str)));
            }
        }
        if (map4 != null) {
            for (String str2 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map4.get(str2)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str3 : map2.keySet()) {
                hashMap.put(str3, map2.get(str3));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
            }
        } catch (ApiException e) {
            throw e;
        } catch (InterruptedException e2) {
            throw e2;
        } catch (ExecutionException e3) {
            if (!(e3.getCause() instanceof VolleyError)) {
                throw e3;
            }
            VolleyError volleyError = (VolleyError) e3.getCause();
            if (volleyError.networkResponse == null) {
                throw e3;
            }
            throw new ApiException(volleyError.networkResponse.statusCode, volleyError.getMessage());
        } catch (TimeoutException e4) {
            throw e4;
        }
    }

    public void accessRead(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling accessRead", new ApiException(400, "Missing the required parameter 'id' when calling accessRead"));
        }
        String replaceAll = "/v1/access/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str, map.get(str)));
            }
        }
        if (map4 != null) {
            for (String str2 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map4.get(str2)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str3 : map2.keySet()) {
                hashMap.put(str3, map2.get(str3));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.AccessApi.3
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.AccessApi.4
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void addHeader(String str, String str2) {
        getInvoker().addDefaultHeader(str, str2);
    }

    public String getBasePath() {
        return this.basePath;
    }

    public ApiInvoker getInvoker() {
        return this.apiInvoker;
    }

    public void setBasePath(String str) {
        this.basePath = str;
    }
}
