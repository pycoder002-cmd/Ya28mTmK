package org.springframework.core.convert.support;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

/* loaded from: classes2.dex */
abstract class ConversionUtils {
    ConversionUtils() {
    }

    public static boolean canConvertElements(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2, ConversionService conversionService) {
        return typeDescriptor2 == null || typeDescriptor == null || conversionService.canConvert(typeDescriptor, typeDescriptor2) || typeDescriptor.getType().isAssignableFrom(typeDescriptor2.getType());
    }

    public static Object invokeConverter(GenericConverter genericConverter, Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        try {
            return genericConverter.convert(obj, typeDescriptor, typeDescriptor2);
        } catch (ConversionFailedException e) {
            throw e;
        } catch (Exception e2) {
            throw new ConversionFailedException(typeDescriptor, typeDescriptor2, obj, e2);
        }
    }
}
