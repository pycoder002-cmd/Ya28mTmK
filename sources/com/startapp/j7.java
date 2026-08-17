package com.startapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class j7 {
    public final Context a;
    public final ConnectivityManager.NetworkCallback b;
    public final ConnectivityManager.OnNetworkActiveListener c;
    public final List<k9<Void>> d = new LinkedList();
    public final AtomicBoolean e = new AtomicBoolean(false);

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends ConnectivityManager.NetworkCallback {
        public a() {
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            j7 j7Var = j7.this;
            synchronized (j7Var) {
                Iterator<k9<Void>> it = j7Var.d.iterator();
                while (it.hasNext()) {
                    it.next().call();
                }
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements ConnectivityManager.OnNetworkActiveListener {
        public b() {
        }

        @Override // android.net.ConnectivityManager.OnNetworkActiveListener
        public void onNetworkActive() {
            j7 j7Var = j7.this;
            synchronized (j7Var) {
                Iterator<k9<Void>> it = j7Var.d.iterator();
                while (it.hasNext()) {
                    it.next().call();
                }
            }
        }
    }

    public j7(Context context) {
        this.a = context;
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            this.b = new a();
            this.c = null;
        } else if (i >= 21) {
            this.b = null;
            this.c = new b();
        } else {
            this.b = null;
            this.c = null;
        }
    }

    public boolean a() {
        if (ya.a(this.a, "android.permission.ACCESS_NETWORK_STATE")) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) this.a.getSystemService("connectivity");
                if (connectivityManager != null) {
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null) {
                        if (activeNetworkInfo.isConnected()) {
                            return true;
                        }
                    }
                    return false;
                }
            } catch (Throwable th) {
                p7.a(this.a, th);
            }
        }
        return true;
    }
}
