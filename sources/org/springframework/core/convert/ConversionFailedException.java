package org.springframework.core.convert;

import org.springframework.util.ObjectUtils;

/* loaded from: classes2.dex */
public final class ConversionFailedException extends ConversionException {
    private final TypeDescriptor sourceType;
    private final TypeDescriptor targetType;
    private final Object value;

    public ConversionFailedException(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2, Object obj, Throwable th) {
        super("Failed to convert from type " + typeDescriptor + " to type " + typeDescriptor2 + " for value '" + ObjectUtils.nullSafeToString(obj) + "'", th);
        this.sourceType = typeDescriptor;
        this.targetType = typeDescriptor2;
        this.value = obj;
    }

    public TypeDescriptor getSourceType() {
        return this.sourceType;
    }

    public TypeDescriptor getTargetType() {
        return this.targetType;
    }

    public Object getValue() {
        return this.value;
    }
}
