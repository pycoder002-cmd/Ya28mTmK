package org.springframework.http.client;

import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class SimpleClientHttpRequestFactory implements ClientHttpRequestFactory {
    private static final int DEFAULT_CHUNK_SIZE = 0;
    private Proxy proxy;
    private boolean bufferRequestBody = true;
    private int chunkSize = 0;
    private int connectTimeout = -1;
    private int readTimeout = -1;
    private boolean outputStreaming = true;
    private boolean reuseConnection = false;

    @Override // org.springframework.http.client.ClientHttpRequestFactory
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        System.setProperty("http.keepAlive", Boolean.toString(this.reuseConnection));
        HttpURLConnection openConnection = openConnection(uri.toURL(), this.proxy);
        prepareConnection(openConnection, httpMethod.name());
        return this.bufferRequestBody ? new SimpleBufferingClientHttpRequest(openConnection, this.outputStreaming) : new SimpleStreamingClientHttpRequest(openConnection, this.chunkSize, this.outputStreaming, this.reuseConnection);
    }

    protected HttpURLConnection openConnection(URL url, Proxy proxy) throws IOException {
        URLConnection openConnection = proxy != null ? url.openConnection(proxy) : url.openConnection();
        Assert.isInstanceOf(HttpURLConnection.class, openConnection);
        return (HttpURLConnection) openConnection;
    }

    protected void prepareConnection(HttpURLConnection httpURLConnection, String str) throws IOException {
        if (this.connectTimeout >= 0) {
            httpURLConnection.setConnectTimeout(this.connectTimeout);
        }
        if (this.readTimeout >= 0) {
            httpURLConnection.setReadTimeout(this.readTimeout);
        }
        httpURLConnection.setDoInput(true);
        if ("GET".equals(str)) {
            httpURLConnection.setInstanceFollowRedirects(true);
        } else {
            httpURLConnection.setInstanceFollowRedirects(false);
        }
        if ("PUT".equals(str) || HttpPost.METHOD_NAME.equals(str) || "PATCH".equals(str)) {
            httpURLConnection.setDoOutput(true);
        } else {
            httpURLConnection.setDoOutput(false);
        }
        httpURLConnection.setRequestMethod(str);
    }

    public void setBufferRequestBody(boolean z) {
        this.bufferRequestBody = z;
    }

    public void setChunkSize(int i) {
        this.chunkSize = i;
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public void setOutputStreaming(boolean z) {
        this.outputStreaming = z;
    }

    public void setProxy(Proxy proxy) {
        this.proxy = proxy;
    }

    public void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public void setReuseConnection(boolean z) {
        this.reuseConnection = z;
    }
}
