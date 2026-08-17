package org.springframework.http.converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

/* loaded from: classes2.dex */
public class ByteArrayHttpMessageConverter extends AbstractHttpMessageConverter<byte[]> {
    public ByteArrayHttpMessageConverter() {
        super(new MediaType("application", "octet-stream"), MediaType.ALL);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public Long getContentLength(byte[] bArr, MediaType mediaType) {
        return Long.valueOf(bArr.length);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public byte[] readInternal(Class<? extends byte[]> cls, HttpInputMessage httpInputMessage) throws IOException {
        long contentLength = httpInputMessage.getHeaders().getContentLength();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(contentLength >= 0 ? (int) contentLength : 4096);
        StreamUtils.copy(httpInputMessage.getBody(), byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public boolean supports(Class<?> cls) {
        return byte[].class.equals(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public void writeInternal(byte[] bArr, HttpOutputMessage httpOutputMessage) throws IOException {
        StreamUtils.copy(bArr, httpOutputMessage.getBody());
    }
}
