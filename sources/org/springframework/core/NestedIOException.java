package org.springframework.core;

import java.io.IOException;

/* loaded from: classes2.dex */
public class NestedIOException extends IOException {
    static {
        NestedExceptionUtils.class.getName();
    }

    public NestedIOException(String str) {
        super(str);
    }

    public NestedIOException(String str, Throwable th) {
        super(str);
        initCause(th);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
    }
}
