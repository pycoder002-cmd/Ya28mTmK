package org.springframework.core.convert.support;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.util.NumberUtils;

/* loaded from: classes2.dex */
final class NumberToNumberConverterFactory implements ConverterFactory<Number, Number>, ConditionalConverter {

    /* loaded from: classes2.dex */
    private static final class NumberToNumber<T extends Number> implements Converter<Number, T> {
        private final Class<T> targetType;

        public NumberToNumber(Class<T> cls) {
            this.targetType = cls;
        }

        @Override // org.springframework.core.convert.converter.Converter
        public T convert(Number number) {
            return (T) NumberUtils.convertNumberToTargetClass(number, this.targetType);
        }
    }

    @Override // org.springframework.core.convert.converter.ConverterFactory
    public <T extends Number> Converter<Number, T> getConverter(Class<T> cls) {
        return new NumberToNumber(cls);
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return !typeDescriptor.equals(typeDescriptor2);
    }
}
