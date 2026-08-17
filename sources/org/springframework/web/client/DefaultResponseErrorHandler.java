package org.springframework.web.client;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;

/* loaded from: classes2.dex */
public class DefaultResponseErrorHandler implements ResponseErrorHandler {
    private Charset getCharset(ClientHttpResponse clientHttpResponse) {
        MediaType contentType = clientHttpResponse.getHeaders().getContentType();
        if (contentType != null) {
            return contentType.getCharSet();
        }
        return null;
    }

    private HttpStatus getHttpStatusCode(ClientHttpResponse clientHttpResponse) throws IOException {
        try {
            return clientHttpResponse.getStatusCode();
        } catch (IllegalArgumentException unused) {
            throw new UnknownHttpStatusCodeException(clientHttpResponse.getRawStatusCode(), clientHttpResponse.getStatusText(), clientHttpResponse.getHeaders(), getResponseBody(clientHttpResponse), getCharset(clientHttpResponse));
        }
    }

    private byte[] getResponseBody(ClientHttpResponse clientHttpResponse) {
        try {
            InputStream body = clientHttpResponse.getBody();
            if (body != null) {
                return FileCopyUtils.copyToByteArray(body);
            }
        } catch (IOException unused) {
        }
        return new byte[0];
    }

    @Override // org.springframework.web.client.ResponseErrorHandler
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus httpStatusCode = getHttpStatusCode(clientHttpResponse);
        switch (httpStatusCode.series()) {
            case CLIENT_ERROR:
                throw new HttpClientErrorException(httpStatusCode, clientHttpResponse.getStatusText(), clientHttpResponse.getHeaders(), getResponseBody(clientHttpResponse), getCharset(clientHttpResponse));
            case SERVER_ERROR:
                throw new HttpServerErrorException(httpStatusCode, clientHttpResponse.getStatusText(), clientHttpResponse.getHeaders(), getResponseBody(clientHttpResponse), getCharset(clientHttpResponse));
            default:
                throw new RestClientException("Unknown status code [" + httpStatusCode + "]");
        }
    }

    protected boolean hasError(HttpStatus httpStatus) {
        return httpStatus.series() == HttpStatus.Series.CLIENT_ERROR || httpStatus.series() == HttpStatus.Series.SERVER_ERROR;
    }

    @Override // org.springframework.web.client.ResponseErrorHandler
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return hasError(getHttpStatusCode(clientHttpResponse));
    }
}
