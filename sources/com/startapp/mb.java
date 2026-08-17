package com.startapp;

import com.startapp.sdk.adsbase.remoteconfig.BluetoothConfig;
import com.startapp.sdk.adsbase.remoteconfig.MetaData;
import com.startapp.sdk.components.ComponentLocator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class mb implements k9<BluetoothConfig> {
    public mb(ComponentLocator.m mVar) {
    }

    @Override // com.startapp.k9
    public BluetoothConfig call() {
        return MetaData.h.f();
    }
}
