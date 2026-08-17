package com.startapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompat;
import android.util.Pair;
import io.sentry.marshaller.json.JsonMarshaller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONTokener;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class s7 extends f9 {
    public static final String[] c = {"rowid", JsonMarshaller.TIMESTAMP, "sdkVersion", "category", "appActivity", "value", "details", "detailsJson", "dParam", NotificationCompat.CATEGORY_SERVICE, "tag"};
    public final List<k9<Void>> d;

    public s7(Context context, String str) {
        super(context, str, null, 1);
        this.d = new LinkedList();
    }

    public static int a(SQLiteDatabase sQLiteDatabase, long j) {
        try {
            Cursor query = sQLiteDatabase.query("events", new String[]{"attempt"}, "rowid = ?", new String[]{String.valueOf(j)}, null, null, null);
            if (!query.moveToFirst()) {
                throw new IllegalStateException();
            }
            int i = query.getInt(0);
            a(query);
            return i;
        } catch (Throwable th) {
            a(null);
            throw th;
        }
    }

    public static void a(long j, long j2) {
        if (j <= 0) {
            throw new IllegalArgumentException();
        }
        if (j2 <= 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void a(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    public static p7 b(Cursor cursor) {
        long j = cursor.getLong(0);
        long j2 = cursor.getLong(1);
        a(j, j2);
        String string = cursor.getString(2);
        q7 a = q7.a(cursor.getString(3));
        String string2 = cursor.getString(4);
        String string3 = cursor.getString(5);
        String string4 = cursor.getString(6);
        String string5 = cursor.getString(7);
        String string6 = cursor.getString(8);
        boolean z = cursor.getInt(9) == 1;
        String string7 = cursor.getString(10);
        if (string == null || string.trim().length() < 1) {
            throw new IllegalArgumentException();
        }
        if (a == null) {
            throw new IllegalArgumentException();
        }
        Object obj = null;
        if (string5 != null) {
            try {
                obj = new JSONTokener(string5).nextValue();
            } catch (JSONException unused) {
            }
        }
        p7 p7Var = new p7(a, j);
        p7Var.h = Long.valueOf(j2);
        p7Var.c = string;
        p7Var.i = string2;
        p7Var.d = string3;
        p7Var.e = string4;
        p7Var.f = obj;
        p7Var.g = string6;
        p7Var.j = z;
        p7Var.k = string7;
        return p7Var;
    }

    public void a(i9<p7, Void> i9Var, int i, int i2) {
        String str = "attempt < " + i + " AND validTill >= " + System.currentTimeMillis() + " AND sendSuccess = 0  AND send <= sendFailure";
        Cursor cursor = null;
        try {
            cursor = a().query("events", c, str, null, null, null, "priority DESC, timestamp ASC", String.valueOf(Math.max(1, i2)));
            while (cursor.moveToNext()) {
                i9Var.a(b(cursor));
            }
        } finally {
            a(cursor);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.lang.String[], java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v4 */
    public boolean a(p7 p7Var, r7 r7Var) {
        long j;
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        ?? r14;
        String str7;
        String str8;
        SQLiteDatabase a = a();
        a.beginTransaction();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long j2 = currentTimeMillis + r7Var.e;
            Long l = p7Var.h;
            if (l != null) {
                j = l.longValue();
            } else {
                p7Var.h = Long.valueOf(currentTimeMillis);
                j = currentTimeMillis;
            }
            Object obj = p7Var.f;
            String obj2 = obj != null ? obj.toString() : null;
            String str9 = p7Var.c;
            if (str9 == null) {
                str9 = "4.9.1";
            }
            String str10 = str9;
            List<t7> list = r7Var.g;
            int size = list.size();
            String str11 = NotificationCompat.CATEGORY_SERVICE;
            String str12 = "dParam";
            String str13 = "detailsJson";
            String str14 = "details";
            String str15 = "value";
            long j3 = j;
            String str16 = "sdkVersion";
            if (size > 0) {
                HashMap hashMap = new HashMap();
                hashMap.put("sdkVersion", str10);
                String str17 = str10;
                hashMap.put("category", p7Var.a.o);
                hashMap.put("appActivity", p7Var.i);
                hashMap.put("value", p7Var.d);
                hashMap.put("details", p7Var.e);
                hashMap.put("detailsJson", obj2);
                hashMap.put("dParam", p7Var.g);
                hashMap.put(NotificationCompat.CATEGORY_SERVICE, p7Var.j ? "1" : "0");
                Iterator<t7> it = list.iterator();
                while (it.hasNext()) {
                    HashMap hashMap2 = hashMap;
                    String str18 = str12;
                    String str19 = str11;
                    String str20 = str13;
                    String str21 = str17;
                    String str22 = obj2;
                    String str23 = str14;
                    String str24 = str15;
                    String str25 = str16;
                    if (a(p7Var, it.next(), a, hashMap2, currentTimeMillis)) {
                        a.endTransaction();
                        return false;
                    }
                    str16 = str25;
                    str14 = str23;
                    str15 = str24;
                    hashMap = hashMap2;
                    str12 = str18;
                    str11 = str19;
                    obj2 = str22;
                    str17 = str21;
                    str13 = str20;
                }
                str = str12;
                str2 = str11;
                str3 = obj2;
                str4 = str13;
                str5 = str14;
                str7 = str17;
                str8 = str16;
                str6 = str15;
                r14 = 0;
            } else {
                str = "dParam";
                str2 = NotificationCompat.CATEGORY_SERVICE;
                str3 = obj2;
                str4 = "detailsJson";
                str5 = "details";
                str6 = "value";
                r14 = 0;
                str7 = str10;
                str8 = "sdkVersion";
            }
            a.delete("events", "validTill < " + currentTimeMillis, r14);
            ContentValues contentValues = new ContentValues();
            contentValues.put(JsonMarshaller.TIMESTAMP, Long.valueOf(j3));
            contentValues.put("validTill", Long.valueOf(j2));
            contentValues.put(str8, str7);
            contentValues.put("category", p7Var.a.o);
            contentValues.put("appActivity", p7Var.i);
            contentValues.put(str6, p7Var.d);
            contentValues.put(str5, p7Var.e);
            contentValues.put(str4, str3);
            contentValues.put(str, p7Var.g);
            contentValues.put(str2, Integer.valueOf(p7Var.j ? 1 : 0));
            contentValues.put("tag", p7Var.k);
            contentValues.put("priority", Integer.valueOf(r7Var.c));
            a.insertOrThrow("events", r14, contentValues);
            a.setTransactionSuccessful();
            a.endTransaction();
            b();
            return true;
        } catch (Throwable th) {
            a.endTransaction();
            throw th;
        }
    }

    public final boolean a(p7 p7Var, t7 t7Var, SQLiteDatabase sQLiteDatabase, Map<String, String> map, long j) {
        if (t7Var.a.size() > 0 && !t7Var.a.contains(p7Var.d)) {
            return false;
        }
        if (t7Var.b.size() > 0 && t7Var.b.contains(p7Var.d)) {
            return false;
        }
        if (t7Var.c.size() > 0 && !t7Var.c.contains(p7Var.i)) {
            return false;
        }
        if (t7Var.d.size() > 0 && t7Var.d.contains(p7Var.i)) {
            return false;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (map.containsKey("sdkVersion")) {
            linkedHashMap.put("sdkVersion", map.get("sdkVersion"));
        }
        if (map.containsKey("category")) {
            linkedHashMap.put("category", map.get("category"));
        }
        for (String str : t7Var.e) {
            if (map.containsKey(str)) {
                linkedHashMap.put(str, map.get(str));
            }
        }
        int size = linkedHashMap.size();
        if (size < 1) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList(size);
        String str2 = "";
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            sb.append(str2);
            sb.append((String) entry.getKey());
            if (entry.getValue() == null) {
                sb.append(" IS NULL");
            } else {
                sb.append(" = ?");
                arrayList.add(entry.getValue());
            }
            str2 = " AND ";
        }
        Pair pair = new Pair(sb.toString(), arrayList.toArray(new String[0]));
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query("events", new String[]{"sendSuccess"}, (String) pair.first, (String[]) pair.second, null, null, "sendSuccess DESC");
            if (cursor.moveToFirst()) {
                long j2 = cursor.getLong(0);
                if (j2 <= 0) {
                    return true;
                }
                long j3 = j - j2;
                long j4 = t7Var.f;
                if (j4 > 0 && j3 < j4) {
                    return true;
                }
            }
            return false;
        } finally {
            a(cursor);
        }
    }

    public final synchronized void b() {
        Iterator<k9<Void>> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().call();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS events ( timestamp INTEGER NOT NULL, validTill INTEGER NOT NULL, sdkVersion TEXT NOT NULL, category TEXT NOT NULL, appActivity TEXT, value TEXT, details TEXT, detailsJson TEXT, dParam TEXT, service INTEGER NOT NULL DEFAULT 0, tag TEXT, priority INTEGER NOT NULL, attempt INTEGER NOT NULL DEFAULT 0, send INTEGER NOT NULL DEFAULT 0, sendFailure INTEGER NOT NULL DEFAULT 0, sendSuccess INTEGER NOT NULL DEFAULT 0, CHECK (attempt >= 0), CHECK (send >= 0), CHECK (sendFailure >= 0), CHECK (sendSuccess >= 0));");
    }
}
