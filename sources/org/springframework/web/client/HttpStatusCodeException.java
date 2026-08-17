package org.springframework.web.client;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/* loaded from: classes2.dex */
public abstract class HttpStatusCodeException extends RestClientException {
    private static final String DEFAULT_CHARSET = "ISO-8859-1";
    private static final long serialVersionUID = -5807494703720513267L;
    private final byte[] responseBody;
    private final String responseCharset;
    private final HttpHeaders responseHeaders;
    private final HttpStatus statusCode;
    private final String statusText;

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpStatusCodeException(HttpStatus httpStatus) {
        this(httpStatus, httpStatus.name(), null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpStatusCodeException(HttpStatus httpStatus, String str) {
        this(httpStatus, str, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpStatusCodeException(HttpStatus httpStatus, String str, HttpHeaders httpHeaders, byte[] bArr, Charset charset) {
        super(httpStatus.value() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
        this.statusCode = httpStatus;
        this.statusText = str;
        this.responseHeaders = httpHeaders;
        this.responseBody = bArr == null ? new byte[0] : bArr;
        this.responseCharset = charset != null ? charset.name() : "ISO-8859-1";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public HttpStatusCodeException(HttpStatus httpStatus, String str, byte[] bArr, Charset charset) {
        this(httpStatus, str, null, bArr, charset);
    }

    public byte[] getResponseBodyAsByteArray() {
        return this.responseBody;
    }

    public String getResponseBodyAsString() {
        try {
            return new String(this.responseBody, this.responseCharset);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    public HttpHeaders getResponseHeaders() {
        return this.responseHeaders;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    public String getStatusText() {
        return this.statusText;
    }
}
