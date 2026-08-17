package cu.uci.android.apklis.presentation.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.permissionrequest.PermissionRequest;
import com.example.permissionrequest.PermissionRequestListener;
import com.kingfisher.easy_sharedpreference_library.SharedPreferencesManager;
import cu.uci.android.apklis.MainApp;
import cu.uci.android.apklis.R;
import cu.uci.android.apklis.device.NotificationUtils;
import cu.uci.android.apklis.device.PackageManagerHelper;
import cu.uci.android.apklis.device.Service.DownloadListener;
import cu.uci.android.apklis.device.Service.ServiceDownload;
import cu.uci.android.apklis.domain.executor.ThreadExecutor;
import cu.uci.android.apklis.presentation.model.AppDetails;
import cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter;
import cu.uci.android.apklis.presentation.presenter.impl.UpdateAppPresenterImpl;
import cu.uci.android.apklis.presentation.ui.adapter.UpdateAppAdapter;
import cu.uci.android.apklis.threading.MainThreadImpl;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class UpdateAppFragment extends Fragment implements UpdateAppPresenter.View {
    private UpdateAppAdapter adapter;
    LinearLayout btTryAgain;
    LinearLayout emptyContent;
    private NotificationUtils mNotificationUtils;
    private UpdateAppPresenter presenter;
    RecyclerView recycler;
    SwipeRefreshLayout swipeRefreshLayout;
    private Boolean updateList = false;
    List<DownloadListener> downloadListeners = new ArrayList();

    public static UpdateAppFragment createInstance() {
        return new UpdateAppFragment();
    }

    private void init(View view) {
        this.recycler = (RecyclerView) view.findViewById(R.id.recycler);
        this.emptyContent = (LinearLayout) view.findViewById(R.id.empty_content);
        this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        this.btTryAgain = (LinearLayout) view.findViewById(R.id.btn_try_again);
        this.btTryAgain.setOnClickListener(new View.OnClickListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                UpdateAppFragment.this.updateList();
            }
        });
        this.presenter = new UpdateAppPresenterImpl(ThreadExecutor.getInstance(), MainThreadImpl.getInstance(), this);
        this.adapter = new UpdateAppAdapter(this, getContext(), this.recycler);
        this.adapter.notifyDataSetChanged();
        this.recycler.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        this.recycler.setAdapter(this.adapter);
        new DefaultItemAnimator() { // from class: cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment.2
            @Override // android.support.v7.widget.SimpleItemAnimator, android.support.v7.widget.RecyclerView.ItemAnimator
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        };
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment.3
            @Override // android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
            public void onRefresh() {
                UpdateAppFragment.this.updateList();
            }
        });
        updateList();
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void hideProgress() {
        this.swipeRefreshLayout.setRefreshing(false);
        this.emptyContent.setVisibility(8);
        this.swipeRefreshLayout.setVisibility(8);
        this.recycler.setVisibility(8);
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onAppsToUpdateLoaded(ArrayList<AppDetails> arrayList) {
        if (arrayList.size() <= 0) {
            this.swipeRefreshLayout.setRefreshing(false);
            this.swipeRefreshLayout.setVisibility(8);
            this.recycler.setVisibility(8);
            this.emptyContent.setVisibility(0);
            return;
        }
        this.swipeRefreshLayout.setRefreshing(false);
        this.emptyContent.setVisibility(8);
        this.swipeRefreshLayout.setVisibility(0);
        this.recycler.setVisibility(0);
        this.adapter.updateApps(arrayList);
        this.recycler.requestLayout();
        String str = "";
        Iterator<AppDetails> it = arrayList.iterator();
        while (it.hasNext()) {
            AppDetails next = it.next();
            this.presenter.checkAppDownload(next.getPackageName());
            str = next.getName() + ", ";
        }
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd yyyy", Locale.US);
        System.out.println(simpleDateFormat.format(calendar.getTime()));
        if (simpleDateFormat.format(calendar.getTime()).equals(SharedPreferencesManager.getInstance().getValue(MainApp.DATE_UPDATE, String.class, "")) || !((Boolean) SharedPreferencesManager.getInstance().getValue(MainApp.ENABLE_NOFT_UPDATE, Boolean.class, true)).booleanValue()) {
            return;
        }
        this.mNotificationUtils = new NotificationUtils(getContext());
        if (Build.VERSION.SDK_INT == 26) {
            this.mNotificationUtils.createNotification8(arrayList.size(), str.substring(0, str.length() - 2));
        } else {
            this.mNotificationUtils.createNotification(arrayList.size(), str.substring(0, str.length() - 2));
        }
        SharedPreferencesManager.getInstance().putValue(MainApp.DATE_UPDATE, simpleDateFormat.format(calendar.getTime()));
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onClickCancelDownload(AppDetails appDetails) {
        this.presenter.cancelDownloadUpdate(appDetails.getPackageName());
    }

    public void onClickTryAgain() {
        updateList();
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onClickUpdate(final AppDetails appDetails) {
        if (appDetails != null) {
            PermissionRequest.requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", getString(R.string.get_account_permission_name), getContext()).withListener(new PermissionRequestListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment.4
                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionAcept(String str) {
                    String downloadUrl = appDetails.getLastRelease().getDownloadUrl();
                    SharedPreferencesManager.getInstance().putValue(appDetails.getPackageName(), appDetails.getLastRelease().getDownloadSha256());
                    String str2 = ((String) SharedPreferencesManager.getInstance().getValue(MainApp.DOWNLOAD_DIRECTORY, String.class, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "")) + File.separator + appDetails.getPackageName() + appDetails.getLastRelease().getPackage_versionName() + ".apk";
                    if (!new File(str2).exists()) {
                        UpdateAppFragment.this.presenter.installApp(downloadUrl, appDetails.getName(), appDetails.getLastRelease().getPackage_versionName(), appDetails.getPackageName(), appDetails.getLastRelease().getDownloadSha256(), appDetails.getExtension());
                        return;
                    }
                    PackageManagerHelper.installAppByUri(appDetails.getPackageName(), "file://" + str2);
                }

                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionDeny(String str) {
                }

                @Override // com.example.permissionrequest.PermissionRequestListener
                public void onPermissionDenyPermanent(String str) {
                }
            }).check();
        }
    }

    @Override // android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_update_app, viewGroup, false);
        init(inflate);
        return inflate;
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onDownloadError(String str) {
        Integer appPositionByPackageName = this.adapter.getAppPositionByPackageName(str);
        if (appPositionByPackageName.intValue() != -1) {
            this.adapter.notifyItemChanged(appPositionByPackageName.intValue(), -1);
        }
    }

    @Override // cu.uci.android.apklis.presentation.presenter.UpdateAppPresenter.View
    public void onDownloadStart(final String str, Long l) {
        DownloadListener downloadListener = new DownloadListener(str, new DownloadListener.DownloadStatusListener() { // from class: cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment.5
            @Override // cu.uci.android.apklis.device.Service.DownloadListener.DownloadStatusListener
            public void onDownloadClosed() {
                if (UpdateAppFragment.this.getActivity() != null) {
                    UpdateAppFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment.5.3
                        @Override // java.lang.Runnable
                        public void run() {
                            Integer appPositionByPackageName = UpdateAppFragment.this.adapter.getAppPositionByPackageName(str);
                            if (appPositionByPackageName.intValue() != -1) {
                                UpdateAppFragment.this.adapter.notifyItemChanged(appPositionByPackageName.intValue(), -1);
                            }
                        }
                    });
                }
            }

            @Override // cu.uci.android.apklis.device.Service.DownloadListener.DownloadStatusListener
            public void onDownloadPending() {
                if (UpdateAppFragment.this.getActivity() != null) {
                    UpdateAppFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment.5.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Integer appPositionByPackageName = UpdateAppFragment.this.adapter.getAppPositionByPackageName(str);
                            if (appPositionByPackageName.intValue() != -1) {
                                UpdateAppFragment.this.adapter.notifyItemChanged(appPositionByPackageName.intValue(), 0);
                            }
                        }
                    });
                }
            }

            @Override // cu.uci.android.apklis.device.Service.DownloadListener.DownloadStatusListener
            public void onDownloadProgressChange(final int i) {
                if (UpdateAppFragment.this.getActivity() != null) {
                    UpdateAppFragment.this.getActivity().runOnUiThread(new Runnable() { // from class: cu.uci.android.apklis.presentation.ui.fragment.UpdateAppFragment.5.2
                        @Override // java.lang.Runnable
                        public void run() {
                            MainApp.log(str + "  -->  " + i);
                            Integer appPositionByPackageName = UpdateAppFragment.this.adapter.getAppPositionByPackageName(str);
                            if (appPositionByPackageName.intValue() != -1) {
                                UpdateAppFragment.this.adapter.notifyItemChanged(appPositionByPackageName.intValue(), Integer.valueOf(i));
                            }
                        }
                    });
                }
            }
        });
        ServiceDownload.addListener(downloadListener);
        this.downloadListeners.add(downloadListener);
        try {
            if (Build.VERSION.SDK_INT == 26) {
                getContext().startForegroundService(new Intent(getContext(), (Class<?>) ServiceDownload.class));
            } else {
                getContext().startService(new Intent(getContext(), (Class<?>) ServiceDownload.class));
            }
        } catch (NullPointerException e) {
            MainApp.log(getClass().getName(), e);
            e.printStackTrace();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.updateList.booleanValue()) {
            updateList();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        String id;
        super.onStop();
        this.updateList = true;
        for (DownloadListener downloadListener : this.downloadListeners) {
            if (downloadListener != null && (id = downloadListener.getId()) != null) {
                ServiceDownload.removeListener(id);
            }
        }
        this.downloadListeners = new ArrayList();
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showError(String str) {
    }

    @Override // cu.uci.android.apklis.presentation.ui.BaseView
    public void showProgress() {
        this.recycler.setVisibility(8);
        this.emptyContent.setVisibility(8);
        this.swipeRefreshLayout.setVisibility(0);
        this.swipeRefreshLayout.setRefreshing(true);
    }

    public void updateList() {
        if (this.presenter != null) {
            this.presenter.getAppsToUpdate();
            this.updateList = false;
        }
    }
}
