package com.startapp;

import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.adsbase.remoteconfig.NetworkDiagnosticConfig;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class fb implements k9<NetworkDiagnosticConfig> {
    public fb(ComponentLocator.h hVar) {
    }

    @Override // com.startapp.k9
    public NetworkDiagnosticConfig call() {
        return MetaData.h.t();
    }
}
