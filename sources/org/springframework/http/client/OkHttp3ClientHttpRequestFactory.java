package org.springframework.http.client;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class OkHttp3ClientHttpRequestFactory implements ClientHttpRequestFactory, DisposableBean {
    private OkHttpClient client;
    private final boolean defaultClient;

    public OkHttp3ClientHttpRequestFactory() {
        this.client = new OkHttpClient();
        this.defaultClient = true;
    }

    public OkHttp3ClientHttpRequestFactory(OkHttpClient okHttpClient) {
        Assert.notNull(okHttpClient, "'client' must not be null");
        this.client = okHttpClient;
        this.defaultClient = false;
    }

    private OkHttp3ClientHttpRequest createRequestInternal(URI uri, HttpMethod httpMethod) {
        return new OkHttp3ClientHttpRequest(this.client, uri, httpMethod);
    }

    @Override // org.springframework.http.client.ClientHttpRequestFactory
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) {
        return createRequestInternal(uri, httpMethod);
    }

    @Override // org.springframework.beans.factory.DisposableBean
    public void destroy() throws Exception {
        if (this.defaultClient) {
            if (this.client.cache() != null) {
                this.client.cache().close();
            }
            this.client.dispatcher().executorService().shutdown();
        }
    }

    public void setConnectTimeout(int i) {
        this.client = this.client.newBuilder().connectTimeout(i, TimeUnit.MILLISECONDS).build();
    }

    public void setReadTimeout(int i) {
        this.client = this.client.newBuilder().readTimeout(i, TimeUnit.MILLISECONDS).build();
    }

    public void setWriteTimeout(int i) {
        this.client = this.client.newBuilder().writeTimeout(i, TimeUnit.MILLISECONDS).build();
    }
}
