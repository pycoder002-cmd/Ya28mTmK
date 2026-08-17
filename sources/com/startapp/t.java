package com.startapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import com.startapp.networkTest.controller.LocationController;
import com.startapp.networkTest.enums.AnonymizationLevel;
import com.startapp.s;
import java.util.Set;
import java.util.UUID;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class t {
    private static final String A = "P3INS_PFK_QOE_MANAGER_ENABLED";
    private static final String B = "P3INS_PFK_REGISTRATION_TIMESTAMP";
    private static final String C = "P3INS_PFK_IS_ALREADY_REGISTERED";
    private static final String D = "P3INS_PFK_SEND_REGISTRATION_TIMESTAMP_ENABLED";
    private static final String E = "P3INS_PFK_UPLOAD_EXTRA";
    private static final String F = "P3INS_PFK_VOWIFI_TEST_MANAGER_ENABLED";
    private static final String G = "P3INS_PFK_CT_CRITERIA_SERVER_LIST";
    private static final String H = "P3INS_PFK_LTR_CRITERIA_SERVER_LIST";
    private static final String I = "P3INS_PFK_CDN_CT_SERVER_LIST";
    private static final String J = "P3INS_PFK_CDN_CT_CRITERIA";
    private static final String K = "P3INS_PFK_CDN_LTR_SERVER_LIST";
    private static final String L = "P3INS_PFK_CDN_LTR_CRITERIA";
    private static final String M = "P3INS_PFK_CONNECTIVITY_TEST_CDNCONFIG_LAST_MODIFIED";
    private static final String N = "P3INS_PFK_CONNECTIVITY_TEST_CDNCONFIG_LAST_CHECK";
    private static final String O = "P3INS_PFK_WIFI_SCAN_TIMESTAMP";
    private static final String P = "P3INS_PFK_WIFI_SCAN_ENABLED";
    private static final String Q = "p3inspreferences";
    private static final String a = "p3ins_pfk_ul_params";
    private static final String b = "p3ins_pfk_ul_paramsetid";
    private static final String c = "p3ins_pfk_ul_allowed";
    private static final String d = "p3ins_pfk_db_retry";
    private static final String e = "p3ins_pfk_last_upload_time";
    private static final String f = "p3ins_pfk_guid";
    private static final String g = "P3INS_PFK_GUID_TIMESTAMP";
    private static final String h = "P3INS_PFK_CONNECTIVITY_TEST_ENABLED";
    private static final String i = "P3INS_PFK_CONNECTIVITY_KEEPALIVE_ENABLED";
    private static final String j = "P3INS_PFK_CONNECTIVITY_TEST_TRUSTSTORE_LAST_CHECK";
    private static final String k = "P3INS_PFK_CONNECTIVITY_TEST_TRUSTSTORE_LAST_MODIFIED";
    private static final String l = "P3INS_PFK_CONNECTIVITY_TEST_TIMESTAMP";
    private static final String m = "P3INS_PFK_APPUSAGE_SERVICE_ENABLED";
    private static final String n = "P3INS_PFK_APPUSAGE_LAST_SCREEN_SESSION_COUNTER";
    private static final String o = "P3INS_PFK_APPUSAGE_INSTALLED_APP_SNAPSHOT_ENABLED";
    private static final String p = "P3INS_PFK_APPUSAGE_INSTALLED_APP_SNAPSHOT_INTERVAL";
    private static final String q = "P3INS_PFK_APPUSAGE_INSTALLED_APP_SNAPSHOT_LAST_TIMESTAMP";
    private static final String r = "P3INS_PFK_APPUSAGE_BROWSER_SESSION_TRACKING_ENABLED";
    private static final String s = "P3INS_PFK_VOICEMANAGER_PHONENUMBER_RECORD_TYPE";
    private static final String t = "P3INS_PFK_VOICE_SERVICE_ENABLED";
    private static final String u = "P3INS_PFK_MESSAGING_SERVICE_ENABLED";
    private static final String v = "P3INS_PFK_MESSAGINGMANAGER_PHONENUMBER_RECORD_TYPE";
    private static final String w = "P3INS_PFK_COVERAGE_SERVICE_ENABLED";
    private static final String x = "P3INS_PFK_COVERAGE_SERVICE_TRIGGER_PROVIDER_MODE";
    private static final String y = "P3INS_PFK_TRAFFIC_ANALYZER_ENABLED";
    private static final String z = "P3INS_PFK_LAST_EXPORT_TIME";
    private SharedPreferences R;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ String a;

        public a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            s.a d = s.d();
            if (d != null) {
                d.a(this.a);
            }
        }
    }

    public t(Context context) {
        this.R = context.getSharedPreferences(Q, 0);
    }

    private boolean O() {
        return this.R.getBoolean(C, false);
    }

    private AnonymizationLevel a(String str) {
        AnonymizationLevel anonymizationLevel = AnonymizationLevel.Anonymized;
        if (str.equals(anonymizationLevel.toString())) {
            return anonymizationLevel;
        }
        AnonymizationLevel anonymizationLevel2 = AnonymizationLevel.Full;
        if (str.equals(anonymizationLevel2.toString())) {
            return anonymizationLevel2;
        }
        AnonymizationLevel anonymizationLevel3 = AnonymizationLevel.None;
        str.equals(anonymizationLevel3.toString());
        return anonymizationLevel3;
    }

    private String a() {
        String replace = UUID.randomUUID().toString().replace("-", "");
        SharedPreferences.Editor edit = this.R.edit();
        edit.putString(f, replace);
        edit.putLong(g, n1.d());
        edit.commit();
        return replace;
    }

    private LocationController.ProviderMode b(String str) {
        LocationController.ProviderMode providerMode = LocationController.ProviderMode.Gps;
        if (str.equals(providerMode.toString())) {
            return providerMode;
        }
        LocationController.ProviderMode providerMode2 = LocationController.ProviderMode.GpsAndNetwork;
        if (str.equals(providerMode2.toString())) {
            return providerMode2;
        }
        LocationController.ProviderMode providerMode3 = LocationController.ProviderMode.Network;
        if (str.equals(providerMode3.toString())) {
            return providerMode3;
        }
        LocationController.ProviderMode providerMode4 = LocationController.ProviderMode.Passive;
        if (str.equals(providerMode4.toString())) {
            return providerMode4;
        }
        LocationController.ProviderMode providerMode5 = LocationController.ProviderMode.RailNet;
        if (str.equals(providerMode5.toString())) {
            return providerMode5;
        }
        return null;
    }

    private void b(boolean z2) {
        this.R.edit().putBoolean(C, z2).commit();
    }

    public boolean A() {
        return this.R.getBoolean(u, s.b().MESSAGING_SERVICE_ENABLED());
    }

    public boolean B() {
        return this.R.getBoolean(A, s.b().QOE_MANAGER_ENABLED());
    }

    public long C() {
        return this.R.getLong(B, 0L);
    }

    public boolean D() {
        return this.R.getBoolean(D, s.b().SEND_REGISTRATION_TIMESTAMP_ENABLED());
    }

    public boolean E() {
        return this.R.getBoolean(y, s.b().TRAFFIC_ANALYZER_ENABLED());
    }

    public long F() {
        return this.R.getLong(k, 0L);
    }

    public String G() {
        return this.R.getString(E, "");
    }

    public String H() {
        return this.R.getString(b, "");
    }

    public String I() {
        return this.R.getString(a, "");
    }

    public long J() {
        return this.R.getLong(d, 0L);
    }

    public boolean K() {
        return this.R.getBoolean(F, s.b().VOWIFI_TEST_MANAGER_ENABLED());
    }

    public AnonymizationLevel L() {
        return a(this.R.getString(s, s.b().VOICEMANAGER_PHONENUMBER_RECORD_TYPE().toString()));
    }

    public boolean M() {
        return this.R.getBoolean(t, s.b().VOICE_SERVICE_ENABLED());
    }

    public boolean N() {
        return this.R.getBoolean(P, s.b().WIFI_SCAN_ENABLED());
    }

    public boolean P() {
        return this.R.getBoolean(c, true);
    }

    public String a(boolean z2) {
        String string = this.R.getString(f, "");
        boolean z3 = true;
        if (string == null || string.length() == 0) {
            string = a();
        } else {
            long d2 = n1.d();
            long j2 = this.R.getLong(g, 0L);
            long GUID_MAX_AGE = s.b().GUID_MAX_AGE();
            if ((GUID_MAX_AGE != -1 || z2) && (d2 - j2 > GUID_MAX_AGE || z2)) {
                string = a();
            } else {
                z3 = false;
            }
        }
        if (z3) {
            new Handler(Looper.getMainLooper()).post(new k1(new a(string)));
            if (D()) {
                O();
            }
        }
        return string;
    }

    public void a(long j2) {
        this.R.edit().putLong(p, j2).commit();
    }

    public void a(AnonymizationLevel anonymizationLevel) {
        this.R.edit().putString(v, anonymizationLevel.toString()).commit();
    }

    public void a(Set<String> set) {
        this.R.edit().putStringSet(G, set).commit();
    }

    public void b(long j2) {
        this.R.edit().putLong(M, j2).commit();
    }

    public void b(AnonymizationLevel anonymizationLevel) {
        this.R.edit().putString(s, anonymizationLevel.toString()).commit();
    }

    public void b(Set<String> set) {
        this.R.edit().putStringSet(I, set).commit();
    }

    public boolean b() {
        return this.R.getBoolean(r, s.b().APPUSAGE_BROWSER_SESSION_TRACKING_ENABLED());
    }

    public void c(long j2) {
        this.R.edit().putLong(q, j2).commit();
    }

    public void c(String str) {
        this.R.edit().putString(J, str).commit();
    }

    public void c(Set<String> set) {
        this.R.edit().putStringSet(K, set).commit();
    }

    public void c(boolean z2) {
        this.R.edit().putBoolean(r, z2).commit();
    }

    public boolean c() {
        return this.R.getBoolean(o, s.b().APPUSAGE_MANAGER_INSTALLED_APP_SNAPSHOT_ENABLED());
    }

    public long d() {
        return this.R.getLong(p, 86400000L);
    }

    public void d(long j2) {
        this.R.edit().putLong(n, j2).apply();
    }

    public void d(String str) {
        this.R.edit().putString(L, str).commit();
    }

    public void d(Set<String> set) {
        this.R.edit().putStringSet(H, set).commit();
    }

    public void d(boolean z2) {
        this.R.edit().putBoolean(o, z2).commit();
    }

    public void e(long j2) {
        this.R.edit().putLong(N, j2).commit();
    }

    public void e(String str) {
        this.R.edit().putString(E, str).commit();
    }

    public void e(boolean z2) {
        this.R.edit().putBoolean(m, z2).commit();
    }

    public boolean e() {
        return this.R.getBoolean(m, s.b().APPUSAGE_SERVICE_ENABLED());
    }

    public Set<String> f() {
        return this.R.getStringSet(G, null);
    }

    public void f(long j2) {
        this.R.edit().putLong(l, j2).commit();
    }

    public void f(String str) {
        this.R.edit().putString(b, str).commit();
    }

    public void f(boolean z2) {
        this.R.edit().putBoolean(i, z2).commit();
    }

    public long g() {
        return this.R.getLong(M, 0L);
    }

    public void g(long j2) {
        this.R.edit().putLong(z, j2).commit();
    }

    public void g(String str) {
        this.R.edit().putString(a, str).commit();
    }

    public void g(boolean z2) {
        this.R.edit().putBoolean(h, z2).commit();
    }

    public String h() {
        return this.R.getString(J, s.b().CONNECTIVITY_TEST_CRITERIA().name());
    }

    public void h(long j2) {
        this.R.edit().putLong(j, j2).commit();
    }

    public void h(boolean z2) {
        this.R.edit().putBoolean(w, z2).commit();
    }

    public void i(long j2) {
        this.R.edit().putLong(e, j2).commit();
    }

    public void i(boolean z2) {
        this.R.edit().putBoolean(u, z2).commit();
    }

    public String[] i() {
        Set<String> stringSet = this.R.getStringSet(I, null);
        return (stringSet == null || stringSet.isEmpty()) ? s.b().CONNECTIVITY_TEST_HOSTNAME_ARRAY() : (String[]) stringSet.toArray(new String[stringSet.size()]);
    }

    public String j() {
        return this.R.getString(L, s.b().LATENCY_TEST_CRITERIA().name());
    }

    public void j(long j2) {
        this.R.edit().putLong(O, j2).commit();
    }

    public void j(boolean z2) {
        this.R.edit().putBoolean(A, z2).commit();
    }

    public void k(long j2) {
        this.R.edit().putLong(B, j2).commit();
    }

    public void k(boolean z2) {
        this.R.edit().putBoolean(D, z2).commit();
    }

    public String[] k() {
        Set<String> stringSet = this.R.getStringSet(K, null);
        return (stringSet == null || stringSet.isEmpty()) ? s.b().LATENCY_TEST_HOSTNAME_ARRAY() : (String[]) stringSet.toArray(new String[stringSet.size()]);
    }

    public void l(long j2) {
        this.R.edit().putLong(k, j2).commit();
    }

    public void l(boolean z2) {
        this.R.edit().putBoolean(y, z2).commit();
    }

    public boolean l() {
        return this.R.getBoolean(i, s.b().CONNECTIVITY_KEEPALIVE_ENABLED());
    }

    public void m(long j2) {
        this.R.edit().putLong(d, j2).commit();
    }

    public void m(boolean z2) {
        this.R.edit().putBoolean(c, z2).commit();
    }

    public boolean m() {
        return this.R.getBoolean(h, s.b().CONNECTIVITY_TEST_ENABLED());
    }

    public void n(boolean z2) {
        this.R.edit().putBoolean(F, z2).commit();
    }

    public boolean n() {
        return this.R.getBoolean(w, s.b().COVERAGE_MAPPER_SERVICE_ENABLED());
    }

    public LocationController.ProviderMode o() {
        return b(this.R.getString(x, s.b().COVERAGE_MAPPER_SERVICE_TRIGGER_PROVIDER_MODE().toString()));
    }

    public void o(boolean z2) {
        this.R.edit().putBoolean(t, z2).commit();
    }

    public String p() {
        return a(false);
    }

    public void p(boolean z2) {
        this.R.edit().putBoolean(P, z2).commit();
    }

    public Set<String> q() {
        return this.R.getStringSet(H, null);
    }

    public long r() {
        return this.R.getLong(q, 0L);
    }

    public long s() {
        return this.R.getLong(n, 1L);
    }

    public long t() {
        return this.R.getLong(N, 0L);
    }

    public long u() {
        return this.R.getLong(l, 2147483647L);
    }

    public long v() {
        return this.R.getLong(z, 0L);
    }

    public long w() {
        return this.R.getLong(j, 0L);
    }

    public long x() {
        return this.R.getLong(e, 0L);
    }

    public long y() {
        return this.R.getLong(O, 2147483647L);
    }

    public AnonymizationLevel z() {
        return a(this.R.getString(v, s.b().MESSAGINGMANAGER_PHONENUMBER_RECORD_TYPE().toString()));
    }
}
