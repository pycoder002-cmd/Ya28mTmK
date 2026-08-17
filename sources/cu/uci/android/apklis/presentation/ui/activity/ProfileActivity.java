package cu.uci.android.apklis.presentation.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.permissionrequest.PermissionRequest;
import com.example.permissionrequest.PermissionRequestListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;
import com.startapp.sdk.adsbase.StartAppAd;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.domain.interactor.impl.UpImagePresenterImpl;
import cu.uci.android.apklis.presentation.presenter.ChangePasswordPresenter;
import cu.uci.android.apklis.presentation.presenter.CloseSessionPresenter;
import cu.uci.android.apklis.presentation.presenter.SaveProfilePresenter;
import cu.uci.android.apklis.presentation.presenter.UpImageProfilePresenter;
import cu.uci.android.apklis.presentation.presenter.impl.ChangePasswordPresenterImpl;
import cu.uci.android.apklis.presentation.presenter.impl.CloseSessionPresenterImpl;
import cu.uci.android.apklis.presentation.presenter.impl.SaveProfilePresenterImpl;
import cu.uci.android.apklis.threading.MainThreadImpl;

/* loaded from: classes.dex */
public class ProfileActivity extends AppCompatActivity implements CloseSessionPresenter.View, SaveProfilePresenter.View, ChangePasswordPresenter.View, UpImageProfilePresenter.View {
    private static final int PICK_IMAGE = 100;
    public static String lastName = "lastName";
    public static String name = "name";
    public static String password = "password";
    Button btnCloseSesion;
    Button btnSavePaswd;
    Button btnSaveProfile;
    CircularImageView edit_user_avatar;
    EditText etProfileLastName;
    EditText etProfileName;
    EditText etProfilePasswordNew;
    EditText etProfilePasswordNewAgain;
    EditText etProfilePasswordOld;
    Uri imageUri;
    private String path3;
    CloseSessionPresenter presenter;
    ChangePasswordPresenter presenterSavePasswd;
    SaveProfilePresenter presenterSaveProfile;
    UpImageProfilePresenter presenterUpImageProfile;
    ProgressDialog progressDialog;

    /* JADX INFO: Access modifiers changed from: private */
    public void changeImageProfile() {
        PermissionRequest.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.get_account_permission_name), this).withListener(new PermissionRequestListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.ProfileActivity.5
            @Override // com.example.permissionrequest.PermissionRequestListener
            public void onPermissionAcept(String str) {
                ProfileActivity.this.startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI), 100);
            }

            @Override // com.example.permissionrequest.PermissionRequestListener
            public void onPermissionDeny(String str) {
            }

            @Override // com.example.permissionrequest.PermissionRequestListener
            public void onPermissionDenyPermanent(String str) {
            }
        }).check();
    }

    private String getpathring(Uri uri) {
        Cursor query = getContentResolver().query(uri, null, null, null, null);
        if (query == null) {
            String path = uri.getPath();
            this.path3 = path.substring(path.lastIndexOf("/") + 1);
            return path;
        }
        query.moveToFirst();
        String string = query.getString(query.getColumnIndex("_data"));
        query.close();
        this.path3 = string.substring(string.lastIndexOf("/") + 1);
        return string;
    }

    private void loadAvatar() {
        String avatar = MainApp.userAccount.getAvatar();
        if (avatar == null) {
            Picasso.get().load(R.drawable.ic_user_account).into(this.edit_user_avatar);
        } else {
            Picasso.get().load(Uri.parse(avatar)).into(this.edit_user_avatar);
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.CloseSessionPresenter.View
    public void goClose() {
        MainApp.userAccount = null;
        finish();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.CloseSessionPresenter.View
    public void goKeep() {
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 100) {
            this.imageUri = intent.getData();
            this.path3 = getpathring(this.imageUri);
            this.presenterUpImageProfile.saveImage(MainApp.userAccount.getUsername(), this.path3);
            this.edit_user_avatar.setImageURI(this.imageUri);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_profile);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar_profile));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.profile);
        Bundle extras = getIntent().getExtras();
        String string = extras.getString(name, "");
        String string2 = extras.getString(lastName, "");
        extras.getString(password, "");
        this.presenter = new CloseSessionPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.presenterSaveProfile = new SaveProfilePresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.presenterSavePasswd = new ChangePasswordPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.presenterUpImageProfile = new UpImagePresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.progressDialog = new ProgressDialog(this);
        this.etProfileName = (EditText) findViewById(R.id.etProfileName);
        this.etProfileLastName = (EditText) findViewById(R.id.etProfileLastName);
        this.etProfilePasswordOld = (EditText) findViewById(R.id.etProfilePasswordOld);
        this.etProfilePasswordNew = (EditText) findViewById(R.id.etProfilePasswordNew);
        this.etProfilePasswordNewAgain = (EditText) findViewById(R.id.etProfilePasswordNewAgain);
        this.edit_user_avatar = (CircularImageView) findViewById(R.id.edit_user_avatar);
        this.btnCloseSesion = (Button) findViewById(R.id.btnCloseSesion);
        this.btnCloseSesion.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.ProfileActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfileActivity.this.presenter.closeSession();
            }
        });
        this.btnSavePaswd = (Button) findViewById(R.id.btnSavePaswd);
        this.btnSavePaswd.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.ProfileActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ProfileActivity.this.etProfilePasswordOld.getText().toString().equals("") || ProfileActivity.this.etProfilePasswordNew.toString().equals("") || ProfileActivity.this.etProfilePasswordNewAgain.toString().equals("") || !ProfileActivity.this.etProfilePasswordNewAgain.getText().toString().equals(ProfileActivity.this.etProfilePasswordNew.getText().toString())) {
                    Toast.makeText(ProfileActivity.this, R.string.error_change_pass, 1).show();
                } else {
                    ProfileActivity.this.presenterSavePasswd.changePassword(ProfileActivity.this.etProfilePasswordOld.getText().toString(), ProfileActivity.this.etProfilePasswordNew.getText().toString());
                }
            }
        });
        this.btnSaveProfile = (Button) findViewById(R.id.btnSaveProfile);
        this.btnSaveProfile.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.ProfileActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfileActivity.this.progressDialog.setMessage(ProfileActivity.this.getString(R.string.loading));
                ProfileActivity.this.progressDialog.setTitle(R.string.modProfile);
                ProfileActivity.this.progressDialog.show();
                ProfileActivity.this.presenterSaveProfile.saveProfile(MainApp.userAccount.getUsername(), ProfileActivity.this.etProfileName.getText().toString(), ProfileActivity.this.etProfileLastName.getText().toString());
            }
        });
        this.etProfileName.setText(string);
        this.etProfileLastName.setText(string2);
        loadAvatar();
        this.edit_user_avatar.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.ProfileActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ProfileActivity.this.changeImageProfile();
            }
        });
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
        return true;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.ChangePasswordPresenter.View
    public void passwordChanged() {
        Log.v("Changed", "UserChanged");
        this.progressDialog.hide();
        Toast.makeText(this, R.string.passwChanged, 1).show();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.SaveProfilePresenter.View
    public void profileChanged() {
        Log.v("Changed", "UserChanged");
        this.progressDialog.hide();
        Toast.makeText(this, R.string.profileChanged, 1).show();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpImageProfilePresenter.View
    public void saveImage() {
        this.progressDialog.hide();
        Log.v("Changed", "AvatarChanged");
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
        this.progressDialog.hide();
        Toast.makeText(this, R.string.error_server_conection, 1).show();
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
    }
}
