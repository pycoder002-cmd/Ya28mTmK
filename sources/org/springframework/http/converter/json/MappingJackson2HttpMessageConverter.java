package org.springframework.http.converter.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class MappingJackson2HttpMessageConverter extends AbstractHttpMessageConverter<Object> implements GenericHttpMessageConverter<Object> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private String jsonPrefix;
    private ObjectMapper objectMapper;
    private Boolean prettyPrint;

    public MappingJackson2HttpMessageConverter() {
        super(new MediaType("application", "json", DEFAULT_CHARSET), new MediaType("application", "*+json", DEFAULT_CHARSET));
        this.objectMapper = new ObjectMapper();
    }

    private void configurePrettyPrint() {
        if (this.prettyPrint != null) {
            this.objectMapper.configure(SerializationFeature.INDENT_OUTPUT, this.prettyPrint.booleanValue());
        }
    }

    private Object readJavaType(JavaType javaType, HttpInputMessage httpInputMessage) {
        try {
            return this.objectMapper.readValue(httpInputMessage.getBody(), javaType);
        } catch (IOException e) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + e.getMessage(), e);
        }
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter, org.springframework.http.converter.HttpMessageConverter
    public boolean canRead(Class<?> cls, MediaType mediaType) {
        return canRead(cls, null, mediaType);
    }

    @Override // org.springframework.http.converter.GenericHttpMessageConverter
    public boolean canRead(Type type, Class<?> cls, MediaType mediaType) {
        return this.objectMapper.canDeserialize(getJavaType(type, cls)) && canRead(mediaType);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter, org.springframework.http.converter.HttpMessageConverter
    public boolean canWrite(Class<?> cls, MediaType mediaType) {
        return this.objectMapper.canSerialize(cls) && canWrite(mediaType);
    }

    protected JavaType getJavaType(Type type, Class<?> cls) {
        return cls != null ? this.objectMapper.getTypeFactory().constructType(type, cls) : this.objectMapper.constructType(type);
    }

    protected JsonEncoding getJsonEncoding(MediaType mediaType) {
        if (mediaType != null && mediaType.getCharSet() != null) {
            Charset charSet = mediaType.getCharSet();
            for (JsonEncoding jsonEncoding : JsonEncoding.values()) {
                if (charSet.name().equals(jsonEncoding.getJavaName())) {
                    return jsonEncoding;
                }
            }
        }
        return JsonEncoding.UTF8;
    }

    public ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    @Override // org.springframework.http.converter.GenericHttpMessageConverter
    public Object read(Type type, Class<?> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readJavaType(getJavaType(type, cls), httpInputMessage);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected Object readInternal(Class<? extends Object> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readJavaType(getJavaType(cls, null), httpInputMessage);
    }

    public void setJsonPrefix(String str) {
        this.jsonPrefix = str;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "ObjectMapper must not be null");
        this.objectMapper = objectMapper;
        configurePrettyPrint();
    }

    public void setPrefixJson(boolean z) {
        this.jsonPrefix = z ? "{} && " : null;
    }

    public void setPrettyPrint(boolean z) {
        this.prettyPrint = Boolean.valueOf(z);
        configurePrettyPrint();
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected boolean supports(Class<?> cls) {
        throw new UnsupportedOperationException();
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected void writeInternal(Object obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        JsonGenerator createGenerator = this.objectMapper.getFactory().createGenerator(httpOutputMessage.getBody(), getJsonEncoding(httpOutputMessage.getHeaders().getContentType()));
        if (this.objectMapper.isEnabled(SerializationFeature.INDENT_OUTPUT)) {
            createGenerator.useDefaultPrettyPrinter();
        }
        try {
            if (this.jsonPrefix != null) {
                createGenerator.writeRaw(this.jsonPrefix);
            }
            this.objectMapper.writeValue(createGenerator, obj);
        } catch (JsonProcessingException e) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + e.getMessage(), e);
        }
    }
}
