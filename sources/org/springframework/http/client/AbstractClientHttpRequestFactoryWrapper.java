package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public abstract class AbstractClientHttpRequestFactoryWrapper implements ClientHttpRequestFactory {
    private final ClientHttpRequestFactory requestFactory;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractClientHttpRequestFactoryWrapper(ClientHttpRequestFactory clientHttpRequestFactory) {
        Assert.notNull(clientHttpRequestFactory, "'requestFactory' must not be null");
        this.requestFactory = clientHttpRequestFactory;
    }

    @Override // org.springframework.http.client.ClientHttpRequestFactory
    public final ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        return createRequest(uri, httpMethod, this.requestFactory);
    }

    protected abstract ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod, ClientHttpRequestFactory clientHttpRequestFactory) throws IOException;
}
