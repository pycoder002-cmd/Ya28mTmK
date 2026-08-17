package com.google.firebase.appindexing.builders;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.zzaa;
import com.google.firebase.appindexing.FirebaseAppIndexingInvalidArgumentException;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.Thing;
import com.google.firebase.appindexing.internal.zzg;
import java.util.Arrays;

/* loaded from: classes.dex */
public abstract class IndexableBuilder<T extends IndexableBuilder<?>> {
    Thing.Metadata aWz;
    final Bundle he;
    String zzae;
    final String zzcpo;

    /* JADX INFO: Access modifiers changed from: protected */
    public IndexableBuilder(@NonNull String str) {
        zzaa.zzy(str);
        zzaa.zzib(str);
        this.he = new Bundle();
        this.zzcpo = str;
    }

    private T zza(@NonNull String str, @NonNull Thing... thingArr) {
        zzaa.zzy(str);
        zzaa.zzy(thingArr);
        if (thingArr.length > 0) {
            int i = 0;
            for (int i2 = 0; i2 < thingArr.length; i2++) {
                thingArr[i] = thingArr[i2];
                if (thingArr[i2] == null) {
                    StringBuilder sb = new StringBuilder(58);
                    sb.append("Thing at ");
                    sb.append(i2);
                    sb.append(" is null and is ignored by put method.");
                    zzg.zzrr(sb.toString());
                } else {
                    i++;
                }
            }
            if (i > 0) {
                this.he.putParcelableArray(str, (Parcelable[]) zzd((Thing[]) Arrays.copyOfRange(thingArr, 0, i)));
            }
        } else {
            zzg.zzrr("Thing array is empty and is ignored by put method.");
        }
        return zzcoa();
    }

    private static boolean[] zza(boolean[] zArr) {
        if (zArr.length < 100) {
            return zArr;
        }
        zzg.zzrr("Input Array of elements is too big, cutting off.");
        return Arrays.copyOf(zArr, 100);
    }

    private static long[] zzb(long[] jArr) {
        if (jArr.length < 100) {
            return jArr;
        }
        zzg.zzrr("Input Array of elements is too big, cutting off.");
        return Arrays.copyOf(jArr, 100);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private T zzcoa() {
        return this;
    }

    private static <S> S[] zzd(S[] sArr) {
        if (sArr.length < 100) {
            return sArr;
        }
        zzg.zzrr("Input Array of elements is too big, cutting off.");
        return (S[]) Arrays.copyOf(sArr, 100);
    }

    @KeepName
    public final Indexable build() {
        return new Thing(new Bundle(this.he), this.aWz == null ? Indexable.Metadata.aWv : this.aWz, this.zzae, this.zzcpo);
    }

    @KeepName
    public T put(@NonNull String str, @NonNull long... jArr) {
        zzaa.zzy(str);
        zzaa.zzy(jArr);
        if (jArr.length > 0) {
            this.he.putLongArray(str, zzb(jArr));
        } else {
            zzg.zzrr("Long array is empty and is ignored by put method.");
        }
        return zzcoa();
    }

    @KeepName
    public T put(@NonNull String str, @NonNull Indexable... indexableArr) throws FirebaseAppIndexingInvalidArgumentException {
        zzaa.zzy(str);
        zzaa.zzy(indexableArr);
        Thing[] thingArr = new Thing[indexableArr.length];
        for (int i = 0; i < indexableArr.length; i++) {
            if (indexableArr[i] != null && !(indexableArr[i] instanceof Thing)) {
                throw new FirebaseAppIndexingInvalidArgumentException("Invalid Indexable encountered. Use Indexable.Builder or convenience methods under Indexables to create the Indexable.");
            }
            thingArr[i] = (Thing) indexableArr[i];
        }
        zza(str, thingArr);
        return zzcoa();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <S extends IndexableBuilder> T put(@NonNull String str, @NonNull S... sArr) {
        zzaa.zzy(str);
        zzaa.zzy(sArr);
        if (sArr.length > 0) {
            Thing[] thingArr = new Thing[sArr.length];
            for (int i = 0; i < sArr.length; i++) {
                if (sArr[i] == null) {
                    StringBuilder sb = new StringBuilder(60);
                    sb.append("Builder at ");
                    sb.append(i);
                    sb.append(" is null and is ignored by put method.");
                    zzg.zzrr(sb.toString());
                } else {
                    thingArr[i] = (Thing) sArr[i].build();
                }
            }
            if (thingArr.length > 0) {
                zza(str, thingArr);
            }
        } else {
            zzg.zzrr("Builder array is empty and is ignored by put method.");
        }
        return zzcoa();
    }

    @KeepName
    public T put(@NonNull String str, @NonNull String... strArr) {
        zzaa.zzy(str);
        zzaa.zzy(strArr);
        if (strArr.length > 0) {
            int i = 0;
            for (int i2 = 0; i2 < Math.min(strArr.length, 100); i2++) {
                strArr[i] = strArr[i2];
                if (strArr[i2] == null) {
                    StringBuilder sb = new StringBuilder(59);
                    sb.append("String at ");
                    sb.append(i2);
                    sb.append(" is null and is ignored by put method.");
                    zzg.zzrr(sb.toString());
                } else {
                    if (strArr[i].length() > 20000) {
                        StringBuilder sb2 = new StringBuilder(53);
                        sb2.append("String at ");
                        sb2.append(i2);
                        sb2.append(" is too long, truncating string.");
                        zzg.zzrr(sb2.toString());
                        strArr[i] = strArr[i].substring(0, 20000);
                    }
                    i++;
                }
            }
            if (i > 0) {
                this.he.putStringArray(str, (String[]) zzd((String[]) Arrays.copyOfRange(strArr, 0, i)));
            }
        } else {
            zzg.zzrr("String array is empty and is ignored by put method.");
        }
        return zzcoa();
    }

    @KeepName
    public T put(@NonNull String str, @NonNull boolean... zArr) {
        zzaa.zzy(str);
        zzaa.zzy(zArr);
        if (zArr.length > 0) {
            this.he.putBooleanArray(str, zza(zArr));
        } else {
            zzg.zzrr("Boolean array is empty and is ignored by put method.");
        }
        return zzcoa();
    }

    @KeepName
    public final T setDescription(@NonNull String str) {
        zzaa.zzy(str);
        return put("description", str);
    }

    @KeepName
    public final T setImage(@NonNull String str) {
        zzaa.zzy(str);
        return put("image", str);
    }

    @KeepName
    public T setMetadata(@NonNull Indexable.Metadata.Builder builder) {
        zzaa.zza(this.aWz == null, "setMetadata may only be called once");
        zzaa.zzy(builder);
        this.aWz = builder.zzcnz();
        return zzcoa();
    }

    @KeepName
    public final T setName(@NonNull String str) {
        zzaa.zzy(str);
        return put("name", str);
    }

    @KeepName
    public final T setSameAs(@NonNull String str) {
        zzaa.zzy(str);
        return put("sameAs", str);
    }

    @KeepName
    public final T setUrl(@NonNull String str) {
        zzaa.zzy(str);
        this.zzae = str;
        return zzcoa();
    }
}
