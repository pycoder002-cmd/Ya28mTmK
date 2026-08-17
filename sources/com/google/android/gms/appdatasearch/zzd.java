package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

/* loaded from: classes.dex */
public class zzd implements Parcelable.Creator<DocumentSection> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(DocumentSection documentSection, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, documentSection.go, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, (Parcelable) documentSection.gp, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 4, documentSection.gq);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, documentSection.gr, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, documentSection.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzcj, reason: merged with bridge method [inline-methods] */
    public DocumentSection[] newArray(int i) {
        return new DocumentSection[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzz, reason: merged with bridge method [inline-methods] */
    public DocumentSection createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        String str = null;
        RegisterSectionInfo registerSectionInfo = null;
        byte[] bArr = null;
        int i = 0;
        int i2 = -1;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu == 1) {
                str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
            } else if (zzgu != 1000) {
                switch (zzgu) {
                    case 3:
                        registerSectionInfo = (RegisterSectionInfo) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcq, RegisterSectionInfo.CREATOR);
                        break;
                    case 4:
                        i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
                        break;
                    case 5:
                        bArr = com.google.android.gms.common.internal.safeparcel.zza.zzt(parcel, zzcq);
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
            return new DocumentSection(i, str, registerSectionInfo, i2, bArr);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }
}
