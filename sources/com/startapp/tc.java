package com.startapp;

import com.startapp.sdk.jobs.JobRequest;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public final class tc extends JobRequest {
    public final Long f;

    /* compiled from: StartAppSDK */
    /* loaded from: classes3.dex */
    public static final class a extends JobRequest.a<a> {
        public Long e;

        public a(Class<? extends pc> cls) {
            super(cls);
        }
    }

    public tc(a aVar) {
        super(aVar);
        this.f = aVar.e;
    }

    @Override // com.startapp.sdk.jobs.JobRequest
    public boolean a(uc ucVar) {
        return ucVar.a(this, this.f.longValue());
    }
}
