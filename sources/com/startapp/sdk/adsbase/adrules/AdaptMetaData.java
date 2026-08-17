package com.startapp.sdk.adsbase.adrules;

import com.startapp.f;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdaptMetaData implements Serializable {
    public static transient AdaptMetaData a = new AdaptMetaData();

    @f(complex = true)
    private AdRules adRules = new AdRules();
    private String adaptMetaDataUpdateVersion = "4.9.1";

    public AdRules a() {
        return this.adRules;
    }
}
