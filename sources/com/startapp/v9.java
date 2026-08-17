package com.startapp;

import android.text.TextUtils;
import com.google.android.gms.search.SearchAuth;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class v9 extends HttpURLConnection {
    public static String a(String str) throws Exception {
        boolean z;
        URI uri;
        try {
            new URI(str);
            z = false;
        } catch (URISyntaxException unused) {
            z = true;
        }
        if (z) {
            URL url = new URL(str);
            uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        } else {
            uri = new URI(str);
        }
        return uri.toURL().toString();
    }

    public static HttpURLConnection a(String str, String str2) throws IOException {
        boolean z;
        try {
            URLDecoder.decode(str, "UTF-8");
            z = false;
        } catch (UnsupportedEncodingException unused) {
            z = true;
        }
        if (z) {
            throw new UnsupportedEncodingException("URL is improperly encoded: " + str);
        }
        try {
            str = a(str);
        } catch (Exception unused2) {
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        if (!TextUtils.isEmpty(str2)) {
            httpURLConnection.setRequestProperty("User-Agent", str2);
        }
        httpURLConnection.setConnectTimeout(SearchAuth.StatusCodes.AUTH_DISABLED);
        httpURLConnection.setReadTimeout(SearchAuth.StatusCodes.AUTH_DISABLED);
        return httpURLConnection;
    }
}
