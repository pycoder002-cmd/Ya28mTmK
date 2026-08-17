package com.google.android.gms.internal;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.internal.zzrj;

/* loaded from: classes.dex */
public abstract class zzqp extends zzro implements DialogInterface.OnCancelListener {
    protected boolean mStarted;
    protected final GoogleApiAvailability xP;
    private ConnectionResult yA;
    private int yB;
    private final Handler yC;
    protected boolean yz;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public class zza implements Runnable {
        private zza() {
        }

        @Override // java.lang.Runnable
        @MainThread
        public void run() {
            if (zzqp.this.mStarted) {
                if (zzqp.this.yA.hasResolution()) {
                    zzqp.this.Bf.startActivityForResult(GoogleApiActivity.zzb(zzqp.this.getActivity(), zzqp.this.yA.getResolution(), zzqp.this.yB, false), 1);
                    return;
                }
                if (zzqp.this.xP.isUserResolvableError(zzqp.this.yA.getErrorCode())) {
                    zzqp.this.xP.zza(zzqp.this.getActivity(), zzqp.this.Bf, zzqp.this.yA.getErrorCode(), 2, zzqp.this);
                } else if (zzqp.this.yA.getErrorCode() != 18) {
                    zzqp.this.zza(zzqp.this.yA, zzqp.this.yB);
                } else {
                    final Dialog zza = zzqp.this.xP.zza(zzqp.this.getActivity(), zzqp.this);
                    zzqp.this.xP.zza(zzqp.this.getActivity().getApplicationContext(), new zzrj.zza() { // from class: com.google.android.gms.internal.zzqp.zza.1
                        @Override // com.google.android.gms.internal.zzrj.zza
                        public void zzarr() {
                            zzqp.this.zzarq();
                            if (zza.isShowing()) {
                                zza.dismiss();
                            }
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public zzqp(zzrp zzrpVar) {
        this(zzrpVar, GoogleApiAvailability.getInstance());
    }

    zzqp(zzrp zzrpVar, GoogleApiAvailability googleApiAvailability) {
        super(zzrpVar);
        this.yB = -1;
        this.yC = new Handler(Looper.getMainLooper());
        this.xP = googleApiAvailability;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0002. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    @Override // com.google.android.gms.internal.zzro
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onActivityResult(int r3, int r4, android.content.Intent r5) {
        /*
            r2 = this;
            r0 = 1
            r1 = 0
            switch(r3) {
                case 1: goto L21;
                case 2: goto L6;
                default: goto L5;
            }
        L5:
            goto L39
        L6:
            com.google.android.gms.common.GoogleApiAvailability r3 = r2.xP
            android.app.Activity r4 = r2.getActivity()
            int r3 = r3.isGooglePlayServicesAvailable(r4)
            if (r3 != 0) goto L13
            goto L14
        L13:
            r0 = r1
        L14:
            com.google.android.gms.common.ConnectionResult r4 = r2.yA
            int r4 = r4.getErrorCode()
            r5 = 18
            if (r4 != r5) goto L3a
            if (r3 != r5) goto L3a
            return
        L21:
            r3 = -1
            if (r4 != r3) goto L25
            goto L3a
        L25:
            if (r4 != 0) goto L39
            r3 = 13
            if (r5 == 0) goto L31
            java.lang.String r4 = "<<ResolutionFailureErrorDetail>>"
            int r3 = r5.getIntExtra(r4, r3)
        L31:
            com.google.android.gms.common.ConnectionResult r4 = new com.google.android.gms.common.ConnectionResult
            r5 = 0
            r4.<init>(r3, r5)
            r2.yA = r4
        L39:
            r0 = r1
        L3a:
            if (r0 == 0) goto L40
            r2.zzarq()
            return
        L40:
            com.google.android.gms.common.ConnectionResult r3 = r2.yA
            int r4 = r2.yB
            r2.zza(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzqp.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        zza(new ConnectionResult(13, null), this.yB);
        zzarq();
    }

    @Override // com.google.android.gms.internal.zzro
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.yz = bundle.getBoolean("resolving_error", false);
            if (this.yz) {
                this.yB = bundle.getInt("failed_client_id", -1);
                this.yA = new ConnectionResult(bundle.getInt("failed_status"), (PendingIntent) bundle.getParcelable("failed_resolution"));
            }
        }
    }

    @Override // com.google.android.gms.internal.zzro
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("resolving_error", this.yz);
        if (this.yz) {
            bundle.putInt("failed_client_id", this.yB);
            bundle.putInt("failed_status", this.yA.getErrorCode());
            bundle.putParcelable("failed_resolution", this.yA.getResolution());
        }
    }

    @Override // com.google.android.gms.internal.zzro
    public void onStart() {
        super.onStart();
        this.mStarted = true;
    }

    @Override // com.google.android.gms.internal.zzro
    public void onStop() {
        super.onStop();
        this.mStarted = false;
    }

    protected abstract void zza(ConnectionResult connectionResult, int i);

    protected abstract void zzarm();

    protected void zzarq() {
        this.yB = -1;
        this.yz = false;
        this.yA = null;
        zzarm();
    }

    public void zzb(ConnectionResult connectionResult, int i) {
        if (this.yz) {
            return;
        }
        this.yz = true;
        this.yB = i;
        this.yA = connectionResult;
        this.yC.post(new zza());
    }
}
