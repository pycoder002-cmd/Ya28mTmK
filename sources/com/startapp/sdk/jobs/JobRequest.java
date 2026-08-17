package com.startapp.sdk.jobs;

import com.startapp.pc;
import com.startapp.uc;
import java.util.ArrayList;
import java.util.UUID;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public abstract class JobRequest {
    public final String[] a;
    public final UUID b = UUID.randomUUID();
    public final Network c;
    public final Boolean d;
    public final boolean e;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public enum Network {
        NONE,
        ANY,
        UNMETERED
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static abstract class a<B extends a<?>> {
        public final String[] a;
        public Network b;
        public Boolean c;
        public boolean d;

        @SafeVarargs
        public a(Class<? extends pc>... clsArr) {
            ArrayList arrayList = new ArrayList();
            for (Class<? extends pc> cls : clsArr) {
                arrayList.add(cls.getName());
            }
            this.a = (String[]) arrayList.toArray(new String[0]);
        }
    }

    public JobRequest(a<?> aVar) {
        this.a = aVar.a;
        this.d = aVar.c;
        this.c = aVar.b;
        this.e = aVar.d;
    }

    @SafeVarargs
    public static int a(Class<? extends pc>... clsArr) {
        if (clsArr.length == 0) {
            return 0;
        }
        String[] strArr = new String[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            strArr[i] = clsArr[i].getName();
        }
        return a(strArr);
    }

    public static int a(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString().hashCode();
    }

    public abstract boolean a(uc ucVar);
}
