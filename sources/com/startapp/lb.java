package com.startapp;

import com.startapp.sdk.adsbase.remoteconfig.AnalyticsConfig;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class lb implements k9<AnalyticsConfig> {
    public lb(ComponentLocator.l lVar) {
    }

    @Override // com.startapp.k9
    public AnalyticsConfig call() {
        return MetaData.h.analytics;
    }
}
