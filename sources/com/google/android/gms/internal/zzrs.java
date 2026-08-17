package com.google.android.gms.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public class zzrs {
    private final Set<zzrr<?>> rK = Collections.newSetFromMap(new WeakHashMap());

    public static <L> zzrr<L> zzb(@NonNull L l, @NonNull Looper looper, @NonNull String str) {
        com.google.android.gms.common.internal.zzaa.zzb(l, "Listener must not be null");
        com.google.android.gms.common.internal.zzaa.zzb(looper, "Looper must not be null");
        com.google.android.gms.common.internal.zzaa.zzb(str, "Listener type must not be null");
        return new zzrr<>(looper, l, str);
    }

    public void release() {
        Iterator<zzrr<?>> it = this.rK.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.rK.clear();
    }

    public <L> zzrr<L> zza(@NonNull L l, @NonNull Looper looper, @NonNull String str) {
        zzrr<L> zzb = zzb(l, looper, str);
        this.rK.add(zzb);
        return zzb;
    }

    public <L> zzrr<L> zzb(@NonNull L l, Looper looper) {
        return zza(l, looper, "NO_TYPE");
    }
}
