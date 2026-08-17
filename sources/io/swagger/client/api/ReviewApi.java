package io.swagger.client.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import io.swagger.client.ApiException;
import io.swagger.client.ApiInvoker;
import io.swagger.client.model.Data19;
import io.swagger.client.model.Data20;
import io.swagger.client.model.Data21;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/* loaded from: classes2.dex */
public class ReviewApi {
    String basePath = "https://localhost";
    ApiInvoker apiInvoker = ApiInvoker.getInstance();

    /* loaded from: classes2.dex */
    public enum reviewCreateFormParams {
        ;

        private String value;

        reviewCreateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewCreateHeaderParams {
        ;

        private String value;

        reviewCreateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewCreateQueryParams {
        ;

        private String value;

        reviewCreateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewDeleteFormParams {
        ;

        private String value;

        reviewDeleteFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewDeleteHeaderParams {
        ;

        private String value;

        reviewDeleteHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewDeleteQueryParams {
        ;

        private String value;

        reviewDeleteQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewListFormParams {
        ;

        private String value;

        reviewListFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewListHeaderParams {
        ;

        private String value;

        reviewListHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewListQueryParams {
        limit("limit"),
        offset("offset"),
        ordering("ordering"),
        search("search"),
        application("application"),
        user("user");

        private String value;

        reviewListQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewPartialUpdateFormParams {
        ;

        private String value;

        reviewPartialUpdateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewPartialUpdateHeaderParams {
        ;

        private String value;

        reviewPartialUpdateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewPartialUpdateQueryParams {
        ;

        private String value;

        reviewPartialUpdateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewReadFormParams {
        ;

        private String value;

        reviewReadFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewReadHeaderParams {
        ;

        private String value;

        reviewReadHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewReadQueryParams {
        ;

        private String value;

        reviewReadQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewUpdateFormParams {
        ;

        private String value;

        reviewUpdateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewUpdateHeaderParams {
        ;

        private String value;

        reviewUpdateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum reviewUpdateQueryParams {
        ;

        private String value;

        reviewUpdateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
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

    public void reviewCreate(Data19 data19, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
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
        String[] strArr = {"application/json"};
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        Object obj = data19;
        if (str4.startsWith("multipart/form-data")) {
            obj = MultipartEntityBuilder.create().build();
        }
        Object obj2 = obj;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/review/", HttpPost.METHOD_NAME, arrayList, obj2, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void reviewCreate(Data19 data19, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/review/".replaceAll("\\{format\\}", "json");
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
        String[] strArr = {"application/json"};
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data19;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, HttpPost.METHOD_NAME, arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReviewApi.1
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReviewApi.2
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void reviewDelete(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling reviewDelete", new ApiException(400, "Missing the required parameter 'id' when calling reviewDelete"));
        }
        String replaceAll = "/v1/review/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "DELETE", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void reviewDelete(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling reviewDelete", new ApiException(400, "Missing the required parameter 'id' when calling reviewDelete"));
        }
        String replaceAll = "/v1/review/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "DELETE", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReviewApi.3
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReviewApi.4
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void reviewList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
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
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/review/", "GET", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void reviewList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/review/".replaceAll("\\{format\\}", "json");
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReviewApi.5
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReviewApi.6
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void reviewPartialUpdate(Integer num, Data21 data21, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling reviewPartialUpdate", new ApiException(400, "Missing the required parameter 'id' when calling reviewPartialUpdate"));
        }
        String replaceAll = "/v1/review/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
        String[] strArr = {"application/json"};
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data21;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PATCH", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void reviewPartialUpdate(Integer num, Data21 data21, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling reviewPartialUpdate", new ApiException(400, "Missing the required parameter 'id' when calling reviewPartialUpdate"));
        }
        String replaceAll = "/v1/review/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
        String[] strArr = {"application/json"};
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data21;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PATCH", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReviewApi.7
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReviewApi.8
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void reviewRead(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling reviewRead", new ApiException(400, "Missing the required parameter 'id' when calling reviewRead"));
        }
        String replaceAll = "/v1/review/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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

    public void reviewRead(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling reviewRead", new ApiException(400, "Missing the required parameter 'id' when calling reviewRead"));
        }
        String replaceAll = "/v1/review/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReviewApi.9
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReviewApi.10
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void reviewUpdate(Integer num, Data20 data20, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling reviewUpdate", new ApiException(400, "Missing the required parameter 'id' when calling reviewUpdate"));
        }
        String replaceAll = "/v1/review/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
        String[] strArr = {"application/json"};
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data20;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void reviewUpdate(Integer num, Data20 data20, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling reviewUpdate", new ApiException(400, "Missing the required parameter 'id' when calling reviewUpdate"));
        }
        String replaceAll = "/v1/review/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
        String[] strArr = {"application/json"};
        String str4 = strArr.length > 0 ? strArr[0] : "application/json";
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data20;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReviewApi.11
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReviewApi.12
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void setBasePath(String str) {
        this.basePath = str;
    }
}
