package com.startapp;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.WebSettings;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.search.SearchAuth;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.IOException;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.springframework.http.ContentCodingType;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class za {
    public static String a;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a {
        public String a;
        public String b;

        public String toString() {
            return "HttpResult: " + this.b + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.startapp.za.a a(java.lang.String r8, java.util.Map<java.lang.String, java.lang.String> r9, java.lang.String r10, boolean r11) throws com.startapp.sdk.common.SDKException {
        /*
            r1 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r0 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            java.net.HttpURLConnection r9 = a(r0, r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L94 java.io.IOException -> L97
            int r10 = r9.getResponseCode()     // Catch: java.lang.Throwable -> L8c java.io.IOException -> L90
            r11 = 200(0xc8, float:2.8E-43)
            if (r10 != r11) goto L6b
            int r11 = android.os.Build.VERSION.SDK_INT     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            r0 = 9
            if (r11 < r0) goto L29
            java.net.CookieManager r11 = com.startapp.d.b     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            if (r11 == 0) goto L29
            java.net.URI r0 = java.net.URI.create(r8)     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            java.util.Map r1 = r9.getHeaderFields()     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            r11.put(r0, r1)     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
        L29:
            java.io.InputStream r11 = r9.getInputStream()     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            com.startapp.za$a r0 = new com.startapp.za$a     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            r0.<init>()     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            java.lang.String r1 = r9.getContentType()     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            r0.b = r1     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            if (r11 == 0) goto L60
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            r1.<init>()     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            r2 = 1024(0x400, float:1.435E-42)
            char[] r2 = new char[r2]     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            java.lang.String r5 = "UTF-8"
            r4.<init>(r11, r5)     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            r3.<init>(r4)     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
        L4f:
            int r4 = r3.read(r2)     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            r5 = -1
            if (r4 == r5) goto L5a
            r1.write(r2, r6, r4)     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            goto L4f
        L5a:
            java.lang.String r1 = r1.toString()     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
            r0.a = r1     // Catch: java.io.IOException -> L67 java.lang.Throwable -> Lb6
        L60:
            com.startapp.ya.a(r11)
            r9.disconnect()
            return r0
        L67:
            r0 = move-exception
            r3 = r10
            r5 = r0
            goto L9c
        L6b:
            android.net.Uri r11 = android.net.Uri.parse(r8)     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            android.net.Uri$Builder r11 = r11.buildUpon()     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            android.net.Uri$Builder r11 = r11.query(r7)     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            android.net.Uri r2 = r11.build()     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            com.startapp.sdk.common.SDKException r11 = new com.startapp.sdk.common.SDKException     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            java.lang.String r1 = "GET"
            r4 = 1
            r5 = 0
            r0 = r11
            r3 = r10
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
            throw r11     // Catch: java.io.IOException -> L87 java.lang.Throwable -> L8c
        L87:
            r11 = move-exception
            r3 = r10
            r5 = r11
            r11 = r7
            goto L9c
        L8c:
            r8 = move-exception
            r11 = r7
        L8e:
            r7 = r9
            goto Lb8
        L90:
            r11 = move-exception
            r5 = r11
            r11 = r7
            goto L9b
        L94:
            r8 = move-exception
            r11 = r7
            goto Lb8
        L97:
            r11 = move-exception
            r5 = r11
            r9 = r7
            r11 = r9
        L9b:
            r3 = 0
        L9c:
            android.net.Uri r8 = android.net.Uri.parse(r8)     // Catch: java.lang.Throwable -> Lb6
            android.net.Uri$Builder r8 = r8.buildUpon()     // Catch: java.lang.Throwable -> Lb6
            android.net.Uri$Builder r8 = r8.query(r7)     // Catch: java.lang.Throwable -> Lb6
            android.net.Uri r2 = r8.build()     // Catch: java.lang.Throwable -> Lb6
            com.startapp.sdk.common.SDKException r8 = new com.startapp.sdk.common.SDKException     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r1 = "GET"
            r4 = 0
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> Lb6
            throw r8     // Catch: java.lang.Throwable -> Lb6
        Lb6:
            r8 = move-exception
            goto L8e
        Lb8:
            com.startapp.ya.a(r11)
            if (r7 == 0) goto Lc0
            r7.disconnect()
        Lc0:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.za.a(java.lang.String, java.util.Map, java.lang.String, boolean):com.startapp.za$a");
    }

    public static String a(Context context) {
        String str = a;
        if (str == null) {
            if (Build.VERSION.SDK_INT > 16) {
                try {
                    str = WebSettings.getDefaultUserAgent(context);
                } catch (Throwable unused) {
                }
            }
            if (str == null) {
                str = "-1";
            }
            a = str;
        }
        return str;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b8  */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.util.Map<java.lang.String, java.lang.String>, java.util.Map] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v19 */
    /* JADX WARN: Type inference failed for: r11v3, types: [java.io.Closeable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.String r8, byte[] r9, java.util.Map<java.lang.String, java.lang.String> r10, java.lang.String r11, boolean r12) throws com.startapp.sdk.common.SDKException {
        /*
            java.lang.String r5 = "application/json"
            r6 = 0
            r7 = 0
            r0 = r8
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            java.net.HttpURLConnection r10 = a(r0, r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> L8f java.io.IOException -> L92
            if (r9 == 0) goto L28
            int r11 = r9.length     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8b
            if (r11 <= 0) goto L28
            java.io.OutputStream r11 = r10.getOutputStream()     // Catch: java.lang.Throwable -> L22
            r11.write(r9)     // Catch: java.lang.Throwable -> L20
            r11.flush()     // Catch: java.lang.Throwable -> L20
            com.startapp.ya.a(r11)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8b
            goto L28
        L20:
            r9 = move-exception
            goto L24
        L22:
            r9 = move-exception
            r11 = r7
        L24:
            com.startapp.ya.a(r11)     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8b
            throw r9     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8b
        L28:
            int r9 = r10.getResponseCode()     // Catch: java.lang.Throwable -> L87 java.io.IOException -> L8b
            r11 = 200(0xc8, float:2.8E-43)
            if (r9 != r11) goto L66
            java.io.InputStream r11 = r10.getInputStream()     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L87
            if (r11 == 0) goto L5f
            java.io.StringWriter r12 = new java.io.StringWriter     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
            r12.<init>()     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
            r0 = 1024(0x400, float:1.435E-42)
            char[] r0 = new char[r0]     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
            java.lang.String r3 = "UTF-8"
            r2.<init>(r11, r3)     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
            r1.<init>(r2)     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
        L4b:
            int r2 = r1.read(r0)     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
            r3 = -1
            if (r2 == r3) goto L56
            r12.write(r0, r6, r2)     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
            goto L4b
        L56:
            java.lang.String r7 = r12.toString()     // Catch: java.io.IOException -> L5b java.lang.Throwable -> Lb1
            goto L5f
        L5b:
            r12 = move-exception
            r3 = r9
            r5 = r12
            goto L97
        L5f:
            com.startapp.ya.a(r11)
            r10.disconnect()
            return r7
        L66:
            android.net.Uri r11 = android.net.Uri.parse(r8)     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L87
            android.net.Uri$Builder r11 = r11.buildUpon()     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L87
            android.net.Uri$Builder r11 = r11.query(r7)     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L87
            android.net.Uri r2 = r11.build()     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L87
            com.startapp.sdk.common.SDKException r11 = new com.startapp.sdk.common.SDKException     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L87
            java.lang.String r1 = "POST"
            r4 = 0
            r5 = 0
            r0 = r11
            r3 = r9
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L87
            throw r11     // Catch: java.io.IOException -> L82 java.lang.Throwable -> L87
        L82:
            r11 = move-exception
            r3 = r9
            r5 = r11
            r11 = r7
            goto L97
        L87:
            r8 = move-exception
            r11 = r7
        L89:
            r7 = r10
            goto Lb3
        L8b:
            r9 = move-exception
            r5 = r9
            r11 = r7
            goto L96
        L8f:
            r8 = move-exception
            r11 = r7
            goto Lb3
        L92:
            r9 = move-exception
            r5 = r9
            r10 = r7
            r11 = r10
        L96:
            r3 = 0
        L97:
            android.net.Uri r8 = android.net.Uri.parse(r8)     // Catch: java.lang.Throwable -> Lb1
            android.net.Uri$Builder r8 = r8.buildUpon()     // Catch: java.lang.Throwable -> Lb1
            android.net.Uri$Builder r8 = r8.query(r7)     // Catch: java.lang.Throwable -> Lb1
            android.net.Uri r2 = r8.build()     // Catch: java.lang.Throwable -> Lb1
            com.startapp.sdk.common.SDKException r8 = new com.startapp.sdk.common.SDKException     // Catch: java.lang.Throwable -> Lb1
            java.lang.String r1 = "POST"
            r4 = 0
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5)     // Catch: java.lang.Throwable -> Lb1
            throw r8     // Catch: java.lang.Throwable -> Lb1
        Lb1:
            r8 = move-exception
            goto L89
        Lb3:
            com.startapp.ya.a(r11)
            if (r7 == 0) goto Lbb
            r7.disconnect()
        Lbb:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.za.a(java.lang.String, byte[], java.util.Map, java.lang.String, boolean):java.lang.String");
    }

    public static HttpURLConnection a(String str, byte[] bArr, Map<String, String> map, String str2, boolean z, String str3) throws IOException {
        CookieManager cookieManager;
        Map<String, List<String>> map2;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.addRequestProperty("Cache-Control", HeaderConstants.CACHE_CONTROL_NO_CACHE);
        if (Build.VERSION.SDK_INT >= 9 && (cookieManager = d.b) != null && (map2 = cookieManager.get(URI.create(str), httpURLConnection.getRequestProperties())) != null && map2.size() > 0 && map2.get("Cookie").size() > 0) {
            httpURLConnection.addRequestProperty("Cookie", TextUtils.join("=", map2.get("Cookie")));
        }
        if (str2 != null && str2.compareTo("-1") != 0) {
            httpURLConnection.addRequestProperty("User-Agent", str2);
        }
        httpURLConnection.setRequestProperty("Accept", "application/json;text/html;text/plain");
        httpURLConnection.setReadTimeout(SearchAuth.StatusCodes.AUTH_DISABLED);
        httpURLConnection.setConnectTimeout(SearchAuth.StatusCodes.AUTH_DISABLED);
        if (bArr != null) {
            httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setFixedLengthStreamingMode(bArr.length);
            if (str3 != null) {
                httpURLConnection.setRequestProperty("Content-Type", str3);
            }
            if (z) {
                httpURLConnection.setRequestProperty("Content-Encoding", ContentCodingType.GZIP_VALUE);
            }
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key != null && value != null) {
                    httpURLConnection.setRequestProperty(key, value);
                }
            }
        }
        return httpURLConnection;
    }
}
