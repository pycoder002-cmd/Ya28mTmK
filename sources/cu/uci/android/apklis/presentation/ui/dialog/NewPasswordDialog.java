package cu.uci.android.apklis.presentation.ui.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.gson.JsonObject;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.presentation.presenter.NewPasswordPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.NewPasswordPresenterImpl;
import cu.uci.android.apklis.presentation.ui.activity.AccountActivity;
import cu.uci.android.apklis.storage.repository.UserRepositoryImpl;
import cu.uci.android.apklis.threading.MainThreadImpl;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class NewPasswordDialog extends AppCompatDialog implements NewPasswordPresenter.View {
    AccountActivity activity;
    ImageView btnCancel;
    LinearLayout btnForgotPassword;
    Bundle bundle;
    private String entity3;
    EditText et_activation_code;
    EditText et_activation_password;
    EditText et_activation_repeat_password;
    private JsonObject jsonObject;
    private JSONObject jsonjObject;
    private SharedPreferences pref;
    NewPasswordPresenter presenter;
    ProgressBar progress;
    TextInputLayout til_activation_code_new_pass;
    TextInputLayout til_activation_password_new_pass;
    TextInputLayout til_activation_repeat_new_pass;

    public NewPasswordDialog(Context context) {
        super(context);
    }

    public NewPasswordDialog(Context context, int i) {
        super(context, i);
    }

    protected NewPasswordDialog(Context context, boolean z, DialogInterface.OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    private void init() {
        this.presenter = new NewPasswordPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this, new UserRepositoryImpl()) { // from class: cu.uci.android.apklis.presentation.ui.dialog.NewPasswordDialog.3
        };
        this.btnForgotPassword.setVisibility(0);
        this.progress.setVisibility(8);
        this.et_activation_code.setTypeface(Typeface.DEFAULT);
        this.et_activation_code.addTextChangedListener(new TextWatcher() { // from class: cu.uci.android.apklis.presentation.ui.dialog.NewPasswordDialog.4
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                NewPasswordDialog.this.til_activation_code_new_pass.setErrorEnabled(false);
                NewPasswordDialog.this.til_activation_code_new_pass.setError("");
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
        String string = this.pref.getString("token", null);
        if (string != null) {
            try {
                this.jsonjObject = new JSONObject(string);
                this.entity3 = this.jsonjObject.getString("token");
                Log.e("token: ", this.entity3);
            } catch (JSONException e) {
                MainApp.log(getClass().getName(), e);
                e.printStackTrace();
            }
        }
        if (this.entity3.length() == 0 || this.et_activation_code.getText().length() == 0 || this.et_activation_password.getText().length() == 0) {
            bool = false;
            this.til_activation_code_new_pass.setErrorEnabled(true);
            this.til_activation_password_new_pass.setErrorEnabled(true);
            this.til_activation_repeat_new_pass.setErrorEnabled(true);
            this.til_activation_code_new_pass.setError(getContext().getString(R.string.error_input_empty));
            this.til_activation_password_new_pass.setError(getContext().getString(R.string.error_input_empty));
            this.til_activation_repeat_new_pass.setError(getContext().getString(R.string.error_input_empty));
        }
        String valueOf = String.valueOf(this.til_activation_code_new_pass.getEditText().getText());
        String valueOf2 = String.valueOf(this.til_activation_password_new_pass.getEditText().getText());
        String valueOf3 = String.valueOf(this.til_activation_repeat_new_pass.getEditText().getText());
        Log.e("onClickNewPassword: ", this.entity3 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + valueOf2 + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + valueOf3);
        if (bool.booleanValue()) {
            if (valueOf2.equals(valueOf3)) {
                this.presenter.change_password_whit_tk(this.entity3, valueOf, valueOf2);
            } else {
                wrong_pass();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(R.layout.fragment_account_new_password);
        this.bundle = new Bundle();
        this.btnCancel = (ImageView) findViewById(R.id.btn_cancel);
        this.pref = getContext().getSharedPreferences("shared", 0);
        this.pref.edit();
        this.btnCancel.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.dialog.NewPasswordDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewPasswordDialog.this.onClickCancel();
            }
        });
        this.til_activation_code_new_pass = (TextInputLayout) findViewById(R.id.til_activation_code_new_pass);
        this.til_activation_password_new_pass = (TextInputLayout) findViewById(R.id.til_activation_password_new_pass);
        this.til_activation_repeat_new_pass = (TextInputLayout) findViewById(R.id.til_activation_repeat_new_pass);
        this.et_activation_code = (EditText) findViewById(R.id.et_activation_code_new_pass);
        this.et_activation_password = (EditText) findViewById(R.id.et_activation_password_new_pass);
        this.et_activation_repeat_password = (EditText) findViewById(R.id.et_activation_repeat_new_pass);
        this.btnForgotPassword = (LinearLayout) findViewById(R.id.btn_forgot_password);
        this.btnForgotPassword.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.dialog.NewPasswordDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NewPasswordDialog.this.onClickNewPassword();
            }
        });
        this.progress = (ProgressBar) findViewById(R.id.progress);
        init();
        setCancelable(true);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.NewPasswordPresenter.View
    public void onServerError() {
        showError(MainApp.context.getString(R.string.error_server_conection));
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

    @Override // cu.uci.android.apklis.presentation.presenter.NewPasswordPresenter.View
    public void wrong_pass() {
        showError(MainApp.context.getString(R.string.error_repeat_password));
    }
}
