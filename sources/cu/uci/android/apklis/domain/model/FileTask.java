package cu.uci.android.apklis.domain.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class FileTask extends AsyncTask<ArrayList<XapkFile>, Integer, Long> {
    private InputStream apk;
    private ArrayList arrayList;
    private Context context;
    MaterialDialog dialog;
    private SharedPreferences.Editor editor;
    private SharedPreferences prefs;
    private ProgressBar progressBar;
    private long totalSize;
    private final int RESULT_OK = 11545;
    private final int RESULT_CANCEL = 165456;
    private final int INSTALL_APK = 1245;

    public FileTask(Context context, long j, InputStream inputStream) {
        this.context = context;
        this.totalSize = j;
        this.apk = inputStream;
    }

    private boolean checkExternalMedia() {
        boolean z;
        boolean z2;
        String externalStorageState = Environment.getExternalStorageState();
        if ("mounted".equals(externalStorageState)) {
            z = true;
        } else {
            if ("mounted_ro".equals(externalStorageState)) {
                z2 = false;
                z = true;
                return z && z2;
            }
            z = false;
        }
        z2 = z;
        if (z) {
            return false;
        }
    }

    private void installXternal(XapkFile xapkFile) {
        if (!checkExternalMedia()) {
            new MaterialDialog.Builder(this.context).title("Error con la tarjeta externa").content("No hay tarjeta externa instalada o esta en modo de solo lectura").positiveText("Instalar en interna").onPositive(new MaterialDialog.SingleButtonCallback() { // from class: cu.uci.android.apklis.domain.model.FileTask.1
                @Override // com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback
                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                }
            }).show();
            return;
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        InputStream file = xapkFile.getFile();
        String location = xapkFile.getLocation();
        int lastIndexOf = location.lastIndexOf(47);
        String substring = location.substring(0, lastIndexOf);
        File file2 = new File(externalStorageDirectory.getAbsolutePath() + "/" + substring, location.substring(lastIndexOf + 1, location.length()));
        Log.e("aki ", file2.getAbsolutePath());
        file2.getParentFile().mkdirs();
        this.arrayList.add(file2.getAbsolutePath());
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(file);
            byte[] bArr = new byte[1048576];
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2), 1048576);
            while (true) {
                int read = bufferedInputStream.read(bArr, 0, 1048576);
                if (read == -1) {
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    return;
                }
                bufferedOutputStream.write(bArr, 0, read);
                publishProgress(Integer.valueOf(read));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Long doInBackground(ArrayList<XapkFile>... arrayListArr) {
        this.arrayList = new ArrayList();
        Iterator<XapkFile> it = arrayListArr[0].iterator();
        while (it.hasNext()) {
            XapkFile next = it.next();
            if (next.getStorage().equals("EXTERNAL_STORAGE")) {
                installXternal(next);
            } else if (next.getStorage().equals("INTERNAL_STORAGE")) {
                installXternal(next);
            }
        }
        try {
            installApk();
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveArrayList(this.arrayList, "list_preference");
        return Long.valueOf(this.totalSize);
    }

    public void installApk() throws IOException {
        FileOutputStream fileOutputStream;
        Log.e("", "aki: Instalando apk");
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/xapx_install", "base.apk");
        file.getParentFile().mkdirs();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(this.apk);
        byte[] bArr = new byte[1048576];
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, 1048576);
        while (true) {
            int read = bufferedInputStream.read(bArr, 0, 1048576);
            if (read == -1) {
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                bufferedInputStream.close();
                return;
            }
            bufferedOutputStream.write(bArr, 0, read);
            publishProgress(Integer.valueOf(read));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Long l) {
        this.dialog.cancel();
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/xapx_install", "base.apk");
        if (Build.VERSION.SDK_INT >= 24) {
            Intent intent = new Intent("android.intent.action.INSTALL_PACKAGE");
            intent.setData(Uri.fromFile(file));
            intent.setFlags(1);
            intent.putExtra("android.intent.extra.RETURN_RESULT", true);
            ((Activity) this.context).startActivityForResult(intent, 1245);
            return;
        }
        Intent intent2 = new Intent("android.intent.action.INSTALL_PACKAGE");
        intent2.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent2.setFlags(1);
        intent2.putExtra("android.intent.extra.RETURN_RESULT", true);
        ((Activity) this.context).startActivityForResult(intent2, 1245);
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        if (((Activity) this.context).hasWindowFocus()) {
            this.dialog.show();
        }
        this.dialog = new MaterialDialog.Builder(this.context).cancelable(false).autoDismiss(false).title("Instalando").progress(false, (int) this.totalSize, false).show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(Integer... numArr) {
        this.dialog.incrementProgress(numArr[0].intValue());
    }

    public void saveArrayList(ArrayList<String> arrayList, String str) {
        this.prefs = this.context.getSharedPreferences("myPrefs", 0);
        this.editor = this.prefs.edit();
        this.editor.putString(str, new Gson().toJson(arrayList));
        this.editor.apply();
    }
}
