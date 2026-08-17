package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import org.springframework.http.ContentCodingType;
import org.springframework.http.HttpStatus;

/* loaded from: classes2.dex */
public abstract class AbstractClientHttpResponse implements ClientHttpResponse {
    private InputStream compressedBody;

    private InputStream getCompressedBody(InputStream inputStream) throws IOException {
        if (this.compressedBody == null) {
            this.compressedBody = new GZIPInputStream(inputStream);
        }
        return this.compressedBody;
    }

    private boolean isCompressed() {
        Iterator<ContentCodingType> it = getHeaders().getContentEncoding().iterator();
        while (it.hasNext()) {
            if (it.next().equals(ContentCodingType.GZIP)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.springframework.http.client.ClientHttpResponse, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.compressedBody != null) {
            try {
                this.compressedBody.close();
            } catch (IOException unused) {
            }
        }
        closeInternal();
    }

    protected abstract void closeInternal();

    @Override // org.springframework.http.HttpInputMessage
    public InputStream getBody() throws IOException {
        InputStream bodyInternal = getBodyInternal();
        return isCompressed() ? getCompressedBody(bodyInternal) : bodyInternal;
    }

    protected abstract InputStream getBodyInternal() throws IOException;

    @Override // org.springframework.http.client.ClientHttpResponse
    public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.valueOf(getRawStatusCode());
    }
}
