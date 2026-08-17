package com.startapp;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.PersistableBundle;
import android.util.Log;
import com.startapp.sdk.jobs.JobRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class rc implements uc {
    public static final String a = "rc";
    public final JobScheduler b;
    public final ComponentName c;
    public final boolean d;

    public rc(Context context, Class<? extends JobService> cls) throws IllegalStateException {
        JobScheduler jobScheduler = (JobScheduler) context.getSystemService("jobscheduler");
        if (jobScheduler == null) {
            throw new IllegalStateException();
        }
        this.b = jobScheduler;
        this.c = new ComponentName(context, cls);
        this.d = ya.a(context, "android.permission.RECEIVE_BOOT_COMPLETED");
    }

    public final JobInfo.Builder a(JobRequest jobRequest, Integer num) {
        JobInfo.Builder builder = new JobInfo.Builder(num != null ? num.intValue() : JobRequest.a(jobRequest.a), this.c);
        PersistableBundle persistableBundle = new PersistableBundle();
        persistableBundle.putString("extraKeyUuid", jobRequest.b.toString());
        persistableBundle.putStringArray("extraKeyTags", jobRequest.a);
        if (num != null) {
            persistableBundle.putInt("extraKeyDuplicate", 1);
        }
        builder.setExtras(persistableBundle);
        Boolean bool = jobRequest.d;
        if (bool != null) {
            builder.setRequiresCharging(bool.booleanValue());
        }
        JobRequest.Network network = jobRequest.c;
        if (network != null) {
            builder.setRequiredNetworkType(network == JobRequest.Network.UNMETERED ? 2 : network == JobRequest.Network.ANY ? 1 : 0);
        }
        if (this.d) {
            builder.setPersisted(true);
        }
        return builder;
    }

    public final List<JobInfo> a() {
        List<JobInfo> list;
        try {
            list = this.b.getAllPendingJobs();
        } catch (Throwable unused) {
            Log.e(a, "getAllPendingJobs() is not reliable on this device.");
            list = null;
        }
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (JobInfo jobInfo : list) {
            if (this.c.equals(jobInfo.getService())) {
                arrayList.add(jobInfo);
            }
        }
        return arrayList;
    }

    @Override // com.startapp.uc
    public boolean a(int i) {
        List<JobInfo> a2 = a();
        if (a2 == null) {
            return false;
        }
        try {
            Iterator<JobInfo> it = a2.iterator();
            while (it.hasNext()) {
                if (it.next().getId() == i) {
                    this.b.cancel(i);
                    return true;
                }
            }
        } catch (Throwable unused) {
            Log.e(a, "cancel(jobId) is not reliable on this device.");
        }
        return false;
    }

    public final boolean a(JobInfo jobInfo, JobRequest jobRequest) {
        try {
        } catch (IllegalStateException unused) {
            Log.e(a, "JobScheduler 100 job limit exceeded. Unable to schedule " + jobRequest.a[0]);
        } catch (Throwable unused2) {
            Log.e(a, "Unable to schedule " + jobRequest.a[0]);
        }
        return this.b.schedule(jobInfo) == 1;
    }

    @Override // com.startapp.uc
    public boolean a(JobRequest jobRequest, long j) {
        JobInfo.Builder a2 = a(jobRequest, (Integer) null);
        if (Build.VERSION.SDK_INT >= 24) {
            return a(a2.setPeriodic(j, JobInfo.getMinFlexMillis()).build(), jobRequest);
        }
        for (JobInfo jobInfo : a()) {
            if (jobInfo.getId() == JobRequest.a(jobRequest.a) && jobInfo.getIntervalMillis() == j) {
                return false;
            }
        }
        return a(a2.setPeriodic(j).build(), jobRequest);
    }

    @Override // com.startapp.uc
    public boolean a(JobRequest jobRequest, Long l, Long l2) {
        ArrayList arrayList = null;
        JobInfo.Builder a2 = a(jobRequest, (Integer) null);
        if (l != null || Build.VERSION.SDK_INT <= 28) {
            a2.setMinimumLatency(l != null ? l.longValue() : 0L);
        }
        JobInfo build = a2.build();
        boolean a3 = a(build, jobRequest);
        if (Build.VERSION.SDK_INT == 23) {
            String uuid = jobRequest.b.toString();
            List<JobInfo> a4 = a();
            if (a4 != null) {
                arrayList = new ArrayList(2);
                for (JobInfo jobInfo : a4) {
                    PersistableBundle extras = jobInfo.getExtras();
                    try {
                        if (extras.containsKey("extraKeyUuid") && uuid.equals(extras.getString("extraKeyUuid"))) {
                            arrayList.add(Integer.valueOf(jobInfo.getId()));
                        }
                    } catch (NullPointerException unused) {
                    }
                }
            }
            if (arrayList != null) {
                int id = build.getId();
                int indexOf = arrayList.indexOf(Integer.valueOf(id));
                if (indexOf >= 0) {
                    arrayList.remove(indexOf);
                }
                JobInfo.Builder a5 = a(jobRequest, Integer.valueOf(!arrayList.isEmpty() ? ((Integer) arrayList.get(0)).intValue() : id < Integer.MAX_VALUE ? id + 1 : id - 1));
                if (l != null || Build.VERSION.SDK_INT <= 28) {
                    a5.setMinimumLatency(l != null ? l.longValue() : 0L);
                }
                a(a5.build(), jobRequest);
            }
        }
        return a3;
    }
}
