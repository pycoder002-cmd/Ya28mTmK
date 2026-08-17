package org.springframework.core.convert;

import java.lang.annotation.Annotation;
import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.core.MethodParameter;

/* loaded from: classes2.dex */
class BeanPropertyDescriptor extends AbstractDescriptor {
    private final Annotation[] annotations;
    private final MethodParameter methodParameter;
    private final Property property;

    private BeanPropertyDescriptor(Class<?> cls, Property property, MethodParameter methodParameter, Annotation[] annotationArr) {
        super(cls);
        this.property = property;
        this.methodParameter = methodParameter;
        this.annotations = annotationArr;
    }

    public BeanPropertyDescriptor(Property property) {
        super(property.getType());
        this.property = property;
        this.methodParameter = property.getMethodParameter();
        this.annotations = property.getAnnotations();
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    public Annotation[] getAnnotations() {
        return this.annotations;
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected AbstractDescriptor nested(Class<?> cls, int i) {
        MethodParameter methodParameter = new MethodParameter(this.methodParameter);
        methodParameter.increaseNestingLevel();
        methodParameter.setTypeIndexForCurrentLevel(i);
        return new BeanPropertyDescriptor(cls, this.property, methodParameter, this.annotations);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveCollectionElementType() {
        return GenericCollectionTypeResolver.getCollectionParameterType(this.methodParameter);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapKeyType() {
        return GenericCollectionTypeResolver.getMapKeyParameterType(this.methodParameter);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapValueType() {
        return GenericCollectionTypeResolver.getMapValueParameterType(this.methodParameter);
    }
}
