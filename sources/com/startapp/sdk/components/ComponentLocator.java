package com.startapp.sdk.components;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import com.startapp.ab;
import com.startapp.ba;
import com.startapp.bb;
import com.startapp.bc;
import com.startapp.ca;
import com.startapp.cb;
import com.startapp.cd;
import com.startapp.db;
import com.startapp.dc;
import com.startapp.eb;
import com.startapp.ed;
import com.startapp.fb;
import com.startapp.fc;
import com.startapp.ga;
import com.startapp.gb;
import com.startapp.hb;
import com.startapp.i5;
import com.startapp.i8;
import com.startapp.ib;
import com.startapp.j7;
import com.startapp.jb;
import com.startapp.k7;
import com.startapp.k8;
import com.startapp.kb;
import com.startapp.l7;
import com.startapp.l9;
import com.startapp.lb;
import com.startapp.m7;
import com.startapp.m9;
import com.startapp.mb;
import com.startapp.n7;
import com.startapp.n9;
import com.startapp.nb;
import com.startapp.nc;
import com.startapp.o9;
import com.startapp.ob;
import com.startapp.p5;
import com.startapp.pb;
import com.startapp.pd;
import com.startapp.qb;
import com.startapp.qc;
import com.startapp.rb;
import com.startapp.rc;
import com.startapp.s7;
import com.startapp.sdk.common.advertisingid.AdvertisingIdResolver;
import com.startapp.sdk.jobs.SchedulerService;
import com.startapp.tb;
import com.startapp.uc;
import com.startapp.v7;
import com.startapp.vb;
import com.startapp.vc;
import com.startapp.x8;
import com.startapp.xb;
import com.startapp.xc;
import com.startapp.y8;
import com.startapp.zb;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class ComponentLocator {
    public static final bb<ComponentLocator, Context> a = new k();
    public final ab<l9> A;
    public final ab<Executor> B;
    public final ab<Executor> C;
    public final ab<Executor> D;
    public final ab<Executor> E;
    public final ab<l9> F;
    public final ab<p5> G;
    public final ab<p5> H;
    public final ab<l7> I;
    public final m7 b;
    public final ab<o9> c;
    public final ab<ca> d;
    public final ab<ba> e;
    public final ab<xb> f;
    public final ab<vb> g;
    public final ab<dc> h;
    public final ab<fc> i;
    public final ab<AdvertisingIdResolver> j;
    public final ab<ed> k;
    public final ab<k7> l;
    public final ab<nc> m;
    public final ab<x8> n;
    public final ab<pd> o;
    public final ab<i5> p;
    public final ab<n7> q;
    public final ab<bc> r;
    public final ab<ga> s;
    public final ab<v7> t;
    public final ab<tb> u;
    public final ab<zb> v;
    public final ab<j7> w;
    public final ab<cd> x;
    public final ab<qc> y;
    public final ab<xc> z;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends ab<AdvertisingIdResolver> {
        public final /* synthetic */ Context b;

        public a(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public AdvertisingIdResolver a() {
            return new AdvertisingIdResolver(this.b, new d0("air"), new cb(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a0 extends ab<p5> {
        public final /* synthetic */ Context b;

        public a0(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public p5 a() {
            return new p5(this.b.getSharedPreferences("com.startapp.sdk.extras", 0), new qb(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b extends ab<ed> {
        public final /* synthetic */ Context b;

        public b(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public ed a() {
            return new ed(this.b, new db(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b0 extends ab<l7> {
        public final /* synthetic */ Context b;

        public b0(Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public l7 a() {
            return new l7(this.b, ComponentLocator.this.l());
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c extends ab<k7> {
        public final /* synthetic */ Context b;

        public c(Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public k7 a() {
            return new k7(this.b, ComponentLocator.this.d());
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class c0 implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            try {
                threadPoolExecutor.getQueue().put(runnable);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d extends ab<nc> {
        public final /* synthetic */ Context b;

        public d(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public nc a() {
            return new nc(new p5(this.b.getSharedPreferences("StartApp-54ff24db2aee60b9", 0), null));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class d0 implements ThreadFactory {
        public final AtomicInteger a = new AtomicInteger();
        public final /* synthetic */ String b;

        public d0(String str) {
            this.b = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "startapp-" + this.b + "-" + this.a.incrementAndGet());
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e extends ab<x8> {
        public e(ComponentLocator componentLocator) {
        }

        @Override // com.startapp.ab
        public x8 a() {
            return new x8();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e0 extends ab<ca> {
        public final /* synthetic */ Context b;

        public e0(Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public ca a() {
            return new ca(this.b, ComponentLocator.this.b);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class f extends ab<pd> {
        public final /* synthetic */ Context b;

        public f(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public pd a() {
            return new pd(this.b, new p5(this.b.getSharedPreferences("StartApp-fba1a5307d96ef31", 0), null), ComponentLocator.a(0, 1, "tlp", 5L), ComponentLocator.a(this.b).a(), new eb(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class f0 extends ab<ba> {
        public final /* synthetic */ Context b;

        public f0(Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public ba a() {
            return new ba(this.b, ComponentLocator.this.t());
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class g extends ab<i5> {
        public final /* synthetic */ Context b;

        public g(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public i5 a() {
            return new i5(new p5(this.b.getSharedPreferences("StartApp-790ba54ab8e69f2f", 0), null));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class g0 extends ab<l9> {
        public g0(ComponentLocator componentLocator) {
        }

        @Override // com.startapp.ab
        public l9 a() {
            return ComponentLocator.a("db");
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class h extends ab<n7> {
        public final /* synthetic */ Context b;
        public final /* synthetic */ ab c;

        public h(Context context, ab abVar) {
            this.b = context;
            this.c = abVar;
        }

        @Override // com.startapp.ab
        public n7 a() {
            return new n7(this.b, ComponentLocator.this.a(), ComponentLocator.this.m(), new i8(this.b, new p5(this.b.getSharedPreferences("StartApp-770c613f81fb5b52", 0), null), new k8(this.b, "StartApp-ac51a09f00e0f80c"), (Executor) this.c.b(), new fb(this)), new gb(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class h0 extends ab<xb> {
        public final /* synthetic */ Context b;

        public h0(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public xb a() {
            return new xb(this.b);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class i extends ab<bc> {
        public final /* synthetic */ Context b;

        public i(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public bc a() {
            return new bc(this.b, new p5(this.b.getSharedPreferences("StartApp-9b9bfdb86df82dad", 0), null), new hb(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class i0 extends ab<vb> {
        public final /* synthetic */ Context b;

        public i0(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public vb a() {
            return new vb(this.b);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class j extends ab<ga> {
        public final /* synthetic */ Context b;

        public j(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public ga a() {
            return new ga(this.b);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class j0 extends ab<dc> {
        public final /* synthetic */ Context b;

        public j0(Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public dc a() {
            return new dc(this.b, new p5(this.b.getSharedPreferences("StartApp-6d5362e8ecc8a910", 0), null), ComponentLocator.this.g());
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class k extends bb<ComponentLocator, Context> {
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class k0 extends ab<fc> {
        public final /* synthetic */ Context b;

        public k0(Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public fc a() {
            return new fc(this.b, ComponentLocator.this.g(), new p5(this.b.getSharedPreferences("StartApp-c5f5846c2a728b2a", 0), null), new rb(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class l extends ab<v7> {
        public final /* synthetic */ Context b;
        public final /* synthetic */ ab c;

        public l(Context context, ab abVar) {
            this.b = context;
            this.c = abVar;
        }

        @Override // com.startapp.ab
        public v7 a() {
            Executor a = ComponentLocator.a(0, 2, "info", 5L);
            return new v7(new s7(this.b, "StartApp-d6864f2502af7851"), (l9) this.c.b(), a, a instanceof ThreadPoolExecutor ? new ib(this, a) : new jb(this), ComponentLocator.this.e(), new kb(this), new lb(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class m extends ab<tb> {
        public final /* synthetic */ Context b;

        public m(Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public tb a() {
            return new tb(this.b, ComponentLocator.this.d(), new p5(this.b.getSharedPreferences("StartApp-dfeaf103310003d9", 0), null), ComponentLocator.this.f(), ComponentLocator.this.g(), new mb(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class n extends ab<zb> {
        public final /* synthetic */ Context b;

        public n(Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public zb a() {
            return new zb(this.b, new p5(this.b.getSharedPreferences("StartApp-6cd3cac226013e8e", 0), null), ComponentLocator.this.f(), ComponentLocator.this.g(), new nb(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class o extends ab<j7> {
        public final /* synthetic */ Context b;

        public o(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public j7 a() {
            return new j7(this.b);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class p extends ab<cd> {
        public final /* synthetic */ Context b;

        public p(Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public cd a() {
            return new cd(this.b, ComponentLocator.this.i(), new ob(this));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class q extends ab<qc> {
        public final /* synthetic */ Context b;

        public q(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public qc a() {
            Context context = this.b;
            vc vcVar = new vc(context);
            Pair pair = Build.VERSION.SDK_INT >= 21 ? new Pair(new rc(context, SchedulerService.class), vcVar) : new Pair(vcVar, vcVar);
            return new qc((uc) pair.first, (uc) pair.second);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class r extends ab<xc> {
        public final /* synthetic */ Context b;

        public r(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public xc a() {
            return new xc(this.b, new p5(this.b.getSharedPreferences("StartApp-b36110d5cb803404", 0), null), new pb(this), new Handler(Looper.getMainLooper()));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class s extends ab<l9> {
        public s(ComponentLocator componentLocator) {
        }

        @Override // com.startapp.ab
        public l9 a() {
            return ComponentLocator.a("core");
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class t extends ab<Executor> {
        public t(ComponentLocator componentLocator) {
        }

        @Override // com.startapp.ab
        public Executor a() {
            return ComponentLocator.a(0, 4, "net-api", 10L);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class u extends ab<Executor> {
        public u(ComponentLocator componentLocator) {
        }

        @Override // com.startapp.ab
        public Executor a() {
            return ComponentLocator.a(0, 2, "net-media", 10L);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class v extends ab<o9> {
        public final /* synthetic */ Context b;

        public v(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public o9 a() {
            return new o9(new p5(this.b.getSharedPreferences("StartApp-c065dea8f7f3a31b", 0), null));
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class w extends ab<Executor> {
        public w(ComponentLocator componentLocator) {
        }

        @Override // com.startapp.ab
        public Executor a() {
            return ComponentLocator.a(0, 2, "disk", 5L);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class x extends ab<Executor> {
        public x(ComponentLocator componentLocator) {
        }

        @Override // com.startapp.ab
        public Executor a() {
            return ComponentLocator.a(0, 4, "generic", 5L);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class y extends ab<l9> {
        public y(ComponentLocator componentLocator) {
        }

        @Override // com.startapp.ab
        public l9 a() {
            return ComponentLocator.a("dc");
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class z extends ab<p5> {
        public final /* synthetic */ Context b;

        public z(ComponentLocator componentLocator, Context context) {
            this.b = context;
        }

        @Override // com.startapp.ab
        public p5 a() {
            return new p5(this.b.getSharedPreferences("com.startapp.sdk", 0), null);
        }
    }

    public ComponentLocator(Context context) {
        m7 m7Var = new m7(context, Looper.getMainLooper());
        this.b = m7Var;
        m7Var.start();
        this.c = new v(this, context);
        this.d = new e0(context);
        this.e = new f0(context);
        g0 g0Var = new g0(this);
        this.f = new h0(this, context);
        this.g = new i0(this, context);
        this.h = new j0(context);
        this.i = new k0(context);
        this.j = new a(this, context);
        this.k = new b(this, context);
        this.l = new c(context);
        this.m = new d(this, context);
        this.n = new e(this);
        this.o = new f(this, context);
        this.p = new g(this, context);
        this.q = new h(context, g0Var);
        this.r = new i(this, context);
        this.s = new j(this, context);
        this.t = new l(context, g0Var);
        this.u = new m(context);
        this.v = new n(context);
        this.w = new o(this, context);
        this.x = new p(context);
        this.y = new q(this, context);
        this.z = new r(this, context);
        this.A = new s(this);
        this.B = new t(this);
        this.C = new u(this);
        this.D = new w(this);
        this.E = new x(this);
        this.F = new y(this);
        this.G = new z(this, context);
        this.H = new a0(this, context);
        this.I = new b0(context);
    }

    public static l9 a(String str) {
        n9 n9Var = new n9("startapp-" + str);
        n9Var.start();
        return new m9(new Handler(n9Var.getLooper()));
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [T, com.startapp.sdk.components.ComponentLocator] */
    public static ComponentLocator a(Context context) {
        ComponentLocator componentLocator;
        bb<ComponentLocator, Context> bbVar = a;
        ComponentLocator componentLocator2 = bbVar.a;
        ComponentLocator componentLocator3 = componentLocator2;
        if (componentLocator2 == null) {
            synchronized (bbVar) {
                ComponentLocator componentLocator4 = bbVar.a;
                componentLocator = componentLocator4;
                if (componentLocator4 == null) {
                    ?? componentLocator5 = new ComponentLocator(y8.b(context));
                    bbVar.a = componentLocator5;
                    componentLocator = componentLocator5;
                }
            }
            componentLocator3 = componentLocator;
        }
        return componentLocator3;
    }

    public static Executor a(int i2, int i3, String str, long j2) {
        if (Build.VERSION.SDK_INT < 21) {
            return i3 < 2 ? Executors.newSingleThreadExecutor(new d0(str)) : Executors.newCachedThreadPool(new d0(str));
        }
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i2, i3, j2, TimeUnit.SECONDS, new LinkedTransferQueue<Runnable>() { // from class: com.startapp.sdk.components.ComponentLocator.36
            @Override // java.util.concurrent.LinkedTransferQueue, java.util.Queue, java.util.concurrent.BlockingQueue
            public boolean offer(Object obj) {
                return tryTransfer((Runnable) obj);
            }
        }, new d0(str), new c0());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        return threadPoolExecutor;
    }

    public static ThreadFactory b(String str) {
        return new d0(str);
    }

    public AdvertisingIdResolver a() {
        return this.j.b();
    }

    public ga b() {
        return this.s.b();
    }

    public i5 c() {
        return this.p.b();
    }

    public p5 d() {
        return this.G.b();
    }

    public j7 e() {
        return this.w.b();
    }

    public k7 f() {
        return this.l.b();
    }

    public l9 g() {
        return this.F.b();
    }

    public Executor h() {
        return this.D.b();
    }

    public Executor i() {
        return this.E.b();
    }

    public n7 j() {
        return this.q.b();
    }

    public v7 k() {
        return this.t.b();
    }

    public qc l() {
        return this.y.b();
    }

    public xb m() {
        return this.f.b();
    }

    public xc n() {
        return this.z.b();
    }

    public Executor o() {
        return this.B.b();
    }

    public ed p() {
        return this.k.b();
    }

    public cd q() {
        return this.x.b();
    }

    public x8 r() {
        return this.n.b();
    }

    public dc s() {
        return this.h.b();
    }

    public ca t() {
        return this.d.b();
    }
}
