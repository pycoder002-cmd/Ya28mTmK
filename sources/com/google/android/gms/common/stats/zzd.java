package com.google.android.gms.common.stats;

import android.support.v4.util.SimpleArrayMap;
import io.sentry.DefaultSentryClientFactory;

/* loaded from: classes.dex */
public class zzd {
    private final long FX;
    private final int FY;
    private final SimpleArrayMap<String, Long> FZ;

    public zzd() {
        this.FX = DefaultSentryClientFactory.BUFFER_FLUSHTIME_DEFAULT;
        this.FY = 10;
        this.FZ = new SimpleArrayMap<>(10);
    }

    public zzd(int i, long j) {
        this.FX = j;
        this.FY = i;
        this.FZ = new SimpleArrayMap<>();
    }
}
