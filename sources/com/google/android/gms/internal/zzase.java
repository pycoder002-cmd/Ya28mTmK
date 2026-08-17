package com.google.android.gms.internal;

import java.io.IOException;

/* loaded from: classes.dex */
public interface zzase {

    /* loaded from: classes.dex */
    public static final class zza extends zzaru<zza> {
        public boolean btZ;
        public String bua;
        public int score;

        public zza() {
            cA();
        }

        public zza cA() {
            this.btZ = false;
            this.score = 0;
            this.bua = "";
            this.btG = null;
            this.btP = -1;
            return this;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            if (this.btZ != zzaVar.btZ || this.score != zzaVar.score) {
                return false;
            }
            if (this.bua == null) {
                if (zzaVar.bua != null) {
                    return false;
                }
            } else if (!this.bua.equals(zzaVar.bua)) {
                return false;
            }
            return (this.btG == null || this.btG.isEmpty()) ? zzaVar.btG == null || zzaVar.btG.isEmpty() : this.btG.equals(zzaVar.btG);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((((527 + getClass().getName().hashCode()) * 31) + (this.btZ ? 1231 : 1237)) * 31) + this.score) * 31) + (this.bua == null ? 0 : this.bua.hashCode()));
            if (this.btG != null && !this.btG.isEmpty()) {
                i = this.btG.hashCode();
            }
            return hashCode + i;
        }

        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public void zza(zzart zzartVar) throws IOException {
            if (this.btZ) {
                zzartVar.zzg(1, this.btZ);
            }
            if (this.score != 0) {
                zzartVar.zzaf(2, this.score);
            }
            if (this.bua != null && !this.bua.equals("")) {
                zzartVar.zzq(3, this.bua);
            }
            super.zza(zzartVar);
        }

        @Override // com.google.android.gms.internal.zzasa
        /* renamed from: zzcn, reason: merged with bridge method [inline-methods] */
        public zza zzb(zzars zzarsVar) throws IOException {
            while (true) {
                int bU = zzarsVar.bU();
                if (bU == 0) {
                    return this;
                }
                if (bU == 8) {
                    this.btZ = zzarsVar.ca();
                } else if (bU == 16) {
                    this.score = zzarsVar.bY();
                } else if (bU == 26) {
                    this.bua = zzarsVar.readString();
                } else if (!super.zza(zzarsVar, bU)) {
                    return this;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public int zzx() {
            int zzx = super.zzx();
            if (this.btZ) {
                zzx += zzart.zzh(1, this.btZ);
            }
            if (this.score != 0) {
                zzx += zzart.zzah(2, this.score);
            }
            return (this.bua == null || this.bua.equals("")) ? zzx : zzx + zzart.zzr(3, this.bua);
        }
    }
}
