package com.startapp;

import android.os.SystemClock;
import android.widget.TextView;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class s2 implements Runnable {
    public final /* synthetic */ r2 a;

    public s2(r2 r2Var) {
        this.a = r2Var;
    }

    @Override // java.lang.Runnable
    public void run() {
        long uptimeMillis = (this.a.s * 1000) - SystemClock.uptimeMillis();
        r2 r2Var = this.a;
        long j = uptimeMillis + r2Var.B;
        TextView textView = r2Var.P;
        if (textView != null) {
            long j2 = j / 1000;
            if (j2 > 0 && j % 1000 < 100) {
                j2--;
            }
            textView.setText(String.valueOf(j2));
        }
        if (j >= 1000) {
            long j3 = j % 1000;
            this.a.T.postDelayed(this, j3 != 0 ? j3 : 1000L);
            return;
        }
        r2 r2Var2 = this.a;
        if (r2Var2.P != null) {
            r2Var2.Q.setVisibility(8);
            this.a.P.setVisibility(8);
        }
        this.a.s();
    }
}
