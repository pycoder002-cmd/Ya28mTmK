package com.startapp.sdk.adsbase;

import android.content.Context;
import com.startapp.sdk.adsbase.model.AdDetails;
import com.startapp.sdk.adsbase.model.AdPreferences;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class JsonAd extends Ad {
    private static final long serialVersionUID = 1;
    private List<AdDetails> adsDetails;

    public JsonAd(Context context, AdPreferences.Placement placement) {
        super(context, placement);
        this.adsDetails = null;
    }

    public void a(List<AdDetails> list) {
        this.adsDetails = list;
        Long l = null;
        for (AdDetails adDetails : list) {
            if (adDetails != null && adDetails.w() != null && (l == null || adDetails.w().longValue() < l.longValue())) {
                l = adDetails.w();
            }
        }
        if (l != null) {
            this.adCacheTtl = Long.valueOf(TimeUnit.SECONDS.toMillis(l.longValue()));
        }
        boolean z = true;
        Iterator<AdDetails> it = this.adsDetails.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!it.next().m()) {
                    z = false;
                    break;
                }
            } else {
                break;
            }
        }
        this.belowMinCPM = z;
    }

    public List<AdDetails> g() {
        return this.adsDetails;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public String getAdId() {
        List<AdDetails> list = this.adsDetails;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return this.adsDetails.get(0).a();
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public String getBidToken() {
        List<AdDetails> list = this.adsDetails;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return this.adsDetails.get(0).c();
    }
}
