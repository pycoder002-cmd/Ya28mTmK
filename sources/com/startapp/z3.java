package com.startapp;

import android.content.Context;
import android.text.TextUtils;
import com.startapp.c4;
import com.startapp.e5;
import com.startapp.sdk.ads.video.VideoAdDetails;
import com.startapp.sdk.ads.video.VideoEnabledAd;
import com.startapp.sdk.ads.video.VideoUtil$VideoEligibility;
import com.startapp.sdk.ads.video.vast.VASTErrorCodes;
import com.startapp.sdk.adsbase.Ad;
import com.startapp.sdk.adsbase.AdsCommonMetaData;
import com.startapp.sdk.adsbase.HtmlAd;
import com.startapp.sdk.adsbase.StartAppAd;
import com.startapp.sdk.adsbase.VideoConfig;
import com.startapp.sdk.adsbase.adlisteners.AdEventListener;
import com.startapp.sdk.adsbase.cache.CacheKey;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.model.GetAdRequest;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.w4;
import com.startapp.za;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class z3 extends gc {
    public final long l;
    public volatile CacheKey m;
    public int n;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements e5.a {
        public a() {
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements w4.b {
        public final /* synthetic */ Boolean a;

        public b(Boolean bool) {
            this.a = bool;
        }

        @Override // com.startapp.w4.b
        public void a(String str) {
            if (str != null) {
                if (!str.equals("downloadInterrupted")) {
                    z3.super.b(this.a);
                    z3.this.e().a(str);
                }
                z3.this.a(this.a.booleanValue());
                return;
            }
            z3.this.a(false);
            z3 z3Var = z3.this;
            d.a(z3Var.a, z3Var.d, z3Var.b);
            z3.a(z3.this, VASTErrorCodes.FileNotFound, (List) null);
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c implements c4.a {
        public c() {
        }

        @Override // com.startapp.c4.a
        public void a(String str) {
            z3.this.e().a(str);
        }
    }

    public z3(Context context, Ad ad, AdPreferences adPreferences, AdEventListener adEventListener) {
        super(context, ad, adPreferences, adEventListener, AdPreferences.Placement.INAPP_OVERLAY, true);
        this.l = System.currentTimeMillis();
        this.n = 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0045 A[Catch: all -> 0x0079, TryCatch #0 {all -> 0x0079, blocks: (B:31:0x0005, B:9:0x0045, B:11:0x0048, B:14:0x0050, B:16:0x0056, B:17:0x005c, B:19:0x006b, B:42:0x001f, B:3:0x0025, B:5:0x002b, B:7:0x0035, B:34:0x000b, B:36:0x000f, B:37:0x0013, B:39:0x0019), top: B:30:0x0005, inners: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(com.startapp.z3 r5, com.startapp.sdk.ads.video.vast.VASTErrorCodes r6, java.util.List r7) {
        /*
            r5.getClass()
            if (r7 == 0) goto L25
            int r0 = r7.size()     // Catch: java.lang.Throwable -> L79
            if (r0 <= 0) goto L25
            com.startapp.sdk.ads.video.vast.VASTErrorCodes r0 = com.startapp.sdk.ads.video.vast.VASTErrorCodes.SAProcessSuccess     // Catch: java.lang.Throwable -> L1e
            if (r6 != r0) goto L13
            java.util.List r7 = com.startapp.aa.a(r7)     // Catch: java.lang.Throwable -> L1e
        L13:
            int r0 = r7.size()     // Catch: java.lang.Throwable -> L1e
            if (r0 <= 0) goto L42
            com.startapp.sdk.ads.video.tracking.ActionTrackingLink[] r7 = com.startapp.sdk.ads.video.tracking.VideoTrackingDetails.b(r7)     // Catch: java.lang.Throwable -> L1e
            goto L43
        L1e:
            r7 = move-exception
            android.content.Context r0 = r5.a     // Catch: java.lang.Throwable -> L79
            com.startapp.p7.a(r0, r7)     // Catch: java.lang.Throwable -> L79
            goto L42
        L25:
            com.startapp.sdk.ads.video.VideoAdDetails r7 = r5.e()     // Catch: java.lang.Throwable -> L79
            if (r7 == 0) goto L42
            com.startapp.sdk.ads.video.VideoAdDetails r7 = r5.e()     // Catch: java.lang.Throwable -> L79
            com.startapp.sdk.ads.video.tracking.VideoTrackingDetails r7 = r7.f()     // Catch: java.lang.Throwable -> L79
            if (r7 == 0) goto L42
            com.startapp.sdk.ads.video.VideoAdDetails r7 = r5.e()     // Catch: java.lang.Throwable -> L79
            com.startapp.sdk.ads.video.tracking.VideoTrackingDetails r7 = r7.f()     // Catch: java.lang.Throwable -> L79
            com.startapp.sdk.ads.video.tracking.ActionTrackingLink[] r7 = r7.e()     // Catch: java.lang.Throwable -> L79
            goto L43
        L42:
            r7 = 0
        L43:
            if (r7 == 0) goto L7f
            int r0 = r7.length     // Catch: java.lang.Throwable -> L79
            if (r0 <= 0) goto L7f
            com.startapp.sdk.ads.video.VideoAdDetails r0 = r5.e()     // Catch: java.lang.Throwable -> L79
            java.lang.String r1 = ""
            if (r0 == 0) goto L5b
            java.lang.String r2 = r0.g()     // Catch: java.lang.Throwable -> L79
            if (r2 == 0) goto L5b
            java.lang.String r0 = r0.g()     // Catch: java.lang.Throwable -> L79
            goto L5c
        L5b:
            r0 = r1
        L5c:
            com.startapp.sdk.ads.video.tracking.VideoTrackingParams r2 = new com.startapp.sdk.ads.video.tracking.VideoTrackingParams     // Catch: java.lang.Throwable -> L79
            java.lang.String r3 = "1"
            r4 = 0
            r2.<init>(r1, r4, r4, r3)     // Catch: java.lang.Throwable -> L79
            com.startapp.y4 r1 = new com.startapp.y4     // Catch: java.lang.Throwable -> L79
            r1.<init>(r7, r2, r0, r4)     // Catch: java.lang.Throwable -> L79
            java.lang.String r7 = "error"
            r1.e = r7     // Catch: java.lang.Throwable -> L79
            r1.f = r6     // Catch: java.lang.Throwable -> L79
            com.startapp.x4 r6 = r1.a()     // Catch: java.lang.Throwable -> L79
            android.content.Context r7 = r5.a     // Catch: java.lang.Throwable -> L79
            com.startapp.d.a(r7, r6)     // Catch: java.lang.Throwable -> L79
            goto L7f
        L79:
            r6 = move-exception
            android.content.Context r5 = r5.a
            com.startapp.p7.a(r5, r6)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.z3.a(com.startapp.z3, com.startapp.sdk.ads.video.vast.VASTErrorCodes, java.util.List):void");
    }

    @Override // com.startapp.gc, com.startapp.k5
    public void a(Boolean bool) {
        super.a(bool);
        if (bool.booleanValue()) {
            if (e() != null) {
                if (AdsCommonMetaData.h.G().p()) {
                    super.b(bool);
                }
                e().a(this.c.isVideoMuted());
                b bVar = new b(bool);
                c cVar = new c();
                Context b2 = y8.b(this.a);
                h4 h4Var = h4.a;
                String g = e().g();
                h4Var.getClass();
                ComponentLocator.a(b2).C.b().execute(new e4(h4Var, b2, g, bVar, cVar));
                return;
            }
        }
        a(bool.booleanValue());
    }

    @Override // com.startapp.gc, com.startapp.k5
    public boolean a(Object obj) {
        VASTErrorCodes vASTErrorCodes;
        za.a aVar = (za.a) obj;
        String str = aVar != null ? aVar.b : null;
        if (str == null || !str.toLowerCase().contains("json")) {
            String str2 = aVar != null ? aVar.a : null;
            if (AdsCommonMetaData.h.G().q()) {
                if (aa.a(str2, "@videoJson@", "@videoJson@") != null) {
                    b(false);
                }
            }
            return super.a(obj);
        }
        VideoConfig G = AdsCommonMetaData.h.G();
        if (G.q()) {
            Set<String> set = this.i.D0;
            if (!(set != null && set.size() > 0)) {
                b(true);
            }
        }
        try {
            d4 d4Var = (d4) com.startapp.c.a(aVar.a, d4.class);
            if (d4Var == null || d4Var.getVastTag() == null) {
                return a("no VAST wrapper in json", (Throwable) null, true);
            }
            String K = MetaData.h.K();
            b5 b5Var = (!d4Var.isRecordHops() || TextUtils.isEmpty(K)) ? null : new b5(this.a, K, d4Var.getPartnerResponse(), d4Var.getPartnerName(), d4Var.isSkipFailed());
            Context context = this.a;
            ComponentLocator.a(context).d();
            e5 e5Var = new e5(context);
            e5Var.g = G.e();
            e5Var.d = new a();
            a5 a2 = e5Var.a(d4Var.getVastTag(), new ArrayList(), b5Var);
            if (a2 != null) {
                ArrayList arrayList = new ArrayList(a2.a);
                VASTErrorCodes vASTErrorCodes2 = VASTErrorCodes.SAProcessSuccess;
                e5Var.a(arrayList, vASTErrorCodes2);
                if (b5Var != null) {
                    b5Var.a(vASTErrorCodes2);
                }
            } else if (b5Var != null && (vASTErrorCodes = e5Var.e) != null) {
                b5Var.a(vASTErrorCodes);
            }
            if (a2 == null) {
                if (d4Var.getCampaignId() != null) {
                    this.h.add(d4Var.getCampaignId());
                }
                this.n++;
                ((VideoEnabledAd) this.b).v();
                return System.currentTimeMillis() - this.l >= ((long) G.n()) ? a("VAST retry timeout", (Throwable) null, false) : this.n > G.d() ? a("VAST too many excludes", (Throwable) null, false) : a().booleanValue();
            }
            Ad ad = this.b;
            ((VideoEnabledAd) ad).a(a2, G, ad.getType() != Ad.AdType.REWARDED_VIDEO);
            if (d4Var.getTtlSec() != null) {
                ((VideoEnabledAd) this.b).b(d4Var.getTtlSec());
            }
            aVar.a = d4Var.getAdmTag();
            aVar.b = "text/html";
            return super.a(aVar);
        } catch (Exception e) {
            return a("VAST json parsing", e, true);
        }
    }

    public final boolean a(String str, Throwable th, boolean z) {
        if (th != null) {
            p7.a(this.a, th);
        } else if (z) {
            p7 p7Var = new p7(q7.c);
            p7Var.d = str;
            p7Var.a(this.a);
        }
        Object c2 = v6.a.c(this.m);
        if (!(c2 instanceof HtmlAd)) {
            this.b.setErrorMessage(this.f);
            return false;
        }
        za.a aVar = new za.a();
        aVar.b = "text/html";
        aVar.a = ((HtmlAd) c2).j();
        return super.a(aVar);
    }

    @Override // com.startapp.gc, com.startapp.k5
    public void b(Boolean bool) {
        if (e() != null) {
            return;
        }
        super.b(bool);
    }

    public final void b(boolean z) {
        Ad.AdType type = this.b.getType();
        Ad.AdType adType = Ad.AdType.REWARDED_VIDEO;
        if ((type == adType || this.b.getType() == Ad.AdType.VIDEO) && !z) {
            return;
        }
        AdPreferences adPreferences = this.c;
        AdPreferences adPreferences2 = adPreferences == null ? new AdPreferences() : new AdPreferences(adPreferences);
        adPreferences2.setType((this.b.getType() == adType || this.b.getType() == Ad.AdType.VIDEO) ? Ad.AdType.VIDEO_NO_VAST : Ad.AdType.NON_VIDEO);
        CacheKey a2 = v6.a.a(this.a, (StartAppAd) null, this.e, adPreferences2, (AdEventListener) null);
        if (z) {
            this.m = a2;
        }
    }

    @Override // com.startapp.gc
    public boolean b(GetAdRequest getAdRequest) {
        VideoUtil$VideoEligibility b2;
        if (!(getAdRequest != null)) {
            return false;
        }
        if (!getAdRequest.b() || (b2 = d.b(this.a)) == VideoUtil$VideoEligibility.ELIGIBLE) {
            return true;
        }
        this.f = b2.a();
        return false;
    }

    @Override // com.startapp.k5
    public GetAdRequest c() {
        GetAdRequest a2 = a((GetAdRequest) new y3());
        if (a2 != null) {
            a2.f(this.a);
        }
        return a2;
    }

    public VideoAdDetails e() {
        return ((VideoEnabledAd) this.b).w();
    }
}
