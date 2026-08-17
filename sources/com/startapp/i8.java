package com.startapp;

import android.content.Context;
import android.content.SharedPreferences;
import com.startapp.sdk.adsbase.remoteconfig.NetworkDiagnosticConfig;
import java.util.concurrent.Executor;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class i8 {
    public final Context a;
    public final SharedPreferences b;
    public final k8 c;
    public final Executor d;
    public final k9<NetworkDiagnosticConfig> e;
    public final Runnable f = new a();
    public final u7 g = new b();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x0129 A[Catch: all -> 0x0134, TRY_LEAVE, TryCatch #2 {all -> 0x0134, blocks: (B:26:0x0123, B:28:0x0129), top: B:25:0x0123 }] */
        /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 316
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.i8.a.run():void");
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements u7 {

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class a implements Runnable {
            public final /* synthetic */ p7 a;

            public a(p7 p7Var) {
                this.a = p7Var;
            }

            @Override // java.lang.Runnable
            public void run() {
                c cVar;
                i8 i8Var = i8.this;
                p7 p7Var = this.a;
                i8Var.getClass();
                Long l = p7Var.h;
                String str = p7Var.k;
                if (str != null) {
                    String[] split = str.split(",");
                    if (split.length == 2 && split[0] != null && split[1] != null) {
                        try {
                            cVar = new c(Long.parseLong(split[0]), Long.parseLong(split[1]));
                        } catch (NumberFormatException unused) {
                        }
                        if (l != null || cVar == null) {
                        }
                        try {
                            i8Var.c.a().delete("traces", "requestId = ? AND statusId = ? AND timeMillis < ?", new String[]{String.valueOf(cVar.a), String.valueOf(cVar.b), String.valueOf(l.longValue())});
                            return;
                        } catch (Throwable th) {
                            if (i8Var.a(4)) {
                                p7.a(i8Var.a, th);
                                return;
                            }
                            return;
                        }
                    }
                }
                cVar = null;
                if (l != null) {
                }
            }
        }

        public b() {
        }

        @Override // com.startapp.u7
        public void a(p7 p7Var, int i) {
            if (i == 1) {
                i8.this.d.execute(new a(p7Var));
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class c {
        public final long a;
        public final long b;

        public c(long j, long j2) {
            this.a = j;
            this.b = j2;
        }
    }

    public i8(Context context, SharedPreferences sharedPreferences, k8 k8Var, Executor executor, k9<NetworkDiagnosticConfig> k9Var) {
        this.a = context;
        this.b = sharedPreferences;
        this.c = k8Var;
        this.d = executor;
        this.e = k9Var;
    }

    public final NetworkDiagnosticConfig a() {
        NetworkDiagnosticConfig call = this.e.call();
        if (call == null || !call.e()) {
            return null;
        }
        return call;
    }

    public boolean a(int i) {
        NetworkDiagnosticConfig a2 = a();
        return a2 != null && (a2.a() & i) == i;
    }
}
