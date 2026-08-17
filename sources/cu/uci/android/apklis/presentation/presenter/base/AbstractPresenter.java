package cu.uci.android.apklis.presentation.presenter.base;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;

/* loaded from: classes.dex */
public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        this.mExecutor = executor;
        this.mMainThread = mainThread;
    }
}
