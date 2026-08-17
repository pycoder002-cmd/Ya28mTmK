package com.startapp;

import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class gb implements k9<o7> {
    public gb(ComponentLocator.h hVar) {
    }

    @Override // com.startapp.k9
    public o7 call() {
        MetaData metaData = MetaData.h;
        return new o7(metaData.M(), metaData.k(), metaData.r());
    }
}
