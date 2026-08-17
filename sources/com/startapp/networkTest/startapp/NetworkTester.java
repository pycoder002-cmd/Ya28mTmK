package com.startapp.networkTest.startapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Looper;
import com.startapp.a1;
import com.startapp.e1;
import com.startapp.h1;
import com.startapp.k1;
import com.startapp.networkTest.results.ConnectivityTestResult;
import com.startapp.networkTest.results.LatencyResult;
import com.startapp.networkTest.startapp.CoverageMapperManager;
import com.startapp.r0;
import com.startapp.s;
import com.startapp.v1;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class NetworkTester {
    private static final String CTLT_CHECK_INTERVAL_KEY = "StartappCtLtCheckIntervalKey";
    private static final String CTLT_GUARD_DIFF_KEY = "StartappGuardDiffKey";
    private static final String CTLT_PREV_TIME_CHECK_KEY = "StartappCtLtPrevTimeCheckKey";
    public static final String LOG_TAG = "NetworkTester";
    private static final String P3WRAPPER_PREFS = "StartappP3WrapperPrefs";
    public static NetworkTester sInstance;
    public Thread mActiveThread;
    public ConnectivityTestListener mConnectivityTestListener;
    private Context mContext;
    public CoverageMapperManager mCoverageMapper;
    public r0 mForegroundTestManager;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static final class Config {
        public String PROJECT_ID = "20050";
        public String CONNECTIVITY_TEST_HOSTNAME = "d2to8y50b3n6dq.cloudfront.net";
        public String CONNECTIVITY_TEST_FILENAME = "/favicon.ico";
        public boolean CONNECTIVITY_TEST_ENABLED = true;
        public boolean NIR_COLLECT_CELLINFO = true;
        public boolean CT_COLLECT_CELLINFO = true;
        public int NIR_COLLECT_CELLINFO_THRESHOLD = 2;
        public String CONNECTIVITY_TEST_CDNCONFIG_URL = "https://d2to8y50b3n6dq.cloudfront.net/truststores/[PROJECTID]/cdnconfig.zip";
        public String GEOIP_URL = "https://geoip.api.c0nnectthed0ts.com/geoip/";
        public long FOREGROUND_TEST_CT_SCHEDULE_INTERVAL = 300000;
        public long FOREGROUND_TEST_CT_MIN_INTERVAL = 180000;
        public boolean FOREGROUND_TEST_CT_ENABLED = true;
        public boolean FOREGROUND_TEST_NIR_ENABLED = true;
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ Context a;
        public final /* synthetic */ b b;

        /* compiled from: StartAppSDK */
        /* renamed from: com.startapp.networkTest.startapp.NetworkTester$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public class C0059a implements e1 {
            public final /* synthetic */ boolean[] a;
            public final /* synthetic */ Looper b;
            public final /* synthetic */ a1 c;

            /* compiled from: StartAppSDK */
            /* renamed from: com.startapp.networkTest.startapp.NetworkTester$a$a$a, reason: collision with other inner class name */
            /* loaded from: classes3.dex */
            public class RunnableC0060a implements Runnable {
                public RunnableC0060a() {
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.this.b.a(false);
                }
            }

            public C0059a(boolean[] zArr, Looper looper, a1 a1Var) {
                this.a = zArr;
                this.b = looper;
                this.c = a1Var;
            }

            @Override // com.startapp.e1
            public void a() {
                this.c.b();
                Looper looper = this.b;
                if (looper != null) {
                    NetworkTester.sInstance.mActiveThread = null;
                    looper.quit();
                }
                ConnectivityTestListener connectivityTestListener = NetworkTester.sInstance.mConnectivityTestListener;
                if (connectivityTestListener != null) {
                    connectivityTestListener.onConnectivityTestFinished(new k1(new RunnableC0060a()));
                } else {
                    a.this.b.a(false);
                }
            }

            @Override // com.startapp.e1
            public void onConnectivityTestResult(ConnectivityTestResult connectivityTestResult) {
                boolean[] zArr = this.a;
                if (zArr[0]) {
                    Looper looper = this.b;
                    if (looper != null) {
                        NetworkTester.sInstance.mActiveThread = null;
                        looper.quit();
                        a.this.b.a(false);
                        return;
                    }
                    return;
                }
                zArr[0] = true;
                if (connectivityTestResult != null) {
                    boolean z = connectivityTestResult.Success;
                }
                ConnectivityTestListener connectivityTestListener = NetworkTester.sInstance.mConnectivityTestListener;
                if (connectivityTestListener != null) {
                    connectivityTestListener.onConnectivityTestResult(connectivityTestResult);
                }
            }

            @Override // com.startapp.e1
            public void onLatencyTestResult(LatencyResult latencyResult) {
                boolean[] zArr = this.a;
                if (zArr[1]) {
                    Looper looper = this.b;
                    if (looper != null) {
                        NetworkTester.sInstance.mActiveThread = null;
                        looper.quit();
                        a.this.b.a(false);
                        return;
                    }
                    return;
                }
                zArr[1] = true;
                if (latencyResult != null) {
                    boolean z = latencyResult.Success;
                }
                ConnectivityTestListener connectivityTestListener = NetworkTester.sInstance.mConnectivityTestListener;
                if (connectivityTestListener != null) {
                    connectivityTestListener.onLatencyTestResult(latencyResult);
                }
            }
        }

        public a(Context context, b bVar) {
            this.a = context;
            this.b = bVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Looper.prepare();
            Looper myLooper = Looper.myLooper();
            a1 a1Var = new a1(this.a.getApplicationContext());
            a1Var.a();
            a1Var.a(new C0059a(new boolean[2], myLooper, a1Var));
            Looper.loop();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface b {
        void a(boolean z);
    }

    private NetworkTester() {
    }

    public static void init(Context context, Config config) {
        if (context == null) {
            throw new IllegalArgumentException("context is null");
        }
        if (config == null) {
            throw new IllegalArgumentException("config is null");
        }
        String a2 = v1.a(config);
        if (a2 == null) {
            throw new IllegalArgumentException("Wrong format of config");
        }
        if (sInstance != null) {
            return;
        }
        NetworkTester networkTester = new NetworkTester();
        sInstance = networkTester;
        networkTester.mContext = context;
        s.a(context, a2.getBytes());
        sInstance.mCoverageMapper = new CoverageMapperManager(context);
        if (Build.VERSION.SDK_INT >= 14) {
            sInstance.mForegroundTestManager = new r0(context, sInstance.mCoverageMapper);
        }
    }

    public static int isAppInForeground() {
        r0 r0Var;
        NetworkTester networkTester = sInstance;
        if (networkTester == null || (r0Var = networkTester.mForegroundTestManager) == null) {
            return -1;
        }
        return r0Var.d();
    }

    public static boolean isPermissionGranted(Context context, String str) {
        try {
            return Build.VERSION.SDK_INT >= 23 ? context.checkSelfPermission(str) == 0 : context.checkCallingOrSelfPermission(str) == 0;
        } catch (Throwable th) {
            h1.a(th);
            return false;
        }
    }

    public static void runTests(Context context, b bVar) {
        CoverageMapperManager coverageMapperManager;
        CoverageMapperManager coverageMapperManager2;
        if (sInstance == null) {
            bVar.a(false);
            return;
        }
        if (!isPermissionGranted(context, "android.permission.ACCESS_FINE_LOCATION") && !isPermissionGranted(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            NetworkTester networkTester = sInstance;
            if (networkTester != null && (coverageMapperManager2 = networkTester.mCoverageMapper) != null) {
                coverageMapperManager2.e();
            }
            bVar.a(false);
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences(P3WRAPPER_PREFS, 0);
        long j = sharedPreferences.getLong(CTLT_GUARD_DIFF_KEY, 120000L);
        if (System.currentTimeMillis() - sharedPreferences.getLong(CTLT_PREV_TIME_CHECK_KEY, 0L) < sharedPreferences.getLong(CTLT_CHECK_INTERVAL_KEY, j) - j) {
            bVar.a(false);
            return;
        }
        if (!s.h()) {
            bVar.a(false);
            return;
        }
        if (sInstance.mActiveThread != null) {
            bVar.a(false);
            return;
        }
        sharedPreferences.edit().putLong(CTLT_PREV_TIME_CHECK_KEY, System.currentTimeMillis()).commit();
        NetworkTester networkTester2 = sInstance;
        if (networkTester2 != null && (coverageMapperManager = networkTester2.mCoverageMapper) != null) {
            coverageMapperManager.b();
        }
        if (networkTester2 != null) {
            networkTester2.mActiveThread = new Thread(new k1(new a(context, bVar)));
        }
        sInstance.mActiveThread.start();
    }

    public static void setOnConnectivityLatencyListener(ConnectivityTestListener connectivityTestListener) {
        NetworkTester networkTester = sInstance;
        if (networkTester != null) {
            networkTester.mConnectivityTestListener = connectivityTestListener;
        }
    }

    public static void setOnNetworkInfoListener(CoverageMapperManager.OnNetworkInfoResultListener onNetworkInfoResultListener) {
        CoverageMapperManager coverageMapperManager;
        NetworkTester networkTester = sInstance;
        if (networkTester == null || (coverageMapperManager = networkTester.mCoverageMapper) == null) {
            return;
        }
        coverageMapperManager.a(onNetworkInfoResultListener);
    }

    public static void startListening(long j, long j2) {
        NetworkTester networkTester = sInstance;
        if (networkTester == null) {
            return;
        }
        SharedPreferences sharedPreferences = networkTester.mContext.getSharedPreferences(P3WRAPPER_PREFS, 0);
        sharedPreferences.edit().putLong(CTLT_CHECK_INTERVAL_KEY, j).commit();
        sharedPreferences.edit().putLong(CTLT_GUARD_DIFF_KEY, j2).commit();
        CoverageMapperManager coverageMapperManager = networkTester.mCoverageMapper;
        if (coverageMapperManager != null) {
            coverageMapperManager.b();
        }
        r0 r0Var = networkTester.mForegroundTestManager;
        if (r0Var != null) {
            r0Var.e();
        }
    }

    public static void stopListening() {
        r0 r0Var;
        CoverageMapperManager coverageMapperManager;
        NetworkTester networkTester = sInstance;
        if (networkTester != null && (coverageMapperManager = networkTester.mCoverageMapper) != null) {
            coverageMapperManager.e();
        }
        if (networkTester == null || Build.VERSION.SDK_INT < 14 || (r0Var = networkTester.mForegroundTestManager) == null) {
            return;
        }
        r0Var.h();
    }
}
