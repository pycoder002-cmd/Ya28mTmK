package com.startapp;

import com.startapp.sdk.triggeredlinks.TriggeredLinksMetadata;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class rd implements Runnable {
    public final /* synthetic */ TriggeredLinksMetadata a;
    public final /* synthetic */ String b;
    public final /* synthetic */ String c;
    public final /* synthetic */ int d;
    public final /* synthetic */ pd e;

    public rd(pd pdVar, TriggeredLinksMetadata triggeredLinksMetadata, String str, String str2, int i) {
        this.e = pdVar;
        this.a = triggeredLinksMetadata;
        this.b = str;
        this.c = str2;
        this.d = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.e.a(this.a, "Periodic", this.b, this.c);
        } catch (Throwable th) {
            try {
                if (this.e.a(1)) {
                    p7.a(this.e.a, th);
                }
            } finally {
                this.e.a(this.b, this.d);
                this.e.a(0L);
            }
        }
    }
}
