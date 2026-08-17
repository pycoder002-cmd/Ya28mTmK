package org.springframework.http;

import java.net.URI;

/* loaded from: classes2.dex */
public interface HttpRequest extends HttpMessage {
    HttpMethod getMethod();

    URI getURI();
}
