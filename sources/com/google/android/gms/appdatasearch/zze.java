package com.google.android.gms.appdatasearch;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

/* loaded from: classes.dex */
public class zze implements Parcelable.Creator<Feature> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Feature feature, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, feature.id);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, feature.gs, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, feature.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzaa, reason: merged with bridge method [inline-methods] */
    public Feature createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        int i = 0;
        Bundle bundle = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
                        break;
                    case 2:
                        bundle = com.google.android.gms.common.internal.safeparcel.zza.zzs(parcel, zzcq);
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
            return new Feature(i, i2, bundle);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzck, reason: merged with bridge method [inline-methods] */
    public Feature[] newArray(int i) {
        return new Feature[i];
    }
}
