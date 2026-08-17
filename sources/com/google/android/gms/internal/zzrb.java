package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzqo;
import com.google.android.gms.internal.zzrf;
import com.google.android.gms.signin.internal.SignInResponse;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

/* loaded from: classes.dex */
public class zzrb implements zzre {
    private final Context mContext;
    private final Api.zza<? extends zzxp, zzxq> xQ;
    private final zzrf zA;
    private int zD;
    private int zF;
    private zzxp zI;
    private int zJ;
    private boolean zK;
    private boolean zL;
    private com.google.android.gms.common.internal.zzp zM;
    private boolean zN;
    private boolean zO;
    private final com.google.android.gms.common.internal.zzf zP;
    private final Lock zg;
    private final Map<Api<?>, Integer> zk;
    private final com.google.android.gms.common.zzc zm;
    private ConnectionResult zq;
    private int zE = 0;
    private final Bundle zG = new Bundle();
    private final Set<Api.zzc> zH = new HashSet();
    private ArrayList<Future<?>> zQ = new ArrayList<>();

    /* loaded from: classes.dex */
    private static class zza implements zze.zzf {
        private final Api<?> vS;
        private final int yU;
        private final WeakReference<zzrb> zS;

        public zza(zzrb zzrbVar, Api<?> api, int i) {
            this.zS = new WeakReference<>(zzrbVar);
            this.vS = api;
            this.yU = i;
        }

        @Override // com.google.android.gms.common.internal.zze.zzf
        public void zzg(@NonNull ConnectionResult connectionResult) {
            zzrb zzrbVar = this.zS.get();
            if (zzrbVar == null) {
                return;
            }
            com.google.android.gms.common.internal.zzaa.zza(Looper.myLooper() == zzrbVar.zA.yW.getLooper(), "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            zzrbVar.zg.lock();
            try {
                if (zzrbVar.zzft(0)) {
                    if (!connectionResult.isSuccess()) {
                        zzrbVar.zzb(connectionResult, this.vS, this.yU);
                    }
                    if (zzrbVar.zzasp()) {
                        zzrbVar.zzasq();
                    }
                }
            } finally {
                zzrbVar.zg.unlock();
            }
        }
    }

    /* loaded from: classes.dex */
    private class zzb extends zzf {
        private final Map<Api.zze, zza> zT;

        public zzb(Map<Api.zze, zza> map) {
            super();
            this.zT = map;
        }

        @Override // com.google.android.gms.internal.zzrb.zzf
        @WorkerThread
        public void zzaso() {
            boolean z;
            Iterator<Api.zze> it = this.zT.keySet().iterator();
            boolean z2 = true;
            boolean z3 = true;
            boolean z4 = false;
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    z2 = z4;
                    break;
                }
                Api.zze next = it.next();
                if (!next.zzaqx()) {
                    z3 = false;
                } else {
                    if (this.zT.get(next).yU == 0) {
                        z = true;
                        break;
                    }
                    z4 = true;
                }
            }
            int isGooglePlayServicesAvailable = z2 ? zzrb.this.zm.isGooglePlayServicesAvailable(zzrb.this.mContext) : 0;
            if (isGooglePlayServicesAvailable != 0 && (z || z3)) {
                final ConnectionResult connectionResult = new ConnectionResult(isGooglePlayServicesAvailable, null);
                zzrb.this.zA.zza(new zzrf.zza(zzrb.this) { // from class: com.google.android.gms.internal.zzrb.zzb.1
                    @Override // com.google.android.gms.internal.zzrf.zza
                    public void zzaso() {
                        zzrb.this.zzf(connectionResult);
                    }
                });
                return;
            }
            if (zzrb.this.zK) {
                zzrb.this.zI.connect();
            }
            for (Api.zze zzeVar : this.zT.keySet()) {
                final zza zzaVar = this.zT.get(zzeVar);
                if (!zzeVar.zzaqx() || isGooglePlayServicesAvailable == 0) {
                    zzeVar.zza(zzaVar);
                } else {
                    zzrb.this.zA.zza(new zzrf.zza(zzrb.this) { // from class: com.google.android.gms.internal.zzrb.zzb.2
                        @Override // com.google.android.gms.internal.zzrf.zza
                        public void zzaso() {
                            zzaVar.zzg(new ConnectionResult(16, null));
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class zzc extends zzf {
        private final ArrayList<Api.zze> zX;

        public zzc(ArrayList<Api.zze> arrayList) {
            super();
            this.zX = arrayList;
        }

        @Override // com.google.android.gms.internal.zzrb.zzf
        @WorkerThread
        public void zzaso() {
            zzrb.this.zA.yW.Ak = zzrb.this.zzasv();
            Iterator<Api.zze> it = this.zX.iterator();
            while (it.hasNext()) {
                it.next().zza(zzrb.this.zM, zzrb.this.zA.yW.Ak);
            }
        }
    }

    /* loaded from: classes.dex */
    private static class zzd extends com.google.android.gms.signin.internal.zzb {
        private final WeakReference<zzrb> zS;

        zzd(zzrb zzrbVar) {
            this.zS = new WeakReference<>(zzrbVar);
        }

        @Override // com.google.android.gms.signin.internal.zzb, com.google.android.gms.signin.internal.zzd
        @BinderThread
        public void zzb(final SignInResponse signInResponse) {
            final zzrb zzrbVar = this.zS.get();
            if (zzrbVar == null) {
                return;
            }
            zzrbVar.zA.zza(new zzrf.zza(zzrbVar) { // from class: com.google.android.gms.internal.zzrb.zzd.1
                @Override // com.google.android.gms.internal.zzrf.zza
                public void zzaso() {
                    zzrbVar.zza(signInResponse);
                }
            });
        }
    }

    /* loaded from: classes.dex */
    private class zze implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private zze() {
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnected(Bundle bundle) {
            zzrb.this.zI.zza(new zzd(zzrb.this));
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzrb.this.zg.lock();
            try {
                if (zzrb.this.zze(connectionResult)) {
                    zzrb.this.zzast();
                    zzrb.this.zzasq();
                } else {
                    zzrb.this.zzf(connectionResult);
                }
            } finally {
                zzrb.this.zg.unlock();
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
        }
    }

    /* loaded from: classes.dex */
    private abstract class zzf implements Runnable {
        private zzf() {
        }

        /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
        @Override // java.lang.Runnable
        @WorkerThread
        public void run() {
            zzrb.this.zg.lock();
            try {
                try {
                } catch (RuntimeException e) {
                    zzrb.this.zA.zza(e);
                }
                if (Thread.interrupted()) {
                    return;
                }
                zzaso();
            } finally {
                zzrb.this.zg.unlock();
            }
        }

        @WorkerThread
        protected abstract void zzaso();
    }

    public zzrb(zzrf zzrfVar, com.google.android.gms.common.internal.zzf zzfVar, Map<Api<?>, Integer> map, com.google.android.gms.common.zzc zzcVar, Api.zza<? extends zzxp, zzxq> zzaVar, Lock lock, Context context) {
        this.zA = zzrfVar;
        this.zP = zzfVar;
        this.zk = map;
        this.zm = zzcVar;
        this.xQ = zzaVar;
        this.zg = lock;
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zza(SignInResponse signInResponse) {
        if (zzft(0)) {
            ConnectionResult zzawn = signInResponse.zzawn();
            if (zzawn.isSuccess()) {
                ResolveAccountResponse zzcdn = signInResponse.zzcdn();
                ConnectionResult zzawn2 = zzcdn.zzawn();
                if (!zzawn2.isSuccess()) {
                    String valueOf = String.valueOf(zzawn2);
                    StringBuilder sb = new StringBuilder(48 + String.valueOf(valueOf).length());
                    sb.append("Sign-in succeeded with resolve account failure: ");
                    sb.append(valueOf);
                    Log.wtf("GoogleApiClientConnecting", sb.toString(), new Exception());
                    zzf(zzawn2);
                    return;
                }
                this.zL = true;
                this.zM = zzcdn.zzawm();
                this.zN = zzcdn.zzawo();
                this.zO = zzcdn.zzawp();
            } else {
                if (!zze(zzawn)) {
                    zzf(zzawn);
                    return;
                }
                zzast();
            }
            zzasq();
        }
    }

    private boolean zza(int i, int i2, ConnectionResult connectionResult) {
        if (i2 != 1 || zzd(connectionResult)) {
            return this.zq == null || i < this.zD;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean zzasp() {
        ConnectionResult connectionResult;
        this.zF--;
        if (this.zF > 0) {
            return false;
        }
        if (this.zF < 0) {
            Log.w("GoogleApiClientConnecting", this.zA.yW.zzatb());
            Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
            connectionResult = new ConnectionResult(8, null);
        } else {
            if (this.zq == null) {
                return true;
            }
            this.zA.AB = this.zD;
            connectionResult = this.zq;
        }
        zzf(connectionResult);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzasq() {
        if (this.zF != 0) {
            return;
        }
        if (!this.zK || this.zL) {
            zzasr();
        }
    }

    private void zzasr() {
        ArrayList arrayList = new ArrayList();
        this.zE = 1;
        this.zF = this.zA.Aj.size();
        for (Api.zzc<?> zzcVar : this.zA.Aj.keySet()) {
            if (!this.zA.Ay.containsKey(zzcVar)) {
                arrayList.add(this.zA.Aj.get(zzcVar));
            } else if (zzasp()) {
                zzass();
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.zQ.add(zzrg.zzatf().submit(new zzc(arrayList)));
    }

    private void zzass() {
        this.zA.zzatd();
        zzrg.zzatf().execute(new Runnable() { // from class: com.google.android.gms.internal.zzrb.1
            @Override // java.lang.Runnable
            public void run() {
                zzrb.this.zm.zzbn(zzrb.this.mContext);
            }
        });
        if (this.zI != null) {
            if (this.zN) {
                this.zI.zza(this.zM, this.zO);
            }
            zzbr(false);
        }
        Iterator<Api.zzc<?>> it = this.zA.Ay.keySet().iterator();
        while (it.hasNext()) {
            this.zA.Aj.get(it.next()).disconnect();
        }
        this.zA.AC.zzn(this.zG.isEmpty() ? null : this.zG);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzast() {
        this.zK = false;
        this.zA.yW.Ak = Collections.emptySet();
        for (Api.zzc<?> zzcVar : this.zH) {
            if (!this.zA.Ay.containsKey(zzcVar)) {
                this.zA.Ay.put(zzcVar, new ConnectionResult(17, null));
            }
        }
    }

    private void zzasu() {
        Iterator<Future<?>> it = this.zQ.iterator();
        while (it.hasNext()) {
            it.next().cancel(true);
        }
        this.zQ.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<Scope> zzasv() {
        if (this.zP == null) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet(this.zP.zzavp());
        Map<Api<?>, zzf.zza> zzavr = this.zP.zzavr();
        for (Api<?> api : zzavr.keySet()) {
            if (!this.zA.Ay.containsKey(api.zzaqv())) {
                hashSet.addAll(zzavr.get(api).jw);
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzb(ConnectionResult connectionResult, Api<?> api, int i) {
        if (i != 2) {
            int priority = api.zzaqs().getPriority();
            if (zza(priority, i, connectionResult)) {
                this.zq = connectionResult;
                this.zD = priority;
            }
        }
        this.zA.Ay.put(api.zzaqv(), connectionResult);
    }

    private void zzbr(boolean z) {
        if (this.zI != null) {
            if (this.zI.isConnected() && z) {
                this.zI.zzcdc();
            }
            this.zI.disconnect();
            this.zM = null;
        }
    }

    private boolean zzd(ConnectionResult connectionResult) {
        return connectionResult.hasResolution() || this.zm.zzfp(connectionResult.getErrorCode()) != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean zze(ConnectionResult connectionResult) {
        if (this.zJ != 2) {
            return this.zJ == 1 && !connectionResult.hasResolution();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zzf(ConnectionResult connectionResult) {
        zzasu();
        zzbr(!connectionResult.hasResolution());
        this.zA.zzh(connectionResult);
        this.zA.AC.zzc(connectionResult);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean zzft(int i) {
        if (this.zE == i) {
            return true;
        }
        Log.w("GoogleApiClientConnecting", this.zA.yW.zzatb());
        String valueOf = String.valueOf(this);
        StringBuilder sb = new StringBuilder(23 + String.valueOf(valueOf).length());
        sb.append("Unexpected callback in ");
        sb.append(valueOf);
        Log.w("GoogleApiClientConnecting", sb.toString());
        int i2 = this.zF;
        StringBuilder sb2 = new StringBuilder(33);
        sb2.append("mRemainingConnections=");
        sb2.append(i2);
        Log.w("GoogleApiClientConnecting", sb2.toString());
        String valueOf2 = String.valueOf(zzfu(this.zE));
        String valueOf3 = String.valueOf(zzfu(i));
        StringBuilder sb3 = new StringBuilder(70 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb3.append("GoogleApiClient connecting is in step ");
        sb3.append(valueOf2);
        sb3.append(" but received callback for step ");
        sb3.append(valueOf3);
        Log.wtf("GoogleApiClientConnecting", sb3.toString(), new Exception());
        zzf(new ConnectionResult(8, null));
        return false;
    }

    private String zzfu(int i) {
        switch (i) {
            case 0:
                return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
            case 1:
                return "STEP_GETTING_REMOTE_SERVICE";
            default:
                return "UNKNOWN";
        }
    }

    @Override // com.google.android.gms.internal.zzre
    public void begin() {
        this.zA.Ay.clear();
        this.zK = false;
        this.zq = null;
        this.zE = 0;
        this.zJ = 2;
        this.zL = false;
        this.zN = false;
        HashMap hashMap = new HashMap();
        boolean z = false;
        for (Api<?> api : this.zk.keySet()) {
            Api.zze zzeVar = this.zA.Aj.get(api.zzaqv());
            int intValue = this.zk.get(api).intValue();
            z |= api.zzaqs().getPriority() == 1;
            if (zzeVar.zzain()) {
                this.zK = true;
                if (intValue < this.zJ) {
                    this.zJ = intValue;
                }
                if (intValue != 0) {
                    this.zH.add(api.zzaqv());
                }
            }
            hashMap.put(zzeVar, new zza(this, api, intValue));
        }
        if (z) {
            this.zK = false;
        }
        if (this.zK) {
            this.zP.zzc(Integer.valueOf(this.zA.yW.getSessionId()));
            zze zzeVar2 = new zze();
            this.zI = this.xQ.zza(this.mContext, this.zA.yW.getLooper(), this.zP, this.zP.zzavv(), zzeVar2, zzeVar2);
        }
        this.zF = this.zA.Aj.size();
        this.zQ.add(zzrg.zzatf().submit(new zzb(hashMap)));
    }

    @Override // com.google.android.gms.internal.zzre
    public void connect() {
    }

    @Override // com.google.android.gms.internal.zzre
    public boolean disconnect() {
        zzasu();
        zzbr(true);
        this.zA.zzh(null);
        return true;
    }

    @Override // com.google.android.gms.internal.zzre
    public void onConnected(Bundle bundle) {
        if (zzft(1)) {
            if (bundle != null) {
                this.zG.putAll(bundle);
            }
            if (zzasp()) {
                zzass();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzre
    public void onConnectionSuspended(int i) {
        zzf(new ConnectionResult(8, null));
    }

    @Override // com.google.android.gms.internal.zzre
    public <A extends Api.zzb, R extends Result, T extends zzqo.zza<R, A>> T zza(T t) {
        this.zA.yW.Ad.add(t);
        return t;
    }

    @Override // com.google.android.gms.internal.zzre
    public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
        if (zzft(1)) {
            zzb(connectionResult, api, i);
            if (zzasp()) {
                zzass();
            }
        }
    }

    @Override // com.google.android.gms.internal.zzre
    public <A extends Api.zzb, T extends zzqo.zza<? extends Result, A>> T zzb(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
