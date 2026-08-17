package org.springframework.beans;

import org.springframework.core.NestedRuntimeException;
import org.springframework.util.ObjectUtils;

/* loaded from: classes2.dex */
public abstract class BeansException extends NestedRuntimeException {
    private static final long serialVersionUID = 1;

    public BeansException(String str) {
        super(str);
    }

    public BeansException(String str, Throwable th) {
        super(str, th);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BeansException)) {
            return false;
        }
        BeansException beansException = (BeansException) obj;
        return getMessage().equals(beansException.getMessage()) && ObjectUtils.nullSafeEquals(getCause(), beansException.getCause());
    }

    public int hashCode() {
        return getMessage().hashCode();
    }
}
