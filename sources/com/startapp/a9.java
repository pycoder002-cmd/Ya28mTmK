package com.startapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.jacoco.agent.rt.internal_b0d6a23.asm.Opcodes;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class a9 {
    public static final Map<String, Bitmap> a = new ConcurrentHashMap();

    public static Bitmap a(Context context, String str) {
        Bitmap b = b(context, str);
        return b == null ? b(context, str) : b;
    }

    public static boolean a(Context context, String str, String str2) {
        if (!str.endsWith(str2)) {
            str = str + str2;
        }
        if (!a.containsKey(str)) {
            if (!new File(context.getFilesDir().getPath() + "/" + str).exists()) {
                return false;
            }
        }
        return true;
    }

    public static Bitmap b(Context context, String str) {
        FileInputStream fileInputStream;
        Map<String, Bitmap> map = a;
        Bitmap bitmap = map.get(str);
        if (bitmap != null) {
            return bitmap;
        }
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(context.getFilesDir().getPath() + "/" + str);
        } catch (Exception unused) {
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream);
            decodeStream.setDensity(context.getResources() != null ? context.getResources().getDisplayMetrics().densityDpi : Opcodes.IF_ICMPNE);
            map.put(str, decodeStream);
            aa.a(fileInputStream);
            return decodeStream;
        } catch (Exception unused2) {
            aa.a(fileInputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            aa.a(fileInputStream2);
            throw th;
        }
    }
}
