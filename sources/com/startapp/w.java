package com.startapp;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.view.InputDeviceCompat;
import android.telephony.CellIdentity;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityNr;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoNr;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthNr;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.SparseArray;
import com.android.volley.DefaultRetryPolicy;
import com.startapp.networkTest.data.RadioInfo;
import com.startapp.networkTest.data.radio.ApnInfo;
import com.startapp.networkTest.data.radio.NetworkRegistrationInfo;
import com.startapp.networkTest.enums.CellConnectionStatus;
import com.startapp.networkTest.enums.CellNetworkTypes;
import com.startapp.networkTest.enums.ConnectionTypes;
import com.startapp.networkTest.enums.DuplexMode;
import com.startapp.networkTest.enums.NetworkGenerations;
import com.startapp.networkTest.enums.NetworkTypes;
import com.startapp.networkTest.enums.PreferredNetworkTypes;
import com.startapp.networkTest.enums.ServiceStates;
import com.startapp.networkTest.enums.ThreeStateShort;
import com.startapp.networkTest.enums.radio.SignalStrengths;
import com.startapp.networkTest.enums.wifi.WifiDetailedStates;
import com.startapp.networkTest.threads.ThreadManager;
import com.startapp.networkTest.utils.LteFrequencyUtil;
import cz.msebera.android.httpclient.client.config.CookieSpecs;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class w {
    private static final String a = "w";
    private static final boolean b = false;
    private static final int c = 16;
    private static final int d = 17;
    private static final int e = 18;
    private static final int f = 19;
    private Method A;
    private Method B;
    private Field C;
    private Field D;
    private Field E;
    private Field F;
    private Field G;
    private Method H;
    private Field I;
    private Field J;
    private Field K;
    private Field L;
    private Field M;
    private Method N;
    private Method O;
    private Method P;
    private Method Q;
    private Method R;
    private Method S;
    private Method T;
    private ContentResolver U;
    private int[] V;
    public final List<y> W;
    private boolean X;
    public final Handler g;
    private TelephonyManager h;
    private SparseArray<TelephonyManager> i;
    private Context j;
    private o k;
    private ArrayList<o> l;
    private ConnectivityManager m;
    private i n;
    private SubscriptionManager.OnSubscriptionsChangedListener o;
    private m0 p;
    private j q;
    private List<CellInfo> r;
    private Method s;
    private Method t;
    private Method u;
    private Method v;
    private Method w;
    private Method x;
    private Method y;
    private Method z;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends SubscriptionManager.OnSubscriptionsChangedListener {
        public a() {
        }

        @Override // android.telephony.SubscriptionManager.OnSubscriptionsChangedListener
        public void onSubscriptionsChanged() {
            super.onSubscriptionsChanged();
            if (w.this.X) {
                return;
            }
            new h().executeOnExecutor(ThreadManager.b().c(), new Void[0]);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements Callable<com.startapp.networkTest.data.radio.CellInfo[]> {
        public b() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public com.startapp.networkTest.data.radio.CellInfo[] call() {
            try {
                return w.this.c();
            } catch (Throwable th) {
                h1.a(th);
                return new com.startapp.networkTest.data.radio.CellInfo[0];
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements Callable<ApnInfo[]> {
        public c() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ApnInfo[] call() throws Exception {
            try {
                return w.this.a();
            } catch (Throwable th) {
                h1.a(th);
                return new ApnInfo[0];
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements Runnable {
        public final /* synthetic */ y a;

        public d(y yVar) {
            this.a = yVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            w.this.a(this.a);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e implements Runnable {
        public final /* synthetic */ y a;

        public e(y yVar) {
            this.a = yVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            w.this.b(this.a);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class f {
        public int a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public int g;
        public long h;
        public long i;
        public String j;
        public WifiDetailedStates k;

        private f() {
            this.a = -1;
            this.b = "";
            this.c = "";
            this.d = "";
            this.e = "";
            this.f = "";
            this.g = -1;
            this.h = -1L;
            this.i = -1L;
            this.j = "";
            this.k = WifiDetailedStates.Unknown;
        }

        public /* synthetic */ f(w wVar, a aVar) {
            this();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class g {
        public CellLocation a;
        public long b;

        private g() {
            this.b = 0L;
        }

        public /* synthetic */ g(w wVar, a aVar) {
            this();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class h extends AsyncTask<Void, Void, Void> {
        public h() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Void doInBackground(Void... voidArr) {
            w.this.z();
            return null;
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(Void r2) {
            w wVar = w.this;
            wVar.a(wVar.V);
            if (Build.VERSION.SDK_INT >= 29) {
                w.this.w();
            }
            w.this.b(false);
            w.this.X = false;
        }

        @Override // android.os.AsyncTask
        public void onPreExecute() {
            w.this.X = true;
            w.this.d(false);
            w.this.V = new int[0];
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class i {
        private SparseArray<n> a = new SparseArray<>();
        private SparseArray<m> b = new SparseArray<>();
        private SparseArray<g> c = new SparseArray<>();
        private HashMap<String, k> d = new HashMap<>();
        private SparseArray<NetworkRegistrationInfo[]> e = new SparseArray<>();
        private Map<String, String> g = new HashMap();
        private SparseArray<l> f = new SparseArray<>();

        public i() {
        }

        public g a(int i) {
            return this.c.get(i);
        }

        public k a(String str) {
            return this.d.get(str);
        }

        public String a(int i, String str) {
            Map<String, String> map = this.g;
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(str != null ? str.split(",")[0] : "");
            String str2 = map.get(sb.toString());
            return str2 == null ? "" : str2;
        }

        public void a(int i, g gVar) {
            this.c.put(i, gVar);
        }

        public void a(int i, l lVar) {
            this.f.put(i, lVar);
        }

        public void a(int i, m mVar) {
            this.b.put(i, mVar);
        }

        public void a(int i, n nVar) {
            this.a.put(i, nVar);
        }

        public void a(int i, String str, String str2) {
            this.g.put(i + str, str2);
        }

        public void a(int i, NetworkRegistrationInfo[] networkRegistrationInfoArr) {
            this.e.put(i, networkRegistrationInfoArr);
        }

        public void a(String str, k kVar) {
            this.d.put(str, kVar);
        }

        public NetworkRegistrationInfo[] b(int i) {
            return this.e.get(i);
        }

        public l c(int i) {
            return this.f.get(i);
        }

        public m d(int i) {
            m mVar = this.b.get(i);
            return mVar == null ? new m(w.this, null) : mVar;
        }

        public n e(int i) {
            n nVar = this.a.get(i);
            return nVar == null ? new n(w.this, null) : nVar;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class j extends BroadcastReceiver {
        public final String a;
        public final String b;

        private j() {
            this.a = "android.intent.action.ANY_DATA_STATE";
            this.b = "com.samsung.ims.action.IMS_REGISTRATION";
        }

        public /* synthetic */ j(w wVar, a aVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || intent.getAction() == null) {
                return;
            }
            try {
                String action = intent.getAction();
                Bundle extras = intent.getExtras();
                int i = -1;
                if (action.equalsIgnoreCase("android.intent.action.ANY_DATA_STATE") && extras != null) {
                    String string = extras.getString("reason", "");
                    String string2 = extras.getString("apnType", "");
                    if (extras.get("subscription") instanceof Integer) {
                        i = extras.getInt("subscription", -1);
                    } else if (extras.get("subscription") instanceof Long) {
                        i = (int) extras.getLong("subscription", -1L);
                    }
                    if (string2.equalsIgnoreCase(CookieSpecs.DEFAULT)) {
                        string2 = "supl";
                    }
                    w.this.n.a(i, string2, string);
                    return;
                }
                if (!action.equalsIgnoreCase("com.samsung.ims.action.IMS_REGISTRATION") || extras == null) {
                    return;
                }
                String string3 = extras.getString("SERVICE");
                int i2 = extras.getInt("PHONE_ID", -1);
                int i3 = extras.getInt("SIP_ERROR", -1);
                extras.getBoolean("VOWIFI", false);
                extras.getBoolean("REGISTERED", false);
                l lVar = new l(w.this, null);
                lVar.a = i3;
                if (string3 != null) {
                    lVar.b = string3.replaceAll("\\[", "").replaceAll("\\]", "").replace(", ", ",");
                }
                Iterator<n0> it = v.f(w.this.j).SimInfos.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    n0 next = it.next();
                    if (next.SimSlotIndex == i2) {
                        i = next.SubscriptionId;
                        break;
                    }
                }
                w.this.n.a(i, lVar);
            } catch (Throwable th) {
                h1.a(th);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class k {
        public long a;
        public int b;
        public int c;
        public long d;

        private k() {
            this.a = 0L;
            this.b = 0;
            this.c = 0;
            this.d = 0L;
        }

        public /* synthetic */ k(w wVar, a aVar) {
            this();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class l {
        public int a;
        public String b;

        private l() {
            this.a = -1;
            this.b = "";
        }

        public /* synthetic */ l(w wVar, a aVar) {
            this();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class m {
        public ServiceStates a;
        public long b;
        public DuplexMode c;
        public ThreeStateShort d;
        public int e;
        public ThreeStateShort f;

        private m() {
            this.a = ServiceStates.Unknown;
            this.b = 0L;
            this.c = DuplexMode.Unknown;
            ThreeStateShort threeStateShort = ThreeStateShort.Unknown;
            this.d = threeStateShort;
            this.e = -1;
            this.f = threeStateShort;
        }

        public /* synthetic */ m(w wVar, a aVar) {
            this();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class n {
        public int a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public long k;
        public int l;
        public int m;
        public int n;
        public int o;
        public int p;
        public int q;

        private n() {
            Integer num = RadioInfo.INVALID;
            this.a = num.intValue();
            this.b = num.intValue();
            this.c = num.intValue();
            this.d = num.intValue();
            this.e = num.intValue();
            this.f = num.intValue();
            this.g = num.intValue();
            this.h = num.intValue();
            this.i = num.intValue();
            this.j = num.intValue();
            this.l = num.intValue();
            this.m = num.intValue();
            this.n = num.intValue();
            this.o = num.intValue();
            this.p = num.intValue();
            this.q = num.intValue();
        }

        public /* synthetic */ n(w wVar, a aVar) {
            this();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class o extends PhoneStateListener {
        private Field a;
        private int b;

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class a implements Runnable {
            public final /* synthetic */ ServiceState a;
            public final /* synthetic */ int b;

            public a(ServiceState serviceState, int i) {
                this.a = serviceState;
                this.b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                Iterator<y> it = w.this.W.iterator();
                while (it.hasNext()) {
                    it.next().a(this.a, this.b);
                }
            }
        }

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class b implements Runnable {
            public final /* synthetic */ CellLocation a;
            public final /* synthetic */ int b;

            public b(CellLocation cellLocation, int i) {
                this.a = cellLocation;
                this.b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                Iterator<y> it = w.this.W.iterator();
                while (it.hasNext()) {
                    it.next().a(this.a, this.b);
                }
            }
        }

        public o() {
            this.b = -1;
        }

        public o(int i) {
            this.b = -1;
            this.b = i;
            try {
                Field declaredField = getClass().getSuperclass().getDeclaredField("mSubId");
                this.a = declaredField;
                declaredField.setAccessible(true);
                this.a.set(this, Integer.valueOf(i));
            } catch (Throwable th) {
                h1.b(th);
            }
        }

        private void a(CellLocation cellLocation, int i) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            g gVar = new g(w.this, null);
            gVar.a = cellLocation;
            gVar.b = elapsedRealtime;
            w.this.n.a(i, gVar);
            w.this.g.post(new k1(new b(cellLocation, i)));
        }

        private void a(ServiceState serviceState, int i) {
            m mVar = new m(w.this, null);
            if (Build.VERSION.SDK_INT >= 25) {
                if (w.this.I != null) {
                    try {
                        mVar.f = w.this.I.getBoolean(serviceState) ? ThreeStateShort.Yes : ThreeStateShort.No;
                    } catch (Throwable th) {
                        h1.a(th);
                    }
                }
                if (mVar.f == ThreeStateShort.Unknown && w.this.H != null) {
                    try {
                        mVar.f = ((Boolean) w.this.H.invoke(serviceState, new Object[0])).booleanValue() ? ThreeStateShort.Yes : ThreeStateShort.No;
                    } catch (Throwable th2) {
                        h1.a(th2);
                    }
                }
                if (Build.VERSION.SDK_INT >= 28) {
                    int duplexMode = serviceState.getDuplexMode();
                    mVar.c = duplexMode != 1 ? duplexMode != 2 ? DuplexMode.Unknown : DuplexMode.TDD : DuplexMode.FDD;
                    mVar.e = serviceState.getChannelNumber();
                }
            }
            mVar.d = serviceState.getIsManualSelection() ? ThreeStateShort.Yes : ThreeStateShort.No;
            int state = serviceState.getState();
            mVar.a = state != 0 ? state != 1 ? state != 2 ? state != 3 ? ServiceStates.Unknown : ServiceStates.PowerOff : ServiceStates.EmergencyOnly : ServiceStates.OutOfService : ServiceStates.InService;
            mVar.b = SystemClock.elapsedRealtime();
            NetworkRegistrationInfo[] c = w1.c(serviceState.toString());
            w.this.n.a(i, mVar);
            w.this.n.a(i, c);
            w.this.g.post(new k1(new a(serviceState, i)));
        }

        /* JADX WARN: Can't wrap try/catch for region: R(11:(3:96|97|(1:99))|101|(2:103|104)|(3:108|109|(2:111|112))|(3:114|115|(2:117|118))|(2:120|121)|(4:123|124|125|(11:127|128|129|130|131|61|(6:63|(2:80|81)|65|(2:75|76)|67|(3:69|70|71))|85|86|(1:88)|90)(1:136))|140|124|125|(0)(0)) */
        /* JADX WARN: Can't wrap try/catch for region: R(15:34|(5:36|(2:40|41)|45|(2:47|(13:49|50|51|(5:53|54|55|56|57)(1:153)|58|(18:96|97|(1:99)|101|(2:103|104)|108|109|(2:111|112)|114|115|(2:117|118)|120|121|(4:123|124|125|(11:127|128|129|130|131|61|(6:63|(2:80|81)|65|(2:75|76)|67|(3:69|70|71))|85|86|(1:88)|90)(1:136))|140|124|125|(0)(0))|60|61|(0)|85|86|(0)|90))(1:159)|(1:157))(1:160)|158|50|51|(0)(0)|58|(0)|60|61|(0)|85|86|(0)|90) */
        /* JADX WARN: Code restructure failed: missing block: B:137:0x0302, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:138:0x0303, code lost:
        
            r22 = r9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:154:0x022c, code lost:
        
            r0 = th;
         */
        /* JADX WARN: Code restructure failed: missing block: B:155:0x022d, code lost:
        
            r19 = r8;
            r22 = r9;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0373, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x0374, code lost:
        
            com.startapp.h1.a(r0);
         */
        /* JADX WARN: Removed duplicated region for block: B:127:0x02e6 A[Catch: all -> 0x0302, TRY_LEAVE, TryCatch #1 {all -> 0x0302, blocks: (B:125:0x02de, B:127:0x02e6), top: B:124:0x02de }] */
        /* JADX WARN: Removed duplicated region for block: B:136:0x02ff  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x0227  */
        /* JADX WARN: Removed duplicated region for block: B:161:0x0388  */
        /* JADX WARN: Removed duplicated region for block: B:19:0x00d1  */
        /* JADX WARN: Removed duplicated region for block: B:22:0x00eb  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x01b9  */
        /* JADX WARN: Removed duplicated region for block: B:53:0x020c A[Catch: all -> 0x022c, TRY_LEAVE, TryCatch #4 {all -> 0x022c, blocks: (B:51:0x0204, B:53:0x020c), top: B:50:0x0204 }] */
        /* JADX WARN: Removed duplicated region for block: B:63:0x0310  */
        /* JADX WARN: Removed duplicated region for block: B:88:0x035f A[Catch: all -> 0x0373, TRY_LEAVE, TryCatch #5 {all -> 0x0373, blocks: (B:86:0x0357, B:88:0x035f), top: B:85:0x0357 }] */
        /* JADX WARN: Removed duplicated region for block: B:96:0x0238 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void a(android.telephony.SignalStrength r27, int r28) {
            /*
                Method dump skipped, instructions count: 994
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.w.o.a(android.telephony.SignalStrength, int):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0074  */
        /* JADX WARN: Removed duplicated region for block: B:28:0x007c  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x007f  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void a(java.util.List<android.telephony.CellInfo> r10) {
            /*
                r9 = this;
                if (r10 != 0) goto L3
                return
            L3:
                com.startapp.w r0 = com.startapp.w.this
                com.startapp.w.a(r0, r10)
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 29
                if (r0 < r1) goto L9f
                java.util.Iterator r10 = r10.iterator()
            L12:
                boolean r0 = r10.hasNext()
                if (r0 == 0) goto L9f
                java.lang.Object r0 = r10.next()
                android.telephony.CellInfo r0 = (android.telephony.CellInfo) r0
                boolean r1 = r0.isRegistered()
                if (r1 == 0) goto L12
                boolean r1 = r0 instanceof android.telephony.CellInfoNr
                if (r1 == 0) goto L12
                android.telephony.CellInfoNr r0 = (android.telephony.CellInfoNr) r0
                android.telephony.CellIdentity r1 = r0.getCellIdentity()
                boolean r2 = r1 instanceof android.telephony.CellIdentityNr
                if (r2 == 0) goto L12
                android.telephony.CellIdentityNr r1 = (android.telephony.CellIdentityNr) r1
                r2 = 0
                java.lang.String r3 = r1.getMccString()     // Catch: java.lang.NumberFormatException -> L48
                int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L48
                java.lang.String r4 = r1.getMncString()     // Catch: java.lang.NumberFormatException -> L46
                int r2 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> L46
                goto L4d
            L46:
                r4 = move-exception
                goto L4a
            L48:
                r4 = move-exception
                r3 = 0
            L4a:
                com.startapp.h1.b(r4)
            L4d:
                long r4 = r1.getNci()
                int r6 = r1.getTac()
                int r1 = r1.getPci()
                java.lang.StringBuilder r7 = new java.lang.StringBuilder
                r7.<init>()
                java.lang.String r8 = ""
                r7.append(r8)
                r7.append(r3)
                r7.append(r2)
                java.lang.String r2 = r7.toString()
                r7 = 2147483647(0x7fffffff, double:1.060997895E-314)
                int r3 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
                if (r3 != 0) goto L76
                r4 = -1
            L76:
                r3 = -1
                r7 = 2147483647(0x7fffffff, float:NaN)
                if (r6 != r7) goto L7d
                r6 = -1
            L7d:
                if (r1 != r7) goto L80
                r1 = -1
            L80:
                com.startapp.w$k r3 = new com.startapp.w$k
                com.startapp.w r7 = com.startapp.w.this
                r8 = 0
                r3.<init>(r7, r8)
                r3.a = r4
                r3.b = r6
                r3.c = r1
                long r0 = r0.getTimeStamp()
                r3.d = r0
                com.startapp.w r0 = com.startapp.w.this
                com.startapp.w$i r0 = com.startapp.w.b(r0)
                r0.a(r2, r3)
                goto L12
            L9f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.w.o.a(java.util.List):void");
        }

        /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:6:0x001b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public int a() {
            /*
                r4 = this;
                java.lang.reflect.Field r0 = r4.a
                r1 = -1
                if (r0 == 0) goto L14
                java.lang.Object r0 = r0.get(r4)     // Catch: java.lang.Throwable -> L10
                java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: java.lang.Throwable -> L10
                int r0 = r0.intValue()     // Catch: java.lang.Throwable -> L10
                goto L15
            L10:
                r0 = move-exception
                com.startapp.h1.a(r0)
            L14:
                r0 = -1
            L15:
                int r2 = android.os.Build.VERSION.SDK_INT
                r3 = 29
                if (r2 < r3) goto L24
                if (r0 == r1) goto L22
                r1 = 2147483647(0x7fffffff, float:NaN)
                if (r0 != r1) goto L24
            L22:
                int r0 = r4.b
            L24:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.w.o.a():int");
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellInfoChanged(List<CellInfo> list) {
            a(list);
        }

        @Override // android.telephony.PhoneStateListener
        public void onCellLocationChanged(CellLocation cellLocation) {
            a(cellLocation, a());
        }

        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            a(serviceState, a());
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            a(signalStrength, a());
        }
    }

    public w(Context context) {
        this.j = context;
        this.h = (TelephonyManager) context.getSystemService("phone");
        this.m = (ConnectivityManager) context.getSystemService("connectivity");
        z();
        a(this.V);
        if (Build.VERSION.SDK_INT >= 29) {
            w();
        }
        this.g = new Handler(Looper.getMainLooper());
        this.W = new CopyOnWriteArrayList();
        this.p = new m0();
        this.n = new i();
        this.U = this.j.getContentResolver();
        s();
        t();
        u();
        r();
        v();
    }

    private SparseArray<PreferredNetworkTypes> a(Context context) {
        SparseArray<PreferredNetworkTypes> sparseArray = new SparseArray<>();
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                String[] split = Settings.Global.getString(context.getContentResolver(), "preferred_network_mode").split(",");
                for (int i2 = 0; i2 < split.length; i2++) {
                    sparseArray.put(i2, e(Integer.valueOf(split[i2]).intValue()));
                }
            } catch (Throwable th) {
                h1.a(th);
            }
        }
        return sparseArray;
    }

    private static CellNetworkTypes a(NetworkTypes networkTypes) {
        if (networkTypes == NetworkTypes.CDMA) {
            return CellNetworkTypes.Cdma;
        }
        int ordinal = b(networkTypes).ordinal();
        return ordinal != 0 ? ordinal != 1 ? ordinal != 2 ? ordinal != 3 ? CellNetworkTypes.Unknown : CellNetworkTypes.Nr : CellNetworkTypes.Lte : CellNetworkTypes.Wcdma : CellNetworkTypes.Gsm;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static NetworkTypes a(String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -2039427040:
                if (str.equals("LTE_CA")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -908593671:
                if (str.equals("TD_SCDMA")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case DefaultRetryPolicy.DEFAULT_TIMEOUT_MS /* 2500 */:
                if (str.equals("NR")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 70881:
                if (str.equals("GSM")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 75709:
                if (str.equals("LTE")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 2063797:
                if (str.equals("CDMA")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 2123197:
                if (str.equals("EDGE")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 2194666:
                if (str.equals("GPRS")) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            case 2227260:
                if (str.equals("HSPA")) {
                    c2 = '\b';
                    break;
                }
                c2 = 65535;
                break;
            case 2608919:
                if (str.equals("UMTS")) {
                    c2 = '\t';
                    break;
                }
                c2 = 65535;
                break;
            case 3195620:
                if (str.equals("iDEN")) {
                    c2 = '\n';
                    break;
                }
                c2 = 65535;
                break;
            case 69034058:
                if (str.equals("HSDPA")) {
                    c2 = 11;
                    break;
                }
                c2 = 65535;
                break;
            case 69045140:
                if (str.equals("HSPAP")) {
                    c2 = '\f';
                    break;
                }
                c2 = 65535;
                break;
            case 69050395:
                if (str.equals("HSUPA")) {
                    c2 = '\r';
                    break;
                }
                c2 = 65535;
                break;
            case 70083979:
                if (str.equals("IWLAN")) {
                    c2 = 14;
                    break;
                }
                c2 = 65535;
                break;
            case 836263277:
                if (str.equals("CDMA - 1xRTT")) {
                    c2 = 15;
                    break;
                }
                c2 = 65535;
                break;
            case 882856261:
                if (str.equals("CDMA - eHRPD")) {
                    c2 = 16;
                    break;
                }
                c2 = 65535;
                break;
            case 893165057:
                if (str.equals("CDMA - EvDo rev. 0")) {
                    c2 = 17;
                    break;
                }
                c2 = 65535;
                break;
            case 893165074:
                if (str.equals("CDMA - EvDo rev. A")) {
                    c2 = 18;
                    break;
                }
                c2 = 65535;
                break;
            case 893165075:
                if (str.equals("CDMA - EvDo rev. B")) {
                    c2 = 19;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return NetworkTypes.LTE_CA;
            case 1:
                return NetworkTypes.TD_SCDMA;
            case 2:
                return NetworkTypes.NR;
            case 3:
                return NetworkTypes.GSM;
            case 4:
                return NetworkTypes.LTE;
            case 5:
                return NetworkTypes.CDMA;
            case 6:
                return NetworkTypes.EDGE;
            case 7:
                return NetworkTypes.GPRS;
            case '\b':
                return NetworkTypes.HSPA;
            case '\t':
                return NetworkTypes.UMTS;
            case '\n':
                return NetworkTypes.IDEN;
            case 11:
                return NetworkTypes.HSDPA;
            case '\f':
                return NetworkTypes.HSPAP;
            case '\r':
                return NetworkTypes.HSUPA;
            case 14:
                return NetworkTypes.WiFi;
            case 15:
                return NetworkTypes.Cdma1xRTT;
            case 16:
                return NetworkTypes.EHRPD;
            case 17:
                return NetworkTypes.EVDO_0;
            case 18:
                return NetworkTypes.EVDO_A;
            case 19:
                return NetworkTypes.EVDO_B;
            default:
                return NetworkTypes.Unknown;
        }
    }

    private PreferredNetworkTypes a(Context context, int i2) {
        PreferredNetworkTypes preferredNetworkTypes = PreferredNetworkTypes.Unknown;
        if (Build.VERSION.SDK_INT < 17) {
            return preferredNetworkTypes;
        }
        try {
            return e(Settings.Global.getInt(context.getContentResolver(), "preferred_network_mode" + i2));
        } catch (Throwable th) {
            h1.a(th);
            return preferredNetworkTypes;
        }
    }

    public static SignalStrengths a(RadioInfo radioInfo) {
        int i2;
        if (radioInfo == null) {
            return SignalStrengths.Unknown;
        }
        int i3 = radioInfo.RXLevel;
        NetworkGenerations b2 = b(radioInfo.NetworkType);
        NetworkGenerations networkGenerations = NetworkGenerations.Gen5;
        if (b2 == networkGenerations && (i2 = radioInfo.NrCsiRsrp) < -1) {
            i3 = i2;
        }
        if (i3 == 0) {
            return SignalStrengths.Unknown;
        }
        q b3 = s.b();
        int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G = b3.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G();
        int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G = b3.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G();
        int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G = b3.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G();
        int[] STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G = b3.STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G();
        return b2 == NetworkGenerations.Gen2 ? i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G[0] ? SignalStrengths.Excellent : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G[1] ? SignalStrengths.Good : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G[2] ? SignalStrengths.Fair : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_2G[3] ? SignalStrengths.Poor : SignalStrengths.Bad : b2 == NetworkGenerations.Gen3 ? i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G[0] ? SignalStrengths.Excellent : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G[1] ? SignalStrengths.Good : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G[2] ? SignalStrengths.Fair : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_3G[3] ? SignalStrengths.Poor : SignalStrengths.Bad : b2 == NetworkGenerations.Gen4 ? i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G[0] ? SignalStrengths.Excellent : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G[1] ? SignalStrengths.Good : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G[2] ? SignalStrengths.Fair : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_4G[3] ? SignalStrengths.Poor : SignalStrengths.Bad : b2 == networkGenerations ? i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G[0] ? SignalStrengths.Excellent : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G[1] ? SignalStrengths.Good : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G[2] ? SignalStrengths.Fair : i3 >= STATSMANAGER_SIGNAL_STRENGTH_MAPPING_5G[3] ? SignalStrengths.Poor : SignalStrengths.Bad : SignalStrengths.Unknown;
    }

    private void a(CellInfo cellInfo, com.startapp.networkTest.data.radio.CellInfo cellInfo2, long j2) {
        CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
        cellInfo2.IsRegistered = cellInfoCdma.isRegistered();
        cellInfo2.CellNetworkType = CellNetworkTypes.Gsm;
        cellInfo2.CellInfoAge = j2 - (cellInfoCdma.getTimeStamp() / 1000000);
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        cellInfo2.CdmaBaseStationLatitude = cellIdentity.getLatitude();
        cellInfo2.CdmaBaseStationLongitude = cellIdentity.getLongitude();
        if (cellIdentity.getSystemId() != Integer.MAX_VALUE) {
            cellInfo2.CdmaSystemId = cellIdentity.getSystemId();
        }
        if (cellIdentity.getNetworkId() != Integer.MAX_VALUE) {
            cellInfo2.CdmaNetworkId = cellIdentity.getNetworkId();
        }
        if (cellIdentity.getBasestationId() != Integer.MAX_VALUE) {
            cellInfo2.CdmaBaseStationId = cellIdentity.getBasestationId();
        }
        CellSignalStrengthCdma cellSignalStrength = cellInfoCdma.getCellSignalStrength();
        cellInfo2.Dbm = cellSignalStrength.getDbm();
        cellInfo2.CdmaDbm = cellSignalStrength.getCdmaDbm();
        cellInfo2.CdmaEcio = cellSignalStrength.getCdmaEcio();
        cellInfo2.EvdoDbm = cellSignalStrength.getEvdoDbm();
        cellInfo2.EvdoEcio = cellSignalStrength.getEvdoEcio();
        cellInfo2.EvdoSnr = cellSignalStrength.getEvdoSnr();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int[] iArr) {
        this.l = new ArrayList<>();
        for (int i2 : iArr) {
            this.l.add(new o(i2));
        }
    }

    private boolean a(com.startapp.networkTest.data.radio.CellInfo cellInfo, RadioInfo radioInfo) {
        try {
            if (radioInfo.MCC.isEmpty() || radioInfo.MNC.isEmpty() || !cellInfo.IsRegistered || cellInfo.Mcc != Integer.parseInt(radioInfo.MCC) || cellInfo.Mnc != Integer.parseInt(radioInfo.MNC)) {
                return false;
            }
            NetworkTypes i2 = i(radioInfo.SubscriptionId);
            NetworkTypes networkTypes = NetworkTypes.Unknown;
            if (i2 == networkTypes) {
                i2 = radioInfo.NetworkType;
            }
            if (i2 != networkTypes) {
                if (cellInfo.CellNetworkType != a(i2)) {
                    return false;
                }
            }
            return true;
        } catch (NumberFormatException e2) {
            h1.b(e2);
            return false;
        }
    }

    private static int b(int i2) {
        if (i2 == 99 || i2 < -5 || i2 > 91) {
            return 0;
        }
        return i2 - 116;
    }

    public static NetworkGenerations b(NetworkTypes networkTypes) {
        switch (networkTypes.ordinal()) {
            case 1:
            case 2:
            case 3:
            case 8:
            case 13:
            case 18:
                return NetworkGenerations.Gen2;
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 11:
            case 12:
            case 17:
            case 19:
                return NetworkGenerations.Gen3;
            case 14:
            case 15:
                return NetworkGenerations.Gen4;
            case 16:
                return NetworkGenerations.Gen5;
            default:
                return NetworkGenerations.Unknown;
        }
    }

    private void b(Context context) {
        if (this.q == null) {
            this.q = new j(this, null);
        }
        Objects.requireNonNull(this.q);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.ANY_DATA_STATE");
        Objects.requireNonNull(this.q);
        intentFilter.addAction("com.samsung.ims.action.IMS_REGISTRATION");
        context.registerReceiver(this.q, intentFilter);
    }

    private void b(CellInfo cellInfo, com.startapp.networkTest.data.radio.CellInfo cellInfo2, long j2) {
        CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
        cellInfo2.IsRegistered = cellInfoGsm.isRegistered();
        cellInfo2.CellNetworkType = CellNetworkTypes.Gsm;
        cellInfo2.CellInfoAge = j2 - (cellInfoGsm.getTimeStamp() / 1000000);
        CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
        if (cellIdentity.getMcc() != Integer.MAX_VALUE) {
            cellInfo2.Mcc = cellIdentity.getMcc();
        }
        if (cellIdentity.getMnc() != Integer.MAX_VALUE) {
            cellInfo2.Mnc = cellIdentity.getMnc();
        }
        if (cellIdentity.getCid() != Integer.MAX_VALUE) {
            int cid = cellIdentity.getCid();
            cellInfo2.Cid = cid;
            cellInfo2.CellId = cid;
        }
        if (cellIdentity.getLac() != Integer.MAX_VALUE) {
            cellInfo2.Lac = cellIdentity.getLac();
        }
        if (cellIdentity.getPsc() != Integer.MAX_VALUE) {
            cellInfo2.Psc = cellIdentity.getPsc();
        }
        if (Build.VERSION.SDK_INT >= 24) {
            if (cellIdentity.getArfcn() != Integer.MAX_VALUE) {
                cellInfo2.Arfcn = cellIdentity.getArfcn();
            }
            if (cellIdentity.getBsic() != Integer.MAX_VALUE) {
                cellInfo2.GsmBsic = cellIdentity.getBsic();
            }
        }
        cellInfo2.Dbm = cellInfoGsm.getCellSignalStrength().getDbm();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        try {
            c(z);
        } catch (Throwable th) {
            h1.a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(int i2) {
        if (i2 == 99 || i2 < 0 || i2 > 31) {
            return 0;
        }
        return (i2 * 2) - 113;
    }

    private void c(Context context) {
        j jVar;
        if (context == null || (jVar = this.q) == null) {
            return;
        }
        try {
            context.unregisterReceiver(jVar);
        } catch (Throwable th) {
            h1.a(th);
        }
    }

    private void c(CellInfo cellInfo, com.startapp.networkTest.data.radio.CellInfo cellInfo2, long j2) {
        CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
        cellInfo2.IsRegistered = cellInfoLte.isRegistered();
        cellInfo2.CellNetworkType = CellNetworkTypes.Lte;
        cellInfo2.CellInfoAge = j2 - (cellInfoLte.getTimeStamp() / 1000000);
        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
        if (cellIdentity.getMcc() != Integer.MAX_VALUE) {
            cellInfo2.Mcc = cellIdentity.getMcc();
        }
        if (cellIdentity.getMnc() != Integer.MAX_VALUE) {
            cellInfo2.Mnc = cellIdentity.getMnc();
        }
        if (cellIdentity.getCi() != Integer.MAX_VALUE) {
            int ci = cellIdentity.getCi();
            cellInfo2.Cid = ci;
            cellInfo2.CellId = ci;
        }
        if (cellIdentity.getPci() != Integer.MAX_VALUE) {
            cellInfo2.LtePci = cellIdentity.getPci();
        }
        if (cellIdentity.getTac() != Integer.MAX_VALUE) {
            cellInfo2.LteTac = cellIdentity.getTac();
        }
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 24 && cellIdentity.getEarfcn() != Integer.MAX_VALUE) {
            int earfcn = cellIdentity.getEarfcn();
            cellInfo2.Arfcn = earfcn;
            c0 a2 = LteFrequencyUtil.a(earfcn);
            if (a2 != null) {
                cellInfo2.LteBand = a2.band;
                cellInfo2.LteUploadEarfcn = a2.upload_earfcn;
                cellInfo2.LteDownloadEarfcn = a2.download_earfcn;
                cellInfo2.LteUploadFrequency = a2.upload_frequency;
                cellInfo2.LteDonwloadFrequency = a2.download_frequency;
            }
        }
        CellSignalStrengthLte cellSignalStrength = cellInfoLte.getCellSignalStrength();
        cellInfo2.Dbm = cellSignalStrength.getDbm();
        if (cellSignalStrength.getTimingAdvance() != Integer.MAX_VALUE) {
            cellInfo2.LteTimingAdvance = cellSignalStrength.getTimingAdvance();
        }
        if (i2 >= 29) {
            int cqi = cellSignalStrength.getCqi();
            if (cqi != Integer.MAX_VALUE) {
                cellInfo2.LteCqi = cqi;
            }
            cellInfo2.LteRssnr = cellSignalStrength.getRssnr();
            cellInfo2.LteRsrq = cellSignalStrength.getRsrq();
            cellInfo2.LteRssi = cellSignalStrength.getRssi();
            return;
        }
        Field field = this.M;
        if (field != null) {
            try {
                int i3 = field.getInt(cellSignalStrength);
                if (i3 != Integer.MAX_VALUE) {
                    cellInfo2.LteCqi = i3;
                }
            } catch (Throwable th) {
                h1.a(th);
            }
        }
        Field field2 = this.K;
        if (field2 != null) {
            try {
                cellInfo2.LteRsrq = field2.getInt(cellSignalStrength);
            } catch (Throwable th2) {
                h1.a(th2);
            }
        }
        Field field3 = this.L;
        if (field3 != null) {
            try {
                cellInfo2.LteRssnr = field3.getInt(cellSignalStrength);
            } catch (Throwable th3) {
                h1.a(th3);
            }
        }
        Field field4 = this.J;
        if (field4 != null) {
            try {
                cellInfo2.LteRssi = field4.getInt(cellSignalStrength);
            } catch (Throwable th4) {
                h1.a(th4);
            }
        }
    }

    private void c(boolean z) {
        int i2;
        SubscriptionManager subscriptionManager;
        if (z && this.o != null && this.j.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0 && Build.VERSION.SDK_INT >= 22 && (subscriptionManager = (SubscriptionManager) this.j.getSystemService("telephony_subscription_service")) != null) {
            subscriptionManager.addOnSubscriptionsChangedListener(this.o);
        }
        if (this.h != null) {
            int i3 = Build.VERSION.SDK_INT;
            if ((i3 >= 29 || this.j.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) && !(this.j.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0 && this.j.checkCallingOrSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0)) {
                i2 = InputDeviceCompat.SOURCE_KEYBOARD;
            } else {
                i2 = 273;
                if (i3 >= 17) {
                    i2 = 1297;
                }
            }
            if (this.l.size() == 0) {
                if (this.k == null) {
                    this.k = new o();
                }
                try {
                    this.h.listen(this.k, i2);
                    return;
                } catch (Throwable th) {
                    h1.a(th);
                    this.h.listen(this.k, InputDeviceCompat.SOURCE_KEYBOARD);
                    return;
                }
            }
            Iterator<o> it = this.l.iterator();
            while (it.hasNext()) {
                o next = it.next();
                TelephonyManager telephonyManager = null;
                SparseArray<TelephonyManager> sparseArray = this.i;
                if (sparseArray != null && sparseArray.size() > 0) {
                    telephonyManager = this.i.get(next.a());
                }
                if (telephonyManager == null) {
                    telephonyManager = this.h;
                }
                try {
                    telephonyManager.listen(next, i2);
                } catch (Throwable th2) {
                    h1.a(th2);
                    telephonyManager.listen(next, InputDeviceCompat.SOURCE_KEYBOARD);
                }
            }
        }
    }

    public static NetworkTypes d(int i2) {
        switch (i2) {
            case 1:
                return NetworkTypes.GPRS;
            case 2:
                return NetworkTypes.EDGE;
            case 3:
                return NetworkTypes.UMTS;
            case 4:
                return NetworkTypes.CDMA;
            case 5:
                return NetworkTypes.EVDO_0;
            case 6:
                return NetworkTypes.EVDO_A;
            case 7:
                return NetworkTypes.Cdma1xRTT;
            case 8:
                return NetworkTypes.HSDPA;
            case 9:
                return NetworkTypes.HSUPA;
            case 10:
                return NetworkTypes.HSPA;
            case 11:
                return NetworkTypes.IDEN;
            case 12:
                return NetworkTypes.EVDO_B;
            case 13:
                return NetworkTypes.LTE;
            case 14:
                return NetworkTypes.EHRPD;
            case 15:
                return NetworkTypes.HSPAP;
            case 16:
                return NetworkTypes.GSM;
            case 17:
                return NetworkTypes.TD_SCDMA;
            case 18:
                return NetworkTypes.WiFi;
            case 19:
                return NetworkTypes.LTE_CA;
            case 20:
                return NetworkTypes.NR;
            default:
                return NetworkTypes.Unknown;
        }
    }

    private void d(CellInfo cellInfo, com.startapp.networkTest.data.radio.CellInfo cellInfo2, long j2) {
        CellInfoNr cellInfoNr = (CellInfoNr) cellInfo;
        cellInfo2.IsRegistered = cellInfoNr.isRegistered();
        cellInfo2.CellNetworkType = CellNetworkTypes.Nr;
        cellInfo2.CellInfoAge = j2 - (cellInfoNr.getTimeStamp() / 1000000);
        CellIdentity cellIdentity = cellInfoNr.getCellIdentity();
        if (cellIdentity instanceof CellIdentityNr) {
            CellIdentityNr cellIdentityNr = (CellIdentityNr) cellIdentity;
            cellInfo2.Arfcn = cellIdentityNr.getNrarfcn();
            cellInfo2.LtePci = cellIdentityNr.getPci();
            cellInfo2.LteTac = cellIdentityNr.getTac();
            cellInfo2.CellId = cellIdentityNr.getNci();
            if (cellIdentityNr.getMccString() != null) {
                try {
                    cellInfo2.Mcc = Integer.parseInt(cellIdentityNr.getMccString());
                } catch (NumberFormatException e2) {
                    h1.b(e2);
                }
            }
            if (cellIdentityNr.getMncString() != null) {
                try {
                    cellInfo2.Mnc = Integer.parseInt(cellIdentityNr.getMncString());
                } catch (NumberFormatException e3) {
                    h1.b(e3);
                }
            }
        }
        CellSignalStrength cellSignalStrength = cellInfoNr.getCellSignalStrength();
        if (cellSignalStrength instanceof CellSignalStrengthNr) {
            CellSignalStrengthNr cellSignalStrengthNr = (CellSignalStrengthNr) cellSignalStrength;
            cellInfo2.Dbm = cellSignalStrengthNr.getDbm();
            cellInfo2.NrCsiRsrp = cellSignalStrengthNr.getCsiRsrp();
            cellInfo2.NrCsiRsrq = cellSignalStrengthNr.getCsiRsrq();
            cellInfo2.NrCsiSinr = cellSignalStrengthNr.getCsiSinr();
            cellInfo2.NrSsRsrp = cellSignalStrengthNr.getSsRsrp();
            cellInfo2.NrSsRsrq = cellSignalStrengthNr.getSsRsrq();
            cellInfo2.NrSsSinr = cellSignalStrengthNr.getSsSinr();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        SubscriptionManager subscriptionManager;
        if (z && this.o != null && this.j.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") == 0 && Build.VERSION.SDK_INT >= 22 && (subscriptionManager = (SubscriptionManager) this.j.getSystemService("telephony_subscription_service")) != null) {
            subscriptionManager.removeOnSubscriptionsChangedListener(this.o);
        }
        TelephonyManager telephonyManager = this.h;
        if (telephonyManager != null) {
            o oVar = this.k;
            if (oVar != null) {
                telephonyManager.listen(oVar, 0);
            }
            Iterator<o> it = this.l.iterator();
            while (it.hasNext()) {
                o next = it.next();
                TelephonyManager telephonyManager2 = null;
                SparseArray<TelephonyManager> sparseArray = this.i;
                if (sparseArray != null && sparseArray.size() > 0) {
                    telephonyManager2 = this.i.get(next.a());
                }
                if (telephonyManager2 == null) {
                    telephonyManager2 = this.h;
                }
                telephonyManager2.listen(next, 0);
            }
        }
    }

    private static PreferredNetworkTypes e(int i2) {
        switch (i2) {
            case 0:
                return PreferredNetworkTypes.WCDMA_PREF;
            case 1:
                return PreferredNetworkTypes.GSM_ONLY;
            case 2:
                return PreferredNetworkTypes.WCDMA_ONLY;
            case 3:
                return PreferredNetworkTypes.GSM_UMTS;
            case 4:
                return PreferredNetworkTypes.CDMA;
            case 5:
                return PreferredNetworkTypes.CDMA_NO_EVDO;
            case 6:
                return PreferredNetworkTypes.EVDO_NO_CDMA;
            case 7:
                return PreferredNetworkTypes.GLOBAL;
            case 8:
                return PreferredNetworkTypes.LTE_CDMA_EVDO;
            case 9:
                return PreferredNetworkTypes.LTE_GSM_WCDMA;
            case 10:
                return PreferredNetworkTypes.LTE_CDMA_EVDO_GSM_WCDMA;
            case 11:
                return PreferredNetworkTypes.LTE_ONLY;
            case 12:
                return PreferredNetworkTypes.LTE_WCDMA;
            case 13:
                return PreferredNetworkTypes.TDSCDMA_ONLY;
            case 14:
                return PreferredNetworkTypes.TDSCDMA_WCDMA;
            case 15:
                return PreferredNetworkTypes.LTE_TDSCDMA;
            case 16:
                return PreferredNetworkTypes.TDSCDMA_GSM;
            case 17:
                return PreferredNetworkTypes.LTE_TDSCDMA_GSM;
            case 18:
                return PreferredNetworkTypes.TDSCDMA_GSM_WCDMA;
            case 19:
                return PreferredNetworkTypes.LTE_TDSCDMA_WCDMA;
            case 20:
                return PreferredNetworkTypes.LTE_TDSCDMA_GSM_WCDMA;
            case 21:
                return PreferredNetworkTypes.TDSCDMA_CDMA_EVDO_GSM_WCDMA;
            case 22:
                return PreferredNetworkTypes.LTE_TDSCDMA_CDMA_EVDO_GSM_WCDMA;
            case 23:
                return PreferredNetworkTypes.NR_ONLY;
            case 24:
                return PreferredNetworkTypes.NR_LTE;
            case 25:
                return PreferredNetworkTypes.NR_LTE_CDMA_EVDO;
            case 26:
                return PreferredNetworkTypes.NR_LTE_GSM_WCDMA;
            case 27:
                return PreferredNetworkTypes.NR_LTE_CDMA_EVDO_GSM_WCDMA;
            case 28:
                return PreferredNetworkTypes.NR_LTE_WCDMA;
            case 29:
                return PreferredNetworkTypes.NR_LTE_TDSCDMA;
            case 30:
                return PreferredNetworkTypes.NR_LTE_TDSCDMA_GSM;
            case 31:
                return PreferredNetworkTypes.NR_LTE_TDSCDMA_WCDMA;
            case 32:
                return PreferredNetworkTypes.NR_LTE_TDSCDMA_GSM_WCDMA;
            case 33:
                return PreferredNetworkTypes.NR_LTE_TDSCDMA_CDMA_EVDO_GSM_WCDMA;
            default:
                return PreferredNetworkTypes.Unknown;
        }
    }

    private void e(CellInfo cellInfo, com.startapp.networkTest.data.radio.CellInfo cellInfo2, long j2) {
        CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
        cellInfo2.IsRegistered = cellInfoWcdma.isRegistered();
        cellInfo2.CellNetworkType = CellNetworkTypes.Wcdma;
        cellInfo2.CellInfoAge = j2 - (cellInfoWcdma.getTimeStamp() / 1000000);
        CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
        if (cellIdentity.getMcc() != Integer.MAX_VALUE) {
            cellInfo2.Mcc = cellIdentity.getMcc();
        }
        if (cellIdentity.getMnc() != Integer.MAX_VALUE) {
            cellInfo2.Mnc = cellIdentity.getMnc();
        }
        if (cellIdentity.getCid() != Integer.MAX_VALUE) {
            int cid = cellIdentity.getCid();
            cellInfo2.Cid = cid;
            cellInfo2.CellId = cid;
        }
        if (cellIdentity.getLac() != Integer.MAX_VALUE) {
            cellInfo2.Lac = cellIdentity.getLac();
        }
        if (cellIdentity.getPsc() != Integer.MAX_VALUE) {
            cellInfo2.Psc = cellIdentity.getPsc();
        }
        if (Build.VERSION.SDK_INT >= 24 && cellIdentity.getUarfcn() != Integer.MAX_VALUE) {
            cellInfo2.Arfcn = cellIdentity.getUarfcn();
        }
        cellInfo2.Dbm = cellInfoWcdma.getCellSignalStrength().getDbm();
    }

    private static CellConnectionStatus f(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? CellConnectionStatus.Unknown : CellConnectionStatus.Secondary : CellConnectionStatus.Primary : CellConnectionStatus.None;
    }

    private boolean k(int i2) {
        return this.p.getSimInfoSubId(i2).SubscriptionId != -1;
    }

    private boolean m() {
        return Build.VERSION.SDK_INT < 17 ? Settings.System.getInt(this.U, "airplane_mode_on", 0) != 0 : Settings.Global.getInt(this.U, "airplane_mode_on", 0) != 0;
    }

    private List<f> q() {
        Network[] allNetworks;
        ArrayList arrayList = new ArrayList();
        if (this.m != null && Build.VERSION.SDK_INT >= 21 && this.j.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0 && (allNetworks = this.m.getAllNetworks()) != null && allNetworks.length > 0) {
            for (Network network : allNetworks) {
                NetworkCapabilities networkCapabilities = this.m.getNetworkCapabilities(network);
                if (networkCapabilities != null && networkCapabilities.hasTransport(0)) {
                    f fVar = new f(this, null);
                    NetworkInfo networkInfo = this.m.getNetworkInfo(network);
                    LinkProperties linkProperties = this.m.getLinkProperties(network);
                    ArrayList arrayList2 = new ArrayList();
                    if (networkCapabilities.hasCapability(4)) {
                        arrayList2.add("ims");
                    }
                    if (networkCapabilities.hasCapability(1)) {
                        arrayList2.add("supl");
                    }
                    if (networkCapabilities.hasCapability(9)) {
                        arrayList2.add("xcap");
                    }
                    if (networkCapabilities.hasCapability(2)) {
                        arrayList2.add("dun");
                    }
                    if (networkCapabilities.hasCapability(5)) {
                        arrayList2.add("cbs");
                    }
                    if (networkCapabilities.hasCapability(3)) {
                        arrayList2.add("fota");
                    }
                    if (networkCapabilities.hasCapability(10)) {
                        arrayList2.add("emergency");
                    }
                    if (networkCapabilities.hasCapability(7)) {
                        arrayList2.add("ia");
                    }
                    if (networkCapabilities.hasCapability(0)) {
                        arrayList2.add("mms");
                    }
                    if (networkCapabilities.hasCapability(8)) {
                        arrayList2.add("rcs");
                    }
                    if (networkCapabilities.hasCapability(23)) {
                        arrayList2.add("mcx");
                    }
                    fVar.d = TextUtils.join(",", arrayList2);
                    if (networkInfo != null) {
                        fVar.b = networkInfo.getExtraInfo();
                        fVar.a = networkInfo.getSubtype();
                        fVar.k = WifiDetailedStates.a(networkInfo.getDetailedState());
                    }
                    if (linkProperties != null) {
                        fVar.e = o1.a(networkCapabilities);
                        fVar.g = o1.b(networkCapabilities);
                        fVar.f = o1.a(linkProperties);
                        String interfaceName = linkProperties.getInterfaceName();
                        if (interfaceName != null) {
                            try {
                                fVar.h = c2.b(interfaceName);
                                fVar.i = c2.a(interfaceName);
                            } catch (Throwable th) {
                                h1.a(th);
                            }
                            fVar.j = interfaceName;
                        }
                    }
                    arrayList.add(fVar);
                }
            }
        }
        return arrayList;
    }

    private void r() {
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Field declaredField = CellSignalStrengthLte.class.getDeclaredField("mSignalStrength");
                this.J = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e2) {
                h1.b(e2);
            }
            try {
                Field declaredField2 = CellSignalStrengthLte.class.getDeclaredField("mRsrq");
                this.K = declaredField2;
                declaredField2.setAccessible(true);
            } catch (NoSuchFieldException e3) {
                h1.b(e3);
            }
            try {
                Field declaredField3 = CellSignalStrengthLte.class.getDeclaredField("mRssnr");
                this.L = declaredField3;
                declaredField3.setAccessible(true);
            } catch (NoSuchFieldException e4) {
                h1.b(e4);
            }
            try {
                Field declaredField4 = CellSignalStrengthLte.class.getDeclaredField("mCqi");
                this.M = declaredField4;
                declaredField4.setAccessible(true);
            } catch (NoSuchFieldException e5) {
                h1.b(e5);
            }
        }
    }

    private void s() {
        if (Build.VERSION.SDK_INT >= 25) {
            try {
                Field declaredField = ServiceState.class.getDeclaredField("mIsUsingCarrierAggregation");
                this.I = declaredField;
                declaredField.setAccessible(true);
            } catch (Throwable th) {
                h1.b(th);
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                this.H = SignalStrength.class.getDeclaredMethod("isUsingCarrierAggregation", new Class[0]);
            } catch (Throwable th2) {
                h1.b(th2);
            }
        }
    }

    private void t() {
        if (Build.VERSION.SDK_INT < 29) {
            try {
                this.t = SignalStrength.class.getDeclaredMethod("getLteSignalStrength", new Class[0]);
            } catch (Throwable th) {
                h1.b(th);
            }
            try {
                this.w = SignalStrength.class.getDeclaredMethod("getLteCqi", new Class[0]);
            } catch (Throwable th2) {
                h1.b(th2);
            }
            try {
                this.x = SignalStrength.class.getDeclaredMethod("getLteRsrp", new Class[0]);
            } catch (Throwable th3) {
                h1.b(th3);
            }
            try {
                this.y = SignalStrength.class.getDeclaredMethod("getLteRsrq", new Class[0]);
            } catch (Throwable th4) {
                h1.b(th4);
            }
            try {
                this.z = SignalStrength.class.getDeclaredMethod("getLteRssnr", new Class[0]);
            } catch (Throwable th5) {
                h1.b(th5);
            }
            try {
                this.u = SignalStrength.class.getDeclaredMethod("getLteDbm", new Class[0]);
            } catch (Throwable th6) {
                h1.b(th6);
            }
            try {
                this.s = SignalStrength.class.getDeclaredMethod("getDbm", new Class[0]);
            } catch (Throwable th7) {
                h1.b(th7);
            }
        }
        try {
            this.v = SignalStrength.class.getDeclaredMethod("getGsmEcno", new Class[0]);
        } catch (Throwable th8) {
            h1.b(th8);
        }
        try {
            Field declaredField = SignalStrength.class.getDeclaredField("mWcdmaRscp");
            this.C = declaredField;
            declaredField.setAccessible(true);
        } catch (Throwable th9) {
            h1.b(th9);
        }
        try {
            Field declaredField2 = SignalStrength.class.getDeclaredField("mWcdmaEcio");
            this.D = declaredField2;
            declaredField2.setAccessible(true);
        } catch (Throwable th10) {
            h1.b(th10);
        }
        if (Build.VERSION.SDK_INT >= 28) {
            try {
                Field declaredField3 = SignalStrength.class.getDeclaredField("mNrRsrp");
                this.E = declaredField3;
                declaredField3.setAccessible(true);
            } catch (Throwable th11) {
                h1.b(th11);
            }
            try {
                Field declaredField4 = SignalStrength.class.getDeclaredField("mNrRsrq");
                this.F = declaredField4;
                declaredField4.setAccessible(true);
            } catch (Throwable th12) {
                h1.b(th12);
            }
            try {
                Field declaredField5 = SignalStrength.class.getDeclaredField("mNrRssnr");
                this.G = declaredField5;
                declaredField5.setAccessible(true);
            } catch (Throwable th13) {
                h1.b(th13);
            }
        }
    }

    private void u() {
        try {
            this.N = this.h.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
        } catch (Throwable th) {
            h1.b(th);
        }
        try {
            this.O = this.h.getClass().getDeclaredMethod("getDataEnabled", Integer.TYPE);
        } catch (Throwable th2) {
            h1.b(th2);
        }
        try {
            this.P = this.h.getClass().getDeclaredMethod("isNetworkRoaming", Integer.TYPE);
        } catch (Throwable th3) {
            h1.b(th3);
        }
        try {
            this.Q = this.h.getClass().getDeclaredMethod("getNetworkType", Integer.TYPE);
        } catch (Throwable th4) {
            h1.b(th4);
        }
        try {
            this.R = this.h.getClass().getDeclaredMethod("getNetworkOperatorName", Integer.TYPE);
        } catch (Throwable th5) {
            h1.b(th5);
        }
        try {
            this.S = this.h.getClass().getDeclaredMethod("getNetworkOperator", Integer.TYPE);
        } catch (Throwable th6) {
            h1.b(th6);
        }
        try {
            this.T = this.h.getClass().getDeclaredMethod("getNetworkOperatorForSubscription", Integer.TYPE);
        } catch (Throwable th7) {
            h1.b(th7);
        }
        try {
            Method declaredMethod = this.h.getClass().getDeclaredMethod("getVoiceNetworkType", null);
            if (!Modifier.isAbstract(declaredMethod.getModifiers())) {
                this.A = declaredMethod;
                declaredMethod.setAccessible(true);
            }
        } catch (Throwable th8) {
            h1.b(th8);
        }
        try {
            Method declaredMethod2 = this.h.getClass().getDeclaredMethod("getVoiceNetworkType", Integer.TYPE);
            if (Modifier.isAbstract(declaredMethod2.getModifiers())) {
                return;
            }
            this.B = declaredMethod2;
            declaredMethod2.setAccessible(true);
        } catch (Throwable th9) {
            h1.b(th9);
        }
    }

    private void v() {
        if (Build.VERSION.SDK_INT >= 22) {
            this.o = new a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        this.i = new SparseArray<>();
        int i2 = 0;
        while (true) {
            int[] iArr = this.V;
            if (i2 >= iArr.length) {
                return;
            }
            this.i.put(iArr[i2], this.h.createForSubscriptionId(iArr[i2]));
            i2++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        m0 f2 = v.f(this.j);
        this.p = f2;
        ArrayList<n0> arrayList = f2.SimInfos;
        n0[] n0VarArr = (n0[]) arrayList.toArray(new n0[arrayList.size()]);
        int[] iArr = new int[n0VarArr.length];
        for (int i2 = 0; i2 < n0VarArr.length; i2++) {
            iArr[i2] = n0VarArr[i2].SubscriptionId;
        }
        this.V = iArr;
    }

    public ThreeStateShort a(NetworkRegistrationInfo[] networkRegistrationInfoArr) {
        if (networkRegistrationInfoArr != null) {
            for (NetworkRegistrationInfo networkRegistrationInfo : networkRegistrationInfoArr) {
                if (networkRegistrationInfo.Domain.equals("PS")) {
                    return networkRegistrationInfo.CarrierAggregation;
                }
            }
        }
        return ThreeStateShort.Unknown;
    }

    public void a(y yVar) {
        if (yVar != null) {
            if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
                this.g.post(new k1(new d(yVar)));
            } else {
                if (this.W.contains(yVar)) {
                    return;
                }
                this.W.add(yVar);
            }
        }
    }

    public ApnInfo[] a() {
        l c2;
        ArrayList arrayList = new ArrayList();
        for (f fVar : q()) {
            ApnInfo apnInfo = new ApnInfo();
            apnInfo.Apn = fVar.b;
            apnInfo.TxBytes = fVar.h;
            apnInfo.RxBytes = fVar.i;
            apnInfo.ApnTypes = fVar.d;
            apnInfo.Capabilities = fVar.e;
            apnInfo.SubscriptionId = fVar.g;
            apnInfo.PcscfAddresses = fVar.f;
            apnInfo.MobileDataConnectionState = fVar.k;
            apnInfo.NetworkType = d(fVar.a);
            apnInfo.Reason = this.n.a(fVar.g, fVar.d);
            if (apnInfo.ApnTypes.contains("ims") && (c2 = this.n.c(fVar.g)) != null) {
                apnInfo.SamsungSipError = c2.a;
                apnInfo.SamsungImsServices = c2.b;
            }
            arrayList.add(apnInfo);
        }
        return (ApnInfo[]) arrayList.toArray(new ApnInfo[arrayList.size()]);
    }

    public com.startapp.networkTest.data.radio.CellInfo[] a(boolean z) {
        List<CellInfo> list;
        if (this.j.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0 || (this.j.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0 && Build.VERSION.SDK_INT >= 29)) {
            return new com.startapp.networkTest.data.radio.CellInfo[0];
        }
        ArrayList arrayList = new ArrayList();
        TelephonyManager telephonyManager = this.h;
        if (telephonyManager != null && Build.VERSION.SDK_INT >= 18) {
            List<CellInfo> list2 = null;
            if (z || (list = this.r) == null) {
                try {
                    list2 = telephonyManager.getAllCellInfo();
                } catch (Throwable th) {
                    h1.a(th);
                }
                list = (this.r == null || !(list2 == null || list2.isEmpty())) ? list2 : this.r;
            }
            if (list == null) {
                return new com.startapp.networkTest.data.radio.CellInfo[0];
            }
            long uptimeMillis = SystemClock.uptimeMillis();
            for (CellInfo cellInfo : list) {
                com.startapp.networkTest.data.radio.CellInfo cellInfo2 = new com.startapp.networkTest.data.radio.CellInfo();
                int i2 = Build.VERSION.SDK_INT;
                if (i2 >= 28) {
                    cellInfo2.CellConnectionStatus = f(cellInfo.getCellConnectionStatus());
                }
                if (cellInfo instanceof CellInfoGsm) {
                    b(cellInfo, cellInfo2, uptimeMillis);
                } else if (cellInfo instanceof CellInfoLte) {
                    c(cellInfo, cellInfo2, uptimeMillis);
                } else if (cellInfo instanceof CellInfoWcdma) {
                    e(cellInfo, cellInfo2, uptimeMillis);
                } else if (cellInfo instanceof CellInfoCdma) {
                    a(cellInfo, cellInfo2, uptimeMillis);
                } else if (i2 >= 29 && (cellInfo instanceof CellInfoNr)) {
                    try {
                        d(cellInfo, cellInfo2, uptimeMillis);
                    } catch (Throwable th2) {
                        h1.a(th2);
                    }
                }
                arrayList.add(cellInfo2);
            }
        }
        return (com.startapp.networkTest.data.radio.CellInfo[]) arrayList.toArray(new com.startapp.networkTest.data.radio.CellInfo[arrayList.size()]);
    }

    public m0 b() {
        return this.p;
    }

    public NetworkTypes b(NetworkRegistrationInfo[] networkRegistrationInfoArr) {
        if (networkRegistrationInfoArr != null) {
            for (NetworkRegistrationInfo networkRegistrationInfo : networkRegistrationInfoArr) {
                if (networkRegistrationInfo.Domain.equals("PS")) {
                    return a(networkRegistrationInfo.NetworkTechnology);
                }
            }
        }
        return NetworkTypes.Unknown;
    }

    public void b(y yVar) {
        if (yVar != null) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                this.W.remove(yVar);
            } else {
                this.g.post(new k1(new e(yVar)));
            }
        }
    }

    public String c(NetworkRegistrationInfo[] networkRegistrationInfoArr) {
        if (networkRegistrationInfoArr == null) {
            return "Unknown";
        }
        for (NetworkRegistrationInfo networkRegistrationInfo : networkRegistrationInfoArr) {
            if (networkRegistrationInfo.Domain.equals("PS") && networkRegistrationInfo.TransportType.equals("WWAN")) {
                return networkRegistrationInfo.NrState;
            }
        }
        return "Unknown";
    }

    public com.startapp.networkTest.data.radio.CellInfo[] c() {
        return a(true);
    }

    public ConnectionTypes d() {
        NetworkInfo activeNetworkInfo;
        ConnectionTypes connectionTypes = ConnectionTypes.Unknown;
        if (this.m == null || this.j.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0 || (activeNetworkInfo = this.m.getActiveNetworkInfo()) == null) {
            return connectionTypes;
        }
        int type = activeNetworkInfo.getType();
        return type != 0 ? type != 1 ? type != 6 ? type != 7 ? type != 9 ? connectionTypes : ConnectionTypes.Ethernet : ConnectionTypes.Bluetooth : ConnectionTypes.WiMAX : ConnectionTypes.WiFi : ConnectionTypes.Mobile;
    }

    public ThreeStateShort d(NetworkRegistrationInfo[] networkRegistrationInfoArr) {
        if (networkRegistrationInfoArr != null) {
            for (NetworkRegistrationInfo networkRegistrationInfo : networkRegistrationInfoArr) {
                if (networkRegistrationInfo.Domain.equals("PS") && networkRegistrationInfo.TransportType.equals("WWAN")) {
                    return networkRegistrationInfo.NrAvailable;
                }
            }
        }
        return ThreeStateShort.Unknown;
    }

    public Future<ApnInfo[]> e() {
        return ThreadManager.b().a().submit(new c());
    }

    public Future<com.startapp.networkTest.data.radio.CellInfo[]> f() {
        return ThreadManager.b().a().submit(new b());
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x03a9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0356  */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.startapp.networkTest.data.RadioInfo g() {
        /*
            Method dump skipped, instructions count: 941
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.w.g():com.startapp.networkTest.data.RadioInfo");
    }

    public NetworkRegistrationInfo[] g(int i2) {
        NetworkRegistrationInfo[] b2 = this.n.b(i2);
        if (b2 == null) {
            return new NetworkRegistrationInfo[0];
        }
        m d2 = this.n.d(i2);
        for (NetworkRegistrationInfo networkRegistrationInfo : b2) {
            if (d2 != null && d2.b > 0) {
                long elapsedRealtime = SystemClock.elapsedRealtime() - d2.b;
                networkRegistrationInfo.Age = elapsedRealtime > 2147483647L ? Integer.MAX_VALUE : (int) elapsedRealtime;
            }
        }
        return b2;
    }

    public RadioInfo h() {
        try {
            return h(this.p.DefaultDataSimId);
        } catch (Throwable th) {
            h1.a(th);
            return new RadioInfo();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0435  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.startapp.networkTest.data.RadioInfo h(int r17) {
        /*
            Method dump skipped, instructions count: 1172
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.w.h(int):com.startapp.networkTest.data.RadioInfo");
    }

    public RadioInfo i() {
        try {
            return h(this.p.DefaultSmsSimId);
        } catch (Throwable th) {
            h1.a(th);
            return new RadioInfo();
        }
    }

    public NetworkTypes i(int i2) {
        if (k(i2) && x1.b(this.j)) {
            SparseArray<TelephonyManager> sparseArray = this.i;
            if (sparseArray == null || sparseArray.get(i2) == null || Build.VERSION.SDK_INT < 24) {
                Method method = this.B;
                if (method != null) {
                    try {
                        return d(((Integer) method.invoke(this.h, Integer.valueOf(i2))).intValue());
                    } catch (Throwable th) {
                        h1.a(th);
                    }
                }
            } else {
                try {
                    return d(this.i.get(i2).getVoiceNetworkType());
                } catch (SecurityException e2) {
                    h1.b(e2);
                }
            }
        }
        return k();
    }

    public RadioInfo j() {
        try {
            return h(this.p.DefaultVoiceSimId);
        } catch (Throwable th) {
            h1.a(th);
            return new RadioInfo();
        }
    }

    public boolean j(int i2) {
        Method method = this.P;
        if (method == null) {
            return p();
        }
        try {
            return ((Boolean) method.invoke(this.h, Integer.valueOf(i2))).booleanValue();
        } catch (Throwable th) {
            h1.a(th);
            return p();
        }
    }

    public NetworkTypes k() {
        if (x1.b(this.j)) {
            TelephonyManager telephonyManager = this.h;
            if (telephonyManager == null || Build.VERSION.SDK_INT < 24) {
                Method method = this.A;
                if (method != null) {
                    try {
                        return d(((Integer) method.invoke(telephonyManager, new Object[0])).intValue());
                    } catch (Throwable th) {
                        h1.a(th);
                    }
                }
            } else {
                try {
                    return d(telephonyManager.getVoiceNetworkType());
                } catch (SecurityException e2) {
                    h1.b(e2);
                }
            }
        }
        return NetworkTypes.Unknown;
    }

    public boolean l() {
        NetworkInfo activeNetworkInfo;
        if (this.m == null || this.j.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0 || (activeNetworkInfo = this.m.getActiveNetworkInfo()) == null) {
            return false;
        }
        return activeNetworkInfo.isConnected();
    }

    public ThreeStateShort n() {
        ConnectivityManager connectivityManager;
        ThreeStateShort threeStateShort = ThreeStateShort.Unknown;
        if (Build.VERSION.SDK_INT < 23 || this.j.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0 || (connectivityManager = this.m) == null) {
            return threeStateShort;
        }
        NetworkCapabilities networkCapabilities = this.m.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        return networkCapabilities != null ? networkCapabilities.hasTransport(4) ? ThreeStateShort.Yes : ThreeStateShort.No : threeStateShort;
    }

    public ThreeStateShort o() {
        return (Build.VERSION.SDK_INT < 16 || this.j.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") != 0) ? ThreeStateShort.Unknown : this.m.isActiveNetworkMetered() ? ThreeStateShort.Yes : ThreeStateShort.No;
    }

    public boolean p() {
        return this.h.isNetworkRoaming();
    }

    public void x() {
        try {
            c(true);
            b(this.j);
        } catch (Throwable th) {
            h1.a(th);
        }
    }

    public void y() {
        try {
            d(true);
            c(this.j);
        } catch (Throwable th) {
            h1.a(th);
        }
    }
}
