package cu.uci.android.apklis.domain.interactor.base;

import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.presentation.ui.dialog.ForgotPasswordDialog;
import cu.uci.android.apklis.storage.repository.UserRepositoryImpl;

/* loaded from: classes.dex */
public abstract class AbstractInteractor implements Interactor {
    protected volatile boolean mIsCanceled;
    protected volatile boolean mIsRunning;
    protected MainThread mMainThread;
    protected Executor mThreadExecutor;

    public AbstractInteractor(Executor executor, MainThread mainThread) {
        this.mThreadExecutor = executor;
        this.mMainThread = mainThread;
    }

    public AbstractInteractor(Executor executor, MainThread mainThread, ForgotPasswordDialog forgotPasswordDialog, UserRepositoryImpl userRepositoryImpl) {
    }

    public void cancel() {
        this.mIsCanceled = true;
        this.mIsRunning = false;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.Interactor
    public void execute() {
        this.mIsRunning = true;
        this.mThreadExecutor.execute(this);
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    public void onFinished() {
        this.mIsRunning = false;
        this.mIsCanceled = false;
    }

    public abstract String run();
}
