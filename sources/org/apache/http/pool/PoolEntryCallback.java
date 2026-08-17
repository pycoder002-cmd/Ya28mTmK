package org.apache.http.pool;

/* loaded from: classes2.dex */
public interface PoolEntryCallback<T, C> {
    void process(PoolEntry<T, C> poolEntry);
}
