package org.springframework.core.convert.support;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.CollectionFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;

/* loaded from: classes2.dex */
final class ObjectToCollectionConverter implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public ObjectToCollectionConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            return null;
        }
        Collection createCollection = CollectionFactory.createCollection(typeDescriptor2.getType(), 1);
        if (typeDescriptor2.getElementTypeDescriptor() == null || typeDescriptor2.getElementTypeDescriptor().isCollection()) {
            createCollection.add(obj);
        } else {
            createCollection.add(this.conversionService.convert(obj, typeDescriptor, typeDescriptor2.getElementTypeDescriptor()));
        }
        return createCollection;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Object.class, Collection.class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return ConversionUtils.canConvertElements(typeDescriptor, typeDescriptor2.getElementTypeDescriptor(), this.conversionService);
    }
}
