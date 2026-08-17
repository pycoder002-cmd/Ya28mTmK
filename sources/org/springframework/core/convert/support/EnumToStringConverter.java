package org.springframework.core.convert.support;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.ClassUtils;

/* loaded from: classes2.dex */
final class EnumToStringConverter implements Converter<Enum<?>, String>, ConditionalConverter {
    private final ConversionService conversionService;

    public EnumToStringConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override // org.springframework.core.convert.converter.Converter
    public String convert(Enum<?> r1) {
        return r1.name();
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        for (Class<?> cls : ClassUtils.getAllInterfacesForClass(typeDescriptor.getType())) {
            if (this.conversionService.canConvert(TypeDescriptor.valueOf(cls), typeDescriptor2)) {
                return false;
            }
        }
        return true;
    }
}
