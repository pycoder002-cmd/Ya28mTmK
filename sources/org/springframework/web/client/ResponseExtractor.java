package org.springframework.web.client;

import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;

/* loaded from: classes2.dex */
public interface ResponseExtractor<T> {
    T extractData(ClientHttpResponse clientHttpResponse) throws IOException;
}
