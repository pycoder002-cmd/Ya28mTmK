package org.springframework.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
final class SimpleClientHttpResponse extends AbstractClientHttpResponse {
    private static final String AUTH_ERROR = "Received authentication challenge is null";
    private static final String AUTH_ERROR_JELLY_BEAN = "No authentication challenges found";
    private static final String PROXY_AUTH_ERROR = "Received HTTP_PROXY_AUTH (407) code while not using proxy";
    private final HttpURLConnection connection;
    private HttpHeaders headers;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleClientHttpResponse(HttpURLConnection httpURLConnection) {
        this.connection = httpURLConnection;
    }

    private int handleIOException(IOException iOException) throws IOException {
        if (AUTH_ERROR.equals(iOException.getMessage()) || AUTH_ERROR_JELLY_BEAN.equals(iOException.getMessage())) {
            return HttpStatus.UNAUTHORIZED.value();
        }
        if (PROXY_AUTH_ERROR.equals(iOException.getMessage())) {
            return HttpStatus.PROXY_AUTHENTICATION_REQUIRED.value();
        }
        throw iOException;
    }

    @Override // org.springframework.http.client.AbstractClientHttpResponse
    protected void closeInternal() {
        this.connection.disconnect();
    }

    @Override // org.springframework.http.client.AbstractClientHttpResponse
    protected InputStream getBodyInternal() throws IOException {
        InputStream errorStream = this.connection.getErrorStream();
        return errorStream != null ? errorStream : this.connection.getInputStream();
    }

    @Override // org.springframework.http.HttpMessage
    public HttpHeaders getHeaders() {
        if (this.headers == null) {
            this.headers = new HttpHeaders();
            String headerFieldKey = this.connection.getHeaderFieldKey(0);
            if (StringUtils.hasLength(headerFieldKey)) {
                this.headers.add(headerFieldKey, this.connection.getHeaderField(0));
            }
            int i = 1;
            while (true) {
                String headerFieldKey2 = this.connection.getHeaderFieldKey(i);
                if (!StringUtils.hasLength(headerFieldKey2)) {
                    break;
                }
                this.headers.add(headerFieldKey2, this.connection.getHeaderField(i));
                i++;
            }
        }
        return this.headers;
    }

    @Override // org.springframework.http.client.ClientHttpResponse
    public int getRawStatusCode() throws IOException {
        try {
            return this.connection.getResponseCode();
        } catch (IOException e) {
            return handleIOException(e);
        }
    }

    @Override // org.springframework.http.client.ClientHttpResponse
    public String getStatusText() throws IOException {
        try {
            return this.connection.getResponseMessage();
        } catch (IOException e) {
            return HttpStatus.valueOf(handleIOException(e)).getReasonPhrase();
        }
    }
}
