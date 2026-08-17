package cu.uci.android.apklis.domain.interactor.impl;

import cu.uci.android.apklis.domain.UserRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.SignUpUserInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;
import cu.uci.android.apklis.domain.model.UserAccount;
import cu.uci.android.apklis.storage.repository.model.ApiUser;

/* loaded from: classes.dex */
public class SignUpUserInteractorImpl extends AbstractInteractor implements SignUpUserInteractor {
    private String firstName;
    private String lastName;
    private SignUpUserInteractor.Callback mCallback;
    private String password;
    private String phoneNumber;
    private UserRepository repository;
    private String username;

    public SignUpUserInteractorImpl(Executor executor, MainThread mainThread, SignUpUserInteractor.Callback callback, UserRepository userRepository, String str, String str2, String str3, String str4, String str5) {
        super(executor, mainThread);
        if (userRepository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.repository = userRepository;
        this.username = str;
        this.firstName = str2;
        this.lastName = str3;
        this.phoneNumber = str4;
        this.password = str5;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        Boolean.valueOf(false);
        ApiUser signUp = this.repository.signUp(this.username, this.firstName, this.lastName, this.phoneNumber, this.password);
        if (signUp == null) {
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.SignUpUserInteractorImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    SignUpUserInteractorImpl.this.mCallback.onServerError();
                }
            });
            return null;
        }
        final UserAccount userAccount = new UserAccount();
        userAccount.setId(signUp.getId());
        userAccount.setUsername(signUp.getUsername());
        userAccount.setFirstName(signUp.getFirst_name());
        userAccount.setLastName(signUp.getLast_name());
        userAccount.setPhoneNumber(signUp.getPhone_number());
        if (userAccount.getId() != null) {
            userAccount.setPassword(this.password);
        }
        if (userAccount.getId() == null) {
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.SignUpUserInteractorImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    SignUpUserInteractorImpl.this.mCallback.onUserDataError(userAccount);
                }
            });
            return null;
        }
        final boolean is_active = signUp.getIs_active();
        this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.SignUpUserInteractorImpl.3
            @Override // java.lang.Runnable
            public void run() {
                SignUpUserInteractorImpl.this.mCallback.onUserRegistered(userAccount, is_active);
            }
        });
        return null;
    }
}
