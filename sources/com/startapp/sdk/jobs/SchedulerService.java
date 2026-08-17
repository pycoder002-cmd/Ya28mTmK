package com.startapp.sdk.jobs;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import com.startapp.oc;
import com.startapp.pc;
import com.startapp.sdk.components.ComponentLocator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class SchedulerService extends JobService {
    public static final String a = "SchedulerService";
    public ExecutorService b;
    public final oc c = new a();

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class a extends oc {
        public a() {
        }

        @Override // com.startapp.oc
        public void a(pc pcVar) {
            ExecutorService executorService = SchedulerService.this.b;
            if (executorService != null) {
                executorService.execute(pcVar);
            }
        }
    }

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public class b implements pc.a {
        public final /* synthetic */ JobParameters a;

        public b(PersistableBundle persistableBundle, JobParameters jobParameters) {
            this.a = jobParameters;
        }

        @Override // com.startapp.pc.a
        public void a(pc pcVar, boolean z) {
            SchedulerService.this.jobFinished(this.a, z);
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.b = Executors.newSingleThreadExecutor(new ComponentLocator.d0("scheduler"));
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        ExecutorService executorService = this.b;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    @Override // android.app.job.JobService
    public boolean onStartJob(JobParameters jobParameters) {
        Bundle bundle;
        if (this.b == null) {
            Log.e(a, "Service is not initialized; requesting retry.");
            return false;
        }
        PersistableBundle extras = jobParameters.getExtras();
        if (extras.containsKey("extraKeyDuplicate")) {
            return false;
        }
        PersistableBundle persistableBundle = extras.getPersistableBundle("extraKeyBundle");
        if (persistableBundle != null) {
            bundle = new Bundle();
            bundle.putAll(persistableBundle);
        } else {
            bundle = null;
        }
        return this.c.a(this, extras.getStringArray("extraKeyTags"), new b(extras, jobParameters), bundle);
    }

    @Override // android.app.job.JobService
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
