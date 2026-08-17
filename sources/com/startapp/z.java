package com.startapp;

import android.content.Context;
import com.startapp.networkTest.enums.CtTestTypes;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class z implements X509TrustManager {
    private static final boolean a = false;
    private static final String b = "z";
    private static String c = "";
    private static boolean d = false;
    private static X509TrustManager e = null;
    private static X509TrustManager f = null;
    private static final String g = "R_hqKukfFZxKn52";
    private static final X509TrustManager h = new a();
    private X509TrustManager[] i;
    private CtTestTypes[] j;
    private String k;
    private CtTestTypes l = CtTestTypes.Unknown;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    public z(Context context, boolean z) {
        this.k = "";
        a(context, z);
        this.i = r4;
        this.j = r3;
        X509TrustManager[] x509TrustManagerArr = {e, f, h};
        CtTestTypes[] ctTestTypesArr = {CtTestTypes.SSLOwnTs, CtTestTypes.SSLDeviceTs, CtTestTypes.SSLTrustAll};
        this.k = c;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0031, code lost:
    
        com.startapp.z.f = (javax.net.ssl.X509TrustManager) r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(android.content.Context r7, boolean r8) {
        /*
            Method dump skipped, instructions count: 233
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.z.a(android.content.Context, boolean):void");
    }

    public String a() {
        return this.k;
    }

    public CtTestTypes b() {
        return this.l;
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        int i = 0;
        while (true) {
            X509TrustManager[] x509TrustManagerArr = this.i;
            if (i >= x509TrustManagerArr.length) {
                return;
            }
            X509TrustManager x509TrustManager = x509TrustManagerArr[i];
            if (x509TrustManager != null) {
                try {
                    this.l = this.j[i];
                    x509TrustManager.checkServerTrusted(x509CertificateArr, str);
                    return;
                } catch (CertificateException e2) {
                    if (i == 0) {
                        this.k += e2.getMessage();
                    }
                    if (i + 1 == this.i.length) {
                        throw e2;
                    }
                }
            }
            i++;
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return f.getAcceptedIssuers();
    }
}
