package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
class zzarx implements Cloneable {
    private zzarv<?, ?> btM;
    private List<zzasc> btN = new ArrayList();
    private Object value;

    private byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzx()];
        zza(zzart.zzbe(bArr));
        return bArr;
    }

    /* renamed from: cq, reason: merged with bridge method [inline-methods] */
    public final zzarx clone() {
        Object clone;
        zzarx zzarxVar = new zzarx();
        try {
            zzarxVar.btM = this.btM;
            if (this.btN == null) {
                zzarxVar.btN = null;
            } else {
                zzarxVar.btN.addAll(this.btN);
            }
            if (this.value == null) {
                return zzarxVar;
            }
            if (this.value instanceof zzasa) {
                clone = (zzasa) ((zzasa) this.value).clone();
            } else {
                if (!(this.value instanceof byte[])) {
                    int i = 0;
                    if (this.value instanceof byte[][]) {
                        byte[][] bArr = (byte[][]) this.value;
                        byte[][] bArr2 = new byte[bArr.length];
                        zzarxVar.value = bArr2;
                        while (i < bArr.length) {
                            bArr2[i] = (byte[]) bArr[i].clone();
                            i++;
                        }
                    } else if (this.value instanceof boolean[]) {
                        clone = ((boolean[]) this.value).clone();
                    } else if (this.value instanceof int[]) {
                        clone = ((int[]) this.value).clone();
                    } else if (this.value instanceof long[]) {
                        clone = ((long[]) this.value).clone();
                    } else if (this.value instanceof float[]) {
                        clone = ((float[]) this.value).clone();
                    } else if (this.value instanceof double[]) {
                        clone = ((double[]) this.value).clone();
                    } else if (this.value instanceof zzasa[]) {
                        zzasa[] zzasaVarArr = (zzasa[]) this.value;
                        zzasa[] zzasaVarArr2 = new zzasa[zzasaVarArr.length];
                        zzarxVar.value = zzasaVarArr2;
                        while (i < zzasaVarArr.length) {
                            zzasaVarArr2[i] = (zzasa) zzasaVarArr[i].clone();
                            i++;
                        }
                    }
                    return zzarxVar;
                }
                clone = ((byte[]) this.value).clone();
            }
            zzarxVar.value = clone;
            return zzarxVar;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzarx)) {
            return false;
        }
        zzarx zzarxVar = (zzarx) obj;
        if (this.value != null && zzarxVar.value != null) {
            if (this.btM != zzarxVar.btM) {
                return false;
            }
            return !this.btM.bkp.isArray() ? this.value.equals(zzarxVar.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) zzarxVar.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) zzarxVar.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) zzarxVar.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) zzarxVar.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) zzarxVar.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) zzarxVar.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) zzarxVar.value);
        }
        if (this.btN != null && zzarxVar.btN != null) {
            return this.btN.equals(zzarxVar.btN);
        }
        try {
            return Arrays.equals(toByteArray(), zzarxVar.toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public int hashCode() {
        try {
            return 527 + Arrays.hashCode(toByteArray());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zza(zzart zzartVar) throws IOException {
        if (this.value != null) {
            this.btM.zza(this.value, zzartVar);
            return;
        }
        Iterator<zzasc> it = this.btN.iterator();
        while (it.hasNext()) {
            it.next().zza(zzartVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zza(zzasc zzascVar) {
        this.btN.add(zzascVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> T zzb(zzarv<?, T> zzarvVar) {
        if (this.value == null) {
            this.btM = zzarvVar;
            this.value = zzarvVar.zzay(this.btN);
            this.btN = null;
        } else if (!this.btM.equals(zzarvVar)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return (T) this.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzx() {
        if (this.value != null) {
            return this.btM.zzct(this.value);
        }
        Iterator<zzasc> it = this.btN.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().zzx();
        }
        return i;
    }
}
