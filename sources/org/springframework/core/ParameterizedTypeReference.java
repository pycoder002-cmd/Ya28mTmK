package org.springframework.core;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public abstract class ParameterizedTypeReference<T> {
    private final Type type;

    protected ParameterizedTypeReference() {
        Type genericSuperclass = findParameterizedTypeReferenceSubclass(getClass()).getGenericSuperclass();
        Assert.isInstanceOf(ParameterizedType.class, genericSuperclass);
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Assert.isTrue(parameterizedType.getActualTypeArguments().length == 1);
        this.type = parameterizedType.getActualTypeArguments()[0];
    }

    private static Class<?> findParameterizedTypeReferenceSubclass(Class<?> cls) {
        Class<? super Object> superclass = cls.getSuperclass();
        if (Object.class.equals(superclass)) {
            throw new IllegalStateException("Expected ParameterizedTypeReference superclass");
        }
        return ParameterizedTypeReference.class.equals(superclass) ? cls : findParameterizedTypeReferenceSubclass(superclass);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ParameterizedTypeReference) && this.type.equals(((ParameterizedTypeReference) obj).type));
    }

    public Type getType() {
        return this.type;
    }

    public int hashCode() {
        return this.type.hashCode();
    }

    public String toString() {
        return "ParameterizedTypeReference<" + this.type + ">";
    }
}
