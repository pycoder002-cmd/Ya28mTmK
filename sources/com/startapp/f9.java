package com.startapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class f9 extends SQLiteOpenHelper {
    public volatile SQLiteDatabase a;
    public final Object b;

    public f9(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, (SQLiteDatabase.CursorFactory) null, i);
        this.b = new Object();
    }

    public SQLiteDatabase a() {
        SQLiteDatabase sQLiteDatabase = this.a;
        if (sQLiteDatabase == null) {
            synchronized (this.b) {
                sQLiteDatabase = this.a;
                if (sQLiteDatabase == null) {
                    sQLiteDatabase = getWritableDatabase();
                    this.a = sQLiteDatabase;
                }
            }
        }
        return sQLiteDatabase;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
