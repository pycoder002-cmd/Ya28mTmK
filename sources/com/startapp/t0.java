package com.startapp;

import android.os.Build;
import android.os.SystemClock;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.system.StructPollfd;
import android.util.SparseArray;
import java.io.FileDescriptor;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class t0 {
    private static final String a = "t0";
    private static final boolean b = false;
    private static final short c;
    private static final int d = 7;
    private static final int e = 16;
    private static final int f = 64;
    private static final short g = 30583;
    private InetAddress h;
    private v0 i;
    private int j;
    private int k;
    private int l;
    private u0 m;
    private short n = 1;
    private short o = g;
    private boolean p = false;
    private boolean q = false;
    private int r;
    private int s;
    private int t;
    private long u;
    private SparseArray<Long> v;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends Thread {
        private StructPollfd[] a;

        public a(StructPollfd[] structPollfdArr) {
            this.a = structPollfdArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int poll;
            StructPollfd structPollfd = this.a[0];
            FileDescriptor fileDescriptor = structPollfd.fd;
            int i = t0.this.s;
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (t0.this.q && !t0.this.p && t0.this.t < t0.this.l) {
                try {
                    poll = Os.poll(this.a, t0.this.j);
                } catch (Throwable th) {
                    th = th;
                }
                if (t0.this.p) {
                    return;
                }
                if (poll >= 0 && structPollfd.revents == t0.c) {
                    structPollfd.revents = (short) 0;
                    Os.recvfrom(fileDescriptor, bArr, 0, i, 64, null);
                    int hashCode = Arrays.hashCode(t0.b(bArr));
                    Long l = (Long) t0.this.v.get(hashCode);
                    if (l != null) {
                        t0.this.v.remove(hashCode);
                        long elapsedRealtime = SystemClock.elapsedRealtime() - l.longValue();
                        int i3 = i2 + 1;
                        try {
                            t0.this.i.a(i2, SystemClock.elapsedRealtime() - t0.this.u, elapsedRealtime);
                            t0.f(t0.this);
                            i2 = i3;
                        } catch (Throwable th2) {
                            th = th2;
                            i2 = i3;
                            h1.a(th);
                        }
                    }
                }
            }
        }
    }

    static {
        int i = OsConstants.POLLIN;
        if (i == 0) {
            i = 1;
        }
        c = (short) i;
    }

    public t0(InetAddress inetAddress, int i, int i2, int i3, int i4) {
        this.h = inetAddress;
        this.j = i3;
        this.l = i;
        this.k = i2;
        this.m = new u0(inetAddress instanceof Inet6Address ? Byte.MIN_VALUE : (byte) 8);
        this.r = i4;
        this.s = i4 + 8;
        this.v = new SparseArray<>();
    }

    private void a(FileDescriptor fileDescriptor) throws ErrnoException {
        if (Build.VERSION.SDK_INT >= 26) {
            Os.setsockoptInt(fileDescriptor, OsConstants.IPPROTO_IP, OsConstants.IP_TOS, 16);
            return;
        }
        try {
            Class cls = Integer.TYPE;
            Os.class.getMethod("setsockoptInt", FileDescriptor.class, cls, cls, cls).invoke(null, fileDescriptor, Integer.valueOf(OsConstants.IPPROTO_IP), Integer.valueOf(OsConstants.IP_TOS), 16);
        } catch (Throwable th) {
            h1.a(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] b(byte[] bArr) {
        return Arrays.copyOfRange(bArr, 8, bArr.length);
    }

    public static /* synthetic */ int f(t0 t0Var) {
        int i = t0Var.t;
        t0Var.t = i + 1;
        return i;
    }

    public void a(v0 v0Var) {
        this.i = v0Var;
    }

    public void a(short s) {
        this.o = s;
    }

    public void b() {
        this.p = true;
    }

    public void c() {
        int i;
        int i2;
        this.p = false;
        if (this.h instanceof Inet6Address) {
            i = OsConstants.AF_INET6;
            i2 = OsConstants.IPPROTO_ICMPV6;
        } else {
            i = OsConstants.AF_INET;
            i2 = OsConstants.IPPROTO_ICMP;
        }
        this.u = SystemClock.elapsedRealtime();
        try {
            FileDescriptor socket = Os.socket(i, OsConstants.SOCK_DGRAM, i2);
            if (socket.valid()) {
                try {
                    a(socket);
                    StructPollfd structPollfd = new StructPollfd();
                    structPollfd.fd = socket;
                    structPollfd.events = c;
                    a aVar = new a(new StructPollfd[]{structPollfd});
                    this.q = true;
                    this.u = SystemClock.elapsedRealtime();
                    aVar.start();
                    for (int i3 = 0; i3 < this.l && !this.p; i3++) {
                        byte[] a2 = u0.a(this.r);
                        u0 u0Var = this.m;
                        short s = this.n;
                        this.n = (short) (s + 1);
                        ByteBuffer a3 = u0Var.a(s, this.o, a2);
                        try {
                            this.v.put(Arrays.hashCode(a2), Long.valueOf(SystemClock.elapsedRealtime()));
                            if (Os.sendto(socket, a3, 0, this.h, 7) < 0) {
                                break;
                            }
                        } catch (Throwable th) {
                            h1.a(th);
                            this.i.a(i3, SystemClock.elapsedRealtime() - this.u, -1L);
                            this.t++;
                        }
                        if (i3 < this.l - 1) {
                            try {
                                Thread.sleep(this.k);
                            } catch (Throwable th2) {
                                h1.a(th2);
                            }
                        }
                    }
                    this.q = false;
                    if (aVar.isAlive()) {
                        aVar.join();
                    }
                    Os.close(socket);
                    this.q = false;
                } catch (Throwable th3) {
                    Os.close(socket);
                    this.q = false;
                    throw th3;
                }
            }
            if (this.p) {
                return;
            }
            for (int i4 = this.t; i4 < this.l; i4++) {
                this.i.a(i4, SystemClock.elapsedRealtime() - this.u, -1L);
            }
        } catch (Throwable th4) {
            h1.a(th4);
        }
    }
}
