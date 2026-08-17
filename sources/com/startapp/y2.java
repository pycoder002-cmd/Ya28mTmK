package com.startapp;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.animation.AnimationUtils;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class y2 implements Parcelable {
    public float a;
    public float b;
    public float c;
    public float d;
    public long e;

    public y2() {
        this.c = Float.MAX_VALUE;
        this.d = -3.4028235E38f;
        this.e = 0L;
    }

    public y2(Parcel parcel) {
        this.c = Float.MAX_VALUE;
        this.d = -3.4028235E38f;
        this.e = 0L;
        this.a = parcel.readFloat();
        this.b = parcel.readFloat();
        this.c = parcel.readFloat();
        this.d = parcel.readFloat();
        this.e = AnimationUtils.currentAnimationTimeMillis();
    }

    public String toString() {
        return "Position: [" + this.a + "], Velocity:[" + this.b + "], MaxPos: [" + this.c + "], mMinPos: [" + this.d + "] LastTime:[" + this.e + "]";
    }
}
