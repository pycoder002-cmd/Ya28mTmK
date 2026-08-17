package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: classes.dex */
public interface zzp extends IInterface {

    /* loaded from: classes.dex */
    public static abstract class zza extends Binder implements zzp {

        /* renamed from: com.google.android.gms.common.internal.zzp$zza$zza, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class C0012zza implements zzp {
            private IBinder zzajq;

            C0012zza(IBinder iBinder) {
                this.zzajq = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.zzajq;
            }

            @Override // com.google.android.gms.common.internal.zzp
            public Account getAccount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IAccountAccessor");
                    this.zzajq.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Account) Account.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzp zzdr(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzp)) ? new C0012zza(iBinder) : (zzp) queryLocalInterface;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 2) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.google.android.gms.common.internal.IAccountAccessor");
                return true;
            }
            parcel.enforceInterface("com.google.android.gms.common.internal.IAccountAccessor");
            Account account = getAccount();
            parcel2.writeNoException();
            if (account == null) {
                parcel2.writeInt(0);
                return true;
            }
            parcel2.writeInt(1);
            account.writeToParcel(parcel2, 1);
            return true;
        }
    }

    Account getAccount() throws RemoteException;
}
