package cu.uci.android.apklis.storage.repository;

import android.util.Log;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.domain.ApiRepository;
import cu.uci.android.apklis.domain.model.App;
import cu.uci.android.apklis.domain.model.Category;
import cu.uci.android.apklis.domain.model.UserAccount;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Collections;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/* loaded from: classes.dex */
public class ApiRepositoryImpl implements ApiRepository {
    private static final String APP_DETAILS_BY_ID_URL = "http://api.apklis.cu/applications/%s/lastRelease";
    private static final String APP_LIST_BY_CATEGORY_URL = "http://api.apklis.cu/categories/%s/applications";
    private static final String APP_LIST_URL = "http://api.apklis.cu/applications";
    private static final String CATEGORY_LIST_URL = "http://api.apklis.cu/categories";
    private static final String FIELDS = "filter[fields][package_name]=true&filter[fields][name]=true&filter[fields][icon]=true&filter[fields][rating]=true&filter[fields][price]=true";
    private static final String FIELD_ID = "filter[fields][package_name]=true";
    private static final Integer PAGE_SIZE = Integer.valueOf(MainApp.APPS_LOAD_RANGE);
    private static final String PAGINATION = "limit=%d&offset=%d";
    private static final String QUERY_SEARCH = "search=%s'";
    private static final String ROOT_URL = "http://api.apklis.cu";
    private static final String USER_LOGIN_URL = "http://api.apklis.cu/users/login";
    private static final String USER_URL = "http://api.apklis.cu/users";

    /* loaded from: classes.dex */
    protected class MyRestTemplate extends RestTemplate {
        public MyRestTemplate(int i) {
            if (getRequestFactory() instanceof SimpleClientHttpRequestFactory) {
                Log.d("HTTP", "HttpUrlConnection is used");
                ((SimpleClientHttpRequestFactory) getRequestFactory()).setConnectTimeout(i);
                ((SimpleClientHttpRequestFactory) getRequestFactory()).setReadTimeout(i);
            } else if (getRequestFactory() instanceof HttpComponentsClientHttpRequestFactory) {
                Log.d("HTTP", "HttpClient is used");
                ((HttpComponentsClientHttpRequestFactory) getRequestFactory()).setReadTimeout(i);
                ((HttpComponentsClientHttpRequestFactory) getRequestFactory()).setConnectTimeout(i);
            }
        }
    }

    private static void AceptAllSSLCertificate() {
        TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: cu.uci.android.apklis.storage.repository.ApiRepositoryImpl.1
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
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: cu.uci.android.apklis.storage.repository.ApiRepositoryImpl.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
        } catch (Exception e) {
            MainApp.log(ApiRepositoryImpl.class.getName(), e);
            System.out.println(e.getMessage());
        }
    }

    private String getAppListUrl() {
        return APP_LIST_URL;
    }

    private String getUrlAccesCheck() {
        return ROOT_URL;
    }

    private String getUrlAppListInRangeMinus(int i, int i2) {
        return "http://api.apklis.cu/applications?" + String.format(PAGINATION, Integer.valueOf(i2), Integer.valueOf(i)) + "&" + FIELD_ID;
    }

    private String getUrlAppListInRangeMinus(int i, String str) {
        String format = String.format(PAGINATION, PAGE_SIZE, Integer.valueOf(i));
        String str2 = "";
        if (str != null) {
            str2 = "&" + String.format(QUERY_SEARCH, str);
        }
        return "http://api.apklis.cu/applications?" + format + str2;
    }

    private String getUrlAppListInRangeMinusByCategory(int i, String str) {
        String format = String.format(PAGINATION, PAGE_SIZE, Integer.valueOf(i));
        if (str == null) {
            return "http://api.apklis.cu/applications?" + format + "&" + FIELDS;
        }
        return String.format(APP_LIST_BY_CATEGORY_URL, str) + "?" + format + "&" + FIELDS;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cu.uci.android.apklis.domain.ApiRepository
    public Boolean checkConnectionToServer() {
        try {
            String urlAccesCheck = getUrlAccesCheck();
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
            HttpEntity<?> httpEntity = new HttpEntity<>((MultiValueMap<String, String>) httpHeaders);
            MyRestTemplate myRestTemplate = new MyRestTemplate(5000);
            myRestTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            myRestTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            ResponseEntity exchange = myRestTemplate.exchange(urlAccesCheck, HttpMethod.GET, httpEntity, String.class, new Object[0]);
            MainApp.log("Response: " + exchange.getStatusCode().value());
            MainApp.log("Body: " + ((String) exchange.getBody()));
            if (exchange.getStatusCode().equals(HttpStatus.OK)) {
                return true;
            }
        } catch (Exception e) {
            MainApp.log(getClass().getName(), e);
            MainApp.log(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cu.uci.android.apklis.domain.ApiRepository
    public App getAppDetailsById(String str) {
        try {
            String format = String.format(APP_DETAILS_BY_ID_URL, str);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
            HttpEntity<?> httpEntity = new HttpEntity<>((MultiValueMap<String, String>) httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            return (App) restTemplate.exchange(format, HttpMethod.GET, httpEntity, App.class, new Object[0]).getBody();
        } catch (Exception e) {
            MainApp.log(getClass().getName(), e);
            MainApp.log(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cu.uci.android.apklis.domain.ApiRepository
    public App[] getAppListMinus(int i, String str) {
        String urlAppListInRangeMinus = getUrlAppListInRangeMinus(i, str);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity<?> httpEntity = new HttpEntity<>((MultiValueMap<String, String>) httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cu.uci.android.apklis.domain.ApiRepository
    public App[] getAppListMinusByCategory(int i, String str) {
        String urlAppListInRangeMinusByCategory = getUrlAppListInRangeMinusByCategory(i, str);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
        HttpEntity<?> httpEntity = new HttpEntity<>((MultiValueMap<String, String>) httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
        return (App[]) restTemplate.exchange(urlAppListInRangeMinusByCategory, HttpMethod.GET, httpEntity, App[].class, new Object[0]).getBody();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cu.uci.android.apklis.domain.ApiRepository
    public Category[] getCategories() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
            HttpEntity<?> httpEntity = new HttpEntity<>((MultiValueMap<String, String>) httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            return (Category[]) restTemplate.exchange(CATEGORY_LIST_URL, HttpMethod.GET, httpEntity, Category[].class, new Object[0]).getBody();
        } catch (Exception e) {
            MainApp.log(getClass().getName(), e);
            MainApp.log(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cu.uci.android.apklis.domain.ApiRepository
    public UserAccount getUserAccountInfo(String str) {
        try {
            String str2 = "http://api.apklis.cu/users/" + str;
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
            HttpEntity<?> httpEntity = new HttpEntity<>((MultiValueMap<String, String>) httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            return (UserAccount) restTemplate.exchange(str2, HttpMethod.GET, httpEntity, UserAccount.class, new Object[0]).getBody();
        } catch (Exception e) {
            MainApp.log(getClass().getName(), e);
            MainApp.log(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // cu.uci.android.apklis.domain.ApiRepository
    public UserAccount login(String str, String str2) {
        try {
            UserAccount userAccount = new UserAccount();
            userAccount.setUsername(str);
            userAccount.setPassword(str2);
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
            httpHeaders.setContentType(new MediaType("application", "json"));
            HttpEntity<?> httpEntity = new HttpEntity<>(userAccount, httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            ResponseEntity exchange = restTemplate.exchange(USER_LOGIN_URL, HttpMethod.POST, httpEntity, UserAccount.class, new Object[0]);
            if (exchange.getStatusCode().equals(HttpStatus.OK)) {
                return (UserAccount) exchange.getBody();
            }
            return null;
        } catch (Exception e) {
            MainApp.log(getClass().getName(), e);
            MainApp.log(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override // cu.uci.android.apklis.domain.ApiRepository
    public UserAccount newLogin() {
        HttpEntity<?> httpEntity;
        RestTemplate restTemplate;
        try {
            LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap();
            linkedMultiValueMap.add("client_id", "7UMqh5p9CPPPEPi0s4Ksrv2vR5tBjBJH8XlaNMMB");
            linkedMultiValueMap.add("grant_type", "pass");
            linkedMultiValueMap.add("username", "admin");
            linkedMultiValueMap.add("password", "ss");
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setAccept(Collections.singletonList(new MediaType("application", "x-www-form-urlencoded")));
            httpHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded"));
            httpEntity = new HttpEntity<>(linkedMultiValueMap, httpHeaders);
            restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new FormHttpMessageConverter());
        } catch (Exception e) {
            MainApp.log(e.getClass().getName(), e);
        }
        return restTemplate.exchange("http://10.3.201.199:8000/o/token/", HttpMethod.POST, httpEntity, String.class, new Object[0]).getStatusCode().equals(HttpStatus.OK) ? null : null;
    }
}
