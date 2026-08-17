package com.startapp.sdk.adsbase.cache;

import com.startapp.n5;
import com.startapp.sdk.adsbase.HtmlAd;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class DiskAdCacheManager$DiskCachedAd implements Serializable {
    private static final long serialVersionUID = 1;
    private n5 ad;
    private String html;

    public DiskAdCacheManager$DiskCachedAd(n5 n5Var) {
        a(n5Var);
        c();
    }

    public n5 a() {
        return this.ad;
    }

    public final void a(n5 n5Var) {
        this.ad = n5Var;
    }

    public String b() {
        return this.html;
    }

    public final void c() {
        Object obj = this.ad;
        if (obj == null || !(obj instanceof HtmlAd)) {
            return;
        }
        this.html = ((HtmlAd) obj).j();
    }
}
