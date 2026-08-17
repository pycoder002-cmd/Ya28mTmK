package org.springframework.core.convert;

import org.springframework.core.NestedRuntimeException;

/* loaded from: classes2.dex */
public abstract class ConversionException extends NestedRuntimeException {
    public ConversionException(String str) {
        super(str);
    }

    public ConversionException(String str, Throwable th) {
        super(str, th);
    }
}
