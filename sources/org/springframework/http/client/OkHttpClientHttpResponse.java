package org.springframework.http.client;

import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;

@Deprecated
/* loaded from: classes2.dex */
class OkHttpClientHttpResponse extends AbstractClientHttpResponse {
    private HttpHeaders headers;
    private final Response response;

    public OkHttpClientHttpResponse(Response response) {
        Assert.notNull(response, "'response' must not be null");
        this.response = response;
    }

    @Override // org.springframework.http.client.AbstractClientHttpResponse
    public void closeInternal() {
        try {
            this.response.body().close();
        } catch (IOException unused) {
        }
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
                Iterator it = this.response.headers(str).iterator();
                while (it.hasNext()) {
                    httpHeaders.add(str, (String) it.next());
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
