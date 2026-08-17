package com.startapp.sdk.adsbase;

import android.app.Activity;
import com.startapp.g5;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ActivityExtra implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean isActivityFullScreen;

    public ActivityExtra(Activity activity) {
        a(g5.a(activity));
    }

    public final void a(boolean z) {
        this.isActivityFullScreen = z;
    }

    public boolean a() {
        return this.isActivityFullScreen;
    }
}
