package org.springframework.core.convert.support;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;
import org.springframework.core.convert.converter.Converter;

/* loaded from: classes2.dex */
final class PropertiesToStringConverter implements Converter<Properties, String> {
    @Override // org.springframework.core.convert.converter.Converter
    public String convert(Properties properties) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
            properties.store(byteArrayOutputStream, (String) null);
            return byteArrayOutputStream.toString("ISO-8859-1");
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to store [" + properties + "] into String", e);
        }
    }
}
