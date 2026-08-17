package com.startapp;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class z1 {
    private static final String a = "z1";
    private static final boolean b = false;

    public static String[] a(String str) {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[10240];
        try {
            fileInputStream = new FileInputStream(str);
            while (true) {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (Throwable th) {
                    th = th;
                    try {
                        h1.a(th);
                        return new String[0];
                    } finally {
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th2) {
                                h1.b(th2);
                            }
                        }
                    }
                }
            }
            String[] split = new String(byteArrayOutputStream.toByteArray(), "UTF-8").split("\n");
            try {
                fileInputStream.close();
            } catch (Throwable th3) {
                h1.b(th3);
            }
            return split;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
        }
    }

    public static String[] b(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Process exec = Runtime.getRuntime().exec(str);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
            boolean z = true;
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                if (!z) {
                    stringBuffer.append("\n");
                }
                stringBuffer.append(readLine);
                z = false;
            }
            bufferedReader.close();
            exec.waitFor();
        } catch (Throwable th) {
            h1.a(th);
        }
        return stringBuffer.toString().split("\\n");
    }
}
