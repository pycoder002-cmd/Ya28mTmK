package com.startapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.liulishuo.filedownloader.model.ConnectionModel;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class k8 extends f9 {
    public static final String[] c = {ConnectionModel.ID};

    public k8(Context context, String str) {
        super(context, str, null, 1);
    }

    public static long a(SQLiteDatabase sQLiteDatabase, String str, String str2) {
        Cursor query = sQLiteDatabase.query(str, c, "value=?", new String[]{str2}, null, null, null);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    return query.getLong(0);
                }
            } finally {
                query.close();
            }
        }
        return query != null ? -1L : -1L;
    }

    public void a(String str, String str2, long j, long j2) {
        SQLiteDatabase a = a();
        a.beginTransaction();
        try {
            ContentValues contentValues = new ContentValues();
            long a2 = a(a, "requests", str);
            if (a2 < 0) {
                contentValues.clear();
                contentValues.put("value", str);
                a2 = a.insert("requests", null, contentValues);
            }
            long a3 = a(a, "statuses", str2);
            if (a3 < 0) {
                contentValues.clear();
                contentValues.put("value", str2);
                a3 = a.insert("statuses", null, contentValues);
            }
            contentValues.clear();
            contentValues.put("requestId", Long.valueOf(a2));
            contentValues.put("statusId", Long.valueOf(a3));
            contentValues.put("timeMillis", Long.valueOf(j));
            contentValues.put("durationNanos", Long.valueOf(j2));
            a.insert("traces", null, contentValues);
            a.setTransactionSuccessful();
        } finally {
            a.endTransaction();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS requests (id INTEGER PRIMARY KEY ASC AUTOINCREMENT NOT NULL,value TEXT NOT NULL UNIQUE,CHECK (value = TRIM(value) AND LENGTH(value) > 0));");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS statuses (id INTEGER PRIMARY KEY ASC AUTOINCREMENT NOT NULL,value TEXT NOT NULL UNIQUE,CHECK (value = TRIM(value) AND LENGTH(value) > 0));");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS traces (requestId INTEGER NOT NULL,statusId INTEGER NOT NULL,timeMillis INTEGER NOT NULL,durationNanos INTEGER NOT NULL,FOREIGN KEY (requestId)REFERENCES requests (requestId)ON UPDATE CASCADE ON DELETE CASCADE,FOREIGN KEY (statusId)REFERENCES statuses (statusId)ON UPDATE CASCADE ON DELETE CASCADE,CHECK (timeMillis > 0),CHECK (durationNanos > 0));");
    }
}
