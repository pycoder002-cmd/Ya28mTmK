package org.springframework.http.client.support;

import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class HttpRequestWrapper implements HttpRequest {
    private final HttpRequest request;

    public HttpRequestWrapper(HttpRequest httpRequest) {
        Assert.notNull(httpRequest, "'request' must not be null");
        this.request = httpRequest;
    }

    @Override // org.springframework.http.HttpMessage
    public HttpHeaders getHeaders() {
        return this.request.getHeaders();
    }

    @Override // org.springframework.http.HttpRequest
    public HttpMethod getMethod() {
        return this.request.getMethod();
    }

    public HttpRequest getRequest() {
        return this.request;
    }

    @Override // org.springframework.http.HttpRequest
    public URI getURI() {
        return this.request.getURI();
    }
}
