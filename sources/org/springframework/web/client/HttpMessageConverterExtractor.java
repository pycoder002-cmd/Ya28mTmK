package org.springframework.web.client;

import android.util.Log;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class HttpMessageConverterExtractor<T> implements ResponseExtractor<T> {
    private static final String TAG = "RestTemplate";
    private final List<HttpMessageConverter<?>> messageConverters;
    private final Class<T> responseClass;
    private final Type responseType;

    public HttpMessageConverterExtractor(Class<T> cls, List<HttpMessageConverter<?>> list) {
        this((Type) cls, list);
    }

    public HttpMessageConverterExtractor(Type type, List<HttpMessageConverter<?>> list) {
        Assert.notNull(type, "'responseType' must not be null");
        Assert.notEmpty(list, "'messageConverters' must not be empty");
        this.responseType = type;
        this.responseClass = type instanceof Class ? (Class) type : null;
        this.messageConverters = list;
    }

    private MediaType getContentType(ClientHttpResponse clientHttpResponse) {
        MediaType contentType = clientHttpResponse.getHeaders().getContentType();
        if (contentType != null) {
            return contentType;
        }
        if (Log.isLoggable(TAG, 2)) {
            Log.v(TAG, "No Content-Type header found, defaulting to application/octet-stream");
        }
        return MediaType.APPLICATION_OCTET_STREAM;
    }

    @Override // org.springframework.web.client.ResponseExtractor
    public T extractData(ClientHttpResponse clientHttpResponse) throws IOException {
        if (!hasMessageBody(clientHttpResponse)) {
            return null;
        }
        MediaType contentType = getContentType(clientHttpResponse);
        for (HttpMessageConverter<?> httpMessageConverter : this.messageConverters) {
            if (httpMessageConverter instanceof GenericHttpMessageConverter) {
                GenericHttpMessageConverter genericHttpMessageConverter = (GenericHttpMessageConverter) httpMessageConverter;
                if (genericHttpMessageConverter.canRead(this.responseType, null, contentType)) {
                    if (Log.isLoggable(TAG, 3)) {
                        Log.d(TAG, "Reading [" + this.responseType + "] as \"" + contentType + "\" using [" + httpMessageConverter + "]");
                    }
                    return (T) genericHttpMessageConverter.read(this.responseType, null, clientHttpResponse);
                }
            }
            if (this.responseClass != null && httpMessageConverter.canRead(this.responseClass, contentType)) {
                if (Log.isLoggable(TAG, 3)) {
                    Log.d(TAG, "Reading [" + this.responseClass.getName() + "] as \"" + contentType + "\" using [" + httpMessageConverter + "]");
                }
                return (T) httpMessageConverter.read(this.responseClass, clientHttpResponse);
            }
        }
        throw new RestClientException("Could not extract response: no suitable HttpMessageConverter found for response type [" + this.responseType + "] and content type [" + contentType + "]");
    }

    protected boolean hasMessageBody(ClientHttpResponse clientHttpResponse) throws IOException {
        HttpStatus statusCode = clientHttpResponse.getStatusCode();
        return (statusCode == HttpStatus.NO_CONTENT || statusCode == HttpStatus.NOT_MODIFIED || clientHttpResponse.getHeaders().getContentLength() == 0) ? false : true;
    }
}
