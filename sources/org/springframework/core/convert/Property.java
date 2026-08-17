package org.springframework.core.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

/* loaded from: classes2.dex */
public final class Property {
    private static Map<Property, Annotation[]> annotationCache = new ConcurrentReferenceHashMap();
    private Annotation[] annotations;
    private final MethodParameter methodParameter;
    private final String name;
    private final Class<?> objectType;
    private final Method readMethod;
    private final Method writeMethod;

    public Property(Class<?> cls, Method method, Method method2) {
        this(cls, method, method2, null);
    }

    public Property(Class<?> cls, Method method, Method method2, String str) {
        this.objectType = cls;
        this.readMethod = method;
        this.writeMethod = method2;
        this.methodParameter = resolveMethodParameter();
        this.name = str == null ? resolveName() : str;
    }

    private void addAnnotationsToMap(Map<Class<? extends Annotation>, Annotation> map, AnnotatedElement annotatedElement) {
        if (annotatedElement != null) {
            for (Annotation annotation : annotatedElement.getAnnotations()) {
                map.put(annotation.annotationType(), annotation);
            }
        }
    }

    private Class<?> declaringClass() {
        return getReadMethod() != null ? getReadMethod().getDeclaringClass() : getWriteMethod().getDeclaringClass();
    }

    private Field getField() {
        String name = getName();
        if (!StringUtils.hasLength(name)) {
            return null;
        }
        Class<?> declaringClass = declaringClass();
        Field findField = ReflectionUtils.findField(declaringClass, name);
        if (findField != null) {
            return findField;
        }
        Field findField2 = ReflectionUtils.findField(declaringClass, name.substring(0, 1).toLowerCase() + name.substring(1));
        if (findField2 != null) {
            return findField2;
        }
        return ReflectionUtils.findField(declaringClass, name.substring(0, 1).toUpperCase() + name.substring(1));
    }

    private Annotation[] resolveAnnotations() {
        Annotation[] annotationArr = annotationCache.get(this);
        if (annotationArr != null) {
            return annotationArr;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        addAnnotationsToMap(linkedHashMap, getReadMethod());
        addAnnotationsToMap(linkedHashMap, getWriteMethod());
        addAnnotationsToMap(linkedHashMap, getField());
        Annotation[] annotationArr2 = (Annotation[]) linkedHashMap.values().toArray(new Annotation[linkedHashMap.size()]);
        annotationCache.put(this, annotationArr2);
        return annotationArr2;
    }

    private MethodParameter resolveMethodParameter() {
        MethodParameter resolveReadMethodParameter = resolveReadMethodParameter();
        MethodParameter resolveWriteMethodParameter = resolveWriteMethodParameter();
        if (resolveWriteMethodParameter == null) {
            if (resolveReadMethodParameter == null) {
                throw new IllegalStateException("Property is neither readable nor writeable");
            }
            return resolveReadMethodParameter;
        }
        if (resolveReadMethodParameter != null) {
            Class<?> parameterType = resolveReadMethodParameter.getParameterType();
            Class<?> parameterType2 = resolveWriteMethodParameter.getParameterType();
            if (!parameterType2.equals(parameterType) && parameterType2.isAssignableFrom(parameterType)) {
                return resolveReadMethodParameter;
            }
        }
        return resolveWriteMethodParameter;
    }

    private String resolveName() {
        int i;
        if (this.readMethod == null) {
            int indexOf = this.writeMethod.getName().indexOf("set") + 3;
            if (indexOf == -1) {
                throw new IllegalArgumentException("Not a setter method");
            }
            return StringUtils.uncapitalize(this.writeMethod.getName().substring(indexOf));
        }
        int indexOf2 = this.readMethod.getName().indexOf("get");
        if (indexOf2 != -1) {
            i = indexOf2 + 3;
        } else {
            int indexOf3 = this.readMethod.getName().indexOf("is");
            if (indexOf3 == -1) {
                throw new IllegalArgumentException("Not a getter method");
            }
            i = indexOf3 + 2;
        }
        return StringUtils.uncapitalize(this.readMethod.getName().substring(i));
    }

    private MethodParameter resolveParameterType(MethodParameter methodParameter) {
        GenericTypeResolver.resolveParameterType(methodParameter, getObjectType());
        return methodParameter;
    }

    private MethodParameter resolveReadMethodParameter() {
        if (getReadMethod() == null) {
            return null;
        }
        return resolveParameterType(new MethodParameter(getReadMethod(), -1));
    }

    private MethodParameter resolveWriteMethodParameter() {
        if (getWriteMethod() == null) {
            return null;
        }
        return resolveParameterType(new MethodParameter(getWriteMethod(), 0));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Property)) {
            return false;
        }
        Property property = (Property) obj;
        return ObjectUtils.nullSafeEquals(this.objectType, property.objectType) && ObjectUtils.nullSafeEquals(this.name, property.name) && ObjectUtils.nullSafeEquals(this.readMethod, property.readMethod) && ObjectUtils.nullSafeEquals(this.writeMethod, property.writeMethod);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Annotation[] getAnnotations() {
        if (this.annotations == null) {
            this.annotations = resolveAnnotations();
        }
        return this.annotations;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MethodParameter getMethodParameter() {
        return this.methodParameter;
    }

    public String getName() {
        return this.name;
    }

    public Class<?> getObjectType() {
        return this.objectType;
    }

    public Method getReadMethod() {
        return this.readMethod;
    }

    public Class<?> getType() {
        return this.methodParameter.getParameterType();
    }

    public Method getWriteMethod() {
        return this.writeMethod;
    }

    public int hashCode() {
        return (ObjectUtils.nullSafeHashCode(this.objectType) * 31) + ObjectUtils.nullSafeHashCode(this.name);
    }
}
