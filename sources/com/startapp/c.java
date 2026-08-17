package com.startapp;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class c {
    public static Object a(Object obj) {
        Class<?> cls = obj.getClass();
        return cls.equals(Boolean.class) || cls.equals(Integer.class) || cls.equals(Character.class) || cls.equals(Byte.class) || cls.equals(Short.class) || cls.equals(Double.class) || cls.equals(Long.class) || cls.equals(Float.class) || cls.equals(String.class) ? obj : b(obj);
    }

    public static <T> T a(String str, Class<T> cls) {
        try {
            return (T) b.a(cls, new JSONObject(str));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static JSONObject b(Object obj) {
        if (obj == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Class<?> cls = obj.getClass(); cls != null && !Object.class.equals(cls); cls = cls.getSuperclass()) {
            arrayList.addAll(Arrays.asList(cls.getDeclaredFields()));
        }
        JSONObject jSONObject = new JSONObject();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field field = (Field) it.next();
            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isTransient(modifiers)) {
                try {
                    field.setAccessible(true);
                    if (field.get(obj) != null) {
                        String a = d.a(field);
                        if (d.b(field)) {
                            jSONObject.put(a, b(field.get(obj)));
                        } else if (List.class.isAssignableFrom(field.getType())) {
                            JSONArray jSONArray = new JSONArray();
                            Iterator it2 = ((List) field.get(obj)).iterator();
                            while (it2.hasNext()) {
                                jSONArray.put(a(it2.next()));
                            }
                            jSONObject.put(a, jSONArray);
                        } else if (Set.class.isAssignableFrom(field.getType())) {
                            JSONArray jSONArray2 = new JSONArray();
                            Iterator it3 = ((Set) field.get(obj)).iterator();
                            while (it3.hasNext()) {
                                jSONArray2.put(a(it3.next()));
                            }
                            jSONObject.put(a, jSONArray2);
                        } else if (Map.class.isAssignableFrom(field.getType())) {
                            JSONObject jSONObject2 = new JSONObject();
                            for (Map.Entry entry : ((Map) field.get(obj)).entrySet()) {
                                jSONObject2.put(entry.getKey().toString(), a(entry.getValue()));
                            }
                            jSONObject.put(a, jSONObject2);
                        } else {
                            jSONObject.put(a, field.get(obj));
                        }
                    }
                } catch (IllegalAccessException | IllegalArgumentException | JSONException unused) {
                }
            }
        }
        return jSONObject;
    }
}
