package com.startapp;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.startapp.networkTest.controller.LocationController;
import com.startapp.networkTest.enums.CtCriteriaTypes;
import com.startapp.networkTest.enums.voice.CallStates;
import com.startapp.networkTest.results.ConnectivityTestResult;
import com.startapp.networkTest.results.LatencyResult;
import com.startapp.networkTest.speedtest.SpeedtestEngineError;
import com.startapp.networkTest.speedtest.SpeedtestEngineStatus;
import com.startapp.networkTest.threads.ThreadManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
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
public class a1 {
    private static final boolean a = false;
    private static final String b = "a1";
    private static final int c = 30000;
    private static final String d = "\r\n";
    private Context e;
    private w f;
    private x g;
    private LocationController h;
    private t i;
    private e1 j;
    private String k;
    private String l;
    private String m;
    private String n;
    private Random o;
    private float p;
    private boolean q;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends AsyncTask<Void, String, ConnectivityTestResult> implements f1 {
        private ConnectivityTestResult a;
        private q0 b;

        /* compiled from: StartAppSDK */
        /* renamed from: com.startapp.a1$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class C0055a implements Comparator<b1> {
            public C0055a() {
            }

            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(b1 b1Var, b1 b1Var2) {
                return b1Var.DNSSuccess - b1Var2.DNSSuccess;
            }
        }

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class b implements Comparator<b1> {
            public b() {
            }

            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(b1 b1Var, b1 b1Var2) {
                return b1Var.TCPSuccess - b1Var2.TCPSuccess;
            }
        }

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class c implements Comparator<b1> {
            public c() {
            }

            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(b1 b1Var, b1 b1Var2) {
                return b1Var.successfulTests - b1Var2.successfulTests;
            }
        }

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class d implements Comparator<b1> {
            public d() {
            }

            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(b1 b1Var, b1 b1Var2) {
                return b1Var.totalTests - b1Var2.totalTests;
            }
        }

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class e {
            public final int a;
            public final String b;
            public final boolean c;

            public e(int i, String str, boolean z) {
                this.a = i;
                this.b = str;
                this.c = z;
            }
        }

        public a() {
        }

        private e a(InputStream inputStream) throws IOException {
            boolean z;
            byte[] bArr = new byte[1024];
            int i = 0;
            int i2 = 0;
            while (true) {
                int read = inputStream.read();
                z = true;
                i++;
                if (read == 10) {
                    z = false;
                    break;
                }
                if (read < 0) {
                    break;
                }
                int i3 = i2 + 1;
                bArr[i2] = (byte) read;
                if (i3 == bArr.length) {
                    bArr = Arrays.copyOf(bArr, i3 + 1024);
                }
                i2 = i3;
            }
            if (i2 > 0 && bArr[i2 - 1] == 13) {
                i2--;
            }
            return new e(i, new String(bArr, 0, i2, "UTF-8"), z);
        }

        private List<b1> a(String[] strArr, CtCriteriaTypes ctCriteriaTypes) {
            LinkedList linkedList = new LinkedList();
            LinkedList linkedList2 = new LinkedList();
            Set<String> f = s.c().f();
            LinkedList<b1> linkedList3 = new LinkedList();
            if (f != null) {
                Iterator<String> it = f.iterator();
                while (it.hasNext()) {
                    b1 b1Var = (b1) v1.a(it.next(), b1.class);
                    if (b1Var != null) {
                        linkedList3.add(b1Var);
                    }
                }
            }
            for (String str : strArr) {
                b1 b1Var2 = new b1();
                b1Var2.address = str;
                linkedList2.add(b1Var2);
            }
            for (b1 b1Var3 : linkedList3) {
                for (int i = 0; i < linkedList2.size(); i++) {
                    if (((b1) linkedList2.get(i)).address.equals(b1Var3.address)) {
                        linkedList2.set(i, b1Var3);
                    }
                }
            }
            int ordinal = ctCriteriaTypes.ordinal();
            if (ordinal == 0) {
                Collections.sort(linkedList2, new d());
                return new LinkedList(linkedList2);
            }
            if (ordinal == 1) {
                Collections.sort(linkedList2, new C0055a());
                return new LinkedList(linkedList2);
            }
            if (ordinal == 2) {
                Collections.sort(linkedList2, new b());
                return new LinkedList(linkedList2);
            }
            if (ordinal == 3) {
                Collections.sort(linkedList2, new c());
                return new LinkedList(linkedList2);
            }
            if (ordinal != 4) {
                return ordinal != 5 ? linkedList : linkedList2;
            }
            Collections.shuffle(linkedList2, new Random(System.nanoTime()));
            return new LinkedList(linkedList2);
        }

        private void a(List<b1> list) {
            HashSet hashSet = new HashSet();
            Iterator<b1> it = list.iterator();
            while (it.hasNext()) {
                hashSet.add(it.next().toString());
            }
            s.c().a(hashSet);
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:45:0x004e -> B:23:0x0051). Please report as a decompilation issue!!! */
        private boolean a() {
            BufferedReader bufferedReader;
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("ping -W 3 -c 1 -s 56 127.0.0.1").getInputStream()));
                    try {
                        bufferedReader.readLine();
                        String readLine = bufferedReader.readLine();
                        if (readLine != null && readLine.length() > 0) {
                            if (readLine.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR).length == 8) {
                                try {
                                    bufferedReader.close();
                                } catch (Throwable th) {
                                    h1.b(th);
                                }
                                return true;
                            }
                        }
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            h1.a(th);
                            if (bufferedReader == null) {
                                return false;
                            }
                            bufferedReader.close();
                            return false;
                        } catch (Throwable th3) {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Throwable th4) {
                                    h1.b(th4);
                                }
                            }
                            throw th3;
                        }
                    }
                } catch (Throwable th5) {
                    th = th5;
                    bufferedReader = null;
                }
            } catch (Throwable th6) {
                h1.b(th6);
            }
            return false;
        }

        private CallStates b() {
            TelephonyManager telephonyManager = (TelephonyManager) a1.this.e.getSystemService("phone");
            if (telephonyManager == null) {
                return CallStates.Unknown;
            }
            int callState = telephonyManager.getCallState();
            return callState != 0 ? callState != 1 ? callState != 2 ? CallStates.Unknown : CallStates.Offhook : CallStates.Ringing : CallStates.Idle;
        }

        /* JADX WARN: Can't wrap try/catch for region: R(11:(2:14|15)|(7:387|388|20|21|(1:383)|25|(2:381|382)(16:29|(1:31)|32|(1:36)|37|(2:38|(23:246|247|248|249|250|251|252|253|254|255|256|(11:345|346|347|348|349|350|351|352|353|354|355)(10:258|259|260|(3:333|334|(3:336|337|267))|262|263|264|265|266|267)|268|269|270|271|272|(14:274|275|276|277|278|279|280|282|283|284|285|286|288|289)(5:315|316|317|318|320)|294|295|296|(2:298|299)(1:301)|300)(0))|(34:68|69|70|71|72|73|(3:221|222|223)(8:75|76|77|78|79|80|81|82)|84|85|(2:87|88)|90|103|104|(1:106)(1:207)|107|108|109|110|111|(3:112|113|(5:115|(2:117|(4:123|124|125|122)(1:119))(4:126|127|128|(2:191|192)(2:130|(1:132)(2:133|(2:137|(1:139))(1:190))))|120|121|122)(2:196|197))|142|143|144|145|(1:147)(1:186)|148|(3:149|150|(1:177)(2:152|(1:175)(2:176|155)))|156|(2:167|168)|158|(1:160)|161|162|163)(1:47)|(2:49|(1:51)(2:52|(2:54|(1:56))))|57|(1:59)|60|(1:62)|63|(1:65)|66|67))|19|20|21|(1:23)|383|25|(1:27)|381|382) */
        /* JADX WARN: Code restructure failed: missing block: B:179:0x0693, code lost:
        
            if (r18 < r10) goto L184;
         */
        /* JADX WARN: Code restructure failed: missing block: B:182:0x069d, code lost:
        
            throw new java.io.IOException("Could not read all bytes");
         */
        /* JADX WARN: Code restructure failed: missing block: B:385:0x00ab, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:386:0x00ac, code lost:
        
            com.startapp.h1.a(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x02b6, code lost:
        
            r31 = r7;
            r9 = r16;
            r12 = r17;
            r7 = r18;
            r4 = r19;
            r0 = r20;
            r16 = 0;
            r17 = 0;
         */
        /* JADX WARN: Removed duplicated region for block: B:106:0x0519 A[Catch: Exception -> 0x0705, all -> 0x0713, TryCatch #30 {all -> 0x0713, blocks: (B:69:0x041a, B:72:0x0420, B:222:0x0428, B:90:0x04fb, B:104:0x0505, B:106:0x0519, B:107:0x053e, B:110:0x055c, B:160:0x06bf, B:95:0x0724, B:173:0x06ed, B:174:0x06fa, B:211:0x04d5, B:214:0x0470), top: B:68:0x041a }] */
        /* JADX WARN: Removed duplicated region for block: B:115:0x05ba A[Catch: all -> 0x06e2, TryCatch #2 {all -> 0x06e2, blocks: (B:113:0x05b4, B:115:0x05ba, B:117:0x05cf, B:124:0x05e8, B:127:0x060b, B:130:0x0627, B:132:0x062f, B:133:0x063e, B:135:0x0642, B:137:0x0649, B:139:0x0651, B:195:0x0623, B:192:0x0615), top: B:112:0x05b4, inners: #1 }] */
        /* JADX WARN: Removed duplicated region for block: B:147:0x0678  */
        /* JADX WARN: Removed duplicated region for block: B:152:0x069e  */
        /* JADX WARN: Removed duplicated region for block: B:160:0x06bf A[Catch: Exception -> 0x06fb, all -> 0x0713, TRY_ENTER, TRY_LEAVE, TryCatch #30 {all -> 0x0713, blocks: (B:69:0x041a, B:72:0x0420, B:222:0x0428, B:90:0x04fb, B:104:0x0505, B:106:0x0519, B:107:0x053e, B:110:0x055c, B:160:0x06bf, B:95:0x0724, B:173:0x06ed, B:174:0x06fa, B:211:0x04d5, B:214:0x0470), top: B:68:0x041a }] */
        /* JADX WARN: Removed duplicated region for block: B:167:0x06ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:173:0x06ed A[Catch: Exception -> 0x06fb, all -> 0x0713, TRY_ENTER, TryCatch #30 {all -> 0x0713, blocks: (B:69:0x041a, B:72:0x0420, B:222:0x0428, B:90:0x04fb, B:104:0x0505, B:106:0x0519, B:107:0x053e, B:110:0x055c, B:160:0x06bf, B:95:0x0724, B:173:0x06ed, B:174:0x06fa, B:211:0x04d5, B:214:0x0470), top: B:68:0x041a }] */
        /* JADX WARN: Removed duplicated region for block: B:177:0x0691 A[EDGE_INSN: B:177:0x0691->B:178:0x0691 BREAK  A[LOOP:2: B:149:0x068a->B:175:?], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:186:0x0681  */
        /* JADX WARN: Removed duplicated region for block: B:196:0x066d A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:207:0x053c  */
        /* JADX WARN: Removed duplicated region for block: B:298:0x0922 A[DONT_GENERATE] */
        /* JADX WARN: Removed duplicated region for block: B:301:0x0925 A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:49:0x0773  */
        /* JADX WARN: Removed duplicated region for block: B:59:0x0826  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0836  */
        /* JADX WARN: Removed duplicated region for block: B:65:0x0845  */
        /* JADX WARN: Removed duplicated region for block: B:87:0x04a2 A[Catch: all -> 0x04d4, TRY_LEAVE, TryCatch #32 {all -> 0x04d4, blocks: (B:85:0x0494, B:87:0x04a2), top: B:84:0x0494 }] */
        /* JADX WARN: Removed duplicated region for block: B:98:0x074d A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.startapp.networkTest.results.ConnectivityTestResult doInBackground(java.lang.Void... r39) {
            /*
                Method dump skipped, instructions count: 2365
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.a1.a.doInBackground(java.lang.Void[]):com.startapp.networkTest.results.ConnectivityTestResult");
        }

        @Override // com.startapp.f1
        public void a(float f, int i) {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(ConnectivityTestResult connectivityTestResult) {
            a1.this.i.f(SystemClock.elapsedRealtime());
            if (a1.this.j != null) {
                a1.this.j.onConnectivityTestResult(connectivityTestResult);
            }
            if (connectivityTestResult == null) {
                if (a1.this.j != null) {
                    a1.this.j.a();
                    return;
                }
                return;
            }
            boolean z = false;
            if (a1.this.i.m() && connectivityTestResult.ServerIp.length() > 0) {
                q0 q0Var = new q0(this, a1.this.e);
                this.b = q0Var;
                q0Var.g(connectivityTestResult.CtId);
                this.b.b(connectivityTestResult.AirportCode);
                this.b.e(String.valueOf(connectivityTestResult.TimeInfo.TimestampMillis + connectivityTestResult.DurationDNS + connectivityTestResult.DurationTcpConnect + connectivityTestResult.DurationHttpReceive));
                this.b.a(s.b().LTR_LOCATIONPROVIDER());
                this.b.a(connectivityTestResult.ServerIp, 10, 200, 30000, 56, true);
                z = true;
            }
            if (z || a1.this.j == null) {
                return;
            }
            a1.this.j.a();
        }

        @Override // com.startapp.f1
        public void a(SpeedtestEngineStatus speedtestEngineStatus, SpeedtestEngineError speedtestEngineError, long j) {
            if (speedtestEngineStatus == SpeedtestEngineStatus.END || speedtestEngineStatus == SpeedtestEngineStatus.ABORTED) {
                this.b.c();
                if (a1.this.j != null) {
                    a1.this.j.onLatencyTestResult((LatencyResult) this.b.a());
                    a1.this.j.a();
                }
            }
        }

        @Override // com.startapp.f1
        public void b(float f, int i) {
        }

        @Override // com.startapp.f1
        public void c(float f, int i) {
        }
    }

    public a1(Context context) {
        this.e = context;
        this.i = new t(context);
        q b2 = s.b();
        this.k = b2.PROJECT_ID();
        this.l = b2.CONNECTIVITY_TEST_HOSTNAME();
        this.m = b2.CONNECTIVITY_TEST_FILENAME();
        this.n = b2.CONNECTIVITY_TEST_IP();
        this.o = new Random();
        this.p = b2.CONNECTIVITY_TEST_MIN_BATTERY_LEVEL();
        this.q = b2.CONNECTIVITY_TEST_ENABLED_IN_ROAMING();
        this.h = new LocationController(context);
        this.f = new w(context);
        this.g = new x(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        return (str == null || str.isEmpty()) ? "" : str.replaceAll("(?:[0-9]{1,3}\\.){3}[0-9]{1,3}", "XXX").replaceAll("([A-Fa-f0-9]{1,4}::?){1,7}[A-Fa-f0-9]{1,4}", "XXX");
    }

    public void a() {
        this.h.a(LocationController.ProviderMode.Passive);
        this.f.x();
        this.g.f();
    }

    public void a(e1 e1Var) {
        this.j = e1Var;
        if (Build.VERSION.SDK_INT < 11) {
            new a().execute(new Void[0]);
        } else {
            new a().executeOnExecutor(ThreadManager.b().a(), new Void[0]);
        }
    }

    public void b() {
        this.h.f();
        this.f.y();
        this.g.g();
    }
}
