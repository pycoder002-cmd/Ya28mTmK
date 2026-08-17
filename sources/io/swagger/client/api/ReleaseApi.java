package io.swagger.client.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import io.swagger.client.ApiException;
import io.swagger.client.ApiInvoker;
import io.swagger.client.model.Data17;
import io.swagger.client.model.Data18;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/* loaded from: classes2.dex */
public class ReleaseApi {
    String basePath = "https://localhost";
    ApiInvoker apiInvoker = ApiInvoker.getInstance();

    /* loaded from: classes2.dex */
    public enum releaseCreateFormParams {
        _public(HeaderConstants.PUBLIC);

        private String value;

        releaseCreateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseCreateHeaderParams {
        ;

        private String value;

        releaseCreateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseCreateQueryParams {
        ;

        private String value;

        releaseCreateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseDeleteFormParams {
        ;

        private String value;

        releaseDeleteFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseDeleteHeaderParams {
        ;

        private String value;

        releaseDeleteHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseDeleteQueryParams {
        ;

        private String value;

        releaseDeleteQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseDownloadFormParams {
        ;

        private String value;

        releaseDownloadFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseDownloadHeaderParams {
        ;

        private String value;

        releaseDownloadHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseDownloadQueryParams {
        ;

        private String value;

        releaseDownloadQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseListFormParams {
        ;

        private String value;

        releaseListFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseListHeaderParams {
        ;

        private String value;

        releaseListHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseListQueryParams {
        limit("limit"),
        offset("offset"),
        ordering("ordering"),
        search("search"),
        versionSdk("version_sdk"),
        versionName("version_name"),
        versionCode("version_code"),
        versionTargetSdk("version_target_sdk"),
        _public(HeaderConstants.PUBLIC),
        application("application"),
        versionSdkIregex("version_sdk__iregex"),
        versionSdkEndswith("version_sdk__endswith"),
        versionSdkLt("version_sdk__lt"),
        versionSdkGte("version_sdk__gte"),
        versionSdkRange("version_sdk__range"),
        versionSdkRegex("version_sdk__regex"),
        versionSdkStartswith("version_sdk__startswith"),
        versionSdkSearch("version_sdk__search"),
        versionSdkIcontains("version_sdk__icontains"),
        versionSdkIstartswith("version_sdk__istartswith"),
        versionSdkIsnull("version_sdk__isnull"),
        versionSdkGt("version_sdk__gt"),
        versionSdkIexact("version_sdk__iexact"),
        versionSdkIn("version_sdk__in"),
        versionSdkLte("version_sdk__lte"),
        versionSdkContains("version_sdk__contains"),
        versionSdkIendswith("version_sdk__iendswith"),
        versionSdkContainedBy("version_sdk__contained_by"),
        versionNameIregex("version_name__iregex"),
        versionNameEndswith("version_name__endswith"),
        versionNameLt("version_name__lt"),
        versionNameGte("version_name__gte"),
        versionNameRange("version_name__range"),
        versionNameRegex("version_name__regex"),
        versionNameStartswith("version_name__startswith"),
        versionNameSearch("version_name__search"),
        versionNameIcontains("version_name__icontains"),
        versionNameIstartswith("version_name__istartswith"),
        versionNameIsnull("version_name__isnull"),
        versionNameGt("version_name__gt"),
        versionNameIexact("version_name__iexact"),
        versionNameIn("version_name__in"),
        versionNameLte("version_name__lte"),
        versionNameContains("version_name__contains"),
        versionNameIendswith("version_name__iendswith"),
        versionCodeIregex("version_code__iregex"),
        versionCodeEndswith("version_code__endswith"),
        versionCodeLt("version_code__lt"),
        versionCodeGte("version_code__gte"),
        versionCodeRange("version_code__range"),
        versionCodeRegex("version_code__regex"),
        versionCodeStartswith("version_code__startswith"),
        versionCodeSearch("version_code__search"),
        versionCodeIcontains("version_code__icontains"),
        versionCodeIstartswith("version_code__istartswith"),
        versionCodeIsnull("version_code__isnull"),
        versionCodeGt("version_code__gt"),
        versionCodeIexact("version_code__iexact"),
        versionCodeIn("version_code__in"),
        versionCodeLte("version_code__lte"),
        versionCodeContains("version_code__contains"),
        versionCodeIendswith("version_code__iendswith"),
        versionCodeContainedBy("version_code__contained_by"),
        versionTargetSdkIregex("version_target_sdk__iregex"),
        versionTargetSdkEndswith("version_target_sdk__endswith"),
        versionTargetSdkLt("version_target_sdk__lt"),
        versionTargetSdkGte("version_target_sdk__gte"),
        versionTargetSdkRange("version_target_sdk__range"),
        versionTargetSdkRegex("version_target_sdk__regex"),
        versionTargetSdkStartswith("version_target_sdk__startswith"),
        versionTargetSdkSearch("version_target_sdk__search"),
        versionTargetSdkIcontains("version_target_sdk__icontains"),
        versionTargetSdkIstartswith("version_target_sdk__istartswith"),
        versionTargetSdkIsnull("version_target_sdk__isnull"),
        versionTargetSdkGt("version_target_sdk__gt"),
        versionTargetSdkIexact("version_target_sdk__iexact"),
        versionTargetSdkIn("version_target_sdk__in"),
        versionTargetSdkLte("version_target_sdk__lte"),
        versionTargetSdkContains("version_target_sdk__contains"),
        versionTargetSdkIendswith("version_target_sdk__iendswith"),
        versionTargetSdkContainedBy("version_target_sdk__contained_by");

        private String value;

        releaseListQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releasePartialUpdateFormParams {
        ;

        private String value;

        releasePartialUpdateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releasePartialUpdateHeaderParams {
        ;

        private String value;

        releasePartialUpdateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releasePartialUpdateQueryParams {
        ;

        private String value;

        releasePartialUpdateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseReadFormParams {
        ;

        private String value;

        releaseReadFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseReadHeaderParams {
        ;

        private String value;

        releaseReadHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseReadQueryParams {
        ;

        private String value;

        releaseReadQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseUpdateFormParams {
        ;

        private String value;

        releaseUpdateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseUpdateHeaderParams {
        ;

        private String value;

        releaseUpdateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum releaseUpdateQueryParams {
        ;

        private String value;

        releaseUpdateQueryParams(String str) {
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

    public void releaseCreate(Map<String, String> map, String str, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, Map<String, String> map5) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        HttpEntity httpEntity;
        if (map == null) {
            new VolleyError("Missing the required parameter 'application' when calling releaseCreate", new ApiException(400, "Missing the required parameter 'application' when calling releaseCreate"));
        }
        if (str == null) {
            new VolleyError("Missing the required parameter 'changelog' when calling releaseCreate", new ApiException(400, "Missing the required parameter 'changelog' when calling releaseCreate"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'apkFile' when calling releaseCreate", new ApiException(400, "Missing the required parameter 'apkFile' when calling releaseCreate"));
        }
        ArrayList arrayList = new ArrayList();
        if (map2 != null) {
            for (String str3 : map2.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map2.get(str3)));
            }
        }
        if (map5 != null) {
            for (String str4 : map5.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str4, map5.get(str4)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap.put(str5, map3.get(str5));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str6 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str6.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (map != null) {
                create.addTextBody("application", ApiInvoker.parameterToString(map), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str != null) {
                create.addTextBody("changelog", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str2 != null) {
                create.addTextBody("apk_file", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("application", ApiInvoker.parameterToString(map));
            hashMap2.put("changelog", ApiInvoker.parameterToString(str));
            hashMap2.put("apk_file", ApiInvoker.parameterToString(str2));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map4 != null) {
            for (String str7 : map4.keySet()) {
                hashMap2.put(str7, map4.get(str7));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/release/", HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str6, new String[0]) != null) {
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

    public void releaseCreate(Map<String, String> map, String str, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, Map<String, String> map5, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        HttpEntity httpEntity;
        if (map == null) {
            new VolleyError("Missing the required parameter 'application' when calling releaseCreate", new ApiException(400, "Missing the required parameter 'application' when calling releaseCreate"));
        }
        if (str == null) {
            new VolleyError("Missing the required parameter 'changelog' when calling releaseCreate", new ApiException(400, "Missing the required parameter 'changelog' when calling releaseCreate"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'apkFile' when calling releaseCreate", new ApiException(400, "Missing the required parameter 'apkFile' when calling releaseCreate"));
        }
        String replaceAll = "/v1/release/".replaceAll("\\{format\\}", "json");
        ArrayList arrayList = new ArrayList();
        if (map2 != null) {
            for (String str3 : map2.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map2.get(str3)));
            }
        }
        if (map5 != null) {
            for (String str4 : map5.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str4, map5.get(str4)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap.put(str5, map3.get(str5));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str6 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str6.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (map != null) {
                create.addTextBody("application", ApiInvoker.parameterToString(map), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str != null) {
                create.addTextBody("changelog", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str2 != null) {
                create.addTextBody("apk_file", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("application", ApiInvoker.parameterToString(map));
            hashMap2.put("changelog", ApiInvoker.parameterToString(str));
            hashMap2.put("apk_file", ApiInvoker.parameterToString(str2));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map4 != null) {
            for (String str7 : map4.keySet()) {
                hashMap2.put(str7, map4.get(str7));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str6, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReleaseApi.1
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str8) {
                    listener.onResponse(str8);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReleaseApi.2
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void releaseDelete(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling releaseDelete", new ApiException(400, "Missing the required parameter 'id' when calling releaseDelete"));
        }
        String replaceAll = "/v1/release/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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

    public void releaseDelete(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling releaseDelete", new ApiException(400, "Missing the required parameter 'id' when calling releaseDelete"));
        }
        String replaceAll = "/v1/release/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "DELETE", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReleaseApi.3
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReleaseApi.4
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void releaseDownload(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
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
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/release/download/", "GET", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void releaseDownload(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/release/download/".replaceAll("\\{format\\}", "json");
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReleaseApi.5
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReleaseApi.6
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void releaseList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
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
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/release/", "GET", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void releaseList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/release/".replaceAll("\\{format\\}", "json");
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReleaseApi.7
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReleaseApi.8
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void releasePartialUpdate(Integer num, Data18 data18, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling releasePartialUpdate", new ApiException(400, "Missing the required parameter 'id' when calling releasePartialUpdate"));
        }
        String replaceAll = "/v1/release/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data18;
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

    public void releasePartialUpdate(Integer num, Data18 data18, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling releasePartialUpdate", new ApiException(400, "Missing the required parameter 'id' when calling releasePartialUpdate"));
        }
        String replaceAll = "/v1/release/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data18;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PATCH", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReleaseApi.9
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReleaseApi.10
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void releaseRead(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling releaseRead", new ApiException(400, "Missing the required parameter 'id' when calling releaseRead"));
        }
        String replaceAll = "/v1/release/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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

    public void releaseRead(Integer num, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling releaseRead", new ApiException(400, "Missing the required parameter 'id' when calling releaseRead"));
        }
        String replaceAll = "/v1/release/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReleaseApi.11
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReleaseApi.12
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void releaseUpdate(Integer num, Data17 data17, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling releaseUpdate", new ApiException(400, "Missing the required parameter 'id' when calling releaseUpdate"));
        }
        String replaceAll = "/v1/release/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data17;
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

    public void releaseUpdate(Integer num, Data17 data17, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling releaseUpdate", new ApiException(400, "Missing the required parameter 'id' when calling releaseUpdate"));
        }
        String replaceAll = "/v1/release/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
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
        Object build = str4.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data17;
        if (map3 != null) {
            for (String str5 : map3.keySet()) {
                hashMap2.put(str5, map3.get(str5));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.ReleaseApi.13
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.ReleaseApi.14
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
