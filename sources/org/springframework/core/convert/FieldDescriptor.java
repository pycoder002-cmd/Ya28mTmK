package org.springframework.core.convert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.GenericCollectionTypeResolver;

/* loaded from: classes2.dex */
class FieldDescriptor extends AbstractDescriptor {
    private final Field field;
    private final int nestingLevel;
    private Map<Integer, Integer> typeIndexesPerLevel;

    private FieldDescriptor(Class<?> cls, Field field, int i, int i2, Map<Integer, Integer> map) {
        super(cls);
        this.field = field;
        this.nestingLevel = i;
        this.typeIndexesPerLevel = map;
        this.typeIndexesPerLevel.put(Integer.valueOf(i), Integer.valueOf(i2));
    }

    public FieldDescriptor(Field field) {
        super(field.getType());
        this.field = field;
        this.nestingLevel = 1;
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    public Annotation[] getAnnotations() {
        return TypeDescriptor.nullSafeAnnotations(this.field.getAnnotations());
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected AbstractDescriptor nested(Class<?> cls, int i) {
        if (this.typeIndexesPerLevel == null) {
            this.typeIndexesPerLevel = new HashMap(4);
        }
        return new FieldDescriptor(cls, this.field, this.nestingLevel + 1, i, this.typeIndexesPerLevel);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveCollectionElementType() {
        return GenericCollectionTypeResolver.getCollectionFieldType(this.field, this.nestingLevel, this.typeIndexesPerLevel);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapKeyType() {
        return GenericCollectionTypeResolver.getMapKeyFieldType(this.field, this.nestingLevel, this.typeIndexesPerLevel);
    }

    @Override // org.springframework.core.convert.AbstractDescriptor
    protected Class<?> resolveMapValueType() {
        return GenericCollectionTypeResolver.getMapValueFieldType(this.field, this.nestingLevel, this.typeIndexesPerLevel);
    }
}
