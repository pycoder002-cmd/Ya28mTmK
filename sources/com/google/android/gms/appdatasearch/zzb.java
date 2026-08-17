package com.google.android.gms.appdatasearch;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.zza;

/* loaded from: classes.dex */
public class zzb implements Parcelable.Creator<DocumentContents> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zza(DocumentContents documentContents, Parcel parcel, int i) {
        int zzcs = com.google.android.gms.common.internal.safeparcel.zzb.zzcs(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, (Parcelable[]) documentContents.gd, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, documentContents.ge, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, documentContents.gf);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, (Parcelable) documentContents.account, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, documentContents.mVersionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcs);
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzch, reason: merged with bridge method [inline-methods] */
    public DocumentContents[] newArray(int i) {
        return new DocumentContents[i];
    }

    @Override // android.os.Parcelable.Creator
    /* renamed from: zzx, reason: merged with bridge method [inline-methods] */
    public DocumentContents createFromParcel(Parcel parcel) {
        int zzcr = com.google.android.gms.common.internal.safeparcel.zza.zzcr(parcel);
        int i = 0;
        boolean z = false;
        DocumentSection[] documentSectionArr = null;
        String str = null;
        Account account = null;
        while (parcel.dataPosition() < zzcr) {
            int zzcq = com.google.android.gms.common.internal.safeparcel.zza.zzcq(parcel);
            int zzgu = com.google.android.gms.common.internal.safeparcel.zza.zzgu(zzcq);
            if (zzgu != 1000) {
                switch (zzgu) {
                    case 1:
                        documentSectionArr = (DocumentSection[]) com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcq, DocumentSection.CREATOR);
                        break;
                    case 2:
                        str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcq);
                        break;
                    case 3:
                        z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcq);
                        break;
                    case 4:
                        account = (Account) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcq, Account.CREATOR);
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
            return new DocumentContents(i, documentSectionArr, str, z, account);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(zzcr);
        throw new zza.C0010zza(sb.toString(), parcel);
    }
}
