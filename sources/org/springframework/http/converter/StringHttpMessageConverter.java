package org.springframework.http.converter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

/* loaded from: classes2.dex */
public class StringHttpMessageConverter extends AbstractHttpMessageConverter<String> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
    private final List<Charset> availableCharsets;
    private final Charset defaultCharset;
    private boolean writeAcceptCharset;

    public StringHttpMessageConverter() {
        this(DEFAULT_CHARSET);
    }

    public StringHttpMessageConverter(Charset charset) {
        super(new MediaType("text", "plain", charset), MediaType.ALL);
        this.writeAcceptCharset = true;
        this.defaultCharset = charset;
        this.availableCharsets = new ArrayList(Charset.availableCharsets().values());
    }

    private Charset getContentTypeCharset(MediaType mediaType) {
        return (mediaType == null || mediaType.getCharSet() == null) ? this.defaultCharset : mediaType.getCharSet();
    }

    protected List<Charset> getAcceptedCharsets() {
        return this.availableCharsets;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public Long getContentLength(String str, MediaType mediaType) {
        try {
            return Long.valueOf(str.getBytes(getContentTypeCharset(mediaType).name()).length);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public String readInternal(Class<? extends String> cls, HttpInputMessage httpInputMessage) throws IOException {
        return StreamUtils.copyToString(httpInputMessage.getBody(), getContentTypeCharset(httpInputMessage.getHeaders().getContentType()));
    }

    public void setWriteAcceptCharset(boolean z) {
        this.writeAcceptCharset = z;
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public boolean supports(Class<?> cls) {
        return String.class.equals(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public void writeInternal(String str, HttpOutputMessage httpOutputMessage) throws IOException {
        if (this.writeAcceptCharset) {
            httpOutputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
        }
        StreamUtils.copy(str, getContentTypeCharset(httpOutputMessage.getHeaders().getContentType()), httpOutputMessage.getBody());
    }
}
