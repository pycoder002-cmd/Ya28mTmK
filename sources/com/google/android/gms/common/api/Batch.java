package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.internal.zzqq;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public final class Batch extends zzqq<BatchResult> {
    private int xp;
    private boolean xq;
    private boolean xr;
    private final PendingResult<?>[] xs;
    private final Object zzako;

    /* loaded from: classes.dex */
    public static final class Builder {
        private GoogleApiClient mD;
        private List<PendingResult<?>> xu = new ArrayList();

        public Builder(GoogleApiClient googleApiClient) {
            this.mD = googleApiClient;
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.xu.size());
            this.xu.add(pendingResult);
            return batchResultToken;
        }

        public Batch build() {
            return new Batch(this.xu, this.mD);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzako = new Object();
        this.xp = list.size();
        this.xs = new PendingResult[this.xp];
        if (list.isEmpty()) {
            zzc((Batch) new BatchResult(Status.xZ, this.xs));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            PendingResult<?> pendingResult = list.get(i);
            this.xs[i] = pendingResult;
            pendingResult.zza(new PendingResult.zza() { // from class: com.google.android.gms.common.api.Batch.1
                @Override // com.google.android.gms.common.api.PendingResult.zza
                public void zzx(Status status) {
                    synchronized (Batch.this.zzako) {
                        if (Batch.this.isCanceled()) {
                            return;
                        }
                        if (status.isCanceled()) {
                            Batch.this.xr = true;
                        } else if (!status.isSuccess()) {
                            Batch.this.xq = true;
                        }
                        Batch.zzb(Batch.this);
                        if (Batch.this.xp == 0) {
                            if (Batch.this.xr) {
                                Batch.super.cancel();
                            } else {
                                Batch.this.zzc((Batch) new BatchResult(Batch.this.xq ? new Status(13) : Status.xZ, Batch.this.xs));
                            }
                        }
                    }
                }
            });
        }
    }

    static /* synthetic */ int zzb(Batch batch) {
        int i = batch.xp;
        batch.xp = i - 1;
        return i;
    }

    @Override // com.google.android.gms.internal.zzqq, com.google.android.gms.common.api.PendingResult
    public void cancel() {
        super.cancel();
        for (PendingResult<?> pendingResult : this.xs) {
            pendingResult.cancel();
        }
    }

    @Override // com.google.android.gms.internal.zzqq
    /* renamed from: createFailedResult, reason: merged with bridge method [inline-methods] */
    public BatchResult zzc(Status status) {
        return new BatchResult(status, this.xs);
    }
}
