package com.startapp.networkTest.results;

import com.startapp.s;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class BaseResult implements Cloneable {
    public String GUID;
    public String ProjectId;
    public String Version = s.c;

    public BaseResult(String str, String str2) {
        this.ProjectId = "";
        this.GUID = "";
        this.ProjectId = str;
        this.GUID = str2;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
