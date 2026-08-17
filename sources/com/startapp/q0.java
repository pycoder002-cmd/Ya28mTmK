package com.startapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import com.startapp.networkTest.controller.LocationController;
import com.startapp.networkTest.data.RadioInfo;
import com.startapp.networkTest.enums.LtrCriteriaTypes;
import com.startapp.networkTest.results.LatencyResult;
import com.startapp.networkTest.results.P3TestResult;
import com.startapp.networkTest.results.speedtest.MeasurementPointLatency;
import com.startapp.networkTest.speedtest.SpeedtestEngineError;
import com.startapp.networkTest.speedtest.SpeedtestEngineStatus;
import com.startapp.networkTest.threads.ThreadManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class q0 {
    public static final String a = "q0";
    private static final boolean b = false;
    private f1 c;
    private Context d;
    private w e;
    private x f;
    private LocationController g;
    private u h;
    private P3TestResult i;
    private ArrayList<f0> j;
    private String k;
    private t l;
    private String m;
    private String n = "";
    private String o = "";
    private String p = "";
    private String q = "";
    private String r = "";
    private String s = "";
    private String t = "";

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends AsyncTask<Void, Void, LatencyResult> {
        private String a;
        private int b;
        private int c;
        private int d;
        private int e;
        private String[] f;
        private LtrCriteriaTypes g;
        private boolean h;

        /* compiled from: StartAppSDK */
        /* renamed from: com.startapp.q0$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class C0061a implements v0 {
            public final /* synthetic */ boolean[] a;
            public final /* synthetic */ int[] b;
            public final /* synthetic */ ArrayList c;
            public final /* synthetic */ t0 d;

            public C0061a(boolean[] zArr, int[] iArr, ArrayList arrayList, t0 t0Var) {
                this.a = zArr;
                this.b = iArr;
                this.c = arrayList;
                this.d = t0Var;
            }

            @Override // com.startapp.v0
            public void a(int i, long j, long j2) {
                if (j2 >= 0) {
                    this.a[0] = true;
                    int[] iArr = this.b;
                    iArr[0] = iArr[0] + 1;
                }
                int i2 = (int) j2;
                this.c.add(a.this.a(j, i2));
                if (q0.this.c != null) {
                    q0.this.c.b(i / a.this.b, j2 >= 0 ? i2 : 0);
                }
                if (a.this.isCancelled()) {
                    this.d.b();
                }
            }
        }

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class b implements Comparator<c1> {
            public b() {
            }

            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(c1 c1Var, c1 c1Var2) {
                return c1Var.successfulTests - c1Var2.successfulTests;
            }
        }

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class c implements Comparator<c1> {
            public c() {
            }

            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(c1 c1Var, c1 c1Var2) {
                return c1Var.totalTests - c1Var2.totalTests;
            }
        }

        public a(String str, int i, int i2, int i3, int i4, boolean z) {
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.h = z;
            if (i2 < 200) {
                this.c = 200;
            }
            if (q0.this.c != null) {
                q0.this.c.a(SpeedtestEngineStatus.CONNECT, SpeedtestEngineError.OK, this.b * this.d);
            }
            t c2 = s.c();
            this.f = c2.k();
            this.g = LtrCriteriaTypes.valueOf(c2.j());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MeasurementPointLatency a(long j, int i) {
            MeasurementPointLatency measurementPointLatency = new MeasurementPointLatency();
            measurementPointLatency.Delta = j;
            RadioInfo h = q0.this.e.h();
            measurementPointLatency.ConnectionType = h.ConnectionType;
            measurementPointLatency.NetworkType = h.NetworkType;
            measurementPointLatency.NrAvailable = h.NrAvailable;
            measurementPointLatency.NrState = h.NrState;
            measurementPointLatency.RxLev = h.RXLevel;
            measurementPointLatency.Rtt = i;
            return measurementPointLatency;
        }

        private List<c1> a(String[] strArr, LtrCriteriaTypes ltrCriteriaTypes, String str) {
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            Set<String> q = s.c().q();
            LinkedList<c1> linkedList3 = new LinkedList();
            if (q != null) {
                Iterator<String> it = q.iterator();
                while (it.hasNext()) {
                    c1 c1Var = (c1) v1.a(it.next(), c1.class);
                    if (c1Var != null) {
                        linkedList3.add(c1Var);
                    }
                }
            }
            for (String str2 : strArr) {
                c1 c1Var2 = new c1();
                c1Var2.address = str2;
                linkedList2.add(c1Var2);
            }
            for (c1 c1Var3 : linkedList3) {
                for (int i = 0; i < strArr.length; i++) {
                    if (strArr[i].equals(c1Var3.address)) {
                        linkedList2.set(i, c1Var3);
                    }
                }
            }
            int ordinal = ltrCriteriaTypes.ordinal();
            if (ordinal == 0) {
                Collections.sort(linkedList2, new c());
                return new LinkedList(linkedList2);
            }
            if (ordinal == 1) {
                Collections.sort(linkedList2, new b());
                return new LinkedList(linkedList2);
            }
            if (ordinal == 2) {
                Collections.shuffle(linkedList2, new Random(System.nanoTime()));
                return new LinkedList(linkedList2);
            }
            if (ordinal == 3) {
                return linkedList2;
            }
            if (ordinal != 4) {
                return linkedList;
            }
            c1 c1Var4 = new c1();
            c1Var4.address = str;
            linkedList.add(c1Var4);
            return linkedList;
        }

        private void a(List<c1> list) {
            HashSet hashSet = new HashSet();
            Iterator<c1> it = list.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().toString());
            }
            s.c().d(hashSet);
        }

        /* JADX WARN: Code restructure failed: missing block: B:61:0x0373, code lost:
        
            if (r1 == null) goto L170;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0497, code lost:
        
            if (r30.g == com.startapp.networkTest.enums.LtrCriteriaTypes.CTItem) goto L198;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0499, code lost:
        
            a(r26);
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x04a6, code lost:
        
            if (com.startapp.s.b().CLEAR_LTR_LOCATION_INFO() == false) goto L202;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x04a8, code lost:
        
            if (r0 == null) goto L202;
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x04aa, code lost:
        
            r0.LocationInfoOnStart = new com.startapp.networkTest.data.LocationInfo();
            r0.LocationInfoOnEnd = new com.startapp.networkTest.data.LocationInfo();
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x04b8, code lost:
        
            return r0;
         */
        /* JADX WARN: Removed duplicated region for block: B:122:0x02dc A[Catch: all -> 0x02f5, TRY_LEAVE, TryCatch #9 {all -> 0x02f5, blocks: (B:110:0x02a1, B:112:0x02a8, B:120:0x02b9, B:122:0x02dc, B:131:0x02b0), top: B:109:0x02a1 }] */
        /* JADX WARN: Removed duplicated region for block: B:130:0x02f2  */
        /* JADX WARN: Removed duplicated region for block: B:159:0x022e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:195:0x0151 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:206:0x01bb A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:22:0x01df  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x021b A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x025c  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0264 A[Catch: all -> 0x0319, TryCatch #16 {all -> 0x0319, blocks: (B:169:0x023e, B:42:0x0264, B:43:0x0269, B:45:0x026d, B:140:0x0273, B:48:0x0285), top: B:168:0x023e }] */
        /* JADX WARN: Removed duplicated region for block: B:64:0x037e  */
        /* JADX WARN: Removed duplicated region for block: B:67:0x03ce  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x0445  */
        /* JADX WARN: Removed duplicated region for block: B:73:0x0474 A[LOOP:0: B:9:0x0028->B:73:0x0474, LOOP_END] */
        /* JADX WARN: Removed duplicated region for block: B:74:0x046d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:84:0x044c  */
        /* JADX WARN: Removed duplicated region for block: B:85:0x03b2  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x036a A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.startapp.networkTest.results.LatencyResult doInBackground(java.lang.Void... r31) {
            /*
                Method dump skipped, instructions count: 1209
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.q0.a.doInBackground(java.lang.Void[]):com.startapp.networkTest.results.LatencyResult");
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(LatencyResult latencyResult) {
            super.onPostExecute(latencyResult);
            q0.this.i = latencyResult;
            if (latencyResult != null) {
                if (q0.this.c != null) {
                    q0.this.c.a(SpeedtestEngineStatus.END, SpeedtestEngineError.OK, 0L);
                }
            } else if (q0.this.c != null) {
                q0.this.c.a(SpeedtestEngineStatus.ABORTED, SpeedtestEngineError.OK, 0L);
            }
        }
    }

    public q0(f1 f1Var, Context context) {
        if (f1Var == null) {
            throw new IllegalArgumentException("ISpeedtestListener is NULL");
        }
        this.c = f1Var;
        this.d = context;
        q b2 = s.b();
        this.k = b2.PROJECT_ID();
        this.l = new t(this.d);
        a(context, b2);
    }

    private void a(Context context, q qVar) {
        this.e = new w(context);
        this.f = new x(context);
        this.g = new LocationController(this.d);
        this.h = new u(this.d);
        this.j = new ArrayList<>();
        if (qVar.BANDWDITH_TEST_MANAGER_GET_IMEI_IMSI()) {
        }
    }

    public P3TestResult a() {
        return this.i;
    }

    public void a(LocationController.ProviderMode providerMode) {
        LocationController locationController = this.g;
        if (locationController != null) {
            locationController.a(providerMode);
        }
        w wVar = this.e;
        if (wVar != null) {
            wVar.x();
        }
        x xVar = this.f;
        if (xVar != null) {
            xVar.f();
        }
    }

    public void a(String str) {
        ArrayList<f0> arrayList = this.j;
        arrayList.add(new f0(arrayList.size() + 1, str));
    }

    public void a(String str, int i, int i2, int i3, int i4) {
        a(str, i, i2, i3, i4, false);
    }

    public void a(String str, int i, int i2, int i3, int i4, boolean z) {
        this.j = new ArrayList<>();
        if (Build.VERSION.SDK_INT < 11) {
            new a(str, i, i2, i3, i4, z).execute(new Void[0]);
        } else {
            new a(str, i, i2, i3, i4, z).executeOnExecutor(ThreadManager.b().a(), new Void[0]);
        }
    }

    public void b() {
        a(LocationController.ProviderMode.GpsAndNetwork);
    }

    public void b(String str) {
        this.t = str;
    }

    public void c() {
        LocationController locationController = this.g;
        if (locationController != null) {
            locationController.f();
        }
        w wVar = this.e;
        if (wVar != null) {
            wVar.y();
        }
        x xVar = this.f;
        if (xVar != null) {
            xVar.g();
        }
    }

    public void c(String str) {
        this.o = str;
    }

    public void d(String str) {
        this.r = str;
    }

    public void e(String str) {
        this.n = str;
    }

    public void f(String str) {
        this.s = str;
    }

    public void g(String str) {
        this.m = str;
    }
}
