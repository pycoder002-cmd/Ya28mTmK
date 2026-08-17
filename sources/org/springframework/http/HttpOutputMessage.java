package org.springframework.http;

import java.io.IOException;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public interface HttpOutputMessage extends HttpMessage {
    OutputStream getBody() throws IOException;
}
