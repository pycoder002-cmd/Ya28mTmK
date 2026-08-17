package com.startapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class q1 {
    private static final String a = "q1";
    private static final boolean b = false;
    private static final String c = "cdnconfig.txt";
    private static final String d = "cdnconfig.txt.sig";
    private static final int e = 10000;

    private static String a(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(Long.valueOf(j));
    }

    public static void a() {
        HttpURLConnection httpURLConnection;
        Throwable th;
        try {
            httpURLConnection = (HttpURLConnection) new URL(s.b().CONNECTIVITY_TEST_CDNCONFIG_URL().replace("[PROJECTID]", s.b().PROJECT_ID())).openConnection();
            try {
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setRequestProperty("If-Modified-Since", a(s.c().g()));
                httpURLConnection.setRequestProperty("Connection", "close");
                if (httpURLConnection.getResponseCode() == 304) {
                    s.c().e(n1.d());
                } else if (httpURLConnection.getResponseCode() == 200) {
                    long lastModified = httpURLConnection.getLastModified();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    try {
                        if (!a(inputStream)) {
                            throw new IOException("Verification of downloaded cdn config failed");
                        }
                        s.c().e(n1.d());
                        s.c().b(lastModified);
                    } finally {
                        inputStream.close();
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    h1.a(th);
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        } catch (Throwable th3) {
            httpURLConnection = null;
            th = th3;
        }
    }

    private static boolean a(ByteArrayOutputStream byteArrayOutputStream, ByteArrayOutputStream byteArrayOutputStream2) {
        try {
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            byte[] byteArray2 = byteArrayOutputStream.toByteArray();
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(s.e());
            signature.update(byteArray2);
            return signature.verify(byteArray);
        } catch (Throwable th) {
            h1.a(th);
            return false;
        }
    }

    private static boolean a(InputStream inputStream) throws IOException {
        z0 z0Var;
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        byte[] bArr = new byte[512];
        while (true) {
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    break;
                }
                if (!nextEntry.isDirectory()) {
                    if (nextEntry.getName().equalsIgnoreCase(c)) {
                        for (int read = zipInputStream.read(bArr); read != -1; read = zipInputStream.read(bArr)) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        byteArrayOutputStream.flush();
                        zipInputStream.closeEntry();
                    } else if (nextEntry.getName().equalsIgnoreCase(d)) {
                        for (int read2 = zipInputStream.read(bArr); read2 != -1; read2 = zipInputStream.read(bArr)) {
                            byteArrayOutputStream2.write(bArr, 0, read2);
                        }
                        byteArrayOutputStream2.flush();
                        zipInputStream.closeEntry();
                    }
                }
            } finally {
            }
        }
        zipInputStream.close();
        try {
            byteArrayOutputStream2.close();
        } catch (Throwable th) {
            h1.b(th);
        }
        try {
            byteArrayOutputStream.close();
        } catch (Throwable th2) {
            h1.b(th2);
        }
        boolean a2 = s.b().CONNECTIVITY_TEST_VERIFY_CDNCONFIG_SIGNATURE() ? a(byteArrayOutputStream, byteArrayOutputStream2) : true;
        if (a2 && (z0Var = (z0) v1.a(new String(byteArrayOutputStream.toByteArray(), "UTF-8"), z0.class)) != null) {
            t c2 = s.c();
            c2.a((Set<String>) null);
            c2.b(new HashSet(z0Var.ct.cdn));
            c2.c(z0Var.ct.criteria);
            c2.c(new HashSet(z0Var.ltr.cdn));
            c2.d(z0Var.ltr.criteria);
        }
        return a2;
    }
}
