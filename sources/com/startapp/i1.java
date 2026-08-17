package com.startapp;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.Callable;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class i1<V> implements Callable<V> {
    private final Callable<V> a;

    public i1(Callable<V> callable) {
        this.a = callable;
    }

    public static <T> Collection<? extends Callable<T>> a(Collection<? extends Callable<T>> collection) {
        LinkedList linkedList = new LinkedList();
        Iterator<? extends Callable<T>> it = collection.iterator();
        while (it.hasNext()) {
            linkedList.add(new i1(it.next()));
        }
        return linkedList;
    }

    @Override // java.util.concurrent.Callable
    public V call() {
        try {
            return this.a.call();
        } catch (Throwable th) {
            h1.c(th);
            return null;
        }
    }
}
