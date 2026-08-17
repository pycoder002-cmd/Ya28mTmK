package org.springframework.http.converter;

import java.io.IOException;
import java.nio.charset.Charset;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class ObjectToStringHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    private ConversionService conversionService;
    private StringHttpMessageConverter stringHttpMessageConverter;

    public ObjectToStringHttpMessageConverter(ConversionService conversionService) {
        this(conversionService, StringHttpMessageConverter.DEFAULT_CHARSET);
    }

    public ObjectToStringHttpMessageConverter(ConversionService conversionService, Charset charset) {
        super(new MediaType("text", "plain", charset));
        Assert.notNull(conversionService, "conversionService is required");
        this.conversionService = conversionService;
        this.stringHttpMessageConverter = new StringHttpMessageConverter(charset);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter, org.springframework.http.converter.HttpMessageConverter
    public boolean canRead(Class<?> cls, MediaType mediaType) {
        return this.conversionService.canConvert(String.class, cls) && canRead(mediaType);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter, org.springframework.http.converter.HttpMessageConverter
    public boolean canWrite(Class<?> cls, MediaType mediaType) {
        return this.conversionService.canConvert(cls, String.class) && canWrite(mediaType);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected Long getContentLength(Object obj, MediaType mediaType) {
        return this.stringHttpMessageConverter.getContentLength((String) this.conversionService.convert(obj, String.class), mediaType);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected Object readInternal(Class<? extends Object> cls, HttpInputMessage httpInputMessage) throws IOException {
        return this.conversionService.convert(this.stringHttpMessageConverter.readInternal(String.class, httpInputMessage), cls);
    }

    public void setWriteAcceptCharset(boolean z) {
        this.stringHttpMessageConverter.setWriteAcceptCharset(z);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected boolean supports(Class<?> cls) {
        throw new UnsupportedOperationException();
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected void writeInternal(Object obj, HttpOutputMessage httpOutputMessage) throws IOException {
        this.stringHttpMessageConverter.writeInternal((String) this.conversionService.convert(obj, String.class), httpOutputMessage);
    }
}
