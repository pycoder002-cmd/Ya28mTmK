package org.springframework.core.convert;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import org.springframework.util.Assert;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class AbstractDescriptor {
    private final Class<?> type;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDescriptor(Class<?> cls) {
        Assert.notNull(cls, "Type must not be null");
        this.type = cls;
    }

    private boolean isArray() {
        return getType().isArray();
    }

    private boolean isCollection() {
        return Collection.class.isAssignableFrom(getType());
    }

    private boolean isMap() {
        return Map.class.isAssignableFrom(getType());
    }

    public abstract Annotation[] getAnnotations();

    public TypeDescriptor getElementTypeDescriptor() {
        if (!isCollection()) {
            if (isArray()) {
                return new TypeDescriptor(nested(getType().getComponentType(), 0));
            }
            return null;
        }
        Class<?> resolveCollectionElementType = resolveCollectionElementType();
        if (resolveCollectionElementType != null) {
            return new TypeDescriptor(nested(resolveCollectionElementType, 0));
        }
        return null;
    }

    public TypeDescriptor getMapKeyTypeDescriptor() {
        Class<?> resolveMapKeyType;
        if (!isMap() || (resolveMapKeyType = resolveMapKeyType()) == null) {
            return null;
        }
        return new TypeDescriptor(nested(resolveMapKeyType, 0));
    }

    public TypeDescriptor getMapValueTypeDescriptor() {
        Class<?> resolveMapValueType;
        if (!isMap() || (resolveMapValueType = resolveMapValueType()) == null) {
            return null;
        }
        return new TypeDescriptor(nested(resolveMapValueType, 1));
    }

    public Class<?> getType() {
        return this.type;
    }

    public AbstractDescriptor nested() {
        if (isCollection()) {
            Class<?> resolveCollectionElementType = resolveCollectionElementType();
            if (resolveCollectionElementType != null) {
                return nested(resolveCollectionElementType, 0);
            }
            return null;
        }
        if (isArray()) {
            return nested(getType().getComponentType(), 0);
        }
        if (!isMap()) {
            if (Object.class.equals(getType())) {
                return this;
            }
            throw new IllegalStateException("Not a collection, array, or map: cannot resolve nested value types");
        }
        Class<?> resolveMapValueType = resolveMapValueType();
        if (resolveMapValueType != null) {
            return nested(resolveMapValueType, 1);
        }
        return null;
    }

    protected abstract AbstractDescriptor nested(Class<?> cls, int i);

    protected abstract Class<?> resolveCollectionElementType();

    protected abstract Class<?> resolveMapKeyType();

    protected abstract Class<?> resolveMapValueType();
}
