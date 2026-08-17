package net.gotev.uploadservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.LinkedHashMap;

/* loaded from: classes2.dex */
public class ServerResponse implements Parcelable {
    public static final Parcelable.Creator<ServerResponse> CREATOR = new Parcelable.Creator<ServerResponse>() { // from class: net.gotev.uploadservice.ServerResponse.1
        @Override // android.os.Parcelable.Creator
        public ServerResponse createFromParcel(Parcel parcel) {
            return new ServerResponse(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ServerResponse[] newArray(int i) {
            return new ServerResponse[i];
        }
    };
    private byte[] body;
    private LinkedHashMap<String, String> headers;
    private int httpCode;

    /* JADX INFO: Access modifiers changed from: protected */
    public ServerResponse(int i, byte[] bArr, LinkedHashMap<String, String> linkedHashMap) {
        this.httpCode = i;
        if (bArr == null || bArr.length <= 0) {
            this.body = new byte[1];
        } else {
            this.body = bArr;
        }
        if (linkedHashMap == null || linkedHashMap.isEmpty()) {
            this.headers = new LinkedHashMap<>(1);
        } else {
            this.headers = linkedHashMap;
        }
    }

    protected ServerResponse(Parcel parcel) {
        this.httpCode = parcel.readInt();
        this.body = new byte[parcel.readInt()];
        parcel.readByteArray(this.body);
        this.headers = (LinkedHashMap) parcel.readSerializable();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public byte[] getBody() {
        return this.body;
    }

    public String getBodyAsString() {
        return new String(this.body);
    }

    public LinkedHashMap<String, String> getHeaders() {
        return this.headers;
    }

    public int getHttpCode() {
        return this.httpCode;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.httpCode);
        parcel.writeInt(this.body.length);
        parcel.writeByteArray(this.body);
        parcel.writeSerializable(this.headers);
    }
}
