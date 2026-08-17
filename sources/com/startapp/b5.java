package com.startapp;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.startapp.e5;
import com.startapp.sdk.ads.video.vast.VASTErrorCodes;
import com.startapp.sdk.components.ComponentLocator;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class b5 implements e5.b {
    public final Context a;
    public final String b;
    public final JSONArray c = new JSONArray();
    public final String d;
    public final String e;
    public boolean f;

    public b5(Context context, String str, String str2, String str3, boolean z) {
        this.a = context;
        this.b = str;
        this.d = str2;
        this.e = str3;
        this.f = z;
    }

    public void a(VASTErrorCodes vASTErrorCodes) {
        if (this.c.length() == 0) {
            return;
        }
        if (!this.f || vASTErrorCodes == VASTErrorCodes.ErrorNone) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("vastDocs", this.c);
                String str = this.d;
                if (str == null) {
                    str = "";
                }
                jSONObject.put("partnerResponse", str);
                String str2 = this.e;
                jSONObject.put("partnerName", str2 != null ? str2 : "");
                jSONObject.put("error", vASTErrorCodes.a());
                String jSONObject2 = jSONObject.toString();
                if (TextUtils.isEmpty(jSONObject2)) {
                    return;
                }
                n7 j = ComponentLocator.a(this.a).j();
                String str3 = this.b;
                j.getClass();
                byte[] bytes = jSONObject2.getBytes();
                Map<Activity, Integer> map = aa.a;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bytes);
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                try {
                    j.a(str3, null, byteArrayOutputStream.toByteArray(), true, null);
                } catch (Throwable th) {
                    p7.a(j.a, th);
                }
            } catch (Throwable th2) {
                p7.a(this.a, th2);
            }
        }
    }
}
