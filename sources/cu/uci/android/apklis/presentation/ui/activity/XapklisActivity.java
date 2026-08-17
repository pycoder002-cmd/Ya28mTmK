package cu.uci.android.apklis.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeSuccessDialog;
import com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure;
import com.example.permissionrequest.PermissionRequest;
import com.example.permissionrequest.PermissionRequestListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.domain.model.AndroidVersion;
import cu.uci.android.apklis.domain.model.FileTask;
import cu.uci.android.apklis.domain.model.XapkFile;
import cu.uci.android.apklis.presentation.ui.adapter.ApklisAdapter;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.ResourceUtils;

/* loaded from: classes.dex */
public class XapklisActivity extends AppCompatActivity {
    private static final long SIZE_GB = 1073741824;
    private static final long SIZE_KB = 1024;
    private static final long SIZE_MB = 1048576;
    private ApklisAdapter adapter;
    private ArrayList arrayList;
    private ArrayList<String> arrayList1;
    Context context;
    private SharedPreferences.Editor editor;
    ListView listView;
    private SharedPreferences prefs;
    private File root;
    ArrayList<ZipFile> files = new ArrayList<>();
    ArrayList<File> files_item = new ArrayList<>();
    private ArrayList<File> fileList = new ArrayList<>();

    private boolean checkPermissions() {
        int checkSelfPermission = ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        ArrayList arrayList = new ArrayList();
        if (checkSelfPermission != 0) {
            arrayList.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
        if (arrayList.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) arrayList.toArray(new String[arrayList.size()]), 5217);
        return false;
    }

    private boolean deleteDirectory(File file) {
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                return true;
            }
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    deleteDirectory(listFiles[i]);
                } else {
                    listFiles[i].delete();
                }
            }
        }
        return file.delete();
    }

    @RequiresApi(api = 18)
    private static long getExternalAvailableSpaceInBytes(int i) {
        long availableBlocks;
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            if (i >= 19) {
                availableBlocks = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            } else {
                availableBlocks = statFs.getAvailableBlocks() * statFs.getBlockSize();
            }
            return availableBlocks;
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveData(long j, ArrayList<XapkFile> arrayList, InputStream inputStream) {
        new FileTask(this.context, j, inputStream).execute(arrayList);
    }

    public ArrayList<String> getArrayList(String str) {
        return (ArrayList) new Gson().fromJson(PreferenceManager.getDefaultSharedPreferences(this.context).getString(str, null), new TypeToken<ArrayList<String>>() { // from class: cu.uci.android.apklis.presentation.ui.activity.XapklisActivity.4
        }.getType());
    }

    public ArrayList<File> getfile(File file) {
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    getfile(listFiles[i]);
                } else if (listFiles[i].getName().endsWith(".apklis") || listFiles[i].getName().endsWith(".APKLIS")) {
                    this.fileList.add(listFiles[i]);
                }
            }
        }
        return this.fileList;
    }

    public void listOn() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath());
        for (int i = 0; i < arrayList.size(); i++) {
            this.root = new File((String) arrayList.get(i));
            this.fileList = getfile(this.root);
        }
        if (this.fileList.size() == 0) {
            new AwesomeSuccessDialog(this).setTitle(R.string.not_conexion).setMessage(R.string.not_information_apklis).setColoredCircle(R.color.dialogWarningBackgroundColor).setDialogIconAndColor(R.drawable.ic_dialog_warning, R.color.white).setCancelable(false).setPositiveButtonText(getString(R.string.dialog_ok_button)).setPositiveButtonbackgroundColor(R.color.dialogWarningBackgroundColor).setPositiveButtonTextColor(R.color.white).setNegativeButtonbackgroundColor(R.color.dialogWarningBackgroundColor).setNegativeButtonTextColor(R.color.white).setPositiveButtonClick(new Closure() { // from class: cu.uci.android.apklis.presentation.ui.activity.XapklisActivity.3
                @Override // com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure
                public void exec() {
                    XapklisActivity.this.finish();
                }
            }).show();
        }
        for (int i2 = 0; i2 < this.fileList.size(); i2++) {
            try {
                File absoluteFile = this.fileList.get(i2).getAbsoluteFile();
                this.files.add(new ZipFile(absoluteFile));
                this.files_item.add(absoluteFile);
                this.adapter = new ApklisAdapter(this, this.files);
                this.listView.setAdapter((ListAdapter) this.adapter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("shared", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (i == 1245) {
            if (i2 == 0) {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/xapx_install", "base.apk");
                this.arrayList1 = getArrayList("list_preference");
                for (int i3 = 0; i3 < this.arrayList.size(); i3++) {
                    deleteDirectory(new File(this.arrayList.get(i3).toString()).getParentFile());
                }
                deleteDirectory(new File(file.getAbsoluteFile().toString()).getParentFile());
            }
            if (i2 == -1) {
                deleteDirectory(new File(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/xapx_install", "base.apk").getAbsoluteFile().toString()).getParentFile());
                if (sharedPreferences.getString(ResourceUtils.URL_PROTOCOL_FILE, null).isEmpty()) {
                    return;
                }
                edit.putString(ResourceUtils.URL_PROTOCOL_FILE, "");
                edit.apply();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_xapklis);
        this.context = this;
        PermissionRequest.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.get_access_write), this.context).withListener(new PermissionRequestListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.XapklisActivity.1
            @Override // com.example.permissionrequest.PermissionRequestListener
            @RequiresApi(api = 18)
            public void onPermissionAcept(String str) {
                SharedPreferences sharedPreferences = XapklisActivity.this.context.getSharedPreferences("shared", 0);
                XapklisActivity.this.editor = sharedPreferences.edit();
                XapklisActivity.this.arrayList = new ArrayList();
                String string = sharedPreferences.getString(ResourceUtils.URL_PROTOCOL_FILE, null);
                XapklisActivity.this.editor.putString(ResourceUtils.URL_PROTOCOL_FILE, "");
                XapklisActivity.this.editor.apply();
                try {
                    if (!string.isEmpty()) {
                        XapklisActivity.this.startWork(string);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                XapklisActivity.this.startWork();
            }

            @Override // com.example.permissionrequest.PermissionRequestListener
            public void onPermissionDeny(String str) {
            }

            @Override // com.example.permissionrequest.PermissionRequestListener
            public void onPermissionDenyPermanent(String str) {
            }
        }).check();
    }

    public void startWork() {
        this.listView = (ListView) findViewById(R.id.xapklis_list);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: cu.uci.android.apklis.presentation.ui.activity.XapklisActivity.2
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long j) {
                final JSONObject jSONObject = (JSONObject) adapterView.getItemAtPosition(i);
                try {
                    new MaterialDialog.Builder(XapklisActivity.this.context).title(jSONObject.getString("name")).content(Html.fromHtml("<b>Package Name: </b>" + jSONObject.get("package_name") + "<br/><b>Android Requerido: </b>" + AndroidVersion.version(jSONObject.getString("min_sdk_version")) + "<br/><b>Android Objetivo: </b>" + AndroidVersion.version(jSONObject.getString("target_sdk_version")) + "<br/>")).positiveText("Instalar").negativeText("Eliminar").onPositive(new MaterialDialog.SingleButtonCallback() { // from class: cu.uci.android.apklis.presentation.ui.activity.XapklisActivity.2.2
                        @Override // com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback
                        public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                            try {
                                JSONArray jSONArray = jSONObject.getJSONArray("expansions");
                                long j2 = 0;
                                ArrayList arrayList = new ArrayList();
                                ZipFile zipFile = XapklisActivity.this.files.get(i);
                                int i2 = 0;
                                while (i2 < jSONArray.length()) {
                                    ZipEntry entry = zipFile.getEntry(jSONArray.getJSONObject(i2).getString(ResourceUtils.URL_PROTOCOL_FILE));
                                    InputStream inputStream = zipFile.getInputStream(entry);
                                    long size = j2 + entry.getSize();
                                    arrayList.add(new XapkFile(jSONArray.getJSONObject(i2).getString("install_location"), inputStream, jSONArray.getJSONObject(i2).getString("install_path")));
                                    i2++;
                                    j2 = size;
                                }
                                ZipEntry entry2 = zipFile.getEntry(jSONObject.getString("package_name") + ".apk");
                                XapklisActivity.this.saveData(j2 + entry2.getSize(), arrayList, zipFile.getInputStream(entry2));
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }).onNegative(new MaterialDialog.SingleButtonCallback() { // from class: cu.uci.android.apklis.presentation.ui.activity.XapklisActivity.2.1
                        @Override // com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback
                        public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                            if (XapklisActivity.this.files_item.get(i).delete()) {
                                XapklisActivity.this.files.remove(i);
                                XapklisActivity.this.files_item.remove(i);
                                XapklisActivity.this.adapter.notifyDataSetChanged();
                            }
                        }
                    }).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        listOn();
    }

    @RequiresApi(api = 18)
    public void startWork(String str) {
        File file = new File(str.replace("file:///", ""));
        if (file.getTotalSpace() <= getExternalAvailableSpaceInBytes(19)) {
            new AwesomeSuccessDialog(this).setTitle(R.string.app_name).setMessage("Insuficiente almacenamiento ").setColoredCircle(R.color.dialogSuccessBackgroundColor).setDialogIconAndColor(R.drawable.ic_success, R.color.white).setCancelable(true).setPositiveButtonText(getString(R.string.dialog_ok_button)).setPositiveButtonbackgroundColor(R.color.dialogSuccessBackgroundColor).setPositiveButtonTextColor(R.color.white).setNegativeButtonbackgroundColor(R.color.dialogSuccessBackgroundColor).setNegativeButtonTextColor(R.color.white).setPositiveButtonClick(new Closure() { // from class: cu.uci.android.apklis.presentation.ui.activity.XapklisActivity.5
                @Override // com.awesomedialog.blennersilva.awesomedialoglibrary.interfaces.Closure
                public void exec() {
                }
            }).show();
            Log.e("startWork: ", "No tiene espacio de almacenamiento");
            return;
        }
        Log.e("startWork: ", "Tiene Espacio");
        try {
            Log.e("startWork: ", file.getAbsolutePath());
            ZipFile zipFile = new ZipFile(file);
            InputStream inputStream = zipFile.getInputStream(zipFile.getEntry("manifest.json"));
            zipFile.getInputStream(zipFile.getEntry("icon.png"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
                sb.append('\n');
            }
            JSONObject jSONObject = new JSONObject(sb.toString());
            JSONArray jSONArray = jSONObject.getJSONArray("expansions");
            long j = 0;
            ArrayList<XapkFile> arrayList = new ArrayList<>();
            int i = 0;
            while (i < jSONArray.length()) {
                ZipEntry entry = zipFile.getEntry(jSONArray.getJSONObject(i).getString(ResourceUtils.URL_PROTOCOL_FILE));
                InputStream inputStream2 = zipFile.getInputStream(entry);
                long size = j + entry.getSize();
                arrayList.add(new XapkFile(jSONArray.getJSONObject(i).getString("install_location"), inputStream2, jSONArray.getJSONObject(i).getString("install_path")));
                i++;
                j = size;
            }
            ZipEntry entry2 = zipFile.getEntry(jSONObject.getString("package_name") + ".apk");
            saveData(j + entry2.getSize(), arrayList, zipFile.getInputStream(entry2));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }
}
