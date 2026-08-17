package org.springframework.http.converter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import kotlin.text.Typography;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
public class FormHttpMessageConverter implements HttpMessageConverter<MultiValueMap<String, ?>> {
    private static final byte[] BOUNDARY_CHARS = {45, 95, 49, 50, 51, 52, 53, 54, 55, 56, 57, 48, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    private final Random rnd = new Random();
    private Charset charset = Charset.forName("UTF-8");
    private List<MediaType> supportedMediaTypes = new ArrayList();
    private List<HttpMessageConverter<?>> partConverters = new ArrayList();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class MultipartHttpOutputMessage implements HttpOutputMessage {
        private final HttpHeaders headers = new HttpHeaders();
        private boolean headersWritten = false;
        private final OutputStream os;

        public MultipartHttpOutputMessage(OutputStream outputStream) {
            this.os = outputStream;
        }

        private void writeHeaders() throws IOException {
            if (this.headersWritten) {
                return;
            }
            for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
                byte[] asciiBytes = getAsciiBytes(entry.getKey());
                Iterator<String> it = entry.getValue().iterator();
                while (it.hasNext()) {
                    byte[] asciiBytes2 = getAsciiBytes(it.next());
                    this.os.write(asciiBytes);
                    this.os.write(58);
                    this.os.write(32);
                    this.os.write(asciiBytes2);
                    FormHttpMessageConverter.this.writeNewLine(this.os);
                }
            }
            FormHttpMessageConverter.this.writeNewLine(this.os);
            this.headersWritten = true;
        }

        protected byte[] getAsciiBytes(String str) {
            try {
                return str.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // org.springframework.http.HttpOutputMessage
        public OutputStream getBody() throws IOException {
            writeHeaders();
            return this.os;
        }

        @Override // org.springframework.http.HttpMessage
        public HttpHeaders getHeaders() {
            return this.headersWritten ? HttpHeaders.readOnlyHttpHeaders(this.headers) : this.headers;
        }
    }

    public FormHttpMessageConverter() {
        this.supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        this.supportedMediaTypes.add(MediaType.MULTIPART_FORM_DATA);
        this.partConverters.add(new ByteArrayHttpMessageConverter());
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        this.partConverters.add(stringHttpMessageConverter);
        this.partConverters.add(new ResourceHttpMessageConverter());
    }

    private HttpEntity getEntity(Object obj) {
        return obj instanceof HttpEntity ? (HttpEntity) obj : new HttpEntity(obj);
    }

    private boolean isMultipart(MultiValueMap<String, ?> multiValueMap, MediaType mediaType) {
        if (mediaType != null) {
            return MediaType.MULTIPART_FORM_DATA.includes(mediaType);
        }
        Iterator<String> it = multiValueMap.keySet().iterator();
        while (it.hasNext()) {
            for (Object obj : (List) multiValueMap.get(it.next())) {
                if (obj != null && !(obj instanceof String)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void writeBoundary(byte[] bArr, OutputStream outputStream) throws IOException {
        outputStream.write(45);
        outputStream.write(45);
        outputStream.write(bArr);
        writeNewLine(outputStream);
    }

    private void writeEnd(byte[] bArr, OutputStream outputStream) throws IOException {
        outputStream.write(45);
        outputStream.write(45);
        outputStream.write(bArr);
        outputStream.write(45);
        outputStream.write(45);
        writeNewLine(outputStream);
    }

    private void writeForm(MultiValueMap<String, String> multiValueMap, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException {
        Charset charset;
        if (mediaType != null) {
            httpOutputMessage.getHeaders().setContentType(mediaType);
            charset = mediaType.getCharSet() != null ? mediaType.getCharSet() : this.charset;
        } else {
            httpOutputMessage.getHeaders().setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            charset = this.charset;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = multiValueMap.keySet().iterator();
        while (it.hasNext()) {
            String next = it.next();
            Iterator it2 = ((List) multiValueMap.get(next)).iterator();
            while (it2.hasNext()) {
                String str = (String) it2.next();
                sb.append(URLEncoder.encode(next, charset.name()));
                if (str != null) {
                    sb.append('=');
                    sb.append(URLEncoder.encode(str, charset.name()));
                    if (it2.hasNext()) {
                        sb.append(Typography.amp);
                    }
                }
            }
            if (it.hasNext()) {
                sb.append(Typography.amp);
            }
        }
        byte[] bytes = sb.toString().getBytes(charset.name());
        httpOutputMessage.getHeaders().setContentLength(bytes.length);
        StreamUtils.copy(bytes, httpOutputMessage.getBody());
    }

    private void writeMultipart(MultiValueMap<String, Object> multiValueMap, HttpOutputMessage httpOutputMessage) throws IOException {
        byte[] generateMultipartBoundary = generateMultipartBoundary();
        httpOutputMessage.getHeaders().setContentType(new MediaType(MediaType.MULTIPART_FORM_DATA, (Map<String, String>) Collections.singletonMap("boundary", new String(generateMultipartBoundary, "US-ASCII"))));
        writeParts(httpOutputMessage.getBody(), multiValueMap, generateMultipartBoundary);
        writeEnd(generateMultipartBoundary, httpOutputMessage.getBody());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeNewLine(OutputStream outputStream) throws IOException {
        outputStream.write(13);
        outputStream.write(10);
    }

    private void writePart(String str, HttpEntity httpEntity, OutputStream outputStream) throws IOException {
        Object body = httpEntity.getBody();
        Class<?> cls = body.getClass();
        HttpHeaders headers = httpEntity.getHeaders();
        MediaType contentType = headers.getContentType();
        for (HttpMessageConverter<?> httpMessageConverter : this.partConverters) {
            if (httpMessageConverter.canWrite(cls, contentType)) {
                MultipartHttpOutputMessage multipartHttpOutputMessage = new MultipartHttpOutputMessage(outputStream);
                multipartHttpOutputMessage.getHeaders().setContentDispositionFormData(str, getFilename(body));
                if (!headers.isEmpty()) {
                    multipartHttpOutputMessage.getHeaders().putAll(headers);
                }
                httpMessageConverter.write(body, contentType, multipartHttpOutputMessage);
                return;
            }
        }
        throw new HttpMessageNotWritableException("Could not write request: no suitable HttpMessageConverter found for request type [" + cls.getName() + "]");
    }

    private void writeParts(OutputStream outputStream, MultiValueMap<String, Object> multiValueMap, byte[] bArr) throws IOException {
        for (Map.Entry<String, Object> entry : multiValueMap.entrySet()) {
            String key = entry.getKey();
            for (Object obj : (List) entry.getValue()) {
                if (obj != null) {
                    writeBoundary(bArr, outputStream);
                    writePart(key, getEntity(obj), outputStream);
                    writeNewLine(outputStream);
                }
            }
        }
    }

    public final void addPartConverter(HttpMessageConverter<?> httpMessageConverter) {
        Assert.notNull(httpMessageConverter, "'partConverter' must not be NULL");
        this.partConverters.add(httpMessageConverter);
    }

    @Override // org.springframework.http.converter.HttpMessageConverter
    public boolean canRead(Class<?> cls, MediaType mediaType) {
        if (!MultiValueMap.class.isAssignableFrom(cls)) {
            return false;
        }
        if (mediaType == null) {
            return true;
        }
        for (MediaType mediaType2 : getSupportedMediaTypes()) {
            if (!mediaType2.equals(MediaType.MULTIPART_FORM_DATA) && mediaType2.includes(mediaType)) {
                return true;
            }
        }
        return false;
    }

    @Override // org.springframework.http.converter.HttpMessageConverter
    public boolean canWrite(Class<?> cls, MediaType mediaType) {
        if (!MultiValueMap.class.isAssignableFrom(cls)) {
            return false;
        }
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

    protected byte[] generateMultipartBoundary() {
        byte[] bArr = new byte[this.rnd.nextInt(11) + 30];
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = BOUNDARY_CHARS[this.rnd.nextInt(BOUNDARY_CHARS.length)];
        }
        return bArr;
    }

    protected String getFilename(Object obj) {
        if (obj instanceof Resource) {
            return ((Resource) obj).getFilename();
        }
        return null;
    }

    @Override // org.springframework.http.converter.HttpMessageConverter
    public List<MediaType> getSupportedMediaTypes() {
        return Collections.unmodifiableList(this.supportedMediaTypes);
    }

    @Override // org.springframework.http.converter.HttpMessageConverter
    public MultiValueMap<String, ?> read(Class<? extends MultiValueMap<String, ?>> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        MediaType contentType = httpInputMessage.getHeaders().getContentType();
        Charset charSet = contentType.getCharSet() != null ? contentType.getCharSet() : this.charset;
        String[] strArr = StringUtils.tokenizeToStringArray(StreamUtils.copyToString(httpInputMessage.getBody(), charSet), "&");
        LinkedMultiValueMap linkedMultiValueMap = new LinkedMultiValueMap(strArr.length);
        for (String str : strArr) {
            int indexOf = str.indexOf(61);
            if (indexOf == -1) {
                linkedMultiValueMap.add(URLDecoder.decode(str, charSet.name()), null);
            } else {
                linkedMultiValueMap.add(URLDecoder.decode(str.substring(0, indexOf), charSet.name()), URLDecoder.decode(str.substring(indexOf + 1), charSet.name()));
            }
        }
        return linkedMultiValueMap;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public final void setPartConverters(List<HttpMessageConverter<?>> list) {
        Assert.notEmpty(list, "'partConverters' must not be empty");
        this.partConverters = list;
    }

    public void setSupportedMediaTypes(List<MediaType> list) {
        this.supportedMediaTypes = list;
    }

    @Override // org.springframework.http.converter.HttpMessageConverter
    public void write(MultiValueMap<String, ?> multiValueMap, MediaType mediaType, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        if (isMultipart(multiValueMap, mediaType)) {
            writeMultipart(multiValueMap, httpOutputMessage);
        } else {
            writeForm(multiValueMap, mediaType, httpOutputMessage);
        }
    }
}
