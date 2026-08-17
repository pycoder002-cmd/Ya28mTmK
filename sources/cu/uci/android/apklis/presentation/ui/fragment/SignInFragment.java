package cu.uci.android.apklis.presentation.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.permissionrequest.PermissionRequest;
import com.example.permissionrequest.PermissionRequestListener;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.presentation.presenter.LoginPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.LoginPresenterImpl;
import cu.uci.android.apklis.presentation.ui.dialog.ActivateAccountDialog;
import cu.uci.android.apklis.presentation.ui.dialog.ForgotPasswordDialog;
import cu.uci.android.apklis.threading.MainThreadImpl;

/* loaded from: classes.dex */
public class SignInFragment extends Fragment implements LoginPresenter.View {
    RelativeLayout btnActivateAccount;
    RelativeLayout btnForgetPassword;
    LinearLayout btnSignIn;
    EditText etPassword;
    EditText etUsername;
    LinearLayout inputContainer;
    LoginPresenter presenter;
    ProgressBar progress;
    TextInputLayout tilPassword;
    TextInputLayout tilUsername;

    public static SignInFragment createInstance() {
        return new SignInFragment();
    }

    private void init(View view) {
        this.inputContainer = (LinearLayout) view.findViewById(R.id.input_container);
        this.tilUsername = (TextInputLayout) view.findViewById(R.id.til_username);
        this.etUsername = (EditText) view.findViewById(R.id.et_username);
        this.tilPassword = (TextInputLayout) view.findViewById(R.id.til_password);
        this.etPassword = (EditText) view.findViewById(R.id.et_password);
        this.btnSignIn = (LinearLayout) view.findViewById(R.id.btn_sign_in);
        this.btnSignIn.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignInFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SignInFragment.this.onClickSignIn();
            }
        });
        this.progress = (ProgressBar) view.findViewById(R.id.progress);
        this.btnActivateAccount = (RelativeLayout) view.findViewById(R.id.btn_activate_account);
        this.btnActivateAccount.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignInFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SignInFragment.this.onClickActivateAccount();
            }
        });
        this.btnForgetPassword = (RelativeLayout) view.findViewById(R.id.btn_forget_password_relative);
        this.btnForgetPassword.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignInFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SignInFragment.this.onClickForgetPassword();
            }
        });
        this.presenter = new LoginPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.etUsername.setTypeface(Typeface.DEFAULT);
        this.etPassword.setTypeface(Typeface.DEFAULT);
        this.btnSignIn.setVisibility(0);
        this.progress.setVisibility(8);
        if (MainApp.userAccount != null && MainApp.userAccount.getUsername() != null && MainApp.userAccount.getPassword() != null) {
            this.etUsername.setText(MainApp.userAccount.getUsername());
            this.etPassword.setText(MainApp.userAccount.getPassword());
        }
        this.etUsername.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignInFragment.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignInFragment.this.tilUsername.setErrorEnabled(false);
                SignInFragment.this.tilUsername.setError("");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.etPassword.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignInFragment.5
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignInFragment.this.tilPassword.setErrorEnabled(false);
                SignInFragment.this.tilPassword.setError("");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
        this.btnSignIn.setVisibility(0);
        this.progress.setVisibility(8);
    }

    public void onClickActivateAccount() {
        new ActivateAccountDialog(getActivity()).show();
    }

    public void onClickForgetPassword() {
        new ForgotPasswordDialog(getActivity()).show();
    }

    public void onClickSignIn() {
        Boolean bool = true;
        if (this.etUsername.getText().length() == 0) {
            bool = false;
            this.tilUsername.setErrorEnabled(true);
            this.tilUsername.setError(getString(R.string.error_input_empty));
        }
        if (this.etPassword.getText().length() == 0) {
            bool = false;
            this.tilPassword.setErrorEnabled(true);
            this.tilPassword.setError(getString(R.string.error_input_empty));
        }
        if (bool.booleanValue()) {
            PermissionRequest.requestPermission("android.permission.GET_ACCOUNTS", getString(R.string.get_account_permission_name), getActivity()).withListener(new PermissionRequestListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignInFragment.6
                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionAcept(String str) {
                    SignInFragment.this.presenter.checkUserAccount(SignInFragment.this.etUsername.getText().toString(), SignInFragment.this.etPassword.getText().toString());
                }

                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionDeny(String str) {
                }

                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionDenyPermanent(String str) {
                }
            }).check();
        }
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_account_signin, viewGroup, false);
        init(inflate);
        return inflate;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.LoginPresenter.View
    public void onUserAccountChecked(Boolean bool) {
        if (!bool.booleanValue()) {
            YoYo.with(Techniques.Shake).duration(700L).playOn(this.inputContainer);
            showError(getString(R.string.error_autentication_title));
            return;
        }
        try {
            getActivity().onBackPressed();
        } catch (NullPointerException e) {
            MainApp.log(getClass().getName(), e);
            e.printStackTrace();
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        MainApp.showToast(str, false);
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
        this.btnSignIn.setVisibility(4);
        this.progress.setVisibility(0);
    }
}
