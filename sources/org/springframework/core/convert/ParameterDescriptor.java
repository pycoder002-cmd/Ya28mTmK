package org.springframework.core.convert;

import java.lang.annotation.Annotation;
import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.core.MethodParameter;

/* loaded from: classes2.dex */
class ParameterDescriptor extends AbstractDescriptor {
    private final MethodParameter methodParameter;

    private ParameterDescriptor(Class<?> cls, MethodParameter methodParameter) {
        super(cls);
        this.methodParameter = methodParameter;
    }

    public ParameterDescriptor(MethodParameter methodParameter) {
        super(methodParameter.getParameterType());
        if (methodParameter.getNestingLevel() != 1) {
            throw new IllegalArgumentException("MethodParameter argument must have its nestingLevel set to 1");
        }
        this.methodParameter = methodParameter;
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    public Annotation[] getAnnotations() {
        return this.methodParameter.getParameterIndex() == -1 ? TypeDescriptor.nullSafeAnnotations(this.methodParameter.getMethodAnnotations()) : TypeDescriptor.nullSafeAnnotations(this.methodParameter.getParameterAnnotations());
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected AbstractDescriptor nested(Class<?> cls, int i) {
        MethodParameter methodParameter = new MethodParameter(this.methodParameter);
        methodParameter.increaseNestingLevel();
        methodParameter.setTypeIndexForCurrentLevel(i);
        return new ParameterDescriptor(cls, methodParameter);
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
