package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.domain.UserRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.NewPasswordInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;

/* loaded from: classes.dex */
public class NewPasswordInteractorImpl extends AbstractInteractor implements NewPasswordInteractor {
    private String long_token;
    private NewPasswordInteractor.Callback mCallback;
    private String new_password;
    private UserRepository repository;
    private String short_token;

    public NewPasswordInteractorImpl(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }

    public NewPasswordInteractorImpl(Executor executor, MainThread mainThread, NewPasswordInteractor.Callback callback, UserRepository userRepository, String str, String str2, String str3) {
        super(executor, mainThread);
        this.mCallback = callback;
        this.repository = userRepository;
        this.long_token = str;
        this.short_token = str2;
        this.new_password = str3;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        Boolean.valueOf(false);
        final String changePassword = this.repository.changePassword(this.long_token, this.short_token, this.new_password);
        if (changePassword == null) {
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.NewPasswordInteractorImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    NewPasswordInteractorImpl.this.mCallback.onServerError();
                }
            });
        } else {
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.NewPasswordInteractorImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    NewPasswordInteractorImpl.this.mCallback.showMessage(changePassword);
                    NewPasswordInteractorImpl.this.mCallback.hideProgress();
                }
            });
        }
        return changePassword;
    }
}
