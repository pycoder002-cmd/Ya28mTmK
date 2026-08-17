package org.springframework.http.converter.xml;

import java.io.IOException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

/* loaded from: classes2.dex */
public abstract class AbstractXmlHttpMessageConverter<T> extends AbstractHttpMessageConverter<T> {
    private final TransformerFactory transformerFactory;

    protected AbstractXmlHttpMessageConverter() {
        super(MediaType.APPLICATION_XML, MediaType.TEXT_XML, new MediaType("application", "*+xml"));
        this.transformerFactory = TransformerFactory.newInstance();
    }

    protected abstract T readFromSource(Class<? extends T> cls, HttpHeaders httpHeaders, Source source) throws IOException;

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public final T readInternal(Class<? extends T> cls, HttpInputMessage httpInputMessage) throws IOException {
        return readFromSource(cls, httpInputMessage.getHeaders(), new StreamSource(httpInputMessage.getBody()));
    }

    protected void transform(Source source, Result result) throws TransformerException {
        this.transformerFactory.newTransformer().transform(source, result);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected final void writeInternal(T t, HttpOutputMessage httpOutputMessage) throws IOException {
        writeToResult(t, httpOutputMessage.getHeaders(), new StreamResult(httpOutputMessage.getBody()));
    }

    protected abstract void writeToResult(T t, HttpHeaders httpHeaders, Result result) throws IOException;
}
