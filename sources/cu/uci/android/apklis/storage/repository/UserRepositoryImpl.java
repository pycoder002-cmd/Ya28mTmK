package cu.uci.android.apklis.storage.repository;

import android.os.StrictMode;
import android.util.Log;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.UserRepository;
import cu.uci.android.apklis.domain.model.UserAccount;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* loaded from: classes.dex */
public class UserRepositoryImpl implements UserRepository {
    private static String CLIENT_ID = "7UMqh5p9CPPPEPi0s4Ksrv2vR5tBjBJH8XlaNMMB";
    private String resStr;
    private String token_final;
    private UserAccount userAccount;

    public UserRepositoryImpl() {
        aceptAllSSLCertificate();
    }

    private static void aceptAllSSLCertificate() {
        TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: cu.uci.android.apklis.storage.repository.UserRepositoryImpl.1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }};
        try {
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: cu.uci.android.apklis.storage.repository.UserRepositoryImpl.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
        } catch (Exception e) {
            MainApp.log(UserRepositoryImpl.class.getName(), e);
            System.out.println(e.getMessage());
        }
    }

    @Override // cu.uci.android.apklis.domain.UserRepository
    public UserAccount activateAccount(String str) {
        Response response;
        UserAccount userAccount = new UserAccount();
        try {
            response = new OkHttpClient().newBuilder().followRedirects(false).build().newCall(new Request.Builder().url("https://api.apklis.cu/v1/user/sms_verify/").post(new MultipartBody.Builder().setType(MediaType.parse("multipart/form-data")).addFormDataPart("token", str).addFormDataPart("client_id", CLIENT_ID).build()).build()).execute();
        } catch (Exception e) {
            e = e;
            response = null;
        }
        try {
            String string = response.body().string();
            if (response.isSuccessful()) {
                JsonObject jsonObject = (JsonObject) new JsonParser().parse(string);
                if (jsonObject.has("access_token")) {
                    userAccount.setAccessToken(jsonObject.get("access_token").getAsString());
                }
                if (jsonObject.has("username")) {
                    userAccount.setUsername(jsonObject.get("username").getAsString());
                }
            }
            return userAccount;
        } catch (Exception e2) {
            e = e2;
            MainApp.log(e.getClass().getName(), e);
            if (response != null) {
                response.close();
            }
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008b A[Catch: Exception -> 0x00ac, TryCatch #1 {Exception -> 0x00ac, blocks: (B:3:0x0001, B:5:0x0055, B:10:0x0085, B:12:0x008b, B:13:0x009c, B:20:0x0077), top: B:2:0x0001 }] */
    @Override // cu.uci.android.apklis.domain.UserRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String changePassword(java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        /*
            r5 = this;
            r0 = 0
            java.lang.String r1 = "https://api.apklis.cu/v1/user/change_password_with_tk/"
            okhttp3.OkHttpClient r2 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> Lac
            r2.<init>()     // Catch: java.lang.Exception -> Lac
            okhttp3.OkHttpClient$Builder r2 = r2.newBuilder()     // Catch: java.lang.Exception -> Lac
            r3 = 0
            okhttp3.OkHttpClient$Builder r2 = r2.followRedirects(r3)     // Catch: java.lang.Exception -> Lac
            okhttp3.OkHttpClient r2 = r2.build()     // Catch: java.lang.Exception -> Lac
            com.google.gson.JsonObject r3 = new com.google.gson.JsonObject     // Catch: java.lang.Exception -> Lac
            r3.<init>()     // Catch: java.lang.Exception -> Lac
            java.lang.String r4 = "token_largo salidaaaa: "
            android.util.Log.e(r4, r6)     // Catch: java.lang.Exception -> Lac
            java.lang.String r4 = "long_token"
            r3.addProperty(r4, r6)     // Catch: java.lang.Exception -> Lac
            java.lang.String r6 = "short_token"
            r3.addProperty(r6, r7)     // Catch: java.lang.Exception -> Lac
            java.lang.String r6 = "new_password"
            r3.addProperty(r6, r8)     // Catch: java.lang.Exception -> Lac
            java.lang.String r6 = "application/json"
            okhttp3.MediaType r6 = okhttp3.MediaType.parse(r6)     // Catch: java.lang.Exception -> Lac
            java.lang.String r7 = r3.toString()     // Catch: java.lang.Exception -> Lac
            okhttp3.RequestBody r6 = okhttp3.RequestBody.create(r6, r7)     // Catch: java.lang.Exception -> Lac
            okhttp3.Request$Builder r7 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> Lac
            r7.<init>()     // Catch: java.lang.Exception -> Lac
            java.lang.String r8 = "Content-Type"
            java.lang.String r3 = "json"
            okhttp3.Request$Builder r7 = r7.header(r8, r3)     // Catch: java.lang.Exception -> Lac
            okhttp3.Request$Builder r7 = r7.url(r1)     // Catch: java.lang.Exception -> Lac
            okhttp3.Request$Builder r6 = r7.post(r6)     // Catch: java.lang.Exception -> Lac
            okhttp3.Request r6 = r6.build()     // Catch: java.lang.Exception -> Lac
            okhttp3.Call r6 = r2.newCall(r6)     // Catch: java.io.IOException -> L76 java.lang.Exception -> Lac
            okhttp3.Response r6 = r6.execute()     // Catch: java.io.IOException -> L76 java.lang.Exception -> Lac
            okhttp3.ResponseBody r7 = r6.body()     // Catch: java.lang.Exception -> L70 java.io.IOException -> L73
            java.lang.String r7 = r7.string()     // Catch: java.lang.Exception -> L70 java.io.IOException -> L73
            r5.resStr = r7     // Catch: java.lang.Exception -> L70 java.io.IOException -> L73
            java.lang.String r7 = "changePassword: "
            java.lang.String r8 = r5.resStr     // Catch: java.lang.Exception -> L70 java.io.IOException -> L73
            android.util.Log.e(r7, r8)     // Catch: java.lang.Exception -> L70 java.io.IOException -> L73
            r0 = r6
            goto L85
        L70:
            r7 = move-exception
            r0 = r6
            goto Lad
        L73:
            r7 = move-exception
            r0 = r6
            goto L77
        L76:
            r7 = move-exception
        L77:
            java.lang.Class r6 = r5.getClass()     // Catch: java.lang.Exception -> Lac
            java.lang.String r6 = r6.getName()     // Catch: java.lang.Exception -> Lac
            cu.uci.android.apklis.MainApp.log(r6, r7)     // Catch: java.lang.Exception -> Lac
            r7.printStackTrace()     // Catch: java.lang.Exception -> Lac
        L85:
            boolean r6 = r0.isSuccessful()     // Catch: java.lang.Exception -> Lac
            if (r6 == 0) goto L9c
            com.google.gson.Gson r6 = new com.google.gson.Gson     // Catch: java.lang.Exception -> Lac
            r6.<init>()     // Catch: java.lang.Exception -> Lac
            java.lang.String r7 = r5.resStr     // Catch: java.lang.Exception -> Lac
            java.lang.Class<cu.uci.android.apklis.domain.model.UserAccount> r8 = cu.uci.android.apklis.domain.model.UserAccount.class
            java.lang.Object r6 = r6.fromJson(r7, r8)     // Catch: java.lang.Exception -> Lac
            cu.uci.android.apklis.domain.model.UserAccount r6 = (cu.uci.android.apklis.domain.model.UserAccount) r6     // Catch: java.lang.Exception -> Lac
            r5.userAccount = r6     // Catch: java.lang.Exception -> Lac
        L9c:
            cu.uci.android.apklis.domain.model.UserAccount r6 = r5.userAccount     // Catch: java.lang.Exception -> Lac
            java.lang.String r6 = r6.getUsername()     // Catch: java.lang.Exception -> Lac
            cu.uci.android.apklis.MainApp.log(r6)     // Catch: java.lang.Exception -> Lac
            cu.uci.android.apklis.domain.model.UserAccount r6 = r5.userAccount     // Catch: java.lang.Exception -> Lac
            java.lang.String r6 = r6.getUsername()     // Catch: java.lang.Exception -> Lac
            return r6
        Lac:
            r7 = move-exception
        Lad:
            java.lang.Class r6 = r7.getClass()
            java.lang.String r6 = r6.getName()
            cu.uci.android.apklis.MainApp.log(r6, r7)
            if (r0 == 0) goto Lbd
            r0.close()
        Lbd:
            cu.uci.android.apklis.domain.model.UserAccount r6 = r5.userAccount
            java.lang.String r6 = r6.getUsername()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.storage.repository.UserRepositoryImpl.changePassword(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:14:? A[RETURN, SYNTHETIC] */
    @Override // cu.uci.android.apklis.domain.UserRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void getUserInfo(cu.uci.android.apklis.domain.model.UserAccount r6) {
        /*
            r5 = this;
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L9f
            r1.<init>()     // Catch: java.lang.Exception -> L9f
            java.lang.String r2 = "https://api.apklis.cu/v1/user/"
            r1.append(r2)     // Catch: java.lang.Exception -> L9f
            java.lang.String r2 = r6.getUsername()     // Catch: java.lang.Exception -> L9f
            r1.append(r2)     // Catch: java.lang.Exception -> L9f
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch: java.lang.Exception -> L9f
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L9f
            okhttp3.OkHttpClient r2 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> L9f
            r2.<init>()     // Catch: java.lang.Exception -> L9f
            okhttp3.OkHttpClient$Builder r2 = r2.newBuilder()     // Catch: java.lang.Exception -> L9f
            r3 = 0
            okhttp3.OkHttpClient$Builder r2 = r2.followRedirects(r3)     // Catch: java.lang.Exception -> L9f
            okhttp3.OkHttpClient r2 = r2.build()     // Catch: java.lang.Exception -> L9f
            okhttp3.Request$Builder r3 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> L9f
            r3.<init>()     // Catch: java.lang.Exception -> L9f
            okhttp3.Request$Builder r1 = r3.url(r1)     // Catch: java.lang.Exception -> L9f
            java.lang.String r3 = "Authorization"
            java.lang.String r4 = r6.getAccessToken()     // Catch: java.lang.Exception -> L9f
            okhttp3.Request$Builder r1 = r1.addHeader(r3, r4)     // Catch: java.lang.Exception -> L9f
            okhttp3.Request$Builder r1 = r1.get()     // Catch: java.lang.Exception -> L9f
            okhttp3.Request r1 = r1.build()     // Catch: java.lang.Exception -> L9f
            okhttp3.Call r1 = r2.newCall(r1)     // Catch: java.lang.Exception -> L9f
            okhttp3.Response r1 = r1.execute()     // Catch: java.lang.Exception -> L9f
            okhttp3.ResponseBody r0 = r1.body()     // Catch: java.lang.Exception -> L9d
            java.lang.String r0 = r0.string()     // Catch: java.lang.Exception -> L9d
            boolean r2 = r1.isSuccessful()     // Catch: java.lang.Exception -> L9d
            if (r2 == 0) goto Lac
            com.google.gson.Gson r2 = new com.google.gson.Gson     // Catch: java.lang.Exception -> L9d
            r2.<init>()     // Catch: java.lang.Exception -> L9d
            java.lang.Class<cu.uci.android.apklis.storage.repository.model.ApiUser> r3 = cu.uci.android.apklis.storage.repository.model.ApiUser.class
            java.lang.Object r0 = r2.fromJson(r0, r3)     // Catch: java.lang.Exception -> L9d
            cu.uci.android.apklis.storage.repository.model.ApiUser r0 = (cu.uci.android.apklis.storage.repository.model.ApiUser) r0     // Catch: java.lang.Exception -> L9d
            java.lang.Integer r2 = r0.getId()     // Catch: java.lang.Exception -> L9d
            r6.setId(r2)     // Catch: java.lang.Exception -> L9d
            java.lang.String r2 = r0.getAvatar()     // Catch: java.lang.Exception -> L9d
            r6.setAvatar(r2)     // Catch: java.lang.Exception -> L9d
            java.lang.String r2 = r0.getEmail()     // Catch: java.lang.Exception -> L9d
            r6.setEmail(r2)     // Catch: java.lang.Exception -> L9d
            java.lang.String r2 = r0.getFirst_name()     // Catch: java.lang.Exception -> L9d
            r6.setFirstName(r2)     // Catch: java.lang.Exception -> L9d
            java.lang.String r2 = r0.getLast_name()     // Catch: java.lang.Exception -> L9d
            r6.setLastName(r2)     // Catch: java.lang.Exception -> L9d
            java.lang.String r2 = r0.getPhone_number()     // Catch: java.lang.Exception -> L9d
            r6.setPhoneNumber(r2)     // Catch: java.lang.Exception -> L9d
            java.util.ArrayList r0 = r0.getFavorite_apps()     // Catch: java.lang.Exception -> L9d
            r6.setFavoriteApps(r0)     // Catch: java.lang.Exception -> L9d
            goto Lac
        L9d:
            r6 = move-exception
            goto La1
        L9f:
            r6 = move-exception
            r1 = r0
        La1:
            java.lang.Class r0 = r6.getClass()
            java.lang.String r0 = r0.getName()
            cu.uci.android.apklis.MainApp.log(r0, r6)
        Lac:
            if (r1 == 0) goto Lb1
            r1.close()
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.storage.repository.UserRepositoryImpl.getUserInfo(cu.uci.android.apklis.domain.model.UserAccount):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00d5  */
    @Override // cu.uci.android.apklis.domain.UserRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public cu.uci.android.apklis.domain.model.UserAccount login(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            cu.uci.android.apklis.domain.model.UserAccount r0 = new cu.uci.android.apklis.domain.model.UserAccount
            r0.<init>()
            r0.setUsername(r8)
            r0.setPassword(r9)
            r1 = 0
            java.lang.String r2 = "https://api.apklis.cu/o/token/"
            okhttp3.OkHttpClient r3 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> Lc6
            r3.<init>()     // Catch: java.lang.Exception -> Lc6
            okhttp3.OkHttpClient$Builder r3 = r3.newBuilder()     // Catch: java.lang.Exception -> Lc6
            r4 = 30000(0x7530, double:1.4822E-319)
            java.util.concurrent.TimeUnit r6 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch: java.lang.Exception -> Lc6
            okhttp3.OkHttpClient$Builder r3 = r3.writeTimeout(r4, r6)     // Catch: java.lang.Exception -> Lc6
            r4 = 0
            okhttp3.OkHttpClient$Builder r3 = r3.followRedirects(r4)     // Catch: java.lang.Exception -> Lc6
            okhttp3.OkHttpClient r3 = r3.build()     // Catch: java.lang.Exception -> Lc6
            java.lang.String r4 = "multipart/form-data"
            okhttp3.MediaType r4 = okhttp3.MediaType.parse(r4)     // Catch: java.lang.Exception -> Lc6
            okhttp3.MultipartBody$Builder r5 = new okhttp3.MultipartBody$Builder     // Catch: java.lang.Exception -> Lc6
            r5.<init>()     // Catch: java.lang.Exception -> Lc6
            okhttp3.MultipartBody$Builder r4 = r5.setType(r4)     // Catch: java.lang.Exception -> Lc6
            java.lang.String r5 = "client_id"
            java.lang.String r6 = cu.uci.android.apklis.storage.repository.UserRepositoryImpl.CLIENT_ID     // Catch: java.lang.Exception -> Lc6
            okhttp3.MultipartBody$Builder r4 = r4.addFormDataPart(r5, r6)     // Catch: java.lang.Exception -> Lc6
            java.lang.String r5 = "grant_type"
            java.lang.String r6 = "password"
            okhttp3.MultipartBody$Builder r4 = r4.addFormDataPart(r5, r6)     // Catch: java.lang.Exception -> Lc6
            java.lang.String r5 = "username"
            okhttp3.MultipartBody$Builder r8 = r4.addFormDataPart(r5, r8)     // Catch: java.lang.Exception -> Lc6
            java.lang.String r4 = "password"
            okhttp3.MultipartBody$Builder r8 = r8.addFormDataPart(r4, r9)     // Catch: java.lang.Exception -> Lc6
            okhttp3.MultipartBody r8 = r8.build()     // Catch: java.lang.Exception -> Lc6
            okhttp3.Request$Builder r9 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> Lc6
            r9.<init>()     // Catch: java.lang.Exception -> Lc6
            okhttp3.Request$Builder r9 = r9.url(r2)     // Catch: java.lang.Exception -> Lc6
            okhttp3.Request$Builder r8 = r9.post(r8)     // Catch: java.lang.Exception -> Lc6
            okhttp3.Request r8 = r8.build()     // Catch: java.lang.Exception -> Lc6
            okhttp3.Call r8 = r3.newCall(r8)     // Catch: java.lang.Exception -> Lc6
            okhttp3.Response r8 = r8.execute()     // Catch: java.lang.Exception -> Lc6
            boolean r9 = r8.isSuccessful()     // Catch: java.lang.Exception -> Lc4
            if (r9 == 0) goto Lb8
            com.google.gson.JsonParser r9 = new com.google.gson.JsonParser     // Catch: java.lang.Exception -> Lc4
            r9.<init>()     // Catch: java.lang.Exception -> Lc4
            okhttp3.ResponseBody r2 = r8.body()     // Catch: java.lang.Exception -> Lc4
            java.lang.String r2 = r2.string()     // Catch: java.lang.Exception -> Lc4
            com.google.gson.JsonElement r9 = r9.parse(r2)     // Catch: java.lang.Exception -> Lc4
            com.google.gson.JsonObject r9 = (com.google.gson.JsonObject) r9     // Catch: java.lang.Exception -> Lc4
            java.lang.String r2 = "token_type"
            com.google.gson.JsonElement r2 = r9.get(r2)     // Catch: java.lang.Exception -> Lc4
            java.lang.String r2 = r2.getAsString()     // Catch: java.lang.Exception -> Lc4
            java.lang.String r3 = "access_token"
            com.google.gson.JsonElement r9 = r9.get(r3)     // Catch: java.lang.Exception -> Lc4
            java.lang.String r9 = r9.getAsString()     // Catch: java.lang.Exception -> Lc4
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lc4
            r3.<init>()     // Catch: java.lang.Exception -> Lc4
            r3.append(r2)     // Catch: java.lang.Exception -> Lc4
            java.lang.String r2 = " "
            r3.append(r2)     // Catch: java.lang.Exception -> Lc4
            r3.append(r9)     // Catch: java.lang.Exception -> Lc4
            java.lang.String r9 = r3.toString()     // Catch: java.lang.Exception -> Lc4
            r0.setAccessToken(r9)     // Catch: java.lang.Exception -> Lc4
            r7.getUserInfo(r0)     // Catch: java.lang.Exception -> Lc4
            return r0
        Lb8:
            okhttp3.ResponseBody r9 = r8.body()     // Catch: java.lang.Exception -> Lc4
            java.lang.String r9 = r9.string()     // Catch: java.lang.Exception -> Lc4
            cu.uci.android.apklis.MainApp.log(r9)     // Catch: java.lang.Exception -> Lc4
            goto Ld3
        Lc4:
            r9 = move-exception
            goto Lc8
        Lc6:
            r9 = move-exception
            r8 = r1
        Lc8:
            java.lang.Class r0 = r9.getClass()
            java.lang.String r0 = r0.getName()
            cu.uci.android.apklis.MainApp.log(r0, r9)
        Ld3:
            if (r8 == 0) goto Ld8
            r8.close()
        Ld8:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.storage.repository.UserRepositoryImpl.login(java.lang.String, java.lang.String):cu.uci.android.apklis.domain.model.UserAccount");
    }

    @Override // cu.uci.android.apklis.domain.UserRepository
    public String newPassword(String str) {
        Response response;
        Request build;
        UserAccount userAccount = new UserAccount();
        try {
            OkHttpClient build2 = new OkHttpClient().newBuilder().followRedirects(false).build();
            build = new Request.Builder().url("https://api.apklis.cu/v1/user/sms_forgot_password/").post(new MultipartBody.Builder().setType(MediaType.parse("multipart/form-data")).addFormDataPart("phone_number", str).build()).build();
            response = build2.newCall(build).execute();
        } catch (Exception e) {
            e = e;
            response = null;
        }
        try {
            String string = response.body().string();
            if (response.isSuccessful()) {
                JsonObject jsonObject = (JsonObject) new JsonParser().parse(string);
                if (jsonObject.has("access_token")) {
                    String asString = jsonObject.get("access_token").getAsString();
                    this.token_final = asString;
                    userAccount.setAccessToken(asString);
                }
                if (jsonObject.has("username")) {
                    userAccount.setUsername(jsonObject.get("username").getAsString());
                }
            }
            MainApp.log(build.toString());
            return string;
        } catch (Exception e2) {
            e = e2;
            MainApp.log(e.getClass().getName(), e);
            if (response != null) {
                response.close();
            }
            return userAccount.getAccessToken();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0100  */
    @Override // cu.uci.android.apklis.domain.UserRepository
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public cu.uci.android.apklis.storage.repository.model.ApiUser signUp(java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11) {
        /*
            r6 = this;
            cu.uci.android.apklis.domain.model.UserAccount r0 = new cu.uci.android.apklis.domain.model.UserAccount
            r0.<init>()
            r0.setUsername(r7)
            r0.setPassword(r11)
            r0 = 0
            java.lang.String r1 = "https://api.apklis.cu/v1/user/"
            okhttp3.OkHttpClient r2 = new okhttp3.OkHttpClient     // Catch: java.lang.Exception -> Lf1
            r2.<init>()     // Catch: java.lang.Exception -> Lf1
            okhttp3.OkHttpClient$Builder r2 = r2.newBuilder()     // Catch: java.lang.Exception -> Lf1
            r3 = 0
            okhttp3.OkHttpClient$Builder r2 = r2.followRedirects(r3)     // Catch: java.lang.Exception -> Lf1
            okhttp3.OkHttpClient r2 = r2.build()     // Catch: java.lang.Exception -> Lf1
            java.lang.String r4 = "multipart/form-data"
            okhttp3.MediaType r4 = okhttp3.MediaType.parse(r4)     // Catch: java.lang.Exception -> Lf1
            okhttp3.MultipartBody$Builder r5 = new okhttp3.MultipartBody$Builder     // Catch: java.lang.Exception -> Lf1
            r5.<init>()     // Catch: java.lang.Exception -> Lf1
            okhttp3.MultipartBody$Builder r4 = r5.setType(r4)     // Catch: java.lang.Exception -> Lf1
            java.lang.String r5 = "username"
            okhttp3.MultipartBody$Builder r7 = r4.addFormDataPart(r5, r7)     // Catch: java.lang.Exception -> Lf1
            java.lang.String r4 = "first_name"
            okhttp3.MultipartBody$Builder r7 = r7.addFormDataPart(r4, r8)     // Catch: java.lang.Exception -> Lf1
            java.lang.String r8 = "last_name"
            okhttp3.MultipartBody$Builder r7 = r7.addFormDataPart(r8, r9)     // Catch: java.lang.Exception -> Lf1
            java.lang.String r8 = "phone_number"
            okhttp3.MultipartBody$Builder r7 = r7.addFormDataPart(r8, r10)     // Catch: java.lang.Exception -> Lf1
            java.lang.String r8 = "password"
            okhttp3.MultipartBody$Builder r7 = r7.addFormDataPart(r8, r11)     // Catch: java.lang.Exception -> Lf1
            okhttp3.MultipartBody r7 = r7.build()     // Catch: java.lang.Exception -> Lf1
            okhttp3.Request$Builder r8 = new okhttp3.Request$Builder     // Catch: java.lang.Exception -> Lf1
            r8.<init>()     // Catch: java.lang.Exception -> Lf1
            okhttp3.Request$Builder r8 = r8.url(r1)     // Catch: java.lang.Exception -> Lf1
            okhttp3.Request$Builder r7 = r8.post(r7)     // Catch: java.lang.Exception -> Lf1
            okhttp3.Request r7 = r7.build()     // Catch: java.lang.Exception -> Lf1
            okhttp3.Call r7 = r2.newCall(r7)     // Catch: java.lang.Exception -> Lf1
            okhttp3.Response r7 = r7.execute()     // Catch: java.lang.Exception -> Lf1
            okhttp3.ResponseBody r8 = r7.body()     // Catch: java.lang.Exception -> Lef
            java.lang.String r8 = r8.string()     // Catch: java.lang.Exception -> Lef
            boolean r9 = r7.isSuccessful()     // Catch: java.lang.Exception -> Lef
            if (r9 == 0) goto L86
            com.google.gson.Gson r9 = new com.google.gson.Gson     // Catch: java.lang.Exception -> Lef
            r9.<init>()     // Catch: java.lang.Exception -> Lef
            java.lang.Class<cu.uci.android.apklis.storage.repository.model.ApiUser> r10 = cu.uci.android.apklis.storage.repository.model.ApiUser.class
            java.lang.Object r8 = r9.fromJson(r8, r10)     // Catch: java.lang.Exception -> Lef
            cu.uci.android.apklis.storage.repository.model.ApiUser r8 = (cu.uci.android.apklis.storage.repository.model.ApiUser) r8     // Catch: java.lang.Exception -> Lef
            return r8
        L86:
            int r9 = r7.code()     // Catch: java.lang.Exception -> Lef
            r10 = 400(0x190, float:5.6E-43)
            if (r9 != r10) goto Le3
            com.google.gson.Gson r9 = new com.google.gson.Gson     // Catch: java.lang.Exception -> Lef
            r9.<init>()     // Catch: java.lang.Exception -> Lef
            java.lang.Class<cu.uci.android.apklis.domain.model.ErrorUser> r10 = cu.uci.android.apklis.domain.model.ErrorUser.class
            java.lang.Object r8 = r9.fromJson(r8, r10)     // Catch: java.lang.Exception -> Lef
            cu.uci.android.apklis.domain.model.ErrorUser r8 = (cu.uci.android.apklis.domain.model.ErrorUser) r8     // Catch: java.lang.Exception -> Lef
            cu.uci.android.apklis.storage.repository.model.ApiUser r9 = new cu.uci.android.apklis.storage.repository.model.ApiUser     // Catch: java.lang.Exception -> Lef
            r9.<init>()     // Catch: java.lang.Exception -> Lef
            java.lang.String[] r10 = r8.getPassword()     // Catch: java.lang.Exception -> Lef
            if (r10 == 0) goto Lb6
            java.lang.String[] r10 = r8.getPassword()     // Catch: java.lang.Exception -> Lef
            int r10 = r10.length     // Catch: java.lang.Exception -> Lef
            if (r10 <= 0) goto Lb6
            java.lang.String[] r10 = r8.getPassword()     // Catch: java.lang.Exception -> Lef
            r10 = r10[r3]     // Catch: java.lang.Exception -> Lef
            r9.setPassword(r10)     // Catch: java.lang.Exception -> Lef
        Lb6:
            java.lang.String[] r10 = r8.getPhoneNumber()     // Catch: java.lang.Exception -> Lef
            if (r10 == 0) goto Lcc
            java.lang.String[] r10 = r8.getPhoneNumber()     // Catch: java.lang.Exception -> Lef
            int r10 = r10.length     // Catch: java.lang.Exception -> Lef
            if (r10 <= 0) goto Lcc
            java.lang.String[] r10 = r8.getPhoneNumber()     // Catch: java.lang.Exception -> Lef
            r10 = r10[r3]     // Catch: java.lang.Exception -> Lef
            r9.setPhone_number(r10)     // Catch: java.lang.Exception -> Lef
        Lcc:
            java.lang.String[] r10 = r8.getUsername()     // Catch: java.lang.Exception -> Lef
            if (r10 == 0) goto Le2
            java.lang.String[] r10 = r8.getUsername()     // Catch: java.lang.Exception -> Lef
            int r10 = r10.length     // Catch: java.lang.Exception -> Lef
            if (r10 <= 0) goto Le2
            java.lang.String[] r8 = r8.getUsername()     // Catch: java.lang.Exception -> Lef
            r8 = r8[r3]     // Catch: java.lang.Exception -> Lef
            r9.setUsername(r8)     // Catch: java.lang.Exception -> Lef
        Le2:
            return r9
        Le3:
            okhttp3.ResponseBody r8 = r7.body()     // Catch: java.lang.Exception -> Lef
            java.lang.String r8 = r8.string()     // Catch: java.lang.Exception -> Lef
            cu.uci.android.apklis.MainApp.log(r8)     // Catch: java.lang.Exception -> Lef
            goto Lfe
        Lef:
            r8 = move-exception
            goto Lf3
        Lf1:
            r8 = move-exception
            r7 = r0
        Lf3:
            java.lang.Class r9 = r8.getClass()
            java.lang.String r9 = r9.getName()
            cu.uci.android.apklis.MainApp.log(r9, r8)
        Lfe:
            if (r7 == 0) goto L103
            r7.close()
        L103:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cu.uci.android.apklis.storage.repository.UserRepositoryImpl.signUp(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):cu.uci.android.apklis.storage.repository.model.ApiUser");
    }

    @Override // cu.uci.android.apklis.domain.UserRepository
    public String suggest(String str, String str2, String str3) {
        MainApp.context.getSharedPreferences("shared", 0);
        try {
            OkHttpClient build = new OkHttpClient().newBuilder().followRedirects(false).build();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("description", str);
            jsonObject.addProperty("category", str2);
            jsonObject.addProperty("name", str3);
            Request build2 = new Request.Builder().url("https://api.apklis.cu/v1/suggest/").header("Content-Type", "json").addHeader("Authorization", MainApp.userAccount.getAccessToken()).post(new MultipartBody.Builder().setType(MediaType.parse("multipart/form-data")).addFormDataPart("description", str).addFormDataPart("category", str2).addFormDataPart("name", str3).build()).build();
            try {
                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
                Response execute = build.newCall(build2).execute();
                this.resStr = execute.body().string();
                Log.e("Sugerencia: ", this.resStr);
                if (execute.isSuccessful()) {
                    Log.e("suggest: ", "Sugerencia Enviada");
                }
            } catch (IOException e) {
                MainApp.log(getClass().getName(), e);
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e("suggest: ", e2.getMessage());
        }
        return this.resStr;
    }
}
