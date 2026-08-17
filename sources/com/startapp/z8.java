package com.startapp;

import android.content.Context;
import android.graphics.Bitmap;
import java.io.FileOutputStream;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class z8 implements Runnable {
    public final /* synthetic */ String a;
    public final /* synthetic */ String b;
    public final /* synthetic */ Bitmap c;
    public final /* synthetic */ Context d;

    public z8(String str, String str2, Bitmap bitmap, Context context) {
        this.a = str;
        this.b = str2;
        this.c = bitmap;
        this.d = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        Throwable th;
        FileOutputStream fileOutputStream;
        a9.a.put(this.a + this.b, this.c);
        try {
            fileOutputStream = new FileOutputStream(this.d.getFilesDir().getPath() + "/" + this.a + this.b);
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        try {
            this.c.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Throwable th3) {
            th = th3;
            try {
                p7.a(this.d, th);
            } finally {
                aa.a(fileOutputStream);
            }
        }
    }
}
