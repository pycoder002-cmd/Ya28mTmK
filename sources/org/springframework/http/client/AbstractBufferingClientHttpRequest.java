package org.springframework.http.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.springframework.http.HttpHeaders;

/* loaded from: classes2.dex */
abstract class AbstractBufferingClientHttpRequest extends AbstractClientHttpRequest {
    private ByteArrayOutputStream bufferedOutput = new ByteArrayOutputStream();

    @Override // org.springframework.http.client.AbstractClientHttpRequest
    protected ClientHttpResponse executeInternal(HttpHeaders httpHeaders) throws IOException {
        byte[] byteArray = this.bufferedOutput.toByteArray();
        if (httpHeaders.getContentLength() == -1) {
            httpHeaders.setContentLength(byteArray.length);
        }
        ClientHttpResponse executeInternal = executeInternal(httpHeaders, byteArray);
        this.bufferedOutput = null;
        return executeInternal;
    }

    protected abstract ClientHttpResponse executeInternal(HttpHeaders httpHeaders, byte[] bArr) throws IOException;

    @Override // org.springframework.http.client.AbstractClientHttpRequest
    protected OutputStream getBodyInternal(HttpHeaders httpHeaders) throws IOException {
        return this.bufferedOutput;
    }
}
