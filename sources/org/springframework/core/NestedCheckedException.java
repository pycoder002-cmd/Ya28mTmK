package org.springframework.core;

/* loaded from: classes2.dex */
public abstract class NestedCheckedException extends Exception {
    private static final long serialVersionUID = 7100714597678207546L;

    static {
        NestedExceptionUtils.class.getName();
    }

    public NestedCheckedException(String str) {
        super(str);
    }

    public NestedCheckedException(String str, Throwable th) {
        super(str, th);
    }

    public boolean contains(Class cls) {
        if (cls == null) {
            return false;
        }
        if (cls.isInstance(this)) {
            return true;
        }
        Throwable cause = getCause();
        if (cause == this) {
            return false;
        }
        if (cause instanceof NestedCheckedException) {
            return ((NestedCheckedException) cause).contains(cls);
        }
        while (cause != null) {
            if (cls.isInstance(cause)) {
                return true;
            }
            if (cause.getCause() == cause) {
                break;
            }
            cause = cause.getCause();
        }
        return false;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return NestedExceptionUtils.buildMessage(super.getMessage(), getCause());
    }

    public Throwable getMostSpecificCause() {
        Throwable rootCause = getRootCause();
        return rootCause != null ? rootCause : this;
    }

    public Throwable getRootCause() {
        Throwable th;
        Throwable cause = getCause();
        Throwable th2 = null;
        while (true) {
            th = th2;
            th2 = cause;
            if (th2 == null || th2 == th) {
                break;
            }
            cause = th2.getCause();
        }
        return th;
    }
}
