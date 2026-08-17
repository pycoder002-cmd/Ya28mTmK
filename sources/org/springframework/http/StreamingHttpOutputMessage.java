package org.springframework.http;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public interface StreamingHttpOutputMessage extends HttpOutputMessage {

    /* loaded from: classes2.dex */
    public interface Body {
        void writeTo(OutputStream outputStream) throws IOException;
    }

    void setBody(Body body);
}
