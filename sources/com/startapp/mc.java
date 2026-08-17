package com.startapp;

import android.content.Context;
import android.os.Bundle;
import com.startapp.networkTest.startapp.NetworkTester;
import com.startapp.pc;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class mc extends pc {

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements NetworkTester.b {
        public a() {
        }

        @Override // com.startapp.networkTest.startapp.NetworkTester.b
        public void a(boolean z) {
            mc.this.callback.a(mc.this, z);
        }
    }

    public mc(Context context, pc.a aVar, Bundle bundle) {
        super(context, aVar, bundle);
    }

    @Override // com.startapp.pc, java.lang.Runnable
    public void run() {
        NetworkTester.runTests(this.context, new a());
    }
}
