package org.springframework.core.convert.converter;

import org.springframework.core.convert.TypeDescriptor;

/* loaded from: classes2.dex */
public interface ConditionalConverter {
    boolean matches(TypeDescriptor typeDescriptor, TypeDescriptor typeDescriptor2);
}
