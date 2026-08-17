package cz.msebera.android.httpclient.impl.execchain;

import cz.msebera.android.httpclient.ConnectionReuseStrategy;
import cz.msebera.android.httpclient.ProtocolException;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.protocol.RequestClientConnControl;
import cz.msebera.android.httpclient.client.utils.URIUtils;
import cz.msebera.android.httpclient.conn.ConnectionKeepAliveStrategy;
import cz.msebera.android.httpclient.conn.HttpClientConnectionManager;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.extras.HttpClientAndroidLog;
import cz.msebera.android.httpclient.protocol.HttpProcessor;
import cz.msebera.android.httpclient.protocol.HttpRequestExecutor;
import cz.msebera.android.httpclient.protocol.ImmutableHttpProcessor;
import cz.msebera.android.httpclient.protocol.RequestContent;
import cz.msebera.android.httpclient.protocol.RequestTargetHost;
import cz.msebera.android.httpclient.protocol.RequestUserAgent;
import cz.msebera.android.httpclient.util.Args;
import cz.msebera.android.httpclient.util.VersionInfo;
import java.net.URI;
import java.net.URISyntaxException;

@Immutable
/* loaded from: classes.dex */
public class MinimalClientExec implements ClientExecChain {
    private final HttpClientConnectionManager connManager;
    private final HttpProcessor httpProcessor;
    private final ConnectionKeepAliveStrategy keepAliveStrategy;
    public HttpClientAndroidLog log = new HttpClientAndroidLog(getClass());
    private final HttpRequestExecutor requestExecutor;
    private final ConnectionReuseStrategy reuseStrategy;

    public MinimalClientExec(HttpRequestExecutor httpRequestExecutor, HttpClientConnectionManager httpClientConnectionManager, ConnectionReuseStrategy connectionReuseStrategy, ConnectionKeepAliveStrategy connectionKeepAliveStrategy) {
        Args.notNull(httpRequestExecutor, "HTTP request executor");
        Args.notNull(httpClientConnectionManager, "Client connection manager");
        Args.notNull(connectionReuseStrategy, "Connection reuse strategy");
        Args.notNull(connectionKeepAliveStrategy, "Connection keep alive strategy");
        this.httpProcessor = new ImmutableHttpProcessor(new RequestContent(), new RequestTargetHost(), new RequestClientConnControl(), new RequestUserAgent(VersionInfo.getUserAgent("Apache-HttpClient", "cz.msebera.android.httpclient.client", getClass())));
        this.requestExecutor = httpRequestExecutor;
        this.connManager = httpClientConnectionManager;
        this.reuseStrategy = connectionReuseStrategy;
        this.keepAliveStrategy = connectionKeepAliveStrategy;
    }

    static void rewriteRequestURI(HttpRequestWrapper httpRequestWrapper, HttpRoute httpRoute) throws ProtocolException {
        try {
            URI uri = httpRequestWrapper.getURI();
            if (uri != null) {
                httpRequestWrapper.setURI(uri.isAbsolute() ? URIUtils.rewriteURI(uri, null, true) : URIUtils.rewriteURI(uri));
            }
        } catch (URISyntaxException e) {
            throw new ProtocolException("Invalid URI: " + httpRequestWrapper.getRequestLine().getUri(), e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b9 A[Catch: RuntimeException -> 0x0063, IOException -> 0x0066, HttpException -> 0x0069, ConnectionShutdownException -> 0x006c, TryCatch #3 {HttpException -> 0x0069, ConnectionShutdownException -> 0x006c, IOException -> 0x0066, RuntimeException -> 0x0063, blocks: (B:49:0x004e, B:51:0x0054, B:52:0x005e, B:53:0x005f, B:17:0x006f, B:19:0x0075, B:22:0x007f, B:24:0x0087, B:26:0x008d, B:27:0x0090, B:29:0x0098, B:31:0x00a4, B:33:0x00b9, B:34:0x00bd, B:36:0x00e9, B:37:0x00fb, B:39:0x0101, B:42:0x0108, B:44:0x010e, B:46:0x00f8), top: B:48:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00e9 A[Catch: RuntimeException -> 0x0063, IOException -> 0x0066, HttpException -> 0x0069, ConnectionShutdownException -> 0x006c, TryCatch #3 {HttpException -> 0x0069, ConnectionShutdownException -> 0x006c, IOException -> 0x0066, RuntimeException -> 0x0063, blocks: (B:49:0x004e, B:51:0x0054, B:52:0x005e, B:53:0x005f, B:17:0x006f, B:19:0x0075, B:22:0x007f, B:24:0x0087, B:26:0x008d, B:27:0x0090, B:29:0x0098, B:31:0x00a4, B:33:0x00b9, B:34:0x00bd, B:36:0x00e9, B:37:0x00fb, B:39:0x0101, B:42:0x0108, B:44:0x010e, B:46:0x00f8), top: B:48:0x004e }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00f8 A[Catch: RuntimeException -> 0x0063, IOException -> 0x0066, HttpException -> 0x0069, ConnectionShutdownException -> 0x006c, TryCatch #3 {HttpException -> 0x0069, ConnectionShutdownException -> 0x006c, IOException -> 0x0066, RuntimeException -> 0x0063, blocks: (B:49:0x004e, B:51:0x0054, B:52:0x005e, B:53:0x005f, B:17:0x006f, B:19:0x0075, B:22:0x007f, B:24:0x0087, B:26:0x008d, B:27:0x0090, B:29:0x0098, B:31:0x00a4, B:33:0x00b9, B:34:0x00bd, B:36:0x00e9, B:37:0x00fb, B:39:0x0101, B:42:0x0108, B:44:0x010e, B:46:0x00f8), top: B:48:0x004e }] */
    @Override // cz.msebera.android.httpclient.impl.execchain.ClientExecChain
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public cz.msebera.android.httpclient.client.methods.CloseableHttpResponse execute(cz.msebera.android.httpclient.conn.routing.HttpRoute r7, cz.msebera.android.httpclient.client.methods.HttpRequestWrapper r8, cz.msebera.android.httpclient.client.protocol.HttpClientContext r9, cz.msebera.android.httpclient.client.methods.HttpExecutionAware r10) throws java.io.IOException, cz.msebera.android.httpclient.HttpException {
        /*
            Method dump skipped, instructions count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cz.msebera.android.httpclient.impl.execchain.MinimalClientExec.execute(cz.msebera.android.httpclient.conn.routing.HttpRoute, cz.msebera.android.httpclient.client.methods.HttpRequestWrapper, cz.msebera.android.httpclient.client.protocol.HttpClientContext, cz.msebera.android.httpclient.client.methods.HttpExecutionAware):cz.msebera.android.httpclient.client.methods.CloseableHttpResponse");
    }
}
