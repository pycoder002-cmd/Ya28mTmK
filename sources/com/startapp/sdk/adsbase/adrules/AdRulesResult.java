package com.startapp.sdk.adsbase.adrules;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdRulesResult implements Serializable {
    private static final long serialVersionUID = 1;
    private String reason;
    private boolean shouldDisplayAd;

    public AdRulesResult(boolean z, String str) {
        this.shouldDisplayAd = z;
        this.reason = str;
    }

    public String a() {
        String str = this.reason;
        return str != null ? str.split(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR)[0] : "";
    }

    public boolean b() {
        return this.shouldDisplayAd;
    }
}
