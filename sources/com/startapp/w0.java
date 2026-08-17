package com.startapp;

import java.net.InetAddress;
import java.net.UnknownHostException;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class w0 {
    private static final String a = "w0";
    private Object b;
    private InetAddress c;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a implements Runnable {
        public final /* synthetic */ String a;

        public a(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                InetAddress byName = InetAddress.getByName(this.a);
                synchronized (w0.this.b) {
                    w0.this.c = byName;
                }
            } catch (Throwable th) {
                h1.a(th);
            }
        }
    }

    public String a(String str, int i) throws UnknownHostException {
        String hostAddress;
        this.b = new Object();
        Thread thread = new Thread(new a(str));
        thread.start();
        try {
            thread.join(i);
        } catch (Throwable th) {
            h1.a(th);
        }
        synchronized (this.b) {
            InetAddress inetAddress = this.c;
            if (inetAddress == null) {
                throw new UnknownHostException();
            }
            hostAddress = inetAddress.getHostAddress();
        }
        return hostAddress;
    }
}
