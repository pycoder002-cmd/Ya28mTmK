package com.startapp;

import com.startapp.common.parser.TypeParser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class z9<T> implements TypeParser<List<T>> {
    private static final String LOG_TAG = "z9";
    private final Class<T> itemClass;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements i9<Integer, JSONObject> {
        public final /* synthetic */ Object a;

        public a(z9 z9Var, Object obj) {
            this.a = obj;
        }

        @Override // com.startapp.i9
        public JSONObject a(Integer num) {
            try {
                return ((JSONArray) this.a).getJSONObject(num.intValue());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements i9<Integer, JSONObject> {
        public final /* synthetic */ Object a;

        public b(z9 z9Var, Object obj) {
            this.a = obj;
        }

        @Override // com.startapp.i9
        public JSONObject a(Integer num) {
            try {
                return ((JSONObject) this.a).getJSONObject(num.toString());
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public z9(Class<T> cls) {
        this.itemClass = cls;
    }

    @Override // com.startapp.common.parser.TypeParser
    public List<T> parse(Class<List<T>> cls, Object obj) {
        int length;
        i9 bVar;
        if (obj instanceof JSONArray) {
            length = ((JSONArray) obj).length();
            bVar = new a(this, obj);
        } else {
            if (!(obj instanceof JSONObject)) {
                return null;
            }
            length = ((JSONObject) obj).length();
            bVar = new b(this, obj);
        }
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            try {
                arrayList.add(com.startapp.b.a(this.itemClass, (JSONObject) bVar.a(Integer.valueOf(i))));
            } catch (Throwable unused) {
            }
        }
        return arrayList;
    }
}
