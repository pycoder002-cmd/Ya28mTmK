package cu.uci.android.apklis.domain.interactor.impl;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.blankj.utilcode.util.Utils;
import cu.uci.android.apklis.domain.UserRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.ForgotInteractor;
import cu.uci.android.apklis.domain.interactor.base.AbstractInteractor;

/* loaded from: classes.dex */
public class ForgotInteractorImpl extends AbstractInteractor implements ForgotInteractor {
    private ForgotInteractor.Callback mCallback;
    private String number_phone;
    private UserRepository repository;

    public ForgotInteractorImpl(Executor executor, MainThread mainThread, ForgotInteractor.Callback callback, UserRepository userRepository, String str) {
        super(executor, mainThread);
        if (userRepository == null || callback == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }
        this.mCallback = callback;
        this.repository = userRepository;
        this.number_phone = str;
    }

    @Override // cu.uci.android.apklis.domain.interactor.base.AbstractInteractor
    public String run() {
        Boolean.valueOf(false);
        final String newPassword = this.repository.newPassword(this.number_phone);
        Log.e("run tokennnnnnn: ", newPassword);
        if (newPassword == null) {
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.ForgotInteractorImpl.1
                @Override // java.lang.Runnable
                public void run() {
                    ForgotInteractorImpl.this.mCallback.onServerError();
                }
            });
        } else {
            this.mMainThread.post(new Runnable() { // from class: cu.uci.android.apklis.domain.interactor.impl.ForgotInteractorImpl.2
                @Override // java.lang.Runnable
                public void run() {
                    ForgotInteractorImpl.this.mCallback.hideProgress();
                    new Bundle().putString("token", newPassword);
                    SharedPreferences.Editor edit = Utils.getContext().getSharedPreferences("shared", 0).edit();
                    edit.putString("token", newPassword);
                    edit.apply();
                    edit.commit();
                    Log.e("run entradaaaaaaaaaaa: ", newPassword);
                }
            });
        }
        return newPassword;
    }
}
