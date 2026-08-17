package com.startapp;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public interface a extends IInterface {

    /* compiled from: StartAppSDK */
    /* renamed from: com.startapp.a$a, reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    public static abstract class AbstractBinderC0053a extends Binder implements a {
        public static final /* synthetic */ int a = 0;

        /* compiled from: StartAppSDK */
        /* renamed from: com.startapp.a$a$a, reason: collision with other inner class name */
        /* loaded from: classes3.dex */
        public static class C0054a implements a {
            public IBinder a;

            public C0054a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // com.startapp.a
            public Bundle a(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.finsky.externalreferrer.IGetInstallReferrerService");
                    obtain.writeInt(1);
                    bundle.writeToParcel(obtain, 0);
                    if (!this.a.transact(1, obtain, obtain2, 0)) {
                        int i = AbstractBinderC0053a.a;
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }
        }
    }

    Bundle a(Bundle bundle) throws RemoteException;
}
