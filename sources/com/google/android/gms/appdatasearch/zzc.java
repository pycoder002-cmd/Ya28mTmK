package com.google.android.gms.appdatasearch;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

/* loaded from: classes.dex */
public class zzc implements Parcelable.Creator<DocumentId> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(DocumentId documentId, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, documentId.zzcjc, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, documentId.gk, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, documentId.gl, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, documentId.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzci, reason: merged with bridge method [inline-methods] */
    public DocumentId[] newArray(int i) {
        return new DocumentId[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzy, reason: merged with bridge method [inline-methods] */
    public DocumentId createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        String str = null;
        int i = 0;
        String str2 = null;
        String str3 = null;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 2:
                        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 3:
                        str3 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
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
            return new DocumentId(i, str, str2, str3);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }
}
