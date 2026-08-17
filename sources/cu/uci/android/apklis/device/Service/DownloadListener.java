package cu.uci.android.apklis.device.Service;

import java.util.UUID;

/* loaded from: classes.dex */
public class DownloadListener {
    private String id = UUID.randomUUID().toString();
    private DownloadStatusListener listener;
    private String package_name;

    /* loaded from: classes.dex */
    public interface DownloadStatusListener {
        void onDownloadClosed();

        void onDownloadPending();

        void onDownloadProgressChange(int i);
    }

    public DownloadListener(String str, DownloadStatusListener downloadStatusListener) {
        this.package_name = str;
        this.listener = downloadStatusListener;
    }

    public String getId() {
        return this.id;
    }

    public DownloadStatusListener getListener() {
        return this.listener;
    }

    public String getPackage_name() {
        return this.package_name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void onDownloadClosed() {
        if (this.listener != null) {
            this.listener.onDownloadClosed();
        }
    }

    public void onDownloadPending() {
        this.listener.onDownloadPending();
    }

    public void onDownloadProgressChange(int i) {
        this.listener.onDownloadProgressChange(i);
    }

    public void setListener(DownloadStatusListener downloadStatusListener) {
        this.listener = downloadStatusListener;
    }

    public void setPackage_name(String str) {
        this.package_name = str;
    }
}
