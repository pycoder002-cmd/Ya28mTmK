package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.Configurable;
import org.apache.http.client.methods.HttpDeleteHC4;
import org.apache.http.client.methods.HttpGetHC4;
import org.apache.http.client.methods.HttpHeadHC4;
import org.apache.http.client.methods.HttpOptionsHC4;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPostHC4;
import org.apache.http.client.methods.HttpPutHC4;
import org.apache.http.client.methods.HttpTraceHC4;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class HttpComponentsClientHttpRequestFactory implements ClientHttpRequestFactory, DisposableBean {
    private boolean bufferRequestBody;
    private int connectTimeout;
    private CloseableHttpClient httpClient;
    private int socketTimeout;

    public HttpComponentsClientHttpRequestFactory() {
        this(HttpClients.createSystem());
    }

    public HttpComponentsClientHttpRequestFactory(HttpClient httpClient) {
        this.bufferRequestBody = true;
        Assert.notNull(httpClient, "'httpClient' must not be null");
        Assert.isInstanceOf(CloseableHttpClient.class, httpClient, "'httpClient' is not of type CloseableHttpClient");
        this.httpClient = (CloseableHttpClient) httpClient;
    }

    private void setLegacyConnectionTimeout(HttpClient httpClient, int i) {
        if (AbstractHttpClient.class.isInstance(httpClient)) {
            httpClient.getParams().setIntParameter("http.connection.timeout", i);
        }
    }

    private void setLegacySocketTimeout(HttpClient httpClient, int i) {
        if (AbstractHttpClient.class.isInstance(httpClient)) {
            httpClient.getParams().setIntParameter("http.socket.timeout", i);
        }
    }

    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
        return null;
    }

    protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
        switch (httpMethod) {
            case GET:
                return new HttpGetHC4(uri);
            case DELETE:
                return new HttpDeleteHC4(uri);
            case HEAD:
                return new HttpHeadHC4(uri);
            case OPTIONS:
                return new HttpOptionsHC4(uri);
            case POST:
                return new HttpPostHC4(uri);
            case PUT:
                return new HttpPutHC4(uri);
            case TRACE:
                return new HttpTraceHC4(uri);
            case PATCH:
                return new HttpPatch(uri);
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + httpMethod);
        }
    }

    @Override // org.springframework.http.client.ClientHttpRequestFactory
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        CloseableHttpClient httpClient = getHttpClient();
        Assert.state(httpClient != null, "Synchronous execution requires an HttpClient to be set");
        Configurable createHttpUriRequest = createHttpUriRequest(httpMethod, uri);
        postProcessHttpRequest(createHttpUriRequest);
        HttpClientContext createHttpContext = createHttpContext(httpMethod, uri);
        if (createHttpContext == null) {
            createHttpContext = HttpClientContext.create();
        }
        if (createHttpContext.getAttribute("http.request-config") == null) {
            RequestConfig config = createHttpUriRequest instanceof Configurable ? createHttpUriRequest.getConfig() : null;
            if (config == null) {
                config = (this.socketTimeout > 0 || this.connectTimeout > 0) ? RequestConfig.custom().setConnectTimeout(this.connectTimeout).setSocketTimeout(this.socketTimeout).build() : RequestConfig.DEFAULT;
            }
            createHttpContext.setAttribute("http.request-config", config);
        }
        return this.bufferRequestBody ? new HttpComponentsClientHttpRequest(httpClient, createHttpUriRequest, createHttpContext) : new HttpComponentsStreamingClientHttpRequest(httpClient, createHttpUriRequest, createHttpContext);
    }

    @Override // org.springframework.beans.factory.DisposableBean
    public void destroy() throws Exception {
        this.httpClient.close();
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    protected void postProcessHttpRequest(HttpUriRequest httpUriRequest) {
    }

    public void setBufferRequestBody(boolean z) {
        this.bufferRequestBody = z;
    }

    public void setConnectTimeout(int i) {
        Assert.isTrue(i >= 0, "Timeout must be a non-negative value");
        this.connectTimeout = i;
        setLegacyConnectionTimeout(getHttpClient(), i);
    }

    public void setHttpClient(HttpClient httpClient) {
        Assert.isInstanceOf(CloseableHttpClient.class, httpClient, "'httpClient' is not of type CloseableHttpClient");
        this.httpClient = (CloseableHttpClient) httpClient;
    }

    public void setReadTimeout(int i) {
        Assert.isTrue(i >= 0, "Timeout must be a non-negative value");
        this.socketTimeout = i;
        setLegacySocketTimeout(getHttpClient(), i);
    }
}
