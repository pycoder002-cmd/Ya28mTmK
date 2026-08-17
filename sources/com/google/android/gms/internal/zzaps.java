package com.google.android.gms.internal;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/* loaded from: classes.dex */
public final class zzaps {
    private final Map<Type, zzaou<?>> bop;

    public zzaps(Map<Type, zzaou<?>> map) {
        this.bop = map;
    }

    private <T> zzapx<T> zzc(final Type type, Class<? super T> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return SortedSet.class.isAssignableFrom(cls) ? new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.7
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    return (T) new TreeSet();
                }
            } : EnumSet.class.isAssignableFrom(cls) ? new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.8
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    if (!(type instanceof ParameterizedType)) {
                        String valueOf = String.valueOf(type.toString());
                        throw new zzaoz(valueOf.length() != 0 ? "Invalid EnumSet type: ".concat(valueOf) : new String("Invalid EnumSet type: "));
                    }
                    Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
                    if (type2 instanceof Class) {
                        return (T) EnumSet.noneOf((Class) type2);
                    }
                    String valueOf2 = String.valueOf(type.toString());
                    throw new zzaoz(valueOf2.length() != 0 ? "Invalid EnumSet type: ".concat(valueOf2) : new String("Invalid EnumSet type: "));
                }
            } : Set.class.isAssignableFrom(cls) ? new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.9
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    return (T) new LinkedHashSet();
                }
            } : Queue.class.isAssignableFrom(cls) ? new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.10
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    return (T) new LinkedList();
                }
            } : new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.11
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    return (T) new ArrayList();
                }
            };
        }
        if (Map.class.isAssignableFrom(cls)) {
            return SortedMap.class.isAssignableFrom(cls) ? new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.12
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    return (T) new TreeMap();
                }
            } : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(zzaqo.zzl(((ParameterizedType) type).getActualTypeArguments()[0]).bB())) ? new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.3
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    return (T) new zzapw();
                }
            } : new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.2
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    return (T) new LinkedHashMap();
                }
            };
        }
        return null;
    }

    private <T> zzapx<T> zzd(final Type type, final Class<? super T> cls) {
        return new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.4
            private final zzaqa boS = zzaqa.bo();

            @Override // com.google.android.gms.internal.zzapx
            public T bj() {
                try {
                    return (T) this.boS.zzf(cls);
                } catch (Exception e) {
                    String valueOf = String.valueOf(type);
                    StringBuilder sb = new StringBuilder(116 + String.valueOf(valueOf).length());
                    sb.append("Unable to invoke no-args constructor for ");
                    sb.append(valueOf);
                    sb.append(". ");
                    sb.append("Register an InstanceCreator with Gson for this type may fix this problem.");
                    throw new RuntimeException(sb.toString(), e);
                }
            }
        };
    }

    private <T> zzapx<T> zzl(Class<? super T> cls) {
        try {
            final Constructor<? super T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                declaredConstructor.setAccessible(true);
            }
            return new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.6
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    try {
                        return (T) declaredConstructor.newInstance(null);
                    } catch (IllegalAccessException e) {
                        throw new AssertionError(e);
                    } catch (InstantiationException e2) {
                        String valueOf = String.valueOf(declaredConstructor);
                        StringBuilder sb = new StringBuilder(30 + String.valueOf(valueOf).length());
                        sb.append("Failed to invoke ");
                        sb.append(valueOf);
                        sb.append(" with no args");
                        throw new RuntimeException(sb.toString(), e2);
                    } catch (InvocationTargetException e3) {
                        String valueOf2 = String.valueOf(declaredConstructor);
                        StringBuilder sb2 = new StringBuilder(30 + String.valueOf(valueOf2).length());
                        sb2.append("Failed to invoke ");
                        sb2.append(valueOf2);
                        sb2.append(" with no args");
                        throw new RuntimeException(sb2.toString(), e3.getTargetException());
                    }
                }
            };
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    public String toString() {
        return this.bop.toString();
    }

    public <T> zzapx<T> zzb(zzaqo<T> zzaqoVar) {
        final Type bC = zzaqoVar.bC();
        Class<? super T> bB = zzaqoVar.bB();
        final zzaou<?> zzaouVar = this.bop.get(bC);
        if (zzaouVar != null) {
            return new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.1
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    return (T) zzaouVar.zza(bC);
                }
            };
        }
        final zzaou<?> zzaouVar2 = this.bop.get(bB);
        if (zzaouVar2 != null) {
            return new zzapx<T>() { // from class: com.google.android.gms.internal.zzaps.5
                @Override // com.google.android.gms.internal.zzapx
                public T bj() {
                    return (T) zzaouVar2.zza(bC);
                }
            };
        }
        zzapx<T> zzl = zzl(bB);
        if (zzl != null) {
            return zzl;
        }
        zzapx<T> zzc = zzc(bC, bB);
        return zzc != null ? zzc : zzd(bC, bB);
    }
}
