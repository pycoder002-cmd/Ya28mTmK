package org.springframework.core.convert.support;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.ObjectUtils;

/* loaded from: classes2.dex */
final class ArrayToArrayConverter implements ConditionalGenericConverter {
    private final ConversionService conversionService;
    private final CollectionToArrayConverter helperConverter;

    public ArrayToArrayConverter(ConversionService conversionService) {
        this.helperConverter = new CollectionToArrayConverter(conversionService);
        this.conversionService = conversionService;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if ((this.conversionService instanceof GenericConversionService) && ((GenericConversionService) this.conversionService).canBypassConvert(typeDescriptor.getElementTypeDescriptor(), typeDescriptor2.getElementTypeDescriptor())) {
            return obj;
        }
        return this.helperConverter.convert(Arrays.asList(ObjectUtils.toObjectArray(obj)), typeDescriptor, typeDescriptor2);
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Object[].class, Object[].class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return this.helperConverter.matches(typeDescriptor, typeDescriptor2);
    }
}
