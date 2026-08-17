package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.zzp;

/* loaded from: classes.dex */
public class zza extends zzp.zza {
    int De;

    public static Account zza(zzp zzpVar) {
        if (zzpVar == null) {
            return null;
        }
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return zzpVar.getAccount();
        } catch (RemoteException unused) {
            Log.w("AccountAccessor", "Remote account accessor probably died");
            return null;
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zza)) {
            return false;
        }
        Account account = null;
        return account.equals(null);
    }

    @Override // com.google.android.gms.common.internal.zzp
    public Account getAccount() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == this.De) {
            return null;
        }
        if (!com.google.android.gms.common.zze.zzf(null, callingUid)) {
            throw new SecurityException("Caller is not GooglePlayServices");
        }
        this.De = callingUid;
        return null;
    }
}
