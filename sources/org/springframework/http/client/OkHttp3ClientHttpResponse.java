package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import okhttp3.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
class OkHttp3ClientHttpResponse extends AbstractClientHttpResponse {
    private HttpHeaders headers;
    private final Response response;

    public OkHttp3ClientHttpResponse(Response response) {
        Assert.notNull(response, "'response' must not be null");
        this.response = response;
    }

    @Override // org.springframework.http.client.AbstractClientHttpResponse
    public void closeInternal() {
        this.response.body().close();
    }

    @Override // org.springframework.http.client.AbstractClientHttpResponse
    public InputStream getBodyInternal() throws IOException {
        return this.response.body().byteStream();
    }

    @Override // org.springframework.http.HttpMessage
    public HttpHeaders getHeaders() {
        if (this.headers == null) {
            HttpHeaders httpHeaders = new HttpHeaders();
            for (String str : this.response.headers().names()) {
                Iterator<String> it = this.response.headers(str).iterator();
                while (it.hasNext()) {
                    httpHeaders.add(str, it.next());
                }
            }
            this.headers = httpHeaders;
        }
        return this.headers;
    }

    @Override // org.springframework.http.client.ClientHttpResponse
    public int getRawStatusCode() {
        return this.response.code();
    }

    @Override // org.springframework.http.client.ClientHttpResponse
    public String getStatusText() {
        return this.response.message();
    }
}
