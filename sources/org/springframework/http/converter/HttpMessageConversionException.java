package org.springframework.http.converter;

import org.springframework.core.NestedRuntimeException;

/* loaded from: classes2.dex */
public class HttpMessageConversionException extends NestedRuntimeException {
    public HttpMessageConversionException(String str) {
        super(str);
    }

    public HttpMessageConversionException(String str, Throwable th) {
        super(str, th);
    }
}
