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
final class CollectionToCollectionConverter implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public CollectionToCollectionConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            return null;
        }
        Collection collection = (Collection) obj;
        boolean z = !typeDescriptor2.getType().isInstance(obj);
        if (!z && collection.isEmpty()) {
            return obj;
        }
        TypeDescriptor elementTypeDescriptor = typeDescriptor2.getElementTypeDescriptor();
        if (elementTypeDescriptor == null && !z) {
            return obj;
        }
        Collection createCollection = CollectionFactory.createCollection(typeDescriptor2.getType(), collection.size());
        if (elementTypeDescriptor == null) {
            createCollection.addAll(collection);
        } else {
            for (Object obj2 : collection) {
                Object convert = this.conversionService.convert(obj2, typeDescriptor.elementTypeDescriptor(obj2), elementTypeDescriptor);
                createCollection.add(convert);
                if (obj2 != convert) {
                    z = true;
                }
            }
        }
        return z ? createCollection : obj;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Collection.class, Collection.class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return ConversionUtils.canConvertElements(typeDescriptor.getElementTypeDescriptor(), typeDescriptor2.getElementTypeDescriptor(), this.conversionService);
    }
}
