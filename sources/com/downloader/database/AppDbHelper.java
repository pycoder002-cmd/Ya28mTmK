package com.downloader.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.liulishuo.filedownloader.model.FileDownloadModel;

/* loaded from: classes.dex */
public class AppDbHelper implements DbHelper {
    public static final String TABLE_NAME = "prdownloader";
    private final SQLiteDatabase db;

    public AppDbHelper(Context context) {
        this.db = new DatabaseOpenHelper(context).getWritableDatabase();
    }

    @Override // com.downloader.database.DbHelper
    public void clear() {
        try {
            this.db.delete(TABLE_NAME, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.downloader.database.DbHelper
    public DownloadModel find(int i) {
        DownloadModel downloadModel;
        Cursor cursor;
        DownloadModel downloadModel2 = null;
        downloadModel2 = null;
        downloadModel2 = null;
        Cursor cursor2 = null;
        try {
            try {
                cursor = this.db.rawQuery("SELECT * FROM prdownloader WHERE id = " + i, null);
                if (cursor != null) {
                    try {
                        try {
                            if (cursor.moveToFirst()) {
                                downloadModel = new DownloadModel();
                                try {
                                    downloadModel.setId(i);
                                    downloadModel.setUrl(cursor.getString(cursor.getColumnIndex(FileDownloadModel.URL)));
                                    downloadModel.setETag(cursor.getString(cursor.getColumnIndex(FileDownloadModel.ETAG)));
                                    downloadModel.setDirPath(cursor.getString(cursor.getColumnIndex("dir_path")));
                                    downloadModel.setFileName(cursor.getString(cursor.getColumnIndex("file_name")));
                                    downloadModel.setTotalBytes(cursor.getLong(cursor.getColumnIndex("total_bytes")));
                                    downloadModel.setDownloadedBytes(cursor.getLong(cursor.getColumnIndex("downloaded_bytes")));
                                    downloadModel.setLastModifiedAt(cursor.getLong(cursor.getColumnIndex("last_modified_at")));
                                    downloadModel2 = downloadModel;
                                } catch (Exception e) {
                                    e = e;
                                    cursor2 = cursor;
                                    e.printStackTrace();
                                    if (cursor2 != null) {
                                        cursor2.close();
                                    }
                                    downloadModel2 = downloadModel;
                                    return downloadModel2;
                                }
                            }
                        } catch (Exception e2) {
                            e = e2;
                            downloadModel = null;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = downloadModel2;
            }
        } catch (Exception e3) {
            e = e3;
            downloadModel = null;
        }
        return downloadModel2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0033, code lost:
    
        if (r1.moveToFirst() != false) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0035, code lost:
    
        r9 = new com.downloader.database.DownloadModel();
        r9.setId(r1.getInt(r1.getColumnIndex(com.liulishuo.filedownloader.model.ConnectionModel.ID)));
        r9.setUrl(r1.getString(r1.getColumnIndex(com.liulishuo.filedownloader.model.FileDownloadModel.URL)));
        r9.setETag(r1.getString(r1.getColumnIndex(com.liulishuo.filedownloader.model.FileDownloadModel.ETAG)));
        r9.setDirPath(r1.getString(r1.getColumnIndex("dir_path")));
        r9.setFileName(r1.getString(r1.getColumnIndex("file_name")));
        r9.setTotalBytes(r1.getLong(r1.getColumnIndex("total_bytes")));
        r9.setDownloadedBytes(r1.getLong(r1.getColumnIndex("downloaded_bytes")));
        r9.setLastModifiedAt(r1.getLong(r1.getColumnIndex("last_modified_at")));
        r0.add(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x00a9, code lost:
    
        if (r1.moveToNext() != false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x00bc, code lost:
    
        if (r1 == null) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x00ae, code lost:
    
        if (r1 != null) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x00c1, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x00be, code lost:
    
        r1.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00c5  */
    /* JADX WARN: Type inference failed for: r1v1, types: [long] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v5, types: [android.database.Cursor] */
    @Override // com.downloader.database.DbHelper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.downloader.database.DownloadModel> getUnwantedModels(int r9) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r9 = r9 * 24
            int r9 = r9 * 60
            int r9 = r9 * 60
            long r1 = (long) r9
            r3 = 1000(0x3e8, double:4.94E-321)
            long r1 = r1 * r3
            r9 = 0
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lb1 java.lang.Exception -> Lb5
            long r5 = r3 - r1
            android.database.sqlite.SQLiteDatabase r1 = r8.db     // Catch: java.lang.Throwable -> Lb1 java.lang.Exception -> Lb5
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb1 java.lang.Exception -> Lb5
            r2.<init>()     // Catch: java.lang.Throwable -> Lb1 java.lang.Exception -> Lb5
            java.lang.String r3 = "SELECT * FROM prdownloader WHERE last_modified_at <= "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lb1 java.lang.Exception -> Lb5
            r2.append(r5)     // Catch: java.lang.Throwable -> Lb1 java.lang.Exception -> Lb5
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lb1 java.lang.Exception -> Lb5
            android.database.Cursor r1 = r1.rawQuery(r2, r9)     // Catch: java.lang.Throwable -> Lb1 java.lang.Exception -> Lb5
            if (r1 == 0) goto Lae
            boolean r9 = r1.moveToFirst()     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            if (r9 == 0) goto Lae
        L35:
            com.downloader.database.DownloadModel r9 = new com.downloader.database.DownloadModel     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r9.<init>()     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = "id"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            int r2 = r1.getInt(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r9.setId(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = "url"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r9.setUrl(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = "etag"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r9.setETag(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = "dir_path"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r9.setDirPath(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = "file_name"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r9.setFileName(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = "total_bytes"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            long r2 = r1.getLong(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r9.setTotalBytes(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = "downloaded_bytes"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            long r2 = r1.getLong(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r9.setDownloadedBytes(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            java.lang.String r2 = "last_modified_at"
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            long r2 = r1.getLong(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r9.setLastModifiedAt(r2)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            r0.add(r9)     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            boolean r9 = r1.moveToNext()     // Catch: java.lang.Exception -> Lac java.lang.Throwable -> Lc2
            if (r9 != 0) goto L35
            goto Lae
        Lac:
            r9 = move-exception
            goto Lb9
        Lae:
            if (r1 == 0) goto Lc1
            goto Lbe
        Lb1:
            r0 = move-exception
            r1 = r9
            r9 = r0
            goto Lc3
        Lb5:
            r1 = move-exception
            r7 = r1
            r1 = r9
            r9 = r7
        Lb9:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> Lc2
            if (r1 == 0) goto Lc1
        Lbe:
            r1.close()
        Lc1:
            return r0
        Lc2:
            r9 = move-exception
        Lc3:
            if (r1 == 0) goto Lc8
            r1.close()
        Lc8:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.downloader.database.AppDbHelper.getUnwantedModels(int):java.util.List");
    }

    @Override // com.downloader.database.DbHelper
    public void insert(DownloadModel downloadModel) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ConnectionModel.ID, Integer.valueOf(downloadModel.getId()));
            contentValues.put(FileDownloadModel.URL, downloadModel.getUrl());
            contentValues.put(FileDownloadModel.ETAG, downloadModel.getETag());
            contentValues.put("dir_path", downloadModel.getDirPath());
            contentValues.put("file_name", downloadModel.getFileName());
            contentValues.put("total_bytes", Long.valueOf(downloadModel.getTotalBytes()));
            contentValues.put("downloaded_bytes", Long.valueOf(downloadModel.getDownloadedBytes()));
            contentValues.put("last_modified_at", Long.valueOf(downloadModel.getLastModifiedAt()));
            this.db.insert(TABLE_NAME, null, contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.downloader.database.DbHelper
    public void remove(int i) {
        try {
            this.db.execSQL("DELETE FROM prdownloader WHERE id = " + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.downloader.database.DbHelper
    public void update(DownloadModel downloadModel) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FileDownloadModel.URL, downloadModel.getUrl());
            contentValues.put(FileDownloadModel.ETAG, downloadModel.getETag());
            contentValues.put("dir_path", downloadModel.getDirPath());
            contentValues.put("file_name", downloadModel.getFileName());
            contentValues.put("total_bytes", Long.valueOf(downloadModel.getTotalBytes()));
            contentValues.put("downloaded_bytes", Long.valueOf(downloadModel.getDownloadedBytes()));
            contentValues.put("last_modified_at", Long.valueOf(downloadModel.getLastModifiedAt()));
            this.db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{String.valueOf(downloadModel.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.downloader.database.DbHelper
    public void updateProgress(int i, long j, long j2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("downloaded_bytes", Long.valueOf(j));
            contentValues.put("last_modified_at", Long.valueOf(j2));
            this.db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{String.valueOf(i)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
