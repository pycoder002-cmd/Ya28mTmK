package org.springframework.http.client.support;

import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.net.URI;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsAndroidClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.client.OkHttpClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/* loaded from: classes2.dex */
public abstract class HttpAccessor {
    private static final String TAG = "HttpAccessor";
    private static final boolean httpClient43Present = ClassUtils.isPresent("org.apache.http.impl.client.CloseableHttpClient", HttpAccessor.class.getClassLoader());
    private static final boolean okHttp3Present = ClassUtils.isPresent("okhttp3.OkHttpClient", HttpAccessor.class.getClassLoader());
    private static final boolean okHttpPresent = ClassUtils.isPresent("com.squareup.okhttp.OkHttpClient", HttpAccessor.class.getClassLoader());
    private ClientHttpRequestFactory requestFactory;

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpAccessor() {
        if (httpClient43Present) {
            this.requestFactory = new HttpComponentsClientHttpRequestFactory();
            return;
        }
        if (okHttp3Present) {
            this.requestFactory = new OkHttp3ClientHttpRequestFactory();
            return;
        }
        if (okHttpPresent) {
            this.requestFactory = new OkHttpClientHttpRequestFactory();
        } else if (Build.VERSION.SDK_INT >= 9) {
            this.requestFactory = new SimpleClientHttpRequestFactory();
        } else {
            this.requestFactory = new HttpComponentsAndroidClientHttpRequestFactory();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        ClientHttpRequest createRequest = getRequestFactory().createRequest(uri, httpMethod);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Created " + httpMethod.name() + " request for \"" + uri + "\"");
        }
        return createRequest;
    }

    public ClientHttpRequestFactory getRequestFactory() {
        return this.requestFactory;
    }

    public void setRequestFactory(ClientHttpRequestFactory clientHttpRequestFactory) {
        Assert.notNull(clientHttpRequestFactory, "'requestFactory' must not be null");
        this.requestFactory = clientHttpRequestFactory;
    }
}
