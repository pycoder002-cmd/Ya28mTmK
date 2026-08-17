package com.startapp;

import com.startapp.sdk.ads.list3d.List3DView;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class c3 implements Runnable {
    public final /* synthetic */ List3DView a;

    public c3(List3DView list3DView) {
        this.a = list3DView;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0045, code lost:
    
        if (r8 < r9) goto L17;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r11 = this;
            com.startapp.sdk.ads.list3d.List3DView r0 = r11.a
            com.startapp.y2 r1 = r0.l
            if (r1 != 0) goto L7
            return
        L7:
            r1 = 0
            android.view.View r0 = r0.getChildAt(r1)
            if (r0 == 0) goto L6e
            com.startapp.sdk.ads.list3d.List3DView r2 = r11.a
            int r0 = r2.d(r0)
            com.startapp.sdk.ads.list3d.List3DView r3 = r11.a
            int r4 = r3.g
            int r0 = r0 - r4
            r2.e = r0
            com.startapp.y2 r0 = r3.l
            long r2 = android.view.animation.AnimationUtils.currentAnimationTimeMillis()
            long r4 = r0.e
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L5f
            long r4 = r2 - r4
            int r5 = (int) r4
            r4 = 50
            if (r5 <= r4) goto L32
            r5 = 50
        L32:
            r4 = r0
            com.startapp.j3 r4 = (com.startapp.j3) r4
            float r6 = r4.b
            r7 = 0
            float r8 = r4.a
            float r9 = r4.c
            int r10 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r10 <= 0) goto L41
            goto L47
        L41:
            float r9 = r4.d
            int r10 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r10 >= 0) goto L49
        L47:
            float r7 = r9 - r8
        L49:
            float r9 = r4.g
            float r7 = r7 * r9
            float r6 = r6 + r7
            r4.b = r6
            float r5 = (float) r5
            float r5 = r5 * r6
            r7 = 1148846080(0x447a0000, float:1000.0)
            float r5 = r5 / r7
            float r8 = r8 + r5
            r4.a = r8
            float r5 = r4.f
            float r6 = r6 * r5
            r4.b = r6
        L5f:
            r0.e = r2
            com.startapp.sdk.ads.list3d.List3DView r0 = r11.a
            com.startapp.y2 r2 = r0.l
            float r2 = r2.a
            int r2 = (int) r2
            int r3 = r0.e
            int r2 = r2 - r3
            r0.a(r2)
        L6e:
            com.startapp.sdk.ads.list3d.List3DView r0 = r11.a
            com.startapp.y2 r0 = r0.l
            r2 = 1053609165(0x3ecccccd, float:0.4)
            float r3 = r0.b
            float r3 = java.lang.Math.abs(r3)
            r4 = 1056964608(0x3f000000, float:0.5)
            r5 = 1
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 >= 0) goto L84
            r3 = 1
            goto L85
        L84:
            r3 = 0
        L85:
            float r4 = r0.a
            float r6 = r4 - r2
            float r7 = r0.c
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 >= 0) goto L98
            float r4 = r4 + r2
            float r0 = r0.d
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 <= 0) goto L98
            r0 = 1
            goto L99
        L98:
            r0 = 0
        L99:
            if (r3 == 0) goto L9e
            if (r0 == 0) goto L9e
            r1 = 1
        L9e:
            if (r1 != 0) goto La7
            com.startapp.sdk.ads.list3d.List3DView r0 = r11.a
            r1 = 16
            r0.postDelayed(r11, r1)
        La7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.c3.run():void");
    }
}
