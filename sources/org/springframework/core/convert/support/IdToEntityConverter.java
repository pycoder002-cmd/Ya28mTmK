package org.springframework.core.convert.support;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Set;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

/* loaded from: classes2.dex */
final class IdToEntityConverter implements ConditionalGenericConverter {
    private final ConversionService conversionService;

    public IdToEntityConverter(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    private String getEntityName(Class<?> cls) {
        String shortName = ClassUtils.getShortName(cls);
        int lastIndexOf = shortName.lastIndexOf(46);
        return lastIndexOf != -1 ? shortName.substring(lastIndexOf + 1) : shortName;
    }

    private Method getFinder(Class<?> cls) {
        Method[] methods;
        boolean z;
        String str = "find" + getEntityName(cls);
        try {
            methods = cls.getDeclaredMethods();
            z = true;
        } catch (SecurityException unused) {
            methods = cls.getMethods();
            z = false;
        }
        for (Method method : methods) {
            if (Modifier.isStatic(method.getModifiers()) && method.getName().equals(str) && method.getParameterTypes().length == 1 && method.getReturnType().equals(cls) && (z || method.getDeclaringClass().equals(cls))) {
                return method;
            }
        }
        return null;
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        if (obj == null) {
            return null;
        }
        Method finder = getFinder(typeDescriptor2.getType());
        return ReflectionUtils.invokeMethod(finder, obj, this.conversionService.convert(obj, typeDescriptor, TypeDescriptor.valueOf(finder.getParameterTypes()[0])));
    }

    @Override // org.springframework.core.convert.converter.GenericConverter
    public Set<GenericConverter.ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new GenericConverter.ConvertiblePair(Object.class, Object.class));
    }

    @Override // org.springframework.core.convert.converter.ConditionalConverter
    public boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        Method finder = getFinder(typeDescriptor2.getType());
        return finder != null && this.conversionService.canConvert(typeDescriptor, TypeDescriptor.valueOf(finder.getParameterTypes()[0]));
    }
}
