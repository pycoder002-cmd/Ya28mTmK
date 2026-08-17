package com.startapp;

import android.content.Context;
import android.util.DisplayMetrics;
import com.startapp.sdk.ads.video.vast.VASTErrorCodes;
import com.startapp.sdk.omsdk.VerificationDetails;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import net.gotev.uploadservice.ContentType;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class e5 {
    public static final List<String> a = Arrays.asList(ContentType.VIDEO_MPEG4, "video/3gpp");
    public final int b;
    public final double c;
    public a d;
    public VASTErrorCodes e;
    public int f;
    public int g = 10;
    public final String h;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface a {
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface b {
    }

    public e5(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        float f = context.getResources().getDisplayMetrics().density;
        f = f <= 0.0f ? 1.0f : f;
        this.c = i / i2;
        this.b = Math.round(i / f);
        this.h = za.a(context);
    }

    public static List<VerificationDetails> a(d5 d5Var) {
        String f;
        String f2;
        List<d5> a2 = d5Var.a("Verification", "AdVerifications", null, null);
        Iterator it = ((ArrayList) d5Var.a("Extension", "Extensions", "type", Collections.singletonList("AdVerifications"))).iterator();
        while (it.hasNext()) {
            ((ArrayList) a2).addAll(((d5) it.next()).a("Verification", "AdVerifications", null, null));
        }
        ArrayList arrayList = new ArrayList();
        Iterator it2 = ((ArrayList) a2).iterator();
        while (it2.hasNext()) {
            d5 d5Var2 = (d5) it2.next();
            String a3 = d5Var2.a("vendor");
            if (a3 != null && (f = d5Var2.f("JavaScriptResource")) != null && (f2 = d5Var2.f("VerificationParameters")) != null) {
                d5 b2 = d5Var2.b("JavaScriptResource", "apiFramework", null);
                String a4 = b2 == null ? null : b2.a("apiFramework");
                if (a4 != null && a4.equalsIgnoreCase("omid")) {
                    arrayList.add(new VerificationDetails(a3, f, f2));
                }
            }
        }
        return arrayList;
    }

    /* JADX WARN: Code restructure failed: missing block: B:103:0x0115, code lost:
    
        if (r12 != null) goto L63;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:120:0x02d0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0291 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x005a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0081 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01cd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01b0  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v30 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.util.List, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.startapp.a5 a(java.lang.String r26, java.util.List<java.lang.String> r27, com.startapp.e5.b r28) {
        /*
            Method dump skipped, instructions count: 737
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.e5.a(java.lang.String, java.util.List, com.startapp.e5$b):com.startapp.a5");
    }

    public final String a(String str) throws IOException {
        Throwable th;
        HttpURLConnection httpURLConnection;
        int i = this.f;
        BufferedInputStream bufferedInputStream = null;
        if (i >= this.g) {
            return null;
        }
        this.f = i + 1;
        try {
            httpURLConnection = v9.a(str, this.h);
            try {
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(httpURLConnection.getInputStream());
                try {
                    String next = new Scanner(bufferedInputStream2).useDelimiter("\\A").next();
                    aa.a(bufferedInputStream2);
                    httpURLConnection.disconnect();
                    return next;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedInputStream = bufferedInputStream2;
                    aa.a(bufferedInputStream);
                    if (httpURLConnection == null) {
                        throw th;
                    }
                    httpURLConnection.disconnect();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            httpURLConnection = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:169:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0330 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:187:0x027e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0322  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.startapp.d5 r29, com.startapp.a5 r30) {
        /*
            Method dump skipped, instructions count: 901
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.e5.a(com.startapp.d5, com.startapp.a5):void");
    }

    public final void a(List<String> list, VASTErrorCodes vASTErrorCodes) {
        this.e = vASTErrorCodes;
        ArrayList arrayList = new ArrayList(list);
        list.clear();
        a aVar = this.d;
        if (aVar != null) {
            z3.a(z3.this, vASTErrorCodes, arrayList);
        }
    }
}
