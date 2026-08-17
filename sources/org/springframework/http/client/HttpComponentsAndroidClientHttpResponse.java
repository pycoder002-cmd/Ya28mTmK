package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.springframework.http.HttpHeaders;

@Deprecated
/* loaded from: classes2.dex */
final class HttpComponentsAndroidClientHttpResponse extends AbstractClientHttpResponse {
    private HttpHeaders headers;
    private final HttpResponse httpResponse;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpComponentsAndroidClientHttpResponse(HttpResponse httpResponse) {
        this.httpResponse = httpResponse;
    }

    @Override // org.springframework.http.client.AbstractClientHttpResponse
    protected void closeInternal() {
        HttpEntity entity = this.httpResponse.getEntity();
        if (entity != null) {
            try {
                entity.consumeContent();
            } catch (IOException unused) {
            }
        }
    }

    @Override // org.springframework.http.client.AbstractClientHttpResponse
    protected InputStream getBodyInternal() throws IOException {
        HttpEntity entity = this.httpResponse.getEntity();
        if (entity != null) {
            return entity.getContent();
        }
        return null;
    }

    @Override // org.springframework.http.HttpMessage
    public HttpHeaders getHeaders() {
        if (this.headers == null) {
            this.headers = new HttpHeaders();
            for (Header header : this.httpResponse.getAllHeaders()) {
                this.headers.add(header.getName(), header.getValue());
            }
        }
        return this.headers;
    }

    @Override // org.springframework.http.client.ClientHttpResponse
    public int getRawStatusCode() throws IOException {
        return this.httpResponse.getStatusLine().getStatusCode();
    }

    @Override // org.springframework.http.client.ClientHttpResponse
    public String getStatusText() throws IOException {
        return this.httpResponse.getStatusLine().getReasonPhrase();
    }
}
