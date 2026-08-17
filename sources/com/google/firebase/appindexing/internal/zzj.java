package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.firebase.appindexing.internal.Thing;

/* loaded from: classes.dex */
public class zzj implements Parcelable.Creator<Thing> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(Thing thing, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, thing.zzahu(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, (Parcelable) thing.zzcon(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, thing.getId(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, thing.getType(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, thing.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzafe, reason: merged with bridge method [inline-methods] */
    public Thing[] newArray(int i) {
        return new Thing[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzwe, reason: merged with bridge method [inline-methods] */
    public Thing createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        Bundle bundle = null;
        Thing.Metadata metadata = null;
        String str = null;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        bundle = com.google.android.gms.common.internal.safeparcel.zza.zzs(parcel, zzcq);
                        break;
                    case 2:
                        metadata = (Thing.Metadata) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcq, Thing.Metadata.CREATOR);
                        break;
                    case 3:
                        str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 4:
                        str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
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
            return new Thing(i, bundle, metadata, str, str2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }
}
