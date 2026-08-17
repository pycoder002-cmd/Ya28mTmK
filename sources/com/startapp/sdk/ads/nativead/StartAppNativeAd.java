package com.startapp.sdk.ads.nativead;

import android.content.Context;
import com.startapp.d;
import com.startapp.g5;
import com.startapp.i6;
import com.startapp.j6;
import com.startapp.sdk.ads.nativead.NativeAdDetails;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.adinformation.AdInformationMetaData;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.adrules.AdRulesResult;
import com.startapp.sdk.adsbase.adrules.AdaptMetaData;
import com.startapp.sdk.adsbase.model.AdDetails;
import com.startapp.sdk.adsbase.model.AdPreferences;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class StartAppNativeAd extends Ad implements NativeAdDetails.g {
    private static final long serialVersionUID = 1;
    private a adEventDelegate;
    public boolean isLoading;
    private List<NativeAdDetails> listNativeAds;
    private NativeAd nativeAd;
    private NativeAdPreferences preferences;
    private int totalObjectsLoaded;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum CampaignAction {
        LAUNCH_APP,
        OPEN_MARKET
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements AdEventListener {
        public AdEventListener a;

        public a(AdEventListener adEventListener) {
            this.a = adEventListener;
        }

        @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
        public void onFailedToReceiveAd(Ad ad) {
            if (ad != null) {
                StartAppNativeAd.this.setErrorMessage(ad.getErrorMessage());
            }
            AdEventListener adEventListener = this.a;
            if (adEventListener != null) {
                StartAppNativeAd startAppNativeAd = StartAppNativeAd.this;
                d.a(startAppNativeAd.b, adEventListener, startAppNativeAd);
                this.a = null;
            }
            StartAppNativeAd startAppNativeAd2 = StartAppNativeAd.this;
            startAppNativeAd2.isLoading = false;
            startAppNativeAd2.h();
        }

        @Override // com.startapp.sdk.adsbase.adlisteners.AdEventListener
        public void onReceiveAd(Ad ad) {
            StartAppNativeAd.this.h();
        }
    }

    public StartAppNativeAd(Context context) {
        super(context, AdPreferences.Placement.INAPP_NATIVE);
        this.totalObjectsLoaded = 0;
        this.listNativeAds = new ArrayList();
        this.isLoading = false;
    }

    public static String getPrivacyImageUrl() {
        return AdInformationMetaData.a.c();
    }

    public static String getPrivacyURL() {
        if (AdInformationMetaData.a.b() == null) {
            return "";
        }
        String b = AdInformationMetaData.a.b();
        if (b.contains("http://") || b.contains("https://")) {
            return AdInformationMetaData.a.b();
        }
        return "https://" + AdInformationMetaData.a.b();
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public void a(AdPreferences adPreferences, AdEventListener adEventListener) {
    }

    public final AdDetails g() {
        NativeAdDetails nativeAdDetails;
        List<NativeAdDetails> list = this.listNativeAds;
        if (list == null || list.size() <= 0 || (nativeAdDetails = this.listNativeAds.get(0)) == null) {
            return null;
        }
        return nativeAdDetails.a;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public String getAdId() {
        AdDetails g = g();
        if (g != null) {
            return g.a();
        }
        return null;
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public String getBidToken() {
        AdDetails g = g();
        if (g != null) {
            return g.c();
        }
        return null;
    }

    public ArrayList<NativeAdDetails> getNativeAds() {
        return getNativeAds(null);
    }

    public ArrayList<NativeAdDetails> getNativeAds(String str) {
        ArrayList<NativeAdDetails> arrayList = new ArrayList<>();
        AdRulesResult a2 = AdaptMetaData.a.a().a(AdPreferences.Placement.INAPP_NATIVE, str);
        if (a2.b()) {
            List<NativeAdDetails> list = this.listNativeAds;
            if (list != null) {
                for (NativeAdDetails nativeAdDetails : list) {
                    nativeAdDetails.h = str;
                    arrayList.add(nativeAdDetails);
                }
                j6.a.a(new i6(AdPreferences.Placement.INAPP_NATIVE, str));
            }
        } else {
            Context context = this.b;
            ArrayList arrayList2 = new ArrayList();
            List<NativeAdDetails> list2 = this.listNativeAds;
            if (list2 != null) {
                Iterator<NativeAdDetails> it = list2.iterator();
                while (it.hasNext()) {
                    arrayList2.add(it.next().a);
                }
            }
            g5.a(context, g5.a(arrayList2), str, 0, a2.a());
        }
        return arrayList;
    }

    public int getNumberOfAds() {
        List<NativeAdDetails> list = this.listNativeAds;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public void h() {
        this.totalObjectsLoaded = 0;
        if (this.listNativeAds == null) {
            this.listNativeAds = new ArrayList();
        }
        this.listNativeAds.clear();
        NativeAd nativeAd = this.nativeAd;
        if (nativeAd == null || nativeAd.g() == null) {
            return;
        }
        for (int i = 0; i < this.nativeAd.g().size(); i++) {
            this.listNativeAds.add(new NativeAdDetails(this.b, this.nativeAd.g().get(i), this.preferences, i, this));
        }
    }

    @Override // com.startapp.sdk.adsbase.Ad
    public boolean isBelowMinCPM() {
        return this.nativeAd.isBelowMinCPM();
    }

    public boolean loadAd() {
        return loadAd(new NativeAdPreferences(), null);
    }

    public boolean loadAd(NativeAdPreferences nativeAdPreferences) {
        return loadAd(nativeAdPreferences, null);
    }

    public boolean loadAd(NativeAdPreferences nativeAdPreferences, AdEventListener adEventListener) {
        this.adEventDelegate = new a(adEventListener);
        this.preferences = nativeAdPreferences;
        if (this.isLoading) {
            setErrorMessage("Ad is currently being loaded");
            return false;
        }
        this.isLoading = true;
        NativeAd nativeAd = new NativeAd(this.b, nativeAdPreferences);
        this.nativeAd = nativeAd;
        return nativeAd.load(nativeAdPreferences, this.adEventDelegate, true);
    }

    public boolean loadAd(AdEventListener adEventListener) {
        return loadAd(new NativeAdPreferences(), adEventListener);
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdDetails.g
    public void onNativeAdDetailsLoaded(int i) {
        this.totalObjectsLoaded++;
        if (this.nativeAd.g() == null || this.totalObjectsLoaded != this.nativeAd.g().size()) {
            return;
        }
        this.isLoading = false;
        setErrorMessage(null);
        a aVar = this.adEventDelegate;
        if (aVar != null) {
            d.b(this.b, aVar.a, this);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n===== StartAppNativeAd =====\n");
        for (int i = 0; i < getNumberOfAds(); i++) {
            sb.append(this.listNativeAds.get(i));
        }
        sb.append("===== End StartAppNativeAd =====");
        return sb.toString();
    }
}
