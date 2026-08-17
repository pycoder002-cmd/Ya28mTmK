package org.springframework.http.converter.xml;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class SimpleXmlHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Serializer serializer;

    public SimpleXmlHttpMessageConverter() {
        this(new Persister());
    }

    public SimpleXmlHttpMessageConverter(Serializer serializer) {
        super(MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_WILDCARD_XML);
        setSerializer(serializer);
    }

    private Charset getCharset(HttpHeaders httpHeaders) {
        return (httpHeaders == null || httpHeaders.getContentType() == null || httpHeaders.getContentType().getCharSet() == null) ? DEFAULT_CHARSET : httpHeaders.getContentType().getCharSet();
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter, org.springframework.http.converter.HttpMessageConverter
    public boolean canRead(Class<?> cls, MediaType mediaType) {
        return canRead(mediaType);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter, org.springframework.http.converter.HttpMessageConverter
    public boolean canWrite(Class<?> cls, MediaType mediaType) {
        return cls.isAnnotationPresent(Root.class) && canWrite(mediaType);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected Object readInternal(Class<? extends Object> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        try {
            Object read = this.serializer.read(cls, new InputStreamReader(httpInputMessage.getBody(), getCharset(httpInputMessage.getHeaders())));
            if (cls.isInstance(read)) {
                return read;
            }
            throw new TypeMismatchException(read, cls);
        } catch (Exception e) {
            throw new HttpMessageNotReadableException("Could not read [" + cls + "]", e);
        }
    }

    public void setSerializer(Serializer serializer) {
        Assert.notNull(serializer, "'serializer' must not be null");
        this.serializer = serializer;
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected boolean supports(Class<?> cls) {
        throw new UnsupportedOperationException();
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected void writeInternal(Object obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpOutputMessage.getBody(), getCharset(httpOutputMessage.getHeaders()));
        try {
            this.serializer.write(obj, outputStreamWriter);
            outputStreamWriter.close();
        } catch (Exception e) {
            throw new HttpMessageNotWritableException("Could not write [" + obj + "]", e);
        }
    }
}
