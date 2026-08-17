package io.sentry.connection;

import cz.msebera.android.httpclient.client.methods.HttpPost;
import io.sentry.environment.SentryEnvironment;
import io.sentry.marshaller.Marshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes2.dex */
public class HttpConnection extends AbstractConnection {
    public static final int HTTP_TOO_MANY_REQUESTS = 429;
    private static final String SENTRY_AUTH = "X-Sentry-Auth";
    private static final String USER_AGENT = "User-Agent";
    private boolean bypassSecurity;
    private int connectionTimeout;
    private EventSampler eventSampler;
    private Marshaller marshaller;
    private final Proxy proxy;
    private int readTimeout;
    private final URL sentryUrl;
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Logger logger = LoggerFactory.getLogger((Class<?>) HttpConnection.class);
    private static final int DEFAULT_CONNECTION_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(1);
    private static final int DEFAULT_READ_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(5);
    private static final HostnameVerifier NAIVE_VERIFIER = new HostnameVerifier() { // from class: io.sentry.connection.HttpConnection.1
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return true;
        }
    };

    public HttpConnection(URL url, String str, String str2, Proxy proxy, EventSampler eventSampler) {
        super(str, str2);
        this.connectionTimeout = DEFAULT_CONNECTION_TIMEOUT;
        this.readTimeout = DEFAULT_READ_TIMEOUT;
        this.bypassSecurity = false;
        this.sentryUrl = url;
        this.proxy = proxy;
        this.eventSampler = eventSampler;
    }

    private String getErrorMessageFromStream(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                if (!z) {
                    sb.append("\n");
                }
                sb.append(readLine);
                z = false;
            } catch (Exception e) {
                logger.error("Exception while reading the error message from the connection.", (Throwable) e);
            }
        }
        return sb.toString();
    }

    public static URL getSentryApiUrl(URI uri, String str) {
        try {
            return new URL(uri.toString() + "api/" + str + "/store/");
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Couldn't build a valid URL from the Sentry API.", e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0059 A[Catch: all -> 0x002b, IOException -> 0x008d, TRY_LEAVE, TryCatch #1 {all -> 0x002b, IOException -> 0x002e, blocks: (B:9:0x0011, B:17:0x002f, B:46:0x0038, B:22:0x0049, B:24:0x0051, B:26:0x0059, B:29:0x007c, B:31:0x0084, B:32:0x008b, B:33:0x008d, B:35:0x0093, B:37:0x0099, B:39:0x00a1, B:40:0x00a6), top: B:8:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x007c A[Catch: all -> 0x002b, IOException -> 0x008d, TRY_ENTER, TryCatch #1 {all -> 0x002b, IOException -> 0x002e, blocks: (B:9:0x0011, B:17:0x002f, B:46:0x0038, B:22:0x0049, B:24:0x0051, B:26:0x0059, B:29:0x007c, B:31:0x0084, B:32:0x008b, B:33:0x008d, B:35:0x0093, B:37:0x0099, B:39:0x00a1, B:40:0x00a6), top: B:8:0x0011 }] */
    @Override // io.sentry.connection.AbstractConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void doSend(io.sentry.event.Event r9) throws io.sentry.connection.ConnectionException {
        /*
            r8 = this;
            io.sentry.connection.EventSampler r0 = r8.eventSampler
            if (r0 == 0) goto Ld
            io.sentry.connection.EventSampler r0 = r8.eventSampler
            boolean r0 = r0.shouldSendEvent(r9)
            if (r0 != 0) goto Ld
            return
        Ld:
            java.net.HttpURLConnection r0 = r8.getConnection()
            r0.connect()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2e
            java.io.OutputStream r1 = r0.getOutputStream()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2e
            io.sentry.marshaller.Marshaller r2 = r8.marshaller     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2e
            r2.marshall(r9, r1)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2e
            r1.close()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2e
            java.io.InputStream r1 = r0.getInputStream()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2e
            r1.close()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L2e
            r0.disconnect()
            return
        L2b:
            r9 = move-exception
            goto La7
        L2e:
            r1 = move-exception
            java.lang.String r2 = "Retry-After"
            java.lang.String r2 = r0.getHeaderField(r2)     // Catch: java.lang.Throwable -> L2b
            r3 = 0
            if (r2 == 0) goto L48
            double r4 = java.lang.Double.parseDouble(r2)     // Catch: java.lang.Throwable -> L2b java.lang.NumberFormatException -> L48
            r6 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r4 = r4 * r6
            long r4 = (long) r4     // Catch: java.lang.Throwable -> L2b java.lang.NumberFormatException -> L48
            java.lang.Long r2 = java.lang.Long.valueOf(r4)     // Catch: java.lang.Throwable -> L2b java.lang.NumberFormatException -> L48
            goto L49
        L48:
            r2 = r3
        L49:
            int r4 = r0.getResponseCode()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8c
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8c
            int r5 = r4.intValue()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            r6 = 403(0x193, float:5.65E-43)
            if (r5 != r6) goto L7c
            org.slf4j.Logger r5 = io.sentry.connection.HttpConnection.logger     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            r6.<init>()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            java.lang.String r7 = "Event '"
            r6.append(r7)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            java.util.UUID r9 = r9.getId()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            r6.append(r9)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            java.lang.String r9 = "' was rejected by the Sentry server due to a filter."
            r6.append(r9)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            java.lang.String r9 = r6.toString()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            r5.debug(r9)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            r0.disconnect()
            return
        L7c:
            int r9 = r4.intValue()     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            r5 = 429(0x1ad, float:6.01E-43)
            if (r9 != r5) goto L8d
            io.sentry.connection.TooManyRequestsException r9 = new io.sentry.connection.TooManyRequestsException     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            java.lang.String r5 = "Too many requests to Sentry: https://docs.sentry.io/learn/quotas/"
            r9.<init>(r5, r1, r2, r4)     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
            throw r9     // Catch: java.lang.Throwable -> L2b java.io.IOException -> L8d
        L8c:
            r4 = r3
        L8d:
            java.io.InputStream r9 = r0.getErrorStream()     // Catch: java.lang.Throwable -> L2b
            if (r9 == 0) goto L97
            java.lang.String r3 = r8.getErrorMessageFromStream(r9)     // Catch: java.lang.Throwable -> L2b
        L97:
            if (r3 == 0) goto L9f
            boolean r9 = r3.isEmpty()     // Catch: java.lang.Throwable -> L2b
            if (r9 == 0) goto La1
        L9f:
            java.lang.String r3 = "An exception occurred while submitting the event to the Sentry server."
        La1:
            io.sentry.connection.ConnectionException r9 = new io.sentry.connection.ConnectionException     // Catch: java.lang.Throwable -> L2b
            r9.<init>(r3, r1, r2, r4)     // Catch: java.lang.Throwable -> L2b
            throw r9     // Catch: java.lang.Throwable -> L2b
        La7:
            r0.disconnect()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.connection.HttpConnection.doSend(io.sentry.event.Event):void");
    }

    protected HttpURLConnection getConnection() {
        try {
            HttpURLConnection httpURLConnection = this.proxy != null ? (HttpURLConnection) this.sentryUrl.openConnection(this.proxy) : (HttpURLConnection) this.sentryUrl.openConnection();
            if (this.bypassSecurity && (httpURLConnection instanceof HttpsURLConnection)) {
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(NAIVE_VERIFIER);
            }
            httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setConnectTimeout(this.connectionTimeout);
            httpURLConnection.setReadTimeout(this.readTimeout);
            httpURLConnection.setRequestProperty("User-Agent", SentryEnvironment.getSentryName());
            httpURLConnection.setRequestProperty(SENTRY_AUTH, getAuthHeader());
            if (this.marshaller.getContentType() != null) {
                httpURLConnection.setRequestProperty("Content-Type", this.marshaller.getContentType());
            }
            if (this.marshaller.getContentEncoding() != null) {
                httpURLConnection.setRequestProperty("Content-Encoding", this.marshaller.getContentEncoding());
            }
            return httpURLConnection;
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't set up a connection to the Sentry server.", e);
        }
    }

    public void setBypassSecurity(boolean z) {
        this.bypassSecurity = z;
    }

    public void setConnectionTimeout(int i) {
        this.connectionTimeout = i;
    }

    public void setMarshaller(Marshaller marshaller) {
        this.marshaller = marshaller;
    }

    public void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    @Deprecated
    public void setTimeout(int i) {
        this.connectionTimeout = i;
    }
}
