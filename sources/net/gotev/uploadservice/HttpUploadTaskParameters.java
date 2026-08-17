package net.gotev.uploadservice;

import android.os.Parcel;
import android.os.Parcelable;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public final class HttpUploadTaskParameters implements Parcelable {
    public static final Parcelable.Creator<HttpUploadTaskParameters> CREATOR = new Parcelable.Creator<HttpUploadTaskParameters>() { // from class: net.gotev.uploadservice.HttpUploadTaskParameters.1
        @Override // android.os.Parcelable.Creator
        public HttpUploadTaskParameters createFromParcel(Parcel parcel) {
            return new HttpUploadTaskParameters(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public HttpUploadTaskParameters[] newArray(int i) {
            return new HttpUploadTaskParameters[i];
        }
    };
    protected static final String PARAM_HTTP_TASK_PARAMETERS = "httpTaskParameters";
    private String customUserAgent;
    private String method;
    private ArrayList<NameValue> requestHeaders;
    private ArrayList<NameValue> requestParameters;
    private boolean usesFixedLengthStreamingMode;

    public HttpUploadTaskParameters() {
        this.method = HttpPost.METHOD_NAME;
        this.usesFixedLengthStreamingMode = true;
        this.requestHeaders = new ArrayList<>();
        this.requestParameters = new ArrayList<>();
    }

    private HttpUploadTaskParameters(Parcel parcel) {
        this.method = HttpPost.METHOD_NAME;
        this.usesFixedLengthStreamingMode = true;
        this.requestHeaders = new ArrayList<>();
        this.requestParameters = new ArrayList<>();
        this.method = parcel.readString();
        this.customUserAgent = parcel.readString();
        this.usesFixedLengthStreamingMode = parcel.readByte() == 1;
        parcel.readList(this.requestHeaders, NameValue.class.getClassLoader());
        parcel.readList(this.requestParameters, NameValue.class.getClassLoader());
    }

    public void addRequestHeader(String str, String str2) {
        this.requestHeaders.add(new NameValue(str, str2));
    }

    public void addRequestParameter(String str, String str2) {
        this.requestParameters.add(new NameValue(str, str2));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCustomUserAgent() {
        return this.customUserAgent;
    }

    public String getMethod() {
        return this.method;
    }

    public List<NameValue> getRequestHeaders() {
        return this.requestHeaders;
    }

    public List<NameValue> getRequestParameters() {
        return this.requestParameters;
    }

    public boolean isCustomUserAgentDefined() {
        return (this.customUserAgent == null || "".equals(this.customUserAgent)) ? false : true;
    }

    public boolean isUsesFixedLengthStreamingMode() {
        return this.usesFixedLengthStreamingMode;
    }

    public HttpUploadTaskParameters setCustomUserAgent(String str) {
        this.customUserAgent = str;
        return this;
    }

    public HttpUploadTaskParameters setMethod(String str) {
        if (str != null && str.length() > 0) {
            this.method = str;
        }
        return this;
    }

    public HttpUploadTaskParameters setUsesFixedLengthStreamingMode(boolean z) {
        this.usesFixedLengthStreamingMode = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.method);
        parcel.writeString(this.customUserAgent);
        parcel.writeByte(this.usesFixedLengthStreamingMode ? (byte) 1 : (byte) 0);
        parcel.writeList(this.requestHeaders);
        parcel.writeList(this.requestParameters);
    }
}
