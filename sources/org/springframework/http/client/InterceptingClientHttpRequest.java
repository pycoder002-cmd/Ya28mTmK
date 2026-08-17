package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.util.StreamUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class InterceptingClientHttpRequest extends AbstractBufferingClientHttpRequest {
    private final List<ClientHttpRequestInterceptor> interceptors;
    private HttpMethod method;
    private final ClientHttpRequestFactory requestFactory;
    private URI uri;

    /* loaded from: classes2.dex */
    private class RequestExecution implements ClientHttpRequestExecution {
        private final Iterator<ClientHttpRequestInterceptor> iterator;

        private RequestExecution() {
            this.iterator = InterceptingClientHttpRequest.this.interceptors.iterator();
        }

        @Override // org.springframework.http.client.ClientHttpRequestExecution
        public ClientHttpResponse execute(HttpRequest httpRequest, byte[] bArr) throws IOException {
            if (this.iterator.hasNext()) {
                return this.iterator.next().intercept(httpRequest, bArr, this);
            }
            ClientHttpRequest createRequest = InterceptingClientHttpRequest.this.requestFactory.createRequest(httpRequest.getURI(), httpRequest.getMethod());
            createRequest.getHeaders().putAll(httpRequest.getHeaders());
            if (bArr.length > 0) {
                StreamUtils.copy(bArr, createRequest.getBody());
            }
            return createRequest.execute();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public InterceptingClientHttpRequest(ClientHttpRequestFactory clientHttpRequestFactory, List<ClientHttpRequestInterceptor> list, URI uri, HttpMethod httpMethod) {
        this.requestFactory = clientHttpRequestFactory;
        this.interceptors = list;
        this.method = httpMethod;
        this.uri = uri;
    }

    @Override // org.springframework.http.client.AbstractBufferingClientHttpRequest
    protected final ClientHttpResponse executeInternal(HttpHeaders httpHeaders, byte[] bArr) throws IOException {
        return new RequestExecution().execute(this, bArr);
    }

    @Override // org.springframework.http.HttpRequest
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // org.springframework.http.HttpRequest
    public URI getURI() {
        return this.uri;
    }
}
