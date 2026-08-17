package cu.uci.android.apklis.presentation.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import com.nononsenseapps.filepicker.AbstractFilePickerActivity;
import com.nononsenseapps.filepicker.FilePickerActivity;
import com.nononsenseapps.filepicker.Utils;
import com.startapp.sdk.adsbase.StartAppAd;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.device.DeviceInfo;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/* loaded from: classes.dex */
public class SettingActivity extends AppCompatActivity {
    int FILE_CODE = 1204;
    int REQCODE_ACCESSIBILI_SETT = 2048;
    Switch swAutoDelete;
    Switch swAutoInstall;
    Switch swIgnoreInstall;
    Switch swIgnoreSDK;
    Switch swNotfUpdate;
    TextView tvDownlPath;
    TextView tvDownlPathDesc;

    /* JADX INFO: Access modifiers changed from: private */
    public void pickPath() {
        Intent intent = new Intent(this, (Class<?>) FilePickerActivity.class);
        intent.putExtra(AbstractFilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
        intent.putExtra(AbstractFilePickerActivity.EXTRA_ALLOW_CREATE_DIR, true);
        intent.putExtra(AbstractFilePickerActivity.EXTRA_MODE, 1);
        intent.putExtra(AbstractFilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
        startActivityForResult(intent, this.FILE_CODE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == this.FILE_CODE && i2 == -1) {
            Iterator<Uri> it = Utils.getSelectedFilesFromResult(intent).iterator();
            while (it.hasNext()) {
                File fileForUri = Utils.getFileForUri(it.next());
                this.tvDownlPathDesc.setText(fileForUri.getAbsolutePath());
                SharedPreferencesManager.getInstance().putValue(MainApp.DOWNLOAD_DIRECTORY, fileForUri.getAbsolutePath());
            }
        }
        if (i == this.REQCODE_ACCESSIBILI_SETT) {
            if (DeviceInfo.isAccessibilitySettingsOn(this)) {
                this.swAutoInstall.setChecked(true);
                SharedPreferencesManager.getInstance().putValue(MainApp.ENABLE_AUTOINSTALL, true);
            } else {
                this.swAutoInstall.setChecked(false);
                SharedPreferencesManager.getInstance().putValue(MainApp.ENABLE_AUTOINSTALL, false);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_setting);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbarIA));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.tvDownlPath = (TextView) findViewById(R.id.tvDownlPath);
        this.tvDownlPathDesc = (TextView) findViewById(R.id.tvDownlPathDesc);
        ((LinearLayout) findViewById(R.id.llPickD)).setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.SettingActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SettingActivity.this.pickPath();
            }
        });
        this.tvDownlPathDesc.setText((String) SharedPreferencesManager.getInstance().getValue(MainApp.DOWNLOAD_DIRECTORY, String.class, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + ""));
        this.swAutoInstall = (Switch) findViewById(R.id.swAutoInstall);
        if (DeviceInfo.isAccessibilitySettingsOn(this)) {
            this.swAutoInstall.setChecked(true);
            SharedPreferencesManager.getInstance().putValue(MainApp.ENABLE_AUTOINSTALL, true);
        } else {
            this.swAutoInstall.setChecked(false);
            SharedPreferencesManager.getInstance().putValue(MainApp.ENABLE_AUTOINSTALL, false);
        }
        this.swAutoInstall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.SettingActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z && DeviceInfo.isRooted()) {
                    try {
                        Runtime.getRuntime().exec("su");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                SettingActivity.this.startActivityForResult(new Intent("android.settings.ACCESSIBILITY_SETTINGS"), SettingActivity.this.REQCODE_ACCESSIBILI_SETT);
            }
        });
        this.swAutoDelete = (Switch) findViewById(R.id.swAutoDelete);
        this.swAutoDelete.setChecked(((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.ENABLE_AUTODELETE_APK, Boolean.class, false)).booleanValue());
        this.swAutoDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.SettingActivity.3
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SharedPreferencesManager.getInstance().putValue(MainApp.ENABLE_AUTODELETE_APK, true);
                } else {
                    SharedPreferencesManager.getInstance().putValue(MainApp.ENABLE_AUTODELETE_APK, false);
                }
            }
        });
        this.swNotfUpdate = (Switch) findViewById(R.id.swNotfUpdate);
        this.swNotfUpdate.setChecked(((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.ENABLE_NOFT_UPDATE, Boolean.class, true)).booleanValue());
        this.swNotfUpdate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.SettingActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SharedPreferencesManager.getInstance().putValue(MainApp.ENABLE_NOFT_UPDATE, true);
                } else {
                    SharedPreferencesManager.getInstance().putValue(MainApp.ENABLE_NOFT_UPDATE, false);
                }
            }
        });
        this.swIgnoreSDK = (Switch) findViewById(R.id.swIgnoreSDK);
        this.swIgnoreSDK.setChecked(((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.IGNORE_MY_SDK, Boolean.class, false)).booleanValue());
        this.swIgnoreSDK.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.SettingActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SharedPreferencesManager.getInstance().putValue(MainApp.IGNORE_MY_SDK, true);
                } else {
                    SharedPreferencesManager.getInstance().putValue(MainApp.IGNORE_MY_SDK, false);
                }
            }
        });
        this.swIgnoreInstall = (Switch) findViewById(R.id.swIgnoreInstall);
        this.swIgnoreInstall.setChecked(!((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.IGNORE_INSTALL, Boolean.class, true)).booleanValue());
        this.swIgnoreInstall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.SettingActivity.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    SharedPreferencesManager.getInstance().putValue(MainApp.IGNORE_INSTALL, false);
                } else {
                    SharedPreferencesManager.getInstance().putValue(MainApp.IGNORE_INSTALL, true);
                }
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
}
