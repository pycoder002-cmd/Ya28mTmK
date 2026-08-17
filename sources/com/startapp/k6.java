package com.startapp;

import android.content.Context;
import android.net.Uri;
import com.startapp.sdk.adsbase.apppresence.AppPresenceDetails;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.components.ComponentLocator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class k6 {
    public final Context a;
    public final List<AppPresenceDetails> b;
    public final Runnable c = new a();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            k6 k6Var = k6.this;
            k6Var.getClass();
            try {
                k6Var.b();
            } catch (Throwable th) {
                p7.a(k6Var.a, th);
            }
        }
    }

    public k6(Context context, List<AppPresenceDetails> list) {
        this.b = list;
        this.a = context;
    }

    public void a() {
        ComponentLocator.a(this.a).o().execute(this.c);
    }

    public final void b() {
        String c;
        String str;
        List<AppPresenceDetails> list = this.b;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (AppPresenceDetails appPresenceDetails : list) {
            if (!appPresenceDetails.e() && (c = appPresenceDetails.c()) != null) {
                try {
                    str = Uri.parse(c).getQueryParameter("d");
                } catch (Throwable th) {
                    p7.a(this.a, th);
                    str = null;
                }
                if (str != null) {
                    if (appPresenceDetails.d()) {
                        arrayList2.add("d=" + str);
                    } else {
                        arrayList3.add("d=" + str);
                    }
                }
            }
        }
        if (!arrayList2.isEmpty()) {
            arrayList.addAll(g5.a(arrayList2, "false", "true"));
        }
        if (!arrayList3.isEmpty()) {
            arrayList.addAll(g5.a(arrayList3, "false", "false"));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (str2.length() != 0) {
                Context context = this.a;
                TrackingParams a2 = new TrackingParams().a("APP_PRESENCE");
                if (!str2.equalsIgnoreCase("")) {
                    aa.a(context, false, "Sending impression", true);
                    g5.b(context, str2, a2);
                }
            }
        }
    }
}
