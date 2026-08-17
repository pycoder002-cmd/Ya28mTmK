package net.gotev.uploadservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public final class NameValue implements Parcelable {
    public static final Parcelable.Creator<NameValue> CREATOR = new Parcelable.Creator<NameValue>() { // from class: net.gotev.uploadservice.NameValue.1
        @Override // android.os.Parcelable.Creator
        public NameValue createFromParcel(Parcel parcel) {
            return new NameValue(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public NameValue[] newArray(int i) {
            return new NameValue[i];
        }
    };
    private static final String NEW_LINE = "\r\n";
    private final Charset US_ASCII;
    private final Charset UTF8;
    private final String name;
    private final String value;

    private NameValue(Parcel parcel) {
        this.US_ASCII = Charset.forName("US-ASCII");
        this.UTF8 = Charset.forName("UTF-8");
        this.name = parcel.readString();
        this.value = parcel.readString();
    }

    public NameValue(String str, String str2) {
        this.US_ASCII = Charset.forName("US-ASCII");
        this.UTF8 = Charset.forName("UTF-8");
        this.name = str;
        this.value = str2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof NameValue)) {
            return false;
        }
        NameValue nameValue = (NameValue) obj;
        return this.name.equals(nameValue.name) && this.value.equals(nameValue.value);
    }

    public byte[] getMultipartBytes(boolean z) throws UnsupportedEncodingException {
        return ("Content-Disposition: form-data; name=\"" + this.name + "\"" + NEW_LINE + NEW_LINE + this.value).getBytes(z ? this.UTF8 : this.US_ASCII);
    }

    public final String getName() {
        return this.name;
    }

    public final String getValue() {
        return this.value;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.value);
    }
}
