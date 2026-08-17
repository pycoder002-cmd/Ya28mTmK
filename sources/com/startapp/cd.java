package com.startapp;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.startapp.sdk.adsbase.remoteconfig.RcdMetadata;
import com.startapp.sdk.adsbase.remoteconfig.RcdTargets;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class cd {
    public static final String[] a = {"getSupportFragmentManager", "getFragmentManager"};
    public final Context b;
    public final Executor c;
    public final Executor d;
    public final k9<RcdMetadata> e;
    public final Application.ActivityLifecycleCallbacks f;
    public String i;
    public final Map<String, Integer> g = new HashMap();
    public final Map<String, List<WeakReference<Activity>>> h = new HashMap();
    public final Runnable j = new a();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                cd.this.d();
            } catch (Throwable th) {
                p7.a(cd.this.b, th);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b extends e9 {
        public final /* synthetic */ Context a;

        public b(Context context) {
            this.a = context;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPostResumed(Activity activity) {
            try {
                cd.this.a(activity);
            } catch (Throwable th) {
                p7.a(this.a, th);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements Runnable {
        public final /* synthetic */ Activity a;

        public c(Activity activity) {
            this.a = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                cd.this.b(this.a);
            } catch (Throwable th) {
                p7.a(cd.this.b, th);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements Runnable {
        public final /* synthetic */ Activity a;
        public final /* synthetic */ View b;

        public d(Activity activity, View view) {
            this.a = activity;
            this.b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                cd cdVar = cd.this;
                Activity activity = this.a;
                View view = this.b;
                RcdTargets a = cdVar.a();
                if (a == null) {
                    return;
                }
                try {
                    if (cdVar.a(a, activity)) {
                        return;
                    }
                } catch (Throwable th) {
                    p7.a(cdVar.b, th);
                }
                try {
                    cdVar.a(a, view);
                } catch (Throwable th2) {
                    p7.a(cdVar.b, th2);
                }
                cdVar.d.execute(cdVar.j);
            } catch (Throwable th3) {
                p7.a(cd.this.b, th3);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e implements Runnable {
        public final /* synthetic */ StackTraceElement[] a;
        public final /* synthetic */ int b;

        public e(StackTraceElement[] stackTraceElementArr, int i) {
            this.a = stackTraceElementArr;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                cd cdVar = cd.this;
                StackTraceElement[] stackTraceElementArr = this.a;
                int i = this.b;
                RcdTargets a = cdVar.a();
                if (a == null) {
                    return;
                }
                for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                    cdVar.a(a, stackTraceElement.getClassName(), i);
                }
                cdVar.d.execute(cdVar.j);
            } catch (Throwable th) {
                p7.a(cd.this.b, th);
            }
        }
    }

    public cd(Context context, Executor executor, k9<RcdMetadata> k9Var) {
        this.b = context;
        this.c = executor;
        this.d = new w9(executor);
        this.e = k9Var;
        if (Build.VERSION.SDK_INT >= 14) {
            this.f = new b(context);
        } else {
            this.f = null;
        }
    }

    public static boolean a(String str) {
        return str.startsWith("android") || str.startsWith("java.");
    }

    public final RcdTargets a() {
        RcdMetadata call = this.e.call();
        if (call == null || !call.c()) {
            call = null;
        }
        if (call != null) {
            return call.b();
        }
        return null;
    }

    public void a(int i) {
        try {
            if (b()) {
                return;
            }
            this.c.execute(new e(Thread.currentThread().getStackTrace(), i));
        } catch (Throwable th) {
            p7.a(this.b, th);
        }
    }

    public void a(Activity activity) {
        Window window;
        View decorView;
        if (b()) {
            return;
        }
        String name = activity.getClass().getName();
        Map<Activity, Integer> map = aa.a;
        if (name.startsWith("com.startapp.")) {
            return;
        }
        List<WeakReference<Activity>> list = this.h.get(name);
        if (list == null) {
            list = new ArrayList<>(2);
            this.h.put(name, list);
            this.c.execute(new c(activity));
        }
        boolean z = false;
        Iterator<WeakReference<Activity>> it = list.iterator();
        while (it.hasNext()) {
            WeakReference<Activity> next = it.next();
            if (next.get() == null) {
                it.remove();
            } else if (next.get() == activity) {
                z = true;
            }
        }
        if (z || (window = activity.getWindow()) == null || (decorView = window.getDecorView()) == null) {
            return;
        }
        list.add(new WeakReference<>(activity));
        this.c.execute(new d(activity, decorView));
    }

    public final void a(RcdTargets rcdTargets, View view) {
        if (view == null) {
            return;
        }
        a(rcdTargets, view.getClass().getName(), 4);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                a(rcdTargets, viewGroup.getChildAt(i));
            }
        }
    }

    public final void a(RcdTargets rcdTargets, Object obj, int i, int i2) {
        for (Class<?> cls = obj.getClass(); cls != null && !a(cls.getName()); cls = cls.getSuperclass()) {
            for (Field field : cls.getDeclaredFields()) {
                if (i != 0) {
                    a(rcdTargets, field.getType().getName(), i);
                }
                try {
                    field.setAccessible(true);
                    if (field.get(obj) != null && i2 != 0) {
                        a(rcdTargets, field.getType().getName(), i2);
                    }
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0065 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[LOOP:0: B:9:0x001a->B:28:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(com.startapp.sdk.adsbase.remoteconfig.RcdTargets r5, java.lang.String r6, int r7) {
        /*
            r4 = this;
            boolean r0 = a(r6)
            if (r0 == 0) goto L7
            return
        L7:
            java.util.Map<android.app.Activity, java.lang.Integer> r0 = com.startapp.aa.a
            java.lang.String r0 = "com.startapp."
            boolean r0 = r6.startsWith(r0)
            if (r0 == 0) goto L12
            return
        L12:
            java.util.Collection r5 = r5.a(r7)
            java.util.Iterator r5 = r5.iterator()
        L1a:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L65
            java.lang.Object r0 = r5.next()
            java.lang.String r0 = (java.lang.String) r0
            int r1 = r0.length()
            r2 = 1
            if (r1 <= 0) goto L62
            boolean r1 = r6.startsWith(r0)
            if (r1 == 0) goto L62
            int r1 = r0.length()
            int r1 = r1 - r2
            char r1 = r0.charAt(r1)
            r3 = 46
            if (r1 != r3) goto L44
            r4.a(r0, r7)
            goto L62
        L44:
            int r1 = r6.length()
            int r3 = r0.length()
            if (r1 <= r3) goto L5e
            int r1 = r0.length()
            char r1 = r6.charAt(r1)
            r2 = 36
            if (r1 != r2) goto L62
            r4.a(r0, r7)
            goto L62
        L5e:
            r4.a(r0, r7)
            goto L63
        L62:
            r2 = 0
        L63:
            if (r2 == 0) goto L1a
        L65:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.cd.a(com.startapp.sdk.adsbase.remoteconfig.RcdTargets, java.lang.String, int):void");
    }

    public final <T extends ComponentInfo> void a(RcdTargets rcdTargets, String str, T[] tArr) {
        if (tArr == null) {
            return;
        }
        for (T t : tArr) {
            if (t != null) {
                String str2 = ((ComponentInfo) t).name;
                if (str2.startsWith(".")) {
                    a(rcdTargets, str + str2, 2);
                } else {
                    a(rcdTargets, str2, 2);
                }
            }
        }
    }

    public final void a(String str, int i) {
        synchronized (this.g) {
            Integer num = this.g.get(str);
            if (num == null) {
                num = 0;
            }
            this.g.put(str, Integer.valueOf(i | num.intValue()));
        }
    }

    public final boolean a(RcdTargets rcdTargets, Activity activity) {
        Collection<String> a2 = rcdTargets.a(8);
        String name = activity.getClass().getName();
        if (!a2.contains(name)) {
            return false;
        }
        a(name, 8);
        return true;
    }

    public void b(Activity activity) {
        RcdTargets a2 = a();
        if (a2 == null) {
            return;
        }
        try {
            if (a(a2, activity)) {
                return;
            }
        } catch (Throwable th) {
            p7.a(this.b, th);
        }
        try {
            a(a2, activity, 16, 32);
        } catch (Throwable th2) {
            p7.a(this.b, th2);
        }
        for (String str : a) {
            try {
                Object invoke = activity.getClass().getMethod(str, new Class[0]).invoke(activity, new Object[0]);
                if (invoke != null) {
                    Object invoke2 = invoke.getClass().getMethod("getFragments", new Class[0]).invoke(invoke, new Object[0]);
                    if (invoke2 instanceof Collection) {
                        for (Object obj : (Collection) invoke2) {
                            if (obj != null) {
                                a(a2, obj, 64, 128);
                            }
                        }
                    }
                }
            } catch (NoSuchMethodException unused) {
            } catch (Throwable th3) {
                p7.a(this.b, th3);
            }
        }
        this.d.execute(this.j);
    }

    public final boolean b() {
        RcdMetadata call = this.e.call();
        if (call == null || !call.c()) {
            call = null;
        }
        return Boolean.valueOf(call == null || Math.random() >= call.a()).booleanValue();
    }

    public void c() {
        RcdTargets a2 = a();
        if (a2 == null) {
            return;
        }
        for (String str : a2.a(1)) {
            try {
                Class.forName(str, false, getClass().getClassLoader());
                a(str, 1);
            } catch (ClassNotFoundException unused) {
            } catch (Throwable th) {
                p7.a(this.b, th);
            }
        }
        try {
            String packageName = this.b.getPackageName();
            PackageInfo packageInfo = this.b.getPackageManager().getPackageInfo(packageName, 15);
            if (packageInfo != null) {
                a(a2, packageName, packageInfo.activities);
                a(a2, packageName, packageInfo.receivers);
                a(a2, packageName, packageInfo.services);
                a(a2, packageName, packageInfo.providers);
            }
        } catch (Throwable th2) {
            p7.a(this.b, th2);
        }
        this.d.execute(this.j);
    }

    public void d() {
        HashMap hashMap;
        RcdTargets a2 = a();
        if (a2 == null) {
            return;
        }
        synchronized (this.g) {
            hashMap = new HashMap(this.g);
        }
        String a3 = a2.a(hashMap);
        if (a3.equals(this.i)) {
            return;
        }
        this.i = a3;
        p7 p7Var = new p7(q7.b);
        p7Var.d = "RCD.results";
        p7Var.e = a3;
        p7Var.a(this.b);
    }
}
