package org.androidannotations.api;

import android.os.Build;

/* loaded from: classes2.dex */
public class SdkVersionHelper {

    /* loaded from: classes2.dex */
    private static class HelperInternal {
        private HelperInternal() {
        }

        static /* synthetic */ int access$000() {
            return getSdkIntInternal();
        }

        private static int getSdkIntInternal() {
            return Build.VERSION.SDK_INT;
        }
    }

    public static int getSdkInt() {
        if (Build.VERSION.RELEASE.startsWith("1.5")) {
            return 3;
        }
        return HelperInternal.access$000();
    }
}
