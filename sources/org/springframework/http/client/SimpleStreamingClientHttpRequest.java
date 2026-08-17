package org.springframework.http.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.StreamUtils;

/* loaded from: classes2.dex */
final class SimpleStreamingClientHttpRequest extends AbstractClientHttpRequest {
    private OutputStream body;
    private final int chunkSize;
    private final HttpURLConnection connection;
    private final boolean outputStreaming;
    private final boolean reuseConnection;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleStreamingClientHttpRequest(HttpURLConnection httpURLConnection, int i, boolean z, boolean z2) {
        this.connection = httpURLConnection;
        this.chunkSize = i;
        this.outputStreaming = z;
        this.reuseConnection = z2;
    }

    private void writeHeaders(HttpHeaders httpHeaders) {
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String key = entry.getKey();
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                this.connection.addRequestProperty(key, it.next());
            }
        }
    }

    @Override // org.springframework.http.client.AbstractClientHttpRequest
    protected ClientHttpResponse executeInternal(HttpHeaders httpHeaders) throws IOException {
        try {
            if (this.body != null) {
                this.body.close();
            } else {
                writeHeaders(httpHeaders);
                this.connection.connect();
            }
        } catch (IOException unused) {
        }
        return new SimpleClientHttpResponse(this.connection);
    }

    @Override // org.springframework.http.client.AbstractClientHttpRequest
    protected OutputStream getBodyInternal(HttpHeaders httpHeaders) throws IOException {
        if (this.body == null) {
            if (this.outputStreaming) {
                int contentLength = (int) httpHeaders.getContentLength();
                if (contentLength >= 0) {
                    this.connection.setFixedLengthStreamingMode(contentLength);
                } else {
                    this.connection.setChunkedStreamingMode(this.chunkSize);
                }
            }
            if (!this.reuseConnection) {
                httpHeaders.setConnection("close");
            }
            writeHeaders(httpHeaders);
            this.connection.connect();
            this.body = this.connection.getOutputStream();
        }
        return StreamUtils.nonClosing(this.body);
    }

    @Override // org.springframework.http.HttpRequest
    public HttpMethod getMethod() {
        return HttpMethod.valueOf(this.connection.getRequestMethod());
    }

    @Override // org.springframework.http.HttpRequest
    public URI getURI() {
        try {
            return this.connection.getURL().toURI();
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Could not get HttpURLConnection URI: " + e.getMessage(), e);
        }
    }
}
