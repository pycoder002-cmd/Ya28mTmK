package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class zzagp {
    static HashMap<String, String> aVk;
    private static Object aVl;
    private static boolean aVm;
    public static final Uri CONTENT_URI = Uri.parse("content://com.google.android.gsf.gservices");
    public static final Uri aVg = Uri.parse("content://com.google.android.gsf.gservices/prefix");
    public static final Pattern aVh = Pattern.compile("^(1|true|t|on|yes|y)$", 2);
    public static final Pattern aVi = Pattern.compile("^(0|false|f|off|no|n)$", 2);
    private static final AtomicBoolean aVj = new AtomicBoolean();
    static String[] aVn = new String[0];

    public static long getLong(ContentResolver contentResolver, String str, long j) {
        String string = getString(contentResolver, str);
        if (string == null) {
            return j;
        }
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException unused) {
            return j;
        }
    }

    @Deprecated
    public static String getString(ContentResolver contentResolver, String str) {
        return zza(contentResolver, str, (String) null);
    }

    public static String zza(ContentResolver contentResolver, String str, String str2) {
        synchronized (zzagp.class) {
            zza(contentResolver);
            Object obj = aVl;
            if (aVk.containsKey(str)) {
                String str3 = aVk.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
                return str3;
            }
            for (String str4 : aVn) {
                if (str.startsWith(str4)) {
                    if (!aVm || aVk.isEmpty()) {
                        zzc(contentResolver, aVn);
                        if (aVk.containsKey(str)) {
                            String str5 = aVk.get(str);
                            if (str5 == null) {
                                str5 = str2;
                            }
                            return str5;
                        }
                    }
                    return str2;
                }
            }
            Cursor query = contentResolver.query(CONTENT_URI, null, null, new String[]{str}, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(1);
                        if (string != null && string.equals(str2)) {
                            string = str2;
                        }
                        zza(obj, str, string);
                        if (string != null) {
                            str2 = string;
                        }
                        return str2;
                    }
                } finally {
                    if (query != null) {
                        query.close();
                    }
                }
            }
            zza(obj, str, (String) null);
            if (query != null) {
                query.close();
            }
            return str2;
        }
    }

    public static Map<String, String> zza(ContentResolver contentResolver, String... strArr) {
        Cursor query = contentResolver.query(aVg, null, null, strArr, null);
        TreeMap treeMap = new TreeMap();
        if (query == null) {
            return treeMap;
        }
        while (query.moveToNext()) {
            try {
                treeMap.put(query.getString(0), query.getString(1));
            } finally {
                query.close();
            }
        }
        return treeMap;
    }

    private static void zza(ContentResolver contentResolver) {
        if (aVk == null) {
            aVj.set(false);
            aVk = new HashMap<>();
            aVl = new Object();
            aVm = false;
            contentResolver.registerContentObserver(CONTENT_URI, true, new ContentObserver(new Handler(Looper.getMainLooper())) { // from class: com.google.android.gms.internal.zzagp.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    zzagp.aVj.set(true);
                }
            });
            return;
        }
        if (aVj.getAndSet(false)) {
            aVk.clear();
            aVl = new Object();
            aVm = false;
        }
    }

    private static void zza(Object obj, String str, String str2) {
        synchronized (zzagp.class) {
            if (obj == aVl) {
                aVk.put(str, str2);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001c, code lost:
    
        if (r3.length != 0) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void zzb(android.content.ContentResolver r2, java.lang.String... r3) {
        /*
            int r0 = r3.length
            if (r0 != 0) goto L4
            return
        L4:
            java.lang.Class<com.google.android.gms.internal.zzagp> r0 = com.google.android.gms.internal.zzagp.class
            monitor-enter(r0)
            zza(r2)     // Catch: java.lang.Throwable -> L27
            java.lang.String[] r3 = zzk(r3)     // Catch: java.lang.Throwable -> L27
            boolean r1 = com.google.android.gms.internal.zzagp.aVm     // Catch: java.lang.Throwable -> L27
            if (r1 == 0) goto L22
            java.util.HashMap<java.lang.String, java.lang.String> r1 = com.google.android.gms.internal.zzagp.aVk     // Catch: java.lang.Throwable -> L27
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> L27
            if (r1 == 0) goto L1b
            goto L22
        L1b:
            int r1 = r3.length     // Catch: java.lang.Throwable -> L27
            if (r1 == 0) goto L25
        L1e:
            zzc(r2, r3)     // Catch: java.lang.Throwable -> L27
            goto L25
        L22:
            java.lang.String[] r3 = com.google.android.gms.internal.zzagp.aVn     // Catch: java.lang.Throwable -> L27
            goto L1e
        L25:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L27
            return
        L27:
            r2 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L27
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzagp.zzb(android.content.ContentResolver, java.lang.String[]):void");
    }

    private static void zzc(ContentResolver contentResolver, String[] strArr) {
        aVk.putAll(zza(contentResolver, strArr));
        aVm = true;
    }

    private static String[] zzk(String[] strArr) {
        HashSet hashSet = new HashSet((((aVn.length + strArr.length) * 4) / 3) + 1);
        hashSet.addAll(Arrays.asList(aVn));
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (hashSet.add(str)) {
                arrayList.add(str);
            }
        }
        if (arrayList.isEmpty()) {
            return new String[0];
        }
        aVn = (String[]) hashSet.toArray(new String[hashSet.size()]);
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }
}
