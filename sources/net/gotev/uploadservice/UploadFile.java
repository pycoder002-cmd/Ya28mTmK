package net.gotev.uploadservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
public class UploadFile implements Parcelable {
    public static final Parcelable.Creator<UploadFile> CREATOR = new Parcelable.Creator<UploadFile>() { // from class: net.gotev.uploadservice.UploadFile.1
        @Override // android.os.Parcelable.Creator
        public UploadFile createFromParcel(Parcel parcel) {
            return new UploadFile(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public UploadFile[] newArray(int i) {
            return new UploadFile[i];
        }
    };
    protected final File file;
    private LinkedHashMap<String, String> properties;

    private UploadFile(Parcel parcel) {
        this.properties = new LinkedHashMap<>();
        this.file = new File(parcel.readString());
        this.properties = (LinkedHashMap) parcel.readSerializable();
    }

    public UploadFile(String str) throws FileNotFoundException {
        this.properties = new LinkedHashMap<>();
        if (str == null || "".equals(str)) {
            throw new IllegalArgumentException("Please specify a file path!");
        }
        File file = new File(str);
        if (!file.exists()) {
            throw new FileNotFoundException("Could not find file at path: " + str);
        }
        if (!file.isDirectory()) {
            this.file = file;
            return;
        }
        throw new FileNotFoundException("The specified path refers to a directory: " + str);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final String getAbsolutePath() {
        return this.file.getAbsolutePath();
    }

    public final String getName() {
        return this.file.getName();
    }

    public String getProperty(String str) {
        return this.properties.get(str);
    }

    public String getProperty(String str, String str2) {
        String str3 = this.properties.get(str);
        return str3 == null ? str2 : str3;
    }

    public final InputStream getStream() throws FileNotFoundException {
        return new FileInputStream(this.file);
    }

    public long length() {
        return this.file.length();
    }

    public void setProperty(String str, String str2) {
        this.properties.put(str, str2);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.file.getAbsolutePath());
        parcel.writeSerializable(this.properties);
    }
}
