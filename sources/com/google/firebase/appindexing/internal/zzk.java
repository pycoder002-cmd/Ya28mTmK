package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

/* loaded from: classes.dex */
public class zzk implements Parcelable.Creator<UpdateTagsRequest> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(UpdateTagsRequest updateTagsRequest, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, updateTagsRequest.getUrl(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, updateTagsRequest.zzcor(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, updateTagsRequest.zzcos(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, updateTagsRequest.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzaff, reason: merged with bridge method [inline-methods] */
    public UpdateTagsRequest[] newArray(int i) {
        return new UpdateTagsRequest[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzwf, reason: merged with bridge method [inline-methods] */
    public UpdateTagsRequest createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        String str = null;
        int i = 0;
        String[] strArr = null;
        String[] strArr2 = null;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 2:
                        strArr = com.google.android.gms.common.internal.safeparcel.zza.zzac(parcel, zzcq);
                        break;
                    case 3:
                        strArr2 = com.google.android.gms.common.internal.safeparcel.zza.zzac(parcel, zzcq);
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
            return new UpdateTagsRequest(i, str, strArr, strArr2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }
}
