package org.springframework.core.convert.converter;

import java.util.Set;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.util.Assert;

/* loaded from: classes2.dex */
public interface GenericConverter {

    /* loaded from: classes2.dex */
    public static final class ConvertiblePair {
        private final Class<?> sourceType;
        private final Class<?> targetType;

        public ConvertiblePair(Class<?> cls, Class<?> cls2) {
            Assert.notNull(cls, "Source type must not be null");
            Assert.notNull(cls2, "Target type must not be null");
            this.sourceType = cls;
            this.targetType = cls2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ConvertiblePair.class) {
                return false;
            }
            ConvertiblePair convertiblePair = (ConvertiblePair) obj;
            return this.sourceType.equals(convertiblePair.sourceType) && this.targetType.equals(convertiblePair.targetType);
        }

        public Class<?> getSourceType() {
            return this.sourceType;
        }

        public Class<?> getTargetType() {
            return this.targetType;
        }

        public int hashCode() {
            return (this.sourceType.hashCode() * 31) + this.targetType.hashCode();
        }

        public String toString() {
            return this.sourceType.getName() + " -> " + this.targetType.getName();
        }
    }

    Object convert(Object obj, TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2);

    Set<ConvertiblePair> getConvertibleTypes();
}
