package com.startapp.sdk.ads.nativead;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import com.startapp.b9;
import com.startapp.da;
import com.startapp.g5;
import com.startapp.l3;
import com.startapp.r5;
import com.startapp.sdk.ads.banner.BannerMetaData;
import com.startapp.sdk.ads.nativead.StartAppNativeAd;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.commontracking.TrackingParams;
import com.startapp.sdk.adsbase.model.AdDetails;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class NativeAdDetails implements NativeAdInterface {
    public AdDetails a;
    public int b;
    public Bitmap c;
    public Bitmap d;
    public g g;
    public String h;
    public da i;
    public View.OnAttachStateChangeListener k;
    public NativeAdDisplayListener l;
    public boolean e = false;
    public boolean f = false;
    public WeakReference<View> j = new WeakReference<>(null);
    public final r5.a m = new a();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements r5.a {
        public a() {
        }

        @Override // com.startapp.r5.a
        public void onSent() {
            NativeAdDetails nativeAdDetails = NativeAdDetails.this;
            nativeAdDetails.e = true;
            NativeAdDisplayListener nativeAdDisplayListener = nativeAdDetails.l;
            if (nativeAdDisplayListener != null) {
                nativeAdDisplayListener.adDisplayed(nativeAdDetails);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements b9.b {
        public final /* synthetic */ Context a;

        /* compiled from: StartAppSDK */
        /* loaded from: classes3.dex */
        public class a implements b9.b {
            public a() {
            }

            @Override // com.startapp.b9.b
            public void a(Bitmap bitmap, int i) {
                NativeAdDetails nativeAdDetails = NativeAdDetails.this;
                nativeAdDetails.d = bitmap;
                nativeAdDetails.getClass();
                new Handler().post(new c());
            }
        }

        public b(Context context) {
            this.a = context;
        }

        @Override // com.startapp.b9.b
        public void a(Bitmap bitmap, int i) {
            NativeAdDetails nativeAdDetails = NativeAdDetails.this;
            nativeAdDetails.c = bitmap;
            new b9(this.a, nativeAdDetails.getSecondaryImageUrl(), new a(), i).a();
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            NativeAdDetails nativeAdDetails = NativeAdDetails.this;
            g gVar = nativeAdDetails.g;
            if (gVar != null) {
                gVar.onNativeAdDetailsLoaded(nativeAdDetails.b);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NativeAdDetails.a(NativeAdDetails.this, view);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NativeAdDetails.a(NativeAdDetails.this, view);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class f implements da.a {
        public f() {
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public interface g {
        void onNativeAdDetailsLoaded(int i);
    }

    public NativeAdDetails(Context context, AdDetails adDetails, NativeAdPreferences nativeAdPreferences, int i, g gVar) {
        this.a = adDetails;
        this.b = i;
        this.g = gVar;
        if (nativeAdPreferences.isAutoBitmapDownload()) {
            new b9(context, getImageUrl(), new b(context), i).a();
        } else {
            a();
        }
    }

    public static void a(NativeAdDetails nativeAdDetails, View view) {
        nativeAdDetails.getClass();
        Context context = view.getContext();
        int ordinal = nativeAdDetails.getCampaignAction().ordinal();
        if (ordinal == 0) {
            g5.a(nativeAdDetails.getPackageName(), nativeAdDetails.a.k(), nativeAdDetails.a.f(), context, new TrackingParams(nativeAdDetails.h));
        } else if (ordinal == 1) {
            boolean a2 = g5.a(context, AdPreferences.Placement.INAPP_NATIVE);
            if (!nativeAdDetails.a.z() || a2) {
                g5.a(context, nativeAdDetails.a.f(), nativeAdDetails.a.t(), new TrackingParams(nativeAdDetails.h), nativeAdDetails.a.A() && !a2, false);
            } else {
                g5.a(context, nativeAdDetails.a.f(), nativeAdDetails.a.t(), nativeAdDetails.a.o(), new TrackingParams(nativeAdDetails.h), AdsCommonMetaData.h.z(), AdsCommonMetaData.h.y(), nativeAdDetails.a.A(), nativeAdDetails.a.B(), false, null);
            }
        }
        NativeAdDisplayListener nativeAdDisplayListener = nativeAdDetails.l;
        if (nativeAdDisplayListener != null) {
            nativeAdDisplayListener.adClicked(nativeAdDetails);
        }
    }

    public void a() {
        new Handler().post(new c());
    }

    public final void a(View view) {
        this.j = new WeakReference<>(view);
        if (view.hasWindowFocus() || Build.VERSION.SDK_INT < 12) {
            b();
            return;
        }
        if (this.k == null) {
            this.k = new l3(this);
        }
        view.addOnAttachStateChangeListener(this.k);
    }

    public final void b() {
        if (this.i != null || this.e) {
            return;
        }
        View view = this.j.get();
        if (view == null) {
            NativeAdDisplayListener nativeAdDisplayListener = this.l;
            if (nativeAdDisplayListener != null) {
                nativeAdDisplayListener.adNotDisplayed(this);
                return;
            }
            return;
        }
        r5 r5Var = new r5(view.getContext(), this.a.v(), new TrackingParams(this.h), this.a.g() != null ? TimeUnit.SECONDS.toMillis(this.a.g().longValue()) : TimeUnit.SECONDS.toMillis(MetaData.h.m()));
        r5Var.l = new WeakReference<>(this.m);
        da daVar = new da(this.j, r5Var, BannerMetaData.b.a().h());
        this.i = daVar;
        daVar.c = new f();
        if (daVar.b()) {
            daVar.run();
        }
    }

    public void finalize() throws Throwable {
        super.finalize();
        unregisterView();
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public String getCallToAction() {
        String d2;
        AdDetails adDetails = this.a;
        return (adDetails == null || (d2 = adDetails.d()) == null) ? "" : d2;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public StartAppNativeAd.CampaignAction getCampaignAction() {
        StartAppNativeAd.CampaignAction campaignAction = StartAppNativeAd.CampaignAction.OPEN_MARKET;
        AdDetails adDetails = this.a;
        return (adDetails == null || !adDetails.y()) ? campaignAction : StartAppNativeAd.CampaignAction.LAUNCH_APP;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public String getCategory() {
        String e2;
        AdDetails adDetails = this.a;
        return (adDetails == null || (e2 = adDetails.e()) == null) ? "" : e2;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public String getDescription() {
        String h;
        AdDetails adDetails = this.a;
        return (adDetails == null || (h = adDetails.h()) == null) ? "" : h;
    }

    public int getIdentifier() {
        return this.b;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public Bitmap getImageBitmap() {
        return this.c;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public String getImageUrl() {
        AdDetails adDetails = this.a;
        if (adDetails != null) {
            return adDetails.i();
        }
        return null;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public String getInstalls() {
        String j;
        AdDetails adDetails = this.a;
        return (adDetails == null || (j = adDetails.j()) == null) ? "" : j;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public String getPackageName() {
        String o;
        AdDetails adDetails = this.a;
        return (adDetails == null || (o = adDetails.o()) == null) ? "" : o;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public float getRating() {
        AdDetails adDetails = this.a;
        if (adDetails != null) {
            return adDetails.p();
        }
        return 5.0f;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public Bitmap getSecondaryImageBitmap() {
        return this.d;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public String getSecondaryImageUrl() {
        AdDetails adDetails = this.a;
        if (adDetails != null) {
            return adDetails.q();
        }
        return null;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public String getTitle() {
        String s;
        AdDetails adDetails = this.a;
        return (adDetails == null || (s = adDetails.s()) == null) ? "" : s;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public boolean isApp() {
        AdDetails adDetails = this.a;
        if (adDetails != null) {
            return adDetails.x();
        }
        return true;
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public boolean isBelowMinCPM() {
        AdDetails adDetails = this.a;
        return adDetails != null && adDetails.m();
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public void registerViewForInteraction(View view) {
        a(view);
        this.j.get().setOnClickListener(new d());
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public void registerViewForInteraction(View view, List<View> list) {
        registerViewForInteraction(view, list, null);
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public void registerViewForInteraction(View view, List<View> list, NativeAdDisplayListener nativeAdDisplayListener) {
        if (list == null || list.isEmpty() || this.j.get() != null) {
            registerViewForInteraction(view);
        } else {
            e eVar = new e();
            Iterator<View> it = list.iterator();
            while (it.hasNext()) {
                it.next().setOnClickListener(eVar);
            }
            a(view);
        }
        this.l = nativeAdDisplayListener;
    }

    public String toString() {
        String description = getDescription();
        if (description != null) {
            description = description.substring(0, Math.min(30, description.length()));
        }
        return "         Title: [" + getTitle() + "]\n         Description: [" + description + "]...\n         Rating: [" + getRating() + "]\n         Installs: [" + getInstalls() + "]\n         Category: [" + getCategory() + "]\n         PackageName: [" + getPackageName() + "]\n         CampaginAction: [" + getCampaignAction() + "]\n";
    }

    @Override // com.startapp.sdk.ads.nativead.NativeAdInterface
    public void unregisterView() {
        View.OnAttachStateChangeListener onAttachStateChangeListener;
        da daVar = this.i;
        if (daVar != null) {
            daVar.a();
            this.i = null;
        }
        View view = this.j.get();
        this.j.clear();
        if (view != null && Build.VERSION.SDK_INT >= 12 && (onAttachStateChangeListener = this.k) != null) {
            view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
        }
        Bitmap bitmap = this.c;
        if (bitmap != null) {
            bitmap.recycle();
            this.c = null;
        }
        Bitmap bitmap2 = this.d;
        if (bitmap2 != null) {
            bitmap2.recycle();
            this.d = null;
        }
    }
}
