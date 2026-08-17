package com.startapp;

import com.startapp.common.parser.JSONStreamException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class b {
    public static Map<String, Class> a;

    static {
        HashMap hashMap = new HashMap();
        a = hashMap;
        hashMap.put("int[]", Integer.class);
        a.put("long[]", Long.class);
        a.put("double[]", Double.class);
        a.put("float[]", Float.class);
        a.put("bool[]", Boolean.class);
        a.put("char[]", Character.class);
        a.put("byte[]", Byte.class);
        a.put("void[]", Void.class);
        a.put("short[]", Short.class);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0137 A[Catch: all -> 0x0282, Exception -> 0x028b, TryCatch #1 {all -> 0x0282, blocks: (B:22:0x00de, B:24:0x00e4, B:26:0x00ee, B:28:0x0100, B:29:0x012b, B:31:0x0137, B:34:0x017a, B:36:0x0193, B:38:0x01a6, B:40:0x01ae, B:42:0x01b6, B:44:0x01be, B:45:0x01cf, B:47:0x01d7, B:48:0x01e4, B:50:0x01ec, B:51:0x01f9, B:53:0x0201, B:54:0x020b, B:56:0x0211, B:58:0x021f, B:59:0x0223, B:61:0x022d, B:62:0x023b, B:64:0x0245, B:65:0x0255, B:67:0x025f, B:68:0x0267, B:71:0x0274, B:73:0x027a, B:76:0x027e), top: B:21:0x00de }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0176  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static <T> T a(java.lang.Class<T> r19, org.json.JSONObject r20) throws com.startapp.common.parser.JSONStreamException {
        /*
            Method dump skipped, instructions count: 668
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.b.a(java.lang.Class, org.json.JSONObject):java.lang.Object");
    }

    public static Object a(Object obj, Class<?> cls) {
        return obj.getClass().equals(cls) ? obj : cls.equals(Integer.class) ? obj.getClass().equals(Double.class) ? Integer.valueOf(((Double) obj).intValue()) : obj.getClass().equals(Long.class) ? Integer.valueOf(((Long) obj).intValue()) : obj : (cls.equals(Long.class) && obj.getClass().equals(Integer.class)) ? Long.valueOf(((Integer) obj).longValue()) : obj;
    }

    public static <T> Object a(JSONObject jSONObject, Class<T> cls, Field field) throws JSONStreamException, JSONException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalArgumentException, NoSuchFieldException {
        if (cls != null) {
            JSONArray jSONArray = jSONObject.getJSONArray(d.a(field));
            int length = jSONArray.length();
            Object newInstance = Array.newInstance((Class<?>) cls, length);
            for (int i = 0; i < length; i++) {
                Array.set(newInstance, i, a(cls, jSONArray.getJSONObject(i)));
            }
            return (Object[]) newInstance;
        }
        JSONArray jSONArray2 = jSONObject.getJSONArray(d.a(field));
        int length2 = jSONArray2.length();
        Class cls2 = a.get(field.getType().getSimpleName());
        Object newInstance2 = Array.newInstance((Class<?>) cls2.getField("TYPE").get(null), length2);
        for (int i2 = 0; i2 < length2; i2++) {
            String string = jSONArray2.getString(i2);
            Constructor<T> constructor = cls2.getConstructor(cls2.equals(Character.class) ? Character.TYPE : String.class);
            Array.set(newInstance2, i2, cls2.equals(Character.class) ? constructor.newInstance(Character.valueOf(string.charAt(0))) : constructor.newInstance(string));
        }
        return newInstance2;
    }

    public static Object a(JSONObject jSONObject, Field field, Object obj, Class<?> cls) throws JSONException {
        return obj.getClass().equals(cls) ? obj : obj.getClass().equals(String.class) ? cls.equals(Integer.TYPE) ? Integer.valueOf(jSONObject.getInt(d.a(field))) : obj : cls.equals(Integer.TYPE) ? Integer.valueOf(((Number) obj).intValue()) : cls.equals(Float.TYPE) ? Float.valueOf(((Number) obj).floatValue()) : cls.equals(Long.TYPE) ? Long.valueOf(((Number) obj).longValue()) : cls.equals(Double.TYPE) ? Double.valueOf(((Number) obj).doubleValue()) : obj;
    }

    public static List a(Class cls, JSONArray jSONArray) throws JSONException, JSONStreamException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                arrayList.add(jSONArray.get(i));
            } else {
                arrayList.add(a(cls, optJSONObject));
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Map a(Class cls, Class cls2, Class cls3, JSONObject jSONObject, Iterator it) throws JSONException, JSONStreamException {
        HashMap hashMap = new HashMap();
        while (it.hasNext()) {
            Object next = it.next();
            Object cast = cls.equals(Integer.class) ? cls.cast(Integer.valueOf(Integer.parseInt((String) next))) : next;
            if (cls.isEnum()) {
                cast = Enum.valueOf(cls, cast.toString());
            }
            String str = (String) next;
            JSONObject optJSONObject = jSONObject.optJSONObject(str);
            if (optJSONObject == null) {
                JSONArray optJSONArray = jSONObject.optJSONArray(str);
                if (optJSONArray != null) {
                    hashMap.put(cast, b(cls3, optJSONArray));
                } else if (cls2.isEnum()) {
                    hashMap.put(cast, Enum.valueOf(cls2, (String) jSONObject.get(str)));
                } else {
                    hashMap.put(cast, jSONObject.get(str));
                }
            } else {
                hashMap.put(cast, a(cls2, optJSONObject));
            }
        }
        return hashMap;
    }

    public static <V> Set<V> b(Class<V> cls, JSONArray jSONArray) throws JSONException, JSONStreamException {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                hashSet.add(jSONArray.get(i));
            } else {
                hashSet.add(a(cls, optJSONObject));
            }
        }
        return hashSet;
    }
}
