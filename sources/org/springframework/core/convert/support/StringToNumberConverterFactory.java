package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.NumberUtils;

/* loaded from: classes2.dex */
final class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    /* loaded from: classes2.dex */
    private static final class StringToNumber<T extends Number> implements Converter<String, T> {
        private final Class<T> targetType;

        public StringToNumber(Class<T> cls) {
            this.targetType = cls;
        }

        @Override // org.springframework.core.convert.converter.Converter
        public T convert(String str) {
            if (str.length() == 0) {
                return null;
            }
            return (T) NumberUtils.parseNumber(str, this.targetType);
        }
    }

    @Override // org.springframework.core.convert.converter.ConverterFactory
    public <T extends Number> Converter<String, T> getConverter(Class<T> cls) {
        return new StringToNumber(cls);
    }
}
