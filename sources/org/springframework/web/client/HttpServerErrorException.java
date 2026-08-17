package org.springframework.web.client;

import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

/* loaded from: classes2.dex */
public class HttpServerErrorException extends HttpStatusCodeException {
    private static final long serialVersionUID = -2915754006618138282L;

    public HttpServerErrorException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public HttpServerErrorException(HttpStatus httpStatus, String str) {
        super(httpStatus, str);
    }

    public HttpServerErrorException(HttpStatus httpStatus, String str, HttpHeaders httpHeaders, byte[] bArr, Charset charset) {
        super(httpStatus, str, httpHeaders, bArr, charset);
    }

    public HttpServerErrorException(HttpStatus httpStatus, String str, byte[] bArr, Charset charset) {
        super(httpStatus, str, bArr, charset);
    }
}
