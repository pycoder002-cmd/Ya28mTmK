package org.springframework.http;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public interface HttpInputMessage extends HttpMessage {
    InputStream getBody() throws IOException;
}
