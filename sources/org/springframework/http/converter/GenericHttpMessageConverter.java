package org.springframework.http.converter;

import java.io.IOException;
import java.lang.reflect.Type;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;

/* loaded from: classes2.dex */
public interface GenericHttpMessageConverter<T> extends HttpMessageConverter<T> {
    boolean canRead(Type type, Class<?> cls, MediaType mediaType);

    T read(Type type, Class<?> cls, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException;
}
