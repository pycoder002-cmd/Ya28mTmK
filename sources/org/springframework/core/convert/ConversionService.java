package org.springframework.core.convert;

/* loaded from: classes2.dex */
public interface ConversionService {
    boolean canConvert(Class<?> cls, Class<?> cls2);

    boolean canConvert(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2);

    <T> T convert(Object obj, Class<T> cls);

    Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2);
}
