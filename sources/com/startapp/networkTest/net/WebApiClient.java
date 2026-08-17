package com.startapp.networkTest.net;

import com.startapp.h1;
import com.startapp.v1;
import com.startapp.x0;
import com.startapp.y0;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class WebApiClient {
    private static final String a = "WebApiClient";
    private static final int b = 10000;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum RequestMethod {
        POST,
        GET,
        PUT,
        DELETE
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public static y0 a(RequestMethod requestMethod, String str) throws IOException {
        return a(requestMethod, str, null);
    }

    public static y0 a(RequestMethod requestMethod, String str, Object obj) throws IOException {
        return a(requestMethod, str, obj, new x0[]{new x0("Content-Type", "application/json; charset=UTF-8"), new x0("Accept", "application/json")});
    }

    public static y0 a(RequestMethod requestMethod, String str, Object obj, x0[] x0VarArr) throws IOException {
        return a(requestMethod, str, obj, x0VarArr, false);
    }

    public static y0 a(RequestMethod requestMethod, String str, Object obj, x0[] x0VarArr, boolean z) throws IOException {
        HttpURLConnection httpURLConnection;
        y0 y0Var = new y0();
        URL url = new URL(str);
        URL url2 = new URL(str);
        if (z && url2.getProtocol().toLowerCase().equals("https")) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
            a(httpsURLConnection);
            httpsURLConnection.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            httpURLConnection = httpsURLConnection;
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setRequestMethod(requestMethod.toString());
        if (x0VarArr != null) {
            for (x0 x0Var : x0VarArr) {
                httpURLConnection.setRequestProperty(x0Var.a, x0Var.b);
            }
        }
        httpURLConnection.setConnectTimeout(10000);
        httpURLConnection.setReadTimeout(10000);
        if (obj != null) {
            if (requestMethod.equals(RequestMethod.GET) || requestMethod.equals(RequestMethod.DELETE)) {
                throw new IOException("GET and DELETE does not support a body");
            }
            httpURLConnection.setDoOutput(true);
            String a2 = v1.a(obj);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(a2.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        y0Var.a = httpURLConnection.getResponseCode();
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
        } catch (Throwable th) {
            h1.a(th);
        }
        httpURLConnection.disconnect();
        y0Var.b = sb.toString();
        return y0Var;
    }

    private static void a(HttpsURLConnection httpsURLConnection) {
        TrustManager[] trustManagerArr = {new a()};
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            httpsURLConnection.setSSLSocketFactory(sSLContext.getSocketFactory());
        } catch (Throwable th) {
            h1.a(th);
        }
    }
}
