package org.springframework.core.convert.support;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.CollectionFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;

/* loaded from: classes2.dex */
final class ArrayToCollectionConverter implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public ArrayToCollectionConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            return null;
        }
        int length = Array.getLength(obj);
        Collection createCollection = CollectionFactory.createCollection(typeDescriptor2.getType(), length);
        int i = 0;
        if (typeDescriptor2.getElementTypeDescriptor() == null) {
            while (i < length) {
                createCollection.add(Array.get(obj, i));
                i++;
            }
        } else {
            while (i < length) {
                Object obj2 = Array.get(obj, i);
                createCollection.add(this.conversionService.convert(obj2, typeDescriptor.elementTypeDescriptor(obj2), typeDescriptor2.getElementTypeDescriptor()));
                i++;
            }
        }
        return createCollection;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Object[].class, Collection.class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return ConversionUtils.canConvertElements(typeDescriptor.getElementTypeDescriptor(), typeDescriptor2.getElementTypeDescriptor(), this.conversionService);
    }
}
