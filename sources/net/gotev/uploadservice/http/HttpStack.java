package net.gotev.uploadservice.http;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface HttpStack {
    HttpConnection createNewConnection(String str, String str2) throws IOException;
}
