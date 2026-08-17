package com.startapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.startapp.p5;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.sdk.adsbase.adinformation.AdInformationMetaData;
import com.startapp.sdk.adsbase.consent.ConsentActivity;
import com.startapp.sdk.adsbase.consent.ConsentConfig;
import com.startapp.sdk.adsbase.consent.ConsentTypeInfoConfig;
import com.startapp.sdk.adsbase.model.AdPreferences;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.MetaDataRequest;
import com.startapp.sdk.components.ComponentLocator;
import io.sentry.marshaller.json.JsonMarshaller;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class k7 implements t8 {
    public final Context a;
    public final p5 b;
    public Intent c;
    public boolean d = false;
    public boolean e = true;

    public k7(Context context, p5 p5Var) {
        this.a = context;
        this.b = p5Var;
    }

    public Boolean a() {
        if (d() && this.b.contains("consentApc")) {
            return Boolean.valueOf(this.b.getBoolean("consentApc", false));
        }
        return null;
    }

    @Override // com.startapp.t8
    public void a(MetaDataRequest.RequestReason requestReason) {
        MetaData.h.a(this);
    }

    @Override // com.startapp.t8
    public void a(MetaDataRequest.RequestReason requestReason, boolean z) {
        MetaData.h.a(this);
        ConsentConfig j = MetaData.h.j();
        if (j == null || !d()) {
            return;
        }
        Integer b = j.b();
        if (b != null) {
            a(b, Long.valueOf(j.i()), null, false, false);
        }
        if (requestReason != MetaDataRequest.RequestReason.CONSENT) {
            if (requestReason == MetaDataRequest.RequestReason.LAUNCH) {
                a(false, null, null, null);
            }
        } else {
            p5.a edit = this.b.edit();
            long i = j.i();
            edit.a("consentTimestamp", (String) Long.valueOf(i));
            edit.a.putLong("consentTimestamp", i);
            edit.apply();
        }
    }

    public void a(Integer num, Long l, Boolean bool, boolean z, boolean z2) {
        if (d()) {
            long j = this.b.getLong("consentTimestamp", 0L);
            int i = this.b.getInt("consentType", -1);
            boolean contains = this.b.contains("consentApc");
            boolean z3 = (num == null || i == num.intValue()) ? false : true;
            boolean z4 = (bool == null || (contains && this.b.getBoolean("consentApc", false) == bool.booleanValue())) ? false : true;
            boolean z5 = l != null && l.longValue() > j;
            if (z || z5) {
                if (z3 || z4) {
                    p5.a edit = this.b.edit();
                    if (z3) {
                        int intValue = num.intValue();
                        edit.a("consentType", (String) Integer.valueOf(intValue));
                        edit.a.putInt("consentType", intValue);
                    }
                    if (z4) {
                        boolean booleanValue = bool.booleanValue();
                        edit.a("consentApc", (String) Boolean.valueOf(booleanValue));
                        edit.a.putBoolean("consentApc", booleanValue);
                    }
                    if (z5) {
                        long longValue = l.longValue();
                        edit.a("consentTimestamp", (String) Long.valueOf(longValue));
                        edit.a.putLong("consentTimestamp", longValue);
                    }
                    edit.apply();
                    if (z2) {
                        MetaData.h.a(this.a, new AdPreferences(), MetaDataRequest.RequestReason.CONSENT, false, null, true);
                    }
                }
            }
        }
    }

    public final void a(boolean z, String str, String str2, String str3) {
        ConsentConfig j;
        Integer b;
        if ((z || StartAppSDKInternal.c()) && (j = MetaData.h.j()) != null) {
            if ((d() || z) && !this.d && aa.g(this.a) && aa.e(this.a)) {
                if (z || !(j.h() == null || j.g() == null || this.b.contains("consentApc"))) {
                    String c = z ? AdInformationMetaData.a.a().c() : j.f();
                    if (c == null) {
                        return;
                    }
                    Intent intent = new Intent(this.a, (Class<?>) ConsentActivity.class);
                    intent.setFlags(805306368);
                    intent.setData(Uri.parse(c));
                    intent.putExtra("allowCT", j.k());
                    intent.putExtra(JsonMarshaller.TIMESTAMP, j.i());
                    Integer valueOf = Integer.valueOf(z ? 4 : j.h().intValue());
                    if (valueOf != null) {
                        intent.putExtra("templateName", valueOf);
                    }
                    Integer valueOf2 = Integer.valueOf(z ? 7 : j.g().intValue());
                    if (valueOf2 != null) {
                        intent.putExtra("templateId", valueOf2);
                    }
                    if (!z) {
                        str = j.d();
                    }
                    if (str != null) {
                        intent.putExtra("dParam", str);
                    }
                    if (!z) {
                        str2 = j.e();
                    }
                    if (str2 != null) {
                        intent.putExtra("impressionUrl", str2);
                    }
                    if (!z) {
                        str3 = j.a();
                    }
                    if (str3 != null) {
                        intent.putExtra("clickUrl", str3);
                    }
                    if (z) {
                        intent.putExtra("advertisingId", ComponentLocator.a(this.a).a().a().b);
                        if (this.b.contains("consentType")) {
                            intent.putExtra("consentType", this.b.getInt("consentType", -1));
                        }
                    }
                    ConsentTypeInfoConfig c2 = j.c();
                    if (c2 != null) {
                        if (c2.b() != null) {
                            intent.putExtra("impression", c2.b());
                        }
                        if (c2.a() != null) {
                            intent.putExtra("falseClick", c2.a());
                        }
                        if (c2.c() != null) {
                            intent.putExtra("trueClick", c2.c());
                        }
                    }
                    if (z && (b = AdInformationMetaData.a.a().b()) != null) {
                        intent.putExtra("trueClick", b);
                    }
                    try {
                        this.a.startActivity(intent);
                        this.d = true;
                    } catch (Throwable th) {
                        p7.a(this.a, th);
                    }
                }
            }
        }
    }

    public Integer b() {
        if (d()) {
            int hashCode = ComponentLocator.a(this.a).a().a().b.hashCode();
            if (!this.b.contains("advIdHash") || this.b.getInt("advIdHash", 0) != hashCode) {
                p5.a remove = this.b.edit().remove("consentType").remove("consentTimestamp");
                remove.a("advIdHash", (String) Integer.valueOf(hashCode));
                remove.a.putInt("advIdHash", hashCode);
                remove.apply();
            }
        }
        if (d() && this.b.contains("consentType")) {
            return Integer.valueOf(this.b.getInt("consentType", -1));
        }
        return null;
    }

    public boolean c() {
        Boolean a = a();
        return a != null && a.booleanValue();
    }

    public final boolean d() {
        ConsentConfig j = MetaData.h.j();
        return this.e && j != null && j.k();
    }
}
