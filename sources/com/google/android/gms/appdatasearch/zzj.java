package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

/* loaded from: classes.dex */
public class zzj implements Parcelable.Creator<UsageInfo> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(UsageInfo usageInfo, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, (Parcelable) usageInfo.gR, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, usageInfo.gS);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 3, usageInfo.gT);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, usageInfo.zzbcj, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, (Parcelable) usageInfo.gU, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, usageInfo.gV);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 7, usageInfo.gW);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, usageInfo.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 8, usageInfo.gX);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzae, reason: merged with bridge method [inline-methods] */
    public UsageInfo createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        DocumentId documentId = null;
        String str = null;
        DocumentContents documentContents = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        long j = 0;
        int i4 = -1;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        documentId = (DocumentId) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcq, DocumentId.CREATOR);
                        break;
                    case 2:
                        j = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzcq);
                        break;
                    case 3:
                        i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
                        break;
                    case 4:
                        str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 5:
                        documentContents = (DocumentContents) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcq, DocumentContents.CREATOR);
                        break;
                    case 6:
                        z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcq);
                        break;
                    case 7:
                        i4 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
                        break;
                    case 8:
                        i3 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
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
            return new UsageInfo(i, documentId, j, i2, str, documentContents, z, i4, i3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzcs, reason: merged with bridge method [inline-methods] */
    public UsageInfo[] newArray(int i) {
        return new UsageInfo[i];
    }
}
