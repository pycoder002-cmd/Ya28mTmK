package com.startapp;

import android.view.MotionEvent;
import com.startapp.sdk.ads.list3d.List3DView;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class b3 implements Runnable {
    public final /* synthetic */ List3DView a;

    public b3(List3DView list3DView) {
        this.a = list3DView;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 2, 0.0f, -20.0f, 0));
        this.a.dispatchTouchEvent(MotionEvent.obtain(System.currentTimeMillis(), System.currentTimeMillis(), 1, 0.0f, -20.0f, 0));
    }
}
