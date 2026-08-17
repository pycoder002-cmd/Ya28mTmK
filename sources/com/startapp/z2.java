package com.startapp;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import com.startapp.sdk.ads.list3d.List3DActivity;
import com.startapp.sdk.ads.list3d.List3DView;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class z2 {
    public i3 d;
    public int e = 0;
    public Hashtable<String, Bitmap> b = new Hashtable<>();
    public Set<String> c = new HashSet();
    public ConcurrentLinkedQueue<b> f = new ConcurrentLinkedQueue<>();
    public HashMap<String, r5> a = new HashMap<>();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends AsyncTask<Void, Void, Bitmap> {
        public int a;
        public String b;
        public String c;

        public a(int i, String str, String str2) {
            this.a = -1;
            this.a = i;
            this.b = str;
            this.c = str2;
        }

        @Override // android.os.AsyncTask
        public Bitmap doInBackground(Void[] voidArr) {
            return c9.b(this.c);
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(Bitmap bitmap) {
            List<e3> list;
            Bitmap bitmap2 = bitmap;
            z2 z2Var = z2.this;
            z2Var.e--;
            if (bitmap2 != null) {
                z2Var.b.put(this.b, bitmap2);
                i3 i3Var = z2.this.d;
                if (i3Var != null) {
                    int i = this.a;
                    List3DActivity list3DActivity = (List3DActivity) i3Var;
                    List3DView list3DView = list3DActivity.a;
                    View childAt = list3DView.getChildAt(i - list3DView.i);
                    if (childAt != null) {
                        f3 f3Var = (f3) childAt.getTag();
                        g3 a = h3.a.a(list3DActivity.h);
                        if (a != null && (list = a.b) != null && i < list.size()) {
                            e3 e3Var = a.b.get(i);
                            f3Var.b.setImageBitmap(a.a.a(i, e3Var.a, e3Var.i));
                            f3Var.b.requestLayout();
                            f3Var.a(e3Var.n != null);
                        }
                    }
                }
                z2 z2Var2 = z2.this;
                if (z2Var2.f.isEmpty()) {
                    return;
                }
                b poll = z2Var2.f.poll();
                new a(poll.a, poll.b, poll.c).execute(new Void[0]);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b {
        public int a;
        public String b;
        public String c;

        public b(z2 z2Var, int i, String str, String str2) {
            this.a = i;
            this.b = str;
            this.c = str2;
        }
    }

    public Bitmap a(int i, String str, String str2) {
        Bitmap bitmap = this.b.get(str);
        if (bitmap != null) {
            return bitmap;
        }
        if (this.c.contains(str)) {
            return null;
        }
        this.c.add(str);
        int i2 = this.e;
        if (i2 >= 15) {
            this.f.add(new b(this, i, str, str2));
            return null;
        }
        this.e = i2 + 1;
        new a(i, str, str2).execute(new Void[0]);
        return null;
    }

    public final String a(String[] strArr, String str) {
        if (strArr == null) {
            return null;
        }
        return TextUtils.join("^", strArr) + str;
    }
}
