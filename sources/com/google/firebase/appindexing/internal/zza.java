package com.google.firebase.appindexing.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.firebase.appindexing.internal.ActionImpl;

/* loaded from: classes.dex */
public class zza implements Parcelable.Creator<ActionImpl> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(ActionImpl actionImpl, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, actionImpl.zzcob(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, actionImpl.zzcoc(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, actionImpl.zzcod(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, actionImpl.zzcoe(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, (Parcelable) actionImpl.zzcof(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, actionImpl.zzcog(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, actionImpl.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzaez, reason: merged with bridge method [inline-methods] */
    public ActionImpl[] newArray(int i) {
        return new ActionImpl[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzwb, reason: merged with bridge method [inline-methods] */
    public ActionImpl createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        ActionImpl.MetadataImpl metadataImpl = null;
        String str5 = null;
        int i = 0;
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
                    case 4:
                        str4 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 5:
                        metadataImpl = (ActionImpl.MetadataImpl) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcq, ActionImpl.MetadataImpl.CREATOR);
                        break;
                    case 6:
                        str5 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
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
            return new ActionImpl(i, str, str2, str3, str4, metadataImpl, str5);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }
}
