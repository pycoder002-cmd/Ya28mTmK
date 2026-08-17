package org.springframework.http.client;

import com.squareup.okhttp.OkHttpClient;
import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

@Deprecated
/* loaded from: classes2.dex */
public class OkHttpClientHttpRequestFactory implements ClientHttpRequestFactory, DisposableBean {
    private final OkHttpClient client;
    private final boolean defaultClient;

    public OkHttpClientHttpRequestFactory() {
        this.client = new OkHttpClient();
        this.defaultClient = true;
    }

    public OkHttpClientHttpRequestFactory(OkHttpClient okHttpClient) {
        Assert.notNull(okHttpClient, "'client' must not be null");
        this.client = okHttpClient;
        this.defaultClient = false;
    }

    private OkHttpClientHttpRequest createRequestInternal(URI uri, HttpMethod httpMethod) {
        return new OkHttpClientHttpRequest(this.client, uri, httpMethod);
    }

    @Override // org.springframework.http.client.ClientHttpRequestFactory
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) {
        return createRequestInternal(uri, httpMethod);
    }

    @Override // org.springframework.beans.factory.DisposableBean
    public void destroy() throws Exception {
        if (this.defaultClient) {
            if (this.client.getCache() != null) {
                this.client.getCache().close();
            }
            this.client.getDispatcher().getExecutorService().shutdown();
        }
    }

    public void setConnectTimeout(int i) {
        this.client.setConnectTimeout(i, TimeUnit.MILLISECONDS);
    }

    public void setReadTimeout(int i) {
        this.client.setReadTimeout(i, TimeUnit.MILLISECONDS);
    }

    public void setWriteTimeout(int i) {
        this.client.setWriteTimeout(i, TimeUnit.MILLISECONDS);
    }
}
