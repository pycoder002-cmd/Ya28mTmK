package cu.uci.android.apklis;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.widget.Toast;
import com.blankj.utilcode.util.Utils;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;
import cu.uci.android.apklis.device.AccountHelper;
import cu.uci.android.apklis.device.DeviceInfo;
import cu.uci.android.apklis.device.Service.ServiceReceiver;
import cu.uci.android.apklis.domain.model.UserAccount;
import cu.uci.android.apklis.presentation.ui.activity.SplashActivity;
import io.sentry.Sentry;
import io.sentry.android.AndroidSentryClientFactory;
import io.sentry.event.UserBuilder;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: classes.dex */
public class MainApp extends MultiDexApplication {
    public static int APPS_LOAD_IN_GROUP = 12;
    public static int APPS_LOAD_RANGE = 15;
    public static String APP_SHA_DOWN = "SHA_DOWN_PARAM";
    public static final String APP_URL_NAME = "apklis.cu";
    public static String DATE_UPDATE = "DATE_UPDATE_PARAM";
    public static String DOWNLOAD_DIRECTORY = "DOWNLOAD_DIRECTORY";
    public static String ENABLE_AUTODELETE_APK = "ENABLE_AUTODELETE_APK";
    public static String ENABLE_AUTOINSTALL = "ENABLE_AUTOINSTALL";
    public static String ENABLE_NOFT_UPDATE = "ENABLE_NOFT_UPDATE";
    public static String IGNORE_INSTALL = "IGNORE_INSTALL";
    public static String IGNORE_MY_SDK = "IGNORE_MY_SDK";
    public static int MY_SDK_VERSION = 0;
    public static final String SERVER_API_URL = "https://api.apklis.cu";
    public static int SPAN_COUNT_RECYCLER_GRID_VIEW = 3;
    public static int SPAN_COUNT_RECYCLER_GRID_VIEW_CATEGORY = 4;
    private static String TAG = "MY_DEBUG";
    public static String USER_ACCOUNT_PARAM = "USER_ACCOUNT_PARAM";
    public static String USER_STATUS_PARAM = "USER_STATUS_PARAM";
    public static Context context;
    public static UserAccount userAccount;
    private static UserAccount userAccountPreference;
    private static Integer userStatus;
    public static Integer USER_STATUS_NORMAL = 0;
    public static Integer USER_STATUS_REQUEST_ACTIVATION = 1;
    public static Integer USER_STATUS_AUTENTICATED = 2;
    private static Integer MAX_CACHE_SIZE = 10485760;

    public static UserAccount getUserAccountPreference() {
        if (getUserStatus() == USER_STATUS_REQUEST_ACTIVATION && userAccountPreference == null) {
            userAccountPreference = (UserAccount) SharedPreferencesManager.getInstance().getValue(USER_ACCOUNT_PARAM, UserAccount.class, null);
        }
        return userAccountPreference;
    }

    public static Integer getUserStatus() {
        if (userStatus == null) {
            userStatus = (Integer) SharedPreferencesManager.getInstance().getValue(USER_STATUS_PARAM, Integer.class, USER_STATUS_NORMAL);
        }
        return userStatus;
    }

    private static void initSentry(Exception exc) {
        Sentry.init("https://d21c37608f2541d39bcd8a2065ea6fe7@reportes.mprc.cu/4", new AndroidSentryClientFactory(context));
        String str = "apklis@uci.cu";
        try {
            if (userAccountPreference.getEmail() != null && userAccountPreference.getEmail().contains("@")) {
                str = userAccountPreference.getEmail();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Sentry.getContext().setUser(new UserBuilder().setEmail(str).build());
        Sentry.capture(exc.getMessage());
    }

    private boolean isMyServiceRunning(Class<?> cls) {
        Iterator<ActivityManager.RunningServiceInfo> it = ((ActivityManager) getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (cls.getName().equals(it.next().service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUserAuthenticate() {
        return (userAccount == null || userAccount.getId() == null || userAccount.getAccessToken() == null || userAccount.getAccessToken().length() <= 0) ? false : true;
    }

    public static void log(String str) {
        Log.e(TAG, str);
    }

    public static void log(String str, Exception exc) {
        Logger.getLogger(str).log(Level.SEVERE, (String) null, (Throwable) exc);
        initSentry(exc);
    }

    public static void setUserStatus(Integer num, UserAccount userAccount2) {
        if (num != userStatus) {
            userStatus = num;
            SharedPreferencesManager.getInstance().remove(USER_STATUS_PARAM);
            SharedPreferencesManager.getInstance().putValue(USER_STATUS_PARAM, userStatus);
            if (num != USER_STATUS_REQUEST_ACTIVATION || userAccount2 == null) {
                return;
            }
            SharedPreferencesManager.getInstance().remove(USER_ACCOUNT_PARAM);
            SharedPreferencesManager.getInstance().putValue(USER_ACCOUNT_PARAM, userAccount2);
        }
    }

    public static void showToast(String str, boolean z) {
        if (z) {
            Toast.makeText(context, str, 0).show();
        } else {
            Toast.makeText(context, str, 1).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.multidex.MultiDexApplication, android.content.ContextWrapper
    public void attachBaseContext(Context context2) {
        super.attachBaseContext(context2);
        MultiDex.install(this);
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            SplashActivity.setDisplayMetrics(true);
        } else if (configuration.orientation == 1) {
            SplashActivity.setDisplayMetrics(false);
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        MY_SDK_VERSION = Build.VERSION.SDK_INT;
        if (isMyServiceRunning(ServiceReceiver.class)) {
            log("the ServiceReceiver is running");
        } else {
            log("starting ServiceReceiver.....");
            if (Build.VERSION.SDK_INT == 26) {
                startForegroundService(new Intent(context, (Class<?>) ServiceReceiver.class));
            }
        }
        AccountHelper.initialize(context);
        Utils.init(context);
        SharedPreferencesManager.init(context, true, new String[0]);
        long freeRamMemorySize = DeviceInfo.freeRamMemorySize(this);
        if (freeRamMemorySize < 100 && freeRamMemorySize > 50) {
            MAX_CACHE_SIZE = 20971520;
        } else if (freeRamMemorySize < 200 && freeRamMemorySize > 100) {
            MAX_CACHE_SIZE = 31457280;
        } else if (freeRamMemorySize > 300) {
            MAX_CACHE_SIZE = 41943040;
        }
        Picasso.setSingletonInstance(new Picasso.Builder(context).memoryCache(new LruCache(MAX_CACHE_SIZE.intValue())).build());
        if (DeviceInfo.isAccessibilitySettingsOn(this)) {
            SharedPreferencesManager.getInstance().putValue(ENABLE_AUTOINSTALL, true);
        } else {
            SharedPreferencesManager.getInstance().putValue(ENABLE_AUTOINSTALL, false);
        }
    }
}
