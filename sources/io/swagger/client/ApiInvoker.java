package io.swagger.client;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ResponseDelivery;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.NoCache;
import com.android.volley.toolbox.RequestFuture;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.gson.JsonParseException;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import io.swagger.client.auth.ApiKeyAuth;
import io.swagger.client.auth.Authentication;
import io.swagger.client.auth.HttpBasicAuth;
import io.swagger.client.request.DeleteRequest;
import io.swagger.client.request.GetRequest;
import io.swagger.client.request.PatchRequest;
import io.swagger.client.request.PostRequest;
import io.swagger.client.request.PutRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

/* loaded from: classes2.dex */
public class ApiInvoker {
    private static ApiInvoker INSTANCE;
    private Map<String, Authentication> authentications;
    private int connectionTimeout;
    private Map<String, String> defaultHeaderMap = new HashMap();
    private RequestQueue mRequestQueue;
    public static final ContentType TEXT_PLAIN_UTF8 = ContentType.create("text/plain", Consts.UTF_8);
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    static {
        DATE_TIME_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    private ApiInvoker(Cache cache, Network network, int i, ResponseDelivery responseDelivery, int i2) {
        cache = cache == null ? new NoCache() : cache;
        network = network == null ? new BasicNetwork(new HurlStack()) : network;
        if (responseDelivery == null) {
            initConnectionRequest(cache, network);
        } else {
            initConnectionRequest(cache, network, i, responseDelivery);
        }
        this.connectionTimeout = i2;
    }

    public static Object deserialize(String str, String str2, Class cls) throws ApiException {
        try {
            if (!"list".equalsIgnoreCase(str2) && !"array".equalsIgnoreCase(str2)) {
                return String.class.equals(cls) ? (str == null || !str.startsWith("\"") || !str.endsWith("\"") || str.length() <= 1) ? str : str.substring(1, str.length() - 1) : JsonUtil.deserializeToObject(str, cls);
            }
            return JsonUtil.deserializeToList(str, cls);
        } catch (JsonParseException e) {
            throw new ApiException(500, e.getMessage());
        }
    }

    public static String formatDate(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String formatDateTime(Date date) {
        return DATE_TIME_FORMAT.format(date);
    }

    public static ApiInvoker getInstance() {
        if (INSTANCE == null) {
            initializeInstance();
        }
        return INSTANCE;
    }

    private void initConnectionRequest(Cache cache, Network network) {
        this.mRequestQueue = new RequestQueue(cache, network);
        this.mRequestQueue.start();
    }

    private void initConnectionRequest(Cache cache, Network network, int i, ResponseDelivery responseDelivery) {
        this.mRequestQueue = new RequestQueue(cache, network, i, responseDelivery);
        this.mRequestQueue.start();
    }

    public static void initializeInstance() {
        initializeInstance(null);
    }

    public static void initializeInstance(Cache cache) {
        initializeInstance(cache, null, 0, null, 30);
    }

    public static void initializeInstance(Cache cache, Network network, int i, ResponseDelivery responseDelivery, int i2) {
        INSTANCE = new ApiInvoker(cache, network, i, responseDelivery, i2);
        setUserAgent("Swagger-Codegen/1.0.0/android");
        INSTANCE.authentications = new HashMap();
        INSTANCE.authentications = Collections.unmodifiableMap(INSTANCE.authentications);
    }

    public static List<Pair> parameterToPairs(String str, String str2, Object obj) {
        ArrayList arrayList = new ArrayList();
        if (str2 == null || str2.isEmpty() || obj == null) {
            return arrayList;
        }
        if (!(obj instanceof Collection)) {
            arrayList.add(new Pair(str2, parameterToString(obj)));
            return arrayList;
        }
        Collection collection = (Collection) obj;
        if (collection.isEmpty()) {
            return arrayList;
        }
        if (str == null || str.isEmpty()) {
            str = "csv";
        }
        if (str.equals("multi")) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                arrayList.add(new Pair(str2, parameterToString(it.next())));
            }
            return arrayList;
        }
        String str3 = ",";
        if (str.equals("csv")) {
            str3 = ",";
        } else if (str.equals("ssv")) {
            str3 = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        } else if (str.equals("tsv")) {
            str3 = "\t";
        } else if (str.equals("pipes")) {
            str3 = "|";
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj2 : collection) {
            sb.append(str3);
            sb.append(parameterToString(obj2));
        }
        arrayList.add(new Pair(str2, sb.substring(1)));
        return arrayList;
    }

    public static String parameterToString(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof Date) {
            return formatDateTime((Date) obj);
        }
        if (!(obj instanceof Collection)) {
            return String.valueOf(obj);
        }
        StringBuilder sb = new StringBuilder();
        for (Object obj2 : (Collection) obj) {
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(String.valueOf(obj2));
        }
        return sb.toString();
    }

    public static Date parseDate(String str) {
        try {
            return DATE_FORMAT.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseDateTime(String str) {
        try {
            return DATE_TIME_FORMAT.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String serialize(Object obj) throws ApiException {
        if (obj == null) {
            return null;
        }
        try {
            return JsonUtil.serialize(obj);
        } catch (Exception e) {
            throw new ApiException(500, e.getMessage());
        }
    }

    public static void setUserAgent(String str) {
        INSTANCE.addDefaultHeader("User-Agent", str);
    }

    private void updateParamsForAuth(String[] strArr, List<Pair> list, Map<String, String> map) {
        for (String str : strArr) {
            Authentication authentication = this.authentications.get(str);
            if (authentication == null) {
                throw new RuntimeException("Authentication undefined: " + str);
            }
            authentication.applyToParams(list, map);
        }
    }

    public void addDefaultHeader(String str, String str2) {
        this.defaultHeaderMap.put(str, str2);
    }

    public Request<String> createRequest(String str, String str2, String str3, List<Pair> list, Object obj, Map<String, String> map, Map<String, String> map2, String str4, String[] strArr, Response.Listener<String> listener, Response.ErrorListener errorListener) throws ApiException, UnsupportedEncodingException {
        String str5;
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        updateParamsForAuth(strArr, list, map);
        if (list != null) {
            for (Pair pair : list) {
                if (!pair.getName().isEmpty()) {
                    sb.append(escapeString(pair.getName()));
                    sb.append("=");
                    sb.append(escapeString(pair.getValue()));
                    sb.append("&");
                }
            }
        }
        String str6 = str + str2 + sb.substring(0, sb.length() - 1);
        HashMap hashMap = new HashMap();
        for (String str7 : map.keySet()) {
            hashMap.put(str7, map.get(str7));
        }
        for (String str8 : this.defaultHeaderMap.keySet()) {
            if (!map.containsKey(str8)) {
                hashMap.put(str8, this.defaultHeaderMap.get(str8));
            }
        }
        hashMap.put("Accept", "application/json");
        if ("application/x-www-form-urlencoded".equals(str4)) {
            StringBuilder sb2 = new StringBuilder();
            for (String str9 : map2.keySet()) {
                String str10 = map2.get(str9);
                if (str10 != null && !"".equals(str10.trim())) {
                    if (sb2.length() > 0) {
                        sb2.append("&");
                    }
                    try {
                        sb2.append(URLEncoder.encode(str9, "utf8"));
                        sb2.append("=");
                        sb2.append(URLEncoder.encode(str10, "utf8"));
                    } catch (Exception unused) {
                    }
                }
            }
            str5 = sb2.toString();
        } else {
            str5 = null;
        }
        Request<String> getRequest = "GET".equals(str3) ? new GetRequest(str6, hashMap, null, listener, errorListener) : HttpPost.METHOD_NAME.equals(str3) ? str5 != null ? new PostRequest(str6, hashMap, str4, new StringEntity(str5, "UTF-8"), listener, errorListener) : obj != null ? obj instanceof HttpEntity ? new PostRequest(str6, hashMap, null, (HttpEntity) obj, listener, errorListener) : new PostRequest(str6, hashMap, str4, new StringEntity(serialize(obj), "UTF-8"), listener, errorListener) : new PostRequest(str6, hashMap, null, null, listener, errorListener) : "PUT".equals(str3) ? str5 != null ? new PutRequest(str6, hashMap, str4, new StringEntity(str5, "UTF-8"), listener, errorListener) : obj != null ? obj instanceof HttpEntity ? new PutRequest(str6, hashMap, null, (HttpEntity) obj, listener, errorListener) : new PutRequest(str6, hashMap, str4, new StringEntity(serialize(obj), "UTF-8"), listener, errorListener) : new PutRequest(str6, hashMap, null, null, listener, errorListener) : "DELETE".equals(str3) ? str5 != null ? new DeleteRequest(str6, hashMap, str4, new StringEntity(str5, "UTF-8"), listener, errorListener) : obj != null ? obj instanceof HttpEntity ? new DeleteRequest(str6, hashMap, null, (HttpEntity) obj, listener, errorListener) : new DeleteRequest(str6, hashMap, str4, new StringEntity(serialize(obj), "UTF-8"), listener, errorListener) : new DeleteRequest(str6, hashMap, null, null, listener, errorListener) : "PATCH".equals(str3) ? str5 != null ? new PatchRequest(str6, hashMap, str4, new StringEntity(str5, "UTF-8"), listener, errorListener) : obj != null ? obj instanceof HttpEntity ? new PatchRequest(str6, hashMap, null, (HttpEntity) obj, listener, errorListener) : new PatchRequest(str6, hashMap, str4, new StringEntity(serialize(obj), "UTF-8"), listener, errorListener) : new PatchRequest(str6, hashMap, null, null, listener, errorListener) : null;
        if (getRequest != null) {
            getRequest.setRetryPolicy(new DefaultRetryPolicy((int) TimeUnit.SECONDS.toMillis(this.connectionTimeout), 1, 1.0f));
        }
        return getRequest;
    }

    public String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str;
        }
    }

    public Authentication getAuthentication(String str) {
        return this.authentications.get(str);
    }

    public Map<String, Authentication> getAuthentications() {
        return this.authentications;
    }

    public int getConnectionTimeout() {
        return this.connectionTimeout;
    }

    public String invokeAPI(String str, String str2, String str3, List<Pair> list, Object obj, Map<String, String> map, Map<String, String> map2, String str4, String[] strArr) throws ApiException, InterruptedException, ExecutionException, TimeoutException {
        try {
            RequestFuture newFuture = RequestFuture.newFuture();
            Request<String> createRequest = createRequest(str, str2, str3, list, obj, map, map2, str4, strArr, newFuture, newFuture);
            if (createRequest == null) {
                return "no data";
            }
            this.mRequestQueue.add(createRequest);
            return (String) newFuture.get(this.connectionTimeout, TimeUnit.SECONDS);
        } catch (UnsupportedEncodingException unused) {
            throw new ApiException(0, "UnsupportedEncodingException");
        }
    }

    public void invokeAPI(String str, String str2, String str3, List<Pair> list, Object obj, Map<String, String> map, Map<String, String> map2, String str4, String[] strArr, Response.Listener<String> listener, Response.ErrorListener errorListener) throws ApiException {
        try {
            Request<String> createRequest = createRequest(str, str2, str3, list, obj, map, map2, str4, strArr, listener, errorListener);
            if (createRequest != null) {
                this.mRequestQueue.add(createRequest);
            }
        } catch (UnsupportedEncodingException unused) {
            throw new ApiException(0, "UnsupportedEncodingException");
        }
    }

    public void setApiKey(String str) {
        for (Authentication authentication : this.authentications.values()) {
            if (authentication instanceof ApiKeyAuth) {
                ((ApiKeyAuth) authentication).setApiKey(str);
                return;
            }
        }
        throw new RuntimeException("No API key authentication configured!");
    }

    public void setApiKeyPrefix(String str) {
        for (Authentication authentication : this.authentications.values()) {
            if (authentication instanceof ApiKeyAuth) {
                ((ApiKeyAuth) authentication).setApiKeyPrefix(str);
                return;
            }
        }
        throw new RuntimeException("No API key authentication configured!");
    }

    public void setConnectionTimeout(int i) {
        this.connectionTimeout = i;
    }

    public void setPassword(String str) {
        for (Authentication authentication : this.authentications.values()) {
            if (authentication instanceof HttpBasicAuth) {
                ((HttpBasicAuth) authentication).setPassword(str);
                return;
            }
        }
        throw new RuntimeException("No HTTP basic authentication configured!");
    }

    public void setUsername(String str) {
        for (Authentication authentication : this.authentications.values()) {
            if (authentication instanceof HttpBasicAuth) {
                ((HttpBasicAuth) authentication).setUsername(str);
                return;
            }
        }
        throw new RuntimeException("No HTTP basic authentication configured!");
    }

    public void stopQueue() {
        this.mRequestQueue.stop();
    }
}
