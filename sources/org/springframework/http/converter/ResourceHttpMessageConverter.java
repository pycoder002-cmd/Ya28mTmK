package org.springframework.http.converter;

import java.io.IOException;
import java.io.InputStream;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

/* loaded from: classes2.dex */
public class ResourceHttpMessageConverter extends AbstractHttpMessageConverter<Resource> {
    public ResourceHttpMessageConverter() {
        super(MediaType.ALL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public Long getContentLength(Resource resource, MediaType mediaType) throws IOException {
        if (InputStreamResource.class.equals(resource.getClass())) {
            return null;
        }
        return Long.valueOf(resource.contentLength());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public MediaType getDefaultContentType(Resource resource) {
        return MediaType.APPLICATION_OCTET_STREAM;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public Resource readInternal(Class<? extends Resource> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return new ByteArrayResource(StreamUtils.copyToByteArray(httpInputMessage.getBody()));
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected boolean supports(Class<?> cls) {
        return Resource.class.isAssignableFrom(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public void writeInternal(Resource resource, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        InputStream inputStream = resource.getInputStream();
        try {
            StreamUtils.copy(inputStream, httpOutputMessage.getBody());
            httpOutputMessage.getBody().flush();
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
