package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.util.Log;
import android.util.SparseArray;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* loaded from: classes.dex */
public class zzqm extends zzqp {
    private final SparseArray<zza> yq;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class zza implements GoogleApiClient.OnConnectionFailedListener {
        public final int yr;
        public final GoogleApiClient ys;
        public final GoogleApiClient.OnConnectionFailedListener yt;

        public zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
            this.yr = i;
            this.ys = googleApiClient;
            this.yt = onConnectionFailedListener;
            googleApiClient.registerConnectionFailedListener(this);
        }

        public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
            printWriter.append((CharSequence) str).append("GoogleApiClient #").print(this.yr);
            printWriter.println(":");
            this.ys.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            String valueOf = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(27 + String.valueOf(valueOf).length());
            sb.append("beginFailureResolution for ");
            sb.append(valueOf);
            Log.d("AutoManageHelper", sb.toString());
            zzqm.this.zzb(connectionResult, this.yr);
        }

        public void zzarn() {
            this.ys.unregisterConnectionFailedListener(this);
            this.ys.disconnect();
        }
    }

    private zzqm(zzrp zzrpVar) {
        super(zzrpVar);
        this.yq = new SparseArray<>();
        this.Bf.zza("AutoManageHelper", this);
    }

    public static zzqm zza(zzrn zzrnVar) {
        zzrp zzc = zzc(zzrnVar);
        zzqm zzqmVar = (zzqm) zzc.zza("AutoManageHelper", zzqm.class);
        return zzqmVar != null ? zzqmVar : new zzqm(zzc);
    }

    @Override // com.google.android.gms.internal.zzro
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        for (int i = 0; i < this.yq.size(); i++) {
            this.yq.valueAt(i).dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @Override // com.google.android.gms.internal.zzqp, com.google.android.gms.internal.zzro
    public void onStart() {
        super.onStart();
        boolean z = this.mStarted;
        String valueOf = String.valueOf(this.yq);
        StringBuilder sb = new StringBuilder(14 + String.valueOf(valueOf).length());
        sb.append("onStart ");
        sb.append(z);
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb.append(valueOf);
        Log.d("AutoManageHelper", sb.toString());
        if (this.yz) {
            return;
        }
        for (int i = 0; i < this.yq.size(); i++) {
            this.yq.valueAt(i).ys.connect();
        }
    }

    @Override // com.google.android.gms.internal.zzqp, com.google.android.gms.internal.zzro
    public void onStop() {
        super.onStop();
        for (int i = 0; i < this.yq.size(); i++) {
            this.yq.valueAt(i).ys.disconnect();
        }
    }

    public void zza(int i, GoogleApiClient googleApiClient, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        com.google.android.gms.common.internal.zzaa.zzb(googleApiClient, "GoogleApiClient instance cannot be null");
        boolean z = this.yq.indexOfKey(i) < 0;
        StringBuilder sb = new StringBuilder(54);
        sb.append("Already managing a GoogleApiClient with id ");
        sb.append(i);
        com.google.android.gms.common.internal.zzaa.zza(z, sb.toString());
        boolean z2 = this.mStarted;
        boolean z3 = this.yz;
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("starting AutoManage for client ");
        sb2.append(i);
        sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb2.append(z2);
        sb2.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        sb2.append(z3);
        Log.d("AutoManageHelper", sb2.toString());
        this.yq.put(i, new zza(i, googleApiClient, onConnectionFailedListener));
        if (!this.mStarted || this.yz) {
            return;
        }
        String valueOf = String.valueOf(googleApiClient);
        StringBuilder sb3 = new StringBuilder(11 + String.valueOf(valueOf).length());
        sb3.append("connecting ");
        sb3.append(valueOf);
        Log.d("AutoManageHelper", sb3.toString());
        googleApiClient.connect();
    }

    @Override // com.google.android.gms.internal.zzqp
    protected void zza(ConnectionResult connectionResult, int i) {
        Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
        if (i < 0) {
            Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
            return;
        }
        zza zzaVar = this.yq.get(i);
        if (zzaVar != null) {
            zzfs(i);
            GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener = zzaVar.yt;
            if (onConnectionFailedListener != null) {
                onConnectionFailedListener.onConnectionFailed(connectionResult);
            }
        }
    }

    @Override // com.google.android.gms.internal.zzqp
    protected void zzarm() {
        for (int i = 0; i < this.yq.size(); i++) {
            this.yq.valueAt(i).ys.connect();
        }
    }

    public void zzfs(int i) {
        zza zzaVar = this.yq.get(i);
        this.yq.remove(i);
        if (zzaVar != null) {
            zzaVar.zzarn();
        }
    }
}
