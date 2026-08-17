package org.springframework.core.convert.support;

import java.io.StringWriter;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;

/* loaded from: classes2.dex */
final class FallbackObjectToStringConverter implements ConditionalGenericConverter {
    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Object.class, String.class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        Class<?> objectType = typeDescriptor.getObjectType();
        if (String.class.equals(objectType)) {
            return false;
        }
        return CharSequence.class.isAssignableFrom(objectType) || StringWriter.class.isAssignableFrom(objectType) || ObjectToObjectConverter.hasValueOfMethodOrConstructor(objectType, String.class);
    }
}
