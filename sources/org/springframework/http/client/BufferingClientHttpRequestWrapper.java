package org.springframework.http.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;

/* loaded from: classes2.dex */
final class BufferingClientHttpRequestWrapper extends AbstractBufferingClientHttpRequest {
    private final ClientHttpRequest request;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BufferingClientHttpRequestWrapper(ClientHttpRequest clientHttpRequest) {
        Assert.notNull(clientHttpRequest, "'request' must not be null");
        this.request = clientHttpRequest;
    }

    @Override // org.springframework.http.client.AbstractBufferingClientHttpRequest
    protected ClientHttpResponse executeInternal(HttpHeaders httpHeaders, byte[] bArr) throws IOException {
        OutputStream body = this.request.getBody();
        this.request.getHeaders().putAll(httpHeaders);
        StreamUtils.copy(bArr, body);
        return new BufferingClientHttpResponseWrapper(this.request.execute());
    }

    @Override // org.springframework.http.HttpRequest
    public HttpMethod getMethod() {
        return this.request.getMethod();
    }

    @Override // org.springframework.http.HttpRequest
    public URI getURI() {
        return this.request.getURI();
    }
}
