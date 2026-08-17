package org.springframework.http.client;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpMethod;

/* loaded from: classes2.dex */
public class InterceptingClientHttpRequestFactory extends AbstractClientHttpRequestFactoryWrapper {
    private final List<ClientHttpRequestInterceptor> interceptors;

    public InterceptingClientHttpRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory, List<ClientHttpRequestInterceptor> list) {
        super(clientHttpRequestFactory);
        this.interceptors = list == null ? Collections.emptyList() : list;
    }

    @Override // org.springframework.http.client.AbstractClientHttpRequestFactoryWrapper
    protected ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod, ClientHttpRequestFactory clientHttpRequestFactory) {
        return new InterceptingClientHttpRequest(clientHttpRequestFactory, this.interceptors, uri, httpMethod);
    }
}
