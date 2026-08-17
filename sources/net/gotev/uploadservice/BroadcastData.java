package net.gotev.uploadservice;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class BroadcastData implements Parcelable {
    public static final Parcelable.Creator<BroadcastData> CREATOR = new Parcelable.Creator<BroadcastData>() { // from class: net.gotev.uploadservice.BroadcastData.1
        @Override // android.os.Parcelable.Creator
        public BroadcastData createFromParcel(Parcel parcel) {
            return new BroadcastData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BroadcastData[] newArray(int i) {
            return new BroadcastData[i];
        }
    };
    private Exception exception;
    private ServerResponse serverResponse;
    private Status status;
    private UploadInfo uploadInfo;

    /* loaded from: classes2.dex */
    public enum Status {
        IN_PROGRESS,
        ERROR,
        COMPLETED,
        CANCELLED
    }

    public BroadcastData() {
    }

    private BroadcastData(Parcel parcel) {
        this.status = Status.values()[parcel.readInt()];
        this.exception = (Exception) parcel.readSerializable();
        this.uploadInfo = (UploadInfo) parcel.readParcelable(UploadInfo.class.getClassLoader());
        this.serverResponse = (ServerResponse) parcel.readParcelable(ServerResponse.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Exception getException() {
        return this.exception;
    }

    public Intent getIntent() {
        Intent intent = new Intent(UploadService.getActionBroadcast());
        intent.putExtra("broadcastData", this);
        return intent;
    }

    public ServerResponse getServerResponse() {
        return this.serverResponse;
    }

    public Status getStatus() {
        return this.status;
    }

    public UploadInfo getUploadInfo() {
        return this.uploadInfo;
    }

    public BroadcastData setException(Exception exc) {
        this.exception = exc;
        return this;
    }

    public BroadcastData setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
        return this;
    }

    public BroadcastData setStatus(Status status) {
        this.status = status;
        return this;
    }

    public BroadcastData setUploadInfo(UploadInfo uploadInfo) {
        this.uploadInfo = uploadInfo;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.status.ordinal());
        parcel.writeSerializable(this.exception);
        parcel.writeParcelable(this.uploadInfo, i);
        parcel.writeParcelable(this.serverResponse, i);
    }
}
