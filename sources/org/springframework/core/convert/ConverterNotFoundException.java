package org.springframework.core.convert;

/* loaded from: classes2.dex */
public final class ConverterNotFoundException extends ConversionException {
    private final TypeDescriptor sourceType;
    private final TypeDescriptor targetType;

    public ConverterNotFoundException(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2) {
        super("No converter found capable of converting from type " + typeDescriptor + " to type " + typeDescriptor2);
        this.sourceType = typeDescriptor;
        this.targetType = typeDescriptor2;
    }

    public TypeDescriptor getSourceType() {
        return this.sourceType;
    }

    public TypeDescriptor getTargetType() {
        return this.targetType;
    }
}
