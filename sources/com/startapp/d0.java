package com.startapp;

import com.startapp.networkTest.enums.MemoryStates;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class d0 implements Cloneable {
    public long MemoryFree;
    public MemoryStates MemoryState = MemoryStates.Unknown;
    public long MemoryTotal;
    public long MemoryUsed;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
