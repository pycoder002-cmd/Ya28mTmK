package com.google.android.gms.search;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* loaded from: classes.dex */
public class GoogleNowAuthState extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GoogleNowAuthState> CREATOR = new zza();
    String aCV;
    String aCW;
    long aCX;
    final int mVersionCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GoogleNowAuthState(int i, String str, String str2, long j) {
        this.mVersionCode = i;
        this.aCV = str;
        this.aCW = str2;
        this.aCX = j;
    }

    public String getAccessToken() {
        return this.aCW;
    }

    public String getAuthCode() {
        return this.aCV;
    }

    public long getNextAllowedTimeMillis() {
        return this.aCX;
    }

    public String toString() {
        String str = this.aCV;
        String str2 = this.aCW;
        long j = this.aCX;
        StringBuilder sb = new StringBuilder(74 + String.valueOf(str).length() + String.valueOf(str2).length());
        sb.append("mAuthCode = ");
        sb.append(str);
        sb.append("\nmAccessToken = ");
        sb.append(str2);
        sb.append("\nmNextAllowedTimeMillis = ");
        sb.append(j);
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }
}
