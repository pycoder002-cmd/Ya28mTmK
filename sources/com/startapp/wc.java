package com.startapp;

import android.content.Context;
import android.content.Intent;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.JsonAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.model.AdDetails;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;
import com.startapp.sdk.adsbase.model.GetAdResponse;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.za;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class wc extends k5 {
    public int g;
    public Set<String> h;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements i9<String, Void> {
        public a() {
        }

        @Override // com.startapp.i9
        public Void a(String str) {
            wc.this.f = str;
            return null;
        }
    }

    public wc(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener, AdPreferences.Placement placement) {
        super(context, ad, adPreferences, adEventListener, placement);
        this.g = 0;
        this.h = new HashSet();
    }

    public abstract void a(Ad ad);

    @Override // com.startapp.k5
    public void a(Boolean bool) {
        super.a(bool);
        Intent intent = new Intent("com.startapp.android.OnReceiveResponseBroadcastListener");
        intent.putExtra("adHashcode", this.b.hashCode());
        intent.putExtra("adResult", bool);
        la.a(this.a).a(intent);
        if (bool.booleanValue()) {
            a(this.b);
            d.b(this.a, this.d, this.b);
        }
    }

    @Override // com.startapp.k5
    public boolean a(Object obj) {
        int i;
        GetAdResponse getAdResponse = (GetAdResponse) obj;
        boolean z = false;
        if (obj == null) {
            this.f = "Empty Response";
            return false;
        }
        if (!getAdResponse.b()) {
            this.f = getAdResponse.a();
            return false;
        }
        JsonAd jsonAd = (JsonAd) this.b;
        List<AdDetails> a2 = d.a(this.a, getAdResponse.d(), this.g, this.h, true);
        jsonAd.a(a2);
        jsonAd.setAdInfoOverride(getAdResponse.c());
        if (getAdResponse.d() != null && getAdResponse.d().size() > 0) {
            z = true;
        }
        if (!z) {
            this.f = "Empty Response";
        } else if (((ArrayList) a2).size() == 0 && (i = this.g) == 0) {
            this.g = i + 1;
            return a().booleanValue();
        }
        return z;
    }

    @Override // com.startapp.k5
    public Object d() {
        za.a aVar;
        GetAdRequest c = c();
        if (c == null) {
            return null;
        }
        if (this.h.size() == 0) {
            this.h.add(this.a.getPackageName());
        }
        int i = this.g;
        if (i > 0) {
            c.F0 = false;
        }
        c.B0 = this.h;
        c.F0 = i == 0;
        n7 j = ComponentLocator.a(this.a).j();
        String a2 = AdsConstants.a(AdsConstants.AdApiType.JSON, this.e);
        j.getClass();
        try {
            aVar = j.a(a2, c, new a());
        } catch (Throwable th) {
            p7.a(j.a, th);
            aVar = null;
        }
        if (aVar == null) {
            return null;
        }
        try {
            return aa.a(aVar.a, GetAdResponse.class);
        } catch (Throwable th2) {
            p7.a(j.a, th2);
            return null;
        }
    }
}
