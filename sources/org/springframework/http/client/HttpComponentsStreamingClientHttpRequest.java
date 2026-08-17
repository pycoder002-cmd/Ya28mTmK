package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.StreamingHttpOutputMessage;

/* loaded from: classes2.dex */
final class HttpComponentsStreamingClientHttpRequest extends AbstractClientHttpRequest implements StreamingHttpOutputMessage {
    private StreamingHttpOutputMessage.Body body;
    private final CloseableHttpClient httpClient;
    private final HttpContext httpContext;
    private final HttpUriRequest httpRequest;

    /* loaded from: classes2.dex */
    private static class StreamingHttpEntity implements HttpEntity {
        private final StreamingHttpOutputMessage.Body body;
        private final HttpHeaders headers;

        public StreamingHttpEntity(HttpHeaders httpHeaders, StreamingHttpOutputMessage.Body body) {
            this.headers = httpHeaders;
            this.body = body;
        }

        @Override // org.apache.http.HttpEntity
        @Deprecated
        public void consumeContent() throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.http.HttpEntity
        public InputStream getContent() throws IOException, IllegalStateException {
            throw new IllegalStateException("No content available");
        }

        @Override // org.apache.http.HttpEntity
        public Header getContentEncoding() {
            String first = this.headers.getFirst("Content-Encoding");
            if (first != null) {
                return new BasicHeader("Content-Encoding", first);
            }
            return null;
        }

        @Override // org.apache.http.HttpEntity
        public long getContentLength() {
            return this.headers.getContentLength();
        }

        @Override // org.apache.http.HttpEntity
        public Header getContentType() {
            MediaType contentType = this.headers.getContentType();
            if (contentType != null) {
                return new BasicHeader("Content-Type", contentType.toString());
            }
            return null;
        }

        @Override // org.apache.http.HttpEntity
        public boolean isChunked() {
            return false;
        }

        @Override // org.apache.http.HttpEntity
        public boolean isRepeatable() {
            return false;
        }

        @Override // org.apache.http.HttpEntity
        public boolean isStreaming() {
            return true;
        }

        @Override // org.apache.http.HttpEntity
        public void writeTo(OutputStream outputStream) throws IOException {
            this.body.writeTo(outputStream);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpComponentsStreamingClientHttpRequest(CloseableHttpClient closeableHttpClient, HttpUriRequest httpUriRequest, HttpContext httpContext) {
        this.httpClient = closeableHttpClient;
        this.httpRequest = httpUriRequest;
        this.httpContext = httpContext;
    }

    @Override // org.springframework.http.client.AbstractClientHttpRequest
    protected ClientHttpResponse executeInternal(HttpHeaders httpHeaders) throws IOException {
        HttpComponentsClientHttpRequest.addHeaders(this.httpRequest, httpHeaders);
        if ((this.httpRequest instanceof HttpEntityEnclosingRequest) && this.body != null) {
            ((HttpEntityEnclosingRequest) this.httpRequest).setEntity(new StreamingHttpEntity(getHeaders(), this.body));
        }
        return new HttpComponentsClientHttpResponse(this.httpClient.execute(this.httpRequest, this.httpContext));
    }

    @Override // org.springframework.http.client.AbstractClientHttpRequest
    protected OutputStream getBodyInternal(HttpHeaders httpHeaders) throws IOException {
        throw new UnsupportedOperationException("getBody not supported");
    }

    @Override // org.springframework.http.HttpRequest
    public HttpMethod getMethod() {
        return HttpMethod.valueOf(this.httpRequest.getMethod());
    }

    @Override // org.springframework.http.HttpRequest
    public URI getURI() {
        return this.httpRequest.getURI();
    }

    @Override // org.springframework.http.StreamingHttpOutputMessage
    public void setBody(StreamingHttpOutputMessage.Body body) {
        assertNotExecuted();
        this.body = body;
    }
}
