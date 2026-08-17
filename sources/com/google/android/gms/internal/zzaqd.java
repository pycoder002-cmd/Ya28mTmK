package com.google.android.gms.internal;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public final class zzaqd extends zzapk<Date> {
    public static final zzapl bpG = new zzapl() { // from class: com.google.android.gms.internal.zzaqd.1
        @Override // com.google.android.gms.internal.zzapl
        public <T> zzapk<T> zza(zzaos zzaosVar, zzaqo<T> zzaqoVar) {
            if (zzaqoVar.bB() == Date.class) {
                return new zzaqd();
            }
            return null;
        }
    };
    private final DateFormat bnQ = DateFormat.getDateTimeInstance(2, 2, Locale.US);
    private final DateFormat bnR = DateFormat.getDateTimeInstance(2, 2);
    private final DateFormat bnS = bp();

    private static DateFormat bp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return simpleDateFormat;
    }

    private synchronized Date zzur(String str) {
        try {
            try {
                try {
                } catch (ParseException unused) {
                    return this.bnS.parse(str);
                }
            } catch (ParseException e) {
                throw new zzaph(str, e);
            }
        } catch (ParseException unused2) {
            return this.bnQ.parse(str);
        }
        return this.bnR.parse(str);
    }

    @Override // com.google.android.gms.internal.zzapk
    public synchronized void zza(zzaqr zzaqrVar, Date date) throws IOException {
        if (date == null) {
            zzaqrVar.bA();
        } else {
            zzaqrVar.zzut(this.bnQ.format(date));
        }
    }

    @Override // com.google.android.gms.internal.zzapk
    /* renamed from: zzk, reason: merged with bridge method [inline-methods] */
    public Date zzb(zzaqp zzaqpVar) throws IOException {
        if (zzaqpVar.bq() != zzaqq.NULL) {
            return zzur(zzaqpVar.nextString());
        }
        zzaqpVar.nextNull();
        return null;
    }
}
