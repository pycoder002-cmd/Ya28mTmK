package com.startapp;

import android.app.Activity;
import android.content.Context;
import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ja {
    public final String a;
    public final String b;
    public final String[] c;
    public final Class[] d;
    public final Object[] e;
    public final String[] f;
    public transient SoftReference<ka> g;
    public final transient Map<String, SoftReference<Map<String, Object>>> h = new ConcurrentHashMap();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements Iterator<Object> {
        public final Object a;
        public final int b;
        public int c;

        public a(Object obj, int i) {
            this.a = obj;
            this.b = i;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.c < this.b;
        }

        @Override // java.util.Iterator
        public Object next() {
            Object obj = this.a;
            int i = this.c;
            this.c = i + 1;
            return Array.get(obj, i);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class b implements Iterator<Object> {
        public static final b a = new b();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Object next() {
            return null;
        }
    }

    public ja(String str, String str2, String[] strArr, Class[] clsArr, Object[] objArr, String[] strArr2) {
        this.a = str;
        this.b = str2;
        this.c = strArr;
        this.d = clsArr;
        this.e = objArr;
        this.f = strArr2;
    }

    public static Object a(Object obj) {
        if (obj instanceof Short) {
            return Integer.valueOf(((Short) obj).intValue());
        }
        if ((obj instanceof Integer) || (obj instanceof Long)) {
            return obj;
        }
        if (obj instanceof Float) {
            return Double.valueOf(((Float) obj).doubleValue());
        }
        if ((obj instanceof Double) || (obj instanceof Boolean) || (obj instanceof String)) {
            return obj;
        }
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    public static Method a(Class<?> cls, String str, Class[] clsArr) throws NoSuchMethodException {
        NoSuchMethodException noSuchMethodException = null;
        while (cls != null) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                if (noSuchMethodException == null) {
                    noSuchMethodException = e;
                }
                cls = cls.getSuperclass();
            }
        }
        throw noSuchMethodException;
    }

    public final ka a(Context context) {
        Object obj;
        SoftReference<ka> softReference = this.g;
        ka kaVar = softReference != null ? softReference.get() : null;
        if (kaVar != null) {
            return kaVar;
        }
        Object systemService = context.getSystemService(this.a);
        if (systemService == null) {
            try {
                Object obj2 = ((LinkedHashMap) a(Context.class, new String[]{this.a})).get(this.a);
                if (obj2 instanceof Method) {
                    obj = ((Method) obj2).invoke(context, new Object[0]);
                } else if (obj2 instanceof Field) {
                    obj = ((Field) obj2).get(context);
                } else {
                    boolean z = obj2 instanceof Throwable;
                }
                systemService = obj;
            } catch (Throwable unused) {
            }
        }
        if (systemService == null) {
            throw new RuntimeException(String.valueOf(1));
        }
        try {
            Method a2 = a(systemService.getClass(), this.b, this.d);
            if (!a2.isAccessible()) {
                try {
                    a2.setAccessible(true);
                } catch (SecurityException e) {
                    throw new RuntimeException(String.valueOf(4), e);
                }
            }
            ka kaVar2 = new ka(systemService, a2);
            this.g = new SoftReference<>(kaVar2);
            return kaVar2;
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException(String.valueOf(3), e2);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0019, code lost:
    
        if (r5.isAccessible() != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x001b, code lost:
    
        r5.setAccessible(true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001e, code lost:
    
        r0.put(r4, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x002c, code lost:
    
        throw r5;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.Map<java.lang.String, java.lang.Object> a(java.lang.Class<?> r11, java.lang.String[] r12) {
        /*
            r10 = this;
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            int r1 = r12.length
            r2 = 0
            r3 = 0
        L8:
            if (r3 >= r1) goto L9a
            r4 = r12[r3]
            r5 = 0
            r6 = r11
        Le:
            r7 = 1
            if (r6 == 0) goto L2c
            java.lang.reflect.Field r5 = r6.getDeclaredField(r4)     // Catch: java.lang.NoSuchFieldException -> L23 java.lang.SecurityException -> L2d
            boolean r6 = r5.isAccessible()     // Catch: java.lang.SecurityException -> L2d java.lang.NoSuchFieldException -> L2f
            if (r6 != 0) goto L1e
            r5.setAccessible(r7)     // Catch: java.lang.SecurityException -> L2d java.lang.NoSuchFieldException -> L2f
        L1e:
            r0.put(r4, r5)     // Catch: java.lang.SecurityException -> L2d java.lang.NoSuchFieldException -> L2f
            goto L96
        L23:
            r8 = move-exception
            if (r5 != 0) goto L27
            r5 = r8
        L27:
            java.lang.Class r6 = r6.getSuperclass()     // Catch: java.lang.SecurityException -> L2d java.lang.NoSuchFieldException -> L2f
            goto Le
        L2c:
            throw r5     // Catch: java.lang.SecurityException -> L2d java.lang.NoSuchFieldException -> L2f
        L2d:
            r5 = move-exception
            goto L30
        L2f:
            r5 = move-exception
        L30:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            char r8 = r4.charAt(r2)
            char r8 = java.lang.Character.toUpperCase(r8)
            r6.append(r8)
            java.lang.String r8 = r4.substring(r7)
            r6.append(r8)
            java.lang.String r6 = r6.toString()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
            r8.<init>()     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
            java.lang.String r9 = "get"
            r8.append(r9)     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
            r8.append(r6)     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
            java.lang.String r8 = r8.toString()     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
            java.lang.Class[] r9 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
            java.lang.reflect.Method r8 = a(r11, r8, r9)     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
            boolean r9 = r8.isAccessible()     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
            if (r9 != 0) goto L6b
            r8.setAccessible(r7)     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
        L6b:
            r0.put(r4, r8)     // Catch: java.lang.NoSuchMethodException -> L6f java.lang.Throwable -> L93
            goto L96
        L6f:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L93
            r8.<init>()     // Catch: java.lang.Throwable -> L93
            java.lang.String r9 = "is"
            r8.append(r9)     // Catch: java.lang.Throwable -> L93
            r8.append(r6)     // Catch: java.lang.Throwable -> L93
            java.lang.String r6 = r8.toString()     // Catch: java.lang.Throwable -> L93
            java.lang.Class[] r8 = new java.lang.Class[r2]     // Catch: java.lang.Throwable -> L93
            java.lang.reflect.Method r6 = a(r11, r6, r8)     // Catch: java.lang.Throwable -> L93
            boolean r8 = r6.isAccessible()     // Catch: java.lang.Throwable -> L93
            if (r8 != 0) goto L8f
            r6.setAccessible(r7)     // Catch: java.lang.Throwable -> L93
        L8f:
            r0.put(r4, r6)     // Catch: java.lang.Throwable -> L93
            goto L96
        L93:
            r0.put(r4, r5)
        L96:
            int r3 = r3 + 1
            goto L8
        L9a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.ja.a(java.lang.Class, java.lang.String[]):java.util.Map");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.List] */
    public JSONArray a(Context context, int[] iArr, Integer num) {
        Comparator comparator;
        try {
            ka a2 = a(context);
            Object invoke = a2.b.invoke(a2.a, this.e);
            Iterator it = invoke == null ? b.a : invoke instanceof Collection ? ((Collection) invoke).iterator() : invoke.getClass().isArray() ? new a(invoke, Array.getLength(invoke)) : Collections.singleton(invoke).iterator();
            ?? arrayList = new ArrayList();
            while (true) {
                comparator = null;
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                if (next != null) {
                    JSONObject jSONObject = new JSONObject();
                    Class<?> cls = next.getClass();
                    SoftReference<Map<String, Object>> softReference = this.h.get(cls.getName());
                    Map<String, Object> map = softReference != null ? softReference.get() : null;
                    if (map == null) {
                        map = a(cls, this.f);
                        this.h.put(cls.getName(), new SoftReference<>(map));
                    }
                    if (map.isEmpty()) {
                        try {
                            jSONObject.put("", next.toString());
                        } catch (Throwable unused) {
                        }
                    } else {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            try {
                                if (value instanceof Field) {
                                    jSONObject.put(key, a(((Field) value).get(next)));
                                } else if (value instanceof Method) {
                                    jSONObject.put(key, a(((Method) value).invoke(next, new Object[0])));
                                } else {
                                    boolean z = value instanceof Throwable;
                                }
                            } catch (Throwable unused2) {
                            }
                        }
                    }
                    arrayList.add(jSONObject);
                }
            }
            if (iArr != null && iArr.length > 0) {
                int length = this.f.length;
                for (int i : iArr) {
                    if (i != 0 && Math.abs(i) <= length) {
                        Comparator p9Var = new p9(this.f[Math.abs(i) - 1]);
                        if (i < 0) {
                            p9Var = Collections.reverseOrder(p9Var);
                        }
                        comparator = comparator == null ? p9Var : new d9(comparator, p9Var);
                    }
                }
                if (comparator != null) {
                    Collections.sort(arrayList, comparator);
                }
            }
            if (num != null && num.intValue() > 0) {
                arrayList = arrayList.subList(0, Math.min(num.intValue(), arrayList.size()));
            }
            JSONArray jSONArray = new JSONArray();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                jSONArray.put((JSONObject) it2.next());
            }
            return jSONArray;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(String.valueOf(5), e);
        } catch (InvocationTargetException e2) {
            throw new RuntimeException(String.valueOf(5), e2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ja.class != obj.getClass()) {
            return false;
        }
        ja jaVar = (ja) obj;
        return aa.a(this.a, jaVar.a) && aa.a(this.b, jaVar.b) && Arrays.equals(this.c, jaVar.c) && Arrays.equals(this.e, jaVar.e) && Arrays.equals(this.f, jaVar.f);
    }

    public int hashCode() {
        Object[] objArr = {this.a, this.b, this.c, this.e, this.f};
        Map<Activity, Integer> map = aa.a;
        return Arrays.deepHashCode(objArr);
    }

    public String toString() {
        return super.toString();
    }
}
