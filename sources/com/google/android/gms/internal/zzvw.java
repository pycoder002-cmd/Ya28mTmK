package com.google.android.gms.internal;

import com.github.mikephil.charting.utils.Utils;
import java.io.IOException;

/* loaded from: classes.dex */
public interface zzvw {

    /* loaded from: classes.dex */
    public static final class zza extends zzaru<zza> {
        public C0040zza[] ahB;

        /* renamed from: com.google.android.gms.internal.zzvw$zza$zza, reason: collision with other inner class name */
        /* loaded from: classes.dex */
        public static final class C0040zza extends zzaru<C0040zza> {
            private static volatile C0040zza[] ahC;
            public String ahD;
            public String ahE;
            public int viewId;

            public C0040zza() {
                zzboa();
            }

            public static C0040zza[] zzbnz() {
                if (ahC == null) {
                    synchronized (zzary.btO) {
                        if (ahC == null) {
                            ahC = new C0040zza[0];
                        }
                    }
                }
                return ahC;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof C0040zza)) {
                    return false;
                }
                C0040zza c0040zza = (C0040zza) obj;
                if (this.ahD == null) {
                    if (c0040zza.ahD != null) {
                        return false;
                    }
                } else if (!this.ahD.equals(c0040zza.ahD)) {
                    return false;
                }
                if (this.ahE == null) {
                    if (c0040zza.ahE != null) {
                        return false;
                    }
                } else if (!this.ahE.equals(c0040zza.ahE)) {
                    return false;
                }
                if (this.viewId != c0040zza.viewId) {
                    return false;
                }
                return (this.btG == null || this.btG.isEmpty()) ? c0040zza.btG == null || c0040zza.btG.isEmpty() : this.btG.equals(c0040zza.btG);
            }

            public int hashCode() {
                int i = 0;
                int hashCode = 31 * (((((((527 + getClass().getName().hashCode()) * 31) + (this.ahD == null ? 0 : this.ahD.hashCode())) * 31) + (this.ahE == null ? 0 : this.ahE.hashCode())) * 31) + this.viewId);
                if (this.btG != null && !this.btG.isEmpty()) {
                    i = this.btG.hashCode();
                }
                return hashCode + i;
            }

            @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
            public void zza(zzart zzartVar) throws IOException {
                if (this.ahD != null && !this.ahD.equals("")) {
                    zzartVar.zzq(1, this.ahD);
                }
                if (this.ahE != null && !this.ahE.equals("")) {
                    zzartVar.zzq(2, this.ahE);
                }
                if (this.viewId != 0) {
                    zzartVar.zzaf(3, this.viewId);
                }
                super.zza(zzartVar);
            }

            @Override // com.google.android.gms.internal.zzasa
            /* renamed from: zzac, reason: merged with bridge method [inline-methods] */
            public C0040zza zzb(zzars zzarsVar) throws IOException {
                while (true) {
                    int bU = zzarsVar.bU();
                    if (bU == 0) {
                        return this;
                    }
                    if (bU == 10) {
                        this.ahD = zzarsVar.readString();
                    } else if (bU == 18) {
                        this.ahE = zzarsVar.readString();
                    } else if (bU == 24) {
                        this.viewId = zzarsVar.bY();
                    } else if (!super.zza(zzarsVar, bU)) {
                        return this;
                    }
                }
            }

            public C0040zza zzboa() {
                this.ahD = "";
                this.ahE = "";
                this.viewId = 0;
                this.btG = null;
                this.btP = -1;
                return this;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
            public int zzx() {
                int zzx = super.zzx();
                if (this.ahD != null && !this.ahD.equals("")) {
                    zzx += zzart.zzr(1, this.ahD);
                }
                if (this.ahE != null && !this.ahE.equals("")) {
                    zzx += zzart.zzr(2, this.ahE);
                }
                return this.viewId != 0 ? zzx + zzart.zzah(3, this.viewId) : zzx;
            }
        }

        public zza() {
            zzbny();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            if (zzary.equals(this.ahB, zzaVar.ahB)) {
                return (this.btG == null || this.btG.isEmpty()) ? zzaVar.btG == null || zzaVar.btG.isEmpty() : this.btG.equals(zzaVar.btG);
            }
            return false;
        }

        public int hashCode() {
            return (31 * (((527 + getClass().getName().hashCode()) * 31) + zzary.hashCode(this.ahB))) + ((this.btG == null || this.btG.isEmpty()) ? 0 : this.btG.hashCode());
        }

        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public void zza(zzart zzartVar) throws IOException {
            if (this.ahB != null && this.ahB.length > 0) {
                for (int i = 0; i < this.ahB.length; i++) {
                    C0040zza c0040zza = this.ahB[i];
                    if (c0040zza != null) {
                        zzartVar.zza(1, c0040zza);
                    }
                }
            }
            super.zza(zzartVar);
        }

        @Override // com.google.android.gms.internal.zzasa
        /* renamed from: zzab, reason: merged with bridge method [inline-methods] */
        public zza zzb(zzars zzarsVar) throws IOException {
            while (true) {
                int bU = zzarsVar.bU();
                if (bU == 0) {
                    return this;
                }
                if (bU == 10) {
                    int zzc = zzasd.zzc(zzarsVar, 10);
                    int length = this.ahB == null ? 0 : this.ahB.length;
                    C0040zza[] c0040zzaArr = new C0040zza[zzc + length];
                    if (length != 0) {
                        System.arraycopy(this.ahB, 0, c0040zzaArr, 0, length);
                    }
                    while (length < c0040zzaArr.length - 1) {
                        c0040zzaArr[length] = new C0040zza();
                        zzarsVar.zza(c0040zzaArr[length]);
                        zzarsVar.bU();
                        length++;
                    }
                    c0040zzaArr[length] = new C0040zza();
                    zzarsVar.zza(c0040zzaArr[length]);
                    this.ahB = c0040zzaArr;
                } else if (!super.zza(zzarsVar, bU)) {
                    return this;
                }
            }
        }

        public zza zzbny() {
            this.ahB = C0040zza.zzbnz();
            this.btG = null;
            this.btP = -1;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public int zzx() {
            int zzx = super.zzx();
            if (this.ahB != null && this.ahB.length > 0) {
                for (int i = 0; i < this.ahB.length; i++) {
                    C0040zza c0040zza = this.ahB[i];
                    if (c0040zza != null) {
                        zzx += zzart.zzc(1, c0040zza);
                    }
                }
            }
            return zzx;
        }
    }

    /* loaded from: classes.dex */
    public static final class zzb extends zzaru<zzb> {
        private static volatile zzb[] ahF;
        public zzd ahG;
        public String name;

        public zzb() {
            zzboc();
        }

        public static zzb[] zzbob() {
            if (ahF == null) {
                synchronized (zzary.btO) {
                    if (ahF == null) {
                        ahF = new zzb[0];
                    }
                }
            }
            return ahF;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb zzbVar = (zzb) obj;
            if (this.name == null) {
                if (zzbVar.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzbVar.name)) {
                return false;
            }
            if (this.ahG == null) {
                if (zzbVar.ahG != null) {
                    return false;
                }
            } else if (!this.ahG.equals(zzbVar.ahG)) {
                return false;
            }
            return (this.btG == null || this.btG.isEmpty()) ? zzbVar.btG == null || zzbVar.btG.isEmpty() : this.btG.equals(zzbVar.btG);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((527 + getClass().getName().hashCode()) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.ahG == null ? 0 : this.ahG.hashCode()));
            if (this.btG != null && !this.btG.isEmpty()) {
                i = this.btG.hashCode();
            }
            return hashCode + i;
        }

        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public void zza(zzart zzartVar) throws IOException {
            if (this.name != null && !this.name.equals("")) {
                zzartVar.zzq(1, this.name);
            }
            if (this.ahG != null) {
                zzartVar.zza(2, this.ahG);
            }
            super.zza(zzartVar);
        }

        @Override // com.google.android.gms.internal.zzasa
        /* renamed from: zzad, reason: merged with bridge method [inline-methods] */
        public zzb zzb(zzars zzarsVar) throws IOException {
            while (true) {
                int bU = zzarsVar.bU();
                if (bU == 0) {
                    return this;
                }
                if (bU == 10) {
                    this.name = zzarsVar.readString();
                } else if (bU == 18) {
                    if (this.ahG == null) {
                        this.ahG = new zzd();
                    }
                    zzarsVar.zza(this.ahG);
                } else if (!super.zza(zzarsVar, bU)) {
                    return this;
                }
            }
        }

        public zzb zzboc() {
            this.name = "";
            this.ahG = null;
            this.btG = null;
            this.btP = -1;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public int zzx() {
            int zzx = super.zzx();
            if (this.name != null && !this.name.equals("")) {
                zzx += zzart.zzr(1, this.name);
            }
            return this.ahG != null ? zzx + zzart.zzc(2, this.ahG) : zzx;
        }
    }

    /* loaded from: classes.dex */
    public static final class zzc extends zzaru<zzc> {
        public zzb[] ahH;
        public String type;

        public zzc() {
            zzbod();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc zzcVar = (zzc) obj;
            if (this.type == null) {
                if (zzcVar.type != null) {
                    return false;
                }
            } else if (!this.type.equals(zzcVar.type)) {
                return false;
            }
            if (zzary.equals(this.ahH, zzcVar.ahH)) {
                return (this.btG == null || this.btG.isEmpty()) ? zzcVar.btG == null || zzcVar.btG.isEmpty() : this.btG.equals(zzcVar.btG);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = 31 * (((((527 + getClass().getName().hashCode()) * 31) + (this.type == null ? 0 : this.type.hashCode())) * 31) + zzary.hashCode(this.ahH));
            if (this.btG != null && !this.btG.isEmpty()) {
                i = this.btG.hashCode();
            }
            return hashCode + i;
        }

        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public void zza(zzart zzartVar) throws IOException {
            if (this.type != null && !this.type.equals("")) {
                zzartVar.zzq(1, this.type);
            }
            if (this.ahH != null && this.ahH.length > 0) {
                for (int i = 0; i < this.ahH.length; i++) {
                    zzb zzbVar = this.ahH[i];
                    if (zzbVar != null) {
                        zzartVar.zza(2, zzbVar);
                    }
                }
            }
            super.zza(zzartVar);
        }

        @Override // com.google.android.gms.internal.zzasa
        /* renamed from: zzae, reason: merged with bridge method [inline-methods] */
        public zzc zzb(zzars zzarsVar) throws IOException {
            while (true) {
                int bU = zzarsVar.bU();
                if (bU == 0) {
                    return this;
                }
                if (bU == 10) {
                    this.type = zzarsVar.readString();
                } else if (bU == 18) {
                    int zzc = zzasd.zzc(zzarsVar, 18);
                    int length = this.ahH == null ? 0 : this.ahH.length;
                    zzb[] zzbVarArr = new zzb[zzc + length];
                    if (length != 0) {
                        System.arraycopy(this.ahH, 0, zzbVarArr, 0, length);
                    }
                    while (length < zzbVarArr.length - 1) {
                        zzbVarArr[length] = new zzb();
                        zzarsVar.zza(zzbVarArr[length]);
                        zzarsVar.bU();
                        length++;
                    }
                    zzbVarArr[length] = new zzb();
                    zzarsVar.zza(zzbVarArr[length]);
                    this.ahH = zzbVarArr;
                } else if (!super.zza(zzarsVar, bU)) {
                    return this;
                }
            }
        }

        public zzc zzbod() {
            this.type = "";
            this.ahH = zzb.zzbob();
            this.btG = null;
            this.btP = -1;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public int zzx() {
            int zzx = super.zzx();
            if (this.type != null && !this.type.equals("")) {
                zzx += zzart.zzr(1, this.type);
            }
            if (this.ahH != null && this.ahH.length > 0) {
                for (int i = 0; i < this.ahH.length; i++) {
                    zzb zzbVar = this.ahH[i];
                    if (zzbVar != null) {
                        zzx += zzart.zzc(2, zzbVar);
                    }
                }
            }
            return zzx;
        }
    }

    /* loaded from: classes.dex */
    public static final class zzd extends zzaru<zzd> {
        public String Fe;
        public boolean ahI;
        public long ahJ;
        public double ahK;
        public zzc ahL;

        public zzd() {
            zzboe();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd zzdVar = (zzd) obj;
            if (this.ahI != zzdVar.ahI) {
                return false;
            }
            if (this.Fe == null) {
                if (zzdVar.Fe != null) {
                    return false;
                }
            } else if (!this.Fe.equals(zzdVar.Fe)) {
                return false;
            }
            if (this.ahJ != zzdVar.ahJ || Double.doubleToLongBits(this.ahK) != Double.doubleToLongBits(zzdVar.ahK)) {
                return false;
            }
            if (this.ahL == null) {
                if (zzdVar.ahL != null) {
                    return false;
                }
            } else if (!this.ahL.equals(zzdVar.ahL)) {
                return false;
            }
            return (this.btG == null || this.btG.isEmpty()) ? zzdVar.btG == null || zzdVar.btG.isEmpty() : this.btG.equals(zzdVar.btG);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((527 + getClass().getName().hashCode()) * 31) + (this.ahI ? 1231 : 1237)) * 31) + (this.Fe == null ? 0 : this.Fe.hashCode())) * 31) + ((int) (this.ahJ ^ (this.ahJ >>> 32)));
            long doubleToLongBits = Double.doubleToLongBits(this.ahK);
            int hashCode2 = 31 * ((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + (this.ahL == null ? 0 : this.ahL.hashCode()));
            if (this.btG != null && !this.btG.isEmpty()) {
                i = this.btG.hashCode();
            }
            return hashCode2 + i;
        }

        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public void zza(zzart zzartVar) throws IOException {
            if (this.ahI) {
                zzartVar.zzg(1, this.ahI);
            }
            if (this.Fe != null && !this.Fe.equals("")) {
                zzartVar.zzq(2, this.Fe);
            }
            if (this.ahJ != 0) {
                zzartVar.zzb(3, this.ahJ);
            }
            if (Double.doubleToLongBits(this.ahK) != Double.doubleToLongBits(Utils.DOUBLE_EPSILON)) {
                zzartVar.zza(4, this.ahK);
            }
            if (this.ahL != null) {
                zzartVar.zza(5, this.ahL);
            }
            super.zza(zzartVar);
        }

        @Override // com.google.android.gms.internal.zzasa
        /* renamed from: zzaf, reason: merged with bridge method [inline-methods] */
        public zzd zzb(zzars zzarsVar) throws IOException {
            while (true) {
                int bU = zzarsVar.bU();
                if (bU == 0) {
                    return this;
                }
                if (bU == 8) {
                    this.ahI = zzarsVar.ca();
                } else if (bU == 18) {
                    this.Fe = zzarsVar.readString();
                } else if (bU == 24) {
                    this.ahJ = zzarsVar.bX();
                } else if (bU == 33) {
                    this.ahK = zzarsVar.readDouble();
                } else if (bU == 42) {
                    if (this.ahL == null) {
                        this.ahL = new zzc();
                    }
                    zzarsVar.zza(this.ahL);
                } else if (!super.zza(zzarsVar, bU)) {
                    return this;
                }
            }
        }

        public zzd zzboe() {
            this.ahI = false;
            this.Fe = "";
            this.ahJ = 0L;
            this.ahK = Utils.DOUBLE_EPSILON;
            this.ahL = null;
            this.btG = null;
            this.btP = -1;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.android.gms.internal.zzaru, com.google.android.gms.internal.zzasa
        public int zzx() {
            int zzx = super.zzx();
            if (this.ahI) {
                zzx += zzart.zzh(1, this.ahI);
            }
            if (this.Fe != null && !this.Fe.equals("")) {
                zzx += zzart.zzr(2, this.Fe);
            }
            if (this.ahJ != 0) {
                zzx += zzart.zzf(3, this.ahJ);
            }
            if (Double.doubleToLongBits(this.ahK) != Double.doubleToLongBits(Utils.DOUBLE_EPSILON)) {
                zzx += zzart.zzb(4, this.ahK);
            }
            return this.ahL != null ? zzx + zzart.zzc(5, this.ahL) : zzx;
        }
    }
}
