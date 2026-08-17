package com.startapp;

import android.app.Activity;
import android.content.Context;
import com.startapp.sdk.common.SDKException;
import com.startapp.sdk.common.advertisingid.AdvertisingIdResolver;
import com.startapp.za;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class n7 {
    public final Context a;
    public final AdvertisingIdResolver b;
    public final xb c;
    public final i8 d;
    public final k9<o7> e;

    public n7(Context context, AdvertisingIdResolver advertisingIdResolver, xb xbVar, i8 i8Var, k9<o7> k9Var) {
        this.a = context;
        this.b = advertisingIdResolver;
        this.c = xbVar;
        this.d = i8Var;
        this.e = k9Var;
    }

    public final za.a a(String str, j5 j5Var, i9<String, Void> i9Var) {
        Map<String, String> map;
        if (j5Var != null) {
            map = a();
            try {
                s9 s9Var = new s9();
                j5Var.a(s9Var);
                String s9Var2 = s9Var.toString();
                if (str.contains("?") && s9Var2.startsWith("?")) {
                    str = str + "&" + s9Var2.substring(1);
                } else {
                    str = str + s9Var2;
                }
            } catch (SDKException e) {
                p7.a(this.a, e);
                return null;
            }
        } else {
            map = null;
        }
        String a = za.a(this.a);
        boolean z = b().b;
        i8 i8Var = this.d;
        i8Var.getClass();
        l8 l8Var = new l8(i8Var);
        try {
            za.a a2 = za.a(str, map, a, z);
            l8Var.a("GET", str, null);
            return a2;
        } catch (SDKException e2) {
            l8Var.a("GET", str, e2);
            if (i9Var != null) {
                try {
                    i9Var.a(e2.getMessage());
                } catch (Throwable th) {
                    p7.a(this.a, th);
                }
            }
            return null;
        }
    }

    public final String a(String str, j5 j5Var, byte[] bArr, boolean z, i9<String, Void> i9Var) {
        Map<String, String> map;
        if (bArr != null) {
            map = null;
        } else if (j5Var != null) {
            Map<String, String> a = a();
            try {
                q9 q9Var = new q9();
                j5Var.a(q9Var);
                byte[] bytes = q9Var.a.toString().getBytes();
                if (b().b) {
                    try {
                        Map<Activity, Integer> map2 = aa.a;
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                        gZIPOutputStream.write(bytes);
                        gZIPOutputStream.flush();
                        gZIPOutputStream.close();
                        bytes = byteArrayOutputStream.toByteArray();
                        z = true;
                    } catch (IOException e) {
                        p7.a(this.a, e);
                    }
                }
                bArr = bytes;
                map = a;
            } catch (SDKException e2) {
                p7.a(this.a, e2);
                return null;
            }
        } else {
            map = null;
            bArr = null;
        }
        String a2 = za.a(this.a);
        i8 i8Var = this.d;
        i8Var.getClass();
        l8 l8Var = new l8(i8Var);
        try {
            String a3 = za.a(str, bArr, map, a2, z);
            l8Var.a(HttpPost.METHOD_NAME, str, null);
            return a3 != null ? a3 : "";
        } catch (SDKException e3) {
            l8Var.a(HttpPost.METHOD_NAME, str, e3);
            if (i9Var != null) {
                try {
                    i9Var.a(e3.getMessage());
                } catch (Throwable th) {
                    p7.a(this.a, th);
                }
            }
            return null;
        }
    }

    public final Map<String, String> a() {
        HashMap hashMap = new HashMap();
        if (!b().c) {
            String str = null;
            try {
                str = URLEncoder.encode(this.b.a().b, "UTF-8");
            } catch (Throwable th) {
                p7.a(this.a, th);
            }
            hashMap.put("device-id", str);
        }
        hashMap.put("Accept-Language", this.c.b().d);
        return hashMap;
    }

    public final o7 b() {
        o7 call = this.e.call();
        return call != null ? call : o7.a;
    }
}
