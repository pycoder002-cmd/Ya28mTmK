package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Deprecated
/* loaded from: classes2.dex */
final class HttpComponentsAndroidClientHttpRequest extends AbstractBufferingClientHttpRequest {
    private final HttpClient httpClient;
    private final HttpContext httpContext;
    private final HttpUriRequest httpRequest;

    public HttpComponentsAndroidClientHttpRequest(HttpClient httpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) {
        this.httpClient = httpClient;
        this.httpRequest = httpUriRequest;
        this.httpContext = httpContext;
    }

    @Override // org.springframework.http.client.AbstractBufferingClientHttpRequest
    public ClientHttpResponse executeInternal(HttpHeaders httpHeaders, byte[] bArr) throws IOException {
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String key = entry.getKey();
            if (!key.equalsIgnoreCase("Content-Length") && !key.equalsIgnoreCase("Transfer-Encoding")) {
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    this.httpRequest.addHeader(key, it.next());
                }
            }
        }
        if (this.httpRequest instanceof HttpEntityEnclosingRequest) {
            ((HttpEntityEnclosingRequest) this.httpRequest).setEntity(new ByteArrayEntity(bArr));
        }
        return new HttpComponentsAndroidClientHttpResponse(this.httpClient.execute(this.httpRequest, this.httpContext));
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
