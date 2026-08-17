package org.springframework.http.converter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public abstract class AbstractHttpMessageConverter<T> implements HttpMessageConverter<T> {
    private List<MediaType> supportedMediaTypes = Collections.emptyList();

    protected AbstractHttpMessageConverter() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractHttpMessageConverter(MediaType mediaType) {
        setSupportedMediaTypes(Collections.singletonList(mediaType));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractHttpMessageConverter(MediaType... mediaTypeArr) {
        setSupportedMediaTypes(Arrays.asList(mediaTypeArr));
    }

    @Override // org.springframework.http.converter.HttpMessageConverter
    public boolean canRead(Class<?> cls, MediaType mediaType) {
        return supports(cls) && canRead(mediaType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean canRead(MediaType mediaType) {
        if (mediaType == null) {
            return true;
        }
        Iterator<MediaType> it = getSupportedMediaTypes().iterator();
        while (it.hasNext()) {
            if (it.next().includes(mediaType)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.springframework.http.converter.HttpMessageConverter
    public boolean canWrite(Class<?> cls, MediaType mediaType) {
        return supports(cls) && canWrite(mediaType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean canWrite(MediaType mediaType) {
        if (mediaType == null || MediaType.ALL.equals(mediaType)) {
            return true;
        }
        Iterator<MediaType> it = getSupportedMediaTypes().iterator();
        while (it.hasNext()) {
            if (it.next().isCompatibleWith(mediaType)) {
                return true;
            }
        }
        return false;
    }

    protected Long getContentLength(T t, MediaType mediaType) throws IOException {
        return null;
    }

    protected MediaType getDefaultContentType(T t) throws IOException {
        List<MediaType> supportedMediaTypes = getSupportedMediaTypes();
        if (supportedMediaTypes.isEmpty()) {
            return null;
        }
        return supportedMediaTypes.get(0);
    }

    @Override // org.springframework.http.converter.HttpMessageConverter
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.unmodifiableList(this.supportedMediaTypes);
    }

    @Override // org.springframework.http.converter.HttpMessageConverter
    public final T read(Class<? extends T> cls, HttpInputMessage httpInputMessage) throws IOException {
        return readInternal(cls, httpInputMessage);
    }

    protected abstract T readInternal(Class<? extends T> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException;

    public void setSupportedMediaTypes(List<MediaType> list) {
        Assert.notEmpty(list, "'supportedMediaTypes' must not be empty");
        this.supportedMediaTypes = new ArrayList(list);
    }

    protected abstract boolean supports(Class<?> cls);

    @Override // org.springframework.http.converter.HttpMessageConverter
    public final void write(T t, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        Long contentLength;
        HttpHeaders headers = httpOutputMessage.getHeaders();
        if (headers.getContentType() == null) {
            if (mediaType == null || mediaType.isWildcardType() || mediaType.isWildcardSubtype()) {
                mediaType = getDefaultContentType(t);
            }
            if (mediaType != null) {
                headers.setContentType(mediaType);
            }
        }
        if (headers.getContentLength() == -1 && (contentLength = getContentLength(t, headers.getContentType())) != null) {
            headers.setContentLength(contentLength.longValue());
        }
        writeInternal(t, httpOutputMessage);
        httpOutputMessage.getBody().flush();
    }

    protected abstract void writeInternal(T t, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException;
}
