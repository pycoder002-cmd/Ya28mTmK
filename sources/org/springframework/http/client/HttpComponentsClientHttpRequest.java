package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntityHC4;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
final class HttpComponentsClientHttpRequest extends AbstractBufferingClientHttpRequest {
    private final CloseableHttpClient httpClient;
    private final HttpContext httpContext;
    private final HttpUriRequest httpRequest;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpComponentsClientHttpRequest(CloseableHttpClient closeableHttpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) {
        this.httpClient = closeableHttpClient;
        this.httpRequest = httpUriRequest;
        this.httpContext = httpContext;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void addHeaders(HttpUriRequest httpUriRequest, HttpHeaders httpHeaders) {
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String key = entry.getKey();
            if ("Cookie".equalsIgnoreCase(key)) {
                httpUriRequest.addHeader(key, StringUtils.collectionToDelimitedString(entry.getValue(), "; "));
            } else if (!"Content-Length".equalsIgnoreCase(key) && !"Transfer-Encoding".equalsIgnoreCase(key)) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    httpUriRequest.addHeader(key, it.next());
                }
            }
        }
    }

    @Override // org.springframework.http.client.AbstractBufferingClientHttpRequest
    protected ClientHttpResponse executeInternal(HttpHeaders httpHeaders, byte[] bArr) throws IOException {
        addHeaders(this.httpRequest, httpHeaders);
        if (this.httpRequest instanceof HttpEntityEnclosingRequest) {
            ((HttpEntityEnclosingRequest) this.httpRequest).setEntity(new ByteArrayEntityHC4(bArr));
        }
        return new HttpComponentsClientHttpResponse(this.httpClient.execute(this.httpRequest, this.httpContext));
    }

    HttpContext getHttpContext() {
        return this.httpContext;
    }

    @Override // org.springframework.http.HttpRequest
    public HttpMethod getMethod() {
        return HttpMethod.valueOf(this.httpRequest.getMethod());
    }

    @Override // org.springframework.http.HttpRequest
    public URI getURI() {
        return this.httpRequest.getURI();
    }
}
