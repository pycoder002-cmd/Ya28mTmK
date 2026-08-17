package org.springframework.core.convert.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

/* loaded from: classes2.dex */
final class ObjectToObjectConverter implements ConditionalGenericConverter {
    private static Constructor<?> getConstructor(Class<?> cls, Class<?> cls2) {
        return ClassUtils.getConstructorIfAvailable(cls, cls2);
    }

    private static Method getValueOfMethodOn(Class<?> cls, Class<?> cls2) {
        return ClassUtils.getStaticMethod(cls, "valueOf", cls2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasValueOfMethodOrConstructor(Class<?> cls, Class<?> cls2) {
        return (getValueOfMethodOn(cls, cls2) == null && getConstructor(cls, cls2) == null) ? false : true;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            return null;
        }
        Class<?> type = typeDescriptor.getType();
        Class<?> type2 = typeDescriptor2.getType();
        Method valueOfMethodOn = getValueOfMethodOn(type2, type);
        try {
            if (valueOfMethodOn != null) {
                ReflectionUtils.makeAccessible(valueOfMethodOn);
                return valueOfMethodOn.invoke(null, obj);
            }
            Constructor<?> constructor = getConstructor(type2, type);
            if (constructor != null) {
                return constructor.newInstance(obj);
            }
            throw new IllegalStateException("No static valueOf(" + type.getName() + ") method or Constructor(" + type.getName() + ") exists on " + type2.getName());
        } catch (InvocationTargetException e) {
            throw new ConversionFailedException(typeDescriptor, typeDescriptor2, obj, e.getTargetException());
        } catch (Throwable th) {
            throw new ConversionFailedException(typeDescriptor, typeDescriptor2, obj, th);
        }
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Object.class, Object.class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (typeDescriptor.getType().equals(typeDescriptor2.getType())) {
            return false;
        }
        return hasValueOfMethodOrConstructor(typeDescriptor2.getType(), typeDescriptor.getType());
    }
}
