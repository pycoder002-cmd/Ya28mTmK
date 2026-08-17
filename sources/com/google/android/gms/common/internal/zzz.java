package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class zzz {

    /* loaded from: classes.dex */
    public static final class zza {
        private final List<String> EG;
        private final Object zzcxk;

        private zza(Object obj) {
            this.zzcxk = zzaa.zzy(obj);
            this.EG = new ArrayList();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append(this.zzcxk.getClass().getSimpleName());
            sb.append('{');
            int size = this.EG.size();
            for (int i = 0; i < size; i++) {
                sb.append(this.EG.get(i));
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append('}');
            return sb.toString();
        }

        public zza zzg(String str, Object obj) {
            List<String> list = this.EG;
            String str2 = (String) zzaa.zzy(str);
            String valueOf = String.valueOf(String.valueOf(obj));
            StringBuilder sb = new StringBuilder(1 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
            sb.append(str2);
            sb.append("=");
            sb.append(valueOf);
            list.add(sb.toString());
            return this;
        }
    }

    public static boolean equal(@Nullable Object obj, @Nullable Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static zza zzx(Object obj) {
        return new zza(obj);
    }
}
