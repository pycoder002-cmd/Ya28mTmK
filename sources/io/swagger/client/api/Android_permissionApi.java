package io.swagger.client.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import io.swagger.client.ApiException;
import io.swagger.client.ApiInvoker;
import io.swagger.client.model.Data;
import io.swagger.client.model.Data1;
import io.swagger.client.model.Data2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/* loaded from: classes2.dex */
public class Android_permissionApi {
    String basePath = "http://10.3.201.199:9000";
    ApiInvoker apiInvoker = ApiInvoker.getInstance();

    public void addHeader(String str, String str2) {
        getInvoker().addDefaultHeader(str, str2);
    }

    public void androidPermissionCreate(Data data) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        Object obj = data;
        if (str.startsWith("multipart/form-data")) {
            obj = MultipartEntityBuilder.create().build();
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/android_permission/", HttpPost.METHOD_NAME, arrayList, obj, hashMap, hashMap2, str, new String[0]) != null) {
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

    public void androidPermissionCreate(Data data, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/android_permission/".replaceAll("\\{format\\}", "json");
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, HttpPost.METHOD_NAME, arrayList, str.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data, hashMap, hashMap2, str, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.Android_permissionApi.1
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str2) {
                    listener.onResponse(str2);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.Android_permissionApi.2
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void androidPermissionDelete(Integer num) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling androidPermissionDelete", new ApiException(400, "Missing the required parameter 'id' when calling androidPermissionDelete"));
        }
        String replaceAll = "/v1/android_permission/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "DELETE", arrayList, str.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null, hashMap, hashMap2, str, new String[0]) != null) {
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

    public void androidPermissionDelete(Integer num, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling androidPermissionDelete", new ApiException(400, "Missing the required parameter 'id' when calling androidPermissionDelete"));
        }
        String replaceAll = "/v1/android_permission/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "DELETE", arrayList, str.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null, hashMap, hashMap2, str, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.Android_permissionApi.3
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str2) {
                    listener.onResponse(str2);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.Android_permissionApi.4
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void androidPermissionList(Integer num, Integer num2, String str, String str2) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        arrayList.addAll(ApiInvoker.parameterToPairs("", "limit", num));
        arrayList.addAll(ApiInvoker.parameterToPairs("", "offset", num2));
        arrayList.addAll(ApiInvoker.parameterToPairs("", "ordering", str));
        arrayList.addAll(ApiInvoker.parameterToPairs("", "search", str2));
        String[] strArr = new String[0];
        String str3 = strArr.length > 0 ? strArr[0] : "application/json";
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/android_permission/", "GET", arrayList, str3.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null, hashMap, hashMap2, str3, new String[0]) != null) {
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

    public void androidPermissionList(Integer num, Integer num2, String str, String str2, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/android_permission/".replaceAll("\\{format\\}", "json");
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        arrayList.addAll(ApiInvoker.parameterToPairs("", "limit", num));
        arrayList.addAll(ApiInvoker.parameterToPairs("", "offset", num2));
        arrayList.addAll(ApiInvoker.parameterToPairs("", "ordering", str));
        arrayList.addAll(ApiInvoker.parameterToPairs("", "search", str2));
        String[] strArr = new String[0];
        String str3 = strArr.length > 0 ? strArr[0] : "application/json";
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, str3.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null, hashMap, hashMap2, str3, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.Android_permissionApi.5
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str4) {
                    listener.onResponse(str4);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.Android_permissionApi.6
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void androidPermissionPartialUpdate(Integer num, Data2 data2) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling androidPermissionPartialUpdate", new ApiException(400, "Missing the required parameter 'id' when calling androidPermissionPartialUpdate"));
        }
        String replaceAll = "/v1/android_permission/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        Object obj = data2;
        if (str.startsWith("multipart/form-data")) {
            obj = MultipartEntityBuilder.create().build();
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PATCH", arrayList, obj, hashMap, hashMap2, str, new String[0]) != null) {
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

    public void androidPermissionPartialUpdate(Integer num, Data2 data2, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling androidPermissionPartialUpdate", new ApiException(400, "Missing the required parameter 'id' when calling androidPermissionPartialUpdate"));
        }
        String replaceAll = "/v1/android_permission/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PATCH", arrayList, str.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data2, hashMap, hashMap2, str, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.Android_permissionApi.7
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str2) {
                    listener.onResponse(str2);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.Android_permissionApi.8
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void androidPermissionRead(Integer num) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling androidPermissionRead", new ApiException(400, "Missing the required parameter 'id' when calling androidPermissionRead"));
        }
        String replaceAll = "/v1/android_permission/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, str.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null, hashMap, hashMap2, str, new String[0]) != null) {
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

    public void androidPermissionRead(Integer num, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling androidPermissionRead", new ApiException(400, "Missing the required parameter 'id' when calling androidPermissionRead"));
        }
        String replaceAll = "/v1/android_permission/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = new String[0];
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, str.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null, hashMap, hashMap2, str, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.Android_permissionApi.9
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str2) {
                    listener.onResponse(str2);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.Android_permissionApi.10
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void androidPermissionUpdate(Integer num, Data1 data1) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling androidPermissionUpdate", new ApiException(400, "Missing the required parameter 'id' when calling androidPermissionUpdate"));
        }
        String replaceAll = "/v1/android_permission/{id}/".replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        Object obj = data1;
        if (str.startsWith("multipart/form-data")) {
            obj = MultipartEntityBuilder.create().build();
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, obj, hashMap, hashMap2, str, new String[0]) != null) {
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

    public void androidPermissionUpdate(Integer num, Data1 data1, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (num == null) {
            new VolleyError("Missing the required parameter 'id' when calling androidPermissionUpdate", new ApiException(400, "Missing the required parameter 'id' when calling androidPermissionUpdate"));
        }
        String replaceAll = "/v1/android_permission/{id}/".replaceAll("\\{format\\}", "json").replaceAll("\\{id\\}", this.apiInvoker.escapeString(num.toString()));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"application/json"};
        String str = strArr.length > 0 ? strArr[0] : "application/json";
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, str.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : data1, hashMap, hashMap2, str, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.Android_permissionApi.11
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str2) {
                    listener.onResponse(str2);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.Android_permissionApi.12
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
