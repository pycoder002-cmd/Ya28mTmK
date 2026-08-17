package com.startapp;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import com.startapp.networkTest.results.BaseResult;
import com.startapp.networkTest.results.ConnectivityTestResult;
import com.startapp.networkTest.results.LatencyResult;
import com.startapp.networkTest.results.NetworkInformationResult;
import com.startapp.networkTest.startapp.ConnectivityTestListener;
import com.startapp.networkTest.startapp.CoverageMapperManager;
import java.util.Map;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class kc implements ConnectivityTestListener, CoverageMapperManager.OnNetworkInfoResultListener {
    public final Context a;

    public kc(Context context) {
        this.a = context;
    }

    public final void a(q7 q7Var, BaseResult baseResult) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Map<Activity, Integer> map = aa.a;
            String encodeToString = Base64.encodeToString(aa.a(String.valueOf(c.b(baseResult))), 11);
            if (encodeToString != null) {
                p7 p7Var = new p7(q7Var);
                p7Var.h = Long.valueOf(currentTimeMillis);
                p7Var.e = encodeToString;
                p7Var.a(this.a);
            } else {
                p7 p7Var2 = new p7(q7.c);
                p7Var2.d = "NTS, can not encode result";
                p7Var2.e = baseResult.getClass().getName();
                p7Var2.a(this.a);
            }
        } catch (Throwable th) {
            p7.a(this.a, th);
        }
    }

    @Override // com.startapp.networkTest.startapp.ConnectivityTestListener
    public void onConnectivityTestFinished(Runnable runnable) {
        ((k1) runnable).run();
    }

    @Override // com.startapp.networkTest.startapp.ConnectivityTestListener
    public void onConnectivityTestResult(ConnectivityTestResult connectivityTestResult) {
        if (connectivityTestResult != null) {
            a(q7.l, connectivityTestResult);
        }
    }

    @Override // com.startapp.networkTest.startapp.ConnectivityTestListener
    public void onLatencyTestResult(LatencyResult latencyResult) {
        if (latencyResult != null) {
            a(q7.m, latencyResult);
        }
    }

    @Override // com.startapp.networkTest.startapp.CoverageMapperManager.OnNetworkInfoResultListener
    public void onNetworkInfoResult(NetworkInformationResult networkInformationResult) {
        if (networkInformationResult != null) {
            a(q7.n, networkInformationResult);
        }
    }
}
