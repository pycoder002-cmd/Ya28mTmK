package cu.uci.android.apklis.presentation.presenter.impl;

import android.widget.Toast;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.UserRepository;
import cu.uci.android.apklis.domain.executor.Executor;
import cu.uci.android.apklis.domain.executor.MainThread;
import cu.uci.android.apklis.domain.interactor.NewPasswordInteractor;
import cu.uci.android.apklis.domain.interactor.impl.NewPasswordInteractorImpl;
import cu.uci.android.apklis.presentation.presenter.NewPasswordPresenter;
import cu.uci.android.apklis.presentation.presenter.base.AbstractPresenter;
import cu.uci.android.apklis.presentation.ui.dialog.NewPasswordDialog;

/* loaded from: classes.dex */
public class NewPasswordPresenterImpl extends AbstractPresenter implements NewPasswordPresenter, NewPasswordInteractor.Callback {
    NewPasswordDialog mView;
    UserRepository repository;

    public NewPasswordPresenterImpl(Executor executor, MainThread mainThread) {
        super(executor, mainThread);
    }

    public NewPasswordPresenterImpl(Executor executor, MainThread mainThread, NewPasswordDialog newPasswordDialog, UserRepository userRepository) {
        super(executor, mainThread);
        this.mView = newPasswordDialog;
        this.repository = userRepository;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.NewPasswordPresenter
    public void change_password_whit_tk(String str, String str2, String str3) {
        this.mView.showProgress();
        new NewPasswordInteractorImpl(this.mExecutor, this.mMainThread, this, this.repository, str, str2, str3).execute();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void destroy() {
    }

    @Override // cu.uci.android.apklis.domain.interactor.NewPasswordInteractor.Callback
    public void hideProgress() {
        this.mView.hideProgress();
        this.mView.dismiss();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void onError(String str) {
    }

    @Override // cu.uci.android.apklis.domain.interactor.NewPasswordInteractor.Callback
    public void onServerError() {
        this.mView.hideProgress();
        this.mView.onServerError();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void pause() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void resume() {
    }

    @Override // cu.uci.android.apklis.domain.interactor.NewPasswordInteractor.Callback
    public void showMessage(String str) {
        Toast.makeText(this.mView.getContext(), str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.mView.getContext().getString(R.string.user_change_password_sucessfull_message), 1).show();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.base.BasePresenter
    public void stop() {
    }
}
