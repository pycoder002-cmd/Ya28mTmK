package net.gotev.uploadservice;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes2.dex */
public class UploadInfo implements Parcelable {
    public static final Parcelable.Creator<UploadInfo> CREATOR = new Parcelable.Creator<UploadInfo>() { // from class: net.gotev.uploadservice.UploadInfo.1
        @Override // android.os.Parcelable.Creator
        public UploadInfo createFromParcel(Parcel parcel) {
            return new UploadInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public UploadInfo[] newArray(int i) {
            return new UploadInfo[i];
        }
    };
    private long currentTime;
    private int numberOfRetries;
    private long startTime;
    private ArrayList<String> successfullyUploadedFiles;
    private long totalBytes;
    private String uploadId;
    private long uploadedBytes;

    private UploadInfo(Parcel parcel) {
        this.successfullyUploadedFiles = new ArrayList<>();
        this.uploadId = parcel.readString();
        this.startTime = parcel.readLong();
        this.currentTime = parcel.readLong();
        this.uploadedBytes = parcel.readLong();
        this.totalBytes = parcel.readLong();
        this.numberOfRetries = parcel.readInt();
        parcel.readStringList(this.successfullyUploadedFiles);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UploadInfo(String str) {
        this.successfullyUploadedFiles = new ArrayList<>();
        this.uploadId = str;
        this.startTime = 0L;
        this.currentTime = 0L;
        this.uploadedBytes = 0L;
        this.totalBytes = 0L;
        this.numberOfRetries = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public UploadInfo(String str, long j, long j2, long j3, int i, List<String> list) {
        this.successfullyUploadedFiles = new ArrayList<>();
        this.uploadId = str;
        this.startTime = j;
        this.currentTime = new Date().getTime();
        this.uploadedBytes = j2;
        this.totalBytes = j3;
        this.numberOfRetries = i;
        if (list == null || list.isEmpty()) {
            return;
        }
        this.successfullyUploadedFiles.addAll(list);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getElapsedTime() {
        return this.currentTime - this.startTime;
    }

    public String getElapsedTimeString() {
        int elapsedTime = (int) (getElapsedTime() / 1000);
        if (elapsedTime == 0) {
            return "0s";
        }
        int i = elapsedTime / 60;
        int i2 = elapsedTime - (60 * i);
        if (i == 0) {
            return i2 + "s";
        }
        return i + "m " + i2 + "s";
    }

    public int getNumberOfRetries() {
        return this.numberOfRetries;
    }

    public int getProgressPercent() {
        if (this.totalBytes == 0) {
            return 0;
        }
        return (int) ((this.uploadedBytes * 100) / this.totalBytes);
    }

    public long getStartTime() {
        return this.startTime;
    }

    public ArrayList<String> getSuccessfullyUploadedFiles() {
        return this.successfullyUploadedFiles;
    }

    public long getTotalBytes() {
        return this.totalBytes;
    }

    public String getUploadId() {
        return this.uploadId;
    }

    public double getUploadRate() {
        return getElapsedTime() < 1000 ? Utils.DOUBLE_EPSILON : ((this.uploadedBytes / 1024.0d) * 8.0d) / (r0 / 1000);
    }

    public String getUploadRateString() {
        double uploadRate = getUploadRate();
        if (uploadRate < 1.0d) {
            return ((int) (uploadRate * 1000.0d)) + " bit/s";
        }
        if (uploadRate >= 1024.0d) {
            return ((int) (uploadRate / 1024.0d)) + " Mbit/s";
        }
        return ((int) uploadRate) + " Kbit/s";
    }

    public long getUploadedBytes() {
        return this.uploadedBytes;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.uploadId);
        parcel.writeLong(this.startTime);
        parcel.writeLong(this.currentTime);
        parcel.writeLong(this.uploadedBytes);
        parcel.writeLong(this.totalBytes);
        parcel.writeInt(this.numberOfRetries);
        parcel.writeStringList(this.successfullyUploadedFiles);
    }
}
