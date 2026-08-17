package com.startapp;

import com.startapp.sdk.ads.video.VideoEnabledAd;
import com.startapp.sdk.adsbase.cache.CachedVideoAd;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class h4 {
    public static h4 a = new h4();
    public LinkedList<CachedVideoAd> b = new LinkedList<>();

    public boolean a(int i) {
        ArrayList arrayList;
        boolean z;
        Iterator<CachedVideoAd> it = this.b.iterator();
        boolean z2 = false;
        while (it.hasNext() && this.b.size() > i) {
            CachedVideoAd next = it.next();
            String a2 = next.a();
            v6 v6Var = v6.a;
            synchronized (v6Var) {
                arrayList = new ArrayList(v6Var.b.values());
            }
            Iterator it2 = arrayList.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                n5 n5Var = ((b7) it2.next()).e;
                if (n5Var instanceof VideoEnabledAd) {
                    VideoEnabledAd videoEnabledAd = (VideoEnabledAd) n5Var;
                    if (videoEnabledAd.w() != null && videoEnabledAd.w().c() != null && videoEnabledAd.w().c().equals(a2)) {
                        z = true;
                        break;
                    }
                }
            }
            if (!z) {
                it.remove();
                if (next.a() != null) {
                    new File(next.a()).delete();
                }
                z2 = true;
            }
        }
        return z2;
    }
}
