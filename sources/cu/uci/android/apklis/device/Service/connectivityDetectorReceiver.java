package cu.uci.android.apklis.device.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.device.NotificationUtils;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.UpdateAppPresenterImpl;
import cu.uci.android.apklis.threading.MainThreadImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes.dex */
public class connectivityDetectorReceiver extends BroadcastReceiver implements UpdateAppPresenter.View {
    private Context context;
    private UpdateAppPresenter presenter;

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onAppsToUpdateLoaded(ArrayList<AppDetails> arrayList) {
        if (arrayList.size() > 0) {
            String str = "";
            Iterator<AppDetails> it = arrayList.iterator();
            while (it.hasNext()) {
                AppDetails next = it.next();
                this.presenter.checkAppDownload(next.getPackageName());
                str = next.getName() + ", ";
            }
            if (((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.ENABLE_NOFT_UPDATE, Boolean.class, true)).booleanValue()) {
                NotificationUtils notificationUtils = new NotificationUtils(this.context);
                if (Build.VERSION.SDK_INT == 26) {
                    notificationUtils.createNotification8(arrayList.size(), str.substring(0, str.length() - 2));
                } else {
                    notificationUtils.createNotification(arrayList.size(), str.substring(0, str.length() - 2));
                }
            }
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onClickCancelDownload(AppDetails appDetails) {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onClickUpdate(AppDetails appDetails) {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onDownloadError(String str) {
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onDownloadStart(String str, Long l) {
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return;
        }
        Log.i("Post", "Connected");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM yyyy", Locale.US);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        if (simpleDateFormat.format(calendar.getTime()).equals(SharedPreferencesManager.getInstance().getValue(MainApp.DATE_UPDATE, String.class, ""))) {
            return;
        }
        this.presenter = new UpdateAppPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.presenter.getAppsToUpdate();
        SharedPreferencesManager.getInstance().putValue(MainApp.DATE_UPDATE, simpleDateFormat.format(calendar.getTime()));
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
    }
}
