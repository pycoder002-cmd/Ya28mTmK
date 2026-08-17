package com.startapp;

import android.content.Context;
import android.util.Log;
import com.startapp.sdk.components.ComponentLocator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class h9 {
    public static final String a = "h9";

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements Runnable {
        public final /* synthetic */ Context a;
        public final /* synthetic */ String b;
        public final /* synthetic */ Serializable c;

        public a(Context context, String str, Serializable serializable) {
            this.a = context;
            this.b = str;
            this.c = serializable;
        }

        @Override // java.lang.Runnable
        public void run() {
            h9.b(this.a, null, this.b, this.c);
        }
    }

    public static <T> T a(Context context, String str, Class<T> cls) {
        try {
            return (T) a(c(context, null), str);
        } catch (Error e) {
            Log.e(a, "Failed to read from disk: " + e.getLocalizedMessage());
            return null;
        } catch (Exception e2) {
            Log.e(a, "Failed to read from disk: " + e2.getLocalizedMessage());
            return null;
        }
    }

    public static Object a(Context context, String str, String str2) {
        if (str2 == null) {
            return null;
        }
        try {
            return a(b(context, str), str2);
        } catch (Error e) {
            Log.e(a, "Failed to read from disk: " + e.getLocalizedMessage());
            return null;
        } catch (Exception e2) {
            Log.e(a, "Failed to read from disk: " + e2.getLocalizedMessage());
            return null;
        }
    }

    public static <T> T a(String str, String str2) throws IOException, ClassNotFoundException {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        File file2 = new File(file, str2);
        if (!file2.exists()) {
            return null;
        }
        Log.i(a, "Reading file: " + file2.getPath());
        FileInputStream fileInputStream = new FileInputStream(file2);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        T t = (T) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();
        return t;
    }

    public static void a(Context context, String str) {
        if (str == null) {
            return;
        }
        a(new File(c(context, str)));
        a(new File(b(context, str)));
    }

    public static void a(Context context, String str, Serializable serializable) {
        ComponentLocator.a(context).h().execute(new a(context, str, serializable));
    }

    public static void a(Context context, String str, String str2, Serializable serializable) {
        if (str2 == null) {
            return;
        }
        try {
            a(b(context, str), str2, serializable);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    public static void a(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                a(file2);
            }
        }
        file.delete();
    }

    public static void a(String str, String str2, Serializable serializable) throws IOException {
        File file = new File(str);
        if (file.exists() || file.mkdirs()) {
            File file2 = new File(file, str2);
            Log.i(a, "Writing file: " + file2.getPath());
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
            fileOutputStream.close();
        }
    }

    public static String b(Context context, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getCacheDir().toString());
        if (str != null) {
            str2 = File.separator + str;
        } else {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }

    public static void b(Context context, String str, String str2, Serializable serializable) {
        if (str2 == null) {
            return;
        }
        try {
            a(c(context, null), str2, serializable);
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    public static String c(Context context, String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        sb.append(context.getFilesDir().toString());
        if (str != null) {
            str2 = File.separator + str;
        } else {
            str2 = "";
        }
        sb.append(str2);
        return sb.toString();
    }

    public static List d(Context context, String str) {
        File file;
        String[] list;
        ArrayList arrayList = new ArrayList();
        try {
            file = new File(b(context, str));
        } catch (Exception e) {
            Log.e(a, "Failed to read from disk: " + e.getLocalizedMessage());
        }
        if (!file.exists() || !file.isDirectory() || (list = file.list()) == null) {
            return null;
        }
        for (String str2 : list) {
            File file2 = new File(file, str2);
            Log.i(a, "Reading file: " + file2.getPath());
            FileInputStream fileInputStream = new FileInputStream(file2);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            arrayList.add(readObject);
        }
        return arrayList;
    }
}
