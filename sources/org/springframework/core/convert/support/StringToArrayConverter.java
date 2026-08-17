package org.springframework.core.convert.support;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
final class StringToArrayConverter implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public StringToArrayConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            return null;
        }
        String[] commaDelimitedListToStringArray = StringUtils.commaDelimitedListToStringArray((String) obj);
        Object newInstance = Array.newInstance(typeDescriptor2.getElementTypeDescriptor().getType(), commaDelimitedListToStringArray.length);
        for (int i = 0; i < commaDelimitedListToStringArray.length; i++) {
            Array.set(newInstance, i, this.conversionService.convert(commaDelimitedListToStringArray[i].trim(), typeDescriptor, typeDescriptor2.getElementTypeDescriptor()));
        }
        return newInstance;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(String.class, Object[].class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return this.conversionService.canConvert(typeDescriptor, typeDescriptor2.getElementTypeDescriptor());
    }
}
