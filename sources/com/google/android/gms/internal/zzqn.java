package com.google.android.gms.internal;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class zzqn {
    private int yw;
    private final TaskCompletionSource<Void> yv = new TaskCompletionSource<>();
    private boolean yx = false;
    private final ArrayMap<zzql<?>, ConnectionResult> xo = new ArrayMap<>();

    public zzqn(Iterable<com.google.android.gms.common.api.zzc<? extends Api.ApiOptions>> iterable) {
        Iterator<com.google.android.gms.common.api.zzc<? extends Api.ApiOptions>> it = iterable.iterator();
        while (it.hasNext()) {
            this.xo.put(it.next().getApiKey(), null);
        }
        this.yw = this.xo.keySet().size();
    }

    public Task<Void> getTask() {
        return this.yv.getTask();
    }

    public void zza(zzql<?> zzqlVar, ConnectionResult connectionResult) {
        this.xo.put(zzqlVar, connectionResult);
        this.yw--;
        if (!connectionResult.isSuccess()) {
            this.yx = true;
        }
        if (this.yw == 0) {
            if (!this.yx) {
                this.yv.setResult(null);
            } else {
                this.yv.setException(new com.google.android.gms.common.api.zzb(this.xo));
            }
        }
    }

    public Set<zzql<?>> zzaro() {
        return this.xo.keySet();
    }

    public void zzarp() {
        this.yv.setResult(null);
    }
}
