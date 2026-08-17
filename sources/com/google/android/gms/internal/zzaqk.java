package com.google.android.gms.internal;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* loaded from: classes.dex */
public final class zzaqk extends zzapk<Date> {
    public static final zzapl bpG = new zzapl() { // from class: com.google.android.gms.internal.zzaqk.1
        @Override // com.google.android.gms.internal.zzapl
        public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar) {
            if (zzaqoVar.bB() == Date.class) {
                return new zzaqk();
            }
            return null;
        }
    };
    private final DateFormat bqg = new SimpleDateFormat("MMM d, yyyy");

    @Override // com.google.android.gms.internal.zzapk
    public synchronized void zza(zzaqr zzaqrVar, Date date) throws IOException {
        zzaqrVar.zzut(date == null ? null : this.bqg.format((java.util.Date) date));
    }

    @Override // com.google.android.gms.internal.zzapk
    /* renamed from: zzm, reason: merged with bridge method [inline-methods] */
    public synchronized Date zzb(zzaqp zzaqpVar) throws IOException {
        if (zzaqpVar.bq() == zzaqq.NULL) {
            zzaqpVar.nextNull();
            return null;
        }
        try {
            return new Date(this.bqg.parse(zzaqpVar.nextString()).getTime());
        } catch (ParseException e) {
            throw new zzaph(e);
        }
    }
}
