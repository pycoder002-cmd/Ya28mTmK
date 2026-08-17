package io.swagger.client.api;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import cz.msebera.android.httpclient.client.methods.HttpPost;
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
public class UserApi {
    String basePath = "https://localhost";
    ApiInvoker apiInvoker = ApiInvoker.getInstance();

    /* loaded from: classes2.dex */
    public enum userCreateFormParams {
        avatar("avatar"),
        isSuperuser("is_superuser"),
        isStaff("is_staff"),
        email("email"),
        theme("theme"),
        favoriteApps("favorite_apps");

        private String value;

        userCreateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userCreateHeaderParams {
        ;

        private String value;

        userCreateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userCreateQueryParams {
        ;

        private String value;

        userCreateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userDeleteFormParams {
        ;

        private String value;

        userDeleteFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userDeleteHeaderParams {
        ;

        private String value;

        userDeleteHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userDeleteQueryParams {
        ;

        private String value;

        userDeleteQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userDownloadsFormParams {
        ;

        private String value;

        userDownloadsFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userDownloadsHeaderParams {
        ;

        private String value;

        userDownloadsHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userDownloadsQueryParams {
        ;

        private String value;

        userDownloadsQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userListFormParams {
        ;

        private String value;

        userListFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userListHeaderParams {
        ;

        private String value;

        userListHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userListQueryParams {
        limit("limit"),
        offset("offset"),
        ordering("ordering"),
        search("search"),
        phoneNumber("phone_number"),
        username("username"),
        email("email"),
        phoneNumberIregex("phone_number__iregex"),
        phoneNumberEndswith("phone_number__endswith"),
        phoneNumberLt("phone_number__lt"),
        phoneNumberGte("phone_number__gte"),
        phoneNumberRange("phone_number__range"),
        phoneNumberRegex("phone_number__regex"),
        phoneNumberStartswith("phone_number__startswith"),
        phoneNumberSearch("phone_number__search"),
        phoneNumberIcontains("phone_number__icontains"),
        phoneNumberIstartswith("phone_number__istartswith"),
        phoneNumberIsnull("phone_number__isnull"),
        phoneNumberGt("phone_number__gt"),
        phoneNumberIexact("phone_number__iexact"),
        phoneNumberIn("phone_number__in"),
        phoneNumberLte("phone_number__lte"),
        phoneNumberContains("phone_number__contains"),
        phoneNumberIendswith("phone_number__iendswith"),
        usernameIregex("username__iregex"),
        usernameEndswith("username__endswith"),
        usernameLt("username__lt"),
        usernameGte("username__gte"),
        usernameRange("username__range"),
        usernameRegex("username__regex"),
        usernameStartswith("username__startswith"),
        usernameSearch("username__search"),
        usernameIcontains("username__icontains"),
        usernameIstartswith("username__istartswith"),
        usernameIsnull("username__isnull"),
        usernameGt("username__gt"),
        usernameIexact("username__iexact"),
        usernameIn("username__in"),
        usernameLte("username__lte"),
        usernameContains("username__contains"),
        usernameIendswith("username__iendswith"),
        emailIregex("email__iregex"),
        emailEndswith("email__endswith"),
        emailLt("email__lt"),
        emailGte("email__gte"),
        emailRange("email__range"),
        emailRegex("email__regex"),
        emailStartswith("email__startswith"),
        emailSearch("email__search"),
        emailIcontains("email__icontains"),
        emailIstartswith("email__istartswith"),
        emailIsnull("email__isnull"),
        emailGt("email__gt"),
        emailIexact("email__iexact"),
        emailIn("email__in"),
        emailLte("email__lte"),
        emailContains("email__contains"),
        emailIendswith("email__iendswith"),
        favoriteApps("id__in");

        private String value;

        userListQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userPartialUpdateFormParams {
        avatar("avatar"),
        isSuperuser("is_superuser"),
        username("username"),
        firstName("first_name"),
        lastName("last_name"),
        isStaff("is_staff"),
        phoneNumber("phone_number"),
        email("email"),
        theme("theme"),
        favoriteApps("favorite_apps");

        private String value;

        userPartialUpdateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userPartialUpdateHeaderParams {
        ;

        private String value;

        userPartialUpdateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userPartialUpdateQueryParams {
        ;

        private String value;

        userPartialUpdateQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userReadFormParams {
        ;

        private String value;

        userReadFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userReadHeaderParams {
        ;

        private String value;

        userReadHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userReadQueryParams {
        ;

        private String value;

        userReadQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSetPasswordFormParams {
        ;

        private String value;

        userSetPasswordFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSetPasswordHeaderParams {
        ;

        private String value;

        userSetPasswordHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSetPasswordQueryParams {
        ;

        private String value;

        userSetPasswordQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSmsForgotPasswordFormParams {
        ;

        private String value;

        userSmsForgotPasswordFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSmsForgotPasswordHeaderParams {
        ;

        private String value;

        userSmsForgotPasswordHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSmsForgotPasswordQueryParams {
        ;

        private String value;

        userSmsForgotPasswordQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSmsResendFormParams {
        ;

        private String value;

        userSmsResendFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSmsResendHeaderParams {
        ;

        private String value;

        userSmsResendHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSmsResendQueryParams {
        ;

        private String value;

        userSmsResendQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSmsVerifyFormParams {
        ;

        private String value;

        userSmsVerifyFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSmsVerifyHeaderParams {
        ;

        private String value;

        userSmsVerifyHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userSmsVerifyQueryParams {
        ;

        private String value;

        userSmsVerifyQueryParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userUpdateFormParams {
        avatar("avatar"),
        isSuperuser("is_superuser"),
        isStaff("is_staff"),
        email("email"),
        theme("theme"),
        favoriteApps("favorite_apps");

        private String value;

        userUpdateFormParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userUpdateHeaderParams {
        ;

        private String value;

        userUpdateHeaderParams(String str) {
            this.value = str;
        }

        public String value() {
            return this.value;
        }
    }

    /* loaded from: classes2.dex */
    public enum userUpdateQueryParams {
        ;

        private String value;

        userUpdateQueryParams(String str) {
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

    public void setBasePath(String str) {
        this.basePath = str;
    }

    public void userCreate(String str, String str2, String str3, String str4, String str5, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'password' when calling userCreate", new ApiException(400, "Missing the required parameter 'password' when calling userCreate"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'username' when calling userCreate", new ApiException(400, "Missing the required parameter 'username' when calling userCreate"));
        }
        if (str3 == null) {
            new VolleyError("Missing the required parameter 'firstName' when calling userCreate", new ApiException(400, "Missing the required parameter 'firstName' when calling userCreate"));
        }
        if (str4 == null) {
            new VolleyError("Missing the required parameter 'lastName' when calling userCreate", new ApiException(400, "Missing the required parameter 'lastName' when calling userCreate"));
        }
        if (str5 == null) {
            new VolleyError("Missing the required parameter 'phoneNumber' when calling userCreate", new ApiException(400, "Missing the required parameter 'phoneNumber' when calling userCreate"));
        }
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str6 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str6, map.get(str6)));
            }
        }
        if (map4 != null) {
            for (String str7 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str7, map4.get(str7)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str8 : map2.keySet()) {
                hashMap.put(str8, map2.get(str8));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str9 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str9.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("password", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str2 != null) {
                create.addTextBody("username", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str3 != null) {
                create.addTextBody("first_name", ApiInvoker.parameterToString(str3), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str4 != null) {
                create.addTextBody("last_name", ApiInvoker.parameterToString(str4), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str5 != null) {
                create.addTextBody("phone_number", ApiInvoker.parameterToString(str5), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("password", ApiInvoker.parameterToString(str));
            hashMap2.put("username", ApiInvoker.parameterToString(str2));
            hashMap2.put("first_name", ApiInvoker.parameterToString(str3));
            hashMap2.put("last_name", ApiInvoker.parameterToString(str4));
            hashMap2.put("phone_number", ApiInvoker.parameterToString(str5));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str10 : map3.keySet()) {
                hashMap2.put(str10, map3.get(str10));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/user/", HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str9, new String[0]) != null) {
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

    public void userCreate(String str, String str2, String str3, String str4, String str5, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, Response.ErrorListener errorListener) {
        HttpEntity httpEntity;
        final Response.ErrorListener errorListener2;
        Map<String, String> map5 = map;
        if (str == null) {
            new VolleyError("Missing the required parameter 'password' when calling userCreate", new ApiException(400, "Missing the required parameter 'password' when calling userCreate"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'username' when calling userCreate", new ApiException(400, "Missing the required parameter 'username' when calling userCreate"));
        }
        if (str3 == null) {
            new VolleyError("Missing the required parameter 'firstName' when calling userCreate", new ApiException(400, "Missing the required parameter 'firstName' when calling userCreate"));
        }
        if (str4 == null) {
            new VolleyError("Missing the required parameter 'lastName' when calling userCreate", new ApiException(400, "Missing the required parameter 'lastName' when calling userCreate"));
        }
        if (str5 == null) {
            new VolleyError("Missing the required parameter 'phoneNumber' when calling userCreate", new ApiException(400, "Missing the required parameter 'phoneNumber' when calling userCreate"));
        }
        String replaceAll = "/v1/user/".replaceAll("\\{format\\}", "json");
        ArrayList arrayList = new ArrayList();
        if (map5 != null) {
            for (String str6 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str6, map5.get(str6)));
                map5 = map;
            }
        }
        if (map4 != null) {
            for (String str7 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str7, map4.get(str7)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str8 : map2.keySet()) {
                hashMap.put(str8, map2.get(str8));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str9 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str9.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("password", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str2 != null) {
                create.addTextBody("username", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str3 != null) {
                create.addTextBody("first_name", ApiInvoker.parameterToString(str3), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str4 != null) {
                create.addTextBody("last_name", ApiInvoker.parameterToString(str4), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str5 != null) {
                create.addTextBody("phone_number", ApiInvoker.parameterToString(str5), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("password", ApiInvoker.parameterToString(str));
            hashMap2.put("username", ApiInvoker.parameterToString(str2));
            hashMap2.put("first_name", ApiInvoker.parameterToString(str3));
            hashMap2.put("last_name", ApiInvoker.parameterToString(str4));
            hashMap2.put("phone_number", ApiInvoker.parameterToString(str5));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str10 : map3.keySet()) {
                hashMap2.put(str10, map3.get(str10));
            }
        }
        try {
            errorListener2 = errorListener;
            try {
                this.apiInvoker.invokeAPI(this.basePath, replaceAll, HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str9, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.1
                    @Override // com.android.volley.Response.Listener
                    public void onResponse(String str11) {
                        listener.onResponse(str11);
                    }
                }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.2
                    @Override // com.android.volley.Response.ErrorListener
                    public void onErrorResponse(VolleyError volleyError) {
                        errorListener2.onErrorResponse(volleyError);
                    }
                });
            } catch (ApiException e) {
                e = e;
                errorListener2.onErrorResponse(new VolleyError(e));
            }
        } catch (ApiException e2) {
            e = e2;
            errorListener2 = errorListener;
        }
    }

    public void userDelete(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (str == null) {
            new VolleyError("Missing the required parameter 'usernameParam' when calling userDelete", new ApiException(400, "Missing the required parameter 'usernameParam' when calling userDelete"));
        }
        String replaceAll = "/v1/user/{usernameParam}/".replaceAll("\\{usernameParam\\}", this.apiInvoker.escapeString(str.toString()));
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

    public void userDelete(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (str == null) {
            new VolleyError("Missing the required parameter 'usernameParam' when calling userDelete", new ApiException(400, "Missing the required parameter 'usernameParam' when calling userDelete"));
        }
        String replaceAll = "/v1/user/{usernameParam}/".replaceAll("\\{format\\}", "json").replaceAll("\\{usernameParam\\}", this.apiInvoker.escapeString(str.toString()));
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "DELETE", arrayList, build, hashMap, hashMap2, str5, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.3
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str7) {
                    listener.onResponse(str7);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.4
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void userDownloads(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
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
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/user/downloads/", "GET", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void userDownloads(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/user/downloads/".replaceAll("\\{format\\}", "json");
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.5
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.6
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void userList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
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
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/user/", "GET", arrayList, build, hashMap, hashMap2, str4, new String[0]) != null) {
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

    public void userList(Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        String replaceAll = "/v1/user/".replaceAll("\\{format\\}", "json");
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str4, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.7
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str6) {
                    listener.onResponse(str6);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.8
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void userPartialUpdate(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (str == null) {
            new VolleyError("Missing the required parameter 'usernameParam' when calling userPartialUpdate", new ApiException(400, "Missing the required parameter 'usernameParam' when calling userPartialUpdate"));
        }
        String replaceAll = "/v1/user/{usernameParam}/".replaceAll("\\{usernameParam\\}", this.apiInvoker.escapeString(str.toString()));
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
        String[] strArr = {"multipart/form-data"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
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

    public void userPartialUpdate(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (str == null) {
            new VolleyError("Missing the required parameter 'usernameParam' when calling userPartialUpdate", new ApiException(400, "Missing the required parameter 'usernameParam' when calling userPartialUpdate"));
        }
        String replaceAll = "/v1/user/{usernameParam}/".replaceAll("\\{format\\}", "json").replaceAll("\\{usernameParam\\}", this.apiInvoker.escapeString(str.toString()));
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
        String[] strArr = {"multipart/form-data"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        HttpEntity build = str5.startsWith("multipart/form-data") ? MultipartEntityBuilder.create().build() : null;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PATCH", arrayList, build, hashMap, hashMap2, str5, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.9
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str7) {
                    listener.onResponse(str7);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.10
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void userRead(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        if (str == null) {
            new VolleyError("Missing the required parameter 'usernameParam' when calling userRead", new ApiException(400, "Missing the required parameter 'usernameParam' when calling userRead"));
        }
        String replaceAll = "/v1/user/{usernameParam}/".replaceAll("\\{usernameParam\\}", this.apiInvoker.escapeString(str.toString()));
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

    public void userRead(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        if (str == null) {
            new VolleyError("Missing the required parameter 'usernameParam' when calling userRead", new ApiException(400, "Missing the required parameter 'usernameParam' when calling userRead"));
        }
        String replaceAll = "/v1/user/{usernameParam}/".replaceAll("\\{format\\}", "json").replaceAll("\\{usernameParam\\}", this.apiInvoker.escapeString(str.toString()));
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
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "GET", arrayList, build, hashMap, hashMap2, str5, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.11
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str7) {
                    listener.onResponse(str7);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.12
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void userSetPassword(String str, String str2, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'oldPassword' when calling userSetPassword", new ApiException(400, "Missing the required parameter 'oldPassword' when calling userSetPassword"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'newPassword' when calling userSetPassword", new ApiException(400, "Missing the required parameter 'newPassword' when calling userSetPassword"));
        }
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str3 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map.get(str3)));
            }
        }
        if (map4 != null) {
            for (String str4 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str4, map4.get(str4)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str5 : map2.keySet()) {
                hashMap.put(str5, map2.get(str5));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str6 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str6.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("old_password", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str2 != null) {
                create.addTextBody("new_password", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("old_password", ApiInvoker.parameterToString(str));
            hashMap2.put("new_password", ApiInvoker.parameterToString(str2));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str7 : map3.keySet()) {
                hashMap2.put(str7, map3.get(str7));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/user/set_password/", "PUT", arrayList, httpEntity2, hashMap, hashMap2, str6, new String[0]) != null) {
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

    public void userSetPassword(String str, String str2, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'oldPassword' when calling userSetPassword", new ApiException(400, "Missing the required parameter 'oldPassword' when calling userSetPassword"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'newPassword' when calling userSetPassword", new ApiException(400, "Missing the required parameter 'newPassword' when calling userSetPassword"));
        }
        String replaceAll = "/v1/user/set_password/".replaceAll("\\{format\\}", "json");
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str3 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map.get(str3)));
            }
        }
        if (map4 != null) {
            for (String str4 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str4, map4.get(str4)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str5 : map2.keySet()) {
                hashMap.put(str5, map2.get(str5));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str6 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str6.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("old_password", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str2 != null) {
                create.addTextBody("new_password", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("old_password", ApiInvoker.parameterToString(str));
            hashMap2.put("new_password", ApiInvoker.parameterToString(str2));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str7 : map3.keySet()) {
                hashMap2.put(str7, map3.get(str7));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, httpEntity2, hashMap, hashMap2, str6, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.13
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str8) {
                    listener.onResponse(str8);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.14
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void userSmsForgotPassword(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'phoneNumber' when calling userSmsForgotPassword", new ApiException(400, "Missing the required parameter 'phoneNumber' when calling userSmsForgotPassword"));
        }
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
        String[] strArr = {"multipart/form-data"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str5.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("phone_number", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("phone_number", ApiInvoker.parameterToString(str));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/user/sms_forgot_password/", HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str5, new String[0]) != null) {
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

    public void userSmsForgotPassword(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'phoneNumber' when calling userSmsForgotPassword", new ApiException(400, "Missing the required parameter 'phoneNumber' when calling userSmsForgotPassword"));
        }
        String replaceAll = "/v1/user/sms_forgot_password/".replaceAll("\\{format\\}", "json");
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
        String[] strArr = {"multipart/form-data"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str5.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("phone_number", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("phone_number", ApiInvoker.parameterToString(str));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str5, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.15
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str7) {
                    listener.onResponse(str7);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.16
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void userSmsResend(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'phoneNumber' when calling userSmsResend", new ApiException(400, "Missing the required parameter 'phoneNumber' when calling userSmsResend"));
        }
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
        String[] strArr = {"multipart/form-data"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str5.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("phone_number", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("phone_number", ApiInvoker.parameterToString(str));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/user/sms_resend/", HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str5, new String[0]) != null) {
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

    public void userSmsResend(String str, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'phoneNumber' when calling userSmsResend", new ApiException(400, "Missing the required parameter 'phoneNumber' when calling userSmsResend"));
        }
        String replaceAll = "/v1/user/sms_resend/".replaceAll("\\{format\\}", "json");
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
        String[] strArr = {"multipart/form-data"};
        String str5 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str5.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("phone_number", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("phone_number", ApiInvoker.parameterToString(str));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str6 : map3.keySet()) {
                hashMap2.put(str6, map3.get(str6));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str5, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.17
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str7) {
                    listener.onResponse(str7);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.18
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void userSmsVerify(String str, String str2, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'token' when calling userSmsVerify", new ApiException(400, "Missing the required parameter 'token' when calling userSmsVerify"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'clientId' when calling userSmsVerify", new ApiException(400, "Missing the required parameter 'clientId' when calling userSmsVerify"));
        }
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str3 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map.get(str3)));
            }
        }
        if (map4 != null) {
            for (String str4 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str4, map4.get(str4)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str5 : map2.keySet()) {
                hashMap.put(str5, map2.get(str5));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str6 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str6.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("token", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str2 != null) {
                create.addTextBody("client_id", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("token", ApiInvoker.parameterToString(str));
            hashMap2.put("client_id", ApiInvoker.parameterToString(str2));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str7 : map3.keySet()) {
                hashMap2.put(str7, map3.get(str7));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, "/v1/user/sms_verify/", HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str6, new String[0]) != null) {
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

    public void userSmsVerify(String str, String str2, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'token' when calling userSmsVerify", new ApiException(400, "Missing the required parameter 'token' when calling userSmsVerify"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'clientId' when calling userSmsVerify", new ApiException(400, "Missing the required parameter 'clientId' when calling userSmsVerify"));
        }
        String replaceAll = "/v1/user/sms_verify/".replaceAll("\\{format\\}", "json");
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str3 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str3, map.get(str3)));
            }
        }
        if (map4 != null) {
            for (String str4 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str4, map4.get(str4)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str5 : map2.keySet()) {
                hashMap.put(str5, map2.get(str5));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str6 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str6.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str != null) {
                create.addTextBody("token", ApiInvoker.parameterToString(str), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str2 != null) {
                create.addTextBody("client_id", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("token", ApiInvoker.parameterToString(str));
            hashMap2.put("client_id", ApiInvoker.parameterToString(str2));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str7 : map3.keySet()) {
                hashMap2.put(str7, map3.get(str7));
            }
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, HttpPost.METHOD_NAME, arrayList, httpEntity2, hashMap, hashMap2, str6, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.19
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str8) {
                    listener.onResponse(str8);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.20
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e) {
            errorListener.onErrorResponse(new VolleyError(e));
        }
    }

    public void userUpdate(String str, String str2, String str3, String str4, String str5, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) throws TimeoutException, ExecutionException, InterruptedException, ApiException {
        HttpEntity httpEntity;
        if (str == null) {
            new VolleyError("Missing the required parameter 'usernameParam' when calling userUpdate", new ApiException(400, "Missing the required parameter 'usernameParam' when calling userUpdate"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'username' when calling userUpdate", new ApiException(400, "Missing the required parameter 'username' when calling userUpdate"));
        }
        if (str3 == null) {
            new VolleyError("Missing the required parameter 'firstName' when calling userUpdate", new ApiException(400, "Missing the required parameter 'firstName' when calling userUpdate"));
        }
        if (str4 == null) {
            new VolleyError("Missing the required parameter 'lastName' when calling userUpdate", new ApiException(400, "Missing the required parameter 'lastName' when calling userUpdate"));
        }
        if (str5 == null) {
            new VolleyError("Missing the required parameter 'phoneNumber' when calling userUpdate", new ApiException(400, "Missing the required parameter 'phoneNumber' when calling userUpdate"));
        }
        String replaceAll = "/v1/user/{usernameParam}/".replaceAll("\\{usernameParam\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str6 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str6, map.get(str6)));
            }
        }
        if (map4 != null) {
            for (String str7 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str7, map4.get(str7)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str8 : map2.keySet()) {
                hashMap.put(str8, map2.get(str8));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str9 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str9.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str2 != null) {
                create.addTextBody("username", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str3 != null) {
                create.addTextBody("first_name", ApiInvoker.parameterToString(str3), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str4 != null) {
                create.addTextBody("last_name", ApiInvoker.parameterToString(str4), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str5 != null) {
                create.addTextBody("phone_number", ApiInvoker.parameterToString(str5), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("username", ApiInvoker.parameterToString(str2));
            hashMap2.put("first_name", ApiInvoker.parameterToString(str3));
            hashMap2.put("last_name", ApiInvoker.parameterToString(str4));
            hashMap2.put("phone_number", ApiInvoker.parameterToString(str5));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str10 : map3.keySet()) {
                hashMap2.put(str10, map3.get(str10));
            }
        }
        try {
            if (this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, httpEntity2, hashMap, hashMap2, str9, new String[0]) != null) {
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

    public void userUpdate(String str, String str2, String str3, String str4, String str5, Map<String, String> map, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4, final Response.Listener<String> listener, Response.ErrorListener errorListener) {
        HttpEntity httpEntity;
        final Response.ErrorListener errorListener2;
        if (str == null) {
            new VolleyError("Missing the required parameter 'usernameParam' when calling userUpdate", new ApiException(400, "Missing the required parameter 'usernameParam' when calling userUpdate"));
        }
        if (str2 == null) {
            new VolleyError("Missing the required parameter 'username' when calling userUpdate", new ApiException(400, "Missing the required parameter 'username' when calling userUpdate"));
        }
        if (str3 == null) {
            new VolleyError("Missing the required parameter 'firstName' when calling userUpdate", new ApiException(400, "Missing the required parameter 'firstName' when calling userUpdate"));
        }
        if (str4 == null) {
            new VolleyError("Missing the required parameter 'lastName' when calling userUpdate", new ApiException(400, "Missing the required parameter 'lastName' when calling userUpdate"));
        }
        if (str5 == null) {
            new VolleyError("Missing the required parameter 'phoneNumber' when calling userUpdate", new ApiException(400, "Missing the required parameter 'phoneNumber' when calling userUpdate"));
        }
        String replaceAll = "/v1/user/{usernameParam}/".replaceAll("\\{format\\}", "json").replaceAll("\\{usernameParam\\}", this.apiInvoker.escapeString(str.toString()));
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (String str6 : map.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str6, map.get(str6)));
            }
        }
        if (map4 != null) {
            for (String str7 : map4.keySet()) {
                arrayList.addAll(ApiInvoker.parameterToPairs("", str7, map4.get(str7)));
            }
        }
        HashMap hashMap = new HashMap();
        if (map2 != null) {
            for (String str8 : map2.keySet()) {
                hashMap.put(str8, map2.get(str8));
            }
        }
        HashMap hashMap2 = new HashMap();
        String[] strArr = {"multipart/form-data"};
        String str9 = strArr.length > 0 ? strArr[0] : "application/json";
        if (str9.startsWith("multipart/form-data")) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            if (str2 != null) {
                create.addTextBody("username", ApiInvoker.parameterToString(str2), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str3 != null) {
                create.addTextBody("first_name", ApiInvoker.parameterToString(str3), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str4 != null) {
                create.addTextBody("last_name", ApiInvoker.parameterToString(str4), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            if (str5 != null) {
                create.addTextBody("phone_number", ApiInvoker.parameterToString(str5), ApiInvoker.TEXT_PLAIN_UTF8);
            }
            httpEntity = create.build();
        } else {
            hashMap2.put("username", ApiInvoker.parameterToString(str2));
            hashMap2.put("first_name", ApiInvoker.parameterToString(str3));
            hashMap2.put("last_name", ApiInvoker.parameterToString(str4));
            hashMap2.put("phone_number", ApiInvoker.parameterToString(str5));
            httpEntity = null;
        }
        HttpEntity httpEntity2 = httpEntity;
        if (map3 != null) {
            for (String str10 : map3.keySet()) {
                hashMap2.put(str10, map3.get(str10));
            }
        }
        try {
            errorListener2 = errorListener;
        } catch (ApiException e) {
            e = e;
            errorListener2 = errorListener;
        }
        try {
            this.apiInvoker.invokeAPI(this.basePath, replaceAll, "PUT", arrayList, httpEntity2, hashMap, hashMap2, str9, new String[0], new Response.Listener<String>() { // from class: io.swagger.client.api.UserApi.21
                @Override // com.android.volley.Response.Listener
                public void onResponse(String str11) {
                    listener.onResponse(str11);
                }
            }, new Response.ErrorListener() { // from class: io.swagger.client.api.UserApi.22
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError volleyError) {
                    errorListener2.onErrorResponse(volleyError);
                }
            });
        } catch (ApiException e2) {
            e = e2;
            errorListener2.onErrorResponse(new VolleyError(e));
        }
    }
}
