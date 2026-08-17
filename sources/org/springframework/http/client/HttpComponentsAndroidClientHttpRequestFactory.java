package org.springframework.http.client;

import java.io.IOException;
import java.net.URI;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

@Deprecated
/* loaded from: classes2.dex */
public class HttpComponentsAndroidClientHttpRequestFactory implements ClientHttpRequestFactory, DisposableBean {
    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 5;
    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 100;
    private static final int DEFAULT_READ_TIMEOUT_MILLISECONDS = 60000;
    private HttpClient httpClient;

    public HttpComponentsAndroidClientHttpRequestFactory() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 100);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(5));
        this.httpClient = new DefaultHttpClient(threadSafeClientConnManager, null);
        setReadTimeout(60000);
    }

    public HttpComponentsAndroidClientHttpRequestFactory(HttpClient httpClient) {
        Assert.notNull(httpClient, "HttpClient must not be null");
        this.httpClient = httpClient;
    }

    protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
        return null;
    }

    protected HttpUriRequest createHttpRequest(HttpMethod httpMethod, URI uri) {
        switch (httpMethod) {
            case GET:
                return new HttpGet(uri);
            case DELETE:
                return new HttpDelete(uri);
            case HEAD:
                return new HttpHead(uri);
            case OPTIONS:
                return new HttpOptions(uri);
            case POST:
                return new HttpPost(uri);
            case PUT:
                return new HttpPut(uri);
            case TRACE:
                return new HttpTrace(uri);
            default:
                throw new IllegalArgumentException("Invalid HTTP method: " + httpMethod);
        }
    }

    @Override // org.springframework.http.client.ClientHttpRequestFactory
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {
        HttpUriRequest createHttpRequest = createHttpRequest(httpMethod, uri);
        postProcessHttpRequest(createHttpRequest);
        return new HttpComponentsAndroidClientHttpRequest(getHttpClient(), createHttpRequest, createHttpContext(httpMethod, uri));
    }

    @Override // org.springframework.beans.factory.DisposableBean
    public void destroy() {
        getHttpClient().getConnectionManager().shutdown();
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    protected void postProcessHttpRequest(HttpUriRequest httpUriRequest) {
        HttpProtocolParams.setUseExpectContinue(httpUriRequest.getParams(), false);
    }

    public void setConnectTimeout(int i) {
        Assert.isTrue(i >= 0, "Timeout must be a non-negative value");
        getHttpClient().getParams().setIntParameter("http.connection.timeout", i);
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setReadTimeout(int i) {
        Assert.isTrue(i >= 0, "Timeout must be a non-negative value");
        getHttpClient().getParams().setIntParameter("http.socket.timeout", i);
    }
}
