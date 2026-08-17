package net.gotev.uploadservice.http.impl;

import com.google.firebase.appindexing.Indexable;
import java.io.IOException;
import net.gotev.uploadservice.http.HttpConnection;
import net.gotev.uploadservice.http.HttpStack;

/* loaded from: classes2.dex */
public class HurlStack implements HttpStack {
    private int mConnectTimeout;
    private boolean mFollowRedirects;
    private int mReadTimeout;
    private boolean mUseCaches;

    public HurlStack() {
        this.mFollowRedirects = true;
        this.mUseCaches = false;
        this.mConnectTimeout = 15000;
        this.mReadTimeout = Indexable.MAX_BYTE_SIZE;
    }

    public HurlStack(boolean z, boolean z2, int i, int i2) {
        this.mFollowRedirects = z;
        this.mUseCaches = z2;
        this.mConnectTimeout = i;
        this.mReadTimeout = i2;
    }

    @Override // net.gotev.uploadservice.http.HttpStack
    public HttpConnection createNewConnection(String str, String str2) throws IOException {
        return new HurlStackConnection(str, str2, this.mFollowRedirects, this.mUseCaches, this.mConnectTimeout, this.mReadTimeout);
    }
}
