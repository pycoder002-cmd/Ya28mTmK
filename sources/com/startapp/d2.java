package com.startapp;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Signature;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class d2 {
    private static final String a = "d2";
    private static final boolean b = false;
    private static final String c = "truststore.bin";
    private static final String d = "truststore.bin.sig";
    private static final int e = 10000;

    private static String a(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat.format(Long.valueOf(j));
    }

    private static void a(InputStream inputStream, File file) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        while (true) {
            try {
                ZipEntry nextEntry = zipInputStream.getNextEntry();
                if (nextEntry == null) {
                    return;
                }
                File file2 = new File(file, nextEntry.getName());
                if (file2.getCanonicalPath().startsWith(file.getCanonicalPath())) {
                    if (!nextEntry.isDirectory()) {
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        while (true) {
                            int read = zipInputStream.read();
                            if (read == -1) {
                                break;
                            } else {
                                fileOutputStream.write(read);
                            }
                        }
                        zipInputStream.closeEntry();
                        fileOutputStream.close();
                    } else if (!file2.isDirectory()) {
                        file2.mkdirs();
                    }
                }
            } finally {
                zipInputStream.close();
            }
        }
    }

    public static boolean a(Context context) {
        HttpURLConnection httpURLConnection;
        Throwable th;
        try {
            httpURLConnection = (HttpURLConnection) new URL(s.b().CONNECTIVITY_TEST_TRUSTSTORE_URL().replace("[PROJECTID]", s.b().PROJECT_ID())).openConnection();
            try {
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setRequestProperty("If-Modified-Since", a(s.c().F()));
                httpURLConnection.setRequestProperty("Connection", "close");
                if (httpURLConnection.getResponseCode() == 304) {
                    s.c().h(n1.d());
                } else if (httpURLConnection.getResponseCode() == 200) {
                    long lastModified = httpURLConnection.getLastModified();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    try {
                        a(inputStream, e(context));
                        inputStream.close();
                        if (!(s.b().CONNECTIVITY_TEST_VERIFY_TRUSTSTORE_SIGNATURE() ? a(new File(e(context), c), new File(e(context), d)) : true)) {
                            throw new IOException("Verification of downloaded truststore failed");
                        }
                        if (!f(context) || !g(context)) {
                            throw new IOException("Moving of cached files failed.");
                        }
                        s.c().h(n1.d());
                        s.c().l(lastModified);
                        httpURLConnection.disconnect();
                        return true;
                    } catch (Throwable th2) {
                        inputStream.close();
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                try {
                    h1.a(th);
                    b(context);
                    if (httpURLConnection == null) {
                        return false;
                    }
                    return false;
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            }
        } catch (Throwable th4) {
            httpURLConnection = null;
            th = th4;
        }
        return false;
    }

    public static boolean a(File file, File file2) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            FileInputStream fileInputStream3 = new FileInputStream(file2);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[512];
                for (int read = fileInputStream3.read(bArr); read != -1; read = fileInputStream3.read(bArr)) {
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                fileInputStream = new FileInputStream(file);
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    for (int read2 = fileInputStream.read(bArr); read2 != -1; read2 = fileInputStream.read(bArr)) {
                        byteArrayOutputStream2.write(bArr, 0, read2);
                    }
                    byteArrayOutputStream2.flush();
                    byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                    Signature signature = Signature.getInstance("SHA256withRSA");
                    signature.initVerify(s.e());
                    signature.update(byteArray2);
                    boolean verify = signature.verify(byteArray);
                    try {
                        fileInputStream3.close();
                    } catch (Throwable th) {
                        h1.b(th);
                    }
                    try {
                        fileInputStream.close();
                    } catch (Throwable th2) {
                        h1.b(th2);
                    }
                    return verify;
                } catch (Throwable th3) {
                    th = th3;
                    fileInputStream2 = fileInputStream3;
                    try {
                        h1.a(th);
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Throwable th4) {
                                h1.b(th4);
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th5) {
                                h1.b(th5);
                            }
                        }
                        return false;
                    } finally {
                    }
                }
            } catch (Throwable th6) {
                th = th6;
                fileInputStream = null;
            }
        } catch (Throwable th7) {
            th = th7;
            fileInputStream = null;
        }
    }

    private static void b(Context context) {
        File file = new File(e(context), c);
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(e(context), d);
        if (file2.exists()) {
            file2.delete();
        }
    }

    public static File c(Context context) {
        File file = new File(context.getFilesDir() + "/insight/truststore/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, c);
    }

    public static File d(Context context) {
        File file = new File(context.getFilesDir() + "/insight/truststore/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return new File(file, d);
    }

    private static File e(Context context) {
        File file = new File(context.getCacheDir() + "/insight/truststore/", "truststoreunzip");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private static boolean f(Context context) throws IOException {
        return new File(e(context), c).renameTo(c(context));
    }

    private static boolean g(Context context) throws IOException {
        return new File(e(context), d).renameTo(d(context));
    }
}
