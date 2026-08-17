package com.startapp;

import com.startapp.sdk.components.ComponentLocator;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class ib implements k9<Integer> {
    public final /* synthetic */ Executor a;

    public ib(ComponentLocator.l lVar, Executor executor) {
        this.a = executor;
    }

    @Override // com.startapp.k9
    public Integer call() {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) this.a;
        return Integer.valueOf(threadPoolExecutor.getMaximumPoolSize() - threadPoolExecutor.getActiveCount());
    }
}
