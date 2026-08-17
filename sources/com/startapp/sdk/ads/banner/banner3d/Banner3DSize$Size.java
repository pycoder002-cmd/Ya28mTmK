package com.startapp.sdk.ads.banner.banner3d;

import com.startapp.l2;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public enum Banner3DSize$Size {
    XXSMALL(new l2(280, 50)),
    XSMALL(new l2(300, 50)),
    SMALL(new l2(320, 50)),
    MEDIUM(new l2(468, 60)),
    LARGE(new l2(728, 90)),
    XLARGE(new l2(1024, 90));

    private l2 size;

    Banner3DSize$Size(l2 l2Var) {
        this.size = l2Var;
    }

    public l2 getSize() {
        return this.size;
    }
}
