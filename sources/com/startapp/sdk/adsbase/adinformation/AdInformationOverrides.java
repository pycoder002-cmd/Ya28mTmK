package com.startapp.sdk.adsbase.adinformation;

import com.startapp.f;
import com.startapp.sdk.adsbase.adinformation.AdInformationPositions;
import java.io.Serializable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class AdInformationOverrides implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean enableOverride = false;
    private boolean enable = true;
    private boolean positionOverride = false;

    @f(type = AdInformationPositions.Position.class)
    private AdInformationPositions.Position position = AdInformationPositions.Position.getByName(AdInformationPositions.a);

    public static AdInformationOverrides a() {
        return new AdInformationOverrides();
    }

    public void a(AdInformationPositions.Position position) {
        this.position = position;
        if (position != null) {
            this.positionOverride = true;
        } else {
            this.positionOverride = false;
        }
    }

    public void a(boolean z) {
        this.enable = z;
        this.enableOverride = true;
    }

    public AdInformationPositions.Position b() {
        return this.position;
    }

    public boolean c() {
        return this.enable;
    }

    public boolean d() {
        return this.enableOverride;
    }

    public boolean e() {
        return this.positionOverride;
    }
}
