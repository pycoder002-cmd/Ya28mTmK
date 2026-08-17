package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

/* loaded from: classes.dex */
public class zzg<T> extends zzb<T> {
    private T Cn;

    public zzg(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    @Override // com.google.android.gms.common.data.zzb, java.util.Iterator
    public T next() {
        if (!hasNext()) {
            int i = this.BS;
            StringBuilder sb = new StringBuilder(46);
            sb.append("Cannot advance the iterator beyond ");
            sb.append(i);
            throw new NoSuchElementException(sb.toString());
        }
        this.BS++;
        if (this.BS == 0) {
            this.Cn = this.BR.get(0);
            if (!(this.Cn instanceof zzc)) {
                String valueOf = String.valueOf(this.Cn.getClass());
                StringBuilder sb2 = new StringBuilder(44 + String.valueOf(valueOf).length());
                sb2.append("DataBuffer reference of type ");
                sb2.append(valueOf);
                sb2.append(" is not movable");
                throw new IllegalStateException(sb2.toString());
            }
        } else {
            ((zzc) this.Cn).zzfy(this.BS);
        }
        return this.Cn;
    }
}
