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
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.domain.model.UserAccount;
import cu.uci.android.apklis.presentation.presenter.SignUpPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.SignUpPresenterImpl;
import cu.uci.android.apklis.presentation.ui.activity.AccountActivity;
import cu.uci.android.apklis.presentation.ui.dialog.ActivateAccountDialog;
import cu.uci.android.apklis.storage.repository.UserRepositoryImpl;
import cu.uci.android.apklis.threading.MainThreadImpl;

/* loaded from: classes.dex */
public class SignUpFragment extends Fragment implements SignUpPresenter.View {
    LinearLayout btnSignUp;
    EditText etFirstName;
    EditText etLastName;
    EditText etPassword;
    EditText etPhoneNumber;
    EditText etRepeatPassword;
    EditText etUsername;
    LinearLayout inputContainer;
    SignUpPresenter presenter;
    ProgressBar progress;
    TextInputLayout tilFirstName;
    TextInputLayout tilLastName;
    TextInputLayout tilPassword;
    TextInputLayout tilPhoneNumber;
    TextInputLayout tilRepeatPassword;
    TextInputLayout tilUsername;

    private void clearFields() {
        this.etUsername.setText("");
        this.etFirstName.setText("");
        this.etLastName.setText("");
        this.etPhoneNumber.setText("");
        this.etPassword.setText("");
        this.etRepeatPassword.setText("");
    }

    public static SignUpFragment createInstance() {
        return new SignUpFragment();
    }

    private void init(View view) {
        this.inputContainer = (LinearLayout) view.findViewById(R.id.input_container);
        this.tilUsername = (TextInputLayout) view.findViewById(R.id.til_username);
        this.etUsername = (EditText) view.findViewById(R.id.et_username);
        this.tilFirstName = (TextInputLayout) view.findViewById(R.id.til_first_name);
        this.etFirstName = (EditText) view.findViewById(R.id.et_first_name);
        this.tilLastName = (TextInputLayout) view.findViewById(R.id.til_last_name);
        this.etLastName = (EditText) view.findViewById(R.id.et_last_name);
        this.tilPhoneNumber = (TextInputLayout) view.findViewById(R.id.til_phone_number);
        this.etPhoneNumber = (EditText) view.findViewById(R.id.et_phone_number);
        this.tilPassword = (TextInputLayout) view.findViewById(R.id.til_password);
        this.etPassword = (EditText) view.findViewById(R.id.et_password);
        this.tilRepeatPassword = (TextInputLayout) view.findViewById(R.id.til_repeat_password);
        this.etRepeatPassword = (EditText) view.findViewById(R.id.et_repeat_password);
        this.btnSignUp = (LinearLayout) view.findViewById(R.id.btn_sign_up);
        this.btnSignUp.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignUpFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SignUpFragment.this.onClickLogin();
            }
        });
        this.progress = (ProgressBar) view.findViewById(R.id.progress);
        this.presenter = new SignUpPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, new UserRepositoryImpl());
        this.btnSignUp.setVisibility(0);
        this.progress.setVisibility(8);
        this.etUsername.setTypeface(Typeface.DEFAULT);
        this.etFirstName.setTypeface(Typeface.DEFAULT);
        this.etLastName.setTypeface(Typeface.DEFAULT);
        this.etPhoneNumber.setTypeface(Typeface.DEFAULT);
        this.etPassword.setTypeface(Typeface.DEFAULT);
        this.etRepeatPassword.setTypeface(Typeface.DEFAULT);
        this.etUsername.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignUpFragment.2
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpFragment.this.tilUsername.setErrorEnabled(false);
                SignUpFragment.this.tilUsername.setError("");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.etFirstName.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignUpFragment.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpFragment.this.tilFirstName.setErrorEnabled(false);
                SignUpFragment.this.tilFirstName.setError("");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.etLastName.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignUpFragment.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpFragment.this.tilLastName.setErrorEnabled(false);
                SignUpFragment.this.tilLastName.setError("");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.etPhoneNumber.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignUpFragment.5
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpFragment.this.tilPhoneNumber.setErrorEnabled(false);
                SignUpFragment.this.tilPhoneNumber.setError("");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.etPassword.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignUpFragment.6
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpFragment.this.tilPassword.setErrorEnabled(false);
                SignUpFragment.this.tilPassword.setError("");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.etRepeatPassword.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.fragment.SignUpFragment.7
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                SignUpFragment.this.tilRepeatPassword.setErrorEnabled(false);
                SignUpFragment.this.tilRepeatPassword.setError("");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
        this.btnSignUp.setVisibility(0);
        this.progress.setVisibility(8);
    }

    public void onClickLogin() {
        Boolean bool = true;
        if (this.etUsername.getText().length() == 0) {
            bool = false;
            this.tilUsername.setErrorEnabled(true);
            this.tilUsername.setError(getString(R.string.error_input_empty));
        }
        if (this.etFirstName.getText().length() == 0) {
            bool = false;
            this.tilFirstName.setErrorEnabled(true);
            this.tilFirstName.setError(getString(R.string.error_input_empty));
        }
        if (this.etLastName.getText().length() == 0) {
            bool = false;
            this.tilLastName.setErrorEnabled(true);
            this.tilLastName.setError(getString(R.string.error_input_empty));
        }
        if (this.etPhoneNumber.getText().length() == 0) {
            bool = false;
            this.tilPhoneNumber.setErrorEnabled(true);
            this.tilPhoneNumber.setError(getString(R.string.error_input_empty));
        } else if (this.etPhoneNumber.getText().length() < 8 || this.etPhoneNumber.getText().length() > 11) {
            bool = false;
            this.tilPhoneNumber.setErrorEnabled(true);
            this.tilPhoneNumber.setError(getString(R.string.error_phone_number));
        }
        if (this.etPassword.getText().length() == 0) {
            bool = false;
            this.tilPassword.setErrorEnabled(true);
            this.tilPassword.setError(getString(R.string.error_input_empty));
        }
        if (this.etRepeatPassword.getText().length() == 0) {
            bool = false;
            this.tilRepeatPassword.setErrorEnabled(true);
            this.tilRepeatPassword.setError(getString(R.string.error_input_empty));
        } else if (!this.etRepeatPassword.getText().toString().equals(this.etPassword.getText().toString())) {
            bool = false;
            this.tilRepeatPassword.setErrorEnabled(true);
            this.tilRepeatPassword.setError(getString(R.string.error_repeat_password));
        }
        if (bool.booleanValue()) {
            this.presenter.signUp(this.etUsername.getText().toString(), this.etFirstName.getText().toString(), this.etLastName.getText().toString(), this.etPhoneNumber.getText().toString(), this.etPassword.getText().toString());
        }
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_account_signup, viewGroup, false);
        init(inflate);
        return inflate;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.SignUpPresenter.View
    public void onServerError() {
        if (isAdded()) {
            showError(getString(R.string.error_server_conection));
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.SignUpPresenter.View
    public void onUserDataError(UserAccount userAccount) {
        if (userAccount.getUsername() != null && userAccount.getUsername().length() > 0) {
            this.tilUsername.setErrorEnabled(true);
            this.tilUsername.setError(userAccount.getUsername());
        }
        if (userAccount.getPhoneNumber() != null && userAccount.getPhoneNumber().length() > 0) {
            this.tilPhoneNumber.setErrorEnabled(true);
            this.tilPhoneNumber.setError(userAccount.getPhoneNumber());
        }
        if (userAccount.getPassword() == null || userAccount.getPassword().length() <= 0) {
            return;
        }
        this.tilPassword.setErrorEnabled(true);
        this.tilPassword.setError(userAccount.getPassword());
    }

    @Override // cu.uci.android.apklis.presentation.presenter.SignUpPresenter.View
    public void onUserRegistered(UserAccount userAccount, boolean z) {
        if (z) {
            ((AccountActivity) getActivity()).goToSignIn();
            showError(getString(R.string.user_register_successful));
        } else {
            clearFields();
            new ActivateAccountDialog(getActivity()).show();
            showError("Proceda a activar su cuenta");
        }
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        MainApp.showToast(str, false);
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
        this.btnSignUp.setVisibility(4);
        this.progress.setVisibility(0);
    }
}
