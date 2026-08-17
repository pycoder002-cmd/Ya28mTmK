package cu.uci.android.apklis.threading;

import android.os.Handler;
import android.os.Looper;
import cu.uci.android.apklis.domain.executor.MainThread;

/* loaded from: classes.dex */
public class MainThreadImpl implements MainThread {
    private static MainThread sMainThread;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private MainThreadImpl() {
    }

    public static MainThread getInstance() {
        if (sMainThread == null) {
            sMainThread = new MainThreadImpl();
        }
        return sMainThread;
    }

    @Override // cu.uci.android.apklis.domain.executor.MainThread
    public void post(Runnable runnable) {
        this.mHandler.post(runnable);
    }
}
