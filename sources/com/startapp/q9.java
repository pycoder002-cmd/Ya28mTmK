package com.startapp;

import android.text.TextUtils;
import com.startapp.sdk.common.SDKException;
import java.util.Collection;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class q9 extends u9 {
    public final JSONObject a = new JSONObject();

    @Override // com.startapp.u9
    public void a(String str, Object obj, boolean z, boolean z2) throws SDKException {
        if (z && obj == null) {
            throw new SDKException("Required value for key: [" + str + "] is missing", null);
        }
        if (obj == null || TextUtils.isEmpty(obj.toString())) {
            return;
        }
        try {
            this.a.put(str, obj);
        } catch (JSONException e) {
            if (z) {
                throw new SDKException("failed converting to json object value: [" + obj + "]", e);
            }
        }
    }

    @Override // com.startapp.u9
    public void a(String str, Set<String> set, boolean z, boolean z2) throws SDKException {
        if (z && (set == null || set.size() == 0)) {
            throw new SDKException("Required value for key: [" + str + "] is missing", null);
        }
        if (set == null || set.size() <= 0) {
            return;
        }
        try {
            this.a.put(str, new JSONArray((Collection) set));
        } catch (JSONException e) {
            if (z) {
                throw new SDKException("failed converting to json array values: [" + set + "]", e);
            }
        }
    }

    public String toString() {
        return this.a.toString();
    }
}
