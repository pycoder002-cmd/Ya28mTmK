package com.google.android.gms.common.data;

import java.util.ArrayList;

/* loaded from: classes.dex */
public abstract class zzf<T> extends AbstractDataBuffer<T> {
    private boolean Cl;
    private ArrayList<Integer> Cm;

    protected zzf(DataHolder dataHolder) {
        super(dataHolder);
        this.Cl = false;
    }

    private void zzaur() {
        synchronized (this) {
            if (!this.Cl) {
                int count = this.zy.getCount();
                this.Cm = new ArrayList<>();
                if (count > 0) {
                    this.Cm.add(0);
                    String zzauq = zzauq();
                    String zzd = this.zy.zzd(zzauq, 0, this.zy.zzga(0));
                    for (int i = 1; i < count; i++) {
                        int zzga = this.zy.zzga(i);
                        String zzd2 = this.zy.zzd(zzauq, i, zzga);
                        if (zzd2 == null) {
                            StringBuilder sb = new StringBuilder(78 + String.valueOf(zzauq).length());
                            sb.append("Missing value for markerColumn: ");
                            sb.append(zzauq);
                            sb.append(", at row: ");
                            sb.append(i);
                            sb.append(", for window: ");
                            sb.append(zzga);
                            throw new NullPointerException(sb.toString());
                        }
                        if (!zzd2.equals(zzd)) {
                            this.Cm.add(Integer.valueOf(i));
                            zzd = zzd2;
                        }
                    }
                }
                this.Cl = true;
            }
        }
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final T get(int i) {
        zzaur();
        return zzn(zzge(i), zzgf(i));
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public int getCount() {
        zzaur();
        return this.Cm.size();
    }

    protected abstract String zzauq();

    protected String zzaus() {
        return null;
    }

    int zzge(int i) {
        if (i >= 0 && i < this.Cm.size()) {
            return this.Cm.get(i).intValue();
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("Position ");
        sb.append(i);
        sb.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(sb.toString());
    }

    protected int zzgf(int i) {
        if (i < 0 || i == this.Cm.size()) {
            return 0;
        }
        int count = (i == this.Cm.size() - 1 ? this.zy.getCount() : this.Cm.get(i + 1).intValue()) - this.Cm.get(i).intValue();
        if (count == 1) {
            int zzge = zzge(i);
            int zzga = this.zy.zzga(zzge);
            String zzaus = zzaus();
            if (zzaus != null && this.zy.zzd(zzaus, zzge, zzga) == null) {
                return 0;
            }
        }
        return count;
    }

    protected abstract T zzn(int i, int i2);
}
