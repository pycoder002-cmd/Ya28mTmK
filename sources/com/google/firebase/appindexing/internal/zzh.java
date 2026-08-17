package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.firebase.appindexing.internal.Thing;

/* loaded from: classes.dex */
public class zzh implements Parcelable.Creator<Thing.Metadata> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Thing.Metadata metadata, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, metadata.zzcop());
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, metadata.getScore());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, metadata.zzcoq(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, metadata.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzafc, reason: merged with bridge method [inline-methods] */
    public Thing.Metadata[] newArray(int i) {
        return new Thing.Metadata[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzwc, reason: merged with bridge method [inline-methods] */
    public Thing.Metadata createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        int i = 0;
        int i2 = 0;
        String str = null;
        boolean z = false;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcq);
                        break;
                    case 2:
                        i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcq);
                        break;
                    case 3:
                        str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
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
            return new Thing.Metadata(i, z, i2, str);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }
}
