package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;

/* loaded from: classes.dex */
public interface zztn extends IInterface {

    /* loaded from: classes.dex */
    public static abstract class zza extends Binder implements zztn {

        /* renamed from: com.google.android.gms.internal.zztn$zza$zza, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        private static class C0038zza implements zztn {
            private IBinder zzajq;

            C0038zza(IBinder iBinder) {
                this.zzajq = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.zzajq;
            }

            @Override // com.google.android.gms.internal.zztn
            public com.google.android.gms.dynamic.zzd zza(com.google.android.gms.dynamic.zzd zzdVar, String str, byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                    obtain.writeStrongBinder(zzdVar != null ? zzdVar.asBinder() : null);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    this.zzajq.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return zzd.zza.zzfd(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zztn zzff(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zztn)) ? new C0038zza(iBinder) : (zztn) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeString("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                return true;
            }
            parcel.enforceInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
            com.google.android.gms.dynamic.zzd zza = zza(zzd.zza.zzfd(parcel.readStrongBinder()), parcel.readString(), parcel.createByteArray());
            parcel2.writeNoException();
            parcel2.writeStrongBinder(zza != null ? zza.asBinder() : null);
            return true;
        }
    }

    com.google.android.gms.dynamic.zzd zza(com.google.android.gms.dynamic.zzd zzdVar, String str, byte[] bArr) throws RemoteException;
}
