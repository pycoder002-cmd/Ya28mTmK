package org.springframework.http.client;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public abstract class AbstractClientHttpRequest implements ClientHttpRequest {
    private GZIPOutputStream compressedBody;
    private final HttpHeaders headers = new HttpHeaders();
    private boolean executed = false;

    private OutputStream getCompressedBody(OutputStream outputStream) throws IOException {
        if (this.compressedBody == null) {
            this.compressedBody = new GZIPOutputStream(outputStream);
        }
        return this.compressedBody;
    }

    private boolean shouldCompress() {
        Iterator<ContentCodingType> it = this.headers.getContentEncoding().iterator();
        while (it.hasNext()) {
            if (it.next().equals(ContentCodingType.GZIP)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void assertNotExecuted() {
        Assert.state(!this.executed, "ClientHttpRequest already executed");
    }

    @Override // org.springframework.http.client.ClientHttpRequest
    public final ClientHttpResponse execute() throws IOException {
        assertNotExecuted();
        if (this.compressedBody != null) {
            this.compressedBody.close();
        }
        ClientHttpResponse executeInternal = executeInternal(this.headers);
        this.executed = true;
        return executeInternal;
    }

    protected abstract ClientHttpResponse executeInternal(HttpHeaders httpHeaders) throws IOException;

    @Override // org.springframework.http.HttpOutputMessage
    public final OutputStream getBody() throws IOException {
        assertNotExecuted();
        OutputStream bodyInternal = getBodyInternal(this.headers);
        return shouldCompress() ? getCompressedBody(bodyInternal) : bodyInternal;
    }

    protected abstract OutputStream getBodyInternal(HttpHeaders httpHeaders) throws IOException;

    @Override // org.springframework.http.HttpMessage
    public final HttpHeaders getHeaders() {
        return this.executed ? HttpHeaders.readOnlyHttpHeaders(this.headers) : this.headers;
    }
}
