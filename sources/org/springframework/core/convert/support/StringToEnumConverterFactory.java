package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/* loaded from: classes2.dex */
final class StringToEnumConverterFactory implements ConverterFactory<String, Enum> {

    /* loaded from: classes2.dex */
    private class StringToEnum<T extends Enum> implements Converter<String, T> {
        private final Class<T> enumType;

        public StringToEnum(Class<T> cls) {
            this.enumType = cls;
        }

        @Override // org.springframework.core.convert.converter.Converter
        public T convert(String str) {
            if (str.length() == 0) {
                return null;
            }
            return (T) Enum.valueOf(this.enumType, str.trim());
        }
    }

    @Override // org.springframework.core.convert.converter.ConverterFactory
    public <T extends Enum> Converter<String, T> getConverter(Class<T> cls) {
        Class<T> cls2 = cls;
        while (cls2 != null && !cls2.isEnum()) {
            cls2 = cls2.getSuperclass();
        }
        if (cls2 != null) {
            return new StringToEnum(cls2);
        }
        throw new IllegalArgumentException("The target type " + cls.getName() + " does not refer to an enum");
    }
}
