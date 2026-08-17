package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.internal.zzqj;
import com.google.android.gms.internal.zzqo;
import com.google.android.gms.internal.zzrr;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class zzrh implements Handler.Callback {
    private static zzrh AJ;
    private long AI;
    private int AK;
    private final AtomicInteger AL;
    private final AtomicInteger AM;
    private zzqw AN;
    private final Set<zzql<?>> AO;
    private final Set<zzql<?>> AP;
    private long Af;
    private long Ag;
    private final Context mContext;
    private final Handler mHandler;
    private final GoogleApiAvailability xP;
    private final Map<zzql<?>, zza<?>> zj;
    public static final Status AG = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status AH = new Status(4, "The user must be signed in to make this API call.");
    private static final Object zzaox = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public class zza<O extends Api.ApiOptions> implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, zzqs {
        private final Api.zzb AR;
        private final zzqv AS;
        private final int AV;
        private boolean Ae;
        private final Api.zze xB;
        private final zzql<O> xx;
        private final Queue<zzqj> AQ = new LinkedList();
        private final Set<zzqn> AT = new HashSet();
        private final Map<zzrr.zzb<?>, zzrx> AU = new HashMap();
        private ConnectionResult AW = null;

        @WorkerThread
        public zza(com.google.android.gms.common.api.zzc<O> zzcVar) {
            if (zzcVar.isConnectionlessGoogleApiClient()) {
                this.xB = zzcVar.getClient();
                zzcVar.getClientCallbacks().zza(this);
            } else {
                this.xB = zzcVar.buildApiClient(zzrh.this.mHandler.getLooper(), this, this);
            }
            this.AR = this.xB instanceof com.google.android.gms.common.internal.zzag ? ((com.google.android.gms.common.internal.zzag) this.xB).zzawt() : this.xB;
            this.xx = zzcVar.getApiKey();
            this.AS = new zzqv();
            this.AV = zzcVar.getInstanceId();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @WorkerThread
        public void connect() {
            if (this.xB.isConnected() || this.xB.isConnecting()) {
                return;
            }
            if (this.xB.zzaqx() && zzrh.this.AK != 0) {
                zzrh.this.AK = zzrh.this.xP.isGooglePlayServicesAvailable(zzrh.this.mContext);
                if (zzrh.this.AK != 0) {
                    onConnectionFailed(new ConnectionResult(zzrh.this.AK, null));
                    return;
                }
            }
            this.xB.zzain();
            this.xB.zza(new zzb(this.xB, this.xx));
        }

        /* JADX INFO: Access modifiers changed from: private */
        @WorkerThread
        public void resume() {
            if (this.Ae) {
                connect();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @WorkerThread
        public void zzac(Status status) {
            Iterator<zzqj> it = this.AQ.iterator();
            while (it.hasNext()) {
                it.next().zzy(status);
            }
            this.AQ.clear();
        }

        /* JADX INFO: Access modifiers changed from: private */
        @WorkerThread
        public void zzasx() {
            if (this.Ae) {
                zzatq();
                zzac(zzrh.this.xP.isGooglePlayServicesAvailable(zzrh.this.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.xB.disconnect();
            }
        }

        @WorkerThread
        private void zzatq() {
            if (this.Ae) {
                zzrh.this.mHandler.removeMessages(9, this.xx);
                zzrh.this.mHandler.removeMessages(7, this.xx);
                this.Ae = false;
            }
        }

        private void zzatr() {
            zzrh.this.mHandler.removeMessages(10, this.xx);
            zzrh.this.mHandler.sendMessageDelayed(zzrh.this.mHandler.obtainMessage(10, this.xx), zzrh.this.AI);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void zzats() {
            if (this.xB.isConnected() && this.AU.size() == 0) {
                if (this.AS.zzasi()) {
                    zzatr();
                } else {
                    this.xB.disconnect();
                }
            }
        }

        @WorkerThread
        private void zzb(zzqj zzqjVar) {
            zzqjVar.zza(this.AS, zzain());
            try {
                zzqjVar.zza(this);
            } catch (DeadObjectException unused) {
                onConnectionSuspended(1);
                this.xB.disconnect();
            }
        }

        @WorkerThread
        private void zzi(ConnectionResult connectionResult) {
            Iterator<zzqn> it = this.AT.iterator();
            while (it.hasNext()) {
                it.next().zza(this.xx, connectionResult);
            }
            this.AT.clear();
        }

        public Api.zze getClient() {
            return this.xB;
        }

        public int getInstanceId() {
            return this.AV;
        }

        boolean isConnected() {
            return this.xB.isConnected();
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        @WorkerThread
        public void onConnected(@Nullable Bundle bundle) {
            zzato();
            zzi(ConnectionResult.wO);
            zzatq();
            Iterator<zzrx> it = this.AU.values().iterator();
            while (it.hasNext()) {
                try {
                    it.next().yi.zza(this.AR, new TaskCompletionSource<>());
                } catch (DeadObjectException unused) {
                    onConnectionSuspended(1);
                    this.xB.disconnect();
                }
            }
            zzatm();
            zzatr();
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
        @WorkerThread
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            zzato();
            zzrh.this.AK = -1;
            zzi(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zzac(zzrh.AH);
                return;
            }
            if (this.AQ.isEmpty()) {
                this.AW = connectionResult;
                return;
            }
            synchronized (zzrh.zzaox) {
                if (zzrh.this.AN != null && zzrh.this.AO.contains(this.xx)) {
                    zzrh.this.AN.zzb(connectionResult, this.AV);
                    return;
                }
                if (zzrh.this.zzc(connectionResult, this.AV)) {
                    return;
                }
                if (connectionResult.getErrorCode() == 18) {
                    this.Ae = true;
                }
                if (this.Ae) {
                    zzrh.this.mHandler.sendMessageDelayed(Message.obtain(zzrh.this.mHandler, 7, this.xx), zzrh.this.Ag);
                    return;
                }
                String valueOf = String.valueOf(this.xx.zzarl());
                StringBuilder sb = new StringBuilder(38 + String.valueOf(valueOf).length());
                sb.append("API: ");
                sb.append(valueOf);
                sb.append(" is not available on this device.");
                zzac(new Status(17, sb.toString()));
            }
        }

        @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
        @WorkerThread
        public void onConnectionSuspended(int i) {
            zzato();
            this.Ae = true;
            this.AS.zzask();
            zzrh.this.mHandler.sendMessageDelayed(Message.obtain(zzrh.this.mHandler, 7, this.xx), zzrh.this.Ag);
            zzrh.this.mHandler.sendMessageDelayed(Message.obtain(zzrh.this.mHandler, 9, this.xx), zzrh.this.Af);
            zzrh.this.AK = -1;
        }

        @WorkerThread
        public void signOut() {
            zzac(zzrh.AG);
            this.AS.zzasj();
            Iterator<zzrr.zzb<?>> it = this.AU.keySet().iterator();
            while (it.hasNext()) {
                zza(new zzqj.zze(it.next(), new TaskCompletionSource()));
            }
            this.xB.disconnect();
        }

        @Override // com.google.android.gms.internal.zzqs
        public void zza(ConnectionResult connectionResult, Api<?> api, int i) {
            onConnectionFailed(connectionResult);
        }

        @WorkerThread
        public void zza(zzqj zzqjVar) {
            if (this.xB.isConnected()) {
                zzb(zzqjVar);
                zzatr();
                return;
            }
            this.AQ.add(zzqjVar);
            if (this.AW == null || !this.AW.hasResolution()) {
                connect();
            } else {
                onConnectionFailed(this.AW);
            }
        }

        public boolean zzain() {
            return this.xB.zzain();
        }

        @WorkerThread
        public void zzatm() {
            while (this.xB.isConnected() && !this.AQ.isEmpty()) {
                zzb(this.AQ.remove());
            }
        }

        public Map<zzrr.zzb<?>, zzrx> zzatn() {
            return this.AU;
        }

        @WorkerThread
        public void zzato() {
            this.AW = null;
        }

        ConnectionResult zzatp() {
            return this.AW;
        }

        @WorkerThread
        public void zzb(zzqn zzqnVar) {
            this.AT.add(zzqnVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class zzb implements zze.zzf {
        private final Api.zze xB;
        private final zzql<?> xx;

        public zzb(Api.zze zzeVar, zzql<?> zzqlVar) {
            this.xB = zzeVar;
            this.xx = zzqlVar;
        }

        @Override // com.google.android.gms.common.internal.zze.zzf
        @WorkerThread
        public void zzg(@NonNull ConnectionResult connectionResult) {
            if (!connectionResult.isSuccess()) {
                ((zza) zzrh.this.zj.get(this.xx)).onConnectionFailed(connectionResult);
            } else {
                if (this.xB.zzain()) {
                    return;
                }
                this.xB.zza(null, Collections.emptySet());
            }
        }
    }

    private zzrh(Context context) {
        this(context, GoogleApiAvailability.getInstance());
    }

    private zzrh(Context context, GoogleApiAvailability googleApiAvailability) {
        this.Ag = 5000L;
        this.Af = 120000L;
        this.AI = 10000L;
        this.AK = -1;
        this.AL = new AtomicInteger(1);
        this.AM = new AtomicInteger(0);
        this.zj = new ConcurrentHashMap(5, 0.75f, 1);
        this.AN = null;
        this.AO = new com.google.android.gms.common.util.zza();
        this.AP = new com.google.android.gms.common.util.zza();
        this.mContext = context;
        HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
        handlerThread.start();
        this.mHandler = new Handler(handlerThread.getLooper(), this);
        this.xP = googleApiAvailability;
    }

    @WorkerThread
    private void zza(int i, ConnectionResult connectionResult) {
        zza<?> zzaVar;
        Iterator<zza<?>> it = this.zj.values().iterator();
        while (true) {
            if (!it.hasNext()) {
                zzaVar = null;
                break;
            } else {
                zzaVar = it.next();
                if (zzaVar.getInstanceId() == i) {
                    break;
                }
            }
        }
        if (zzaVar == null) {
            StringBuilder sb = new StringBuilder(76);
            sb.append("Could not find API instance ");
            sb.append(i);
            sb.append(" while trying to fail enqueued calls.");
            Log.wtf("GoogleApiManager", sb.toString(), new Exception());
            return;
        }
        String valueOf = String.valueOf(this.xP.getErrorString(connectionResult.getErrorCode()));
        String valueOf2 = String.valueOf(connectionResult.getErrorMessage());
        StringBuilder sb2 = new StringBuilder(69 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb2.append("Error resolution was canceled by the user, original error message: ");
        sb2.append(valueOf);
        sb2.append(": ");
        sb2.append(valueOf2);
        zzaVar.zzac(new Status(17, sb2.toString()));
    }

    @WorkerThread
    private void zza(zzrv zzrvVar) {
        zza<?> zzaVar = this.zj.get(zzrvVar.Bs.getApiKey());
        if (zzaVar == null) {
            zzb(zzrvVar.Bs);
            zzaVar = this.zj.get(zzrvVar.Bs.getApiKey());
        }
        if (!zzaVar.zzain() || this.AM.get() == zzrvVar.Br) {
            zzaVar.zza(zzrvVar.Bq);
        } else {
            zzrvVar.Bq.zzy(AG);
            zzaVar.signOut();
        }
    }

    public static zzrh zzatg() {
        zzrh zzrhVar;
        synchronized (zzaox) {
            com.google.android.gms.common.internal.zzaa.zzb(AJ, "Must guarantee manager is non-null before using getInstance");
            zzrhVar = AJ;
        }
        return zzrhVar;
    }

    @WorkerThread
    private void zzati() {
        for (zza<?> zzaVar : this.zj.values()) {
            zzaVar.zzato();
            zzaVar.connect();
        }
    }

    @WorkerThread
    private void zzb(com.google.android.gms.common.api.zzc<?> zzcVar) {
        zzql<?> apiKey = zzcVar.getApiKey();
        if (!this.zj.containsKey(apiKey)) {
            this.zj.put(apiKey, new zza<>(zzcVar));
        }
        zza<?> zzaVar = this.zj.get(apiKey);
        if (zzaVar.zzain()) {
            this.AP.add(apiKey);
        }
        zzaVar.connect();
    }

    public static zzrh zzbx(Context context) {
        zzrh zzrhVar;
        synchronized (zzaox) {
            if (AJ == null) {
                AJ = new zzrh(context.getApplicationContext());
            }
            zzrhVar = AJ;
        }
        return zzrhVar;
    }

    @Override // android.os.Handler.Callback
    @WorkerThread
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                zza((zzqn) message.obj);
                return true;
            case 2:
                zzati();
                return true;
            case 3:
            case 6:
            case 11:
                zza((zzrv) message.obj);
                return true;
            case 4:
                zza(message.arg1, (ConnectionResult) message.obj);
                return true;
            case 5:
                zzb((com.google.android.gms.common.api.zzc<?>) message.obj);
                return true;
            case 7:
                if (!this.zj.containsKey(message.obj)) {
                    return true;
                }
                this.zj.get(message.obj).resume();
                return true;
            case 8:
                zzatj();
                return true;
            case 9:
                if (!this.zj.containsKey(message.obj)) {
                    return true;
                }
                this.zj.get(message.obj).zzasx();
                return true;
            case 10:
                if (!this.zj.containsKey(message.obj)) {
                    return true;
                }
                this.zj.get(message.obj).zzats();
                return true;
            default:
                int i = message.what;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Unknown message id: ");
                sb.append(i);
                Log.w("GoogleApiManager", sb.toString());
                return false;
        }
    }

    public <O extends Api.ApiOptions> Task<Void> zza(@NonNull com.google.android.gms.common.api.zzc<O> zzcVar, @NonNull zzrr.zzb<?> zzbVar) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(11, new zzrv(new zzqj.zze(zzbVar, taskCompletionSource), this.AM.get(), zzcVar)));
        return taskCompletionSource.getTask();
    }

    public <O extends Api.ApiOptions> Task<Void> zza(@NonNull com.google.android.gms.common.api.zzc<O> zzcVar, @NonNull zzrw<Api.zzb> zzrwVar, @NonNull zzsh<Api.zzb> zzshVar) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6, new zzrv(new zzqj.zzc(new zzrx(zzrwVar, zzshVar), taskCompletionSource), this.AM.get(), zzcVar)));
        return taskCompletionSource.getTask();
    }

    public Task<Void> zza(Iterable<com.google.android.gms.common.api.zzc<?>> iterable) {
        zzqn zzqnVar = new zzqn(iterable);
        Iterator<com.google.android.gms.common.api.zzc<?>> it = iterable.iterator();
        while (it.hasNext()) {
            zza<?> zzaVar = this.zj.get(it.next().getApiKey());
            if (zzaVar == null || !zzaVar.isConnected()) {
                this.mHandler.sendMessage(this.mHandler.obtainMessage(1, zzqnVar));
                break;
            }
        }
        zzqnVar.zzarp();
        return zzqnVar.getTask();
    }

    public void zza(ConnectionResult connectionResult, int i) {
        if (zzc(connectionResult, i)) {
            return;
        }
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, i, 0, connectionResult));
    }

    public void zza(com.google.android.gms.common.api.zzc<?> zzcVar) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(5, zzcVar));
    }

    public <O extends Api.ApiOptions> void zza(com.google.android.gms.common.api.zzc<O> zzcVar, int i, zzqo.zza<? extends Result, Api.zzb> zzaVar) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new zzrv(new zzqj.zzb(i, zzaVar), this.AM.get(), zzcVar)));
    }

    public <O extends Api.ApiOptions, TResult> void zza(com.google.android.gms.common.api.zzc<O> zzcVar, int i, zzse<Api.zzb, TResult> zzseVar, TaskCompletionSource<TResult> taskCompletionSource, zzsb zzsbVar) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3, new zzrv(new zzqj.zzd(i, zzseVar, taskCompletionSource, zzsbVar), this.AM.get(), zzcVar)));
    }

    @WorkerThread
    public void zza(zzqn zzqnVar) {
        ConnectionResult connectionResult;
        for (zzql<?> zzqlVar : zzqnVar.zzaro()) {
            zza<?> zzaVar = this.zj.get(zzqlVar);
            if (zzaVar == null) {
                zzqnVar.zza(zzqlVar, new ConnectionResult(13));
                return;
            }
            if (zzaVar.isConnected()) {
                connectionResult = ConnectionResult.wO;
            } else if (zzaVar.zzatp() != null) {
                connectionResult = zzaVar.zzatp();
            } else {
                zzaVar.zzb(zzqnVar);
            }
            zzqnVar.zza(zzqlVar, connectionResult);
        }
    }

    public void zza(@NonNull zzqw zzqwVar) {
        synchronized (zzaox) {
            if (this.AN != zzqwVar) {
                this.AN = zzqwVar;
                this.AO.clear();
                this.AO.addAll(zzqwVar.zzasl());
            }
        }
    }

    public void zzarm() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(2));
    }

    public int zzath() {
        return this.AL.getAndIncrement();
    }

    @WorkerThread
    public void zzatj() {
        Iterator<zzql<?>> it = this.AP.iterator();
        while (it.hasNext()) {
            this.zj.remove(it.next()).signOut();
        }
        this.AP.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zzb(@NonNull zzqw zzqwVar) {
        synchronized (zzaox) {
            if (this.AN == zzqwVar) {
                this.AN = null;
                this.AO.clear();
            }
        }
    }

    boolean zzc(ConnectionResult connectionResult, int i) {
        if (!connectionResult.hasResolution() && !this.xP.isUserResolvableError(connectionResult.getErrorCode())) {
            return false;
        }
        this.xP.zza(this.mContext, connectionResult, i);
        return true;
    }
}
