package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public final class zzk implements Handler.Callback {
    private final zza Ee;
    private final Handler mHandler;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> Ef = new ArrayList<>();
    final ArrayList<GoogleApiClient.ConnectionCallbacks> Eg = new ArrayList<>();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> Eh = new ArrayList<>();
    private volatile boolean Ei = false;
    private final AtomicInteger Ej = new AtomicInteger(0);
    private boolean Ek = false;
    private final Object zzako = new Object();

    /* loaded from: classes.dex */
    public interface zza {
        boolean isConnected();

        Bundle zzapn();
    }

    public zzk(Looper looper, zza zzaVar) {
        this.Ee = zzaVar;
        this.mHandler = new Handler(looper, this);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1) {
            int i = message.what;
            StringBuilder sb = new StringBuilder(45);
            sb.append("Don't know how to handle message: ");
            sb.append(i);
            Log.wtf("GmsClientEvents", sb.toString(), new Exception());
            return false;
        }
        GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) message.obj;
        synchronized (this.zzako) {
            if (this.Ei && this.Ee.isConnected() && this.Ef.contains(connectionCallbacks)) {
                connectionCallbacks.onConnected(this.Ee.zzapn());
            }
        }
        return true;
    }

    public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        boolean contains;
        zzaa.zzy(connectionCallbacks);
        synchronized (this.zzako) {
            contains = this.Ef.contains(connectionCallbacks);
        }
        return contains;
    }

    public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        boolean contains;
        zzaa.zzy(onConnectionFailedListener);
        synchronized (this.zzako) {
            contains = this.Eh.contains(onConnectionFailedListener);
        }
        return contains;
    }

    public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzaa.zzy(connectionCallbacks);
        synchronized (this.zzako) {
            if (this.Ef.contains(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                StringBuilder sb = new StringBuilder(62 + String.valueOf(valueOf).length());
                sb.append("registerConnectionCallbacks(): listener ");
                sb.append(valueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.Ef.add(connectionCallbacks);
            }
        }
        if (this.Ee.isConnected()) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, connectionCallbacks));
        }
    }

    public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzaa.zzy(onConnectionFailedListener);
        synchronized (this.zzako) {
            if (this.Eh.contains(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder sb = new StringBuilder(67 + String.valueOf(valueOf).length());
                sb.append("registerConnectionFailedListener(): listener ");
                sb.append(valueOf);
                sb.append(" is already registered");
                Log.w("GmsClientEvents", sb.toString());
            } else {
                this.Eh.add(onConnectionFailedListener);
            }
        }
    }

    public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        zzaa.zzy(connectionCallbacks);
        synchronized (this.zzako) {
            if (!this.Ef.remove(connectionCallbacks)) {
                String valueOf = String.valueOf(connectionCallbacks);
                StringBuilder sb = new StringBuilder(52 + String.valueOf(valueOf).length());
                sb.append("unregisterConnectionCallbacks(): listener ");
                sb.append(valueOf);
                sb.append(" not found");
                Log.w("GmsClientEvents", sb.toString());
            } else if (this.Ek) {
                this.Eg.add(connectionCallbacks);
            }
        }
    }

    public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        zzaa.zzy(onConnectionFailedListener);
        synchronized (this.zzako) {
            if (!this.Eh.remove(onConnectionFailedListener)) {
                String valueOf = String.valueOf(onConnectionFailedListener);
                StringBuilder sb = new StringBuilder(57 + String.valueOf(valueOf).length());
                sb.append("unregisterConnectionFailedListener(): listener ");
                sb.append(valueOf);
                sb.append(" not found");
                Log.w("GmsClientEvents", sb.toString());
            }
        }
    }

    public void zzawc() {
        this.Ei = false;
        this.Ej.incrementAndGet();
    }

    public void zzawd() {
        this.Ei = true;
    }

    public void zzgn(int i) {
        zzaa.zza(Looper.myLooper() == this.mHandler.getLooper(), "onUnintentionalDisconnection must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzako) {
            this.Ek = true;
            ArrayList arrayList = new ArrayList(this.Ef);
            int i2 = this.Ej.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.Ei || this.Ej.get() != i2) {
                    break;
                } else if (this.Ef.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnectionSuspended(i);
                }
            }
            this.Eg.clear();
            this.Ek = false;
        }
    }

    public void zzn(ConnectionResult connectionResult) {
        zzaa.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionFailure must only be called on the Handler thread");
        this.mHandler.removeMessages(1);
        synchronized (this.zzako) {
            ArrayList arrayList = new ArrayList(this.Eh);
            int i = this.Ej.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener) it.next();
                if (this.Ei && this.Ej.get() == i) {
                    if (this.Eh.contains(onConnectionFailedListener)) {
                        onConnectionFailedListener.onConnectionFailed(connectionResult);
                    }
                }
                return;
            }
        }
    }

    public void zzp(Bundle bundle) {
        boolean z = true;
        zzaa.zza(Looper.myLooper() == this.mHandler.getLooper(), "onConnectionSuccess must only be called on the Handler thread");
        synchronized (this.zzako) {
            zzaa.zzbs(!this.Ek);
            this.mHandler.removeMessages(1);
            this.Ek = true;
            if (this.Eg.size() != 0) {
                z = false;
            }
            zzaa.zzbs(z);
            ArrayList arrayList = new ArrayList(this.Ef);
            int i = this.Ej.get();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                GoogleApiClient.ConnectionCallbacks connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) it.next();
                if (!this.Ei || !this.Ee.isConnected() || this.Ej.get() != i) {
                    break;
                } else if (!this.Eg.contains(connectionCallbacks)) {
                    connectionCallbacks.onConnected(bundle);
                }
            }
            this.Eg.clear();
            this.Ek = false;
        }
    }
}
