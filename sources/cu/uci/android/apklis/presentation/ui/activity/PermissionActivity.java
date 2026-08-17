package cu.uci.android.apklis.presentation.ui.activity;

import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PermissionActivity extends AppCompatActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_permission);
        PackageManager packageManager = getPackageManager();
        ArrayList arrayList = new ArrayList();
        for (PermissionGroupInfo permissionGroupInfo : packageManager.getAllPermissionGroups(128)) {
            Log.e("perm", permissionGroupInfo.name + ": " + permissionGroupInfo.loadLabel(packageManager).toString());
            try {
                for (PermissionInfo permissionInfo : packageManager.queryPermissionsByGroup(permissionGroupInfo.name, 128)) {
                    CharSequence loadLabel = permissionInfo.loadLabel(packageManager);
                    Log.e("perm", "   " + permissionInfo.name + ": " + loadLabel.toString() + ": " + ((Object) permissionInfo.loadDescription(packageManager)));
                    permissionInfo.loadIcon(packageManager);
                    arrayList.add(new Permission(permissionGroupInfo.loadIcon(packageManager), permissionInfo.name, loadLabel.toString(), permissionInfo.loadDescription(packageManager).toString()));
                }
            } catch (Exception e) {
                MainApp.log(getClass().getName(), e);
                MainApp.log("AAAAA", e);
            }
        }
        ((ListView) findViewById(R.id.list_view)).setAdapter((ListAdapter) new PermissionAdapter(this, arrayList));
    }
}
