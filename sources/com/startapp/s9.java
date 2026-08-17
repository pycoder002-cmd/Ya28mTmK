package com.startapp;

import android.text.TextUtils;
import com.startapp.sdk.common.SDKException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;
import kotlin.text.Typography;
import org.slf4j.Marker;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class s9 extends u9 {
    public final Set<t9> a = new HashSet();

    @Override // com.startapp.u9
    public void a(String str, Object obj, boolean z, boolean z2) throws SDKException {
        if (z && obj == null) {
            throw new SDKException("Required value for key: [" + str + "] is missing", null);
        }
        if (obj == null || TextUtils.isEmpty(obj.toString())) {
            return;
        }
        try {
            t9 t9Var = new t9();
            t9Var.a = str;
            String obj2 = obj.toString();
            if (z2) {
                obj2 = URLEncoder.encode(obj2, "UTF-8");
            }
            t9Var.b = obj2;
            if (this.a.add(t9Var)) {
                return;
            }
            this.a.remove(t9Var);
            this.a.add(t9Var);
        } catch (UnsupportedEncodingException e) {
            if (z) {
                throw new SDKException("failed encoding value: [" + obj + "]", e);
            }
        }
    }

    @Override // com.startapp.u9
    public void a(String str, Set<String> set, boolean z, boolean z2) throws SDKException {
        if (z && set == null) {
            throw new SDKException("Required value for key: [" + str + "] is missing", null);
        }
        if (set != null) {
            t9 t9Var = new t9();
            t9Var.a = str;
            HashSet hashSet = new HashSet();
            for (String str2 : set) {
                if (z2) {
                    try {
                        str2 = URLEncoder.encode(str2, "UTF-8");
                    } catch (UnsupportedEncodingException unused) {
                    }
                }
                hashSet.add(str2);
            }
            if (z && hashSet.size() == 0) {
                throw new SDKException("failed encoding value: [" + set + "]", null);
            }
            t9Var.c = hashSet;
            if (this.a.add(t9Var)) {
                return;
            }
            this.a.remove(t9Var);
            this.a.add(t9Var);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('?');
        for (t9 t9Var : this.a) {
            if (t9Var.b != null) {
                sb.append(t9Var.a);
                sb.append('=');
                sb.append(t9Var.b);
                sb.append(Typography.amp);
            } else {
                Set<String> set = t9Var.c;
                if (set != null) {
                    for (String str : set) {
                        sb.append(t9Var.a);
                        sb.append('=');
                        sb.append(str);
                        sb.append(Typography.amp);
                    }
                }
            }
        }
        if (sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString().replace(Marker.ANY_NON_NULL_MARKER, "%20");
    }
}
