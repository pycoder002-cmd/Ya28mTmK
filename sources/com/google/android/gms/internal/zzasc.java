package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

/* loaded from: classes.dex */
final class zzasc {
    final byte[] btQ;
    final int tag;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzasc(int i, byte[] bArr) {
        this.tag = i;
        this.btQ = bArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzasc)) {
            return false;
        }
        zzasc zzascVar = (zzasc) obj;
        return this.tag == zzascVar.tag && Arrays.equals(this.btQ, zzascVar.btQ);
    }

    public int hashCode() {
        return (31 * (527 + this.tag)) + Arrays.hashCode(this.btQ);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zza(zzart zzartVar) throws IOException {
        zzartVar.zzahd(this.tag);
        zzartVar.zzbh(this.btQ);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzx() {
        return 0 + zzart.zzahe(this.tag) + this.btQ.length;
    }
}
