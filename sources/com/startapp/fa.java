package com.startapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.startapp.p5;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class fa extends WebViewClient {
    public final Context a;
    public final p5 b;
    public final Executor c;
    public final Handler d;
    public String e;
    public String f;
    public long i;
    public final long j;
    public boolean k;
    public Boolean l;
    public String m;
    public Runnable n;
    public long r;
    public boolean g = false;
    public boolean h = false;
    public boolean o = false;
    public boolean p = false;
    public final LinkedHashMap<String, Float> q = new LinkedHashMap<>();
    public final Runnable s = new a();
    public final Runnable t = new b();
    public final Runnable u = new c();
    public final Runnable v = new d();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            fa faVar = fa.this;
            faVar.c.execute(faVar.t);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            fa faVar = fa.this;
            if (faVar.g) {
                return;
            }
            try {
                p7 p7Var = new p7(q7.c);
                StringBuilder sb = new StringBuilder();
                sb.append("Failed smart redirect hop info: ");
                sb.append(faVar.p ? "Page Finished" : "Timeout");
                p7Var.d = sb.toString();
                p7Var.f = faVar.b();
                p7Var.g = faVar.f;
                p7Var.a(faVar.a);
            } catch (Throwable th) {
                p7.a(faVar.a, th);
            }
            try {
                faVar.o = true;
                g5.b(faVar.a);
                faVar.a();
                if (faVar.k && MetaData.h.N()) {
                    g5.a(faVar.a, faVar.e, faVar.f);
                } else {
                    g5.b(faVar.a, faVar.e, faVar.f);
                }
                Runnable runnable = faVar.n;
                if (runnable != null) {
                    runnable.run();
                }
            } catch (Throwable th2) {
                p7.a(faVar.a, th2);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            fa faVar = fa.this;
            faVar.c.execute(faVar.v);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements Runnable {
        public d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            fa faVar = fa.this;
            if (faVar.o || faVar.g) {
                return;
            }
            try {
                faVar.g = true;
                g5.b(faVar.a);
                if (faVar.k && MetaData.h.N()) {
                    g5.a(faVar.a, faVar.e, faVar.f);
                } else {
                    g5.b(faVar.a, faVar.e, faVar.f);
                }
                Runnable runnable = faVar.n;
                if (runnable != null) {
                    runnable.run();
                }
            } catch (Throwable th) {
                p7.a(faVar.a, th);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e implements Runnable {
        public final /* synthetic */ String a;

        public e(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            fa faVar = fa.this;
            String str = this.a;
            if (!faVar.h) {
                faVar.r = System.currentTimeMillis();
                faVar.q.put(str, Float.valueOf(-1.0f));
                faVar.d.postDelayed(faVar.s, faVar.i);
                faVar.h = true;
            }
            faVar.p = false;
            faVar.a();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class f implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ String c;

        public f(String str, boolean z, String str2) {
            this.a = str;
            this.b = z;
            this.c = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            fa faVar = fa.this;
            String str = this.a;
            boolean z = this.b;
            String str2 = this.c;
            faVar.getClass();
            try {
                long currentTimeMillis = System.currentTimeMillis();
                Float valueOf = Float.valueOf(((float) (currentTimeMillis - faVar.r)) / 1000.0f);
                faVar.r = currentTimeMillis;
                faVar.q.put(faVar.e, valueOf);
                faVar.q.put(str, Float.valueOf(-1.0f));
                faVar.e = str;
                if (faVar.o) {
                    return;
                }
                boolean z2 = true;
                faVar.g = true;
                g5.b(faVar.a);
                faVar.a();
                Context context = faVar.a;
                if (z) {
                    str = str2;
                }
                g5.b(context, str, (String) null);
                String str3 = faVar.m;
                if (str3 == null || str3.equals("") || faVar.e.toLowerCase().contains(faVar.m.toLowerCase())) {
                    if (!MetaData.h.analytics.i() || !faVar.b.getBoolean("firstSucceededSmartRedirect", true)) {
                        z2 = false;
                    }
                    Boolean bool = faVar.l;
                    float h = bool == null ? MetaData.h.analytics.h() : bool.booleanValue() ? 100.0f : 0.0f;
                    if (z2 || Math.random() * 100.0d < h) {
                        p7 p7Var = new p7(q7.j);
                        p7Var.f = faVar.b();
                        p7Var.g = faVar.f;
                        p7Var.a(faVar.a);
                        p5.a edit = faVar.b.edit();
                        edit.a("firstSucceededSmartRedirect", (String) Boolean.FALSE);
                        edit.a.putBoolean("firstSucceededSmartRedirect", false);
                        edit.apply();
                    }
                } else {
                    p7 p7Var2 = new p7(q7.c);
                    p7Var2.d = "Wrong package reached";
                    p7Var2.e = "Expected: " + faVar.m + ", Link: " + faVar.e;
                    p7Var2.g = faVar.f;
                    p7Var2.a(faVar.a);
                }
                Runnable runnable = faVar.n;
                if (runnable != null) {
                    runnable.run();
                }
            } catch (Throwable th) {
                p7.a(faVar.a, th);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class g implements Runnable {
        public final /* synthetic */ String a;

        public g(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            fa faVar = fa.this;
            String str = this.a;
            if (faVar.g || faVar.o || !faVar.e.equals(str) || g5.b(str)) {
                return;
            }
            if (str.startsWith("http://") || str.startsWith("https://")) {
                faVar.p = true;
                faVar.a(str);
                synchronized (faVar.d) {
                    faVar.d.removeCallbacks(faVar.u);
                    faVar.d.postDelayed(faVar.u, faVar.j);
                }
            }
        }
    }

    public fa(Context context, p5 p5Var, Executor executor, Handler handler, long j, long j2, boolean z, Boolean bool, String str, String str2, String str3, Runnable runnable) {
        this.a = context;
        this.b = p5Var;
        this.c = new w9(executor);
        this.d = handler;
        this.i = j;
        this.j = j2;
        this.k = z;
        this.l = bool;
        this.e = str;
        this.m = str2;
        this.f = str3;
        this.n = runnable;
    }

    public void a() {
        synchronized (this.d) {
            this.d.removeCallbacks(this.u);
        }
    }

    public final void a(String str) {
        Float f2 = this.q.get(str);
        if (f2 == null || f2.floatValue() < 0.0f) {
            this.q.put(str, Float.valueOf(((float) (System.currentTimeMillis() - this.r)) / 1000.0f));
        }
    }

    public JSONArray b() {
        JSONArray jSONArray = new JSONArray();
        for (Map.Entry<String, Float> entry : this.q.entrySet()) {
            String key = entry.getKey();
            Float value = entry.getValue();
            JSONObject jSONObject = new JSONObject();
            try {
                a(key);
                jSONObject.put("time", String.valueOf(value));
                jSONObject.put(FileDownloadModel.URL, key);
                jSONArray.put(jSONObject);
            } catch (JSONException unused) {
            }
        }
        return jSONArray;
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        this.c.execute(new g(str));
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.c.execute(new e(str));
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        a();
        if (str2 != null && !g5.b(str2) && g5.c(str2)) {
            p7 p7Var = new p7(q7.c);
            p7Var.d = "Failed smart redirect: " + i;
            p7Var.e = str2;
            p7Var.g = this.f;
            p7Var.a(this.a);
        }
        super.onReceivedError(webView, i, str, str2);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (webView == null || str == null || aa.b(webView.getContext(), str)) {
            return true;
        }
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        boolean b2 = g5.b(lowerCase);
        boolean startsWith = lowerCase.startsWith("intent://");
        if (!b2 && !startsWith) {
            return false;
        }
        this.c.execute(new f(str, startsWith, webView.getUrl()));
        return true;
    }
}
