package com.startapp;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class j3 extends y2 implements Parcelable {
    public static final Parcelable.Creator<j3> CREATOR = new a();
    public float f;
    public float g;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static class a implements Parcelable.Creator<j3> {
        @Override // android.os.Parcelable.Creator
        public j3 createFromParcel(Parcel parcel) {
            return new j3(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public j3[] newArray(int i) {
            return new j3[i];
        }
    }

    public j3(float f, float f2) {
        this.f = f;
        this.g = f2;
    }

    public j3(Parcel parcel) {
        super(parcel);
        this.f = parcel.readFloat();
        this.g = parcel.readFloat();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.startapp.y2
    public String toString() {
        return super.toString() + ", Friction: [" + this.f + "], Snap:[" + this.g + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.c);
        parcel.writeFloat(this.d);
        parcel.writeFloat(this.f);
        parcel.writeFloat(this.g);
    }
}
