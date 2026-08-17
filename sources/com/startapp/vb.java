package com.startapp;

import android.content.Context;

/* compiled from: StartAppSDK */
/* loaded from: classes3.dex */
public class vb extends sb<ub> {
    public vb(Context context) {
        super(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    @Override // com.startapp.sb
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.startapp.ub a() {
        /*
            r9 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 11
            if (r0 >= r2) goto L9
            goto La6
        L9:
            android.content.Context r0 = r9.a
            java.lang.String r2 = "input_method"
            java.lang.Object r0 = r0.getSystemService(r2)
            boolean r2 = r0 instanceof android.view.inputmethod.InputMethodManager
            if (r2 != 0) goto L17
            goto La6
        L17:
            android.view.inputmethod.InputMethodManager r0 = (android.view.inputmethod.InputMethodManager) r0
            android.view.inputmethod.InputMethodSubtype r2 = r0.getCurrentInputMethodSubtype()
            r3 = 10
            java.lang.String r4 = "keyboard"
            if (r2 == 0) goto L46
            java.lang.String r5 = r2.getMode()
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L46
            java.lang.String r2 = r2.getLocale()
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 != 0) goto L46
            java.util.LinkedHashSet r5 = new java.util.LinkedHashSet
            r5.<init>()
            int r6 = r5.size()
            if (r6 >= r3) goto L47
            r5.add(r2)
            goto L47
        L46:
            r5 = r1
        L47:
            java.util.List r2 = r0.getInputMethodList()
            if (r2 == 0) goto L9f
            java.util.Iterator r2 = r2.iterator()
        L51:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L9f
            java.lang.Object r6 = r2.next()
            android.view.inputmethod.InputMethodInfo r6 = (android.view.inputmethod.InputMethodInfo) r6
            if (r6 != 0) goto L60
            goto L51
        L60:
            r7 = 1
            java.util.List r6 = r0.getEnabledInputMethodSubtypeList(r6, r7)
            if (r6 != 0) goto L68
            goto L51
        L68:
            java.util.Iterator r6 = r6.iterator()
        L6c:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L51
            java.lang.Object r7 = r6.next()
            android.view.inputmethod.InputMethodSubtype r7 = (android.view.inputmethod.InputMethodSubtype) r7
            if (r7 == 0) goto L6c
            java.lang.String r8 = r7.getMode()
            boolean r8 = r4.equals(r8)
            if (r8 == 0) goto L6c
            java.lang.String r7 = r7.getLocale()
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 != 0) goto L6c
            if (r5 != 0) goto L95
            java.util.LinkedHashSet r5 = new java.util.LinkedHashSet
            r5.<init>()
        L95:
            int r8 = r5.size()
            if (r8 >= r3) goto L6c
            r5.add(r7)
            goto L6c
        L9f:
            if (r5 == 0) goto La6
            com.startapp.ub r1 = new com.startapp.ub
            r1.<init>(r5)
        La6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.vb.a():java.lang.Object");
    }

    @Override // com.startapp.sb
    public /* bridge */ /* synthetic */ ub c() {
        return ub.a;
    }
}
