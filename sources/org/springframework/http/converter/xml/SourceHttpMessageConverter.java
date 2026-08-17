package org.springframework.http.converter.xml;

import android.os.Build;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/* loaded from: classes2.dex */
public class SourceHttpMessageConverter<T extends Source> extends AbstractHttpMessageConverter<T> {
    private static final EntityResolver NO_OP_ENTITY_RESOLVER;
    private static final Set<Class<?>> SUPPORTED_CLASSES = new HashSet(4);
    private boolean processExternalEntities;
    private final TransformerFactory transformerFactory;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class CountingOutputStream extends OutputStream {
        long count;

        private CountingOutputStream() {
            this.count = 0L;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.count++;
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.count += bArr.length;
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.count += i2;
        }
    }

    static {
        SUPPORTED_CLASSES.add(DOMSource.class);
        SUPPORTED_CLASSES.add(SAXSource.class);
        SUPPORTED_CLASSES.add(StreamSource.class);
        SUPPORTED_CLASSES.add(Source.class);
        NO_OP_ENTITY_RESOLVER = new EntityResolver() { // from class: org.springframework.http.converter.xml.SourceHttpMessageConverter.1
            @Override // org.xml.sax.EntityResolver
            public InputSource resolveEntity(String str, String str2) {
                return new InputSource(new StringReader(""));
            }
        };
    }

    public SourceHttpMessageConverter() {
        super(MediaType.APPLICATION_XML, MediaType.TEXT_XML, new MediaType("application", "*+xml"));
        this.transformerFactory = TransformerFactory.newInstance();
        this.processExternalEntities = false;
    }

    private DOMSource readDOMSource(InputStream inputStream) throws IOException {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            newInstance.setNamespaceAware(true);
            DocumentBuilder newDocumentBuilder = newInstance.newDocumentBuilder();
            if (!isProcessExternalEntities()) {
                newDocumentBuilder.setEntityResolver(NO_OP_ENTITY_RESOLVER);
            }
            return new DOMSource(newDocumentBuilder.parse(inputStream));
        } catch (ParserConfigurationException e) {
            throw new HttpMessageNotReadableException("Could not set feature: " + e.getMessage(), e);
        } catch (SAXException e2) {
            throw new HttpMessageNotReadableException("Could not parse document: " + e2.getMessage(), e2);
        }
    }

    private SAXSource readSAXSource(InputStream inputStream) throws IOException {
        try {
            XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            if (Build.VERSION.SDK_INT >= 14) {
                xMLReader.setFeature("http://xml.org/sax/features/external-general-entities", isProcessExternalEntities());
            }
            byte[] copyToByteArray = StreamUtils.copyToByteArray(inputStream);
            if (!isProcessExternalEntities()) {
                xMLReader.setEntityResolver(NO_OP_ENTITY_RESOLVER);
            }
            return new SAXSource(xMLReader, new InputSource(new ByteArrayInputStream(copyToByteArray)));
        } catch (ParserConfigurationException e) {
            throw new HttpMessageNotReadableException("Could not parse document: " + e.getMessage(), e);
        } catch (SAXException e2) {
            throw new HttpMessageNotReadableException("Could not parse document: " + e2.getMessage(), e2);
        }
    }

    private StreamSource readStreamSource(InputStream inputStream) throws IOException {
        return new StreamSource(new ByteArrayInputStream(StreamUtils.copyToByteArray(inputStream)));
    }

    private void transform(Source source, Result result) throws TransformerException {
        this.transformerFactory.newTransformer().transform(source, result);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public Long getContentLength(T t, MediaType mediaType) {
        if (t instanceof DOMSource) {
            try {
                CountingOutputStream countingOutputStream = new CountingOutputStream();
                transform(t, new StreamResult(countingOutputStream));
                return Long.valueOf(countingOutputStream.count);
            } catch (TransformerException unused) {
            }
        }
        return null;
    }

    public boolean isProcessExternalEntities() {
        return this.processExternalEntities;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public T readInternal(Class<? extends T> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        InputStream body = httpInputMessage.getBody();
        if (DOMSource.class.equals(cls)) {
            return readDOMSource(body);
        }
        if (SAXSource.class.equals(cls)) {
            return readSAXSource(body);
        }
        if (StreamSource.class.equals(cls) || Source.class.equals(cls)) {
            return readStreamSource(body);
        }
        throw new HttpMessageConversionException("Could not read class [" + cls + "]. Only DOMSource, SAXSource, and StreamSource are supported.");
    }

    public void setProcessExternalEntities(boolean z) {
        this.processExternalEntities = z;
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public boolean supports(Class<?> cls) {
        return SUPPORTED_CLASSES.contains(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    public void writeInternal(T t, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        try {
            transform(t, new StreamResult(httpOutputMessage.getBody()));
        } catch (TransformerException e) {
            throw new HttpMessageNotWritableException("Could not transform [" + t + "] to output message", e);
        }
    }
}
