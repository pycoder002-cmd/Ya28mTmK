package org.springframework.web.client;

import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/* loaded from: classes2.dex */
public class HttpClientErrorException extends HttpStatusCodeException {
    private static final long serialVersionUID = 5177019431887513952L;

    public HttpClientErrorException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public HttpClientErrorException(HttpStatus httpStatus, String str) {
        super(httpStatus, str);
    }

    public HttpClientErrorException(HttpStatus httpStatus, String str, HttpHeaders httpHeaders, byte[] bArr, Charset charset) {
        super(httpStatus, str, httpHeaders, bArr, charset);
    }

    public HttpClientErrorException(HttpStatus httpStatus, String str, byte[] bArr, Charset charset) {
        super(httpStatus, str, bArr, charset);
    }
}
