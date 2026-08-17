package com.google.firebase.appindexing.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzz;
import com.google.firebase.appindexing.Indexable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

/* loaded from: classes.dex */
public final class Thing extends AbstractSafeParcelable implements ReflectedParcelable, Indexable {
    public static final Parcelable.Creator<Thing> CREATOR = new zzj();
    private final Metadata aWz;
    private final Bundle he;
    public final int mVersionCode;
    private final String zzboa;
    private final String zzcpo;

    /* loaded from: classes.dex */
    public static class Metadata extends AbstractSafeParcelable {
        public static final Parcelable.Creator<Metadata> CREATOR = new zzh();
        private final boolean aWx;
        private String aWy;
        public final int mVersionCode;
        private int zzavt;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Metadata(int i, boolean z, int i2, String str) {
            this.mVersionCode = i;
            this.aWx = z;
            this.zzavt = i2;
            this.aWy = str;
        }

        public Metadata(boolean z, int i, String str) {
            this.mVersionCode = 1;
            this.aWx = z;
            this.zzavt = i;
            this.aWy = str;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Metadata)) {
                return false;
            }
            Metadata metadata = (Metadata) obj;
            return zzz.equal(Boolean.valueOf(this.aWx), Boolean.valueOf(metadata.aWx)) && zzz.equal(Integer.valueOf(this.zzavt), Integer.valueOf(metadata.zzavt)) && zzz.equal(this.aWy, metadata.aWy);
        }

        public int getScore() {
            return this.zzavt;
        }

        public int hashCode() {
            return zzz.hashCode(Boolean.valueOf(this.aWx), Integer.valueOf(this.zzavt), this.aWy);
        }

        public String toString() {
            String str = "";
            if (!zzcoq().isEmpty()) {
                String valueOf = String.valueOf(zzcoq());
                str = valueOf.length() != 0 ? ", accountEmail: ".concat(valueOf) : new String(", accountEmail: ");
            }
            boolean zzcop = zzcop();
            int score = getScore();
            StringBuilder sb = new StringBuilder(39 + String.valueOf(str).length());
            sb.append("worksOffline: ");
            sb.append(zzcop);
            sb.append(", score: ");
            sb.append(score);
            sb.append(str);
            return sb.toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            zzh.zza(this, parcel, i);
        }

        public boolean zzcop() {
            return this.aWx;
        }

        public String zzcoq() {
            return this.aWy;
        }
    }

    public Thing(int i, Bundle bundle, Metadata metadata, String str, String str2) {
        this.mVersionCode = i;
        this.he = bundle;
        this.aWz = metadata;
        this.zzboa = str;
        this.zzcpo = str2;
        this.he.setClassLoader(getClass().getClassLoader());
    }

    public Thing(@NonNull Bundle bundle, @NonNull Metadata metadata, String str, @NonNull String str2) {
        this.mVersionCode = 6;
        this.he = bundle;
        this.aWz = metadata;
        this.zzboa = str;
        this.zzcpo = str2;
    }

    public String getId() {
        return this.zzboa;
    }

    public String getType() {
        return this.zzcpo;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public String toString() {
        String sb;
        String obj;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(zzcoo());
        sb2.append(" { ");
        sb2.append("{ id: ");
        if (getId() == null) {
            sb = "<null> } ";
        } else {
            String valueOf = String.valueOf(getId());
            StringBuilder sb3 = new StringBuilder(5 + String.valueOf(valueOf).length());
            sb3.append("'");
            sb3.append(valueOf);
            sb3.append("' } ");
            sb = sb3.toString();
        }
        sb2.append(sb);
        sb2.append("Properties { ");
        Set<String> keySet = this.he.keySet();
        String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
        Arrays.sort(strArr);
        for (String str : strArr) {
            sb2.append("{ key: '");
            sb2.append(str);
            sb2.append("' value: ");
            Object obj2 = this.he.get(str);
            if (obj2 == null) {
                obj = "<null>";
            } else if (obj2.getClass().isArray()) {
                sb2.append("[ ");
                for (int i = 0; i < Array.getLength(obj2); i++) {
                    sb2.append("'");
                    sb2.append(Array.get(obj2, i));
                    sb2.append("' ");
                }
                obj = "]";
            } else {
                obj = obj2.toString();
            }
            sb2.append(obj);
            sb2.append(" } ");
        }
        sb2.append("} ");
        sb2.append("Metadata { ");
        sb2.append(this.aWz.toString());
        sb2.append(" } ");
        sb2.append("}");
        return sb2.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        zzj.zza(this, parcel, i);
    }

    public Bundle zzahu() {
        return this.he;
    }

    public Metadata zzcon() {
        return this.aWz;
    }

    public String zzcoo() {
        return this.zzcpo.equals("Thing") ? "Indexable" : this.zzcpo;
    }
}
