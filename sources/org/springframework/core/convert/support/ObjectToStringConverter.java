package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;

/* loaded from: classes2.dex */
final class ObjectToStringConverter implements Converter<Object, String> {
    @Override // org.springframework.core.convert.converter.Converter
    public String convert(Object obj) {
        return obj.toString();
    }
}
