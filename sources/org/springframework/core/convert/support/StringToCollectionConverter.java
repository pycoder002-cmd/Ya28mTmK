package org.springframework.core.convert.support;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.CollectionFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
final class StringToCollectionConverter implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public StringToCollectionConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            return null;
        }
        String[] commaDelimitedListToStringArray = StringUtils.commaDelimitedListToStringArray((String) obj);
        int i = 0;
        Collection createCollection = CollectionFactory.createCollection(typeDescriptor2.getType(), commaDelimitedListToStringArray.length);
        if (typeDescriptor2.getElementTypeDescriptor() == null) {
            int length = commaDelimitedListToStringArray.length;
            while (i < length) {
                createCollection.add(commaDelimitedListToStringArray[i].trim());
                i++;
            }
        } else {
            int length2 = commaDelimitedListToStringArray.length;
            while (i < length2) {
                createCollection.add(this.conversionService.convert(commaDelimitedListToStringArray[i].trim(), typeDescriptor, typeDescriptor2.getElementTypeDescriptor()));
                i++;
            }
        }
        return createCollection;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(String.class, Collection.class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        return typeDescriptor2.getElementTypeDescriptor() == null || this.conversionService.canConvert(typeDescriptor, typeDescriptor2.getElementTypeDescriptor());
    }
}
