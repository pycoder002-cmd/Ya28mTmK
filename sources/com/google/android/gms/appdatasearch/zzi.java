package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

/* loaded from: classes.dex */
public class zzi implements Parcelable.Creator<RegisterSectionInfo> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(RegisterSectionInfo registerSectionInfo, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, registerSectionInfo.name, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, registerSectionInfo.gD, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, registerSectionInfo.gE);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 4, registerSectionInfo.weight);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, registerSectionInfo.gF);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, registerSectionInfo.gG, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, (Parcelable[]) registerSectionInfo.gH, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, registerSectionInfo.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, registerSectionInfo.gI, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 11, registerSectionInfo.gJ, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzad, reason: merged with bridge method [inline-methods] */
    public RegisterSectionInfo createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        String str = null;
        String str2 = null;
        String str3 = null;
        Feature[] featureArr = null;
        int[] iArr = null;
        String str4 = null;
        int i2 = 1;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu == 11) {
                str4 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
            } else if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 2:
                        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 3:
                        z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcq);
                        break;
                    case 4:
                        i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
                        break;
                    case 5:
                        z2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcq);
                        break;
                    case 6:
                        str3 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 7:
                        featureArr = (Feature[]) com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcq, Feature.CREATOR);
                        break;
                    case 8:
                        iArr = com.google.android.gms.common.internal.safeparcel.zza.zzw(parcel, zzcq);
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
            return new RegisterSectionInfo(i, str, str2, z, i2, z2, str3, featureArr, iArr, str4);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzcp, reason: merged with bridge method [inline-methods] */
    public RegisterSectionInfo[] newArray(int i) {
        return new RegisterSectionInfo[i];
    }
}
