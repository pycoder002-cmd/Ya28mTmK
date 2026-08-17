package com.startapp;

import com.startapp.sdk.jobs.JobRequest;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class qc {
    public final uc a;
    public final uc b;

    public qc(uc ucVar, uc ucVar2) {
        this.a = ucVar;
        this.b = ucVar2;
    }

    public boolean a(int i) {
        if (this.b.a(i)) {
            return true;
        }
        return this.a.a(i);
    }

    public boolean a(JobRequest... jobRequestArr) {
        int i;
        int length = jobRequestArr.length;
        boolean z = true;
        while (i < length) {
            JobRequest jobRequest = jobRequestArr[i];
            if (jobRequest.e) {
                i = jobRequest.a(this.b) ? i + 1 : 0;
                z = false;
            } else {
                if (jobRequest.a(this.a)) {
                }
                z = false;
            }
        }
        return z;
    }
}
