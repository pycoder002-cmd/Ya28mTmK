package com.startapp.sdk.adsbase.remoteconfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.startapp.sdk.adsbase.StartAppSDKInternal;
import com.startapp.u5;
import com.startapp.y8;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class BootCompleteListener extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str = StartAppSDKInternal.a;
        Context b = y8.b(context);
        StartAppSDKInternal.a(b, new u5(b));
    }
}
