package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtilsHC4;
import org.springframework.http.HttpHeaders;

/* loaded from: classes2.dex */
final class HttpComponentsClientHttpResponse extends AbstractClientHttpResponse {
    private HttpHeaders headers;
    private final CloseableHttpResponse httpResponse;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpComponentsClientHttpResponse(CloseableHttpResponse closeableHttpResponse) {
        this.httpResponse = closeableHttpResponse;
    }

    @Override // org.springframework.http.client.AbstractClientHttpResponse
    public void closeInternal() {
        try {
            try {
                EntityUtilsHC4.consume(this.httpResponse.getEntity());
                this.httpResponse.close();
            } catch (Throwable th) {
                this.httpResponse.close();
                throw th;
            }
        } catch (IOException unused) {
        }
    }

    @Override // org.springframework.http.client.AbstractClientHttpResponse
    public InputStream getBodyInternal() throws IOException {
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
