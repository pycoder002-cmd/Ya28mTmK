package org.springframework.core.convert;

import java.lang.annotation.Annotation;
import org.springframework.core.GenericCollectionTypeResolver;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class ClassDescriptor extends AbstractDescriptor {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ClassDescriptor(Class<?> cls) {
        super(cls);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    public Annotation[] getAnnotations() {
        return TypeDescriptor.EMPTY_ANNOTATION_ARRAY;
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected AbstractDescriptor nested(Class<?> cls, int i) {
        return new ClassDescriptor(cls);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveCollectionElementType() {
        return GenericCollectionTypeResolver.getCollectionType(getType());
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapKeyType() {
        return GenericCollectionTypeResolver.getMapKeyType(getType());
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapValueType() {
        return GenericCollectionTypeResolver.getMapValueType(getType());
    }
}
