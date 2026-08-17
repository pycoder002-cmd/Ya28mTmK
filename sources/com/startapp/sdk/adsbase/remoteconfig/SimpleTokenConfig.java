package com.startapp.sdk.adsbase.remoteconfig;

import android.content.Context;
import com.startapp.sdk.components.ComponentLocator;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class SimpleTokenConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean enabled = false;

    public boolean a(Context context) {
        ComponentLocator a = ComponentLocator.a(context);
        return !a.d().getBoolean("userDisabledSimpleToken", false) && this.enabled && a.f().c();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && SimpleTokenConfig.class == obj.getClass() && this.enabled == ((SimpleTokenConfig) obj).enabled;
    }

    public int hashCode() {
        return Boolean.valueOf(this.enabled).hashCode();
    }
}
