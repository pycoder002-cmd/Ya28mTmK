package cu.uci.android.apklis.device;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.ToastUtils;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.presentation.model.AppStatus;
import cu.uci.android.apklis.presentation.ui.activity.XapklisActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipFile;
import org.springframework.util.ResourceUtils;

/* loaded from: classes.dex */
public class PackageManagerHelper {
    static ArrayList<ZipFile> files = new ArrayList<>();
    SharedPreferences pref;

    public static boolean checkSHA(String str, String str2) {
        try {
            str = URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            MainApp.log("PackageManagerHelper", e);
            e.printStackTrace();
        }
        Log.e("checkSHA: ", str);
        File file = new File(str.replace("file:///", ""));
        Log.e("checkSHA: ", file.getAbsolutePath());
        if (!file.exists()) {
            Log.e("checkSHA: ", "heere");
            ToastUtils.showLong(R.string.not_avaiable);
            return false;
        }
        try {
            StringBuffer createSha1 = createSha1(file);
            Log.e("checkSHA: ", createSha1.toString());
            if (createSha1.toString().equals(str2)) {
                return true;
            }
            file.delete();
            ToastUtils.showLong(R.string.not_avaiable);
            return false;
        } catch (Exception e2) {
            MainApp.log("PackageManagerHelper", e2);
            e2.printStackTrace();
            ToastUtils.showLong(R.string.not_avaiable);
            return false;
        }
    }

    private static StringBuffer createSha1(File file) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA256");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[8192];
        int i = 0;
        while (i != -1) {
            i = fileInputStream.read(bArr);
            if (i > 0) {
                messageDigest.update(bArr, 0, i);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : messageDigest.digest()) {
            int i2 = b & FileDownloadStatus.error;
            if (i2 < 16) {
                stringBuffer.append("0");
                stringBuffer.append(Integer.toHexString(i2));
            } else {
                stringBuffer.append(Integer.toHexString(i2));
            }
        }
        return stringBuffer;
    }

    public static void deleteApp(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }

    public static AppStatus getAppStatus(String str, String str2) {
        AppStatus appStatus = AppStatus.NOT_INSTALLED;
        int parseInt = Integer.parseInt(str2);
        try {
            PackageInfo packageInfo = MainApp.context.getPackageManager().getPackageInfo(str, 0);
            String str3 = packageInfo.versionName;
            return parseInt > packageInfo.versionCode ? AppStatus.UPDATABLE : AppStatus.INSTALLED;
        } catch (PackageManager.NameNotFoundException e) {
            MainApp.log(e.getMessage());
            return appStatus;
        }
    }

    public static Map<String, Integer> getUserInstalledApps() {
        HashMap hashMap = new HashMap();
        for (PackageInfo packageInfo : MainApp.context.getPackageManager().getInstalledPackages(1152)) {
            if ((packageInfo.applicationInfo.flags & 1) != 1) {
                hashMap.put(packageInfo.packageName, Integer.valueOf(packageInfo.versionCode));
            }
        }
        return hashMap;
    }

    public static void installAppByUri(String str, String str2) {
        String str3 = (String) SharedPreferencesManager.getInstance().getValue(str, String.class, "");
        Log.e("installAppByUri: ", str3);
        if (checkSHA(str2, str3) && ((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.IGNORE_INSTALL, Boolean.class, true)).booleanValue()) {
            if (str2.endsWith(".apk")) {
                if (!((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.ENABLE_AUTOINSTALL, Boolean.class, false)).booleanValue()) {
                    installWithIntent(str2);
                    return;
                } else {
                    if (rootInstall(str2.replace("file:///", ""))) {
                        return;
                    }
                    installWithIntent(str2);
                    return;
                }
            }
            Intent intent = new Intent(MainApp.context, (Class<?>) XapklisActivity.class);
            intent.setFlags(268435456);
            SharedPreferences.Editor edit = MainApp.context.getSharedPreferences("shared", 0).edit();
            edit.putString(ResourceUtils.URL_PROTOCOL_FILE, str2);
            edit.apply();
            MainApp.context.startActivity(intent);
        }
    }

    private static void installWithIntent(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.parse(str), "application/vnd.android.package-archive");
        intent.setFlags(268435456);
        MainApp.context.startActivity(intent);
    }

    private static boolean rootInstall(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (new File("/system/bin/su").exists() || new File("/system/xbin/su").exists()) {
            return new RootInstaller().install(str);
        }
        return false;
    }
}
