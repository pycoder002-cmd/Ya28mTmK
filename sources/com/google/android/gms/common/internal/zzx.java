package com.google.android.gms.common.internal;

import java.util.Iterator;

/* loaded from: classes.dex */
public class zzx {
    private final String separator;

    private zzx(String str) {
        this.separator = str;
    }

    public static zzx zzia(String str) {
        return new zzx(str);
    }

    public final StringBuilder zza(StringBuilder sb, Iterable<?> iterable) {
        Iterator<?> it = iterable.iterator();
        if (it.hasNext()) {
            while (true) {
                sb.append(zzw(it.next()));
                if (!it.hasNext()) {
                    break;
                }
                sb.append(this.separator);
            }
        }
        return sb;
    }

    public final String zzb(Iterable<?> iterable) {
        return zza(new StringBuilder(), iterable).toString();
    }

    CharSequence zzw(Object obj) {
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }
}
