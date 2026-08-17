package net.gotev.uploadservice;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class HttpUploadRequest extends UploadRequest {
    protected final HttpUploadTaskParameters httpParams;

    public HttpUploadRequest(Context context, String str, String str2) {
        super(context, str, str2);
        this.httpParams = new HttpUploadTaskParameters();
    }

    public HttpUploadRequest addArrayParameter(String str, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            this.httpParams.addRequestParameter(str, it.next());
        }
        return this;
    }

    public HttpUploadRequest addArrayParameter(String str, String... strArr) {
        for (String str2 : strArr) {
            this.httpParams.addRequestParameter(str, str2);
        }
        return this;
    }

    public HttpUploadRequest addHeader(String str, String str2) {
        this.httpParams.addRequestHeader(str, str2);
        return this;
    }

    public HttpUploadRequest addParameter(String str, String str2) {
        this.httpParams.addRequestParameter(str, str2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.gotev.uploadservice.UploadRequest
    public void initializeIntent(Intent intent) {
        super.initializeIntent(intent);
        intent.putExtra("httpTaskParameters", this.httpParams);
    }

    public HttpUploadRequest setBasicAuth(String str, String str2) {
        String encodeToString = Base64.encodeToString((str + ":" + str2).getBytes(), 2);
        this.httpParams.addRequestHeader("Authorization", "Basic " + encodeToString);
        return this;
    }

    public HttpUploadRequest setCustomUserAgent(String str) {
        this.httpParams.setCustomUserAgent(str);
        return this;
    }

    public HttpUploadRequest setMethod(String str) {
        this.httpParams.setMethod(str);
        return this;
    }

    public HttpUploadRequest setUsesFixedLengthStreamingMode(boolean z) {
        this.httpParams.setUsesFixedLengthStreamingMode(z);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.gotev.uploadservice.UploadRequest
    public void validate() throws IllegalArgumentException, MalformedURLException {
        super.validate();
        if (!this.params.getServerUrl().startsWith("http://") && !this.params.getServerUrl().startsWith("https://")) {
            throw new IllegalArgumentException("Specify either http:// or https:// as protocol");
        }
        new URL(this.params.getServerUrl());
    }
}
