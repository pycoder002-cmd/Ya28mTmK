package org.springframework.web.client;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;

/* loaded from: classes2.dex */
public class UnknownHttpStatusCodeException extends RestClientException {
    private static final String DEFAULT_CHARSET = "ISO-8859-1";
    private static final long serialVersionUID = 4702443689088991600L;
    private final int rawStatusCode;
    private final byte[] responseBody;
    private final String responseCharset;
    private final HttpHeaders responseHeaders;
    private final String statusText;

    public UnknownHttpStatusCodeException(int i, String str, HttpHeaders httpHeaders, byte[] bArr, Charset charset) {
        super("Unknown status code [" + String.valueOf(i) + "]" + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str);
        this.rawStatusCode = i;
        this.statusText = str;
        this.responseHeaders = httpHeaders;
        this.responseBody = bArr == null ? new byte[0] : bArr;
        this.responseCharset = charset != null ? charset.name() : "ISO-8859-1";
    }

    public int getRawStatusCode() {
        return this.rawStatusCode;
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

    public String getStatusText() {
        return this.statusText;
    }
}
