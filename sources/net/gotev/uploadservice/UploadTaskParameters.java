package net.gotev.uploadservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class UploadTaskParameters implements Parcelable {
    public static final Parcelable.Creator<UploadTaskParameters> CREATOR = new Parcelable.Creator<UploadTaskParameters>() { // from class: net.gotev.uploadservice.UploadTaskParameters.1
        @Override // android.os.Parcelable.Creator
        public UploadTaskParameters createFromParcel(Parcel parcel) {
            return new UploadTaskParameters(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public UploadTaskParameters[] newArray(int i) {
            return new UploadTaskParameters[i];
        }
    };
    private boolean autoDeleteSuccessfullyUploadedFiles;
    private ArrayList<UploadFile> files;
    private String id;
    private int maxRetries;
    private UploadNotificationConfig notificationConfig;
    private String serverUrl;

    public UploadTaskParameters() {
        this.maxRetries = 0;
        this.autoDeleteSuccessfullyUploadedFiles = false;
        this.files = new ArrayList<>();
    }

    private UploadTaskParameters(Parcel parcel) {
        this.maxRetries = 0;
        this.autoDeleteSuccessfullyUploadedFiles = false;
        this.files = new ArrayList<>();
        this.id = parcel.readString();
        this.serverUrl = parcel.readString();
        this.maxRetries = parcel.readInt();
        this.autoDeleteSuccessfullyUploadedFiles = parcel.readByte() == 1;
        this.notificationConfig = (UploadNotificationConfig) parcel.readParcelable(UploadNotificationConfig.class.getClassLoader());
        parcel.readList(this.files, UploadFile.class.getClassLoader());
    }

    public void addFile(UploadFile uploadFile) throws FileNotFoundException {
        this.files.add(uploadFile);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public List<UploadFile> getFiles() {
        return this.files;
    }

    public String getId() {
        return this.id;
    }

    public int getMaxRetries() {
        return this.maxRetries;
    }

    public UploadNotificationConfig getNotificationConfig() {
        return this.notificationConfig;
    }

    public String getServerUrl() {
        return this.serverUrl;
    }

    public boolean isAutoDeleteSuccessfullyUploadedFiles() {
        return this.autoDeleteSuccessfullyUploadedFiles;
    }

    public UploadTaskParameters setAutoDeleteSuccessfullyUploadedFiles(boolean z) {
        this.autoDeleteSuccessfullyUploadedFiles = z;
        return this;
    }

    public UploadTaskParameters setId(String str) {
        this.id = str;
        return this;
    }

    public UploadTaskParameters setMaxRetries(int i) {
        if (i < 0) {
            this.maxRetries = 0;
        } else {
            this.maxRetries = i;
        }
        return this;
    }

    public UploadTaskParameters setNotificationConfig(UploadNotificationConfig uploadNotificationConfig) {
        this.notificationConfig = uploadNotificationConfig;
        return this;
    }

    public UploadTaskParameters setServerUrl(String str) {
        this.serverUrl = str;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.serverUrl);
        parcel.writeInt(this.maxRetries);
        parcel.writeByte(this.autoDeleteSuccessfullyUploadedFiles ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.notificationConfig, 0);
        parcel.writeList(this.files);
    }
}
