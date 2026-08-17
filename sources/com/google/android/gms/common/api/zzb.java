package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzql;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class zzb extends Exception {
    private final ArrayMap<zzql<?>, ConnectionResult> xo;

    public zzb(ArrayMap<zzql<?>, ConnectionResult> arrayMap) {
        this.xo = arrayMap;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        for (zzql<?> zzqlVar : this.xo.keySet()) {
            ConnectionResult connectionResult = this.xo.get(zzqlVar);
            if (connectionResult.isSuccess()) {
                z = false;
            }
            String valueOf = String.valueOf(zzqlVar.zzarl());
            String valueOf2 = String.valueOf(connectionResult);
            StringBuilder sb = new StringBuilder(2 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
            sb.append(valueOf);
            sb.append(": ");
            sb.append(valueOf2);
            arrayList.add(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z ? "None of the queried APIs are available. " : "Some of the queried APIs are unavailable. ");
        zzx.zzia("; ").zza(sb2, arrayList);
        return sb2.toString();
    }

    public ArrayMap<zzql<?>, ConnectionResult> zzara() {
        return this.xo;
    }
}
