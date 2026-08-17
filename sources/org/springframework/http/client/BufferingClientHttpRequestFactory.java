package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;

/* loaded from: classes2.dex */
public class BufferingClientHttpRequestFactory extends AbstractClientHttpRequestFactoryWrapper {
    public BufferingClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory) {
        super(clientHttpRequestFactory);
    }

    @Override // org.springframework.http.client.AbstractClientHttpRequestFactoryWrapper
    protected ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod, ClientHttpRequestFactory clientHttpRequestFactory) throws IOException {
        ClientHttpRequest createRequest = clientHttpRequestFactory.createRequest(uri, httpMethod);
        return shouldBuffer(uri, httpMethod) ? new BufferingClientHttpRequestWrapper(createRequest) : createRequest;
    }

    protected boolean shouldBuffer(URI uri, HttpMethod httpMethod) {
        return true;
    }
}
