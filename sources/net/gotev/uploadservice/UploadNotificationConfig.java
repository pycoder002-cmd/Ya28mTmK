package net.gotev.uploadservice;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public final class UploadNotificationConfig implements Parcelable {
    public static final Parcelable.Creator<UploadNotificationConfig> CREATOR = new Parcelable.Creator<UploadNotificationConfig>() { // from class: net.gotev.uploadservice.UploadNotificationConfig.1
        @Override // android.os.Parcelable.Creator
        public UploadNotificationConfig createFromParcel(Parcel parcel) {
            return new UploadNotificationConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public UploadNotificationConfig[] newArray(int i) {
            return new UploadNotificationConfig[i];
        }
    };
    private boolean autoClearOnSuccess;
    private boolean clearOnAction;
    private Intent clickIntent;
    private String completed;
    private String error;
    private int iconResourceID;
    private String inProgress;
    private boolean ringToneEnabled;
    private String title;

    public UploadNotificationConfig() {
        this.iconResourceID = android.R.drawable.ic_menu_upload;
        this.title = "File Upload";
        this.inProgress = "Uploading at [[UPLOAD_RATE]] ([[PROGRESS]])";
        this.completed = "Upload completed successfully in [[ELAPSED_TIME]]";
        this.error = "Error during upload";
        this.autoClearOnSuccess = false;
        this.clearOnAction = false;
        this.clickIntent = null;
        this.ringToneEnabled = true;
    }

    private UploadNotificationConfig(Parcel parcel) {
        this.iconResourceID = parcel.readInt();
        this.title = parcel.readString();
        this.inProgress = parcel.readString();
        this.completed = parcel.readString();
        this.error = parcel.readString();
        this.autoClearOnSuccess = parcel.readByte() == 1;
        this.clearOnAction = parcel.readByte() == 1;
        this.ringToneEnabled = parcel.readByte() == 1;
        this.clickIntent = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getCompletedMessage() {
        return this.completed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getErrorMessage() {
        return this.error;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getIconResourceID() {
        return this.iconResourceID;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getInProgressMessage() {
        return this.inProgress;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final PendingIntent getPendingIntent(Context context) {
        return this.clickIntent == null ? PendingIntent.getBroadcast(context, 0, new Intent(), 134217728) : PendingIntent.getActivity(context, 1, this.clickIntent, 134217728);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String getTitle() {
        return this.title;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isAutoClearOnSuccess() {
        return this.autoClearOnSuccess;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isClearOnAction() {
        return this.clearOnAction;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isRingToneEnabled() {
        return this.ringToneEnabled;
    }

    public final UploadNotificationConfig setAutoClearOnSuccess(boolean z) {
        this.autoClearOnSuccess = z;
        return this;
    }

    public final UploadNotificationConfig setClearOnAction(boolean z) {
        this.clearOnAction = z;
        return this;
    }

    public final UploadNotificationConfig setClickIntent(Intent intent) {
        this.clickIntent = intent;
        return this;
    }

    public final UploadNotificationConfig setCompletedMessage(String str) {
        this.completed = str;
        return this;
    }

    public final UploadNotificationConfig setErrorMessage(String str) {
        this.error = str;
        return this;
    }

    public final UploadNotificationConfig setIcon(int i) {
        this.iconResourceID = i;
        return this;
    }

    public final UploadNotificationConfig setInProgressMessage(String str) {
        this.inProgress = str;
        return this;
    }

    public final UploadNotificationConfig setRingToneEnabled(Boolean bool) {
        this.ringToneEnabled = bool.booleanValue();
        return this;
    }

    public final UploadNotificationConfig setTitle(String str) {
        this.title = str;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.iconResourceID);
        parcel.writeString(this.title);
        parcel.writeString(this.inProgress);
        parcel.writeString(this.completed);
        parcel.writeString(this.error);
        parcel.writeByte(this.autoClearOnSuccess ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.clearOnAction ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.ringToneEnabled ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.clickIntent, 0);
    }
}
