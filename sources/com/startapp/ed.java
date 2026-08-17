package com.startapp;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.util.Pair;
import com.startapp.sdk.adsbase.remoteconfig.RscMetadata;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ed {
    public final Context a;
    public final k9<RscMetadata> b;
    public RscMetadata c;
    public List<nd> d;
    public List<fd> e;
    public final Map<ja, Pair<Long, SoftReference<JSONObject>>> f = new WeakHashMap();

    public ed(Context context, k9<RscMetadata> k9Var) {
        this.a = context;
        this.b = k9Var;
    }

    public static String a(String str) throws IOException {
        byte[] a = wa.a(Base64.decode(str, 8));
        Map<Activity, Integer> map = aa.a;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InflaterOutputStream inflaterOutputStream = new InflaterOutputStream(byteArrayOutputStream, new Inflater(true));
        inflaterOutputStream.write(a);
        inflaterOutputStream.close();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public static JSONArray a(Context context, fd fdVar) {
        ja jaVar = fdVar.a;
        String[] strArr = jaVar.c;
        Object[] objArr = jaVar.e;
        if (strArr.length == objArr.length) {
            int length = strArr.length;
            if (length == 0) {
                return null;
            }
            try {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(strArr[i], objArr[i]);
                    jSONArray.put(jSONObject);
                }
                return jSONArray;
            } catch (JSONException e) {
                if (fdVar.a(32)) {
                    p7.a(context, e);
                }
            }
        } else if (fdVar.a(512)) {
            p7 p7Var = new p7(q7.c);
            p7Var.d = "c690e4ef5365d88b";
            p7Var.e = Arrays.toString(strArr) + ", " + Arrays.toString(objArr);
            p7Var.a(context);
        }
        return null;
    }

    public static boolean a(RscMetadata rscMetadata, int i) {
        return (rscMetadata == null || (rscMetadata.a() & i) == 0) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:135:0x00a4, code lost:
    
        if ((((java.lang.Long) r10.first).longValue() + (r0 * 1000)) < android.os.SystemClock.elapsedRealtime()) goto L51;
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0190 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00b1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String a(java.lang.Object r19) {
        /*
            Method dump skipped, instructions count: 493
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.ed.a(java.lang.Object):java.lang.String");
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x01f8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01a8 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<com.startapp.fd> a() {
        /*
            Method dump skipped, instructions count: 679
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.ed.a():java.util.List");
    }

    public final synchronized List<fd> a(RscMetadata rscMetadata, List<nd> list, List<fd> list2) {
        List<nd> list3 = this.d;
        if (list3 != null) {
            Iterator<nd> it = list3.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(this.a);
                } catch (Throwable th) {
                    if (a(this.c, 64)) {
                        p7.a(this.a, th);
                    }
                }
            }
        }
        this.c = rscMetadata;
        this.d = list;
        this.e = list2;
        if (list != null) {
            Iterator<nd> it2 = list.iterator();
            while (it2.hasNext()) {
                try {
                    it2.next().a(this.a, this);
                } catch (Throwable th2) {
                    if (a(rscMetadata, 128)) {
                        p7.a(this.a, th2);
                    }
                }
            }
        }
        return list2;
    }

    public final boolean a(int i) {
        RscMetadata call = this.b.call();
        if (call == null || !call.d()) {
            call = null;
        }
        return (call == null || (i & call.a()) == 0) ? false : true;
    }
}
