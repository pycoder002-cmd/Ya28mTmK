package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.appdatasearch.GetRecentContextCall;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class zzg implements Parcelable.Creator<GetRecentContextCall.Response> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(GetRecentContextCall.Response response, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, (Parcelable) response.gy, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, response.gz, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, response.gA, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, response.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzac, reason: merged with bridge method [inline-methods] */
    public GetRecentContextCall.Response createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        Status status = null;
        int i = 0;
        ArrayList arrayList = null;
        String[] strArr = null;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        status = (Status) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcq, Status.CREATOR);
                        break;
                    case 2:
                        arrayList = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcq, UsageInfo.CREATOR);
                        break;
                    case 3:
                        strArr = com.google.android.gms.common.internal.safeparcel.zza.zzac(parcel, zzcq);
                        break;
                    default:
                        com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcq);
                        break;
                }
            } else {
                i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
            }
        }
        if (parcel.dataPosition() == zzcr) {
            return new GetRecentContextCall.Response(i, status, arrayList, strArr);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzcm, reason: merged with bridge method [inline-methods] */
    public GetRecentContextCall.Response[] newArray(int i) {
        return new GetRecentContextCall.Response[i];
    }
}
