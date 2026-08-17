package com.startapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.lang.Thread;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class m7 extends Thread implements Handler.Callback {
    public static final AtomicInteger a = new AtomicInteger();
    public final Context b;
    public final Handler c;
    public final Handler d;
    public volatile boolean e;
    public long f;
    public int g;
    public final Queue<Runnable> h;

    public m7(Context context, Looper looper) {
        super("startapp-anrd-" + a.incrementAndGet());
        this.h = new LinkedList();
        this.b = context;
        this.c = new Handler(looper, this);
        this.d = new Handler(looper);
    }

    public final void a() {
        String str;
        ThreadGroup threadGroup = getThreadGroup();
        if (threadGroup != null) {
            Thread thread = this.c.getLooper().getThread();
            if (thread != null) {
                Thread.State state = thread.getState();
                StackTraceElement[] stackTrace = thread.getStackTrace();
                StackTraceElement a2 = aa.a(stackTrace);
                if (a2 != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    PrintWriter printWriter = new PrintWriter(new DeflaterOutputStream(new Base64OutputStream(byteArrayOutputStream, 10), new Deflater(9, true)));
                    printWriter.print('\"');
                    printWriter.print(thread.getName());
                    printWriter.print("\" ");
                    printWriter.print(state);
                    printWriter.println();
                    aa.a(printWriter, stackTrace);
                    Thread[] threadArr = new Thread[Thread.activeCount() * 2];
                    int enumerate = threadGroup.enumerate(threadArr, true);
                    for (int i = 0; i < enumerate; i++) {
                        Thread thread2 = threadArr[i];
                        if (thread2 != null && thread2 != thread) {
                            Thread.State state2 = thread2.getState();
                            StackTraceElement[] stackTrace2 = thread2.getStackTrace();
                            if (state2 == Thread.State.BLOCKED) {
                                printWriter.print('\"');
                                printWriter.print(thread2.getName());
                                printWriter.print("\" ");
                                printWriter.print(state2);
                                printWriter.println();
                                aa.a(printWriter, stackTrace2);
                            }
                        }
                    }
                    printWriter.close();
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    p7 p7Var = new p7(q7.g);
                    p7Var.d = aa.a(a2);
                    p7Var.e = byteArrayOutputStream2;
                    p7Var.a(this.b);
                }
                str = null;
            } else {
                str = "ANRD: no main";
            }
        } else {
            str = "ANRD: no group";
        }
        if (str != null) {
            p7 p7Var2 = new p7(q7.c);
            p7Var2.d = str;
            p7Var2.a(this.b);
        }
    }

    public void a(Runnable runnable) {
        synchronized (this.h) {
            this.h.add(runnable);
        }
    }

    @Override // android.os.Handler.Callback
    public synchronized boolean handleMessage(Message message) {
        this.e = false;
        notifyAll();
        return true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        boolean z;
        Runnable poll;
        while (!isInterrupted()) {
            try {
                long j = -SystemClock.elapsedRealtime();
                synchronized (this) {
                    this.e = true;
                    this.c.sendEmptyMessage(0);
                    wait(5000L);
                    z = this.e;
                }
                long elapsedRealtime = j + SystemClock.elapsedRealtime();
                int i = this.g;
                if (i < 8) {
                    this.g = i + 1;
                    this.f += elapsedRealtime;
                } else {
                    long j2 = this.f;
                    this.f = j2 + (elapsedRealtime - (j2 / i));
                }
                if (z) {
                    a();
                    this.f = 0L;
                    this.g = 0;
                    synchronized (this) {
                        wait(10000L);
                    }
                } else {
                    if (this.f < 160) {
                        synchronized (this.h) {
                            poll = this.h.poll();
                        }
                        if (poll != null) {
                            this.d.post(poll);
                            this.f = 0L;
                            this.g = 0;
                        }
                    }
                    synchronized (this) {
                        wait(500L);
                    }
                }
            } catch (InterruptedException unused) {
                return;
            } catch (Throwable th) {
                p7.a(this.b, th);
                return;
            }
        }
    }
}
