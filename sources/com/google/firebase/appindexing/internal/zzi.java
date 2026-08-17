package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.firebase.appindexing.internal.ActionImpl;

/* loaded from: classes.dex */
public class zzi implements Parcelable.Creator<ActionImpl.MetadataImpl> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(ActionImpl.MetadataImpl metadataImpl, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, metadataImpl.zzcoh());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, metadataImpl.zzcoi());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, metadataImpl.zzcoj(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, metadataImpl.getAccountName(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, metadataImpl.zzcok(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, metadataImpl.zzcol());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, metadataImpl.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzafd, reason: merged with bridge method [inline-methods] */
    public ActionImpl.MetadataImpl[] newArray(int i) {
        return new ActionImpl.MetadataImpl[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzwd, reason: merged with bridge method [inline-methods] */
    public ActionImpl.MetadataImpl createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        String str = null;
        String str2 = null;
        byte[] bArr = null;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
                        break;
                    case 2:
                        z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcq);
                        break;
                    case 3:
                        str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 4:
                        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 5:
                        bArr = com.google.android.gms.common.internal.safeparcel.zza.zzt(parcel, zzcq);
                        break;
                    case 6:
                        z2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcq);
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
            return new ActionImpl.MetadataImpl(i, i2, z, str, str2, bArr, z2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }
}
