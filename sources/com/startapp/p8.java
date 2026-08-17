package com.startapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.startapp.a;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class p8 {
    public static CountDownLatch a;
    public static volatile q8 b;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static final class a implements ServiceConnection {
        public String a;

        public a(String str) {
            this.a = str;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.startapp.a c0054a;
            int i = a.AbstractBinderC0053a.a;
            if (iBinder == null) {
                c0054a = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
                c0054a = (queryLocalInterface == null || !(queryLocalInterface instanceof com.startapp.a)) ? new a.AbstractBinderC0053a.C0054a(iBinder) : (com.startapp.a) queryLocalInterface;
            }
            Bundle bundle = new Bundle();
            bundle.putString("package_name", this.a);
            try {
                p8.b = new q8(c0054a.a(bundle));
            } catch (RemoteException unused) {
            }
            p8.a.countDown();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            p8.a.countDown();
        }
    }

    public static q8 a(Context context) {
        ServiceInfo serviceInfo;
        if (b == null) {
            try {
                a = new CountDownLatch(1);
                a aVar = new a(context.getPackageName());
                Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
                intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
                boolean z = false;
                List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
                if (queryIntentServices != null && !queryIntentServices.isEmpty() && (serviceInfo = queryIntentServices.get(0).serviceInfo) != null) {
                    String str = serviceInfo.packageName;
                    String str2 = serviceInfo.name;
                    if ("com.android.vending".equals(str) && str2 != null) {
                        try {
                            if (context.getPackageManager().getPackageInfo("com.android.vending", 128).versionCode >= 80837300) {
                                z = true;
                            }
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                        if (z && context.bindService(new Intent(intent), aVar, 1)) {
                            try {
                                a.await(1L, TimeUnit.SECONDS);
                            } catch (InterruptedException unused2) {
                            }
                            ya.a(context, aVar);
                        }
                    }
                }
            } catch (SecurityException unused3) {
            } catch (Throwable th) {
                p7.a(context, th);
            }
        }
        return b;
    }
}
