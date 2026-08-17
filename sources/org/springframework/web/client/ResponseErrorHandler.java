package org.springframework.web.client;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;

/* loaded from: classes2.dex */
public interface ResponseErrorHandler {
    void handleError(ClientHttpResponse clientHttpResponse) throws IOException;

    boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException;
}
