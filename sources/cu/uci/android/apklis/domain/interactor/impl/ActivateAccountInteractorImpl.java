package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.domain.UserRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.ActivateAccountInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.domain.model.UserAccount;

/* loaded from: classes.dex */
public class ActivateAccountInteractorImpl extends AbstractInteractor implements ActivateAccountInteractor {
    private String activationCode;
    private ActivateAccountInteractor.Callback mCallback;
    private UserRepository repository;

    public ActivateAccountInteractorImpl(Executor executor, MainThread mainThread, ActivateAccountInteractor.Callback callback, UserRepository userRepository, String str) {
        super(executor, mainThread);
        if (userRepository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.repository = userRepository;
        this.activationCode = str;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        Boolean.valueOf(false);
        UserAccount activateAccount = this.repository.activateAccount(this.activationCode);
        if (activateAccount == null) {
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.ActivateAccountInteractorImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    ActivateAccountInteractorImpl.this.mCallback.onServerError();
                }
            });
            return null;
        }
        final boolean z = activateAccount.getAccessToken() != null;
        this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.ActivateAccountInteractorImpl.2
            @Override // java.lang.Runnable
            public void run() {
                ActivateAccountInteractorImpl.this.mCallback.onUserActivated(z);
            }
        });
        return null;
    }
}
