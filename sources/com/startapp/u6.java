package com.startapp;

import com.startapp.b7;
import java.util.Iterator;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class u6 implements b7.b {
    public final /* synthetic */ v6 a;

    public u6(v6 v6Var) {
        this.a = v6Var;
    }

    public void a(b7 b7Var) {
        synchronized (this.a.b) {
            Iterator<b7> it = this.a.b.values().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (it.next() == b7Var) {
                    it.remove();
                    break;
                }
            }
        }
    }
}
