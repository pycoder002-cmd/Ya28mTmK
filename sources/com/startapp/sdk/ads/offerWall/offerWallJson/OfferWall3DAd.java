package com.startapp.sdk.ads.offerWall.offerWallJson;

import android.content.Context;
import android.content.Intent;
import com.startapp.aa;
import com.startapp.g5;
import com.startapp.h3;
import com.startapp.n3;
import com.startapp.n5;
import com.startapp.p7;
import com.startapp.sdk.ads.list3d.List3DActivity;
import com.startapp.sdk.adsbase.ActivityExtra;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsConstants;
import com.startapp.sdk.adsbase.JsonAd;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adlisteners.NotDisplayedReason;
import com.startapp.sdk.adsbase.model.AdPreferences;
import java.util.UUID;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class OfferWall3DAd extends JsonAd implements n5 {
    public static String c = null;
    private static final long serialVersionUID = 1;
    private final String uuid;

    public OfferWall3DAd(Context context) {
        super(context, AdPreferences.Placement.INAPP_OFFER_WALL);
        this.uuid = UUID.randomUUID().toString();
        if (c == null) {
            c = aa.a(context);
        }
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public void a(AdPreferences adPreferences, AdEventListener adEventListener) {
        new n3(this.b, this, adPreferences, adEventListener).b();
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public void a(boolean z) {
        super.a(z);
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public boolean a() {
        return super.a();
    }

    @Override // com.startapp.n5
    public boolean a(String str) {
        h3.a.a(this.uuid).c = "&position=" + g5.a();
        ActivityExtra activityExtra = this.activityExtra;
        boolean a = activityExtra != null ? activityExtra.a() : false;
        if (super.d()) {
            a(NotDisplayedReason.AD_EXPIRED);
            return false;
        }
        Intent intent = new Intent(this.b, (Class<?>) List3DActivity.class);
        intent.putExtra("adInfoOverride", getAdInfoOverride());
        intent.putExtra("fullscreen", a);
        intent.putExtra("adTag", str);
        intent.putExtra("lastLoadTime", super.b());
        intent.putExtra("adCacheTtl", super.c());
        intent.putExtra("position", g5.a());
        intent.putExtra("listModelUuid", this.uuid);
        intent.addFlags(343932928);
        try {
            this.b.startActivity(intent);
            if (AdsConstants.g.booleanValue()) {
                return true;
            }
            setState(Ad.AdState.UN_INITIALIZED);
            return true;
        } catch (Throwable th) {
            p7.a(this.b, th);
            return false;
        }
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public Long b() {
        return super.b();
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public Long c() {
        return super.c();
    }

    @Override // com.startapp.sdk.adsbase.Ad, com.startapp.n5
    public boolean d() {
        return super.d();
    }

    public String h() {
        return this.uuid;
    }
}
