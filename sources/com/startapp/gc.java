package com.startapp;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.startapp.aa;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.HtmlAd;
import com.startapp.sdk.adsbase.SimpleTokenUtils;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.apppresence.AppPresenceDetails;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.za;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class gc extends k5 {
    public Set<String> g;
    public Set<String> h;
    public GetAdRequest i;
    public int j;
    public boolean k;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements i9<String, Void> {
        public a() {
        }

        @Override // com.startapp.i9
        public Void a(String str) {
            gc.this.f = str;
            return null;
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements aa.a {
        public b() {
        }

        @Override // com.startapp.aa.a
        public void a() {
            gc gcVar = gc.this;
            d.b(gcVar.a, gcVar.d, gcVar.b);
        }

        @Override // com.startapp.aa.a
        public void a(String str) {
            gc.this.b.setErrorMessage(str);
            gc gcVar = gc.this;
            d.a(gcVar.a, gcVar.d, gcVar.b);
        }
    }

    public gc(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, AdPreferences.Placement placement, boolean z) {
        super(context, ad, adPreferences, adEventListener, placement);
        this.g = new HashSet();
        this.h = new HashSet();
        this.j = 0;
        this.k = z;
    }

    @Override // com.startapp.k5
    public void a(Boolean bool) {
        super.a(bool);
    }

    public void a(boolean z) {
        Ad ad;
        Intent intent = new Intent("com.startapp.android.OnReceiveResponseBroadcastListener");
        intent.putExtra("adHashcode", this.b.hashCode());
        intent.putExtra("adResult", z);
        la.a(this.a).a(intent);
        if (!z || (ad = this.b) == null) {
            return;
        }
        if (this.k) {
            ComponentLocator.a(this.a).e.b().a(((HtmlAd) this.b).j(), new b());
        } else if (z) {
            d.b(this.a, this.d, ad);
        } else {
            d.a(this.a, this.d, ad);
        }
    }

    @Override // com.startapp.k5
    public boolean a(Object obj) {
        if (obj == null) {
            if (this.f == null) {
                this.f = "No response";
            }
            return false;
        }
        try {
            ArrayList arrayList = new ArrayList();
            String str = ((za.a) obj).a;
            if (TextUtils.isEmpty(str)) {
                if (this.f == null) {
                    GetAdRequest getAdRequest = this.i;
                    if (getAdRequest == null || !getAdRequest.b()) {
                        this.f = "Empty Ad";
                    } else {
                        this.f = "Video isn't available";
                    }
                }
                return false;
            }
            List<AppPresenceDetails> a2 = d.a(str, this.j);
            if (AdsCommonMetaData.h.H() ? d.a(this.a, a2, this.j, this.g, arrayList).booleanValue() : false) {
                this.j++;
                new k6(this.a, arrayList).a();
                return a().booleanValue();
            }
            ((HtmlAd) this.b).a(a2);
            ((HtmlAd) this.b).c(str);
            return true;
        } catch (Throwable th) {
            p7.a(this.a, th);
            return false;
        }
    }

    @Override // com.startapp.k5
    public void b(Boolean bool) {
        this.b.setState(bool.booleanValue() ? Ad.AdState.READY : Ad.AdState.UN_INITIALIZED);
    }

    public boolean b(GetAdRequest getAdRequest) {
        return getAdRequest != null;
    }

    @Override // com.startapp.k5
    public Object d() {
        GetAdRequest c = c();
        this.i = c;
        if (!b(c)) {
            return null;
        }
        if (this.g.size() == 0) {
            this.g.add(this.a.getPackageName());
        }
        GetAdRequest getAdRequest = this.i;
        getAdRequest.B0 = this.g;
        getAdRequest.D0 = this.h;
        if (this.j > 0) {
            getAdRequest.F0 = false;
            if (MetaData.h.D().a(this.a)) {
                SimpleTokenUtils.e(this.a);
            }
        }
        n7 j = ComponentLocator.a(this.a).j();
        String a2 = AdsConstants.a(AdsConstants.AdApiType.HTML, this.e);
        j.getClass();
        try {
            return j.a(a2, this.i, new a());
        } catch (Throwable th) {
            p7.a(j.a, th);
            return null;
        }
    }
}
