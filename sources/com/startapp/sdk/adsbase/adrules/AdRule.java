package com.startapp.sdk.adsbase.adrules;

import com.startapp.e;
import com.startapp.i6;
import java.io.Serializable;
import java.util.List;

/* compiled from: StartAppSDK */
@e(decider = "type", packageName = "com.startapp.sdk.adsbase.adrules")
/* loaded from: classes3.dex */
public abstract class AdRule implements Serializable {
    private static final long serialVersionUID = 1;
    public transient boolean a;

    public abstract boolean a(List<i6> list);
}
