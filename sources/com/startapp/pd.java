package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.JsonReader;
import android.util.MalformedJsonException;
import com.startapp.p5;
import com.startapp.sc;
import com.startapp.sdk.common.advertisingid.AdvertisingIdResolver;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.sdk.jobs.JobRequest;
import com.startapp.sdk.triggeredlinks.AppEventsMetadata;
import com.startapp.sdk.triggeredlinks.PeriodicAppEventMetadata;
import com.startapp.sdk.triggeredlinks.TriggeredLinksMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.jvm.internal.LongCompanionObject;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class pd {
    public final Context a;
    public final p5 b;
    public final Executor c;
    public final AdvertisingIdResolver f;
    public final k9<TriggeredLinksMetadata> g;
    public final Runnable h = new a();
    public final Handler d = new Handler(Looper.getMainLooper());
    public final Map<String, Long> e = new HashMap();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            pd.this.b();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Runnable {
        public final /* synthetic */ TriggeredLinksMetadata a;
        public final /* synthetic */ Map b;
        public final /* synthetic */ String c;

        public b(TriggeredLinksMetadata triggeredLinksMetadata, Map map, String str) {
            this.a = triggeredLinksMetadata;
            this.b = map;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                pd.this.b(this.a, this.b, this.c);
            } catch (Throwable th) {
                if (pd.this.a(2)) {
                    p7.a(pd.this.a, th);
                }
            }
        }
    }

    public pd(Context context, p5 p5Var, Executor executor, AdvertisingIdResolver advertisingIdResolver, k9<TriggeredLinksMetadata> k9Var) {
        this.a = context;
        this.b = p5Var;
        this.c = new w9(executor);
        this.f = advertisingIdResolver;
        this.g = k9Var;
    }

    public final TriggeredLinksMetadata a() {
        TriggeredLinksMetadata call = this.g.call();
        if (call == null || !call.e()) {
            return null;
        }
        return call;
    }

    public void a(long j) {
        if (j > 0) {
            this.d.postDelayed(this.h, j);
        } else {
            this.d.post(this.h);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x005f, code lost:
    
        r10 = "0";
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0061, code lost:
    
        switch(r9) {
            case 0: goto L87;
            case 1: goto L86;
            case 2: goto L85;
            default: goto L88;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0068, code lost:
    
        r8 = r11.f.a().b;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0074, code lost:
    
        if (r8.equals("0") == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0076, code lost:
    
        r8 = "00000000-0000-0000-0000-000000000000";
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0078, code lost:
    
        r0.appendQueryParameter(r7, r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0084, code lost:
    
        if (r11.f.a().d == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0086, code lost:
    
        r10 = "1";
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0088, code lost:
    
        r0.appendQueryParameter(r7, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x008c, code lost:
    
        r0.appendQueryParameter(r7, r11.a.getPackageName());
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x0064, code lost:
    
        r0.appendQueryParameter(r7, r8);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.net.URLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(com.startapp.sdk.triggeredlinks.TriggeredLinksMetadata r12, java.lang.String r13, java.lang.String r14, java.lang.String r15) throws java.io.IOException, org.json.JSONException {
        /*
            Method dump skipped, instructions count: 334
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.pd.a(com.startapp.sdk.triggeredlinks.TriggeredLinksMetadata, java.lang.String, java.lang.String, java.lang.String):void");
    }

    public void a(TriggeredLinksMetadata triggeredLinksMetadata, Map<String, String> map, String str) {
        this.c.execute(new b(triggeredLinksMetadata, map, str));
    }

    public void a(String str, int i) {
        p5.a edit = this.b.edit();
        long currentTimeMillis = System.currentTimeMillis() + (i * 1000);
        edit.a(str, (String) Long.valueOf(currentTimeMillis));
        edit.a.putLong(str, currentTimeMillis);
        edit.apply();
    }

    public final void a(String str, String str2, InputStream inputStream) throws IOException {
        try {
            Object b2 = d.b(new JsonReader(new InputStreamReader(inputStream)));
            if (b2 instanceof Map) {
                Object obj = ((Map) b2).get("throttleSec");
                if (obj instanceof Number) {
                    int intValue = ((Number) obj).intValue();
                    this.e.put(str2 + "-" + str, Long.valueOf(SystemClock.elapsedRealtime() + (intValue * 1000)));
                }
            }
        } catch (IOException e) {
            if (!(e instanceof MalformedJsonException)) {
                throw e;
            }
        }
    }

    public boolean a(int i) {
        TriggeredLinksMetadata a2 = a();
        return a2 != null && (a2.b() & i) == i;
    }

    public void b() {
        String b2;
        this.d.removeCallbacks(this.h);
        qc l = ComponentLocator.a(this.a).l();
        int i = 1;
        l.a(JobRequest.a((Class<? extends pc>[]) new Class[]{od.class}));
        TriggeredLinksMetadata a2 = a();
        AppEventsMetadata a3 = a2 != null ? a2.a() : null;
        Map<String, PeriodicAppEventMetadata> d = a3 != null ? a3.d() : null;
        if (d == null || d.size() < 1) {
            return;
        }
        p5.a edit = this.b.edit();
        long currentTimeMillis = System.currentTimeMillis();
        long j = Long.MAX_VALUE;
        for (Map.Entry<String, PeriodicAppEventMetadata> entry : d.entrySet()) {
            String key = entry.getKey();
            PeriodicAppEventMetadata value = entry.getValue();
            if (key != null && key.length() >= i && value != null && (b2 = value.b()) != null && b2.length() >= i) {
                int a4 = value.a();
                int i2 = a4 < 5 ? 5 : a4;
                long j2 = this.b.getLong(key, 0L);
                if (j2 > currentTimeMillis) {
                    edit.a(key, (String) Long.valueOf(j2));
                    edit.a.putLong(key, j2);
                    if (j > j2) {
                        j = j2;
                    }
                } else {
                    long j3 = (i2 * 1000) + currentTimeMillis;
                    edit.a(key, (String) Long.valueOf(j3));
                    edit.a.putLong(key, j3);
                    this.c.execute(new rd(this, a2, key, b2, i2));
                }
            }
            i = 1;
        }
        edit.apply();
        if (j != LongCompanionObject.MAX_VALUE) {
            long j4 = j - currentTimeMillis;
            if (j4 < 5000) {
                a(j4);
                return;
            }
            sc.a aVar = new sc.a(od.class);
            aVar.e = Long.valueOf(j4);
            aVar.b = JobRequest.Network.ANY;
            l.a(new sc(aVar));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x007d, code lost:
    
        if (r2.longValue() > android.os.SystemClock.elapsedRealtime()) goto L28;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(com.startapp.sdk.triggeredlinks.TriggeredLinksMetadata r9, java.util.Map<java.lang.String, java.lang.String> r10, java.lang.String r11) {
        /*
            r8 = this;
            java.util.Set r10 = r10.entrySet()
            java.util.Iterator r10 = r10.iterator()
        L8:
            boolean r0 = r10.hasNext()
            if (r0 == 0) goto L97
            java.lang.Object r0 = r10.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            if (r1 == 0) goto L97
            int r2 = r1.length()
            r3 = 1
            if (r2 >= r3) goto L2b
            goto L97
        L2b:
            if (r0 == 0) goto L97
            int r2 = r0.length()
            if (r2 >= r3) goto L34
            goto L97
        L34:
            java.util.List r2 = r9.d()
            if (r2 != 0) goto L3b
            goto L80
        L3b:
            java.util.Iterator r2 = r2.iterator()
        L3f:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L80
            java.lang.Object r4 = r2.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            java.lang.String r4 = java.lang.String.valueOf(r4)
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L3f
            java.util.Map<java.lang.String, java.lang.Long> r2 = r8.e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r1)
            java.lang.String r5 = "-"
            r4.append(r5)
            r4.append(r11)
            java.lang.String r4 = r4.toString()
            java.lang.Object r2 = r2.get(r4)
            java.lang.Long r2 = (java.lang.Long) r2
            if (r2 == 0) goto L80
            long r4 = r2.longValue()
            long r6 = android.os.SystemClock.elapsedRealtime()
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 <= 0) goto L80
            goto L81
        L80:
            r3 = 0
        L81:
            if (r3 == 0) goto L84
            return
        L84:
            r8.a(r9, r11, r1, r0)     // Catch: java.lang.Throwable -> L88
            goto L8
        L88:
            r0 = move-exception
            r1 = 4
            boolean r1 = r8.a(r1)
            if (r1 == 0) goto L8
            android.content.Context r1 = r8.a
            com.startapp.p7.a(r1, r0)
            goto L8
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.pd.b(com.startapp.sdk.triggeredlinks.TriggeredLinksMetadata, java.util.Map, java.lang.String):void");
    }
}
