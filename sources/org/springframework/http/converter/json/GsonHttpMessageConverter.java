package org.springframework.http.converter.json;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public class GsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> implements GenericHttpMessageConverter<Object> {
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private Gson gson;
    private String jsonPrefix;

    public GsonHttpMessageConverter() {
        super(new MediaType("application", "json", DEFAULT_CHARSET), new MediaType("application", "*+json", DEFAULT_CHARSET));
        this.gson = new Gson();
    }

    private Charset getCharset(HttpHeaders httpHeaders) {
        return (httpHeaders == null || httpHeaders.getContentType() == null || httpHeaders.getContentType().getCharSet() == null) ? DEFAULT_CHARSET : httpHeaders.getContentType().getCharSet();
    }

    private Object readTypeToken(TypeToken<?> typeToken, HttpInputMessage httpInputMessage) throws IOException {
        try {
            return this.gson.fromJson(new InputStreamReader(httpInputMessage.getBody(), getCharset(httpInputMessage.getHeaders())), typeToken.getType());
        } catch (JsonParseException e) {
            throw new HttpMessageNotReadableException("Could not read JSON: " + e.getMessage(), e);
        }
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter, org.springframework.http.converter.HttpMessageConverter
    public boolean canRead(Class<?> cls, MediaType mediaType) {
        return canRead(mediaType);
    }

    @Override // org.springframework.http.converter.GenericHttpMessageConverter
    public boolean canRead(Type type, Class<?> cls, MediaType mediaType) {
        return canRead(mediaType);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter, org.springframework.http.converter.HttpMessageConverter
    public boolean canWrite(Class<?> cls, MediaType mediaType) {
        return canWrite(mediaType);
    }

    public Gson getGson() {
        return this.gson;
    }

    protected TypeToken<?> getTypeToken(Type type) {
        return TypeToken.get(type);
    }

    @Override // org.springframework.http.converter.GenericHttpMessageConverter
    public Object read(Type type, Class<?> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readTypeToken(getTypeToken(type), httpInputMessage);
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected Object readInternal(Class<? extends Object> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return readTypeToken(getTypeToken(cls), httpInputMessage);
    }

    public void setGson(Gson gson) {
        Assert.notNull(gson, "'gson' is required");
        this.gson = gson;
    }

    public void setJsonPrefix(String str) {
        this.jsonPrefix = str;
    }

    public void setPrefixJson(boolean z) {
        this.jsonPrefix = z ? "{} && " : null;
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected boolean supports(Class<?> cls) {
        throw new UnsupportedOperationException();
    }

    @Override // org.springframework.http.converter.AbstractHttpMessageConverter
    protected void writeInternal(Object obj, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpOutputMessage.getBody(), getCharset(httpOutputMessage.getHeaders()));
        try {
            if (this.jsonPrefix != null) {
                outputStreamWriter.append((CharSequence) this.jsonPrefix);
            }
            this.gson.toJson(obj, outputStreamWriter);
            outputStreamWriter.close();
        } catch (JsonIOException e) {
            throw new HttpMessageNotWritableException("Could not write JSON: " + e.getMessage(), e);
        }
    }
}
