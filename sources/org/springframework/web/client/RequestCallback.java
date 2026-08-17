package org.springframework.web.client;

import java.io.IOException;
import org.springframework.http.client.ClientHttpRequest;

/* loaded from: classes2.dex */
public interface RequestCallback {
    void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException;
}
