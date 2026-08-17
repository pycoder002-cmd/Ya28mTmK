package org.springframework.core.convert.support;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;

/* loaded from: classes2.dex */
final class ArrayToObjectConverter implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public ArrayToObjectConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            return null;
        }
        if (typeDescriptor.isAssignableTo(typeDescriptor2)) {
            return obj;
        }
        if (Array.getLength(obj) == 0) {
            return null;
        }
        Object obj2 = Array.get(obj, 0);
        return this.conversionService.convert(obj2, typeDescriptor.elementTypeDescriptor(obj2), typeDescriptor2);
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Object[].class, Object.class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return ConversionUtils.canConvertElements(typeDescriptor.getElementTypeDescriptor(), typeDescriptor2, this.conversionService);
    }
}
