package org.springframework.http.client;

import java.io.IOException;
import org.springframework.http.HttpRequest;

/* loaded from: classes2.dex */
public interface ClientHttpRequestInterceptor {
    ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bArr, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException;
}
