package io.swagger.client.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import io.swagger.client.ApiException;
import io.swagger.client.ApiInvoker;
import io.swagger.client.model.Data11;
import io.swagger.client.model.Data12;
import io.swagger.client.model.Data13;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/* loaded from: classes2.dex */
public class ComplaintKindApi {
    String basePath = "https://localhost";
    ApiInvoker apiInvoker = ApiInvoker.getInstance();

    /* loaded from: classes2.dex */
    public enum complaintKindCreateFormParams {
        ;

        private String value;

        complaintKindCreateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindCreateHeaderParams {
        ;

        private String value;

        complaintKindCreateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindCreateQueryParams {
        ;

        private String value;

        complaintKindCreateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindDeleteFormParams {
        ;

        private String value;

        complaintKindDeleteFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindDeleteHeaderParams {
        ;

        private String value;

        complaintKindDeleteHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindDeleteQueryParams {
        ;

        private String value;

        complaintKindDeleteQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindListFormParams {
        ;

        private String value;

        complaintKindListFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindListHeaderParams {
        ;

        private String value;

        complaintKindListHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindListQueryParams {
        ordering("ordering"),
        search("search");

        private String value;

        complaintKindListQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindPartialUpdateFormParams {
        ;

        private String value;

        complaintKindPartialUpdateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindPartialUpdateHeaderParams {
        ;

        private String value;

        complaintKindPartialUpdateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindPartialUpdateQueryParams {
        ;

        private String value;

        complaintKindPartialUpdateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindReadFormParams {
        ;

        private String value;

        complaintKindReadFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindReadHeaderParams {
        ;

        private String value;

        complaintKindReadHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindReadQueryParams {
        ;

        private String value;

        complaintKindReadQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindUpdateFormParams {
        ;

        private String value;

        complaintKindUpdateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindUpdateHeaderParams {
        ;

        private String value;

        complaintKindUpdateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum complaintKindUpdateQueryParams {
        ;

        private String value;

        complaintKindUpdateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    public void addHeader(String str, String str2) {
        getInvoker().addDefaultHeader(str, str2);
    }

    public void complaintKindCreate(Data11 data11, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
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
        Object obj = data11;
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
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/complaint_kind/", HttpPost.METHOD_NAME, arrayList, obj2, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void complaintKindCreate(Data11 data11, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/complaint_kind/".replaceAll("\\{format\\}", "json");
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
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data11;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, HttpPost.METHOD_NAME, arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ComplaintKindApi.1
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ComplaintKindApi.2
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void complaintKindDelete(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (str == null) {
            new VolleyError("Missing the required parameter 'title' when calling complaintKindDelete", new ApiException(400, "Missing the required parameter 'title' when calling complaintKindDelete"));
        }
        String replaceAll = "/v1/complaint_kind/{title}/".replaceAll("\\{title\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str2 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map.get(str2)));
            }
        }
        if (map4 != null) {
            for (String str3 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map4.get(str3)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str4 : map2.keySet()) {
                hashMap.put(str4, map2.get(str4));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "DELETE", arrayList, build, hashMap, hashMap2, str5, new String[0]) != null) {
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

    public void complaintKindDelete(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (str == null) {
            new VolleyError("Missing the required parameter 'title' when calling complaintKindDelete", new ApiException(400, "Missing the required parameter 'title' when calling complaintKindDelete"));
        }
        String replaceAll = "/v1/complaint_kind/{title}/".replaceAll("\\{format\\}", "json").replaceAll("\\{title\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str2 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map.get(str2)));
            }
        }
        if (map4 != null) {
            for (String str3 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map4.get(str3)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str4 : map2.keySet()) {
                hashMap.put(str4, map2.get(str4));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "DELETE", arrayList, build, hashMap, hashMap2, str5, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ComplaintKindApi.3
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str7) {
                    listener.onResponse(str7);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ComplaintKindApi.4
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void complaintKindList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
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
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/complaint_kind/", "GET", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void complaintKindList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/complaint_kind/".replaceAll("\\{format\\}", "json");
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ComplaintKindApi.5
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ComplaintKindApi.6
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void complaintKindPartialUpdate(String str, Data13 data13, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (str == null) {
            new VolleyError("Missing the required parameter 'title' when calling complaintKindPartialUpdate", new ApiException(400, "Missing the required parameter 'title' when calling complaintKindPartialUpdate"));
        }
        String replaceAll = "/v1/complaint_kind/{title}/".replaceAll("\\{title\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str2 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map.get(str2)));
            }
        }
        if (map4 != null) {
            for (String str3 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map4.get(str3)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str4 : map2.keySet()) {
                hashMap.put(str4, map2.get(str4));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        Object build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data13;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PATCH", arrayList, build, hashMap, hashMap2, str5, new String[0]) != null) {
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

    public void complaintKindPartialUpdate(String str, Data13 data13, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (str == null) {
            new VolleyError("Missing the required parameter 'title' when calling complaintKindPartialUpdate", new ApiException(400, "Missing the required parameter 'title' when calling complaintKindPartialUpdate"));
        }
        String replaceAll = "/v1/complaint_kind/{title}/".replaceAll("\\{format\\}", "json").replaceAll("\\{title\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str2 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map.get(str2)));
            }
        }
        if (map4 != null) {
            for (String str3 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map4.get(str3)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str4 : map2.keySet()) {
                hashMap.put(str4, map2.get(str4));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        Object build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data13;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PATCH", arrayList, build, hashMap, hashMap2, str5, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ComplaintKindApi.7
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str7) {
                    listener.onResponse(str7);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ComplaintKindApi.8
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void complaintKindRead(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (str == null) {
            new VolleyError("Missing the required parameter 'title' when calling complaintKindRead", new ApiException(400, "Missing the required parameter 'title' when calling complaintKindRead"));
        }
        String replaceAll = "/v1/complaint_kind/{title}/".replaceAll("\\{title\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str2 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map.get(str2)));
            }
        }
        if (map4 != null) {
            for (String str3 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map4.get(str3)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str4 : map2.keySet()) {
                hashMap.put(str4, map2.get(str4));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str5, new String[0]) != null) {
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

    public void complaintKindRead(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (str == null) {
            new VolleyError("Missing the required parameter 'title' when calling complaintKindRead", new ApiException(400, "Missing the required parameter 'title' when calling complaintKindRead"));
        }
        String replaceAll = "/v1/complaint_kind/{title}/".replaceAll("\\{format\\}", "json").replaceAll("\\{title\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str2 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map.get(str2)));
            }
        }
        if (map4 != null) {
            for (String str3 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map4.get(str3)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str4 : map2.keySet()) {
                hashMap.put(str4, map2.get(str4));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str5, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ComplaintKindApi.9
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str7) {
                    listener.onResponse(str7);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ComplaintKindApi.10
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void complaintKindUpdate(String str, Data12 data12, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (str == null) {
            new VolleyError("Missing the required parameter 'title' when calling complaintKindUpdate", new ApiException(400, "Missing the required parameter 'title' when calling complaintKindUpdate"));
        }
        String replaceAll = "/v1/complaint_kind/{title}/".replaceAll("\\{title\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str2 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map.get(str2)));
            }
        }
        if (map4 != null) {
            for (String str3 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map4.get(str3)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str4 : map2.keySet()) {
                hashMap.put(str4, map2.get(str4));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        Object build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data12;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, build, hashMap, hashMap2, str5, new String[0]) != null) {
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

    public void complaintKindUpdate(String str, Data12 data12, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (str == null) {
            new VolleyError("Missing the required parameter 'title' when calling complaintKindUpdate", new ApiException(400, "Missing the required parameter 'title' when calling complaintKindUpdate"));
        }
        String replaceAll = "/v1/complaint_kind/{title}/".replaceAll("\\{format\\}", "json").replaceAll("\\{title\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str2 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str2, map.get(str2)));
            }
        }
        if (map4 != null) {
            for (String str3 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map4.get(str3)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str4 : map2.keySet()) {
                hashMap.put(str4, map2.get(str4));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        Object build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data12;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, build, hashMap, hashMap2, str5, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ComplaintKindApi.11
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str7) {
                    listener.onResponse(str7);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ComplaintKindApi.12
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
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
