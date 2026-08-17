package org.springframework.http.client;

import java.io.IOException;
import java.net.ProtocolException;
import java.net.URI;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class OkHttp3ClientHttpRequest extends AbstractBufferingClientHttpRequest implements ClientHttpRequest {
    private static final byte[] NO_BODY = new byte[0];
    private static final String PROXY_AUTH_ERROR = "Received HTTP_PROXY_AUTH (407) code while not using proxy";
    private final OkHttpClient client;
    private final HttpMethod method;
    private final URI uri;

    public OkHttp3ClientHttpRequest(OkHttpClient okHttpClient, URI uri, HttpMethod httpMethod) {
        this.client = okHttpClient;
        this.uri = uri;
        this.method = httpMethod;
    }

    private MediaType getContentType(HttpHeaders httpHeaders) {
        String first = httpHeaders.getFirst("Content-Type");
        if (StringUtils.hasText(first)) {
            return MediaType.parse(first);
        }
        return null;
    }

    @Override // org.springframework.http.client.AbstractBufferingClientHttpRequest
    protected ClientHttpResponse executeInternal(HttpHeaders httpHeaders, byte[] bArr) throws IOException {
        Request.Builder method = new Request.Builder().url(this.uri.toURL()).method(this.method.name(), ((this.method == HttpMethod.POST || this.method == HttpMethod.PUT || this.method == HttpMethod.PATCH) && bArr.length == 0) ? RequestBody.create((MediaType) null, NO_BODY) : bArr.length > 0 ? RequestBody.create(getContentType(httpHeaders), bArr) : null);
        for (Map.Entry<String, List<String>> entry : httpHeaders.entrySet()) {
            String key = entry.getKey();
            Iterator<String> it = entry.getValue().iterator();
            while (it.hasNext()) {
                method.addHeader(key, it.next());
            }
        }
        try {
            return new OkHttp3ClientHttpResponse(this.client.newCall(method.build()).execute());
        } catch (ProtocolException e) {
            if (PROXY_AUTH_ERROR.equals(e.getMessage())) {
                throw new HttpClientErrorException(HttpStatus.PROXY_AUTHENTICATION_REQUIRED, HttpStatus.PROXY_AUTHENTICATION_REQUIRED.getReasonPhrase());
            }
            throw e;
        }
    }

    @Override // org.springframework.http.HttpRequest
    public HttpMethod getMethod() {
        return this.method;
    }

    @Override // org.springframework.http.HttpRequest
    public URI getURI() {
        return this.uri;
    }
}
