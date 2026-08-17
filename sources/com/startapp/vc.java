package com.startapp;

import android.content.Context;
import com.startapp.pc;
import com.startapp.sdk.components.ComponentLocator;
import com.startapp.sdk.jobs.JobRequest;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class vc implements uc {
    public final WeakReference<Context> a;
    public final Map<Integer, Future<?>> b = new HashMap();
    public final ScheduledExecutorService c = Executors.newScheduledThreadPool(1, ComponentLocator.b("scheduler"));

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends oc {
        public final /* synthetic */ JobRequest b;
        public final /* synthetic */ long c;

        public a(JobRequest jobRequest, long j) {
            this.b = jobRequest;
            this.c = j;
        }

        @Override // com.startapp.oc
        public void a(pc pcVar) {
            vc vcVar = vc.this;
            int a = JobRequest.a(this.b.a);
            long j = this.c;
            synchronized (vcVar) {
                vcVar.b.put(Integer.valueOf(a), vcVar.c.scheduleAtFixedRate(pcVar, j, j, TimeUnit.MILLISECONDS));
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements pc.a {
        public b(vc vcVar, JobRequest jobRequest) {
        }

        @Override // com.startapp.pc.a
        public void a(pc pcVar, boolean z) {
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class c extends oc {
        public final /* synthetic */ JobRequest b;
        public final /* synthetic */ long c;

        public c(JobRequest jobRequest, long j) {
            this.b = jobRequest;
            this.c = j;
        }

        @Override // com.startapp.oc
        public void a(pc pcVar) {
            vc vcVar = vc.this;
            int a = JobRequest.a(this.b.a);
            long j = this.c;
            synchronized (vcVar) {
                vcVar.b.put(Integer.valueOf(a), vcVar.c.schedule(pcVar, j, TimeUnit.MILLISECONDS));
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class d implements pc.a {
        public final /* synthetic */ JobRequest a;
        public final /* synthetic */ long b;

        public d(JobRequest jobRequest, long j) {
            this.a = jobRequest;
            this.b = j;
        }

        @Override // com.startapp.pc.a
        public void a(pc pcVar, boolean z) {
            if (!z) {
                synchronized (this) {
                    vc.this.b.remove(Integer.valueOf(JobRequest.a(this.a.a)));
                }
                return;
            }
            vc vcVar = vc.this;
            int a = JobRequest.a(this.a.a);
            long j = this.b;
            synchronized (vcVar) {
                vcVar.b.put(Integer.valueOf(a), vcVar.c.schedule(pcVar, j, TimeUnit.MILLISECONDS));
            }
        }
    }

    public vc(Context context) {
        this.a = new WeakReference<>(context);
    }

    @Override // com.startapp.uc
    public synchronized boolean a(int i) {
        Future<?> future = this.b.get(Integer.valueOf(i));
        if (future == null) {
            return false;
        }
        this.b.remove(Integer.valueOf(i));
        return future.cancel(true);
    }

    @Override // com.startapp.uc
    public boolean a(JobRequest jobRequest, long j) {
        Context context = this.a.get();
        if (context == null) {
            return false;
        }
        return new a(jobRequest, j).a(context, jobRequest.a, new b(this, jobRequest), null);
    }

    @Override // com.startapp.uc
    public boolean a(JobRequest jobRequest, Long l, Long l2) {
        Context context = this.a.get();
        if (context == null) {
            return false;
        }
        long longValue = l != null ? l.longValue() : 0L;
        return new c(jobRequest, longValue).a(context, jobRequest.a, new d(jobRequest, longValue), null);
    }
}
