package cu.uci.android.apklis.domain.executor;

import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class ThreadExecutor implements Executor {
    private static final int CORE_POOL_SIZE = 3;
    private static final int KEEP_ALIVE_TIME = 120;
    private static final int MAX_POOL_SIZE = 5;
    private static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue();
    private static volatile ThreadExecutor sThreadExecutor;
    private ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(3, 5, 120, TIME_UNIT, WORK_QUEUE);

    private ThreadExecutor() {
    }

    public static Executor getInstance() {
        if (sThreadExecutor == null) {
            sThreadExecutor = new ThreadExecutor();
        }
        return sThreadExecutor;
    }

    @Override // cu.uci.android.apklis.domain.executor.Executor
    public void execute(final AbstractInteractor abstractInteractor) {
        this.mThreadPoolExecutor.submit(new Runnable() { // from class: cu.uci.android.apklis.domain.executor.ThreadExecutor.1
            @Override // java.lang.Runnable
            public void run() {
                abstractInteractor.run();
                abstractInteractor.onFinished();
            }
        });
    }
}
