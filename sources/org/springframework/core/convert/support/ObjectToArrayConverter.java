package org.springframework.core.convert.support;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;

/* loaded from: classes2.dex */
final class ObjectToArrayConverter implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public ObjectToArrayConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            return null;
        }
        Object newInstance = Array.newInstance(typeDescriptor2.getElementTypeDescriptor().getType(), 1);
        Array.set(newInstance, 0, this.conversionService.convert(obj, typeDescriptor, typeDescriptor2.getElementTypeDescriptor()));
        return newInstance;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Object.class, Object[].class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return ConversionUtils.canConvertElements(typeDescriptor, typeDescriptor2.getElementTypeDescriptor(), this.conversionService);
    }
}
