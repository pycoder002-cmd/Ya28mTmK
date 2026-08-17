package net.gotev.uploadservice;

import android.content.Context;
import android.content.Intent;
import java.net.MalformedURLException;
import java.util.UUID;

/* loaded from: classes2.dex */
public abstract class UploadRequest {
    private static final String LOG_TAG = "UploadRequest";
    protected final Context context;
    protected UploadStatusDelegate delegate;
    protected final UploadTaskParameters params = new UploadTaskParameters();

    public UploadRequest(Context context, String str, String str2) {
        if (context == null) {
            throw new IllegalArgumentException("Context MUST not be null!");
        }
        this.context = context;
        if (str == null || str.isEmpty()) {
            Logger.debug(LOG_TAG, "null or empty upload ID. Generating it");
            this.params.setId(UUID.randomUUID().toString());
        } else {
            Logger.debug(LOG_TAG, "setting provided upload ID");
            this.params.setId(str);
        }
        this.params.setServerUrl(str2);
        Logger.debug(LOG_TAG, "Created new upload request to " + this.params.getServerUrl() + " with ID: " + this.params.getId());
    }

    protected abstract Class<? extends UploadTask> getTaskClass();

    /* JADX INFO: Access modifiers changed from: protected */
    public void initializeIntent(Intent intent) {
        intent.putExtra("taskParameters", this.params);
        Class<? extends UploadTask> taskClass = getTaskClass();
        if (taskClass == null) {
            throw new RuntimeException("The request must specify a task class!");
        }
        intent.putExtra("taskClass", taskClass.getName());
    }

    public UploadRequest setAutoDeleteFilesAfterSuccessfulUpload(boolean z) {
        this.params.setAutoDeleteSuccessfullyUploadedFiles(z);
        return this;
    }

    public UploadRequest setDelegate(UploadStatusDelegate uploadStatusDelegate) {
        this.delegate = uploadStatusDelegate;
        return this;
    }

    public UploadRequest setMaxRetries(int i) {
        this.params.setMaxRetries(i);
        return this;
    }

    public UploadRequest setNotificationConfig(UploadNotificationConfig uploadNotificationConfig) {
        this.params.setNotificationConfig(uploadNotificationConfig);
        return this;
    }

    public final String startUpload() throws IllegalArgumentException, MalformedURLException {
        validate();
        UploadService.setUploadStatusDelegate(this.params.getId(), this.delegate);
        Intent intent = new Intent(this.context, (Class<?>) UploadService.class);
        initializeIntent(intent);
        intent.setAction(UploadService.getActionUpload());
        this.context.startService(intent);
        return this.params.getId();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validate() throws IllegalArgumentException, MalformedURLException {
        if (this.params.getServerUrl() == null || "".equals(this.params.getServerUrl())) {
            throw new IllegalArgumentException("Server URL cannot be null or empty");
        }
        if (this.params.getFiles().isEmpty()) {
            throw new IllegalArgumentException("You have to add at least one file to upload");
        }
    }
}
