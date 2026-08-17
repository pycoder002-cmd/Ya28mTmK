package cu.uci.android.apklis.presentation.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.presentation.presenter.ActivateAccountPresenter;
import cu.uci.android.apklis.presentation.presenter.ForgotPasswordPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.ForgotPasswordPresenterImpl;
import cu.uci.android.apklis.presentation.ui.activity.AccountActivity;
import cu.uci.android.apklis.storage.repository.UserRepositoryImpl;
import cu.uci.android.apklis.threading.MainThreadImpl;

/* loaded from: classes.dex */
public class ForgotPasswordDialog extends AppCompatDialog implements ActivateAccountPresenter.View {
    AccountActivity activity;
    ImageView btnCancel;
    LinearLayout btnForgotPassword;
    EditText etActivationCode;
    ForgotPasswordPresenter presenter;
    ProgressBar progress;
    TextInputLayout tilActivationCode;

    public ForgotPasswordDialog(Context context) {
        super(context);
        this.activity = (AccountActivity) context;
    }

    public ForgotPasswordDialog(Context context, int i) {
        super(context, i);
    }

    protected ForgotPasswordDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    private void init() {
        this.presenter = new ForgotPasswordPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, new UserRepositoryImpl());
        this.btnForgotPassword.setVisibility(0);
        this.progress.setVisibility(8);
        this.etActivationCode.setTypeface(Typeface.DEFAULT);
        this.etActivationCode.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.dialog.ForgotPasswordDialog.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                ForgotPasswordDialog.this.tilActivationCode.setErrorEnabled(false);
                ForgotPasswordDialog.this.tilActivationCode.setError("");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
        setCancelable(true);
        this.btnForgotPassword.setVisibility(0);
        this.progress.setVisibility(8);
    }

    void onClickCancel() {
        onBackPressed();
    }

    public void onClickNewPassword() {
        Boolean bool = true;
        if (this.etActivationCode.getText().length() == 0) {
            bool = false;
            this.tilActivationCode.setErrorEnabled(true);
            this.tilActivationCode.setError(getContext().getString(R.string.error_input_empty));
        }
        if (bool.booleanValue()) {
            this.presenter.receiveToken(this.etActivationCode.getText().toString());
            dismiss();
            new NewPasswordDialog(getContext()).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.fragment_account_forgot_password);
        this.btnCancel = (ImageView) findViewById(R.id.btn_cancel);
        this.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.dialog.ForgotPasswordDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ForgotPasswordDialog.this.onClickCancel();
            }
        });
        this.tilActivationCode = (TextInputLayout) findViewById(R.id.til_activation_code);
        this.etActivationCode = (EditText) findViewById(R.id.et_activation_code_phone);
        this.btnForgotPassword = (LinearLayout) findViewById(R.id.btn_forgot_password);
        this.btnForgotPassword.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.dialog.ForgotPasswordDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ForgotPasswordDialog.this.onClickNewPassword();
            }
        });
        this.progress = (ProgressBar) findViewById(R.id.progress);
        init();
        setCancelable(true);
    }

    public void onReceiveToken(boolean z) {
        if (!z) {
            this.tilActivationCode.setErrorEnabled(true);
            this.tilActivationCode.setError(MainApp.context.getString(R.string.error_activation_code));
        } else {
            showError(MainApp.context.getString(R.string.user_activation_sucessfull_message));
            dismiss();
            ((AccountActivity) getContext()).finish();
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ActivateAccountPresenter.View
    public void onServerError() {
        showError(MainApp.context.getString(R.string.error_server_conection));
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ActivateAccountPresenter.View
    public void onUserActivated(boolean z) {
        if (!z) {
            this.tilActivationCode.setErrorEnabled(true);
            this.tilActivationCode.setError(MainApp.context.getString(R.string.error_activation_code));
        } else {
            showError(MainApp.context.getString(R.string.user_activation_sucessfull_message));
            dismiss();
            ((AccountActivity) getContext()).finish();
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        MainApp.showToast(str, false);
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
        setCancelable(false);
        this.btnForgotPassword.setVisibility(4);
        this.progress.setVisibility(0);
    }
}
